package fr.legrain.supportImmosManager.dao;

// Generated Mar 25, 2009 10:06:50 AM by Hibernate Tools 3.2.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.SupportAbon.dao.TaSupportAbon;
import fr.legrain.gestCom.Appli.Const;

/**
 * Home object for domain model class TaCPaiement.
 * @see fr.legrain.tiers.dao.test.TaCPaiement
 * @author Hibernate Tools
 */
public   class TaImmosManagerDAO /*extends AbstractApplicationDAO<TaImmosManager>*/{

	static Logger logger = Logger.getLogger(TaImmosManagerDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaHebergement a";
	
	public TaImmosManagerDAO() {
//		this(null);
	}
	
//	public TaImmosManagerDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
//		initChampId(TaImmosManager.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaImmosManager());
//		initNomTableMere(new TaSupportAbon());
//	}
	
	
	public void persist(TaImmosManager transientInstance) {
		logger.debug("persisting TaHebergement instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}
	
//	public TaImmosManager refresh(TaImmosManager detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
//		    session.evict(detachedInstance);
//		    detachedInstance = entityManager.find(TaImmosManager.class, detachedInstance.getIdSupportAbon());
//		    return detachedInstance;
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}

	public void remove(TaImmosManager persistentInstance) {
		logger.debug("removing TaHebergement instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaImmosManager merge(TaImmosManager detachedInstance) {
		logger.debug("merging TaHebergement instance");
		try {
			TaImmosManager result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaImmosManager findById(int id) {
		logger.debug("getting TaHebergement instance with id: " + id);
		try {
			TaImmosManager instance = entityManager.find(TaImmosManager.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public TaImmosManager findByCode(String code) {
		logger.debug("getting TaHebergement instance with code: " + code);
		try {
			if(!code.equals("")){
			Query query = entityManager.createQuery("select f from TaHebergement f where " +
					"f.codeSupportAbon='"+code+"'");
			TaImmosManager instance = (TaImmosManager)query.getSingleResult();
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
	public List<TaImmosManager> selectAll() {
		// TODO Auto-generated method stub
		logger.debug("selectAll TaHebergement");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaImmosManager> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	

}
