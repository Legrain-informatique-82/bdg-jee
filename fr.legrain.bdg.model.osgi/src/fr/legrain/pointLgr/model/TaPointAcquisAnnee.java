package fr.legrain.pointLgr.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
import java.util.Date;

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

import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaAdresse generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "pointacquisannee")
//@SequenceGenerator(name = "gen_pointacquis", sequenceName = "num_id_pointacquis", allocationSize = 1)
public class TaPointAcquisAnnee implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6259599706991144067L;
	private int idPoint;
	private Date datePeremption;//DATE_PEREMPTION
	private TaTiers taTiers;//ID_TIERS
	private BigDecimal points;//POINTS
	private Integer historique;//HISTORIQUE
	private String codeDocument;//CODE_FACTURE
	private String ipAcces;
	private Integer versionObj;
	private String version;

	public TaPointAcquisAnnee() {
	}

	public TaPointAcquisAnnee(int idPoint) {
		this.idPoint = idPoint;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_point", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_point",table = "pointacquisannee", champEntite="idPoint", clazz = TaPointAcquisAnnee.class)
	public int getIdPoint() {
		return this.idPoint;
	}

	public void setIdPoint(int idArticleAccPoint) {
		this.idPoint = idArticleAccPoint;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "points", precision = 15)
	@LgrHibernateValidated(champBd = "points",table = "pointacquisannee", champEntite="points", clazz = TaPointAcquisAnnee.class)
	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	@Column(name = "historique")
	@LgrHibernateValidated(champBd = "historique",table = "pointacquisannee", champEntite="historique", clazz = TaPointAcquisAnnee.class)
	public Integer getHistorique() {
		return historique;
	}

	public void setHistorique(Integer historique) {
		this.historique = historique;
	}

	@Column(name = "code_document", length = 9)
	@LgrHibernateValidated(champBd = "code_document",table = "pointacquisannee", champEntite="codeDocument", clazz = TaPointAcquisAnnee.class)
	public String getCodeDocument() {
		return codeDocument;
	}

	public void setCodeDocument(String codeDocument) {
		this.codeDocument = codeDocument;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tiers")
	@LgrHibernateValidated(champBd = "id_tiers",table = "ta_tiers", champEntite="taTiers.idTiers", clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return this.taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "date_peremption", length = 19)
	@LgrHibernateValidated(champBd = "date_peremption",table = "pointacquisannee", champEntite="datePeremption", clazz = TaPointAcquisAnnee.class)
	public Date getDatePeremption() {
		return this.datePeremption;
	}

	public void setDatePeremption(Date debutPeriode) {
		this.datePeremption = debutPeriode;
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




}
