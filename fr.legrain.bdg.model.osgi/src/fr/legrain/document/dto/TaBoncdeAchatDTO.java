package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.document.model.TaBoncde;
import fr.legrain.document.model.TaBoncdeAchat;
import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.LibDate;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class TaBoncdeAchatDTO extends ModelObject implements java.io.Serializable, IDocumentDTO {

	private static final long serialVersionUID = 3915574190173815276L;
	
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
	private String nomEntreprise=LgrConstantes.C_STR_VIDE;
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
//	private Boolean export=false;
	private String commentaire = "";
	private String codeTTvaDoc=LgrConstantes.C_STR_VIDE;
	private String codeEtat;
	private String liblEtat;
	private Integer versionObj;
	private boolean estMiseADisposition;
	private Date dateExport;
	private Date dateVerrouillage;
	private String numeroCommandeFournisseur;
	private Boolean gestionLot = false;
	
	private Integer idTransporteur;
	private String codeTransporteur = LgrConstantes.C_STR_VIDE;
	private Boolean utiliseUniteSaisie = true;

	public TaBoncdeAchatDTO() {
	}
	
	public TaBoncdeAchatDTO(Integer idDocument, 
			String codeDocument, 
			Date dateDocument, 
			String libelleDocument, 
			String codeTiers, 
			String nomTiers,
			String prenomTiers,
			String nomEntreprise,
			Date dateEchDocument,
			Date dateExport,
			BigDecimal netHtCalc,
			BigDecimal netTvaCalc,
			BigDecimal netTtcCalc ) {
		this.id = idDocument;
		this.codeDocument = codeDocument;
		this.dateDocument = dateDocument;
		this.libelleDocument = libelleDocument;
		this.codeTiers = codeTiers;
		this.nomTiers = nomTiers;
		this.prenomTiers = prenomTiers;
		this.setNomEntreprise(nomEntreprise);
		this.dateEchDocument = dateEchDocument;
		this.dateExport = dateExport;
		this.netHtCalc = netHtCalc;
		this.netTvaCalc = netTvaCalc;
		this.netTtcCalc = netTtcCalc;
	}
	
	// Utilisé par la requete préparée FIND_ALL_LIGHT_PERIODE, FIND_TRANSFORME_LIGHT_PERIODE
		public TaBoncdeAchatDTO(Integer idDocument, 
				String codeDocument, 
				Date dateDocument, 
				String libelleDocument, 
				String codeTiers, 
				String nomTiers,
				Date dateEchDocument,
				Date dateExport,
				BigDecimal netHtCalc,
				BigDecimal netTvaCalc,
				BigDecimal netTtcCalc ) {
			this.id = idDocument;
			this.codeDocument = codeDocument;
			this.dateDocument = dateDocument;
			this.libelleDocument = libelleDocument;
			this.codeTiers = codeTiers;
			this.nomTiers = nomTiers;
			this.dateEchDocument = dateEchDocument;
			this.dateExport = dateExport;
			this.netHtCalc = netHtCalc;
			this.netTvaCalc = netTvaCalc;
			this.netTtcCalc = netTtcCalc;
		}
		
	public TaBoncdeAchatDTO(Integer ID_Boncde, String CODE_Boncde,
			Date DATE_Boncde, Date DATE_ECH_Boncde, Date DATE_LIV_Boncde,
			String LIBELLE_Boncde, Integer ID_ADRESSE, Integer ID_ADRESSE_LIV,
			Integer ID_TIERS, String CODE_TIERS, String NOM_TIERS,
			String PRENOM_TIERS, String SURNOM_TIERS, String CODE_COMPTA,
			String COMPTE, Integer ID_T_PAIEMENT, String CODE_T_PAIEMENT,
			Integer ID_C_PAIEMENT, BigDecimal REGLE_Boncde,
			BigDecimal REM_HT_Boncde, BigDecimal TX_REM_HT_Boncde,
			BigDecimal REM_TTC_Boncde, BigDecimal TX_REM_TTC_Boncde,
			Integer NB_E_Boncde, BigDecimal MT_TTC_CALC, BigDecimal MT_HT_CALC,
			BigDecimal MT_TVA_CALC, BigDecimal NET_TTC_CALC, BigDecimal NET_HT_CALC,
			BigDecimal NET_TVA_CALC, String IP_ACCES, Boolean TTC, Date dateExport,String COMMENTAIRE) {
		this.id = ID_Boncde;
		this.codeDocument = CODE_Boncde;
		this.dateDocument = DATE_Boncde;
		this.dateEchDocument = DATE_ECH_Boncde;
		this.dateLivDocument = DATE_LIV_Boncde;
		this.libelleDocument = LIBELLE_Boncde;
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
		this.regleDocument = REGLE_Boncde;
		this.remHtDocument = REM_HT_Boncde;
		this.txRemHtDocument = TX_REM_HT_Boncde;
		this.remTtcDocument = REM_TTC_Boncde;
		this.txRemTtcDocument = TX_REM_TTC_Boncde;
		this.nbEDocument = NB_E_Boncde;
		this.mtTtcCalc = MT_TTC_CALC;
		this.mtHtCalc = MT_HT_CALC;
		this.mtTvaCalc = MT_TVA_CALC;
		this.netTtcCalc = NET_TTC_CALC;
		this.netHtCalc = NET_HT_CALC;
		this.netTvaCalc = NET_TVA_CALC;
		this.ipAcces = IP_ACCES;
		this.ttc = TTC;
		this.dateExport = dateExport;
		this.commentaire = COMMENTAIRE;
	}

	public void setIHMEnteteBoncde(TaBoncdeAchatDTO ihmEnteteBoncde){
		setId(ihmEnteteBoncde.id);
		setCodeDocument(ihmEnteteBoncde.codeDocument);
		setDateDocument(ihmEnteteBoncde.dateDocument);
		setDateEchDocument(ihmEnteteBoncde.dateEchDocument);
		setDateLivDocument(ihmEnteteBoncde.dateLivDocument);
		setLibelleDocument(ihmEnteteBoncde.libelleDocument);
		setIdAdresse(ihmEnteteBoncde.idAdresse);
		setIdAdresseLiv(ihmEnteteBoncde.idAdresseLiv);
		setIdTiers(ihmEnteteBoncde.idTiers);
		setCodeTiers(ihmEnteteBoncde.codeTiers);
		setNomTiers(ihmEnteteBoncde.nomTiers);
		setPrenomTiers(ihmEnteteBoncde.prenomTiers);
		setSurnomTiers(ihmEnteteBoncde.surnomTiers);
		setCodeCompta(ihmEnteteBoncde.codeCompta);
		setCompte(ihmEnteteBoncde.compte);
		setIdTPaiement(ihmEnteteBoncde.idTPaiement);
		setCodeTPaiement(ihmEnteteBoncde.codeTPaiement);
		setIdCPaiement(ihmEnteteBoncde.idCPaiement);
		setRegleDocument(ihmEnteteBoncde.regleDocument);
		setRemHtDocument(ihmEnteteBoncde.remHtDocument);
		setTxRemHtDocument(ihmEnteteBoncde.txRemHtDocument);
		setRemTtcDocument(ihmEnteteBoncde.remTtcDocument);
		setTxRemTtcDocument(ihmEnteteBoncde.txRemTtcDocument);
		setNbEDocument(ihmEnteteBoncde.nbEDocument);
		setMtTtcCalc(ihmEnteteBoncde.mtTtcCalc);
		setMtHtCalc(ihmEnteteBoncde.mtHtCalc);
		setMtTvaCalc(ihmEnteteBoncde.mtTvaCalc);
		setNetTtcCalc(ihmEnteteBoncde.netTtcCalc);
		setNetHtCalc(ihmEnteteBoncde.netHtCalc);
		setNetTvaCalc(ihmEnteteBoncde.netTvaCalc);
		setIpAcces(ihmEnteteBoncde.ipAcces);
		setTtc(ihmEnteteBoncde.ttc);
		setDateExport(ihmEnteteBoncde.dateExport);
		setCommentaire(ihmEnteteBoncde.commentaire);
	}
	
	
	public static TaBoncdeAchatDTO copy(TaBoncdeAchatDTO ihmEnteteBoncde){
		TaBoncdeAchatDTO ihmEnteteBoncdeLoc = new TaBoncdeAchatDTO();
		ihmEnteteBoncdeLoc.setId(ihmEnteteBoncde.id);
		ihmEnteteBoncdeLoc.setCodeDocument(ihmEnteteBoncde.codeDocument);
		ihmEnteteBoncdeLoc.setDateDocument(ihmEnteteBoncde.dateDocument);
		ihmEnteteBoncdeLoc.setDateEchDocument(ihmEnteteBoncde.dateEchDocument);
		ihmEnteteBoncdeLoc.setDateLivDocument(ihmEnteteBoncde.dateLivDocument);
		ihmEnteteBoncdeLoc.setLibelleDocument(ihmEnteteBoncde.libelleDocument);
		ihmEnteteBoncdeLoc.setIdAdresse(ihmEnteteBoncde.idAdresse);
		ihmEnteteBoncdeLoc.setIdAdresseLiv(ihmEnteteBoncde.idAdresseLiv);
		ihmEnteteBoncdeLoc.setIdTiers(ihmEnteteBoncde.idTiers);
		ihmEnteteBoncdeLoc.setCodeTiers(ihmEnteteBoncde.codeTiers);
		ihmEnteteBoncdeLoc.setNomTiers(ihmEnteteBoncde.nomTiers);
		ihmEnteteBoncdeLoc.setPrenomTiers(ihmEnteteBoncde.prenomTiers);
		ihmEnteteBoncdeLoc.setSurnomTiers(ihmEnteteBoncde.surnomTiers);
		ihmEnteteBoncdeLoc.setCodeCompta(ihmEnteteBoncde.codeCompta);
		ihmEnteteBoncdeLoc.setCompte(ihmEnteteBoncde.compte);
		ihmEnteteBoncdeLoc.setIdTPaiement(ihmEnteteBoncde.idTPaiement);
		ihmEnteteBoncdeLoc.setCodeTPaiement(ihmEnteteBoncde.codeTPaiement);
		ihmEnteteBoncdeLoc.setIdCPaiement(ihmEnteteBoncde.idCPaiement);
		ihmEnteteBoncdeLoc.setRegleDocument(ihmEnteteBoncde.regleDocument);
		ihmEnteteBoncdeLoc.setRemHtDocument(ihmEnteteBoncde.remHtDocument);
		ihmEnteteBoncdeLoc.setTxRemHtDocument(ihmEnteteBoncde.txRemHtDocument);
		ihmEnteteBoncdeLoc.setRemTtcDocument(ihmEnteteBoncde.remTtcDocument);
		ihmEnteteBoncdeLoc.setTxRemTtcDocument(ihmEnteteBoncde.txRemTtcDocument);
		ihmEnteteBoncdeLoc.setNbEDocument(ihmEnteteBoncde.nbEDocument);
		ihmEnteteBoncdeLoc.setMtTtcCalc(ihmEnteteBoncde.mtTtcCalc);
		ihmEnteteBoncdeLoc.setMtHtCalc(ihmEnteteBoncde.mtHtCalc);
		ihmEnteteBoncdeLoc.setMtTvaCalc(ihmEnteteBoncde.mtTvaCalc);
		ihmEnteteBoncdeLoc.setNetTtcCalc(ihmEnteteBoncde.netTtcCalc);
		ihmEnteteBoncdeLoc.setNetHtCalc(ihmEnteteBoncde.netHtCalc);
		ihmEnteteBoncdeLoc.setNetTvaCalc(ihmEnteteBoncde.netTvaCalc);
		ihmEnteteBoncdeLoc.setIpAcces(ihmEnteteBoncde.ipAcces);
		ihmEnteteBoncdeLoc.setTtc(ihmEnteteBoncde.ttc);
		ihmEnteteBoncdeLoc.setDateExport(ihmEnteteBoncde.dateExport);
		ihmEnteteBoncdeLoc.setCommentaire(ihmEnteteBoncde.commentaire);
		return ihmEnteteBoncdeLoc;
	}
	
	public boolean BoncdeAchatEstVide(){
		Date dateExemple = new Date();
//		if(!getCODE_Boncde().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!(LibDate.compareTo(getDateDocument(), dateExemple)==0))return false;
		if(!(LibDate.compareTo(getDateEchDocument(), dateExemple)==0))return false;
		if(!(LibDate.compareTo(getDateLivDocument(), dateExemple)==0))return false;
		
		if(getLibelleDocument()!=null && !getLibelleDocument().equals(LgrConstantes.C_STR_VIDE))return false;

		if(!getIdTiers().equals(0))return false;
		if(getCodeTiers()!=null && !getCodeTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getNomTiers()!=null && !getNomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getPrenomTiers()!=null && !getPrenomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getSurnomTiers()!=null && !getSurnomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCodeCompta()!=null && !getCodeCompta().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCompte()!=null && !getCompte().equals(LgrConstantes.C_STR_VIDE))return false;
		return true;
	}
	
	public boolean BoncdeAchatEstVide(Date dateDansExercice){
		Date dateExemple = new Date();
//		if(!getCODE_Boncde().equals(LgrConstantes.C_STR_VIDE))return true;
		if(!((LibDate.compareTo(getDateDocument(), dateDansExercice)==0) || 
				(LibDate.compareTo(getDateDocument(), dateExemple)==0)))return false;
//		if(!((LibDate.compareTo(getDateEchDocument(), dateDansExercice)==0) || 
//				(LibDate.compareTo(getDateEchDocument(), dateExemple)==0)))return false;
//		if(!((LibDate.compareTo(getDateLivDocument(), dateDansExercice)==0) || 
//				(LibDate.compareTo(getDateLivDocument(), dateExemple)==0)))return false;
		
		if(getLibelleDocument()!=null && !getLibelleDocument().equals(LgrConstantes.C_STR_VIDE))return false;

		if(!getIdTiers().equals(0))return false;
		if(getCodeTiers()!=null && !getCodeTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getNomTiers()!=null && !getNomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getPrenomTiers()!=null && !getPrenomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getSurnomTiers()!=null && !getSurnomTiers().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCodeCompta()!=null && !getCodeCompta().equals(LgrConstantes.C_STR_VIDE))return false;
		if(getCompte()!=null && !getCompte().equals(LgrConstantes.C_STR_VIDE))return false;
		return true;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer ID_Boncde) {
		firePropertyChange("idDocument", this.id, this.id = ID_Boncde);
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_document",table = "ta_boncde",champEntite="codeDocument", clazz = TaBoncdeAchatDTO.class)
	public String getCodeDocument() {
		return this.codeDocument;
	}

	public void setCodeDocument(String CODE_Boncde) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = CODE_Boncde);
	}

	@LgrHibernateValidated(champBd = "date_document",table = "ta_boncde",champEntite="dateDocument",clazz = TaBoncdeAchatDTO.class)
	public Date getDateDocument() {
		return this.dateDocument;
	}

	public void setDateDocument(Date DATE_Boncde) {
		firePropertyChange("dateDocument", this.dateDocument, this.dateDocument = DATE_Boncde);
	}

	@LgrHibernateValidated(champBd = "date_ech_document",table = "ta_boncde",champEntite="dateEchDocument",clazz = TaBoncdeAchatDTO.class)
	public Date getDateEchDocument() {
		return this.dateEchDocument;
	}

	public void setDateEchDocument(Date DATE_ECH_Boncde) {
		firePropertyChange("dateEchDocument", this.dateEchDocument, this.dateEchDocument = DATE_ECH_Boncde);
	}

	@LgrHibernateValidated(champBd = "date_liv_document",table = "ta_boncde",champEntite="dateLivDocument",clazz = TaBoncdeAchatDTO.class)
	public Date getDateLivDocument() {
		return this.dateLivDocument;
	}

	public void setDateLivDocument(Date DATE_LIV_Boncde) {
		firePropertyChange("dateLivDocument", this.dateLivDocument, this.dateLivDocument = DATE_LIV_Boncde);
	}

	@LgrHibernateValidated(champBd = "libelle_document",table = "ta_boncde",champEntite="libelleDocument",clazz = TaBoncdeAchatDTO.class)
	public String getLibelleDocument() {
		return this.libelleDocument;
	}

	public void setLibelleDocument(String LIBELLE_Boncde) {
		firePropertyChange("libelleDocument", this.libelleDocument, this.libelleDocument = LIBELLE_Boncde);
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

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_tiers",table = "ta_tiers",champEntite="codeTiers",clazz = TaBoncdeAchatDTO.class)
	public String getCodeTiers() {
		return this.codeTiers;
	}

	public void setCodeTiers(String CODE_TIERS) {
		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = CODE_TIERS);
	}

	@LgrHibernateValidated(champBd = "nom_tiers",table = "ta_tiers",champEntite="nomTiers",clazz = TaBoncdeAchatDTO.class)
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

	@Size(min=0, max=8)
	@LgrHibernateValidated(champBd = "code_compta",table = "ta_tiers",champEntite="codeCompta",clazz = TaBoncdeAchatDTO.class)
	public String getCodeCompta() {
		return this.codeCompta;
	}

	public void setCodeCompta(String CODE_COMPTA) {
		firePropertyChange("codeCompta", this.codeCompta, this.codeCompta = CODE_COMPTA);
	}

	@Size(min=0, max=8)
	@LgrHibernateValidated(champBd = "compte",table = "ta_tiers",champEntite="compte",clazz = TaBoncdeAchatDTO.class)
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

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_t_paiement",table = "ta_t_paiement",champEntite="codeTPaiement",clazz = TaBoncdeAchatDTO.class)
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

//	public BigDecimal getREGLE_Boncde() {
//		return this.REGLE_Boncde;
//	}
//
//	public void setREGLE_Boncde(BigDecimal REGLE_Boncde) {
//		firePropertyChange("REGLE_Boncde", this.REGLE_Boncde, this.REGLE_Boncde = REGLE_Boncde);
//	}

	@LgrHibernateValidated(champBd = "rem_ht_document",table = "ta_boncde",champEntite="remHtDocument",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getRemHtDocument() {
		return this.remHtDocument;
	}

	public void setRemHtDocument(BigDecimal REM_HT_Boncde) {
		firePropertyChange("remHtDocument", this.remHtDocument, this.remHtDocument = REM_HT_Boncde);
	}

	@LgrHibernateValidated(champBd = "tx_rem_ht_document",table = "ta_boncde",champEntite="txRemHtDocument",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getTxRemHtDocument() {
		return this.txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal TX_REM_HT_Boncde) {
		firePropertyChange("txRemHtDocument", this.txRemHtDocument, this.txRemHtDocument = TX_REM_HT_Boncde);
	}

	@LgrHibernateValidated(champBd = "rem_ttc_document",table = "ta_boncde",champEntite="remTtcDocument",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getRemTtcDocument() {
		return this.remTtcDocument;
	}

	public void setRemTtcDocument(BigDecimal REM_TTC_Boncde) {
		firePropertyChange("remTtcDocument", this.remTtcDocument, this.remTtcDocument = REM_TTC_Boncde);
	}

	@LgrHibernateValidated(champBd = "tx_rem_ttc_document",table = "ta_boncde",champEntite="txRemTtcDocument",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getTxRemTtcDocument() {
		return this.txRemTtcDocument;
	}

	public void setTxRemTtcDocument(BigDecimal TX_REM_TTC_Boncde) {
		firePropertyChange("txRemTtcDocument", this.txRemTtcDocument, this.txRemTtcDocument = TX_REM_TTC_Boncde);
	}

	public Integer getNbEDocument() {
		return this.nbEDocument;
	}

	public void setNbEDocument(Integer NB_E_Boncde) {
		firePropertyChange("nbEDocument", this.nbEDocument, this.nbEDocument = NB_E_Boncde);
	}

	@LgrHibernateValidated(champBd = "mt_ttc_calc",table = "ta_boncde",champEntite="mtTtcCalc",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getMtTtcCalc() {
		return this.mtTtcCalc;
	}

	public void setMtTtcCalc(BigDecimal MT_TTC_CALC) {
		firePropertyChange("mtTtcCalc", this.mtTtcCalc, this.mtTtcCalc = MT_TTC_CALC);
	}

	@LgrHibernateValidated(champBd = "mt_ht_calc",table = "ta_boncde",champEntite="mtHtCalc",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getMtHtCalc() {
		return this.mtHtCalc;
	}

	public void setMtHtCalc(BigDecimal MT_HT_CALC) {
		firePropertyChange("mtHtCalc", this.mtHtCalc, this.mtHtCalc = MT_HT_CALC);
	}

	@LgrHibernateValidated(champBd = "mt_tva_calc",table = "ta_boncde",champEntite="mtTvaCalc",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getMtTvaCalc() {
		return this.mtTvaCalc;
	}

	public void setMtTvaCalc(BigDecimal MT_TVA_CALC) {
		firePropertyChange("mtTvaCalc", this.mtTvaCalc, this.mtTvaCalc = MT_TVA_CALC);
	}

	@LgrHibernateValidated(champBd = "net_ttc_calc",table = "ta_boncde",champEntite="netTtcCalc",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getNetTtcCalc() {
		return this.netTtcCalc;
	}

	public void setNetTtcCalc(BigDecimal NET_TTC_CALC) {
		firePropertyChange("netTtcCalc", this.netTtcCalc, this.netTtcCalc = NET_TTC_CALC);
	}

	@LgrHibernateValidated(champBd = "net_ht_calc",table = "ta_boncde",champEntite="netHtCalc",clazz = TaBoncdeAchatDTO.class)
	public BigDecimal getNetHtCalc() {
		return this.netHtCalc;
	}

	public void setNetHtCalc(BigDecimal NET_HT_CALC) {
		firePropertyChange("netHtCalc", this.netHtCalc, this.netHtCalc = NET_HT_CALC);
	}

	@LgrHibernateValidated(champBd = "net_tva_calc",table = "ta_boncde",champEntite="netTvaCalc",clazz = TaBoncdeAchatDTO.class)
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

//	@LgrHibernateValidated(champBd = "export",table = "ta_boncde",champEntite="export",clazz = TaBoncdeDTO.class)
//	public Boolean getExport() {
//		return this.export;
//	}
//
//	public void setExport(Boolean EXPORT) {
//		firePropertyChange("export", this.export, this.export = EXPORT);
//	}
	
	@LgrHibernateValidated(champBd = "commentaire",table = "ta_boncde",champEntite="commentaire",clazz = TaBoncdeAchatDTO.class)
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String COMMENTAIRE) {
		firePropertyChange("commentaire", this.commentaire, this.commentaire = COMMENTAIRE);		
	}	

	public BigDecimal getRegleDocument() {
		return regleDocument;
	}

	public void setRegleDocument(BigDecimal regleDocument) {
		firePropertyChange("regleDocument", this.regleDocument, this.regleDocument = regleDocument);		
	}
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public String getCodeTTvaDoc() {
		return codeTTvaDoc;
	}

	public void setCodeTTvaDoc(String codeTTvaDoc) {
		this.codeTTvaDoc = codeTTvaDoc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeCompta == null) ? 0 : codeCompta.hashCode());
		result = prime * result + ((codeDocument == null) ? 0 : codeDocument.hashCode());
		result = prime * result + ((codeTPaiement == null) ? 0 : codeTPaiement.hashCode());
		result = prime * result + ((codeTTvaDoc == null) ? 0 : codeTTvaDoc.hashCode());
		result = prime * result + ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result + ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result + ((dateDocument == null) ? 0 : dateDocument.hashCode());
		result = prime * result + ((dateEchDocument == null) ? 0 : dateEchDocument.hashCode());
		result = prime * result + ((dateLivDocument == null) ? 0 : dateLivDocument.hashCode());
		result = prime * result + ((dateExport == null) ? 0 : dateExport.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idAdresse == null) ? 0 : idAdresse.hashCode());
		result = prime * result + ((idAdresseLiv == null) ? 0 : idAdresseLiv.hashCode());
		result = prime * result + ((idCPaiement == null) ? 0 : idCPaiement.hashCode());
		result = prime * result + ((idTPaiement == null) ? 0 : idTPaiement.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result + ((ipAcces == null) ? 0 : ipAcces.hashCode());
		result = prime * result + ((libelleDocument == null) ? 0 : libelleDocument.hashCode());
		result = prime * result + ((mtHtCalc == null) ? 0 : mtHtCalc.hashCode());
		result = prime * result + ((mtTtcCalc == null) ? 0 : mtTtcCalc.hashCode());
		result = prime * result + ((mtTvaCalc == null) ? 0 : mtTvaCalc.hashCode());
		result = prime * result + ((nbEDocument == null) ? 0 : nbEDocument.hashCode());
		result = prime * result + ((netHtCalc == null) ? 0 : netHtCalc.hashCode());
		result = prime * result + ((netTtcCalc == null) ? 0 : netTtcCalc.hashCode());
		result = prime * result + ((netTvaCalc == null) ? 0 : netTvaCalc.hashCode());
		result = prime * result + ((nomTiers == null) ? 0 : nomTiers.hashCode());
		result = prime * result + ((prenomTiers == null) ? 0 : prenomTiers.hashCode());
		result = prime * result + ((regleDocument == null) ? 0 : regleDocument.hashCode());
		result = prime * result + ((remHtDocument == null) ? 0 : remHtDocument.hashCode());
		result = prime * result + ((remTtcDocument == null) ? 0 : remTtcDocument.hashCode());
		result = prime * result + ((surnomTiers == null) ? 0 : surnomTiers.hashCode());
		result = prime * result + ((ttc == null) ? 0 : ttc.hashCode());
		result = prime * result + ((txRemHtDocument == null) ? 0 : txRemHtDocument.hashCode());
		result = prime * result + ((txRemTtcDocument == null) ? 0 : txRemTtcDocument.hashCode());
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
		TaBoncdeAchatDTO other = (TaBoncdeAchatDTO) obj;
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
		if (codeTTvaDoc == null) {
			if (other.codeTTvaDoc != null)
				return false;
		} else if (!codeTTvaDoc.equals(other.codeTTvaDoc))
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
		if (dateExport == null) {
			if (other.dateExport != null)
				return false;
		} else if (!dateExport.equals(other.dateExport))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
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


	@Override
	public Date getDateExport() {
		// TODO Auto-generated method stub
		return dateExport;
	}

	@Override
	public Date getDateVerrouillage() {
		// TODO Auto-generated method stub
		return dateVerrouillage;
	}

	@Override
	public boolean getEstMisADisposition() {
		// TODO Auto-generated method stub
		return estMiseADisposition;
	}
	@Override
	public boolean setEstMisADisposition(boolean mad) {
		// TODO Auto-generated method stub
		return estMiseADisposition=mad;
	}

	public void setDateExport(Date dateExport) {
		this.dateExport = dateExport;
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

	public Integer getIdTransporteur() {
		return idTransporteur;
	}

	public void setIdTransporteur(Integer idTransporteur) {
		this.idTransporteur = idTransporteur;
	}

	public String getCodeTransporteur() {
		return codeTransporteur;
	}

	public void setCodeTransporteur(String codeTransporteur) {
		this.codeTransporteur = codeTransporteur;
	}

	@Override
	public String getTypeDocument() {
		// TODO Auto-generated method stub
		return TaBoncdeAchat.TYPE_DOC;
	}
	public Boolean getUtiliseUniteSaisie() {
		return utiliseUniteSaisie;
	}
	public void setUtiliseUniteSaisie(Boolean utiliseUniteSaisie) {
		this.utiliseUniteSaisie = utiliseUniteSaisie;
	}
}

