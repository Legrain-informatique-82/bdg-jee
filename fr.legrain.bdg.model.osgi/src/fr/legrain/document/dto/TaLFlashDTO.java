package fr.legrain.document.dto;

import java.math.BigDecimal;

import fr.legrain.bdg.model.ModelObject;

/**
 * SWTLFacture generated by hbm2java
 */
public class TaLFlashDTO extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2073504661688098849L;
	private Integer id;
	private Integer idDocument;
	private String codeDocument;

	private Integer idArticle;
	private Integer idTiers;

	private String codeArticle;
	private String codeTiers;
	private String libLDocument;
	private BigDecimal qteLDocument;
	private BigDecimal qte2LDocument;

	private String ipAcces;
	private String u1LDocument;
	private String u2LDocument;
	
	private String codeEntrepot;
	private String emplacement;

	private String numLot;
	private String libelleLot;
	
	private String codeBarre;
	private String codeBarreLu;
	
	private Integer versionObj;
	private String codeEtat;
	
	private BigDecimal qteSaisieLDocument;
	private String uSaisieLDocument;

	public TaLFlashDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Integer idDocument) {
		this.idDocument = idDocument;
	}

	public String getCodeDocument() {
		return codeDocument;
	}

	public void setCodeDocument(String codeDocument) {
		this.codeDocument = codeDocument;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getLibLDocument() {
		return libLDocument;
	}

	public void setLibLDocument(String libLDocument) {
		this.libLDocument = libLDocument;
	}

	public BigDecimal getQteLDocument() {
		return qteLDocument;
	}

	public void setQteLDocument(BigDecimal qteLDocument) {
		this.qteLDocument = qteLDocument;
	}

	public BigDecimal getQte2LDocument() {
		return qte2LDocument;
	}

	public void setQte2LDocument(BigDecimal qte2lDocument) {
		qte2LDocument = qte2lDocument;
	}

	public String getIpAcces() {
		return ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	public String getU1LDocument() {
		return u1LDocument;
	}

	public void setU1LDocument(String u1lDocument) {
		u1LDocument = u1lDocument;
	}

	public String getU2LDocument() {
		return u2LDocument;
	}

	public void setU2LDocument(String u2lDocument) {
		u2LDocument = u2lDocument;
	}

	public String getCodeEntrepot() {
		return codeEntrepot;
	}

	public void setCodeEntrepot(String codeEntrepot) {
		this.codeEntrepot = codeEntrepot;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public String getNumLot() {
		return numLot;
	}

	public void setNumLot(String numLot) {
		this.numLot = numLot;
	}

	public String getLibelleLot() {
		return libelleLot;
	}

	public void setLibelleLot(String libelleLot) {
		this.libelleLot = libelleLot;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public String getCodeBarreLu() {
		return codeBarreLu;
	}

	public void setCodeBarreLu(String codeBarreLu) {
		this.codeBarreLu = codeBarreLu;
	}

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public String getCodeEtat() {
		return codeEtat;
	}

	public void setCodeEtat(String codeEtat) {
		this.codeEtat = codeEtat;
	}

	public Integer getIdTiers() {
		return idTiers;
	}

	public void setIdTiers(Integer idTiers) {
		this.idTiers = idTiers;
	}

	public String getCodeTiers() {
		return codeTiers;
	}

	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeArticle == null) ? 0 : codeArticle.hashCode());
		result = prime * result + ((codeBarre == null) ? 0 : codeBarre.hashCode());
		result = prime * result + ((codeBarreLu == null) ? 0 : codeBarreLu.hashCode());
		result = prime * result + ((codeDocument == null) ? 0 : codeDocument.hashCode());
		result = prime * result + ((codeEntrepot == null) ? 0 : codeEntrepot.hashCode());
		result = prime * result + ((codeEtat == null) ? 0 : codeEtat.hashCode());
		result = prime * result + ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result + ((emplacement == null) ? 0 : emplacement.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idArticle == null) ? 0 : idArticle.hashCode());
		result = prime * result + ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result + ((ipAcces == null) ? 0 : ipAcces.hashCode());
		result = prime * result + ((libLDocument == null) ? 0 : libLDocument.hashCode());
		result = prime * result + ((libelleLot == null) ? 0 : libelleLot.hashCode());
		result = prime * result + ((numLot == null) ? 0 : numLot.hashCode());
		result = prime * result + ((qte2LDocument == null) ? 0 : qte2LDocument.hashCode());
		result = prime * result + ((qteLDocument == null) ? 0 : qteLDocument.hashCode());
		result = prime * result + ((u1LDocument == null) ? 0 : u1LDocument.hashCode());
		result = prime * result + ((u2LDocument == null) ? 0 : u2LDocument.hashCode());
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
		TaLFlashDTO other = (TaLFlashDTO) obj;
		if (codeArticle == null) {
			if (other.codeArticle != null)
				return false;
		} else if (!codeArticle.equals(other.codeArticle))
			return false;
		if (codeBarre == null) {
			if (other.codeBarre != null)
				return false;
		} else if (!codeBarre.equals(other.codeBarre))
			return false;
		if (codeBarreLu == null) {
			if (other.codeBarreLu != null)
				return false;
		} else if (!codeBarreLu.equals(other.codeBarreLu))
			return false;
		if (codeDocument == null) {
			if (other.codeDocument != null)
				return false;
		} else if (!codeDocument.equals(other.codeDocument))
			return false;
		if (codeEntrepot == null) {
			if (other.codeEntrepot != null)
				return false;
		} else if (!codeEntrepot.equals(other.codeEntrepot))
			return false;
		if (codeEtat == null) {
			if (other.codeEtat != null)
				return false;
		} else if (!codeEtat.equals(other.codeEtat))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
			return false;
		if (emplacement == null) {
			if (other.emplacement != null)
				return false;
		} else if (!emplacement.equals(other.emplacement))
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
		if (libLDocument == null) {
			if (other.libLDocument != null)
				return false;
		} else if (!libLDocument.equals(other.libLDocument))
			return false;
		if (libelleLot == null) {
			if (other.libelleLot != null)
				return false;
		} else if (!libelleLot.equals(other.libelleLot))
			return false;
		if (numLot == null) {
			if (other.numLot != null)
				return false;
		} else if (!numLot.equals(other.numLot))
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
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}

	


	public String getUSaisieLDocument() {
		return uSaisieLDocument;
	}

	public void setUSaisieLDocument(String uSaisieLDocument) {
		this.uSaisieLDocument = uSaisieLDocument;
	}

	public BigDecimal getQteSaisieLDocument() {
		return qteSaisieLDocument;
	}

	public void setQteSaisieLDocument(BigDecimal qteSaisieLDocument) {
		this.qteSaisieLDocument = qteSaisieLDocument;
	}


}
