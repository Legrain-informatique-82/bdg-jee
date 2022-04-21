package fr.legrain.generation.service;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import fr.legrain.bdg.documents.service.remote.ITaPrelevementServiceRemote;
import fr.legrain.bdg.generation.service.remote.IGenereDocPrelevementVersPrelevementServiceRemote;
import fr.legrain.bdg.lib.osgi.Const;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.dto.IInfosDocumentTiers;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.document.model.TaEtat;
import fr.legrain.document.model.TaPrelevement;
import fr.legrain.document.model.TaInfosPrelevement;
import fr.legrain.document.model.TaLPrelevement;
import fr.legrain.document.model.TaPrelevement;
import fr.legrain.hibernate.multitenant.ServerTenantInterceptor;
import fr.legrain.lib.data.LibDate;


@Stateless
@DeclareRoles("admin")
@Interceptors(ServerTenantInterceptor.class)
public  class GenereDocPrelevementVersPrelevement extends AbstractGenereDocVersPrelevement implements IGenereDocPrelevementVersPrelevementServiceRemote{
	private @EJB ITaPrelevementServiceRemote taPrelevementService;
	protected TaPrelevement castDocumentSource() {
		return (TaPrelevement)ds;
	}
	
	protected TaPrelevement castDocumentDest() {
		return (TaPrelevement)dd;
	}
	
	@Override
	public IDocumentTiers copieDocumentSpecifique(IDocumentTiers ds, IDocumentTiers dd
			,Boolean ligneSeparatrice,String libelleSeparateur) {
		try {
			((TaPrelevement)dd).setTaTPaiement(((TaPrelevement)ds).getTaTPaiement());
			((TaPrelevement)dd).setDateEchDocument(((TaPrelevement)ds).getDateEchDocument());
//			((TaPrelevement)dd).setRegleDocument(BigDecimal.valueOf(0));
			((TaPrelevement)dd).setRemHtDocument(((TaPrelevement)ds).getRemHtDocument());
			((TaPrelevement)dd).setTxRemHtDocument(((TaPrelevement)ds).getTxRemHtDocument());
			((TaPrelevement)dd).setRemTtcDocument(((TaPrelevement)ds).getRemTtcDocument());
			((TaPrelevement)dd).setTxRemTtcDocument(((TaPrelevement)ds).getTxRemTtcDocument());
			((TaPrelevement)dd).setNbEDocument(((TaPrelevement)ds).getNbEDocument());
			((TaPrelevement)dd).setTtc(((TaPrelevement)ds).getTtc());
			//((TaPrelevement)dd).setExport(((TaPrelevement)ds).getExport());
			((TaPrelevement)dd).setCommentaire(((TaPrelevement)ds).getCommentaire());
			
			TaEtat etatLigne=taEtatService.documentEncours(null);
			codeDejaGenere=false;
			
			Map<String, String> paramsCode = new LinkedHashMap<String, String>();
			if((codeTiers==null || codeTiers.isEmpty()) 
					&& ((TaPrelevement)dd).getTaTiers()!=null)
				codeTiers=((TaPrelevement)dd).getTaTiers().getCodeTiers();
			paramsCode.put(Const.C_NOMFOURNISSEUR, codeTiers);

			paramsCode.put(Const.C_DATEDOCUMENT, LibDate.dateToString(((TaPrelevement)dd).getDateDocument()));				
			if(isGenereCode()) {
				((TaPrelevement)dd).setCodeDocument(taPrelevementService.genereCode(paramsCode));
				codeDejaGenere=true;
			}
						

			for (ILigneDocumentTiers ligne : ((TaPrelevement)ds).getLignes()) {
				TaLPrelevement temp = (TaLPrelevement)copieLigneDocument(ligne, new TaLPrelevement()); 
				temp.setTaDocument(((TaPrelevement)dd));
				((TaPrelevement)dd).addLigne(temp);
			}

			TaInfosPrelevement infos = (TaInfosPrelevement)copieInfosDocument(((TaPrelevement)ds).getTaInfosDocument(),new TaInfosPrelevement());
			infos.setTaDocument(((TaPrelevement)dd));
			((TaPrelevement)dd).setTaInfosDocument(infos);

			return dd;
		} catch (Exception e) {
			logger.error("",e);
		}
		return dd;
	}

	@Override
	public IInfosDocumentTiers copieInfosDocumentSpecifique(IInfosDocumentTiers is, IInfosDocumentTiers id) {
		return id;
	}

	@Override
	public ILigneDocumentTiers copieLigneDocumentSpecifique(ILigneDocumentTiers ls, ILigneDocumentTiers ld) {
		return ld;
	}

	@Override
	public IDocumentTiers genereDocumentSpecifique(IDocumentTiers ds, IDocumentTiers dd,String codeTiers, boolean generationModele,boolean genereCode) {
		
		TaPrelevement documentInit = ((TaPrelevement)ds);
		try {
			if(documentInit!=null){
				documentInit.setLegrain(true);
				if(genereCode && !codeDejaGenere )((TaPrelevement)dd).setCodeDocument(taPrelevementService.genereCode(null));
				((TaPrelevement)dd).setCommentaire(commentaire);
				taPrelevementService.calculDateEcheanceAbstract(((TaPrelevement)dd),taCPaiement.getReportCPaiement(),taCPaiement.getFinMoisCPaiement());
				((TaPrelevement)dd).calculeTvaEtTotaux();	
			}
			return dd;
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
	
	public boolean dejaGenere(IDocumentTiers ds) {
		//on peut generer autant de document que l'on veut si ils sont du meme type
		return false;
	}

	@Override
	public String creationRequeteDejaGenere(IDocumentTiers ds) {
		return null;
	}

	@Override
	public void setRecupLibelleSeparateurDoc(boolean b) {
		// TODO Auto-generated method stub
		super.setRecupLibelleSeparateurDoc(b);
	}

	@Override
	public void setLigneSeparatrice(Boolean ligneSeparatrice) {
		// TODO Auto-generated method stub
		super.setLigneSeparatrice(ligneSeparatrice);
	}

	@Override
	public IDocumentTiers genereDocument(IDocumentTiers document, IDocumentTiers docGenere, String codeTiers, boolean b,
			boolean generationModele,boolean genereCode,boolean multiple) {
		// TODO Auto-generated method stub
		return super.genereDocument(document, docGenere, codeTiers, b, generationModele, genereCode, multiple);
	}

	@Override
	public void setRelationDocument(boolean relationDocument) {
		super.setRelationDocument(relationDocument);
		
	}



}
