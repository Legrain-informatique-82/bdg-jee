package fr.legrain.document.etat.proforma.preferences;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import fr.legrain.document.etat.devis.preferences.EcheancePreference;
import fr.legrain.document.etat.proforma.Activator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class EcheanceProformaPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public EcheanceProformaPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		
		String[][] listeEtatCombo = EcheancePreference.getListeEtat();
		
		final ComboFieldEditor etat = new ComboFieldEditor(
				PreferenceConstants.P_ETAT_DEFAUT, "Etat recherche par defaut", listeEtatCombo, getFieldEditorParent()
				);
		addField(etat);
		
		addField(
				new StringFieldEditor(PreferenceConstants.P_NB_JOUR, "Nombre de jour", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}