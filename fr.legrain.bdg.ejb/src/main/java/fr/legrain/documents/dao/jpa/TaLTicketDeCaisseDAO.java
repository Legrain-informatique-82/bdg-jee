package fr.legrain.documents.dao.jpa;

// Generated Apr 7, 2009 3:27:24 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import fr.legrain.article.model.TaEtatStock;
import fr.legrain.article.titretransport.model.TaEtatStockCapsules;
import fr.legrain.dms.model.TaEtatMouvementDms;
import fr.legrain.document.model.TaLTicketDeCaisse;
import fr.legrain.documents.dao.ILFactureDAO;
import fr.legrain.documents.dao.ILTicketDeCaisseDAO;
import fr.legrain.lib.data.LibDate;
import fr.legrain.validator.BeanValidator;

/**
 * Home object for domain model class TaLTicketDeCaisse.
 * @see fr.legrain.documents.dao.TaLTicketDeCaisse
 * @author Hibernate Tools
 */
public class TaLTicketDeCaisseDAO /*extends AbstractApplicationDAO<TaLTicketDeCaisse>*/ implements ILTicketDeCaisseDAO{

//	private static final Log log = LogFactory.getLog(TaLTicketDeCaisseDAO.class);
	static Logger logger = Logger.getLogger(TaLTicketDeCaisseDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;
	
	boolean  raffraichir=false;
	private String defaultJPQLQuery = "select a from TaLTicketDeCaisse a";
	

//	private String JPQLQueryMouvDocumentSorties=
//	 "select L.taDocument.dateDocument,L.taArticle.codeArticle,sum(-1*L.qteLDocument),"+
//	 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  "+
//	 "from TaLTicketDeCaisse L "+
//	 " where L.taDocument.idDocument not in (select rdoc.taFacture.idDocument from TaRDocument rdoc) "+
//	 " and (L.u1LDocument='' "+
//	 " or L.u1LDocument in (select u.codeUnite from TaUnite u)) "+
//	 " and L.taArticle.codeArticle is not null  "+ 
//	 " group by  L.taDocument.dateDocument,L.taArticle.codeArticle, L.u1LDocument, L.u2LDocument";

	public TaLTicketDeCaisseDAO(){
//		this(null);
	}
	
//	public TaLTicketDeCaisseDAO(EntityManager em){
//		if(em!=null) {
//			setEm(em);
//		}
////		champIdTable=ctrlGeneraux.getID_TABLE(TaLTicketDeCaisse.class.getSimpleName());
//		initChampId(TaLTicketDeCaisse.class);
//		setJPQLQuery(defaultJPQLQuery);
//		setListeChampMaj(Const.C_FICHIER_INI_CHAMPMAJ);
//		initNomTable(new TaLTicketDeCaisse());
//	}

	public void persist(TaLTicketDeCaisse transientInstance) {
		logger.debug("persisting TaLTicketDeCaisse instance");
		try {
			entityManager.persist(transientInstance);
			logger.debug("persist successful");
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			throw re;
		}
	}
	
//	public TaLTicketDeCaisse refresh(TaLTicketDeCaisse detachedInstance) {
//		logger.debug("refresh instance");
//		try {
//			detachedInstance.setLegrain(false);
//			entityManager.refresh(detachedInstance);
////			org.hibernate.Session session = (org.hibernate.Session) entityManager.getDelegate();
////		    session.evict(detachedInstance);
////		    detachedInstance = entityManager.find(TaLTicketDeCaisse.class, detachedInstance.getIdLDocument());
//			detachedInstance.setLegrain(true);
//		    return detachedInstance;
//			
//		} catch (RuntimeException re) {
//			logger.error("refresh failed", re);
//			throw re;
//		}
//	}
	
	public void remove(TaLTicketDeCaisse persistentInstance) {
		logger.debug("removing TaLTicketDeCaisse instance");
		try {
			entityManager.remove(persistentInstance);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public TaLTicketDeCaisse merge(TaLTicketDeCaisse detachedInstance) {
		logger.debug("merging TaLTicketDeCaisse instance");
		try {
			TaLTicketDeCaisse result = entityManager.merge(detachedInstance);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	public TaLTicketDeCaisse findById(int id) {
		logger.debug("getting TaLTicketDeCaisse instance with id: " + id);
		try {
			TaLTicketDeCaisse instance = entityManager.find(TaLTicketDeCaisse.class, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	public void annuler(TaLTicketDeCaisse persistentInstance) throws Exception{
		persistentInstance.setLegrain(false);
//		super.annuler(persistentInstance);
	}
	
	/* (non-Javadoc)
	 * @see fr.legrain.articles.dao.ILgrDAO#selectAll()
	 */
	public List<TaLTicketDeCaisse> selectAll() {
		logger.debug("selectAll TaLTicketDeCaisse");
		try {
			Query ejbQuery = entityManager.createQuery(defaultJPQLQuery);
			List<TaLTicketDeCaisse> l = ejbQuery.getResultList();
			logger.debug("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			logger.error("selectAll failed", re);
			throw re;
		}
	}
	public List<TaEtatStock> selectSumMouvDocumentStocksSorties(TaEtatStock criteres,Date dateDeb) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.stocks.dao.TaEtatStock(a.codeArticle,'S',f.dateDocument,sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,f.codeDocument,L.libLDocument,'Facture','7' ) "+
				 " from TaLTicketDeCaisse L join L.taDocument f join L.taArticle a "+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
			 if(criteres.getExclusionQteSsUnite())
				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			 JPQLQueryMouvDocument+=" and  ((L.qteLDocument is not null and L.qteLDocument >0)" +
			 		"or(L.qte2LDocument is not null and L.qte2LDocument >0)) "  ;
			
			if(dateDeb!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument > '"+LibDate.dateToStringSql(dateDeb)+"'";
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
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1Stock();
		    }
		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStock> l = ejbQuery.getResultList();
		    for (TaEtatStock taEtatStock : l) {
				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSorties failed", re);
			throw re;
		}
	}
	
	public List<TaEtatStock> selectSumMouvDocumentStocksEntree(TaEtatStock criteres,Date dateDeb) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.stocks.dao.TaEtatStock(a.codeArticle,'E',f.dateDocument,sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument ,f.codeDocument,L.libLDocument,'Facture','4' ) "+
				 " from TaLTicketDeCaisse L join L.taDocument f join L.taArticle a "+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
			 if(criteres.getExclusionQteSsUnite())
				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			 JPQLQueryMouvDocument+=" and  ((L.qteLDocument is not null and L.qteLDocument <0)" +
			 		"or(L.qte2LDocument is not null and L.qte2LDocument <0)) "  ;
			
			if(dateDeb!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument > '"+LibDate.dateToStringSql(dateDeb)+"'";
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
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteLDocument) >= "+criteres.getQte1Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteLDocument) >= "+criteres.getQte1Stock();
		    }
		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
		    	if(!JPQLQueryMouvDocument.contains(" having "))
		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();
		    	else
		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStock> l = ejbQuery.getResultList();
		    for (TaEtatStock taEtatStock : l) {
				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSorties failed", re);
			throw re;
		}
	}
	
	public List<TaEtatStockCapsules> selectSumMouvDocumentStocksCapsulesSorties(TaEtatStockCapsules criteres,Date dateDeb, boolean strict) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.gestioncapsules.dao.TaEtatStockCapsules(L.codeTitreTransport,t.libelleTitreTransport,'S',f.dateDocument,sum(-1*L.qteTitreTransport)," +
				 "f.codeDocument,L.libLDocument,'Facture','7' ) "+
				 " from TaLTicketDeCaisse L join L.taDocument f , TaTitreTransport t"+
				 //" join L.codeTitreTransport a "+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
//			 if(criteres.getExclusionQteSsUnite())
//				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u ))";// +
//				 		"or" +
//				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			 JPQLQueryMouvDocument+=" and  ((L.qteTitreTransport is not null and L.qteTitreTransport >0)) ";// +
			 		//"or(L.qte2LDocument is not null and L.qte2LDocument >0)) "  ;
			
			if(dateDeb!=null) {
				String signe = ">";
				if(!strict) signe=">=";
				JPQLQueryMouvDocument+=" and f.dateDocument "+signe+" '"+LibDate.dateToStringSql(dateDeb)+"'";
			}
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
			 //JPQLQueryMouvDocument+=" group by  f.dateDocument,L.codeTitreTransport, L.u1LDocument, L.u2LDocument,f.codeDocument,L.libLDocument";			
			JPQLQueryMouvDocument+=" group by  f.dateDocument,L.codeTitreTransport,t.libelleTitreTransport,f.codeDocument,L.libLDocument";
			
		    //clause having
//		    if(criteres.getQteCapsuleStock()!=null && !criteres.getQteCapsuleStock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    }
//		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
//		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStockCapsules> l = ejbQuery.getResultList();
//		    for (TaEtatStockCapsules taEtatStock : l) {
//				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
////				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
//			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSorties failed", re);
			throw re;
		}
	}
	
	public List<TaEtatStockCapsules> selectSumMouvDocumentStocksCapsulesEntree(TaEtatStockCapsules criteres,Date dateDeb, boolean strict) {
		try {
			String JPQLQueryMouvDocument=
				 "select NEW fr.legrain.gestioncapsules.dao.TaEtatStockCapsules(L.codeTitreTransport,t.libelleTitreTransport,'E',f.dateDocument" +
				 ",sum(-1*L.qteTitreTransport)," +
				 "f.codeDocument,L.libLDocument,'Facture','4' ) "+
				 " from TaLTicketDeCaisse L join L.taDocument f , TaTitreTransport t"+
				 " where f.idDocument not in " +
				 " (select rdoc.taFacture.idDocument from TaRDocument rdoc where id_bonliv is not null) ";
//			 if(criteres.getExclusionQteSsUnite())
//				 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u ))";// +
//				 		"or" +
//				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;

			 JPQLQueryMouvDocument+=" and  ((L.qteTitreTransport is not null and L.qteTitreTransport <0)) ";
			 		//"or(L.qte2LDocument is not null and L.qte2LDocument <0)) "  ;
			
			if(dateDeb!=null) {
				String signe = ">";
				if(!strict) signe=">=";
				JPQLQueryMouvDocument+=" and f.dateDocument "+signe+" '"+LibDate.dateToStringSql(dateDeb)+"'";
			}
			//Clause where
			if(criteres.getCodeTitreTransport()!=null && !criteres.getCodeTitreTransport().equals(""))
				JPQLQueryMouvDocument+=" and L.codeTitreTransport like '"+criteres.getCodeTitreTransport()+"'";
			if(criteres.getDateStock()!=null)
				JPQLQueryMouvDocument+=" and f.dateDocument <= '"+LibDate.dateToStringSql(criteres.getDateStock())+"'";
			
			JPQLQueryMouvDocument+=" and L.codeTitreTransport = t.codeTitreTransport";
//			if(criteres.getUn1Stock()!=null && !criteres.getUn1Stock().equals(""))
//				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1Stock()+"'";
//			 if(criteres.getUn2Stock()!=null && !criteres.getUn2Stock().equals(""))
//				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2Stock()+"'";
			 
			 
			//Clause group by
			 JPQLQueryMouvDocument+=" group by  f.dateDocument,L.codeTitreTransport,t.libelleTitreTransport,f.codeDocument,L.libLDocument";			
			
		    //clause having
//		    if(criteres.getQteCapsuleStock()!=null && !criteres.getQteCapsuleStock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(-1*L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(-1*L.qteTitreTransport) >= "+criteres.getQteCapsuleStock();
//		    }
//		    if(criteres.getQte2Stock()!=null && !criteres.getQte2Stock().equals("")){
//		    	if(!JPQLQueryMouvDocument.contains(" having "))
//		    		JPQLQueryMouvDocument+=" having sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();
//		    	else
//		    		JPQLQueryMouvDocument+=" and sum(-1*L.qte2LDocument) >= "+criteres.getQte2Stock();		    	
//		    } 

			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatStockCapsules> l = ejbQuery.getResultList();
//		    for (TaEtatStockCapsules taEtatStock : l) {
//				if(taEtatStock.getQte1Stock()!=null && taEtatStock.getQte1Stock().signum()==0)taEtatStock.setUn1Stock(null);
////				if(taEtatStock.getQte2Stock()!=null && taEtatStock.getQte2Stock().signum()==0)taEtatStock.setUn2Stock(null);
//			}
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSorties failed", re);
			throw re;
		}
	}

	public List<TaEtatMouvementDms> selectSumMouvementDms(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  				
				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
				 "fa.codeFamille,'S', extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument) ,sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  join a.taFamille fa " +
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
		    List<TaEtatMouvementDms> liste1 = ejbQuery.getResultList();
		    
		    JPQLQueryMouvDocument="";
 
		    
		    if(!criteres.getExclusionQteSsUnite()){
				JPQLQueryMouvDocument+= "  " +
				 " select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
				 " fa.codeFamille,'S', extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument) ,'0',"+
				 " L.u1LDocument,'0',L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 " sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  join a.taFamille fa "+   //left 
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
			
			ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> liste2 = ejbQuery.getResultList();
		    liste1.addAll(liste2);
			return liste1;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	public List<TaEtatMouvementDms> selectSumMouvementEtatFamille(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument= ""; 
//				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
//				 "fa.codeFamille,fa.libcFamille,'S', extract(month from F.dateDocument) ," +
//				 " extract(year from F.dateDocument) ,sum(L.qteLDocument),"+
//				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
//				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  left join a.taFamille fa " +
//				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
			if(criteres.getTravailSurDateLivraison()){
				JPQLQueryMouvDocument= "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateLivDocument,a.codeArticle,a.libellecArticle," +
						 "fa.codeFamille,fa.libcFamille,'S', extract(month from F.dateLivDocument) ," +
						 " extract(year from F.dateLivDocument) ,(L.qteLDocument),"+
						 " L.u1LDocument,(L.qte2LDocument),L.u2LDocument ,(L.mtHtLApresRemiseGlobaleDocument)," +
						 "(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),(L.mtTtcLApresRemiseGlobaleDocument) ) "+
						 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  left join a.taFamille fa " +
						 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
			}else{
				JPQLQueryMouvDocument= "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
						 "fa.codeFamille,fa.libcFamille,'S', extract(month from F.dateDocument) ," +
						 " extract(year from F.dateDocument) ,(L.qteLDocument),"+
						 " L.u1LDocument,(L.qte2LDocument),L.u2LDocument ,(L.mtHtLApresRemiseGlobaleDocument)," +
						 "(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),(L.mtTtcLApresRemiseGlobaleDocument) ) "+
						 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  left join a.taFamille fa " +
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
																	
 
//		    if(!criteres.getExclusionQteSsUnite()){
//				JPQLQueryMouvDocument+= " union " +
//				 " select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
//				 " fa.codeFamille,'S', extract(month from F.dateDocument) ," +
//				 " extract(year from F.dateDocument) ,0,"+
//				 " L.u1LDocument,0,L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
//				 " sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a  join a.taFamille fa "+   //left 
//				 " where  a.codeArticle is not null and (L.u1LDocument='' )";
//				
//				if(criteres.getMois()!=null)
//					JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
//				if(criteres.getAnnee()!=null)
//					JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
//				if(criteres.getDateDeb()!=null)
//					JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
//				if(criteres.getDateFin()!=null)
//					JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";		
//				//Clause where
//				if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
//					JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
//				if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
//					JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
//				if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
//					JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
//				 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
//					 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
//				//Clause group by
//				 JPQLQueryMouvDocument+=" group by  F.dateDocument,fa.codeFamille,a.codeArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			
//		    }													
//			    //clause having
//			    if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
//			    }
//			    if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
//			    }				
			
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
			String JPQLQueryMouvDocument="" ; 
//				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
//				 "fa.codeFamille,fa.libcFamille,'S', extract(month from F.dateDocument) ," +
//				 " extract(year from F.dateDocument) ,sum(L.qteLDocument),"+
//				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
//				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa" +
//				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
				if(criteres.getTravailSurDateLivraison()){
					JPQLQueryMouvDocument="select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateLivDocument,a.codeArticle,a.libellecArticle," +
							 "fa.codeFamille,fa.libcFamille,'S', extract(month from F.dateLivDocument) ," +
							 " extract(year from F.dateLivDocument) ,(L.qteLDocument),"+
							 " L.u1LDocument,(L.qte2LDocument),L.u2LDocument ,(L.mtHtLApresRemiseGlobaleDocument)," +
							 "(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),(L.mtTtcLApresRemiseGlobaleDocument) ) "+
							 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa" +
							 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr
				}else{
					JPQLQueryMouvDocument="select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
					 "fa.codeFamille,fa.libcFamille,'S', extract(month from F.dateDocument) ," +
					 " extract(year from F.dateDocument) ,(L.qteLDocument),"+
					 " L.u1LDocument,(L.qte2LDocument),L.u2LDocument ,(L.mtHtLApresRemiseGlobaleDocument)," +
					 "(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),(L.mtTtcLApresRemiseGlobaleDocument) ) "+
					 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join a.taFamille fa" +
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
//			//Clause group by
//			 JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			
//																						
//			    //clause having
//			    if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
//			    }
//			    if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
//			    }				
			
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
//				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
//				 //" ft.codeFamille,ft.libcFamille,'S', extract(month from F.dateDocument) ," +
//				 " '','','S', extract(month from F.dateDocument) ," +
//				 " extract(year from F.dateDocument) ,sum(L.qteLDocument),"+
//				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
//				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				 //" from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a join t.taFamilleTierses ft" +
//				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a " +
//				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
					 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
					 //" ft.codeFamille,ft.libcFamille,'S', extract(month from F.dateDocument) ," +
					 " '','','S', extract(month from F.dateDocument) ," +
					 " extract(year from F.dateDocument) ,(L.qteLDocument),"+
					 " L.u1LDocument,(L.qte2LDocument),L.u2LDocument ,(L.mtHtLApresRemiseGlobaleDocument)," +
					 "(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),(L.mtTtcLApresRemiseGlobaleDocument) ) "+
					 //" from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a join t.taFamilleTierses ft" +
					 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a " +
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
//			//Clause group by
//			 //JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers ,ft.codeFamille,ft.libcFamille, L.u1LDocument, L.u2LDocument";			
//			 JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers, L.u1LDocument, L.u2LDocument";
//																									
//			    //clause having
//			    if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
//			    }
//			    if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
//			    }				
			
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
//				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(" +
//				 "t.codeTiers,i.nomTiers , ft.codeFamille,ft.libcFamille," +
//				 "F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
//				 "'','','S', extract(month from F.dateDocument) ," +
//				 " extract(year from F.dateDocument) ,sum(L.qteLDocument),"+
//				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
//				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join t.taFamilleTierses ft" +
//				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
					 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(" +
					 "t.codeTiers,i.nomTiers , ft.codeFamille,ft.libcFamille," +
					 "F.codeDocument,F.dateDocument,a.codeArticle,a.libellecArticle," +
					 "'','','S', extract(month from F.dateDocument) ," +
					 " extract(year from F.dateDocument) ,(L.qteLDocument),"+
					 " L.u1LDocument,(L.qte2LDocument),L.u2LDocument ,(L.mtHtLApresRemiseGlobaleDocument)," +
					 "(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),(L.mtTtcLApresRemiseGlobaleDocument) ) "+
					 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a left join t.taFamilleTierses ft" +
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
//			//Clause group by
//			 JPQLQueryMouvDocument+=" group by  a.codeArticle,a.libellecArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , ft.codeFamille,ft.libcFamille, L.u1LDocument, L.u2LDocument";			
																	
 
//		    if(!criteres.getExclusionQteSsUnite()){
//				JPQLQueryMouvDocument+= " union " +
//				 " select NEW fr.legrain.dms.model.TaEtatMouvementDms(t.codeTiers,i.nomTiers ,F.codeDocument,F.dateDocument,a.codeArticle," +
//				 " '','S', extract(month from F.dateDocument) ," +
//				 " extract(year from F.dateDocument) ,0,"+
//				 " L.u1LDocument,0,L.u2LDocument ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
//				 " sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+
//				 " from TaLTicketDeCaisse L  join L.taDocument F join F.taInfosDocument i join F.taTiers t join L.taArticle a   "+   //left 
//				 " where  a.codeArticle is not null and (L.u1LDocument='' )";
//				
//				if(criteres.getMois()!=null)
//					JPQLQueryMouvDocument+=" and extract(month from F.dateDocument) = '"+criteres.getMois()+"'";
//				if(criteres.getAnnee()!=null)
//					JPQLQueryMouvDocument+=" and extract(year from F.dateDocument) = '"+criteres.getAnnee()+"'";
//				if(criteres.getDateDeb()!=null)
//					JPQLQueryMouvDocument+=" and F.dateDocument >=  '"+LibDate.dateToStringSql(criteres.getDateDeb())+"'";
//				if(criteres.getDateFin()!=null)
//					JPQLQueryMouvDocument+=" and F.dateDocument <=  '"+LibDate.dateToStringSql(criteres.getDateFin())+"'";		
//				//Clause where
//				if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
//					JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
//
//				if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
//					JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
//				 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
//					 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
//				//Clause group by
//				 JPQLQueryMouvDocument+=" group by  F.dateDocument,a.codeArticle,F.codeDocument,F.dateDocument,t.codeTiers,i.nomTiers , L.u1LDocument, L.u2LDocument";			
//		    }													
//			    //clause having
//			    if(criteres.getQte1()!=null && !criteres.getQte1().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qteLDocument) >= "+criteres.getQte1();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qteLDocument) >= "+criteres.getQte1();
//			    }
//			    if(criteres.getQte2()!=null && !criteres.getQte2().equals(BigDecimal.valueOf(0))){
//			    	if(!JPQLQueryMouvDocument.contains(" having "))
//			    		JPQLQueryMouvDocument+=" having sum(L.qte2LDocument) >= "+criteres.getQte2();
//			    	else
//			    		JPQLQueryMouvDocument+=" and sum(L.qte2LDocument) >= "+criteres.getQte2();		    	
//			    }				
			
			Query ejbQuery = entityManager.createQuery(JPQLQueryMouvDocument);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}
	
	public List<TaEtatMouvementDms> selectSumReportDms(TaEtatMouvementDms criteres) {
		try {
			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(fa.codeFamille,a.codeArticle,'S' ,sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ) "+
				 " from TaLTicketDeCaisse L  join L.taArticle a join a.taFamille fa " +
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr 
				 if(criteres.getExclusionQteSsUnite())
					 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
				 
				 
			Date dateDeb=LibDate.stringToDateNew("01/"+criteres.getMois()+"/"+criteres.getAnnee());
			//dateDeb.setTime(dateDeb.getTime()-1);
			
			JPQLQueryMouvDocument+=" and exists(select f.idDocument from TaFacture f where f.idDocument=L.taDocument.idDocument and f.dateDocument < :date )";

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
			 JPQLQueryMouvDocument+=" group by fa.codeFamille,a.codeArticle, L.u1LDocument, L.u2LDocument";			
																	
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
			ejbQuery.setParameter("date", dateDeb,TemporalType.DATE);
		    List<TaEtatMouvementDms> l = ejbQuery.getResultList();
			return l;
		} catch (RuntimeException re) {
			logger.error("selectSumMouvDocumentSortiesDms failed", re);
			throw re;
		}
	}

	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatFamille(TaEtatMouvementDms criteres) {
		try {

			String JPQLQueryMouvDocument= "" ;
			if(criteres.getTravailSurDateLivraison()){
			JPQLQueryMouvDocument= "select NEW fr.legrain.dms.model.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'S' , extract(month from F.dateLivDocument) ," +
				 " extract(year from F.dateLivDocument),sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
				 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO " +
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr  
			}else{
				JPQLQueryMouvDocument= "select NEW fr.legrain.dms.model.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'S' , extract(month from F.dateDocument) ," +
						 " extract(year from F.dateDocument),sum(L.qteLDocument),"+
						 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
						 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
						 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO " +
						 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr  	
			}
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
			 JPQLQueryMouvDocument+=" group by fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle, L.u2LDocument, L.u1LDocument,F.dateLivDocument";
			else JPQLQueryMouvDocument+=" group by fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle, L.u2LDocument, L.u1LDocument,F.dateDocument";
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
	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatArticle(TaEtatMouvementDms criteres) {
		try {

			String JPQLQueryMouvDocument=""  ;
			if(criteres.getTravailSurDateLivraison()){
			JPQLQueryMouvDocument="select NEW fr.legrain.dms.model.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'S' , extract(month from F.dateLivDocument) ," +
				 " extract(year from F.dateLivDocument),sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
				 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO " +
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr
			}else{
				JPQLQueryMouvDocument="select NEW fr.legrain.dms.model.TaEtatMouvementDms(fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle,'S' , extract(month from F.dateDocument) ," +
						 " extract(year from F.dateDocument),sum(L.qteLDocument),"+
						 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
						 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
						 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a left join a.taFamille fa join F.taInfosDocument INFO " +
						 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr
			}
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
			if(criteres.getTravailSurDateLivraison())//Clause group by			 
			 JPQLQueryMouvDocument+=" group by a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille, L.u1LDocument, L.u2LDocument,F.dateLivDocument";
			else JPQLQueryMouvDocument+=" group by a.codeArticle,a.libellecArticle,fa.codeFamille,fa.libcFamille, L.u1LDocument, L.u2LDocument,F.dateDocument";
																	
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
	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatFamilleTiers(TaEtatMouvementDms criteres) {
		try {

			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(" +
				 "fa.codeFamille,fa.libcFamille,a.codeArticle,a.libellecArticle," +
				 "ft.codeFamille,ft.libcFamille,t.codeTiers,INFO.nomTiers," + 
				 "'S' , extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument),sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
				 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a left join a.taFamille fa " +
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
	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvEtatTiers(TaEtatMouvementDms criteres) {
		try {

			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms('','',a.codeArticle,a.libellecArticle," +
				 //"ft.codeFamille,ft.libcFamille,t.codeTiers,i.nomTiers," +
				 "'','',t.codeTiers,i.nomTiers," +
				 "'S' , extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument),sum(L.qteLDocument),"+
				 " L.u1LDocument,sum(L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
				 //" from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a join F.taInfosDocument i join F.taTiers t left join t.taFamilleTierses ft" +
				 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a join F.taInfosDocument i join F.taTiers t" +
				 " where L.taArticle is not null " ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr   
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
			
			if(criteres.getCodeTauxTVA()!=null)
				JPQLQueryMouvDocument+=" and L.codeTvaLDocument =  '"+criteres.getCodeTauxTVA()+"'";
			
//			//Clause where
			if(criteres.getCodeTiers()!=null && !criteres.getCodeTiers().equals("")) {
				if(criteres.getCodeTiersFin()!=null && !criteres.getCodeTiersFin().equals("")) {
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
			 //JPQLQueryMouvDocument+=" group by t.codeTiers,i.nomTiers,ft.codeFamille,ft.libcFamille,a.codeArticle,a.libellecArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";	
			 JPQLQueryMouvDocument+=" group by t.codeTiers,i.nomTiers,a.codeArticle,a.libellecArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";
																	
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

	
	public List<TaEtatMouvementDms> selectSumSyntheseMouvDms(TaEtatMouvementDms criteres) {
		try {

			String JPQLQueryMouvDocument=  
				 "select NEW fr.legrain.dms.model.TaEtatMouvementDms(fa.codeFamille,a.codeArticle,'S' , extract(month from F.dateDocument) ," +
				 " extract(year from F.dateDocument),sum(-1*L.qteLDocument),"+
				 " L.u1LDocument,sum(-1*L.qte2LDocument),L.u2LDocument  ,sum(L.mtHtLApresRemiseGlobaleDocument)," +
				 "sum(L.mtTtcLApresRemiseGlobaleDocument-L.mtHtLApresRemiseGlobaleDocument),sum(L.mtTtcLApresRemiseGlobaleDocument) ) "+ 
				 " from TaLTicketDeCaisse L  join L.taDocument F join L.taArticle a join a.taFamille fa " +
				 " where L.taArticle is not null" ;//cette ligne est inutile fu point de vue sql, mais elle permet de commencer la condition de façon sûr   
				 if(criteres.getExclusionQteSsUnite()){
					 JPQLQueryMouvDocument+=" and  (L.u1LDocument in (select u.codeUnite from TaUnite u )or" +
				 		" L.u2LDocument in (select u.codeUnite from TaUnite u ))" ;
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
			if(criteres.getCodeArticle()!=null && !criteres.getCodeArticle().equals(""))
				JPQLQueryMouvDocument+=" and  a.codeArticle like '"+criteres.getCodeArticle()+"'";
			if(criteres.getCodeFamille()!=null && !criteres.getCodeFamille().equals(""))
				JPQLQueryMouvDocument+=" and fa.codeFamille like '"+criteres.getCodeFamille()+"'";
			if(criteres.getUn1()!=null && !criteres.getUn1().equals(""))
				JPQLQueryMouvDocument+=" and L.u1LDocument like '"+criteres.getUn1()+"'";
			 if(criteres.getUn2()!=null && !criteres.getUn2().equals(""))
				 JPQLQueryMouvDocument+=" and L.u2LDocument like '"+criteres.getUn2()+"'";
			//Clause group by
			 JPQLQueryMouvDocument+=" group by fa.codeFamille,a.codeArticle, L.u1LDocument, L.u2LDocument,F.dateDocument";	
																	
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

	

	public List<TaLTicketDeCaisse> rechercheLigneSupportAbon(Date dateDeb, Date datefin, String codeSupportAbon, String codeTiers) {
		List<TaLTicketDeCaisse> result = null;
		Query query = null;

//		String requete="select lf from TaLTicketDeCaisse lf join lf.taArticle a join lf.taDocument f join f.taTiers t  " +
//				" where f.dateDocument between ? and ?" +
//				" and t.codeTiers like ? and exists(" +
//				" select a1 from TaArticle a1 join a1.taRTSupports rta join rta.taTSupport ta where a1.codeArticle=a.codeArticle  and ta.codeTSupport like ?)" +
//				" and (select count(s) from TaSupportAbon s  where s.taLDocument.idLDocument=lf.idLDocument)<lf.qteLDocument" +
//				" order by f.codeDocument,a.codeArticle";
		
		String requete="select lf from TaLTicketDeCaisse lf join lf.taArticle a join lf.taDocument f join f.taTiers t  " +
				" where f.dateDocument between :dateDeb and :dateFin" +
				" and t.codeTiers like :codeTiers and exists(" +
				" select rta from TaRTSupport rta join rta.taArticle a1 join rta.taTSupport ta where a1.codeArticle=a.codeArticle  and ta.codeTSupport like :codeTSupport)" +
				" and (select count(s) from TaSupportAbon s  where s.taLDocument.idLDocument=lf.idLDocument)<" +
				"(select sum(lf2.qteLDocument)from TaLTicketDeCaisse lf2 where lf2.taDocument.idDocument=f.idDocument and" +
				" lf2.taArticle.idArticle=a.idArticle)" +
				" order by f.codeDocument,a.codeArticle";

		if(codeSupportAbon==null || codeSupportAbon.toLowerCase().equals("tous")) {
			query = entityManager.createQuery(requete);
			query.setParameter("dateDeb", dateDeb);
			query.setParameter("dateFin", datefin);
			query.setParameter("codeTiers", codeTiers);
			query.setParameter("codeTSupport", "%");
		}else if(codeSupportAbon!=null ) {
			query = entityManager.createQuery(requete);
			query.setParameter("dateDeb", dateDeb);
			query.setParameter("dateFin", datefin);
			query.setParameter("codeTiers", codeTiers);
			query.setParameter("codeTSupport", codeSupportAbon);
		}
		
		result = query.getResultList();
		return result;
	}
	
	@Override
	public List<TaLTicketDeCaisse> findWithNamedQuery(String queryName) {
		try {
			Query ejbQuery = entityManager.createNamedQuery(queryName);
			List<TaLTicketDeCaisse> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<TaLTicketDeCaisse> findWithJPQLQuery(String JPQLquery) {
		try {
			Query ejbQuery = entityManager.createQuery(JPQLquery);
			List<TaLTicketDeCaisse> l = ejbQuery.getResultList();
			System.out.println("selectAll successful");
			return l;
		} catch (RuntimeException re) {
			System.out.println("selectAll failed");
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public boolean validate(TaLTicketDeCaisse value) throws Exception {
		BeanValidator<TaLTicketDeCaisse> validator = new BeanValidator<TaLTicketDeCaisse>(TaLTicketDeCaisse.class);
		return validator.validate(value);
	}

	@Override
	public boolean validateField(TaLTicketDeCaisse value, String propertyName)
			throws Exception {
		BeanValidator<TaLTicketDeCaisse> validator = new BeanValidator<TaLTicketDeCaisse>(TaLTicketDeCaisse.class);
		return validator.validateField(value,propertyName);
	}

	@Override
	public void detach(TaLTicketDeCaisse transientInstance) {
		entityManager.detach(transientInstance);
	}
	
	@Override
	public TaLTicketDeCaisse findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public void removeTousLesAbonnements(TaLTicketDeCaisse persistentInstance) {
//		try {
//			TaAbonnementDAO daoAbonnement = new TaAbonnementDAO(entityManager);
//			List<TaAbonnement> listeAbonnement=null;
//			listeAbonnement=daoAbonnement.selectAbonnementLigneFacture(persistentInstance);
//			if(listeAbonnement!=null){
//				for (TaAbonnement taAbonnement : listeAbonnement) {
//					daoAbonnement.remove(taAbonnement);
//				}
//			}
//		} catch (RuntimeException re) {
//			logger.error("removeTousLesAbonnements failed", re);
//			throw re;
//		}
//	}

}
