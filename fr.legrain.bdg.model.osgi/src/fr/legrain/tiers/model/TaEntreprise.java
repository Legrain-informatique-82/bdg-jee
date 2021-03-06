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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.log4j.Logger;

import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaEntreprise generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_entreprise")
//@SequenceGenerator(name = "gen_entreprise", sequenceName = "num_id_entreprise", allocationSize = 1)
public class TaEntreprise extends TaObjetLgr   implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -75306557032451872L;
	
	@Transient
	static Logger logger = Logger.getLogger(TaEntreprise.class.getName());
	
	
	private int idEntreprise;
//	private String version;
	private String nomEntreprise;
	private String contact;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;
//	private Set<TaTiers> taTierses = new HashSet<TaTiers>(0);

	public TaEntreprise() {
	}

	public TaEntreprise(int idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public TaEntreprise(int idEntreprise, String nomEntreprise,
			String liblEntreprise, String contact, String quiCreeEntreprise,
			Date quandCreeEntreprise, String quiModifEntreprise,
			Date quandModifEntreprise, String ipAcces, Integer versionObj,
			Set<TaTiers> taTierses) {
		this.idEntreprise = idEntreprise;
		this.nomEntreprise = nomEntreprise;
		this.contact = contact;
		this.quiCree = quiCreeEntreprise;
		this.quandCree = quandCreeEntreprise;
		this.quiModif = quiModifEntreprise;
		this.quandModif = quandModifEntreprise;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
//		this.taTierses = taTierses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entreprise", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_entreprise",table = "ta_entreprise",champEntite="idEntreprise", clazz = TaEntreprise.class)
	public int getIdEntreprise() {
		return this.idEntreprise;
	}

	public void setIdEntreprise(int idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@Column(name = "nom_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "nom_entreprise",table = "ta_entreprise",champEntite="nomEntreprise",clazz = TaEntreprise.class)
	public String getNomEntreprise() {
		return this.nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	@Column(name = "contact", length = 100)
	@LgrHibernateValidated(champBd = "contact",table = "ta_entreprise",champEntite="contact",clazz = TaEntreprise.class)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeEntreprise) {
//		this.quiCree = quiCreeEntreprise;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeEntreprise) {
//		this.quandCree = quandCreeEntreprise;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifEntreprise) {
//		this.quiModif = quiModifEntreprise;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifEntreprise) {
//		this.quandModif = quandModifEntreprise;
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

	@Override
	public Object clone() throws CloneNotSupportedException {
		TaEntreprise objet = new TaEntreprise();
		try {
			objet.setContact(contact);
			objet.setNomEntreprise(nomEntreprise);

		} catch (Exception e) {
			logger.error("", e);
		}
		// on renvoie le clone
		return objet;
	}
	
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taEntreprise")
//	public Set<TaTiers> getTaTierses() {
//		return this.taTierses;
//	}
//
//	public void setTaTierses(Set<TaTiers> taTierses) {
//		this.taTierses = taTierses;
//	}

//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setNomEntreprise(nomEntreprise.toUpperCase());
	}
}
