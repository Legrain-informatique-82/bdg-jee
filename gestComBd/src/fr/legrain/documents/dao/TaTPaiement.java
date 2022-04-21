package fr.legrain.documents.dao;

// Generated Apr 7, 2009 3:27:23 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTPaiement generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_T_PAIEMENT", uniqueConstraints = @UniqueConstraint(columnNames = "CODE_T_PAIEMENT"))
@SequenceGenerator(name = "GEN_T_PAIEMENT", sequenceName = "NUM_ID_T_PAIEMENT", allocationSize = 1)
public class TaTPaiement implements java.io.Serializable {

	private int idTPaiement;
	private String version;
	private String codeTPaiement;
	private String libTPaiement;
	private String compte;
	private Integer reportTPaiement;
	private Integer finMoisTPaiement;
	private String quiCreeTPaiement;
	private Date quandCreeTPaiement;
	private String quiModifTPaiement;
	private Date quandModifTPaiement;
	private String ipAcces;
	private Integer versionObj;
	//private Set<TaFacture> taFactures = new HashSet<TaFacture>(0);
	private Set<TaAvoir> taAvoirs = new HashSet<TaAvoir>(0);
	private Set<TaBonliv> taBonlivs = new HashSet<TaBonliv>(0);
	private Set<TaBoncde> taBoncdes = new HashSet<TaBoncde>(0);
	private Set<TaApporteur> taApporteurs = new HashSet<TaApporteur>(0);
	private Set<TaProforma> taProformas = new HashSet<TaProforma>(0);

	public TaTPaiement() {
	}

	public TaTPaiement(int idTPaiement) {
		this.idTPaiement = idTPaiement;
	}

	public TaTPaiement(int idTPaiement, String codeTPaiement,
			String libTPaiement, String quiCreeTPaiement,
			Date quandCreeTPaiement, String quiModifTPaiement,
			Date quandModifTPaiement, String ipAcces, Integer versionObj,
			Set<TaFacture> taFactures, Set<TaAvoir> taAvoirs,
			Set<TaBonliv> taBonlivs, Set<TaBoncde> taBoncdes,
			Set<TaApporteur> taApporteurs, Set<TaProforma> taProformas) {
		this.idTPaiement = idTPaiement;
		this.codeTPaiement = codeTPaiement;
		this.libTPaiement = libTPaiement;
		this.quiCreeTPaiement = quiCreeTPaiement;
		this.quandCreeTPaiement = quandCreeTPaiement;
		this.quiModifTPaiement = quiModifTPaiement;
		this.quandModifTPaiement = quandModifTPaiement;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		//this.taFactures = taFactures;
		this.taAvoirs = taAvoirs;
		this.taBonlivs = taBonlivs;
		this.taBoncdes = taBoncdes;
		this.taApporteurs = taApporteurs;
		this.taProformas = taProformas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_T_PAIEMENT")
	@Column(name = "ID_T_PAIEMENT", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_PAIEMENT",table = "TA_T_PAIEMENT",clazz = TaTPaiement.class)
	public int getIdTPaiement() {
		return this.idTPaiement;
	}

	public void setIdTPaiement(int idTPaiement) {
		this.idTPaiement = idTPaiement;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "CODE_T_PAIEMENT", unique = true, length = 50)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_PAIEMENT",table = "TA_T_PAIEMENT",clazz = TaTPaiement.class)
	public String getCodeTPaiement() {
		return this.codeTPaiement;
	}

	public void setCodeTPaiement(String codeTPaiement) {
		this.codeTPaiement = codeTPaiement;
	}

	@Column(name = "LIB_T_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "LIB_T_PAIEMENT",table = "TA_T_PAIEMENT",clazz = TaTPaiement.class)
	public String getLibTPaiement() {
		return this.libTPaiement;
	}

	public void setLibTPaiement(String libTPaiement) {
		this.libTPaiement = libTPaiement;
	}

	@Column(name = "COMPTE")
	@LgrHibernateValidated(champEntite = "", champBd = "COMPTE",table = "TA_T_PAIEMENT",clazz = TaTPaiement.class)
	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}
	
	
	@Column(name = "REPORT_T_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "REPORT_T_PAIEMENT",table = "TA_T_PAIEMENT",clazz = TaTPaiement.class)
	public Integer getReportTPaiement() {
		return this.reportTPaiement;
	}

	public void setReportTPaiement(Integer reportCPaiement) {
		this.reportTPaiement = reportCPaiement;
	}

	@Column(name = "FIN_MOIS_T_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "FIN_MOIS_T_PAIEMENT",table = "TA_T_PAIEMENT",clazz = TaTPaiement.class)
	public Integer getFinMoisTPaiement() {
		return this.finMoisTPaiement;
	}

	public void setFinMoisTPaiement(Integer finMoisCPaiement) {
		this.finMoisTPaiement = finMoisCPaiement;
	}
	
	
	@Column(name = "QUI_CREE_T_PAIEMENT", length = 50)
	public String getQuiCreeTPaiement() {
		return this.quiCreeTPaiement;
	}

	public void setQuiCreeTPaiement(String quiCreeTPaiement) {
		this.quiCreeTPaiement = quiCreeTPaiement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_T_PAIEMENT", length = 19)
	public Date getQuandCreeTPaiement() {
		return this.quandCreeTPaiement;
	}

	public void setQuandCreeTPaiement(Date quandCreeTPaiement) {
		this.quandCreeTPaiement = quandCreeTPaiement;
	}

	@Column(name = "QUI_MODIF_T_PAIEMENT", length = 50)
	public String getQuiModifTPaiement() {
		return this.quiModifTPaiement;
	}

	public void setQuiModifTPaiement(String quiModifTPaiement) {
		this.quiModifTPaiement = quiModifTPaiement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_T_PAIEMENT", length = 19)
	public Date getQuandModifTPaiement() {
		return this.quandModifTPaiement;
	}

	public void setQuandModifTPaiement(Date quandModifTPaiement) {
		this.quandModifTPaiement = quandModifTPaiement;
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

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaFacture> getTaFactures() {
//		return this.taFactures;
//	}
//
//	public void setTaFactures(Set<TaFacture> taFactures) {
//		this.taFactures = taFactures;
//	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
	public Set<TaAvoir> getTaAvoirs() {
		return this.taAvoirs;
	}

	public void setTaAvoirs(Set<TaAvoir> taAvoirs) {
		this.taAvoirs = taAvoirs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
	public Set<TaBonliv> getTaBonlivs() {
		return this.taBonlivs;
	}

	public void setTaBonlivs(Set<TaBonliv> taBonlivs) {
		this.taBonlivs = taBonlivs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
	public Set<TaBoncde> getTaBoncdes() {
		return this.taBoncdes;
	}

	public void setTaBoncdes(Set<TaBoncde> taBoncdes) {
		this.taBoncdes = taBoncdes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
	public Set<TaApporteur> getTaApporteurs() {
		return this.taApporteurs;
	}

	public void setTaApporteurs(Set<TaApporteur> taApporteurs) {
		this.taApporteurs = taApporteurs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
	public Set<TaProforma> getTaProformas() {
		return this.taProformas;
	}

	public void setTaProformas(Set<TaProforma> taProformas) {
		this.taProformas = taProformas;
	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTPaiement(codeTPaiement.toUpperCase());
	}

}