package fr.legrain.liasseFiscale.db.dao;

// Generated Dec 7, 2006 10:37:19 AM by Hibernate Tools 3.1.0.beta5

/**
 * Final generated by hbm2java
 */
public class Final implements java.io.Serializable {

	// Fields    

	private Integer idFinal;

	private Integer cle;

	private String valeur;

	private Integer idDetail;

	// Constructors

	/** default constructor */
	public Final() {
	}

	/** minimal constructor */
	public Final(Integer idFinal) {
		this.idFinal = idFinal;
	}

	/** full constructor */
	public Final(Integer idFinal, Integer cle, String valeur, Integer idDetail) {
		this.idFinal = idFinal;
		this.cle = cle;
		this.valeur = valeur;
		this.idDetail = idDetail;
	}

	// Property accessors
	public Integer getIdFinal() {
		return this.idFinal;
	}

	public void setIdFinal(Integer idFinal) {
		this.idFinal = idFinal;
	}

	public Integer getCle() {
		return this.cle;
	}

	public void setCle(Integer cle) {
		this.cle = cle;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Integer getIdDetail() {
		return this.idDetail;
	}

	public void setIdDetail(Integer idDetail) {
		this.idDetail = idDetail;
	}

}
