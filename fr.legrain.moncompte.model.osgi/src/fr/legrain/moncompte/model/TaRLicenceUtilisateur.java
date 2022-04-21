package fr.legrain.moncompte.model;


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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;



@Entity
@Table(name = "ta_r_licence_utilsateur")
public class TaRLicenceUtilisateur implements java.io.Serializable {

	private static final long serialVersionUID = -1791584148276356085L;
	
	private Integer idRLicenceUtilisateur;
	private TaLicence taLicence;
	private TaUtilisateur taUtilisateur;
	
	private String ipAcces;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private Integer versionObj;

	public TaRLicenceUtilisateur() {
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeAdresse) {
		this.quandCree = quandCreeAdresse;
	}

	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeAdresse) {
		this.quiCree = quiCreeAdresse;
	}
	

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifAdresse) {
		this.quiModif = quiModifAdresse;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifAdresse) {
		this.quandModif = quandModifAdresse;
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

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_r_licence_utilisateur")
	public Integer getIdRLicenceUtilisateur() {
		return idRLicenceUtilisateur;
	}

	public void setIdRLicenceUtilisateur(Integer idRLicenceUtilisateur) {
		this.idRLicenceUtilisateur = idRLicenceUtilisateur;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_licence")
	public TaLicence getTaLicence() {
		return taLicence;
	}

	public void setTaLicence(TaLicence taLicence) {
		this.taLicence = taLicence;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_utlisateur")
	public TaUtilisateur getTaUtilisateur() {
		return taUtilisateur;
	}

	public void setTaUtilisateur(TaUtilisateur taUtilisateur) {
		this.taUtilisateur = taUtilisateur;
	}


}
