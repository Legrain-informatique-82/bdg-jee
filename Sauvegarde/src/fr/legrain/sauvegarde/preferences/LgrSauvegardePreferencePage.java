package fr.legrain.sauvegarde.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import fr.legrain.sauvegarde.SauvegardePlugin;

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

public class LgrSauvegardePreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public LgrSauvegardePreferencePage() {
		super(GRID);
		setPreferenceStore(SauvegardePlugin.getDefault().getPreferenceStore());
		setDescription("Paramètres de sauvegarde et de restauration.");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		//sauvegarde
//		addField(
//			new StringFieldEditor(PreferenceConstants.P_DOSSIER_A_SAUVEGARDER, "Dossier à sauvegarder :", getFieldEditorParent()));		
		addField(
				new StringFieldEditor(PreferenceConstants.P_EMPLACEMENT, "Répertoire de destination des sauvegardes :", getFieldEditorParent()));		
		//restauration
		addField(
				new StringFieldEditor(PreferenceConstants.P_REP_DEST, "Répertoire de restauration:", getFieldEditorParent()));
//			addField(
//					new StringFieldEditor(PreferenceConstants.P_DOSSIER_A_EFFACER, "Dossier à effacer :", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		setPreferenceStore(SauvegardePlugin.getDefault().getPreferenceStore());
	}
	
}