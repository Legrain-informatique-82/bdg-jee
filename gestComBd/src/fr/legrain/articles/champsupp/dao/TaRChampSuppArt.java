package fr.legrain.articles.champsupp.dao;

// Generated Sep 1, 2008 3:06:27 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.articles.dao.TaArticle;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaArticle generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_R_CHAMP_SUPP_ART")
@SequenceGenerator(name = "GEN_R_CHAMP_SUPP_ART", sequenceName = "NUM_ID_R_CHAMP_SUPP", allocationSize = 1)
public class TaRChampSuppArt implements java.io.Serializable {

	private int idRChampSuppArt;
	private TaChampSuppArt taChampSuppArt;
	private TaArticle taArticle;
	private String valeur;
	private String quiCreeRChampSuppArt;
	private Date quandCreeRChampSuppArt;
	private String quiModifRChampSuppArt;
	private Date quandModifRChampSuppArt;
	private String ipAcces;
	private String version;
	private Integer versionObj;

	public TaRChampSuppArt() {
	}

	public TaRChampSuppArt(int idRChampSuppArt) {
		this.idRChampSuppArt = idRChampSuppArt;
	}

	/*
	 //A mettre dans TaArticle
	  
	   private Set<TaRChampSuppArt> taRChampSuppArts = new HashSet<TaRChampSuppArt>(0);
	  
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taArticle")
	public Set<TaRChampSuppArt> getTaRChampSuppArts(){
		return this.taRChampSuppArts;
	}

	public void setTaRChampSuppArts(Set<TaRChampSuppArt> taRChampSuppArts) {
		this.taRChampSuppArts = taRChampSuppArts;
	}
*/

	public TaRChampSuppArt(int idRChampSuppArt, TaChampSuppArt taChampSuppArt,
			TaArticle taArticle, String valeur, String quiCreeRChampSuppArt,
			Date quandCreeRChampSuppArt, String quiModifRChampSuppArt,
			Date quandModifRChampSuppArt, String ipAcces, Integer versionObj) {
		super();
		this.idRChampSuppArt = idRChampSuppArt;
		this.taChampSuppArt = taChampSuppArt;
		this.taArticle = taArticle;
		this.valeur = valeur;
		this.quiCreeRChampSuppArt = quiCreeRChampSuppArt;
		this.quandCreeRChampSuppArt = quandCreeRChampSuppArt;
		this.quiModifRChampSuppArt = quiModifRChampSuppArt;
		this.quandModifRChampSuppArt = quandModifRChampSuppArt;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_R_CHAMP_SUPP_ART")
	@Column(name = "ID_R_CHAMP_SUPP", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "ID_R_CHAMP_SUPP",table = "TA_R_CHAMP_SUPP",clazz = TaRChampSuppArt.class, champEntite = "")
	public int getIdRChampSuppArt() {
		return this.idRChampSuppArt;
	}

	public void setIdRChampSuppArt(int idRChampSuppArt) {
		this.idRChampSuppArt = idRChampSuppArt;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_ARTICLE")
	@LgrHibernateValidated(champBd = "ID_ARTICLE",table = "TA_ARTICLE",clazz = TaArticle.class, champEntite = "")
	public TaArticle getTaArticle() {
		return this.taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ID_CHAMP_SUPP_ART")
	@LgrHibernateValidated(champBd = "ID_CHAMP_SUPP_ART",table = "TA_CHAMP_SUPP_ART",clazz = TaChampSuppArt.class, champEntite = "")
	public TaChampSuppArt getTaChampSuppArt() {
		return this.taChampSuppArt;
	}

	public void setTaChampSuppArt(TaChampSuppArt taChampSuppArt) {
		this.taChampSuppArt = taChampSuppArt;
	}

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


	@Column(name = "VALEUR", length = 20)
	@LgrHibernateValidated(champBd = "VALEUR",table = "TA_R_CHAMP_SUPP",clazz = TaRChampSuppArt.class, champEntite = "")
	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
/*
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TA_R_CHAMP_SUPP",
			joinColumns = {@JoinColumn(name = "ID_TIERS")},inverseJoinColumns = {@JoinColumn(name = "ID_FAMILLE")})
	public Set<TaFamilleTiers> getTaFamilleTierses() {
		return taFamilleTierses;
	}
*/
	@Column(name = "QUI_CREE_R_CHAMP_SUPP", length = 50)
	public String getQuiCreeRChampSuppArt() {
		return this.quiCreeRChampSuppArt;
	}

	public void setQuiCreeRChampSuppArt(String quiCreeRChampSuppArt) {
		this.quiCreeRChampSuppArt = quiCreeRChampSuppArt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_R_CHAMP_SUPP", length = 19)
	public Date getQuandCreeRChampSuppArt() {
		return this.quandCreeRChampSuppArt;
	}

	public void setQuandCreeRChampSuppArt(Date quandCreeRChampSuppArt) {
		this.quandCreeRChampSuppArt = quandCreeRChampSuppArt;
	}

	@Column(name = "QUI_MODIF_R_CHAMP_SUPP", length = 50)
	public String getQuiModifRChampSuppArt() {
		return this.quiModifRChampSuppArt;
	}

	public void setQuiModifRChampSuppArt(String quiModifRChampSuppArt) {
		this.quiModifRChampSuppArt = quiModifRChampSuppArt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_R_CHAMP_SUPP", length = 19)
	public Date getQuandModifRChampSuppArt() {
		return this.quandModifRChampSuppArt;
	}

	public void setQuandModifRChampSuppArt(Date quandModifRChampSuppArt) {
		this.quandModifRChampSuppArt = quandModifRChampSuppArt;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((valeur == null) ? 0 : valeur.hashCode());
		return result;
	}


}
