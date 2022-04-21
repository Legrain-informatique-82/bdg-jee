package fr.legrain.gestCom.Module_Document;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;

import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class IHMTotauxAcompte extends ModelObject {

	private Integer idDocument=0;
	private Integer idTPaiement=0;
	private String codeTPaiement=LgrConstantes.C_STR_VIDE;
	private Integer idCPaiement=0;
	private BigDecimal regleDocument=new BigDecimal(0);
	private BigDecimal remHtDocument=new BigDecimal(0);
	private BigDecimal txRemHtDocument=new BigDecimal(0);
	private BigDecimal remTtcDocument=new BigDecimal(0);
	private BigDecimal txRemTtcDocument=new BigDecimal(0);
	private BigDecimal mtTtcCalc=new BigDecimal(0);
	private BigDecimal mtHtCalc=new BigDecimal(0);
	private BigDecimal mtTvaCalc=new BigDecimal(0);
	private BigDecimal netTtcCalc=new BigDecimal(0);
	private BigDecimal netHtCalc=new BigDecimal(0);
	private BigDecimal netTvaCalc=new BigDecimal(0);
	private String commentaire = "";
	private String compte=LgrConstantes.C_STR_VIDE;
	private Integer idCompteBanque=0;
	
	private Boolean imprimer = false;

	public IHMTotauxAcompte() {
	}

	public IHMTotauxAcompte(Integer ID_FACTURE, Integer ID_T_PAIEMENT, String CODE_T_PAIEMENT,
			Integer ID_C_PAIEMENT, BigDecimal REGLE_FACTURE,
			BigDecimal REM_HT_FACTURE, BigDecimal TX_REM_HT_FACTURE,
			BigDecimal REM_TTC_FACTURE, BigDecimal TX_REM_TTC_FACTURE,
			BigDecimal MT_TTC_CALC, BigDecimal MT_HT_CALC,
			BigDecimal MT_TVA_CALC, BigDecimal NET_TTC_CALC, BigDecimal NET_HT_CALC,
			BigDecimal NET_TVA_CALC, Boolean imprimer,String COMMENTAIRE) {
		this.idDocument = ID_FACTURE;

		this.idTPaiement = ID_T_PAIEMENT;
		this.codeTPaiement = CODE_T_PAIEMENT;
		this.idCPaiement = ID_C_PAIEMENT;
		this.regleDocument = REGLE_FACTURE;
		this.remHtDocument = REM_HT_FACTURE;
		this.txRemHtDocument = TX_REM_HT_FACTURE;
		this.remTtcDocument = REM_TTC_FACTURE;
		this.txRemTtcDocument = TX_REM_TTC_FACTURE;
		this.mtTtcCalc = MT_TTC_CALC;
		this.mtHtCalc = MT_HT_CALC;
		this.mtTvaCalc = MT_TVA_CALC;
		this.netTtcCalc = NET_TTC_CALC;
		this.netHtCalc = NET_HT_CALC;
		this.netTvaCalc = NET_TVA_CALC;
		this.imprimer = imprimer;
		this.commentaire = COMMENTAIRE;
	}

	public void setIHMTotauxFacture(IHMTotauxAcompte ihmTotauxFacture){
		setIdDocument(ihmTotauxFacture.idDocument);
		setIdTPaiement(ihmTotauxFacture.idTPaiement);
		setCodeTPaiement(ihmTotauxFacture.codeTPaiement);
		setIdCPaiement(ihmTotauxFacture.idCPaiement);
		setRegleDocument(ihmTotauxFacture.regleDocument);
		setRemHtDocument(ihmTotauxFacture.remHtDocument);
		setTxRemHtDocument(ihmTotauxFacture.txRemHtDocument);
		setRemTtcDocument(ihmTotauxFacture.remTtcDocument);
		setTxRemTtcDocument(ihmTotauxFacture.txRemTtcDocument);
		setMtTtcCalc(ihmTotauxFacture.mtTtcCalc);
		setMtHtCalc(ihmTotauxFacture.mtHtCalc);
		setMtTvaCalc(ihmTotauxFacture.mtTvaCalc);
		setNetTtcCalc(ihmTotauxFacture.netTtcCalc);
		setNetHtCalc(ihmTotauxFacture.netHtCalc);
		setNetTvaCalc(ihmTotauxFacture.netTvaCalc);
		setImprimer(ihmTotauxFacture.imprimer);
		setCommentaire(ihmTotauxFacture.commentaire);
	}
	

	
	public static IHMTotauxAcompte copy(IHMTotauxAcompte ihmTotauxFacture){
		IHMTotauxAcompte ihmEnteteFactureLoc = new IHMTotauxAcompte();
		ihmEnteteFactureLoc.setIdDocument(ihmTotauxFacture.idDocument);
		ihmEnteteFactureLoc.setIdTPaiement(ihmTotauxFacture.idTPaiement);
		ihmEnteteFactureLoc.setCodeTPaiement(ihmTotauxFacture.codeTPaiement);
		ihmEnteteFactureLoc.setIdCPaiement(ihmTotauxFacture.idCPaiement);
		ihmEnteteFactureLoc.setRegleDocument(ihmTotauxFacture.regleDocument);
		ihmEnteteFactureLoc.setRemHtDocument(ihmTotauxFacture.remHtDocument);
		ihmEnteteFactureLoc.setTxRemHtDocument(ihmTotauxFacture.txRemHtDocument);
		ihmEnteteFactureLoc.setRemTtcDocument(ihmTotauxFacture.remTtcDocument);
		ihmEnteteFactureLoc.setTxRemTtcDocument(ihmTotauxFacture.txRemTtcDocument);
		ihmEnteteFactureLoc.setMtTtcCalc(ihmTotauxFacture.mtTtcCalc);
		ihmEnteteFactureLoc.setMtHtCalc(ihmTotauxFacture.mtHtCalc);
		ihmEnteteFactureLoc.setMtTvaCalc(ihmTotauxFacture.mtTvaCalc);
		ihmEnteteFactureLoc.setNetTtcCalc(ihmTotauxFacture.netTtcCalc);
		ihmEnteteFactureLoc.setNetHtCalc(ihmTotauxFacture.netHtCalc);
		ihmEnteteFactureLoc.setNetTvaCalc(ihmTotauxFacture.netTvaCalc);
		ihmEnteteFactureLoc.setImprimer(ihmTotauxFacture.imprimer);
		ihmEnteteFactureLoc.setCommentaire(ihmTotauxFacture.commentaire);
		return ihmEnteteFactureLoc;
	}
	
//	public boolean FactureEstVide(){
//		Date dateExemple = new Date();
////		if(!getID_T_PAIEMENT().equals(0))return false;
////		if(!getCODE_T_PAIEMENT().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_C_PAIEMENT().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getREGLE_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getREM_HT_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_HT_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getREM_TTC_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_TTC_FACTURE()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TVA_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TVA_CALC()))return false;
//		return true;
//	}
	
//	public boolean FactureEstVide(Date dateDansExercice){
//		Date dateExemple = new Date();
////		if(!getID_T_PAIEMENT().equals(0))return false;
////		if(!getCODE_T_PAIEMENT().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_C_PAIEMENT().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getREGLE_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getREM_HT_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_HT_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getREM_TTC_FACTURE()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_TTC_FACTURE()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TVA_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TVA_CALC()))return false;
//		return true;
//	}

	public Integer getIdDocument() {
		return this.idDocument;
	}

	public void setIdDocument(Integer ID_FACTURE) {
		firePropertyChange("idDocument", this.idDocument, this.idDocument = ID_FACTURE);
	}

	
	public Integer getIdTPaiement() {
		return this.idTPaiement;
	}

	public void setIdTPaiement(Integer ID_T_PAIEMENT) {
		firePropertyChange("idTPaiement", this.idTPaiement, this.idTPaiement = ID_T_PAIEMENT);
	}

	public String getCodeTPaiement() {
		return this.codeTPaiement;
	}

	public void setCodeTPaiement(String CODE_T_PAIEMENT) {
		firePropertyChange("codeTPaiement", this.codeTPaiement, this.codeTPaiement = CODE_T_PAIEMENT);
	}

	public Integer getIdCPaiement() {
		return this.idCPaiement;
	}

	public void setIdCPaiement(Integer ID_C_PAIEMENT) {
		firePropertyChange("idCPaiement", this.idCPaiement, this.idCPaiement = ID_C_PAIEMENT);
	}

	public BigDecimal getRegleDocument() {
		return this.regleDocument;
	}

	public void setRegleDocument(BigDecimal REGLE_FACTURE) {
		firePropertyChange("regleDocument", this.regleDocument, this.regleDocument = REGLE_FACTURE);
	}

	public BigDecimal getRemHtDocument() {
		return this.remHtDocument;
	}

	public void setRemHtDocument(BigDecimal REM_HT_FACTURE) {
		firePropertyChange("remHtDocument", this.remHtDocument, this.remHtDocument = REM_HT_FACTURE);
	}

	public BigDecimal getTxRemHtDocument() {
		return this.txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal TX_REM_HT_FACTURE) {
		firePropertyChange("txRemHtDocument", this.txRemHtDocument, this.txRemHtDocument = TX_REM_HT_FACTURE);
	}

	public BigDecimal getRemTtcDocument() {
		return this.remTtcDocument;
	}

	public void setRemTtcDocument(BigDecimal REM_TTC_FACTURE) {
		firePropertyChange("remTtcDocument", this.remTtcDocument, this.remTtcDocument = REM_TTC_FACTURE);
	}

	public BigDecimal getTxRemTtcDocument() {
		return this.txRemTtcDocument;
	}

	public void setTxRemTtcDocument(BigDecimal TX_REM_TTC_FACTURE) {
		firePropertyChange("txRemTtcDocument", this.txRemTtcDocument, this.txRemTtcDocument = TX_REM_TTC_FACTURE);
	}


	public BigDecimal getMtTtcCalc() {
		return this.mtTtcCalc;
	}

	public void setMtTtcCalc(BigDecimal MT_TTC_CALC) {
		firePropertyChange("mtTtcCalc", this.mtTtcCalc, this.mtTtcCalc = MT_TTC_CALC);
	}

	public BigDecimal getMtHtCalc() {
		return this.mtHtCalc;
	}

	public void setMtHtCalc(BigDecimal MT_HT_CALC) {
		firePropertyChange("mtHtCalc", this.mtHtCalc, this.mtHtCalc = MT_HT_CALC);
	}

	public BigDecimal getMtTvaCalc() {
		return this.mtTvaCalc;
	}

	public void setMtTvaCalc(BigDecimal MT_TVA_CALC) {
		firePropertyChange("mtTvaCalc", this.mtTvaCalc, this.mtTvaCalc = MT_TVA_CALC);
	}

	public BigDecimal getNetTtcCalc() {
		return this.netTtcCalc;
	}

	public void setNetTtcCalc(BigDecimal NET_TTC_CALC) {
		firePropertyChange("netTtcCalc", this.netTtcCalc, this.netTtcCalc = NET_TTC_CALC);
	}

	public BigDecimal getNetHtCalc() {
		return this.netHtCalc;
	}

	public void setNetHtCalc(BigDecimal NET_HT_CALC) {
		firePropertyChange("netHtCalc", this.netHtCalc, this.netHtCalc = NET_HT_CALC);
	}

	public BigDecimal getNetTvaCalc() {
		return this.netTvaCalc;
	}

	public void setNetTvaCalc(BigDecimal NET_TVA_CALC) {
		firePropertyChange("netTvaCalc", this.netTvaCalc, this.netTvaCalc = NET_TVA_CALC);
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String COMMENTAIRE) {
		firePropertyChange("commentaire", this.commentaire, this.commentaire = COMMENTAIRE);
	}



	public Boolean getImprimer() {
		return imprimer;
	}

	public void setImprimer(Boolean imprimer) {
		firePropertyChange("imprimer", this.imprimer, this.imprimer = imprimer);
	}

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		firePropertyChange("compte", this.compte, this.compte = compte);
	}

	public Integer getIdCompteBanque() {
		return idCompteBanque;
	}

	public void setIdCompteBanque(Integer idCompteBanque) {
		firePropertyChange("idCompteBanque", this.idCompteBanque, this.idCompteBanque = idCompteBanque);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeTPaiement == null) ? 0 : codeTPaiement.hashCode());
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result
				+ ((idCPaiement == null) ? 0 : idCPaiement.hashCode());
		result = prime * result
				+ ((idCompteBanque == null) ? 0 : idCompteBanque.hashCode());
		result = prime * result
				+ ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result
				+ ((idTPaiement == null) ? 0 : idTPaiement.hashCode());
		result = prime * result
				+ ((imprimer == null) ? 0 : imprimer.hashCode());
		result = prime * result
				+ ((mtHtCalc == null) ? 0 : mtHtCalc.hashCode());
		result = prime * result
				+ ((mtTtcCalc == null) ? 0 : mtTtcCalc.hashCode());
		result = prime * result
				+ ((mtTvaCalc == null) ? 0 : mtTvaCalc.hashCode());
		result = prime * result
				+ ((netHtCalc == null) ? 0 : netHtCalc.hashCode());
		result = prime * result
				+ ((netTtcCalc == null) ? 0 : netTtcCalc.hashCode());
		result = prime * result
				+ ((netTvaCalc == null) ? 0 : netTvaCalc.hashCode());
		result = prime * result
				+ ((regleDocument == null) ? 0 : regleDocument.hashCode());
		result = prime * result
				+ ((remHtDocument == null) ? 0 : remHtDocument.hashCode());
		result = prime * result
				+ ((remTtcDocument == null) ? 0 : remTtcDocument.hashCode());
		result = prime * result
				+ ((txRemHtDocument == null) ? 0 : txRemHtDocument.hashCode());
		result = prime
				* result
				+ ((txRemTtcDocument == null) ? 0 : txRemTtcDocument.hashCode());
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
		IHMTotauxAcompte other = (IHMTotauxAcompte) obj;
		if (codeTPaiement == null) {
			if (other.codeTPaiement != null)
				return false;
		} else if (!codeTPaiement.equals(other.codeTPaiement))
			return false;
		if (commentaire == null) {
			if (other.commentaire != null)
				return false;
		} else if (!commentaire.equals(other.commentaire))
			return false;
		if (compte == null) {
			if (other.compte != null)
				return false;
		} else if (!compte.equals(other.compte))
			return false;
		if (idCPaiement == null) {
			if (other.idCPaiement != null)
				return false;
		} else if (!idCPaiement.equals(other.idCPaiement))
			return false;
		if (idCompteBanque == null) {
			if (other.idCompteBanque != null)
				return false;
		} else if (!idCompteBanque.equals(other.idCompteBanque))
			return false;
		if (idDocument == null) {
			if (other.idDocument != null)
				return false;
		} else if (!idDocument.equals(other.idDocument))
			return false;
		if (idTPaiement == null) {
			if (other.idTPaiement != null)
				return false;
		} else if (!idTPaiement.equals(other.idTPaiement))
			return false;
		if (imprimer == null) {
			if (other.imprimer != null)
				return false;
		} else if (!imprimer.equals(other.imprimer))
			return false;
		if (mtHtCalc == null) {
			if (other.mtHtCalc != null)
				return false;
		} else if (!mtHtCalc.equals(other.mtHtCalc))
			return false;
		if (mtTtcCalc == null) {
			if (other.mtTtcCalc != null)
				return false;
		} else if (!mtTtcCalc.equals(other.mtTtcCalc))
			return false;
		if (mtTvaCalc == null) {
			if (other.mtTvaCalc != null)
				return false;
		} else if (!mtTvaCalc.equals(other.mtTvaCalc))
			return false;
		if (netHtCalc == null) {
			if (other.netHtCalc != null)
				return false;
		} else if (!netHtCalc.equals(other.netHtCalc))
			return false;
		if (netTtcCalc == null) {
			if (other.netTtcCalc != null)
				return false;
		} else if (!netTtcCalc.equals(other.netTtcCalc))
			return false;
		if (netTvaCalc == null) {
			if (other.netTvaCalc != null)
				return false;
		} else if (!netTvaCalc.equals(other.netTvaCalc))
			return false;
		if (regleDocument == null) {
			if (other.regleDocument != null)
				return false;
		} else if (!regleDocument.equals(other.regleDocument))
			return false;
		if (remHtDocument == null) {
			if (other.remHtDocument != null)
				return false;
		} else if (!remHtDocument.equals(other.remHtDocument))
			return false;
		if (remTtcDocument == null) {
			if (other.remTtcDocument != null)
				return false;
		} else if (!remTtcDocument.equals(other.remTtcDocument))
			return false;
		if (txRemHtDocument == null) {
			if (other.txRemHtDocument != null)
				return false;
		} else if (!txRemHtDocument.equals(other.txRemHtDocument))
			return false;
		if (txRemTtcDocument == null) {
			if (other.txRemTtcDocument != null)
				return false;
		} else if (!txRemTtcDocument.equals(other.txRemTtcDocument))
			return false;
		return true;
	}




	

}
