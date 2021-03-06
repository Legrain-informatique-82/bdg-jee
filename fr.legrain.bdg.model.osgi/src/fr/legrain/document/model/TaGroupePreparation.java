package fr.legrain.document.model;
// Generated Apr 9, 2009 2:12:06 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.legrain.article.model.TaTransporteur;
import fr.legrain.droits.model.TaUtilisateur;
import fr.legrain.lib.data.ChangeModeEvent;
import fr.legrain.lib.data.ChangeModeListener;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.stock.model.TaGrMouvStock;
import fr.legrain.tiers.model.TaAdresse;
import fr.legrain.tiers.model.TaCPaiement;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaGroupePreparation generated by hbm2java
 */
@Entity
@Table(name = "ta_groupe_preparation", uniqueConstraints = @UniqueConstraint(columnNames = "code_groupe_preparation"))

public class TaGroupePreparation  implements  java.io.Serializable  {

	private static final long serialVersionUID = -3124862746875290086L;
	
	public static final String TYPE_DOC = "GoupePreparation";
//	public static final String PATH_ICONE_COULEUR = "dashboard/bon-de-livraison.svg";
//	public static final String PATH_ICONE_BLANC = "";
//	public static final String PATH_ICONE_GRIS = "";
	

	private String version;
	private int idGroupePreparation;

	private String codeGroupePreparation;
	private Date dateGroupePreparation;
	protected Integer versionObj;
	protected String quiCree;
	protected Date quandCree;
	protected String quiModif;
	protected Date quandModif;

	protected TaUtilisateur taUtilisateur;
	private String commentaire;
	private String ipAcces;


	protected  List<TaFlash> listeTaFlash = null;
	protected  List<TaPreparation> listeTaPreparation = null;
//	private Set<TaREtat> taREtats = new HashSet<TaREtat>(0);
//	private Set<TaHistREtat> taHistREtats = new HashSet<TaHistREtat>(0);
	
	@Transient
	static Logger logger = Logger.getLogger(TaGroupePreparation.class.getName());

	public TaGroupePreparation() {

	}
	
	public TaGroupePreparation(int idGroupePreparation) {
		this.idGroupePreparation = idGroupePreparation;
	}

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_groupe_preparation", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_groupe_preparation",table = "ta_groupe_preparation",champEntite="idGroupePreparation", clazz = TaGroupePreparation.class)
	public int getIdGroupePreparation() {
		return this.idGroupePreparation;
	}

	public void setIdGroupePreparation(int idGroupePreparation) {
		this.idGroupePreparation = idGroupePreparation;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}



	@Column(name = "code_groupe_preparation", unique = true, length = 20)
	@LgrHibernateValidated(champBd = "code_groupe_preparation",table = "ta_groupe_preparation",champEntite="codeGroupePreparation", clazz = TaGroupePreparation.class)
	public String getCodeGroupePreparation() {
		return this.codeGroupePreparation;
	}

	public void setCodeGroupePreparation(String codeGroupePreparation) {
		this.codeGroupePreparation = codeGroupePreparation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_groupe_preparation", length = 19)
	@LgrHibernateValidated(champBd = "date_groupe_preparation",table = "ta_groupe_preparation",champEntite="dateGroupePreparation", clazz = TaGroupePreparation.class)
	public Date getDateGroupePreparation() {
		return this.dateGroupePreparation;
	}

	public void setDateGroupePreparation(Date dateFacture) {
		this.dateGroupePreparation = dateFacture;
	}




	@Column(name = "commentaire", length = 2000)
	@LgrHibernateValidated(champBd = "commentaire",table = "ta_groupe_preparation",champEntite="commentaire", clazz = TaGroupePreparation.class)
	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		if(this.commentaire==null||
				this.commentaire.compareTo(commentaire)!=0){
			this.commentaire = commentaire;		
		}
	}

	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeGroupePreparation) {
		this.quiCree = quiCreeGroupePreparation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeGroupePreparation) {
		this.quandCree = quandCreeGroupePreparation;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifGroupePreparation) {
		this.quiModif = quiModifGroupePreparation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifGroupePreparation) {
		this.quandModif = quandModifGroupePreparation;
	}

	@Column(name = "ip_acces", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taGroupePreparation", orphanRemoval=true)
	@OrderBy("numLigneLGroupePreparation")
	@Fetch(FetchMode.SUBSELECT)
	public List<TaFlash> getLignes() {
		return this.listeTaFlash;
	}

	public void setLignes(List<TaFlash> taFlash) {
		this.listeTaFlash = taFlash;
	}
	






	

	@Transient
	public String getTypeGroupePreparation() {
		// TODO Auto-generated method stub
		return TYPE_DOC;
	}


	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {/*CascadeType.PERSIST, CascadeType.MERGE,*/ CascadeType.REFRESH})
	@JoinColumn(name = "id_utilisateur")
	public TaUtilisateur getTaUtilisateur() {
		return taUtilisateur;
	}
	public void setTaUtilisateur(TaUtilisateur taUtilisateur) {
		this.taUtilisateur = taUtilisateur;
	}




	
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taBoncdeAchat", orphanRemoval = true)
//	public Set<TaREtat> getTaREtats() {
//		return this.taREtats;
//	}
//
//	public void setTaREtats(Set<TaREtat> taREtats) {
//		this.taREtats = taREtats;
//	}
//	
//
//	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taBoncdeAchat", orphanRemoval = true)
//	public Set<TaHistREtat> getTaHistREtats() {
//		return this.taHistREtats;
//	}
//
//	public void setTaHistREtats(Set<TaHistREtat> taHistREtats) {
//		this.taHistREtats = taHistREtats;
//	}	


}
