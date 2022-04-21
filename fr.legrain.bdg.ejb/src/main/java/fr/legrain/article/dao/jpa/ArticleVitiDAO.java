package fr.legrain.article.dao.jpa;

// Generated Aug 11, 2008 4:05:28 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.legrain.article.dao.IArticleVitiDAO;
import fr.legrain.article.model.TaArticleViti;
import fr.legrain.article.model.TaTTva;
import fr.legrain.bdg.general.service.remote.ThrowableExceptionLgr;
import fr.legrain.bdg.lib.osgi.Const;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.LibChaine;
import fr.legrain.validator.BeanValidator;

/**
 * Home object for domain model class TaArticle.
 * @see fr.legrain.articles.dao.TaArticle
 * @author Hibernate Tools
 */
public class ArticleVitiDAO /*extends ArticleDAO*/ implements IArticleVitiDAO{

	//private static final Log logger = LogFactory.getLog(TaArticleDAO.class);
	static Logger logger = Logger.getLogger(ArticleVitiDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	

	private String defaultJPQLQuery = "select a from TaArticleViti a";
	
	public ArticleVitiDAO(){
//		this(null);
	}
	
//	public TaArticleVitiDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
//		initChampId(TaArticleViti.class);
////		champIdTable=ctrlGeneraux.getID_TABLE(TaArticleViti.class.getSimpleName());
//		setJPQLQuery(defaultJPQLQuery);
//		setJPQLQueryInitial(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaArticleViti());
//	}
	

	public void persist(TaArticleViti transientInstance) {
		logger.debug("persisting TaArticleViti instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TaArticleViti persistentInstance) {
		logger.debug("removing TaArticleViti instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaArticleViti merge(TaArticleViti detachedInstance) {
		logger.debug("merging TaArticleViti instance");
		try {
			TaArticleViti result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			RuntimeException re2=ThrowableExceptionLgr.renvoieCauseRuntimeException(re);
			logger.error("merge failed", re);
			throw re2;
		}
	}

	public TaArticleViti findById(int id) {
		logger.debug("getting TaArticleViti instance with id: " + id);
		try {
			TaArticleViti instance = entityManager.find(TaArticleViti.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
//	public TaArticleViti findByIdEM(int id) {
//		logger.debug("getting TaArticleViti instance with id: " + id);
//		try {
//			EntityManager em = new JPABdLgr().entityManager;
//			TaArticleViti instance = em.find(TaArticleViti.class, id);
////			em.close();
//			instance=entityManager.merge(instance);
//			logger.debug("get successful");
//			return instance;
//		} catch (RuntimeException re) {
//			logger.error("get failed", re);
//			throw re;
//		}
//	}
	
	public TaArticleViti findByCode(String code) {
		logger.debug("getting TaArticleViti instance with code: " + code);
		try {
			if(!code.equals("")){
			Query query = entityManager.createQuery("select a from TaArticleViti a where a.codeArticle='"+code+"'");
			TaArticleViti instance = (TaArticleViti)query.getSingleResult();
			logger.debug("get successful");
			return instance;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}	
	
	public List findByActif(boolean actif) {
		logger.debug("getting TaArticleViti instance with actif: " + actif);
		try {
			int param = 0;
			if(actif){
				param = 1;
			}
			Query query = entityManager.createNamedQuery(TaArticleViti.QN.FIND_BY_ACTIF);
			query.setParameter("actif", param);
			List<TaArticleViti> l = query.getResultList();;
			logger.debug("get successful");
			return l;

		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * Liste des articles créé ou modifié par partir de la date passée en paramètre.
	 * @param d
	 * @return
	 */
	public List findByNewOrUpdatedAfter(Date d) {
		logger.debug("getting TaArticleViti instance with quandCreeArticle or quandModifArticle >= " + d);
		try {
			List<TaArticleViti> l = null;
			if(d!=null) {
				Query query = entityManager.createNamedQuery(TaArticleViti.QN.FIND_BY_NEW_OR_UPDATED_AFTER);
				query.setParameter("dateLimite", d);
//				query.setParameter(2, d);
				l = query.getResultList();
				logger.debug("get successful");
			}
			return l;

		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * Recherche les articles qui ont été importés d'un autre programme
	 * @param origineImport
	 * @param idImport - id externe (dans le programme d'origine)
	 * @return
	 */
	public List rechercheParImport(String origineImport, String idImport) {
		Query query = entityManager.createNamedQuery(TaArticleViti.QN.FIND_BY_IMPORT);
		query.setParameter("origineImport", origineImport);
		query.setParameter("idImport", idImport);
		List<TaArticleViti> l = query.getResultList();

		return l;
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List selectAll() {
		logger.debug("selectAll TaArticleViti");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaArticleViti> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	
	public TaArticleViti refresh(TaArticleViti detachedInstance) {
		logger.debug("refresh instance");
		try {			
			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
		    session.evict(detachedInstance);
		    detachedInstance = entityManager.find(TaArticleViti.class, detachedInstance.getIdArticle());
		    return detachedInstance;		    
		} catch (RuntimeException re) {
			logger.error("refresh failed", re);
			throw re;
		}
	}
	

	public void initCodeTTva(TaArticleViti entity){
		if (entity.getTaTva()!=null && !LibChaine.empty(entity.getTaTva().getCodeTva())){
			if( entity.getTaTTva()==null)entity.setTaTTva(new TaTTva());  
				if(LibChaine.empty(entity.getTaTTva().getCodeTTva()))
					entity.getTaTTva().setCodeTTva("D");
		}else
			if(entity.getTaTva()==null || LibChaine.empty(entity.getTaTva().getCodeTva()))
				entity.setTaTTva(null);	
	}
	
	public void initPrixTTC(TaArticleViti entity){
//		boolean res = true;
//		CallableStatement cs;
//		try {
//			cs = cnx.prepareCall("{call " + "RECALCULPRIXTTC" +"(?,?)}");			
//			cs.setInt(1,entity.getIdArticle());
//			cs.setString(2,entity.getTaTva().getCodeTva());
//			cs.execute();
//			cnx.commit();
//		} catch (SQLException e) {
//			logger.error("", e);
//		}
	}
	
	public void ctrlSaisieSpecifique(TaArticleViti entity,String field) throws ExceptLgr {	
		try {
			if(field.equals(
					Const.C_CODE_UNITE)){	
			}
			if(field.equals(Const.C_CODE_TVA )||field.equals(Const.C_CODE_T_TVA )){
				initCodeTTva(entity);
				if(field.equals(Const.C_CODE_TVA ))
					initPrixTTC(entity);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Override
	public List<TaArticleViti> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaArticleViti> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TaArticleViti> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaArticleViti> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaArticleViti value) throws Exception {
		BeanValidator<TaArticleViti> validator = new BeanValidator<TaArticleViti>(TaArticleViti.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaArticleViti value, String propertyName) throws Exception {
		BeanValidator<TaArticleViti> validator = new BeanValidator<TaArticleViti>(TaArticleViti.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaArticleViti transientInstance) {
		entityManager.detach(transientInstance);
	}
}
