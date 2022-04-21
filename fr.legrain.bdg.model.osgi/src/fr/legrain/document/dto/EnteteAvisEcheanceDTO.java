package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.LibDate;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class EnteteAvisEcheanceDTO extends ModelObject  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1739567747973777383L;
	private Integer id=0;
	private String codeDocument=LgrConstantes.C_STR_VIDE;
	private Date dateDocument=new Date();
	private Date dateEchDocument=new Date();;
	private Date dateLivDocument=new Date();;
	private String libelleDocument=LgrConstantes.C_STR_VIDE;
	private Integer idAdresse=0;
	private Integer idAdresseLiv=0;
	private Integer idTiers=0;
	private String codeTiers=LgrConstantes.C_STR_VIDE;
	private String nomTiers=LgrConstantes.C_STR_VIDE;
	private String prenomTiers=LgrConstantes.C_STR_VIDE;
	private String surnomTiers=LgrConstantes.C_STR_VIDE;
	private String codeCompta=LgrConstantes.C_STR_VIDE;
	private String compte=LgrConstantes.C_STR_VIDE;
	private Integer idTPaiement=0;
	private String codeTPaiement=LgrConstantes.C_STR_VIDE;
	private Integer idCPaiement=0;
	private BigDecimal regleDocument=new BigDecimal(0);
	private BigDecimal remHtDocument=new BigDecimal(0);
	private BigDecimal txRemHtDocument=new BigDecimal(0);
	private BigDecimal remTtcDocument=new BigDecimal(0);
	private BigDecimal txRemTtcDocument=new BigDecimal(0);
	private Integer nbEDocument=0;
	private BigDecimal mtTtcCalc=new BigDecimal(0);
	private BigDecimal mtHtCalc=new BigDecimal(0);
	private BigDecimal mtTvaCalc=new BigDecimal(0);
	private BigDecimal netTtcCalc=new BigDecimal(0);
	private BigDecimal netHtCalc=new BigDecimal(0);
	private BigDecimal netTvaCalc=new BigDecimal(0);
	private String ipAcces=LgrConstantes.C_STR_VIDE;
	private Boolean ttc=false;
	private Boolean export=false;
	private String commentaire = "";
	private Boolean accepte=true;
	private List<TaLAvisEcheanceDTO> lignesAvis = null;

	public EnteteAvisEcheanceDTO() {
	}



	


	public EnteteAvisEcheanceDTO(Integer idDocument, String codeDocument,
			Date dateDocument, Date dateEchDocument, Date dateLivDocument,
			String libelleDocument, Integer idAdresse, Integer idAdresseLiv,
			Integer idTiers, String codeTiers, String nomTiers,
			String prenomTiers, String surnomTiers, String codeCompta,
			String compte, Integer idTPaiement, String codeTPaiement,
			Integer idCPaiement, BigDecimal regleDocument,
			BigDecimal remHtDocument, BigDecimal txRemHtDocument,
			BigDecimal remTtcDocument, BigDecimal txRemTtcDocument,
			Integer nbEDocument, BigDecimal mtTtcCalc, BigDecimal mtHtCalc,
			BigDecimal mtTvaCalc, BigDecimal netTtcCalc, BigDecimal netHtCalc,
			BigDecimal netTvaCalc, String ipAcces, Boolean ttc, Boolean export,
			String commentaire, Boolean accepte, List<TaLAvisEcheanceDTO> lignes) {
		super();
		this.id = idDocument;
		this.codeDocument = codeDocument;
		this.dateDocument = dateDocument;
		this.dateEchDocument = dateEchDocument;
		this.dateLivDocument = dateLivDocument;
		this.libelleDocument = libelleDocument;
		this.idAdresse = idAdresse;
		this.idAdresseLiv = idAdresseLiv;
		this.idTiers = idTiers;
		this.codeTiers = codeTiers;
		this.nomTiers = nomTiers;
		this.prenomTiers = prenomTiers;
		this.surnomTiers = surnomTiers;
		this.codeCompta = codeCompta;
		this.compte = compte;
		this.idTPaiement = idTPaiement;
		this.codeTPaiement = codeTPaiement;
		this.idCPaiement = idCPaiement;
		this.regleDocument = regleDocument;
		this.remHtDocument = remHtDocument;
		this.txRemHtDocument = txRemHtDocument;
		this.remTtcDocument = remTtcDocument;
		this.txRemTtcDocument = txRemTtcDocument;
		this.nbEDocument = nbEDocument;
		this.mtTtcCalc = mtTtcCalc;
		this.mtHtCalc = mtHtCalc;
		this.mtTvaCalc = mtTvaCalc;
		this.netTtcCalc = netTtcCalc;
		this.netHtCalc = netHtCalc;
		this.netTvaCalc = netTvaCalc;
		this.ipAcces = ipAcces;
		this.ttc = ttc;
		this.export = export;
		this.commentaire = commentaire;
		this.accepte = accepte;
		this.lignesAvis = lignesAvis;
	}






	public void setIHMEnteteDevis(EnteteAvisEcheanceDTO ihmEnteteDevis){
		setIdDocument(ihmEnteteDevis.id);
		setCodeDocument(ihmEnteteDevis.codeDocument);
		setDateDocument(ihmEnteteDevis.dateDocument);
		setDateEchDocument(ihmEnteteDevis.dateEchDocument);
		setDateLivDocument(ihmEnteteDevis.dateLivDocument);
		setLibelleDocument(ihmEnteteDevis.libelleDocument);
		setIdAdresse(ihmEnteteDevis.idAdresse);
		setIdAdresseLiv(ihmEnteteDevis.idAdresseLiv);
		setIdTiers(ihmEnteteDevis.idTiers);
		setCodeTiers(ihmEnteteDevis.codeTiers);
		setNomTiers(ihmEnteteDevis.nomTiers);
		setPrenomTiers(ihmEnteteDevis.prenomTiers);
		setSurnomTiers(ihmEnteteDevis.surnomTiers);
		setCodeCompta(ihmEnteteDevis.codeCompta);
		setCompte(ihmEnteteDevis.compte);
		setIdTPaiement(ihmEnteteDevis.idTPaiement);
		setCodeTPaiement(ihmEnteteDevis.codeTPaiement);
		setIdCPaiement(ihmEnteteDevis.idCPaiement);
		setRegleDocument(ihmEnteteDevis.regleDocument);
		setRemHtDocument(ihmEnteteDevis.remHtDocument);
		setTxRemHtDocument(ihmEnteteDevis.txRemHtDocument);
		setRemTtcDocument(ihmEnteteDevis.remTtcDocument);
		setTxRemTtcDocument(ihmEnteteDevis.txRemTtcDocument);
		setNbEDocument(ihmEnteteDevis.nbEDocument);
		setMtTtcCalc(ihmEnteteDevis.mtTtcCalc);
		setMtHtCalc(ihmEnteteDevis.mtHtCalc);
		setMtTvaCalc(ihmEnteteDevis.mtTvaCalc);
		setNetTtcCalc(ihmEnteteDevis.netTtcCalc);
		setNetHtCalc(ihmEnteteDevis.netHtCalc);
		setNetTvaCalc(ihmEnteteDevis.netTvaCalc);
		setIpAcces(ihmEnteteDevis.ipAcces);
		setTtc(ihmEnteteDevis.ttc);
		setExport(ihmEnteteDevis.export);
		setCommentaire(ihmEnteteDevis.commentaire);
		setAccepte(ihmEnteteDevis.accepte);
		setLignesAvis(ihmEnteteDevis.lignesAvis);
	}
	
	public static EnteteAvisEcheanceDTO copy(EnteteAvisEcheanceDTO ihmEnteteDevis){
		EnteteAvisEcheanceDTO ihmEnteteDevisLoc = new EnteteAvisEcheanceDTO();
		ihmEnteteDevisLoc.setIdDocument(ihmEnteteDevis.id);
		ihmEnteteDevisLoc.setCodeDocument(ihmEnteteDevis.codeDocument);
		ihmEnteteDevisLoc.setDateDocument(ihmEnteteDevis.dateDocument);
		ihmEnteteDevisLoc.setDateEchDocument(ihmEnteteDevis.dateEchDocument);
		ihmEnteteDevisLoc.setDateLivDocument(ihmEnteteDevis.dateLivDocument);
		ihmEnteteDevisLoc.setLibelleDocument(ihmEnteteDevis.libelleDocument);
		ihmEnteteDevisLoc.setIdAdresse(ihmEnteteDevis.idAdresse);
		ihmEnteteDevisLoc.setIdAdresseLiv(ihmEnteteDevis.idAdresseLiv);
		ihmEnteteDevisLoc.setIdTiers(ihmEnteteDevis.idTiers);
		ihmEnteteDevisLoc.setCodeTiers(ihmEnteteDevis.codeTiers);
		ihmEnteteDevisLoc.setNomTiers(ihmEnteteDevis.nomTiers);
		ihmEnteteDevisLoc.setPrenomTiers(ihmEnteteDevis.prenomTiers);
		ihmEnteteDevisLoc.setSurnomTiers(ihmEnteteDevis.surnomTiers);
		ihmEnteteDevisLoc.setCodeCompta(ihmEnteteDevis.codeCompta);
		ihmEnteteDevisLoc.setCompte(ihmEnteteDevis.compte);
		ihmEnteteDevisLoc.setIdTPaiement(ihmEnteteDevis.idTPaiement);
		ihmEnteteDevisLoc.setCodeTPaiement(ihmEnteteDevis.codeTPaiement);
		ihmEnteteDevisLoc.setIdCPaiement(ihmEnteteDevis.idCPaiement);
		ihmEnteteDevisLoc.setRegleDocument(ihmEnteteDevis.regleDocument);
		ihmEnteteDevisLoc.setRemHtDocument(ihmEnteteDevis.remHtDocument);
		ihmEnteteDevisLoc.setTxRemHtDocument(ihmEnteteDevis.txRemHtDocument);
		ihmEnteteDevisLoc.setRemTtcDocument(ihmEnteteDevis.remTtcDocument);
		ihmEnteteDevisLoc.setTxRemTtcDocument(ihmEnteteDevis.txRemTtcDocument);
		ihmEnteteDevisLoc.setNbEDocument(ihmEnteteDevis.nbEDocument);
		ihmEnteteDevisLoc.setMtTtcCalc(ihmEnteteDevis.mtTtcCalc);
		ihmEnteteDevisLoc.setMtHtCalc(ihmEnteteDevis.mtHtCalc);
		ihmEnteteDevisLoc.setMtTvaCalc(ihmEnteteDevis.mtTvaCalc);
		ihmEnteteDevisLoc.setNetTtcCalc(ihmEnteteDevis.netTtcCalc);
		ihmEnteteDevisLoc.setNetHtCalc(ihmEnteteDevis.netHtCalc);
		ihmEnteteDevisLoc.setNetTvaCalc(ihmEnteteDevis.netTvaCalc);
		ihmEnteteDevisLoc.setIpAcces(ihmEnteteDevis.ipAcces);
		ihmEnteteDevisLoc.setTtc(ihmEnteteDevis.ttc);
		ihmEnteteDevisLoc.setExport(ihmEnteteDevis.export);
		ihmEnteteDevisLoc.setCommentaire(ihmEnteteDevis.commentaire);
		ihmEnteteDevisLoc.setAccepte(ihmEnteteDevis.accepte);
		ihmEnteteDevisLoc.setLignesAvis(ihmEnteteDevis.lignesAvis);
		return ihmEnteteDevisLoc;
	}
	
	public boolean DocumentEstVide(){
		Date dateExemple = new Date();
//		if(!getCODE_DEVIS().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!(LibDate.compareTo(getDateDocument(), dateExemple)==0))return false;
		if(!(LibDate.compareTo(getDateEchDocument(), dateExemple)==0))return false;
		if(!(LibDate.compareTo(getDateLivDocument(), dateExemple)==0))return false;
		
		if(!getLibelleDocument().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_ADRESSE().equals(0))return false;
//		if(!getID_ADRESSE_LIV().equals(0))return false;
		if(!getIdTiers().equals(0))return false;
		if(!getCodeTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getNomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getPrenomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getSurnomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getCodeCompta().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getCompte().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_T_PAIEMENT().equals(0))return false;
//		if(!getCODE_T_PAIEMENT().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_C_PAIEMENT().equals(0))return false;
//		//if(!LibChaine.emptyNumeric(getREGLE_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getREM_HT_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_HT_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getREM_TTC_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_TTC_DEVIS()))return false;
//		if(!getNB_E_DEVIS().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getMT_TTC_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getMT_HT_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getMT_TVA_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getNET_TTC_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getNET_HT_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getNET_TVA_CALC()))return false;
//		if(!getIP_ACCES().equals(""))return false;
//		if(!getTTC().equals(false))return false;
//		if(!getEXPORT().equals(false))return false;
		return true;
	}
	
	public boolean DocumentEstVide(Date dateDansExercice){
		Date dateExemple = new Date();
//		if(!getCODE_DEVIS().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!((LibDate.compareTo(getDateDocument(), dateDansExercice)==0) || 
				(LibDate.compareTo(getDateDocument(), dateExemple)==0)))return false;
//		if(!((LibDate.compareTo(getDateEchDocument(), dateDansExercice)==0) || 
//				(LibDate.compareTo(getDateEchDocument(), dateExemple)==0)))return false;
//		if(!((LibDate.compareTo(getDateLivDocument(), dateDansExercice)==0) || 
//				(LibDate.compareTo(getDateLivDocument(), dateExemple)==0)))return false;
		
		if(getLibelleDocument()!=null && !getLibelleDocument().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_ADRESSE().equals(0))return false;
//		if(!getID_ADRESSE_LIV().equals(0))return false;
		if(getIdTiers()!=null && !getIdTiers().equals(0))return false;
		if(getCodeTiers()!=null && !getCodeTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getNomTiers()!=null && !getNomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getPrenomTiers()!=null && !getPrenomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getSurnomTiers()!=null && !getSurnomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCodeCompta()!=null && !getCodeCompta().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCompte()!=null && !getCompte().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_T_PAIEMENT().equals(0))return false;
//		if(!getCODE_T_PAIEMENT().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getID_C_PAIEMENT().equals(0))return false;
//		//if(!LibChaine.emptyNumeric(getREGLE_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getREM_HT_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_HT_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getREM_TTC_DEVIS()))return false;
//		if(!LibChaine.emptyNumeric(getTX_REM_TTC_DEVIS()))return false;
//		if(!getNB_E_DEVIS().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getMT_TTC_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getMT_HT_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getMT_TVA_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getNET_TTC_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getNET_HT_CALC()))return false;
//		if(!LibChaine.emptyNumeric(getNET_TVA_CALC()))return false;
//		if(!getIP_ACCES().equals(""))return false;
//		if(!getTTC().equals(false))return false;
//		if(!getEXPORT().equals(false))return false;
		return true;
	}

	public Integer getIdDocument() {
		return this.id;
	}

	public void setIdDocument(Integer ID_DEVIS) {
		firePropertyChange("idDocument", this.id, this.id = ID_DEVIS);
	}

	public String getCodeDocument() {
		return this.codeDocument;
	}

	public void setCodeDocument(String CODE_DEVIS) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = CODE_DEVIS);
	}

	public Date getDateDocument() {
		return this.dateDocument;
	}

	public void setDateDocument(Date DATE_DEVIS) {
		firePropertyChange("dateDocument", this.dateDocument, this.dateDocument = DATE_DEVIS);
	}

	public Date getDateEchDocument() {
		return this.dateEchDocument;
	}

	public void setDateEchDocument(Date DATE_ECH_DEVIS) {
		firePropertyChange("dateEchDocument", this.dateEchDocument, this.dateEchDocument = DATE_ECH_DEVIS);
	}

	public Date getDateLivDocument() {
		return this.dateLivDocument;
	}

	public void setDateLivDocument(Date DATE_LIV_DEVIS) {
		firePropertyChange("dateLivDocument", this.dateLivDocument, this.dateLivDocument = DATE_LIV_DEVIS);
	}

	public String getLibelleDocument() {
		return this.libelleDocument;
	}

	public void setLibelleDocument(String LIBELLE_DEVIS) {
		firePropertyChange("libelleDocument", this.libelleDocument, this.libelleDocument = LIBELLE_DEVIS);
	}

	public Integer getIdAdresse() {
		return this.idAdresse;
	}

	public void setIdAdresse(Integer ID_ADRESSE) {
		firePropertyChange("idAdresse", this.idAdresse, this.idAdresse = ID_ADRESSE);
	}

	public Integer getIdAdresseLiv() {
		return this.idAdresseLiv;
	}

	public void setIdAdresseLiv(Integer ID_ADRESSE_LIV) {
		firePropertyChange("idAdresseLiv", this.idAdresseLiv, this.idAdresseLiv = ID_ADRESSE_LIV);
	}

	public Integer getIdTiers() {
		return this.idTiers;
	}

	public void setIdTiers(Integer ID_TIERS) {
		firePropertyChange("idTiers", this.idTiers, this.idTiers = ID_TIERS);
	}

	public String getCodeTiers() {
		return this.codeTiers;
	}

	public void setCodeTiers(String CODE_TIERS) {
		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = CODE_TIERS);
	}

	public String getNomTiers() {
		return this.nomTiers;
	}

	public void setNomTiers(String NOM_TIERS) {
		firePropertyChange("nomTiers", this.nomTiers, this.nomTiers = NOM_TIERS);
	}

	public String getPrenomTiers() {
		return this.prenomTiers;
	}

	public void setPrenomTiers(String PRENOM_TIERS) {
		firePropertyChange("prenomTiers", this.prenomTiers, this.prenomTiers = PRENOM_TIERS);
	}

	public String getSurnomTiers() {
		return this.surnomTiers;
	}

	public void setSurnomTiers(String SURNOM_TIERS) {
		firePropertyChange("surnomTiers", this.surnomTiers, this.surnomTiers = SURNOM_TIERS);
	}

	public String getCodeCompta() {
		return this.codeCompta;
	}

	public void setCodeCompta(String CODE_COMPTA) {
		firePropertyChange("codeCompta", this.codeCompta, this.codeCompta = CODE_COMPTA);
	}

	public String getCompte() {
		return this.compte;
	}

	public void setCompte(String COMPTE) {
		firePropertyChange("compte", this.compte, this.compte = COMPTE);
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
		return regleDocument;
	}

	public void setRegleDocument(BigDecimal regleDocument) {
		firePropertyChange("regleDocument", this.regleDocument, this.regleDocument = regleDocument);
	}

	public BigDecimal getRemHtDocument() {
		return this.remHtDocument;
	}

	public void setRemHtDocument(BigDecimal REM_HT_DEVIS) {
		firePropertyChange("remHtDocument", this.remHtDocument, this.remHtDocument = REM_HT_DEVIS);
	}

	public BigDecimal getTxRemHtDocument() {
		return this.txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal TX_REM_HT_DEVIS) {
		firePropertyChange("txRemHtDocument", this.txRemHtDocument, this.txRemHtDocument = TX_REM_HT_DEVIS);
	}

	public BigDecimal getRemTtcDocument() {
		return this.remTtcDocument;
	}

	public void setRemTtcDocument(BigDecimal REM_TTC_DEVIS) {
		firePropertyChange("remTtcDocument", this.remTtcDocument, this.remTtcDocument = REM_TTC_DEVIS);
	}

	public BigDecimal getTxRemTtcDocument() {
		return this.txRemTtcDocument;
	}

	public void setTxRemTtcDocument(BigDecimal TX_REM_TTC_DEVIS) {
		firePropertyChange("txRemTtcDocument", this.txRemTtcDocument, this.txRemTtcDocument = TX_REM_TTC_DEVIS);
	}

	public Integer getNbEDocument() {
		return this.nbEDocument;
	}

	public void setNbEDocument(Integer NB_E_DEVIS) {
		firePropertyChange("nbEDocument", this.nbEDocument, this.nbEDocument = NB_E_DEVIS);
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

	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String IP_ACCES) {
		firePropertyChange("ipAcces", this.ipAcces, this.ipAcces = IP_ACCES);
	}

	public Boolean getTtc() {
		return this.ttc;
	}

	public void setTtc(Boolean TTC) {
		firePropertyChange("ttc", this.ttc, this.ttc = TTC);
	}

	public Boolean getExport() {
		return this.export;
	}

	public void setExport(Boolean EXPORT) {
		firePropertyChange("export", this.export, this.export = EXPORT);
	}
	
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String COMMENTAIRE) {
		firePropertyChange("commentaire", this.commentaire, this.commentaire = COMMENTAIRE);		
	}	


	public Boolean getAccepte() {
		return accepte;
	}

	public void setAccepte(Boolean accepte) {
		firePropertyChange("accepte", this.accepte, this.accepte = accepte);		
	}






	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accepte == null) ? 0 : accepte.hashCode());
		result = prime * result
				+ ((codeCompta == null) ? 0 : codeCompta.hashCode());
		result = prime * result
				+ ((codeDocument == null) ? 0 : codeDocument.hashCode());
		result = prime * result
				+ ((codeTPaiement == null) ? 0 : codeTPaiement.hashCode());
		result = prime * result
				+ ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result
				+ ((dateDocument == null) ? 0 : dateDocument.hashCode());
		result = prime * result
				+ ((dateEchDocument == null) ? 0 : dateEchDocument.hashCode());
		result = prime * result
				+ ((dateLivDocument == null) ? 0 : dateLivDocument.hashCode());
		result = prime * result + ((export == null) ? 0 : export.hashCode());
		result = prime * result
				+ ((idAdresse == null) ? 0 : idAdresse.hashCode());
		result = prime * result
				+ ((idAdresseLiv == null) ? 0 : idAdresseLiv.hashCode());
		result = prime * result
				+ ((idCPaiement == null) ? 0 : idCPaiement.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idTPaiement == null) ? 0 : idTPaiement.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result + ((ipAcces == null) ? 0 : ipAcces.hashCode());
		result = prime * result
				+ ((libelleDocument == null) ? 0 : libelleDocument.hashCode());
		result = prime * result
				+ ((mtHtCalc == null) ? 0 : mtHtCalc.hashCode());
		result = prime * result
				+ ((mtTtcCalc == null) ? 0 : mtTtcCalc.hashCode());
		result = prime * result
				+ ((mtTvaCalc == null) ? 0 : mtTvaCalc.hashCode());
		result = prime * result
				+ ((nbEDocument == null) ? 0 : nbEDocument.hashCode());
		result = prime * result
				+ ((netHtCalc == null) ? 0 : netHtCalc.hashCode());
		result = prime * result
				+ ((netTtcCalc == null) ? 0 : netTtcCalc.hashCode());
		result = prime * result
				+ ((netTvaCalc == null) ? 0 : netTvaCalc.hashCode());
		result = prime * result
				+ ((nomTiers == null) ? 0 : nomTiers.hashCode());
		result = prime * result
				+ ((prenomTiers == null) ? 0 : prenomTiers.hashCode());
		result = prime * result
				+ ((regleDocument == null) ? 0 : regleDocument.hashCode());
		result = prime * result
				+ ((remHtDocument == null) ? 0 : remHtDocument.hashCode());
		result = prime * result
				+ ((remTtcDocument == null) ? 0 : remTtcDocument.hashCode());
		result = prime * result
				+ ((surnomTiers == null) ? 0 : surnomTiers.hashCode());
		result = prime * result + ((ttc == null) ? 0 : ttc.hashCode());
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
		EnteteAvisEcheanceDTO other = (EnteteAvisEcheanceDTO) obj;
		if (accepte == null) {
			if (other.accepte != null)
				return false;
		} else if (!accepte.equals(other.accepte))
			return false;
		if (codeCompta == null) {
			if (other.codeCompta != null)
				return false;
		} else if (!codeCompta.equals(other.codeCompta))
			return false;
		if (codeDocument == null) {
			if (other.codeDocument != null)
				return false;
		} else if (!codeDocument.equals(other.codeDocument))
			return false;
		if (codeTPaiement == null) {
			if (other.codeTPaiement != null)
				return false;
		} else if (!codeTPaiement.equals(other.codeTPaiement))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
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
		if (dateDocument == null) {
			if (other.dateDocument != null)
				return false;
		} else if (!dateDocument.equals(other.dateDocument))
			return false;
		if (dateEchDocument == null) {
			if (other.dateEchDocument != null)
				return false;
		} else if (!dateEchDocument.equals(other.dateEchDocument))
			return false;
		if (dateLivDocument == null) {
			if (other.dateLivDocument != null)
				return false;
		} else if (!dateLivDocument.equals(other.dateLivDocument))
			return false;
		if (export == null) {
			if (other.export != null)
				return false;
		} else if (!export.equals(other.export))
			return false;
		if (idAdresse == null) {
			if (other.idAdresse != null)
				return false;
		} else if (!idAdresse.equals(other.idAdresse))
			return false;
		if (idAdresseLiv == null) {
			if (other.idAdresseLiv != null)
				return false;
		} else if (!idAdresseLiv.equals(other.idAdresseLiv))
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
		if (idTiers == null) {
			if (other.idTiers != null)
				return false;
		} else if (!idTiers.equals(other.idTiers))
			return false;
		if (ipAcces == null) {
			if (other.ipAcces != null)
				return false;
		} else if (!ipAcces.equals(other.ipAcces))
			return false;
		if (libelleDocument == null) {
			if (other.libelleDocument != null)
				return false;
		} else if (!libelleDocument.equals(other.libelleDocument))
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
		if (nbEDocument == null) {
			if (other.nbEDocument != null)
				return false;
		} else if (!nbEDocument.equals(other.nbEDocument))
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
		if (nomTiers == null) {
			if (other.nomTiers != null)
				return false;
		} else if (!nomTiers.equals(other.nomTiers))
			return false;
		if (prenomTiers == null) {
			if (other.prenomTiers != null)
				return false;
		} else if (!prenomTiers.equals(other.prenomTiers))
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
		if (surnomTiers == null) {
			if (other.surnomTiers != null)
				return false;
		} else if (!surnomTiers.equals(other.surnomTiers))
			return false;
		if (ttc == null) {
			if (other.ttc != null)
				return false;
		} else if (!ttc.equals(other.ttc))
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

	
	public List<TaLAvisEcheanceDTO> getLignesAvis() {
		return lignesAvis;
	}

	public void setLignesAvis(List<TaLAvisEcheanceDTO> lignesAvis) {
		this.lignesAvis = lignesAvis;
	}








	

}
