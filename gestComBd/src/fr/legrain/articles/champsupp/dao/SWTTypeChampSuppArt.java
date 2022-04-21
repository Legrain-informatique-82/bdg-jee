package fr.legrain.articles.champsupp.dao;

import fr.legrain.lib.data.ModelObject;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTTTva generated by hbm2java
 */
public class SWTTypeChampSuppArt extends ModelObject {

	private Integer idChampSuppArt;
	private String nomChampSuppArt;
	private String typeValeurChampSuppArt;
	private String descriptionChampSuppArt;
	private String defautChampSuppArt;

	public SWTTypeChampSuppArt() {
	}

	public SWTTypeChampSuppArt(Integer idChampSuppArt, String nomChampSuppArt,
			String typeValeurChampSuppArt, String descriptionChampSuppArt,
			String defautChampSuppArt) {
		this.idChampSuppArt = idChampSuppArt;
		this.nomChampSuppArt = nomChampSuppArt;
		this.typeValeurChampSuppArt = typeValeurChampSuppArt;
		this.descriptionChampSuppArt = descriptionChampSuppArt;
		this.defautChampSuppArt = defautChampSuppArt;
	}

	
	public void setSWTChampSuppArt(SWTTypeChampSuppArt swtChampSuppArt) {
		this.idChampSuppArt = swtChampSuppArt.idChampSuppArt;
		this.nomChampSuppArt = swtChampSuppArt.nomChampSuppArt;
		this.typeValeurChampSuppArt = swtChampSuppArt.typeValeurChampSuppArt;
		this.descriptionChampSuppArt = swtChampSuppArt.descriptionChampSuppArt;
		this.defautChampSuppArt = swtChampSuppArt.defautChampSuppArt;
	}
	
	public static SWTTypeChampSuppArt copy(SWTTypeChampSuppArt swtChampSuppArt){
		SWTTypeChampSuppArt swtChampSuppArtLoc = new SWTTypeChampSuppArt();
		swtChampSuppArtLoc.setIdChampSuppArt(swtChampSuppArt.idChampSuppArt);
		swtChampSuppArtLoc.setNomChampSuppArt(swtChampSuppArt.nomChampSuppArt);
		swtChampSuppArtLoc.setTypeValeurChampSuppArt(swtChampSuppArt.typeValeurChampSuppArt);
		swtChampSuppArtLoc.setDescriptionChampSuppArt(swtChampSuppArt.descriptionChampSuppArt);
		swtChampSuppArtLoc.setDefautChampSuppArt(swtChampSuppArt.defautChampSuppArt);
		return swtChampSuppArtLoc;
	}

	public void setNomChampSuppArt(String nomChampSuppArt) {
		firePropertyChange("nomChampSuppArt", this.nomChampSuppArt, this.nomChampSuppArt = nomChampSuppArt);
	}

	public String getTypeValeurChampSuppArt() {
		return typeValeurChampSuppArt;
	}

	public void setTypeValeurChampSuppArt(String typeValeurChampSuppArt) {
		firePropertyChange("typeValeurChampSuppArt", this.typeValeurChampSuppArt, this.typeValeurChampSuppArt = typeValeurChampSuppArt);
	}

	public String getDescriptionChampSuppArt() {
		return descriptionChampSuppArt;
	}

	public void setDescriptionChampSuppArt(String descriptionChampSuppArt) {
		firePropertyChange("descriptionChampSuppArt", this.descriptionChampSuppArt, this.descriptionChampSuppArt = descriptionChampSuppArt);
	}

	public String getDefautChampSuppArt() {
		return defautChampSuppArt;
	}

	public void setDefautChampSuppArt(String defautChampSuppArt) {
		firePropertyChange("defautChampSuppArt", this.defautChampSuppArt, this.defautChampSuppArt = defautChampSuppArt);
	}

	public Integer getIdChampSuppArt() {
		return idChampSuppArt;
	}

	public void setIdChampSuppArt(Integer idChampSuppArt) {
		firePropertyChange("idChampSuppArt", this.idChampSuppArt, this.idChampSuppArt = idChampSuppArt);
	}

	public String getNomChampSuppArt() {
		return nomChampSuppArt;
	}
	

}
