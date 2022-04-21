package fr.legrain.articles.dao;

// Generated Sep 1, 2008 3:06:27 PM by Hibernate Tools 3.2.0.CR1

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

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTTva generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_T_TVA")
@SequenceGenerator(name = "GEN_T_TVA", sequenceName = "NUM_ID_T_TVA", allocationSize = 1)
public class TaTTva implements java.io.Serializable {

	private int idTTva;
	private String version;
	private String codeTTva;
	private String libTTva;
	private String quiCreeTTva;
	private Date quandCreeTTva;
	private String quiModifTTva;
	private Date quandModifTTva;
	private String ipAcces;
	private Set<TaArticle> taArticles = new HashSet<TaArticle>(0);
	private Integer versionObj;

	public TaTTva() {
	}

	public TaTTva(int idTTva) {
		this.idTTva = idTTva;
	}

	public TaTTva(int idTTva, String codeTTva, String libTTva,
			String quiCreeTTva, Date quandCreeTTva, String quiModifTTva,
			Date quandModifTTva, String ipAcces, Set<TaArticle> taArticles) {
		this.idTTva = idTTva;
		this.codeTTva = codeTTva;
		this.libTTva = libTTva;
		this.quiCreeTTva = quiCreeTTva;
		this.quandCreeTTva = quandCreeTTva;
		this.quiModifTTva = quiModifTTva;
		this.quandModifTTva = quandModifTTva;
		this.ipAcces = ipAcces;
		this.taArticles = taArticles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_T_TVA")
	@Column(name = "ID_T_TVA", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_TVA",table = "TA_T_TVA",clazz = TaTTva.class)
	public int getIdTTva() {
		return this.idTTva;
	}

	public void setIdTTva(int idTTva) {
		this.idTTva = idTTva;
	}

	//@Version
	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Version
	@Column(name = "VERSION_OBJ", precision = 15)
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	@Column(name = "CODE_T_TVA", length = 1)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_TVA",table = "TA_T_TVA",clazz = TaTTva.class)
	public String getCodeTTva() {
		return this.codeTTva;
	}

	public void setCodeTTva(String codeTTva) {
		this.codeTTva = codeTTva;
	}

	@Column(name = "LIB_T_TVA")
	@LgrHibernateValidated(champEntite = "", champBd = "LIB_T_TVA",table = "TA_T_TVA",clazz = TaTTva.class)
	public String getLibTTva() {
		return this.libTTva;
	}

	public void setLibTTva(String libTTva) {
		this.libTTva = libTTva;
	}

	@Column(name = "QUI_CREE_T_TVA", length = 50)
	public String getQuiCreeTTva() {
		return this.quiCreeTTva;
	}

	public void setQuiCreeTTva(String quiCreeTTva) {
		this.quiCreeTTva = quiCreeTTva;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_T_TVA", length = 19)
	public Date getQuandCreeTTva() {
		return this.quandCreeTTva;
	}

	public void setQuandCreeTTva(Date quandCreeTTva) {
		this.quandCreeTTva = quandCreeTTva;
	}

	@Column(name = "QUI_MODIF_T_TVA", length = 50)
	public String getQuiModifTTva() {
		return this.quiModifTTva;
	}

	public void setQuiModifTTva(String quiModifTTva) {
		this.quiModifTTva = quiModifTTva;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_T_TVA", length = 19)
	public Date getQuandModifTTva() {
		return this.quandModifTTva;
	}

	public void setQuandModifTTva(Date quandModifTTva) {
		this.quandModifTTva = quandModifTTva;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "taTTva")
	public Set<TaArticle> getTaArticles() {
		return this.taArticles;
	}

	public void setTaArticles(Set<TaArticle> taArticles) {
		this.taArticles = taArticles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeTTva == null) ? 0 : codeTTva.hashCode());
		return result;
	}

	
//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTTva(codeTTva.toUpperCase());
	}
}
