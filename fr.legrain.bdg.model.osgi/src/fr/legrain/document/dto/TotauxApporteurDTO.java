package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;

import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteApporteur generated by hbm2java
 */
public class TotauxApporteurDTO extends ModelObject  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5505918123225376260L;
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

	public TotauxApporteurDTO() {
	}

	public TotauxApporteurDTO(Integer ID_APPORTEUR, Integer ID_T_PAIEMENT, String CODE_T_PAIEMENT,
			Integer ID_C_PAIEMENT, BigDecimal REGLE_APPORTEUR,
			BigDecimal REM_HT_APPORTEUR, BigDecimal TX_REM_HT_APPORTEUR,
			BigDecimal REM_TTC_APPORTEUR, BigDecimal TX_REM_TTC_APPORTEUR,
			BigDecimal MT_TTC_CALC, BigDecimal MT_HT_CALC,
			BigDecimal MT_TVA_CALC, BigDecimal NET_TTC_CALC, BigDecimal NET_HT_CALC,
			BigDecimal NET_TVA_CALC, Boolean imprimer,String COMMENTAIRE) {
		this.id = ID_APPORTEUR;

		this.idTPaiement = ID_T_PAIEMENT;
		this.codeTPaiement = CODE_T_PAIEMENT;
		this.idCPaiement = ID_C_PAIEMENT;
		this.regleDocument = REGLE_APPORTEUR;
		this.remHtDocument = REM_HT_APPORTEUR;
		this.txRemHtDocument = TX_REM_HT_APPORTEUR;
		this.remTtcDocument = REM_TTC_APPORTEUR;
		this.txRemTtcDocument = TX_REM_TTC_APPORTEUR;
		this.mtTtcCalc = MT_TTC_CALC;
		this.mtHtCalc = MT_HT_CALC;
		this.mtTvaCalc = MT_TVA_CALC;
		this.netTtcCalc = NET_TTC_CALC;
		this.netHtCalc = NET_HT_CALC;
		this.netTvaCalc = NET_TVA_CALC;
		this.imprimer = imprimer;
		this.commentaire = COMMENTAIRE;
	}

	public void setIHMTotauxApporteur(TotauxApporteurDTO ihmTotauxApporteur){
		setId(ihmTotauxApporteur.id);
		setIdTPaiement(ihmTotauxApporteur.idTPaiement);
		setCodeTPaiement(ihmTotauxApporteur.codeTPaiement);
		setIdCPaiement(ihmTotauxApporteur.idCPaiement);
		setRegleDocument(ihmTotauxApporteur.regleDocument);
		setRemHtDocument(ihmTotauxApporteur.remHtDocument);
		setTxRemHtDocument(ihmTotauxApporteur.txRemHtDocument);
		setRemTtcDocument(ihmTotauxApporteur.remTtcDocument);
		setTxRemTtcDocument(ihmTotauxApporteur.txRemTtcDocument);
		setMtTtcCalc(ihmTotauxApporteur.mtTtcCalc);
		setMtHtCalc(ihmTotauxApporteur.mtHtCalc);
		setMtTvaCalc(ihmTotauxApporteur.mtTvaCalc);
		setNetTtcCalc(ihmTotauxApporteur.netTtcCalc);
		setNetHtCalc(ihmTotauxApporteur.netHtCalc);
		setNetTvaCalc(ihmTotauxApporteur.netTvaCalc);
		setImprimer(ihmTotauxApporteur.imprimer);
		setCommentaire(ihmTotauxApporteur.commentaire);
	}
	
//	public void setIHMTotauxApporteur(SWTEnteteApporteur swtEnteteApporteur){
//		setIdApporteur(swtEnteteApporteur.getID_APPORTEUR());
//		setIdTPaiement(swtEnteteApporteur.getID_T_PAIEMENT());
//		setCodeTPaiement(swtEnteteApporteur.getCODE_T_PAIEMENT());
//		setIdCPaiement(swtEnteteApporteur.getID_C_PAIEMENT());
//		setRegleApporteur(swtEnteteApporteur.getREGLE_APPORTEUR());
//		setRemHtApporteur(swtEnteteApporteur.getREM_HT_APPORTEUR());
//		setTxRemHtApporteur(swtEnteteApporteur.getTX_REM_HT_APPORTEUR());
//		setRemTtcApporteur(swtEnteteApporteur.getREM_TTC_APPORTEUR());
//		setTxRemTtcApporteur(swtEnteteApporteur.getTX_REM_TTC_APPORTEUR());
//		setMtTtcCalc(swtEnteteApporteur.getMT_TTC_CALC());
//		setMtHtCalc(swtEnteteApporteur.getMT_HT_CALC());
//		setMtTvaCalc(swtEnteteApporteur.getMT_TVA_CALC());
//		setNetHtCalc(swtEnteteApporteur.getNET_HT_CALC());
//		setNetTvaCalc(swtEnteteApporteur.getNET_TVA_CALC());
//		setNetTtcCalc(swtEnteteApporteur.getNET_TTC_CALC());
//		setImprimer(swtEnteteApporteur.getImprimer());
//		setCommentaire(swtEnteteApporteur.getCOMMENTAIRE());
//	}

	
	public static TotauxApporteurDTO copy(TotauxApporteurDTO ihmTotauxApporteur){
		TotauxApporteurDTO ihmEnteteApporteurLoc = new TotauxApporteurDTO();
		ihmEnteteApporteurLoc.setId(ihmTotauxApporteur.id);
		ihmEnteteApporteurLoc.setIdTPaiement(ihmTotauxApporteur.idTPaiement);
		ihmEnteteApporteurLoc.setCodeTPaiement(ihmTotauxApporteur.codeTPaiement);
		ihmEnteteApporteurLoc.setIdCPaiement(ihmTotauxApporteur.idCPaiement);
		ihmEnteteApporteurLoc.setRegleDocument(ihmTotauxApporteur.regleDocument);
		ihmEnteteApporteurLoc.setRemHtDocument(ihmTotauxApporteur.remHtDocument);
		ihmEnteteApporteurLoc.setTxRemHtDocument(ihmTotauxApporteur.txRemHtDocument);
		ihmEnteteApporteurLoc.setRemTtcDocument(ihmTotauxApporteur.remTtcDocument);
		ihmEnteteApporteurLoc.setTxRemTtcDocument(ihmTotauxApporteur.txRemTtcDocument);
		ihmEnteteApporteurLoc.setMtTtcCalc(ihmTotauxApporteur.mtTtcCalc);
		ihmEnteteApporteurLoc.setMtHtCalc(ihmTotauxApporteur.mtHtCalc);
		ihmEnteteApporteurLoc.setMtTvaCalc(ihmTotauxApporteur.mtTvaCalc);
		ihmEnteteApporteurLoc.setNetTtcCalc(ihmTotauxApporteur.netTtcCalc);
		ihmEnteteApporteurLoc.setNetHtCalc(ihmTotauxApporteur.netHtCalc);
		ihmEnteteApporteurLoc.setNetTvaCalc(ihmTotauxApporteur.netTvaCalc);
		ihmEnteteApporteurLoc.setImprimer(ihmTotauxApporteur.imprimer);
		ihmEnteteApporteurLoc.setCommentaire(ihmTotauxApporteur.commentaire);
		return ihmEnteteApporteurLoc;
	}
	
//	public boolean ApporteurEstVide(){
//		Date dateExemple = new Date();
////		if(!getID_T_PAIEMENT().equals(0))return false;
////		if(!getCODE_T_PAIEMENT().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_C_PAIEMENT().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getREGLE_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getREM_HT_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_HT_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getREM_TTC_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_TTC_APPORTEUR()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TVA_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TVA_CALC()))return false;
//		return true;
//	}
	
//	public boolean ApporteurEstVide(Date dateDansExercice){
//		Date dateExemple = new Date();
////		if(!getID_T_PAIEMENT().equals(0))return false;
////		if(!getCODE_T_PAIEMENT().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_C_PAIEMENT().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getREGLE_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getREM_HT_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_HT_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getREM_TTC_APPORTEUR()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_TTC_APPORTEUR()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getMT_TVA_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TTC_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_HT_CALC()))return false;
////		if(!LibChaine.emptyNumeric(getNET_TVA_CALC()))return false;
//		return true;
//	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer ID_APPORTEUR) {
		firePropertyChange("idDocument", this.id, this.id = ID_APPORTEUR);
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

	public void setRegleDocument(BigDecimal REGLE_APPORTEUR) {
		firePropertyChange("regleDocument", this.regleDocument, this.regleDocument = REGLE_APPORTEUR);
	}

	public BigDecimal getRemHtDocument() {
		return this.remHtDocument;
	}

	public void setRemHtDocument(BigDecimal REM_HT_APPORTEUR) {
		firePropertyChange("remHtDocument", this.remHtDocument, this.remHtDocument = REM_HT_APPORTEUR);
	}

	public BigDecimal getTxRemHtDocument() {
		return this.txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal TX_REM_HT_APPORTEUR) {
		firePropertyChange("txRemHtDocument", this.txRemHtDocument, this.txRemHtDocument = TX_REM_HT_APPORTEUR);
	}

	public BigDecimal getRemTtcDocument() {
		return this.remTtcDocument;
	}

	public void setRemTtcDocument(BigDecimal REM_TTC_APPORTEUR) {
		firePropertyChange("remTtcDocument", this.remTtcDocument, this.remTtcDocument = REM_TTC_APPORTEUR);
	}

	public BigDecimal getTxRemTtcDocument() {
		return this.txRemTtcDocument;
	}

	public void setTxRemTtcDocument(BigDecimal TX_REM_TTC_APPORTEUR) {
		firePropertyChange("txRemTtcDocument", this.txRemTtcDocument, this.txRemTtcDocument = TX_REM_TTC_APPORTEUR);
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TotauxApporteurDTO))
			return false;
		TotauxApporteurDTO castOther = (TotauxApporteurDTO) other;

		return ((this.getId() == castOther.getId()) || (this
				.getId() != null
				&& castOther.getId() != null && this.getId()
				.equals(castOther.getId())))
				&& ((this.getIdTPaiement() == castOther.getIdTPaiement()) || (this
						.getIdTPaiement() != null
						&& castOther.getIdTPaiement() != null && this
						.getIdTPaiement()
						.equals(castOther.getIdTPaiement())))
				&& ((this.getCodeTPaiement() == castOther
						.getCodeTPaiement()) || (this.getCodeTPaiement() != null
						&& castOther.getCodeTPaiement() != null && this
						.getCodeTPaiement().equals(
								castOther.getCodeTPaiement())))
				&& ((this.getIdCPaiement() == castOther.getIdCPaiement()) || (this
						.getIdCPaiement() != null
						&& castOther.getIdCPaiement() != null && this
						.getIdCPaiement()
						.equals(castOther.getIdCPaiement())))
				&& ((this.getRegleDocument() == castOther.getRegleDocument()) || (this
						.getRegleDocument() != null
						&& castOther.getRegleDocument() != null && this
						.getRegleDocument()
						.equals(castOther.getRegleDocument())))
				&& ((this.getRemHtDocument() == castOther.getRemHtDocument()) || (this
						.getRemHtDocument() != null
						&& castOther.getRemHtDocument() != null && this
						.getRemHtDocument().equals(
								castOther.getRemHtDocument())))
				&& ((this.getTxRemHtDocument() == castOther
						.getTxRemHtDocument()) || (this
						.getTxRemHtDocument() != null
						&& castOther.getTxRemHtDocument() != null && this
						.getTxRemHtDocument().equals(
								castOther.getTxRemHtDocument())))
				&& ((this.getRemTtcDocument() == castOther
						.getRemTtcDocument()) || (this.getRemTtcDocument() != null
						&& castOther.getRemTtcDocument() != null && this
						.getRemTtcDocument().equals(
								castOther.getRemTtcDocument())))
				&& ((this.getTxRemTtcDocument() == castOther
						.getTxRemTtcDocument()) || (this
						.getTxRemTtcDocument() != null
						&& castOther.getTxRemTtcDocument() != null && this
						.getTxRemTtcDocument().equals(
								castOther.getTxRemTtcDocument())))
				&& ((this.getMtTtcCalc() == castOther.getMtTtcCalc()) || (this
						.getMtTtcCalc() != null
						&& castOther.getMtTtcCalc() != null && this
						.getMtTtcCalc().equals(castOther.getMtTtcCalc())))
				&& ((this.getMtHtCalc() == castOther.getMtHtCalc()) || (this
						.getMtHtCalc() != null
						&& castOther.getMtHtCalc() != null && this
						.getMtHtCalc().equals(castOther.getMtHtCalc())))
				&& ((this.getMtTvaCalc() == castOther.getMtTvaCalc()) || (this
						.getMtTvaCalc() != null
						&& castOther.getMtTvaCalc() != null && this
						.getMtTvaCalc().equals(castOther.getMtTvaCalc())))
				&& ((this.getNetTtcCalc() == castOther.getNetTtcCalc()) || (this
						.getNetTtcCalc() != null
						&& castOther.getNetTtcCalc() != null && this
						.getNetTtcCalc().equals(castOther.getNetTtcCalc())))
				&& ((this.getNetHtCalc() == castOther.getNetHtCalc()) || (this
						.getNetHtCalc() != null
						&& castOther.getNetHtCalc() != null && this
						.getNetHtCalc().equals(castOther.getNetHtCalc())))
				&& ((this.getImprimer() == castOther.getImprimer()) || (this
						.getImprimer() != null
						&& castOther.getImprimer() != null && this
						.getImprimer().equals(castOther.getImprimer())))
				&& ((this.getCommentaire() == castOther.getCommentaire()) || (this
						.getCommentaire() != null
						&& castOther.getCommentaire() != null && this
						.getCommentaire().equals(castOther.getCommentaire())))						
				&& ((this.getNetTvaCalc() == castOther.getNetTvaCalc()) || (this
						.getNetTvaCalc() != null
						&& castOther.getNetTvaCalc() != null && this
						.getNetTvaCalc().equals(castOther.getNetTvaCalc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getId() == null ? 0 : this.getId()
						.hashCode());
	
		result = 37
				* result
				+ (getIdTPaiement() == null ? 0 : this.getIdTPaiement()
						.hashCode());
		result = 37
				* result
				+ (getCodeTPaiement() == null ? 0 : this.getCodeTPaiement()
						.hashCode());
		result = 37
				* result
				+ (getIdCPaiement() == null ? 0 : this.getIdCPaiement()
						.hashCode());
		result = 37
				* result
				+ (getRegleDocument() == null ? 0 : this.getRegleDocument()
						.hashCode());
		result = 37
				* result
				+ (getRemHtDocument() == null ? 0 : this.getRemHtDocument()
						.hashCode());
		result = 37
				* result
				+ (getTxRemHtDocument() == null ? 0 : this
						.getTxRemHtDocument().hashCode());
		result = 37
				* result
				+ (getRemTtcDocument() == null ? 0 : this.getRemTtcDocument()
						.hashCode());
		result = 37
				* result
				+ (getTxRemTtcDocument() == null ? 0 : this
						.getTxRemTtcDocument().hashCode());
		result = 37
				* result
				+ (getMtTtcCalc() == null ? 0 : this.getMtTtcCalc()
						.hashCode());
		result = 37
				* result
				+ (getMtHtCalc() == null ? 0 : this.getMtHtCalc()
						.hashCode());
		result = 37
				* result
				+ (getMtTvaCalc() == null ? 0 : this.getMtTvaCalc()
						.hashCode());
		result = 37
				* result
				+ (getNetTtcCalc() == null ? 0 : this.getNetTtcCalc()
						.hashCode());
		result = 37
				* result
				+ (getNetHtCalc() == null ? 0 : this.getNetHtCalc()
						.hashCode());
		result = 37
				* result
				+ (getImprimer() == null ? 0 : this.getImprimer()
				.hashCode());
		result = 37
				* result
				+ (getCommentaire() == null ? 0 : this.getCommentaire()
						.hashCode());		
		result = 37
				* result
				+ (getNetTvaCalc() == null ? 0 : this.getNetTvaCalc()
						.hashCode());
		return result;
	}

	public Boolean getImprimer() {
		return imprimer;
	}

	public void setImprimer(Boolean imprimer) {
		firePropertyChange("imprimer", this.imprimer, this.imprimer = imprimer);
	}




	

}
