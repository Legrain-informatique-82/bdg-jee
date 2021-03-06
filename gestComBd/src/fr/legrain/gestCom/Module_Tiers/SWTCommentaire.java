package fr.legrain.gestCom.Module_Tiers;

import fr.legrain.gestCom.Module_Document.IHMCommentaire;
import fr.legrain.lib.data.ModelObject;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTCPaiement generated by hbm2java
 */
public class SWTCommentaire extends ModelObject {

	private String commentaire;

	public SWTCommentaire() {
	}

	public SWTCommentaire( String COMMENTAIRE) {
		this.commentaire = COMMENTAIRE;
	}
	
	public void setSWTCPaiement(SWTCommentaire swtCommentaire) {
		this.commentaire = swtCommentaire.commentaire;
	}
	
	public SWTCommentaire setSWTCPaiement(IHMInfosCommentaire cp) {
		setCommentaire(cp.getCOMMENTAIRE());
		return this;
	}

	
	public static SWTCommentaire copy(SWTCommentaire swtCommentaire){
		SWTCommentaire swtCommentaireLoc = new SWTCommentaire();
		swtCommentaireLoc.setCommentaire(swtCommentaire.getCommentaire());
		return swtCommentaireLoc;
	}



	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String COMMENTAIRE) {
		firePropertyChange("commentaire", this.commentaire, this.commentaire = COMMENTAIRE);
	}




	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SWTCommentaire))
			return false;
		SWTCommentaire castOther = (SWTCommentaire) other;

		return (((this.getCommentaire() == castOther
				.getCommentaire()) || (this.getCommentaire() != null
						&& castOther.getCommentaire() != null && this
						.getCommentaire().equals(
								castOther.getCommentaire())))
		);
	}

	public int hashCode() {
		int result = 17;
		result = 37
		* result
		+ (getCommentaire() == null ? 0 : this.getCommentaire()
				.hashCode());
		return result;
	}
	
	public SWTCommentaire setSWTCPaiement(IHMCommentaire ihmCommentaire) {
		setCommentaire(ihmCommentaire.getCOMMENTAIRE());
		return this;
	}

}
