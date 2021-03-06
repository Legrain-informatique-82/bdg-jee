package fr.legrain.moncompte.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.moncompte.commun.model.ModelObject;
import fr.legrain.moncompte.model.TaUtilisateur;


/**
 * SWTInfoEntreprise generated by hbm2java
 */
public class TaPanierDTO extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
//	private TaUtilisateur taUtilisateur;
	private Integer idTiers;
	private Date dateCreation;
	private Date datePaiement;
	private String libelle;
	private BigDecimal totalHT;
	private BigDecimal totalTTC;
	private BigDecimal totalTVA;
	private Boolean paye;

	
	private Integer versionObj;

	public TaPanierDTO() {
	}

	public TaPanierDTO(Integer id, Integer idTiers, Date dateCreation,
			Date datePaiement, String libelle, BigDecimal totalHT,
			BigDecimal totalTTC, BigDecimal totalTVA, Boolean paye,
			Integer versionObj) {
		super();
		this.id = id;
		this.idTiers = idTiers;
		this.dateCreation = dateCreation;
		this.datePaiement = datePaiement;
		this.libelle = libelle;
		this.totalHT = totalHT;
		this.totalTTC = totalTTC;
		this.totalTVA = totalTVA;
		this.paye = paye;
		this.versionObj = versionObj;
	}


	public void setSWTInfoEntreprise(TaPanierDTO taPanierDTO) {
		this.id = taPanierDTO.id;
		this.idTiers = taPanierDTO.idTiers;
		this.dateCreation = taPanierDTO.dateCreation;
		this.datePaiement = taPanierDTO.datePaiement;
		this.libelle = taPanierDTO.libelle;
		this.totalHT = taPanierDTO.totalHT;
		this.totalTTC = taPanierDTO.totalTTC;
		this.totalTVA = taPanierDTO.totalTVA;
		this.paye = taPanierDTO.paye;
		this.versionObj = taPanierDTO.versionObj;
	}
	
	public static TaPanierDTO copy(TaPanierDTO taPanierDTO){
		TaPanierDTO taPanierDTOLoc = new TaPanierDTO();
		taPanierDTOLoc.setId(taPanierDTO.id);
		taPanierDTOLoc.setIdTiers(taPanierDTO.idTiers);
		taPanierDTOLoc.setDateCreation(taPanierDTO.dateCreation);
		taPanierDTOLoc.setDatePaiement(taPanierDTO.datePaiement);
		taPanierDTOLoc.setLibelle(taPanierDTO.libelle);
		taPanierDTOLoc.setTotalHT(taPanierDTO.totalHT);
		taPanierDTOLoc.setTotalTTC(taPanierDTO.totalTTC);
		taPanierDTOLoc.setTotalTVA(taPanierDTO.totalTVA);
		taPanierDTOLoc.setVersionObj(taPanierDTO.versionObj);
		return taPanierDTOLoc;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}

	public Integer getIdTiers() {
		return idTiers;
	}

	public void setIdTiers(Integer idTiers) {
		firePropertyChange("idTiers", this.idTiers, this.idTiers = idTiers);
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		firePropertyChange("dateCreation", this.dateCreation, this.dateCreation = dateCreation);
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		firePropertyChange("datePaiement", this.datePaiement, this.datePaiement = datePaiement);
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		firePropertyChange("libelle", this.libelle, this.libelle = libelle);
	}

	public BigDecimal getTotalHT() {
		return totalHT;
	}

	public void setTotalHT(BigDecimal totalHT) {
		firePropertyChange("totalHT", this.totalHT, this.totalHT = totalHT);
	}

	public BigDecimal getTotalTTC() {
		return totalTTC;
	}

	public void setTotalTTC(BigDecimal totalTTC) {
		firePropertyChange("totalTTC", this.totalTTC, this.totalTTC = totalTTC);
	}

	public BigDecimal getTotalTVA() {
		return totalTVA;
	}

	public void setTotalTVA(BigDecimal totalTVA) {
		firePropertyChange("totalTVA", this.totalTVA, this.totalTVA = totalTVA);
	}

	public Boolean getPaye() {
		return paye;
	}

	public void setPaye(Boolean paye) {
		firePropertyChange("paye", this.paye, this.paye = paye);
	}

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		firePropertyChange("versionObj", this.versionObj, this.versionObj = versionObj);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result
				+ ((datePaiement == null) ? 0 : datePaiement.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((paye == null) ? 0 : paye.hashCode());
		result = prime * result + ((totalHT == null) ? 0 : totalHT.hashCode());
		result = prime * result
				+ ((totalTTC == null) ? 0 : totalTTC.hashCode());
		result = prime * result
				+ ((totalTVA == null) ? 0 : totalTVA.hashCode());
		result = prime * result
				+ ((versionObj == null) ? 0 : versionObj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaPanierDTO other = (TaPanierDTO) obj;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (datePaiement == null) {
			if (other.datePaiement != null)
				return false;
		} else if (!datePaiement.equals(other.datePaiement))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTiers == null) {
			if (other.idTiers != null)
				return false;
		} else if (!idTiers.equals(other.idTiers))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (paye == null) {
			if (other.paye != null)
				return false;
		} else if (!paye.equals(other.paye))
			return false;
		if (totalHT == null) {
			if (other.totalHT != null)
				return false;
		} else if (!totalHT.equals(other.totalHT))
			return false;
		if (totalTTC == null) {
			if (other.totalTTC != null)
				return false;
		} else if (!totalTTC.equals(other.totalTTC))
			return false;
		if (totalTVA == null) {
			if (other.totalTVA != null)
				return false;
		} else if (!totalTVA.equals(other.totalTVA))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}

	
}
