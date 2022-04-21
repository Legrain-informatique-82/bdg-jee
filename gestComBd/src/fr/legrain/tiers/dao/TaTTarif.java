package fr.legrain.tiers.dao;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
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
 * TaTTarif generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_T_TARIF")
@SequenceGenerator(name = "GEN_T_TARIF", sequenceName = "NUM_ID_T_TARIF", allocationSize = 1)
public class TaTTarif implements java.io.Serializable {

	private int idTTarif;
	private String version;
	private String codeTTarif;
	private String liblTTarif;
	private Integer sens;
	private Integer pourcentage;
	private BigDecimal valeur;
	private String quiCreeTTarif;
	private Date quandCreeTTarif;
	private String quiModifTTarif;
	private Date quandModifTTarif;
	private String ipAcces;
	private Integer versionObj;
	private Set<TaTiers> taTierses = new HashSet<TaTiers>(0);

	public TaTTarif() {
	}

	public TaTTarif(int idTTarif) {
		this.idTTarif = idTTarif;
	}

	public TaTTarif(int idTTarif, String codeTTarif, String liblTTarif,
			String quiCreeTTarif, Date quandCreeTTarif, String quiModifTTarif,
			Date quandModifTTarif, String ipAcces, Integer versionObj,
			Set<TaTiers> taTierses) {
		this.idTTarif = idTTarif;
		this.codeTTarif = codeTTarif;
		this.liblTTarif = liblTTarif;
		this.quiCreeTTarif = quiCreeTTarif;
		this.quandCreeTTarif = quandCreeTTarif;
		this.quiModifTTarif = quiModifTTarif;
		this.quandModifTTarif = quandModifTTarif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.taTierses = taTierses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_T_TARIF")
	@Column(name = "ID_T_TARIF", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_TARIF",table = "TA_T_TARIF",clazz = TaTTarif.class)
	public int getIdTTarif() {
		return this.idTTarif;
	}

	public void setIdTTarif(int idTTarif) {
		this.idTTarif = idTTarif;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "CODE_T_TARIF", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_TARIF",table = "TA_T_TARIF",clazz = TaTTarif.class)
	public String getCodeTTarif() {
		return this.codeTTarif;
	}

	public void setCodeTTarif(String codeTTarif) {
		this.codeTTarif = codeTTarif;
	}

	@Column(name = "LIBL_T_TARIF", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "LIBL_T_TARIF",table = "TA_T_TARIF",clazz = TaTTarif.class)
	public String getLiblTTarif() {
		return this.liblTTarif;
	}

	public void setLiblTTarif(String liblTTarif) {
		this.liblTTarif = liblTTarif;
	}
	
	@Column(name = "SENS")
	@LgrHibernateValidated(champEntite = "", champBd = "SENS",table = "TA_T_TARIF",clazz = TaTTarif.class)
	public Integer getSens() {
		return this.sens;
	}

	public void setSens(Integer sens) {
		this.sens = sens;
	}
	
	@Column(name = "POURCENTAGE")
	@LgrHibernateValidated(champEntite = "", champBd = "POURCENTAGE",table = "TA_T_TARIF",clazz = TaTTarif.class)
	public Integer getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(Integer pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	@Column(name = "VALEUR")
	@LgrHibernateValidated(champEntite = "", champBd = "VALEUR",table = "TA_T_TARIF",clazz = TaTTarif.class)
	public BigDecimal getValeur() {
		return this.valeur;
	}

	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}

	@Column(name = "QUI_CREE_T_TARIF", length = 50)
	public String getQuiCreeTTarif() {
		return this.quiCreeTTarif;
	}

	public void setQuiCreeTTarif(String quiCreeTTarif) {
		this.quiCreeTTarif = quiCreeTTarif;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_T_TARIF", length = 19)
	public Date getQuandCreeTTarif() {
		return this.quandCreeTTarif;
	}

	public void setQuandCreeTTarif(Date quandCreeTTarif) {
		this.quandCreeTTarif = quandCreeTTarif;
	}

	@Column(name = "QUI_MODIF_T_TARIF", length = 50)
	public String getQuiModifTTarif() {
		return this.quiModifTTarif;
	}

	public void setQuiModifTTarif(String quiModifTTarif) {
		this.quiModifTTarif = quiModifTTarif;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_T_TARIF", length = 19)
	public Date getQuandModifTTarif() {
		return this.quandModifTTarif;
	}

	public void setQuandModifTTarif(Date quandModifTTarif) {
		this.quandModifTTarif = quandModifTTarif;
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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTTarif")
	public Set<TaTiers> getTaTierses() {
		return this.taTierses;
	}

	public void setTaTierses(Set<TaTiers> taTierses) {
		this.taTierses = taTierses;
	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTTarif(codeTTarif.toUpperCase());
	}
}
