package fr.legrain.document.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ta_t_relance")
//@SequenceGenerator(name = "gen_t_relance", sequenceName = "num_id_t_relance", allocationSize = 1)
public class TaTRelance implements java.io.Serializable {

	private static final long serialVersionUID = -7117376822699933424L;
	private int idTRelance;
	private String version;
	private String codeTRelance;
	private String libelleTRelance;
	private String cheminModelRelance;
	private String cheminCorrespRelance;
	private Integer ordreTRelance;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private Integer actif=1;
	private Integer defaut=0;
	private String typeLogiciel;

	public TaTRelance() {
	}

	public TaTRelance(int idTRelance) {
		this.idTRelance = idTRelance;
	}

	
	public TaTRelance(int idTRelance, String version, String codeTRelance,
			String libelleTRelance, String cheminModelRelance,
			String cheminCorrespRelance, Integer ordreTRelance,
			String quiCreeTRelance, Date quandCreeTRelance,
			String quiModifTRelance, Date quandModifTRelance, String ipAcces,
			Integer versionObj, Integer actif, Integer defaut,
			String typeLogiciel) {
		this.idTRelance = idTRelance;
		this.version = version;
		this.codeTRelance = codeTRelance;
		this.libelleTRelance = libelleTRelance;
		this.cheminModelRelance = cheminModelRelance;
		this.cheminCorrespRelance = cheminCorrespRelance;
		this.ordreTRelance = ordreTRelance;
		this.quiCree = quiCreeTRelance;
		this.quandCree = quandCreeTRelance;
		this.quiModif = quiModifTRelance;
		this.quandModif = quandModifTRelance;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.actif = actif;
		this.defaut = defaut;
		this.typeLogiciel = typeLogiciel;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_relance", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_relance",table = "ta_t_relance", champEntite="idTRelance", clazz = TaTRelance.class)
	public int getIdTRelance() {
		return this.idTRelance;
	}

	public void setIdTRelance(int idTRelance) {
		this.idTRelance = idTRelance;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "code_t_relance", length = 20)
	@LgrHibernateValidated(champBd = "code_t_relance",table = "ta_t_relance", champEntite="codeTRelance", clazz = TaTRelance.class)
	public String getCodeTRelance() {
		return this.codeTRelance;
	}

	public void setCodeTRelance(String codeTRelance) {
		this.codeTRelance = codeTRelance;
	}

	@Column(name = "libelle_t_relance", length = 100)
	@LgrHibernateValidated(champBd = "libelle_t_relance",table = "ta_t_relance", champEntite="libelleTRelance", clazz = TaTRelance.class)
	public String getLibelleTRelance() {
		return libelleTRelance;
	}

	public void setLibelleTRelance(String libelleTRelance) {
		this.libelleTRelance = libelleTRelance;
	}
	
	@Column(name = "chemin_model_relance", length = 255)
	@LgrHibernateValidated(champBd = "chemin_model_relance",table = "ta_t_relance", champEntite="cheminModelRelance", clazz = TaTRelance.class)
	public String getCheminModelRelance() {
		return this.cheminModelRelance;
	}

	public void setCheminModelRelance(String cheminModelRelance) {
		this.cheminModelRelance = cheminModelRelance;
	}
	
	@Column(name = "chemin_corresp_relance", length = 255)
	@LgrHibernateValidated(champBd = "chemin_corresp_relance",table = "ta_t_relance", champEntite="cheminCorrespRelance", clazz = TaTRelance.class)
	public String getCheminCorrespRelance() {
		return cheminCorrespRelance;
	}

	public void setCheminCorrespRelance(String cheminCorrespRelance) {
		this.cheminCorrespRelance = cheminCorrespRelance;
	}

	@Column(name = "ordre_t_relance" , nullable = false)
	@LgrHibernateValidated(champBd = "ordre_t_relance",table = "ta_t_relance", champEntite="ordreTRelance", clazz = TaTRelance.class)
	public Integer getOrdreTRelance() {
		return ordreTRelance;
	}

	public void setOrdreTRelance(Integer ordreTRelance) {
		this.ordreTRelance = ordreTRelance;
	}
	
	@Column(name = "actif" )
	@LgrHibernateValidated(champBd = "actif",table = "ta_t_relance", champEntite="actif", clazz = TaTRelance.class)
	public Integer getActif() {
		return actif;
	}

	public void setActif(Integer actif) {
		this.actif = actif;
	}

	@Column(name = "defaut")
	@LgrHibernateValidated(champBd = "defaut",table = "ta_t_relance", champEntite="defaut", clazz = TaTRelance.class)
	public Integer getDefaut() {
		return defaut;
	}

	public void setDefaut(Integer defaut) {
		this.defaut = defaut;
	}
	
	@Column(name = "type_logiciel", length = 20)
	@LgrHibernateValidated(champBd = "type_logiciel",table = "ta_t_relance", champEntite="typeLogiciel", clazz = TaTRelance.class)
	public String getTypeLogiciel() {
		return typeLogiciel;
	}

	public void setTypeLogiciel(String typeLogiciel) {
		this.typeLogiciel = typeLogiciel;
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



	
//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTRelance(codeTRelance.toUpperCase());
	}




}