package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;

import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class TotauxBoncdeDTO extends ModelObject  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3627586259158301040L;
	private Integer id=0;
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
	
	private Boolean imprimer = false;

	public TotauxBoncdeDTO() {
	}

	public TotauxBoncdeDTO(Integer ID_Boncde, Integer ID_T_PAIEMENT, String CODE_T_PAIEMENT,
			Integer ID_C_PAIEMENT, BigDecimal REGLE_Boncde,
			BigDecimal REM_HT_Boncde, BigDecimal TX_REM_HT_Boncde,
			BigDecimal REM_TTC_Boncde, BigDecimal TX_REM_TTC_Boncde,
			BigDecimal MT_TTC_CALC, BigDecimal MT_HT_CALC,
			BigDecimal MT_TVA_CALC, BigDecimal NET_TTC_CALC, BigDecimal NET_HT_CALC,
			BigDecimal NET_TVA_CALC, Boolean imprimer,String COMMENTAIRE) {
		this.id = ID_Boncde;

		this.idTPaiement = ID_T_PAIEMENT;
		this.codeTPaiement = CODE_T_PAIEMENT;
		this.idCPaiement = ID_C_PAIEMENT;
		this.regleDocument = REGLE_Boncde;
		this.remHtDocument = REM_HT_Boncde;
		this.txRemHtDocument = TX_REM_HT_Boncde;
		this.remTtcDocument = REM_TTC_Boncde;
		this.txRemTtcDocument = TX_REM_TTC_Boncde;
		this.mtTtcCalc = MT_TTC_CALC;
		this.mtHtCalc = MT_HT_CALC;
		this.mtTvaCalc = MT_TVA_CALC;
		this.netTtcCalc = NET_TTC_CALC;
		this.netHtCalc = NET_HT_CALC;
		this.netTvaCalc = NET_TVA_CALC;
		this.imprimer = imprimer;
		this.commentaire = COMMENTAIRE;
	}

	public void setIHMTotauxBoncde(TotauxBoncdeDTO ihmTotauxBoncde){
		setId(ihmTotauxBoncde.id);
		setIdTPaiement(ihmTotauxBoncde.idTPaiement);
		setCodeTPaiement(ihmTotauxBoncde.codeTPaiement);
		setIdCPaiement(ihmTotauxBoncde.idCPaiement);
		setRegleDocument(ihmTotauxBoncde.regleDocument);
		setRemHtDocument(ihmTotauxBoncde.remHtDocument);
		setTxRemHtDocument(ihmTotauxBoncde.txRemHtDocument);
		setRemTtcDocument(ihmTotauxBoncde.remTtcDocument);
		setTxRemTtcDocument(ihmTotauxBoncde.txRemTtcDocument);
		setMtTtcCalc(ihmTotauxBoncde.mtTtcCalc);
		setMtHtCalc(ihmTotauxBoncde.mtHtCalc);
		setMtTvaCalc(ihmTotauxBoncde.mtTvaCalc);
		setNetTtcCalc(ihmTotauxBoncde.netTtcCalc);
		setNetHtCalc(ihmTotauxBoncde.netHtCalc);
		setNetTvaCalc(ihmTotauxBoncde.netTvaCalc);
		setImprimer(ihmTotauxBoncde.imprimer);
		setCommentaire(ihmTotauxBoncde.commentaire);
	}


	
	public static TotauxBoncdeDTO copy(TotauxBoncdeDTO ihmTotauxBoncde){
		TotauxBoncdeDTO ihmEnteteBoncdeLoc = new TotauxBoncdeDTO();
		ihmEnteteBoncdeLoc.setId(ihmTotauxBoncde.id);
		ihmEnteteBoncdeLoc.setIdTPaiement(ihmTotauxBoncde.idTPaiement);
		ihmEnteteBoncdeLoc.setCodeTPaiement(ihmTotauxBoncde.codeTPaiement);
		ihmEnteteBoncdeLoc.setIdCPaiement(ihmTotauxBoncde.idCPaiement);
		ihmEnteteBoncdeLoc.setRegleDocument(ihmTotauxBoncde.regleDocument);
		ihmEnteteBoncdeLoc.setRemHtDocument(ihmTotauxBoncde.remHtDocument);
		ihmEnteteBoncdeLoc.setTxRemHtDocument(ihmTotauxBoncde.txRemHtDocument);
		ihmEnteteBoncdeLoc.setRemTtcDocument(ihmTotauxBoncde.remTtcDocument);
		ihmEnteteBoncdeLoc.setTxRemTtcDocument(ihmTotauxBoncde.txRemTtcDocument);
		ihmEnteteBoncdeLoc.setMtTtcCalc(ihmTotauxBoncde.mtTtcCalc);
		ihmEnteteBoncdeLoc.setMtHtCalc(ihmTotauxBoncde.mtHtCalc);
		ihmEnteteBoncdeLoc.setMtTvaCalc(ihmTotauxBoncde.mtTvaCalc);
		ihmEnteteBoncdeLoc.setNetTtcCalc(ihmTotauxBoncde.netTtcCalc);
		ihmEnteteBoncdeLoc.setNetHtCalc(ihmTotauxBoncde.netHtCalc);
		ihmEnteteBoncdeLoc.setNetTvaCalc(ihmTotauxBoncde.netTvaCalc);
		ihmEnteteBoncdeLoc.setImprimer(ihmTotauxBoncde.imprimer);
		ihmEnteteBoncdeLoc.setCommentaire(ihmTotauxBoncde.commentaire);
		return ihmEnteteBoncdeLoc;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer ID_Boncde) {
		firePropertyChange("idDocument", this.id, this.id = ID_Boncde);
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



	public BigDecimal getRemHtDocument() {
		return this.remHtDocument;
	}

	public void setRemHtDocument(BigDecimal REM_HT_Boncde) {
		firePropertyChange("remHtDocument", this.remHtDocument, this.remHtDocument = REM_HT_Boncde);
	}

	public BigDecimal getTxRemHtDocument() {
		return this.txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal TX_REM_HT_Boncde) {
		firePropertyChange("txRemHtDocument", this.txRemHtDocument, this.txRemHtDocument = TX_REM_HT_Boncde);
	}

	public BigDecimal getRemTtcDocument() {
		return this.remTtcDocument;
	}

	public void setRemTtcDocument(BigDecimal REM_TTC_Boncde) {
		firePropertyChange("remTtcDocument", this.remTtcDocument, this.remTtcDocument = REM_TTC_Boncde);
	}

	public BigDecimal getTxRemTtcDocument() {
		return this.txRemTtcDocument;
	}

	public void setTxRemTtcDocument(BigDecimal TX_REM_TTC_Boncde) {
		firePropertyChange("txRemTtcDocument", this.txRemTtcDocument, this.txRemTtcDocument = TX_REM_TTC_Boncde);
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

	public BigDecimal getRegleDocument() {
		return regleDocument;
	}

	public void setRegleDocument(BigDecimal regleDocument) {
		firePropertyChange("regleDocument", this.regleDocument, this.regleDocument = regleDocument);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeTPaiement == null) ? 0 : codeTPaiement.hashCode());
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result
				+ ((idCPaiement == null) ? 0 : idCPaiement.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
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
		TotauxBoncdeDTO other = (TotauxBoncdeDTO) obj;
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
		if (idCPaiement == null) {
			if (other.idCPaiement != null)
				return false;
		} else if (!idCPaiement.equals(other.idCPaiement))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
