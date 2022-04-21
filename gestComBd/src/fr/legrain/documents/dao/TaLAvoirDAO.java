package fr.legrain.documents.dao;

// Generated Apr 7, 2009 3:27:24 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import fr.legrain.dms.dao.TaEtatMouvementDms;
import fr.legrain.gestCom.Appli.Const;
import fr.legrain.gestioncapsules.dao.TaEtatStockCapsules;
import fr.legrain.lib.data.LibDate;
import fr.legrain.stocks.dao.TaEtatStock;

/**
 * Home object for domain model class TaLAvoir.
 * @see fr.legrain.documents.dao.TaLAvoir
 * @author Hibernate Tools
 */
public class TaLAvoirDAO /*extends AbstractApplicationDAO<TaLAvoir>*/ {

//	private static final Log log = LogFactory.getLog(TaLAvoirDAO.class);
	static Logger logger = Logger.getLogger(TaLAvoirDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	private String defaultJPQLQuery = "select a from TaLAvoir a";
//	private String JPQLQueryMouvDocumentEntrees=
//	 "select L.taDocument.dateDocument,L.taArticle.codeArticle,sum(L.qteLDocument),"+
//	 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  "+
//	 "from TaLAvoir L "+
//	 " where L.taDocument.idDocument not in (select rdoc.taAvoir.idDocument from TaRDocument rdoc) "+
//	 " and (L.u1LDocument='' "+
//	 " or L.u1LDocument in (select u.codeUnite from TaUnite u)) "+
//	 " and L.taArticle.codeArticle is not null  "+ 
//	 " group by  L.taDocument.dateDocument,L.taArticle.codeArticle, L.u1LDocument, L.u2LDocument";
	
	public TaLAvoirDAO(){
//		this(null);
	}
	
//	public TaLAvoirDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaLAvoir.class.getSimpleName());
//		initChampId(TaLAvoir.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaLAvoir());
//	}
	
//	public TaLAvoir refresh(TaLAvoir detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			detachedInstance.setLegrain(false);
//        	entityManager.refresh(detachedInstance);
////			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////		    session.evict(detachedInstance);
////		    detachedInstance = entityManager.find(TaLAvoir.class, detachedInstance.getIdLDocument());
//			detachedInstance.setLegrain(true);
//		    return detachedInstance;
//			
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}
	
	public void persist(TaLAvoir transientInstance) {
		logger.debug("persisting TaLAvoir instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void remove(TaLAvoir persistentInstance) {
		logger.debug("removing TaLAvoir instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaLAvoir merge(TaLAvoir detachedInstance) {
		logger.debug("merging TaLAvoir instance");
		try {
			TaLAvoir result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaLAvoir findById(int id) {
		logger.debug("getting TaLAvoir instance with id: " + id);
		try {
			TaLAvoir instance = entityManager.find(TaLAvoir.class, id);
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
	public List<TaLAvoir> selectAll() {
		logger.debug("selectAll TaLAvoir");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaLAvoir> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	
	public List<TaEtatStock> selectSumMouvDocumentStocksEntrees(TaEtatStock criteres,Date dateDeb) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.stocks.dao.TaEtatStock(a.codeArticle,'E',f.dateDocument,sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,f.codeDocument,L.libLDocument,'Avoir','6' ) "+
				 " from TaLAvoir L join L.taDocument f join L.taArticle a "+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
			 if(criteres.getExclusionQteSsUnite())
				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
			 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			if(dateDeb!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument > '"+LibDate.dateToStringSql(dateDeb)+"'";
			
			 JPQLQueryMouvDocument+=" and  ((L.qteLDocument is not null and L.qteLDocument >0)" +
		 		"or(L.qte2LDocument is not null and L.qte2LDocument >0)) "  ;
			 
			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getDateStock()!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument <= '"+LibDate.dateToStringSql(criteres.getDateStock())+"'";
			if(criteres.getUn1Stock()!=null && !criteres.getUn1Stock().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1Stock()+"'";
			 if(criteres.getUn2Stock()!=null && !criteres.getUn2Stock().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2Stock()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  f.dateDocument,a.codeArticle, L.u1LDocument, L.u2LDocument,f.codeDocument,L.libLDocument";			
			
		    //clause having
		    if(criteres.getQte1Stock()!=null && !criteres.getQte1Stock().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1Stock();
		    }
		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStock> l = ejbQuery.getResultList();
		    for (TaEtatStock taEtatStock : l) {
				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentEntrees failed", re);
			throw re;
		}
	}
	public List<TaEtatStock> selectSumMouvDocumentStocksSorties(TaEtatStock criteres,Date dateDeb) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.stocks.dao.TaEtatStock(a.codeArticle,'S',f.dateDocument,sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,f.codeDocument,L.libLDocument,'Avoir' ,'9') "+
				 " from TaLAvoir L join L.taDocument f join L.taArticle a "+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
			 if(criteres.getExclusionQteSsUnite())
				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
			 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			if(dateDeb!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument > '"+LibDate.dateToStringSql(dateDeb)+"'";
			
			 JPQLQueryMouvDocument+=" and  ((L.qteLDocument is not null and L.qteLDocument <0)" +
		 		"or(L.qte2LDocument is not null and L.qte2LDocument <0)) "  ;
			 
			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getDateStock()!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument <= '"+LibDate.dateToStringSql(criteres.getDateStock())+"'";
			if(criteres.getUn1Stock()!=null && !criteres.getUn1Stock().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1Stock()+"'";
			 if(criteres.getUn2Stock()!=null && !criteres.getUn2Stock().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2Stock()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  f.dateDocument,a.codeArticle, L.u1LDocument, L.u2LDocument,f.codeDocument,L.libLDocument";			
			
		    //clause having
		    if(criteres.getQte1Stock()!=null && !criteres.getQte1Stock().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1Stock();
		    }
		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStock> l = ejbQuery.getResultList();
		    for (TaEtatStock taEtatStock : l) {
				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentEntrees failed", re);
			throw re;
		}
	}
	
	public List<TaEtatStockCapsules> selectSumMouvDocumentStocksCapsulesEntrees(TaEtatStockCapsules criteres,Date dateDeb,boolean strict) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.gestioncapsules.dao.TaEtatStockCapsules(L.codeTitreTransport,t.libelleTitreTransport,'E',f.dateDocument,sum(L.qteTitreTransport)," +
				 "f.codeDocument,L.libLDocument,'Avoir','6' ) "+
				 " from TaLAvoir L join L.taDocument f , TaTitreTransport t"+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
//			 if(criteres.getExclusionQteSsUnite())
//				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u ))";// +
//				 		"or" +
//			 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			if(dateDeb!=null) {
				String signe = ">";
				if(!strict) signe=">=";
				JPQLQueryMouvDocument+=" and f.dateDocument "+signe+" '"+LibDate.dateToStringSql(dateDeb)+"'";
			}
			
			 JPQLQueryMouvDocument+=" and  ((L.qteTitreTransport is not null and L.qteTitreTransport >0)) ";
		 		//+"or(L.qte2LDocument is not null and L.qte2LDocument >0)) "  ;
			 
			//Clause where
			if(criteres.getCodeTitreTransport()!=null && !criteres.getCodeTitreTransport().equals(""))
				JPQLQueryMouvDocument+=" and L.codeTitreTransport like '"+criteres.getCodeTitreTransport()+"'";
			if(criteres.getDateStock()!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument <= '"+LibDate.dateToStringSql(criteres.getDateStock())+"'";
			
			JPQLQueryMouvDocument+=" and L.codeTitreTransport = t.codeTitreTransport ";
			
//			if(criteres.getUn1Stock()!=null && !criteres.getUn1Stock().equals(""))
//				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1Stock()+"'";
//			 if(criteres.getUn2Stock()!=null && !criteres.getUn2Stock().equals(""))
//				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2Stock()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  f.dateDocument,L.codeTitreTransport,t.libelleTitreTransport, f.codeDocument,L.libLDocument";			
			
		    //clause having
//		    if(criteres.getQteCapsuleStock()!=null && !criteres.getQteCapsuleStock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    }
//		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2Stock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
//		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStockCapsules> l = ejbQuery.getResultList();
//		    for (TaEtatStockCapsules taEtatStock : l) {
//				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
////				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
//			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentEntrees failed", re);
			throw re;
		}
	}
	public List<TaEtatStockCapsules> selectSumMouvDocumentStocksCapsulesSorties(TaEtatStockCapsules criteres,Date dateDeb, boolean strict) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.gestioncapsules.dao.TaEtatStockCapsules(L.codeTitreTransport,t.libelleTitreTransport,'S'" +
				 ",f.dateDocument,sum(L.qteTitreTransport)," +
				 "f.codeDocument,L.libLDocument,'Avoir' ,'9') "+
				 " from TaLAvoir L join L.taDocument f , TaTitreTransport t"+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
//			 if(criteres.getExclusionQteSsUnite())
//				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u ))";// +
//				 		"or" +
//			 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			if(dateDeb!=null) {
				String signe = ">";
				if(!strict) signe=">=";
				JPQLQueryMouvDocument+=" and f.dateDocument "+signe+" '"+LibDate.dateToStringSql(dateDeb)+"'";
			}
			
			 JPQLQueryMouvDocument+=" and  ((L.qteTitreTransport is not null and L.qteTitreTransport <0)) ";// +
		 		//"or(L.qte2LDocument is not null and L.qte2LDocument <0)) "  ;
			 
			//Clause where
			if(criteres.getCodeTitreTransport()!=null && !criteres.getCodeTitreTransport().equals(""))
				JPQLQueryMouvDocument+=" and L.codeTitreTransport like '"+criteres.getCodeTitreTransport()+"'";
			if(criteres.getDateStock()!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument <= '"+LibDate.dateToStringSql(criteres.getDateStock())+"'";
			
			JPQLQueryMouvDocument+=" and L.codeTitreTransport = t.codeTitreTransport ";
			
//			if(criteres.getUn1Stock()!=null && !criteres.getUn1Stock().equals(""))
//				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1Stock()+"'";
//			 if(criteres.getUn2Stock()!=null && !criteres.getUn2Stock().equals(""))
//				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2Stock()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  f.dateDocument,L.codeTitreTransport,t.libelleTitreTransport,f.codeDocument,L.libLDocument";			
			
		    //clause having
//		    if(criteres.getQteCapsuleStock()!=null && !criteres.getQteCapsuleStock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    }
//		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2Stock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
//		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStockCapsules> l = ejbQuery.getResultList();
//		    for (TaEtatStockCapsules taEtatStock : l) {
//				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
////				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
//			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentEntrees failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumReportDms(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.dao.TaEtatMouvementDms(fa.codeFamille,a.codeArticle,'E' ,sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ) "+
				 " from TaLAvoir L  join L.taArticle a join a.taFamille fa "+   //left 
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr   
				 if(criteres.getExclusionQteSsUnite())
					 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
				 
			Date dateDeb=LibDate.stringToDateNew("01/"+criteres.getMois()+"/"+criteres.getAnnee());
			//dateDeb.setTime(dateDeb.getTime()-1);
			
			JPQLQueryMouvDocument+=" and exists(select f.idDocument from TaFacture f where f.idDocument=L.taDocument.idDocument and f.dateDocument < ? )";

//			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  fa.codeFamille ,a.codeArticle, L.u1LDocument, L.u2LDocument";			
																	
		    //clause having
		    if(criteres.getQte1()!=null && !criteres.getQte1().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
		    }
		    if(criteres.getQte2()!=null && !criteres.getQte2().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
			ejbQuery.setParameter(1, dateDeb,TemporalType.DATE);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvDms(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.dao.TaEtatMouvementDms(fa.codeFamille,a.codeArticle,'E', extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument) ,sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				 " from TaLAvoir L  join L.taDocument F  join L.taArticle a join a.taFamille fa "+   //left 
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr    
				 if(criteres.getExclusionQteSsUnite())
					 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
				 
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
//			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  fa.codeFamille ,a.codeArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";			
																	
		    //clause having
			 if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
		    }
			 if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatFamille(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument="";  
			if(criteres.getTravailSurDateLivraison()){
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'E', extract(month from F.dateLivDocument) ," +
						 " extract(year from F.dateLivDocument) ,sum(-1*L.qteLDocument),"+
						 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						 " from TaLAvoir L  join L.taDocument F  join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO "+   //left 
						 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr  	
			}else{
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'E', extract(month from F.dateDocument) ," +
						 " extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
						 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						 " from TaLAvoir L  join L.taDocument F  join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO "+   //left 
						 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr  
			}
  
			if(criteres.getExclusionQteSsUnite())
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			
			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and INFO.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and INFO.codeTTvaDoc is null ";
			}

			if(criteres.getTravailSurDateLivraison()){
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateLivDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateLivDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			}else{
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			}

//			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  a.codeArticle between '"+criteres.getCodeArticle()+"' and '"+criteres.getCodeArticleFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
				}
			}
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			if(criteres.getTravailSurDateLivraison())//Clause group by
			 JPQLQueryMouvDocument+=" group by  fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle, L.u2LDocument, L.u1LDocument,F.dateLivDocument";
			else JPQLQueryMouvDocument+=" group by  fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle, L.u2LDocument, L.u1LDocument,F.dateDocument";
		    //clause having
			 if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
		    }
			 if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatArticle(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=""  ;
			if(criteres.getTravailSurDateLivraison()){
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'E', extract(month from F.dateLivDocument) ," +
						 " extract(year from F.dateLivDocument) ,sum(-1*L.qteLDocument),"+
						 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						 " from TaLAvoir L  join L.taDocument F  join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO "+   //left 
						 " where L.taArticle is not null" ;//cette ligne est inutile du point de vue sql, mais elle permet de commencer la condition de façon sûr
			}else{
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'E', extract(month from F.dateDocument) ," +
						 " extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
						 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						 " from TaLAvoir L  join L.taDocument F  join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO "+   //left 
						 " where L.taArticle is not null" ;//cette ligne est inutile du point de vue sql, mais elle permet de commencer la condition de façon sûr	
			}
    
			if(criteres.getExclusionQteSsUnite())
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			
			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and INFO.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and INFO.codeTTvaDoc is null ";
			}
			if(criteres.getTravailSurDateLivraison()){
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateLivDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateLivDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";	
			}else{
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";		
			}

			
			if(criteres.getCodeTauxTVA()!=null && !criteres.getCodeTauxTVA().equals(""))
				JPQLQueryMouvDocument+=" and L.codeTvaLDocument =  '"+criteres.getCodeTauxTVA()+"'";
			
//			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  a.codeArticle between '"+criteres.getCodeArticle()+"' and '"+criteres.getCodeArticleFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
				}
			}
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  fa.codeFamille between '"+criteres.getCodeFamille()+"' and '"+criteres.getCodeFamilleFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  fa.codeFamille like '"+criteres.getCodeFamille()+"'";
				}
			}
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			
			if(criteres.getTravailSurDateLivraison()) //Clause group by
			 JPQLQueryMouvDocument+=" group by  a.codeArticle,a.libellecArticle, fa.codeFamille,fa.libcFamille,L.u1LDocument, L.u2LDocument,F.dateLivDocument";
			else JPQLQueryMouvDocument+=" group by  a.codeArticle,a.libellecArticle, fa.codeFamille,fa.libcFamille,L.u1LDocument, L.u2LDocument,F.dateDocument";
																	
		    //clause having
			 if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
		    }
			 if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatTiers(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.dao.TaEtatMouvementDms('','',a.codeArticle,a.libellecArticle," +
				 //"ft.codeFamille,ft.libcFamille,t.codeTiers,i.nomTiers," 
				 "'','',t.codeTiers,i.nomTiers,"
				 +"'E', extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				 //" from TaLAvoir L  join L.taDocument F  join L.taArticle a join F.taInfosDocument i join F.taTiers t left join t.taFamilleTierses ft" +
				 " from TaLAvoir L  join L.taDocument F  join L.taArticle a join F.taInfosDocument i join F.taTiers t" +
				 " where L.taArticle is not null" ;//cette ligne est inutile du point de vue sql, mais elle permet de commencer la condition de façon sûr    
			if(criteres.getExclusionQteSsUnite())
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			
			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc is null ";
			}
			
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
//			//Clause where
			if(criteres.getCodeTiers()!=null && !criteres.getCodeTiers().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  t.codeTiers between '"+criteres.getCodeTiers()+"' and '"+criteres.getCodeTiersFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  t.codeTiers like '"+criteres.getCodeTiers()+"'";
				}
			}
			if(criteres.getCodeFamilleTiers()!=null && !criteres.getCodeFamilleTiers().equals("")) {
				if(criteres.getCodeFamilleTiersFin()!=null && !criteres.getCodeFamilleTiersFin().equals("")) {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like between '"+criteres.getCodeFamilleTiers()+"' and '"+criteres.getCodeFamilleTiersFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like like '"+criteres.getCodeFamilleTiers()+"'";
				}
			}
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			 //JPQLQueryMouvDocument+=" group by  t.codeTiers,i.nomTiers,ft.codeFamille,ft.libcFamille,a.codeArticle,a.libellecArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";			
			 JPQLQueryMouvDocument+=" group by  t.codeTiers,i.nomTiers,a.codeArticle,a.libellecArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";
																	
		    //clause having
			 if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
		    }
			 if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatFamilleTiers(TaEtatMouvementDms criteres) {
		try {

			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.dao.TaEtatMouvementDms(" +
				 "fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle," +
				 "ft.codeFamille,ft.libcFamille,t.codeTiers,INFO.nomTiers," +
				 "'S' , extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument),sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
				 " from TaLAvoir L  join L.taDocument F join L.taArticle a left join a.taFamille fa " +
				 " join F.taInfosDocument INFO join F.taTiers t left join t.taFamilleTierses ft" +
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr   
			if(criteres.getExclusionQteSsUnite()){
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			}
			
			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and INFO.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and INFO.codeTTvaDoc is null ";
			}
				 
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
//			//Clause where
			if(criteres.getCodeFamilleTiers()!=null && !criteres.getCodeFamilleTiers().equals("")) {
				if(criteres.getCodeFamilleTiersFin()!=null && !criteres.getCodeFamilleTiersFin().equals("")) {
					JPQLQueryMouvDocument+=" and  ft.codeFamille between '"+criteres.getCodeFamilleTiers()+"' and '"+criteres.getCodeFamilleTiersFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like '"+criteres.getCodeFamilleTiers()+"'";
				}
			}
//			if(criteres.getCodeFamilleTiers()!=null && !criteres.getCodeFamilleTiers().equals(""))
//				JPQLQueryMouvDocument+=" and ft.codeFamille like '"+criteres.getCodeFamilleTiers()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by ft.codeFamille,ft.libcFamille,t.codeTiers,INFO.nomTiers,fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle, L.u2LDocument, L.u1LDocument,F.dateDocument";	
//			 JPQLQueryMouvDocument+=" group by fa.codeFamille,a.codeArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";	
																	
		    //clause having
		    if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
		    }
		    if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}

	public List<TaEtatMouvementDms> selectSumMouvementDms(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				"select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,fa.codeFamille,'E', extract(month from F.dateDocument) ," +
				" extract(year from F.dateDocument) ,sum(L.qteLDocument),"+
				" L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				"sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a join a.taFamille fa "+   //left 
				" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr    
				 if(criteres.getExclusionQteSsUnite())
					 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			JPQLQueryMouvDocument+=" group by  F.dateDocument,fa.codeFamille,a.codeArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			

			if(!criteres.getExclusionQteSsUnite()){
				JPQLQueryMouvDocument+= " union " +
				" select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
				" fa.codeFamille,'S', extract(month from F.dateDocument) ," +
				" extract(year from F.dateDocument) ,0,"+
				" L.u1LDocument,0,L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				" sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  join a.taFamille fa "+   //left 
				" where  a.codeArticle is not null and (L.u1LDocument='' )";
			
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			JPQLQueryMouvDocument+=" group by  F.dateDocument,fa.codeFamille,a.codeArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			
			}
			//clause having
			if(criteres.getQte1()!=null && !criteres.getQte1().equals("")){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
				else
					JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
			}
			if(criteres.getQte2()!=null && !criteres.getQte2().equals("")){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
				else
					JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
			} 
			
			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
			List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}

	public List<TaEtatMouvementDms> selectSumMouvementEtatFamille(TaEtatMouvementDms criteres) {
		try {
			
			String JPQLQueryMouvDocument="";  
			if(criteres.getTravailSurDateLivraison()){
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateLivDocument,a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille,'E', extract(month from F.dateLivDocument) ," +
						" extract(year from F.dateLivDocument) ,(-1*L.qteLDocument),"+
						" L.u1LDocument,(-1*L.qte2LDocument),L.u2LDocument ,(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						"(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa "+   //left 
						" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr   
			}else{
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille,'E', extract(month from F.dateDocument) ," +
						" extract(year from F.dateDocument) ,(-1*L.qteLDocument),"+
						" L.u1LDocument,(-1*L.qte2LDocument),L.u2LDocument ,(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						"(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa "+   //left 
						" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr   
			}
//			JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille,'E', extract(month from F.dateDocument) ," +
//				" extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
//				" L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
//				"sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa "+   //left 
//				" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr    
			if(criteres.getExclusionQteSsUnite())
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
			
			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc is null ";
			}

			if(criteres.getTravailSurDateLivraison()){
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateLivDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateLivDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			}else{
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			}

			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  a.codeArticle between '"+criteres.getCodeArticle()+"' and '"+criteres.getCodeArticleFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
				}
			}
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
//			//Clause group by
//			 JPQLQueryMouvDocument+=" group by  F.dateDocument,fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";

//			if(!criteres.getExclusionQteSsUnite()){
//				JPQLQueryMouvDocument+= " union " +
//				" select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
//				" fa.codeFamille,'S', extract(month from F.dateDocument) ," +
//				" extract(year from F.dateDocument) ,0,"+
//				" L.u1LDocument,0,L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
//				" sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  join a.taFamille fa "+   //left 
//				" where  a.codeArticle is not null and (L.u1LDocument='' )";
//			
//			if(criteres.getMois()!=null)
//				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
//			if(criteres.getAnnee()!=null)
//				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
//			if(criteres.getDateDeb()!=null)
//				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
//			if(criteres.getDateFin()!=null)
//				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
//			//Clause where
//			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
//				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
//			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
//				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
//			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
//				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
//			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
//				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
//			//Clause group by
//			JPQLQueryMouvDocument+=" group by  F.dateDocument,fa.codeFamille,a.codeArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			
//			}
			//clause having
			if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
			}
			if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
			} 
			
			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
			List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumMouvementEtatArticle(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument="";
			if(criteres.getTravailSurDateLivraison()){
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateLivDocument,a.codeArticle,a.libellecArticle," +
						"fa.codeFamille,fa.libcFamille,'E', extract(month from F.dateLivDocument) ," +
						" extract(year from F.dateLivDocument) ,sum(-1*L.qteLDocument),"+
						" L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						"sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa"+   //left 
						" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
			}else{
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
						"fa.codeFamille,fa.libcFamille,'E', extract(month from F.dateDocument) ," +
						" extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
						" L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
						"sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
						" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa"+   //left 
						" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 	
			}
   
//			if(criteres.getExclusionQteSsUnite())
//				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
//						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc is null ";
			}
			if(criteres.getTravailSurDateLivraison()){
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateLivDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateLivDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateLivDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			}else{
				if(criteres.getMois()!=null)
					JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
				if(criteres.getAnnee()!=null)
					JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
				if(criteres.getDateDeb()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
				if(criteres.getDateFin()!=null)
					JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			}

			
			if(criteres.getCodeTauxTVA()!=null && !criteres.getCodeTauxTVA().equals(""))
				JPQLQueryMouvDocument+=" and L.codeTvaLDocument =  '"+criteres.getCodeTauxTVA()+"'";
			
			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  a.codeArticle between '"+criteres.getCodeArticle()+"' and '"+criteres.getCodeArticleFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
				}
			}
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals("")) {
				if(criteres.getCodeFamilleFin()!=null && !criteres.getCodeFamilleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  fa.codeFamille between '"+criteres.getCodeFamille()+"' and '"+criteres.getCodeFamilleFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  fa.codeFamille like '"+criteres.getCodeFamille()+"'";
				}
			}
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			if(criteres.getTravailSurDateLivraison())//Clause group by
			JPQLQueryMouvDocument+=" group by  F.dateLivDocument,a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille,F.codeDocument,F.dateLivDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";
			else JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";

			//clause having
			if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
			}
			if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
			} 
			
			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
			List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumMouvementEtatTiers(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				"select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
				//"ft.codeFamille,ft.libcFamille,'E', extract(month from F.dateDocument) ," +
				"'','','E', extract(month from F.dateDocument) ," +
				" extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
				" L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				"sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				//" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a join t.taFamilleTierses ft "+   //left 
				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a"+   //left
				" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr    
			if(criteres.getExclusionQteSsUnite())
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc is null ";
			}
			
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			//Clause where
			if(criteres.getCodeTiers()!=null && !criteres.getCodeTiers().equals("")) {
				if(criteres.getCodeArticleFin()!=null && !criteres.getCodeArticleFin().equals("")) {
					JPQLQueryMouvDocument+=" and  t.codeTiers between '"+criteres.getCodeTiers()+"' and '"+criteres.getCodeTiersFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  t.codeTiers like '"+criteres.getCodeTiers()+"'";
				}
			}
			if(criteres.getCodeFamilleTiers()!=null && !criteres.getCodeFamilleTiers().equals("")) {
				if(criteres.getCodeFamilleTiersFin()!=null && !criteres.getCodeFamilleTiersFin().equals("")) {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like between '"+criteres.getCodeFamilleTiers()+"' and '"+criteres.getCodeFamilleTiersFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like like '"+criteres.getCodeFamilleTiers()+"'";
				}
			}
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			//JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers ,ft.codeFamille,ft.libcFamille, L.u1LDocument, L.u2LDocument";			
			JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers, L.u1LDocument, L.u2LDocument";

			//clause having
			if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
			}
			if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
			} 
			
			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
			List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumMouvementEtatFamilleTiers(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				"select NEW fr.legrain.dms.dao.TaEtatMouvementDms(" +
				"t.codeTiers,i.nomTiers , ft.codeFamille,ft.libcFamille," +
				"F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
				"'','','E', extract(month from F.dateDocument) ," +
				" extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
				" L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				"sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join t.taFamilleTierses ft "+   //left 
				" where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr    
			if(criteres.getExclusionQteSsUnite())
				JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
						" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			if(criteres.isLocalisationTVAFr() || criteres.isLocalisationTVAUE() || criteres.isLocalisationTVAHUE() || criteres.isLocalisationTVAFranchise()) {
				String JPQLLocalisationTVAIn = "";
				if(criteres.isLocalisationTVAFr()) {
					JPQLLocalisationTVAIn+="'"+"F"+"',";
				}
				if(criteres.isLocalisationTVAUE()) {
					JPQLLocalisationTVAIn+="'"+"UE"+"',";
				}
				if(criteres.isLocalisationTVAHUE()) {
					JPQLLocalisationTVAIn+="'"+"HUE"+"',";
				}
				if(criteres.isLocalisationTVAFranchise()) {
					JPQLLocalisationTVAIn+="'"+"N"+"',";
				}
				if(JPQLLocalisationTVAIn.endsWith("',")) {
					JPQLLocalisationTVAIn = JPQLLocalisationTVAIn.substring(0,JPQLLocalisationTVAIn.length()-2)+"'";
				}
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc in ("+JPQLLocalisationTVAIn+") ";
			} else {
				JPQLQueryMouvDocument+=" and i.codeTTvaDoc is null ";
			}
			
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			//Clause where
			if(criteres.getCodeFamilleTiers()!=null && !criteres.getCodeFamilleTiers().equals("")) {
				if(criteres.getCodeFamilleTiersFin()!=null && !criteres.getCodeFamilleTiersFin().equals("")) {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like between '"+criteres.getCodeFamilleTiers()+"' and '"+criteres.getCodeFamilleTiersFin()+"'";
				} else {
					JPQLQueryMouvDocument+=" and  ft.codeFamille like like '"+criteres.getCodeFamilleTiers()+"'";
				}
			}
//			if(criteres.getCodeFamilleTiers()!=null && !criteres.getCodeFamilleTiers().equals(""))
//				JPQLQueryMouvDocument+=" and ft.codeFamille like '"+criteres.getCodeFamilleTiers()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers ,ft.codeFamille,ft.libcFamille, L.u1LDocument, L.u2LDocument";			

			if(!criteres.getExclusionQteSsUnite()){
				JPQLQueryMouvDocument+= " union " +
				" select NEW fr.legrain.dms.dao.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
				" '','S', extract(month from F.dateDocument) ," +
				" extract(year from F.dateDocument) ,0,"+
				" L.u1LDocument,0,L.u2LDocument ,sum(-1*L.mtHtLApresRemiseGlobaleDocument)," +
				" sum(-1*(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument)),sum(-1*L.mtTtcLApresRemiseGlobaleDocument) ) "+
				" from TaLAvoir L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a   "+   //left 
				" where  a.codeArticle is not null and (L.u1LDocument='' )";
			
			if(criteres.getMois()!=null)
				JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
			if(criteres.getAnnee()!=null)
				JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
			if(criteres.getDateDeb()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
			if(criteres.getDateFin()!=null)
				JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";
			//Clause where
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			
			}
			//clause having
			if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1();
			}
			if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
				if(!JPQLQueryMouvDocument.contains(" having "))
					JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2();
				else
					JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2();		    	
			} 
			
			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
			List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}

}
