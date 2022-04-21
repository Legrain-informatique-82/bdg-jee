package fr.legrain.tiers.model;

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

import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTAdr generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_adr")
//@SequenceGenerator(name = "gen_t_adr", sequenceName = "num_id_t_adr", allocationSize = 1)
public class TaTAdr extends TaObjetLgr   implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8367156792295862250L;
	private int idTAdr;
//	private String version;
	private String codeTAdr;
	private String liblTAdr;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;
//	private Set<TaAdresse> taAdresses = new HashSet<TaAdresse>(0);

	public TaTAdr() {
	}

	public TaTAdr(int idTAdr) {
		this.idTAdr = idTAdr;
	}

	public TaTAdr(int idTAdr, String codeTAdr, String liblTAdr,
			String quiCreeTAdr, Date quandCreeTAdr, String quiModifTAdr,
			Date quandModifTAdr, String ipAcces, Integer versionObj/*,
			Set<TaAdresse> taAdresses*/) {
		this.idTAdr = idTAdr;
		this.codeTAdr = codeTAdr;
		this.liblTAdr = liblTAdr;
		this.quiCree = quiCreeTAdr;
		this.quandCree = quandCreeTAdr;
		this.quiModif = quiModifTAdr;
		this.quandModif = quandModifTAdr;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		//this.taAdresses = taAdresses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_adr", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_adr",table = "ta_t_adr",champEntite="idTAdr", clazz = TaTAdr.class)
	public int getIdTAdr() {
		return this.idTAdr;
	}

	public void setIdTAdr(int idTAdr) {
		this.idTAdr = idTAdr;
	}

	
//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@Column(name = "code_t_adr", length = 20)
	@LgrHibernateValidated(champBd = "code_t_adr",table = "ta_t_adr",champEntite="codeTAdr",clazz = TaTAdr.class)
	public String getCodeTAdr() {
		return this.codeTAdr;
	}

	public void setCodeTAdr(String codeTAdr) {
		this.codeTAdr = codeTAdr;
	}

	@Column(name = "libl_t_adr", length = 100)
	@LgrHibernateValidated(champBd = "libl_t_adr",table = "ta_t_adr",champEntite="liblTAdr",clazz = TaTAdr.class)
	public String getLiblTAdr() {
		return this.liblTAdr;
	}

	public void setLiblTAdr(String liblTAdr) {
		this.liblTAdr = liblTAdr;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeTAdr) {
//		this.quiCree = quiCreeTAdr;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeTAdr) {
//		this.quandCree = quandCreeTAdr;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifTAdr) {
//		this.quiModif = quiModifTAdr;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifTAdr) {
//		this.quandModif = quandModifTAdr;
//	}
//
//	@Column(name = "ip_acces", length = 50)
//	public String getIpAcces() {
//		return this.ipAcces;
//	}
//
//	public void setIpAcces(String ipAcces) {
//		this.ipAcces = ipAcces;
//	}

	@Version
	@Column(name = "version_obj")
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTAdr")
//	public Set<TaAdresse> getTaAdresses() {
//		return this.taAdresses;
//	}
//
//	public void setTaAdresses(Set<TaAdresse> taAdresses) {
//		this.taAdresses = taAdresses;
//	}

}
