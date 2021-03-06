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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.legrain.abonnement.dao.TaAbonnement;
import fr.legrain.articles.dao.TaArticle;
import fr.legrain.articles.dao.TaPrix;
import fr.legrain.articles.dao.TaTAbonnement;
import fr.legrain.gestCom.Appli.Const;
import fr.legrain.validator.LgrHibernateValidated;
import fr.legrain.gestCom.Module_Document.IDocumentTiers;
import fr.legrain.gestCom.Module_Document.ILigneDocumentTiers;
import fr.legrain.gestCom.Module_Document.SWTLigneDocument;
import fr.legrain.lib.data.LibCalcul;

/**
 * TaLEcheance generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_L_ECHEANCE")
@SequenceGenerator(name = "GEN_L_ECHEANCE", sequenceName = "NUM_ID_L_ECHEANCE", allocationSize = 1)
public class TaLEcheance  implements java.io.Serializable /*, ILigneDocumentTiers*/ {

	private int idLEcheance;
	private String version;
//	private TaTLigne taTLigne;
	private TaArticle taArticle;
//	private Integer numLigneLDocument;
	private String libLDocument;
	private BigDecimal qteLDocument;
//	private BigDecimal qte2LDocument;
	private String u1LDocument;
//	private String u2LDocument;
	private BigDecimal prixULDocument;
	private BigDecimal tauxTvaLDocument;
//	private String compteLDocument;
//	private String codeTvaLDocument;
//	private String codeTTvaLDocument;
	private BigDecimal mtHtLDocument;
	private BigDecimal mtTtcLDocument;
	private BigDecimal remTxLDocument;
	private BigDecimal remHtLDocument;
	private TaAbonnement taAbonnement;
	private Date debutPeriode;
	private Date finPeriode;
	private Date dateFinCalcul;
	private Date debAbonnement;
	private Date finAbonnement;
	private BigDecimal pourcIndice;
	private BigDecimal pourcPartenaire;
	private String quiCreeLDocument;
	private Date quandCreeLDocument;
	private String quiModifLDocument;
	private Date quandModifLDocument;
	private Integer versionObj;
	private String ipAcces;
	@Transient
	private boolean legrain = false;
//	@Transient
//	private BigDecimal txRemHtDocument;
	@Transient
	static Logger logger = Logger.getLogger(TaLEcheance.class.getName());
	@Transient
	private BigDecimal mtHtLApresRemiseGlobaleDocument;
	@Transient
	private BigDecimal mtTtcLApresRemiseGlobaleDocument;
	@Transient
	private Boolean accepte=true;
	
	public TaLEcheance() {
	}
	
	public TaLEcheance	(boolean legrain) {
		this.legrain = legrain;
	}
	
	public TaLEcheance(int idLEcheance) {
		this.idLEcheance = idLEcheance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_L_ECHEANCE")
	@Column(name = "ID_L_ECHEANCE", unique = true, nullable = false)
	public int getIdLEcheance() {
		return idLEcheance;
	}

	public void setIdLEcheance(int idLEcheance) {
		this.idLEcheance = idLEcheance;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID_T_LIGNE")
//	@LgrHibernateValidated(champEntite = "", champ = "ID_T_LIGNE",table = "TA_T_LIGNE",clazz = TaTLigne.class)
//	public TaTLigne getTaTLigne() {
//		return this.taTLigne;
//	}
//
//	public void setTaTLigne(TaTLigne taTLigne) {
//		this.taTLigne = taTLigne;
//	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_ARTICLE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_ARTICLE",table = "TA_ARTICLE",clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return this.taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}


//	@Column(name = "NUM_LIGNE_L_DOCUMENT")
//	@LgrHibernateValidated(champEntite = "", champ = "NUM_LIGNE_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
//	public Integer getNumLigneLDocument() {
//		return this.numLigneLDocument;
//	}
//
//	public void setNumLigneLDocument(Integer numLigneLEcheance) {
//		this.numLigneLDocument = numLigneLEcheance;
//	}

	@Column(name = "LIB_L_DOCUMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "LIB_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public String getLibLDocument() {
		return this.libLDocument;
	}

	public void setLibLDocument(String libLEcheance) {
		this.libLDocument = libLEcheance;
	}

	@Column(name = "QTE_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "QTE_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getQteLDocument() {
		return this.qteLDocument;
	}

	public void setQteLDocument(BigDecimal qteLEcheance) {		
		BigDecimal qteLDocumentOld = this.qteLDocument;
		this.qteLDocument = qteLEcheance;
	}

//	@Column(name = "QTE2_L_DOCUMENT", precision = 15)
//	@LgrHibernateValidated(champEntite = "", champ = "QTE2_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
//	public BigDecimal getQte2LDocument() {
//		return this.qte2LDocument;
//	}
//
//	public void setQte2LDocument(BigDecimal qte2LEcheance) {
//		this.qte2LDocument = qte2LEcheance;
//	}

	@Column(name = "U1_L_DOCUMENT", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "U1_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public String getU1LDocument() {
		return this.u1LDocument;
	}

	public void setU1LDocument(String u1LEcheance) {
		this.u1LDocument = u1LEcheance;
	}

//	@Column(name = "U2_L_DOCUMENT", length = 20)
//	@LgrHibernateValidated(champEntite = "", champ = "U2_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
//	public String getU2LDocument() {
//		return this.u2LDocument;
//	}
//
//	public void setU2LDocument(String u2LEcheance) {
//		this.u2LDocument = u2LEcheance;
//	}

	@Column(name = "PRIX_U_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "PRIX_U_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getPrixULDocument() {
		return this.prixULDocument;
	}

	public void setPrixULDocument(BigDecimal prixULEcheance) {
		this.prixULDocument = prixULEcheance;
		if(legrain) {
			calculMontant();
		}
	}

	@Column(name = "TAUX_TVA_L_DOCUMENT", precision = 15, scale = 4)
	@LgrHibernateValidated(champEntite = "", champBd = "TAUX_TVA_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getTauxTvaLDocument() {
		return this.tauxTvaLDocument;
	}

	public void setTauxTvaLDocument(BigDecimal tauxTvaLEcheance) {
		this.tauxTvaLDocument = tauxTvaLEcheance;
	}

//	@Column(name = "COMPTE_L_DOCUMENT", length = 8)
//	@LgrHibernateValidated(champEntite = "", champ = "COMPTE_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
//	public String getCompteLDocument() {
//		return this.compteLDocument;
//	}
//
//	public void setCompteLDocument(String compteLEcheance) {
//		this.compteLDocument = compteLEcheance;
//	}
//
//	@Column(name = "CODE_TVA_L_DOCUMENT", length = 20)
//	@LgrHibernateValidated(champEntite = "", champ = "CODE_TVA_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
//	public String getCodeTvaLDocument() {
//		return this.codeTvaLDocument;
//	}
//
//	public void setCodeTvaLDocument(String codeTvaLEcheance) {
//		this.codeTvaLDocument = codeTvaLEcheance;
//	}
//
//	@Column(name = "CODE_T_TVA_L_DOCUMENT", length = 1)
//	@LgrHibernateValidated(champEntite = "", champ = "CODE_T_TVA_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
//	public String getCodeTTvaLDocument() {
//		return this.codeTTvaLDocument;
//	}
//
//	public void setCodeTTvaLDocument(String codeTTvaLEcheance) {
//		this.codeTTvaLDocument = codeTTvaLEcheance;
//	}

	@Column(name = "MT_HT_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_HT_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getMtHtLDocument() {
		return this.mtHtLDocument;
	}

	public void setMtHtLDocument(BigDecimal mtHtLEcheance) {
		this.mtHtLDocument = LibCalcul.arrondi(mtHtLEcheance);
	}

	@Column(name = "MT_TTC_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_TTC_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getMtTtcLDocument() {
		return this.mtTtcLDocument;
	}

	public void setMtTtcLDocument(BigDecimal mtTtcLEcheance) {
		this.mtTtcLDocument = LibCalcul.arrondi(mtTtcLEcheance);
	}

	@Column(name = "REM_TX_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "REM_TX_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getRemTxLDocument() {
		return this.remTxLDocument;
	}

	public void setRemTxLDocument(BigDecimal remTxLEcheance) {
		this.remTxLDocument = remTxLEcheance;
	}

	@Column(name = "REM_HT_L_DOCUMENT", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "REM_HT_L_DOCUMENT",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getRemHtLDocument() {
		return this.remHtLDocument;
	}

	public void setRemHtLDocument(BigDecimal remHtLEcheance) {
		this.remHtLDocument = remHtLEcheance;
	}

	@Column(name = "QUI_CREE_L_DOCUMENT", length = 50)
	public String getQuiCreeLDocument() {
		return this.quiCreeLDocument;
	}

	public void setQuiCreeLDocument(String quiCreeLEcheance) {
		this.quiCreeLDocument = quiCreeLEcheance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_L_DOCUMENT", length = 19)
	public Date getQuandCreeLDocument() {
		return this.quandCreeLDocument;
	}

	public void setQuandCreeLDocument(Date quandCreeLEcheance) {
		this.quandCreeLDocument = quandCreeLEcheance;
	}

	@Column(name = "QUI_MODIF_L_DOCUMENT", length = 50)
	public String getQuiModifLDocument() {
		return this.quiModifLDocument;
	}

	public void setQuiModifLDocument(String quiModifLEcheance) {
		this.quiModifLDocument = quiModifLEcheance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_L_DOCUMENT", length = 19)
	public Date getQuandModifLDocument() {
		return this.quandModifLDocument;
	}

	public void setQuandModifLDocument(Date quandModifLEcheance) {
		this.quandModifLDocument = quandModifLEcheance;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@Version
	@Column(name = "VERSION_OBJ")
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}


	public void initialiseLigne(boolean reset){
		if (reset){
			setLibLDocument("");
			setQteLDocument(null);
//			setQte2LDocument(null);
			setU1LDocument("") ;
//			setU2LDocument("") ;
			setPrixULDocument(null);
			setTauxTvaLDocument(null);
//			setCodeTvaLDocument("");
//			setCodeTTvaLDocument("");
			setMtHtLDocument(null);
			setMtTtcLDocument(null);
			setRemTxLDocument(null);
			setRemHtLDocument(null);
		}else {
			setLibLDocument("");
			setQteLDocument(new BigDecimal(0));
//			setQte2LDocument(new BigDecimal(0));
			setU1LDocument("");
//			setU2LDocument("");
			setPrixULDocument(new BigDecimal(0)) ;
			setTauxTvaLDocument(new BigDecimal(0));
//			setCodeTvaLDocument("");
//			setCodeTTvaLDocument("");
			setMtHtLDocument(new BigDecimal(0));
			setMtTtcLDocument(new BigDecimal(0));
			setRemTxLDocument(new BigDecimal(0));
			setRemHtLDocument(new BigDecimal(0)) ;
		}
//		setTaArticle(null);	
	}

	public void calculMontant() {
		if(qteLDocument!=null && prixULDocument!=null) {
//			if(taDocument!=null) {//#JPA
					setMtHtLDocument(qteLDocument.multiply(prixULDocument));
					if(remTxLDocument!=null && remTxLDocument.signum()!=0){
						setMtHtLDocument(getMtHtLDocument().subtract(getMtHtLDocument().multiply(remTxLDocument.divide(new BigDecimal(100)))));
					}
					if( tauxTvaLDocument!=null && tauxTvaLDocument.signum()!=0){
						setMtTtcLDocument(mtHtLDocument.add((mtHtLDocument.multiply(tauxTvaLDocument.divide(new BigDecimal(100))))));
					} else {
						setMtTtcLDocument(mtHtLDocument);
					}				
				setMtTtcLApresRemiseGlobaleDocument(getMtTtcLDocument());
				setMtHtLApresRemiseGlobaleDocument(getMtHtLDocument());
			}
//		}
	}
	
//	@Transient
//	public BigDecimal getTxRemHtDocument() {
//		return txRemHtDocument;
//	}
//
//	public void setTxRemHtDocument(BigDecimal txRemHtEcheance) {
//		this.txRemHtDocument = txRemHtEcheance;
//	}
	

	//@Transient
	@Column(name = "MT_HT_APR_REM_GLOBALE", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_APR_REM_GLOBALE",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
	public BigDecimal getMtHtLApresRemiseGlobaleDocument() {
		return mtHtLApresRemiseGlobaleDocument;
	}

	public void setMtHtLApresRemiseGlobaleDocument(
			BigDecimal mtHtLApresRemiseGlobaleDocument) {
		this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument;
	}

	//@Transient
	@Column(name = "MT_TTC_APR_REM_GLOBALE", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "MT_TTC_APR_REM_GLOBALE",table = "TA_L_ECHEANCE",clazz = TaLEcheance.class)
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

//	@Override
//	public IDocumentTiers getTaDocument() {
//		// TODO Auto-generated method stub
//		return null;
//	}



	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_ABONNEMENT")
	public TaAbonnement getTaAbonnement() {
		return taAbonnement;
	}

	public void setTaAbonnement(TaAbonnement taAbonnement) {
		this.taAbonnement = taAbonnement;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "DEBUT_PERIODE", length = 19)
	public Date getDebutPeriode() {
		return debutPeriode;
	}

	public void setDebutPeriode(Date debutPeriode) {
		this.debutPeriode = debutPeriode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIN_PERIODE", length = 19)
	public Date getFinPeriode() {
		return finPeriode;
	}

	public void setFinPeriode(Date finPeriode) {
		this.finPeriode = finPeriode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FIN_CALCUL", length = 19)
	public Date getDateFinCalcul() {
		return dateFinCalcul;
	}

	public void setDateFinCalcul(Date dateFinCalcul) {
		this.dateFinCalcul = dateFinCalcul;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEB_ABON", length = 19)
	public Date getDebAbonnement() {
		return debAbonnement;
	}

	public void setDebAbonnement(Date debAbonnement) {
		this.debAbonnement = debAbonnement;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIN_ABON", length = 19)
	public Date getFinAbonnement() {
		return finAbonnement;
	}

	public void setFinAbonnement(Date finAbonnement) {
		this.finAbonnement = finAbonnement;
	}

	@Column(name = "POURC_INDICE")
	public BigDecimal getPourcIndice() {
		return pourcIndice;
	}

	public void setPourcIndice(BigDecimal pourcIndice) {
		this.pourcIndice = pourcIndice;
	}

	@Column(name = "POURC_PARTENAIRE")
	public BigDecimal getPourcPartenaire() {
		return pourcPartenaire;
	}

	public void setPourcPartenaire(BigDecimal pourcPartenaire) {
		this.pourcPartenaire = pourcPartenaire;
	}

	@Transient
	public Boolean getAccepte() {
		return accepte;
	}

	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}
}
