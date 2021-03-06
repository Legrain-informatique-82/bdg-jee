package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.lib.data.LgrConstantes;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class TaRAvoirDTO extends ModelObject implements java.io.Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2139968953587371494L;
	private int id;
	private Integer idFacture;
	private Integer idAvoir;
	private Date dateExport;
	private BigDecimal affectation  = new BigDecimal(0);
	private Boolean accepte=true;
	
	
	private Integer versionObj;
	
	public TaRAvoirDTO() {
	}
	
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}



	public Integer getVersionObj() {
		return versionObj;
	}


	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}


	public Integer getIdFacture() {
		return idFacture;
	}


	public void setIdFacture(Integer idFacture) {
		firePropertyChange("idFacture", this.idFacture, this.idFacture = idFacture);
	}


	


	public Integer getIdAvoir() {
		return idAvoir;
	}


	public void setIdAvoir(Integer idAvoir) {
		firePropertyChange("idAvoir", this.idAvoir, this.idAvoir = idAvoir);
	}


	public Date getDateExport() {
		return dateExport;
	}


	public void setDateExport(Date dateExport) {
		firePropertyChange("dateExport", this.dateExport, this.dateExport = dateExport);
	}


	public BigDecimal getAffectation() {
		return affectation;
	}


	public void setAffectation(BigDecimal affectation) {
		firePropertyChange("affectation", this.affectation, this.affectation = affectation);
	}


	public Boolean getAccepte() {
		return accepte;
	}


	public void setAccepte(Boolean accepte) {
		firePropertyChange("accepte", this.accepte, this.accepte = accepte);
	}




}
