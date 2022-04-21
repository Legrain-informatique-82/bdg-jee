package fr.legrain.licenceEpicea.dao;

// Generated Mar 25, 2009 10:06:50 AM by Hibernate Tools 3.2.0.CR1

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import fr.legrain.SupportAbon.dao.TaSupportAbon;
import fr.legrain.abonnement.dao.TaAbonnement;
import fr.legrain.documents.dao.TaLEcheance;
import fr.legrain.documents.dao.TaLEcheanceDAO;
import fr.legrain.gestCom.Appli.Const;
import fr.legrain.pointLgr.dao.TaComptePointDAO;

/**
 * Home object for domain model class TaCPaiement.
 * @see fr.legrain.tiers.dao.test.TaCPaiement
 * @author Hibernate Tools
 */
public   class TaLicenceEpiceaDAO /*extends AbstractApplicationDAO<TaLicenceEpicea>*/{

	static Logger logger = Logger.getLogger(TaLicenceEpiceaDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaHebergement a";
	
	public TaLicenceEpiceaDAO() {
//		this(null);
	}
	
//	public TaLicenceEpiceaDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
//		initChampId(TaLicenceEpicea.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaLicenceEpicea());
//		initNomTableMere(new TaSupportAbon());
//	}
	
	
	public void persist(TaLicenceEpicea transientInstance) {
		logger.debug("persisting TaHebergement instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}
	
//	public TaLicenceEpicea refresh(TaLicenceEpicea detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
//		    session.evict(detachedInstance);
//		    detachedInstance = entityManager.find(TaLicenceEpicea.class, detachedInstance.getIdSupportAbon());
//		    return detachedInstance;
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}

	public void remove(TaLicenceEpicea persistentInstance) {
		logger.debug("removing TaLicenceEpicea instance");
		try {
			TaLEcheanceDAO daoLEcheance = new TaLEcheanceDAO();
			List<TaLEcheance> listeEcheance=null;
			Boolean continuer=true;
			Iterator<TaAbonnement> iterator=persistentInstance.getTaAbonnements().iterator();
			while(continuer && iterator.hasNext()){
				listeEcheance=daoLEcheance.rechercheEcheanceLieAAbonnement(iterator.next()) ;
				continuer=listeEcheance==null || listeEcheance.size()<=0;
			};
			
			if(!continuer){
				MessageDialog.openWarning(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), "Suppression impossible", "Vous ne pouvez pas supprimer ce support car il est lié a des échéances.");
			}else
				entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaLicenceEpicea merge(TaLicenceEpicea detachedInstance) {
		logger.debug("merging TaLicenceEpicea instance");
		try {
			TaLicenceEpicea result = entityManager.merge(detachedInstance);
			TaComptePointDAO daoComptePoint = new TaComptePointDAO();
			daoComptePoint.calculPointTotalAnneeSupport(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaLicenceEpicea findById(int id) {
		logger.debug("getting TaLicenceEpicea instance with id: " + id);
		try {
			TaLicenceEpicea instance = entityManager.find(TaLicenceEpicea.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public TaLicenceEpicea findByCode(String code) {
		logger.debug("getting TaLicenceEpicea instance with code: " + code);
		try {
			if(!code.equals("")){
			Query query = entityManager.createQuery("select f from TaLicenceEpicea f where " +
					"f.codeSupportAbon='"+code+"'");
			TaLicenceEpicea instance = (TaLicenceEpicea)query.getSingleResult();
			logger.debug("get successful");
			return instance;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

//	@Override
	public List<TaLicenceEpicea> selectAll() {
		// TODO Auto-generated method stub
		logger.debug("selectAll TaLicenceEpicea");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaLicenceEpicea> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	

}
