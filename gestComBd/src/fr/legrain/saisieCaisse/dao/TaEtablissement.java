package fr.legrain.saisieCaisse.dao;

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

import fr.legrain.documents.dao.TaTPaiement;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaEtablissement generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_etablissement")
public class TaEtablissement implements java.io.Serializable {

	private int idEtablissement;
	private String version;
	private String nomEtablissement;
	private String contact;
	private TaTPaiement taTPaiement;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private Set<TaOperation> taOperations = new HashSet<TaOperation>(0);
	private Set<TaDepot> taDepots = new HashSet<TaDepot>(0);

	public TaEtablissement() {
	}

	public TaEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	public TaEtablissement(int idEtablissement, String nomEtablissement,
			String contact, String quiCreeEtablissement,
			Date quandCreeEtablissement, String quiModifEtablissement,
			Date quandModifEtablissement, String ipAcces, Integer versionObj,
			Set<TaOperation> taOperations,Set<TaDepot> taDepots, TaTPaiement taTPaiement) {
		this.idEtablissement = idEtablissement;
		this.nomEtablissement = nomEtablissement;
		this.contact = contact;
		this.quiCree = quiCreeEtablissement;
		this.quandCree = quandCreeEtablissement;
		this.quiModif = quiModifEtablissement;
		this.quandModif = quandModifEtablissement;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.taOperations = taOperations;
		this.taDepots = taDepots;
		this.taTPaiement = taTPaiement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_etablissement", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "id_etablissement",table = "ta_etablissement",clazz = TaEtablissement.class)
	public int getIdEtablissement() {
		return this.idEtablissement;
	}

	public void setIdEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "nom_etablissement", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "nom_etablissement",table = "ta_etablissement",clazz = TaEtablissement.class)
	public String getNomEtablissement() {
		return this.nomEtablissement;
	}

	public void setNomEtablissement(String nomEtablissement) {
		this.nomEtablissement = nomEtablissement;
	}


	@Column(name = "contact", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "contact",table = "ta_etablissement",clazz = TaEtablissement.class)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_t_paiement")
	@LgrHibernateValidated(champEntite = "", champBd = "id_t_paiement",table = "ta_etablissement",clazz = TaTPaiement.class)
	public TaTPaiement getTaTPaiement() {
		return this.taTPaiement;
	}

	public void setTaTPaiement(TaTPaiement taTPaiement) {
		this.taTPaiement = taTPaiement;
	}
	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeEtablissement) {
		this.quiCree = quiCreeEtablissement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeEtablissement) {
		this.quandCree = quandCreeEtablissement;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifEtablissement) {
		this.quiModif = quiModifEtablissement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifEtablissement) {
		this.quandModif = quandModifEtablissement;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taEtablissement")
	public Set<TaOperation> getTaOperations() {
		return this.taOperations;
	}

	public void setTaOperations(Set<TaOperation> taOperations) {
		this.taOperations = taOperations;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taEtablissement")
	public Set<TaDepot> getTaDepots() {
		return taDepots;
	}

	public void setTaDepots(Set<TaDepot> taDepots) {
		this.taDepots = taDepots;
	}



 
}