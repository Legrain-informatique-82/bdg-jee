package fr.legrain.dossier.model;

// Generated 14 mai 2009 11:08:14 by Hibernate Tools 3.2.4.GA

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

/**
 * TaInfoEntreprise generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_version")
//@SequenceGenerator(name = "gen_version", sequenceName = "num_id_version", allocationSize = 1)
public class TaVersion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1887803952514902274L;
	private int idVersion;
	private String numVersion;
	private String oldVersion;
	private String dateVersion;
//	private Integer versionObj;
	
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	
	public TaVersion() {
	}

	public TaVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public TaVersion(int idVersion, String numVersion,String oldVersion,
			String dateVersion) {
		this.idVersion = idVersion;
		this.numVersion = numVersion;
		this.oldVersion = oldVersion;
		this.dateVersion = dateVersion;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_version", unique = true, nullable = false)
	public int getIdVersion() {
		return this.idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	@Column(name = "num_version", length = 100)
	public String getNumVersion() {
		return this.numVersion;
	}

	public void setNumVersion(String numVersion) {
		this.numVersion = numVersion;
	}

	@Column(name = "old_version", length = 100)
	public String getOldVersion() {
		return this.oldVersion;
	}

	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}
	@Column(name = "date_version", length = 100)
	public String getDateVersion() {
		return this.dateVersion;
	}

	public void setDateVersion(String dateVersion) {
		this.dateVersion = dateVersion;
	}
	
//	@Version
//	@Column(name = "version_obj")
//	public Integer getVersionObj() {
//		return this.versionObj;
//	}
//
//	@Version
//	public void setVersionObj(Integer versionObj) {
//		this.versionObj = versionObj;
//	}
	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeArticle) {
		this.quiCree = quiCreeArticle;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeArticle) {
		this.quandCree = quandCreeArticle;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifArticle) {
		this.quiModif = quiModifArticle;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifArticle) {
		this.quandModif = quandModifArticle;
	}
}