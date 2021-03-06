package fr.legrain.article.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.article.model.TaFabrication;
import fr.legrain.article.model.TaTFabrication;
import fr.legrain.bdg.model.ModelObject;
import fr.legrain.lib.data.LgrConstantes;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;



// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * TaBaremeDTO generated by hbm2java
 */
public class TaFabricationDTO extends ModelObject implements java.io.Serializable {
	
	private static final long serialVersionUID = -4425732529899621121L;
	private Integer id=0;
	private String codeDocument;
	private Date dateDebT;
	private Date dateFinT;
	private Date dateDebR;
	private Date dateFinR;
	private String description;
	
	//private String codeDocument=LgrConstantes.C_STR_VIDE;
	private Date dateDocument=new Date();
	private Date dateEchDocument=new Date();
	private Date dateLivDocument=new Date();
	private Integer numLigneLDocument;
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
	//private BigDecimal REGLE_BONLIV=new BigDecimal(0);
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
	private Integer idTFabrication=0;
	private String codeTFabrication=LgrConstantes.C_STR_VIDE;
	private String liblTFabrication=LgrConstantes.C_STR_VIDE;
	
	private String codeTiersPrestation=LgrConstantes.C_STR_VIDE;
	private String nomRedacteur=LgrConstantes.C_STR_VIDE;
	private String nomControleur=LgrConstantes.C_STR_VIDE;
	
	private List<TaLabelArticleDTO> taLabelArticleDTOs;
	
	private Integer versionObj;
	
	public TaFabricationDTO() {
	}
	
	
	public static TaFabricationDTO copy(TaFabricationDTO taFabrication){
		TaFabricationDTO taFabricationLoc = new TaFabricationDTO();
		
		taFabricationLoc.setCodeDocument(taFabrication.codeDocument);
		taFabricationLoc.setCommentaire(taFabrication.commentaire);
		taFabricationLoc.setDescription(taFabrication.description);
		taFabricationLoc.setDateFinR(taFabrication.dateFinR);
		taFabricationLoc.setDateDebR(taFabrication.dateDebR);
		taFabricationLoc.setDateFinT(taFabrication.dateFinT);
		taFabricationLoc.setDateDebT(taFabrication.dateDebT);
		taFabricationLoc.setId(taFabrication.id);
		
		return taFabricationLoc;
	}
	
	public void setTaFabricationDTO(TaFabricationDTO dto){
		this.id = dto.id;
		this.codeDocument = dto.codeDocument;
		this.commentaire = dto.commentaire;
		this.dateDebR = dto.dateDebR;
		this.dateDebT = dto.dateDebT;
		this.dateFinR = dto.dateFinR;
		this.dateFinT = dto.dateFinT;
		this.description = dto.description;
	}
	
	
	public TaFabricationDTO(Integer id, String codeDocument, Date dateDebR,
			Date dateDocument, String libelleDocument, String codeTiers,
			String nomTiers) {
		super();
		this.id = id;
		this.codeDocument = codeDocument;
		this.dateDebR = dateDebR;
		this.dateDocument = dateDocument;
		this.libelleDocument = libelleDocument;
		this.codeTiers = codeTiers;
		this.nomTiers = nomTiers;
	}
	
	public TaFabricationDTO(Integer id, String codeDocument, Date dateDebR,
			Date dateDocument, String libelleDocument, String codeTiers,
			String nomTiers,String codeTFabrication,String liblTFabrication) {
		super();
		this.id = id;
		this.codeDocument = codeDocument;
		this.dateDebR = dateDebR;
		this.dateDocument = dateDocument;
		this.libelleDocument = libelleDocument;
		this.codeTiers = codeTiers;
		this.nomTiers = nomTiers;
		this.codeTFabrication = codeTFabrication;
		this.liblTFabrication = liblTFabrication;
	}


	public TaFabricationDTO(Integer id, String commentaire, Date dateDebR, Date dateDebT, Date dateFinR, Date dateFinT, String description)
	{
		this.commentaire = commentaire;
		this.dateDebR = dateDebR;
		this.dateDebT = dateDebT;
		this.dateFinR = dateFinR;
		this.dateFinT = dateFinT;
		this.description = description;
		this.id = id;
		
		
	}
	public Integer getId() {
		return this.id;
	}
	
	public void  setId(Integer idFabrication) {
		firePropertyChange("id", this.id, this.id = idFabrication);
	}
	
	
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
//
//	this.commentaire = commentaire;
//	this.dateDebR = dateDebR;
//	this.dateDebT = dateDebT;
//	this.dateFinR = dateFinR;
//	this.dateFinT = dateFinT;
//	this.description = description;
//	this.id = id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaFabricationDTO other = (TaFabricationDTO) obj;
		if (commentaire == null) {
			if (other.commentaire != null){
				return false;
			}
		} 
		else if (!commentaire.equals(other.commentaire)){
			return false;
		}
		if (dateDebR == null) {
			if (other.dateDebR != null){
				return false;
			}
		} 
		else if (!dateDebR.equals(other.dateDebR)){
			return false;
		}
		if (dateDebT == null) {
			if (other.dateDebT != null){
				return false;
			}
		} 
		else if (!dateDebT.equals(other.dateDebT)){
			return false;
		}
		
		
		if (dateFinR == null) {
			if (other.dateFinR != null){
				return false;
			}
		} 
		else if (!dateFinR.equals(other.dateFinR)){
			return false;
		}
		if (dateFinT == null) {
			if (other.dateFinT != null){
				return false;
			}
		} 
		else if (!dateFinT.equals(other.dateFinT)){
			return false;
		}
		if (description == null) {
			if (other.description != null){
				return false;
			}
		} 
		else if (!description.equals(other.description)){
			return false;
		}		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

	@LgrHibernateValidated(champBd = "date_deb_t",table = "ta_fabrication", champEntite="dateDebT", clazz = TaFabricationDTO.class)
	public Date getDateDebT() {
		return dateDebT;
	}


	public void setDateDebT(Date dateDebT) {
		this.dateDebT = dateDebT;
	}

	@LgrHibernateValidated(champBd = "date_fin_t",table = "ta_fabrication", champEntite="dateFinT", clazz = TaFabricationDTO.class)
	public Date getDateFinT() {
		return dateFinT;
	}


	public void setDateFinT(Date dateFinT) {
		this.dateFinT = dateFinT;
	}

	@LgrHibernateValidated(champBd = "date_deb_r",table = "ta_fabrication", champEntite="dateDebR", clazz = TaFabricationDTO.class)
	public Date getDateDebR() {
		return dateDebR;
	}


	public void setDateDebR(Date dateDebR) {
		this.dateDebR = dateDebR;
	}

	@LgrHibernateValidated(champBd = "date_fin_r",table = "ta_fabrication", champEntite="dateFinR", clazz = TaFabricationDTO.class)
	public Date getDateFinR() {
		return dateFinR;
	}


	public void setDateFinR(Date dateFinR) {
		this.dateFinR = dateFinR;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@LgrHibernateValidated(champBd = "commentaire",table = "ta_fabrication", champEntite="commentaire", clazz = TaFabricationDTO.class)
	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_document",table = "ta_fabrication", champEntite="codeDocument", clazz = TaFabricationDTO.class)
	public String getCodeDocument() {
		return codeDocument;
	}


	public void setCodeDocument(String code) {
		this.codeDocument = code;
	}


	public Integer getNumLigneLDocument() {
		return numLigneLDocument;
	}


	public void setNumLigneLDocument(Integer numLigneLDocument) {
		this.numLigneLDocument = numLigneLDocument;
	}

	@LgrHibernateValidated(champBd = "libelle_document",table = "ta_fabrication", champEntite="libelleDocument", clazz = TaFabricationDTO.class)
	public String getLibelleDocument() {
		return libelleDocument;
	}


	public void setLibelleDocument(String libelleDocument) {
		this.libelleDocument = libelleDocument;
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_tiers",table = "ta_tiers", champEntite="codeTiers", clazz = TaFabricationDTO.class)
	public String getCodeTiers() {
		return codeTiers;
	}


	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}

	@LgrHibernateValidated(champBd = "nom_tiers",table = "ta_tiers", champEntite="nomTiers", clazz = TaFabricationDTO.class)
	public String getNomTiers() {
		return nomTiers;
	}


	public void setNomTiers(String nomTiers) {
		this.nomTiers = nomTiers;
	}

public Integer getIdTFabrication() {
		return idTFabrication;
	}


	public void setIdTFabrication(Integer idTFabrication) {
		this.idTFabrication = idTFabrication;
	}

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_t_fabrication",table = "ta_t_fabrication", champEntite="codeTFabrication", clazz = TaFabricationDTO.class)
	public String getCodeTFabrication() {
		return codeTFabrication;
	}


	public void setCodeTFabrication(String codeTFabrication) {
		this.codeTFabrication = codeTFabrication;
	}

	@LgrHibernateValidated(champBd = "libl_t_fabrication",table = "ta_t_fabrication", champEntite="liblTFabrication", clazz = TaFabricationDTO.class)
	public String getLiblTFabrication() {
		return liblTFabrication;
	}


	public void setLiblTFabrication(String liblTFabrication) {
		this.liblTFabrication = liblTFabrication;
	}
	
	
	@LgrHibernateValidated(champBd = "date_document",table = "ta_fabrication", champEntite="dateDocument", clazz = TaFabricationDTO.class)
	public Date getDateDocument() {
		return dateDocument;
	}


	public void setDateDocument(Date dateDocument) {
		this.dateDocument = dateDocument;
	}


	public String getCodeTiersPrestation() {
		return codeTiersPrestation;
	}


	public void setCodeTiersPrestation(String codeTiersPrestation) {
		this.codeTiersPrestation = codeTiersPrestation;
	}


	public String getNomRedacteur() {
		return nomRedacteur;
	}


	public void setNomRedacteur(String nomRedacteur) {
		this.nomRedacteur = nomRedacteur;
	}


	public String getNomControleur() {
		return nomControleur;
	}


	public void setNomControleur(String nomControleur) {
		this.nomControleur = nomControleur;
	}


	public List<TaLabelArticleDTO> getTaLabelArticleDTOs() {
		return taLabelArticleDTOs;
	}


	public void setTaLabelArticleDTOs(List<TaLabelArticleDTO> taLabelArticleDTOs) {
		this.taLabelArticleDTOs = taLabelArticleDTOs;
	}


	public Date getDateEchDocument() {
		return dateEchDocument;
	}


	public void setDateEchDocument(Date dateEchDocument) {
		this.dateEchDocument = dateEchDocument;
	}


	public Date getDateLivDocument() {
		return dateLivDocument;
	}


	public void setDateLivDocument(Date dateLivDocument) {
		this.dateLivDocument = dateLivDocument;
	}


	public Integer getIdAdresse() {
		return idAdresse;
	}


	public void setIdAdresse(Integer idAdresse) {
		this.idAdresse = idAdresse;
	}


	public Integer getIdAdresseLiv() {
		return idAdresseLiv;
	}


	public void setIdAdresseLiv(Integer idAdresseLiv) {
		this.idAdresseLiv = idAdresseLiv;
	}


	public Integer getIdTiers() {
		return idTiers;
	}


	public void setIdTiers(Integer idTiers) {
		this.idTiers = idTiers;
	}


	public String getPrenomTiers() {
		return prenomTiers;
	}


	public void setPrenomTiers(String prenomTiers) {
		this.prenomTiers = prenomTiers;
	}


	public String getSurnomTiers() {
		return surnomTiers;
	}


	public void setSurnomTiers(String surnomTiers) {
		this.surnomTiers = surnomTiers;
	}


	public String getCodeCompta() {
		return codeCompta;
	}


	public void setCodeCompta(String codeCompta) {
		this.codeCompta = codeCompta;
	}


	public String getCompte() {
		return compte;
	}


	public void setCompte(String compte) {
		this.compte = compte;
	}


	public Integer getIdTPaiement() {
		return idTPaiement;
	}


	public void setIdTPaiement(Integer idTPaiement) {
		this.idTPaiement = idTPaiement;
	}


	public String getCodeTPaiement() {
		return codeTPaiement;
	}


	public void setCodeTPaiement(String codeTPaiement) {
		this.codeTPaiement = codeTPaiement;
	}


	public Integer getIdCPaiement() {
		return idCPaiement;
	}


	public void setIdCPaiement(Integer idCPaiement) {
		this.idCPaiement = idCPaiement;
	}


	public BigDecimal getRemHtDocument() {
		return remHtDocument;
	}


	public void setRemHtDocument(BigDecimal remHtDocument) {
		this.remHtDocument = remHtDocument;
	}


	public BigDecimal getTxRemHtDocument() {
		return txRemHtDocument;
	}


	public void setTxRemHtDocument(BigDecimal txRemHtDocument) {
		this.txRemHtDocument = txRemHtDocument;
	}


	public BigDecimal getRemTtcDocument() {
		return remTtcDocument;
	}


	public void setRemTtcDocument(BigDecimal remTtcDocument) {
		this.remTtcDocument = remTtcDocument;
	}


	public BigDecimal getTxRemTtcDocument() {
		return txRemTtcDocument;
	}


	public void setTxRemTtcDocument(BigDecimal txRemTtcDocument) {
		this.txRemTtcDocument = txRemTtcDocument;
	}


	public Integer getNbEDocument() {
		return nbEDocument;
	}


	public void setNbEDocument(Integer nbEDocument) {
		this.nbEDocument = nbEDocument;
	}


	public BigDecimal getMtTtcCalc() {
		return mtTtcCalc;
	}


	public void setMtTtcCalc(BigDecimal mtTtcCalc) {
		this.mtTtcCalc = mtTtcCalc;
	}


	public BigDecimal getMtHtCalc() {
		return mtHtCalc;
	}


	public void setMtHtCalc(BigDecimal mtHtCalc) {
		this.mtHtCalc = mtHtCalc;
	}


	public BigDecimal getMtTvaCalc() {
		return mtTvaCalc;
	}


	public void setMtTvaCalc(BigDecimal mtTvaCalc) {
		this.mtTvaCalc = mtTvaCalc;
	}


	public BigDecimal getNetTtcCalc() {
		return netTtcCalc;
	}


	public void setNetTtcCalc(BigDecimal netTtcCalc) {
		this.netTtcCalc = netTtcCalc;
	}


	public BigDecimal getNetHtCalc() {
		return netHtCalc;
	}


	public void setNetHtCalc(BigDecimal netHtCalc) {
		this.netHtCalc = netHtCalc;
	}


	public BigDecimal getNetTvaCalc() {
		return netTvaCalc;
	}


	public void setNetTvaCalc(BigDecimal netTvaCalc) {
		this.netTvaCalc = netTvaCalc;
	}


	public String getIpAcces() {
		return ipAcces;
	}


	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}


	public Boolean getTtc() {
		return ttc;
	}


	public void setTtc(Boolean ttc) {
		this.ttc = ttc;
	}


	public Boolean getExport() {
		return export;
	}


	public void setExport(Boolean export) {
		this.export = export;
	}


	public Boolean getAccepte() {
		return accepte;
	}


	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}


}
