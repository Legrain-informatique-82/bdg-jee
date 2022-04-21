package fr.legrain.article.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.article.model.TaTReception;
import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTTReception generated by hbm2java
 */
public class TaTReceptionDTO extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6872976469850948430L;
	private Integer id;
	private String codeTReception;
	private String liblTReception;
	private Integer versionObj;

	public TaTReceptionDTO() {
	}
	
	public TaTReceptionDTO(Integer id,String codeTReception,String liblTReception) {
		this.id = id;
		this.codeTReception = codeTReception;
		this.liblTReception = liblTReception;
	}

	public void setTaTReceptionDTO(TaTReceptionDTO taTReceptionDTO) {
		this.id = taTReceptionDTO.id;
		this.codeTReception = taTReceptionDTO.codeTReception;
		this.liblTReception = taTReceptionDTO.liblTReception;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_t_reception",table = "ta_t_reception", champEntite="codeTReception", clazz = TaTReceptionDTO.class)
	public String getCodeTReception() {
		return this.codeTReception;
	}

	public void setCodeTReception(String codeTReception) {
		firePropertyChange("codeTReception", this.codeTReception, this.codeTReception = codeTReception);
	}

	@LgrHibernateValidated(champBd = "libl_t_reception",table = "ta_t_reception", champEntite="liblTReception", clazz = TaTReceptionDTO.class)
	public String getLiblTReception() {
		return this.liblTReception;
	}

	public void setLiblTReception(String liblTReception) {
		firePropertyChange("liblTReception", this.liblTReception, this.liblTReception = liblTReception);
	}
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaTReceptionDTO))
			return false;
		TaTReceptionDTO castOther = (TaTReceptionDTO) other;

		return ((this.getId() == castOther.getId()) || (this
				.getId() != null
				&& castOther.getId() != null && this.getId()
				.equals(castOther.getId())))
				&& ((this.getCodeTReception() == castOther.getCodeTReception()) || (this
						.getCodeTReception() != null
						&& castOther.getCodeTReception() != null && this
						.getCodeTReception().equals(castOther.getCodeTReception())))
				&& ((this.getLiblTReception() == castOther.getLiblTReception()) || (this
						.getLiblTReception() != null
						&& castOther.getLiblTReception() != null && this
						.getLiblTReception().equals(castOther.getLiblTReception())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getCodeTReception() == null ? 0 : this.getCodeTReception()
						.hashCode());
		result = 37
				* result
				+ (getLiblTReception() == null ? 0 : this.getLiblTReception()
						.hashCode());
		return result;
	}

	
	public static TaTReceptionDTO copy(TaTReceptionDTO taTReceptionDTO){
		TaTReceptionDTO taTReceptionDTOLoc = new TaTReceptionDTO();
		taTReceptionDTOLoc.setId(taTReceptionDTO.getId());                //1
		taTReceptionDTOLoc.setCodeTReception(taTReceptionDTO.getCodeTReception());        //2
		taTReceptionDTOLoc.setLiblTReception(taTReceptionDTO.getLiblTReception());            //3
		return taTReceptionDTOLoc;
	}
}
