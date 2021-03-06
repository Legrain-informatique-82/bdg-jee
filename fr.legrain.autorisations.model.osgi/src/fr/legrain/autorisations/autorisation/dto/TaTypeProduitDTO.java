package fr.legrain.autorisations.autorisation.dto;

import fr.legrain.autorisations.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTAdresse generated by hbm2java
 */
public class TaTypeProduitDTO extends ModelObject implements java.io.Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1637347593367768774L;
	
	
	private Integer id;
	private String code;
	private String libelle;
	
	private Integer versionObj;

	public TaTypeProduitDTO() {
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}

	@LgrHibernateValidated(champBd = "code",table = "ta_type_produit", champEntite="code", clazz = TaTypeProduitDTO.class)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		firePropertyChange("code", this.code, this.code = code);
	}
	

	@LgrHibernateValidated(champBd = "libelle",table = "ta_type_produit", champEntite="libelle", clazz = TaTypeProduitDTO.class)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		firePropertyChange("libelle", this.libelle, this.libelle = libelle);
	}

	
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		TaTypeProduitDTO other = (TaTypeProduitDTO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (versionObj == null) {
			if (other.versionObj != null)
				return false;
		} else if (!versionObj.equals(other.versionObj))
			return false;
		return true;
	}


	
	
}
