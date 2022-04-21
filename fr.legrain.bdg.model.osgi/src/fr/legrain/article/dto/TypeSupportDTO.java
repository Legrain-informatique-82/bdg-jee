package fr.legrain.article.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import fr.legrain.article.model.TaArticle;
import fr.legrain.lib.data.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * SWTPrix generated by hbm2java
 */
public class TypeSupportDTO extends ModelObject implements java.io.Serializable {  

	/**
	 * 
	 */
	private static final long serialVersionUID = -5301345371995008701L;
	private Integer id;
	private Integer idTSupport;	
	private String codeTSupport;
	
	private Integer idTAbonnement;	
	private String codeTAbonnement;
	
	private Integer idArticle;	
	private String codeArticle;	

	private Integer idArticleAbonnement;	
	private String codeArticleAbonnement;
	public TypeSupportDTO() {
	}
	



	public void setSWTTypeArticle(TypeSupportDTO swtTSupport) {
		this.idTSupport = swtTSupport.idTSupport;
		this.codeTSupport = swtTSupport.codeTSupport;
		this.idTAbonnement = swtTSupport.idTAbonnement;
		this.codeTAbonnement = swtTSupport.codeTAbonnement;
		this.idArticle = swtTSupport.idArticle;
		this.codeArticle = swtTSupport.codeArticle;
		this.idArticleAbonnement = swtTSupport.idArticleAbonnement;
		this.codeArticleAbonnement = swtTSupport.codeArticleAbonnement;		
	}
	
	public static TypeSupportDTO copy(TypeSupportDTO swtTSupport){
		TypeSupportDTO swtTSupportLoc = new TypeSupportDTO();
		swtTSupportLoc.setIdTSupport(swtTSupport.idTSupport);
		swtTSupportLoc.setCodeTSupport(swtTSupport.codeTSupport);
		swtTSupportLoc.setIdTAbonnement(swtTSupport.idTAbonnement);
		swtTSupportLoc.setCodeTAbonnement(swtTSupport.codeTAbonnement);
		swtTSupportLoc.setIdArticle(swtTSupport.idArticle);
		swtTSupportLoc.setCodeArticle(swtTSupport.codeArticle);
		swtTSupportLoc.setIdArticleAbonnement(swtTSupport.idArticleAbonnement);
		swtTSupportLoc.setCodeArticleAbonnement(swtTSupport.codeArticleAbonnement);		
		return swtTSupportLoc;
	}
	
	public Integer getIdTSupport() {
		return idTSupport;
	}

	public void setIdTSupport(Integer idTSupport) {
		firePropertyChange("idTSupport", this.idTSupport, this.idTSupport = idTSupport);
	}

	public String getCodeTSupport() {
		return codeTSupport;
	}

	public void setCodeTSupport(String codeTSupport) {
		firePropertyChange("codeTSupport", this.codeTSupport, this.codeTSupport = codeTSupport);
	}


	public Integer getIdTAbonnement() {
		return idTAbonnement;
	}


	public void setIdTAbonnement(Integer idTAbonnement) {
		firePropertyChange("idTAbonnement", this.idTAbonnement, this.idTAbonnement = idTAbonnement);
		}


	public String getCodeTAbonnement() {
		return codeTAbonnement;
	}


	public void setCodeTAbonnement(String codeTAbonnement) {
		firePropertyChange("codeTAbonnement", this.codeTAbonnement, this.codeTAbonnement = codeTAbonnement);
	}


	public Integer getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(Integer idArticle) {
		firePropertyChange("idArticle", this.idArticle, this.idArticle = idArticle);
	}


	@LgrHibernateValidated(champBd = "code_article",table = "ta_article", champEntite="codeArticle", clazz = TypeSupportDTO.class)
	public String getCodeArticle() {
		return codeArticle;
	}


	public void setCodeArticle(String codeArticle) {
		firePropertyChange("codeArticle", this.codeArticle, this.codeArticle = codeArticle);
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer idRTSupport) {
		firePropertyChange("idRTSupport", this.id, this.id = idRTSupport);
	}

	public Integer getIdArticleAbonnement() {
		return idArticleAbonnement;
	}




	public void setIdArticleAbonnement(Integer idArticleAbonnement) {
		firePropertyChange("idArticleAbonnement", this.idArticleAbonnement, this.idArticleAbonnement = idArticleAbonnement);
	}




	public String getCodeArticleAbonnement() {
		return codeArticleAbonnement;
	}




	public void setCodeArticleAbonnement(String codeArticleAbonnement) {
		firePropertyChange("codeArticleAbonnement", this.codeArticleAbonnement, this.codeArticleAbonnement = codeArticleAbonnement);
	}




	public TypeSupportDTO(Integer idRTSupport, Integer idTSupport,
			String codeTSupport, Integer idTAbonnement, String codeTAbonnement,
			Integer idArticle, String codeArticle, Integer idArticleAbonnement,
			String codeArticleAbonnement) {
		super();
		this.id = idRTSupport;
		this.idTSupport = idTSupport;
		this.codeTSupport = codeTSupport;
		this.idTAbonnement = idTAbonnement;
		this.codeTAbonnement = codeTAbonnement;
		this.idArticle = idArticle;
		this.codeArticle = codeArticle;
		this.idArticleAbonnement = idArticleAbonnement;
		this.codeArticleAbonnement = codeArticleAbonnement;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeArticle == null) ? 0 : codeArticle.hashCode());
		result = prime
				* result
				+ ((codeArticleAbonnement == null) ? 0 : codeArticleAbonnement
						.hashCode());
		result = prime * result
				+ ((codeTAbonnement == null) ? 0 : codeTAbonnement.hashCode());
		result = prime * result
				+ ((codeTSupport == null) ? 0 : codeTSupport.hashCode());
		result = prime * result
				+ ((idArticle == null) ? 0 : idArticle.hashCode());
		result = prime
				* result
				+ ((idArticleAbonnement == null) ? 0 : idArticleAbonnement
						.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idTAbonnement == null) ? 0 : idTAbonnement.hashCode());
		result = prime * result
				+ ((idTSupport == null) ? 0 : idTSupport.hashCode());
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
		TypeSupportDTO other = (TypeSupportDTO) obj;
		if (codeArticle == null) {
			if (other.codeArticle != null)
				return false;
		} else if (!codeArticle.equals(other.codeArticle))
			return false;
		if (codeArticleAbonnement == null) {
			if (other.codeArticleAbonnement != null)
				return false;
		} else if (!codeArticleAbonnement.equals(other.codeArticleAbonnement))
			return false;
		if (codeTAbonnement == null) {
			if (other.codeTAbonnement != null)
				return false;
		} else if (!codeTAbonnement.equals(other.codeTAbonnement))
			return false;
		if (codeTSupport == null) {
			if (other.codeTSupport != null)
				return false;
		} else if (!codeTSupport.equals(other.codeTSupport))
			return false;
		if (idArticle == null) {
			if (other.idArticle != null)
				return false;
		} else if (!idArticle.equals(other.idArticle))
			return false;
		if (idArticleAbonnement == null) {
			if (other.idArticleAbonnement != null)
				return false;
		} else if (!idArticleAbonnement.equals(other.idArticleAbonnement))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTAbonnement == null) {
			if (other.idTAbonnement != null)
				return false;
		} else if (!idTAbonnement.equals(other.idTAbonnement))
			return false;
		if (idTSupport == null) {
			if (other.idTSupport != null)
				return false;
		} else if (!idTSupport.equals(other.idTSupport))
			return false;
		return true;
	}




}
