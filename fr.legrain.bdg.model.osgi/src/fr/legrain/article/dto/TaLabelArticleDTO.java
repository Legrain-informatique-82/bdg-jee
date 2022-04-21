package fr.legrain.article.dto;

import fr.legrain.bdg.model.ModelObject;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9


/**
 * SWTPrix generated by hbm2java
 */
public class TaLabelArticleDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 927394561532736639L;
	
	private Integer id;	
	private String codeLabelArticle;
	private String libelleLabelArticle;
	private String desciptionLabelArticle;
	private String cheminImageLabelArticle;
	private String nomImageLabelArticle;
	private byte[] blobLogo;
	
	private Integer versionObj;

	public Integer getVersionObj() {
		return versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public TaLabelArticleDTO() {
	}
	
	public TaLabelArticleDTO(int idLabelArticle, String codeLabelArticle,
			String libelleLabelArticle, String desciptionLabelArticle,
			String cheminImageLabelArticle, String nomImageLabelArticle) {
		super();
		this.id = idLabelArticle;
		this.codeLabelArticle = codeLabelArticle;
		this.libelleLabelArticle = libelleLabelArticle;
		this.desciptionLabelArticle = desciptionLabelArticle;
		this.cheminImageLabelArticle = cheminImageLabelArticle;
		this.nomImageLabelArticle = nomImageLabelArticle;
	}

	public void setSWTLabelArticle(TaLabelArticleDTO swtLabelArticle) {
		this.id = swtLabelArticle.id;
		this.codeLabelArticle = swtLabelArticle.codeLabelArticle;
		this.libelleLabelArticle = swtLabelArticle.libelleLabelArticle;
		this.desciptionLabelArticle = swtLabelArticle.desciptionLabelArticle;
		this.cheminImageLabelArticle = swtLabelArticle.cheminImageLabelArticle;
		this.nomImageLabelArticle = swtLabelArticle.nomImageLabelArticle;
	}
	
	public static TaLabelArticleDTO copy(TaLabelArticleDTO swtLabelArticle){
		TaLabelArticleDTO swtLabelArticleLoc = new TaLabelArticleDTO();
		swtLabelArticleLoc.setId(swtLabelArticle.id);
		swtLabelArticleLoc.setCodeLabelArticle(swtLabelArticle.codeLabelArticle);
		swtLabelArticleLoc.setLibelleLabelArticle(swtLabelArticle.libelleLabelArticle);
		swtLabelArticleLoc.setDesciptionLabelArticle(swtLabelArticle.desciptionLabelArticle);
		swtLabelArticleLoc.setCheminImageLabelArticle(swtLabelArticle.cheminImageLabelArticle);
		swtLabelArticleLoc.setNomImageLabelArticle(swtLabelArticle.nomImageLabelArticle);
		return swtLabelArticleLoc;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer idLabelArticle) {
		firePropertyChange("id", this.id, this.id = idLabelArticle);
	}

	public String getCodeLabelArticle() {
		return codeLabelArticle;
	}

	public void setCodeLabelArticle(String codeLabelArticle) {
		firePropertyChange("codeLabelArticle", this.codeLabelArticle, this.codeLabelArticle = codeLabelArticle);
	}

	public String getLibelleLabelArticle() {
		return libelleLabelArticle;
	}

	public void setLibelleLabelArticle(String libelleLabelArticle) {
		firePropertyChange("libelleLabelArticle", this.libelleLabelArticle, this.libelleLabelArticle = libelleLabelArticle);
	}

	public String getDesciptionLabelArticle() {
		return desciptionLabelArticle;
	}

	public void setDesciptionLabelArticle(String desciptionLabelArticle) {
		firePropertyChange("desciptionLabelArticle", this.desciptionLabelArticle, this.desciptionLabelArticle = desciptionLabelArticle);
	}
	
	public String getCheminImageLabelArticle() {
		return cheminImageLabelArticle;
	}

	public void setCheminImageLabelArticle(String cheminImageLabelArticle) {
		firePropertyChange("cheminImageLabelArticle", this.cheminImageLabelArticle, this.cheminImageLabelArticle = cheminImageLabelArticle);
	}

	public String getNomImageLabelArticle() {
		return nomImageLabelArticle;
	}

	public void setNomImageLabelArticle(String nomImageLabelArticle) {
		firePropertyChange("nomImageLabelArticle", this.nomImageLabelArticle, this.nomImageLabelArticle = nomImageLabelArticle);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeLabelArticle == null) ? 0 : codeLabelArticle
						.hashCode());
		result = prime
				* result
				+ ((desciptionLabelArticle == null) ? 0 : desciptionLabelArticle
						.hashCode());
		result = prime * result
				+ ((libelleLabelArticle == null) ? 0 : libelleLabelArticle.hashCode());
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
		TaLabelArticleDTO other = (TaLabelArticleDTO) obj;
		if (codeLabelArticle == null) {
			if (other.codeLabelArticle != null)
				return false;
		} else if (!codeLabelArticle.equals(other.codeLabelArticle))
			return false;
		if (desciptionLabelArticle == null) {
			if (other.desciptionLabelArticle != null)
				return false;
		} else if (!desciptionLabelArticle.equals(other.desciptionLabelArticle))
			return false;
		if (libelleLabelArticle == null) {
			if (other.libelleLabelArticle != null)
				return false;
		} else if (!libelleLabelArticle.equals(other.libelleLabelArticle))
			return false;
		return true;
	}

	public byte[] getBlobLogo() {
		return blobLogo;
	}

	public void setBlobLogo(byte[] blobLogo) {
		this.blobLogo = blobLogo;
	}

}
