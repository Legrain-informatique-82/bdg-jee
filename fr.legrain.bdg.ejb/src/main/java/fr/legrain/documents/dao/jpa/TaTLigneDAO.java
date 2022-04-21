package fr.legrain.documents.dao.jpa;

// Generated Apr 7, 2009 3:27:24 PM by Hibernate Tools 3.2.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.document.model.TaTLigne;
import fr.legrain.documents.dao.ITLigneDAO;
import fr.legrain.validator.BeanValidator;

/**
 * Home object for domain model class TaTLigne.
 * @see fr.legrain.documents.dao.TaTLigne
 * @author Hibernate Tools
 */
public class TaTLigneDAO /*extends AbstractApplicationDAO<TaTLigne>*/ implements ITLigneDAO {

//	private static final Log log = LogFactory.getLog(TaTLigneDAO.class);
	static Logger logger = Logger.getLogger(TaTLigneDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaTLigne a";
	
	public TaTLigneDAO(){
//		this(null);
	}
	
//	public TaTLigneDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaTLigne.class.getSimpleName());
//		initChampId(TaTLigne.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaTLigne());
//	}
	
//	public TaTLigne refresh(TaTLigne detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			entityManager.refresh(detachedInstance);
////			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////		    session.evict(detachedInstance);
////		    detachedInstance = entityManager.find(TaTLigne.class, detachedInstance.getIdTLigne());
//		    return detachedInstance;
//
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}
	
	public void persist(TaTLigne transientInstance) {
		logger.debug("persisting TaTLigne instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TaTLigne persistentInstance) {
		logger.debug("removing TaTLigne instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaTLigne merge(TaTLigne detachedInstance) {
		logger.debug("merging TaTLigne instance");
		try {
			TaTLigne result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaTLigne findById(int id) {
		logger.debug("getting TaTLigne instance with id: " + id);
		try {
			TaTLigne instance = entityManager.find(TaTLigne.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	public TaTLigne findByCode(String code) {
		logger.debug("getting TaTLigne instance with code: " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select f from TaTLigne f where f.codeTLigne='"+code+"'");
				TaTLigne instance = (TaTLigne)query.getSingleResult();
				logger.debug("get successful");
				return instance;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List<TaTLigne> selectAll() {
		logger.debug("selectAll TaTLigne");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaTLigne> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	
	@Override
	public List<TaTLigne> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaTLigne> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TaTLigne> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaTLigne> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaTLigne value) throws Exception {
		BeanValidator<TaTLigne> validator = new BeanValidator<TaTLigne>(TaTLigne.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaTLigne value, String propertyName) throws Exception {
		BeanValidator<TaTLigne> validator = new BeanValidator<TaTLigne>(TaTLigne.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaTLigne transientInstance) {
		entityManager.detach(transientInstance);
	}
}
