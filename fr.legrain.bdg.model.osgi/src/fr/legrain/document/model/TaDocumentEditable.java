package fr.legrain.document.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "ta_document_doc")
//@SequenceGenerator(name = "gen_document_doc", sequenceName = "num_id_document_doc", allocationSize = 1)
public class TaDocumentEditable implements java.io.Serializable {

	private static final long serialVersionUID = -307660127132197359L;
	
	private int idDocumentDoc;
	private String version;
	private String codeDocumentDoc;
	private String libelleDocumentDoc;
	private String cheminModelDocumentDoc;
	private String cheminCorrespDocumentDoc;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private Integer actif=1;
	private Integer defaut=0;
	private String typeLogiciel;
	private Set<TaTDoc> taTDoc = new HashSet<TaTDoc>(0);

	public TaDocumentEditable() {
	}

	public TaDocumentEditable(int idDocumentDoc) {
		this.idDocumentDoc = idDocumentDoc;
	}

	
	public TaDocumentEditable(int idDocumentDoc, String version, String codeDocumentDoc,
			String libelleDocumentDoc, String cheminModelDocumentDoc,
			String cheminCorrespDocumentDoc, 
			String quiCreeDocumentDoc, Date quandCreeDocumentDoc,
			String quiModifDocumentDoc, Date quandModifDocumentDoc, String ipAcces,
			Integer versionObj, Integer actif, Integer defaut,
			String typeLogiciel) {
		this.idDocumentDoc = idDocumentDoc;
		this.version = version;
		this.codeDocumentDoc = codeDocumentDoc;
		this.libelleDocumentDoc = libelleDocumentDoc;
		this.cheminModelDocumentDoc = cheminModelDocumentDoc;
		this.cheminCorrespDocumentDoc = cheminCorrespDocumentDoc;
		this.quiCree = quiCreeDocumentDoc;
		this.quandCree = quandCreeDocumentDoc;
		this.quiModif = quiModifDocumentDoc;
		this.quandModif = quandModifDocumentDoc;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.actif = actif;
		this.defaut = defaut;
		this.typeLogiciel = typeLogiciel;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_document_doc", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_document_doc",table = "ta_document_doc",champEntite="idDocumentDoc", clazz = TaDocumentEditable.class)
	public int getIdDocumentDoc() {
		return this.idDocumentDoc;
	}

	public void setIdDocumentDoc(int idDocumentDoc) {
		this.idDocumentDoc = idDocumentDoc;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "code_document_doc", length = 20)
	@LgrHibernateValidated(champBd = "code_document_doc",table = "ta_document_doc",champEntite="codeDocumentDoc", clazz = TaDocumentEditable.class)
	public String getCodeDocumentDoc() {
		return this.codeDocumentDoc;
	}

	public void setCodeDocumentDoc(String codeDocumentDoc) {
		this.codeDocumentDoc = codeDocumentDoc;
	}

	@Column(name = "libelle_document_doc", length = 100)
	@LgrHibernateValidated(champBd = "libelle_document_doc",table = "ta_document_doc",champEntite="libelleDocumentDoc", clazz = TaDocumentEditable.class)
	public String getLibelleDocumentDoc() {
		return libelleDocumentDoc;
	}

	public void setLibelleDocumentDoc(String libelleDocumentDoc) {
		this.libelleDocumentDoc = libelleDocumentDoc;
	}
	
	@Column(name = "chemin_model_document_doc", length = 255)
	@LgrHibernateValidated(champBd = "chemin_model_document_doc",table = "ta_document_doc",champEntite="cheminModelDocumentDoc", clazz = TaDocumentEditable.class)
	public String getCheminModelDocumentDoc() {
		return this.cheminModelDocumentDoc;
	}

	public void setCheminModelDocumentDoc(String cheminModelDocumentDoc) {
		this.cheminModelDocumentDoc = cheminModelDocumentDoc;
	}
	
	@Column(name = "chemin_corresp_document_doc", length = 255)
	@LgrHibernateValidated(champBd = "chemin_corresp_document_doc",table = "ta_document_doc",champEntite="cheminCorrespDocumentDoc", clazz = TaDocumentEditable.class)
	public String getCheminCorrespDocumentDoc() {
		return cheminCorrespDocumentDoc;
	}

	public void setCheminCorrespDocumentDoc(String cheminCorrespDocumentDoc) {
		this.cheminCorrespDocumentDoc = cheminCorrespDocumentDoc;
	}
	
	@Column(name = "actif" )
	@LgrHibernateValidated(champBd = "actif",table = "ta_document_doc",champEntite="actif", clazz = TaDocumentEditable.class)
	public Integer getActif() {
		return actif;
	}

	public void setActif(Integer actif) {
		this.actif = actif;
	}

	@Column(name = "defaut")
	@LgrHibernateValidated(champBd = "defaut",table = "ta_document_doc",champEntite="defaut", clazz = TaDocumentEditable.class)
	public Integer getDefaut() {
		return defaut;
	}

	public void setDefaut(Integer defaut) {
		this.defaut = defaut;
	}
	
	@Column(name = "type_logiciel", length = 20)
	@LgrHibernateValidated(champBd = "type_logiciel",table = "ta_document_doc",champEntite="typeLogiciel", clazz = TaDocumentEditable.class)
	public String getTypeLogiciel() {
		return typeLogiciel;
	}

	public void setTypeLogiciel(String typeLogiciel) {
		this.typeLogiciel = typeLogiciel;
	}
	
	@ManyToMany(/*cascade = CascadeType.ALL,*/ fetch = FetchType.LAZY)
	@JoinTable(name = "ta_r_document_doc",
			joinColumns = {@JoinColumn(name = "id_document_doc")},inverseJoinColumns = {@JoinColumn(name = "id_t_doc")})
	public Set<TaTDoc> getTaTDoc(){
		return this.taTDoc;
	}

	public void setTaTDoc(Set<TaTDoc> taTDoc) {
		this.taTDoc = taTDoc;
	}

	@Column(name = "qui_cree ", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeDocumentDoc) {
		this.quiCree = quiCreeDocumentDoc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeDocumentDoc) {
		this.quandCree = quandCreeDocumentDoc;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifDocumentDoc) {
		this.quiModif = quiModifDocumentDoc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifDocumentDoc) {
		this.quandModif = quandModifDocumentDoc;
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
	
	public void addTaTDoc(TaTDoc taTDoc){
		if(!this.getTaTDoc().contains(taTDoc))
			this.getTaTDoc().add(taTDoc);
	}
	public void removeTaTDoc(TaTDoc taTDoc){
		this.getTaTDoc().remove(taTDoc);
	}

}
