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
 * TaTNote generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_note_tiers")
//@SequenceGenerator(name = "gen_t_note_tiers", sequenceName = "num_id_t_note_tiers", allocationSize = 1)
public class TaTNoteTiers extends TaObjetLgr   implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7808339566727049279L;
	private int idTNoteTiers;
//	private String version;
	private String codeTNoteTiers;
	private String liblTNoteTiers;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;
//	private Set<TaNoteTiers> taNotesTiers = new HashSet<TaNoteTiers>(0);

	public TaTNoteTiers() {
	}

	public TaTNoteTiers(int idTNote) {
		this.idTNoteTiers = idTNote;
	}

	public TaTNoteTiers(int idTNoteTiers, String codeTNoteTiers, String liblTNoteTiers,
			String quiCreeTNote, Date quandCreeTNote, String quiModifTNote,
			Date quandModifTNote, String ipAcces, Integer versionObj/*,
			Set<TaNoteTiers> taNotesTiers*/) {
		this.idTNoteTiers = idTNoteTiers;
		this.codeTNoteTiers = codeTNoteTiers;
		this.liblTNoteTiers = liblTNoteTiers;
		this.quiCree = quiCreeTNote;
		this.quandCree = quandCreeTNote;
		this.quiModif = quiModifTNote;
		this.quandModif = quandModifTNote;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
//		this.taNotesTiers = taNotesTiers;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_note_tiers", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_note_tiers",table = "ta_t_note_tiers",champEntite="idTNoteTiers", clazz = TaTNoteTiers.class)
	public int getIdTNoteTiers() {
		return this.idTNoteTiers;
	}

	public void setIdTNoteTiers(int idTNote) {
		this.idTNoteTiers = idTNote;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@Column(name = "code_t_note_tiers", length = 20)
	@LgrHibernateValidated(champBd = "code_t_note_tiers",table = "ta_t_note_tiers",champEntite="codeTNoteTiers",clazz = TaTNoteTiers.class)
	public String getCodeTNoteTiers() {
		return this.codeTNoteTiers;
	}

	public void setCodeTNoteTiers(String codeTNoteTiers) {
		this.codeTNoteTiers = codeTNoteTiers;
	}

	@Column(name = "libl_t_note_tiers", length = 100)
	@LgrHibernateValidated(champBd = "libl_t_note_tiers",table = "ta_t_note_tiers",champEntite="liblTNoteTiers",clazz = TaTNoteTiers.class)
	public String getLiblTNoteTiers() {
		return this.liblTNoteTiers;
	}

	public void setLiblTNoteTiers(String liblTNoteTiers) {
		this.liblTNoteTiers = liblTNoteTiers;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeTNote) {
//		this.quiCree = quiCreeTNote;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeTNote) {
//		this.quandCree = quandCreeTNote;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifTNote) {
//		this.quiModif = quiModifTNote;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifTNote) {
//		this.quandModif = quandModifTNote;
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

//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTNoteTiers")
//	public Set<TaNoteTiers> getTaNotesTiers() {
//		return this.taNotesTiers;
//	}
//
//	public void setTaNotesTiers(Set<TaNoteTiers> taNotes) {
//		this.taNotesTiers = taNotes;
//	}
	
//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTNoteTiers(codeTNoteTiers.toUpperCase());
	}
}