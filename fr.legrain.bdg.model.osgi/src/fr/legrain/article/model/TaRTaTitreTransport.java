package fr.legrain.article.model;

// Generated 11 juin 2009 10:56:23 by Hibernate Tools 3.2.4.GA

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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.log4j.Logger;

import fr.legrain.article.titretransport.model.TaTitreTransport;
import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaRapportUnite generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_r_titre_transport")
//@SequenceGenerator(name = "gen_r_titre_transport", sequenceName = "num_id_r_titre_transport", allocationSize = 1)
public class TaRTaTitreTransport extends TaObjetLgr   implements java.io.Serializable {

	private static final long serialVersionUID = -6588841876737399445L;

	@Transient
	static Logger logger = Logger.getLogger(TaRTaTitreTransport.class.getName());
	
	private int idRTitreTransport;
//	private String version;
	private TaArticle taArticle;
	private TaUnite taUnite;
	private TaTitreTransport taTitreTransport;
	private BigDecimal qteTitreTransport;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;

	public TaRTaTitreTransport() {
	}

	public TaRTaTitreTransport(int id) {
		this.idRTitreTransport = id;
	}

	public TaRTaTitreTransport(int id, TaArticle taArticle, TaUnite taUnite,
			TaTitreTransport taTitreTransport, BigDecimal qteTitreTransport,
			String quiCree, Date quandCree, String quiModif, Date quandModif,
			String ipAcces, Integer versionObj) {
		this.idRTitreTransport = id;
		this.taArticle = taArticle;
		this.taUnite = taUnite;
		this.taTitreTransport = taTitreTransport;
		this.qteTitreTransport = qteTitreTransport;
		this.quiCree = quiCree;
		this.quandCree = quandCree;
		this.quiModif = quiModif;
		this.quandModif = quandModif;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_r_titre_transport", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_r_titre_transport",table = "ta_r_titre_transport", champEntite="idRTitreTransport", clazz = TaRTaTitreTransport.class)
	public int getIdRTitreTransport() {
		return this.idRTitreTransport;
	}

	public void setIdRTitreTransport(int id) {
		this.idRTitreTransport = id;
	}

//	//@Version
//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_article")
	@LgrHibernateValidated(champBd = "id_article",table = "ta_r_titre_transport", champEntite="taArticle.idArticle", clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return this.taArticle;
	}

	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unite")
	@LgrHibernateValidated(champBd = "id_unite",table = "ta_r_titre_transport", champEntite="taUnite.idUnite", clazz = TaUnite.class)
	public TaUnite getTaUnite() {
		return this.taUnite;
	}

	public void setTaUnite(TaUnite taUnite) {
		this.taUnite = taUnite;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_titre_transport")
	@LgrHibernateValidated(champBd = "id_titre_transport",table = "ta_r_titre_transport", champEntite="taTitreTransport.idTitreTransport", clazz = TaTitreTransport.class)
	public TaTitreTransport getTaTitreTransport() {
		return this.taTitreTransport;
	}

	public void setTaTitreTransport(TaTitreTransport taTitreTransport) {
		this.taTitreTransport = taTitreTransport;
	}

	@Column(name = "qte_titre_transport")
	@LgrHibernateValidated(champBd = "qte_titre_transport",table = "ta_r_titre_transport", champEntite="qteTitreTransport", clazz = TaRTaTitreTransport.class)
	public BigDecimal getQteTitreTransport() {
		return this.qteTitreTransport;
	}

	public void setQteTitreTransport(BigDecimal qteTitreTransport) {
		this.qteTitreTransport = qteTitreTransport;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCree) {
//		this.quiCree = quiCree;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCree) {
//		this.quandCree = quandCree;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModif) {
//		this.quiModif = quiModif;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModif) {
//		this.quandModif = quandModif;
//	}
//
//	@Column(name = "ip_acces", length = 50)
//	public String getIpAcces() {
//		return this.ipAcces;
//	}
//
//	public void setIpAcces(String ipAcces) {
//		this.ipAcces = ipAcces;
//	}

	@Version
	@Column(name = "version_obj")
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRTitreTransport;
		return result;
	}

	
	@Override
	public Object clone() throws CloneNotSupportedException {
		TaRTaTitreTransport objet = new TaRTaTitreTransport();
		try {
			objet.setQteTitreTransport(qteTitreTransport);
			objet.setTaArticle(taArticle);
			objet.setTaUnite(taUnite);
			objet.setTaTitreTransport(taTitreTransport);
		} catch (Exception e) {
			logger.error("", e);
		}
		// on renvoie le clone
		return objet;
	}

}
