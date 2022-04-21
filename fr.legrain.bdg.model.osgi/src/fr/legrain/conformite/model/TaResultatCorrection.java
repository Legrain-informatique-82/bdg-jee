package fr.legrain.conformite.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.legrain.article.model.TaLot;
import fr.legrain.droits.model.TaUtilisateur;
import fr.legrain.validator.LgrHibernateValidated;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * TaTCivilite generated by hbm2java
 */
@Entity
@Table(name = "ta_resultat_correction")
public class TaResultatCorrection implements java.io.Serializable {
	
	private static final long serialVersionUID = 3611696014233072863L;

	private int idResultatCorrection;
	
	private TaResultatConformite taResultatConformite;
	private TaBareme taBareme;
	private Boolean effectuee = false;
	private Boolean valide = false;
	private Boolean validationForcee = false;
	private String observation;	
	private TaResultatConformite taResultatConformiteApresActionCorrective;
	
	private TaUtilisateur taUtilisateurControleur;
	private TaUtilisateur taUtilisateurServiceQualite;
	private TaUtilisateur taUtilisateurDirection;
	private TaUtilisateur taUtilisateurForceValidation;
	private String actionImmediate;
	private Date dateCorrection;
	private Date dateVisaServiceQualite;
	private Date dateVisaDirection;
	private Date dateForceValidation;
	
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private String version;
	private Integer versionObj;

	public TaResultatCorrection() {
		this.valide = false;	
	}

	public TaResultatCorrection(int idGroupe) {
		this.idResultatCorrection = idGroupe;
		this.valide = false;	
	}

public static TaResultatCorrection copie(TaResultatCorrection source , TaResultatCorrection dest){
	
	return null;
}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resultat_correction", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_resultat_correction",table = "ta_resultat_correction", champEntite="idResultatCorrection", clazz = TaResultatCorrection.class)
	public int getIdResultatCorrection() {
		return this.idResultatCorrection;
	}

	public void setIdResultatCorrection(int idGroupe) {
		this.idResultatCorrection = idGroupe;
	}

	
	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_resultat_conformite")
	@LgrHibernateValidated(champBd = "id_resultat_conformite",table = "ta_resultat_correction", champEntite="taResultatConformite.idResultatConformite", clazz = TaResultatConformite.class)
	public TaResultatConformite getTaResultatConformite() {
		return taResultatConformite;
	}
	public void setTaResultatConformite(TaResultatConformite taResultatConformite) {
		this.taResultatConformite = taResultatConformite;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bareme")
	@LgrHibernateValidated(champBd = "id_bareme",table = "ta_resultat_correction", champEntite="taBareme.idBareme", clazz = TaBareme.class)
	public TaBareme getTaBareme() {
		return taBareme;
	}
	public void setTaBareme(TaBareme taBareme) {
		this.taBareme = taBareme;
	}

	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeTCivilite) {
		this.quiCree = quiCreeTCivilite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeTCivilite) {
		this.quandCree = quandCreeTCivilite;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifTCivilite) {
		this.quiModif = quiModifTCivilite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifTCivilite) {
		this.quandModif = quandModifTCivilite;
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

	public TaResultatCorrection(int idGroupe, String version, String codeGroupe,
			String libelle, String quiCree, Date quandCree, String quiModif,
			Date quandModif, String ipAcces, Integer versionObj) {
		super();
		this.idResultatCorrection = idGroupe;
		this.version = version;
		this.quiCree = quiCree;
		this.quandCree = quandCree;
		this.quiModif = quiModif;
		this.quandModif = quandModif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Column(name = "effectuee", length = 50)
	@LgrHibernateValidated(champBd = "effectuee",table = "ta_resultat_correction", champEntite="effectuee", clazz = TaResultatCorrection.class)
	public Boolean getEffectuee() {
		return effectuee;
	}

	public void setEffectuee(Boolean effectuee) {
		this.effectuee = effectuee;
	}

	@Column(name = "valide", length = 50)
	@LgrHibernateValidated(champBd = "valide",table = "ta_resultat_correction", champEntite="valide", clazz = TaResultatCorrection.class)
	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	@Column(name = "observation", length = 50)
	@LgrHibernateValidated(champBd = "observation",table = "ta_resultat_correction", champEntite="observation", clazz = TaResultatCorrection.class)
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "id_utilisateur_controleur")
	@LgrHibernateValidated(champBd = "id_utilisateur_controleur",table = "ta_utilisateur", champEntite="taUtilisateur.id", clazz = TaUtilisateur.class)
	public TaUtilisateur getTaUtilisateurControleur() {
		return taUtilisateurControleur;
	}

	public void setTaUtilisateurControleur(TaUtilisateur taUtilisateurControleur) {
		this.taUtilisateurControleur = taUtilisateurControleur;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "id_utilisateur_service_qualite")
	@LgrHibernateValidated(champBd = "id_utilisateur_service_qualite",table = "ta_utilisateur", champEntite="taUtilisateur.id", clazz = TaUtilisateur.class)
	public TaUtilisateur getTaUtilisateurServiceQualite() {
		return taUtilisateurServiceQualite;
	}

	public void setTaUtilisateurServiceQualite(TaUtilisateur taUtilisateurServiceQualite) {
		this.taUtilisateurServiceQualite = taUtilisateurServiceQualite;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "id_utilisateur_direction")
	@LgrHibernateValidated(champBd = "id_utilisateur_direction",table = "ta_utilisateur", champEntite="taUtilisateur.id", clazz = TaUtilisateur.class)
	public TaUtilisateur getTaUtilisateurDirection() {
		return taUtilisateurDirection;
	}

	public void setTaUtilisateurDirection(TaUtilisateur taUtilisateurDirection) {
		this.taUtilisateurDirection = taUtilisateurDirection;
	}

	@Column(name = "action_immediate")
	public String getActionImmediate() {
		return actionImmediate;
	}

	public void setActionImmediate(String actionImmediate) {
		this.actionImmediate = actionImmediate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_correction")
	public Date getDateCorrection() {
		return dateCorrection;
	}

	public void setDateCorrection(Date dateCorrection) {
		this.dateCorrection = dateCorrection;
	}
	
	@Column(name = "validation_forcee")
	public Boolean getValidationForcee() {
		return validationForcee;
	}
	public void setValidationForcee(Boolean validationForcee) {
		this.validationForcee = validationForcee;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "id_utilisateur_force_validation")
	@LgrHibernateValidated(champBd = "id_utilisateur_force_validation",table = "ta_utilisateur", champEntite="taUtilisateur.id", clazz = TaUtilisateur.class)
	public TaUtilisateur getTaUtilisateurForceValidation() {
		return taUtilisateurForceValidation;
	}
	public void setTaUtilisateurForceValidation(TaUtilisateur taUtilisateurForceValidation) {
		this.taUtilisateurForceValidation = taUtilisateurForceValidation;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_visa_service_qualite")
	public Date getDateVisaServiceQualite() {
		return dateVisaServiceQualite;
	}
	public void setDateVisaServiceQualite(Date dateVisaServiceQualite) {
		this.dateVisaServiceQualite = dateVisaServiceQualite;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_visa_direction")
	public Date getDateVisaDirection() {
		return dateVisaDirection;
	}
	public void setDateVisaDirection(Date dateVisaDirection) {
		this.dateVisaDirection = dateVisaDirection;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_force_validation")
	public Date getDateForceValidation() {
		return dateForceValidation;
	}

	public void setDateForceValidation(Date dateForceValidation) {
		this.dateForceValidation = dateForceValidation;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_resultat_conformite_apres_action_corrective")
	public TaResultatConformite getTaResultatConformiteApresActionCorrective() {
		return taResultatConformiteApresActionCorrective;
	}

	public void setTaResultatConformiteApresActionCorrective(
			TaResultatConformite taResultatConformiteApresActionCorrective) {
		this.taResultatConformiteApresActionCorrective = taResultatConformiteApresActionCorrective;
	}

	
}
