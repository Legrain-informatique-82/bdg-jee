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
 * TaFamille generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_FAMILLE")
@SequenceGenerator(name = "GEN_FAMILLE", sequenceName = "NUM_ID_FAMILLE", allocationSize = 1)
public class TaFamille implements java.io.Serializable {

	private int idFamille;
	private String version;
	private String codeFamille;
	private String libcFamille;
	private String liblFamille;
	private String quiCreeFamille;
	private Date quandCreeFamille;
	private String quiModifFamille;
	private Date quandModifFamille;
	private String ipAcces;
	private Set<TaArticle> taArticles = new HashSet<TaArticle>(0);
	private Integer versionObj;

	public TaFamille() {
	}

	public TaFamille(int idFamille) {
		this.idFamille = idFamille;
	}

	public TaFamille(int idFamille, String codeFamille, String libcFamille,
			String liblFamille, String quiCreeFamille, Date quandCreeFamille,
			String quiModifFamille, Date quandModifFamille, String ipAcces,
			Set<TaArticle> taArticles) {
		this.idFamille = idFamille;
		this.codeFamille = codeFamille;
		this.libcFamille = libcFamille;
		this.liblFamille = liblFamille;
		this.quiCreeFamille = quiCreeFamille;
		this.quandCreeFamille = quandCreeFamille;
		this.quiModifFamille = quiModifFamille;
		this.quandModifFamille = quandModifFamille;
		this.ipAcces = ipAcces;
		this.taArticles = taArticles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_FAMILLE")
	@Column(name = "ID_FAMILLE", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_FAMILLE",table = "TA_FAMILLE",clazz = TaFamille.class)
	public int getIdFamille() {
		return this.idFamille;
	}

	public void setIdFamille(int idFamille) {
		this.idFamille = idFamille;
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

	@Column(name = "CODE_FAMILLE", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_FAMILLE",table = "TA_FAMILLE",clazz = TaFamille.class)
	public String getCodeFamille() {
		return this.codeFamille;
	}

	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}

	@Column(name = "LIBC_FAMILLE", length = 50)
	@LgrHibernateValidated(champEntite = "", champBd = "LIBC_FAMILLE",table = "TA_FAMILLE",clazz = TaFamille.class)
	public String getLibcFamille() {
		return this.libcFamille;
	}

	public void setLibcFamille(String libcFamille) {
		this.libcFamille = libcFamille;
	}

	@Column(name = "LIBL_FAMILLE")
	@LgrHibernateValidated(champEntite = "", champBd = "LIBL_FAMILLE",table = "TA_FAMILLE",clazz = TaFamille.class)
	public String getLiblFamille() {
		return this.liblFamille;
	}

	public void setLiblFamille(String liblFamille) {
		this.liblFamille = liblFamille;
	}

	@Column(name = "QUI_CREE_FAMILLE", length = 50)
	public String getQuiCreeFamille() {
		return this.quiCreeFamille;
	}

	public void setQuiCreeFamille(String quiCreeFamille) {
		this.quiCreeFamille = quiCreeFamille;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_FAMILLE", length = 19)
	public Date getQuandCreeFamille() {
		return this.quandCreeFamille;
	}

	public void setQuandCreeFamille(Date quandCreeFamille) {
		this.quandCreeFamille = quandCreeFamille;
	}

	@Column(name = "QUI_MODIF_FAMILLE", length = 50)
	public String getQuiModifFamille() {
		return this.quiModifFamille;
	}

	public void setQuiModifFamille(String quiModifFamille) {
		this.quiModifFamille = quiModifFamille;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_FAMILLE", length = 19)
	public Date getQuandModifFamille() {
		return this.quandModifFamille;
	}

	public void setQuandModifFamille(Date quandModifFamille) {
		this.quandModifFamille = quandModifFamille;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "taFamille")
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
				+ ((codeFamille == null) ? 0 : codeFamille.hashCode());
		return result;
	}


}
