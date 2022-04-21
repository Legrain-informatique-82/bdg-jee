package fr.legrain.supportAbonnement.dao.jpa;

// Generated Mar 25, 2009 10:06:50 AM by Hibernate Tools 3.2.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.supportAbonnement.model.TaOptionAbon;
import fr.legrain.supportAbonnements.dao.IOptionAbonDAO;
import fr.legrain.validator.BeanValidator;

/**
 * Home object for domain model class TaCPaiement.
 * @see fr.legrain.tiers.dao.test.TaCPaiement
 * @author Hibernate Tools
 */
public   class TaOptionAbonDAO implements IOptionAbonDAO{

	static Logger logger = Logger.getLogger(TaOptionAbonDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaOptionAbon a";
	
	public TaOptionAbonDAO() {
//		this(null);
	}
	
//	public TaOptionAbonDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
//		initChampId(TaOptionAbon.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaOptionAbon());
//	}
	
	public void persist(TaOptionAbon transientInstance) {
		logger.debug("persisting TaOptionAbon instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}
	
//	public TaOptionAbon refresh(TaOptionAbon detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
//		    session.evict(detachedInstance);
//		    detachedInstance = entityManager.find(TaOptionAbon.class, detachedInstance.getIdOptionAbon());
//		    return detachedInstance;
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}

	public void remove(TaOptionAbon persistentInstance) {
		logger.debug("removing TaOptionAbon instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaOptionAbon merge(TaOptionAbon detachedInstance) {
		logger.debug("merging TaOptionAbon instance");
		try {
			TaOptionAbon result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaOptionAbon findById(int id) {
		logger.debug("getting TaOptionAbon instance with id: " + id);
		try {
			TaOptionAbon instance = entityManager.find(TaOptionAbon.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public TaOptionAbon findByCode(String code) {
		logger.debug("getting TaOptionAbon instance with code: " + code);
		try {
			if(!code.equals("")){
			Query query = entityManager.createQuery("select f from TaOptionAbon f where" +
					"f.optionAbon='"+code+"'");
			TaOptionAbon instance = (TaOptionAbon)query.getSingleResult();
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
	public List<TaOptionAbon> selectAll() {
		// TODO Auto-generated method stub
		logger.debug("selectAll TaOptionAbon");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaOptionAbon> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}

	@Override
	public List<TaOptionAbon> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaOptionAbon> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}


	@Override
	public List<TaOptionAbon> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaOptionAbon> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaOptionAbon value) throws Exception {
		BeanValidator<TaOptionAbon> validator = new BeanValidator<TaOptionAbon>(TaOptionAbon.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaOptionAbon value, String propertyName)
			throws Exception {
		BeanValidator<TaOptionAbon> validator = new BeanValidator<TaOptionAbon>(TaOptionAbon.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaOptionAbon transientInstance) {
		// TODO Auto-generated method stub
		entityManager.detach(transientInstance);
	}
	

}
