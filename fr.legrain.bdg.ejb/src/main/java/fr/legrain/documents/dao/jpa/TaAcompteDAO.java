package fr.legrain.documents.dao.jpa;

import java.math.BigDecimal;

// Generated Apr 7, 2009 3:27:24 PM by Hibernate Tools 3.2.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import fr.legrain.bdg.general.service.remote.ThrowableExceptionLgr;
import fr.legrain.document.dashboard.dto.TaArticlesParTiersDTO;
import fr.legrain.document.dto.DocumentChiffreAffaireDTO;
import fr.legrain.document.dto.DocumentDTO;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.document.dto.TaAcompteDTO;
import fr.legrain.document.dto.TaFactureDTO;
import fr.legrain.document.model.TaAcompte;
import fr.legrain.document.model.TaAvoir;
import fr.legrain.document.model.TaAcompte;
import fr.legrain.document.model.TaFacture;
import fr.legrain.document.model.TaInfosAcompte;
import fr.legrain.document.model.TaLAcompte;
import fr.legrain.document.model.TaReglement;
import fr.legrain.document.model.TaTicketDeCaisse;
import fr.legrain.documents.dao.IAcompteDAO;
import fr.legrain.documents.dao.IReglementDAO;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.LibDate;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.BeanValidator;

/**
 * Home object for domain model class TaAcompte.
 * @see fr.legrain.documents.dao.TaAcompte_old
 * @author Hibernate Tools
 */
public class TaAcompteDAO extends AbstractDocumentDAO<TaAcompte,TaInfosAcompte,TaLAcompte> /*extends AbstractApplicationDAO<TaAcompte>*/ implements IAcompteDAO {

	
	@Inject private IReglementDAO reglementDAO;
//	private static final Log log = LogFactory.getLog(TaAcompteDAO.class);
	static Logger logger = Logger.getLogger(TaAcompteDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaAcompte a";
	
	public TaAcompteDAO(){
//		this(null);
	}
	
//	public TaAcompteDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaAcompte.class.getSimpleName());
//		initChampId(TaAcompte.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaAcompte());
//	}
	
//	public TaAcompte refresh(TaAcompte detachedInstance) {
//        logger.debug("refresh instance");
//        try {
//        	detachedInstance.setLegrain(false);
//        	entityManager.refresh(detachedInstance);
////            detachedInstance.setLegrain(false);
////            org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////
////            for (TaRAcompte a : detachedInstance.getTaRAcomptes()) {
////
////                 if(a.getTaDevis()!=null) {
////                	 for (TaRAcompte b : a.getTaDevis().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaDevis().getTaRAcomptes());
////                 }
////                 if(a.getTaBoncde()!=null) {
////                	 for (TaRAcompte b : a.getTaBoncde().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaBoncde().getTaRAcomptes());
////                 }
////                 if(a.getTaProforma()!=null) {
////                	 for (TaRAcompte b : a.getTaProforma().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaProforma().getTaRAcomptes());
////                 } 
////                 if(a.getTaFacture()!=null) {
////                	 for (TaRAcompte b : a.getTaFacture().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaFacture().getTaRAcomptes());
////                 }
////                 if(a.getTaAcompte()!=null) {
////                	 for (TaRAcompte b : a.getTaAcompte().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaAcompte().getTaRAcomptes());
////                 }
////                 if(a.getTaAvoir()!=null) {
////                	 for (TaRAcompte b : a.getTaAvoir().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaAvoir().getTaRAcomptes());
////                 }
////                 if(a.getTaApporteur()!=null) {
////                	 for (TaRAcompte b : a.getTaApporteur().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaApporteur().getTaRAcomptes());
////                 }
////                 if(a.getTaPrelevement()!=null) {
////                	 for (TaRAcompte b : a.getTaPrelevement().getTaRAcomptes()) {
////                		 session.evict(b.getTaAcompte());
////                		 session.evict(b);
////                	 }
////                	 session.evict(a.getTaPrelevement().getTaRAcomptes());
////                 }
////                 session.evict(a.getTaDevis());
////                 session.evict(a.getTaApporteur());
////                 session.evict(a.getTaAvoir());
////                 session.evict(a.getTaBoncde());
////                 session.evict(a.getTaAcompte());
////                 session.evict(a.getTaFacture());
////                 session.evict(a.getTaProforma());
////                 session.evict(a.getTaPrelevement());
////              	for (TaRAcompte c : a.getTaAcompte().getTaRAcomptes()) {
////            		session.evict(c);
////            	}
////            	session.evict(a.getTaAcompte().getTaRAcomptes());
////            	session.evict(a.getTaAcompte());
////                 
////                 session.evict(a);
////            }
////            session.evict(detachedInstance.getTaRAcomptes());
////            session.evict(detachedInstance);
////            
////            detachedInstance = entityManager.find(TaAcompte.class, detachedInstance.getIdDocument());
//            detachedInstance.setLegrain(true);
//            detachedInstance.calculLignesTva();
//            return detachedInstance;
//        } catch (RuntimeException re) {
//            logger.error("refresh failed", re);
//            throw re;
//        }
//    } 
	
	public void persist(TaAcompte transientInstance) {
		logger.debug("persisting TaAcompte instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("persist failed", re);
			throw re2;
		}
	}

	public void remove(TaAcompte persistentInstance) {
		logger.debug("removing TaAcompte instance");
		try {
			if(persistentInstance.getTaReglement()!=null){				
				TaReglement reglement=reglementDAO.findById(persistentInstance.getTaReglement().getIdDocument());
				reglement.setTaAcompte(null);
				reglementDAO.merge(reglement);
			}
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("remove failed", re);
			throw re2;
		}
	}
	
	public void annuler(TaAcompte persistentInstance) throws Exception{
		persistentInstance.setLegrain(false);
		List<TaLAcompte> listTemp=new ArrayList<TaLAcompte>(0);
		for (Object ligne : persistentInstance.getLignes()) {
			if(((TaLAcompte)ligne).getIdLDocument()==0)
				listTemp.add((TaLAcompte)ligne);				
		}
		for (TaLAcompte taLAcompte : listTemp) {
			persistentInstance.getLignes().remove(taLAcompte);
		}
//		super.annuler(persistentInstance);
	}
	

	public TaAcompte merge(TaAcompte detachedInstance) {
		logger.debug("merging TaAcompte instance");
		try {
			TaAcompte result = entityManager.merge(detachedInstance);
//			if(detachedInstance.getTaInfosDocument()==null) {
//				throw new RuntimeException("Il manque l'infoDocument du document n??: "+detachedInstance.getCodeDocument());
//			}
			if(detachedInstance.getTaReglement()!=null){
				detachedInstance.getTaReglement().setTaAcompte(result);
				detachedInstance.setTaReglement(reglementDAO.merge(detachedInstance.getTaReglement()));
			}
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("merge failed", re);
			throw re2;
		}
	}

	public TaAcompte findById(int id) {
		logger.debug("getting TaAcompte instance with id: " + id);
		try {
			TaAcompte instance = entityManager.find(TaAcompte.class, id);
//			if(instance!=null)
//				instance.setTaReglement(reglementDAO.findByCodeAcompte(instance.getCodeDocument()));
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public TaAcompte findByCode(String code) {
		logger.debug("getting TaAcompte instance with code: " + code);
		try {
			Query query = entityManager.createQuery("select a from TaAcompte a " +
					"  left join FETCH a.lignes l"+
//					"  left join FETCH a.taRAcomptesDevis RDevis"+
//					"  left join FETCH a.taRAcomptesBonCde RBonCde"+
//					"  left join FETCH a.taRAcomptesProforma RProforma"+
//					"  left join FETCH a.taRAcomptesFacture RFacture"+
					" where a.codeDocument='"+code+"' " +
							" order by l.numLigneLDocument");
			TaAcompte instance = (TaAcompte)query.getSingleResult();
			instance.setTaReglement(reglementDAO.findByCodeAcompte(instance.getCodeDocument()));
			instance.setLegrain(true);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List<TaAcompte> selectAll() {
		logger.debug("TaAcompte selectAll");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaAcompte> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List<TaAcompte> selectAllDisponible(TaFacture taFacture) {
		logger.debug("TaAcompte selectAllDisponible");
		try {
			String requete ="";
			Query ejbQuery =null;
			if(taFacture!=null /*&& taFacture.getIdDocument()!=0*/){
			requete ="select a from TaAcompte  a " +
			" where a.taTiers=:taTiers and (" +
//			" not exists(select ra from TaRAcompte ra " +
//					"where  ra.taAcompte=a and ra.taFacture =?) and(  " +
			" not exists(select ra from TaRAcompte ra " +
					"where  ra.taAcompte=a and ra.taFacture is not null) or" +
			" (a.netTtcCalc > (select sum(ra.affectation) from TaRAcompte ra " +
					"where  ra.taAcompte=a and ra.taFacture is not null))) " ;
			ejbQuery = entityManager.createQuery(requete);
			ejbQuery.setParameter("taTiers", taFacture.getTaTiers());
//			ejbQuery.setParameter(2, taFacture);
			}
//			else{
//				requete ="select a from TaAcompte  a " +
//				" where a.taTiers=? and" +
//				" not exists(select ra from TaRAcompte ra " +
//						"where  ra.taAcompte=a and ra.taFacture is not null) or" +
//				" (a.netTtcCalc > (select sum(ra.affectation) from TaRAcompte ra " +
//						"where  ra.taAcompte=a and ra.taFacture is not null)) " ;	
//				ejbQuery = entityManager.createQuery(requete);
//				ejbQuery.setParameter(1, taFacture.getTaTiers());
//			}
			if(taFacture!=null /*&& taFacture.getIdDocument()!=0*/){
				List<TaAcompte> l = ejbQuery.getResultList();
				logger.debug("selectAllDisponible successful");
				return l;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("selectAllDisponible failed", re);
			throw re;
		}
	}
	/**
	 * Recherche les factures apporteur entre 2 dates
	 * @param dateDeb - date de d??but
	 * @param dateFin - date de fin
	 * @return String[] - tableau contenant les IDs des factures apporteur entre ces 2 dates (null en cas d'erreur)
	 */
	public List<TaAcompte> rechercheDocument(Date dateDeb, Date dateFin) {
		List<TaAcompte> result = null;
		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		return result;
	}
	
	
	/**
	 * Recherche les factures apporteur entre 2 dates ordonn??es par date
	 * @param dateDeb - date de d??but
	 * @param dateFin - date de fin
	 * @return String[] - tableau contenant les IDs des factures apporteur entre ces 2 dates (null en cas d'erreur)
	 */
	public List<TaAcompte> rechercheDocumentOrderByDate(Date dateDeb, Date dateFin) {
		List<TaAcompte> result = null;
		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_PARDATE);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		return result;
	}
	
	/**
	 * Recherche les factures apporteur entre 2 codes factures apporteur
	 * @param codeDeb - code de d??but
	 * @param codeFin - code de fin
	 * @return String[] - tableau contenant les IDs des factures apporteur entre ces 2 codes (null en cas d'erreur)
	 */
	public List<TaAcompte> rechercheDocument(String codeDeb, String codeFin) {		
		List<TaAcompte> result = null;
		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_CODE);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", codeDeb);
		query.setParameter("dateFin", codeFin);
		result = query.getResultList();
		return result;
	}
	
//	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public List<TaAcompte> findByCodeTiersAndDate(String codeTiers, Date debut, Date fin) {
		logger.debug("getting TaAcompte instance with codeTiers: " + codeTiers);
		List<TaAcompte> result = null;
		try {
			Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_TIERS_AND_DATE);
			query.setParameter("codeTiers", codeTiers);
			query.setParameter("dateDeb", debut);
			query.setParameter("dateFin", fin);
			result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	public void messageNonAutoriseModificationRelation(TaAcompte acompte) throws Exception{
//			if(!dataSetEnModif()) {
//					String message="l'acompte "+acompte.getCodeDocument()+" a ??t?? modifi?? par ailleurs !!!" +
//					"\r\nIl doit ??tre recharg?? pour y " +
//					"apporter de nouvelles modifications.";
//					MessageDialog.openWarning(PlatformUI.getWorkbench()
//							.getActiveWorkbenchWindow().getShell(),"Attention",
//							message);
//					throw new Exception();
//			}
	}
	
	/**
	 * |ann??e|codeTiers|HT|TVA|TTC
	 * @param codeTiers
	 * @param debut
	 * @param fin
	 * @return
	 */
	public List<Object> findChiffreAffaireByCodeTiers(String codeTiers,Date debut, Date fin, int precision) {
		logger.debug("getting TaAcompte instance with codeTiers: " + codeTiers);
		List<Object> result = null;
		try {
			String jpql = null;
//			precision = 0;
			String groupBy = null;
			String select = null;
			
			if(precision==0) {
				select = "'','',cast(extract(year from f.dateDocument)as string)";
				groupBy = "'','',extract(year from f.dateDocument)";
			} else if (precision==1){
				select = "'',cast(extract(month from f.dateDocument)as string),cast(extract(year from f.dateDocument)as string)";
				groupBy = "'',extract(month from f.dateDocument),extract(year from f.dateDocument)";
			} else {
				select = "cast(extract(day from f.dateDocument)as string),cast(extract(month from f.dateDocument)as string),cast(extract(year from f.dateDocument)as string)";
				groupBy = "extract(day from f.dateDocument),extract(month from f.dateDocument),extract(year from f.dateDocument)";
			}
			
			jpql = "select  "
				+select+", t.codeTiers,"
				+"case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netHtCalc/6.55957) else sum(f.netHtCalc) end, "
				+"case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netTvaCalc/6.55957) else sum(f.netTvaCalc) end, "
				+"case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netTtcCalc/6.55957 ) else sum(f.netTtcCalc) end "
				+" from TaAcompte f "
				+"left join f.taTiers t "
				+"where  t.codeTiers = :codeTiers and f.dateDocument between :debut and :fin "
				+"group by "+groupBy+",t.codeTiers"
				;

//			jpql = "select  "
//			+"cast(extract(year from f.dateDocument)as string), t.codeTiers,"
//			+"case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netHtCalc/6.55957) else sum(f.netHtCalc) end, "
//			+"case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netTvaCalc/6.55957) else sum(f.netTvaCalc) end, "
//			+"case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netTtcCalc/6.55957 ) else sum(f.netTtcCalc) end "
//			+" from TaAcompte f "
//			+"left join f.taTiers t "
//			+"where  t.codeTiers = ? and f.dateDocument between ? and ? "
//			+"group by extract(year from f.dateDocument),t.codeTiers"
//			;
			Query query = entityManager.createQuery(jpql);
			query.setParameter("codeTiers", codeTiers);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * Classe permettant d'obtenir le ca g??n??r?? par les apporteur sur une p??riode donn??e
	 * @param debut date de d??but des donn??es
	 * @param fin date de fin des donn??es
	 * @return La requ??te renvoi le montant des par les apporteurs sur la p??riode
	 */
	public List<Object> findCASurPeriode(Date debut, Date fin) {
		logger.debug("getting nombre ca des apporteurs");
		List<Object> result = null;
		
		
		try {
			String requete = "";

			requete = "SELECT "
				+" sum(d.netHtCalc)"
				+" FROM TaAcompte d " 
				+" where d.dateDocument between :debut and :fin";
			Query query = entityManager.createQuery(requete);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * M??thode permettant de retourner le chiffre d'affaire total g??n??r??
	 * @param debut
	 * @param fin
	 * @param precision
	 * @return
	 */
	public List<Object> findChiffreAffaireTotal(Date debut, Date fin, int precision) {
		logger.debug("getting ChiffreAffaire total");
		List<Object> result = null;
		try {
			String requete = "";
			String groupBy = "";
			String select = "";
			
			if(precision==0) {
				select = "'','',cast(extract(year from f.dateDocument)as string)";
				groupBy = "'','',extract(year from f.dateDocument)";
			} else if (precision==1){
				select = "'',cast(extract(month from f.dateDocument)as string),cast(extract(year from f.dateDocument)as string)";
				groupBy = "'',extract(month from f.dateDocument),extract(year from f.dateDocument)";
			} else {
				select = "cast(extract(day from f.dateDocument)as string),cast(extract(month from f.dateDocument)as string),cast(extract(year from f.dateDocument)as string)";
				groupBy = "extract(day from f.dateDocument),extract(month from f.dateDocument),extract(year from f.dateDocument)";
			}
			
			requete = "SELECT "+select+ ", "
			+" case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netHtCalc/6.55957) else sum(f.netHtCalc) end, "
			+" case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netTvaCalc/6.55957) else sum(f.netTvaCalc) end, "
			+" case when extract ( year from f.dateDocument) <= 2001 then  sum(f.netTtcCalc/6.55957 ) else sum(f.netTtcCalc) end "
			+" FROM TaAcompte f " 
			+" where f.dateDocument between :debut and :fin"
			+" group by "+groupBy; 
			Query query = entityManager.createQuery(requete);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * Classe permettant d'obtenir de la base de donn??es X clients ordonn??s par chiffre d'affaires
	 * @param debut date de d??but des donn??es
	 * @param fin date de fin des donn??es
	 * @param leNbResult le nombre de r??sultats ?? sortir de la BD
	 * Si cet int est positif, il sort les leNbResult meilleurs
	 * Si cet int est n??gatif, il sort les leNbResult moins bons
	 * @return La requ??te renvoy??e renvoi le CA, le codeTiers, le nom et le CP
	 */
	public List<Object> findMeilleursClientsParCA(Date debut, Date fin, int leNbResult) {
		logger.debug("getting ChiffreAffaire total ordo tiers");
		
		int nbResult = leNbResult; // nombre de r??sultats ?? afficher
		String ordre = "DESC";     // Ordre d'affichage
		
		if (nbResult<0){ // On passe en param??tres une valeur n??gative : on souhaite les x derniers
			nbResult = Math.abs(leNbResult); // On prend la valeur absolue du nombre d??sir??
			ordre = "ASC";					   // On inverse l'ordre d'affichage
			
		}
		List<Object> result = null;
		try {
			String requete = "";

			
			requete = "SELECT "
			+" sum(f.mtHtCalc)"
			+", t.codeTiers"
			+", t.nomTiers"
			+", a.codepostalAdresse"
			+" FROM TaAcompte f " 
			+" left join f.taTiers t"
			+" left join t.taAdresse a"
			+" where f.dateDocument between :debut and :fin"
			+" group by t.codeTiers, t.nomTiers, a.codepostalAdresse"
			+" order by sum(f.mtHtCalc)"+ordre;
			Query query = entityManager.createQuery(requete);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			query.setMaxResults(nbResult);
			result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * Classe permettant d'obtenir de la base de donn??es X articles ordonn??s par chiffre d'affaires
	 * @param debut date de d??but des donn??es
	 * @param fin date de fin des donn??es
	 * @param leNbResult le nombre de r??sultats ?? sortir de la BD
	 * Si cet int est positif, il sort les leNbResult meilleurs
	 * Si cet int est n??gatif, il sort les leNbResult moins bons
	 * @return La requ??te renvoy??e renvoi le CA, le codeTiers, le nom et le CP
	 */
	public List<Object> findMeilleursArticlesParCA(Date debut, Date fin, int leNbResult) {
		logger.debug("getting ChiffreAffaire total ordo articles");
		
		int nbResult = leNbResult; // nombre de r??sultats ?? afficher
		String ordre = "DESC";     // Ordre d'affichage
		
		if (nbResult<0){ // On passe en param??tres une valeur n??gative : on souhaite les x derniers
			nbResult = Math.abs(leNbResult); // On prend la valeur absolue du nombre d??sir??
			ordre = "ASC";					   // On inverse l'ordre d'affichage
			
		}
		List<Object> result = null;
		try {
			String requete = "";

			
			requete = "SELECT a.codeArticle"
			+", a.libellecArticle"
			+", p.prixPrix"
			+", sum(l.qteLDocument)"
			+", sum(l.mtHtLDocument)"
			+" FROM TaLAcompte l " 
			+" left join l.taDocument f"
			+" left join l.taArticle a"
			+" left join a.taPrix p"
			+" where f.dateDocument between :debut and :fin"
			+" group by a.codeArticle, a.libellecArticle, p.prixPrix"
			+" order by sum(l.mtHtLDocument)"+ordre;
			Query query = entityManager.createQuery(requete);
			query.setParameter("debut", debut);
			query.setParameter("fin", fin);
			query.setMaxResults(nbResult);
			result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	

	/**
	 * Recherche les acomptes entre 2 dates et non exportees
	 * @param dateDeb - date de d??but
	 * @param dateFin - date de fin
	 * @return String[] - tableau contenant les IDs des acomptes entre ces 2 dates (null en cas d'erreur)
	 */
	public List<TaAcompte> rechercheDocumentNonExporte(Date dateDeb, Date dateFin) {
		List<TaAcompte> result = null;
		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_NON_EXPORT);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		
		
		return result;
		
	}

	
	
	/**
	 * Recherche les acomptes non inclus dans des remises entre 2 dates
	 * @param codeTiers
	 * @param dateDeb
	 * @param DateFin
	 * @param export
	 * @param codeTPaiement
	 * @param compteBancaire
	 * @return String[] - tableau contenant les IDs des acomptes entre ces 2 dates (null en cas d'erreur)
	 */
	public List<TaAcompte> rechercheAcompteNonRemises(String codeTiers,Date dateDeb,Date DateFin,Boolean export,String codeTPaiement,String iban,boolean byDate) {
		String requete="select r from TaAcompte r where not exists" +
				"(select a from TaLRemise a where a.taAcompte.codeDocument=r.codeDocument)" +
				" and r.taTiers.codeTiers like :codeTiers " +
				" and r.dateDocument between :dateDeb and :DateFin" +
				" and r.taTPaiement.codeTPaiement like :codeTPaiement"+
				" and r.taCompteBanque.iban like :iban";
		
		if(!export)requete+=" and r.dateExport is null " ;
		if(byDate)requete+=" order by r.dateDocument";else requete+=" order by r.codeDocument";
		
		try {
			if(codeTiers==null ||codeTiers.equals(""))codeTiers="%";
			if(codeTPaiement==null ||codeTPaiement.equals(""))codeTPaiement="%";
			if(iban==null ||iban.equals(""))iban="%";
			Query ejbQuery = entityManager.createQuery(requete);
			ejbQuery.setParameter("codeTiers",codeTiers );
			ejbQuery.setParameter("dateDeb",dateDeb );
			ejbQuery.setParameter("DateFin", DateFin);
			ejbQuery.setParameter("codeTPaiement", codeTPaiement);
			ejbQuery.setParameter("iban", iban);
			
			
			List<TaAcompte> l = ejbQuery.getResultList();
			logger.debug("rechercheAcompteNonRemises successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("rechercheAcompteNonRemises failed", re);
			throw re;
		}
	}

	@Override
	public List<TaAcompte> rechercheDocument(String codeDeb, String codeFin,
			String codeTiers) {
		return rechercheDocument(codeDeb, codeFin, codeTiers,null);
	}

	@Override
	public List<TaAcompte> rechercheDocument(Date dateDeb, Date dateFin,
			String codeTiers) {
		return rechercheDocument(dateDeb, dateFin, codeTiers,null);
	}

	@Override
	public List<TaAcompte> rechercheDocument(Date dateDeb, Date dateFin,
			String codeTiers, Boolean export) {
		List<TaAcompte> result = null;
		Query query = null;
		if(export!=null && export)query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_EXPORT);
		else if(export!=null && export==false)query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_NON_EXPORT);
		else
		query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_TIERS_AND_DATE);
		if(codeTiers==null || codeTiers.equals(""))codeTiers="%";
		query.setParameter("codeTiers", codeTiers);
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		return result;
	}

	@Override
	public List<TaAcompte> rechercheDocument(String codeDeb, String codeFin,
			String codeTiers, Boolean export) {
		List<TaAcompte> result = null;
		Query query = null;
		if(export!=null && export)query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_CODE_EXPORT);
		else if(export!=null && export==false)query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_CODE_NON_EXPORT);
		else query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_TIERS_AND_CODE);
		if(codeTiers==null || codeTiers.equals(""))codeTiers="%";
		query.setParameter("codeTiers", codeTiers);
		query.setParameter("dateDeb", codeDeb);
		query.setParameter("dateFin", codeFin);
		result = query.getResultList();
		return result;
	}

	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List<TaAcompte> selectAll(IDocumentTiers taDocument,Date dateDeb,Date dateFin) {
		TaTiers taTiersLoc=null;
		if(taDocument!=null)taTiersLoc=taDocument.getTaTiers();
		logger.debug("selectAll TaAcompte");
		try {
			if(taTiersLoc!=null){				
				if(dateDeb==null)dateDeb=new Date(0);
				if(dateFin==null)dateFin=new Date("01/01/3000");
				
				String requete="select a from TaAcompte a where (" +
				" not exists(select r from TaRAcompte r where r.taAcompte.idDocument=a.id " +
				" and (r.taFacture=:taFacture))" +
//				" and exists(select r from TaRAcompte r where r.taAcompte.idDocument<>a.id " +
//				" and r.taFacture.taTiers=?) " +
				" ) and a.taTiers =:taTiers"+
				" and a.dateDocument between :dateDeb and :dateFin";
				
				Query ejbQuery = entityManager.createQuery(requete);
				ejbQuery.setParameter("taFacture", taDocument);
				ejbQuery.setParameter("taTiers", taTiersLoc);
//				ejbQuery.setParameter(3, taTiersLoc);
				ejbQuery.setParameter("dateDeb", dateDeb,TemporalType.DATE);
				ejbQuery.setParameter("dateFin", dateFin,TemporalType.DATE);

				List<TaAcompte> l = ejbQuery.getResultList();
				logger.debug("selectAll successful");
				return l;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}

	/**
	 * Recherche les factures entre 2 dates light
	 * @param dateDeb - date de d??but
	 * @param dateFin - date de fin
	 * @return String[] - tableau contenant les IDs des factures entre ces 2 dates (null en cas d'erreur)
	 */
	//public String[] rechercheFacture(Date dateDeb, Date dateFin) {
	public List<Object[]> rechercheDocument(Date dateDeb, Date dateFin,Boolean light) {
		List<Object[]> result = null;
		Query query = null;
		if(light)
			query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_LIGHT);
		else query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		
		
		return result;
		
	}

	@Override
	public List<Object[]> rechercheDocumentLight(Date dateDeb, Date dateFin,String codeTiers) {
		List<Object[]> result = null;
		Query query = null;
		query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_LIGHT);
		if(codeTiers==null || codeTiers.equals(""))codeTiers="%";
		query.setParameter("codeTiers", codeTiers);
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<Object[]> rechercheDocumentLight(String codeDoc, String codeTiers) {
			List<Object[]> result = null;
			Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_TIERS_AND_CODE_LIGHT);
			query.setParameter("codeTiers","%");
			query.setParameter("codeDocument", codeDoc+"%");
			result = query.getResultList();
			return result;

		}

	@Override
	public List<TaAcompte> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaAcompte> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TaAcompte> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaAcompte> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaAcompte value) throws Exception {
		BeanValidator<TaAcompte> validator = new BeanValidator<TaAcompte>(TaAcompte.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaAcompte value, String propertyName)
			throws Exception {
		BeanValidator<TaAcompte> validator = new BeanValidator<TaAcompte>(TaAcompte.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaAcompte transientInstance) {
		entityManager.detach(transientInstance);
		
	}
	
	@Override
	public String genereCode() {
		System.err.println("******************* NON IMPLEMENTE ****************************************");
		return null;
	}
	
	public List<TaAcompteDTO> findAllLight() {
		try {
			Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_ALL_LIGHT);
			List<TaAcompteDTO> l = query.getResultList();;
			logger.debug("get successful");
			return l;

		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<TaAcompte> rechercheDocumentNonExporte(Date dateDeb, Date dateFin, boolean parDate) {
		List<TaAcompte> result = null;
		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_NON_EXPORT);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		
		
		return result;
		
	}

	/**
	 * Recherche les acomptes entre 2 dates et non exportees
	 * @param dateDeb - date de d??but
	 * @param dateFin - date de fin
	 * @return String[] - tableau contenant les IDs des acomptes entre ces 2 dates (null en cas d'erreur)
	 */
	public List<TaAcompteDTO> rechercheDocumentNonExporteLight(Date dateDeb, Date dateFin, boolean parDate) {
		List<TaAcompteDTO> result = null;
		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_BY_DATE_NON_EXPORT_LIGHT);
		query.setParameter("codeTiers","%");
		query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
		query.setParameter("dateFin", dateFin, TemporalType.DATE);
		result = query.getResultList();
		
		
		return result;
		
	}
	

//	/**-------------------------------------------------------------------------------------
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement sur la p??riode ??clat?? en fonction de la pr??cision 
//	 * Jour Mois Ann??e
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalJmaDTO(Date dateDebut, Date dateFin, int precision,String codeTiers) {
//		Query query = null;
//		if(codeTiers==null)codeTiers="%";
//		try {
//			switch (precision) {
//			case 0:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_ANNEE_LIGTH_PERIODE);
//				
//				break;
//
//			case 1:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_MOIS_LIGTH_PERIODE);
//				
//				break;
//			case 2:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_JOUR_LIGTH_PERIODE);
//				
//				break;
//
//			default:
//				break;
//			}
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}

//	/**
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement sur la p??riode 
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
//		try {
//			Query query = null;
//			if(codeTiers==null)codeTiers="%";
//			query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_TOTAL_LIGTH_PERIODE);
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}


//	/**
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement transform??s sur la p??riode ??clat?? en fonction de la pr??cision 
//	 * Jour Mois Ann??e
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTransformeJmaDTO(Date dateDebut, Date dateFin, int precision,String codeTiers) {
//		Query query = null;
//		if(codeTiers==null)codeTiers="%";
//		try {
//			switch (precision) {
//			case 0:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_ANNEE_LIGTH_PERIODE_TRANSFORME);
//				
//				break;
//
//			case 1:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_MOIS_LIGTH_PERIODE_TRANSFORME);
//				
//				break;
//			case 2:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_JOUR_LIGTH_PERIODE_TRANSFORME);
//				
//				break;
//
//			default:
//				break;
//			}
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}
//	
//	/**
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement transform??s sur la p??riode 
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
//		try {
//			Query query = null;
//			if(codeTiers==null)codeTiers="%";
//			query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_TOTAL_LIGTH_PERIODE_TRANSFORME);
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}

	

//	/**
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement Non transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement Non transform??s sur la p??riode ??clat?? en fonction de la pr??cision 
//	 * Jour Mois Ann??e
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeJmaDTO(Date dateDebut, Date dateFin, int precision,String codeTiers) {
//		Query query = null;
//		if(codeTiers==null)codeTiers="%";
//		try {
//			switch (precision) {
//			case 0:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_ANNEE_LIGTH_PERIODE_NON_TRANSFORME);
//				
//				break;
//
//			case 1:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_MOIS_LIGTH_PERIODE_NON_TRANSFORME);
//				
//				break;
//			case 2:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_JOUR_LIGTH_PERIODE_NON_TRANSFORME);
//				
//				break;
//
//			default:
//				break;
//			}
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}
	
//	/**
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement Non transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement Non transform??s sur la p??riode ??clat?? en fonction de la pr??cision 
//	 * Jour Mois Ann??e
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeARelancerJmaDTO(Date dateDebut, Date dateFin, int precision, int deltaNbJours,String codeTiers) {
//		Query query = null;
//		try {
//			Date dateref = LibDate.incrementDate(LibDate.dateDuJour(), deltaNbJours, 0, 0);
//			Date datejour = LibDate.dateDuJour();
//			if(codeTiers==null)codeTiers="%";
//			switch (precision) {
//			case 0:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_ANNEE_LIGTH_PERIODE_NON_TRANSFORME_A_RELANCER);
//				
//				break;
//
//			case 1:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_MOIS_LIGTH_PERIODE_NON_TRANSFORME_A_RELANCER);
//				
//				break;
//			case 2:
//				query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_JOUR_LIGTH_PERIODE_NON_TRANSFORME_A_RELANCER);
//				
//				break;
//
//			default:
//				break;
//			}
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("dateref", dateref, TemporalType.DATE);
//			query.setParameter("datejour", datejour, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}
//	
//	/**
//	 * Classe permettant d'obtenir le ca g??n??r?? par les Prelevement non transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le CA des Prelevement non transform??s sur la p??riode 
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers) {
//		try {
//			Query query = null;
//			if(codeTiers==null)codeTiers="%";
//			query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_TOTAL_LIGTH_PERIODE_NON_TRANSFORME);
//			query.setParameter("datedebut", dateDebut, TemporalType.DATE);
//			query.setParameter("datefin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//			
//	}
	
//	 /**
//	 * Classe permettant d'obtenir les Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi la liste des Prelevement non transform??s
//	 */
//	public List<DocumentDTO> findDocumentNonTransfosDTO(Date dateDebut, Date dateFin,String codeTiers) {
//		logger.debug("getting nombre Acompte non transfos");
//		List<DocumentDTO> result = null;
//		if(codeTiers==null)codeTiers="%";
//		try {
//		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_NON_TRANSFORME_LIGHT_PERIODE);
//		query.setParameter("dateDebut", dateDebut, TemporalType.DATE);
//		query.setParameter("dateFin", dateFin, TemporalType.DATE);
//		query.setParameter("codeTiers",codeTiers);
//		List<DocumentDTO> l = query.getResultList();;
//		logger.debug("get successful");
//		return l;
//
//	} catch (RuntimeException re) {
//		logger.error("get failed", re);
//		throw re;
//	}
//	}
//
//	 /**
//	 * Classe permettant d'obtenir la liste des Prelevement transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi la liste des Prelevement non transform??s
//	 */
//	public List<DocumentDTO> findDocumentTransfosDTO(Date dateDebut, Date dateFin,String codeTiers) {
//		logger.debug("getting nombre Acompte transforme");
//		List<DocumentDTO> result = null;
//		if(codeTiers==null)codeTiers="%";
//		try {
//		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_TRANSFORME_LIGHT_PERIODE);
//		query.setParameter("dateDebut", dateDebut, TemporalType.DATE);
//		query.setParameter("dateFin", dateFin, TemporalType.DATE);
//		query.setParameter("codeTiers",codeTiers);
//		List<DocumentDTO> l = query.getResultList();;
//		logger.debug("get successful");
//		return l;
//
//	} catch (RuntimeException re) {
//		logger.error("get failed", re);
//		throw re;
//	}
//	}
//	
//	 /**
//	 * Classe permettant d'obtenir la listes des Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @param deltaNbJours Permet de calculer la date de r??f??rence que les dates d'??ch??ances 
//	 * ne doivent pas d??passer par rapport ?? la date du jour 
//	 * @return La requ??te renvoy??e renvoi la liste des Prelevement non transform??s ?? relancer
//	 */
//	public List<DocumentDTO> findDocumentNonTransfosARelancerDTO(Date dateDebut, Date dateFin, int deltaNbJours,String codeTiers) {
//		logger.debug("getting liste Acompte non transfos ?? relancer");
//		List<DocumentDTO> result = null;
//		try {
//		Date dateref = LibDate.incrementDate(LibDate.dateDuJour(), deltaNbJours, 0, 0);
//		Date datejour = LibDate.dateDuJour();
//		if(codeTiers==null)codeTiers="%";
//		Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_NON_TRANSFORME_ARELANCER_LIGHT_PERIODE);
//		query.setParameter("dateDebut", dateDebut, TemporalType.DATE);
//		query.setParameter("dateFin", dateFin, TemporalType.DATE);
//		query.setParameter("dateref", dateref, TemporalType.DATE);
//		query.setParameter("datejour", datejour, TemporalType.DATE);
//		query.setParameter("codeTiers",codeTiers);
//		List<DocumentDTO> l = query.getResultList();;
//		logger.debug("get successful");
//		return l;
//
//	} catch (RuntimeException re) {
//		logger.error("get failed", re);
//		throw re;
//	}
//	}
	
//	 /**
//	 * Classe permettant d'obtenir la listes des Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @param deltaNbJours Permet de calculer la date de r??f??rence que les dates d'??ch??ances 
//	 * ne doivent pas d??passer par rapport ?? la date du jour 
//	 * @return La requ??te renvoy??e renvoi la liste des Prelevement non transform??s ?? relancer
//	 */
//	public List<DocumentChiffreAffaireDTO> listeChiffreAffaireNonTransformeARelancerTotalDTO(Date dateDebut, Date dateFin, int deltaNbJours,String codeTiers) {
//		logger.debug("getting ca des Acompte non transfos ?? relancer");
//		List<TaAcompteDTO> result = null;
//		if(codeTiers==null)codeTiers="%";
//		try {
//		Date dateref = LibDate.incrementDate(LibDate.dateDuJour(), deltaNbJours, 0, 0);
//		Date datejour = LibDate.dateDuJour();
//		Query query = entityManager.createNamedQuery(TaAcompte.QN.SUM_CA_TOTAL_NON_TRANSFORME_ARELANCER_LIGHT_PERIODE);
//		query.setParameter("dateDebut", dateDebut, TemporalType.DATE);
//		query.setParameter("dateFin", dateFin, TemporalType.DATE);
//		query.setParameter("dateref", dateref, TemporalType.DATE);
//		query.setParameter("datejour", datejour, TemporalType.DATE);
//		query.setParameter("codeTiers",codeTiers);
//		List<DocumentChiffreAffaireDTO> l = query.getResultList();;
//		logger.debug("get successful");
//		return l;
//
//	} catch (RuntimeException re) {
//		logger.error("get failed", re);
//		throw re;
//	}
//	}
//
	
//	 /**
//	 * Classe permettant d'obtenir les Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le nombre total de Prelevement dans la p??riode
//	 */
//	public long countDocument(Date debut, Date fin,String codeTiers) {
//		logger.debug("getting nombre Acompte dans periode");
//		Long result = (long) 0;
//		
//		if(codeTiers==null)codeTiers="%";
//		try {
//			String requete = "";
//
//			requete = "SELECT "
//				+" count(d)"
//				+" FROM TaAcompte d " 
//				+" where d.dateDocument between :datedeb and :datefin  and d.taTiers.codeTiers like :codeTiers";
//			Query query = entityManager.createQuery(requete);
//			query.setParameter("datedeb", debut);
//			query.setParameter("datefin", fin);
//			query.setParameter("codeTiers", codeTiers);
//			Long nbAcompte = (Long)query.getSingleResult();
//			result = nbAcompte;
//			return result;
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//	}
//
//	/**
//	 * Classe permettant d'obtenir les Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le nombre de Prelevement non transform??s
//	 */
//	public long countDocumentNonTransforme(Date debut, Date fin,String codeTiers) {
//		logger.debug("getting nombre Acompte non transfos");
//		Long result = (long) 0;
//		
//		if(codeTiers==null)codeTiers="%";
//		try {
//			String requete = "";
//
//			requete = "SELECT "
//				+" count(d)"
//				+" FROM TaAcompte d " 
//				+" where d.dateDocument between :datedeb and :datefin"
//				+" and not exists (select r " +
//						"from TaRDocument r " +
//						" where r.taAcompte = d" +
//						" and ( taFacture IS NOT NULL))  and d.taTiers.codeTiers like :codeTiers";;
////						+" order by d.mtHtCalc DESC";;
//			Query query = entityManager.createQuery(requete);
//			query.setParameter("datedeb", debut);
//			query.setParameter("datefin", fin);
//			query.setParameter("codeTiers", codeTiers);
//			Long nbAcompteNonTranforme = (Long)query.getSingleResult();
//			result = nbAcompteNonTranforme;
//			return result;
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//	}
//
//	/**
//	 * Classe permettant d'obtenir les Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le nombre de Prelevement non transform??s ?? relancer
//	 */
//	public long countDocumentNonTransformeARelancer(Date debut, Date fin, int deltaNbJours,String codeTiers) {
//		logger.debug("getting nombre Acompte non transfos ?? relancer");
//		Long result = (long) 0;
//		Date dateref = LibDate.incrementDate(LibDate.dateDuJour(), deltaNbJours, 0, 0);
//		Date dateJour = LibDate.dateDuJour();
//		
//		if(codeTiers==null)codeTiers="%";
//		try {
//			String requete = "";
//
//			requete = "SELECT "
//				+" count(d)"
//				+" FROM TaAcompte d " 
//				+" where d.dateDocument between :datedeb and :datefin"
//				+" and d.dateEchDocument <= :dateref"
//				+" and d.dateEchDocument >= :datejour"
//				+" and not exists (select r " +
//						"from TaRDocument r " +
//						" where r.taAcompte = d" +
//						" and ( taFacture IS NOT NULL))  and d.taTiers.codeTiers like :codeTiers";;
////						+" order by d.mtHtCalc DESC";;
//			Query query = entityManager.createQuery(requete);
//			query.setParameter("datedeb", debut);
//			query.setParameter("datefin", fin);
//			query.setParameter("dateref", dateref);
//			query.setParameter("datejour", dateJour);
//			query.setParameter("codeTiers", codeTiers);
//			Long nbAcompteNonTranformeARelancer = (Long)query.getSingleResult();
//			result = nbAcompteNonTranformeARelancer;
//			return result;
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//	}
//
//	/**
//	 * Classe permettant d'obtenir les Prelevement non transform??s
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return La requ??te renvoy??e renvoi le nombre de Prelevement transform??s
//	 */
//	public long countDocumentTransforme(Date debut, Date fin,String codeTiers) {
//		logger.debug("getting nombre Acompte transfos");
//		Long result = (long) 0;
//		
//		if(codeTiers==null)codeTiers="%";
//		try {
//			String requete = "";
//
//			requete = "SELECT "
//				+" count(d)"
//				+" FROM TaAcompte d " 
//				+" where d.dateDocument between :datedeb and :datefin"
//				+" and exists (select r " +
//						"from TaRDocument r " +
//						" where r.taAcompte = d" +
//						" and ( taFacture IS NOT NULL))  and d.taTiers.codeTiers like :codeTiers";
////						+" order by d.mtHtCalc DESC";
//			Query query = entityManager.createQuery(requete);
//			query.setParameter("datedeb", debut);
//			query.setParameter("datefin", fin);
//			query.setParameter("codeTiers", codeTiers);
//			Long nbAcompteNonTranforme = (Long)query.getSingleResult();
//			result = nbAcompteNonTranforme;
//			return result;
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//	}
//	public List<DocumentDTO> findAllDTOPeriode(Date dateDebut, Date dateFin,String codeTiers) {
//		try {
//			if(codeTiers==null)codeTiers="%";
//			Query query = entityManager.createNamedQuery(TaAcompte.QN.FIND_ALL_LIGHT_PERIODE);
//			query.setParameter("dateDebut", dateDebut, TemporalType.DATE);
//			query.setParameter("dateFin", dateFin, TemporalType.DATE);
//			query.setParameter("codeTiers",codeTiers);
//			List<DocumentDTO> l = query.getResultList();
//			logger.debug("get successful");
//			return l;
//
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//	}	
//
	
	
//	/**
//	 * Permet d'obtenir le ca g??n??r?? par les Acomptes non transform??s ?? relancer sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return Retourne les informations de CA Total non transform??s ?? relancer directement dans un objet DocumentChiffreAffaireDTO 
//	 */
//	public DocumentChiffreAffaireDTO chiffreAffaireNonTransformeARelancerTotalDTO(Date dateDebut, Date dateFin, int deltaNbJours,String codeTiers){
//			DocumentChiffreAffaireDTO infosCaTotal = null;
//			infosCaTotal = listeChiffreAffaireNonTransformeARelancerTotalDTO(dateDebut, dateFin, deltaNbJours,codeTiers).get(0);
//			infosCaTotal.setMtHtCalc(infosCaTotal.getMtHtCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTvaCalc(infosCaTotal.getMtTvaCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTtcCalc(infosCaTotal.getMtTtcCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			return infosCaTotal;
//	}
//	
//	/**
//	 * Permet d'obtenir le ca g??n??r?? par les Acomptes non transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return Retourne les informations de CA Total NON Transform?? directement dans un objet DocumentChiffreAffaireDTO 
//	 */
//	public DocumentChiffreAffaireDTO chiffreAffaireNonTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers){
//			DocumentChiffreAffaireDTO infosCaTotal = null;
//			infosCaTotal = listeChiffreAffaireNonTransformeTotalDTO(dateDebut, dateFin,codeTiers).get(0);
//			infosCaTotal.setMtHtCalc(infosCaTotal.getMtHtCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTvaCalc(infosCaTotal.getMtTvaCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTtcCalc(infosCaTotal.getMtTtcCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			return infosCaTotal;
//	}
//	
//	// On r??cup??re les informations de CA HT Total directement dans un objet DocumentChiffreAffaireDTO
//	public DocumentChiffreAffaireDTO chiffreAffaireTotalDTO(Date dateDebut, Date dateFin,String codeTiers){
//			DocumentChiffreAffaireDTO infosCaTotal = null;
//			infosCaTotal = listeChiffreAffaireTotalDTO(dateDebut, dateFin,codeTiers).get(0);
//			infosCaTotal.setMtHtCalc(infosCaTotal.getMtHtCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTvaCalc(infosCaTotal.getMtTvaCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTtcCalc(infosCaTotal.getMtTtcCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			return infosCaTotal;
//	}
//	
//	/**
//	 * Permet d'obtenir le ca g??n??r?? par les Acomptes transform??s sur une p??riode donn??e
//	 * @param debut date de d??but des donn??es
//	 * @param fin date de fin des donn??es
//	 * @return Retourne les informations de CA Total Transform?? directement dans un objet DocumentChiffreAffaireDTO 
//	 */
//	public DocumentChiffreAffaireDTO chiffreAffaireTransformeTotalDTO(Date dateDebut, Date dateFin,String codeTiers){
//			DocumentChiffreAffaireDTO infosCaTotal = null;
//			infosCaTotal = listeChiffreAffaireTransformeTotalDTO(dateDebut, dateFin,codeTiers).get(0);
//			infosCaTotal.setMtHtCalc(infosCaTotal.getMtHtCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTvaCalc(infosCaTotal.getMtTvaCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			infosCaTotal.setMtTtcCalc(infosCaTotal.getMtTtcCalc().setScale(2,BigDecimal.ROUND_HALF_UP));
//			return infosCaTotal;
//	}

	@Override
	public List<TaAcompte> rechercheDocumentVerrouille(Date dateDeb, Date dateFin, String codeTiers,
			Boolean verrouille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaAcompte> rechercheDocumentVerrouille(String codeDeb, String codeFin, String codeTiers,
			Boolean verrouille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Date> findDateExport(Date dateDeb,Date dateFin) {
		try {
			Query query = entityManager.createQuery("select distinct(f.dateExport) from TaAcompte f where f.dateExport is not null and f.dateExport between :dateDeb and :dateFin ");
			query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
			query.setParameter("dateFin", dateFin, TemporalType.DATE);
			List<Date> l = query.getResultList();
			return l;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<TaAcompte> rechercheDocument(Date dateExport,String codeTiers,Date dateDeb, Date dateFin ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequeteDocumentTransforme(String prefixe) {
		// TODO Auto-generated method stub
		return "select r   from TaRDocument r   where r.taAcompte = "+prefixe;
	}

	@Override
	public String getDateAVerifierSiARelancer() {
		// TODO Auto-generated method stub
		return "doc.dateEchDocument";
	}

	@Override
	public String getRequeteARelancer() {
		// TODO Auto-generated method stub
		return " and doc.date_Ech_Document <= :dateRef and doc.date_Ech_Document >= :dateJour ";
	}
	@Override
	public String getRequeteARelancerJPQL() {
		// TODO Auto-generated method stub
		return " and doc.dateEchDocument <= :dateRef and doc.dateEchDocument >= :dateJour ";
	}

	@Override
	public List<TaArticlesParTiersDTO> findArticlesParTiersParMoisParTypeRegroupement(Date dateDebut, Date dateFin,
			String codeTiers, String typeRegroupement, Object valeurRegroupement, boolean regroupee) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TaAcompte findDocByLDoc(ILigneDocumentTiers lDoc) {
		try {
			Query query = entityManager.createQuery("select a from TaAcompte a " +
					"  join  a.lignes l"+
					" where l=:ldoc " +
							" order by l.numLigneLDocument");
			query.setParameter("ldoc", lDoc);
			TaAcompte instance = (TaAcompte)query.getSingleResult();
//			instance.setLegrain(true);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}


	@Override
	public TaAcompte findByIdFetch(int id) {
		logger.debug("getting TaAcompte instance with id: " + id);
		try {
			Query query = entityManager.createQuery("select a from TaAcompte a " +
					" left join fetch  a.lignes l "
//					+" join fetch l.taDocument aa "
//					
//					+" left join fetch a.taREtats re "
//					+" left join fetch a.taHistREtats hre "
//					+" left join fetch a.taRDocuments rd "
//					
//					+" left join fetch l.taREtatLigneDocuments rel "
//					+" left join fetch l.taHistREtatLigneDocuments rhel "
//					+" left join fetch l.taLigneALignes lal "
					+" where a.idDocument="+id+" " +
					" order by l.numLigneLDocument");
			TaAcompte instance = (TaAcompte)query.getSingleResult();			
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}


	@Override
	public TaAcompte findByCodeFetch(String code) {
		logger.debug("getting TaAcompte instance with code: " + code);
		try {
			
			Query query = entityManager.createQuery("select a from TaAcompte a " +
					" left join fetch  a.lignes l "
//					+" join fetch l.taDocument aa "
					
//					+" left join fetch a.taREtats re "
//					+" left join fetch a.taHistREtats hre "
//					+" left join fetch a.taRDocuments rd "
//					
//					+" left join fetch l.taREtatLigneDocuments rel "
//					+" left join fetch l.taHistREtatLigneDocuments rhel "
//					+" left join fetch l.taLigneALignes lal "
					+" where a.codeDocument='"+code+"' " +
					" order by l.numLigneLDocument");
		
			TaAcompte instance = (TaAcompte)query.getSingleResult();
			instance.setLegrain(true);

			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		} catch (Exception e) {
			return null;
		}
	}

}
	

