package fr.legrain.documents.dao;

// Generated Apr 9, 2009 2:12:06 PM by Hibernate Tools 3.2.0.CR1

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import fr.legrain.articles.dao.TaArticle;
import fr.legrain.articles.dao.TaPrix;
import fr.legrain.gestCom.Appli.Const;
import fr.legrain.gestCom.Module_Document.ILigneDocumentTiers;
import fr.legrain.gestCom.Module_Document.SWTLigneDocument;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaLBonliv generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_L_BONLIV")
//@SequenceGenerator(name = "GEN_L_BONLIV", sequenceName = "NUM_ID_L_BONLIV", allocationSize = 1)
public class TaLBonliv extends SWTLigneDocument implements java.io.Serializable, ILigneDocumentTiers {

	static Logger logger = Logger.getLogger(TaLBonliv.class.getName());
//	private int idLDocument;
	private String version;
	private TaTLigne taTLigne;
	private TaArticle taArticle;
	private TaBonliv taDocument;
	private Integer numLigneLDocument;
	private String libLDocument;
	private BigDecimal qteLDocument;
	private BigDecimal qte2LDocument;
	private String u1LDocument;
	private String u2LDocument;
	private BigDecimal prixULDocument;
	private BigDecimal tauxTvaLDocument;
	private String compteLDocument;
	private String codeTvaLDocument;
	private String codeTTvaLDocument;
	private BigDecimal mtHtLDocument;
	private BigDecimal mtTtcLDocument;
	private BigDecimal remTxLDocument;
	private BigDecimal remHtLDocument;
	private String quiCreeLDocument;
	private Date quandCreeLDocument;
	private String quiModifLDocument;
	private Date quandModifLDocument;

	private String ipAcces;
	@Transient
	private boolean legrain = false;
	@Transient
	private BigDecimal txRemHtDocument;
	@Transient
	private BigDecimal mtHtLApresRemiseGlobaleDocument;
	@Transient
	private BigDecimal mtTtcLApresRemiseGlobaleDocument;
	@Transient
	private BigDecimal prixU2LDocument;


	public TaLBonliv() {
	}
	
	public TaLBonliv	(boolean legrain) {
		this.legrain = legrain;
	}
	
	public TaLBonliv(int idLBonliv) {
		this.idLDocument = idLBonliv;
	}

	public TaLBonliv(int idLBonliv, TaTLigne taTLigne, TaArticle taArticle,
			TaBonliv taBonliv, Integer numLigneLBonliv, String libLBonliv,
			BigDecimal qteLBonliv, BigDecimal qte2LBonliv, String u1LBonliv,
			String u2LBonliv, BigDecimal prixULBonliv, BigDecimal tauxTvaLBonliv,
			String compteLBonliv, String codeTvaLBonliv, String codeTTvaLBonliv,
			BigDecimal mtHtLBonliv, BigDecimal mtTtcLBonliv,
			BigDecimal remTxLBonliv, BigDecimal remHtLBonliv,
			String quiCreeLBonliv, Date quandCreeLBonliv, String quiModifLBonliv,
			Date quandModifLBonliv, String ipAcces, Integer versionObj) {
		this.idLDocument = idLBonliv;
		this.taTLigne = taTLigne;
		this.taArticle = taArticle;
		this.taDocument = taBonliv;
		this.numLigneLDocument = numLigneLBonliv;
		this.libLDocument = libLBonliv;
		this.qteLDocument = qteLBonliv;
		this.qte2LDocument = qte2LBonliv;
		this.u1LDocument = u1LBonliv;
		this.u2LDocument = u2LBonliv;
		this.prixULDocument = prixULBonliv;
		this.tauxTvaLDocument = tauxTvaLBonliv;
		this.compteLDocument = compteLBonliv;
		this.codeTvaLDocument = codeTvaLBonliv;
		this.codeTTvaLDocument = codeTTvaLBonliv;
		this.mtHtLDocument = mtHtLBonliv;
		this.mtTtcLDocument = mtTtcLBonliv;
		this.remTxLDocument = remTxLBonliv;
		this.remHtLDocument = remHtLBonliv;
		this.quiCreeLDocument = quiCreeLBonliv;
		this.quandCreeLDocument = quandCreeLBonliv;
		this.quiModifLDocument = quiModifLBonliv;
		this.quandModifLDocument = quandModifLBonliv;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_L_BONLIV")
//	@Column(name = "ID_L_DOCUMENT", unique = true, nullable = false)
//	public int getIdLDocument() {
//		return this.idLDocument;
//	}
//
//	public void setIdLDocument(int idLBonliv) {
//		this.idLDocument = idLBonliv;
//	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_T_LIGNE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_LIGNE",table = "TA_T_LIGNE",clazz = TaTLigne.class)
	public TaTLigne getTaTLigne() {
		return this.taTLigne;
	}

	public void setTaTLigne(TaTLigne taTLigne) {
		this.taTLigne = taTLigne;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_ARTICLE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_ARTICLE",table = "TA_ARTICLE",clazz = TaArticle.class)
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
//					if(prix.getTaUnite()!=null) {
//						setU1LDocument(prix.getTaUnite().getCodeUnite());
//					}
//				}
//
//				if(taArticle.getTaTva()!=null && taDocument.isGestionTVA()) {
//					setCodeTvaLDocument(taArticle.getTaTva().getCodeTva());
//					setTauxTvaLDocument(taArticle.getTaTva().getTauxTva());
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
				if(getTaTLigne()==null || getTaTLigne().getCodeTLigne()==null)
					setTaTLigne(new TaTLigneDAO().findByCode(Const.C_CODE_T_LIGNE_H));
				else
					if(!getTaTLigne().getCodeTLigne().equals(Const.C_CODE_T_LIGNE_H))
						setTaTLigne(new TaTLigneDAO().findByCode(Const.C_CODE_T_LIGNE_H));
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

						if(prix.getTaUnite()!=null) {
							setU1LDocument(prix.getTaUnite().getCodeUnite());
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
	@JoinColumn(name = "ID_DOCUMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_DOCUMENT",table = "TA_BONLIV",clazz = TaBonliv.class)
	public TaBonliv getTaDocument() {
		return this.taDocument;
	}

	public void setTaDocument(TaBonliv taBonliv) {
		this.taDocument = taBonliv;
	}

	@Column(name = "NUM_LIGNE_L_DOCUMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "NUM_LIGNE_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public Integer getNumLigneLDocument() {
		return this.numLigneLDocument;
	}

	public void setNumLigneLDocument(Integer numLigneLBonliv) {
		this.numLigneLDocument = numLigneLBonliv;
	}

	@Column(name = "LIB_L_DOCUMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "LIB_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public String getLibLDocument() {
		return this.libLDocument;
	}

	public void setLibLBonliv(String libLBonliv) {
		setLibLDocument(libLBonliv);
	}

	public void setLibLDocument(String libLBonliv) {
		this.libLDocument = libLBonliv;
	}

	@Column(name = "QTE_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "QTE_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getQteLDocument() {
		return this.qteLDocument;
	}

	public void setQteLDocument(BigDecimal qteLBonliv) {		
		BigDecimal qteLDocumentOld = this.qteLDocument;
		this.qteLDocument = qteLBonliv;
		if(legrain) {
			calculMontant();
			firePropertyChange(new PropertyChangeEvent(this,"qteLDocument",qteLDocumentOld,qteLBonliv));
		}
	}

	@Column(name = "QTE2_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "QTE2_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getQte2LDocument() {
		return this.qte2LDocument;
	}

	public void setQte2LDocument(BigDecimal qte2LBonliv) {
		this.qte2LDocument = qte2LBonliv;
	}

	@Column(name = "U1_L_DOCUMENT", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "U1_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public String getU1LDocument() {
		return this.u1LDocument;
	}

	public void setU1LDocument(String u1LBonliv) {
		this.u1LDocument = u1LBonliv;
	}

	@Column(name = "U2_L_DOCUMENT", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "U2_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public String getU2LDocument() {
		return this.u2LDocument;
	}

	public void setU2LDocument(String u2LBonliv) {
		this.u2LDocument = u2LBonliv;
	}

	@Column(name = "PRIX_U_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "PRIX_U_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getPrixULDocument() {
		return this.prixULDocument;
	}

	public void setPrixULDocument(BigDecimal prixULBonliv) {
		this.prixULDocument = prixULBonliv;
		if(legrain) {
			calculMontant();
		}
	}
	
	@Transient
	public BigDecimal getPrixU2LDocument() {
		return prixU2LDocument;
	}
	@Transient
	public void setPrixU2LDocument(BigDecimal prixU2LDocument) {
		this.prixU2LDocument = prixU2LDocument;
	}
	
	@Column(name = "TAUX_TVA_L_DOCUMENT", precision = 15, scale = 4)
	@LgrHibernateValidated(champEntite = "", champBd = "TAUX_TVA_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getTauxTvaLDocument() {
		return this.tauxTvaLDocument;
	}

	public void setTauxTvaLDocument(BigDecimal tauxTvaLBonliv) {
		this.tauxTvaLDocument = tauxTvaLBonliv;
	}

	@Column(name = "COMPTE_L_DOCUMENT", length = 8)
	@LgrHibernateValidated(champEntite = "", champBd = "COMPTE_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public String getCompteLDocument() {
		return this.compteLDocument;
	}

	public void setCompteLDocument(String compteLBonliv) {
		this.compteLDocument = compteLBonliv;
	}

	@Column(name = "CODE_TVA_L_DOCUMENT", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_TVA_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public String getCodeTvaLDocument() {
		return this.codeTvaLDocument;
	}

	public void setCodeTvaLDocument(String codeTvaLBonliv) {
		this.codeTvaLDocument = codeTvaLBonliv;
	}

	@Column(name = "CODE_T_TVA_L_DOCUMENT", length = 1)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_TVA_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public String getCodeTTvaLDocument() {
		return this.codeTTvaLDocument;
	}

	public void setCodeTTvaLDocument(String codeTTvaLBonliv) {
		this.codeTTvaLDocument = codeTTvaLBonliv;
	}

	@Column(name = "MT_HT_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_HT_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getMtHtLDocument() {
		return this.mtHtLDocument;
	}

	public void setMtHtLDocument(BigDecimal mtHtLBonliv) {
		this.mtHtLDocument = LibCalcul.arrondi(mtHtLBonliv);
	}

	@Column(name = "MT_TTC_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_TTC_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getMtTtcLDocument() {
		return this.mtTtcLDocument;
	}

	public void setMtTtcLDocument(BigDecimal mtTtcLBonliv) {
		this.mtTtcLDocument = LibCalcul.arrondi(mtTtcLBonliv);
	}

	@Column(name = "REM_TX_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "REM_TX_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getRemTxLDocument() {
		return this.remTxLDocument;
	}

	public void setRemTxLDocument(BigDecimal remTxLBonliv) {
		this.remTxLDocument = remTxLBonliv;
		if(legrain) {
			//calculMontant();
			taDocument.calculeTvaEtTotaux();
		}
		
	}

	@Column(name = "REM_HT_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "REM_HT_L_DOCUMENT",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getRemHtLDocument() {
		return this.remHtLDocument;
	}

	public void setRemHtLDocument(BigDecimal remHtLBonliv) {
		this.remHtLDocument = remHtLBonliv;
	}

	@Column(name = "QUI_CREE_L_DOCUMENT", length = 50)
	public String getQuiCreeLDocument() {
		return this.quiCreeLDocument;
	}

	public void setQuiCreeLDocument(String quiCreeLBonliv) {
		this.quiCreeLDocument = quiCreeLBonliv;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_L_DOCUMENT", length = 19)
	public Date getQuandCreeLDocument() {
		return this.quandCreeLDocument;
	}

	public void setQuandCreeLDocument(Date quandCreeLBonliv) {
		this.quandCreeLDocument = quandCreeLBonliv;
	}

	@Column(name = "QUI_MODIF_L_DOCUMENT", length = 50)
	public String getQuiModifLDocument() {
		return this.quiModifLDocument;
	}

	public void setQuiModifLDocument(String quiModifLBonliv) {
		this.quiModifLDocument = quiModifLBonliv;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_L_DOCUMENT", length = 19)
	public Date getQuandModifLDocument() {
		return this.quandModifLDocument;
	}

	public void setQuandModifLDocument(Date quandModifLBonliv) {
		this.quandModifLDocument = quandModifLBonliv;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

//	@Version
//	@Column(name = "VERSION_OBJ")
//	public Integer getVersionObj() {
//		return this.versionObj;
//	}
//
//	public void setVersionObj(Integer versionObj) {
//		this.versionObj = versionObj;
//	}

	@Override
	public boolean ligneEstVide() {
		if (taArticle!=null)return false;
		if (!libLDocument.equals(""))return false;		
		return true;
	}
	
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
				if(taDocument.getTtc()==1) {					
					setMtTtcLDocument(qteLDocument.multiply(prixULDocument));
					if(remTxLDocument!=null && remTxLDocument.signum()!=0){
						setMtTtcLDocument(getMtTtcLDocument().subtract(getMtTtcLDocument().multiply(remTxLDocument.divide(new BigDecimal(100)))));
					}
					if( tauxTvaLDocument!=null && tauxTvaLDocument.signum()!=0){
						setMtHtLDocument(mtTtcLDocument.divide(BigDecimal.valueOf(1).add((tauxTvaLDocument.divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(3,BigDecimal.ROUND_HALF_UP)
								)),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP));
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

	public void setTxRemHtDocument(BigDecimal txRemHtBonliv) {
		this.txRemHtDocument = txRemHtBonliv;
	}

	//@Transient
	@Column(name = "MT_HT_APR_REM_GLOBALE", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_APR_REM_GLOBALE",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
	public BigDecimal getMtHtLApresRemiseGlobaleDocument() {
		return mtHtLApresRemiseGlobaleDocument;
	}

	public void setMtHtLApresRemiseGlobaleDocument(
			BigDecimal mtHtLApresRemiseGlobaleDocument) {
		this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument;
	}

	//@Transient
	@Column(name = "MT_TTC_APR_REM_GLOBALE", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_TTC_APR_REM_GLOBALE",table = "TA_L_BONLIV",clazz = TaLBonliv.class)
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

	public TaLBonliv clone() {
		TaLBonliv ligne = new TaLBonliv();
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
			ligne.setQuiCreeLDocument(quiCreeLDocument);
			ligne.setQuandCreeLDocument(quandCreeLDocument);
			ligne.setQuiModifLDocument(quiModifLDocument);
			ligne.setQuandModifLDocument(quandModifLDocument);
			ligne.setIpAcces(ipAcces);
			ligne.setVersionObj(versionObj);			
		} catch(Exception cnse) {
			logger.error("",cnse);
		}
		// on renvoie le clone
		return ligne;
	}

}
