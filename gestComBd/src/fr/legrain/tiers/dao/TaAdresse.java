package fr.legrain.tiers.dao;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "TA_ADRESSE")
@SequenceGenerator(name = "GEN_ADRESSE", sequenceName = "NUM_ID_ADRESSE", allocationSize = 1)
@NamedQueries(value = { 
		@NamedQuery(name=TaAdresse.QN.FIND_BY_IMPORT, query="select a from TaAdresse a where a.origineImport = ? and a.idImport = ?")
		})
public class TaAdresse implements java.io.Serializable {
	
	public static class QN {
		public static final String FIND_BY_IMPORT = "TaAdresse.findByImport";
	}

	private int idAdresse;
	private String version;
	private TaTAdr taTAdr;
	private TaTiers taTiers;
	private String adresse1Adresse;
	private String adresse2Adresse;
	private String adresse3Adresse;
	private String codepostalAdresse;
	private String villeAdresse;
	private String paysAdresse ;
	private Integer commCommercialAdresse;
	private Integer commAdministratifAdresse;
	private String quiCreeAdresse;
	private Date quandCreeAdresse;
	private String quiModifAdresse;
	private Date quandModifAdresse;
	private String idImport;
	private String origineImport;
	private String ipAcces;
	private Integer versionObj;
	private Set<TaTiers> taTierses = new HashSet<TaTiers>(0);

	public TaAdresse() {
	}

	public TaAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public TaAdresse(int idAdresse, TaTAdr taTAdr, TaTiers taTiers,
			String adresse1Adresse, String adresse2Adresse,
			String adresse3Adresse, String codepostalAdresse,
			String villeAdresse, String paysAdresse, String quiCreeAdresse,
			Date quandCreeAdresse, String quiModifAdresse,
			Date quandModifAdresse, String ipAcces, Integer versionObj,
			Set<TaTiers> taTierses) {
		this.idAdresse = idAdresse;
		this.taTAdr = taTAdr;
		this.taTiers = taTiers;
		this.adresse1Adresse = adresse1Adresse;
		this.adresse2Adresse = adresse2Adresse;
		this.adresse3Adresse = adresse3Adresse;
		this.codepostalAdresse = codepostalAdresse;
		this.villeAdresse = villeAdresse;
		this.paysAdresse = paysAdresse;
		this.quiCreeAdresse = quiCreeAdresse;
		this.quandCreeAdresse = quandCreeAdresse;
		this.quiModifAdresse = quiModifAdresse;
		this.quandModifAdresse = quandModifAdresse;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.taTierses = taTierses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ADRESSE")
	@Column(name = "ID_ADRESSE", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public int getIdAdresse() {
		return this.idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "ID_T_ADR")
	//@LgrHibernateValidated(champEntite = "", champ = "ID_T_ADR",table = "TA_T_ADR",clazz = TaTAdr.class)
	public TaTAdr getTaTAdr() {
		return this.taTAdr;
	}

	public void setTaTAdr(TaTAdr taTAdr) {
		this.taTAdr = taTAdr;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_TIERS")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_TIERS",table = "TA_TIERS",clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return this.taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}

	@Column(name = "ADRESSE1_ADRESSE", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "ADRESSE1_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getAdresse1Adresse() {
		return this.adresse1Adresse;
	}

	public void setAdresse1Adresse(String adresse1Adresse) {
		this.adresse1Adresse = adresse1Adresse;
	}

	@Column(name = "ADRESSE2_ADRESSE", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "ADRESSE2_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getAdresse2Adresse() {
		return this.adresse2Adresse;
	}

	public void setAdresse2Adresse(String adresse2Adresse) {
		this.adresse2Adresse = adresse2Adresse;
	}

	@Column(name = "ADRESSE3_ADRESSE", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "ADRESSE3_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getAdresse3Adresse() {
		return this.adresse3Adresse;
	}

	public void setAdresse3Adresse(String adresse3Adresse) {
		this.adresse3Adresse = adresse3Adresse;
	}

	@Column(name = "CODEPOSTAL_ADRESSE", length = 25)
	@LgrHibernateValidated(champEntite = "", champBd = "CODEPOSTAL_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getCodepostalAdresse() {
		return this.codepostalAdresse;
	}

	public void setCodepostalAdresse(String codepostalAdresse) {
		this.codepostalAdresse = codepostalAdresse;
	}

	@Column(name = "VILLE_ADRESSE", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "VILLE_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getVilleAdresse() {
		return this.villeAdresse;
	}

	public void setVilleAdresse(String villeAdresse) {
		this.villeAdresse = villeAdresse;
	}

	@Column(name = "PAYS_ADRESSE", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "PAYS_ADRESSE",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getPaysAdresse() {
		return this.paysAdresse;
	}

	public void setPaysAdresse(String paysAdresse) {
		this.paysAdresse = paysAdresse;
	}
	
	@Column(name = "COMM_COMMERCIAL")
	@LgrHibernateValidated(champEntite = "", champBd = "COMM_COMMERCIAL",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public Integer getCommCommercialAdresse() {
		return this.commCommercialAdresse;
	}

	public void setCommCommercialAdresse(Integer commCommercialTelephone) {
		this.commCommercialAdresse = commCommercialTelephone;
	}
	
	@Column(name = "COMM_ADMINISTRATIF")
	@LgrHibernateValidated(champEntite = "", champBd = "COMM_ADMINISTRATIF",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public Integer getCommAdministratifAdresse() {
		return this.commAdministratifAdresse;
	}

	public void setCommAdministratifAdresse(Integer commAdministratifTelephone) {
		this.commAdministratifAdresse = commAdministratifTelephone;
	}

	@Column(name = "QUI_CREE_ADRESSE", length = 50)
	public String getQuiCreeAdresse() {
		return this.quiCreeAdresse;
	}

	public void setQuiCreeAdresse(String quiCreeAdresse) {
		this.quiCreeAdresse = quiCreeAdresse;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_ADRESSE", length = 19)
	public Date getQuandCreeAdresse() {
		return this.quandCreeAdresse;
	}

	public void setQuandCreeAdresse(Date quandCreeAdresse) {
		this.quandCreeAdresse = quandCreeAdresse;
	}

	@Column(name = "QUI_MODIF_ADRESSE", length = 50)
	public String getQuiModifAdresse() {
		return this.quiModifAdresse;
	}

	public void setQuiModifAdresse(String quiModifAdresse) {
		this.quiModifAdresse = quiModifAdresse;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_ADRESSE", length = 19)
	public Date getQuandModifAdresse() {
		return this.quandModifAdresse;
	}

	public void setQuandModifAdresse(Date quandModifAdresse) {
		this.quandModifAdresse = quandModifAdresse;
	}
	
	@Column(name = "ORIGINE_IMPORT", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "ORIGINE_IMPORT",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getOrigineImport() {
		return this.origineImport;
	}

	public void setOrigineImport(String origineImport) {
		this.origineImport = origineImport;
	}
	
	@Column(name = "ID_IMPORT", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_IMPORT",table = "TA_ADRESSE",clazz = TaAdresse.class)
	public String getIdImport() {
		return this.idImport;
	}

	public void setIdImport(String idImport) {
		this.idImport = idImport;
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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taAdresse")
	public Set<TaTiers> getTaTierses() {
		return this.taTierses;
	}

	public void setTaTierses(Set<TaTiers> taTierses) {
		this.taTierses = taTierses;
	}

	
//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodepostalAdresse(codepostalAdresse.toUpperCase());
		this.setVilleAdresse(villeAdresse.toUpperCase());
		this.setPaysAdresse(paysAdresse.toUpperCase());
	}
}
