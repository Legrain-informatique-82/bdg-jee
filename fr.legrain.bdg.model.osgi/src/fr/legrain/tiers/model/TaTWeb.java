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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTWeb generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_web")
//@SequenceGenerator(name = "gen_t_web", sequenceName = "num_id_t_web", allocationSize = 1)
public class TaTWeb extends TaObjetLgr   implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8951807222044054855L;
	private int idTWeb;
//	private String version;
	private String codeTWeb;
	private String liblTWeb;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;
//	private Set<TaWeb> taWebs = new HashSet<TaWeb>(0);

	public TaTWeb() {
	}

	public TaTWeb(int idTWeb) {
		this.idTWeb = idTWeb;
	}

	public TaTWeb(int idTWeb, String codeTWeb, String liblTWeb,
			String quiCreeTWeb, Date quandCreeTWeb, String quiModifTWeb,
			Date quandModifTWeb, String ipAcces, Integer versionObj/*,
			Set<TaWeb> taWebs*/) {
		this.idTWeb = idTWeb;
		this.codeTWeb = codeTWeb;
		this.liblTWeb = liblTWeb;
		this.quiCree = quiCreeTWeb;
		this.quandCree = quandCreeTWeb;
		this.quiModif = quiModifTWeb;
		this.quandModif = quandModifTWeb;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
//		this.taWebs = taWebs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_web", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_web",table = "ta_t_web",champEntite="idTWeb", clazz = TaTWeb.class)
	public int getIdTWeb() {
		return this.idTWeb;
	}

	public void setIdTWeb(int idTWeb) {
		this.idTWeb = idTWeb;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@Column(name = "code_t_web", length = 20)
	@LgrHibernateValidated(champBd = "code_t_web",table = "ta_t_web",champEntite="codeTWeb",clazz = TaTWeb.class)
	public String getCodeTWeb() {
		return this.codeTWeb;
	}

	public void setCodeTWeb(String codeTWeb) {
		this.codeTWeb = codeTWeb;
	}

	@Column(name = "libl_t_web", length = 100)
	@LgrHibernateValidated(champBd = "libl_t_web",table = "ta_t_web",champEntite="liblTWeb",clazz = TaTWeb.class)
	public String getLiblTWeb() {
		return this.liblTWeb;
	}

	public void setLiblTWeb(String liblTWeb) {
		this.liblTWeb = liblTWeb;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeTWeb) {
//		this.quiCree = quiCreeTWeb;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeTWeb) {
//		this.quandCree = quandCreeTWeb;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifTWeb) {
//		this.quiModif = quiModifTWeb;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifTWeb) {
//		this.quandModif = quandModifTWeb;
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

//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTWeb")
//	public Set<TaWeb> getTaWebs() {
//		return this.taWebs;
//	}
//
//	public void setTaWebs(Set<TaWeb> taWebs) {
//		this.taWebs = taWebs;
//	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTWeb(codeTWeb.toUpperCase());
	}
}
