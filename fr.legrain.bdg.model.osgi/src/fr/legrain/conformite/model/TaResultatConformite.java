package fr.legrain.conformite.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;








import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



import fr.legrain.article.dto.TaArticleDTO;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
import fr.legrain.article.model.TaLot;
import fr.legrain.droits.model.TaUtilisateur;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTCivilite generated by hbm2java
 */
@Entity
@Table(name = "ta_resultat_conformite")
public class TaResultatConformite implements java.io.Serializable {

	private static final long serialVersionUID = -334932676151214835L;

	private int idResultatConformite;
	
	private String valeurConstatee;
	private TaLot taLot;
	private TaConformite taConformite;
	private TaBareme taBareme;
	private TaStatusConformite taStatusConformite;
	private List<TaResultatCorrection> taResultatCorrections = new ArrayList<TaResultatCorrection>();
	
	private TaUtilisateur taUtilisateurControleur;
	private Date dateControle;
	
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private String version;
	private Integer versionObj;

	public TaResultatConformite() {
	}
	
	public TaResultatCorrection chercheCorrectionPourBareme(TaBareme taBareme) {
		for(TaResultatCorrection correction : taResultatCorrections) {
			if(correction.getTaBareme().getIdBareme()==taBareme.getIdBareme()) {
				return correction;
			}
		} 
		return null;
	}
	
	public boolean resultatSaisi() {
		if(valeurConstatee!=null && !valeurConstatee.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public TaResultatConformite(int idGroupe) {
		this.idResultatConformite = idGroupe;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resultat_conformite", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_resultat_conformite",table = "ta_resultat_conformite", champEntite="idResultatConformite", clazz = TaResultatConformite.class)
	public int getIdResultatConformite() {
		return this.idResultatConformite;
	}

	public void setIdResultatConformite(int idGroupe) {
		this.idResultatConformite = idGroupe;
	}
	
//	@ManyToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_lot")
	@LgrHibernateValidated(champBd = "id_lot",table = "ta_resultat_conformite",  champEntite="taLot.idLot", clazz = TaLot.class)
	public TaLot getTaLot() {
		return taLot;
	}
	public void setTaLot(TaLot taLot) {
		this.taLot = taLot;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_conformite")
	@LgrHibernateValidated(champBd = "id_conformite",table = "ta_resultat_conformite", champEntite="taConformite.idConformite", clazz = TaConformite.class)
	public TaConformite getTaConformite() {
		return taConformite;
	}
	public void setTaConformite(TaConformite taConformite) {
		this.taConformite = taConformite;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bareme")
	@LgrHibernateValidated(champBd = "id_bareme",table = "ta_resultat_conformite", champEntite="taBareme.idBareme", clazz = TaBareme.class)
	public TaBareme getTaBareme() {
		return taBareme;
	}
	public void setTaBareme(TaBareme taBareme) {
		this.taBareme = taBareme;
	}

	
	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "valeur_constatee", length = 20)
	@LgrHibernateValidated(champBd = "valeur_constatee",table = "ta_resultat_conformite", champEntite="valeurConstatee", clazz = TaResultatConformite.class)
	public String getValeurConstatee() {
		return this.valeurConstatee;
	}

	public void setValeurConstatee(String codeTCivilite) {
		this.valeurConstatee = codeTCivilite;
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

	public TaResultatConformite(int idGroupe, String version, String codeGroupe,
			String libelle, String quiCree, Date quandCree, String quiModif,
			Date quandModif, String ipAcces, Integer versionObj) {
		super();
		this.idResultatConformite = idGroupe;
		this.version = version;
		this.valeurConstatee = codeGroupe;
		this.quiCree = quiCree;
		this.quandCree = quandCree;
		this.quiModif = quiModif;
		this.quandModif = quandModif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taResultatConformite", orphanRemoval=true)
	@Fetch(FetchMode.SUBSELECT)
	public List<TaResultatCorrection> getTaResultatCorrections() {
		return this.taResultatCorrections;
	}

	public void setTaResultatCorrections(List<TaResultatCorrection> taResultatCorrections) {
		this.taResultatCorrections = taResultatCorrections;
	}


	public static List<TaResultatCorrection> renvoi(List<TaResultatCorrection> source,List<TaResultatCorrection> dest){		
		for (TaResultatCorrection d : dest) {
			for (TaResultatCorrection s : source) {
				if(d.getIdResultatCorrection()==s.getIdResultatCorrection()){
					d.setEffectuee(s.getEffectuee());
					d.setObservation(s.getObservation());
					d.setValide(s.getValide());
				}
			}
		}	
		return dest;
	}
	
	public static List<TaResultatCorrection> copie(List<TaResultatCorrection> source){
		List<TaResultatCorrection> retour = new LinkedList<TaResultatCorrection>();		
		for (TaResultatCorrection taResultatCorrection : source) {
			TaResultatCorrection obj = new TaResultatCorrection();
			obj.setIdResultatCorrection(taResultatCorrection.getIdResultatCorrection());
			obj.setEffectuee(taResultatCorrection.getEffectuee());
			obj.setObservation(taResultatCorrection.getObservation());
			obj.setValide(taResultatCorrection.getValide());
			retour.add(obj);
		}	
		return retour;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_controle")
	public Date getDateControle() {
		return dateControle;
	}

	public void setDateControle(Date dateControle) {
		this.dateControle = dateControle;
	}

	@ManyToOne(fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "id_status_conformite")
	public TaStatusConformite getTaStatusConformite() {
		return taStatusConformite;
	}

	public void setTaStatusConformite(TaStatusConformite taStatusConformite) {
		this.taStatusConformite = taStatusConformite;
	}
	
}
