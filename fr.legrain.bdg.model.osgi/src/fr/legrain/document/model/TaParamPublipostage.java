package fr.legrain.document.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;



/**
 * TaTRelance generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_param_publipostage")
//@SequenceGenerator(name = "gen_t_param_publipostage", sequenceName = "num_id_param_publipostage", allocationSize = 1)
public class TaParamPublipostage implements java.io.Serializable {

	private static final long serialVersionUID = 6348673159939562233L;
	
	private int id;
	private String version;
	private String logicielPublipostage;
	private String extension;	
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;

	public TaParamPublipostage() {
	}


	public TaParamPublipostage(int id) {
		this.id = id;
	}
	
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_t_publipostage")
	@Column(name = "id", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id",table = "ta_param_publipostage", champEntite="id", clazz = TaParamPublipostage.class)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "logiciel_publipostage", unique = true, length = 100)
	@LgrHibernateValidated(champBd = "logiciel_publipostage",table = "ta_param_publipostage", champEntite="logicielPublipostage", clazz = TaParamPublipostage.class)
	public String getLogicielPublipostage() {
		return logicielPublipostage;
	}

	public void setLogicielPublipostage(String logicielPublipostage) {
		this.logicielPublipostage = logicielPublipostage;
	}

	@Column(name = "extension",  length = 5)
	@LgrHibernateValidated(champBd = "extension",table = "ta_param_publipostage", champEntite="extension", clazz = TaParamPublipostage.class)
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeTRelance) {
		this.quiCree = quiCreeTRelance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeTRelance) {
		this.quandCree = quandCreeTRelance;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifTRelance) {
		this.quiModif = quiModifTRelance;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifTRelance) {
		this.quandModif = quandModifTRelance;
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