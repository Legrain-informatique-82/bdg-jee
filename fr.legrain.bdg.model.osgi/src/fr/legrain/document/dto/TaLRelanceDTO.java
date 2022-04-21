package fr.legrain.document.dto;

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.bdg.model.ModelObject;

public class TaLRelanceDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 867297619745756999L;
	
	private Integer id;
	private String codeRelance;
	private Integer idRelance;
	private Date dateRelance;
	private String codeDocument;
	private Date dateEcheance;
	private String codeTiers;
	private String nomTiers;
	private String typeDocument;
	private BigDecimal netTTC=new BigDecimal(0);
	private BigDecimal resteARegler=new BigDecimal(0);
	private Integer idTRelance;
	private String codeTRelance;
	private String libelleTRelance;
	private Integer etat = 0;
	private Boolean accepte=true;
	private Integer versionObj;
	
	public TaLRelanceDTO() {
	}

	public void setIHMLRelance(TaLRelanceDTO ihmLRelance) {
		setIdLRelance(ihmLRelance.id);
		setCodeRelance(ihmLRelance.codeRelance) ;
		setIdRelance(ihmLRelance.idRelance) ;
		setCodeDocument(ihmLRelance.codeDocument);
		setDateEcheance(ihmLRelance.dateEcheance);
		setCodeTiers(ihmLRelance.codeTiers);
		setTypeDocument(ihmLRelance.typeDocument);
		setNetTTC(ihmLRelance.netTTC);
		setResteARegler(ihmLRelance.resteARegler);
		setIdTRelance(ihmLRelance.idTRelance);
		setCodeTRelance(ihmLRelance.codeTRelance);
		setLibelleTRelance(ihmLRelance.libelleTRelance);
		setNomTiers(ihmLRelance.nomTiers);
		setEtat(ihmLRelance.etat);
	}
	
	static public TaLRelanceDTO copy(TaLRelanceDTO ihmLRelance) {
		TaLRelanceDTO swtLRelanceLoc = new TaLRelanceDTO();
		swtLRelanceLoc.setIdLRelance(ihmLRelance.id);
		swtLRelanceLoc.setCodeRelance(ihmLRelance.codeRelance);
		swtLRelanceLoc.setIdRelance(ihmLRelance.idRelance);
		swtLRelanceLoc.setCodeDocument(ihmLRelance.codeDocument);
		swtLRelanceLoc.setDateEcheance(ihmLRelance.dateEcheance);
		swtLRelanceLoc.setCodeTiers(ihmLRelance.codeTiers);
		swtLRelanceLoc.setTypeDocument(ihmLRelance.typeDocument);
		swtLRelanceLoc.setNetTTC(ihmLRelance.netTTC);
		swtLRelanceLoc.setResteARegler(ihmLRelance.resteARegler);
		swtLRelanceLoc.setIdTRelance(ihmLRelance.idTRelance);
		swtLRelanceLoc.setCodeTRelance(ihmLRelance.codeTRelance);
		swtLRelanceLoc.setLibelleTRelance(ihmLRelance.libelleTRelance);
		swtLRelanceLoc.setEtat(ihmLRelance.etat);
		swtLRelanceLoc.setNomTiers(ihmLRelance.nomTiers);
		swtLRelanceLoc.setVersionObj(ihmLRelance.versionObj);
		return swtLRelanceLoc;
	}

	public Integer getIdLRelance() {
		return this.id;
	}

	public void setIdLRelance(Integer idLRelance) {
		firePropertyChange("idLRelance", this.id, this.id = idLRelance);
	}


	public String getCodeRelance() {
		return codeRelance;
	}


	public void setCodeRelance(String codeRelance) {
		firePropertyChange("codeRelance", this.codeRelance, this.codeRelance = codeRelance);
	}


	public Integer getIdRelance() {
		return idRelance;
	}


	public void setIdRelance(Integer idRelance) {
		firePropertyChange("idRelance", this.idRelance, this.idRelance = idRelance);
	}


	public String getCodeDocument() {
		return codeDocument;
	}


	public void setCodeDocument(String codeDocument) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = codeDocument);
	}


	public Date getDateEcheance() {
		return dateEcheance;
	}


	public void setDateEcheance(Date dateEcheance) {
		firePropertyChange("dateEcheance", this.dateEcheance, this.dateEcheance = dateEcheance);
	}


	public String getCodeTiers() {
		return codeTiers;
	}


	public void setCodeTiers(String codeTiers) {
		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = codeTiers);
	}


	public String getTypeDocument() {
		return typeDocument;
	}


	public void setTypeDocument(String typeDocument) {
		firePropertyChange("typeDocument", this.typeDocument, this.typeDocument = typeDocument);
	}


	public BigDecimal getNetTTC() {
		return netTTC;
	}


	public void setNetTTC(BigDecimal netTTC) {
		firePropertyChange("netTTC", this.netTTC, this.netTTC = netTTC);
	}


	public BigDecimal getResteARegler() {
		return resteARegler;
	}


	public void setResteARegler(BigDecimal resteARegler) {
		firePropertyChange("resteARegler", this.resteARegler, this.resteARegler = resteARegler);
	}


	public Integer getIdTRelance() {
		return idTRelance;
	}


	public void setIdTRelance(Integer idTRelance) {
		firePropertyChange("idTRelance", this.idTRelance, this.idTRelance = idTRelance);
	}


	public String getCodeTRelance() {
		return codeTRelance;
	}


	public void setCodeTRelance(String codeTRelance) {
		firePropertyChange("codeTRelance", this.codeTRelance, this.codeTRelance = codeTRelance);
	}


	public String getLibelleTRelance() {
		return libelleTRelance;
	}


	public void setLibelleTRelance(String libelleTRelance) {
		firePropertyChange("libelleTRelance", this.libelleTRelance, this.libelleTRelance = libelleTRelance);
	}


	public Integer getEtat() {
		return etat;
	}


	public void setEtat(Integer etat) {
		firePropertyChange("etat", this.etat, this.etat = etat);
	}


	public Date getDateRelance() {
		return dateRelance;
	}
	public void setDateRelance(Date dateRelance) {
		firePropertyChange("dateRelance", this.dateRelance, this.dateRelance = dateRelance);
	}

	public Boolean getAccepte() {
		return accepte;
	}

	public void setAccepte(Boolean accepte) {
		firePropertyChange("accepte", this.accepte, this.accepte = accepte);
	}


	public String getNomTiers() {
		return nomTiers;
	}

	public void setNomTiers(String nomTiers) {
		firePropertyChange("nomTiers", this.nomTiers, this.nomTiers = nomTiers);
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accepte == null) ? 0 : accepte.hashCode());
		result = prime * result
				+ ((codeDocument == null) ? 0 : codeDocument.hashCode());
		result = prime * result
				+ ((codeRelance == null) ? 0 : codeRelance.hashCode());
		result = prime * result
				+ ((codeTRelance == null) ? 0 : codeTRelance.hashCode());
		result = prime * result
				+ ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result
				+ ((dateEcheance == null) ? 0 : dateEcheance.hashCode());
		result = prime * result
				+ ((dateRelance == null) ? 0 : dateRelance.hashCode());
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idRelance == null) ? 0 : idRelance.hashCode());
		result = prime * result
				+ ((idTRelance == null) ? 0 : idTRelance.hashCode());
		result = prime * result
				+ ((libelleTRelance == null) ? 0 : libelleTRelance.hashCode());
		result = prime * result + ((netTTC == null) ? 0 : netTTC.hashCode());
		result = prime * result
				+ ((nomTiers == null) ? 0 : nomTiers.hashCode());
		result = prime * result
				+ ((resteARegler == null) ? 0 : resteARegler.hashCode());
		result = prime * result
				+ ((typeDocument == null) ? 0 : typeDocument.hashCode());
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
		TaLRelanceDTO other = (TaLRelanceDTO) obj;
		if (accepte == null) {
			if (other.accepte != null)
				return false;
		} else if (!accepte.equals(other.accepte))
			return false;
		if (codeDocument == null) {
			if (other.codeDocument != null)
				return false;
		} else if (!codeDocument.equals(other.codeDocument))
			return false;
		if (codeRelance == null) {
			if (other.codeRelance != null)
				return false;
		} else if (!codeRelance.equals(other.codeRelance))
			return false;
		if (codeTRelance == null) {
			if (other.codeTRelance != null)
				return false;
		} else if (!codeTRelance.equals(other.codeTRelance))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
			return false;
		if (dateEcheance == null) {
			if (other.dateEcheance != null)
				return false;
		} else if (!dateEcheance.equals(other.dateEcheance))
			return false;
		if (dateRelance == null) {
			if (other.dateRelance != null)
				return false;
		} else if (!dateRelance.equals(other.dateRelance))
			return false;
		if (etat == null) {
			if (other.etat != null)
				return false;
		} else if (!etat.equals(other.etat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idRelance == null) {
			if (other.idRelance != null)
				return false;
		} else if (!idRelance.equals(other.idRelance))
			return false;
		if (idTRelance == null) {
			if (other.idTRelance != null)
				return false;
		} else if (!idTRelance.equals(other.idTRelance))
			return false;
		if (libelleTRelance == null) {
			if (other.libelleTRelance != null)
				return false;
		} else if (!libelleTRelance.equals(other.libelleTRelance))
			return false;
		if (netTTC == null) {
			if (other.netTTC != null)
				return false;
		} else if (!netTTC.equals(other.netTTC))
			return false;
		if (nomTiers == null) {
			if (other.nomTiers != null)
				return false;
		} else if (!nomTiers.equals(other.nomTiers))
			return false;
		if (resteARegler == null) {
			if (other.resteARegler != null)
				return false;
		} else if (!resteARegler.equals(other.resteARegler))
			return false;
		if (typeDocument == null) {
			if (other.typeDocument != null)
				return false;
		} else if (!typeDocument.equals(other.typeDocument))
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

}
