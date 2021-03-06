package fr.legrain.tiers.dto;

import fr.legrain.lib.data.ModelObject;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTCPaiement generated by hbm2java
 */
public class InfosCommentaireDTO extends ModelObject  implements java.io.Serializable {

	private String COMMENTAIRE;

	public InfosCommentaireDTO() {
	}

	public InfosCommentaireDTO(String COMMENTAIRE) {
		this.COMMENTAIRE = COMMENTAIRE;
	}
	
	public void setIHMInfosCPaiement(InfosCommentaireDTO swtCommentaire) {
		setCOMMENTAIRE(swtCommentaire.COMMENTAIRE);
	}
	
	public void setSWTCPaiement(TaCommentaireDTO swtCommentaire) {
		setCOMMENTAIRE(swtCommentaire.getCommentaire());
	}

	
	public static InfosCommentaireDTO copy(InfosCommentaireDTO swtCPaiement){
		InfosCommentaireDTO ihmInfosCPaiement = new InfosCommentaireDTO();
		ihmInfosCPaiement.setCOMMENTAIRE(swtCPaiement.getCOMMENTAIRE());
		return ihmInfosCPaiement;
	}

	public String getCOMMENTAIRE() {
		return this.COMMENTAIRE;
	}

	public void setCOMMENTAIRE(String COMMENTAIRE) {
		firePropertyChange("COMMENTAIRE", this.COMMENTAIRE, this.COMMENTAIRE = COMMENTAIRE);
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InfosCommentaireDTO))
			return false;
		InfosCommentaireDTO castOther = (InfosCommentaireDTO) other;

		return (((this.getCOMMENTAIRE() == castOther
						.getCOMMENTAIRE()) || (this.getCOMMENTAIRE() != null
						&& castOther.getCOMMENTAIRE() != null && this
						.getCOMMENTAIRE().equals(
								castOther.getCOMMENTAIRE()))));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCOMMENTAIRE() == null ? 0 : this.getCOMMENTAIRE()
						.hashCode());
		return result;
	}

}
