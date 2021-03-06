package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.document.model.TaFlash;
import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.LibDate;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class TaFlashDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 8598457189242824959L;
	
	private Integer id=0;
	private String codeFlash=LgrConstantes.C_STR_VIDE;
	private Date dateFlash=new Date();
	private Date dateTransfert=new Date();
	private Integer numLigneLDocument;
	private String libelleFlash=LgrConstantes.C_STR_VIDE;
	private String codeEtat;
	private String liblEtat;
	private Integer versionObj;
	
	private String numeroCommandeFournisseur;
	private Boolean gestionLot = false;
	
	private Integer idUtilisateur;
	private String nom = LgrConstantes.C_STR_VIDE;
	private Integer idTiers;
	private String codeTiers = LgrConstantes.C_STR_VIDE;
	private String nomTiers = LgrConstantes.C_STR_VIDE;
	private Integer idGroupePreparation;
	private String codeGroupePreparation = LgrConstantes.C_STR_VIDE;
	private String codeTDoc;
	private Integer idTDoc;
	
	private List<TaLFlashDTO> lignesDTO;
	
	public TaFlashDTO() {
	}
	




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getCodeFlash() {
		return codeFlash;
	}




	public void setCodeFlash(String codeFlash) {
		this.codeFlash = codeFlash;
	}




	public Date getDateFlash() {
		return dateFlash;
	}




	public void setDateFlash(Date dateFlash) {
		this.dateFlash = dateFlash;
	}




	public Date getDateTransfert() {
		return dateTransfert;
	}




	public void setDateTransfert(Date dateTransfert) {
		this.dateTransfert = dateTransfert;
	}




	public Integer getNumLigneLDocument() {
		return numLigneLDocument;
	}




	public void setNumLigneLDocument(Integer numLigneLDocument) {
		this.numLigneLDocument = numLigneLDocument;
	}




	public String getLibelleFlash() {
		return libelleFlash;
	}




	public void setLibelleFlash(String libelleFlash) {
		this.libelleFlash = libelleFlash;
	}




	public String getCodeEtat() {
		return codeEtat;
	}




	public void setCodeEtat(String codeEtat) {
		this.codeEtat = codeEtat;
	}




	public String getLiblEtat() {
		return liblEtat;
	}




	public void setLiblEtat(String liblEtat) {
		this.liblEtat = liblEtat;
	}




	public Integer getVersionObj() {
		return versionObj;
	}




	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}




	public String getNumeroCommandeFournisseur() {
		return numeroCommandeFournisseur;
	}




	public void setNumeroCommandeFournisseur(String numeroCommandeFournisseur) {
		this.numeroCommandeFournisseur = numeroCommandeFournisseur;
	}




	public Boolean getGestionLot() {
		return gestionLot;
	}




	public void setGestionLot(Boolean gestionLot) {
		this.gestionLot = gestionLot;
	}




	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}




	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}




	public String getNom() {
		return nom;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public Integer getIdTiers() {
		return idTiers;
	}




	public void setIdTiers(Integer idTiers) {
		this.idTiers = idTiers;
	}




	public String getCodeTiers() {
		return codeTiers;
	}




	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}




	public Integer getIdGroupePreparation() {
		return idGroupePreparation;
	}




	public void setIdGroupePreparation(Integer idGroupePreparation) {
		this.idGroupePreparation = idGroupePreparation;
	}




	public String getCodeGroupePreparation() {
		return codeGroupePreparation;
	}




	public void setCodeGroupePreparation(String codeGroupePreparation) {
		this.codeGroupePreparation = codeGroupePreparation;
	}




	public List<TaLFlashDTO> getLignesDTO() {
		return lignesDTO;
	}




	public void setLignesDTO(List<TaLFlashDTO> lignesDTO) {
		this.lignesDTO = lignesDTO;
	}





	public String getCodeTDoc() {
		return codeTDoc;
	}





	public void setCodeTDoc(String codeTDoc) {
		this.codeTDoc = codeTDoc;
	}





	public Integer getIdTDoc() {
		return idTDoc;
	}





	public void setIdTDoc(Integer idTDoc) {
		this.idTDoc = idTDoc;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeEtat == null) ? 0 : codeEtat.hashCode());
		result = prime * result + ((codeFlash == null) ? 0 : codeFlash.hashCode());
		result = prime * result + ((codeGroupePreparation == null) ? 0 : codeGroupePreparation.hashCode());
		result = prime * result + ((codeTDoc == null) ? 0 : codeTDoc.hashCode());
		result = prime * result + ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result + ((dateFlash == null) ? 0 : dateFlash.hashCode());
		result = prime * result + ((dateTransfert == null) ? 0 : dateTransfert.hashCode());
		result = prime * result + ((gestionLot == null) ? 0 : gestionLot.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idGroupePreparation == null) ? 0 : idGroupePreparation.hashCode());
		result = prime * result + ((idTDoc == null) ? 0 : idTDoc.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result + ((idUtilisateur == null) ? 0 : idUtilisateur.hashCode());
		result = prime * result + ((libelleFlash == null) ? 0 : libelleFlash.hashCode());
		result = prime * result + ((liblEtat == null) ? 0 : liblEtat.hashCode());
		result = prime * result + ((lignesDTO == null) ? 0 : lignesDTO.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numLigneLDocument == null) ? 0 : numLigneLDocument.hashCode());
		result = prime * result + ((numeroCommandeFournisseur == null) ? 0 : numeroCommandeFournisseur.hashCode());
		result = prime * result + ((versionObj == null) ? 0 : versionObj.hashCode());
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
		TaFlashDTO other = (TaFlashDTO) obj;
		if (codeEtat == null) {
			if (other.codeEtat != null)
				return false;
		} else if (!codeEtat.equals(other.codeEtat))
			return false;
		if (codeFlash == null) {
			if (other.codeFlash != null)
				return false;
		} else if (!codeFlash.equals(other.codeFlash))
			return false;
		if (codeGroupePreparation == null) {
			if (other.codeGroupePreparation != null)
				return false;
		} else if (!codeGroupePreparation.equals(other.codeGroupePreparation))
			return false;
		if (codeTDoc == null) {
			if (other.codeTDoc != null)
				return false;
		} else if (!codeTDoc.equals(other.codeTDoc))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
			return false;
		if (dateFlash == null) {
			if (other.dateFlash != null)
				return false;
		} else if (!dateFlash.equals(other.dateFlash))
			return false;
		if (dateTransfert == null) {
			if (other.dateTransfert != null)
				return false;
		} else if (!dateTransfert.equals(other.dateTransfert))
			return false;
		if (gestionLot == null) {
			if (other.gestionLot != null)
				return false;
		} else if (!gestionLot.equals(other.gestionLot))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idGroupePreparation == null) {
			if (other.idGroupePreparation != null)
				return false;
		} else if (!idGroupePreparation.equals(other.idGroupePreparation))
			return false;
		if (idTDoc == null) {
			if (other.idTDoc != null)
				return false;
		} else if (!idTDoc.equals(other.idTDoc))
			return false;
		if (idTiers == null) {
			if (other.idTiers != null)
				return false;
		} else if (!idTiers.equals(other.idTiers))
			return false;
		if (idUtilisateur == null) {
			if (other.idUtilisateur != null)
				return false;
		} else if (!idUtilisateur.equals(other.idUtilisateur))
			return false;
		if (libelleFlash == null) {
			if (other.libelleFlash != null)
				return false;
		} else if (!libelleFlash.equals(other.libelleFlash))
			return false;
		if (liblEtat == null) {
			if (other.liblEtat != null)
				return false;
		} else if (!liblEtat.equals(other.liblEtat))
			return false;
		if (lignesDTO == null) {
			if (other.lignesDTO != null)
				return false;
		} else if (!lignesDTO.equals(other.lignesDTO))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numLigneLDocument == null) {
			if (other.numLigneLDocument != null)
				return false;
		} else if (!numLigneLDocument.equals(other.numLigneLDocument))
			return false;
		if (numeroCommandeFournisseur == null) {
			if (other.numeroCommandeFournisseur != null)
				return false;
		} else if (!numeroCommandeFournisseur.equals(other.numeroCommandeFournisseur))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}





	public String getNomTiers() {
		return nomTiers;
	}





	public void setNomTiers(String nomTiers) {
		this.nomTiers = nomTiers;
	}



	
	
	
}
