package fr.legrain.documents.dao.jpa;

// Generated Apr 7, 2009 3:27:24 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import fr.legrain.document.dto.IDocumentTiersComplet;
import fr.legrain.document.model.TaFacture;
import fr.legrain.document.model.TaRAvoir;
import fr.legrain.documents.dao.IRAvoirDAO;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.BeanValidator;

/**
 * Home object for domain model class TaRAcompte.
 * @see fr.legrain.documents.dao.TaRAcompte
 * @author Hibernate Tools
 */
public class TaRAvoirDAO /*extends AbstractApplicationDAO<TaRAvoir>*/implements IRAvoirDAO {

//	private static final Log log = LogFactory.getLog(TaRAcompteDAO.class);
	static Logger logger = Logger.getLogger(TaRAvoirDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaRAvoir a";
	
	public TaRAvoirDAO(){
//		this(null);
	}
	
//	public TaRAvoirDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaRAvoir.class.getSimpleName());
//		initChampId(TaRAvoir.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaRAvoir());
//	}
	
//	public TaRAvoir refresh(TaRAvoir detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			entityManager.refresh(detachedInstance);
////			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////		    session.evict(detachedInstance);
////		    detachedInstance = entityManager.find(TaRAvoir.class, detachedInstance.getId());
//		    return detachedInstance;
//
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void persist(TaRAvoir transientInstance) {
		logger.debug("persisting TaRAvoir instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TaRAvoir persistentInstance) {
		logger.debug("removing TaRAvoir instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaRAvoir merge(TaRAvoir detachedInstance) {
		logger.debug("merging TaRAvoir instance");
		try {
			TaRAvoir result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaRAvoir findById(int id) {
		logger.debug("getting TaRAvoir instance with id: " + id);
		try {
			TaRAvoir instance = entityManager.find(TaRAvoir.class, id);
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
	public List<TaRAvoir> selectAll(IDocumentTiersComplet taFacture) {
		logger.debug("selectAll TaRAvoir");
		try {
			if(taFacture!=null){
			Query ejbQuery = entityManager.createQuery("select a from TaRAvoir a " +
					"where a.taFacture.codeDocument=:codeDocument1" +
					" or a.taAvoir.codeDocument=:codeDocument2");
			ejbQuery.setParameter("codeDocument", taFacture.getCodeDocument());
			ejbQuery.setParameter("codeDocument2", taFacture.getCodeDocument());
			List<TaRAvoir> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List<TaRAvoir> selectAll(TaTiers taTiers,Date dateDeb,Date dateFin) {
		logger.debug("selectAll TaRAvoir");
		try {
			if(taTiers!=null){				
				if(dateDeb==null)dateDeb=new Date(0);
				if(dateFin==null)dateFin=new Date("01/01/3000");
				String requete="select a from TaRAvoir a where a.taFacture.taTiers.codeTiers like :codeTiers " +
						"and a.taReglement.dateDocument between :dateDeb and :dateFin";
				Query ejbQuery = entityManager.createQuery(requete);
				ejbQuery.setParameter("codeTiers", taTiers.getCodeTiers());
				ejbQuery.setParameter("dateDeb", dateDeb,TemporalType.DATE);
				ejbQuery.setParameter("dateFin", dateFin,TemporalType.DATE);
			
			List<TaRAvoir> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	
	public List<TaRAvoir> selectSumReglementDocument(TaFacture taFacture) {
		logger.debug("selectAll TaRAcompte");
		try {
			if(taFacture!=null){
			Query ejbQuery = entityManager.createQuery("select a from TaRAvoir a " +
					"where a.taFacture=:taFacture");
			ejbQuery.setParameter("taFacture", taFacture);
			List<TaRAvoir> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
			}
			return null;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}

	public List<TaRAvoir> findByCodeList(String code) {
		logger.debug("getting TaRAvoir instance with code: " + code);
		try {
			Query query = entityManager.createQuery("select a from TaRAvoir a " +
					"where a.taReglement.codeDocument like :codeDocument");
			query.setParameter("codeDocument", code);
			List<TaRAvoir> l = query.getResultList();
			return l;
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			return null;
		}
	}
//	@Override
	public List<TaRAvoir> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaRAvoir> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaRAvoir> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}
	@Override
	public List<TaRAvoir> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaRAvoir> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaRAvoir value) throws Exception {
		BeanValidator<TaRAvoir> validator = new BeanValidator<TaRAvoir>(TaRAvoir.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaRAvoir value, String propertyName)
			throws Exception {
		BeanValidator<TaRAvoir> validator = new BeanValidator<TaRAvoir>(TaRAvoir.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaRAvoir transientInstance) {
		entityManager.detach(transientInstance);
	}

	@Override
	public TaRAvoir findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
