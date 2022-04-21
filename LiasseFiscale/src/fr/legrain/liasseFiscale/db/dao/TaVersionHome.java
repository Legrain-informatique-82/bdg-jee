package fr.legrain.liasseFiscale.db.dao;

// Generated Dec 7, 2006 10:37:19 AM by Hibernate Tools 3.1.0.beta5

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TaVersion.
 * @see fr.legrain.liasseFiscale.db.dao.TaVersion
 * @author Hibernate Tools
 */
public class TaVersionHome {

	private static final Log log = LogFactory.getLog(TaVersionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TaVersion transientInstance) {
		log.debug("persisting TaVersion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TaVersion instance) {
		log.debug("attaching dirty TaVersion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaVersion instance) {
		log.debug("attaching clean TaVersion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TaVersion persistentInstance) {
		log.debug("deleting TaVersion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaVersion merge(TaVersion detachedInstance) {
		log.debug("merging TaVersion instance");
		try {
			TaVersion result = (TaVersion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TaVersion findById(java.lang.Integer id) {
		log.debug("getting TaVersion instance with id: " + id);
		try {
			TaVersion instance = (TaVersion) sessionFactory.getCurrentSession()
					.get("fr.legrain.liasseFiscale.db.dao.TaVersion", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TaVersion> findByExample(TaVersion instance) {
		log.debug("finding TaVersion instance by example");
		try {
			List<TaVersion> results = (List<TaVersion>) sessionFactory
					.getCurrentSession().createCriteria(
							"fr.legrain.liasseFiscale.db.dao.TaVersion").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
