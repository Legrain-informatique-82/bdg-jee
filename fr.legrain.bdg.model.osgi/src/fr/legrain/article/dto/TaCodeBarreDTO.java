package fr.legrain.article.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.article.model.TaCodeBarre;
import fr.legrain.article.model.TaLot;
import fr.legrain.article.model.TaTypeCodeBarre;
import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;



// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * TaBaremeDTO generated by hbm2java
 */
public class TaCodeBarreDTO extends ModelObject implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 656467988688867937L;

	
	
	
	
	private Integer id;
	private String codeBarre;
	private String codeTypeCodeBarre;
	private TaTypeCodeBarre typeCodeBarre;
	//topEffacable
	
	private Integer versionObj;
	
	public TaCodeBarreDTO() {
	}
	
	
	public static TaCodeBarreDTO copy(TaCodeBarreDTO taCodeBarre){
		TaCodeBarreDTO taEntrepotLoc = new TaCodeBarreDTO();
		taEntrepotLoc.setCodeBarre(taCodeBarre.codeBarre);
		taEntrepotLoc.setId(taCodeBarre.id);
		
		return taEntrepotLoc;
	}
	
	public void setTaCodeBarreDTO(TaCodeBarreDTO taCodeBarre){
		this.id = taCodeBarre.id;
		this.codeBarre = taCodeBarre.codeBarre;
		this.typeCodeBarre = taCodeBarre.typeCodeBarre;
	}
	
	
	public TaCodeBarreDTO(Integer id, String codeBarre,
			Boolean topEffacable) {
		this.id = id;
		this.codeBarre = codeBarre;
		
	}
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}
	
	@NotNull
	@Size(min=0, max=255)
	@LgrHibernateValidated(champBd = "code_barre",table = "ta_code_barre", champEntite="codeBarre", clazz = TaCodeBarreDTO.class)
	public String getCodeBarre() {
		return this.codeBarre;
	}
	
	public void setCodeBarre(String codeBarre) {
		firePropertyChange("codeBarre", this.codeBarre, this.codeBarre = codeBarre);
	}
	
	public TaTypeCodeBarre getTypeCodeBarre() {
		return typeCodeBarre;
	}


	public void setTypeCodeBarre(TaTypeCodeBarre typeCodeBarre) {
		this.typeCodeBarre = typeCodeBarre;
	}


	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}


	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaCodeBarreDTO other = (TaCodeBarreDTO) obj;
		if (codeBarre == null) {
			if (other.codeBarre != null)
				return false;
		} else if (!codeBarre.equals(other.codeBarre))
			return false;
		
		if (id == null) 
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (typeCodeBarre == null) 
		{
			if (other.typeCodeBarre != null)
				return false;
		} else if (!typeCodeBarre.equals(other.typeCodeBarre))
			return false;
		
		return true;
	}


	public String getCodeTypeCodeBarre() {
		return codeTypeCodeBarre;
	}


	public void setCodeTypeCodeBarre(String codeTypeCodeBarre) {
		this.codeTypeCodeBarre = codeTypeCodeBarre;
	}





}
