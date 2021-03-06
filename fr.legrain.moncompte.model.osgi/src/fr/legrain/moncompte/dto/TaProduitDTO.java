package fr.legrain.moncompte.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.moncompte.commun.model.ModelObject;


/**
 * SWTInfoEntreprise generated by hbm2java
 */
public class TaProduitDTO extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String code;
	private String libelle;
	private String description;
	private String libelleHtml;
	private String descriptionHtml;
	private Boolean sousProduit;
	private Boolean compose;
	private Boolean vendable;
	private Boolean eligibleCommission;
	private String identifiantModule;
	private String versionProduit;
	private String versionOS;
	private String versionBrowser;
	private String nomGroupe;
	
	private BigDecimal prixHT;
	private BigDecimal tva;
	private BigDecimal tauxTVA;
	private BigDecimal prixTTC;
	
	private BigDecimal prixHTLight;
	private BigDecimal tvaLight;
	private BigDecimal prixTTCLight;
	
	private BigDecimal prixHTParPosteInstalle;
	private BigDecimal tvaParPosteInstalle;
	private BigDecimal prixTTCParPosteInstalle;
	
	private BigDecimal prixHTWs;
	private BigDecimal tvaWs;
	private BigDecimal prixTTCWs;
	
	private String codeTypeProduit;
	private String codeTNiveau;
	private Integer versionObj;

	public TaProduitDTO() {
	}


	public TaProduitDTO(Integer id, String code, String libelle,
			String description, Boolean sousProduit, Boolean compose,
			String versionProduit, String versionOS, String versionBrowser,
			BigDecimal prixHT, BigDecimal tva, BigDecimal tauxTVA,
			BigDecimal prixTTC,String codeTypeProduit,String codeTNiveau ,Integer versionObj) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.description = description;
		this.sousProduit = sousProduit;
		this.compose = compose;
		this.versionProduit = versionProduit;
		this.versionOS = versionOS;
		this.versionBrowser = versionBrowser;
		this.prixHT = prixHT;
		this.tva = tva;
		this.tauxTVA = tauxTVA;
		this.prixTTC = prixTTC;
		this.codeTypeProduit = codeTypeProduit;
		this.codeTNiveau = codeTNiveau;
		this.versionObj = versionObj;
	}


	public void setSWTInfoEntreprise(TaProduitDTO taProduitDTO) {
		this.id = taProduitDTO.id;
		this.code = taProduitDTO.code;
		this.libelle = taProduitDTO.libelle;
		this.description = taProduitDTO.description;
		this.sousProduit = taProduitDTO.sousProduit;
		this.compose = taProduitDTO.compose;
		this.versionProduit = taProduitDTO.versionProduit;
		this.versionOS = taProduitDTO.versionOS;
		this.versionBrowser = taProduitDTO.versionBrowser;
		this.prixHT = taProduitDTO.prixHT;
		this.tva = taProduitDTO.tva;
		this.tauxTVA = taProduitDTO.tauxTVA;
		this.prixTTC = taProduitDTO.prixTTC;
		this.codeTypeProduit = taProduitDTO.codeTypeProduit;
		this.codeTNiveau = taProduitDTO.codeTNiveau;
		this.versionObj = taProduitDTO.versionObj;
	}
	
	public static TaProduitDTO copy(TaProduitDTO taProduitDTO){
		TaProduitDTO taProduitDTOLoc = new TaProduitDTO();
		taProduitDTOLoc.setId(taProduitDTO.id);
		taProduitDTOLoc.setCode(taProduitDTO.code);
		taProduitDTOLoc.setLibelle(taProduitDTO.libelle);
		taProduitDTOLoc.setDescription(taProduitDTO.description);
		taProduitDTOLoc.setSousProduit(taProduitDTO.sousProduit);
		taProduitDTOLoc.setCompose(taProduitDTO.compose);
		taProduitDTOLoc.setVersionProduit(taProduitDTO.versionProduit);
		taProduitDTOLoc.setVersionOS(taProduitDTO.versionOS);
		taProduitDTOLoc.setVersionBrowser(taProduitDTO.versionBrowser);
		taProduitDTOLoc.setPrixHT(taProduitDTO.prixHT);
		taProduitDTOLoc.setTva(taProduitDTO.tva);
		taProduitDTOLoc.setTauxTVA(taProduitDTO.tauxTVA);
		taProduitDTOLoc.setPrixTTC(taProduitDTO.prixTTC);
		taProduitDTOLoc.setCodeTypeProduit(taProduitDTO.codeTypeProduit);
		taProduitDTOLoc.setCodeTNiveau(taProduitDTO.codeTNiveau);
		taProduitDTOLoc.setVersionObj(taProduitDTO.versionObj);
		return taProduitDTOLoc;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		firePropertyChange("code", this.code, this.code = code);
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		firePropertyChange("libelle", this.libelle, this.libelle = libelle);
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		firePropertyChange("description", this.description, this.description = description);
	}


	public Boolean getSousProduit() {
		return sousProduit;
	}


	public void setSousProduit(Boolean sousProduit) {
		firePropertyChange("sousProduit", this.sousProduit, this.sousProduit = sousProduit);
	}


	public Boolean getCompose() {
		return compose;
	}


	public void setCompose(Boolean compose) {
		firePropertyChange("compose", this.compose, this.compose = compose);
	}


	public String getVersionProduit() {
		return versionProduit;
	}


	public void setVersionProduit(String versionProduit) {
		firePropertyChange("versionProduit", this.versionProduit, this.versionProduit = versionProduit);
	}


	public String getVersionOS() {
		return versionOS;
	}


	public void setVersionOS(String versionOS) {
		firePropertyChange("versionOS", this.versionOS, this.versionOS = versionOS);
	}


	public String getVersionBrowser() {
		return versionBrowser;
	}


	public void setVersionBrowser(String versionBrowser) {
		firePropertyChange("versionBrowser", this.versionBrowser, this.versionBrowser = versionBrowser);
	}


	public BigDecimal getPrixHT() {
		return prixHT;
	}


	public void setPrixHT(BigDecimal prixHT) {
		firePropertyChange("prixHT", this.prixHT, this.prixHT = prixHT);
	}


	public BigDecimal getTva() {
		return tva;
	}


	public void setTva(BigDecimal tva) {
		firePropertyChange("tva", this.tva, this.tva = tva);
	}


	public BigDecimal getTauxTVA() {
		return tauxTVA;
	}


	public void setTauxTVA(BigDecimal tauxTVA) {
		firePropertyChange("tauxTVA", this.tauxTVA, this.tauxTVA = tauxTVA);
	}


	public BigDecimal getPrixTTC() {
		return prixTTC;
	}


	public void setPrixTTC(BigDecimal prixTTC) {
		firePropertyChange("prixTTC", this.prixTTC, this.prixTTC = prixTTC);
	}
	
	public String getCodeTypeProduit() {
		return codeTypeProduit;
	}


	public void setCodeTypeProduit(String codeTypeProduit) {
		firePropertyChange("codeTypeProduit", this.codeTypeProduit, this.codeTypeProduit = codeTypeProduit);
	}
	
	public Boolean getVendable() {
		return vendable;
	}

	public void setVendable(Boolean vendable) {
		firePropertyChange("vendable", this.vendable, this.vendable = vendable);
	}
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	
	public String getIdentifiantModule() {
		return identifiantModule;
	}


	public void setIdentifiantModule(String identifiantModule) {
		firePropertyChange("identifiantModule", this.identifiantModule, this.identifiantModule = identifiantModule);
	}


	public BigDecimal getPrixHTLight() {
		return prixHTLight;
	}


	public void setPrixHTLight(BigDecimal prixHTLight) {
		firePropertyChange("prixHTLight", this.prixHTLight, this.prixHTLight = prixHTLight);
	}


	public BigDecimal getTvaLight() {
		return tvaLight;
	}


	public void setTvaLight(BigDecimal tvaLight) {
		firePropertyChange("tvaLight", this.tvaLight, this.tvaLight = tvaLight);
	}


	public BigDecimal getPrixTTCLight() {
		return prixTTCLight;
	}


	public void setPrixTTCLight(BigDecimal prixTTCLight) {
		firePropertyChange("prixTTCLight", this.prixTTCLight, this.prixTTCLight = prixTTCLight);
	}


	public BigDecimal getPrixHTParPosteInstalle() {
		return prixHTParPosteInstalle;
	}


	public void setPrixHTParPosteInstalle(BigDecimal prixHTParPosteInstalle) {
		firePropertyChange("prixHTParPosteInstalle", this.prixHTParPosteInstalle, this.prixHTParPosteInstalle = prixHTParPosteInstalle);
	}


	public BigDecimal getTvaParPosteInstalle() {
		return tvaParPosteInstalle;
	}


	public void setTvaParPosteInstalle(BigDecimal tvaParPosteInstalle) {
		firePropertyChange("tvaParPosteInstalle", this.tvaParPosteInstalle, this.tvaParPosteInstalle = tvaParPosteInstalle);
	}


	public BigDecimal getPrixTTCParPosteInstalle() {
		return prixTTCParPosteInstalle;
	}


	public void setPrixTTCParPosteInstalle(BigDecimal prixTTCParPosteInstalle) {
		firePropertyChange("prixTTCParPosteInstalle", this.prixTTCParPosteInstalle, this.prixTTCParPosteInstalle = prixTTCParPosteInstalle);
	}


	public BigDecimal getPrixHTWs() {
		return prixHTWs;
	}


	public void setPrixHTWs(BigDecimal prixHTWs) {
		firePropertyChange("prixHTWs", this.prixHTWs, this.prixHTWs = prixHTWs);
	}


	public BigDecimal getTvaWs() {
		return tvaWs;
	}


	public void setTvaWs(BigDecimal tvaWs) {
		firePropertyChange("tvaWs", this.tvaWs, this.tvaWs = tvaWs);
	}


	public BigDecimal getPrixTTCWs() {
		return prixTTCWs;
	}


	public void setPrixTTCWs(BigDecimal prixTTCWs) {
		firePropertyChange("prixTTCWs", this.prixTTCWs, this.prixTTCWs = prixTTCWs);
	}


	public String getCodeTNiveau() {
		return codeTNiveau;
	}


	public void setCodeTNiveau(String codeTNiveau) {
		firePropertyChange("codeTNiveau", this.codeTNiveau, this.codeTNiveau = codeTNiveau);
	}


	public String getLibelleHtml() {
		return libelleHtml;
	}


	public void setLibelleHtml(String libelleHtml) {
		firePropertyChange("libelleHtml", this.libelleHtml, this.libelleHtml = libelleHtml);
	}


	public String getDescriptionHtml() {
		return descriptionHtml;
	}


	public void setDescriptionHtml(String descriptionHtml) {
		firePropertyChange("descriptionHtml", this.descriptionHtml, this.descriptionHtml = descriptionHtml);
	}


	public Boolean getEligibleCommission() {
		return eligibleCommission;
	}


	public void setEligibleCommission(Boolean eligibleCommission) {
		this.eligibleCommission = eligibleCommission;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}


	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}


}
