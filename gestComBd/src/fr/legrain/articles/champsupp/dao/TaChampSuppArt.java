package fr.legrain.articles.champsupp.dao;

// Generated Sep 1, 2008 3:06:27 PM by Hibernate Tools 3.2.0.CR1

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
 * TaArticle generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_CHAMP_SUPP_ART")
@SequenceGenerator(name = "GEN_CHAMP_SUPP_ART", sequenceName = "NUM_ID_CHAMP_SUPP_ART", allocationSize = 1)
public class TaChampSuppArt implements java.io.Serializable {

	private int idChampSuppArt;
	private String nomChampSuppArt;
	private String typeValeurChampSuppArt;
	private String descriptionChampSuppArt;
	private String defautChampSuppArt;
	private String quiCreeChampSuppArt;
	private Date quandCreeChampSuppArt;
	private String quiModifChampSuppArt;
	private Date quandModifChampSuppArt;
	private String ipAcces;
	private Integer versionObj;
	private String version;

	public TaChampSuppArt() {
	}

	public TaChampSuppArt(int idChampSuppArt) {
		this.idChampSuppArt = idChampSuppArt;
	}

	public TaChampSuppArt(int idChampSuppArt, String nomChampSuppArt,
			String typeValeurChampSuppArt, String descriptionChampSuppArt,
			String defautChampSuppArt, String quiCreeChampSuppArt,
			Date quandCreeChampSuppArt, String quiModifChampSuppArt,
			Date quandModifChampSuppArt, String ipAcces, Integer versionObj,
			String version) {
		super();
		this.idChampSuppArt = idChampSuppArt;
		this.nomChampSuppArt = nomChampSuppArt;
		this.typeValeurChampSuppArt = typeValeurChampSuppArt;
		this.descriptionChampSuppArt = descriptionChampSuppArt;
		this.defautChampSuppArt = defautChampSuppArt;
		this.quiCreeChampSuppArt = quiCreeChampSuppArt;
		this.quandCreeChampSuppArt = quandCreeChampSuppArt;
		this.quiModifChampSuppArt = quiModifChampSuppArt;
		this.quandModifChampSuppArt = quandModifChampSuppArt;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.version = version;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_CHAMP_SUPP_ART")
	@Column(name = "ID_CHAMP_SUPP_ART", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "ID_CHAMP_SUPP_ART",table = "TA_CHAMP_SUPP_ART",clazz = TaChampSuppArt.class, champEntite = "")
	public int getIdChampSuppArt() {
		return this.idChampSuppArt;
	}

	public void setIdChampSuppArt(int idChampSuppArt) {
		this.idChampSuppArt = idChampSuppArt;
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

	@Column(name = "NOM_CHAMP_SUPP_ART", length = 20)
	@LgrHibernateValidated(champBd = "NOM_CHAMP_SUPP_ART",table = "TA_CHAMP_SUPP_ART",clazz = TaChampSuppArt.class, champEntite = "")
	public String getNomChampSuppArt() {
		return this.nomChampSuppArt;
	}

	public void setNomChampSuppArt(String nomChampSuppArt) {
		this.nomChampSuppArt = nomChampSuppArt;
	}

	@Column(name = "TYPE_VALEUR_CHAMP_SUPP_ART", length = 100)
	@LgrHibernateValidated(champBd = "TYPE_VALEUR_CHAMP_SUPP_ART",table = "TA_CHAMP_SUPP_ART",clazz = TaChampSuppArt.class, champEntite = "")
	public String getTypeValeurChampSuppArt() {
		return this.typeValeurChampSuppArt;
	}

	public void setTypeValeurChampSuppArt(String typeValeurChampSuppArt) {
		this.typeValeurChampSuppArt = typeValeurChampSuppArt;
	}

	@Column(name = "DESCRIPTION_CHAMP_SUPP_ART")
	@LgrHibernateValidated(champBd = "DESCRIPTION_CHAMP_SUPP_ART",table = "TA_CHAMP_SUPP_ART",clazz = TaChampSuppArt.class, champEntite = "")
	public String getDescriptionChampSuppArt() {
		return this.descriptionChampSuppArt;
	}

	public void setDescriptionChampSuppArt(String descriptionChampSuppArt) {
		this.descriptionChampSuppArt = descriptionChampSuppArt;
	}

	@Column(name = "DEFAUT_CHAMP_SUPP_ART", length = 8)
	@LgrHibernateValidated(champBd = "DEFAUT_CHAMP_SUPP_ART",table = "TA_CHAMP_SUPP_ART",clazz = TaChampSuppArt.class, champEntite = "")
	public String getDefautChampSuppArt() {
		return this.defautChampSuppArt;
	}
	
	public void setDefautChampSuppArt(String defautChampSuppArt) {
		this.defautChampSuppArt = defautChampSuppArt;
	}

	@Column(name = "QUI_CREE_CHAMP_SUPP_ART", length = 50)
	public String getQuiCreeChampSuppArt() {
		return this.quiCreeChampSuppArt;
	}

	public void setQuiCreeChampSuppArt(String quiCreeChampSuppArt) {
		this.quiCreeChampSuppArt = quiCreeChampSuppArt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_CHAMP_SUPP_ART", length = 19)
	public Date getQuandCreeChampSuppArt() {
		return this.quandCreeChampSuppArt;
	}

	public void setQuandCreeChampSuppArt(Date quandCreeChampSuppArt) {
		this.quandCreeChampSuppArt = quandCreeChampSuppArt;
	}

	@Column(name = "QUI_MODIF_CHAMP_SUPP_ART", length = 50)
	public String getQuiModifChampSuppArt() {
		return this.quiModifChampSuppArt;
	}

	public void setQuiModifChampSuppArt(String quiModifChampSuppArt) {
		this.quiModifChampSuppArt = quiModifChampSuppArt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_CHAMP_SUPP_ART", length = 19)
	public Date getQuandModifChampSuppArt() {
		return this.quandModifChampSuppArt;
	}

	public void setQuandModifChampSuppArt(Date quandModifChampSuppArt) {
		this.quandModifChampSuppArt = quandModifChampSuppArt;
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
				+ ((nomChampSuppArt == null) ? 0 : nomChampSuppArt.hashCode());
		result = prime * result
				+ ((typeValeurChampSuppArt == null) ? 0 : typeValeurChampSuppArt.hashCode());
		result = prime * result
				+ ((descriptionChampSuppArt == null) ? 0 : descriptionChampSuppArt.hashCode());
		result = prime * result
				+ ((defautChampSuppArt == null) ? 0 : defautChampSuppArt.hashCode());
		
		return result;
	}


}