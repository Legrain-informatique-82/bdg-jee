package fr.legrain.article.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaLot;
import fr.legrain.article.model.TaUnite;
import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;



// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * TaBaremeDTO generated by hbm2java
 */
public class TaArticleLotDTO extends ModelObject implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 656467988688867937L;
																	
	private Integer id;
	private Integer idLot;
	private String numLot; 
	private Integer idArticle;
	private String codeArticle;
	private String libelleArticle;
	private Integer idUnite1;
	private String codeUnite1;
	private String liblUnite1;
	
	
	private Integer idUnite2;
	private String codeUnite2;
	private String liblUnite2;
	private BigDecimal rapport;
	private Integer nbDecimal;
	private Integer sens;
	
	private Integer versionObj;
	
	public TaArticleLotDTO() {
	}
	
	
	
	
	
	
	public TaArticleLotDTO(Integer idLot,Integer idArticle, String numLot, String libelleArticle) {
		this.idArticle = idArticle;
		this.idLot = idLot;
		this.numLot = numLot;
		this.libelleArticle = libelleArticle;
		
	}
	
	
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}


//	private TaEntrepot entrepotOrg;
//	private String emplacementOrg;
//	private TaEntrepot entrepotDest;
//	private String emplacementDest;
//	private TaLotArticle lotArticle;
//	private Date dateMouvement;

	
	@LgrHibernateValidated(champBd = "numLot",table = "ta_lot", champEntite="numLot", clazz = TaLotDTO.class)
	public String getNumLot() {
		return numLot;
	}


	public void setNumLot(String numLot) {
		this.numLot = numLot;
	}


	public Integer getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}






	public Integer getIdLot() {
		return idLot;
	}






	public void setIdLot(Integer idLot) {
		this.idLot = idLot;
	}






	public String getLibelleArticle() {
		return libelleArticle;
	}






	public void setLibelleArticle(String libelleArticle) {
		this.libelleArticle = libelleArticle;
	}






	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}




	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_article",table = "ta_article", champEntite="codeArticle", clazz = TaLotDTO.class)
	public String getCodeArticle() {
		return codeArticle;
	}






	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}






	public Integer getIdUnite1() {
		return idUnite1;
	}






	public void setIdUnite1(Integer idUnite1) {
		this.idUnite1 = idUnite1;
	}




	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_unite",table = "ta_unite", champEntite="codeUnite", clazz = TaLotDTO.class)
	public String getCodeUnite1() {
		return codeUnite1;
	}






	public void setCodeUnite1(String codeUnite1) {
		this.codeUnite1 = codeUnite1;
	}






	public String getLiblUnite1() {
		return liblUnite1;
	}






	public void setLiblUnite1(String liblUnite1) {
		this.liblUnite1 = liblUnite1;
	}






	public BigDecimal getRapport() {
		return rapport;
	}






	public void setRapport(BigDecimal rapport) {
		this.rapport = rapport;
	}






	public Integer getIdUnite2() {
		return idUnite2;
	}






	public void setIdUnite2(Integer idUnite2) {
		this.idUnite2 = idUnite2;
	}




	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_unite",table = "ta_unite", champEntite="codeUnite", clazz = TaLotDTO.class)
	public String getCodeUnite2() {
		return codeUnite2;
	}






	public void setCodeUnite2(String codeUnite2) {
		this.codeUnite2 = codeUnite2;
	}






	public String getLiblUnite2() {
		return liblUnite2;
	}






	public void setLiblUnite2(String liblUnite2) {
		this.liblUnite2 = liblUnite2;
	}






	public Integer getNbDecimal() {
		return nbDecimal;
	}






	public void setNbDecimal(Integer nbDecimal) {
		this.nbDecimal = nbDecimal;
	}






	public Integer getSens() {
		return sens;
	}






	public void setSens(Integer sens) {
		this.sens = sens;
	}





}
