package fr.legrain.tiers.dto;

import fr.legrain.lib.data.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTCPaiement generated by hbm2java
 */
public class TaCommentaireDTO extends ModelObject implements java.io.Serializable {

	private String commentaire;

	public TaCommentaireDTO() {
	}

	public TaCommentaireDTO( String COMMENTAIRE) {
		this.commentaire = COMMENTAIRE;
	}
	
	public void setSWTCPaiement(TaCommentaireDTO swtCommentaire) {
		this.commentaire = swtCommentaire.commentaire;
	}
	
//	public TaCommentaireDTO setSWTCPaiement(IHMInfosCommentaire cp) {
//		setCommentaire(cp.getCOMMENTAIRE());
//		return this;
//	}

	
	public static TaCommentaireDTO copy(TaCommentaireDTO swtCommentaire){
		TaCommentaireDTO swtCommentaireLoc = new TaCommentaireDTO();
		swtCommentaireLoc.setCommentaire(swtCommentaire.getCommentaire());
		return swtCommentaireLoc;
	}


	@LgrHibernateValidated(champBd = "commentaire",table = "ta_commentaire",champEntite="commentaire", clazz = TaCommentaireDTO.class)
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
		if (!(other instanceof TaCommentaireDTO))
			return false;
		TaCommentaireDTO castOther = (TaCommentaireDTO) other;

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
	


}
