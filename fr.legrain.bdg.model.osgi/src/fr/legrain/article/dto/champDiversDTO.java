package fr.legrain.article.dto;

import fr.legrain.bdg.model.ModelObject;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9


/**
 * SWTPrix generated by hbm2java
 */
public class champDiversDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 927394561532736639L;
	
	
	private String nomChamp;
	private Object valeur;
	public String getNomChamp() {
		return nomChamp;
	}
	public void setNomChamp(String nomChamp) {
		this.nomChamp = nomChamp;
	}
	public Object getValeur() {
		return valeur;
	}
	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}

	
	

}