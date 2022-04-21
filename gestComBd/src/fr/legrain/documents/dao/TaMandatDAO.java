package fr.legrain.documents.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * Home object for domain model class TaMandat.
 * @see fr.legrain.documents.dao.TaMandat
 * @author Hibernate Tools
 */
public class TaMandatDAO /*extends AbstractApplicationDAO<TaMandat>*/ {

	static Logger logger = Logger.getLogger(TaMandatDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaMandat a";
	
	public TaMandatDAO(){
//		this(null);
	}
	
//	public TaMandatDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaMandat.class.getSimpleName());
//		initChampId(TaMandat.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaMandat());
//	}
//	public TaMandat refresh(TaMandat detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			entityManager.refresh(detachedInstance);
////			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////		    session.evict(detachedInstance);
////		    detachedInstance = entityManager.find(TaMandat.class, detachedInstance.getIdTDoc());
//		    return detachedInstance;
//			
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}
	
	public void persist(TaMandat transientInstance) {
		logger.debug("persisting TaMandat instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TaMandat persistentInstance) {
		logger.debug("removing TaMandat instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaMandat merge(TaMandat detachedInstance) {
		logger.debug("merging TaMandat instance");
		try {
			TaMandat result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaMandat findById(int id) {
		logger.debug("getting TaMandat instance with id: " + id);
		try {
			TaMandat instance = entityManager.find(TaMandat.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public TaMandat findByCode(String code) {
		logger.debug("getting TaMandat instance with code: " + code);
		try {
			Query query = entityManager.createQuery("select a from TaMandat a where a.codeMandat='"+code+"'");
			TaMandat instance = (TaMandat)query.getSingleResult();
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
	public List<TaMandat> selectAll() {
		logger.debug("selectAll TaMandat");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaMandat> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
}
