package fr.legrain.tiers.dto;

import javax.validation.constraints.Size;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTCompl generated by hbm2java
 */
public class TaComplDTO extends ModelObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3828375731774924833L;
	private Integer id;
	private Integer idTiers;
	private String tvaIComCompl;
	private String siretCompl;
	private String codeTiers;
	private String nomTiers;
	private String prenomTiers;
	private String accise;
	private Integer versionObj;

	public TaComplDTO() {
	}

	public void setSWTCompl(TaComplDTO taComplDTO) {
		this.id = taComplDTO.id;
		this.idTiers = taComplDTO.idTiers;
		this.tvaIComCompl = taComplDTO.tvaIComCompl;
		this.siretCompl = taComplDTO.siretCompl;
		this.codeTiers = taComplDTO.codeTiers;
		this.nomTiers = taComplDTO.nomTiers;
		this.prenomTiers = taComplDTO.prenomTiers;
		this.accise = taComplDTO.accise;
	}

	public static TaComplDTO copy(TaComplDTO taComplDTO){
		TaComplDTO taComplDTOLoc = new TaComplDTO();
		taComplDTOLoc.setIdTiers(taComplDTO.getIdTiers());                //1
		taComplDTOLoc.setId(taComplDTO.getId());                //1
		taComplDTOLoc.setTvaIComCompl(taComplDTO.getTvaIComCompl());                //1
		taComplDTOLoc.setCodeTiers(taComplDTO.getCodeTiers());                //1
		taComplDTOLoc.setNomTiers(taComplDTO.getNomTiers());                //1
		taComplDTOLoc.setPrenomTiers(taComplDTO.getPrenomTiers());                //1
		taComplDTOLoc.setSiretCompl(taComplDTO.getSiretCompl()); 
		taComplDTOLoc.setAccise(taComplDTO.getAccise());//1
		return taComplDTOLoc;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer ID_COMPL) {
		firePropertyChange("id", this.id, this.id = ID_COMPL);
	}

	public Integer getIdTiers() {
		return this.idTiers;
	}

	public void setIdTiers(Integer ID_TIERS) {
		firePropertyChange("idTiers", this.idTiers, this.idTiers = ID_TIERS);
	}

	@LgrHibernateValidated(champBd = "tva_i_com_compl",table = "ta_compl",champEntite="tvaIComCompl", clazz = TaComplDTO.class)
	public String getTvaIComCompl() {
		return this.tvaIComCompl;
	}

	public void setTvaIComCompl(String TVA_I_COM_COMPL) {
		firePropertyChange("tvaIComCompl", this.tvaIComCompl, this.tvaIComCompl = TVA_I_COM_COMPL);
	}

	@LgrHibernateValidated(champBd = "siret_compl",table = "ta_compl",champEntite="siretCompl",clazz = TaComplDTO.class)
	public String getSiretCompl() {
		return this.siretCompl;
	}

	public void setSiretCompl(String SIRET_COMPL) {
		firePropertyChange("siretCompl", this.siretCompl, this.siretCompl = SIRET_COMPL);
	}

	@LgrHibernateValidated(champBd = "code_tiers",table = "ta_tiers",champEntite="codeTiers",clazz = TaComplDTO.class)
	@Size(min=0, max=20)
	public String getCodeTiers() {
		return this.codeTiers;
	}
	
	public void setCodeTiers(String CODE_TIERS) {
		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = CODE_TIERS);
	}

	@LgrHibernateValidated(champBd = "nom_tiers",table = "ta_tiers",champEntite="nomTiers",clazz = TaComplDTO.class)
	public String getNomTiers() {
		return this.nomTiers;
	}


	public void setNomTiers(String NOM_TIERS) {
		firePropertyChange("nomTiers", this.nomTiers, this.nomTiers = NOM_TIERS);
	}

	@LgrHibernateValidated(champBd = "prenom_tiers",table = "ta_tiers",champEntite="prenomTiers",clazz = TaComplDTO.class)
	public String getPrenomTiers() {
		return this.prenomTiers;
	}

	public void setPrenomTiers(String PRENOM_TIERS) {
		firePropertyChange("prenomTiers", this.prenomTiers, this.prenomTiers = PRENOM_TIERS);
	}

	@LgrHibernateValidated(champBd = "accise",table = "ta_compl",champEntite="accise",clazz = TaComplDTO.class)
	public String getAccise() {
		return accise;
	}

	public void setAccise(String accise) {
		firePropertyChange("accise", this.accise, this.accise = accise);
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
		result = prime * result + ((accise == null) ? 0 : accise.hashCode());
		result = prime * result
				+ ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idTiers == null) ? 0 : idTiers.hashCode());
		result = prime * result
				+ ((nomTiers == null) ? 0 : nomTiers.hashCode());
		result = prime * result
				+ ((prenomTiers == null) ? 0 : prenomTiers.hashCode());
		result = prime * result
				+ ((siretCompl == null) ? 0 : siretCompl.hashCode());
		result = prime * result
				+ ((tvaIComCompl == null) ? 0 : tvaIComCompl.hashCode());
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
		TaComplDTO other = (TaComplDTO) obj;
		if (accise == null) {
			if (other.accise != null)
				return false;
		} else if (!accise.equals(other.accise))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idTiers == null) {
			if (other.idTiers != null)
				return false;
		} else if (!idTiers.equals(other.idTiers))
			return false;
		if (nomTiers == null) {
			if (other.nomTiers != null)
				return false;
		} else if (!nomTiers.equals(other.nomTiers))
			return false;
		if (prenomTiers == null) {
			if (other.prenomTiers != null)
				return false;
		} else if (!prenomTiers.equals(other.prenomTiers))
			return false;
		if (siretCompl == null) {
			if (other.siretCompl != null)
				return false;
		} else if (!siretCompl.equals(other.siretCompl))
			return false;
		if (tvaIComCompl == null) {
			if (other.tvaIComCompl != null)
				return false;
		} else if (!tvaIComCompl.equals(other.tvaIComCompl))
			return false;
		return true;
	}





}
