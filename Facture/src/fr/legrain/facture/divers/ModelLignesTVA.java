package fr.legrain.facture.divers;

import java.util.LinkedList;

import fr.legrain.document.model.LigneTva;
import fr.legrain.document.model.TaFacture;


public class ModelLignesTVA {

	LinkedList<IHMLignesTVA> listeObjet = new LinkedList<IHMLignesTVA>();

	public LinkedList<IHMLignesTVA> remplirListe(TaFacture taFacture) {
		IHMLignesTVA ihmLignesTVA = null;
		listeObjet.clear();
		if(taFacture != null) {
			for(LigneTva ligneTVA : taFacture.getLignesTVA()){
				ihmLignesTVA = new IHMLignesTVA();
				ihmLignesTVA.setIHMFactureLignesTVA(ligneTVA);
				listeObjet.add(ihmLignesTVA);
			}
		}
		return listeObjet;
	}
	
	public LinkedList<IHMLignesTVA> getListeObjet() {
		return listeObjet;
	}

}
