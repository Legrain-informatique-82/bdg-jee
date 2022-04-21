package fr.legrain.devis.divers;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import fr.legrain.devis.DevisPlugin;
import fr.legrain.devis.preferences.PreferenceConstants;
import fr.legrain.document.divers.IImpressionDocumentTiers;
//import fr.legrain.edition.Activator;
import fr.legrain.edition.ImprimeObjet;
//import fr.legrain.edition.actions.ConstEdition;
//import fr.legrain.edition.actions.SwtReportWithExpandbar;

public class Impression implements IImpressionDocumentTiers {

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void imprimerSelection(int idDoc, String codeDoc, boolean preview, String fileEditionDefaut,
			String nameOnglet, String nomEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imprimerChoix(String fileEditionDefaut, String nameOnglet, Collection collection, String nomEntity,
			boolean flagPreview, boolean flagPrint) {
		// TODO Auto-generated method stub
		
	}
//	Shell shellParent = null;
//	
//	private ConstEdition constEdition = null;
//	private Object object = null;
//	private Collection collection = null;
//	private EntityManager entityManager;
//	public Impression() {
//		super();
//	}
//	public Impression(Shell s){
//		this(s, null);	
//	}
//	public Impression(Shell s,EntityManager entityManager){
//		this.shellParent = s;
//		this.entityManager = entityManager;
//	}
//	
//
//	public Impression(ConstEdition constEdition,Object object){
//		this.constEdition = constEdition;
//		this.object = object;
//	}
//	
//	static Logger logger = Logger.getLogger(Impression.class.getName());
//
//	
//	public void imprimerSelection(int idDoc,final String codeDoc,boolean preview,String fileEditionDefaut,
//			String namePlugin, String nomEntity){
//		
//		IPreferenceStore preferenceStore = DevisPlugin.getDefault().getPreferenceStore();
//		/**
//		 * afficheAutomatique si true  ==> quand on enregist une facture, ensuit affiche l'edition 
//		 * 					  si false ==>
//		 */
//		boolean afficheAutomatique = preferenceStore.getBoolean(PreferenceConstants.IMPRIMER_AUTO);
//		
//		boolean affiche = preferenceStore.getBoolean(PreferenceConstants.AFFICHER_SELECTION_EDITION);
//		
//		String nomOnglet = ConstEdition.EDITION+namePlugin;
//		HashMap<String,String> reportParam = new HashMap<String,String>();
//
//		String choix= preferenceStore.getString(PreferenceConstants.LISTE_CHOIX);
//		reportParam.put(ConstEditionDevis.PARAM_CHOIX_DEST,choix);
//		if(choix == null || choix.equals(""))choix="choix 1";
//		
//		String typeAdresseCorrespondance = preferenceStore.getString(PreferenceConstants.TYPE_ADRESSE_CORRESPONDANCE);
//		if(typeAdresseCorrespondance !=null &&!typeAdresseCorrespondance.equals("")){
//			reportParam.put("ParamCorr",typeAdresseCorrespondance);
//		}
//		else{
//			reportParam.put("ParamCorr",null);
//		}
//		
//		String reportFileLocationDefaut = null;
//		boolean buttonEditionDefaut = false;
//		boolean flagMessageEditionPreference = false;
//
//		/** 08/02/2010 **/
//		boolean imprimerDirect = preferenceStore.getBoolean(PreferenceConstants.editionImprimerDirect);
//		boolean afficheEditionAImprimer = preferenceStore.getBoolean(PreferenceConstants.afficheEditionImprimer);
//			
//
//		IPreferenceStore preferenceStoreEdition = Activator.getDefault().getPreferenceStore();
//		
//		String pathFileAImprimer = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
//				   PATH_SAVE_PDF);
//		String pathAdobeReader = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
//  			     PATH_ACROBAT_READER);
//		
//		/*****************************/	
//		LinkedList<Integer> idEntity = new LinkedList<Integer>();
//		idEntity.add(idDoc);
//		constEdition.setIdOne(idEntity);
//		constEdition.setObjectEntity(object);
//		constEdition.setCollection(collection);
//		constEdition.setNameEntity(nomEntity);
//		
////		/** 05/01/2010 **/
////		constEdition.prepartionEditionBirt();
//		ImprimeObjet.clearListAndMap();
//		try {
////			Bundle bundleCourant = DevisPlugin.getDefault().getBundle();
////			String namePlugin = bundleCourant.getSymbolicName();
//			/**
//			 * affiche is false ==> ne affiche pas les choix des edition.pour obtenir l'edition
//			 * 						prendre la chemin de l'edition dans le preference  
//			 */			
//			if(!affiche){
//
//				constEdition.editionDirect(namePlugin, nomOnglet, reportParam,false,imprimerDirect,
//						afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity,
//						null,false,fileEditionDefaut);
//			}else{
//
//				reportFileLocationDefaut = ConstEdition.pathFichierEditionsSpecifiques(fileEditionDefaut,DevisPlugin.PLUGIN_ID);
//				File fileReportFileLocationDefaut = new File(reportFileLocationDefaut);
//				if(fileReportFileLocationDefaut.exists()){
//					buttonEditionDefaut = true;
//				}
//				
//				/********************************/
//				/**
//				 * Eventuellement, ces codes ne servit plus
//				 */
//				if (reportFileLocationDefaut == null){
//					reportFileLocationDefaut = fileEditionDefaut;
//				}
//				/********************************/			
//				ConstEdition.CONTENT_COMMENTS =ConstEdition.COMMENTAIRE_EDITION_DEFAUT;
//				/**
//				 * trouver path de l'edition pour un client spécific 
//				 * EX : /home/lee/testJPA26052009/1715/EditionsClient/Devis
//				 */
//				String FloderEditionSpecifiquesClient="";                //pathRepertoireEditionsSpecifiques()
//				if(ConstEdition.pathRepertoireEditionsSpecifiquesClient()!=null)
//					FloderEditionSpecifiquesClient = ConstEdition.pathRepertoireEditionsSpecifiquesClient()+ConstEdition.SEPARATOR+
//							namePlugin+ConstEdition.SEPARATOR+nomEntity;
//				//File fileEditionSpecifiquesClient = constEdition.makeFolderEditions(FloderEditionSpecifiquesClient);
//				File fileEditionSpecifiquesClient = new File(FloderEditionSpecifiquesClient);
//				
//				/**
//				 * trouver path de l'edition pour plugin EditionSpecifique 
//				 * EX : /home/lee/testJPA26052009/EditionsSpecifiques/Editions/Facture/TaFacture
//				 */
//				String FloderEditionSpecifiques="";
//				if(ConstEdition.pathRepertoireEditionsSpecifiques()!=null)
//					FloderEditionSpecifiques = ConstEdition.pathRepertoireEditionsSpecifiques()+ConstEdition.SEPARATOR+
//					namePlugin+ConstEdition.SEPARATOR+nomEntity;    
//				File fileEditionSpecifiques = new File(FloderEditionSpecifiques);
//					
//			
//				Shell dialogShell = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
//						//SWT.DIALOG_TRIM |SWT.APPLICATION_MODAL);
//						SWT.RESIZE | SWT.CLOSE | SWT.MAX | SWT.APPLICATION_MODAL);
//				dialogShell.setText(ConstEdition.TITLE_SHELL);
//				dialogShell.setLayout(new FillLayout());
//				
////				SwtCompositeReport_new ecranDialogReport = new SwtCompositeReport_new(dialogShell,SWT.NULL);
//				
//				/**
//				 * With Expandbar
//				 */
//				SwtReportWithExpandbar ecranDialogReport = new SwtReportWithExpandbar(dialogShell,SWT.NULL);
//				
////				ConstEdition.addCommentToEditionDefaut(ecranDialogReport.getRadioReportDefaut(), 
////						buttonEditionDefaut, ConstEdition.TEXT_BUTTON_EDITION_DEFAUT, nameOnglet);
//				
////				LinkedList<Integer> idSWTTiers = new LinkedList<Integer>();
////				LinkedList<Integer> oneIDSWTTiers = new LinkedList<Integer>();
////				idSWTTiers.add(idDoc);
////				oneIDSWTTiers.add(idDoc);
////				constEdition.addValues(idSWTTiers,oneIDSWTTiers);
//				
//				constEdition.setPARAM_ID_TABLE(ConstEdition.PARAM_ID_DOC);
//				constEdition.paramId = ConstEdition.PARAM_ID_DOC;
//				/**
//				 * with Expandbar
//				 */
//				constEdition.fillMapNameExpandbar(true);
////				constEdition.addExpandItem(ecranDialogReport.getExpandBarEdition());
//				
//				constEdition.openDialogChoixEditionDefaut(ecranDialogReport,fileEditionSpecifiquesClient, 
//				reportFileLocationDefaut, namePlugin,nomOnglet,dialogShell,null,affiche,
//				reportParam,fileEditionSpecifiques,buttonEditionDefaut,imprimerDirect,
//				afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity);
//			}
//			
//
//		} catch (Exception ex) {
//			logger.error(ex);
//		}
//		
//	}
//
//
//	public Shell getShellParent() {
//		return shellParent;
//	}
//
//
//	public void setShellParent(Shell shellParent) {
//		this.shellParent = shellParent;
//	}
//
//	public void setConstEdition(ConstEdition constEdition) {
//		this.constEdition = constEdition;
//	}
//
//	public void setObject(Object object) {
//		this.object = object;
//	}
//
//	public void setCollection(Collection collection) {
//		this.collection = collection;
//	}
//
//	@Override
//	public void imprimerChoix(String fileEditionDefaut, String nameOnglet, Collection collection,String nomEntity,
//			boolean flagPriview,boolean flagPrint) {
//		
//		IPreferenceStore preferenceStore = DevisPlugin.getDefault().getPreferenceStore();
//		
//		boolean affiche =preferenceStore.getBoolean(PreferenceConstants.AFFICHER_SELECTION_EDITION);
//		boolean affiche_en_liste =preferenceStore.getBoolean(PreferenceConstants.AFFICHER_SELECTION_EDITION_EDITION_EN_LISTE);
//		//ViewerPlugin.getDefault().getPluginPreferences().setValue(WebViewer.APPCONTEXT_EXTENSION_KEY, EditionAppContext.APP_CONTEXT_NAME);
//
//		HashMap<String, String> reportParam = new HashMap<String, String>();
//		
//		String choix= preferenceStore.getString(PreferenceConstants.LISTE_CHOIX);
//		reportParam.put(ConstEditionDevis.PARAM_CHOIX_DEST,choix);
//		if(choix == null || choix.equals(""))choix="choix 1";
//		
//		String typeAdresseCorrespondance = preferenceStore.getString(PreferenceConstants.TYPE_ADRESSE_CORRESPONDANCE);
//		if(typeAdresseCorrespondance !=null &&!typeAdresseCorrespondance.equals("")){
//			reportParam.put("ParamCorr",typeAdresseCorrespondance);
//		}
//		else{
//			reportParam.put("ParamCorr",null);
//		}
//		
//		boolean flagMessageEditionpreference = false;
//		Bundle bundleCourant = DevisPlugin.getDefault().getBundle();
//		String namePlugin = bundleCourant.getSymbolicName();
//		
//		constEdition = new ConstEdition(this.entityManager);
//		constEdition.setNameEntity(nomEntity);
//		constEdition.setFlagListFicheEditionDocument(true);
//		
//		/** 08/02/2010 **/
////		boolean imprimerDirect = preferenceStore.getBoolean(PreferenceConstants.editionImprimerDirect);
////		boolean afficheEditionAImprimer = preferenceStore.getBoolean(PreferenceConstants.afficheEditionImprimer);
//		boolean imprimerDirect = flagPrint;
//		boolean afficheEditionAImprimer = flagPriview;
//
//		IPreferenceStore preferenceStoreEdition = Activator.getDefault().getPreferenceStore();
//		
//		String pathFileAImprimer = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
//				   PATH_SAVE_PDF);
//		String pathAdobeReader = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
//  			     PATH_ACROBAT_READER);
//		
//		/*****************************/	
//		/** 05/01/2010 add **/
//
//		ImprimeObjet.clearListAndMap();
//		
//		String reportFileLocation = preferenceStore.getString(PreferenceConstants.P_PATH_EDITION_DEFAUT);
//		if(!affiche_en_liste){
//
//			constEdition.setCollection(collection);
//			constEdition.editionDirect(namePlugin, nameOnglet, reportParam,true,imprimerDirect,
//					afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity,
//					null,true,fileEditionDefaut);
//		}
//		else{
//			reportFileLocation = ConstEdition.pathFichierEditionsSpecifiques(reportFileLocation,namePlugin);
//			File fileReportFileLocation = new File(reportFileLocation);
//			boolean buttonEditionDefaut = false;
//
//			if(fileReportFileLocation.exists()){
//				buttonEditionDefaut = true;
//			}
//
//			File reportFile = constEdition.findPathReportPlugin(bundleCourant, 
//					"/report/defaut/", "");
//
//			ConstEdition.CONTENT_COMMENTS =ConstEdition.COMMENTAIRE_EDITION_DEFAUT;
//
//			String FloderEdition="";
//			if(ConstEdition.pathRepertoireEditionsSpecifiquesClient()!=null)	
//				FloderEdition = ConstEdition.pathRepertoireEditionsSpecifiquesClient()+ConstEdition.SEPARATOR+
//				namePlugin+ConstEdition.SEPARATOR+nomEntity;
//
//		
//			File FloderFileEditions = new File(FloderEdition);
//			String nomOnglet = ConstEdition.EDITION+namePlugin;
//			Shell dialogShell = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
//					SWT.RESIZE | SWT.CLOSE | SWT.MAX | SWT.APPLICATION_MODAL);
//			dialogShell.setText(ConstEdition.TITLE_SHELL);
//			dialogShell.setLayout(new FillLayout());
//
////			SwtCompositeReport_new dialogReport = new SwtCompositeReport_new(dialogShell,SWT.NULL);
//			/**
//			 * With Expandbar
//			 */
//			SwtReportWithExpandbar ecranDialogReport = new SwtReportWithExpandbar(dialogShell,SWT.NULL);
//
//			constEdition.setCollection(collection);
//			
////			ConstEdition.addCommentToEditionDefaut(dialogReport.getRadioReportDefaut(), buttonEditionDefaut, 
////					ConstEdition.TEXT_BUTTON_EDITION_DEFAUT, nameOnglet);
//
//			String reportEditionsSpecifiques = ConstEdition.pathRepertoireEditionsSpecifiques()+ConstEdition.SEPARATOR+
//			namePlugin+ConstEdition.SEPARATOR+nomEntity;
//			
//			constEdition.setCollection(collection);
//			constEdition.setFlagEditionMultiple(false);
//			
////			constEdition.openDialogChoixEdition_Defaut(dialogReport, FloderFileEditions, 
////					reportFileLocation, namePlugin,nomOnglet,dialogShell,reportFile,affiche,reportParam,new File(reportEditionsSpecifiques), true);
//			/**
//			 * with Expandbar
//			 */
//			constEdition.fillMapNameExpandbar(true);
//			
//			constEdition.openDialogChoixEditionDefaut(ecranDialogReport,FloderFileEditions, 
//					reportFileLocation, namePlugin,nomOnglet,dialogShell,null,affiche,
//					reportParam,new File(reportEditionsSpecifiques),buttonEditionDefaut,imprimerDirect,
//					afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity);
//		}
//	}
//
//	public EntityManager getEntityManager() {
//		return entityManager;
//	}
//
//	public void setEntityManager(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//	@Override
//	public String getPluginName() {
//		// TODO Auto-generated method stub
//		return DevisPlugin.getDefault().getBundle().getSymbolicName();
//	}
//
//	@Override
//	public IPreferenceStore getPreferenceStore() {
//		// TODO Auto-generated method stub
//		return 	DevisPlugin.getDefault().getPreferenceStore();
//	}




}
