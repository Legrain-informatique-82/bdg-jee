package fr.legrain.tiers.dto;

import javax.validation.constraints.Size;

import com.ibm.icu.math.BigDecimal;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTAdresse generated by hbm2java
 */
public class TaAdresseDTO extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2371159789946188318L;
	private Integer id;
	private String adresse1Adresse;
	private String adresse2Adresse;
	private String adresse3Adresse;
	private String codepostalAdresse;
	private String villeAdresse;
	private String paysAdresse;
	private String codeTAdr;
	private Integer idTAdr;
	private Integer idTiers;
	private Integer ordre;
	private String livraison;
	
	private String latitudeDec;
	private String longitudeDec;
	
	private Integer versionObj;
	
	private boolean defaut=false;

	public TaAdresseDTO() {
	}

	public void setSWTAdresse(TaAdresseDTO taAdresseDTO) {
		this.id = taAdresseDTO.id;
		this.adresse1Adresse = taAdresseDTO.adresse1Adresse;
		this.adresse2Adresse = taAdresseDTO.adresse2Adresse;
		this.adresse3Adresse = taAdresseDTO.adresse3Adresse;
		this.codepostalAdresse = taAdresseDTO.codepostalAdresse;
		this.villeAdresse = taAdresseDTO.villeAdresse;
		this.paysAdresse = taAdresseDTO.paysAdresse;
		this.codeTAdr = taAdresseDTO.codeTAdr;
		this.idTAdr = taAdresseDTO.idTAdr;
		this.idTiers = taAdresseDTO.idTiers;
//		this.ordre = taAdresseDTO.ordre;
	}

	
	public boolean estVide() {
		if(this.getAdresse1Adresse()!=null && !this.getAdresse1Adresse().isEmpty())return false;
		if(this.getAdresse2Adresse()!=null && !this.getAdresse2Adresse().isEmpty())return false;
		if(this.getAdresse3Adresse()!=null && !this.getAdresse3Adresse().isEmpty())return false;
		if(this.getCodepostalAdresse()!=null && !this.getCodepostalAdresse().isEmpty())return false;
		if(this.getVilleAdresse()!=null && !this.getVilleAdresse().isEmpty())return false;
		if(this.getPaysAdresse()!=null && !this.getPaysAdresse().isEmpty())return false;
		return true;
	}
	
	public static TaAdresseDTO copy(TaAdresseDTO taAdresseDTO){
		TaAdresseDTO taAdresseDTOLoc = new TaAdresseDTO();
		taAdresseDTOLoc.setId(taAdresseDTO.getId());                //1
		taAdresseDTOLoc.setIdTAdr(taAdresseDTO.getIdTAdr());                //1
		taAdresseDTOLoc.setIdTiers(taAdresseDTO.getIdTiers());                //1
		taAdresseDTOLoc.setAdresse1Adresse(taAdresseDTO.getAdresse1Adresse());                //1
		taAdresseDTOLoc.setAdresse2Adresse(taAdresseDTO.getAdresse2Adresse());                //1
		taAdresseDTOLoc.setAdresse3Adresse(taAdresseDTO.getAdresse3Adresse());                //1
		taAdresseDTOLoc.setCodepostalAdresse(taAdresseDTO.getCodepostalAdresse());                //1
		taAdresseDTOLoc.setVilleAdresse(taAdresseDTO.getVilleAdresse());                //1
		taAdresseDTOLoc.setPaysAdresse(taAdresseDTO.getPaysAdresse());                //1
		taAdresseDTOLoc.setCodeTAdr(taAdresseDTO.getCodeTAdr()); 
//		taAdresseDTOLoc.setOrdre(taAdresseDTO.getOrdre());//1
		return taAdresseDTOLoc;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}

	@LgrHibernateValidated(champBd = "adresse1_adresse",table = "ta_adresse",champEntite="adresse1Adresse",clazz = TaAdresseDTO.class)
	public String getAdresse1Adresse() {
		return this.adresse1Adresse;
	}

	public void setAdresse1Adresse(String adresse1Adresse) {
		firePropertyChange("adresse1Adresse", this.adresse1Adresse, this.adresse1Adresse = adresse1Adresse);
	}

	@LgrHibernateValidated(champBd = "adresse2_adresse",table = "ta_adresse",champEntite="adresse2Adresse",clazz = TaAdresseDTO.class)
	public String getAdresse2Adresse() {
		return this.adresse2Adresse;
	}

	public void setAdresse2Adresse(String adresse2Adresse) {
		firePropertyChange("adresse2Adresse", this.adresse2Adresse, this.adresse2Adresse = adresse2Adresse);
	}

	@LgrHibernateValidated(champBd = "adresse3_adresse",table = "ta_adresse",champEntite="adresse3Adresse",clazz = TaAdresseDTO.class)
	public String getAdresse3Adresse() {
		return this.adresse3Adresse;
	}

	public void setAdresse3Adresse(String adresse3Adresse) {
		firePropertyChange("adresse3Adresse", this.adresse3Adresse, this.adresse3Adresse = adresse3Adresse);
	}

	@LgrHibernateValidated(champBd = "codepostal_adresse",table = "ta_adresse",champEntite="codepostalAdresse",clazz = TaAdresseDTO.class)
	public String getCodepostalAdresse() {
		return this.codepostalAdresse;
	}

	public void setCodepostalAdresse(String codepostalAdresse) {
		firePropertyChange("codepostalAdresse", this.codepostalAdresse, this.codepostalAdresse = codepostalAdresse);
	}

	@LgrHibernateValidated(champBd = "ville_adresse",table = "ta_adresse",champEntite="villeAdresse",clazz = TaAdresseDTO.class)
	public String getVilleAdresse() {
		return this.villeAdresse;
	}

	public void setVilleAdresse(String villeAdresse) {
		firePropertyChange("villeAdresse", this.villeAdresse, this.villeAdresse = villeAdresse);
	}

	@LgrHibernateValidated(champBd = "pays_adresse",table = "ta_adresse",champEntite="paysAdresse",clazz = TaAdresseDTO.class)
	public String getPaysAdresse() {
		return this.paysAdresse;
	}

	public void setPaysAdresse(String paysAdresse) {
		firePropertyChange("paysAdresse", this.paysAdresse, this.paysAdresse = paysAdresse);
	}

	@LgrHibernateValidated(champBd = "code_t_adr",table = "ta_t_adr",champEntite="codeTAdr", clazz = TaAdresseDTO.class)
	@Size(min=0, max=20)
	public String getCodeTAdr() {
		return this.codeTAdr;
	}

	public void setCodeTAdr(String codeTAdr) {
		firePropertyChange("codeTAdr", this.codeTAdr, this.codeTAdr = codeTAdr);
	}

	public Integer getIdTAdr() {
		return this.idTAdr;
	}

	public void setIdTAdr(Integer idTAdr) {
		firePropertyChange("idTAdr", this.idTAdr, this.idTAdr = idTAdr);
	}

	public Integer getIdTiers() {
		return this.idTiers;
	}

	public void setIdTiers(Integer idTiers) {
		firePropertyChange("idTiers", this.idTiers, this.idTiers = idTiers);
	}
	
	public Integer getVersionObj() {
		return this.versionObj;
	}

	
	public String getLatitudeDec() {
		return this.latitudeDec;
	}

	public void setLatitudeDec(String latitudeDec) {
		firePropertyChange("latitudeDec", this.latitudeDec, this.latitudeDec = latitudeDec);
	}
	
	public String getLongitudeDec() {
		return this.longitudeDec;
	}

	public void setLongitudeDec(String longitudeDec) {
		firePropertyChange("longitudeDec", this.longitudeDec, this.longitudeDec = longitudeDec);
	}
	
	public String getLivraison() {
		return this.livraison;
	}

	public void setLivraison(String livraison) {
		firePropertyChange("livraison", this.livraison, this.livraison = livraison);
	}
	
	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaAdresseDTO))
			return false;
		TaAdresseDTO castOther = (TaAdresseDTO) other;

		return ((this.getId() == castOther.getId()) || (this
				.getId() != null
				&& castOther.getId() != null && this.getId()
				.equals(castOther.getId())))
				&& ((this.getAdresse1Adresse() == castOther
						.getAdresse1Adresse()) || (this.getAdresse1Adresse() != null
						&& castOther.getAdresse1Adresse() != null && this
						.getAdresse1Adresse().equals(
								castOther.getAdresse1Adresse())))
				&& ((this.getAdresse2Adresse() == castOther
						.getAdresse2Adresse()) || (this.getAdresse2Adresse() != null
						&& castOther.getAdresse2Adresse() != null && this
						.getAdresse2Adresse().equals(
								castOther.getAdresse2Adresse())))
				&& ((this.getAdresse3Adresse() == castOther
						.getAdresse3Adresse()) || (this.getAdresse3Adresse() != null
						&& castOther.getAdresse3Adresse() != null && this
						.getAdresse3Adresse().equals(
								castOther.getAdresse3Adresse())))
				&& ((this.getCodepostalAdresse() == castOther
						.getCodepostalAdresse()) || (this
						.getCodepostalAdresse() != null
						&& castOther.getCodepostalAdresse() != null && this
						.getCodepostalAdresse().equals(
								castOther.getCodepostalAdresse())))
				&& ((this.getVilleAdresse() == castOther.getVilleAdresse()) || (this
						.getVilleAdresse() != null
						&& castOther.getVilleAdresse() != null && this
						.getVilleAdresse()
						.equals(castOther.getVilleAdresse())))
				&& ((this.getPaysAdresse() == castOther.getPaysAdresse()) || (this
						.getPaysAdresse() != null
						&& castOther.getPaysAdresse() != null && this
						.getPaysAdresse().equals(castOther.getPaysAdresse())))
				&& ((this.getCodeTAdr() == castOther.getCodeTAdr()) || (this
						.getCodeTAdr() != null
						&& castOther.getCodeTAdr() != null && this
						.getCodeTAdr().equals(castOther.getCodeTAdr())))
				&& ((this.getIdTAdr() == castOther.getIdTAdr()) || (this
						.getIdTAdr() != null
						&& castOther.getIdTAdr() != null && this
						.getIdTAdr().equals(castOther.getIdTAdr())))
				&& ((this.getIdTiers() == castOther.getIdTiers()) || (this
						.getIdTiers() != null
						&& castOther.getIdTiers() != null && this
						.getIdTiers().equals(castOther.getIdTiers())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getId() == null ? 0 : this.getId()
						.hashCode());
		result = 37
				* result
				+ (getAdresse1Adresse() == null ? 0 : this
						.getAdresse1Adresse().hashCode());
		result = 37
				* result
				+ (getAdresse2Adresse() == null ? 0 : this
						.getAdresse2Adresse().hashCode());
		result = 37
				* result
				+ (getAdresse3Adresse() == null ? 0 : this
						.getAdresse3Adresse().hashCode());
		result = 37
				* result
				+ (getCodepostalAdresse() == null ? 0 : this
						.getCodepostalAdresse().hashCode());
		result = 37
				* result
				+ (getVilleAdresse() == null ? 0 : this.getVilleAdresse()
						.hashCode());
		result = 37
				* result
				+ (getPaysAdresse() == null ? 0 : this.getPaysAdresse()
						.hashCode());
		result = 37
				* result
				+ (getCodeTAdr() == null ? 0 : this.getCodeTAdr()
						.hashCode());
		result = 37 * result
				+ (getIdTAdr() == null ? 0 : this.getIdTAdr().hashCode());
		result = 37 * result
				+ (getIdTiers() == null ? 0 : this.getIdTiers().hashCode());
		return result;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public boolean isDefaut() {
		return defaut;
	}

	public void setDefaut(boolean defaut) {
		this.defaut = defaut;
	}


	
	//passage ejb
//	public TaAdresseDTO setSWTAdresse(IHMAdresseInfosFacturation adr) {
//		setAdresse1Adresse(adr.getAdresse1Adresse());
//		setAdresse2Adresse(adr.getAdresse2Adresse());
//		setAdresse3Adresse(adr.getAdresse3Adresse());
//		setCodepostalAdresse(adr.getCodepostalAdresse());
//		setVilleAdresse(adr.getVilleAdresse());
//		setPaysAdresse(adr.getPaysAdresse());
//		return this;
//	}
//	
//	public TaAdresseDTO setSWTAdresse(IHMAdresseInfosLivraison adr) {
//		setAdresse1Adresse(adr.getAdresse1AdresseLiv());
//		setAdresse2Adresse(adr.getAdresse2AdresseLiv());
//		setAdresse3Adresse(adr.getAdresse3AdresseLiv());
//		setCodepostalAdresse(adr.getCodepostalAdresseLiv());
//		setVilleAdresse(adr.getVilleAdresseLiv());
//		setPaysAdresse(adr.getPaysAdresseLiv());
//		return this;
//	}

}