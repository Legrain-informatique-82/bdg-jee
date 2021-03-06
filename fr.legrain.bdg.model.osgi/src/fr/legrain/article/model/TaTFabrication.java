package fr.legrain.article.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTFabrication generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_fabrication")
//@SequenceGenerator(name = "gen_t_fabrication", sequenceName = "num_id_t_fabrication", allocationSize = 1)
@NamedQueries(value = { 
		@NamedQuery(name=TaTFabrication.QN.FIND_BY_CODE_LIGHT, query="select new fr.legrain.article.dto.TaTFabricationDTO(a.idTFabrication,a.codeTFabrication,a.liblTFabrication) from TaTFabrication a where a.codeTFabrication like :codeTFabrication order by a.codeTFabrication"),
		@NamedQuery(name=TaTFabrication.QN.FIND_ALL_LIGHT, query="select new fr.legrain.article.dto.TaTFabricationDTO(a.idTFabrication,a.codeTFabrication,a.liblTFabrication) from TaTFabrication a order by a.codeTFabrication")
		})
public class TaTFabrication extends TaObjetLgr   implements java.io.Serializable {

	private static final long serialVersionUID = -3431201205714900550L;
	
	public static class QN {
		public static final String FIND_BY_CODE_LIGHT = "TaTFabrication.findByCodeLight";
		public static final String FIND_ALL_LIGHT = "TaTFabrication.findAllLight";
	}
	
	private int idTFabrication;
//	private String version;
	private String codeTFabrication;
	private String liblTFabrication;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;

	public TaTFabrication() {
	}

	public TaTFabrication(int idTFabrication) {
		this.idTFabrication = idTFabrication;
	}
	
	public TaTFabrication(int idTFabrication, String codeTFabrication, String liblTFabrication) {
		this.idTFabrication = idTFabrication;
		this.codeTFabrication = codeTFabrication;
		this.liblTFabrication = liblTFabrication;
	}

	public TaTFabrication(int idTFabrication, String codeTFabrication, String liblTFabrication,
			String quiCreeTFabrication, Date quandCreeTFabrication, String quiModifTFabrication,
			Date quandModifTFabrication, String ipAcces, Integer versionObj/*,
			Set<TaFabricationephone> taFabricationephones*/) {
		this.idTFabrication = idTFabrication;
		this.codeTFabrication = codeTFabrication;
		this.liblTFabrication = liblTFabrication;
		this.quiCree = quiCreeTFabrication;
		this.quandCree = quandCreeTFabrication;
		this.quiModif = quiModifTFabrication;
		this.quandModif = quandModifTFabrication;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		//this.taFabricationephones = taFabricationephones;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_fabrication", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_fabrication",table = "ta_t_fabrication", champEntite="idTFabrication", clazz = TaTFabrication.class)
	public int getIdTFabrication() {
		return this.idTFabrication;
	}

	public void setIdTFabrication(int idTFabrication) {
		this.idTFabrication = idTFabrication;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@Column(name = "code_t_fabrication", unique = true, length = 20)
	@LgrHibernateValidated(champBd = "code_t_fabrication",table = "ta_t_fabrication", champEntite="codeTFabrication", clazz = TaTFabrication.class)
	public String getCodeTFabrication() {
		return this.codeTFabrication;
	}

	public void setCodeTFabrication(String codeTFabrication) {
		this.codeTFabrication = codeTFabrication;
	}

	@Column(name = "libl_t_fabrication", length = 100)
	@LgrHibernateValidated(champBd = "libl_t_fabrication",table = "ta_t_fabrication", champEntite="liblTFabrication", clazz = TaTFabrication.class)
	public String getLiblTFabrication() {
		return this.liblTFabrication;
	}

	public void setLiblTFabrication(String liblTFabrication) {
		this.liblTFabrication = liblTFabrication;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeTFabrication) {
//		this.quiCree = quiCreeTFabrication;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeTFabrication) {
//		this.quandCree = quandCreeTFabrication;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifTFabrication) {
//		this.quiModif = quiModifTFabrication;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifTFabrication) {
//		this.quandModif = quandModifTFabrication;
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

//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTFabrication")
//	public Set<TaFabricationephone> getTaFabricationephones() {
//		return this.taFabricationephones;
//	}
//
//	public void setTaFabricationephones(Set<TaFabricationephone> taFabricationephones) {
//		this.taFabricationephones = taFabricationephones;
//	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTFabrication(codeTFabrication.toUpperCase());
	}




	public boolean equalsCode(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaTFabrication other = (TaTFabrication) obj;
		if (codeTFabrication == null) {
			if (other.codeTFabrication != null)
				return false;
		} else if (!codeTFabrication.equals(other.codeTFabrication))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}
	
	
}
