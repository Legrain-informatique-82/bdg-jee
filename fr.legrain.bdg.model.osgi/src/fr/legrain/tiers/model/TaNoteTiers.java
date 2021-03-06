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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.tiers.model.TaTNoteTiers;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaNote generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_note_tiers")
//@SequenceGenerator(name = "gen_note_tiers", sequenceName = "num_id_note_tiers", allocationSize = 1)
public class TaNoteTiers extends TaObjetLgr   implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1891637318150688940L;
	
	private int idNoteTiers;
//	private String version;
	private TaTNoteTiers taTNoteTiers;
	private TaTiers taTiers;
	private String noteTiers;
	private Date dateNoteTiers;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;

	public TaNoteTiers() {
	}

	public TaNoteTiers(int idNote) {
		this.idNoteTiers = idNote;
	}

	public TaNoteTiers(int idNoteTiers, TaTNoteTiers taTNoteTiers, TaTiers taTiers, String noteTiers, Date dateNoteTiers,
			String quiCreeNote, Date quandCreeNote, String quiModifNote,
			Date quandModifNote, String ipAcces, Integer versionObj) {
		this.idNoteTiers = idNoteTiers;
		this.taTNoteTiers = taTNoteTiers;
		this.taTiers = taTiers;
		this.noteTiers = noteTiers;
		this.dateNoteTiers = dateNoteTiers;
		this.quiCree = quiCreeNote;
		this.quandCree = quandCreeNote;
		this.quiModif = quiModifNote;
		this.quandModif = quandModifNote;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_note_tiers", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_note_tiers",table = "ta_note_tiers",champEntite="idNoteTiers", clazz = TaNoteTiers.class)
	public int getIdNoteTiers() {
		return this.idNoteTiers;
	}

	public void setIdNoteTiers(int idNoteTiers) {
		this.idNoteTiers = idNoteTiers;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, /*CascadeType.MERGE,*/ CascadeType.REFRESH})
	@JoinColumn(name = "id_t_note_tiers")
	@LgrHibernateValidated(champBd = "id_t_note_tiers",table = "ta_t_note_tiers",champEntite="idTNoteTiers",clazz = TaTNoteTiers.class)
	public TaTNoteTiers getTaTNoteTiers() {
		return this.taTNoteTiers;
	}

	public void setTaTNoteTiers(TaTNoteTiers taTNoteTiers) {
		this.taTNoteTiers = taTNoteTiers;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_tiers")
	@LgrHibernateValidated(champBd = "id_tiers",table = "ta_tiers",champEntite="idTiers",clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return this.taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}

	@Column(name = "note_tiers")
	@LgrHibernateValidated(champBd = "note_tiers",table = "ta_note_tiers",champEntite="noteTiers",clazz = TaNoteTiers.class)
	public String getNoteTiers() {
		return this.noteTiers;
	}

	public void setNoteTiers(String noteTiers) {
		this.noteTiers = noteTiers;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_note_tiers", length = 19)
	@LgrHibernateValidated(champBd = "date_note_tiers",table = "ta_note_tiers",champEntite="dateNoteTiers",clazz = TaNoteTiers.class)
	public Date getDateNoteTiers() {
		return this.dateNoteTiers;
	}

	public void setDateNoteTiers(Date dateNoteTiers) {
		this.dateNoteTiers = dateNoteTiers;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeNote) {
//		this.quiCree = quiCreeNote;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeNote) {
//		this.quandCree = quandCreeNote;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifNote) {
//		this.quiModif = quiModifNote;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifNote) {
//		this.quandModif = quandModifNote;
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

}
