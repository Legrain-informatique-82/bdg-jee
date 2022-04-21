package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.LibDate;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteApporteur generated by hbm2java
 */
public class EnteteApporteurDTO extends ModelObject  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2908167439615504771L;
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
	private String IP_ACCES=LgrConstantes.C_STR_VIDE;
	private Boolean ttc=false;
	private Boolean export=false;
	private String commentaire = "";

	public EnteteApporteurDTO() {
	}

	public EnteteApporteurDTO(Integer ID_APPORTEUR, String CODE_APPORTEUR,
			Date DATE_APPORTEUR, Date DATE_ECH_APPORTEUR, Date DATE_LIV_APPORTEUR,
			String LIBELLE_APPORTEUR, Integer ID_ADRESSE, Integer ID_ADRESSE_LIV,
			Integer ID_TIERS, String CODE_TIERS, String NOM_TIERS,
			String PRENOM_TIERS, String SURNOM_TIERS, String CODE_COMPTA,
			String COMPTE, Integer ID_T_PAIEMENT, String CODE_T_PAIEMENT,
			Integer ID_C_PAIEMENT, BigDecimal REGLE_APPORTEUR,
			BigDecimal REM_HT_APPORTEUR, BigDecimal TX_REM_HT_APPORTEUR,
			BigDecimal REM_TTC_APPORTEUR, BigDecimal TX_REM_TTC_APPORTEUR,
			Integer NB_E_APPORTEUR, BigDecimal MT_TTC_CALC, BigDecimal MT_HT_CALC,
			BigDecimal MT_TVA_CALC, BigDecimal NET_TTC_CALC, BigDecimal NET_HT_CALC,
			BigDecimal NET_TVA_CALC, String IP_ACCES, Boolean TTC, Boolean EXPORT,String COMMENTAIRE) {
		this.id = ID_APPORTEUR;
		this.codeDocument = CODE_APPORTEUR;
		this.dateDocument = DATE_APPORTEUR;
		this.dateEchDocument = DATE_ECH_APPORTEUR;
		this.dateLivDocument = DATE_LIV_APPORTEUR;
		this.libelleDocument = LIBELLE_APPORTEUR;
		this.idAdresse = ID_ADRESSE;
		this.idAdresseLiv = ID_ADRESSE_LIV;
		this.idTiers = ID_TIERS;
		this.codeTiers = CODE_TIERS;
		this.nomTiers = NOM_TIERS;
		this.prenomTiers = PRENOM_TIERS;
		this.surnomTiers = SURNOM_TIERS;
		this.codeCompta = CODE_COMPTA;
		this.compte = COMPTE;
		this.idTPaiement = ID_T_PAIEMENT;
		this.codeTPaiement = CODE_T_PAIEMENT;
		this.idCPaiement = ID_C_PAIEMENT;
		this.regleDocument = REGLE_APPORTEUR;
		this.remHtDocument = REM_HT_APPORTEUR;
		this.txRemHtDocument = TX_REM_HT_APPORTEUR;
		this.remTtcDocument = REM_TTC_APPORTEUR;
		this.txRemTtcDocument = TX_REM_TTC_APPORTEUR;
		this.nbEDocument = NB_E_APPORTEUR;
		this.mtTtcCalc = MT_TTC_CALC;
		this.mtHtCalc = MT_HT_CALC;
		this.mtTvaCalc = MT_TVA_CALC;
		this.netTtcCalc = NET_TTC_CALC;
		this.netHtCalc = NET_HT_CALC;
		this.netTvaCalc = NET_TVA_CALC;
		this.IP_ACCES = IP_ACCES;
		this.ttc = TTC;
		this.export = EXPORT;
		this.commentaire = COMMENTAIRE;
	}

	public void setIHMEnteteApporteur(EnteteApporteurDTO ihmEnteteApporteur){
		setIdDocument(ihmEnteteApporteur.id);
		setCodeDocument(ihmEnteteApporteur.codeDocument);
		setDateDocument(ihmEnteteApporteur.dateDocument);
		setDateEchDocument(ihmEnteteApporteur.dateEchDocument);
		setDateLivDocument(ihmEnteteApporteur.dateLivDocument);
		setLibelleDocument(ihmEnteteApporteur.libelleDocument);
		setIdAdresse(ihmEnteteApporteur.idAdresse);
		setIdAdresseLiv(ihmEnteteApporteur.idAdresseLiv);
		setIdTiers(ihmEnteteApporteur.idTiers);
		setCodeTiers(ihmEnteteApporteur.codeTiers);
		setNomTiers(ihmEnteteApporteur.nomTiers);
		setPrenomTiers(ihmEnteteApporteur.prenomTiers);
		setSurnomTiers(ihmEnteteApporteur.surnomTiers);
		setCodeCompta(ihmEnteteApporteur.codeCompta);
		setCompte(ihmEnteteApporteur.compte);
		setIdTPaiement(ihmEnteteApporteur.idTPaiement);
		setCodeTPaiement(ihmEnteteApporteur.codeTPaiement);
		setIdCPaiement(ihmEnteteApporteur.idCPaiement);
		setRegleDocument(ihmEnteteApporteur.regleDocument);
		setRemHtDocument(ihmEnteteApporteur.remHtDocument);
		setTxRemHtDocument(ihmEnteteApporteur.txRemHtDocument);
		setRemTtcDocument(ihmEnteteApporteur.remTtcDocument);
		setTxRemTtcDocument(ihmEnteteApporteur.txRemTtcDocument);
		setNbEDocument(ihmEnteteApporteur.nbEDocument);
		setMtTtcCalc(ihmEnteteApporteur.mtTtcCalc);
		setMtHtCalc(ihmEnteteApporteur.mtHtCalc);
		setMtTvaCalc(ihmEnteteApporteur.mtTvaCalc);
		setNetTtcCalc(ihmEnteteApporteur.netTtcCalc);
		setNetHtCalc(ihmEnteteApporteur.netHtCalc);
		setNetTvaCalc(ihmEnteteApporteur.netTvaCalc);
		setIP_ACCES(ihmEnteteApporteur.IP_ACCES);
		setTtc(ihmEnteteApporteur.ttc);
		setExport(ihmEnteteApporteur.export);
		setCommentaire(ihmEnteteApporteur.commentaire);
	}
	
//	public void setIHMEnteteApporteur(SWTEnteteApporteur swtEnteteApporteur){
//		System.out.println("mise à jour ihm par SWTEnteteApporteur");
//		setCodeApporteur(swtEnteteApporteur.getCODE());
//		System.out.println("Code facture swtEnteteApporteur : "+swtEnteteApporteur.getCODE());
//		setDateApporteur(swtEnteteApporteur.getDATE());
//		setDateEchApporteur(swtEnteteApporteur.getDATE_ECH_APPORTEUR());
//		setDateLivApporteur(swtEnteteApporteur.getDATE_LIV_APPORTEUR());
//		setLibelleApporteur(swtEnteteApporteur.getLIBELLE());
//		setIdAdresse(swtEnteteApporteur.getID_ADRESSE());
//		setIdAdresseLiv(swtEnteteApporteur.getID_ADRESSE_LIV());
//		setIdTiers(swtEnteteApporteur.getID_TIERS());
//		setCodeTiers(swtEnteteApporteur.getCODE_TIERS());
//		setIdTPaiement(swtEnteteApporteur.getID_T_PAIEMENT());
//		setCodeTPaiement(swtEnteteApporteur.getCODE_T_PAIEMENT());
//		setIdCPaiement(swtEnteteApporteur.getID_C_PAIEMENT());
//		setRegleApporteur(swtEnteteApporteur.getREGLE_APPORTEUR());
//		setRemHtApporteur(swtEnteteApporteur.getREM_HT_APPORTEUR());
//		setTxRemHtApporteur(swtEnteteApporteur.getTX_REM_HT_APPORTEUR());
//		setRemTtcApporteur(swtEnteteApporteur.getREM_TTC_APPORTEUR());
//		setTxRemTtcApporteur(swtEnteteApporteur.getTX_REM_TTC_APPORTEUR());
//		setNbEApporteur(swtEnteteApporteur.getNB_E_APPORTEUR());
//		setMtTtcCalc(swtEnteteApporteur.getMT_TTC_CALC());
//		setMtHtCalc(swtEnteteApporteur.getMT_HT_CALC());
//		setMtTvaCalc(swtEnteteApporteur.getMT_TVA_CALC());
//		setTtc(swtEnteteApporteur.getTTC());
//		setExport(swtEnteteApporteur.getEXPORT());
//		setNetHtCalc(swtEnteteApporteur.getNET_HT_CALC());
//		setNetTvaCalc(swtEnteteApporteur.getNET_TVA_CALC());
//		setNetTtcCalc(swtEnteteApporteur.getNET_TTC_CALC());
//		setCommentaire(swtEnteteApporteur.getCOMMENTAIRE());
//	}
	
	public static EnteteApporteurDTO copy(EnteteApporteurDTO ihmEnteteApporteur){
		EnteteApporteurDTO ihmEnteteApporteurLoc = new EnteteApporteurDTO();
		ihmEnteteApporteurLoc.setIdDocument(ihmEnteteApporteur.id);
		ihmEnteteApporteurLoc.setCodeDocument(ihmEnteteApporteur.codeDocument);
		ihmEnteteApporteurLoc.setDateDocument(ihmEnteteApporteur.dateDocument);
		ihmEnteteApporteurLoc.setDateEchDocument(ihmEnteteApporteur.dateEchDocument);
		ihmEnteteApporteurLoc.setDateLivDocument(ihmEnteteApporteur.dateLivDocument);
		ihmEnteteApporteurLoc.setLibelleDocument(ihmEnteteApporteur.libelleDocument);
		ihmEnteteApporteurLoc.setIdAdresse(ihmEnteteApporteur.idAdresse);
		ihmEnteteApporteurLoc.setIdAdresseLiv(ihmEnteteApporteur.idAdresseLiv);
		ihmEnteteApporteurLoc.setIdTiers(ihmEnteteApporteur.idTiers);
		ihmEnteteApporteurLoc.setCodeTiers(ihmEnteteApporteur.codeTiers);
		ihmEnteteApporteurLoc.setNomTiers(ihmEnteteApporteur.nomTiers);
		ihmEnteteApporteurLoc.setPrenomTiers(ihmEnteteApporteur.prenomTiers);
		ihmEnteteApporteurLoc.setSurnomTiers(ihmEnteteApporteur.surnomTiers);
		ihmEnteteApporteurLoc.setCodeCompta(ihmEnteteApporteur.codeCompta);
		ihmEnteteApporteurLoc.setCompte(ihmEnteteApporteur.compte);
		ihmEnteteApporteurLoc.setIdTPaiement(ihmEnteteApporteur.idTPaiement);
		ihmEnteteApporteurLoc.setCodeTPaiement(ihmEnteteApporteur.codeTPaiement);
		ihmEnteteApporteurLoc.setIdCPaiement(ihmEnteteApporteur.idCPaiement);
		ihmEnteteApporteurLoc.setRegleDocument(ihmEnteteApporteur.regleDocument);
		ihmEnteteApporteurLoc.setRemHtDocument(ihmEnteteApporteur.remHtDocument);
		ihmEnteteApporteurLoc.setTxRemHtDocument(ihmEnteteApporteur.txRemHtDocument);
		ihmEnteteApporteurLoc.setRemTtcDocument(ihmEnteteApporteur.remTtcDocument);
		ihmEnteteApporteurLoc.setTxRemTtcDocument(ihmEnteteApporteur.txRemTtcDocument);
		ihmEnteteApporteurLoc.setNbEDocument(ihmEnteteApporteur.nbEDocument);
		ihmEnteteApporteurLoc.setMtTtcCalc(ihmEnteteApporteur.mtTtcCalc);
		ihmEnteteApporteurLoc.setMtHtCalc(ihmEnteteApporteur.mtHtCalc);
		ihmEnteteApporteurLoc.setMtTvaCalc(ihmEnteteApporteur.mtTvaCalc);
		ihmEnteteApporteurLoc.setNetTtcCalc(ihmEnteteApporteur.netTtcCalc);
		ihmEnteteApporteurLoc.setNetHtCalc(ihmEnteteApporteur.netHtCalc);
		ihmEnteteApporteurLoc.setNetTvaCalc(ihmEnteteApporteur.netTvaCalc);
		ihmEnteteApporteurLoc.setIP_ACCES(ihmEnteteApporteur.IP_ACCES);
		ihmEnteteApporteurLoc.setTtc(ihmEnteteApporteur.ttc);
		ihmEnteteApporteurLoc.setExport(ihmEnteteApporteur.export);
		ihmEnteteApporteurLoc.setCommentaire(ihmEnteteApporteur.commentaire);
		return ihmEnteteApporteurLoc;
	}
	
	public boolean factureEstVide(){
		Date dateExemple = new Date();
//		if(!getCodeApporteur().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!(LibDate.compareTo(getDateDocument(), dateExemple)==0))return false;
		if(!(LibDate.compareTo(getDateEchDocument(), dateExemple)==0))return false;
		if(!(LibDate.compareTo(getDateLivDocument(), dateExemple)==0))return false;
		
		if(!getLibelleDocument().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getIdAdresse().equals(0))return false;
//		if(!getIdAdresseLiv().equals(0))return false;
		if(!getIdTiers().equals(0))return false;
		if(!getCodeTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getNomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getPrenomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getSurnomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getCodeCompta().equals(LgrConstantes.C_STR_VIDE))return false;
		if(!getCompte().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getIdTPaiement().equals(0))return false;
//		if(!getCodeTPaiement().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getIdCPaiement().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getRegleApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getRemHtApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemHtApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getRemTtcApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemTtcApporteur()))return false;
//		if(!getNbEApporteur().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getMtTtcCalc()))return false;
//		if(!LibChaine.emptyNumeric(getMtHtCalc()))return false;
//		if(!LibChaine.emptyNumeric(getMtTvaCalc()))return false;
//		if(!LibChaine.emptyNumeric(getNetTtcCalc()))return false;
//		if(!LibChaine.emptyNumeric(getNetHtCalc()))return false;
//		if(!LibChaine.emptyNumeric(getNetTvaCalc()))return false;
//		if(!getIP_ACCES().equals(""))return false;
//		if(!getTtc().equals(false))return false;
//		if(!getExport().equals(false))return false;
//		if(!getCommentaire().equals(""))return false;
		return true;
	}
	
	public boolean factureEstVide(Date dateDansExercice){
		Date dateExemple = new Date();
//		if(!getCodeApporteur().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!((LibDate.compareTo(getDateDocument(), dateDansExercice)==0) || 
				(LibDate.compareTo(getDateDocument(), dateExemple)==0)))return false;
//		if(!((LibDate.compareTo(getDateEchDocument(), dateDansExercice)==0) || 
//				(LibDate.compareTo(getDateEchDocument(), dateExemple)==0)))return false;
//		if(!((LibDate.compareTo(getDateLivDocument(), dateDansExercice)==0) || 
//				(LibDate.compareTo(getDateLivDocument(), dateExemple)==0)))return false;
		
		if(getLibelleDocument()!=null && !getLibelleDocument().equals(LgrConstantes.C_STR_VIDE))return false;
//		if(!getIdAdresse().equals(0))return false;
//		if(!getIdAdresseLiv().equals(0))return false;
		if(getIdTiers()!=null &&!getIdTiers().equals(0))return false;
		if(getCodeTiers()!=null &&!getCodeTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getNomTiers()!=null &&!getNomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getPrenomTiers()!=null &&!getPrenomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getSurnomTiers()!=null &&!getSurnomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCodeCompta()!=null &&!getCodeCompta().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCompte()!=null &&!getCompte().equals(LgrConstantes.C_STR_VIDE))return false;
		//if(!getIdTPaiement().equals(0))return false;
//		if(!getCodeTPaiement().equals(LgrConstantes.C_STR_VIDE))return false;
		//if(!getIdCPaiement().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getRegleApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getRemHtApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemHtApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getRemTtcApporteur()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemTtcApporteur()))return false;
//		if(!getNbEApporteur().equals(0))return false;
//		if(!LibChaine.emptyNumeric(getMtTtcCalc()))return false;
//		if(!LibChaine.emptyNumeric(getMtHtCalc()))return false;
//		if(!LibChaine.emptyNumeric(getMtTvaCalc()))return false;
//		if(!LibChaine.emptyNumeric(getNetTtcCalc()))return false;
//		if(!LibChaine.emptyNumeric(getNetHtCalc()))return false;
//		if(!LibChaine.emptyNumeric(getNetTvaCalc()))return false;
//		if(!getIP_ACCES().equals(""))return false;
//		if(!getTtc().equals(false))return false;
//		if(!getExport().equals(false))return false;
//		if(!getCommentaire().equals(""))return false;
		return true;
	}

	public Integer getIdDocument() {
		return this.id;
	}

	public void setIdDocument(Integer ID_APPORTEUR) {
		firePropertyChange("idDocument", this.id, this.id = ID_APPORTEUR);
	}

	public String getCodeDocument() {
		return this.codeDocument;
	}

	public void setCodeDocument(String CODE_APPORTEUR) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = CODE_APPORTEUR);
	}

	public Date getDateDocument() {
		return this.dateDocument;
	}

	public void setDateDocument(Date DATE_APPORTEUR) {
		firePropertyChange("dateDocument", this.dateDocument, this.dateDocument = DATE_APPORTEUR);
	}

	public Date getDateEchDocument() {
		return this.dateEchDocument;
	}

	public void setDateEchDocument(Date DATE_ECH_APPORTEUR) {
		firePropertyChange("dateEchDocument", this.dateEchDocument, this.dateEchDocument = DATE_ECH_APPORTEUR);
	}

	public Date getDateLivDocument() {
		return this.dateLivDocument;
	}

	public void setDateLivDocument(Date DATE_LIV_APPORTEUR) {
		firePropertyChange("dateLivDocument", this.dateLivDocument, this.dateLivDocument = DATE_LIV_APPORTEUR);
	}

	public String getLibelleDocument() {
		return this.libelleDocument;
	}

	public void setLibelleDocument(String LIBELLE_APPORTEUR) {
		firePropertyChange("libelleDocument", this.libelleDocument, this.libelleDocument = LIBELLE_APPORTEUR);
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

	public Integer getNbEDocument() {
		return this.nbEDocument;
	}

	public void setNbEDocument(Integer NB_E_APPORTEUR) {
		firePropertyChange("nbEDocument", this.nbEDocument, this.nbEDocument = NB_E_APPORTEUR);
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

	public String getIP_ACCES() {
		return this.IP_ACCES;
	}

	public void setIP_ACCES(String IP_ACCES) {
		firePropertyChange("IP_ACCES", this.IP_ACCES, this.IP_ACCES = IP_ACCES);
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
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EnteteApporteurDTO))
			return false;
		EnteteApporteurDTO castOther = (EnteteApporteurDTO) other;

		return ((this.getIdDocument() == castOther.getIdDocument()) || (this
				.getIdDocument() != null
				&& castOther.getIdDocument() != null && this.getIdDocument()
				.equals(castOther.getIdDocument())))
				&& ((this.getCodeDocument() == castOther.getCodeDocument()) || (this
						.getCodeDocument() != null
						&& castOther.getCodeDocument() != null && this
						.getCodeDocument().equals(castOther.getCodeDocument())))
				&& ((this.getDateDocument() == castOther.getDateDocument()) || (this
						.getDateDocument() != null
						&& castOther.getDateDocument() != null && this
						.getDateDocument().equals(castOther.getDateDocument())))
				&& ((this.getDateEchDocument() == castOther
						.getDateEchDocument()) || (this.getDateEchDocument() != null
						&& castOther.getDateEchDocument() != null && this
						.getDateEchDocument().equals(
								castOther.getDateEchDocument())))
				&& ((this.getDateLivDocument() == castOther
						.getDateLivDocument()) || (this.getDateLivDocument() != null
						&& castOther.getDateLivDocument() != null && this
						.getDateLivDocument().equals(
								castOther.getDateLivDocument())))
				&& ((this.getLibelleDocument() == castOther
						.getLibelleDocument()) || (this.getLibelleDocument() != null
						&& castOther.getLibelleDocument() != null && this
						.getLibelleDocument().equals(
								castOther.getLibelleDocument())))
				&& ((this.getIdAdresse() == castOther.getIdAdresse()) || (this
						.getIdAdresse() != null
						&& castOther.getIdAdresse() != null && this
						.getIdAdresse().equals(castOther.getIdAdresse())))
				&& ((this.getIdAdresseLiv() == castOther.getIdAdresseLiv()) || (this
						.getIdAdresseLiv() != null
						&& castOther.getIdAdresseLiv() != null && this
						.getIdAdresseLiv().equals(
								castOther.getIdAdresseLiv())))
				&& ((this.getIdTiers() == castOther.getIdTiers()) || (this
						.getIdTiers() != null
						&& castOther.getIdTiers() != null && this
						.getIdTiers().equals(castOther.getIdTiers())))
				&& ((this.getCodeTiers() == castOther.getCodeTiers()) || (this
						.getCodeTiers() != null
						&& castOther.getCodeTiers() != null && this
						.getCodeTiers().equals(castOther.getCodeTiers())))
				&& ((this.getNomTiers() == castOther.getNomTiers()) || (this
						.getNomTiers() != null
						&& castOther.getNomTiers() != null && this
						.getNomTiers().equals(castOther.getNomTiers())))
				&& ((this.getPrenomTiers() == castOther.getPrenomTiers()) || (this
						.getPrenomTiers() != null
						&& castOther.getPrenomTiers() != null && this
						.getPrenomTiers().equals(castOther.getPrenomTiers())))
				&& ((this.getSurnomTiers() == castOther.getSurnomTiers()) || (this
						.getSurnomTiers() != null
						&& castOther.getSurnomTiers() != null && this
						.getSurnomTiers().equals(castOther.getSurnomTiers())))
				&& ((this.getCodeCompta() == castOther.getCodeCompta()) || (this
						.getCodeCompta() != null
						&& castOther.getCodeCompta() != null && this
						.getCodeCompta().equals(castOther.getCodeCompta())))
				&& ((this.getCompte() == castOther.getCompte()) || (this
						.getCompte() != null
						&& castOther.getCompte() != null && this.getCompte()
						.equals(castOther.getCompte())))
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
				&& ((this.getNbEDocument() == castOther.getNbEDocument()) || (this
						.getNbEDocument() != null
						&& castOther.getNbEDocument() != null && this
						.getNbEDocument().equals(castOther.getNbEDocument())))
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
				&& ((this.getNetTvaCalc() == castOther.getNetTvaCalc()) || (this
						.getNetTvaCalc() != null
						&& castOther.getNetTvaCalc() != null && this
						.getNetTvaCalc().equals(castOther.getNetTvaCalc())))
				&& ((this.getIP_ACCES() == castOther.getIP_ACCES()) || (this
						.getIP_ACCES() != null
						&& castOther.getIP_ACCES() != null && this
						.getIP_ACCES().equals(castOther.getIP_ACCES())))
				&& ((this.getTtc() == castOther.getTtc()) || (this.getTtc() != null
						&& castOther.getTtc() != null && this.getTtc().equals(
						castOther.getTtc())))
				&& ((this.getCommentaire() == castOther.getCommentaire()) || (this.getCommentaire() != null
						&& castOther.getCommentaire() != null && this.getCommentaire().equals(
						castOther.getCommentaire())))						
				&& ((this.getExport() == castOther.getExport()) || (this
						.getExport() != null
						&& castOther.getExport() != null && this.getExport()
						.equals(castOther.getExport())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIdDocument() == null ? 0 : this.getIdDocument()
						.hashCode());
		result = 37
				* result
				+ (getCodeDocument() == null ? 0 : this.getCodeDocument()
						.hashCode());
		result = 37
				* result
				+ (getDateDocument() == null ? 0 : this.getDateDocument()
						.hashCode());
		result = 37
				* result
				+ (getDateEchDocument() == null ? 0 : this
						.getDateEchDocument().hashCode());
		result = 37
				* result
				+ (getDateLivDocument() == null ? 0 : this
						.getDateLivDocument().hashCode());
		result = 37
				* result
				+ (getLibelleDocument() == null ? 0 : this.getLibelleDocument()
						.hashCode());
		result = 37
				* result
				+ (getIdAdresse() == null ? 0 : this.getIdAdresse()
						.hashCode());
		result = 37
				* result
				+ (getIdAdresseLiv() == null ? 0 : this.getIdAdresseLiv()
						.hashCode());
		result = 37 * result
				+ (getIdTiers() == null ? 0 : this.getIdTiers().hashCode());
		result = 37
				* result
				+ (getCodeTiers() == null ? 0 : this.getCodeTiers()
						.hashCode());
		result = 37 * result
				+ (getNomTiers() == null ? 0 : this.getNomTiers().hashCode());
		result = 37
				* result
				+ (getPrenomTiers() == null ? 0 : this.getPrenomTiers()
						.hashCode());
		result = 37
				* result
				+ (getSurnomTiers() == null ? 0 : this.getSurnomTiers()
						.hashCode());
		result = 37
				* result
				+ (getCodeCompta() == null ? 0 : this.getCodeCompta()
						.hashCode());
		result = 37 * result
				+ (getCompte() == null ? 0 : this.getCompte().hashCode());
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
				+ (getNbEDocument() == null ? 0 : this.getNbEDocument()
						.hashCode());
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
				+ (getNetTvaCalc() == null ? 0 : this.getNetTvaCalc()
						.hashCode());
		result = 37 * result
				+ (getIP_ACCES() == null ? 0 : this.getIP_ACCES().hashCode());
		result = 37 * result
				+ (getTtc() == null ? 0 : this.getTtc().hashCode());
		result = 37 * result
				+ (getCommentaire() == null ? 0 : this.getCommentaire().hashCode());		
		result = 37 * result
				+ (getExport() == null ? 0 : this.getExport().hashCode());
		return result;
	}




	

}
