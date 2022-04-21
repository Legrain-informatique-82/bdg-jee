package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.lib.data.LgrConstantes;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class TaReglementTracaDTO extends ModelObject implements java.io.Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2663400794508986127L;
	
	private int id;
	private String codeReglement;
	private String typeDocument;
	private Date dateReglement;
	private String codeTPaiement=LgrConstantes.C_STR_VIDE;
	private String libelleReglement;
	private BigDecimal netTtcCalc  = new BigDecimal(0);
	private BigDecimal affectation  = new BigDecimal(0);
//	private Boolean export= false;
	private Integer etat= 0;
	private String codeEtat;
	private Date dateTraca;
	private String typeModif;
	
	private String codeFactureAffectation = "";
	private Date dateFactureAffectation;
	private BigDecimal htFactureAffectation = new BigDecimal(0); 
	private BigDecimal tvaFactureAffectation = new BigDecimal(0); 
	private BigDecimal ttcFactureAffectation = new BigDecimal(0); 
	private String codeEtatAffectation;
	private Date dateExportAffectation;	


	private Boolean accepte = true;
	private String codeTiers;
	private String nomTiers;
	
	private String codeAcompte ;

	private Integer versionObj;
	
	public TaReglementTracaDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCodeReglement() {
		return codeReglement;
	}

	public void setCodeReglement(String codeReglement) {
		this.codeReglement = codeReglement;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public Date getDateReglement() {
		return dateReglement;
	}

	public void setDateReglement(Date dateReglement) {
		this.dateReglement = dateReglement;
	}

	public String getCodeTPaiement() {
		return codeTPaiement;
	}

	public void setCodeTPaiement(String codeTPaiement) {
		this.codeTPaiement = codeTPaiement;
	}

	public String getLibelleReglement() {
		return libelleReglement;
	}

	public void setLibelleReglement(String libelleReglement) {
		this.libelleReglement = libelleReglement;
	}

	public BigDecimal getNetTtcCalc() {
		return netTtcCalc;
	}

	public void setNetTtcCalc(BigDecimal netTtcCalc) {
		this.netTtcCalc = netTtcCalc;
	}

	public BigDecimal getAffectation() {
		return affectation;
	}

	public void setAffectation(BigDecimal affectation) {
		this.affectation = affectation;
	}

//	public Boolean getExport() {
//		return export;
//	}
//
//	public void setExport(Boolean export) {
//		this.export = export;
//	}

	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	public String getCodeEtat() {
		return codeEtat;
	}

	public void setCodeEtat(String codeEtat) {
		this.codeEtat = codeEtat;
	}

	public Date getDateTraca() {
		return dateTraca;
	}

	public void setDateTraca(Date dateTraca) {
		this.dateTraca = dateTraca;
	}

	public String getTypeModif() {
		return typeModif;
	}

	public void setTypeModif(String typeModif) {
		this.typeModif = typeModif;
	}

	public String getCodeFactureAffectation() {
		return codeFactureAffectation;
	}

	public void setCodeFactureAffectation(String codeFactureAffectation) {
		this.codeFactureAffectation = codeFactureAffectation;
	}

	public Date getDateFactureAffectation() {
		return dateFactureAffectation;
	}

	public void setDateFactureAffectation(Date dateFactureAffectation) {
		this.dateFactureAffectation = dateFactureAffectation;
	}

	public BigDecimal getHtFactureAffectation() {
		return htFactureAffectation;
	}

	public void setHtFactureAffectation(BigDecimal htFactureAffectation) {
		this.htFactureAffectation = htFactureAffectation;
	}

	public BigDecimal getTvaFactureAffectation() {
		return tvaFactureAffectation;
	}

	public void setTvaFactureAffectation(BigDecimal tvaFactureAffectation) {
		this.tvaFactureAffectation = tvaFactureAffectation;
	}

	public BigDecimal getTtcFactureAffectation() {
		return ttcFactureAffectation;
	}

	public void setTtcFactureAffectation(BigDecimal ttcFactureAffectation) {
		this.ttcFactureAffectation = ttcFactureAffectation;
	}

	public String getCodeEtatAffectation() {
		return codeEtatAffectation;
	}

	public void setCodeEtatAffectation(String codeEtatAffectation) {
		this.codeEtatAffectation = codeEtatAffectation;
	}

	public Date getDateExportAffectation() {
		return dateExportAffectation;
	}

	public void setDateExportAffectation(Date dateExportAffectation) {
		this.dateExportAffectation = dateExportAffectation;
	}

	public Boolean getAccepte() {
		return accepte;
	}

	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}

	public String getCodeTiers() {
		return codeTiers;
	}

	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}

	public String getNomTiers() {
		return nomTiers;
	}

	public void setNomTiers(String nomTiers) {
		this.nomTiers = nomTiers;
	}

	public String getCodeAcompte() {
		return codeAcompte;
	}

	public void setCodeAcompte(String codeAcompte) {
		this.codeAcompte = codeAcompte;
	}

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	

	



}
