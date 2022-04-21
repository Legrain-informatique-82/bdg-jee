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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import fr.legrain.document.model.TaTDoc;
import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaNote generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_param_cree_doc_tiers")
//@SequenceGenerator(name = "gen_param_cree_doc_tiers", sequenceName = "num_id_cree_doc_tiers", allocationSize = 1)
public class TaParamCreeDocTiers extends TaObjetLgr   implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3662855715413352306L;
	private int idParamCreeDocTiers;
//	private String version;
	private TaTiers taTiers;
	
	private Integer tiers=0;
	private Integer document=0;
	private Integer semaine=0;
	private Integer deuxSemaines=0;
	private Integer mois=0;
	private Integer decade=0;
	private Integer xJours=0;
	private Integer nbJours=1;
	
	private String codeParam;
	
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;


//passage ejb
	private Set<TaTDoc> taTDoc = new HashSet<TaTDoc>(0);
	
	
	@Transient
	private Boolean appliquerATous=false; 

	public TaParamCreeDocTiers() {
	}

	public TaParamCreeDocTiers(int idParamCreeDocTiers) {
		this.idParamCreeDocTiers = idParamCreeDocTiers;
	}


//String typeDoc,
	public TaParamCreeDocTiers(int idParamCreeDocTiers, String version, TaTiers taTiers,
			 Integer tiers, Integer document, Integer semaine,
			Integer deuxSemaines, Integer mois, Integer decade, Integer xJours,
			Integer nbJours, String quiCree, Date quandCree, String quiModif,
			Date quandModif, String ipAcces, Integer versionObj) {
		super();
		this.idParamCreeDocTiers = idParamCreeDocTiers;
		this.version = version;
		this.taTiers = taTiers;
//		this.typeDoc = typeDoc;
		this.tiers = tiers;
		this.document = document;
		this.semaine = semaine;
		this.deuxSemaines = deuxSemaines;
		this.mois = mois;
		this.decade = decade;
		this.xJours = xJours;
		this.nbJours = nbJours;
		this.quiCree = quiCree;
		this.quandCree = quandCree;
		this.quiModif = quiModif;
		this.quandModif = quandModif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_param_cree_doc_tiers", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_param_cree_doc_tiers",table = "ta_param_cree_doc_tiers",champEntite="idParamCreeDocTiers", clazz = TaParamCreeDocTiers.class)
	public int getIdParamCreeDocTiers() {
		return this.idParamCreeDocTiers;
	}

	public void setIdParamCreeDocTiers(int idParamCreeDocTiers) {
		this.idParamCreeDocTiers = idParamCreeDocTiers;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}


	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_tiers")
	@LgrHibernateValidated(champBd = "id_tiers",table = "ta_tiers",champEntite="idTiers",clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return this.taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}

//	@Column(name = "type_doc")
//	@LgrHibernateValidated(champ = "type_doc",table = "ta_param_cree_doc_tiers",clazz = TaParamCreeDocTiers.class)
//	public String getTypeDoc() {
//		return this.typeDoc;
//	}
//
//	public void setTypeDoc(String typeDoc) {
//		this.typeDoc = typeDoc;
//	}
	
	@Column(name = "tiers")
	@LgrHibernateValidated(champBd = "tiers",table = "ta_param_cree_doc_tiers",champEntite="tiers",clazz = TaParamCreeDocTiers.class)
	public Integer getTiers() {
		return tiers;
	}

	public void setTiers(Integer tiers) {
		this.tiers = tiers;
	}

	@Column(name = "document")
	@LgrHibernateValidated(champBd = "document",table = "ta_param_cree_doc_tiers",champEntite="",clazz = TaParamCreeDocTiers.class)
	public Integer getDocument() {
		return document;
	}

	public void setDocument(Integer document) {
		this.document = document;
	}

	@Column(name = "semaine")
	@LgrHibernateValidated(champBd = "semaine",table = "ta_param_cree_doc_tiers",champEntite="semaine",clazz = TaParamCreeDocTiers.class)
	public Integer getSemaine() {
		return semaine;
	}

	public void setSemaine(Integer semaine) {
		this.semaine = semaine;
	}

	@Column(name = "deux_semaines")
	@LgrHibernateValidated(champBd = "deux_semaines",table = "ta_param_cree_doc_tiers",champEntite="deuxSemaines",clazz = TaParamCreeDocTiers.class)
	public Integer getDeuxSemaines() {
		return deuxSemaines;
	}

	public void setDeuxSemaines(Integer deuxSemaines) {
		this.deuxSemaines = deuxSemaines;
	}

	@Column(name = "mois")
	@LgrHibernateValidated(champBd = "mois",table = "ta_param_cree_doc_tiers",champEntite="mois",clazz = TaParamCreeDocTiers.class)
	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	@Column(name = "decade")
	@LgrHibernateValidated(champBd = "decade",table = "ta_param_cree_doc_tiers",champEntite="decade",clazz = TaParamCreeDocTiers.class)
	public Integer getDecade() {
		return decade;
	}

	public void setDecade(Integer decade) {
		this.decade = decade;
	}
	
	@Column(name = "x_jours")
	@LgrHibernateValidated(champBd = "x_jours",table = "ta_param_cree_doc_tiers",champEntite="xJours",clazz = TaParamCreeDocTiers.class)
	public Integer getxJours() {
		return xJours;
	}

	public void setxJours(Integer xJours) {
		this.xJours = xJours;
	}

	@Column(name = "nb_jours")
	@LgrHibernateValidated(champBd = "nb_jours",table = "ta_param_cree_doc_tiers",champEntite="nbJours",clazz = TaParamCreeDocTiers.class)
	public Integer getNbJours() {
		return nbJours;
	}

	public void setNbJours(Integer nbJours) {
		this.nbJours = nbJours;
	}
	
	@Column(name = "code_param")
	@LgrHibernateValidated(champBd = "code_param",table = "ta_param_cree_doc_tiers",champEntite="codeParam",clazz = TaParamCreeDocTiers.class)
	public String getCodeParam() {
		return codeParam;
	}

	public void setCodeParam(String codeParam) {
		this.codeParam = codeParam;
	}
	

//passage ejb	
	@ManyToMany(/*cascade = CascadeType.ALL,*/ fetch = FetchType.EAGER)
	@JoinTable(name = "ta_r_param_creat_doc",
			joinColumns = {@JoinColumn(name = "id_param_cree_doc_tiers")},inverseJoinColumns = {@JoinColumn(name = "id_t_doc")})
	public Set<TaTDoc> getTaTDoc(){
		return this.taTDoc;
	}

	public void setTaTDoc(Set<TaTDoc> taTDoc) {
		this.taTDoc = taTDoc;
	}

	
//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCree) {
//		this.quiCree = quiCree;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCree) {
//		this.quandCree = quandCree;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModif) {
//		this.quiModif = quiModif;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModif) {
//		this.quandModif = quandModif;
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

	@Transient
	public Boolean getAppliquerATous() {
		return appliquerATous;
	}
	
	@Transient
	public void setAppliquerATous(Boolean appliquerATous) {
		this.appliquerATous = appliquerATous;
	}





}