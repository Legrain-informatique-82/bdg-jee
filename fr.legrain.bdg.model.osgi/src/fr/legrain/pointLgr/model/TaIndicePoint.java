package fr.legrain.pointLgr.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
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
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaAdresse generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_indice_point")
//@SequenceGenerator(name = "gen_indice_point", sequenceName = "num_id_indice_point", allocationSize = 1)
public class TaIndicePoint implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8973659811575220780L;
	private int idIndicePoint;
	private BigDecimal points;
	private BigDecimal tranche;
	private Integer nbProduit;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private String version;

	public TaIndicePoint() {
	}

	public TaIndicePoint(int idIndicePoint) {
		this.idIndicePoint = idIndicePoint;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_indice_point", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_indice_point",table = "ta_indice_point", champEntite="idIndicePoint", clazz = TaIndicePoint.class)
	public int getIdIndicePoint() {
		return this.idIndicePoint;
	}

	public void setIdIndicePoint(int idIndicePoint) {
		this.idIndicePoint = idIndicePoint;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}	
	
	@Column(name = "points", precision = 15)
	@LgrHibernateValidated(champBd = "points",table = "ta_indice_point", champEntite="points", clazz = TaIndicePoint.class)
	public BigDecimal getPoints() {
		return this.points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	

	@Column(name = "tranche", precision = 15)
	@LgrHibernateValidated(champBd = "tranche",table = "ta_indice_point", champEntite="tranche", clazz = TaIndicePoint.class)
	public BigDecimal getTranche() {
		return this.tranche;
	}

	public void setTranche(BigDecimal tranche) {
		this.tranche = tranche;
	}
	
	@Column(name = "nb_produit")
	@LgrHibernateValidated(champBd = "nb_produit",table = "ta_indice_point", champEntite="nbProduit", clazz = TaIndicePoint.class)
	public Integer getNbProduit() {
		return nbProduit;
	}

	public void setNbProduit(Integer nbProduit) {
		this.nbProduit = nbProduit;
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








}
