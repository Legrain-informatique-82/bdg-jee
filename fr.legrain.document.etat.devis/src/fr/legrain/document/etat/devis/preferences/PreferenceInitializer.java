package fr.legrain.document.etat.devis.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import fr.legrain.document.etat.devis.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_ETAT_DEFAUT, "");
		store.setDefault(PreferenceConstants.P_NB_JOUR,"10");
		store.setDefault(PreferenceConstants.P_AFFICHAGE_AU_LANCEMENT,false);
	}

}
