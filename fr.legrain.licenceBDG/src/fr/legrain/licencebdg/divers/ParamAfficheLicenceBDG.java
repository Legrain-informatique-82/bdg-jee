package fr.legrain.licencebdg.divers;

import javax.swing.JComponent;

import org.eclipse.swt.widgets.Control;

import fr.legrain.lib.gui.ParamAffiche;

public class ParamAfficheLicenceBDG extends ParamAffiche {
	private String idTiers = null;
	
	public static final String C_TITRE_FORMULAIRE = "Fiche licence BDG";
	public static final String C_TITRE_GRILLE = "Liste des supports d'abonnement";
	public static final String C_SOUS_TITRE = "Gestion des supports d'abonnement";
	
	
	public ParamAfficheLicenceBDG() {
		titreFormulaire = C_TITRE_FORMULAIRE;
		titreGrille = C_TITRE_GRILLE;
		sousTitre = C_SOUS_TITRE;
	}

	public JComponent getFocusDefaut() {
		return focusDefaut;
	}
	public void setFocusDefaut(JComponent focusDefaut) {
		this.focusDefaut = focusDefaut;
	}
	public String getSousTitre() {
		return sousTitre;
	}
	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}
	public String getTitreFormulaire() {
		return titreFormulaire;
	}
	public void setTitreFormulaire(String titreFormulaire) {
		this.titreFormulaire = titreFormulaire;
	}
	public String getTitreGrille() {
		return titreGrille;
	}
	public void setTitreGrille(String titreGrille) {
		this.titreGrille = titreGrille;
	}
	public String getIdTiers() {
		return idTiers;
	}
	public void setIdTiers(String idTiers) {
		this.idTiers = idTiers;
	}

	public Control getFocusDefautSWT() {
		return focusDefautSWT;
	}
	public void setFocusDefautSWT(Control focusDefautSWT) {
		this.focusDefautSWT = focusDefautSWT;
	}


}
