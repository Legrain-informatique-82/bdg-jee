package fr.legrain.pointLgr.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
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
import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaAdresse generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_article_point")
//@SequenceGenerator(name = "gen_article_point", sequenceName = "num_id_article_point", allocationSize = 1)
public class TaArticlePoint implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3096749282579796358L;
	private int idArticlePoint;
	private Date debutPeriode;
	private Date finPeriode;
	private BigDecimal points;
	private BigDecimal prixReference;
	private TaArticle taArticle;
	private Integer indice = 0;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private String version;
	private TaTypeMouvPoint taTypeMouvPoint;

	public TaArticlePoint() {
	}

	public TaArticlePoint(int idArticlePoint) {
		this.idArticlePoint = idArticlePoint;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_article_point", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_article_point",table = "ta_article_point", champEntite="idArticlePoint", clazz = TaArticlePoint.class)
	public int getIdArticlePoint() {
		return this.idArticlePoint;
	}

	public void setIdArticlePoint(int idArticlePoint) {
		this.idArticlePoint = idArticlePoint;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_article")
	@LgrHibernateValidated(champBd = "id_article",table = "ta_article", champEntite="taArticle.idArticle", clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return this.taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "debut_periode", length = 19)
	@LgrHibernateValidated(champBd = "debut_periode",table = "ta_article_point", champEntite="debutPeriode", clazz = TaArticlePoint.class)
	public Date getDebutPeriode() {
		return this.debutPeriode;
	}

	public void setDebutPeriode(Date debutPeriode) {
		this.debutPeriode = debutPeriode;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fin_periode", length = 19)
	@LgrHibernateValidated(champBd = "fin_periode",table = "ta_article_point", champEntite="finPeriode", clazz = TaArticlePoint.class)
	public Date getFinPeriode() {
		return this.finPeriode;
	}

	public void setFinPeriode(Date finPeriode) {
		this.finPeriode = finPeriode;
	}
	
	@Column(name = "points", precision = 15)
	@LgrHibernateValidated(champBd = "points",table = "ta_article_point", champEntite="points", clazz = TaArticlePoint.class)
	public BigDecimal getPoints() {
		return this.points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	
	@Column(name = "prix_reference", precision = 15)
	@LgrHibernateValidated(champBd = "prix_reference",table = "ta_article_point", champEntite="prixReference", clazz = TaArticlePoint.class)
	public BigDecimal getPrixReference() {
		return this.prixReference;
	}

	public void setPrixReference(BigDecimal prixReference) {
		this.prixReference = prixReference;
	}
	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCree) {
		this.quiCree = quiCree;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCree) {
		this.quandCree = quandCree;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModif) {
		this.quiModif = quiModif;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModif) {
		this.quandModif = quandModif;
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

	@Column(name = "indice")
	@LgrHibernateValidated(champBd = "indice",table = "ta_article_point", champEntite="indice", clazz = TaArticlePoint.class)
	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "id_type_mouv")
	@LgrHibernateValidated(champBd = "id_type_mouv",table = "type_mouv_point_bonus", champEntite="taTypeMouvPoint.idTypeMouv", clazz = TaTypeMouvPoint.class)
	public TaTypeMouvPoint getTaTypeMouvPoint() {
		return taTypeMouvPoint;
	}

	public void setTaTypeMouvPoint(TaTypeMouvPoint taTypeMouvPoint) {
		this.taTypeMouvPoint = taTypeMouvPoint;
	}


}
