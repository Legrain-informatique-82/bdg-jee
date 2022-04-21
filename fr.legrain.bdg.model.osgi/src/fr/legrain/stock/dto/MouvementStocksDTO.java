package fr.legrain.stock.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaEntrepot;
import fr.legrain.article.model.TaLot;
import fr.legrain.bdg.model.ModelObject;
import fr.legrain.document.dto.TaLFactureDTO;
import fr.legrain.document.model.TaLBonReception;
import fr.legrain.stock.model.TaMouvementStock;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * SWTPrix generated by hbm2java
 */
public class MouvementStocksDTO extends ModelObject implements java.io.Serializable {


	private static final long serialVersionUID = 4743190844390318811L;
	
	
	private Integer id;	
	private Integer idGrMouvStock;
	private String codeGrMouvStock;	
	private Date dateStock;
	private String libelleStock;
	private BigDecimal qte1Stock;
	private String un1Stock;
	private BigDecimal qte2Stock;
	private String un2Stock;	
	private Integer idArticle;
	private String codeArticle;	
	private String codeFamille;	
	private Integer idLot;
	private String numLot;	
	private String description;
	private String typeDoc;
	private Integer idDocument;
	
	private Integer idEntrepot;
	private String codeEntrepot;
	private String emplacement;
	
	private String codeEntrepotDest;
	private String emplacementDest;
	private String lettrageDeplacement;
	
	private BigDecimal depart;
	private BigDecimal entree;
	private BigDecimal sortie;
	private BigDecimal dispo;
	
	private BigDecimal qteRef;
	private String unRef;
	private Boolean termine=false;
	
	private String codeTitreTransport;
	private BigDecimal qteTitreTransport;
	
	@Transient
	private Boolean utilise=false;
	
	private Integer versionObj;
	
	public MouvementStocksDTO() {	
	}


	public MouvementStocksDTO(Integer idGrMouvStock, String codeGrMouvStock,
			Date dateStock, String libelleStock, String typeDoc) {
		super();
		this.idGrMouvStock = idGrMouvStock;
		this.codeGrMouvStock = codeGrMouvStock;
		this.dateStock = dateStock;
		this.libelleStock = libelleStock;
		this.typeDoc = typeDoc;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer ID_STOCK) {
		firePropertyChange("idMouvementStock", this.id, this.id = ID_STOCK);
	}

	@NotNull
	@Size(min=1, max=20)
	public String getCodeGrMouvStock() {
		return codeGrMouvStock;
	}


	public void setCodeGrMouvStock(String codeGrMouvStock) {
		firePropertyChange("codeGrMouvStock", this.codeGrMouvStock, this.codeGrMouvStock = codeGrMouvStock);
	}

	@LgrHibernateValidated(champBd = "date_stock",table = "ta_mouvement_stock", champEntite="dateStock", clazz = MouvementStocksDTO.class)
	public Date getDateStock() {
		return dateStock;
	}


	public void setDateStock(Date dateStock) {
		firePropertyChange("dateStock", this.dateStock, this.dateStock = dateStock);
	}

	@LgrHibernateValidated(champBd = "libelle_stock",table = "ta_mouvement_stock", champEntite="libelleStock", clazz = MouvementStocksDTO.class)
	public String getLibelleStock() {
		return libelleStock;
	}


	public void setLibelleStock(String libelleStock) {
		firePropertyChange("libelleStock", this.libelleStock, this.libelleStock = libelleStock);
	}

	@LgrHibernateValidated(champBd = "qte1_stock",table = "ta_mouvement_stock", champEntite="qte1Stock", clazz = MouvementStocksDTO.class)
	public BigDecimal getQte1Stock() {
		return qte1Stock;
	}


	public void setQte1Stock(BigDecimal qte1Stock) {
		firePropertyChange("qte1Stock", this.qte1Stock, this.qte1Stock = qte1Stock);
	}

	@LgrHibernateValidated(champBd = "un1_stock",table = "ta_mouvement_stock", champEntite="un1Stock", clazz = MouvementStocksDTO.class)
	public String getUn1Stock() {
		return un1Stock;
	}


	public void setUn1Stock(String un1Stock) {
		firePropertyChange("un1Stock", this.un1Stock, this.un1Stock = un1Stock);
	}

	@LgrHibernateValidated(champBd = "qte2_stock",table = "ta_mouvement_stock", champEntite="qte2Stock", clazz = MouvementStocksDTO.class)
	public BigDecimal getQte2Stock() {
		return qte2Stock;
	}


	public void setQte2Stock(BigDecimal qte2Stock) {
		firePropertyChange("qte2Stock", this.qte2Stock, this.qte2Stock = qte2Stock);
	}

	@LgrHibernateValidated(champBd = "un2_stock",table = "ta_mouvement_stock", champEntite="un2Stock", clazz = MouvementStocksDTO.class)
	public String getUn2Stock() {
		return un2Stock;
	}


	public void setUn2Stock(String un2Stock) {
		firePropertyChange("un2Stock", this.un2Stock, this.un2Stock = un2Stock);
	}


	public Integer getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(Integer idArticle) {
		firePropertyChange("idArticle", this.idArticle, this.idArticle = idArticle);
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_article",table = "ta_article", champEntite="codeArticle", clazz = MouvementStocksDTO.class)
	public String getCodeArticle() {
		return codeArticle;
	}


	public void setCodeArticle(String codeArticle) {
		firePropertyChange("codeArticle", this.codeArticle, this.codeArticle = codeArticle);
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String commentaire) {
		firePropertyChange("description", this.description, this.description = description);
	}


	public String getTypeDoc() {
		return typeDoc;
	}


	public void setTypeDoc(String typeDoc) {
		firePropertyChange("typeDoc", this.typeDoc, this.typeDoc = typeDoc);
	}


	public Integer getIdDocument() {
		return idDocument;
	}


	public void setIdDocument(Integer idDocument) {
		firePropertyChange("idDocument", this.idDocument, this.idDocument = idDocument);
	}


	public Integer getIdGrMouvStock() {
		return idGrMouvStock;
	}


	public void setIdGrMouvStock(Integer idGrMouvStock) {
		firePropertyChange("idGrMouvStock", this.idGrMouvStock, this.idGrMouvStock = idGrMouvStock);
	}
	public Integer getIdEntrepot() {
		return idEntrepot;
	}


	public void setIdEntrepot(Integer idEntrepot) {
		firePropertyChange("idEntrepot", this.idEntrepot, this.idEntrepot = idEntrepot);
	}

	@LgrHibernateValidated(champBd = "code_entrepot",table = "ta_entrepot", champEntite="codeEntrepot", clazz = MouvementStocksDTO.class)
	public String getCodeEntrepot() {
		return codeEntrepot;
	}


	public void setCodeEntrepot(String codeEntrepot) {
		firePropertyChange("codeEntrepot", this.codeEntrepot, this.codeEntrepot = codeEntrepot);
	}


	@LgrHibernateValidated(champBd = "emplacement",table = "ta_mouvement_stock", champEntite="emplacement", clazz = MouvementStocksDTO.class)
	public String getEmplacement() {
		return emplacement;
	}


	public void setEmplacement(String emplacement) {
		firePropertyChange("emplacement", this.emplacement, this.emplacement = emplacement);
	}


	public String getCodeEntrepotDest() {
		return codeEntrepotDest;
	}


	public void setCodeEntrepotDest(String codeEntrepotDest) {
		firePropertyChange("codeEntrepotDest", this.codeEntrepotDest, this.codeEntrepotDest = codeEntrepotDest);
	}
	
	@LgrHibernateValidated(champBd = "emplacement",table = "ta_mouvement_stock", champEntite="emplacementDest", clazz = MouvementStocksDTO.class)
	public String getEmplacementDest() {
		return emplacementDest;
	}


	public void setEmplacementDest(String emplacementDest) {
		firePropertyChange("emplacementDest", this.emplacementDest, this.emplacementDest = emplacementDest);
	}
	

	public Integer getIdLot() {
		return idLot;
	}


	public void setIdLot(Integer idLot) {
		firePropertyChange("idLot", this.idLot, this.idLot = idLot);
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "num_lot",table = "ta_lot", champEntite="numLot", clazz = MouvementStocksDTO.class)
	public String getNumLot() {
		return numLot;
	}


	public void setNumLot(String numLot) {
		firePropertyChange("numLot", this.numLot, this.numLot = numLot);
	}	
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}



	public String getLettrageDeplacement() {
		return lettrageDeplacement;
	}


	public void setLettrageDeplacement(String lettrageDeplacement) {
		firePropertyChange("lettrageDeplacement", this.lettrageDeplacement, this.lettrageDeplacement = lettrageDeplacement);
	}


	@Transient
	public Boolean getUtilise() {
		return utilise;
	}


	public void setUtilise(Boolean utilise) {
		this.utilise = utilise;
	}

	

	public static MouvementStocksDTO copy(MouvementStocksDTO mouvementStockDTO){
		MouvementStocksDTO mouvementStockDTOLoc = new MouvementStocksDTO();
		mouvementStockDTOLoc.setId(mouvementStockDTO.id);
		mouvementStockDTOLoc.setCodeEntrepotDest(mouvementStockDTO.codeEntrepotDest);
		mouvementStockDTOLoc.setCodeEntrepot(mouvementStockDTO.codeEntrepot);
		mouvementStockDTOLoc.setCodeGrMouvStock(mouvementStockDTO.codeGrMouvStock);
		mouvementStockDTOLoc.setIdArticle(mouvementStockDTO.idArticle);
		mouvementStockDTOLoc.setCodeArticle(mouvementStockDTO.codeArticle);
		mouvementStockDTOLoc.setIdLot(mouvementStockDTO.idLot);
		mouvementStockDTOLoc.setNumLot(mouvementStockDTO.numLot);		
		mouvementStockDTOLoc.setDateStock(mouvementStockDTO.dateStock);
		mouvementStockDTOLoc.setDescription(mouvementStockDTO.description);
		mouvementStockDTOLoc.setEmplacementDest(mouvementStockDTO.emplacementDest);
		mouvementStockDTOLoc.setEmplacement(mouvementStockDTO.emplacement);
		mouvementStockDTOLoc.setIdDocument(mouvementStockDTO.idDocument);
		mouvementStockDTOLoc.setIdEntrepot(mouvementStockDTO.idEntrepot);
		mouvementStockDTOLoc.setIdGrMouvStock(mouvementStockDTO.idGrMouvStock);
		mouvementStockDTOLoc.setLibelleStock(mouvementStockDTO.libelleStock);
		mouvementStockDTOLoc.setQte1Stock(mouvementStockDTO.qte1Stock);
		mouvementStockDTOLoc.setQte2Stock(mouvementStockDTO.qte2Stock);
		mouvementStockDTOLoc.setTypeDoc(mouvementStockDTO.typeDoc);
		mouvementStockDTOLoc.setUn1Stock(mouvementStockDTO.un1Stock);
		mouvementStockDTOLoc.setUn2Stock(mouvementStockDTO.un2Stock);
		mouvementStockDTOLoc.setLettrageDeplacement(mouvementStockDTO.lettrageDeplacement);
		return mouvementStockDTOLoc;
	}


	public MouvementStocksDTO(Integer id, Integer idGrMouvStock,
			String codeGrMouvStock, Date dateStock, String libelleStock,
			BigDecimal qte1Stock, String un1Stock, BigDecimal qte2Stock,
			String un2Stock, Integer idArticle, String codeArticle,
			Integer idLot, String numLot, String description, String typeDoc,
			Integer idDocument, Integer idEntrepot, String codeEntrepot,
			String emplacement, String codeEntrepotDest,
			String emplacementDest, String lettrageEmplacement,
			Integer versionObj) {
		super();
		this.id = id;
		this.idGrMouvStock = idGrMouvStock;
		this.codeGrMouvStock = codeGrMouvStock;
		this.dateStock = dateStock;
		this.libelleStock = libelleStock;
		this.qte1Stock = qte1Stock;
		this.un1Stock = un1Stock;
		this.qte2Stock = qte2Stock;
		this.un2Stock = un2Stock;
		this.idArticle = idArticle;
		this.codeArticle = codeArticle;
		this.idLot = idLot;
		this.numLot = numLot;
		this.description = description;
		this.typeDoc = typeDoc;
		this.idDocument = idDocument;
		this.idEntrepot = idEntrepot;
		this.codeEntrepot = codeEntrepot;
		this.emplacement = emplacement;
		this.codeEntrepotDest = codeEntrepotDest;
		this.emplacementDest = emplacementDest;
		this.lettrageDeplacement = lettrageEmplacement;
		this.versionObj = versionObj;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeArticle == null) ? 0 : codeArticle.hashCode());
		result = prime * result
				+ ((codeEntrepot == null) ? 0 : codeEntrepot.hashCode());
		result = prime
				* result
				+ ((codeEntrepotDest == null) ? 0 : codeEntrepotDest.hashCode());
		result = prime * result
				+ ((codeGrMouvStock == null) ? 0 : codeGrMouvStock.hashCode());
		result = prime * result
				+ ((dateStock == null) ? 0 : dateStock.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((emplacement == null) ? 0 : emplacement.hashCode());
		result = prime * result
				+ ((emplacementDest == null) ? 0 : emplacementDest.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idArticle == null) ? 0 : idArticle.hashCode());
		result = prime * result
				+ ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result
				+ ((idEntrepot == null) ? 0 : idEntrepot.hashCode());
		result = prime * result
				+ ((idGrMouvStock == null) ? 0 : idGrMouvStock.hashCode());
		result = prime * result + ((idLot == null) ? 0 : idLot.hashCode());
		result = prime
				* result
				+ ((lettrageDeplacement == null) ? 0 : lettrageDeplacement
						.hashCode());
		result = prime * result
				+ ((libelleStock == null) ? 0 : libelleStock.hashCode());
		result = prime * result + ((numLot == null) ? 0 : numLot.hashCode());
		result = prime * result
				+ ((qte1Stock == null) ? 0 : qte1Stock.hashCode());
		result = prime * result
				+ ((qte2Stock == null) ? 0 : qte2Stock.hashCode());
		result = prime * result + ((typeDoc == null) ? 0 : typeDoc.hashCode());
		result = prime * result
				+ ((un1Stock == null) ? 0 : un1Stock.hashCode());
		result = prime * result
				+ ((un2Stock == null) ? 0 : un2Stock.hashCode());
		result = prime * result
				+ ((versionObj == null) ? 0 : versionObj.hashCode());
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
		MouvementStocksDTO other = (MouvementStocksDTO) obj;
		if (codeArticle == null) {
			if (other.codeArticle != null)
				return false;
		} else if (!codeArticle.equals(other.codeArticle))
			return false;
		if (codeEntrepot == null) {
			if (other.codeEntrepot != null)
				return false;
		} else if (!codeEntrepot.equals(other.codeEntrepot))
			return false;
		if (codeEntrepotDest == null) {
			if (other.codeEntrepotDest != null)
				return false;
		} else if (!codeEntrepotDest.equals(other.codeEntrepotDest))
			return false;
		if (codeGrMouvStock == null) {
			if (other.codeGrMouvStock != null)
				return false;
		} else if (!codeGrMouvStock.equals(other.codeGrMouvStock))
			return false;
		if (dateStock == null) {
			if (other.dateStock != null)
				return false;
		} else if (!dateStock.equals(other.dateStock))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (emplacement == null) {
			if (other.emplacement != null)
				return false;
		} else if (!emplacement.equals(other.emplacement))
			return false;
		if (emplacementDest == null) {
			if (other.emplacementDest != null)
				return false;
		} else if (!emplacementDest.equals(other.emplacementDest))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idArticle == null) {
			if (other.idArticle != null)
				return false;
		} else if (!idArticle.equals(other.idArticle))
			return false;
		if (idDocument == null) {
			if (other.idDocument != null)
				return false;
		} else if (!idDocument.equals(other.idDocument))
			return false;
		if (idEntrepot == null) {
			if (other.idEntrepot != null)
				return false;
		} else if (!idEntrepot.equals(other.idEntrepot))
			return false;
		if (idGrMouvStock == null) {
			if (other.idGrMouvStock != null)
				return false;
		} else if (!idGrMouvStock.equals(other.idGrMouvStock))
			return false;
		if (idLot == null) {
			if (other.idLot != null)
				return false;
		} else if (!idLot.equals(other.idLot))
			return false;
		if (lettrageDeplacement == null) {
			if (other.lettrageDeplacement != null)
				return false;
		} else if (!lettrageDeplacement.equals(other.lettrageDeplacement))
			return false;
		if (libelleStock == null) {
			if (other.libelleStock != null)
				return false;
		} else if (!libelleStock.equals(other.libelleStock))
			return false;
		if (numLot == null) {
			if (other.numLot != null)
				return false;
		} else if (!numLot.equals(other.numLot))
			return false;
		if (qte1Stock == null) {
			if (other.qte1Stock != null)
				return false;
		} else if (!qte1Stock.equals(other.qte1Stock))
			return false;
		if (qte2Stock == null) {
			if (other.qte2Stock != null)
				return false;
		} else if (!qte2Stock.equals(other.qte2Stock))
			return false;
		if (typeDoc == null) {
			if (other.typeDoc != null)
				return false;
		} else if (!typeDoc.equals(other.typeDoc))
			return false;
		if (un1Stock == null) {
			if (other.un1Stock != null)
				return false;
		} else if (!un1Stock.equals(other.un1Stock))
			return false;
		if (un2Stock == null) {
			if (other.un2Stock != null)
				return false;
		} else if (!un2Stock.equals(other.un2Stock))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}


	public String getCodeFamille() {
		return codeFamille;
	}


	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}


	public BigDecimal getDepart() {
		return depart;
	}


	public void setDepart(BigDecimal depart) {
		this.depart = depart;
	}


	public BigDecimal getEntree() {
		return entree;
	}


	public void setEntree(BigDecimal entree) {
		this.entree = entree;
	}


	public BigDecimal getSortie() {
		return sortie;
	}


	public void setSortie(BigDecimal sortie) {
		this.sortie = sortie;
	}


	public BigDecimal getDispo() {
		return dispo;
	}


	public void setDispo(BigDecimal dispo) {
		this.dispo = dispo;
	}


	public BigDecimal getQteRef() {
		return qteRef;
	}


	public void setQteRef(BigDecimal qteRef) {
		this.qteRef = qteRef;
	}


	public String getUnRef() {
		return unRef;
	}


	public void setUnRef(String unRef) {
		this.unRef = unRef;
	}


	public Boolean getTermine() {
		return termine;
	}


	public void setTermine(Boolean termine) {
		this.termine = termine;
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



	
	
}
