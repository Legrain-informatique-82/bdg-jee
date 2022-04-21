package fr.legrain.dossierIntelligent.dto;

import java.util.Date;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTAdresse generated by hbm2java
 */
public class TaParamDossierIntelligentDTO extends ModelObject implements java.io.Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2174205507841153363L;
	
	
	private int id;
	private String mot;
	private String sql;
	private int nbZones;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	private String version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMot() {
		return mot;
	}
	public void setMot(String mot) {
		this.mot = mot;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public int getNbZones() {
		return nbZones;
	}
	public void setNbZones(int nbZones) {
		this.nbZones = nbZones;
	}
	public String getQuiCree() {
		return quiCree;
	}
	public void setQuiCree(String quiCree) {
		this.quiCree = quiCree;
	}
	public Date getQuandCree() {
		return quandCree;
	}
	public void setQuandCree(Date quandCree) {
		this.quandCree = quandCree;
	}
	public String getQuiModif() {
		return quiModif;
	}
	public void setQuiModif(String quiModif) {
		this.quiModif = quiModif;
	}
	public Date getQuandModif() {
		return quandModif;
	}
	public void setQuandModif(Date quandModif) {
		this.quandModif = quandModif;
	}
	public String getIpAcces() {
		return ipAcces;
	}
	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}
	public Integer getVersionObj() {
		return versionObj;
	}
	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	

	
}
