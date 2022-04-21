package fr.legrain.abonnement.model;

// Generated 11 août 2009 15:52:23 by Hibernate Tools 3.2.4.GA

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

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaTAbonnement;
import fr.legrain.document.model.TaLFacture;
import fr.legrain.supportAbonnement.model.TaSupportAbon;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaWlgr generated by hbm2java
 */
@Entity
@Table(name = "ta_abonnement_old")
public class TaAbonnementOld implements java.io.Serializable {

	private static final long serialVersionUID = 4636761580130101752L;
	
	public static final String TYPE_DOC = "fr.legrain.abonnement";
	private int idAbonnement;
    private Date dateDebut;
    private Date dateFin;
    private String commentaire;
    private TaArticle taArticle;
    private TaTiers taTiers;
    private TaTAbonnement taTAbonnement;
    private TaSupportAbon taSupportAbon;
    private TaLFacture taLDocument;
	private Integer versionObj;
	private Integer etat=0;
    
	public TaAbonnementOld() {
	}

	public TaAbonnementOld(int idAbonnement) {
		this.idAbonnement = idAbonnement;
	}


	public TaAbonnementOld(int idAbonnement, Date dateDebut, Date dateFin,
			String commentaire, TaArticle taArticle, TaTiers taTiers,
			TaTAbonnement taTAbonnement, TaSupportAbon taSupportAbon,
			TaLFacture taLDocument, Integer versionObj, Integer etat) {
		super();
		this.idAbonnement = idAbonnement;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.commentaire = commentaire;
		this.taArticle = taArticle;
		this.taTiers = taTiers;
		this.taTAbonnement = taTAbonnement;
		this.taSupportAbon = taSupportAbon;
		this.taLDocument = taLDocument;
		this.versionObj = versionObj;
		this.etat = etat;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_abonnement", unique = true)
	public int getIdAbonnement() {
		return this.idAbonnement;
	}

	public void setIdAbonnement(int idAbonnement) {
		this.idAbonnement = idAbonnement;
	}



	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 10)
	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 10)
	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_article")
	@LgrHibernateValidated(champBd = "id_article",table = "ta_article", champEntite="taArticle.idArticle", clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_tiers")
	@LgrHibernateValidated(champBd = "id_tiers",table = "ta_tiers", champEntite="taTiers.idTiers", clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_t_abonnement")
	@LgrHibernateValidated(champBd = "id_t_abonnement",table = "ta_t_abonnement", champEntite="taTAbonnement.idTAbonnement", clazz = TaTAbonnement.class)
	public TaTAbonnement getTaTAbonnement() {
		return taTAbonnement;
	}

	public void setTaTAbonnement(TaTAbonnement taTAbonnement) {
		this.taTAbonnement = taTAbonnement;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_support_abon")
	@LgrHibernateValidated(champBd = "id_support",table = "ta_support", champEntite="taSupportAbon.idSupportAbon", clazz = TaSupportAbon.class)
	public TaSupportAbon getTaSupportAbon() {
		return taSupportAbon;
	}

	public void setTaSupportAbon(TaSupportAbon taSupportAbon) {
		this.taSupportAbon = taSupportAbon;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_l_facture")
	@LgrHibernateValidated(champBd = "id_l_document",table = "ta_l_facture", champEntite="taLDocument.idLDocument", clazz = TaLFacture.class)
	public TaLFacture getTaLDocument() {
		return taLDocument;
	}

	public void setTaLDocument(TaLFacture taLDocument) {
		this.taLDocument = taLDocument;
	}
	

	@Column(name = "commentaire")
	@LgrHibernateValidated(champBd = "commentaire",table = "ta_abonnement", champEntite="commentaire", clazz = TaAbonnementOld.class)
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	@Version
	@Column(name = "version_obj")
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	@Column(name = "etat")
	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	
	
}
