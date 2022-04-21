package fr.legrain.dossierIntelligent.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

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
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaAdresse generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_param_dossier_intelligent")
//@SequenceGenerator(name = "gen_param_dossier_intelligent", sequenceName = "num_id_param_doss_intel", allocationSize = 1)
public class TaParamDossierIntel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8812043882686009027L;
	
	
	private int id;
	private String mot;
	private String sql;
	private int nbZones;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private String version;
	private Set<TaRParamDossierIntel> taRParamDossierIntel = new HashSet<TaRParamDossierIntel>(0);

	public TaParamDossierIntel() {
	}

	public TaParamDossierIntel(int id) {
		this.id = id;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id",table = "ta_param_dossier_intelligent", champEntite="id", clazz = TaParamDossierIntel.class)
	public int getId() {
		return this.id;
	}

	public void setId(int idTiersPoint) {
		this.id = idTiersPoint;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}



	@Column(name = "mot", length = 50)
	@LgrHibernateValidated(champBd = "mot",table = "ta_param_dossier_intelligent", champEntite="mot", clazz = TaParamDossierIntel.class)
	public String getMot() {
		return this.mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	@Column(name = "sql", length = 50)
	@LgrHibernateValidated(champBd = "sql",table = "ta_param_dossier_intelligent", champEntite="sql", clazz = TaParamDossierIntel.class)
	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Column(name = "nb_zones" )
	@LgrHibernateValidated(champBd = "nb_zones",table = "ta_param_dossier_intelligent", champEntite="nbZones", clazz = TaParamDossierIntel.class)
	public int getNbZones() {
		return this.nbZones;
	}

	public void setNbZones(int nbZones) {
		this.nbZones = nbZones;
	}
	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCree) {
		this.quiCree = quiCree;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCree) {
		this.quandCree = quandCree;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModif) {
		this.quiModif = quiModif;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModif) {
		this.quandModif = quandModif;
	}

	@Column(name = "ip_acces", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@Version
	@Column(name = "version_obj")
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taParamDossierIntel")
	public Set<TaRParamDossierIntel> getTaRParamDossierIntel() {
		return this.taRParamDossierIntel;
	}

	public void setTaRParamDossierIntel(Set<TaRParamDossierIntel> taRParamDossierIntel) {
		this.taRParamDossierIntel = taRParamDossierIntel;
	}

}