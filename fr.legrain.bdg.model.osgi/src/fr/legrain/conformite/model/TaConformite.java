package fr.legrain.conformite.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.log4j.Logger;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaPrix;
import fr.legrain.article.model.TaUnite;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.validator.LgrHibernateValidated;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * TaTCivilite generated by hbm2java
 */
@Entity
@Table(name = "ta_conformite")
public class TaConformite implements java.io.Serializable {

	private static final long serialVersionUID = -6449046233470019161L;

	@Transient
	static Logger logger = Logger.getLogger(TaConformite.class.getName());
	
	private int idConformite;
	
	private String code;
	private String valeurDefaut;
	private String deuxiemeValeur;
	private String libelleConformite;
	private String valeurCalculee;
	private Boolean ctrlBloquant = true;
	private Boolean ctrlFacultatif = false;
	private Boolean supprime = false;
	private TaTypeConformite taTypeConformite;
	private TaGroupe taGroupe;
	private TaArticle taArticle;
	private Set<TaBareme> taBaremes;
	
	private String codeModeleGroupeOrigine;
	private String codeModeleConformiteOrigine;
	private TaModeleConformite taModeleConformiteOrigine;
	private TaModeleGroupe taModeleGroupeOrigine;
	private Integer position;
	
	@XmlTransient
	private TaConformite taConformiteParentAvantModif;
	private Integer versionCtrl;
	
	private String version;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;

	public TaConformite() {
	}

	public TaConformite(int idConformite) {
		this.idConformite = idConformite;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conformite", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_conformite",table = "ta_conformite", champEntite="idConformite", clazz = TaConformite.class)
	public int getIdConformite() {
		return this.idConformite;
	}

	public void setIdConformite(int idConformite) {
		this.idConformite = idConformite;
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

	@Column(name = "valeur_defaut", length = 50)
	@LgrHibernateValidated(champBd = "valeur_defaut",table = "ta_conformite", champEntite="valeurDefaut", clazz = TaConformite.class)	
	public String getValeurDefaut() {
		return valeurDefaut;
	}

	public void setValeurDefaut(String valeurDefaut) {
		this.valeurDefaut = valeurDefaut;
	}

	@Column(name = "deuxieme_valeur", length = 50)
	@LgrHibernateValidated(champBd = "deuxieme_valeur",table = "ta_conformite", champEntite="deuxiemeValeur", clazz = TaConformite.class)
	public String getDeuxiemeValeur() {
		return deuxiemeValeur;
	}

	public void setDeuxiemeValeur(String deuxiemeValeur) {
		this.deuxiemeValeur = deuxiemeValeur;
	}

	@Column(name = "libelle_conformite", length = 50)
	@LgrHibernateValidated(champBd = "libelle_conformite",table = "ta_conformite", champEntite="libelleConformite", clazz = TaConformite.class)
	public String getLibelleConformite() {
		return libelleConformite;
	}

	public void setLibelleConformite(String libelleConformite) {
		this.libelleConformite = libelleConformite;
	}

	@Column(name = "valeur_calculee", length = 50)
	@LgrHibernateValidated(champBd = "valeur_calculee",table = "ta_conformite", champEntite="valeurCalculee", clazz = TaConformite.class)
	public String getValeurCalculee() {
		return valeurCalculee;
	}

	public void setValeurCalculee(String valeurCalculee) {
		this.valeurCalculee = valeurCalculee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_conformite")
	@LgrHibernateValidated(champBd = "id_type_conformite",table = "ta_conformite", champEntite="taTypeConformite.idTypeConformite", clazz = TaTypeConformite.class)
	public TaTypeConformite getTaTypeConformite() {
		return taTypeConformite;
	}

	public void setTaTypeConformite(TaTypeConformite taTypeConformite) {
		this.taTypeConformite = taTypeConformite;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_groupe")
	@LgrHibernateValidated(champBd = "id_groupe",table = "ta_conformite", champEntite="taGroupe.idGroupe", clazz = TaGroupe.class)
	public TaGroupe getTaGroupe() {
		return taGroupe;
	}

	public void setTaGroupe(TaGroupe taGroupe) {
		this.taGroupe = taGroupe;
	}
	
//	@ManyToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_article")
	@LgrHibernateValidated(champBd = "id_article",table = "ta_conformite", champEntite="taArticle.idArticle", clazz = TaArticle.class)
	@XmlElement
	@XmlInverseReference(mappedBy="taConformites")
	public TaArticle getTaArticle() {
		return taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taConformite", orphanRemoval=true)
	public Set<TaBareme> getTaBaremes() {
		return this.taBaremes;
	}

	public void setTaBaremes(Set<TaBareme> taBaremes) {
		this.taBaremes = taBaremes;
	}

	public TaConformite(int idConformite, String valeurDefaut,
			String deuxiemeValeur, String libelleConformite,
			String valeurCalculee, TaTypeConformite taTypeConformite,
			TaGroupe taGroupe, String version, String quiCree, Date quandCree,
			String quiModif, Date quandModif, String ipAcces, Integer versionObj) {
		super();
		this.idConformite = idConformite;
		this.valeurDefaut = valeurDefaut;
		this.deuxiemeValeur = deuxiemeValeur;
		this.libelleConformite = libelleConformite;
		this.valeurCalculee = valeurCalculee;
		this.taTypeConformite = taTypeConformite;
		this.taGroupe = taGroupe;
		this.version = version;
		this.quiCree = quiCree;
		this.quandCree = quandCree;
		this.quiModif = quiModif;
		this.quandModif = quandModif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}
	
	public List<Integer> findListeIdControleDependant() {
		List<Integer> listeIdControleDependant = new ArrayList<Integer>();;
		if(taTypeConformite.getCode().equals(TaTypeConformite.C_TYPE_CALCULE)) {
			
			//analyser la formule et lister les controles dépendant
			
			Pattern pattern = Pattern.compile(TaBareme.REGEX_FORMULE_VALEUR_CALC); 
			String expr = getDeuxiemeValeur();
			if(expr != null && !expr.equals("")) {
				Matcher matcher = pattern.matcher(expr);
				while (matcher.find()) {
					String match = matcher.group().replaceAll(TaBareme.REGEX_FORMULE_CARACTERE_PONCTUATION,"");
					String idControleModele = match.substring(1);
					listeIdControleDependant.add(LibConversion.stringToInteger(idControleModele));
				}
			}
			return listeIdControleDependant;
		} else {
			//ne fait référence à aucun autre controle dans les calculs
			return listeIdControleDependant;
		}
	}
	
	public boolean dependDuControle(Integer idControle) {
		List<Integer> listeIdControleDependant = findListeIdControleDependant();
		return listeIdControleDependant.contains(idControle);
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modele_conformite_origine")
	@LgrHibernateValidated(champBd = "id_modele_conformite_origine",table = "ta_modele_conformite", champEntite="taModeleConformiteOrigine.idModeleConformite", clazz = TaModeleConformite.class)
	public TaModeleConformite getTaModeleConformiteOrigine() {
		return taModeleConformiteOrigine;
	}

	public void setTaModeleConformiteOrigine(
			TaModeleConformite taModeleConformiteOrigine) {
		this.taModeleConformiteOrigine = taModeleConformiteOrigine;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modele_groupe_origine")
	@LgrHibernateValidated(champBd = "id_modele_groupe_origine",table = "ta_modele_groupe", champEntite="taModeleGroupeOrigine.idModeleGroupe", clazz = TaModeleGroupe.class)
	public TaModeleGroupe getTaModeleGroupeOrigine() {
		return taModeleGroupeOrigine;
	}

	public void setTaModeleGroupeOrigine(TaModeleGroupe taModeleGroupeOrigine) {
		this.taModeleGroupeOrigine = taModeleGroupeOrigine;
	}

	@Column(name = "position")
	@LgrHibernateValidated(champBd = "position",table = "ta_conformite", champEntite="position", clazz = TaConformite.class)
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "ctrl_bloquant")
	public Boolean getCtrlBloquant() {
		return ctrlBloquant;
	}

	public void setCtrlBloquant(Boolean ctrlBloquant) {
		this.ctrlBloquant = ctrlBloquant;
	}

	@Column(name = "ctrl_facultatif")
	public Boolean getCtrlFacultatif() {
		return ctrlFacultatif;
	}

	public void setCtrlFacultatif(Boolean ctrlFacultatif) {
		this.ctrlFacultatif = ctrlFacultatif;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modele_conformite_parent_avant_modif")
	@XmlTransient
	public TaConformite getTaConformiteParentAvantModif() {
		return taConformiteParentAvantModif;
	}

	public void setTaConformiteParentAvantModif(TaConformite taConformiteParentAvantModif) {
		this.taConformiteParentAvantModif = taConformiteParentAvantModif;
	}

	@Column(name = "version_ctrl")
	public Integer getVersionCtrl() {
		return versionCtrl;
	}

	public void setVersionCtrl(Integer versionCtrl) {
		this.versionCtrl = versionCtrl;
	}

	@Column(name = "supprime")
	public Boolean getSupprime() {
		return supprime;
	}

	public void setSupprime(Boolean supprime) {
		this.supprime = supprime;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "code_modele_groupe_origine")
	public String getCodeModeleGroupeOrigine() {
		return codeModeleGroupeOrigine;
	}

	public void setCodeModeleGroupeOrigine(String codeModeleGroupeOrigine) {
		this.codeModeleGroupeOrigine = codeModeleGroupeOrigine;
	}

	@Column(name = "code_modele_conformite_origine")
	public String getCodeModeleConformiteOrigine() {
		return codeModeleConformiteOrigine;
	}

	public void setCodeModeleConformiteOrigine(String codeModeleConformiteOrigine) {
		this.codeModeleConformiteOrigine = codeModeleConformiteOrigine;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		TaConformite objet = new TaConformite();
		try {
//			objet.setCode(code);
			objet.setCodeModeleConformiteOrigine(codeModeleConformiteOrigine);
			objet.setCodeModeleGroupeOrigine(codeModeleGroupeOrigine);
			objet.setCtrlBloquant(ctrlBloquant);
			objet.setCtrlFacultatif(ctrlFacultatif);
			objet.setDeuxiemeValeur(deuxiemeValeur);
			objet.setLibelleConformite(libelleConformite);
			objet.setPosition(position);
			objet.setSupprime(supprime);

			objet.setTaConformiteParentAvantModif(objet);
			objet.setTaGroupe(taGroupe);
			objet.setTaModeleConformiteOrigine(taModeleConformiteOrigine);
			objet.setTaModeleGroupeOrigine(taModeleGroupeOrigine);
			objet.setTaTypeConformite(taTypeConformite);
			objet.setValeurCalculee(valeurCalculee);
			objet.setValeurCalculee(valeurCalculee);
			objet.setValeurDefaut(valeurDefaut);
			objet.setVersionCtrl(versionCtrl);

			objet.setTaBaremes(taBaremes);
			for (TaBareme taBareme : taBaremes) {
				TaBareme b = (TaBareme) taBareme.clone();
				b.setTaConformite(objet);
			}
			
		} catch (Exception e) {
			logger.error("", e);
		}
		// on renvoie le clone
		return objet;
	}
}
