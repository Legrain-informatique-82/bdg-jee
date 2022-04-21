package fr.legrain.document.model;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaMandat
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_mandat", uniqueConstraints = @UniqueConstraint(columnNames = "code_mandat"))
//@SequenceGenerator(name = "gen_mandat", sequenceName = "num_id_mandat", allocationSize = 1)
public class TaMandat implements java.io.Serializable {

	private static final long serialVersionUID = -3865235896564359929L;
	
	private int idMandat;
	private String version;
	private String codeMandat;
	private TaTiers taTiers;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;

	public TaMandat() {
	}

	public TaMandat(int idMandat) {
		this.idMandat = idMandat;
	}

	public TaMandat(int idMandat, String codeMandat, TaTiers taTiers,
			String quiCree, Date quandCree, String quiModif,
			Date quandModif, String ipAcces, Integer versionObj,
			Set<TaComDoc> taComDocs) {
		this.idMandat = idMandat;
		this.codeMandat = codeMandat;
		this.taTiers = taTiers;
		this.quiCree = quiCree;
		this.quandCree = quandCree;
		this.quiModif = quiModif;
		this.quandModif = quandModif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mandat", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_mandat",table = "ta_mandat", champEntite="idMandat", clazz = TaMandat.class)
	public int getIdMandat() {
		return this.idMandat;
	}

	public void setIdMandat(int idTDoc) {
		this.idMandat = idTDoc;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "code_mandat", unique = true, length = 50)
	@LgrHibernateValidated(champBd = "code_mandat",table = "ta_mandat", champEntite="codeMandat", clazz = TaMandat.class)
	public String getCodeMandat() {
		return this.codeMandat;
	}

	public void setCodeMandat(String codeMandat) {
		this.codeMandat = codeMandat;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tiers")
	@LgrHibernateValidated(champBd = "id_tiers",table = "ta_mandat", champEntite="taTiers.idTiers", clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return this.taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}

	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeTDoc) {
		this.quiCree = quiCreeTDoc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeTDoc) {
		this.quandCree = quandCreeTDoc;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifTDoc) {
		this.quiModif = quiModifTDoc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifTDoc) {
		this.quandModif = quandModifTDoc;
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

}
