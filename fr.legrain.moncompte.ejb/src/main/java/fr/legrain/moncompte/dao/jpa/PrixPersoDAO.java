package fr.legrain.moncompte.dao.jpa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.legrain.bdg.general.service.remote.ThrowableExceptionLgr;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.moncompte.dao.IDossierDAO;
import fr.legrain.moncompte.dao.IPrixPersoDAO;
import fr.legrain.moncompte.model.TaPrixPerso;
import fr.legrain.validator.BeanValidator;


public class PrixPersoDAO implements IPrixPersoDAO {

	private static final Log logger = LogFactory.getLog(PrixPersoDAO.class);
	
	@PersistenceContext(unitName = "moncompte")
	private EntityManager entityManager;

	private String defaultJPQLQuery = "select p from TaPrixPerso p order by p.code";
	
	public PrixPersoDAO(){
//		this(null);
	}

//	public TaTaPrixPersoDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
//		initChampId(TaPrixPerso.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaPrixPerso());
//	}


	public void persist(TaPrixPerso transientInstance) {
		logger.debug("persisting TaPrixPerso instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("persist failed", re);
			throw re2;
		}
	}

	public void remove(TaPrixPerso persistentInstance) {
		logger.debug("removing TaPrixPerso instance");
		//boolean estRefPrix=false;
		try {
			entityManager.remove(findById(persistentInstance.getId()));

			logger.debug("remove successful");
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("remove failed", re);
			throw re2;
		}
	}

	public TaPrixPerso merge(TaPrixPerso detachedInstance) {
		logger.debug("merging TaPrixPerso instance");
		try {
			TaPrixPerso result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("merge failed", re);
			throw re2;
		}
	}

	public TaPrixPerso findById(int id) {
		logger.debug("getting TaPrixPerso instance with id: " + id);
		try {
			TaPrixPerso instance = entityManager.find(TaPrixPerso.class, id);
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
	public List<TaPrixPerso> selectAll() {
		logger.debug("selectAll TaArticle");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaPrixPerso> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}

	public void ctrlSaisieSpecifique(TaPrixPerso entity,String field) throws ExceptLgr {	
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public List<TaPrixPerso> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaPrixPerso> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TaPrixPerso> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaPrixPerso> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaPrixPerso value) throws Exception {
		BeanValidator<TaPrixPerso> validator = new BeanValidator<TaPrixPerso>(TaPrixPerso.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaPrixPerso value, String propertyName) throws Exception {
		BeanValidator<TaPrixPerso> validator = new BeanValidator<TaPrixPerso>(TaPrixPerso.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaPrixPerso transientInstance) {
		entityManager.detach(transientInstance);
	}
	
	public TaPrixPerso findByCode(String code) {
		logger.debug("getting TaPrixPerso instance with code: " + code);
		try {
			if(!code.equals("")){
			Query query = entityManager.createQuery("select f from TaPrixPerso f where f.code='"+code+"'");
			TaPrixPerso instance = (TaPrixPerso)query.getSingleResult();
			logger.debug("get successful");
			return instance;
			}
			return null;
		} catch (RuntimeException re) {
			//logger.error("get failed", re);
			return null;
		}
	}
	
}

