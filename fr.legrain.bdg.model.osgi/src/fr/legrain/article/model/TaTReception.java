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
 * TaTReception generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_reception")
//@SequenceGenerator(name = "gen_t_reception", sequenceName = "num_id_t_reception", allocationSize = 1)
@NamedQueries(value = { 
		@NamedQuery(name=TaTReception.QN.FIND_BY_CODE_LIGHT, query="select new fr.legrain.article.dto.TaTReceptionDTO(a.idTReception,a.codeTReception,a.liblTReception) from TaTReception a where a.codeTReception like :codeTReception order by a.codeTReception"),
		@NamedQuery(name=TaTReception.QN.FIND_ALL_LIGHT, query="select new fr.legrain.article.dto.TaTReceptionDTO(a.idTReception,a.codeTReception,a.liblTReception) from TaTReception a order by a.codeTReception")
		})
public class TaTReception extends TaObjetLgr   implements java.io.Serializable {

	private static final long serialVersionUID = -3431201205714900550L;
	

	public static class QN {
		public static final String FIND_BY_CODE_LIGHT = "TaTReception.findByCodeLight";
		public static final String FIND_ALL_LIGHT = "TaTReception.findAllLight";
	}
	
	private int idTReception;
//	private String version;
	private String codeTReception;
	private String liblTReception;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
	private Integer versionObj;

	public TaTReception() {
	}

	public TaTReception(int idTReception) {
		this.idTReception = idTReception;
	}
	
	
	public TaTReception(int idTReception, String codeTReception, String liblTReception) {
		this.idTReception = idTReception;
		this.codeTReception = codeTReception;
		this.liblTReception = liblTReception;
	}

	public TaTReception(int idTReception, String codeTReception, String liblTReception,
			String quiCreeTReception, Date quandCreeTReception, String quiModifTReception,
			Date quandModifTReception, String ipAcces, Integer versionObj/*,
			Set<TaReceptionephone> taReceptionephones*/) {
		this.idTReception = idTReception;
		this.codeTReception = codeTReception;
		this.liblTReception = liblTReception;
		this.quiCree = quiCreeTReception;
		this.quandCree = quandCreeTReception;
		this.quiModif = quiModifTReception;
		this.quandModif = quandModifTReception;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		//this.taReceptionephones = taReceptionephones;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_reception", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_reception",table = "ta_t_reception", champEntite="idTReception", clazz = TaTReception.class)
	public int getIdTReception() {
		return this.idTReception;
	}

	public void setIdTReception(int idTReception) {
		this.idTReception = idTReception;
	}

//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@Column(name = "code_t_reception", unique = true, length = 20)
	@LgrHibernateValidated(champBd = "code_t_reception",table = "ta_t_reception", champEntite="codeTReception", clazz = TaTReception.class)
	public String getCodeTReception() {
		return this.codeTReception;
	}

	public void setCodeTReception(String codeTReception) {
		this.codeTReception = codeTReception;
	}

	@Column(name = "libl_t_reception", length = 100)
	@LgrHibernateValidated(champBd = "libl_t_reception",table = "ta_t_reception", champEntite="liblTReception", clazz = TaTReception.class)
	public String getLiblTReception() {
		return this.liblTReception;
	}

	public void setLiblTReception(String liblTReception) {
		this.liblTReception = liblTReception;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeTReception) {
//		this.quiCree = quiCreeTReception;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeTReception) {
//		this.quandCree = quandCreeTReception;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifTReception) {
//		this.quiModif = quiModifTReception;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifTReception) {
//		this.quandModif = quandModifTReception;
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

//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTReception")
//	public Set<TaReceptionephone> getTaReceptionephones() {
//		return this.taReceptionephones;
//	}
//
//	public void setTaReceptionephones(Set<TaReceptionephone> taReceptionephones) {
//		this.taReceptionephones = taReceptionephones;
//	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTReception(codeTReception.toUpperCase());
	}



	public boolean equalsCode(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaTReception other = (TaTReception) obj;
		if (codeTReception == null) {
			if (other.codeTReception != null)
				return false;
		} else if (!codeTReception.equals(other.codeTReception))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}
	
	
}
