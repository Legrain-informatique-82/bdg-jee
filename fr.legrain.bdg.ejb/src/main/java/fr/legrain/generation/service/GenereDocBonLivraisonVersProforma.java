package fr.legrain.generation.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import fr.legrain.bdg.documents.service.remote.ITaProformaServiceRemote;
import fr.legrain.bdg.generation.service.remote.IGenereDocBonLivraisonVersProformaServiceRemote;
import fr.legrain.bdg.lib.osgi.Const;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.dto.IInfosDocumentTiers;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.document.dto.TaLigneALigneSupplementDTO;
import fr.legrain.document.model.TaBonliv;
import fr.legrain.document.model.TaEtat;
import fr.legrain.document.model.TaInfosProforma;
import fr.legrain.document.model.TaLBonliv;
import fr.legrain.document.model.TaLProforma;
import fr.legrain.document.model.TaLProforma;
import fr.legrain.document.model.TaLigneALigne;
import fr.legrain.document.model.TaProforma;
import fr.legrain.document.model.TaRDocument;
import fr.legrain.hibernate.multitenant.ServerTenantInterceptor;
import fr.legrain.lib.data.LibDate;


@Stateless
@DeclareRoles("admin")
@Interceptors(ServerTenantInterceptor.class)
public  class GenereDocBonLivraisonVersProforma extends AbstractGenereDocVersProforma implements IGenereDocBonLivraisonVersProformaServiceRemote{
	private @EJB ITaProformaServiceRemote taProformaService;
	protected TaBonliv castDocumentSource() {
		return (TaBonliv)ds;
	}
	
	protected TaProforma castDocumentDest() {
		return (TaProforma)dd;
	}
	
	@Override
	public IDocumentTiers copieDocumentSpecifique(IDocumentTiers ds, IDocumentTiers dd
			,Boolean ligneSeparatrice,String libelleSeparateur) {
		try {
			((TaProforma)dd).setTaTPaiement(((TaBonliv)ds).getTaTPaiement());
//			((TaProforma)dd).setDateEchDocument(((TaBonliv)ds).getDateEchDocument());
			((TaProforma)dd).setRegleDocument(BigDecimal.valueOf(0));
			((TaProforma)dd).setRemHtDocument(((TaBonliv)ds).getRemHtDocument());
			if(multiple)((TaProforma)dd).setTxRemHtDocument(BigDecimal.valueOf(0));else((TaProforma)dd).setTxRemHtDocument(((TaBonliv)ds).getTxRemHtDocument());
			((TaProforma)dd).setRemTtcDocument(((TaBonliv)ds).getRemTtcDocument());
			((TaProforma)dd).setTxRemTtcDocument(((TaBonliv)ds).getTxRemTtcDocument());
			((TaProforma)dd).setNbEDocument(((TaBonliv)ds).getNbEDocument());
			((TaProforma)dd).setTtc(((TaBonliv)ds).getTtc());
//			((TaProforma)dd).setExport(((TaBonliv)ds).getExport());
			((TaProforma)dd).setCommentaire(((TaBonliv)ds).getCommentaire());
			
			
			TaEtat etatLigne=taEtatService.documentEncours(null);
			codeDejaGenere=false;
			

				Map<String, String> paramsCode = new LinkedHashMap<String, String>();
				if((codeTiers==null || codeTiers.isEmpty()) 
						&& ((TaProforma)dd).getTaTiers()!=null)
					codeTiers=((TaProforma)dd).getTaTiers().getCodeTiers();
				paramsCode.put(Const.C_NOMFOURNISSEUR, codeTiers);

				paramsCode.put(Const.C_DATEDOCUMENT, LibDate.dateToString(((TaProforma)dd).getDateDocument()));				
				if(isGenereCode()) {
					((TaProforma)dd).setCodeDocument(taProformaService.genereCode(paramsCode));
				codeDejaGenere=true;
				}

				if(!generationModele) {
				for (ILigneDocumentTiers ligne : ((TaBonliv)ds).getLignes()) {
					if(ligne.getTaArticle()==null) {
						TaLProforma temp = (TaLProforma)copieLigneDocument(ligne, new TaLProforma()); 
						temp.setTaDocument(((TaProforma)dd));
						((TaProforma)dd).addLigne(temp);
					}
					else {
					if(ligne.getGenereLigne()) {
						//récupérer la liste lignesALignes
						ligne.setTaLigneALignes(new HashSet<TaLigneALigne>(taLigneALigneService.findByLDocument(ligne)));

						if(ligne.getQteGenere()==null)ligne.setQteGenere(ligne.getQteLDocument());
						for (TaLigneALigneSupplementDTO lSupp : ligne.getListeSupplement()) {
							TaLProforma temp = (TaLProforma)copieLigneDocument(ligne, new TaLProforma());
							BigDecimal ratio=recupRatioQte(temp.getQteLDocument(),lSupp.getQteRecue());
							if(lSupp.getQteRecue()!=null)temp.setQteLDocument(lSupp.getQteRecue());
							if(ligne.getUniteGenere()!=null)temp.setU1LDocument(ligne.getUniteGenere());
							//calculer la proportion de la qté 2 en fonction de la Qté1 générée
							if(temp.getQte2LDocument()!=null && temp.getQte2LDocument().compareTo(BigDecimal.ZERO)!=0) {
								temp.setQte2LDocument(temp.getQte2LDocument().multiply(ratio));
							}
							if(lSupp.getNumLot()==null ||lSupp.getNumLot().isEmpty()) {
								Map<String, String> params = new LinkedHashMap<String, String>();
								if(((TaProforma)dd)!=null && ((TaProforma)dd).getTaTiers()!=null)params.put(Const.C_NOMFOURNISSEUR, ((TaProforma)dd).getTaTiers().getNomTiers());
								if(((TaProforma)dd)!=null && ((TaProforma)dd).getTaTiers()!=null)params.put(Const.C_CODEFOURNISSEUR, ((TaProforma)dd).getTaTiers().getCodeTiers());

								params.put(Const.C_CODEDOCUMENT, ((TaProforma)dd).getCodeDocument());
								params.put(Const.C_DATEDOCUMENT, LibDate.dateToString(((TaProforma)dd).getDateDocument()));

							}

							temp.setTaDocument(((TaProforma)dd));
							TaLigneALigne lal = new TaLigneALigne();
							lal.setNumLot(lSupp.getNumLot());
							lal.setPrixU(ligne.getPrixULDocument());
							lal.setQte(ligne.getQteLDocument());
							lal.setQteRecue(lSupp.getQteRecue());
							lal.setUnite(ligne.getU1LDocument());
							lal.setUniteRecue(ligne.getUniteGenere());
							lal.setTaLBonliv((TaLBonliv) ligne);
							lal.setTaLProforma(temp);
							lal.setIdLigneSrc(ligne.getIdLDocument());
							temp.getTaLigneALignes().add(lal);
							((TaLBonliv) ligne).getTaLigneALignes().add(lal);
							temp.addREtatLigneDoc(etatLigne);
							if(temp.getTaArticle()!=null)temp.setTaArticle(taArticleService.findByIdAvecLazy(temp.getTaArticle().getIdArticle(), false));
							((TaProforma)dd).addLigne(temp);
						}

						//génération de la ligne existante si qté # de 0
						if(ligne.getQteGenere()!=null && ligne.getQteGenere().compareTo(BigDecimal.ZERO)!=0) {
							BigDecimal qteTotale=ligne.getQteGenere();
							TaLProforma temp = (TaLProforma)copieLigneDocument(ligne, new TaLProforma());
							for (TaLigneALigneSupplementDTO o : ligne.getListeLigneAIntegrer()) {
								//s'il y a des lignes à regrouper sur cette ligne
								qteTotale=qteTotale.add(o.getQteRecue());
							}

							BigDecimal ratio=recupRatioQte(temp.getQteLDocument(),qteTotale);
							if(qteTotale!=null)temp.setQteLDocument(qteTotale);
							if(ligne.getUniteGenere()!=null)temp.setU1LDocument(ligne.getUniteGenere());
							//calculer la proportion de la qté 2 en fonction de la Qté1 générée
							if(temp.getQte2LDocument()!=null && temp.getQte2LDocument().compareTo(BigDecimal.ZERO)!=0) {
								temp.setQte2LDocument(temp.getQte2LDocument().multiply(ratio));
							}


							temp.setTaDocument(((TaProforma)dd));
							TaLigneALigne lal = new TaLigneALigne();
							if(ligne.getTaLot()!=null)lal.setNumLot(ligne.getTaLot().getNumLot());
							lal.setPrixU(ligne.getPrixULDocument());
							lal.setQte(ligne.getQteLDocument());
							lal.setQteRecue(ligne.getQteGenere());
							lal.setUnite(ligne.getU1LDocument());
							lal.setUniteRecue(ligne.getUniteGenere());
							lal.setTaLBonliv((TaLBonliv) ligne);
							lal.setTaLProforma(temp);
							lal.setIdLigneSrc(ligne.getIdLDocument());
							temp.getTaLigneALignes().add(lal);
							((TaLBonliv) ligne).getTaLigneALignes().add(lal);
							temp.addREtatLigneDoc(etatLigne);

							for (TaLigneALigneSupplementDTO o : ligne.getListeLigneAIntegrer()) {
								//enregistrer le lien entre les lignes en recherchant la vraie ligne d'origine
								TaLigneALigne lal2 = new TaLigneALigne();

								TaLBonliv ligneReel=(TaLBonliv) o.getLigne();
								lal2.setNumLot(ligne.getNumlotGenere());
								lal2.setPrixU(ligneReel.getPrixULDocument());
								lal2.setQte(ligneReel.getQteLDocument());
								lal2.setQteRecue(o.getQteRecue());
								lal2.setUnite(ligneReel.getU1LDocument());
								lal2.setUniteRecue(ligne.getUniteGenere());
								lal2.setTaLBonliv((TaLBonliv) ligneReel);
								lal2.setTaLProforma(temp);
								lal2.setIdLigneSrc(ligneReel.getIdLDocument());
								temp.getTaLigneALignes().add(lal2);
								((TaLBonliv) ligne).getTaLigneALignes().add(lal2);
								temp.addREtatLigneDoc(etatLigne);
							}
							if(temp.getTaArticle()!=null)temp.setTaArticle(taArticleService.findByIdAvecLazy(temp.getTaArticle().getIdArticle(), false));
							((TaProforma)dd).addLigne(temp);
						}
					}
					}
				}
			}else {			

			for (ILigneDocumentTiers ligne : ((TaBonliv)ds).getLignes()) {
				TaLProforma temp = (TaLProforma)copieLigneDocument(ligne, new TaLProforma()); 
				temp.setTaDocument(((TaProforma)dd));
				((TaProforma)dd).addLigne(temp);
			}
			}
			TaInfosProforma infos = (TaInfosProforma)copieInfosDocument(((TaBonliv)ds).getTaInfosDocument(),new TaInfosProforma());
			infos.setTaDocument(((TaProforma)dd));
			((TaProforma)dd).setTaInfosDocument(infos);

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
		TaBonliv documentInit = ((TaBonliv)ds);
		try {
			
			if(documentInit!=null){
				documentInit.setLegrain(true);
				if(genereCode && !codeDejaGenere )((TaProforma)dd).setCodeDocument(taProformaService.genereCode(null));
				((TaProforma)dd).setCommentaire(commentaire);
				taProformaService.calculDateEcheanceAbstract(((TaProforma)dd),taCPaiement.getReportCPaiement(),taCPaiement.getFinMoisCPaiement());
				if(ds.getRelationDocument()){
				TaRDocument taRDocument = new TaRDocument();
				taRDocument.setTaProforma(((TaProforma)dd));
				taRDocument.setTaBonliv(documentInit);
				taRDocument.setIdSrc(documentInit.getIdDocument());
				((TaProforma)dd).getTaRDocuments().add(taRDocument);
				}
				((TaProforma)dd).calculeTvaEtTotaux();	
			}
			return dd;
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
	
	public String creationRequeteDejaGenere(IDocumentTiers ds) {
		int idDoc = ds.getIdDocument();
		String jpql = "select x from TaRDocument x where x.taBonliv.idDocument="+idDoc+" and x.taProforma is not null";
		return jpql;
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
