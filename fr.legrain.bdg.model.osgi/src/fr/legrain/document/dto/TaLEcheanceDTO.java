package fr.legrain.document.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.legrain.bdg.model.ModelObject;


/**
 * SWTLFacture generated by hbm2java
 */
public class TaLEcheanceDTO extends ModelObject implements PropertyChangeListener,java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8021900210386983592L;
	private Integer id;
	private String codeTLigne;
	private Integer numLigneLDocument;
	
	private Integer idArticle;
	private String codeArticle;
	
	private String libLDocument;
	private BigDecimal qteLDocument;
	private BigDecimal qte2LDocument;
	private BigDecimal prixULDocument;
	private BigDecimal tauxTvaLDocument;
	private String codeTvaLDocument;
	private String codeTTvaLDocument;
	private BigDecimal mtHtLDocument;
	private BigDecimal mtTtcLDocument;
	private BigDecimal mtHtLApresRemiseGlobaleDocument;
	private BigDecimal mtTtcLApresRemiseGlobaleDocument;	
	private BigDecimal remTxLDocument;
	private BigDecimal remHtLDocument;
	
	private String IP_ACCES;
	
	private String u1LDocument;
	private String u2LDocument;
	private BigDecimal txRemHtDocument;
	private String codeTitreTransport;
	private BigDecimal qteTitreTransport;
	
	private Integer idAbonnement;
	private Date debutPeriode;
	private Date finPeriode;
	private Date dateFinCalcul;
	private Date debAbonnement;
	private Date finAbonnement;
	private Date dateEcheance;
	private String codeAbonnement;
	
	private BigDecimal pourcIndice;
	private BigDecimal pourcPartenaire;
	private Integer idTAbonnement;
	private String codeTAbonnement;
	private Integer idTiers;
	private String codeTiers;
	private String nomTiers;
	private Boolean accepte;
	private String codeSupportAbon;
	
	private Date dateAnnulation;
	private String commentaireAnnulation;
	private String codeEtat;
	private Integer versionObj;
	
	private BigDecimal coefMultiplicateur;
	
	private Integer idLAvisEcheance;
	private Integer idLFacture;
	
	private String codeModuleBdg;

	public TaLEcheanceDTO() {
	}
	
	public TaLEcheanceDTO(Integer idLEcheance,
			Integer idArticle, String codeArticle,
			String libLDocument, BigDecimal qteLDocument,
			BigDecimal prixULDocument,
			BigDecimal tauxTvaLDocument, BigDecimal mtHtLDocument,
			BigDecimal mtTtcLDocument,
			BigDecimal mtHtLApresRemiseGlobaleDocument,
			BigDecimal mtTtcLApresRemiseGlobaleDocument,
			BigDecimal remTxLDocument, BigDecimal remHtLDocument, String u1lDocument, 
		    Integer idAbonnement,
			Date debutPeriode, Date finPeriode, Date dateFinCalcul,
			Date debAbonnement, Date finAbonnement, Date dateEcheance,
			Integer idTiers, String codeTiers,
			String nomTiers,
			BigDecimal coefMultiplicateur, String codeEtat, String codeModuleBdg,
			String codeAbonnement) {
		super();
		this.id = idLEcheance;
		this.idArticle = idArticle;
		this.codeArticle = codeArticle;
		this.libLDocument = libLDocument;
		this.qteLDocument = qteLDocument;
		this.prixULDocument = prixULDocument;
		this.tauxTvaLDocument = tauxTvaLDocument;
		this.mtHtLDocument = mtHtLDocument;
		this.mtTtcLDocument = mtTtcLDocument;
		this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument;
		this.mtTtcLApresRemiseGlobaleDocument = mtTtcLApresRemiseGlobaleDocument;
		this.remTxLDocument = remTxLDocument;
		this.remHtLDocument = remHtLDocument;
		this.u1LDocument = u1lDocument;
		this.idAbonnement = idAbonnement;
		this.debutPeriode = debutPeriode;
		this.finPeriode = finPeriode;
		this.dateFinCalcul = dateFinCalcul;
		this.debAbonnement = debAbonnement;
		this.finAbonnement = finAbonnement;
		this.dateEcheance = dateEcheance;
		this.idTiers = idTiers;
		this.codeTiers = codeTiers;
		this.nomTiers = nomTiers;
		this.coefMultiplicateur =coefMultiplicateur;
		this.codeEtat =codeEtat;
		this.codeModuleBdg =codeModuleBdg;
		this.codeAbonnement = codeAbonnement;
	}


	
	@JsonIgnore
	public void setIHMLFacture(TaLEcheanceDTO ihmLFacture) {
		setIdLEcheance(ihmLFacture.id);
		setCodeTLigne(ihmLFacture.codeTLigne);
		setIdArticle(ihmLFacture.idArticle);
		setNumLigneLDocument(ihmLFacture.numLigneLDocument);
		setCodeArticle(ihmLFacture.codeArticle);
		setLibLDocument(ihmLFacture.libLDocument);
		setQteLDocument(ihmLFacture.qteLDocument);
		setU1LDocument(ihmLFacture.u1LDocument);
		setU2LDocument(ihmLFacture.u2LDocument);
		setPrixULDocument(ihmLFacture.prixULDocument);
		setTauxTvaLDocument(ihmLFacture.tauxTvaLDocument);
		setCodeTvaLDocument(ihmLFacture.codeTvaLDocument);
		setCodeTTvaLDocument(ihmLFacture.codeTTvaLDocument);
		setMtHtLDocument(ihmLFacture.mtHtLDocument);
		setMtTtcLDocument(ihmLFacture.mtTtcLDocument);
		setRemTxLDocument(ihmLFacture.remTxLDocument);
		setRemHtLDocument(ihmLFacture.remHtLDocument);
		setMtHtLApresRemiseGlobaleDocument(mtHtLApresRemiseGlobaleDocument);
		setMtTtcLApresRemiseGlobaleDocument(mtTtcLApresRemiseGlobaleDocument);
		setCodeTitreTransport(codeTitreTransport);
		setQteTitreTransport(qteTitreTransport);
		setIP_ACCES(ihmLFacture.IP_ACCES);
		setAccepte(ihmLFacture.accepte);
	}
	
	static public TaLEcheanceDTO copy(TaLEcheanceDTO ihmLFacture) {
		TaLEcheanceDTO swtLFactureLoc = new TaLEcheanceDTO();
		swtLFactureLoc.setIdLEcheance(ihmLFacture.id);
		swtLFactureLoc.setCodeTLigne(ihmLFacture.codeTLigne);
		swtLFactureLoc.setIdArticle(ihmLFacture.idArticle);
		swtLFactureLoc.setNumLigneLDocument(ihmLFacture.numLigneLDocument);
		swtLFactureLoc.setCodeArticle(ihmLFacture.codeArticle);
		swtLFactureLoc.setLibLDocument(ihmLFacture.libLDocument);
		swtLFactureLoc.setQteLDocument(ihmLFacture.qteLDocument);
		swtLFactureLoc.setU1LDocument(ihmLFacture.u1LDocument);
		swtLFactureLoc.setU2LDocument(ihmLFacture.u2LDocument);
		swtLFactureLoc.setPrixULDocument(ihmLFacture.prixULDocument);
		swtLFactureLoc.setTauxTvaLDocument(ihmLFacture.tauxTvaLDocument);
		swtLFactureLoc.setCodeTvaLDocument(ihmLFacture.codeTvaLDocument);
		swtLFactureLoc.setCodeTTvaLDocument(ihmLFacture.codeTTvaLDocument);
		swtLFactureLoc.setMtHtLDocument(ihmLFacture.mtHtLDocument);
		swtLFactureLoc.setMtTtcLDocument(ihmLFacture.mtTtcLDocument);
		swtLFactureLoc.setMtHtLApresRemiseGlobaleDocument(ihmLFacture.mtHtLApresRemiseGlobaleDocument);
		swtLFactureLoc.setMtTtcLApresRemiseGlobaleDocument(ihmLFacture.mtTtcLApresRemiseGlobaleDocument);
		swtLFactureLoc.setRemTxLDocument(ihmLFacture.remTxLDocument);
		swtLFactureLoc.setRemHtLDocument(ihmLFacture.remHtLDocument);
		swtLFactureLoc.setIP_ACCES(ihmLFacture.IP_ACCES);
		swtLFactureLoc.setCodeTitreTransport(ihmLFacture.codeTitreTransport);
		swtLFactureLoc.setQteTitreTransport(ihmLFacture.qteTitreTransport);
		swtLFactureLoc.setAccepte(ihmLFacture.accepte);
		swtLFactureLoc.setVersionObj(ihmLFacture.versionObj);
		return swtLFactureLoc;
	}

	public Integer getIdLEcheance() {
		return this.id;
	}

	public void setIdLEcheance(Integer ID_L_ECHEANCE) {
		firePropertyChange("ID_L_ECHEANCE", this.id, this.id = ID_L_ECHEANCE);
	}


	public String getCodeTLigne() {
		return this.codeTLigne;
	}

	public void setCodeTLigne(String codeTLigne) {
		firePropertyChange("codeTLigne", this.codeTLigne, this.codeTLigne = codeTLigne);
	}

	public Integer getIdArticle() {
		return this.idArticle;
	}

	public void setIdArticle(Integer ID_ARTICLE) {
		firePropertyChange("idArticle", this.idArticle, this.idArticle = ID_ARTICLE);
	}

	public Integer getNumLigneLDocument() {
		return this.numLigneLDocument;
	}

	public void setNumLigneLDocument(Integer NUM_LIGNE_L_ECHEANCE) {
		firePropertyChange("numLigneLDocument", this.numLigneLDocument, this.numLigneLDocument = NUM_LIGNE_L_ECHEANCE);
	}

	public String getCodeArticle() {
		return this.codeArticle;
	}

	public void setCodeArticle(String CODE_ARTICLE) {
		firePropertyChange("codeArticle", this.codeArticle, this.codeArticle = CODE_ARTICLE);
	}

	public String getLibLDocument() {
		return this.libLDocument;
	}

	public void setLibLDocument(String LIB_L_ECHEANCE) {
		firePropertyChange("libLDocument", this.libLDocument, this.libLDocument = LIB_L_ECHEANCE);
	}

	public BigDecimal getQteLDocument() {
		return this.qteLDocument;
	}

	public void setQteLDocument(BigDecimal QTE_L_ECHEANCE) {
		firePropertyChange("qteLDocument", this.qteLDocument, this.qteLDocument = QTE_L_ECHEANCE);
	}

	public String getU1LDocument() {
		return this.u1LDocument;
	}

	public void setU1LDocument(String U1_L_ECHEANCE) {
		firePropertyChange("u1LDocument", this.u1LDocument, this.u1LDocument = U1_L_ECHEANCE);
	}

	public String getU2LDocument() {
		return this.u2LDocument;
	}

	public void setU2LDocument(String U2_L_ECHEANCE) {
		firePropertyChange("u2LDocument", this.u2LDocument, this.u2LDocument = U2_L_ECHEANCE);
	}

	public BigDecimal getPrixULDocument() {
		return this.prixULDocument;
	}

	public void setPrixULDocument(BigDecimal PRIX_U_L_ECHEANCE) {
		firePropertyChange("prixULDocument", this.prixULDocument, this.prixULDocument = PRIX_U_L_ECHEANCE);
	}

	public BigDecimal getTauxTvaLDocument() {
		return this.tauxTvaLDocument;
	}

	public void setTauxTvaLDocument(BigDecimal TAUX_TVA_L_ECHEANCE) {
		firePropertyChange("tauxTvaLDocument", this.tauxTvaLDocument, this.tauxTvaLDocument = TAUX_TVA_L_ECHEANCE);
	}

	public String getCodeTvaLDocument() {
		return this.codeTvaLDocument;
	}

	public void setCodeTvaLDocument(String CODE_TVA_L_ECHEANCE) {
		firePropertyChange("codeTvaLDocument", this.codeTvaLDocument, this.codeTvaLDocument = CODE_TVA_L_ECHEANCE);
	}

	public String getCodeTTvaLDocument() {
		return this.codeTTvaLDocument;
	}

	public void setCodeTTvaLDocument(String CODE_T_TVA_L_ECHEANCE) {
		firePropertyChange("codeTTvaLDocument", this.codeTTvaLDocument, this.codeTTvaLDocument = CODE_T_TVA_L_ECHEANCE);
	}

	public BigDecimal getMtHtLDocument() {
		return this.mtHtLDocument;
	}

	public void setMtHtLDocument(BigDecimal MT_HT_L_ECHEANCE) {
		firePropertyChange("mtHtLDocument", this.mtHtLDocument, this.mtHtLDocument = MT_HT_L_ECHEANCE);
	}

	public BigDecimal getMtTtcLDocument() {
		return this.mtTtcLDocument;
	}

	public void setMtTtcLDocument(BigDecimal MT_TTC_L_ECHEANCE) {
		firePropertyChange("mtTtcLDocument", this.mtTtcLDocument, this.mtTtcLDocument = MT_TTC_L_ECHEANCE);
	}

	public BigDecimal getRemTxLDocument() {
		return this.remTxLDocument;
	}

	public void setRemTxLDocument(BigDecimal REM_TX_L_ECHEANCE) {
		firePropertyChange("remTxLDocument", this.remTxLDocument, this.remTxLDocument = REM_TX_L_ECHEANCE);
	}

	public BigDecimal getRemHtLDocument() {
		return this.remHtLDocument;
	}

	public void setRemHtLDocument(BigDecimal REM_HT_L_ECHEANCE) {
		firePropertyChange("remHtLDocument", this.remHtLDocument, this.remHtLDocument = REM_HT_L_ECHEANCE);
	}

	public String getIP_ACCES() {
		return this.IP_ACCES;
	}

	public void setIP_ACCES(String IP_ACCES) {
		firePropertyChange("IP_ACCES", this.IP_ACCES, this.IP_ACCES = IP_ACCES);
	}
	
	public String getCodeTitreTransport() {
		return this.codeTitreTransport;
	}

	public void setCodeTitreTransport(String codeTitreTransport) {
		firePropertyChange("codeTitreTransport", this.codeTitreTransport, this.codeTitreTransport = codeTitreTransport);
	}
	
	public BigDecimal getQteTitreTransport() {
		return this.qteTitreTransport;
	}

	public void setQteTitreTransport(BigDecimal qteTitreTransport) {
		firePropertyChange("qteTitreTransport", this.qteTitreTransport, this.qteTitreTransport = qteTitreTransport);
	}


	public void propertyChange(PropertyChangeEvent evt) {

//		if(evt.getPropertyName().equals("ID_L_ECHEANCE")){
//			this.setIdLFacture((Integer)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("ID_T_LIGNE")){
//			this.setCodeTLigne((String)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("ID_ARTICLE")){
//			this.setIdArticle((Integer)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("LIB_L_ECHEANCE")){
//			this.setLibLFacture((String)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("QTE_L_ECHEANCE")){
//			this.setQteLFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("U1_L_ECHEANCE")){
//			this.setU1LFacture((String)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("U2_L_ECHEANCE")){
//			this.setU2LFacture((String)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("PRIX_U_L_ECHEANCE")){
//			this.setPrixULFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("TAUX_TVA_L_ECHEANCE")){
//			this.setTauxTvaLFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("CODE_TVA_L_ECHEANCE")){
//			this.setCodeTvaLFacture((String)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("CODE_T_TVA_L_ECHEANCE")){
//			this.setCodeTTvaLFacture((String)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("MT_HT_L_ECHEANCE")){
//			this.setMtHtLFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("MT_TTC_L_ECHEANCE")){
//			this.setMtTtcLFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("REM_TX_L_ECHEANCE")){
//			this.setRemTxLFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("REM_HT_L_ECHEANCE")){
//			this.setRemHtLFacture((BigDecimal)evt.getNewValue());
//		}		
//		if(evt.getPropertyName().equals("CODE_ARTICLE")){
//			this.setCodeArticle((String)evt.getNewValue());
//		}		
	}

	
	public BigDecimal getQte2LDocument() {
		return qte2LDocument;
	}

	public void setQte2LDocument(BigDecimal qte2LFacture) {
		firePropertyChange("qte2LDocument", this.qte2LDocument, this.qte2LDocument = qte2LFacture);

	}

	public BigDecimal getTxRemHtDocument() {
		return txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal txRemHtFacture) {
		firePropertyChange("txRemHtDocument", this.txRemHtDocument, this.txRemHtDocument = txRemHtFacture);
	}

	public BigDecimal getMtHtLApresRemiseGlobaleDocument() {
		return mtHtLApresRemiseGlobaleDocument;
	}

	public void setMtHtLApresRemiseGlobaleDocument(
			BigDecimal mtHtLApresRemiseGlobaleDocument) {
		firePropertyChange("mtHtLApresRemiseGlobaleDocument", this.mtHtLApresRemiseGlobaleDocument, 
				this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument);
	}

	public BigDecimal getMtTtcLApresRemiseGlobaleDocument() {
		return mtTtcLApresRemiseGlobaleDocument;
	}

	public void setMtTtcLApresRemiseGlobaleDocument(
			BigDecimal mtTtcLApresRemiseGlobaleDocument) {
		firePropertyChange("mtTtcLApresRemiseGlobaleDocument", this.mtTtcLApresRemiseGlobaleDocument, 
				this.mtTtcLApresRemiseGlobaleDocument = mtTtcLApresRemiseGlobaleDocument);
	}

	public Integer getIdAbonnement() {
		return idAbonnement;
	}

	public void setIdAbonnement(Integer idAbonnement) {
		firePropertyChange("idAbonnement", this.idAbonnement, this.idAbonnement = idAbonnement);
	}

	public Date getDebutPeriode() {
		return debutPeriode;
	}

	public void setDebutPeriode(Date debutPeriode) {
		firePropertyChange("debutPeriode", this.debutPeriode, this.debutPeriode = debutPeriode);
	}

	public Date getFinPeriode() {
		return finPeriode;
	}

	public void setFinPeriode(Date finPeriode) {
		firePropertyChange("finPeriode", this.finPeriode, this.finPeriode = finPeriode);
	}

	public Date getDateFinCalcul() {
		return dateFinCalcul;
	}

	public void setDateFinCalcul(Date dateFinCalcul) {
		firePropertyChange("dateFinCalcul", this.dateFinCalcul, this.dateFinCalcul = dateFinCalcul);
	}

	public Date getDebAbonnement() {
		return debAbonnement;
	}

	public void setDebAbonnement(Date debAbonnement) {
		firePropertyChange("debAbonnement", this.debAbonnement, this.debAbonnement = debAbonnement);
	}

	public Date getFinAbonnement() {
		return finAbonnement;
	}

	public void setFinAbonnement(Date finAbonnement) {
		firePropertyChange("finAbonnement", this.finAbonnement, this.finAbonnement = finAbonnement);
	}

	public BigDecimal getPourcIndice() {
		return pourcIndice;
	}

	public void setPourcIndice(BigDecimal pourcIndice) {
		firePropertyChange("pourcIndice", this.pourcIndice, this.pourcIndice = pourcIndice);
	}

	public BigDecimal getPourcPartenaire() {
		return pourcPartenaire;
	}

	public void setPourcPartenaire(BigDecimal pourcPartenaire) {
		firePropertyChange("pourcPartenaire", this.pourcPartenaire, this.pourcPartenaire = pourcPartenaire);
	}

	public Integer getIdTAbonnement() {
		return idTAbonnement;
	}

	public void setIdTAbonnement(Integer idTAbonnement) {
		firePropertyChange("idTAbonnement", this.idTAbonnement, this.idTAbonnement = idTAbonnement);
	}

	public String getCodeTAbonnement() {
		return codeTAbonnement;
	}

	public void setCodeTAbonnement(String codeTAbonnement) {
		firePropertyChange("codeTAbonnement", this.codeTAbonnement, this.codeTAbonnement = codeTAbonnement);
	}

	public Integer getIdTiers() {
		return idTiers;
	}

	public void setIdTiers(Integer idTiers) {
		firePropertyChange("idTiers", this.idTiers, this.idTiers = idTiers);
	}

	public String getCodeTiers() {
		return codeTiers;
	}

	public void setCodeTiers(String codeTiers) {
		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = codeTiers);
	}

	public String getNomTiers() {
		return nomTiers;
	}

	public void setNomTiers(String nomTiers) {
		firePropertyChange("nomTiers", this.nomTiers, this.nomTiers = nomTiers);
	}

	public Boolean getAccepte() {
		return accepte;
	}

	public void setAccepte(Boolean accepte) {
		firePropertyChange("accepte", this.accepte, this.accepte = accepte);
	}


	public String getCodeSupportAbon() {
		return codeSupportAbon;
	}



	public void setCodeSupportAbon(String codeSupportAbon) {
		firePropertyChange("codeSupportAbon", this.codeSupportAbon, this.codeSupportAbon = codeSupportAbon);
	}






	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IP_ACCES == null) ? 0 : IP_ACCES.hashCode());
		result = prime * result + ((accepte == null) ? 0 : accepte.hashCode());
		result = prime * result
				+ ((codeArticle == null) ? 0 : codeArticle.hashCode());
		result = prime * result
				+ ((codeSupportAbon == null) ? 0 : codeSupportAbon.hashCode());
		result = prime * result
				+ ((codeTAbonnement == null) ? 0 : codeTAbonnement.hashCode());
		result = prime * result
				+ ((codeTLigne == null) ? 0 : codeTLigne.hashCode());
		result = prime
				* result
				+ ((codeTTvaLDocument == null) ? 0 : codeTTvaLDocument
						.hashCode());
		result = prime * result
				+ ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime
				* result
				+ ((codeTitreTransport == null) ? 0 : codeTitreTransport
						.hashCode());
		result = prime
				* result
				+ ((codeTvaLDocument == null) ? 0 : codeTvaLDocument.hashCode());
		result = prime * result
				+ ((dateFinCalcul == null) ? 0 : dateFinCalcul.hashCode());
		result = prime * result
				+ ((debAbonnement == null) ? 0 : debAbonnement.hashCode());
		result = prime * result
				+ ((debutPeriode == null) ? 0 : debutPeriode.hashCode());
		result = prime * result
				+ ((finAbonnement == null) ? 0 : finAbonnement.hashCode());
		result = prime * result
				+ ((finPeriode == null) ? 0 : finPeriode.hashCode());
		result = prime * result
				+ ((idAbonnement == null) ? 0 : idAbonnement.hashCode());
		result = prime * result
				+ ((idArticle == null) ? 0 : idArticle.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idTAbonnement == null) ? 0 : idTAbonnement.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result
				+ ((libLDocument == null) ? 0 : libLDocument.hashCode());
		result = prime
				* result
				+ ((mtHtLApresRemiseGlobaleDocument == null) ? 0
						: mtHtLApresRemiseGlobaleDocument.hashCode());
		result = prime * result
				+ ((mtHtLDocument == null) ? 0 : mtHtLDocument.hashCode());
		result = prime
				* result
				+ ((mtTtcLApresRemiseGlobaleDocument == null) ? 0
						: mtTtcLApresRemiseGlobaleDocument.hashCode());
		result = prime * result
				+ ((mtTtcLDocument == null) ? 0 : mtTtcLDocument.hashCode());
		result = prime * result
				+ ((nomTiers == null) ? 0 : nomTiers.hashCode());
		result = prime
				* result
				+ ((numLigneLDocument == null) ? 0 : numLigneLDocument
						.hashCode());
		result = prime * result
				+ ((pourcIndice == null) ? 0 : pourcIndice.hashCode());
		result = prime * result
				+ ((pourcPartenaire == null) ? 0 : pourcPartenaire.hashCode());
		result = prime * result
				+ ((prixULDocument == null) ? 0 : prixULDocument.hashCode());
		result = prime * result
				+ ((qte2LDocument == null) ? 0 : qte2LDocument.hashCode());
		result = prime * result
				+ ((qteLDocument == null) ? 0 : qteLDocument.hashCode());
		result = prime
				* result
				+ ((qteTitreTransport == null) ? 0 : qteTitreTransport
						.hashCode());
		result = prime * result
				+ ((remHtLDocument == null) ? 0 : remHtLDocument.hashCode());
		result = prime * result
				+ ((remTxLDocument == null) ? 0 : remTxLDocument.hashCode());
		result = prime
				* result
				+ ((tauxTvaLDocument == null) ? 0 : tauxTvaLDocument.hashCode());
		result = prime * result
				+ ((txRemHtDocument == null) ? 0 : txRemHtDocument.hashCode());
		result = prime * result
				+ ((u1LDocument == null) ? 0 : u1LDocument.hashCode());
		result = prime * result
				+ ((u2LDocument == null) ? 0 : u2LDocument.hashCode());
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
		TaLEcheanceDTO other = (TaLEcheanceDTO) obj;
		if (IP_ACCES == null) {
			if (other.IP_ACCES != null)
				return false;
		} else if (!IP_ACCES.equals(other.IP_ACCES))
			return false;
		if (accepte == null) {
			if (other.accepte != null)
				return false;
		} else if (!accepte.equals(other.accepte))
			return false;
		if (codeArticle == null) {
			if (other.codeArticle != null)
				return false;
		} else if (!codeArticle.equals(other.codeArticle))
			return false;
		if (codeSupportAbon == null) {
			if (other.codeSupportAbon != null)
				return false;
		} else if (!codeSupportAbon.equals(other.codeSupportAbon))
			return false;
		if (codeTAbonnement == null) {
			if (other.codeTAbonnement != null)
				return false;
		} else if (!codeTAbonnement.equals(other.codeTAbonnement))
			return false;
		if (codeTLigne == null) {
			if (other.codeTLigne != null)
				return false;
		} else if (!codeTLigne.equals(other.codeTLigne))
			return false;
		if (codeTTvaLDocument == null) {
			if (other.codeTTvaLDocument != null)
				return false;
		} else if (!codeTTvaLDocument.equals(other.codeTTvaLDocument))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
			return false;
		if (codeTitreTransport == null) {
			if (other.codeTitreTransport != null)
				return false;
		} else if (!codeTitreTransport.equals(other.codeTitreTransport))
			return false;
		if (codeTvaLDocument == null) {
			if (other.codeTvaLDocument != null)
				return false;
		} else if (!codeTvaLDocument.equals(other.codeTvaLDocument))
			return false;
		if (dateFinCalcul == null) {
			if (other.dateFinCalcul != null)
				return false;
		} else if (!dateFinCalcul.equals(other.dateFinCalcul))
			return false;
		if (debAbonnement == null) {
			if (other.debAbonnement != null)
				return false;
		} else if (!debAbonnement.equals(other.debAbonnement))
			return false;
		if (debutPeriode == null) {
			if (other.debutPeriode != null)
				return false;
		} else if (!debutPeriode.equals(other.debutPeriode))
			return false;
		if (finAbonnement == null) {
			if (other.finAbonnement != null)
				return false;
		} else if (!finAbonnement.equals(other.finAbonnement))
			return false;
		if (finPeriode == null) {
			if (other.finPeriode != null)
				return false;
		} else if (!finPeriode.equals(other.finPeriode))
			return false;
		if (idAbonnement == null) {
			if (other.idAbonnement != null)
				return false;
		} else if (!idAbonnement.equals(other.idAbonnement))
			return false;
		if (idArticle == null) {
			if (other.idArticle != null)
				return false;
		} else if (!idArticle.equals(other.idArticle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTAbonnement == null) {
			if (other.idTAbonnement != null)
				return false;
		} else if (!idTAbonnement.equals(other.idTAbonnement))
			return false;
		if (idTiers == null) {
			if (other.idTiers != null)
				return false;
		} else if (!idTiers.equals(other.idTiers))
			return false;
		if (libLDocument == null) {
			if (other.libLDocument != null)
				return false;
		} else if (!libLDocument.equals(other.libLDocument))
			return false;
		if (mtHtLApresRemiseGlobaleDocument == null) {
			if (other.mtHtLApresRemiseGlobaleDocument != null)
				return false;
		} else if (!mtHtLApresRemiseGlobaleDocument
				.equals(other.mtHtLApresRemiseGlobaleDocument))
			return false;
		if (mtHtLDocument == null) {
			if (other.mtHtLDocument != null)
				return false;
		} else if (!mtHtLDocument.equals(other.mtHtLDocument))
			return false;
		if (mtTtcLApresRemiseGlobaleDocument == null) {
			if (other.mtTtcLApresRemiseGlobaleDocument != null)
				return false;
		} else if (!mtTtcLApresRemiseGlobaleDocument
				.equals(other.mtTtcLApresRemiseGlobaleDocument))
			return false;
		if (mtTtcLDocument == null) {
			if (other.mtTtcLDocument != null)
				return false;
		} else if (!mtTtcLDocument.equals(other.mtTtcLDocument))
			return false;
		if (nomTiers == null) {
			if (other.nomTiers != null)
				return false;
		} else if (!nomTiers.equals(other.nomTiers))
			return false;
		if (numLigneLDocument == null) {
			if (other.numLigneLDocument != null)
				return false;
		} else if (!numLigneLDocument.equals(other.numLigneLDocument))
			return false;
		if (pourcIndice == null) {
			if (other.pourcIndice != null)
				return false;
		} else if (!pourcIndice.equals(other.pourcIndice))
			return false;
		if (pourcPartenaire == null) {
			if (other.pourcPartenaire != null)
				return false;
		} else if (!pourcPartenaire.equals(other.pourcPartenaire))
			return false;
		if (prixULDocument == null) {
			if (other.prixULDocument != null)
				return false;
		} else if (!prixULDocument.equals(other.prixULDocument))
			return false;
		if (qte2LDocument == null) {
			if (other.qte2LDocument != null)
				return false;
		} else if (!qte2LDocument.equals(other.qte2LDocument))
			return false;
		if (qteLDocument == null) {
			if (other.qteLDocument != null)
				return false;
		} else if (!qteLDocument.equals(other.qteLDocument))
			return false;
		if (qteTitreTransport == null) {
			if (other.qteTitreTransport != null)
				return false;
		} else if (!qteTitreTransport.equals(other.qteTitreTransport))
			return false;
		if (remHtLDocument == null) {
			if (other.remHtLDocument != null)
				return false;
		} else if (!remHtLDocument.equals(other.remHtLDocument))
			return false;
		if (remTxLDocument == null) {
			if (other.remTxLDocument != null)
				return false;
		} else if (!remTxLDocument.equals(other.remTxLDocument))
			return false;
		if (tauxTvaLDocument == null) {
			if (other.tauxTvaLDocument != null)
				return false;
		} else if (!tauxTvaLDocument.equals(other.tauxTvaLDocument))
			return false;
		if (txRemHtDocument == null) {
			if (other.txRemHtDocument != null)
				return false;
		} else if (!txRemHtDocument.equals(other.txRemHtDocument))
			return false;
		if (u1LDocument == null) {
			if (other.u1LDocument != null)
				return false;
		} else if (!u1LDocument.equals(other.u1LDocument))
			return false;
		if (u2LDocument == null) {
			if (other.u2LDocument != null)
				return false;
		} else if (!u2LDocument.equals(other.u2LDocument))
			return false;
		return true;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getVersionObj() {
		return versionObj;
	}



	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}



	public Date getDateAnnulation() {
		return dateAnnulation;
	}



	public void setDateAnnulation(Date dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}



	public String getCommentaireAnnulation() {
		return commentaireAnnulation;
	}



	public void setCommentaireAnnulation(String commentaireAnnulation) {
		this.commentaireAnnulation = commentaireAnnulation;
	}



	public String getCodeEtat() {
		return codeEtat;
	}



	public void setCodeEtat(String codeEtat) {
		this.codeEtat = codeEtat;
	}



	public BigDecimal getCoefMultiplicateur() {
		return coefMultiplicateur;
	}



	public void setCoefMultiplicateur(BigDecimal coefMultiplicateur) {
		this.coefMultiplicateur = coefMultiplicateur;
	}



	public Integer getIdLAvisEcheance() {
		return idLAvisEcheance;
	}



	public void setIdLAvisEcheance(Integer idLAvisEcheance) {
		this.idLAvisEcheance = idLAvisEcheance;
	}



	public Integer getIdLFacture() {
		return idLFacture;
	}



	public void setIdLFacture(Integer idLFacture) {
		this.idLFacture = idLFacture;
	}



	public String getCodeModuleBdg() {
		return codeModuleBdg;
	}



	public void setCodeModuleBdg(String codeModuleBdg) {
		this.codeModuleBdg = codeModuleBdg;
	}



	public Date getDateEcheance() {
		return dateEcheance;
	}



	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getCodeAbonnement() {
		return codeAbonnement;
	}

	public void setCodeAbonnement(String codeAbonnement) {
		this.codeAbonnement = codeAbonnement;
	}


	
	
}
