package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.LibDate;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class EnteteTicketDeCaisseDTO extends ModelObject  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8090639162051801186L;
	private Integer id=0;
	private String codeDocument=LgrConstantes.C_STR_VIDE;
	private Date dateDocument=new Date();
	//private Date dateEchDocument=new Date();;
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
	private BigDecimal regleCompletDocument=new BigDecimal(0);
	private BigDecimal resteAReglerComplet=new BigDecimal(0);
	
	private BigDecimal mtTtcAvantRemiseGlobaleCalc = new BigDecimal(0);
	private BigDecimal netAPayer = new BigDecimal(0); //remplace le netTTcCalc après escompte
	private BigDecimal resteARegler = new BigDecimal(0);
	private BigDecimal acomptes = new BigDecimal(0);
	private BigDecimal avoirs = new BigDecimal(0);
	public String typeDocument;
//	private String libellePaiement ;
	
	
	public EnteteTicketDeCaisseDTO() {
	}

	public EnteteTicketDeCaisseDTO(Integer ID_FACTURE, String CODE_FACTURE,
			Date DATE_FACTURE, Date DATE_ECH_FACTURE, Date DATE_LIV_FACTURE,
			String LIBELLE_FACTURE, Integer ID_ADRESSE, Integer ID_ADRESSE_LIV,
			Integer ID_TIERS, String CODE_TIERS, String NOM_TIERS,
			String PRENOM_TIERS, String SURNOM_TIERS, String CODE_COMPTA,
			String COMPTE, Integer ID_T_PAIEMENT, String CODE_T_PAIEMENT,
			Integer ID_C_PAIEMENT, BigDecimal REGLE_FACTURE,
			BigDecimal REM_HT_FACTURE, BigDecimal TX_REM_HT_FACTURE,
			BigDecimal REM_TTC_FACTURE, BigDecimal TX_REM_TTC_FACTURE,
			Integer NB_E_FACTURE, BigDecimal MT_TTC_CALC, BigDecimal MT_HT_CALC,
			BigDecimal MT_TVA_CALC, BigDecimal NET_TTC_CALC, BigDecimal NET_HT_CALC,
			BigDecimal NET_TVA_CALC, String IP_ACCES, Boolean TTC, Boolean EXPORT,String COMMENTAIRE) {
		this.id = ID_FACTURE;
		this.codeDocument = CODE_FACTURE;
		this.dateDocument = DATE_FACTURE;
		//this.dateEchDocument = DATE_ECH_FACTURE;
		this.dateLivDocument = DATE_LIV_FACTURE;
		this.libelleDocument = LIBELLE_FACTURE;
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
		this.regleDocument = REGLE_FACTURE;
		this.remHtDocument = REM_HT_FACTURE;
		this.txRemHtDocument = TX_REM_HT_FACTURE;
		this.remTtcDocument = REM_TTC_FACTURE;
		this.txRemTtcDocument = TX_REM_TTC_FACTURE;
		this.nbEDocument = NB_E_FACTURE;
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

	public void setIHMEnteteFacture(EnteteTicketDeCaisseDTO ihmEnteteFacture){
		setIdDocument(ihmEnteteFacture.id);
		setCodeDocument(ihmEnteteFacture.codeDocument);
		setDateDocument(ihmEnteteFacture.dateDocument);
		//setDateEchDocument(ihmEnteteFacture.dateEchDocument);
		setDateLivDocument(ihmEnteteFacture.dateLivDocument);
		setLibelleDocument(ihmEnteteFacture.libelleDocument);
		setIdAdresse(ihmEnteteFacture.idAdresse);
		setIdAdresseLiv(ihmEnteteFacture.idAdresseLiv);
		setIdTiers(ihmEnteteFacture.idTiers);
		setCodeTiers(ihmEnteteFacture.codeTiers);
		setNomTiers(ihmEnteteFacture.nomTiers);
		setPrenomTiers(ihmEnteteFacture.prenomTiers);
		setSurnomTiers(ihmEnteteFacture.surnomTiers);
		setCodeCompta(ihmEnteteFacture.codeCompta);
		setCompte(ihmEnteteFacture.compte);
		setIdTPaiement(ihmEnteteFacture.idTPaiement);
		setCodeTPaiement(ihmEnteteFacture.codeTPaiement);
		setIdCPaiement(ihmEnteteFacture.idCPaiement);
		setRegleDocument(ihmEnteteFacture.regleDocument);
		setRemHtDocument(ihmEnteteFacture.remHtDocument);
		setTxRemHtDocument(ihmEnteteFacture.txRemHtDocument);
		setRemTtcDocument(ihmEnteteFacture.remTtcDocument);
		setTxRemTtcDocument(ihmEnteteFacture.txRemTtcDocument);
		setNbEDocument(ihmEnteteFacture.nbEDocument);
		setMtTtcCalc(ihmEnteteFacture.mtTtcCalc);
		setMtHtCalc(ihmEnteteFacture.mtHtCalc);
		setMtTvaCalc(ihmEnteteFacture.mtTvaCalc);
		setNetTtcCalc(ihmEnteteFacture.netTtcCalc);
		setNetHtCalc(ihmEnteteFacture.netHtCalc);
		setNetTvaCalc(ihmEnteteFacture.netTvaCalc);
		setIP_ACCES(ihmEnteteFacture.IP_ACCES);
		setTtc(ihmEnteteFacture.ttc);
		setExport(ihmEnteteFacture.export);
		setCommentaire(ihmEnteteFacture.commentaire);
	}
	
	
	public static EnteteTicketDeCaisseDTO copy(EnteteTicketDeCaisseDTO ihmEnteteFacture){
		EnteteTicketDeCaisseDTO ihmEnteteFactureLoc = new EnteteTicketDeCaisseDTO();
		ihmEnteteFactureLoc.setIdDocument(ihmEnteteFacture.id);
		ihmEnteteFactureLoc.setCodeDocument(ihmEnteteFacture.codeDocument);
		ihmEnteteFactureLoc.setDateDocument(ihmEnteteFacture.dateDocument);
//		ihmEnteteFactureLoc.setDateEchDocument(ihmEnteteFacture.dateEchDocument);
		ihmEnteteFactureLoc.setDateLivDocument(ihmEnteteFacture.dateLivDocument);
		ihmEnteteFactureLoc.setLibelleDocument(ihmEnteteFacture.libelleDocument);
		ihmEnteteFactureLoc.setIdAdresse(ihmEnteteFacture.idAdresse);
		ihmEnteteFactureLoc.setIdAdresseLiv(ihmEnteteFacture.idAdresseLiv);
		ihmEnteteFactureLoc.setIdTiers(ihmEnteteFacture.idTiers);
		ihmEnteteFactureLoc.setCodeTiers(ihmEnteteFacture.codeTiers);
		ihmEnteteFactureLoc.setNomTiers(ihmEnteteFacture.nomTiers);
		ihmEnteteFactureLoc.setPrenomTiers(ihmEnteteFacture.prenomTiers);
		ihmEnteteFactureLoc.setSurnomTiers(ihmEnteteFacture.surnomTiers);
		ihmEnteteFactureLoc.setCodeCompta(ihmEnteteFacture.codeCompta);
		ihmEnteteFactureLoc.setCompte(ihmEnteteFacture.compte);
		ihmEnteteFactureLoc.setIdTPaiement(ihmEnteteFacture.idTPaiement);
		ihmEnteteFactureLoc.setCodeTPaiement(ihmEnteteFacture.codeTPaiement);
		ihmEnteteFactureLoc.setIdCPaiement(ihmEnteteFacture.idCPaiement);
		ihmEnteteFactureLoc.setRegleDocument(ihmEnteteFacture.regleDocument);
		ihmEnteteFactureLoc.setRemHtDocument(ihmEnteteFacture.remHtDocument);
		ihmEnteteFactureLoc.setTxRemHtDocument(ihmEnteteFacture.txRemHtDocument);
		ihmEnteteFactureLoc.setRemTtcDocument(ihmEnteteFacture.remTtcDocument);
		ihmEnteteFactureLoc.setTxRemTtcDocument(ihmEnteteFacture.txRemTtcDocument);
		ihmEnteteFactureLoc.setNbEDocument(ihmEnteteFacture.nbEDocument);
		ihmEnteteFactureLoc.setMtTtcCalc(ihmEnteteFacture.mtTtcCalc);
		ihmEnteteFactureLoc.setMtHtCalc(ihmEnteteFacture.mtHtCalc);
		ihmEnteteFactureLoc.setMtTvaCalc(ihmEnteteFacture.mtTvaCalc);
		ihmEnteteFactureLoc.setNetTtcCalc(ihmEnteteFacture.netTtcCalc);
		ihmEnteteFactureLoc.setNetHtCalc(ihmEnteteFacture.netHtCalc);
		ihmEnteteFactureLoc.setNetTvaCalc(ihmEnteteFacture.netTvaCalc);
		ihmEnteteFactureLoc.setIP_ACCES(ihmEnteteFacture.IP_ACCES);
		ihmEnteteFactureLoc.setTtc(ihmEnteteFacture.ttc);
		ihmEnteteFactureLoc.setExport(ihmEnteteFacture.export);
		ihmEnteteFactureLoc.setCommentaire(ihmEnteteFacture.commentaire);
		return ihmEnteteFactureLoc;
	}
	
	public boolean factureEstVide(){
		Date dateExemple = new Date();
//		if(!getCodeFacture().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!(LibDate.compareTo(getDateDocument(), dateExemple)==0))return false;
//		if(!(LibDate.compareTo(getDateEchDocument(), dateExemple)==0))return false;
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
//		if(!LibChaine.emptyNumeric(getRegleFacture()))return false;
//		if(!LibChaine.emptyNumeric(getRemHtFacture()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemHtFacture()))return false;
//		if(!LibChaine.emptyNumeric(getRemTtcFacture()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemTtcFacture()))return false;
//		if(!getNbEFacture().equals(0))return false;
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
//		if(!getCodeFacture().equals(LgrConstantes.C_STR_VIDE))return true;
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
//		if(!LibChaine.emptyNumeric(getRegleFacture()))return false;
//		if(!LibChaine.emptyNumeric(getRemHtFacture()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemHtFacture()))return false;
//		if(!LibChaine.emptyNumeric(getRemTtcFacture()))return false;
//		if(!LibChaine.emptyNumeric(getTxRemTtcFacture()))return false;
//		if(!getNbEFacture().equals(0))return false;
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

	public void setIdDocument(Integer ID_FACTURE) {
		firePropertyChange("idDocument", this.id, this.id = ID_FACTURE);
	}

	public String getCodeDocument() {
		return this.codeDocument;
	}

	public void setCodeDocument(String CODE_FACTURE) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = CODE_FACTURE);
	}

	public Date getDateDocument() {
		return this.dateDocument;
	}

	public void setDateDocument(Date DATE_FACTURE) {
		firePropertyChange("dateDocument", this.dateDocument, this.dateDocument = DATE_FACTURE);
	}

//	public Date getDateEchDocument() {
//		return this.dateEchDocument;
//	}
//
//	public void setDateEchDocument(Date DATE_ECH_FACTURE) {
//		firePropertyChange("dateEchDocument", this.dateEchDocument, this.dateEchDocument = DATE_ECH_FACTURE);
//	}

	public Date getDateLivDocument() {
		return this.dateLivDocument;
	}

	public void setDateLivDocument(Date DATE_LIV_FACTURE) {
		firePropertyChange("dateLivDocument", this.dateLivDocument, this.dateLivDocument = DATE_LIV_FACTURE);
	}

	public String getLibelleDocument() {
		return this.libelleDocument;
	}

	public void setLibelleDocument(String LIBELLE_FACTURE) {
		firePropertyChange("libelleDocument", this.libelleDocument, this.libelleDocument = LIBELLE_FACTURE);
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

	public Integer getNbEDocument() {
		return this.nbEDocument;
	}

	public void setNbEDocument(Integer NB_E_FACTURE) {
		firePropertyChange("nbEDocument", this.nbEDocument, this.nbEDocument = NB_E_FACTURE);
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
	
	public BigDecimal getMtTtcAvantRemiseGlobaleCalc() {
		return mtTtcAvantRemiseGlobaleCalc;
	}

	public void setMtTtcAvantRemiseGlobaleCalc(
			BigDecimal mtTtcAvantRemiseGlobaleCalc) {
		firePropertyChange("mtTtcAvantRemiseGlobaleCalc", this.mtTtcAvantRemiseGlobaleCalc, this.mtTtcAvantRemiseGlobaleCalc = mtTtcAvantRemiseGlobaleCalc);
	}

	public BigDecimal getNetAPayer() {
		return netAPayer;
	}

	public void setNetAPayer(BigDecimal netAPayer) {
		firePropertyChange("netAPayer", this.netAPayer, this.netAPayer = netAPayer);
	}

	public BigDecimal getResteARegler() {
		return resteARegler;
	}

	public void setResteARegler(BigDecimal resteARegler) {
		firePropertyChange("resteARegler", this.resteARegler, this.resteARegler = resteARegler);
	}

	public BigDecimal getAcomptes() {
		return acomptes;
	}

	public void setAcomptes(BigDecimal acomptes) {
		firePropertyChange("acomptes", this.acomptes, this.acomptes = acomptes);
	}

	public BigDecimal getRegleCompletDocument() {
		return regleCompletDocument;
	}

	public void setRegleCompletDocument(BigDecimal regleCompletDocument) {
		firePropertyChange("regleCompletDocument", this.regleCompletDocument, this.regleCompletDocument = regleCompletDocument);
	}
	

	public BigDecimal getResteAReglerComplet() {
		return resteAReglerComplet;
	}

	public void setResteAReglerComplet(BigDecimal resteAReglerComplet) {
		firePropertyChange("resteAReglerComplet", this.resteAReglerComplet, this.resteAReglerComplet = resteAReglerComplet);
	}

	
	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		firePropertyChange("typeDocument", this.typeDocument, this.typeDocument = typeDocument);
	}

	public BigDecimal getAvoirs() {
		return avoirs;
	}

	public void setAvoirs(BigDecimal avoirs) {
		firePropertyChange("avoirs", this.avoirs, this.avoirs = avoirs);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IP_ACCES == null) ? 0 : IP_ACCES.hashCode());
		result = prime * result
				+ ((acomptes == null) ? 0 : acomptes.hashCode());
		result = prime * result + ((avoirs == null) ? 0 : avoirs.hashCode());
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
//		result = prime * result
//				+ ((dateEchDocument == null) ? 0 : dateEchDocument.hashCode());
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
		result = prime * result
				+ ((libelleDocument == null) ? 0 : libelleDocument.hashCode());
		result = prime * result
				+ ((mtHtCalc == null) ? 0 : mtHtCalc.hashCode());
		result = prime
				* result
				+ ((mtTtcAvantRemiseGlobaleCalc == null) ? 0
						: mtTtcAvantRemiseGlobaleCalc.hashCode());
		result = prime * result
				+ ((mtTtcCalc == null) ? 0 : mtTtcCalc.hashCode());
		result = prime * result
				+ ((mtTvaCalc == null) ? 0 : mtTvaCalc.hashCode());
		result = prime * result
				+ ((nbEDocument == null) ? 0 : nbEDocument.hashCode());
		result = prime * result
				+ ((netAPayer == null) ? 0 : netAPayer.hashCode());
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
		result = prime
				* result
				+ ((regleCompletDocument == null) ? 0 : regleCompletDocument
						.hashCode());
		result = prime * result
				+ ((regleDocument == null) ? 0 : regleDocument.hashCode());
		result = prime * result
				+ ((remHtDocument == null) ? 0 : remHtDocument.hashCode());
		result = prime * result
				+ ((remTtcDocument == null) ? 0 : remTtcDocument.hashCode());
		result = prime * result
				+ ((resteARegler == null) ? 0 : resteARegler.hashCode());
		result = prime
				* result
				+ ((resteAReglerComplet == null) ? 0 : resteAReglerComplet
						.hashCode());
		result = prime * result
				+ ((surnomTiers == null) ? 0 : surnomTiers.hashCode());
		result = prime * result + ((ttc == null) ? 0 : ttc.hashCode());
		result = prime * result
				+ ((txRemHtDocument == null) ? 0 : txRemHtDocument.hashCode());
		result = prime
				* result
				+ ((txRemTtcDocument == null) ? 0 : txRemTtcDocument.hashCode());
		result = prime * result
				+ ((typeDocument == null) ? 0 : typeDocument.hashCode());
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
		EnteteTicketDeCaisseDTO other = (EnteteTicketDeCaisseDTO) obj;
		if (IP_ACCES == null) {
			if (other.IP_ACCES != null)
				return false;
		} else if (!IP_ACCES.equals(other.IP_ACCES))
			return false;
		if (acomptes == null) {
			if (other.acomptes != null)
				return false;
		} else if (!acomptes.equals(other.acomptes))
			return false;
		if (avoirs == null) {
			if (other.avoirs != null)
				return false;
		} else if (!avoirs.equals(other.avoirs))
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
//		if (dateEchDocument == null) {
//			if (other.dateEchDocument != null)
//				return false;
//		} else if (!dateEchDocument.equals(other.dateEchDocument))
//			return false;
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
		if (mtTtcAvantRemiseGlobaleCalc == null) {
			if (other.mtTtcAvantRemiseGlobaleCalc != null)
				return false;
		} else if (!mtTtcAvantRemiseGlobaleCalc
				.equals(other.mtTtcAvantRemiseGlobaleCalc))
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
		if (netAPayer == null) {
			if (other.netAPayer != null)
				return false;
		} else if (!netAPayer.equals(other.netAPayer))
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
		if (regleCompletDocument == null) {
			if (other.regleCompletDocument != null)
				return false;
		} else if (!regleCompletDocument.equals(other.regleCompletDocument))
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
		if (resteARegler == null) {
			if (other.resteARegler != null)
				return false;
		} else if (!resteARegler.equals(other.resteARegler))
			return false;
		if (resteAReglerComplet == null) {
			if (other.resteAReglerComplet != null)
				return false;
		} else if (!resteAReglerComplet.equals(other.resteAReglerComplet))
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
		if (typeDocument == null) {
			if (other.typeDocument != null)
				return false;
		} else if (!typeDocument.equals(other.typeDocument))
			return false;
		return true;
	}





	
}