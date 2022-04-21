package fr.legrain.document.model;

// Generated Apr 7, 2009 3:27:23 PM by Hibernate Tools 3.2.0.CR1

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
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * TaModele generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_modele")
//@SequenceGenerator(name = "gen_modele", sequenceName = "num_id_modele", allocationSize = 1)
public class TaModele implements java.io.Serializable {

	private static final long serialVersionUID = -4635916740451364591L;
	
	private int idModele;
	private String version;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	@Transient
	private boolean legrain = false;
	public TaModele() {
	}

	public TaModele(int idModele) {
		this.idModele = idModele;
	}

	public TaModele(int idModele, String quiCreeModele, Date quandCreeModele,
			String quiModifModele, Date quandModifModele, String ipAcces,
			Integer versionObj) {
		this.idModele = idModele;
		this.quiCree = quiCreeModele;
		this.quandCree = quandCreeModele;
		this.quiModif = quiModifModele;
		this.quandModif = quandModifModele;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modele", unique = true, nullable = false)
	public int getIdModele() {
		return this.idModele;
	}

	public void setIdModele(int idModele) {
		this.idModele = idModele;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeModele) {
		this.quiCree = quiCreeModele;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeModele) {
		this.quandCree = quandCreeModele;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifModele) {
		this.quiModif = quiModifModele;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifModele) {
		this.quandModif = quandModifModele;
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

	public void setLegrain(boolean legrain) {
		this.legrain = legrain;
	}

}
