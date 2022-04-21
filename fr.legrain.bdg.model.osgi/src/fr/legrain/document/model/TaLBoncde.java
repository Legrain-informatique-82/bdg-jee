package fr.legrain.document.model;

// Generated Apr 9, 2009 2:12:06 PM by Hibernate Tools 3.2.0.CR1

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaEntrepot;
import fr.legrain.article.model.TaLot;
import fr.legrain.article.model.TaPrix;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.document.dto.TaLigneALigneSupplementDTO;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.stock.model.TaMouvementStockPrev;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaLDevis generated by hbm2java
 */
@Entity
@EntityListeners(SwtLDocumentListener.class)
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_l_boncde")
//@SequenceGenerator(name = "gen_l_boncde", sequenceName = "num_id_l_boncde", allocationSize = 1)
public class TaLBoncde extends SWTLigneDocument implements java.io.Serializable, ILigneDocumentTiers {

	private static final long serialVersionUID = -8697236961902517212L;
	
	static Logger logger = Logger.getLogger(TaLBoncde.class.getName());
//	private int idLDocument;
	private String version;
	private TaTLigne taTLigne;
	private TaArticle taArticle;
	private TaBoncde taDocument;
	private Integer numLigneLDocument;
//	private String libLDocument;
	private BigDecimal qteLDocument;
	private BigDecimal qte2LDocument;
	private String u1LDocument;
	private String u2LDocument;
	private BigDecimal prixULDocument;
	private BigDecimal tauxTvaLDocument;
	private String compteLDocument;
	private String codeTvaLDocument;
	private String libTvaLDocument;
	private String codeTTvaLDocument;
	private BigDecimal mtHtLDocument;
	private BigDecimal mtTtcLDocument;
	private BigDecimal remTxLDocument;
	private BigDecimal remHtLDocument;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Date datePrevue;
	private Set<TaLigneALigne> taLigneALignes = new HashSet<TaLigneALigne>(0);
	private Set<TaREtatLigneDocument> taREtatLigneDocuments = new HashSet<TaREtatLigneDocument>(0);
	private Set<TaHistREtatLigneDocument> taHistREtatLigneDocuments = new HashSet<TaHistREtatLigneDocument>(0);
	protected Set<TaLigneALigneEcheance> taLigneALignesEcheance = new HashSet<TaLigneALigneEcheance>(0);
	
	protected TaMouvementStockPrev taMouvementStockPrev;
	
	
	private String uSaisieLDocument;
	private BigDecimal qteSaisieLDocument;
	
	private String codeTitreTransport;
	private BigDecimal qteTitreTransport;



	@Transient
	private boolean legrain = false;
	@Transient
	private BigDecimal txRemHtDocument;
	@Transient
	private BigDecimal mtHtLApresRemiseGlobaleDocument;
	@Transient
	private BigDecimal mtTtcLApresRemiseGlobaleDocument;
	@Transient
	private List<TaLigneALigneSupplementDTO> listeSupplement=new LinkedList<>();
	@Transient
	private List<TaLigneALigneSupplementDTO> listeLigneAIntegrer=new LinkedList<>();

	
	private boolean abonnement;
	

	public TaLBoncde() {
	}
	
	public TaLBoncde	(boolean legrain) {
		this.legrain = legrain;
	}
	
	public TaLBoncde(int idLDevis) {
		this.idLDocument = idLDevis;
	}

	public TaLBoncde(int idLDevis, TaTLigne taTLigne, TaArticle taArticle,
			TaDevis taDevis, Integer numLigneLDevis, String libLDevis,
			BigDecimal qteLDevis, BigDecimal qte2LDevis, String u1LDevis,
			String u2LDevis, BigDecimal prixULDevis, BigDecimal tauxTvaLDevis,
			String compteLDevis, String codeTvaLDevis, String codeTTvaLDevis,
			BigDecimal mtHtLDevis, BigDecimal mtTtcLDevis,
			BigDecimal remTxLDevis, BigDecimal remHtLDevis,
			String quiCreeLDevis, Date quandCreeLDevis, String quiModifLDevis,
			Date quandModifLDevis, String ipAcces, Integer versionObj) {
		this.idLDocument = idLDevis;
		this.taTLigne = taTLigne;
		this.taArticle = taArticle;
		this.taDocument = taDocument;
		this.numLigneLDocument = numLigneLDevis;
		this.libLDocument = libLDevis;
		this.qteLDocument = qteLDevis;
		this.qte2LDocument = qte2LDevis;
		this.u1LDocument = u1LDevis;
		this.u2LDocument = u2LDevis;
		this.prixULDocument = prixULDevis;
		this.tauxTvaLDocument = tauxTvaLDevis;
		this.compteLDocument = compteLDevis;
		this.codeTvaLDocument = codeTvaLDevis;
		this.codeTTvaLDocument = codeTTvaLDevis;
		this.mtHtLDocument = mtHtLDevis;
		this.mtTtcLDocument = mtTtcLDevis;
		this.remTxLDocument = remTxLDevis;
		this.remHtLDocument = remHtLDevis;
		this.quiCree = quiCreeLDevis;
		this.quandCree = quandCreeLDevis;
		this.quiModif = quiModifLDevis;
		this.quandModif = quandModifLDevis;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_l_boncde")
//	@Column(name = "id_l_document", unique = true, nullable = false)
//	public int getIdLDocument() {
//		return this.idLDocument;
//	}
//
//	public void setIdLDocument(int idLBoncde) {
//		this.idLDocument = idLBoncde;
//	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_t_ligne")
	@LgrHibernateValidated(champBd = "id_t_ligne",table = "ta_t_ligne", champEntite="taTLigne.idTLigne", clazz = TaTLigne.class)
	public TaTLigne getTaTLigne() {
		return this.taTLigne;
	}

	public void setTaTLigne(TaTLigne taTLigne) {
		this.taTLigne = taTLigne;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_article")
	@LgrHibernateValidated(champBd = "id_article",table = "ta_article", champEntite="taArticle.idArticle", clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return this.taArticle;
	}

//	public void setTaArticle(TaArticle taArticle) {
//		this.taArticle = taArticle;
//		if(legrain) {
//			if(taArticle!=null) {
//				if(getTaTLigne()==null || getTaTLigne().getCodeTLigne()==null)
//					setTaTLigne(new TaTLigneDAO().findByCode(Const.C_CODE_T_LIGNE_H));
//				else
//					if(!getTaTLigne().getCodeTLigne().equals(Const.C_CODE_T_LIGNE_H))
//						setTaTLigne(new TaTLigneDAO().findByCode(Const.C_CODE_T_LIGNE_H));
//
//				TaPrix prix = taArticle.chercheTarif(taDocument.getTaTiers());
//				if(prix!=null) {
//					if(taDocument!=null) { //#JPA
//						if(taDocument.getTtc()==1)
//							setPrixULDocument(prix.getPrixttcPrix());
//						else
//							setPrixULDocument(prix.getPrixPrix());
//					}
//
//					if(taArticle.getTaUnite1()!=null) {
//						setU1LDocument(taArticle.getTaUnite1().getCodeUnite());
//					}
//				}
//
//				if(taArticle.getTaTva()!=null && taDocument.isGestionTVA()) {
//					setCodeTvaLDocument(taArticle.getTaTva().getCodeTva());
//					setTauxTvaLDocument(taArticle.getTaTva().getTauxTva());
//				}else{
//					setCodeTvaLDocument("");
//					setTauxTvaLDocument(BigDecimal.valueOf(0));
//				}
//
//				setLibLDocument(taArticle.getLibellecArticle());
//				setQteLDocument(new BigDecimal(1));
//				setCompteLDocument(taArticle.getNumcptArticle());
//
//				calculMontant();
//			}
//			
//			
//		}
//	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;

		if(legrain) {
			if(taArticle!=null) {
				
				System.out.println("===****=== Transfert de code metier des entites vers les services, à bien vérifier");
				//passage ejb => dans TaXXXService
//				if(getTaTLigne()==null || getTaTLigne().getCodeTLigne()==null)
//					setTaTLigne(new TaTLigneDAO().findByCode(SWTDocument.C_CODE_T_LIGNE_H));
//				else
//					if(!getTaTLigne().getCodeTLigne().equals(SWTDocument.C_CODE_T_LIGNE_H))
//						setTaTLigne(new TaTLigneDAO().findByCode(SWTDocument.C_CODE_T_LIGNE_H));
				
				boolean commentaire=false;
				commentaire=taArticle.getTaFamille()!=null && taArticle.getTaFamille().getCodeFamille().equals("&&&");
				if(!commentaire){
					TaPrix prix = taArticle.chercheTarif(taDocument.getTaTiers());
					if(prix!=null) {
						if(taDocument!=null) { //#JPA
							if(taDocument.getTtc()==1)
								setPrixULDocument(prix.getPrixttcPrix());
							else
								setPrixULDocument(prix.getPrixPrix());
						}

						if(taArticle.getTaUnite1()!=null) {
							setU1LDocument(taArticle.getTaUnite1().getCodeUnite());
						}
					}else{
						setPrixULDocument(BigDecimal.valueOf(0));
						setU1LDocument("");
					}

					if(taArticle.getTaTva()!=null && taDocument.isGestionTVA()) {
						setCodeTvaLDocument(taArticle.getTaTva().getCodeTva());
						setTauxTvaLDocument(taArticle.getTaTva().getTauxTva());
					}else{
						setCodeTvaLDocument("");
						setTauxTvaLDocument(BigDecimal.valueOf(0));
					}
					setQteLDocument(new BigDecimal(1));
					setCompteLDocument(taArticle.getNumcptArticle());
					calculMontant();
					setLibLDocument(taArticle.getLibellecArticle());
				}
				else {
				setLibLDocument(taArticle.getLibellecArticle());
				setTaArticle(null);
				}
			}
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_document")
	@LgrHibernateValidated(champBd = "id_document",table = "ta_boncde", champEntite="taDocument.idDocument", clazz = TaBoncde.class)
	public TaBoncde getTaDocument() {
		return this.taDocument;
	}

	public void setTaDocument(TaBoncde taBoncde) {
		this.taDocument = taBoncde;
	}
	
	public void setTaDocumentGeneral(IDocumentTiers taDocument) {
		this.taDocument = (TaBoncde) taDocument;
	}

	@Column(name = "num_ligne_l_document")
	@LgrHibernateValidated(champBd = "num_ligne_l_document",table = "ta_l_boncde", champEntite="numLigneLDocument", clazz = TaLBoncde.class)
	public Integer getNumLigneLDocument() {
		return this.numLigneLDocument;
	}

	public void setNumLigneLDocument(Integer numLigneLBoncde) {
		this.numLigneLDocument = numLigneLBoncde;
	}

	@Column(name = "lib_l_document")
	@LgrHibernateValidated(champBd = "lib_l_document",table = "ta_l_boncde", champEntite="libLDocument", clazz = TaLBoncde.class)
	public String getLibLDocument() {
		return this.libLDocument;
	}

	public void setLibLDocument(String libLBoncde) {
		this.libLDocument = libLBoncde;
	}

	@Column(name = "qte_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte_l_document",table = "ta_l_boncde", champEntite="qteLDocument", clazz = TaLBoncde.class)
	public BigDecimal getQteLDocument() {
		return this.qteLDocument;
	}

	public void setQteLDocument(BigDecimal qteLBoncde) {		
		BigDecimal qteLDocumentOld = this.qteLDocument;
		this.qteLDocument = qteLBoncde;
		if(legrain) {
			calculMontant();
			firePropertyChange(new PropertyChangeEvent(this,"qteLDocument",qteLDocumentOld,qteLBoncde));
		}
	}

	@Column(name = "qte2_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte2_l_document",table = "ta_l_boncde", champEntite="qte2LDocument", clazz = TaLBoncde.class)
	public BigDecimal getQte2LDocument() {
		return this.qte2LDocument;
	}

	public void setQte2LDocument(BigDecimal qte2LBoncde) {
		this.qte2LDocument = qte2LBoncde;
	}

	@Column(name = "u1_l_document", length = 20)
	@LgrHibernateValidated(champBd = "u1_l_document",table = "ta_l_boncde", champEntite="u1LDocument", clazz = TaLBoncde.class)
	public String getU1LDocument() {
		return this.u1LDocument;
	}

	public void setU1LDocument(String u1LBoncde) {
		this.u1LDocument = u1LBoncde;
	}

	@Column(name = "u2_l_document", length = 20)
	@LgrHibernateValidated(champBd = "u2_l_document",table = "ta_l_boncde", champEntite="u2LDocument", clazz = TaLBoncde.class)
	public String getU2LDocument() {
		return this.u2LDocument;
	}

	public void setU2LDocument(String u2LBoncde) {
		this.u2LDocument = u2LBoncde;
	}

	@Column(name = "prix_u_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "prix_u_l_document",table = "ta_l_boncde", champEntite="prixULDocument", clazz = TaLBoncde.class)
	public BigDecimal getPrixULDocument() {
		return this.prixULDocument;
	}

	public void setPrixULDocument(BigDecimal prixULBoncde) {
		this.prixULDocument = prixULBoncde;
		if(legrain) {
			calculMontant();
		}
	}

	@Column(name = "taux_tva_l_document", precision = 15, scale = 4)
	@LgrHibernateValidated(champBd = "taux_tva_l_document",table = "ta_l_boncde", champEntite="tauxTvaLDocument", clazz = TaLBoncde.class)
	public BigDecimal getTauxTvaLDocument() {
		return this.tauxTvaLDocument;
	}

	public void setTauxTvaLDocument(BigDecimal tauxTvaLBoncde) {
		this.tauxTvaLDocument = tauxTvaLBoncde;
	}

	@Column(name = "compte_l_document", length = 8)
	@LgrHibernateValidated(champBd = "compte_l_document",table = "ta_l_boncde", champEntite="compteLDocument", clazz = TaLBoncde.class)
	public String getCompteLDocument() {
		return this.compteLDocument;
	}

	public void setCompteLDocument(String compteLBoncde) {
		this.compteLDocument = compteLBoncde;
	}

	@Column(name = "code_tva_l_document", length = 20)
	@LgrHibernateValidated(champBd = "code_tva_l_document",table = "ta_l_boncde", champEntite="codeTvaLDocument", clazz = TaLBoncde.class)
	public String getCodeTvaLDocument() {
		return this.codeTvaLDocument;
	}

	public void setCodeTvaLDocument(String codeTvaLBoncde) {
		this.codeTvaLDocument = codeTvaLBoncde;
	}

	@Column(name = "code_t_tva_l_document", length = 1)
	@LgrHibernateValidated(champBd = "code_t_tva_l_document",table = "ta_l_boncde", champEntite="codeTTvaLDocument", clazz = TaLBoncde.class)
	public String getCodeTTvaLDocument() {
		return this.codeTTvaLDocument;
	}

	public void setCodeTTvaLDocument(String codeTTvaLBoncde) {
		this.codeTTvaLDocument = codeTTvaLBoncde;
	}

	@Column(name = "mt_ht_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "mt_ht_l_document",table = "ta_l_boncde", champEntite="mtHtLDocument", clazz = TaLBoncde.class)
	public BigDecimal getMtHtLDocument() {
		return this.mtHtLDocument;
	}

	public void setMtHtLDocument(BigDecimal mtHtLBoncde) {
		this.mtHtLDocument = LibCalcul.arrondi(mtHtLBoncde);
	}

	@Column(name = "mt_ttc_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "mt_ttc_l_document",table = "ta_l_boncde", champEntite="mtTtcLDocument", clazz = TaLBoncde.class)
	public BigDecimal getMtTtcLDocument() {
		return this.mtTtcLDocument;
	}

	public void setMtTtcLDocument(BigDecimal mtTtcLBoncde) {
		this.mtTtcLDocument = LibCalcul.arrondi(mtTtcLBoncde);
	}

	@Column(name = "rem_tx_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "rem_tx_l_document",table = "ta_l_boncde", champEntite="remTxLDocument", clazz = TaLBoncde.class)
	public BigDecimal getRemTxLDocument() {
		return this.remTxLDocument;
	}

	public void setRemTxLDocument(BigDecimal remTxLBoncde) {
		this.remTxLDocument = remTxLBoncde;
		if(legrain) {
			//calculMontant();
			
			System.out.println("===****=== Transfert de code metier des entites vers les services, à bien vérifier");
			//passage ejb => dans TaXXXService
//			taDocument.calculeTvaEtTotaux();
		}
		
	}

	@Column(name = "rem_ht_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "rem_ht_l_document",table = "ta_l_boncde", champEntite="remHtLDocument", clazz = TaLBoncde.class)
	public BigDecimal getRemHtLDocument() {
		return this.remHtLDocument;
	}

	public void setRemHtLDocument(BigDecimal remHtLBoncde) {
		this.remHtLDocument = remHtLBoncde;
	}

	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeLBoncde) {
		this.quiCree = quiCreeLBoncde;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeLBoncde) {
		this.quandCree = quandCreeLBoncde;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifLBoncde) {
		this.quiModif = quiModifLBoncde;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifLBoncde) {
		this.quandModif = quandModifLBoncde;
	}

	@Column(name = "ip_acces", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

//	@Version
//	@Column(name = "version_obj")
//	public Integer getVersionObj() {
//		return this.versionObj;
//	}
//
//	public void setVersionObj(Integer versionObj) {
//		this.versionObj = versionObj;
//	}

//	@Override
//	public boolean ligneEstVide() {
//		if (taArticle!=null)return false;
//		if (libLDocument!=null && !libLDocument.equals(""))return false;		
//		return true;
//	}
	
	public void initialiseLigne(boolean reset){
		if (reset){
			setLibLDocument("");
			setQteLDocument(null);
			setQte2LDocument(null);
			setU1LDocument("") ;
			setU2LDocument("") ;
			setPrixULDocument(null);
			setTauxTvaLDocument(null);
			setCodeTvaLDocument("");
			setCodeTTvaLDocument("");
			setMtHtLDocument(null);
			setMtTtcLDocument(null);
			setRemTxLDocument(null);
			setRemHtLDocument(null);
		}else {
			setLibLDocument("");
			setQteLDocument(new BigDecimal(0));
			setQte2LDocument(new BigDecimal(0));
			setU1LDocument("");
			setU2LDocument("");
			setPrixULDocument(new BigDecimal(0)) ;
			setTauxTvaLDocument(new BigDecimal(0));
			setCodeTvaLDocument("");
			setCodeTTvaLDocument("");
			setMtHtLDocument(new BigDecimal(0));
			setMtTtcLDocument(new BigDecimal(0));
			setRemTxLDocument(new BigDecimal(0));
			setRemHtLDocument(new BigDecimal(0)) ;
		}
		setTaArticle(null);	
	}

	public void calculMontant() {
		if(qteLDocument!=null && prixULDocument!=null) {
			if(taDocument!=null) {//#JPA
				setTxRemHtDocument(taDocument.getTxRemHtDocument());
				prixULDocument=prixULDocument.setScale(taDocument.getNbDecimalesPrix(),RoundingMode.DOWN);
				if(taDocument.getTtc()==1) {
					setMtTtcLDocument(qteLDocument.multiply(prixULDocument));
					if(remTxLDocument!=null && remTxLDocument.signum()!=0){
						setMtTtcLDocument(getMtTtcLDocument().subtract(getMtTtcLDocument().multiply(remTxLDocument.divide(new BigDecimal(100)))));
					}
					if( tauxTvaLDocument!=null && tauxTvaLDocument.signum()!=0){
						setMtHtLDocument(mtTtcLDocument.divide(BigDecimal.valueOf(1).add((tauxTvaLDocument.divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(3,BigDecimal.ROUND_HALF_UP))),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP));
					} else {
						setMtHtLDocument(mtTtcLDocument);
					}
					
				} else {
					setMtHtLDocument(qteLDocument.multiply(prixULDocument));
					if(remTxLDocument!=null && remTxLDocument.signum()!=0){
						setMtHtLDocument(getMtHtLDocument().subtract(getMtHtLDocument().multiply(remTxLDocument.divide(new BigDecimal(100)))));
					}
					if( tauxTvaLDocument!=null && tauxTvaLDocument.signum()!=0){
						setMtTtcLDocument(mtHtLDocument.add((mtHtLDocument.multiply(tauxTvaLDocument.divide(new BigDecimal(100))))));
					} else {
						setMtTtcLDocument(mtHtLDocument);
					}
				
				}
				setMtTtcLApresRemiseGlobaleDocument(getMtTtcLDocument());
				setMtHtLApresRemiseGlobaleDocument(getMtHtLDocument());
			}
		}
	}
	
	@Transient
	public BigDecimal getTxRemHtDocument() {
		return txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal txRemHtBoncde) {
		this.txRemHtDocument = txRemHtBoncde;
	}

	//@Transient
	@Column(name = "mt_ht_apr_rem_globale", precision = 15)
	@LgrHibernateValidated(champBd = "mt_apr_rem_globale",table = "ta_l_boncde", champEntite="mtHtLApresRemiseGlobaleDocument", clazz = TaLBoncde.class)
	public BigDecimal getMtHtLApresRemiseGlobaleDocument() {
		return mtHtLApresRemiseGlobaleDocument;
	}

	public void setMtHtLApresRemiseGlobaleDocument(
			BigDecimal mtHtLApresRemiseGlobaleDocument) {
		this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument;
	}

	//@Transient
	@Column(name = "mt_ttc_apr_rem_globale", precision = 15)
	@LgrHibernateValidated(champBd = "mt_ttc_apr_rem_globale",table = "ta_l_boncde", champEntite="mtTtcLApresRemiseGlobaleDocument", clazz = TaLBoncde.class)
	public BigDecimal getMtTtcLApresRemiseGlobaleDocument() {
		return mtTtcLApresRemiseGlobaleDocument;
	}

	public void setMtTtcLApresRemiseGlobaleDocument(
			BigDecimal mtTtcLApresRemiseGlobaleDocument) {
		this.mtTtcLApresRemiseGlobaleDocument = mtTtcLApresRemiseGlobaleDocument;
	}

	public void setLegrain(boolean legrain) {
		this.legrain = legrain;
	}

	@Column(name = "lib_tva_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "lib_tva_l_document",table = "ta_l_devis", champEntite="libTvaLDocument", clazz = TaLDevis.class)
	public String getLibTvaLDocument() {
		return this.libTvaLDocument;
	}
	public void setLibTvaLDocument(String libTvaLDocument) {
		this.libTvaLDocument = libTvaLDocument;
	}

	
	public TaLBoncde clone() {
		TaLBoncde ligne = new TaLBoncde();
		try {
			//ligne = (TaLFacture)super.clone();
			ligne.setIdLDocument(0);
			ligne.setVersion(version);
			ligne.setTaTLigne(taTLigne);
			ligne.setTaDocument(taDocument);
			ligne.setTaArticle(taArticle);
			ligne.setNumLigneLDocument(numLigneLDocument);
			ligne.setLibLDocument(libLDocument);
			ligne.setQteLDocument(qteLDocument);
			ligne.setQte2LDocument(qte2LDocument);
			ligne.setU1LDocument(u1LDocument);
			ligne.setU2LDocument(u2LDocument);
			ligne.setPrixULDocument(prixULDocument);
			ligne.setTauxTvaLDocument(tauxTvaLDocument);
			ligne.setTxRemHtDocument(txRemHtDocument);
			ligne.setCompteLDocument(compteLDocument);
			ligne.setCodeTvaLDocument(codeTvaLDocument);
			ligne.setCodeTTvaLDocument(codeTTvaLDocument);
			ligne.setMtHtLDocument(mtHtLDocument);
			ligne.setMtTtcLDocument(mtTtcLDocument);
			ligne.setMtHtLApresRemiseGlobaleDocument(mtHtLApresRemiseGlobaleDocument);
			ligne.setMtTtcLApresRemiseGlobaleDocument(mtTtcLApresRemiseGlobaleDocument);
			ligne.setRemTxLDocument(remTxLDocument);
			ligne.setRemHtLDocument(remHtLDocument);
			ligne.setQuiCree(quiCree);
			ligne.setQuandCree(quandCree);
			ligne.setQuiModif(quiModif);
			ligne.setQuandModif(quandModif);
			ligne.setIpAcces(ipAcces);
			ligne.setVersionObj(versionObj);			
		} catch(Exception cnse) {
			logger.error("",cnse);
		}
		// on renvoie le clone
		return ligne;
	}

	@Transient
	public TaLot getTaLot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setTaLot(TaLot taLot) {
		// TODO Auto-generated method stub
		
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taLBoncde", orphanRemoval=true)
	@XmlTransient
	@Fetch(FetchMode.SUBSELECT)
	public Set<TaLigneALigne> getTaLigneALignes() {
		return this.taLigneALignes;
	}

	public void setTaLigneALignes(Set<TaLigneALigne> taLigneALignes) {
		this.taLigneALignes = taLigneALignes;
	}


	@Transient
	public List<TaLigneALigneSupplementDTO> getListeSupplement() {
		// TODO Auto-generated method stub
		return listeSupplement;
	}

	@Transient
	public void setListeSupplement(List<TaLigneALigneSupplementDTO> listeSupplement) {
		// TODO Auto-generated method stub
		this.listeSupplement=listeSupplement;
	}


	@Transient
	public List<TaLigneALigneSupplementDTO> getListeLigneAIntegrer() {
		// TODO Auto-generated method stub
		return listeLigneAIntegrer;
	}

	@Transient
	public void setListeLigneAIntegrer(List<TaLigneALigneSupplementDTO> listeLigneAIntegrer) {
		// TODO Auto-generated method stub
		this.listeLigneAIntegrer=listeLigneAIntegrer;
	}



	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_prevue", length = 19)
	@LgrHibernateValidated(champBd = "date_prevue",table = "ta_l_boncde_achat",champEntite="datePrevue",clazz = TaLBoncdeAchat.class)
	public Date getDatePrevue() {
		return this.datePrevue;
	}

	public void setDatePrevue(Date datePrevue) {
		this.datePrevue = datePrevue;
	}


	//@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_mouvement_stock_prev")
	public TaMouvementStockPrev getTaMouvementStockPrev() {
		return taMouvementStockPrev;
	}

	public void setTaMouvementStockPrev(TaMouvementStockPrev taMouvementStockPrev) {
		this.taMouvementStockPrev = taMouvementStockPrev;
	}


	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taLBoncde", orphanRemoval = true)
	public Set<TaREtatLigneDocument> getTaREtatLigneDocuments() {
		return this.taREtatLigneDocuments;
	}

	public void setTaREtatLigneDocuments(Set<TaREtatLigneDocument> taREtats) {
		this.taREtatLigneDocuments = taREtats;
	}
	

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taLBoncde", orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	public Set<TaHistREtatLigneDocument> getTaHistREtatLigneDocuments() {
		return this.taHistREtatLigneDocuments;
	}

	public void setTaHistREtatLigneDocuments(Set<TaHistREtatLigneDocument> taHistREtatLigneDocuments) {
		this.taHistREtatLigneDocuments = taHistREtatLigneDocuments;
	}
	

	public void removeTaREtatLigneDoc(TaREtatLigneDocument taREtatLigneDocument) {
		taREtatLigneDocument.setTaEtat(null);
		taREtatLigneDocument.setTaLBoncde(null);
		this.getTaREtatLigneDocuments().remove(taREtatLigneDocument);
		if(!this.getTaREtatLigneDocuments().isEmpty())
			this.setTaREtatLigneDocument(this.getTaREtatLigneDocuments().iterator().next());
		else if(this.getTaREtatLigneDocuments().isEmpty()){
			this.setTaREtatLigneDocument(null);
		}
	}


	
	public void addREtatLigneDoc(TaEtat taEtat) {
		TaREtatLigneDocument rEtat=getTaREtatLigneDocument();
		if(rEtat!=null) {
			TaHistREtatLigneDocument hist=new TaHistREtatLigneDocument();
			hist.setTaEtat(getTaREtatLigneDocument().getTaEtat());
			hist.setTaLBoncde(this);
			this.getTaHistREtatLigneDocuments().add(hist);
		}else rEtat=new TaREtatLigneDocument();
		
		rEtat.setTaEtat(taEtat);
		rEtat.setTaLBoncde(this);
		this.setTaREtatLigneDocument(rEtat);
		this.getTaREtatLigneDocuments().add(rEtat);
	}
	
	@Column(name = "abonnement")
	public Boolean getAbonnement() {
		return abonnement;
	}
	
	public void setAbonnement(Boolean abonnement) {
		this.abonnement = abonnement;
	}

	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taLBoncde", orphanRemoval=true)
	@XmlTransient
	@Fetch(FetchMode.SUBSELECT)	
	public Set<TaLigneALigneEcheance> getTaLigneALignesEcheance() {
		return taLigneALignesEcheance;
	}

	public void setTaLigneALignesEcheance(Set<TaLigneALigneEcheance> taLigneALignesEcheance) {
		this.taLigneALignesEcheance = taLigneALignesEcheance;
	}

    @Transient
    public void setTaEntrepot(TaEntrepot findById) {
        // TODO Auto-generated method stub
        
    }

 

    @Transient
    public void setEmplacementLDocument(String emplacement) {
        // TODO Auto-generated method stub
        
    }

 

    @Transient
    public TaEntrepot getTaEntrepot() {
        // TODO Auto-generated method stub
        return null;
    }

 

    @Transient
    public String getEmplacementLDocument() {
        // TODO Auto-generated method stub
        return null;
    }

	

	@Column(name = "u_saisie_l_document", length = 20)
	@LgrHibernateValidated(champBd = "u_saisie_l_document",table = "ta_l_boncde", champEntite="uSaisieLDocument", clazz = TaLBoncde.class)
	public String getUSaisieLDocument() {
		return uSaisieLDocument;
	}

	public void setUSaisieLDocument(String uSaisieLDocument) {
		this.uSaisieLDocument = uSaisieLDocument;
	}

	@Column(name = "qte_saisie_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte_saisie_l_document",table = "ta_l_boncde", champEntite="qteSaisieLDocument", clazz = TaLBoncde.class)
	public BigDecimal getQteSaisieLDocument() {
		return qteSaisieLDocument;
	}

	public void setQteSaisieLDocument(BigDecimal qteSaisieLDocument) {
		this.qteSaisieLDocument = qteSaisieLDocument;
	}

	
	
	@Column(name = "titre_transport_l_document", length = 20)
	@LgrHibernateValidated(champBd = "titre_transport_l_document",table = "ta_l_boncde", champEntite="codeTitreTransport", clazz = TaLBoncde.class)
	public String getCodeTitreTransport() {
		return this.codeTitreTransport;
	}

	public void setCodeTitreTransport(String titreTransport) {
		this.codeTitreTransport = titreTransport;
	}
	
	@Column(name = "qte_titre_transport_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte_titre_transport_l_document",table = "ta_l_boncde", champEntite="qteTitreTransport", clazz = TaLBoncde.class)
	public BigDecimal getQteTitreTransport() {
		return this.qteTitreTransport;
	}

	public void setQteTitreTransport(BigDecimal qteTitreTransport) {
		this.qteTitreTransport = qteTitreTransport;
	}

}


