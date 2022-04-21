package fr.legrain.acompte.divers;

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

import fr.legrain.acompte.pluginAcompte;
import fr.legrain.acompte.preferences.PreferenceConstants;
import fr.legrain.document.divers.IImpressionDocumentTiers;
import fr.legrain.edition.Activator;
import fr.legrain.edition.ImprimeObjet;
import fr.legrain.edition.actions.ConstEdition;
import fr.legrain.edition.actions.SwtReportWithExpandbar;


public class Impression implements IImpressionDocumentTiers {
	Shell shellParent = null;
	
	private ConstEdition constEdition = null;
	private Object object = null;
	private Collection collection = null;
	private EntityManager entityManager;
	
	public Impression(){
		super();
	}
	
	public Impression(Shell s){
		this(s, null);
	}
	public Impression(Shell s,EntityManager entityManager){
		this.shellParent = s;
		this.entityManager = entityManager;
	}

	public Impression(ConstEdition constEdition,Object object){
		this.constEdition = constEdition;
		this.object = object;
	}
	
	static Logger logger = Logger.getLogger(Impression.class.getName());
	
	public void imprimerSelection(int idDoc,final String codeDoc,boolean preview,String fileEditionDefaut,String namePlugin, 
			String nomEntity){
		
		IPreferenceStore preferenceStore = pluginAcompte.getDefault().getPreferenceStore();
		/*
		 * afficheAutomatique si true  ==> quand on enregist une facture, ensuit affiche l'edition 
		 * 					  si false ==>
		 */
		boolean afficheAutomatique = preferenceStore.getBoolean(PreferenceConstants.IMPRIMER_AUTO);
			
		boolean affiche = preferenceStore.getBoolean(PreferenceConstants.AFFICHER_SELECTION_EDITION);
		String choix= preferenceStore.getString(PreferenceConstants.LISTE_CHOIX);
		
		String nomOnglet = ConstEdition.EDITION+namePlugin;
		HashMap<String,String> reportParam = new HashMap<String,String>();
		
		if(choix == null || choix.equals(""))choix="choix 1";
		reportParam.put(ConstEditionAcompte.PARAM_CHOIX_DEST,choix);
		String typeAdresseCorrespondance = preferenceStore.getString(PreferenceConstants.TYPE_ADRESSE_CORRESPONDANCE);
		
		if(typeAdresseCorrespondance !=null &&!typeAdresseCorrespondance.equals("")){
			reportParam.put("ParamCorr",typeAdresseCorrespondance);
		}
		else{
			reportParam.put("ParamCorr",null);
		}
		
		String reportFileLocationDefaut = null;
		boolean buttonEditionDefaut = false;
		boolean flagMessageEditionPreference = false;
		
		/** 08/02/2010 **/
		boolean imprimerDirect = preferenceStore.getBoolean(PreferenceConstants.editionImprimerDirect);
		boolean afficheEditionAImprimer = preferenceStore.getBoolean(PreferenceConstants.afficheEditionImprimer);
		
		IPreferenceStore preferenceStoreEdition = Activator.getDefault().getPreferenceStore();
		String pathFileAImprimer = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
				   PATH_SAVE_PDF);
		String pathAdobeReader = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
			     PATH_ACROBAT_READER);
		/***************************************/
		LinkedList<Integer> idEntity = new LinkedList<Integer>();
		idEntity.add(idDoc);
		constEdition.setIdOne(idEntity);
		constEdition.setObjectEntity(object);
		constEdition.setCollection(collection);
		constEdition.setNameEntity(nomEntity);
		ImprimeObjet.clearListAndMap();
//		/** 05/01/2010 **/
//		constEdition.setPARAM_ID_TABLE(ConstEditionArticle.PARAM_REPORT_ID_ARTICLE);
//		constEdition.paramId = ConstEditionArticle.PARAM_REPORT_ID_ARTICLE;
		
//		constEdition.prepartionEditionBirt();
		
		try {
			/**
			 * affiche is false ==> ne affiche pas les choix des edition.pour obtenir l'edition
			 * 						prendre la chemin de l'edition dans le preference  
			 */			
			if(!affiche){

				constEdition.editionDirect(namePlugin, nomOnglet, reportParam,false,imprimerDirect,
						afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity,
						null,false,fileEditionDefaut);
			}else{
				reportFileLocationDefaut = ConstEdition.pathFichierEditionsSpecifiques(fileEditionDefaut,pluginAcompte.PLUGIN_ID);
				File fileReportFileLocationDefaut = new File(reportFileLocationDefaut);
				if(fileReportFileLocationDefaut.exists()){
					buttonEditionDefaut = true;
				}
				/********************************/
				/**
				 * Eventuellement, ces codes ne servit plus
				 */
				if (reportFileLocationDefaut == null){
					reportFileLocationDefaut = fileEditionDefaut;
				}
				
				ConstEdition.CONTENT_COMMENTS =ConstEdition.COMMENTAIRE_EDITION_DEFAUT;			
				/**
				 * trouver path de l'edition pour un client spécific 
				 * EX : /home/lee/testJPA26052009/1715/EditionsClient/BonLiv
				 */
				String FloderEditionSpecifiquesClient="";                //pathRepertoireEditionsSpecifiques()
				if(ConstEdition.pathRepertoireEditionsSpecifiquesClient()!=null)
					FloderEditionSpecifiquesClient = ConstEdition.pathRepertoireEditionsSpecifiquesClient()+"/"+namePlugin+"/"+nomEntity;
				//File fileEditionSpecifiquesClient = constEdition.makeFolderEditions(FloderEditionSpecifiquesClient);
				File fileEditionSpecifiquesClient = new File(FloderEditionSpecifiquesClient);
				
				/**
				 * trouver path de l'edition pour plugin EditionSpecifique 
				 * EX : /home/lee/testJPA26052009/EditionsSpecifiques/Editions/BonLiv/TaDevis
				 */
				String FloderEditionSpecifiques="";
				if(ConstEdition.pathRepertoireEditionsSpecifiques()!=null)
					FloderEditionSpecifiques = ConstEdition.pathRepertoireEditionsSpecifiques()+"/"+namePlugin+"/"+nomEntity;    
				File fileEditionSpecifiques = new File(FloderEditionSpecifiques);

				//File FloderFileEditions = constEdition.makeFolderEditions(FloderEdition);			
				
				Shell dialogShell = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						//SWT.DIALOG_TRIM |SWT.APPLICATION_MODAL);
						SWT.RESIZE | SWT.CLOSE | SWT.MAX | SWT.APPLICATION_MODAL);
				dialogShell.setText(ConstEdition.TITLE_SHELL);
				dialogShell.setLayout(new FillLayout());
//				
//				SwtCompositeReport_new ecranDialogReport = new SwtCompositeReport_new(dialogShell,SWT.NULL);
//				
//				ConstEdition.addCommentToEditionDefaut(ecranDialogReport.getRadioReportDefaut(), 
//						buttonEditionDefaut, ConstEdition.TEXT_BUTTON_EDITION_DEFAUT,nameOnglet);
				
				/**
				 * With Expandbar
				 */
				SwtReportWithExpandbar ecranDialogReport = new SwtReportWithExpandbar(dialogShell,SWT.NULL);
				
//				LinkedList<Integer> idSWTTiers = new LinkedList<Integer>();
//				LinkedList<Integer> oneIDSWTTiers = new LinkedList<Integer>();
//				idSWTTiers.add(idDoc);
//				oneIDSWTTiers.add(idDoc);
//				constEdition.addValues(idSWTTiers,oneIDSWTTiers);
			
				constEdition.setPARAM_ID_TABLE(ConstEdition.PARAM_ID_DOC);
				constEdition.paramId = ConstEdition.PARAM_ID_DOC;
					
				/**
				 * with Expandbar
				 */
				constEdition.fillMapNameExpandbar(true);
								
				constEdition.openDialogChoixEditionDefaut(ecranDialogReport,fileEditionSpecifiquesClient, 
				reportFileLocationDefaut, namePlugin,nomOnglet,dialogShell,null,affiche,
				reportParam,fileEditionSpecifiques,buttonEditionDefaut,imprimerDirect,
				afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity);

			}

		} catch (Exception ex) {
			logger.error(ex);
		}
		
	}


	public Shell getShellParent() {
		return shellParent;
	}


	public void setShellParent(Shell shellParent) {
		this.shellParent = shellParent;
	}

	public void setConstEdition(ConstEdition constEdition) {
		this.constEdition = constEdition;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	
	@Override
	public void imprimerChoix(String fileEditionDefaut,String nameOnglet,Collection collection,String nomEntity,
			boolean flagPriview,boolean flagPrint) {
		
		IPreferenceStore preferenceStore = pluginAcompte.getDefault().getPreferenceStore();
		
		boolean affiche = preferenceStore.getBoolean(PreferenceConstants.AFFICHER_SELECTION_EDITION);
		boolean affiche_en_liste = preferenceStore.getBoolean(PreferenceConstants.AFFICHER_SELECTION_EDITION_EDITION_EN_LISTE);
		//ViewerPlugin.getDefault().getPluginPreferences().setValue(WebViewer.APPCONTEXT_EXTENSION_KEY, EditionAppContext.APP_CONTEXT_NAME);

		HashMap<String, String> reportParam = new HashMap<String, String>();
		
		String choix= preferenceStore.getString(PreferenceConstants.LISTE_CHOIX);
		if(choix == null || choix.equals(""))choix="choix 1";
		reportParam.put(ConstEditionAcompte.PARAM_CHOIX_DEST,choix);
		String typeAdresseCorrespondance = preferenceStore.getString(PreferenceConstants.TYPE_ADRESSE_CORRESPONDANCE);
		
		if(typeAdresseCorrespondance !=null &&!typeAdresseCorrespondance.equals("")){
			reportParam.put("ParamCorr",typeAdresseCorrespondance);
		}
		else{
			reportParam.put("ParamCorr",null);
		}
		
		boolean flagMessageEditionpreference = false;
		Bundle bundleCourant = pluginAcompte.getDefault().getBundle();
		String namePlugin = bundleCourant.getSymbolicName();
		
		constEdition = new ConstEdition(this.entityManager);
		constEdition.setNameEntity(nomEntity);
		constEdition.setFlagListFicheEditionDocument(true);
		
		/** 08/02/2010 **/
//		boolean imprimerDirect = preferenceStore.getBoolean(PreferenceConstants.editionImprimerDirect);
//		boolean afficheEditionAImprimer = preferenceStore.getBoolean(PreferenceConstants.afficheEditionImprimer);
		boolean imprimerDirect = flagPrint;
		boolean afficheEditionAImprimer = flagPriview;
		IPreferenceStore preferenceStoreEdition = Activator.getDefault().getPreferenceStore();
		String pathFileAImprimer = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
				   PATH_SAVE_PDF);
		String pathAdobeReader = preferenceStoreEdition.getString(fr.legrain.edition.preferences.PreferenceConstants.
			     PATH_ACROBAT_READER);
		/**************************************/		
		ImprimeObjet.clearListAndMap();
		
		String reportFileLocation = preferenceStore.getString(PreferenceConstants.P_PATH_EDITION_DEFAUT);
		if(!affiche_en_liste){
			
			constEdition.setCollection(collection);
			constEdition.editionDirect(namePlugin, nameOnglet, reportParam,true,imprimerDirect,
					afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity,
					null,true,fileEditionDefaut);
		}
		else{
			reportFileLocation = ConstEdition.pathFichierEditionsSpecifiques(reportFileLocation,namePlugin);
			File fileReportFileLocation = new File(reportFileLocation);
			boolean buttonEditionDefaut = false;

			if(fileReportFileLocation.exists()){
				buttonEditionDefaut = true;
			}

			
			File reportFile = constEdition.findPathReportPlugin(bundleCourant, 
					ConstEdition.SEPARATOR+"report"+ConstEdition.SEPARATOR+"defaut"+ConstEdition.SEPARATOR, "");
			ConstEdition.CONTENT_COMMENTS =ConstEdition.COMMENTAIRE_EDITION_DEFAUT;

			String FloderEdition="";
			if(ConstEdition.pathRepertoireEditionsSpecifiquesClient()!=null)	
				FloderEdition = ConstEdition.pathRepertoireEditionsSpecifiquesClient()+ConstEdition.SEPARATOR+
				namePlugin+ConstEdition.SEPARATOR+nomEntity;

		
			File FloderFileEditions = new File(FloderEdition);
			String nomOnglet = ConstEdition.EDITION+namePlugin;
			Shell dialogShell = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					SWT.RESIZE | SWT.CLOSE | SWT.MAX | SWT.APPLICATION_MODAL);
			dialogShell.setText(ConstEdition.TITLE_SHELL);
			dialogShell.setLayout(new FillLayout());

			
			SwtReportWithExpandbar ecranDialogReport = new SwtReportWithExpandbar(dialogShell,SWT.NULL);
			

			String reportEditionsSpecifiques = ConstEdition.pathRepertoireEditionsSpecifiques()+ConstEdition.SEPARATOR+
						namePlugin+ConstEdition.SEPARATOR+nomEntity;
			
			constEdition.setCollection(collection);
			constEdition.setFlagEditionMultiple(false);
			
			/*** with Expandbar	***/
			constEdition.fillMapNameExpandbar(true);
						
			constEdition.openDialogChoixEditionDefaut(ecranDialogReport,FloderFileEditions, 
					reportFileLocation, namePlugin,nomOnglet,dialogShell,null,affiche,
					reportParam,new File(reportEditionsSpecifiques),buttonEditionDefaut,imprimerDirect,
					afficheEditionAImprimer,pathFileAImprimer,pathAdobeReader,nomEntity);
		}
	}

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return pluginAcompte.getDefault().getBundle().getSymbolicName();
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		// TODO Auto-generated method stub
		return 	pluginAcompte.getDefault().getPreferenceStore();
	}


	
//	public void imprimerModeleDefaut(int idDoc,final String codeDoc,boolean preview){
//		try {
//			String url = "http://" + WebappAccessor.getHost() + ":"
//					+ WebappAccessor.getPort() + "/birt/";
//
//			System.setProperty("RUN_UNDER_ECLIPSE", "true");
//
//			url += "run?__report=";
//
//			Bundle bundleCourant = BonLivraisonPlugin.getDefault().getBundle();
//			String reportFileLocation =BonLivraisonPlugin.getDefault().getPreferenceStore().getString(PreferenceConstants.P_PATH_EDITION_DEFAUT);
//			if (reportFileLocation == null){
//				reportFileLocation = ConstEdition.FICHE_FILE_REPORT_FACTURE;
//			URL urlReportFile = Platform.asLocalURL(bundleCourant
//					.getEntry(reportFileLocation));
//			URI uriReportFile = new URI("file", urlReportFile.getAuthority(),
//					urlReportFile.getPath(), urlReportFile.getQuery(), null);
//			File reportFile = new File(uriReportFile);
//			url += reportFile.getAbsolutePath();
//			}else
//				url += reportFileLocation;
//
//			url += "&paramID_DOC=" + idDoc;
//			url += "&paramUrlJDBC="
//					+ IB_APPLICATION.findDatabase().getConnection()
//							.getConnectionURL();
//
//			SWTInfoEntreprise infoEntreprise = SWT_IB_TA_INFO_ENTREPRISE
//					.infosEntreprise("1", null);
//			url += "&capital=" + infoEntreprise.getCAPITAL_INFO_ENTREPRISE();
//			url += "&ape=" + infoEntreprise.getAPE_INFO_ENTREPRISE();
//			url += "&siret=" + infoEntreprise.getSIRET_INFO_ENTREPRISE();
//			url += "&rcs=" + infoEntreprise.getRCS_INFO_ENTREPRISE();
//			url += "&nomEntreprise=" + infoEntreprise.getNOM_INFO_ENTREPRISE();
//			url += "&__document=doc"+new Date().getTime();
//
//			url += "&__format=pdf";
//
//			logger.debug("URL edition: " + url);
//			final String finalURL = url;
//			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
//				public void run() {
//					try {
//						PlatformUI.getWorkbench().getBrowserSupport()
//								.createBrowser(
//										IWorkbenchBrowserSupport.AS_EDITOR,
//										"myId",
//										"Prévisualisation de la facture "
//												+ codeDoc,
//										"Prévisualisation de la facture "
//												+ codeDoc).openURL(
//										new URL(finalURL));
//						// oldFacture = new OldFacture();//réinitialisation de
//						// cet objet
//					} catch (PartInitException e) {
//						logger.error("", e);
//					} catch (MalformedURLException e) {
//						logger.error("", e);
//					}
//				}
//			});
//////			
//////			SWTPaBrowserController.openURL(finalURL, 
//////					"Prévisualisation de la facture "+ codeFacture, 
//////					"Prévisualisation de la facture "+ codeFacture);
//
//		} catch (Exception ex) {
//			logger.error(ex);
//		}
//		
//	}

	
//	protected void imprimerModeleDefaut(String[] idFactureAImprimer,boolean preview) throws Exception{		
//	//Chemin de l'edition
//		Bundle bundleCourant = BonLivraisonPlugin.getDefault().getBundle();
//		File reportFile = null;
//		String reportFileLocation =BonLivraisonPlugin.getDefault().getPreferenceStore().getString(PreferenceConstants.P_PATH_EDITION_DEFAUT);
//		if (reportFileLocation == null){
//			reportFileLocation = ConstEdition.FICHE_FILE_REPORT_FACTURE;
//		URL urlReportFile = Platform.asLocalURL(bundleCourant
//				.getEntry(reportFileLocation));
//		URI uriReportFile = new URI("file", urlReportFile.getAuthority(),
//				urlReportFile.getPath(), urlReportFile.getQuery(), null);
//		reportFile = new File(uriReportFile);
//		}else
//			reportFile = new File(reportFileLocation);
//	
//	//Preparation de l'edition
//	BirtUtil.startReportEngine();
//	
//	SWTInfoEntreprise infoEntreprise = SWT_IB_TA_INFO_ENTREPRISE.infosEntreprise("1",null);
//	
//	HashMap<String,String> reportParam = new HashMap<String,String>();
//	reportParam.put("paramUrlJDBC",IB_APPLICATION.findDatabase().getConnection().getConnectionURL());
//	reportParam.put("capital",infoEntreprise.getCAPITAL_INFO_ENTREPRISE());
//	reportParam.put("ape",infoEntreprise.getAPE_INFO_ENTREPRISE());
//	reportParam.put("siret",infoEntreprise.getSIRET_INFO_ENTREPRISE());
//	reportParam.put("rcs",infoEntreprise.getRCS_INFO_ENTREPRISE());
//	reportParam.put("nomEntreprise",infoEntreprise.getNOM_INFO_ENTREPRISE());
//
//	
//	
//	LgrSpooler spooler = LgrSpooler.getInstance();
//
//	final String[] finalIdFactureAImprimer = idFactureAImprimer;
//	final File finalReportFile = reportFile;
//	final HashMap finalReportParam = reportParam;
//	final LgrSpooler finalSpooler = spooler;
//	Job job = new Job("Préparation de l'impression") {
//		protected IStatus run(IProgressMonitor monitor) {
//			final int ticks = finalIdFactureAImprimer.length;
//			monitor.beginTask("Préparation de l'impression", ticks);
//			try {
//				OutputStream os = null;
//				for (int i = 0; i < finalIdFactureAImprimer.length; i++) {
//					finalReportParam.put("paramID_DOC",finalIdFactureAImprimer[i]);
//					os = BirtUtil.renderReportToStream(finalReportFile.getAbsolutePath(),finalReportParam,BirtUtil.PDF_FORMAT);
//					finalSpooler.add(os);
//					monitor.worked(1);
//					if (monitor.isCanceled()) {
//						finalSpooler.clear();
//						return Status.CANCEL_STATUS;
//					}
//				}
//			} finally {
//				monitor.done();
//			}
//			return Status.OK_STATUS;
//		}
//	};
//	job.setPriority(Job.SHORT);
//	//job.setUser(true);
//	job.schedule(); 
//	job.join();
//	
//	//Impression
//	if(job.getResult()==Status.OK_STATUS)
//		spooler.print(preview);
//	
//	BirtUtil.destroyReportEngine();
//}

}
