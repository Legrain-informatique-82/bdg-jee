package fr.legrain.traite.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.osgi.framework.Bundle;

import fr.legrain.articles.dao.TaArticle;

import fr.legrain.document.divers.ListEditorGestionTypeTiersDocument;
import fr.legrain.documents.dao.TaDevis;
import fr.legrain.edition.actions.ConstEdition;
import fr.legrain.edition.divers.FieldEditorPreferencePageLGR;
import fr.legrain.gestCom.Appli.Const;
import fr.legrain.gestCom.librairiesEcran.swt.ListEditorAddRemoveEditLgr;
import fr.legrain.gestCom.librairiesEcran.swt.StringCommentaireEditor;
import fr.legrain.traite.Activator;

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

public class TraitePreferencePage
extends FieldEditorPreferencePageLGR{
	

	private RadioGroupFieldEditor radioGroupChoixDestinataire = null;
	private String simpleName="Traite";
	 @Override
	protected void performDefaults() {
		PreferenceInitializer.initDefautProperties();
		super.performDefaults();
	}

	static private PropertiesConfiguration listeGestCode = new PropertiesConfiguration();
	 static Logger logger = Logger.getLogger(TraitePreferencePage.class.getName());
	  
	public TraitePreferencePage() {
		super(GRID);
		String idPlugin = Activator.getDefault().getBundle().getSymbolicName();
		this.constEdition = new ConstEdition();
		this.setFolderEdition(constEdition.FOLDER_EDITION);
		this.setFolderEditionClients(constEdition.FOLDER_EDITION_CLIENT);
		this.setFolderEditionReportPlugin(constEdition.FICHE_FILE_REPORT_TRAITE);
		this.setNamePlugin(Activator.PLUGIN_ID);
		reportPlugin =ConstEdition.pathRepertoireEditionsSpecifiques()+"//"+idPlugin+"/"+simpleName;
		
		reportPluginClients=ConstEdition.pathRepertoireEditionsSpecifiquesClient()+"//"+
		idPlugin+"/"+simpleName;
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Paramètres de codification des traites");
		
		/** 23/02/2010 **/
		/** 23/02/2010 **/
		reportEditionSupp = Const.PATH_FOLDER_EDITION_SUPP+ConstEdition.SEPARATOR+idPlugin+ConstEdition.SEPARATOR
	    					+simpleName;
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
//		addField(
//			new StringFieldEditor(PreferenceConstants.TA_DOC_FIXE_1, "fixe", getFieldEditorParent()));
//		addField(
//				new StringFieldEditor(PreferenceConstants.TA_DOC_EXO, "exo", getFieldEditorParent()));
//		addField(
//			new StringFieldEditor(PreferenceConstants.TA_DOC_COMPTEUR, "Compteur", getFieldEditorParent()));
		addField(
				new StringCommentaireEditor(PreferenceConstants.COMMENTAIRE, "Commentaire à insérer", getFieldEditorParent()));
		
		/** 05/01/2010 Add **/
		Bundle bundleCourant = Activator.getDefault().getBundle();
		String namePlugin = bundleCourant.getSymbolicName();
		
		String PathEditionDefaut = ConstEdition.pathFichierEditionsSpecifiques(ConstEdition.FICHE_FILE_REPORT_TRAITE,
				   namePlugin);
		
		File filePathEditionDefaut = new File(PathEditionDefaut);
		File fileEditionSpecifiquesClient = new File(reportPluginClients);
		File fileEditionSpecifiques = new File(reportPlugin);
		
		constEdition.fillMapNameExpandbar(true);
		
		constEdition.valuePropertieNamePlugin = namePlugin;
		constEdition.valuePropertiePathEditionDefaut = ConstEdition.FICHE_FILE_REPORT_TRAITE;
		constEdition.valuePropertieCommentEditions = String.format(ConstEdition.COMMENT_ONE_EDITION_ENTITY, 
				namePlugin,namePlugin);
		
		File fileEditionsSuppDevis = constEdition.makeFolderEditions(reportEditionSupp);
		
		boolean flag = constEdition.getAllInfosEdition(filePathEditionDefaut,null,namePlugin,
				  fileEditionSpecifiquesClient,fileEditionSpecifiques,simpleName,
				  null,false,fileEditionsSuppDevis);
		
		/*********************/
		createFieldParamEdition();
		createFieldChoixDestinataire();
		
	
		addField(new BooleanFieldEditor(PreferenceConstants.editionImprimerDirect,
				PreferenceConstants.EDITION_IMPRIMER_DIRECT,getFieldEditorParent()));
		
		addField(new BooleanFieldEditor(PreferenceConstants.afficheEditionImprimer,
				PreferenceConstants.AFFICHE_EDITION_IMPRIMER,getFieldEditorParent()));
		
		addField(
				new StringFieldEditor(PreferenceConstants.TYPE_TRAITE, "Type traite", getFieldEditorParent()));
	}



	@Override
	protected void performApply() {
		super.performApply();
	
		/** 05/01/2010 Add **/
		constEdition.saveProprietyPreferencPage();
		/********************/
		
		PropertiesConfiguration listeGestCode = new PropertiesConfiguration() ;
		//File fProp = new File(Const.C_FICHIER_GESTCODE) ;

		// Charge le contenu de ton fichier properties dans un objet Properties
		FileInputStream stream;
		try {
			stream = new FileInputStream(Const.C_FICHIER_GESTCODE);
			listeGestCode.load(stream) ;
			stream.close();
			String taNouvelleValeur=null;
			IPreferenceStore store = Activator.getDefault().getPreferenceStore();

			// Change la valeur de la clé taCle dans l'objet Properties
			taNouvelleValeur=Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.TA_DOC_FIXE_1) ;
			listeGestCode.setProperty(PreferenceConstants.TA_DOC_FIXE_1,taNouvelleValeur) ;

			taNouvelleValeur=Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.TA_DOC_EXO) ;
			listeGestCode.setProperty(PreferenceConstants.TA_DOC_EXO,taNouvelleValeur) ;

			taNouvelleValeur=Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.TA_DOC_COMPTEUR) ;
			listeGestCode.setProperty(PreferenceConstants.TA_DOC_COMPTEUR,taNouvelleValeur) ;

			// Charge le contenu de ton objet Properties dans ton fichier properties
			FileOutputStream oStream = new FileOutputStream(Const.C_FICHIER_GESTCODE);
			listeGestCode.save(oStream,null) ;
			oStream.close();

		} catch (ConfigurationException e) {
			logger.error("",e);
		
		} catch (IOException e) {
			logger.error("",e);
		}
	}


	public void createFieldChoixDestinataire(){
		try {
			addField(
					new BooleanFieldEditor(PreferenceConstants.AFFICHER_SELECTION_EDITION_EDITION_EN_LISTE, "Afficher la sélection des editions en liste", getFieldEditorParent()));
			//création des boutons radios contenant les choix disponibles
			String[][]listeChoix = new String[][]{{"choix 1 : Civilité,Nom,Prénom","choix 1"},{"choix 2 : type entreprise,nom entreprise","choix 2"},{"choix 3 : les 2","choix 3"},};
			radioGroupChoixDestinataire =new RadioGroupFieldEditor(
					PreferenceConstants.LISTE_CHOIX, PreferenceConstants.LISTE_CHOIX, 1,
					listeChoix,				
					getFieldEditorParent(),
					true); 
			addField(radioGroupChoixDestinataire);

		} catch (Exception e1) {
			logger.error("", e1);
		}
		
	}

	public RadioGroupFieldEditor getRadioGroupChoixDestinataire() {
		return radioGroupChoixDestinataire;
	}

	public void setRadioGroupChoixDestinataire(
			RadioGroupFieldEditor radioGroupChoixDestinataire) {
		this.radioGroupChoixDestinataire = radioGroupChoixDestinataire;
	}
	
	
}
