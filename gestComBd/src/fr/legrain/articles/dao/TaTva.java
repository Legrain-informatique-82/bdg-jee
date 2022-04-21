package fr.legrain.articles.dao;

// Generated Sep 1, 2008 3:06:27 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
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
 * TaTva generated by hbm2java
 */
@Entity
@Table(name = "TA_TVA")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@SequenceGenerator(name = "GEN_TVA", sequenceName = "NUM_ID_TVA", allocationSize = 1)
public class TaTva implements java.io.Serializable {

	private int idTva;
	private String version;
	private String codeTva;
	private String libelleTva;
	private BigDecimal tauxTva;
	private String numcptTva;
	private String quiCreeTva;
	private Date quandCreeTva;
	private String quiModifTva;
	private Date quandModifTva;
	private String ipAcces;
	private Set<TaArticle> taArticles = new HashSet<TaArticle>(0);
	private Integer versionObj;

	public TaTva() {
	}

	public TaTva(int idTva) {
		this.idTva = idTva;
	}

	public TaTva(int idTva, String codeTva, String libelleTva,
			BigDecimal tauxTva, String numcptTva, String quiCreeTva,
			Date quandCreeTva, String quiModifTva, Date quandModifTva,
			String ipAcces, Set<TaArticle> taArticles) {
		this.idTva = idTva;
		this.codeTva = codeTva;
		this.libelleTva = libelleTva;
		this.tauxTva = tauxTva;
		this.numcptTva = numcptTva;
		this.quiCreeTva = quiCreeTva;
		this.quandCreeTva = quandCreeTva;
		this.quiModifTva = quiModifTva;
		this.quandModifTva = quandModifTva;
		this.ipAcces = ipAcces;
		this.taArticles = taArticles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_TVA")
	@Column(name = "ID_TVA", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_TVA",table = "TA_TVA",clazz = TaTva.class)
	public int getIdTva() {
		return this.idTva;
	}

	public void setIdTva(int idTva) {
		this.idTva = idTva;
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

	@Column(name = "CODE_TVA", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_TVA",table = "TA_TVA",clazz = TaTva.class)
	public String getCodeTva() {
		return this.codeTva;
	}

	public void setCodeTva(String codeTva) {
		this.codeTva = codeTva;
	}

	@Column(name = "LIBELLE_TVA")
	@LgrHibernateValidated(champEntite = "", champBd = "LIBELLE_TVA",table = "TA_TVA",clazz = TaTva.class)
	public String getLibelleTva() {
		return this.libelleTva;
	}

	public void setLibelleTva(String libelleTva) {
		this.libelleTva = libelleTva;
	}

	@Column(name = "TAUX_TVA", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "TAUX_TVA",table = "TA_TVA",clazz = TaTva.class)
	public BigDecimal getTauxTva() {
		return this.tauxTva;
	}

	public void setTauxTva(BigDecimal tauxTva) {
		this.tauxTva = tauxTva;
	}

	@Column(name = "NUMCPT_TVA", length = 8)
	@LgrHibernateValidated(champEntite = "", champBd = "NUMCPT_TVA",table = "TA_TVA",clazz = TaTva.class)
	public String getNumcptTva() {
		return this.numcptTva;
	}

	public void setNumcptTva(String numcptTva) {
		this.numcptTva = numcptTva;
	}

	@Column(name = "QUI_CREE_TVA", length = 50)
	public String getQuiCreeTva() {
		return this.quiCreeTva;
	}

	public void setQuiCreeTva(String quiCreeTva) {
		this.quiCreeTva = quiCreeTva;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_TVA", length = 19)
	public Date getQuandCreeTva() {
		return this.quandCreeTva;
	}

	public void setQuandCreeTva(Date quandCreeTva) {
		this.quandCreeTva = quandCreeTva;
	}

	@Column(name = "QUI_MODIF_TVA", length = 50)
	public String getQuiModifTva() {
		return this.quiModifTva;
	}

	public void setQuiModifTva(String quiModifTva) {
		this.quiModifTva = quiModifTva;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_TVA", length = 19)
	public Date getQuandModifTva() {
		return this.quandModifTva;
	}

	public void setQuandModifTva(Date quandModifTva) {
		this.quandModifTva = quandModifTva;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "taTva")
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
		result = prime * result + ((codeTva == null) ? 0 : codeTva.hashCode());
		return result;
	}

//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTva(codeTva.toUpperCase());
	}
}
