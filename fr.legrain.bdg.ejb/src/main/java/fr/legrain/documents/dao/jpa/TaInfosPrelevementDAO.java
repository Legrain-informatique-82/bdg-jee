package fr.legrain.documents.dao.jpa;

// Generated Apr 7, 2009 3:27:24 PM by Hibernate Tools 3.2.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.document.model.TaInfosPrelevement;
import fr.legrain.document.model.TaInfosProforma;
import fr.legrain.documents.dao.IInfosPrelevementDAO;
import fr.legrain.documents.dao.IInfosProformaDAO;
import fr.legrain.validator.BeanValidator;


/**
 * Home object for domain model class TaInfosDevis.
 * @see fr.legrain.documents.dao.TaInfosDevis
 * @author Hibernate Tools
 */
public class TaInfosPrelevementDAO implements IInfosPrelevementDAO {

//	private static final Log log = LogFactory.getLog(TaInfosDevisDAO.class);
	static Logger logger = Logger.getLogger(TaInfosPrelevementDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaInfosPrelevement a";
	
	public TaInfosPrelevementDAO(){
//		this(null);
	}
	
//	public TaInfosPrelevementDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaInfosPrelevement.class.getSimpleName());
//		initChampId(TaInfosPrelevement.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaInfosPrelevement());
//	}
	
//	public TaInfosPrelevement refresh(TaInfosPrelevement detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			entityManager.refresh(detachedInstance);
////			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////		    session.evict(detachedInstance);
////		    detachedInstance = entityManager.find(TaInfosPrelevement.class, detachedInstance.getIdInfosDocument());
//		    return detachedInstance;
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}
	
	public void persist(TaInfosPrelevement transientInstance) {
		logger.debug("persisting TaInfosPrelevement instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TaInfosPrelevement persistentInstance) {
		logger.debug("removing TaInfosPrelevement instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaInfosPrelevement merge(TaInfosPrelevement detachedInstance) {
		logger.debug("merging TaInfosPrelevement instance");
		try {
			TaInfosPrelevement result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaInfosPrelevement findById(int id) {
		logger.debug("getting TaInfosPrelevement instance with id: " + id);
		try {
			TaInfosPrelevement instance = entityManager.find(TaInfosPrelevement.class, id);
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
	public List<TaInfosPrelevement> selectAll() {
		logger.debug("selectAll TaInfosPrelevement");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaInfosPrelevement> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}

	@Override
	public TaInfosPrelevement findByCode(String code) {
		// TODO Auto-generated method stub
		return findByCodePrelevement(code);
	}

	@Override
	public List<TaInfosPrelevement> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaInfosPrelevement> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TaInfosPrelevement> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaInfosPrelevement> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaInfosPrelevement value) throws Exception {
		BeanValidator<TaInfosPrelevement> validator = new BeanValidator<TaInfosPrelevement>(TaInfosPrelevement.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaInfosPrelevement value, String propertyName) throws Exception {
		BeanValidator<TaInfosPrelevement> validator = new BeanValidator<TaInfosPrelevement>(TaInfosPrelevement.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaInfosPrelevement transientInstance) {
		entityManager.detach(transientInstance);
		
	}
	public TaInfosPrelevement findByCodePrelevement(String code) {
		logger.debug("getting TaInfosPrelevement instance with code InfosProforma : " + code);
		try {
			if(!code.equals("")){
				Query query = entityManager.createQuery("select a from TaInfosPrelevement a where a.taDocument.codeDocument='"+code+"'");
				TaInfosPrelevement instance = (TaInfosPrelevement)query.getSingleResult();
				logger.debug("get successful");
				return instance;
			}
			return null;
		} catch (NoResultException re) {
			return null;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
}
