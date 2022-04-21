package fr.legrain.documents.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.DeclareRoles;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;

import org.apache.log4j.Logger;
import org.hibernate.OptimisticLockException;

import fr.legrain.article.dao.ITvaDAO;
import fr.legrain.bdg.article.service.remote.ITaArticleServiceRemote;
import fr.legrain.bdg.controle.service.remote.ITaGenCodeExServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaAvisEcheanceServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaFactureServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaLEcheanceServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaLigneALigneEcheanceServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaTLigneServiceRemote;
import fr.legrain.bdg.dossier.service.remote.ITaAutorisationsDossierServiceRemote;
import fr.legrain.bdg.dossier.service.remote.ITaInfoEntrepriseServiceRemote;
import fr.legrain.bdg.edition.osgi.EditionProgrammeDefaut;
import fr.legrain.bdg.edition.service.remote.ITaEditionServiceRemote;
import fr.legrain.bdg.lib.server.osgi.BdgProperties;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.model.mapping.mapper.TaAvisEcheanceMapper;
import fr.legrain.bdg.tiers.service.remote.ITaTiersServiceRemote;
import fr.legrain.birt.BirtUtil;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.data.AbstractDocumentService;
import fr.legrain.document.dashboard.dto.TaArticlesParTiersDTO;
import fr.legrain.document.dto.DocumentChiffreAffaireDTO;
import fr.legrain.document.dto.DocumentDTO;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.document.dto.TaAvisEcheanceDTO;
import fr.legrain.document.dto.TaLAvisEcheanceDTO;
import fr.legrain.document.model.LigneTva;
import fr.legrain.document.model.SWTDocument;
import fr.legrain.document.model.TaAbonnement;
import fr.legrain.document.model.TaAvisEcheance;
import fr.legrain.document.model.TaEtat;
import fr.legrain.document.model.TaFacture;
import fr.legrain.document.model.TaInfosAvisEcheance;
import fr.legrain.document.model.TaLAvisEcheance;
import fr.legrain.document.model.TaLEcheance;
import fr.legrain.document.model.TaLFacture;
import fr.legrain.document.model.TaMiseADisposition;
import fr.legrain.document.model.TaRAvoir;
import fr.legrain.document.model.TaRReglement;
import fr.legrain.document.model.TaTLigne;
import fr.legrain.documents.dao.IAvisEcheanceDAO;
import fr.legrain.dossier.model.TaPreferences;
import fr.legrain.droits.model.IModulesProgramme;
import fr.legrain.droits.service.SessionInfo;
import fr.legrain.edition.model.TaActionEdition;
import fr.legrain.edition.model.TaEdition;
import fr.legrain.hibernate.multitenant.SchemaResolver;
import fr.legrain.hibernate.multitenant.ServerTenantInterceptor;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.dao.ICPaiementDAO;
import fr.legrain.tiers.dao.ITCPaiementDAO;
import fr.legrain.tiers.model.TaCPaiement;
import fr.legrain.tiers.model.TaTCPaiement;
import fr.legrain.tiers.model.TaTiers;

/**
 * Session Bean implementation class TaAvisEcheanceBean
 */
@Stateless
@DeclareRoles("admin")
@Interceptors(ServerTenantInterceptor.class)
public class TaAvisEcheanceService extends AbstractDocumentService<TaAvisEcheance, TaAvisEcheanceDTO> implements ITaAvisEcheanceServiceRemote {

	static Logger logger = Logger.getLogger(TaAvisEcheanceService.class);

	@Inject private IAvisEcheanceDAO dao;
	@Inject private ITvaDAO taTvaDAO;
	@Inject private ITCPaiementDAO taTCPaiementDAO;
	@Inject private ICPaiementDAO taCPaiementDAO;
	@Inject private	SessionInfo sessionInfo;
	@EJB private ITaGenCodeExServiceRemote gencode;
	@EJB private ITaInfoEntrepriseServiceRemote taInfoEntrepriseService;
	@EJB private ITaEditionServiceRemote taEditionService;
	@EJB private ITaTiersServiceRemote taTiersService;
	@EJB private ITaArticleServiceRemote taArticleService;
	@EJB private ITaTLigneServiceRemote taTLigneService;
	@EJB private ITaLEcheanceServiceRemote taLEcheanceService;
	@EJB private ITaLigneALigneEcheanceServiceRemote taLigneALigneEcheanceService;
	@EJB private ITaAutorisationsDossierServiceRemote taAutorisationDossierService;

	/**
	 * Default constructor. 
	 */
	public TaAvisEcheanceService() {
		super(TaAvisEcheance.class,TaAvisEcheanceDTO.class);
		editionDefaut = EditionProgrammeDefaut.EDITION_DEFAUT_AVIS_ECHEANCE_FICHIER;
	}
	
//	public List<TaAvisEcheance> findByAbonnementAndDate(TaStripeSubscription taStripeSubscription, Date dateEcheance) {
//		return dao.findByAbonnementAndDate(taStripeSubscription, dateEcheance);
//	}
	
	public List<TaAvisEcheance> rechercheDocumentSubscription(int idStripeSubscription) {
		return dao.rechercheDocumentSubscription(idStripeSubscription);
	}
	
	public List<TaAvisEcheance> findByCodeTiersAndDateCompteClient(String codeTiers, Date debut, Date fin) {
		return dao.findByCodeTiersAndDateCompteClient(codeTiers, debut, fin);
	}
	
	public List<TaAbonnement> rechercheAbonnementDansAvisEcheance(TaAvisEcheance taAvisEcheance) {
		return dao.rechercheAbonnementDansAvisEcheance(taAvisEcheance);
	}
	
	//////////DASHBOARD////////////
   	//dashboard facture onglet detail des factures
   	public List<DocumentChiffreAffaireDTO> listLigneArticleDTOTiersParTypeEtatDoc(Date dateDebut, Date dateFin, String codeArticle, String codeTiers, String typeEtatDoc, int deltaNbJours){
   		return dao.listLigneArticleDTOTiersParTypeEtatDoc( dateDebut,  dateFin,  codeArticle,  codeTiers,  typeEtatDoc, deltaNbJours);
   	}
	
	
	//////////FIN DASHBOARD/////////
   	
	//SPECIFIQUE ABONNEMENTS
   	public void removeAllRDocumentEvenementAvisEcheance() {
   	   dao.removeAllRDocumentEvenementAvisEcheance();
   	}
   	
	public DocumentChiffreAffaireDTO chiffreAffaireNonTransformeTotalDTOAboEnCours(Date dateDebut, Date dateFin, String codeTiers){
		return dao.chiffreAffaireNonTransformeTotalDTOAboEnCours(dateDebut, dateFin, codeTiers);
	}
	public DocumentChiffreAffaireDTO chiffreAffaireNonTransformeARelancerTotalDTOAboEnCours(Date dateDebut,Date dateFin, int deltaNbJours,String codeTiers){
		return dao.chiffreAffaireNonTransformeARelancerTotalDTOAboEnCours(dateDebut, dateFin, deltaNbJours, codeTiers);
	}
	public long countDocumentNonTransformeAboEnCours(Date debut, Date fin, String codeTiers) {
		return dao.countDocumentNonTransformeAboEnCours(debut, fin, codeTiers);
	}
	public long countDocumentNonTransformeARelancerAboEnCours(Date debut, Date fin, int deltaNbJours,String codeTiers) {
		return dao.countDocumentNonTransformeARelancerAboEnCours(debut, fin, deltaNbJours, codeTiers);
	}
	public List<DocumentDTO> findDocumentNonTransfosDTOAboEnCours(Date dateDebut, Date dateFin,String codeTiers){
		return dao.findDocumentNonTransfosDTOAboEnCours(dateDebut, dateFin, codeTiers);
	}
	public List<DocumentDTO> findDocumentNonTransfosARelancerDTOAboEnCours(Date dateDebut, Date dateFin, int deltaNbJours,String codeTiers){
		return dao.findDocumentNonTransfosARelancerDTOAboEnCours(dateDebut, dateFin, deltaNbJours, codeTiers);
	}
	
	/**
	 * @author yann
	 * février 2022 
	 * pour supprimer les avis d'échéances vieux de X mois et plus
	 */
	public Integer supprimeAvisVieuxXmois(Integer nbMois) {
		Integer nbSuppression = 0;		
		Date dateFin =  new Date();
		//Date dateDebut = dateNow;
		Date dateDebut = new Date();
		//j'invente une date très vielle pour être sur de choper tous les avis
		Calendar calendar = new GregorianCalendar(1860,01,01);
		dateDebut = calendar.getTime();
		
		LocalDateTime localDateTimeNow = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime localDateTimeNowMoinsXMois = localDateTimeNow.minusMonths(nbMois);
		dateFin = Date.from(localDateTimeNowMoinsXMois.atZone(ZoneId.systemDefault()).toInstant());
		//je chope tous les avis d'échéance qui ont une date document comprise entre janvier 1860 et aujourd'hui moins X mois
		List<DocumentDTO> liste = findAllDTOPeriode(dateDebut, dateFin, null);
		System.out.println("Suppréssion des avis d'échéances compris entre "+dateDebut+" et "+dateFin+" !");
		for (DocumentDTO taAvisEcheanceDTO : liste) {
			try {
				TaAvisEcheance avis = findByIdDocument(taAvisEcheanceDTO.getId());
				remove(avis);
				System.out.println("Suppréssion de l'avis d'échéance "+avis.getCodeDocument()+" avec date document "+avis.getDateDocument());
				nbSuppression++;
			} catch (FinderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				catch (RemoveException e) {
//				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return nbSuppression;
	}
	
	
	public TaAvisEcheance creerAvisEcheance(List<TaLEcheance> listeLigneEcheance) {
		try {
			TaAvisEcheance taAvisEcheance = new TaAvisEcheance();
			TaAvisEcheanceDTO dto = new TaAvisEcheanceDTO();
			TaInfosAvisEcheance taInfosDocument = new TaInfosAvisEcheance();
			LgrDozerMapper<TaAvisEcheanceDTO,TaAvisEcheance> mapperUIToModel = new LgrDozerMapper<>();
			LgrDozerMapper<TaLAvisEcheanceDTO,TaLAvisEcheance> mapperUIToModelLigne = new LgrDozerMapper<>();
			TaTiers tiers = listeLigneEcheance.get(0).getTaAbonnement().getTaTiers();
			if(taInfosDocument==null) {
					taInfosDocument = new TaInfosAvisEcheance();
			}
			if(taAvisEcheance!=null) {
				taInfosDocument.setTaDocumentGeneral(taAvisEcheance);
				taAvisEcheance.setTaInfosDocument(taInfosDocument);
			}
		
			//taGenCodeExService.libereVerrouTout(new ArrayList<String>(SessionListener.getSessions().keySet()));
			dto.setCodeDocument(genereCode(null)); //ejb
			//validateUIField(Const.C_CODE_TIERS,dto.getCodeTiers());//permet de verrouiller le code genere
		
			dto.setIdTiers(dto.getIdTiers());
			dto.setCodeTiers(dto.getCodeTiers());
			taAvisEcheance.setTaTiers(tiers);
			dto.setCodeCompta(taAvisEcheance.getTaTiers().getCodeCompta());
			dto.setCompte(taAvisEcheance.getTaTiers().getCompte());
			taAvisEcheance.setNbDecimalesPrix(2);
			taAvisEcheance.setNbDecimalesQte(2);
			taAvisEcheance.getTaInfosDocument().setCodeCompta(taAvisEcheance.getTaTiers().getCodeCompta());
			taAvisEcheance.getTaInfosDocument().setCompte(taAvisEcheance.getTaTiers().getCompte());
			
			//rajout yann
			if(taAvisEcheance.getTaTiers().getTaAdresse() != null) {
				taAvisEcheance.getTaInfosDocument().setAdresse1(taAvisEcheance.getTaTiers().getTaAdresse().getAdresse1Adresse());
				taAvisEcheance.getTaInfosDocument().setAdresse2(taAvisEcheance.getTaTiers().getTaAdresse().getAdresse2Adresse());
				taAvisEcheance.getTaInfosDocument().setAdresse3(taAvisEcheance.getTaTiers().getTaAdresse().getAdresse3Adresse());
				taAvisEcheance.getTaInfosDocument().setAdresse1Liv(taAvisEcheance.getTaTiers().getTaAdresse().getAdresse1Adresse());
				taAvisEcheance.getTaInfosDocument().setAdresse2Liv(taAvisEcheance.getTaTiers().getTaAdresse().getAdresse2Adresse());
				taAvisEcheance.getTaInfosDocument().setAdresse3Liv(taAvisEcheance.getTaTiers().getTaAdresse().getAdresse3Adresse());
				
				taAvisEcheance.getTaInfosDocument().setCodepostal(taAvisEcheance.getTaTiers().getTaAdresse().getCodepostalAdresse());
				taAvisEcheance.getTaInfosDocument().setCodepostalLiv(taAvisEcheance.getTaTiers().getTaAdresse().getCodepostalAdresse());
				
				taAvisEcheance.getTaInfosDocument().setVille(taAvisEcheance.getTaTiers().getTaAdresse().getVilleAdresse());
				taAvisEcheance.getTaInfosDocument().setVilleLiv(taAvisEcheance.getTaTiers().getTaAdresse().getVilleAdresse());
				taAvisEcheance.getTaInfosDocument().setPays(taAvisEcheance.getTaTiers().getTaAdresse().getPaysAdresse());
				taAvisEcheance.getTaInfosDocument().setPaysLiv(taAvisEcheance.getTaTiers().getTaAdresse().getPaysAdresse());
			}
			
			if(taAvisEcheance.getTaTiers().getTaTCivilite() != null) {
				taAvisEcheance.getTaInfosDocument().setCodeTCivilite(taAvisEcheance.getTaTiers().getTaTCivilite().getCodeTCivilite());
			}
			
			if(taAvisEcheance.getTaTiers().getTaTEntite() != null) {
				taAvisEcheance.getTaInfosDocument().setCodeTEntite(taAvisEcheance.getTaTiers().getTaTEntite().getCodeTEntite());
			}
			
			if(taAvisEcheance.getTaTiers().getTaTTvaDoc() != null) {
				taAvisEcheance.getTaInfosDocument().setCodeTTvaDoc(taAvisEcheance.getTaTiers().getTaTTvaDoc().getCodeTTvaDoc());
			}
						
			
			
			taAvisEcheance.getTaInfosDocument().setNomTiers(taAvisEcheance.getTaTiers().getNomTiers());
			taAvisEcheance.getTaInfosDocument().setPrenomTiers(taAvisEcheance.getTaTiers().getPrenomTiers());
			taAvisEcheance.getTaInfosDocument().setSurnomTiers(taAvisEcheance.getTaTiers().getSurnomTiers());
			if(taAvisEcheance.getTaTiers().getTaEntreprise() != null) {
				taAvisEcheance.getTaInfosDocument().setNomEntreprise(taAvisEcheance.getTaTiers().getTaEntreprise().getNomEntreprise());
			}
			
			
			
			
			dto.setLibelleDocument("Avis d'échéance");
			
			mapperUIToModel.map(dto, taAvisEcheance);
			
			TaLAvisEcheance l = null;
			TaTLigne taTLigne = taTLigneService.findByCode(SWTDocument.C_CODE_T_LIGNE_H);
			for (TaLEcheance ech : listeLigneEcheance) {
				l = new TaLAvisEcheance();
				l.setTaArticle(taArticleService.findById(ech.getTaArticle().getIdArticle()));
				l.setTaTLigne(taTLigne);
				//l.setTaLEcheance(ech);
				l.setQteLDocument(ech.getQteLDocument());
				
				if(ech.getCoefMultiplicateur() != null) {
					l.setQteLDocument(l.getQteLDocument().multiply(ech.getCoefMultiplicateur()));
				}
				
				l.setLibLDocument(ech.getLibLDocument());
				l.setPrixULDocument(ech.getPrixULDocument());
				l.setMtHtLDocument(ech.getMtHtLDocument());
				l.setMtTtcLDocument(ech.getMtTtcLDocument());
				//rajout yann
//				l.setQte2LDocument();
//				l.setU2LDocument();
				l.setCompteLDocument(l.getTaArticle().getNumcptArticle());
				l.setU1LDocument(ech.getU1LDocument());
				//l.setCodeTTvaLDocument();
				l.setRemHtLDocument(ech.getRemHtLDocument());
				l.setRemTxLDocument(ech.getRemTxLDocument());
				//l.setLibTvaLDocument();
				
				//l.setTauxTvaLDocument(ech.getTauxTvaLDocument());
				l.setCodeTvaLDocument(l.getTaArticle().getTaTva().getCodeTva());
				l.setTauxTvaLDocument(l.getTaArticle().getTaTva().getTauxTva());
				taAvisEcheance.addLigne(l);
				l.setTaDocument(taAvisEcheance);
				
				//ajout d'une liaison entre la ligne d'échéance et la ligne d'avis
				//ech.setTaLAvisEcheance(l);
				
				//ligne de commentaire sur la période
				l = new TaLAvisEcheance();
				l.setTaTLigne(taTLigne);
				//l.setTaLEcheance(ech);
				l.setLibLDocument("Période du "+LibDate.dateToString(ech.getDebutPeriode())+" au "+LibDate.dateToString(ech.getFinPeriode()));
				taAvisEcheance.addLigne(l);
				l.setTaDocument(taAvisEcheance);
				
				if(ech.getTaLAbonnement()!=null) {
					if(ech.getTaLAbonnement().getComplement1()!=null) {
						l = new TaLAvisEcheance();
						l.setTaTLigne(taTLigne);
						//l.setTaLEcheance(ech);
						l.setLibLDocument(ech.getTaLAbonnement().getComplement1());
						taAvisEcheance.addLigne(l);
						l.setTaDocument(taAvisEcheance);
					}
					if(ech.getTaLAbonnement().getComplement2()!=null) {
						l = new TaLAvisEcheance();
						l.setTaTLigne(taTLigne);
						//l.setTaLEcheance(ech);
						l.setLibLDocument(ech.getTaLAbonnement().getComplement2());
						taAvisEcheance.addLigne(l);
						l.setTaDocument(taAvisEcheance);
					}
					if(ech.getTaLAbonnement().getComplement3()!=null) {
						l = new TaLAvisEcheance();
						l.setTaTLigne(taTLigne);
						//l.setTaLEcheance(ech);
						l.setLibLDocument(ech.getTaLAbonnement().getComplement3());
						taAvisEcheance.addLigne(l);
						l.setTaDocument(taAvisEcheance);
					}
				}
				
				
			}
			taAvisEcheance.calculeTvaEtTotaux();
			taAvisEcheance = merge(taAvisEcheance);
			
//			for (TaLEcheance ech : listeLigneEcheance) {
//				for (TaLAvisEcheance taLAvis : taAvisEcheance.getLignes()) {
//					if(taLAvis.getNumLigneLDocument() == ech.getTaLAvisEcheance().getNumLigneLDocument()) {
//						ech.setTaLAvisEcheance(taLAvis);
//					}
//				}
//				
//				ech = taLEcheanceService.merge(ech);
//			}
			
			
			return taAvisEcheance;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void calculeTvaEtTotaux(TaAvisEcheance doc){
		calculTvaTotal(doc);
		calculTotaux(doc);
	}
	
	/**
	 * Calcul des totaux de la facture
	 */
	public void calculTotaux(TaAvisEcheance doc) {
		
//			    MT_TVA Numeric(15,2),
			doc.setMtHtCalc(new BigDecimal(0));
			doc.setNetHtCalc(new BigDecimal(0));
			doc.setMtTtcCalc(new BigDecimal(0));
			doc.setMtTtcAvantRemiseGlobaleCalc(new BigDecimal(0));
			for (Object ligne : doc.getLignes()) {
				if(((TaLAvisEcheance)ligne).getTaTLigne().getCodeTLigne().equals(SWTDocument.C_CODE_T_LIGNE_H)) {
					if(((TaLAvisEcheance)ligne).getMtHtLApresRemiseGlobaleDocument()!=null)
						doc.setNetHtCalc(doc.getNetHtCalc().add(((TaLAvisEcheance)ligne).getMtHtLApresRemiseGlobaleDocument()));
					if(((TaLAvisEcheance)ligne).getMtTtcLApresRemiseGlobaleDocument()!=null)
						doc.setMtTtcCalc(doc.getMtTtcCalc().add(((TaLAvisEcheance)ligne).getMtTtcLApresRemiseGlobaleDocument()));
					if(((TaLAvisEcheance)ligne).getMtHtLDocument()!=null)
						doc.setMtHtCalc(doc.getMtHtCalc().add(((TaLAvisEcheance)ligne).getMtHtLDocument()));
					if(((TaLAvisEcheance)ligne).getMtTtcLDocument()!=null)
						doc.setMtTtcAvantRemiseGlobaleCalc(doc.getMtTtcAvantRemiseGlobaleCalc().add(((TaLAvisEcheance)ligne).getMtTtcLDocument()));
				}
				
			}
			
			doc.setNetTvaCalc(doc.getMtTtcCalc().subtract(doc.getNetHtCalc()));
			BigDecimal tva = new BigDecimal(0);
			for (LigneTva ligneTva : doc.getLignesTVA()) {
				tva = tva.add(ligneTva.getMtTva());
			}
			if(tva.compareTo(doc.getNetTvaCalc())!=0) {
				logger.error("Montant de la TVA incorrect : "+doc.getNetTvaCalc()+" ** "+tva);
			}
			
			//doc.setNetTtcCalc(doc.getMtTtcCalc().subtract(doc.getMtTtcCalc().multiply(doc.getRemTtcFacture().divide(new BigDecimal(100)))));
			//doc.setNetTtcCalc(doc.getMtTtcCalc().subtract(doc.getMtTtcCalc().multiply(doc.getTxRemTtcDocument().divide(new BigDecimal(100)))));
			doc.setNetTtcCalc(doc.getMtTtcCalc().subtract(doc.getMtTtcCalc().multiply(doc.getTxRemTtcDocument().divide(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP)));
			
			/*
			 * remise HT déjà calculée dans dispatcherTva()
			 */
			//doc.setRemTtcDocument(doc.getMtTtcCalc().subtract(doc.getNetTtcCalc()));
			doc.setRemTtcDocument(doc.getMtTtcCalc().subtract(doc.getNetTtcCalc()).setScale(2,BigDecimal.ROUND_HALF_UP));
			
			doc.setNetAPayer(doc.getNetTtcCalc().subtract(doc.getRegleDocument()));
			
			//TODO A Finir ou a supprimer
//			select sum(f.mt_tva_recup) from calcul_tva_direct(:module,:id_document,:taux_r_ht,:ttc) f into :MTNETTVA;
//			tva=:mtnettva;
//			mt_ttc=:totalttc;
//			mt_tva=:mt_ttc-:mt_ht;
//			if (ttc=1) {
//			       txremiseht = taux_r_ht;
//			       mtnetttc=:mt_ttc - (:mt_ttc*(:txremiseht/100));
//			       MTNETHT=:mtnetttc - :MTNETTVA;
//			       remise_ht =  :totalttc - :mtnetttc ;
//			} else {
//			      txremiseht = taux_r_ht;
//			      MTNETHT=:mt_ht-(:mt_ht*(:txremiseht/100));
//			      mtnetttc=:MTNETHT + :MTNETTVA;
//			      remise_ht = mt_ht - mtnetht;
//			}
//			  txremidoc.settc = taux_r_ttc;
//			  remise_ttc = (:mtnetttc * (:txremidoc.settc/100));
//			  mtnetttc = :mtnetttc -:remise_ttc;
//			  netapayer = :mtnetttc - :regle;
	}

	/**
	 * Reparti le total chaque code TVA sur l'ensemble des lignes concernées par ce code. 
	 */
	public void dispatcherTva(TaAvisEcheance doc) {

		BigDecimal tvaLigne = new BigDecimal(0);
		BigDecimal totalTemp = new BigDecimal(0);

		for (Object ligne : doc.getLignes()) {
			if(((TaLAvisEcheance)ligne).getMtHtLDocument()!=null)
				totalTemp = totalTemp.add(((TaLAvisEcheance)ligne).getMtHtLDocument());
		}
		if(totalTemp!=null && doc.getTxRemHtDocument()!=null)
			doc.setRemHtDocument(totalTemp.multiply(doc.getTxRemHtDocument().divide(new BigDecimal(100))).setScale(2,BigDecimal.ROUND_HALF_UP));	
		
		for (TaLAvisEcheance ligne : doc.getLignes()) {
			if(doc.getTxRemHtDocument()!=null && doc.getTxRemHtDocument().signum()>0 && ligne.getMtHtLDocument()!=null  && ligne.getMtTtcLDocument()!=null) {
				if(doc.getTtc()==1){
					((TaLAvisEcheance)ligne).setMtTtcLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtTtcLDocument().subtract(((TaLAvisEcheance)ligne).getMtTtcLDocument()
							.multiply(doc.getTxRemHtDocument()).divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP)));
					((TaLAvisEcheance)ligne).setMtHtLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtTtcLApresRemiseGlobaleDocument());
					
				}else{
					((TaLAvisEcheance)ligne).setMtHtLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtHtLDocument().subtract(((TaLAvisEcheance)ligne).getMtHtLDocument()
							.multiply(doc.getTxRemHtDocument()).divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP)));
					((TaLAvisEcheance)ligne).setMtTtcLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtHtLApresRemiseGlobaleDocument());	
				}
			}
		}
		
		
		for (LigneTva ligneTva : doc.getLignesTVA()) {

			if (ligneTva.getMtTva()!=null) {
				int lignepasse=1;
				BigDecimal tvaTmp = ligneTva.getMtTva();
				BigDecimal ttcTmp = LibCalcul.arrondi(ligneTva.getMontantTotalTtcAvecRemise());
				BigDecimal htTmp = LibCalcul.arrondi(ligneTva.getMontantTotalHtAvecRemise());

				for (Object ligne : doc.getLignes()) {
					if(((TaLAvisEcheance)ligne).getTaTLigne().getCodeTLigne().equals(SWTDocument.C_CODE_T_LIGNE_H)) {
						if(((TaLAvisEcheance)ligne).getCodeTvaLDocument()!=null && 
								((TaLAvisEcheance)ligne).getCodeTvaLDocument().equals(ligneTva.getCodeTva())){
							if (ligneTva.getMontantTotalHt().signum()==0) 
								tvaLigne = ((TaLAvisEcheance)ligne).getMtHtLDocument().multiply(ligneTva.getTauxTva()).divide(new BigDecimal(100));
							else {
								if  (lignepasse>= ligneTva.getNbLigneDocument()) 
									tvaLigne = tvaTmp;
								else {
									if(doc.getTtc()==1){
										if(LibCalcul.arrondi(ligneTva.getMontantTotalTtcAvecRemise()).signum()<=0)
											tvaLigne=BigDecimal.valueOf(0);
										else
											tvaLigne = (ligneTva.getMtTva().multiply(((TaLAvisEcheance)ligne).getMtTtcLDocument())).divide(LibCalcul.arrondi(ligneTva.getMontantTotalTtcAvecRemise()),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
									}
									else{
										if(LibCalcul.arrondi(ligneTva.getMontantTotalHtAvecRemise()).signum()<=0)
											tvaLigne =BigDecimal.valueOf(0);
										else
											tvaLigne = (ligneTva.getMtTva().multiply(((TaLAvisEcheance)ligne).getMtHtLDocument())).divide(LibCalcul.arrondi(ligneTva.getMontantTotalHtAvecRemise()),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
									}
								}
							}
							tvaTmp =  tvaTmp.subtract(tvaLigne);
							totalTemp = totalTemp.add(((TaLAvisEcheance)ligne).getMtHtLDocument());

							if(doc.getTxRemHtDocument()!=null && doc.getTxRemHtDocument().signum()>0) {
								if  (lignepasse>= ligneTva.getNbLigneDocument()) {
									((TaLAvisEcheance)ligne).setMtHtLApresRemiseGlobaleDocument(htTmp);
									((TaLAvisEcheance)ligne).setMtTtcLApresRemiseGlobaleDocument(ttcTmp);
								} else {
									((TaLAvisEcheance)ligne).setMtHtLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtHtLDocument().subtract(((TaLAvisEcheance)ligne).getMtHtLDocument().multiply(doc.getTxRemHtDocument()).divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP)));
									((TaLAvisEcheance)ligne).setMtTtcLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtTtcLDocument().subtract(((TaLAvisEcheance)ligne).getMtTtcLDocument().multiply(doc.getTxRemHtDocument()).divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP)));
								}
//								ttcTmp =  ttcTmp.subtract(((TaLAvisEcheance)ligne).doc.getMtTtcLFacture());
//								htTmp =  htTmp.subtract(((TaLAvisEcheance)ligne).doc.getMtHtLFacture());
							} else {
								if(doc.getTtc()==1)
									if  (lignepasse>= ligneTva.getNbLigneDocument()) {
										((TaLAvisEcheance)ligne).setMtHtLApresRemiseGlobaleDocument(htTmp);
									}else{
										((TaLAvisEcheance)ligne).setMtHtLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtTtcLDocument().subtract(tvaLigne));
									}
								else
									if  (lignepasse>= ligneTva.getNbLigneDocument()) {
										((TaLAvisEcheance)ligne).setMtTtcLApresRemiseGlobaleDocument(ttcTmp);
									}else {
										((TaLAvisEcheance)ligne).setMtTtcLApresRemiseGlobaleDocument(((TaLAvisEcheance)ligne).getMtHtLDocument().add(tvaLigne));
									}

							}
							ttcTmp =  ttcTmp.subtract(((TaLAvisEcheance)ligne).getMtTtcLApresRemiseGlobaleDocument());
							htTmp =  htTmp.subtract(((TaLAvisEcheance)ligne).getMtHtLApresRemiseGlobaleDocument());

							lignepasse++;
						}
					}
					doc.setRemHtDocument(totalTemp.multiply(doc.getTxRemHtDocument().divide(new BigDecimal(100))));						

//					doc.setRemHtDocument(doc.getRemHtDocument().add(totalTemp.multiply(txRemHtDocument.divide(new BigDecimal(100)))));						

				}
			}
		}
//		}

	}
	
	/**
	 * Lance la fonction de calcul du montant sur chacunes des lignes du document.
	 */
	public void calculMontantLigneDocument(TaAvisEcheance doc) {
		for (Object ligne : doc.getLignes()) {
			((TaLAvisEcheance)ligne).calculMontant();
		}
	}
	
	/**
	 * Calcul de la grille de TVA en fonction de lignes du document et du taux de remise HT global.
	 * Mise à jour de la propriété <code>lignesTVA</code>
	 * et mise à jour du montant de la TVA dans les lignes du document
	 */
	public void calculTvaTotal(TaAvisEcheance doc) {
		calculMontantLigneDocument(doc);
		calculLignesTva(doc);
		dispatcherTva(doc);
	}
	
	/**
	 * Calcul de la grille de TVA en fonction de lignes du document et du taux de remise HT global.
	 * Mise à jour de la propriété <code>lignesTVA</code>
	 */
	public void calculLignesTva(TaAvisEcheance doc) {

		Map<String,BigDecimal> montantTotalHt = new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> montantTotalTtc = new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> montantTotalHtAvecRemise = new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> montantTotalTtcAvecRemise = new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> mtTVA = new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> tauxTVA = new HashMap<String,BigDecimal>();
		Map<String,Integer> nbLigne = new HashMap<String,Integer>();
		String codeTVA = null;
		
		/*
		 * calcul de la TVA different en fonction de la propriete TTC
		 */
		BigDecimal ttcLigne = null;
		BigDecimal htLigne = null;
		for (Object ligne : doc.getLignes()) {
			//en commentaire pour ne pas refaire les calculs pendants les editions, 
			//((TaLAvisEcheance)ligne).calculMontant();
			codeTVA = ((TaLAvisEcheance)ligne).getCodeTvaLDocument();
			if(codeTVA!=null && !codeTVA.equals("")) {
				ttcLigne = ((TaLAvisEcheance)ligne).getMtTtcLDocument();
				htLigne = ((TaLAvisEcheance)ligne).getMtHtLDocument();
				if(montantTotalHt.containsKey(codeTVA)) {
					montantTotalTtc.put(codeTVA,montantTotalTtc.get(codeTVA).add(ttcLigne));
					montantTotalHt.put(codeTVA,montantTotalHt.get(codeTVA).add(htLigne));
					montantTotalTtcAvecRemise.put(codeTVA,montantTotalTtcAvecRemise.get(codeTVA).add(ttcLigne));
					montantTotalHtAvecRemise.put(codeTVA,montantTotalHtAvecRemise.get(codeTVA).add(htLigne));
					nbLigne.put(codeTVA,nbLigne.get(codeTVA)+1);
				} else {
					montantTotalTtc.put(codeTVA,ttcLigne);
					montantTotalHt.put(codeTVA,htLigne);
					montantTotalTtcAvecRemise.put(codeTVA,ttcLigne);
					montantTotalHtAvecRemise.put(codeTVA,htLigne);
					tauxTVA.put(codeTVA,((TaLAvisEcheance)ligne).getTauxTvaLDocument());
					nbLigne.put(codeTVA,1);
				}
			}
		}

		for (String codeTva : montantTotalTtc.keySet()) {
			//les 2 maps ont les meme cles
			BigDecimal mtTtcTotal = montantTotalTtc.get(codeTva);
			BigDecimal mtHtTotal = montantTotalHt.get(codeTva);
			BigDecimal tva =null;
			if(doc.getTxRemHtDocument()!=null && doc.getTxRemHtDocument().signum()>0) {
//				mtTtcTotal =LibCalcul.arrondi(mtTtcTotal.subtract(     mtTtcTotal.multiply(   txRemHtDocument.divide(new BigDecimal(100))  )       ));
//				mtHtTotal = LibCalcul.arrondi(mtHtTotal.subtract(    mtHtTotal.multiply( (txRemHtDocument.divide(new BigDecimal(100))))     ) ) ;
				BigDecimal valeurInterTTC=mtTtcTotal.multiply(   doc.getTxRemHtDocument().divide(new BigDecimal(100)),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
				mtTtcTotal =LibCalcul.arrondi(mtTtcTotal.subtract(valeurInterTTC )) ;
				BigDecimal valeurInterHT=mtHtTotal.multiply( doc.getTxRemHtDocument().divide(new BigDecimal(100)),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP);
				mtHtTotal = LibCalcul.arrondi(mtHtTotal.subtract( valeurInterHT )) ;
				montantTotalTtcAvecRemise.put(codeTva, mtTtcTotal);
				montantTotalHtAvecRemise.put(codeTva, mtHtTotal);
			} 

			if (doc.getTtc()==1) {
				tva=mtTtcTotal.subtract((mtTtcTotal.multiply(BigDecimal.valueOf(100))) .divide((BigDecimal.valueOf(100).add(tauxTVA.get(codeTva))) ,MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP)     ) ;
				mtTVA.put(codeTva, tva);
				montantTotalHtAvecRemise.put(codeTva, mtTtcTotal.subtract(tva));
			} else {
				tva=mtHtTotal.multiply(   (tauxTVA.get(codeTva).divide(new BigDecimal(100)))) ;
				mtTVA.put(codeTva, tva );
				montantTotalTtcAvecRemise.put(codeTva, mtHtTotal.add(tva));
			}
		}

		doc.getLignesTVA().clear();
		for (String codeTva : mtTVA.keySet()) {
			LigneTva ligneTva = new LigneTva();
			ligneTva.setCodeTva(codeTva);
			ligneTva.setTauxTva(tauxTVA.get(codeTva));
			ligneTva.setMtTva(mtTVA.get(codeTva));
			ligneTva.setMontantTotalHt(montantTotalHt.get(codeTva));
			ligneTva.setMontantTotalTtc(montantTotalTtc.get(codeTva));
			ligneTva.setMontantTotalHtAvecRemise(montantTotalHtAvecRemise.get(codeTva));
			ligneTva.setMontantTotalTtcAvecRemise(montantTotalTtcAvecRemise.get(codeTva));
			ligneTva.setLibelle(taTvaDAO.findByCode(codeTva).getLibelleTva());
			ligneTva.setNbLigneDocument(nbLigne.get(codeTva));
			doc.getLignesTVA().add(ligneTva);
		}
		
		//dispatcherTva();
	}
	
	public Date calculDateEcheanceAbstract(TaAvisEcheance doc, Integer report, Integer finDeMois){
		return calculDateEcheance(doc,report,finDeMois);
	}
	
	public Date calculDateEcheance(TaAvisEcheance doc, Integer report, Integer finDeMois) {
		TaTCPaiement typeCP = taTCPaiementDAO.findByCode(TaTCPaiement.C_CODE_TYPE_AVIS_ECHEANCE);
		TaCPaiement conditionDoc = null;
		TaCPaiement conditionTiers = null;
		TaCPaiement conditionSaisie = null;
		
		if(typeCP!=null) conditionDoc = typeCP.getTaCPaiement();
		List<TaCPaiement> liste=taCPaiementDAO.rechercheParType(typeCP.getCodeTCPaiement());
		if(liste!=null && !liste.isEmpty())conditionDoc=liste.get(0);
		if(doc.getTaTiers()!=null) conditionTiers = doc.getTaTiers().getTaCPaiement();

		if(report!=null || finDeMois!=null) { 
			conditionSaisie = new TaCPaiement();
			if(report!=null)
				conditionSaisie.setReportCPaiement(report);
			if(finDeMois!=null)
				conditionSaisie.setFinMoisCPaiement(finDeMois);
		}
		
		//on applique toute les conditions par ordre décroissant de priorité, la derniere valide est conservée
		Date nouvelleDate = doc.getDateDocument();
		if(conditionDoc!=null) {
			nouvelleDate = conditionDoc.calculeNouvelleDate(doc.getDateDocument());
		}
		if(conditionTiers!=null) {
			nouvelleDate = conditionTiers.calculeNouvelleDate(doc.getDateDocument());
		}
		if(conditionSaisie!=null) {
			nouvelleDate = conditionSaisie.calculeNouvelleDate(doc.getDateDocument());
		}
		doc.setDateEchDocument(nouvelleDate);
		return nouvelleDate;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void persist(TaAvisEcheance transientInstance) throws CreateException {
		persist(transientInstance, null);
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@WebMethod(operationName = "persistValidationContext")
	public void persist(TaAvisEcheance transientInstance, String validationContext) throws CreateException {

		validateEntity(transientInstance, validationContext);

		dao.persist(transientInstance);
	}

	@Override
	public void remove(TaAvisEcheance persistentInstance, boolean recharger) throws RemoveException {
		try {
			if(taAutorisationDossierService.autoriseMenu(IModulesProgramme.ID_MODULE_ABONNEMENT, sessionInfo.getUtilisateur())) {
				//taLigneALigneEcheanceService.removeAllByIdDocumentAndTypeDoc(persistentInstance.getIdDocument(), *.TYPE_DOC);
			}
			if(recharger)persistentInstance=findByIDFetch(persistentInstance.getIdDocument());
			List<ILigneDocumentTiers> listeLien = recupListeLienLigneALigne(persistentInstance);
			dao.remove(persistentInstance);
			mergeEntityLieParLigneALigne(listeLien);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	public void remove(TaAvisEcheance persistentInstance) throws RemoveException {
		// taLEcheanceService.supprimeLiaisonsAvisEcheance(persistentInstance);
		 remove(persistentInstance, true);
	}
	
	public TaAvisEcheance merge(TaAvisEcheance detachedInstance) {
		return merge(detachedInstance, null);
	}

	@Override
	@WebMethod(operationName = "mergeValidationContext")
	public TaAvisEcheance merge(TaAvisEcheance detachedInstance, String validationContext) {
		validateEntity(detachedInstance, validationContext);

		List<ILigneDocumentTiers> listeLien =null;
		TaAvisEcheance objInitial = detachedInstance;
		try {
			if(detachedInstance.getIdDocument()!=0)
				objInitial=findByIDFetch(detachedInstance.getIdDocument());
		} catch (FinderException e) {
			// TODO Auto-generated catch block
		}
		
//		à mettre si gestion des états !!!!
		modifEtatLigne(detachedInstance);		
		TaEtat etat=changeEtatDocument(detachedInstance);
		if(etat!=null)detachedInstance.addREtat(etat);
		
		//récupération des liens avec le document d'origine en cours si ces liens sont les sources du document d'origine
		listeLien = recupListeLienLigneALigne(objInitial);
		//récupération des liens avec le document modifié en cours si ces liens sont les sources du document modifié 
		listeLien =recupListeLienLigneALigne(detachedInstance,listeLien);
		
		detachedInstance=dao.merge(detachedInstance);
		
		//merger les documents source lié au document en cours d'enregistrement
		mergeEntityLieParLigneALigne(listeLien);
		
		annuleCode(detachedInstance.getCodeDocument());
		return detachedInstance;
	}

	public TaAvisEcheance findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public TaAvisEcheance findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

//	@RolesAllowed("admin")
	public List<TaAvisEcheance> selectAll() {
		List<TaAvisEcheance> l=dao.selectAll();
		for (TaAvisEcheance o : l) {
			o=(TaAvisEcheance) recupSetREtat(o);
			o=(TaAvisEcheance) recupSetHistREtat(o);
			o=(TaAvisEcheance) recupSetLigneALigne(o);
			o=(TaAvisEcheance) recupSetRDocument(o);
			o=(TaAvisEcheance) recupSetREtatLigneDocuments(o);
			o=(TaAvisEcheance) recupSetHistREtatLigneDocuments(o);
		}
		return l;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<TaAvisEcheanceDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<TaAvisEcheanceDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<TaAvisEcheance> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<TaAvisEcheanceDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public TaAvisEcheanceDTO entityToDTO(TaAvisEcheance entity) {
//		TaAvisEcheanceDTO dto = new TaAvisEcheanceDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		TaAvisEcheanceMapper mapper = new TaAvisEcheanceMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<TaAvisEcheanceDTO> listEntityToListDTO(List<TaAvisEcheance> entity) {
		List<TaAvisEcheanceDTO> l = new ArrayList<TaAvisEcheanceDTO>();

		for (TaAvisEcheance taTCivilite : entity) {
			l.add(entityToDTO(taTCivilite));
		}

		return l;
	}

//	@RolesAllowed("admin")
	public List<TaAvisEcheanceDTO> selectAllDTO() {
		System.out.println("List of TaAvisEcheanceDTO EJB :");
		ArrayList<TaAvisEcheanceDTO> liste = new ArrayList<TaAvisEcheanceDTO>();

		List<TaAvisEcheance> projects = selectAll();
		for(TaAvisEcheance project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public TaAvisEcheanceDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public TaAvisEcheanceDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(TaAvisEcheanceDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}
	
	public void mergeDTO(TaAvisEcheanceDTO dto) throws EJBException {
		mergeDTO(dto, null);
	}

	@Override
	@WebMethod(operationName = "mergeDTOValidationContext")
	public void mergeDTO(TaAvisEcheanceDTO dto, String validationContext) throws EJBException {
		try {
			TaAvisEcheanceMapper mapper = new TaAvisEcheanceMapper();
			TaAvisEcheance entity = null;
			if(dto.getId()!=null) {
				entity = dao.findById(dto.getId());
				if(dto.getVersionObj()!=entity.getVersionObj()) {
					throw new OptimisticLockException(entity,
							"L'objet à été modifié depuis le dernier accés. Client ID : "+dto.getId()+" - Client Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
				} else {
					 entity = mapper.mapDtoToEntity(dto,entity);
				}
			}
			
			//dao.merge(entity);
			dao.detach(entity); //pour passer les controles
			enregistrerMerge(entity, validationContext);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CreateException(e.getMessage());
			throw new EJBException(e.getMessage());
		}
	}
	
	public void persistDTO(TaAvisEcheanceDTO dto) throws CreateException {
		persistDTO(dto, null);
	}

	@Override
	@WebMethod(operationName = "persistDTOValidationContext")
	public void persistDTO(TaAvisEcheanceDTO dto, String validationContext) throws CreateException {
		try {
			TaAvisEcheanceMapper mapper = new TaAvisEcheanceMapper();
			TaAvisEcheance entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity, validationContext);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(TaAvisEcheanceDTO dto) throws RemoveException {
		try {
			TaAvisEcheanceMapper mapper = new TaAvisEcheanceMapper();
			TaAvisEcheance entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected TaAvisEcheance refresh(TaAvisEcheance persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(operationName = "validateEntityValidationContext")
	public void validateEntity(TaAvisEcheance value, String validationContext) /*throws ExceptLgr*/ {
		try {
			if(validationContext==null) validationContext="";
			validateAll(value,validationContext,false); //ancienne validation, extraction de l'annotation et appel
			//dao.validate(value); //validation automatique via la JSR bean validation
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	@WebMethod(operationName = "validateEntityPropertyValidationContext")
	public void validateEntityProperty(TaAvisEcheance value, String propertyName, String validationContext) {
		try {
			if(validationContext==null) validationContext="";
			validate(value, propertyName, validationContext);
			//dao.validateField(value,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	@WebMethod(operationName = "validateDTOValidationContext")
	public void validateDTO(TaAvisEcheanceDTO dto, String validationContext) {
		try {
			TaAvisEcheanceMapper mapper = new TaAvisEcheanceMapper();
			TaAvisEcheance entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity,validationContext);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<TaAvisEcheanceDTO> validator = new BeanValidator<TaAvisEcheanceDTO>(TaAvisEcheanceDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	@WebMethod(operationName = "validateDTOPropertyValidationContext")
	public void validateDTOProperty(TaAvisEcheanceDTO dto, String propertyName, String validationContext) {
		try {
			TaAvisEcheanceMapper mapper = new TaAvisEcheanceMapper();
			TaAvisEcheance entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName,validationContext);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<TaAvisEcheanceDTO> validator = new BeanValidator<TaAvisEcheanceDTO>(TaAvisEcheanceDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	@Override
	@WebMethod(operationName = "validateDTO")
	public void validateDTO(TaAvisEcheanceDTO dto) {
		validateDTO(dto,null);
		
	}

	@Override
	@WebMethod(operationName = "validateDTOProperty")
	public void validateDTOProperty(TaAvisEcheanceDTO dto, String propertyName) {
		validateDTOProperty(dto,propertyName,null);
		
	}

	@Override
	@WebMethod(operationName = "validateEntity")
	public void validateEntity(TaAvisEcheance value) {
		validateEntity(value,null);
	}

	@Override
	@WebMethod(operationName = "validateEntityProperty")
	public void validateEntityProperty(TaAvisEcheance value, String propertyName) {
		validateEntityProperty(value,propertyName,null);
		
	}
	
	@Override
	public List<TaAvisEcheance> rechercheDocument(Date dateDeb, Date dateFin) {
		return dao.rechercheDocument(dateDeb, dateFin);
	}

	@Override
	public List<Object[]> rechercheDocument(Date dateDeb, Date dateFin,
			Boolean light) {
		return dao.rechercheDocument(dateDeb, dateFin, light);
	}

	@Override
	public List<TaAvisEcheance> rechercheDocument(String codeDeb, String codeFin) {
		return dao.rechercheDocument(codeDeb, codeFin);
	}

	@Override
	public List<TaAvisEcheance> rechercheDocument(Date dateDeb, Date dateFin,
			String codeTiers) {
		return dao.rechercheDocument(dateDeb, dateFin, codeTiers);
	}

	@Override
	public List<Object[]> rechercheDocumentLight(Date dateDeb, Date dateFin,
			String codeTiers) {
		return dao.rechercheDocumentLight(dateDeb, dateFin, codeTiers);
	}

	@Override
	public List<Object[]> rechercheDocumentLight(String codeDoc,
			String codeTiers) {
		return dao.rechercheDocumentLight(codeDoc, codeTiers);
	}

	@Override
	public List<TaAvisEcheance> rechercheDocument(String codeDeb, String codeFin,
			String codeTiers) {
		return dao.rechercheDocument(codeDeb, codeFin, codeTiers);
	}

	@Override
	public List<TaAvisEcheance> rechercheDocument(Date dateDeb, Date dateFin,
			String codeTiers, Boolean export) {
		// TODO Auto-generated method stub
		return dao.rechercheDocument(dateDeb, dateFin, codeTiers, export);
	}

	@Override
	public List<TaAvisEcheance> rechercheDocument(String codeDeb, String codeFin,
			String codeTiers, Boolean export) {
		// TODO Auto-generated method stub
		return dao.rechercheDocument(codeDeb, codeFin, codeTiers, export);
	}

	@Override
	public List<TaAvisEcheance> selectAll(IDocumentTiers taDocument, Date dateDeb,
			Date dateFin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaAvisEcheance> findByCodeTiersAndDate(String codeTiers,
			Date debut, Date fin) {
		return dao.findByCodeTiersAndDate(codeTiers, debut, fin);
	}

	@Override
	public List<Object> findChiffreAffaireByCodeTiers(String codeTiers,
			Date debut, Date fin, int precision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaTiers> findTiersByCodeArticleAndDate(String codeArticle,
			Date debut, Date fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findChiffreAffaireByCodeArticle(String codeArticle,
			Date debut, Date fin, int precision) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaAvisEcheance findByIdDocument(int id) throws FinderException {
		return findById(id);
	}

	public String genereCode( Map<String, String> params) {
		//return dao.genereCode();
		try {
			return gencode.genereCodeJPA(TaAvisEcheance.class.getSimpleName(),params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "NOUVEAU CODE";
	}
	
	public void annuleCode(String code) {
		try {
			
			gencode.annulerCodeGenere(gencode.findByCode(TaAvisEcheance.class.getSimpleName()),code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verrouilleCode(String code) {
		try {
			gencode.rentreCodeGenere(gencode.findByCode(TaAvisEcheance.class.getSimpleName()),code, sessionInfo.getSessionID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public TaAvisEcheance mergeAndFindById(TaAvisEcheance detachedInstance, String validationContext) {
		TaAvisEcheance br = merge(detachedInstance,validationContext);
		try {
			br = findByIDFetch(br.getIdDocument()); //pour etre sur que les valeur initialisé par les triggers "qui_cree,..." soit bien affecté avant une éventuelle nouvelle modification du document
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}

	@Override
	public List<TaAvisEcheanceDTO> findAllLight() {
		return dao.findAllLight();
	}
	
//	@Override
//	public List<TaAvisEcheanceDTO> findAllDTOPeriode(Date dateDebut, Date dateFin) {
//		// TODO Auto-generated method stub
//		return dao.findAllDTOPeriode(dateDebut, dateFin);
//	}
//
//	@Override
//	public List<TaAvisEcheanceDTO> findAvisEcheanceNonTransfosDTO(Date dateDebut, Date dateFin) {
//		// TODO Auto-generated method stub
//		return dao.findAvisEcheanceNonTransfosDTO(dateDebut, dateFin);
//	}
//	
//	@Override
//	public List<TaAvisEcheanceDTO> findAvisEcheanceTransfosDTO(Date dateDebut, Date dateFin) {
//		// TODO Auto-generated method stub
//		return dao.findAvisEcheanceTransfosDTO(dateDebut, dateFin);
//	}
//	
//	@Override
//	public List<TaAvisEcheanceDTO> findAvisEcheanceNonTransfosARelancerDTO(Date dateDebut, Date dateFin, int deltaNbJours) {
//		// TODO Auto-generated method stub
//		return dao.findAvisEcheanceNonTransfosARelancerDTO(dateDebut,dateFin,deltaNbJours);
//	}
	
	@Override
	public long countDocument(Date debut, Date fin,String codeTiers) {
		return dao.countDocument(debut, fin, codeTiers);
	}
	@Override
	public long countDocumentNonTransforme(Date debut, Date fin,String codeTiers) {
		return dao.countDocumentNonTransforme(debut, fin, codeTiers);
	}

	@Override
	public long countDocumentNonTransformeARelancer(Date debut, Date fin, int deltaNbJours,String codeTiers){
		return dao.countDocumentNonTransformeARelancer(debut, fin, deltaNbJours, codeTiers);
	}
	
	@Override
	public long countDocumentTransforme(Date debut, Date fin,String codeTiers){
		return dao.countDocumentTransforme(debut, fin, codeTiers);
	}
	
	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTotalDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public DocumentChiffreAffaireDTO chiffreAffaireTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.chiffreAffaireTotalDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTransformeTotalDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public DocumentChiffreAffaireDTO chiffreAffaireTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.chiffreAffaireTransformeTotalDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireNonTransformeTotalDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public DocumentChiffreAffaireDTO chiffreAffaireNonTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.chiffreAffaireNonTransformeTotalDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeARelancerTotalDTO(Date dateDebut,
			Date dateFin, int deltaNbJours,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireNonTransformeARelancerTotalDTO(dateDebut, dateFin, deltaNbJours, codeTiers);
	}

	@Override
	public DocumentChiffreAffaireDTO chiffreAffaireNonTransformeARelancerTotalDTO(Date dateDebut, Date dateFin,
			int deltaNbJours,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.chiffreAffaireNonTransformeARelancerTotalDTO(dateDebut, dateFin, deltaNbJours, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalJmaDTO(Date dateDebut, Date dateFin, int precision,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTotalJmaDTO(dateDebut, dateFin, precision, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeJmaDTO(Date dateDebut, Date dateFin,
			int precision,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireNonTransformeJmaDTO(dateDebut, dateFin, precision, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTransformeJmaDTO(Date dateDebut, Date dateFin,
			int precision,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTransformeJmaDTO(dateDebut, dateFin, precision, codeTiers);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeARelancerJmaDTO(Date dateDebut, Date dateFin,
			int precision, int deltaNbJours,String codeTiers) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireNonTransformeARelancerJmaDTO(dateDebut, dateFin, precision, deltaNbJours, codeTiers);
	}


	/**
	 * Méthode qui est censé ne plus servir
	 * Attention méthode dépréciée, mais elle devrait quand même fonctionné
	 * utiliser les generePDF avec 5 param a la place
	 */
	public String generePDF(int id, Map<String,Object> mapParametreEdition, List<String> listResourcesPath, String theme) {
		return generePDF(id,editionDefaut,mapParametreEdition, null, null);
	}
	
	//Rajout YANN pour impression edition defaut choisi par l'utilisateur
	/**
	 * Méthode intérmédiaire a laquelle on peut passé une action edition (canal) en param par exemple mail, impression, ou espace client
	 * L'autre particularité de cette méthode est qu'elle n'a pas besoin de modeleEdition en param,
	 *  elle va chercher l'edition choisie si il y a en focntion de l'action et elle ecrit dans un fichier tmp le birt xml
	 * Cette méthode fini quoi qu'il arrive par appellé l'autre generePDF final a 5 param
	 * Cette méthode ne peut pas être extraite telle quelle car elle contient des références propre au document (taFacture, editionDefaut etc...)
	 * @return generePDF final
	 * @author yann
	 */
	public String generePDF(int id, Map<String,Object> mapParametreEdition, List<String> listResourcesPath, String theme, TaActionEdition action) {
		if(action != null) {
			TaEdition edition = taEditionService.rechercheEditionActionDefaut(null, action, TaAvisEcheance.TYPE_DOC);
			
			if(edition != null) {//si j'ai trouver une edition par defaut
				TaEdition editionDefautChoisie = edition;
				//je vais chercher l'entité avec l'id du DTO
				String localPath = writingFileEdition(edition);
				BdgProperties bdgProperties = new BdgProperties();
				List<String> listeResourcesPath = null;
				
				if(editionDefautChoisie.getResourcesPath()!=null) {
					listeResourcesPath = new ArrayList<>();
					listeResourcesPath.add(editionDefautChoisie.getResourcesPath());
				}
				
				return generePDF(id,  localPath, mapParametreEdition, listeResourcesPath,  editionDefautChoisie.getTheme()); 
				
			//si pas d'édition par defaut
			}else {
				return generePDF(id,editionDefaut,mapParametreEdition, null, null);
			}
			
		}else {// si action nulle
			return generePDF(id,editionDefaut,mapParametreEdition, null, null);
		}
		
	}
	
	
	/**
	 * Méthode qui génére un pdf à partir de 5 param avec BIRT
	 * Méthode FINALE, toute les méthode generePDF finisse par appelé celle-ci.
	 * @param int idFacture
	 * @param String modeleEdition
	 * @param Map<String,Object> mapParametreEdition
	 * @param List<String> listResourcesPath
	 * @param String theme
	 */
	public String generePDF(int id, String modeleEdition, Map<String,Object> mapParametreEdition, List<String> listResourcesPath, String theme) {
		try {
			TaAvisEcheance doc = findById(id);
			doc.calculeTvaEtTotaux();
			SchemaResolver sr = new SchemaResolver();
			String localPath = bdgProperties.osTempDirDossier(sr.resolveCurrentTenantIdentifier())+"/"+bdgProperties.tmpFileName(doc.getCodeDocument(), doc.getTaTiers().getCodeTiers() , "avis_echeance.pdf");
			System.out.println(localPath);
			System.out.println(bdgProperties.urlDemoHttps()+modeleEdition);
			
			Map<String,Object> mapEdition = new HashMap<String,Object>();
			
			boolean editionClient = false;
			
//			TaFacture doc = findById(idFacture);
//			doc.calculeTvaEtTotaux();

			mapEdition.put("doc",doc );
			
			

			mapEdition.put("taInfoEntreprise", entrepriseService.findInstance());

			mapEdition.put("lignes", doc.getLignes());

			//mapEdition.put("taRReglement", doc.getTaRReglements());
			
			//Traitement des différents règlements
			List<TaRReglement> listeReglement =new LinkedList<>(); 
			List<TaRReglement> listeAcompte =new LinkedList<>();
			List<TaRAvoir> listeAvoir =new LinkedList<>();
//			for (TaRReglement ligne : doc.getTaRReglements()) {
//				if(ligne.getTaReglement().getTaAcompte()==null)
//					listeReglement.add(ligne);
//				else
//					listeAcompte.add(ligne);
//			}
			
			mapEdition.put("listeReglement", listeReglement);
			mapEdition.put("listeAcompte", listeAcompte);
			//mapEdition.put("listeAvoir", doc.getTaRAvoirs());
			
			if(mapParametreEdition == null) {
				mapParametreEdition = new HashMap<String,Object>();
			} else {
				if(mapParametreEdition.containsKey("mode")) {
					mapEdition.put("mode", mapParametreEdition.get("mode") );
				}
				if(mapParametreEdition.containsKey("editionClient")) {
					editionClient = (boolean) mapParametreEdition.get("editionClient");
				}
				mapEdition.put("mode", mapParametreEdition.get("mode") );
			}
			List<TaPreferences> liste= taPreferencesService.findByGroupe("AvisEcheance");
			if(!mapParametreEdition.containsKey("preferences")) mapParametreEdition.put("preferences", liste);
			if(!mapParametreEdition.containsKey("gestionLot")) mapParametreEdition.put("gestionLot", false);
			if(!mapParametreEdition.containsKey("mode")) mapParametreEdition.put("mode", "");
			if(!mapParametreEdition.containsKey("Theme")) mapParametreEdition.put("Theme", "defaultTheme");
			if(!mapParametreEdition.containsKey("Librairie")) mapParametreEdition.put("Librairie", "bdgFactTheme1");
			mapEdition.put("param", mapParametreEdition);

			//sessionMap.put("edition", mapEdition);
			
			if(theme == null) {
				theme = mapParametreEdition.get("Theme").toString();
						
			}
			
			////////////////////////////////////////////////////////////////////////////////////////
			//Test génération de fichier PDF
			
			HashMap<String,Object> hm = new HashMap<>();

			hm.put("edition", mapEdition);
			
			
			if(listResourcesPath == null) {
				listResourcesPath = new ArrayList<String>();
				listResourcesPath.add(EditionProgrammeDefaut.LIBRAIRIE_EDITION_DEFAUT_AVIS_ECHEANCE);
			}else if(listResourcesPath.isEmpty()) {
					listResourcesPath = new ArrayList<String>();
					listResourcesPath.add(EditionProgrammeDefaut.LIBRAIRIE_EDITION_DEFAUT_AVIS_ECHEANCE);
			}
			
			
			BirtUtil.setAppContextEdition(hm);
			BirtUtil.startReportEngine();
			
			BirtUtil.renderReportToFile(
					//bdgProperties.urlDemoHttps()+modeleEdition, //"https://dev.demo.promethee.biz:8443/reports/documents/facture/FicheFacture.rptdesign",
					modeleEdition,
					localPath, 
					new HashMap<>(), 
					BirtUtil.PDF_FORMAT,
					listResourcesPath,
					theme);
			
//			if(editionClient) {
//				//Mise à jour de la mise à dispostion
//				if(doc.getTaMiseADisposition()==null) {
//					doc.setTaMiseADisposition(new TaMiseADisposition());
//				}
//				Date dateMiseADispositionClient = new Date();
//				doc.getTaMiseADisposition().setImprimePourClient(dateMiseADispositionClient);
//				doc = mergeAndFindById(doc,ITaAvisEcheanceServiceRemote.validationContext);
//			}
			 
			 return localPath;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	@Override
//	public String generePDF(int idDoc, Map<String,Object> mapParametreEdition, List<String> listResourcesPath, String theme) {
//		return generePDF(idDoc,editionDefaut,mapParametreEdition, null, null);
//	}
//	
//	public String generePDF(int idDoc, String modeleEdition, Map<String,Object> mapParametreEdition, List<String> listResourcesPath, String theme) {
//		try {
//			SchemaResolver sr = new SchemaResolver();
//			TaAvisEcheance doc = findById(idDoc);
//
//			String localPath = bdgProperties.osTempDirDossier(sr.resolveCurrentTenantIdentifier())+"/"+bdgProperties.tmpFileName("AvisEcheance.pdf",doc.getCodeDocument());
//			System.out.println(localPath);
//			System.out.println(bdgProperties.urlDemoHttps()+modeleEdition);
//			
//			Map<String,Object> mapEdition = new HashMap<String,Object>();
//
//			doc.calculeTvaEtTotaux();
//
//			mapEdition.put("doc",doc );
//
//			mapEdition.put("taInfoEntreprise", entrepriseService.findInstance());
//
//			mapEdition.put("lignes", doc.getLignes());
//
//			
//			if(mapParametreEdition == null) {
//				mapParametreEdition = new HashMap<String,Object>();
//			}
//			List<TaPreferences> liste= taPreferencesService.findByGroupe("AvisEcheance");
//			if(!mapParametreEdition.containsKey("preferences")) mapParametreEdition.put("preferences", liste);
//			if(!mapParametreEdition.containsKey("gestionLot")) mapParametreEdition.put("gestionLot", false);
//			if(!mapParametreEdition.containsKey("mode")) mapParametreEdition.put("mode", "");
//			if(!mapParametreEdition.containsKey("Theme")) mapParametreEdition.put("Theme", "defaultTheme");
//			if(!mapParametreEdition.containsKey("Librairie")) mapParametreEdition.put("Librairie", "bdgFactTheme1");
//			mapEdition.put("param", mapParametreEdition);
//
//			//sessionMap.put("edition", mapEdition);
//			
//			////////////////////////////////////////////////////////////////////////////////////////
//			//Test génération de fichier PDF
//			
//			HashMap<String,Object> hm = new HashMap<>();
//
//			hm.put("edition", mapEdition);
//			
//			BirtUtil.setAppContextEdition(hm);
//			BirtUtil.startReportEngine();
//			
//			BirtUtil.renderReportToFile(
//					//bdgProperties.urlDemoHttps()+modeleEdition, //"https://dev.demo.promethee.biz:8443/reports/documents/facture/FicheFacture.rptdesign",
//					modeleEdition,
//					localPath, 
//					new HashMap<>(), 
//					BirtUtil.PDF_FORMAT,
//					listResourcesPath,
//					theme);
//			 
//			 return localPath;
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public List<DocumentDTO> findAllDTOPeriode(Date dateDebut, Date dateFin, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findAllDTOPeriode(dateDebut, dateFin, codeTiers);
	}

	@Override
	public List<DocumentDTO> findDocumentNonTransfosDTO(Date dateDebut, Date dateFin, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findDocumentNonTransfosDTO(dateDebut, dateFin,  codeTiers);
	}

	@Override
	public List<DocumentDTO> findDocumentNonTransfosARelancerDTO(Date dateDebut, Date dateFin, int deltaNbJours,
			String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findDocumentNonTransfosARelancerDTO(dateDebut, dateFin, deltaNbJours, codeTiers);
	}

	@Override
	public List<DocumentDTO> findDocumentTransfosDTO(Date dateDebut, Date dateFin, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findDocumentTransfosDTO(dateDebut, dateFin, codeTiers);
	}

	@Override
	public List<TaAvisEcheance> rechercheDocumentVerrouille(Date dateDeb, Date dateFin, String codeTiers,
			Boolean verrouille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaAvisEcheance> rechercheDocumentVerrouille(String codeDeb, String codeFin, String codeTiers,
			Boolean verrouille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaAvisEcheance> rechercheDocument(String codeTiers, Date dateExport, Date dateDeb, Date dateFin) {
		// TODO Auto-generated method stub
		return null;
	}


	
/////// regroupement
	
	@Override
	public DocumentChiffreAffaireDTO chiffreAffaireTotalDTOParTypeRegroupement(Date dateDebut, Date dateFin,
			String codeTiers, String typeRegroupement, Object valeurRegroupement, boolean regrouper) {
		// TODO Auto-generated method stub
		return dao.chiffreAffaireTotalDTOParTypeRegroupement(dateDebut, dateFin, codeTiers, typeRegroupement, valeurRegroupement, regrouper);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalDTOParTypeRegroupement(Date dateDebut, Date dateFin,
			String codeTiers, String typeRegroupement, Object valeurRegroupement, boolean regrouper) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTotalDTOParTypeRegroupement(dateDebut, dateFin, codeTiers, typeRegroupement, valeurRegroupement, regrouper);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalJmaDTOParRegroupement(Date dateDebut, Date dateFin,
			int precision, String codeTiers, String typeRegroupement, Object valeurRegroupement) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTotalJmaDTOParRegroupement(dateDebut, dateFin, precision, codeTiers, typeRegroupement, valeurRegroupement);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> chiffreAffaireParEtat(Date debut, Date fin, String codeTiers,
			Object codeEtat, String typeRegroupement, Object valeurRegroupement) {
		// TODO Auto-generated method stub
		return dao.chiffreAffaireParEtat(debut, fin, codeTiers, codeEtat, typeRegroupement, valeurRegroupement);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> countDocumentAndCodeEtatParTypeRegroupement(Date debut, Date fin,
			String codeTiers, Object codeEtat, String typeRegroupement, Object valeurRegroupement) {
		// TODO Auto-generated method stub
		return dao.countDocumentAndCodeEtatParTypeRegroupement(debut, fin, codeTiers, codeEtat, typeRegroupement, valeurRegroupement);
	}

	@Override
	public List<DocumentDTO> findAllDTOPeriodeAndCodeEtatParTypeRegroupement(Date debut, Date fin, String codeTiers,
			Object codeEtat, String typeRegroupement, Object valeurRegroupement) {
		// TODO Auto-generated method stub
		return dao.findAllDTOPeriodeAndCodeEtatParTypeRegroupement(debut, fin, codeTiers, codeEtat, typeRegroupement, valeurRegroupement);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersMoisAndCodeEtatParTypeRegroupement(Date debut, Date fin,
			Object codeEtat, String typeRegroupement, Object valeurRegroupement) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersMoisAndCodeEtatParTypeRegroupement(debut, fin, codeEtat, typeRegroupement, valeurRegroupement);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalDTOAndCodeEtatParTypeRegroupement(Date dateDebut,
			Date dateFin, String codeTiers, Object codeEtat, String typeRegroupement, Object valeurRegroupement,
			boolean regrouper) {
		// TODO Auto-generated method stub
		return dao.listeChiffreAffaireTotalDTOAndCodeEtatParTypeRegroupement(dateDebut, dateFin, codeTiers, codeEtat, typeRegroupement, valeurRegroupement, regrouper);
	}
	
	
	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersParMois(Date debut, Date fin, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersParMois(debut, fin, codeTiers);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersTransforme(Date debut, Date fin, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersTransforme(debut, fin, codeTiers);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersNonTransforme(Date debut, Date fin, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersNonTransforme(debut, fin, codeTiers);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersNonTransformeARelancer(Date debut, Date fin,
			int deltaNbJours, String codeTiers) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersNonTransformeARelancer(debut, fin, deltaNbJours, codeTiers);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersParMoisParTypeRegroupement(Date dateDebut, Date dateFin,
			String codeTiers, String typeRegroupement, Object valeurRegroupement, boolean regroupee) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersParMoisParTypeRegroupement(dateDebut, dateFin, codeTiers, typeRegroupement, valeurRegroupement, regroupee);
	}
	

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersParMois(Date debut, Date fin) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersParMois(debut, fin);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersTransforme(Date debut, Date fin) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersTransforme(debut, fin);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersNonTransforme(Date debut, Date fin) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersNonTransforme(debut, fin);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersNonTransformeARelancer(Date debut, Date fin,
			int deltaNbJours) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersNonTransformeARelancer(debut, fin, deltaNbJours);
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersParMoisParTypeRegroupement(Date dateDebut, Date dateFin,
			String typeRegroupement, Object valeurRegroupement) {
		// TODO Auto-generated method stub
		return dao.findArticlesParTiersParMoisParTypeRegroupement(dateDebut, dateFin, typeRegroupement, valeurRegroupement);
	}

	@Override
	public List<DocumentChiffreAffaireDTO> listeSumChiffreAffaireTotalDTOArticle(Date dateDebut, Date dateFin,
			String codeArticle, String codeTiers) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<DocumentChiffreAffaireDTO> countChiffreAffaireTotalDTOArticle(Date dateDebut, Date dateFin,
			String codeArticle, String codeTiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentChiffreAffaireDTO> sumChiffreAffaireTotalDTOArticle(Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return dao.sumChiffreAffaireTotalDTOArticle(dateDebut, dateFin);
	}
	@Override
	public List<DocumentChiffreAffaireDTO> sumChiffreAffaireTotalDTOArticle(Date dateDebut, Date dateFin,
			String codeArticle) {
		return dao.sumChiffreAffaireTotalDTOArticle(dateDebut,  dateFin, codeArticle);
	}
	@Override
	public List<DocumentChiffreAffaireDTO> sumChiffreAffaireTotalDTOArticleMoinsAvoir(Date dateDebut, Date dateFin, String codeArticle){
		return dao.sumChiffreAffaireTotalDTOArticleMoinsAvoir(dateDebut, dateFin,  codeArticle);
	}
	
/////////Bloc de méthode pour notamment les onglets factures/devis/commandes/AvisEcheance du dash par article et du  dash par tiers//////
@Override
public long countDocument(Date debut, Date fin, String codeTiers , String codeArticle) {
	return dao.countDocument(debut, fin, codeTiers, codeArticle);
}
@Override
public long countDocumentNonTransforme(Date debut, Date fin, String codeTiers , String codeArticle) {
	return dao.countDocumentNonTransforme(debut, fin, codeTiers, codeArticle);
}
@Override
public long countDocumentTransforme(Date debut, Date fin, String codeTiers , String codeArticle) {
	return dao.countDocumentTransforme(debut, fin, codeTiers, codeArticle);
}
@Override
public long countDocumentNonTransformeARelancer(Date debut, Date fin,int deltaNbJours, String codeTiers , String codeArticle) {
	return dao.countDocumentNonTransformeARelancer(debut, fin, deltaNbJours, codeTiers, codeArticle);
}
@Override
public List<DocumentChiffreAffaireDTO> sumChiffreAffaireTotalDTOArticleNonTransforme(Date dateDebut, Date dateFin, String codeArticle){
	return dao.sumChiffreAffaireTotalDTOArticleNonTransforme( dateDebut,  dateFin,  codeArticle);
}
@Override
public List<DocumentChiffreAffaireDTO> sumChiffreAffaireTotalDTOArticleTransforme(Date dateDebut, Date dateFin, String codeArticle){
	return dao.sumChiffreAffaireTotalDTOArticleTransforme( dateDebut,  dateFin,  codeArticle);
}
@Override
public List<DocumentChiffreAffaireDTO> sumChiffreAffaireTotalDTOArticleARelancer(Date dateDebut,Date dateFin, int deltaNbJours,String codeArticle){
	return dao.sumChiffreAffaireTotalDTOArticleARelancer(dateDebut, dateFin, deltaNbJours, codeArticle);
}
@Override
public List<DocumentDTO> findAllLigneDTOPeriode(Date dateDebut, Date dateFin,String codeTiers, String codeArticle){
	return dao.findAllLigneDTOPeriode(dateDebut, dateFin, codeTiers, codeArticle);
}
@Override
public List<DocumentDTO> findDocumentTransfosLigneDTO(Date dateDebut, Date dateFin,String codeTiers, String codeArticle){
	return dao.findDocumentTransfosLigneDTO(dateDebut, dateFin, codeTiers, codeArticle);
}
@Override
public List<DocumentDTO> findDocumentNonTransfosLigneDTO(Date dateDebut, Date dateFin,String codeTiers, String codeArticle){
	return dao.findDocumentNonTransfosLigneDTO(dateDebut, dateFin, codeTiers, codeArticle);
}
@Override
public List<DocumentDTO> findDocumentNonTransfosARelancerLigneDTO(Date dateDebut, Date dateFin,int deltaNbJours, String codeTiers, String codeArticle){
	return dao.findDocumentNonTransfosARelancerLigneDTO(dateDebut, dateFin, deltaNbJours, codeTiers, codeArticle);
}
@Override
public List<DocumentChiffreAffaireDTO> listeSumChiffreAffaireTotalDTOArticleMois(Date dateDebut, Date dateFin, String codeArticle, String codeTiers){
	return dao.listeSumChiffreAffaireTotalDTOArticleMois(dateDebut, dateFin, codeArticle, codeTiers);
}
@Override
public List<DocumentChiffreAffaireDTO> listeSumChiffreAffaireTransformeDTOArticleMois(Date dateDebut, Date dateFin, String codeArticle, String codeTiers){
	return dao.listeSumChiffreAffaireTransformeDTOArticleMois(dateDebut, dateFin, codeArticle, codeTiers);
}
@Override
public List<DocumentChiffreAffaireDTO> listeSumChiffreAffaireNonTransformeDTOArticleMois(Date dateDebut, Date dateFin, String codeArticle, String codeTiers){
	return dao.listeSumChiffreAffaireNonTransformeDTOArticleMois(dateDebut, dateFin, codeArticle, codeTiers);
}
@Override
public List<DocumentChiffreAffaireDTO> listeSumChiffreAffaireNonTransformeARelancerDTOArticleMois(Date dateDebut,Date dateFin, int deltaNbJours,String codeArticle,String codeTiers){
	return dao.listeSumChiffreAffaireNonTransformeARelancerDTOArticleMois(dateDebut, dateFin, deltaNbJours, codeArticle, codeTiers);
}

////@Override
//public String generePDF(int idDoc, Map<String, Object> mapParametreEdition, List<String> listResourcesPath,
//		String theme, TaActionEdition action) {
//	// TODO Auto-generated method stub
//	return null;
//}


//////////FIN//////////

public List<TaAvisEcheanceDTO> rechercheNonLieAFactureByIdTiers(Integer id){
	return dao.rechercheNonLieAFactureByIdTiers(id);
}
	
public TaEtat rechercheEtatInitialDocument() {
	try {
		return taEtatService.documentEncours(null);
	} catch (Exception e) {
		return null;
	}

}
	
@Override
public List<DocumentDTO> findAllDTOPeriodeSimple(Date dateDebut, Date dateFin,String codeTiers) {
	// TODO Auto-generated method stub
	return dao.findAllDTOPeriodeSimple(dateDebut, dateFin, codeTiers);
}

@Override
public List<DocumentDTO> findAllDTOIntervalle(String codeDebut, String codeFin,String codeTiers) {
	// TODO Auto-generated method stub
	return dao.findAllDTOIntervalle(codeDebut, codeFin, codeTiers);
}

@Override
public TaAvisEcheance findDocByLDoc(ILigneDocumentTiers lDoc) {
	TaAvisEcheance o= (TaAvisEcheance) dao.findDocByLDoc(lDoc);
	 recupSetREtat(o);
	 recupSetHistREtat(o);
	 recupSetLigneALigne(o);
	 recupSetRDocument(o);
	 recupSetREtatLigneDocuments(o);
	 recupSetHistREtatLigneDocuments(o);
	return o;
}


@Override
public TaAvisEcheance mergeEtat(IDocumentTiers detachedInstance) {
//	à mettre si gestion des états !!!!
	modifEtatLigne(detachedInstance);		
	TaEtat etat=changeEtatDocument(detachedInstance);
	((TaAvisEcheance) detachedInstance).addREtat(etat);
	
	detachedInstance=dao.merge((TaAvisEcheance) detachedInstance);	
	return (TaAvisEcheance) detachedInstance;
}

@Override
public int findDocByLDocDTO(ILigneDocumentTiers lDoc) {
	return dao.findDocByLDocDTO(lDoc);
}

@Override
public TaAvisEcheance findByCodeFetch(String code) throws FinderException {
	// TODO Auto-generated method stub
	TaAvisEcheance o=  (TaAvisEcheance) dao.findByCodeFetch(code);
	if(o!=null) {
	recupSetREtat(o);
	recupSetHistREtat(o);
	recupSetLigneALigne(o);
	recupSetRDocument(o);
	recupSetREtatLigneDocuments(o);
	recupSetHistREtatLigneDocuments(o);
	}
	return o;
}

@Override
public TaAvisEcheance findByIDFetch(int id) throws FinderException {
	// TODO Auto-generated method stub
	TaAvisEcheance o= (TaAvisEcheance) dao.findByIdFetch(id);
	if(o!=null) {
	recupSetREtat(o);
	recupSetHistREtat(o);
	recupSetLigneALigne(o);
	recupSetRDocument(o);
	recupSetREtatLigneDocuments(o);
	recupSetHistREtatLigneDocuments(o);
	}
	return o;
}

@Override
public TaEtat etatLigneInsertion(TaAvisEcheance masterEntity) {
	// TODO Auto-generated method stub
	return super.etatLigneInsertion(masterEntity);
}

@Override
public TaAvisEcheance mergeEtat(IDocumentTiers detachedInstance, List<IDocumentTiers> avecLien) {
//		à mettre si gestion des états !!!!
		modifEtatLigne(detachedInstance);		
		TaEtat etat=changeEtatDocument(detachedInstance);
		((TaAvisEcheance) detachedInstance).addREtat(etat);
		
		
	
		detachedInstance=dao.merge((TaAvisEcheance) detachedInstance);
		
		if(avecLien!=null) {
			mergeEntityLieParLigneALigneDTO(avecLien);
		}

		return (TaAvisEcheance) detachedInstance;
	}

@Override
public List<TaAvisEcheance> rechercheByIdAbonnement(Integer idAbo) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<TaAvisEcheance> rechercheAvisFaux() {
	// TODO Auto-generated method stub
	return null;
}




@Override
public List<DocumentDTO> findAllDTOPeriodeAvecEtat(Date dateDebut, Date dateFin, String codeTiers, String etat) {
	// TODO Auto-generated method stub
	return dao.findAllDTOPeriodeAvecEtat(dateDebut, dateFin, codeTiers, etat);
}

@Override
public List<DocumentChiffreAffaireDTO> chiffreAffaireTotalAvecEtatDTO(Date dateDebut, Date dateFin, String codeTiers,
		String etat) {
	// TODO Auto-generated method stub
	return dao.chiffreAffaireTotalAvecEtatDTO(dateDebut, dateFin, codeTiers, etat);
}

@Override
public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalAvecEtatDTO(Date dateDebut, Date dateFin,
		String codeTiers, String etat) {
	// TODO Auto-generated method stub
	return dao.listeChiffreAffaireTotalAvecEtatDTO(dateDebut, dateFin, codeTiers, etat);
}

@Override
public DocumentChiffreAffaireDTO chiffreAffaireTotalSurEtatDTO(Date dateDebut, Date dateFin, String codeTiers,
		String etat) {
	// TODO Auto-generated method stub
	return dao.chiffreAffaireTotalSurEtatDTO(dateDebut, dateFin, codeTiers, etat);
}

@Override
public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalSurEtatDTO(Date dateDebut, Date dateFin,
		String codeTiers, String etat) {
	// TODO Auto-generated method stub
	return dao.listeChiffreAffaireTotalSurEtatDTO(dateDebut, dateFin, codeTiers, etat);
}


@Override
public List<DocumentChiffreAffaireDTO> countDocumentAvecEtat(Date debut, Date fin, String codeTiers,
		String codeArticle, String etat) {
	// TODO Auto-generated method stub
	return dao.countDocumentAvecEtat(debut, fin, codeTiers, codeArticle, etat);
}

@Override
public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalJmaAvecEtatDTO(Date dateDebut, Date dateFin,
		int precision, String codeTiers, String etat) {
	// TODO Auto-generated method stub
	return dao.listeChiffreAffaireTotalJmaAvecEtatDTO(dateDebut, dateFin, precision, codeTiers, etat);
}

@Override
public List<DocumentChiffreAffaireDTO> listLigneArticleDTOTiersAvecEtat(Date dateDebut, Date dateFin,
		String codeArticle, String codeTiers, String etat, int deltaNbJours) {
	// TODO Auto-generated method stub
	return dao.listLigneArticleDTOTiersAvecEtat(dateDebut, dateFin, codeArticle, codeTiers, etat, deltaNbJours);
}

@Override
public List<DocumentChiffreAffaireDTO> listLigneArticleDTOTiersAvecEtat(Date dateDebut, Date dateFin,
		String codeArticle, String codeTiers, String etat, String orderBy, int deltaNbJours) {
	// TODO Auto-generated method stub
	return dao.listLigneArticleDTOTiersAvecEtat(dateDebut, dateFin, codeArticle, codeTiers, etat, orderBy, deltaNbJours);
}

@Override
public List<TaArticlesParTiersDTO> findArticlesParTiersParMoisAvecEtat(Date debut, Date fin, String codeTiers,
		String etat, int deltaNbJours) {
	// TODO Auto-generated method stub
	return dao.findArticlesParTiersParMoisAvecEtat(debut, fin, codeTiers, etat, deltaNbJours);
}
}

