package fr.legrain.article.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;

import fr.legrain.bdg.model.ModelObject;


/**
 * SWTPrix generated by hbm2java
 */
public class TaCatalogueWebDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = -541282207118171033L;
	
	private Integer id;
	private String urlRewritingCatalogueWeb;
	private String resumeCatWeb;
	private String descriptionLongueCatWeb;
	private Boolean nouveauteCatalogueWeb;
	private Boolean exportationCatalogueWeb;
	private Boolean expediableCatalogueWeb;
	private Boolean specialCatalogueWeb;
	private BigDecimal promotionCatalogueWeb;
	private BigDecimal promotionU2CatalogueWeb;
	
	private String libelleCatalogue;
	private Boolean nonDisponibleCatalogueWeb;
	private BigDecimal fraisPortAdditionnel;
	
	private Integer idPrix;
	private BigDecimal prixPrix;
	private BigDecimal prixttcPrix;
	
	private Integer idCategorie;
	private String codeCategorie;
	
	private Integer versionObj;


	public Integer getVersionObj() {
		return versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public TaCatalogueWebDTO() {
	}
	
	public TaCatalogueWebDTO(int idCatalogueWeb, String urlRewritingCatalogueWeb,
			String descriptionLongueCatWeb, Boolean nouveauteCatalogueWeb, Boolean exportationCatalogueWeb,
			Boolean expediableCatalogueWeb, Boolean specialCatalogueWeb, BigDecimal promotionCatalogueWeb, BigDecimal promotionU2CatalogueWeb) {
		super();
		this.id = idCatalogueWeb;
		this.urlRewritingCatalogueWeb = urlRewritingCatalogueWeb;
		this.descriptionLongueCatWeb = descriptionLongueCatWeb;
		this.nouveauteCatalogueWeb = nouveauteCatalogueWeb;
		this.promotionCatalogueWeb = promotionCatalogueWeb;
		this.promotionU2CatalogueWeb = promotionU2CatalogueWeb;
		this.exportationCatalogueWeb = exportationCatalogueWeb;
		this.expediableCatalogueWeb = expediableCatalogueWeb;
		this.specialCatalogueWeb = specialCatalogueWeb;
	}

	public void setSWTImageArticle(TaCatalogueWebDTO swtCatalogueWeb) {
		this.id = swtCatalogueWeb.id;
		this.urlRewritingCatalogueWeb = swtCatalogueWeb.urlRewritingCatalogueWeb;
		this.descriptionLongueCatWeb = swtCatalogueWeb.descriptionLongueCatWeb;
		this.nouveauteCatalogueWeb = swtCatalogueWeb.nouveauteCatalogueWeb;
		this.promotionCatalogueWeb = swtCatalogueWeb.promotionCatalogueWeb;
		this.promotionU2CatalogueWeb = swtCatalogueWeb.promotionU2CatalogueWeb;
		this.exportationCatalogueWeb = swtCatalogueWeb.exportationCatalogueWeb;
		this.expediableCatalogueWeb = swtCatalogueWeb.expediableCatalogueWeb;
		this.specialCatalogueWeb = swtCatalogueWeb.specialCatalogueWeb;
	}
	
	public static TaCatalogueWebDTO copy(TaCatalogueWebDTO swtCatalogueWeb){
		TaCatalogueWebDTO swtCatalogueWebLoc = new TaCatalogueWebDTO();
		swtCatalogueWebLoc.setId(swtCatalogueWeb.id);
		swtCatalogueWebLoc.setUrlRewritingCatalogueWeb(swtCatalogueWeb.urlRewritingCatalogueWeb);
		swtCatalogueWebLoc.setDescriptionLongueCatWeb(swtCatalogueWeb.descriptionLongueCatWeb);
		swtCatalogueWebLoc.setNouveauteCatalogueWeb(swtCatalogueWeb.nouveauteCatalogueWeb);
		swtCatalogueWebLoc.setPromotionCatalogueWeb(swtCatalogueWeb.promotionCatalogueWeb);
		swtCatalogueWebLoc.setPromotionU2CatalogueWeb(swtCatalogueWeb.promotionU2CatalogueWeb);
		swtCatalogueWebLoc.setExportationCatalogueWeb(swtCatalogueWeb.exportationCatalogueWeb);
		swtCatalogueWebLoc.setExpediableCatalogueWeb(swtCatalogueWeb.expediableCatalogueWeb);
		swtCatalogueWebLoc.setSpecialCatalogueWeb(swtCatalogueWeb.specialCatalogueWeb);
		return swtCatalogueWebLoc;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer idImageArticle) {
		firePropertyChange("id", this.id, this.id = idImageArticle);
	}

	public String getUrlRewritingCatalogueWeb() {
		return urlRewritingCatalogueWeb;
	}

	public void setUrlRewritingCatalogueWeb(String cheminImageArticle) {
		firePropertyChange("cheminImageArticle", this.urlRewritingCatalogueWeb, this.urlRewritingCatalogueWeb = cheminImageArticle);
	}

	public String getDescriptionLongueCatWeb() {
		return descriptionLongueCatWeb;
	}

	public void setDescriptionLongueCatWeb(String nomImageArticle) {
		firePropertyChange("nomImageArticle", this.descriptionLongueCatWeb, this.descriptionLongueCatWeb = nomImageArticle);
	}

	public Boolean getNouveauteCatalogueWeb() {
		return nouveauteCatalogueWeb;
	}

	public void setNouveauteCatalogueWeb(Boolean defautImageArticle) {
		firePropertyChange("defautImageArticle", this.nouveauteCatalogueWeb, this.nouveauteCatalogueWeb = defautImageArticle);
	}
	
	public Boolean getExportationCatalogueWeb() {
		return exportationCatalogueWeb;
	}

	public void setExportationCatalogueWeb(Boolean exportationCatalogueWeb) {
		firePropertyChange("exportationCatalogueWeb", this.exportationCatalogueWeb, this.exportationCatalogueWeb = exportationCatalogueWeb);
	}
	
	public BigDecimal getPromotionCatalogueWeb() {
		return promotionCatalogueWeb;
	}

	public void setPromotionCatalogueWeb(BigDecimal promotionCatalogueWeb) {
		firePropertyChange("promotionCatalogueWeb", this.promotionCatalogueWeb, this.promotionCatalogueWeb = promotionCatalogueWeb);
	}
	
	public BigDecimal getPromotionU2CatalogueWeb() {
		return promotionU2CatalogueWeb;
	}

	public void setPromotionU2CatalogueWeb(BigDecimal promotionU2CatalogueWeb) {
		firePropertyChange("promotionU2CatalogueWeb", this.promotionU2CatalogueWeb, this.promotionU2CatalogueWeb = promotionU2CatalogueWeb);
	}
	
	public Boolean getExpediableCatalogueWeb() {
		return expediableCatalogueWeb;
	}

	public void setExpediableCatalogueWeb(Boolean expediableCatalogueWeb) {
		firePropertyChange("expediableCatalogueWeb", this.expediableCatalogueWeb, this.expediableCatalogueWeb = expediableCatalogueWeb);
	}

	public Boolean getSpecialCatalogueWeb() {
		return specialCatalogueWeb;
	}

	public void setSpecialCatalogueWeb(Boolean specialCatalogueWeb) {
		firePropertyChange("specialCatalogueWeb", this.specialCatalogueWeb, this.specialCatalogueWeb = specialCatalogueWeb);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((urlRewritingCatalogueWeb == null) ? 0 : urlRewritingCatalogueWeb
						.hashCode());
		result = prime
				* result
				+ ((nouveauteCatalogueWeb == null) ? 0 : nouveauteCatalogueWeb
						.hashCode());
		result = prime * result
				+ ((descriptionLongueCatWeb == null) ? 0 : descriptionLongueCatWeb.hashCode());
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
		TaCatalogueWebDTO other = (TaCatalogueWebDTO) obj;
		if (urlRewritingCatalogueWeb == null) {
			if (other.urlRewritingCatalogueWeb != null)
				return false;
		} else if (!urlRewritingCatalogueWeb.equals(other.urlRewritingCatalogueWeb))
			return false;
		if (nouveauteCatalogueWeb == null) {
			if (other.nouveauteCatalogueWeb != null)
				return false;
		} else if (!nouveauteCatalogueWeb.equals(other.nouveauteCatalogueWeb))
			return false;
		if (descriptionLongueCatWeb == null) {
			if (other.descriptionLongueCatWeb != null)
				return false;
		} else if (!descriptionLongueCatWeb.equals(other.descriptionLongueCatWeb))
			return false;
		return true;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public Integer getIdPrix() {
		return idPrix;
	}

	public void setIdPrix(Integer idPrix) {
		this.idPrix = idPrix;
	}

	public BigDecimal getPrixPrix() {
		return prixPrix;
	}

	public void setPrixPrix(BigDecimal prixPrix) {
		this.prixPrix = prixPrix;
	}

	public BigDecimal getPrixttcPrix() {
		return prixttcPrix;
	}

	public void setPrixttcPrix(BigDecimal prixttcPrix) {
		this.prixttcPrix = prixttcPrix;
	}

	public String getLibelleCatalogue() {
		return libelleCatalogue;
	}

	public void setLibelleCatalogue(String libelleCatalogue) {
		this.libelleCatalogue = libelleCatalogue;
	}

	public BigDecimal getFraisPortAdditionnel() {
		return fraisPortAdditionnel;
	}

	public void setFraisPortAdditionnel(BigDecimal fraisPortAdditionnel) {
		this.fraisPortAdditionnel = fraisPortAdditionnel;
	}

	public Boolean getNonDisponibleCatalogueWeb() {
		return nonDisponibleCatalogueWeb;
	}

	public void setNonDisponibleCatalogueWeb(Boolean nonDisponibleCatalogueWeb) {
		this.nonDisponibleCatalogueWeb = nonDisponibleCatalogueWeb;
	}

	public String getResumeCatWeb() {
		return resumeCatWeb;
	}

	public void setResumeCatWeb(String resumeCatWeb) {
		this.resumeCatWeb = resumeCatWeb;
	}

}
