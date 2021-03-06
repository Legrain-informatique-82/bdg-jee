package fr.legrain.gestCom.Module_Tiers;

import fr.legrain.lib.data.ModelObject;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTTAdr generated by hbm2java
 */
public class SWTTAdr extends ModelObject {

	private Integer idTAdr;
	private String codeTAdr;
	private String liblTAdr;

	public SWTTAdr() {
	}

	public void setSWTTAdr(SWTTAdr swtTAdr) {
		this.idTAdr = swtTAdr.idTAdr;
		this.codeTAdr = swtTAdr.codeTAdr;
		this.liblTAdr = swtTAdr.liblTAdr;
	}

	public Integer getIdTAdr() {
		return this.idTAdr;
	}

	public void setIdTAdr(Integer ID_T_ADR) {
		firePropertyChange("idTAdr", this.idTAdr, this.idTAdr = ID_T_ADR);
	}

	public String getCodeTAdr() {
		return this.codeTAdr;
	}

	public void setCodeTAdr(String CODE_T_ADR) {
		firePropertyChange("codeTAdr", this.codeTAdr, this.codeTAdr = CODE_T_ADR);
	}

	public String getLiblTAdr() {
		return this.liblTAdr;
	}

	public void setLiblTAdr(String LIBL_T_ADR) {
		firePropertyChange("liblTAdr", this.liblTAdr, this.liblTAdr = LIBL_T_ADR);
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SWTTAdr))
			return false;
		SWTTAdr castOther = (SWTTAdr) other;

		return ((this.getIdTAdr() == castOther.getIdTAdr()) || (this
				.getIdTAdr() != null
				&& castOther.getIdTAdr() != null && this.getIdTAdr()
				.equals(castOther.getIdTAdr())))
				&& ((this.getCodeTAdr() == castOther.getCodeTAdr()) || (this
						.getCodeTAdr() != null
						&& castOther.getCodeTAdr() != null && this
						.getCodeTAdr().equals(castOther.getCodeTAdr())))
				&& ((this.getLiblTAdr() == castOther.getLiblTAdr()) || (this
						.getLiblTAdr() != null
						&& castOther.getLiblTAdr() != null && this
						.getLiblTAdr().equals(castOther.getLiblTAdr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdTAdr() == null ? 0 : this.getIdTAdr().hashCode());
		result = 37
				* result
				+ (getCodeTAdr() == null ? 0 : this.getCodeTAdr()
						.hashCode());
		result = 37
				* result
				+ (getLiblTAdr() == null ? 0 : this.getLiblTAdr()
						.hashCode());
		return result;
	}

	
	public static SWTTAdr copy(SWTTAdr swtTypeAdr){
		SWTTAdr swtTypeAdrLoc = new SWTTAdr();
		swtTypeAdrLoc.setIdTAdr(swtTypeAdr.getIdTAdr());                //1
		swtTypeAdrLoc.setCodeTAdr(swtTypeAdr.getCodeTAdr());        //2
		swtTypeAdrLoc.setLiblTAdr(swtTypeAdr.getLiblTAdr());            //3
		return swtTypeAdrLoc;
	}
}
