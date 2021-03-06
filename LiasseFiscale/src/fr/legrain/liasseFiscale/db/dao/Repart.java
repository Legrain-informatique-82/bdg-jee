package fr.legrain.liasseFiscale.db.dao;

// Generated Dec 7, 2006 10:37:19 AM by Hibernate Tools 3.1.0.beta5

import java.math.BigDecimal;

/**
 * Repart generated by hbm2java
 */
public class Repart implements java.io.Serializable {

	// Fields    

	private Integer idRepart;

	private BigDecimal numCpt;

	private BigDecimal mtDebit;

	private BigDecimal mtCredit;

	private BigDecimal solde;

	private BigDecimal mtDebit2;

	private BigDecimal mtCredit2;

	private BigDecimal solde2;

	private BigDecimal mtDebit3;

	private BigDecimal mtCredit3;

	private BigDecimal solde3;

	private String regime;

	private String typeTraitement;

	// Constructors

	/** default constructor */
	public Repart() {
	}

	/** minimal constructor */
	public Repart(Integer idRepart) {
		this.idRepart = idRepart;
	}

	/** full constructor */
	public Repart(Integer idRepart, BigDecimal numCpt, BigDecimal mtDebit,
			BigDecimal mtCredit, BigDecimal solde, BigDecimal mtDebit2,
			BigDecimal mtCredit2, BigDecimal solde2, BigDecimal mtDebit3,
			BigDecimal mtCredit3, BigDecimal solde3, String regime,
			String typeTraitement) {
		this.idRepart = idRepart;
		this.numCpt = numCpt;
		this.mtDebit = mtDebit;
		this.mtCredit = mtCredit;
		this.solde = solde;
		this.mtDebit2 = mtDebit2;
		this.mtCredit2 = mtCredit2;
		this.solde2 = solde2;
		this.mtDebit3 = mtDebit3;
		this.mtCredit3 = mtCredit3;
		this.solde3 = solde3;
		this.regime = regime;
		this.typeTraitement = typeTraitement;
	}

	// Property accessors
	public Integer getIdRepart() {
		return this.idRepart;
	}

	public void setIdRepart(Integer idRepart) {
		this.idRepart = idRepart;
	}

	public BigDecimal getNumCpt() {
		return this.numCpt;
	}

	public void setNumCpt(BigDecimal numCpt) {
		this.numCpt = numCpt;
	}

	public BigDecimal getMtDebit() {
		return this.mtDebit;
	}

	public void setMtDebit(BigDecimal mtDebit) {
		this.mtDebit = mtDebit;
	}

	public BigDecimal getMtCredit() {
		return this.mtCredit;
	}

	public void setMtCredit(BigDecimal mtCredit) {
		this.mtCredit = mtCredit;
	}

	public BigDecimal getSolde() {
		return this.solde;
	}

	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}

	public BigDecimal getMtDebit2() {
		return this.mtDebit2;
	}

	public void setMtDebit2(BigDecimal mtDebit2) {
		this.mtDebit2 = mtDebit2;
	}

	public BigDecimal getMtCredit2() {
		return this.mtCredit2;
	}

	public void setMtCredit2(BigDecimal mtCredit2) {
		this.mtCredit2 = mtCredit2;
	}

	public BigDecimal getSolde2() {
		return this.solde2;
	}

	public void setSolde2(BigDecimal solde2) {
		this.solde2 = solde2;
	}

	public BigDecimal getMtDebit3() {
		return this.mtDebit3;
	}

	public void setMtDebit3(BigDecimal mtDebit3) {
		this.mtDebit3 = mtDebit3;
	}

	public BigDecimal getMtCredit3() {
		return this.mtCredit3;
	}

	public void setMtCredit3(BigDecimal mtCredit3) {
		this.mtCredit3 = mtCredit3;
	}

	public BigDecimal getSolde3() {
		return this.solde3;
	}

	public void setSolde3(BigDecimal solde3) {
		this.solde3 = solde3;
	}

	public String getRegime() {
		return this.regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public String getTypeTraitement() {
		return this.typeTraitement;
	}

	public void setTypeTraitement(String typeTraitement) {
		this.typeTraitement = typeTraitement;
	}

}
