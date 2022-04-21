package fr.legrain.wsimportosc.controller;


import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ActiveShellExpression;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;

import com.borland.dx.dataset.DataRow;
import com.borland.dx.sql.dataset.QueryDataSet;
import com.borland.dx.sql.dataset.QueryDescriptor;


import fr.legrain.gestCom.Appli.Const;
import fr.legrain.gestCom.Module_Tiers.IB_Tables.SWT_IB_TA_TIERS;
import fr.legrain.gestCom.librairiesEcran.LibrairiesEcranPlugin;
import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideControllerSWT;
import fr.legrain.gestCom.librairiesEcran.swt.old.SWTBaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.workbench.AbstractLgrMultiPageEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.lib.data.ContentProposalProvider;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.lib.data.LibChaine;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.ModeObjet;
import fr.legrain.lib.data.ModeObjet.EnumModeObjet;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.lib.gui.ResultAffiche;
import fr.legrain.lib.gui.RetourEcranEvent;
import fr.legrain.lib.gui.RetourEcranListener;
import fr.legrain.osc.articles.dao.TaArticleOsc;
import fr.legrain.osc.articles.dao.TaArticleOscDAO;
import fr.legrain.wsimportosc.ecran.PaImportOscWs;
import fr.legrain.wsimportosc.fonction.AskWebService;



public class SWTPaImportOscWsController extends SWTBaseControllerSWTStandard implements
	RetourEcranListener {
	
	static Logger logger = Logger.getLogger(SWTPaImportOscWsController.class.getName());		
	private PaImportOscWs vue = null; // vue
	private SWT_IB_TA_TIERS ibTaTable = new SWT_IB_TA_TIERS();
	private Object ecranAppelant = null;
	private String LibelleFacture = "";
	private String commentaire = null;
	private String codeTiers = "";
	private String finDeLigne = "\r\n";
//	private Impression impressionFacture = null ;
//	private fr.legrain.generationdocumentLGR.divers.Impression impressionGeneration = null ;
	private BigDecimal totalFacture = new BigDecimal(0); 
	private Integer nbFactures = 0;
	
	/** pour osccommerce**/
	private AskWebService askWebService = new AskWebService();
	private List<String> listCodeFactureSelected = new ArrayList<String>();
	private List<Integer> listIdFactureSelected = new ArrayList<Integer>();
	
	/** import oscommerce**/
	public static final String C_COMMAND_WSIMPORTOSC_IMPORTER_ID = "fr.legrain.wsimportosc.Importer";
	protected HandlerImporter handlerImporter = new HandlerImporter();
	
	public static final String C_COMMAND_WSIMPORTOSC_SELECTCODEFACTURE_ID = "fr.legrain.wsimportosc.SelectCF";
	protected HandlerSelectCF handlerSelectCF = new HandlerSelectCF();
	public static final String C_COMMAND_WSIMPORTOSC_DESELECTCODEFACTURE_ID = "fr.legrain.wsimportosc.DeselectCF";
	
	/********************************************************************/
	public static final String C_COMMAND_GENERATIONDOCUMENT_RAJOUTER_ID = "fr.legrain.generationdocumentLGR.Rajouter";
	protected HandlerRajouter handlerRajouter = new HandlerRajouter();

	public static final String C_COMMAND_GENERATIONDOCUMENT_REINITIALISER_ID = "fr.legrain.generationdocumentLGR.Reinitialiser";
	protected HandlerReinitialiser handlerReinitialiser = new HandlerReinitialiser();

	public static final String C_COMMAND_GENERATIONDOCUMENT_CHARGERFICHIER_ID = "fr.legrain.generationdocumentLGR.ChargerFichier";
    protected HandlerChargerFichier handlerChargerFichier = new HandlerChargerFichier();

	public static final String C_COMMAND_GENERATIONDOCUMENT_REMONTERDOC_ID = "fr.legrain.generationdocumentLGR.RemonterDoc";
    protected HandlerRemonterDoc	 handlerRemonterDoc = new HandlerRemonterDoc	();
	
	public static final String C_COMMAND_GENERATIONDOCUMENT_IMPRIMERDOC_ID = "fr.legrain.generationdocumentLGR.ImprimerDoc";
    protected HandlerImprimerDoc	 handlerImprimerDoc = new HandlerImprimerDoc	();
	
    public static final String C_COMMAND_GENERATIONDOCUMENT_REINITDOCCREE_ID = "fr.legrain.generationdocumentLGR.ReinitDocCree";
	protected HandlerReinitDocCree handlerReinitDocCree = new HandlerReinitDocCree();

    public static final String C_COMMAND_GENERATIONDOCUMENT_IMPRESSIONDIVERSES_ID = "fr.legrain.generationdocumentLGR.ImpressionDiverses";
	protected HandlerImpressionDiverses handlerImpressionDiverses = new HandlerImpressionDiverses();
	//
    private static final String END_STYLES_MARK = "***EndStyles***";
	  private static final String START_STYLES_MARK = "***Styles***";
	  private static final String START_TEXT_MARK = "***Text***";
	  private static  String FILE_NAME = Platform.getInstanceLocation().getURL().getFile()+"/CodeTiers.TXT";;

	  private boolean doBold = false;
	
	String[] listeCodeTiers=null;
	String[] listeDescriptionTiers=null;

	private ContentProposalAdapter tiersContentProposal;	

	public SWTPaImportOscWsController(PaImportOscWs vue) {
		try {
			setVue(vue);

			this.vue=vue;
			vue.getShell().addShellListener(this);
//			impressionFacture= new Impression(vue.getShell());
//			impressionGeneration= new fr.legrain.generationdocumentLGR.divers.Impression(vue.getShell());
//			Branchement action annuler : empeche la fermeture automatique de la
			// fenetre sur ESC
			vue.getShell().addTraverseListener(new Traverse());
			actionImprimer.setText("Valider[F3]");

			initController();
		} catch (Exception e) {
			logger.error("Erreur ", e);
		}

	}
	
	
	private class LgrModifyListener implements ModifyListener{

		public void modifyText(ModifyEvent e) {
			modif(e);
		}
	}
	
	private void modif(TypedEvent e) {
		
	}
	private void initController() {
		try {
			setIbTaTableStandard(ibTaTable);		
			
			initMapComposantChamps();
			initMapComposantDecoratedField();
			listeComponentFocusableSWT(listeComposantFocusable);
			initFocusOrder();
			initActions();
			initDeplacementSaisie(listeComposantFocusable);

			branchementBouton();

			Menu popupMenuFormulaire = new Menu(vue.getShell(), SWT.POP_UP);
			Menu popupMenuGrille = new Menu(vue.getShell(), SWT.POP_UP);
			Menu[] tabPopups = new Menu[] { popupMenuFormulaire,
					popupMenuGrille };
			this.initPopupAndButtons(mapActions, tabPopups);


			initEtatBouton();
		} catch (Exception e) {
//			vue.getLaMessage().setText(e.getMessage());
			logger.error("Erreur : PaArticlesController", e);
		}
	}

	@Override
	protected void initImageBouton() {
		vue.getPaBtn1().getBtnFermer().setImage(LibrairiesEcranPlugin.getImageDescriptor(C_IMAGE_FERMER).createImage());
//		vue.getPaBtn1().getBtnImprimer().setImage(StocksPlugin.getImageDescriptor(C_IMAGE_EXPORT).createImage());
		vue.layout(true);
	}
	
	public Composite getVue() {return vue;}
	
	
	public ResultAffiche configPanel(ParamAffiche param){
		if (param!=null){
			if (param.getFocusDefaut()!=null)
				param.getFocusDefaut().requestFocus();
			
			if(param.getEcranAppelant()!=null) {
				ecranAppelant = param.getEcranAppelant();
			}
			param.setFocus(ibTaTable.getFModeObjet().getFocusCourant());
//			codeTiers = ((ParamAfficheImportOscWs)param).getCodeTiers();
		}
		return null;
	}
	
	/**
	 * Initialisation des composants graphiques de la vue.
	 * @throws ExceptLgr 
	 */
	protected void initComposantsVue() throws ExceptLgr {
	}
	
	/**
	 * Initialisation des boutons suivant l'�tat de l'objet "ibTaTable"
	 */
	protected void initEtatBouton() {
		super.initEtatBouton();
			switch (ibTaTableStandard.getFModeObjet().getMode()) {
			case C_MO_INSERTION:
				actionImprimer.setEnabled(true);
				actionAnnuler.setEnabled(true);
				actionFermer.setEnabled(true);
				break;
			case C_MO_EDITION:
				actionImprimer.setEnabled(true);
				actionAnnuler.setEnabled(true);
				actionFermer.setEnabled(true);
				break;
			case C_MO_CONSULTATION:
				actionImprimer.setEnabled(true);
				actionAnnuler.setEnabled(true);
				actionFermer.setEnabled(true);
				break;
			default:
				break;
			}
		}	
	
	
	/**
	 * Initialisation des correspondances entre les champs de formulaire et les
	 * champs de bdd
	 */
	protected void initMapComposantChamps() {
		// formulaire Adresse
		if (listeComposantFocusable == null) 
			listeComposantFocusable = new ArrayList();
		
		if (mapComposantChamps == null) 
			mapComposantChamps = new LinkedHashMap();
//		listeComposantFocusable.add(vue.getTfLibelle());
//		listeComposantFocusable.add(vue.getTfDateDocument());
		
		listeComposantFocusable.add(vue.getButtonImporter());
		
		listeComposantFocusable.add(vue.getTfRecherche());
		listeComposantFocusable.add(vue.getTfListeTiers());
		listeComposantFocusable.add(vue.getTfStyleEditeur());
		listeComposantFocusable.add(vue.getTfListeDoc());
		
		//listeComposantFocusable.add(vue.getBtnRajouter());
		listeComposantFocusable.add(vue.getBtnReinitialiser());

		//listeComposantFocusable.add(vue.getBtnChargerFichier());

		listeComposantFocusable.add(vue.getPaBtn1().getBtnImprimer());
		listeComposantFocusable.add(vue.getPaBtn1().getBtnFermer());
		
		listeComposantFocusable.add(vue.getBtnRemonterDoc());
		listeComposantFocusable.add(vue.getBtnImprimerDoc());
		listeComposantFocusable.add(vue.getBtnReinitDocCree());
		
		if(mapInitFocus == null)mapInitFocus = new LinkedHashMap();
		mapInitFocus.put(ModeObjet.EnumModeObjet.C_MO_CONSULTATION,vue.getTfLibelle());
		mapInitFocus.put(ModeObjet.EnumModeObjet.C_MO_INSERTION,vue.getTfLibelle());
		mapInitFocus.put(ModeObjet.EnumModeObjet.C_MO_EDITION,vue.getTfLibelle());
	
		
	    vue.getTfRecherche().addModifyListener(new ModifyListener(){

			public void modifyText(ModifyEvent e) {
				int i =0;
				vue.getTfListeTiers().deselectAll();
				i=vue.getTfListeTiers().indexOf(vue.getTfRecherche().getText(), 0);
				if(i==-1)i=vue.getTfListeTiers().indexOf(vue.getTfRecherche().getText().toUpperCase(), 0);
				if(i!=-1)vue.getTfListeTiers().setSelection(i);				
			}
	    	
	    });
	    try {
			actRefresh();
			setNbFactures(0);
			setTotalFacture(new BigDecimal(0));

		} catch (Exception e1) {
			logger.error("", e1);
		}



	}
	

	public String[] initAdapterWlgr(){
//		String requete = "select v.code_client,v.traite from v_wlgr v where v.traite=0 and ttc is not null and ttc<>0 order by 1";
//		ibTaTable.getQueryRecherche().close();
//		ibTaTable.getQueryRecherche().setQuery(new QueryDescriptor(ibTaTable.getFIBBase(),requete,true));
//		ibTaTable.getQueryRecherche().open();
//		String[] valeurs = null;
//		if(!ibTaTable.getQueryRecherche().isEmpty()) {
//			valeurs = new String[ibTaTable.getQueryRecherche().getRowCount()];
//			int i = 0;
//			do {
//				valeurs[i]= ibTaTable.getQueryRecherche().getString("code_client");
//				//+";"+ibTaTable.getQueryRecherche().getShort("Traite");
//				i++;
//			} while(ibTaTable.getQueryRecherche().next());
//		}
//		ibTaTable.getQueryRecherche().close();
		String[] valeurs = null;
		valeurs = askWebService.getCodeFactureOsc();
		return valeurs;
	}	
	@Override
	public void initCommands(){
		if(handlerService == null)
			handlerService = (IHandlerService)PlatformUI.getWorkbench().getService(IHandlerService.class);
		if(contextService == null)
			contextService = (IContextService) PlatformUI.getWorkbench().getService(IContextService.class);
		contextService.registerShell(vue.getShell(),IContextService.TYPE_DIALOG);
		activeShellExpression = new ActiveShellExpression(vue.getShell());
		
		handlerService.activateHandler(C_COMMAND_GLOBAL_AIDE_ID, handlerAide,activeShellExpression);
		handlerService.activateHandler(C_COMMAND_GLOBAL_ANNULER_ID, handlerAnnuler, activeShellExpression);
		handlerService.activateHandler(C_COMMAND_GLOBAL_FERMER_ID, handlerFermer, activeShellExpression);
		handlerService.activateHandler(C_COMMAND_GLOBAL_IMPRIMER_ID, handlerImprimer, activeShellExpression);
		handlerService.activateHandler(C_COMMAND_GENERATIONDOCUMENT_IMPRESSIONDIVERSES_ID,handlerImpressionDiverses);		
		
		handlerService.activateHandler(C_COMMAND_WSIMPORTOSC_IMPORTER_ID,handlerImporter);
		handlerService.activateHandler(C_COMMAND_WSIMPORTOSC_SELECTCODEFACTURE_ID,handlerSelectCF);
	}
	
	protected void initActions() {
		initCommands();
		if (mapActions == null)
			mapActions = new LinkedHashMap();
		
		mapCommand = new HashMap<String, IHandler>();
		
		mapCommand.put(C_COMMAND_GLOBAL_AIDE_ID, handlerAide);
		mapCommand.put(C_COMMAND_GLOBAL_FERMER_ID, handlerFermer);
		mapCommand.put(C_COMMAND_GLOBAL_IMPRIMER_ID, handlerImprimer);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_RAJOUTER_ID, handlerRajouter);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_REINITIALISER_ID, handlerReinitialiser);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_CHARGERFICHIER_ID, handlerChargerFichier);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_REMONTERDOC_ID, handlerRemonterDoc);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_IMPRIMERDOC_ID, handlerImprimerDoc);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_REINITDOCCREE_ID, handlerReinitDocCree);
		mapCommand.put(C_COMMAND_GENERATIONDOCUMENT_IMPRESSIONDIVERSES_ID, handlerImpressionDiverses);
		
		mapCommand.put(C_COMMAND_WSIMPORTOSC_IMPORTER_ID, handlerImporter);
		mapCommand.put(C_COMMAND_WSIMPORTOSC_SELECTCODEFACTURE_ID, handlerSelectCF);
		
		initFocusCommand(String.valueOf(this.hashCode()),
				listeComposantFocusable, mapCommand);
		
		mapActions.put(vue.getPaBtn1().getBtnFermer(), C_COMMAND_GLOBAL_FERMER_ID);
		mapActions.put(vue.getPaBtn1().getBtnImprimer(), C_COMMAND_GLOBAL_IMPRIMER_ID);
		//mapActions.put(vue.getBtnRajouter(), C_COMMAND_GENERATIONDOCUMENT_RAJOUTER_ID);
		mapActions.put(vue.getBtnReinitialiser(), C_COMMAND_GENERATIONDOCUMENT_REINITIALISER_ID);
		//mapActions.put(vue.getBtnChargerFichier(), C_COMMAND_GENERATIONDOCUMENT_CHARGERFICHIER_ID);
		mapActions.put(vue.getBtnRemonterDoc(), C_COMMAND_GENERATIONDOCUMENT_REMONTERDOC_ID);
		mapActions.put(vue.getBtnImprimerDoc(), C_COMMAND_GENERATIONDOCUMENT_IMPRIMERDOC_ID);
		mapActions.put(vue.getBtnReinitDocCree(), C_COMMAND_GENERATIONDOCUMENT_REINITDOCCREE_ID);
		
		mapActions.put(vue.getButtonImporter(), C_COMMAND_WSIMPORTOSC_IMPORTER_ID);
		mapActions.put(vue.getButtonSelect(), C_COMMAND_WSIMPORTOSC_SELECTCODEFACTURE_ID);	
		
		Object[] tabActionsAutres = new Object[] { C_COMMAND_GLOBAL_AIDE_ID,C_COMMAND_GENERATIONDOCUMENT_IMPRESSIONDIVERSES_ID };
		mapActions.put(null, tabActionsAutres);
	}
	
	public SWTPaImportOscWsController getThis(){
		return this;
	}
	
	@Override
	public boolean onClose() throws ExceptLgr {
		return true;
	}

	
	public void retourEcran(RetourEcranEvent evt) {
		if (evt.getRetour() != null
				&& (evt.getSource() instanceof SWTPaAideControllerSWT)) {
			if (getFocusAvantAideSWT() instanceof Text) {
				try {
					((Text) getFocusAvantAideSWT()).setText(((ResultAffiche) evt
							.getRetour()).getResult());					
					ctrlUnChampsSWT(getFocusAvantAideSWT());
				} catch (Exception e) {
					logger.error("",e);
//					vue.getLaMessage().setText(e.getMessage());
				}
			}
		}
		super.retourEcran(evt);
	}
	
	@Override
	protected void actInserer() throws Exception{}
	
	@Override
	protected void actModifier() throws Exception{}
	
	@Override
	protected void actSupprimer() throws Exception{}
	
	@Override
	protected void actFermer() throws Exception {
		closeWorkbenchPart();
	}

	@Override
	protected void actAnnuler() throws Exception {
		actFermer();
	}
	
	@Override
	/** pour button valider**/
	protected void actImprimer() throws Exception {
		askWebService.importTaArticleOScVersBDG();
//		if(validation()){
//			String text = vue.getTfStyleEditeur().getText();
//			//vue.getTfStyleEditeur().get
//			String[] lines = text.split("\r\n|\r|\n");
//			setCommentaire(FacturePlugin.getDefault().getPreferenceStore().getString(PreferenceConstants.COMMENTAIRE));
//			setLibelleFacture(vue.getTfLibelle().getText());
//			String fichierExportation = Platform.getInstanceLocation().getURL().getFile()+"/Erreur_Generation_Document.TXT";
//			String message = "";
//			String messageErreur = "";
//
//			GenCode code=IB_APPLICATION.recupCodeDocument(Const.C_NOM_TA_FACTURE);
//
//
//			StringBuffer ligne = new StringBuffer("");
//
//			FileWriter fw = new FileWriter(fichierExportation);
//			BufferedWriter bw = new BufferedWriter(fw);
//			CallableStatement ps;
//
//			//
//			//cr�er une connection � part pour �viter d'avoir la m�me connection que les factures
//			//en cours de saisie ou de modification dans perspective "Facture"		
//			Database base =new Database();
//			base.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor(
//					Const.C_URL_BDD+Const.C_FICHIER_BDD,Const.C_USER,Const.C_PASS , false, Const.C_DRIVER_JDBC));
//			base.setAutoCommit(false);
//			ps = base.getJdbcConnection().prepareCall("{?,?,? = call GENERATIONFACTURE_LGR(?,?,?,?,?,?)}");
//			ps.registerOutParameter(1,Types.INTEGER);
//			ps.registerOutParameter(2,Types.VARCHAR);
//			ps.registerOutParameter(3,Types.NUMERIC);
//			boolean continuer = true;
//			int nb = 0;
//			BigDecimal totalFacture = new BigDecimal(0);
//			for (int i = 0; i < lines.length; i++) {
//				codeTiers=lines[i];
////				if (!codeTiers.trim().equals("")){
//				if(ClientExiste(codeTiers)){	
//					try {
//						continuer=true;
//						if(ClientPlusieursLicence(codeTiers)){
//							MessageDialog.openWarning(Workbench.getInstance()
//									.getActiveWorkbenchWindow().getShell(),
//									"Attention", "Le code client "+codeTiers+" a plusieurs licences");
//							throw new Exception();
//					}
//						if(!ClientNonTraite(codeTiers)){
//							continuer=MessageDialog.openConfirm(Workbench.getInstance()
//									.getActiveWorkbenchWindow().getShell(),
//									"Attention", "Le code client "+codeTiers+" est d�j� trait�"+finDeLigne
//									+"Voulez-vous le re-traiter ?");
//								
//						}
//						if(continuer){
//						ps.setString(4,LibelleFacture);
//						ps.setString(5,code.getCodeFixe());
//						ps.setInt(6,5);
//						ps.setString(7,codeTiers);
//						ps.setDate(8,new java.sql.Date(vue.getTfDateDocument().getSelection().getTime()));
//						ps.setString(9,commentaire);
//						ps.execute();
//						
//						if(ps.getString(2).equals(""))throw new Exception();
//						message+="Code client : "+codeTiers+" - Document : "+ps.getString(2)+finDeLigne;
//						vue.getTfListeDoc().add(ps.getString(2));
//						nb +=ps.getInt(1);
//						totalFacture=totalFacture.add(ps.getBigDecimal(3));
//						base.commit();
//						}
//					} catch (Exception e) {
//						ligne.append(codeTiers).append(';').append(';');
//						fw.write(ligne.toString());
//						ligne.delete(0,ligne.length());
//						fw.write(finDeLigne);
//						messageErreur+="Code tiers : "+codeTiers+finDeLigne;
//
//						logger.error("",e);
//					}
//				}else{
//					MessageDialog.openWarning(Workbench.getInstance()
//							.getActiveWorkbenchWindow().getShell(),
//							"Attention", "Le code tiers "+codeTiers+" n'est pas valide");
//				}
//			}
//			if(!message.equals("")){
//				MessageDialog.openInformation(Workbench.getInstance()
//						.getActiveWorkbenchWindow().getShell(),
//						"Information", "Les documents : "+finDeLigne+finDeLigne+message+finDeLigne+" ont �t� cr��s correctement.");
//			}
//
//			if(!messageErreur.equals("")){
//				MessageDialog.openWarning(Workbench.getInstance()
//						.getActiveWorkbenchWindow().getShell(),
//						"ATTENTION", "La g�n�ration des documents ne s'est pas d�roul�e correctement."
//						+finDeLigne+finDeLigne+"Les documents sur les clients suivants n'ont pas pu �tre cr��s"+finDeLigne+finDeLigne+
//						messageErreur+finDeLigne);
//			}
//			setNbFactures(getNbFactures()+ nb);
//			setTotalFacture(getTotalFacture().add(totalFacture));
//			bw.close();
//			fw.close();
//			base.closeConnection();	
//		}else{
//			MessageDialog.openWarning(Workbench.getInstance()
//					.getActiveWorkbenchWindow().getShell(),
//					"Attention", "Vous n'avez pas renseign� correctement le libell� de la facture ou le(s) client(s) !!!");
//		}
//
//		actRefresh();	
	}

	private boolean validation(){
		AskWebService sss = new AskWebService();
		int info = sss.obtenirInfoWsorders();
		System.out.println("eee--"+info);
		//System.out.println("eee--");
		
//		if(vue.getTfLibelle().getText().trim().equals(""))
//			return false;
//		Date dateTemp;
//		try {
//			dateTemp = vue.getTfDateDocument().getSelection();
//		SWTInfoEntreprise infosEntreprise = new SWTInfoEntreprise();
//		SWT_IB_TA_INFO_ENTREPRISE.infosEntreprise("0", infosEntreprise);
//		if(infosEntreprise.getDATEDEB_INFO_ENTREPRISE().after(dateTemp)||
//				infosEntreprise.getDATEFIN_INFO_ENTREPRISE().before(dateTemp))
//			return false;
//		} catch (Exception e) {
//			logger.error("", e);
//			return false;
//		}
		return true;
	}
	@Override
	protected void actAide() throws Exception {
		actAide(null);
	}
	
	
	@Override
	protected void actAide(String message) throws Exception {
		if(vue.getTfRecherche().isFocusControl()){
			 KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+Space");
			vue.getTfRecherche().traverse(keyStroke.getModifierKeys());
		}
	}

	
	@Override
	protected void actEnregistrer() throws Exception{
	}
	
	
	@Override
	public void initEtatComposant() {}
	

	@Override
	protected void actRefresh() throws Exception {
		vue.getTfListeTiers().setItems(initAdapterWlgr());
		
		
//		vue.getLaNbWlgr().setText(LibConversion.integerToString(vue.getTfListeTiers().getItemCount())+" bons restants");
//		vue.getLaSumWlgr().setText(LibConversion.bigDecimalToString(sommeRestanteWlgr())+" � � venir");
//		
//		try {
//			//char[] autoActivationCharacters = new char[] {'?'};
//	    KeyStroke keyStroke = KeyStroke.getInstance("Ctrl+Space");
//	    
//		String[][] TabTiers = initAdapterWlgrDecrit();
//		String[] listeCodeTiers = null;
//		String[] listeDescriptionTiers = null;
//		if (TabTiers != null) {
//			listeCodeTiers = new String[TabTiers.length];
//			listeDescriptionTiers = new String[TabTiers.length];
//			for (int i = 0; i < TabTiers.length; i++) {
//				listeCodeTiers[i] = TabTiers[i][0];
//				listeDescriptionTiers[i] = TabTiers[i][1];
//			}
//		}
//		tiersContentProposal = new ContentProposalAdapter(vue
//				.getTfRecherche(), new TextContentAdapter(),
//				new ContentProposalProvider(listeCodeTiers,
//						listeDescriptionTiers), keyStroke, null);
//		tiersContentProposal
//				.setFilterStyle(ContentProposalAdapter.FILTER_NONE);
//		tiersContentProposal
//				.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
//		//tiersContentProposal.setAutoActivationCharacters(autoActivationCharacters);
//		//tiersContentProposal.setAutoActivationDelay(1);
//		//tiersContentProposal.setPropagateKeys(true);
//		tiersContentProposal
//				.addContentProposalListener(new IContentProposalListener() {
//					public void proposalAccepted(IContentProposal proposal) {
//						try {
//
//						} catch (Exception e) {
//							logger.error("", e);
//						}
//					}
//				});	
//		} catch (Exception e) {
//			logger.error("", e);
//		}		
	}

	@Override
	protected void initMapComposantDecoratedField() {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}

	@Override
	protected void sortieChamps() {
		// TODO Raccord de m�thode auto-g�n�r�
		
	}
	
    private boolean trouveCodeTiers(String newValeur){
    	return false;
    	//indexOf(newValeur)!=-1;
    	
    }

	private class HandlerImporter extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
			try {
//	    		String selected[] = vue.getTfListeTiers().getSelection();
//	    		for (int i = 0; i < selected.length; i++) {
//	    			if(!trouveCodeTiers(selected[i])){
//	    				String valeur=vue.getTfStyleEditeur().getText();
//	    				if (!valeur.equals(""))valeur+=finDeLigne;
//	    				vue.getTfStyleEditeur().setText(valeur+selected[i]);
//	    			}
//	    		}
				askWebService = new AskWebService();

				
				String userLogin = vue.getTextLogin().getText();
				String userPassword = vue.getTextPassword().getText();
				askWebService.getInfosOscommerce(userLogin, userPassword);
				
				if(askWebService.getMessageError()!=null){
					vue.getLabelMessage().setText(askWebService.getMessageError());
				}
				askWebService.getConnectionFirebird().commit();
				actRefresh();
//				TaArticleOscDAO taArticleOscDAO = new TaArticleOscDAO();
//				TaArticleOsc taArticleOsc = new TaArticleOsc();
//				taArticleOsc.setIdArticle(99);
//				taArticleOsc.setCodeArticle("99");
//				taArticleOsc.setLibellecArticle("99");
//				taArticleOsc.setStockMinArticle(BigDecimal.valueOf(1.00));
				
//				System.out.println(taArticleOscDAO.findById(42).getLibellecArticle());
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}
	
	private class HandlerSelectCF extends LgrAbstractHandler {

		@Override
		public Object execute(ExecutionEvent event) throws ExecutionException {
			// TODO Auto-generated method stub
			try {
				List<String> listCodeFacture = askWebService.getListCodeFactureOsc();
				String selected[] = vue.getTfListeTiers().getSelection();
				String[] codeFacture = askWebService.getCodeFactureOsc();
				String[] noSelected = null; 
				
				for (int i = 0; i < selected.length; i++) {
					String valeur=vue.getTfStyleEditeur().getText();
					if (!valeur.equals(""))valeur+=finDeLigne;
					listCodeFactureSelected.add(selected[i]);
					listIdFactureSelected.add(askWebService.getMapCodeEtIdFactureOsc().get(selected[i]));
					vue.getTfStyleEditeur().setText(valeur+selected[i]);
				}
				int sizeNoselected = listCodeFacture.size() - listCodeFactureSelected.size();
				noSelected = new String[sizeNoselected];
				int count = 0;
				for (String stringCodeFacture : listCodeFacture) {
					if(!listCodeFactureSelected.contains(stringCodeFacture)){
						noSelected[count] = stringCodeFacture;
						count++;
					}
				}
				vue.getTfListeTiers().setItems(noSelected);
				askWebService.getSommeFactureHtEtSommeFactureTtc(askWebService.getSizeWsOrders().getListWsOrdersProducts(),
															     listIdFactureSelected);
				
				
				
				String SommeFactureHt = NumberFormat.getCurrencyInstance().format(LibCalcul.arrondi(askWebService.getSommeFactureHt()));
				String SommeFactureTtc =NumberFormat.getCurrencyInstance().format(LibCalcul.arrondi(askWebService.getSommeFactureTtc()));
				
				vue.getSommeTotuaxHt().setText(SommeFactureHt);
				vue.getSommeTotauxTtc().setText(SommeFactureTtc);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Erreur : actionPerformed", e);
			}
			
			return event;
		}
		
	}
	
	private class HandlerRajouter extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
			try {
	    		String selected[] = vue.getTfListeTiers().getSelection();
	    		for (int i = 0; i < selected.length; i++) {
	    			if(!trouveCodeTiers(selected[i])){
	    				String valeur=vue.getTfStyleEditeur().getText();
	    				if (!valeur.equals(""))valeur+=finDeLigne;
	    				vue.getTfStyleEditeur().setText(valeur+selected[i]);
	    			}
	    		}
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}	

	
	private class HandlerReinitialiser extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
			try {
				vue.getTfStyleEditeur().setText("");
				vue.getTfListeTiers().setItems(askWebService.getCodeFactureOsc());
				listCodeFactureSelected.clear();
				listIdFactureSelected.clear();
				vue.getSommeTotauxTtc().setText("");
				vue.getSommeTotuaxHt().setText("");
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}
	private class HandlerReinitDocCree extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
			try {
				vue.getTfListeDoc().removeAll();
				setNbFactures(0);
				setTotalFacture(new BigDecimal(0));
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}	

	private class HandlerChargerFichier extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
			try {
				FileDialog dd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
				dd.setFilterExtensions(new String[] {"*.txt"});
				dd.setFilterNames(new String[] {"texte"});
				String repDestination = Platform.getInstanceLocation().getURL().getFile();
				dd.setFilterPath(LibChaine.pathCorrect(repDestination));
				String choix = dd.open();
				System.out.println(choix);
				if(choix!=null){
					FILE_NAME = choix;				
					load();
				}
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}
	
	  private void load() {
		    File file = new File(FILE_NAME);
		    if (file.exists()) {
		      try {
		        BufferedReader reader = new BufferedReader(new FileReader(file));
		        String currLine = reader.readLine();

		        StringTokenizer tokenizer = new StringTokenizer(currLine);
		       // tokenizer.nextToken(); //discard START_TEXT_MARKER
		        String contentLengthString = tokenizer.nextToken();
		        int contentLength = Integer.parseInt(contentLengthString);

		        readContent(reader, contentLength);

//		        //find the beginning of the styles section
//		        while (((currLine = reader.readLine()) != null)
//		            && !START_STYLES_MARK.equals(currLine))
//		        	readStyles(reader, currLine);
		        reader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    }
		  }
	
//	  private void readStyles(BufferedReader reader, String currLine)
//	  throws IOException {
//		  while (!END_STYLES_MARK.equals(currLine)) {
//			  currLine = reader.readLine();
//			  if (!END_STYLES_MARK.equals(currLine))
//				  buildOneStyle(currLine);
//		  }
//	  }
	  
	  

  private void readContent(BufferedReader reader, int contentLength)
      throws IOException {
    char[] buffer = new char[contentLength];
    reader.read(buffer, 0, contentLength);

    vue.getTfStyleEditeur().append(new String(buffer));
  }

//  private void buildOneStyle(String styleText) {
//    StringTokenizer tokenizer = new StringTokenizer(styleText);
//    int startPos = Integer.parseInt(tokenizer.nextToken());
//    int length = Integer.parseInt(tokenizer.nextToken());
//
//    StyleRange style = new StyleRange(startPos, length, null, null,
//        SWT.BOLD);
//    vue.getTfStyleEditeur().setStyleRange(style);
//  }

  private class HandlerRemonterDoc extends LgrAbstractHandler {

	  public Object execute(ExecutionEvent event) throws ExecutionException {
		  try {
			  //fr.legrain.editor.facture.swt.multi
			  if(vue.getTfListeDoc().getSelection()!=null&&vue.getTfListeDoc().getSelection().length>0){										
					String idEditor = "fr.legrain.editor.facture.swt.multi";
					IEditorInput editorInput = new IEditorInput() {
						public boolean exists() {
							// TODO Auto-generated method stub
							return false;
						}

						public ImageDescriptor getImageDescriptor() {
							// TODO Auto-generated method stub
							return null;
						}

						public String getName() {
							// TODO Auto-generated method stub
							return "";
						}

						public IPersistableElement getPersistable() {
							// TODO Auto-generated method stub
							return null;
						}

						public String getToolTipText() {
							// TODO Auto-generated method stub
							return "";
						}

						public Object getAdapter(Class adapter) {
							// TODO Auto-generated method stub
							return null;
						}

					};
					
					if(idEditor!=null) {
						IEditorPart editor = AbstractLgrMultiPageEditor.chercheEditeurDocumentOuvert(idEditor);
						if(editor==null) {
							LgrPartListener.getLgrPartListener().setLgrActivePart(null);
							IEditorPart e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().
							  getActivePage().openEditor(editorInput, idEditor);
//							LgrPartListener.getLgrPartListener().getLgrNavigation().add(e);
//							LgrPartListener.getLgrPartListener().setLgrActivePart(e);

							ParamAffiche paramAffiche = new ParamAffiche();	
							paramAffiche.setCodeDocument(vue.getTfListeDoc().getSelection()[0]);
							paramAffiche.setModeEcran(EnumModeObjet.C_MO_CONSULTATION);
							((AbstractLgrMultiPageEditor)e).findMasterController().configPanel(paramAffiche);
						}

					}				  
			  }
		  } catch (Exception e1) {
			  logger.error("Erreur : actionPerformed", e1);
		  }
		  return event;
	  }
  }
	
	private class HandlerImprimerDoc extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
//			if(vue.getTfListeDoc().getSelectionCount()>0){
//			try {
//				LgrPartListener.getLgrPartListener().setLgrActivePart(null);
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//				FacturePlugin.getDefault().getBundle();
//
//				String[] idFactureAImprimer = new String[vue.getTfListeDoc().getSelection().length];;
//				boolean preview = vue.getCbReExport().getSelection();
//
//				String codeDeb = null;
//				String[] idDoc=null;
//				
//				for (int i = 0; i < vue.getTfListeDoc().getSelection().length; i++) {
//					idDoc=null;
//					codeDeb = vue.getTfListeDoc().getSelection()[i];
//					idDoc=SWT_IB_TA_FACTURE.rechercheFacture(codeDeb, codeDeb);
//					if(idDoc!=null && idDoc.length>0)
//					  idFactureAImprimer[i]=idDoc[0];
//				}
// 
//				///////////////////////////////////////
//				final String[] finalIdFactureAImprimer = idFactureAImprimer;
//				final boolean finalPreview = preview;
//				vue.getParent().getDisplay().asyncExec(new Thread() {
//					public void run() {
//						try {					
//							impressionFacture.imprimer(finalIdFactureAImprimer,finalPreview);
//							ibTaTable.commitLgr();
//							//actFermer();
//						} catch (Exception e) {
//							logger.error("Erreur � l'impression ",e);
//						} finally {
//						}
//					}
//				});
//				
//			} catch (Exception e1) {
//				logger.error("Erreur : actionPerformed", e1);
//			}
//			}
			return event;
		}
	}
//	protected void imprimer(String[] idFactureAImprimer,boolean preview) throws Exception{		
//		//Chemin de l'edition
//		PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		Bundle bundleCourant = FacturePlugin.getDefault().getBundle();
//		URL urlReportFile = Platform.asLocalURL(bundleCourant.getEntry("/report/facture.rptdesign"));
//		URI uriReportFile = new URI("file",urlReportFile.getAuthority(),urlReportFile.getPath(),urlReportFile.getQuery(),null);
//		File reportFile = new File(uriReportFile);
//		
//		//Preparation de l'edition
//		BirtUtil.startReportEngine();
//		
//		SWTInfoEntreprise infoEntreprise = SWT_IB_TA_INFO_ENTREPRISE.infosEntreprise("1",null);
//		
//		HashMap<String,String> reportParam = new HashMap<String,String>();
//		reportParam.put("paramUrlJDBC",IB_APPLICATION.findDatabase().getConnection().getConnectionURL());
//		reportParam.put("capital",infoEntreprise.getCAPITAL_INFO_ENTREPRISE());
//		reportParam.put("ape",infoEntreprise.getAPE_INFO_ENTREPRISE());
//		reportParam.put("siret",infoEntreprise.getSIRET_INFO_ENTREPRISE());
//		reportParam.put("rcs",infoEntreprise.getRCS_INFO_ENTREPRISE());
//		reportParam.put("nomEntreprise",infoEntreprise.getNOM_INFO_ENTREPRISE());
//		
//		LgrSpooler spooler = LgrSpooler.getInstance();
//	
//		final String[] finalIdFactureAImprimer = idFactureAImprimer;
//		final File finalReportFile = reportFile;
//		final HashMap finalReportParam = reportParam;
//		final LgrSpooler finalSpooler = spooler;
//		Job job = new Job("Pr�paration de l'impression") {
//			protected IStatus run(IProgressMonitor monitor) {
//				final int ticks = finalIdFactureAImprimer.length;
//				monitor.beginTask("Pr�paration de l'impression", ticks);
//				try {
//					OutputStream os = null;
//					for (int i = 0; i < finalIdFactureAImprimer.length; i++) {
//						finalReportParam.put("paramID_DOC",finalIdFactureAImprimer[i]);
//						os = BirtUtil.renderReportToStream(finalReportFile.getAbsolutePath(),finalReportParam,BirtUtil.PDF_FORMAT);
//						finalSpooler.add(os);
//						monitor.worked(1);
//						if (monitor.isCanceled()) {
//							finalSpooler.clear();
//							return Status.CANCEL_STATUS;
//						}
//					}
//				} finally {
//					monitor.done();
//				}
//				return Status.OK_STATUS;
//			}
//		};
//		job.setPriority(Job.SHORT);
//		//job.setUser(true);
//		job.schedule(); 
//		job.join();
//		
//		//Impression
//		if(job.getResult()==Status.OK_STATUS)
//			spooler.print(preview);
//		
//		BirtUtil.destroyReportEngine();		
//	}
	
  
	private class HandlerImpressionDiverses extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException {
////			if(vue.getTfListeDoc().getSelectionCount()>0)
//			{
//			try {
//				LgrPartListener.getLgrPartListener().setLgrActivePart(null);
//
//				String[] idFactureAImprimer = new String[0];;
//				boolean preview = vue.getCbReExport().getSelection();
//
//				String codeDeb = null;
//				String[] idDoc=null;
//				
//				for (int i = 0; i < vue.getTfListeDoc().getSelection().length; i++) {
//					idDoc=null;
//					codeDeb = vue.getTfListeDoc().getSelection()[i];
//					idDoc=SWT_IB_TA_FACTURE.rechercheFacture(codeDeb, codeDeb);
//					if(idDoc!=null && idDoc.length>0)
//					  idFactureAImprimer[i]=idDoc[0];
//				}
// 
//				///////////////////////////////////////
//				final String[] finalIdFactureAImprimer = idFactureAImprimer;
//				final boolean finalPreview = preview;
//				vue.getParent().getDisplay().asyncExec(new Thread() {
//					public void run() {
//						try {	
//							//impressionGeneration.imprimer(null,finalPreview);
//							impressionGeneration.imprimer(finalIdFactureAImprimer,finalPreview);
//							ibTaTable.commitLgr();
//							//actFermer();
//						} catch (Exception e) {
//							logger.error("Erreur � l'impression ",e);
//						} finally {
//						}
//					}
//				});
//				
//			} catch (Exception e1) {
//				logger.error("Erreur : actionPerformed", e1);
//			}
//			}
			return event;
		}
	}

	
	class ListItem {
		  public String codeTiers;
		  public String nomTiers;

		  public ListItem(String codeTiers, String nomTiers) {
			  this.codeTiers = codeTiers;
			  this.nomTiers = nomTiers;
		  }
	}
	
	public String[][] initAdapterWlgrDecrit() {
		String requete = "select v.code_client,v.licence, v.nom,(case v.traite when 0 then 'non trait�' else 'trait�' end)as T from v_wlgr v where ttc is not null and ttc<>0 order by 1";
		ibTaTable.getQueryRecherche().close();
		ibTaTable.getQueryRecherche().setQuery(
				new QueryDescriptor(ibTaTable.getFIBBase(),
						requete, true));
		ibTaTable.getQueryRecherche().open();
		String[][] valeurs = null;
		if (!ibTaTable.getQueryRecherche().isEmpty()) {
			valeurs = new String[ibTaTable.getQueryRecherche().getRowCount()][2];
			int i = 0;
			do {
				valeurs[i][0] = ibTaTable.getQueryRecherche().getString("code_client");
				valeurs[i][1] = ibTaTable.getQueryRecherche().getString(
						"licence")
						+ " - "
						+ ibTaTable.getQueryRecherche().getString(
								"nom")
						+ " - "
						+ ibTaTable.getQueryRecherche().getString(
								"T");
				i++;
			} while (ibTaTable.getQueryRecherche().next());
		}
		ibTaTable.getQueryRecherche().close();
		return valeurs;
	}

	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getLibelleFacture() {
		return LibelleFacture;
	}
	public void setLibelleFacture(String libelleFacture) {
		LibelleFacture = libelleFacture;
	}
	
	public boolean ClientNonTraite(String codeClient) throws HeadlessException, Exception {
		QueryDataSet queryRechercheLoc = new QueryDataSet();
		boolean result = false;
		queryRechercheLoc.close();
		queryRechercheLoc.setQuery(new QueryDescriptor(ibTaTable.getFIBBase(),"select v.code_client,v.traite from v_wlgr v where " +
				"code_client like('"+codeClient+"')",true));
		queryRechercheLoc.open();
		DataRow courant = new DataRow(queryRechercheLoc,"traite");
		courant.setShort("traite",new Short("0"));
		result=queryRechercheLoc.locate(courant,Const.C_LOCATE_OPTION_BORLAND_FIRST);
		queryRechercheLoc.close();
		return result;
	}	
	
	public boolean ClientPlusieursLicence(String codeClient) throws HeadlessException, Exception {
		QueryDataSet queryRechercheLoc = new QueryDataSet();
		boolean result = false;
		queryRechercheLoc.close();
		queryRechercheLoc.setQuery(new QueryDescriptor(ibTaTable.getFIBBase(),"select count(*)as nb from ta_wlgr where " +
				"code_client like('"+codeClient+"') ",true));
		queryRechercheLoc.open();
		result=queryRechercheLoc.getInt("nb")>1;
		queryRechercheLoc.close();
		return result;
	}
	
	public BigDecimal sommeRestanteWlgr() throws HeadlessException, Exception {
		QueryDataSet queryRechercheLoc = new QueryDataSet();
		BigDecimal result = new BigDecimal(0);
		queryRechercheLoc.close();
		queryRechercheLoc.setQuery(new QueryDescriptor(ibTaTable.getFIBBase(),
				"select sum(ttc)as sommeRestante from ta_wlgr where " +
				"traite = 0 and ttc > 0 ",true));
		queryRechercheLoc.open();
		result=queryRechercheLoc.getBigDecimal("sommeRestante");
		queryRechercheLoc.close();
		return result;
	}
	
	public boolean ClientExiste(String codeClient) throws HeadlessException, Exception {
		QueryDataSet queryRechercheLoc = new QueryDataSet();
		boolean result = false;
		queryRechercheLoc.close();
		queryRechercheLoc.setQuery(new QueryDescriptor(ibTaTable.getFIBBase(),"select v.code_client,v.traite from v_wlgr v where " +
				"code_client like('"+codeClient+"')",true));
		queryRechercheLoc.open();
		DataRow courant = new DataRow(queryRechercheLoc,"code_client");
		courant.setString("code_client",codeClient);
		result=queryRechercheLoc.locate(courant,Const.C_LOCATE_OPTION_BORLAND_FIRST);
		queryRechercheLoc.close();
		return result;
	}
	public BigDecimal getTotalFacture() {
		return totalFacture;
	}
	public void setTotalFacture(BigDecimal totalFacture) {
		this.totalFacture = totalFacture;
		vue.getLaTotalDoc().setText(LibConversion.bigDecimalToString(totalFacture)+" �");
	}
	public Integer getNbFactures() {
		return nbFactures;
	}
	public void setNbFactures(Integer nbFactures) {
		this.nbFactures = nbFactures;
		vue.getLaNbFacture().setText(LibConversion.integerToString(nbFactures)+" facture(s)");
	}
}

