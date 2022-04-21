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
 * TaTLigne generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_T_LIGNE", uniqueConstraints = @UniqueConstraint(columnNames = "CODE_T_LIGNE"))
@SequenceGenerator(name = "GEN_T_LIGNE", sequenceName = "NUM_ID_T_LIGNE", allocationSize = 1)
public class TaTLigne implements java.io.Serializable {

	private int idTLigne;
	private String version;
	private String codeTLigne;
	private String libTLigne;
	private String quiCreeTLigne;
	private Date quandCreeTLigne;
	private String quiModifTLigne;
	private Date quandModifTLigne;
	private String ipAcces;
	private Integer versionObj;
	private Set<TaLAvoir> taLAvoirs = new HashSet<TaLAvoir>(0);
//	private Set<TaLProformaTemp> taLProformaTemps = new HashSet<TaLProformaTemp>(
//			0);
//	private Set<TaLFactureTemp> taLFactureTemps = new HashSet<TaLFactureTemp>(0);
//	private Set<TaLApporteurTemp> taLApporteurTemps = new HashSet<TaLApporteurTemp>(
//			0);
//	private Set<TaLBoncdeTemp> taLBoncdeTemps = new HashSet<TaLBoncdeTemp>(0);
	private Set<TaLApporteur> taLApporteurs = new HashSet<TaLApporteur>(0);
	private Set<TaLBoncde> taLBoncdes = new HashSet<TaLBoncde>(0);
	private Set<TaLProforma> taLProformas = new HashSet<TaLProforma>(0);
	private Set<TaLDevis> taLDevises = new HashSet<TaLDevis>(0);
//	private Set<TaLBonlivTemp> taLBonlivTemps = new HashSet<TaLBonlivTemp>(0);
//	private Set<TaLDevisTemp> taLDevisTemps = new HashSet<TaLDevisTemp>(0);
//	private Set<TaLAvoirTemp> taLAvoirTemps = new HashSet<TaLAvoirTemp>(0);
	private Set<TaLBonliv> taLBonlivs = new HashSet<TaLBonliv>(0);
	private Set<TaLFacture> taLFactures = new HashSet<TaLFacture>(0);

	public TaTLigne() {
	}

	public TaTLigne(int idTLigne) {
		this.idTLigne = idTLigne;
	}

	public TaTLigne(int idTLigne, String codeTLigne, String libTLigne,
			String quiCreeTLigne, Date quandCreeTLigne, String quiModifTLigne,
			Date quandModifTLigne, String ipAcces, Integer versionObj,
			Set<TaLAvoir> taLAvoirs, 
//			Set<TaLProformaTemp> taLProformaTemps,
//			Set<TaLFactureTemp> taLFactureTemps,
//			Set<TaLApporteurTemp> taLApporteurTemps,
//			Set<TaLBoncdeTemp> taLBoncdeTemps, 
			Set<TaLApporteur> taLApporteurs,
			Set<TaLBoncde> taLBoncdes, Set<TaLProforma> taLProformas,
			Set<TaLDevis> taLDevises, 
//			Set<TaLBonlivTemp> taLBonlivTemps,
//			Set<TaLDevisTemp> taLDevisTemps, Set<TaLAvoirTemp> taLAvoirTemps,
			Set<TaLBonliv> taLBonlivs, Set<TaLFacture> taLFactures) {
		this.idTLigne = idTLigne;
		this.codeTLigne = codeTLigne;
		this.libTLigne = libTLigne;
		this.quiCreeTLigne = quiCreeTLigne;
		this.quandCreeTLigne = quandCreeTLigne;
		this.quiModifTLigne = quiModifTLigne;
		this.quandModifTLigne = quandModifTLigne;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.taLAvoirs = taLAvoirs;
//		this.taLProformaTemps = taLProformaTemps;
//		this.taLFactureTemps = taLFactureTemps;
//		this.taLApporteurTemps = taLApporteurTemps;
//		this.taLBoncdeTemps = taLBoncdeTemps;
		this.taLApporteurs = taLApporteurs;
		this.taLBoncdes = taLBoncdes;
		this.taLProformas = taLProformas;
		this.taLDevises = taLDevises;
//		this.taLBonlivTemps = taLBonlivTemps;
//		this.taLDevisTemps = taLDevisTemps;
//		this.taLAvoirTemps = taLAvoirTemps;
		this.taLBonlivs = taLBonlivs;
		this.taLFactures = taLFactures;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_T_LIGNE")
	@Column(name = "ID_T_LIGNE", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_LIGNE",table = "TA_T_LIGNE",clazz = TaTLigne.class)
	public int getIdTLigne() {
		return this.idTLigne;
	}

	public void setIdTLigne(int idTLigne) {
		this.idTLigne = idTLigne;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "CODE_T_LIGNE", unique = true, length = 50)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_LIGNE",table = "TA_T_LIGNE",clazz = TaTLigne.class)
	public String getCodeTLigne() {
		return this.codeTLigne;
	}

	public void setCodeTLigne(String codeTLigne) {
		this.codeTLigne = codeTLigne;
	}

	@Column(name = "LIB_T_LIGNE")
	@LgrHibernateValidated(champEntite = "", champBd = "LIB_T_LIGNE",table = "TA_T_LIGNE",clazz = TaTLigne.class)
	public String getLibTLigne() {
		return this.libTLigne;
	}

	public void setLibTLigne(String libTLigne) {
		this.libTLigne = libTLigne;
	}

	@Column(name = "QUI_CREE_T_LIGNE", length = 50)
	public String getQuiCreeTLigne() {
		return this.quiCreeTLigne;
	}

	public void setQuiCreeTLigne(String quiCreeTLigne) {
		this.quiCreeTLigne = quiCreeTLigne;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_T_LIGNE", length = 19)
	public Date getQuandCreeTLigne() {
		return this.quandCreeTLigne;
	}

	public void setQuandCreeTLigne(Date quandCreeTLigne) {
		this.quandCreeTLigne = quandCreeTLigne;
	}

	@Column(name = "QUI_MODIF_T_LIGNE", length = 50)
	public String getQuiModifTLigne() {
		return this.quiModifTLigne;
	}

	public void setQuiModifTLigne(String quiModifTLigne) {
		this.quiModifTLigne = quiModifTLigne;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_T_LIGNE", length = 19)
	public Date getQuandModifTLigne() {
		return this.quandModifTLigne;
	}

	public void setQuandModifTLigne(Date quandModifTLigne) {
		this.quandModifTLigne = quandModifTLigne;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLAvoir> getTaLAvoirs() {
		return this.taLAvoirs;
	}

	public void setTaLAvoirs(Set<TaLAvoir> taLAvoirs) {
		this.taLAvoirs = taLAvoirs;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLProformaTemp> getTaLProformaTemps() {
//		return this.taLProformaTemps;
//	}
//
//	public void setTaLProformaTemps(Set<TaLProformaTemp> taLProformaTemps) {
//		this.taLProformaTemps = taLProformaTemps;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLFactureTemp> getTaLFactureTemps() {
//		return this.taLFactureTemps;
//	}
//
//	public void setTaLFactureTemps(Set<TaLFactureTemp> taLFactureTemps) {
//		this.taLFactureTemps = taLFactureTemps;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLApporteurTemp> getTaLApporteurTemps() {
//		return this.taLApporteurTemps;
//	}
//
//	public void setTaLApporteurTemps(Set<TaLApporteurTemp> taLApporteurTemps) {
//		this.taLApporteurTemps = taLApporteurTemps;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLBoncdeTemp> getTaLBoncdeTemps() {
//		return this.taLBoncdeTemps;
//	}
//
//	public void setTaLBoncdeTemps(Set<TaLBoncdeTemp> taLBoncdeTemps) {
//		this.taLBoncdeTemps = taLBoncdeTemps;
//	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLApporteur> getTaLApporteurs() {
		return this.taLApporteurs;
	}

	public void setTaLApporteurs(Set<TaLApporteur> taLApporteurs) {
		this.taLApporteurs = taLApporteurs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLBoncde> getTaLBoncdes() {
		return this.taLBoncdes;
	}

	public void setTaLBoncdes(Set<TaLBoncde> taLBoncdes) {
		this.taLBoncdes = taLBoncdes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLProforma> getTaLProformas() {
		return this.taLProformas;
	}

	public void setTaLProformas(Set<TaLProforma> taLProformas) {
		this.taLProformas = taLProformas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLDevis> getTaLDevises() {
		return this.taLDevises;
	}

	public void setTaLDevises(Set<TaLDevis> taLDevises) {
		this.taLDevises = taLDevises;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLBonlivTemp> getTaLBonlivTemps() {
//		return this.taLBonlivTemps;
//	}
//
//	public void setTaLBonlivTemps(Set<TaLBonlivTemp> taLBonlivTemps) {
//		this.taLBonlivTemps = taLBonlivTemps;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLDevisTemp> getTaLDevisTemps() {
//		return this.taLDevisTemps;
//	}
//
//	public void setTaLDevisTemps(Set<TaLDevisTemp> taLDevisTemps) {
//		this.taLDevisTemps = taLDevisTemps;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
//	public Set<TaLAvoirTemp> getTaLAvoirTemps() {
//		return this.taLAvoirTemps;
//	}
//
//	public void setTaLAvoirTemps(Set<TaLAvoirTemp> taLAvoirTemps) {
//		this.taLAvoirTemps = taLAvoirTemps;
//	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLBonliv> getTaLBonlivs() {
		return this.taLBonlivs;
	}

	public void setTaLBonlivs(Set<TaLBonliv> taLBonlivs) {
		this.taLBonlivs = taLBonlivs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTLigne")
	public Set<TaLFacture> getTaLFactures() {
		return this.taLFactures;
	}

	public void setTaLFactures(Set<TaLFacture> taLFactures) {
		this.taLFactures = taLFactures;
	}

}
