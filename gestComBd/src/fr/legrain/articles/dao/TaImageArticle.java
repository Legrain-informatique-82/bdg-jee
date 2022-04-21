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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaPrix generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_IMAGE_ARTICLE")
@SequenceGenerator(name = "GEN_IMAGE_ARTICLE", sequenceName = "NUM_ID_IMAGE_ARTICLE", allocationSize = 1)
public class TaImageArticle implements java.io.Serializable {

	private int idImageArticle;
	private String version;
	private TaArticle taArticle;
	private String cheminImageArticle;
	private String nomImageArticle;
//	private Integer defautImageArticle;
	private TaImageArticle imageOrigine;
	private String checksum;
	private String quiCreeImageArticle;
	private Date quandCreeImageArticle;
	private String quiModifImageArticle;
	private Date quandModifImageArticle;
	private String ipAcces;
	private Boolean defautImageArticle=true;
	private Integer versionObj;
	private Set<TaImageArticle> taImagesGenere = new HashSet<TaImageArticle>(0);
	
	@Transient
	public Boolean getDefautImageArticle() {
		if(taArticle!=null && taArticle.getTaImageArticle()!=null)
		  return taArticle.getTaImageArticle().equals(this);
		else return false;
	}

	public void setDefautImageArticle(Boolean defaut) {

		this.defautImageArticle = defaut;
		if(taArticle!=null) {
			if(defaut) {
				taArticle.setTaImageArticle(this);
			} else {
				//selection d'une image "par defaut" (la premiere de la liste)
				if(!taArticle.getTaImageArticles().isEmpty())
					taArticle.setTaImageArticle(taArticle.getTaImageArticles().iterator().next());
			}
		}
	}

	public TaImageArticle() {
	}

	public TaImageArticle(int idImageArticle) {
		this.idImageArticle = idImageArticle;
	}



	public TaImageArticle(int idImageArticle, String version,
			TaArticle taArticle, String cheminImageArticle,
			String nomImageArticle, Integer defautImageArticle,
			String quiCreeImageArticle, Date quandCreeImageArticle,
			String quiModifImageArticle, Date quandModifImageArticle,
			String ipAcces, Integer versionObj) {
		super();
		this.idImageArticle = idImageArticle;
		this.version = version;
		this.taArticle = taArticle;
		this.cheminImageArticle = cheminImageArticle;
		this.nomImageArticle = nomImageArticle;
	//	this.defautImageArticle = defautImageArticle;
		this.quiCreeImageArticle = quiCreeImageArticle;
		this.quandCreeImageArticle = quandCreeImageArticle;
		this.quiModifImageArticle = quiModifImageArticle;
		this.quandModifImageArticle = quandModifImageArticle;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_IMAGE_ARTICLE")
	@Column(name = "ID_IMAGE_ARTICLE", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_IMAGE_ARTICLE",table = "TA_IMAGE_ARTICLE",clazz = TaImageArticle.class)
	public int getIdImageArticle() {
		return this.idImageArticle;
	}

	public void setIdImageArticle(int idImageArticle) {
		this.idImageArticle = idImageArticle;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ARTICLE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_ARTICLE",table = "TA_PRIX",clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return this.taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}

	@Column(name = "CHEMIN_IMAGE_ARTICLE", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "CHEMIN_IMAGE_ARTICLE",table = "TA_IMAGE_ARTICLE",clazz = TaImageArticle.class)
	public String getCheminImageArticle() {
		return this.cheminImageArticle;
	}

	public void setCheminImageArticle(String cheminImageArticle) {
		this.cheminImageArticle = cheminImageArticle;
	}
	
	@Column(name = "NOM_IMAGE_ARTICLE", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "NOM_IMAGE_ARTICLE",table = "TA_IMAGE_ARTICLE",clazz = TaImageArticle.class)
	public String getNomImageArticle() {
		return this.nomImageArticle;
	}

	public void setNomImageArticle(String nomImageArticle) {
		this.nomImageArticle = nomImageArticle;
	}
	
//	@Column(name = "DEFAUT_IMAGE_ARTICLE", precision = 15)
//	@LgrHibernateValidated(champEntite = "", champ = "DEFAUT_IMAGE_ARTICLE",table = "TA_IMAGE_ARTICLE",clazz = TaImageArticle.class)
//	public Integer getDefautImageArticle() {
//		return this.defautImageArticle;
//	}
//
//	public void setDefautImageArticle(Integer defautImageArticle) {
//		this.defautImageArticle = defautImageArticle;
//	}
	
	/*
	 * ****************************************************************************************
	 */
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "imageOrigine")
	//@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "imageOrigine")
	public Set<TaImageArticle> getTaImagesGenere() {
		return this.taImagesGenere;
	}
	
	public void setTaImagesGenere(Set<TaImageArticle> taImagesGenere) {
		this.taImagesGenere = taImagesGenere;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_IMAGE_ORIGINE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_IMAGE_ORIGINE",table = "TA_IMAGE_ARTICLE",clazz = TaImageArticle.class)
	public TaImageArticle getImageOrigine() {
		return this.imageOrigine;
	}

	public void setImageOrigine(TaImageArticle imageOrigine) {
		this.imageOrigine = imageOrigine;
	}
	
	@Column(name = "CHECKSUM_IMAGE_ARTICLE")
	@LgrHibernateValidated(champEntite = "", champBd = "CHECKSUM_IMAGE_ARTICLE",table = "TA_IMAGE_ARTICLE",clazz = TaImageArticle.class)
	public String getChecksum() {
		return this.checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	/*
	 * ****************************************************************************************
	 */

	@Column(name = "QUI_CREE_IMAGE_ARTICLE", length = 50)
	public String getQuiCreeImageArticle() {
		return this.quiCreeImageArticle;
	}

	public void setQuiCreeImageArticle(String quiCreeImageArticle) {
		this.quiCreeImageArticle = quiCreeImageArticle;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_IMAGE_ARTICLE", length = 19)
	public Date getQuandCreeImageArticle() {
		return this.quandCreeImageArticle;
	}

	public void setQuandCreeImageArticle(Date quandCreeImageArticle) {
		this.quandCreeImageArticle = quandCreeImageArticle;
	}

	@Column(name = "QUI_MODIF_IMAGE_ARTICLE", length = 50)
	public String getQuiModifImageArticle() {
		return this.quiModifImageArticle;
	}

	public void setQuiModifImageArticle(String quiModifImageArticle) {
		this.quiModifImageArticle = quiModifImageArticle;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_IMAGE_ARTICLE", length = 19)
	public Date getQuandModifImageArticle() {
		return this.quandModifImageArticle;
	}

	public void setQuandModifImageArticle(Date quandModifImageArticle) {
		this.quandModifImageArticle = quandModifImageArticle;
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
		result = prime * result + idImageArticle;
		result = prime * result
				+ ((cheminImageArticle == null) ? 0 : cheminImageArticle.hashCode());
		result = prime * result
				+ ((nomImageArticle == null) ? 0 : nomImageArticle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TaImageArticle other = (TaImageArticle) obj;
		if (idImageArticle != other.idImageArticle)
			return false;
		if (cheminImageArticle == null) {
			if (other.cheminImageArticle != null)
				return false;
		} else if (!cheminImageArticle.equals(other.cheminImageArticle))
			return false;
		if (ipAcces == null) {
			if (other.ipAcces != null)
				return false;
		} else if (!ipAcces.equals(other.ipAcces))
			return false;
		if (nomImageArticle == null) {
			if (other.nomImageArticle != null)
				return false;
		} else if (!nomImageArticle.equals(other.nomImageArticle))
			return false;
		return true;
	}

}
