package fr.legrain.tiers.ecran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.ejb.FinderException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import fr.legrain.bdg.tiers.service.remote.ITaTWebServiceRemote;
import fr.legrain.gestCom.Appli.Const;
import fr.legrain.gestCom.Appli.ModelGeneralObjetEJB;
import fr.legrain.gestCom.librairiesEcran.editor.EditorAide;
import fr.legrain.gestCom.librairiesEcran.editor.EditorInputAide;
import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.LgrShellUtil;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheAideRechercheSWT;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheAideSWT;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideControllerSWT;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideRechercheControllerSWT;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBLgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.InfosVerifSaisie;
import fr.legrain.lib.data.ModeObjet;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.VerrouInterface;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.lib.gui.ResultAffiche;
import fr.legrain.lib.gui.RetourEcranEvent;
import fr.legrain.lib.gui.RetourEcranListener;
import fr.legrain.lib.gui.aide.PaAideRechercheSWT;
import fr.legrain.lib.gui.aide.PaAideSWT;
import fr.legrain.lib.gui.aide.ParamAfficheAide;
import fr.legrain.lib.gui.aide.ResultAide;
import fr.legrain.lib.gui.grille.LgrTableViewer;
import fr.legrain.lib.gui.grille.LgrViewerSupport;
import fr.legrain.lib.validator.AbstractApplicationDAOClient;
import fr.legrain.libMessageLGR.LgrMess;
import fr.legrain.tiers.TiersPlugin;
import fr.legrain.tiers.clientutility.JNDILookupClass;
import fr.legrain.tiers.dto.TaTWebDTO;
import fr.legrain.tiers.editor.EditorInputTypeWeb;
import fr.legrain.tiers.editor.EditorTypeWeb;
import fr.legrain.tiers.model.TaTWeb;

public class SWTPaTypeWebController extends EJBBaseControllerSWTStandard
		implements RetourEcranListener {

	static Logger logger = Logger.getLogger(SWTPaTypeWebController.class.getName());
	private PaTypeWebSWT vue = null;
//	private TaTWebDAO dao = null;//new TaTWebDAO();

	private Object ecranAppelant = null;
	private TaTWebDTO taTWebDTO;
	private TaTWebDTO swtOldTypeWeb;
	private Realm realm;
	private DataBindingContext dbc;

	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedTypeWeb;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();
	private MapChangeListener changeListener = new MapChangeListener();
	private Class classModel = TaTWebDTO.class;
	private ModelGeneralObjetEJB<TaTWeb,TaTWebDTO> modelTypeWeb = null;//new ModelGeneralObjetEJB<TaTWebDTO,TaTWebDAO,TaTWeb>(dao,classModel);
	
//	private LgrDozerMapper<TaTWebDTO,TaTWeb> mapperUIToModel  = new LgrDozerMapper<TaTWebDTO,TaTWeb>();
	private TaTWeb taTWeb = null;
	
	private ITaTWebServiceRemote dao = null;
	
	private static ITaTWebServiceRemote doLookup() {
		Context context = null;
		ITaTWebServiceRemote bean = null;
		try {
			// 1. Obtaining Context
			context = JNDILookupClass.getInitialContext();
			// 2. Generate JNDI Lookup name
			String beanName = "TaTWebService";
			final String interfaceName = ITaTWebServiceRemote.class.getName();
			String lookupName = getLookupName(beanName,interfaceName);
			// 3. Lookup and cast
			bean = (ITaTWebServiceRemote) context.lookup(lookupName);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	public SWTPaTypeWebController(PaTypeWebSWT vue) {
		this(vue,null);
	}

	public SWTPaTypeWebController(PaTypeWebSWT vue,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
//		dao = new TaTWebDAO(getEm());
		dao = doLookup();
		modelTypeWeb = new ModelGeneralObjetEJB<TaTWeb,TaTWebDTO>(dao);
		setVue(vue);

		// a faire avant l'initialisation du Binding
		vue.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldTypeWeb();
			}
		});
		vue.getShell().addShellListener(this);

		// Branchement action annuler : empeche la fermeture automatique de la
		// fenetre sur ESC
		vue.getShell().addTraverseListener(new Traverse());

		initController();
	}

	private void initController() {
		try {
			setGrille(vue.getGrille());
			initSashFormWeight();
			setDaoStandard(dao);
			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);
			
			initMapComposantChamps();
			initVerifySaisie();
			initMapComposantDecoratedField();
			listeComponentFocusableSWT(listeComposantFocusable);
			initFocusOrder();
			initActions();
			initDeplacementSaisie(listeComposantFocusable);

			branchementBouton();

			Menu popupMenuFormulaire = new Menu(vue.getShell(), SWT.POP_UP);
			Menu popupMenuGrille = new Menu(vue.getShell(), SWT.POP_UP);
			Menu[] tabPopups = new Menu[] { popupMenuFormulaire,popupMenuGrille };
			this.initPopupAndButtons(mapActions, tabPopups);
			vue.getPaCorpsFormulaire().setMenu(popupMenuFormulaire);
			vue.getPaGrille().setMenu(popupMenuGrille);

			initEtatBouton();
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			logger.error("Erreur : PaTiersController", e);
		}
	}

	/**
	 * Creation des objet pour l'interface, a partir de l'entite principale.<br>
	 * @return
	 */
	
	public void bind(PaTypeWebSWT paTypeWebSWT) {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(paTypeWebSWT.getGrille());
			tableViewer.createTableCol(classModel,paTypeWebSWT.getGrille(), nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE,0);
			listeChamp = tableViewer.setListeChampGrille(nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE);

//			ObservableListContentProvider viewerContent = new ObservableListContentProvider();
//			tableViewer.setContentProvider(viewerContent);
//
//			IObservableMap[] attributeMaps = BeansObservables.observeMaps(
//					viewerContent.getKnownElements(), classModel,listeChamp);
//
//			tableViewer.setLabelProvider(new ObservableMapLabelProvider(attributeMaps));
//			writableList = new WritableList(realm, modelTypeWeb.remplirListe(), classModel);
//			tableViewer.setInput(writableList);
			
			// Set up data binding.
			LgrViewerSupport.bind(tableViewer, 
					new WritableList(modelTypeWeb.remplirListe(), classModel),
					BeanProperties.values(listeChamp)
					);

			selectedTypeWeb = ViewersObservables.observeSingleSelection(tableViewer);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedTypeWeb,classModel);
			changementDeSelection();
			selectedTypeWeb.addChangeListener(new IChangeListener() {

				public void handleChange(ChangeEvent event) {
					changementDeSelection();
				}

			});

		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			logger.error("", e);
		}
	}
	
	private void changementDeSelection() {
		if(selectedTypeWeb!=null && selectedTypeWeb.getValue()!=null) {
			if(((TaTWebDTO) selectedTypeWeb.getValue()).getId()!=null) {
				try {
					taTWeb = dao.findById(((TaTWebDTO) selectedTypeWeb.getValue()).getId());
				} catch (FinderException e) {
					logger.error("", e);
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaTypeWebController.this));
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			if (((ParamAfficheTypeWeb) param).getFocusDefautSWT() != null && !((ParamAfficheTypeWeb) param).getFocusDefautSWT().isDisposed())
				((ParamAfficheTypeWeb) param).getFocusDefautSWT().setFocus();
			else
				((ParamAfficheTypeWeb) param).setFocusDefautSWT(vue.getGrille());
			vue.getLaTitreFormulaire().setText(((ParamAfficheTypeWeb) param).getTitreFormulaire());
			vue.getLaTitreGrille().setText(((ParamAfficheTypeWeb) param).getTitreGrille());
			vue.getLaTitreFenetre().setText(((ParamAfficheTypeWeb) param).getSousTitre());

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}
			
			/*
			 * passage ejb
			Map<String,String[]> map = dao.getParamWhereSQL();
			if(param.getIdFiche()!=null &&  !param.getIdFiche().equals("")) {
				if(map==null) map = new HashMap<String,String[]>();
				map.clear();
				map.put("idTWeb",new String[]{"=",param.getIdFiche()});
				dao.setParamWhereSQL(map);
				vue.getBtnTous().setVisible(true);
				vue.getGrille().setVisible(false);
				vue.getLaTitreGrille().setVisible(false);
				vue.getCompositeForm().setWeights(new int[]{0,100});					
			}
			*/
			
			bind(vue);
			// permet de se positionner sur le 1er enregistrement et de remplir
			// le formulaire
			tableViewer.selectionGrille(0);
			tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
			setSwtOldTypeWeb();

			if (param.getModeEcran() != null
					&& param.getModeEcran().compareTo(EnumModeObjet.C_MO_INSERTION) == 0) {
				try {
					actInserer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			}
		}
		return null;
	}
	
	protected void initVerifySaisie() {
		if (mapInfosVerifSaisie == null)
			mapInfosVerifSaisie = new HashMap<Control, InfosVerifSaisie>();
		
		mapInfosVerifSaisie.put(vue.getTfCODE_T_WEB(), new InfosVerifSaisie(new TaTWeb(),Const.C_CODE_T_WEB,null));
		mapInfosVerifSaisie.put(vue.getTfLABELLE_T_WEB(), new InfosVerifSaisie(new TaTWeb(),Const.C_LIBL_T_WEB,null));

		initVerifyListener(mapInfosVerifSaisie, dao);
	}

	protected void initComposantsVue() throws ExceptLgr {
	}

	protected void initMapComposantChamps() {
		if (mapComposantChamps == null)
			mapComposantChamps = new LinkedHashMap<Control, String>();

		if (listeComposantFocusable == null)
			listeComposantFocusable = new ArrayList<Control>();
		listeComposantFocusable.add(vue.getGrille());

		vue.getTfCODE_T_WEB().setToolTipText(Const.C_CODE_T_WEB);
		vue.getTfLABELLE_T_WEB().setToolTipText(Const.C_LIBL_T_WEB);


		mapComposantChamps.put(vue.getTfCODE_T_WEB(), Const.C_CODE_T_WEB);
		mapComposantChamps.put(vue.getTfLABELLE_T_WEB(), Const.C_LIBL_T_WEB);

		for (Control c : mapComposantChamps.keySet()) {
			listeComposantFocusable.add(c);
		}

		listeComposantFocusable.add(vue.getBtnEnregistrer());
		listeComposantFocusable.add(vue.getBtnInserer());
		listeComposantFocusable.add(vue.getBtnModifier());
		listeComposantFocusable.add(vue.getBtnSupprimer());
		listeComposantFocusable.add(vue.getBtnFermer());
		listeComposantFocusable.add(vue.getBtnAnnuler());
		listeComposantFocusable.add(vue.getBtnImprimer());
		listeComposantFocusable.add(vue.getBtnTous());

		if (mapInitFocus == null)
			mapInitFocus = new LinkedHashMap<EnumModeObjet, Control>();
		mapInitFocus.put(EnumModeObjet.C_MO_INSERTION, vue
				.getTfCODE_T_WEB());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfCODE_T_WEB());
		mapInitFocus.put(EnumModeObjet.C_MO_CONSULTATION, vue
				.getGrille());

		activeModifytListener();
	}

	protected void initActions() {
		mapCommand = new HashMap<String, IHandler>();
		
		mapCommand.put(C_COMMAND_GLOBAL_MODIFIER_ID, handlerModifier);
		mapCommand.put(C_COMMAND_GLOBAL_ANNULER_ID, handlerAnnuler);
		mapCommand.put(C_COMMAND_GLOBAL_ENREGISTRER_ID, handlerEnregistrer);
		mapCommand.put(C_COMMAND_GLOBAL_INSERER_ID, handlerInserer);
		mapCommand.put(C_COMMAND_GLOBAL_SUPPRIMER_ID, handlerSupprimer);
		mapCommand.put(C_COMMAND_GLOBAL_REFRESH_ID, handlerRefresh);
		mapCommand.put(C_COMMAND_GLOBAL_AIDE_ID, handlerAide);
		mapCommand.put(C_COMMAND_GLOBAL_FERMER_ID, handlerFermer);
		mapCommand.put(C_COMMAND_GLOBAL_IMPRIMER_ID, handlerImprimer);
		//passage ejb
		//mapCommand.put(C_COMMAND_DOCUMENT_AFFICHER_TOUS_ID, handlerAfficherTous);
		
		initFocusCommand(String.valueOf(this.hashCode()),listeComposantFocusable,mapCommand);
		
		if (mapActions == null)
			mapActions = new LinkedHashMap<Button, Object>();
		
		mapActions.put(vue.getBtnAnnuler(), C_COMMAND_GLOBAL_ANNULER_ID);
		mapActions.put(vue.getBtnEnregistrer(), C_COMMAND_GLOBAL_ENREGISTRER_ID);
		mapActions.put(vue.getBtnInserer(), C_COMMAND_GLOBAL_INSERER_ID);
		mapActions.put(vue.getBtnModifier(), C_COMMAND_GLOBAL_MODIFIER_ID);
		mapActions.put(vue.getBtnSupprimer(), C_COMMAND_GLOBAL_SUPPRIMER_ID);
		mapActions.put(vue.getBtnFermer(), C_COMMAND_GLOBAL_FERMER_ID);
		mapActions.put(vue.getBtnImprimer(), C_COMMAND_GLOBAL_IMPRIMER_ID);
		//passage ejb
		//mapActions.put(vue.getBtnTous(), C_COMMAND_DOCUMENT_AFFICHER_TOUS_ID);

		Object[] tabActionsAutres = new Object[] { C_COMMAND_GLOBAL_AIDE_ID, C_COMMAND_GLOBAL_REFRESH_ID };
		mapActions.put(null, tabActionsAutres);
	}

	public SWTPaTypeWebController getThis() {
		return this;
	}

	@Override
	public boolean onClose() throws ExceptLgr {
		boolean retour = true;
		VerrouInterface.setVerrouille(true);
		switch (getModeEcran().getMode()) {
		case C_MO_INSERTION:
		case C_MO_EDITION:
			if (MessageDialog.openQuestion(vue.getShell(), MessagesEcran
					.getString("Message.Attention"), MessagesEcran
					.getString("TypeWeb.Message.Enregistrer"))) {

				try {
					actEnregistrer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			} else
			break;
		case C_MO_CONSULTATION:
			break;
		default:
			break;
		}

		if (retour) {
			if (ecranAppelant instanceof SWTPaAideControllerSWT) {
				setListeEntity(getModelTypeWeb().remplirListe());
				dao.initValeurIdTable(taTWeb);
				fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
						dao.getChampIdEntite(), dao.getValeurIdTable(),
						selectedTypeWeb.getValue())));

				retour = true;
			}
		}
		return retour;
	}

	public void retourEcran(final RetourEcranEvent evt) {
		if (evt.getRetour() != null
				&& (evt.getSource() instanceof SWTPaAideControllerSWT)) {
			if (getFocusAvantAideSWT() instanceof Text) {
				try {
					((Text) getFocusAvantAideSWT()).setText(((ResultAffiche) evt.getRetour()).getResult());					
					ctrlUnChampsSWT(getFocusAvantAideSWT());
				} catch (Exception e) {
					logger.error("",e);
					vue.getLaMessage().setText(e.getMessage());
				}
			}
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
					if(((ResultAffiche) evt.getRetour()).getSelection()!=null)
						tableViewer.setSelection(((ResultAffiche) evt.getRetour()).getSelection(),true);
				}
			}
		} else if (evt.getRetour() != null) {
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
					if(((ResultAffiche) evt.getRetour()).getSelection()!=null)
						tableViewer.setSelection(((ResultAffiche) evt.getRetour()).getSelection(),true);
				}
			}
		}
		super.retourEcran(evt);
	}

	@Override
	protected void actInserer() throws Exception {
		try {
			if(getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_CONSULTATION)==0) {
				
				VerrouInterface.setVerrouille(true);
				setSwtOldTypeWeb();
				
				taTWebDTO = new TaTWebDTO();
				
				taTWeb = new TaTWeb();
//				dao.inserer(taTWeb);
				
				modelTypeWeb.getListeObjet().add(taTWebDTO);
				writableList = new WritableList(realm, modelTypeWeb.getListeObjet(), classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(taTWebDTO));
				
				getModeEcran().setMode(EnumModeObjet.C_MO_INSERTION);//ejb
				initEtatBouton();
			}

		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		} finally {
			initEtatComposant();
			VerrouInterface.setVerrouille(false);
		}
	}

	@Override
	protected void actModifier() throws Exception {
		try {
			if(!LgrMess.isDOSSIER_EN_RESEAU()){
				setSwtOldTypeWeb();
				taTWeb = dao.findById(((TaTWebDTO) selectedTypeWeb.getValue()).getId());
			}else{
				if(!setSwtOldTypeWebRefresh())throw new Exception();
			}			
			dao.modifier(taTWeb);
			
			modeEcran.setMode(EnumModeObjet.C_MO_EDITION);//ejb
			initEtatBouton();
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		}
	}
	
	public boolean containsEntity(TaTWeb entite){
		if(modelTypeWeb.getListeEntity()!=null){
			for (Object e : modelTypeWeb.getListeEntity()) {
				if(((TaTWeb)e).getIdTWeb()==
					entite.getIdTWeb())return true;
			}
		}
		return false;
	}

	public boolean setSwtOldTypeWebRefresh() {
//passage ejb
//		try {	
//			if (selectedTypeWeb.getValue()!=null){
//				TaTWeb taArticleOld =dao.findById(taTWeb.getIdTWeb());
//				taArticleOld=dao.refresh(taArticleOld);
//				if(containsEntity(taTWeb)) 
//					modelTypeWeb.getListeEntity().remove(taTWeb);
//				if(!taTWeb.getVersionObj().equals(taArticleOld.getVersionObj())){
//					taTWeb=taArticleOld;
//					if(!containsEntity(taTWeb)) 
//						modelTypeWeb.getListeEntity().add(taTWeb);					
//					actRefresh();
//					dao.messageNonAutoriseModification();
//				}
//				taTWeb=taArticleOld;
//				if(!containsEntity(taTWeb)) 
//					modelTypeWeb.getListeEntity().add(taTWeb);
//				changementDeSelection();
//				this.swtOldTypeWeb=TaTWebDTO.copy((TaTWebDTO)selectedTypeWeb.getValue());
//			}
			return true;
//		} catch (Exception e) {
//			return false;
//		}		
	}

//	public void setSwtOldTypeWebRefresh() {
//		if (selectedTypeWeb.getValue()!=null){
//			if(LgrMess.isDOSSIER_EN_RESEAU())dao.refresh(dao.findById(((TaTWebDTO) selectedTypeWeb.getValue()).getIdTWeb()));
//			taTWeb=dao.findById(((TaTWebDTO) selectedTypeWeb.getValue()).getIdTWeb());
//			try {
//				if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
//			} catch (Exception e) {
//				logger.error("",e);
//			}			
//			this.swtOldTypeWeb=TaTWebDTO.copy((TaTWebDTO)selectedTypeWeb.getValue());
//		}
//	}
	
	@Override
	protected void actSupprimer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();
		try {
			VerrouInterface.setVerrouille(true);
			if(isUtilise())MessageDialog.openInformation(vue.getShell(), MessagesEcran
					.getString("Message.Attention"), MessagesEcran
					.getString("Message.suppression"));
			else		
			if (MessageDialog.openConfirm(vue.getShell(), MessagesEcran
					.getString("Message.Attention"), MessagesEcran
					.getString("TypeWeb.Message.Supprimer"))) {

//			dao.begin(transaction);
				TaTWeb u = dao.findById(((TaTWebDTO) selectedTypeWeb.getValue()).getId());
				dao.supprimer(u);		
				
//			dao.commit(transaction);
			Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
			modelTypeWeb.removeEntity(taTWeb);
			taTWeb=null;

			modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
			if(suivant!=null)tableViewer.setSelection(new StructuredSelection(suivant),true);
			else tableViewer.selectionGrille(0);
			actRefresh(); //ajouter pour tester jpa

			}
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
			VerrouInterface.setVerrouille(false);
		}
	}

	@Override
	protected void actFermer() throws Exception {
		// (controles de sortie et fermeture de la fenetre) => onClose()
		if(onClose())
		closeWorkbenchPart();
	}

	@Override
	protected void actAnnuler() throws Exception {
		// // verifier si l'objet est en modification ou en consultation
		// // si modification ou insertion, alors demander si annulation des
		// // modifications si ok, alors annulation si pas ok, alors arreter le
		// processus d'annulation
		// // si consultation declencher l'action "fermer".
		try {
			VerrouInterface.setVerrouille(true);
			switch (getModeEcran().getMode()) {
			case C_MO_INSERTION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("TypeWeb.Message.Annuler")))) {
					remetTousLesChampsApresAnnulationSWT(dbc);
					if(((TaTWebDTO) selectedTypeWeb.getValue()).getId()==null){
					modelTypeWeb.getListeObjet().remove(((TaTWebDTO) selectedTypeWeb.getValue()));
					writableList = new WritableList(realm, modelTypeWeb.getListeObjet(), classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.selectionGrille(0);
					}
					dao.annuler(taTWeb);
					hideDecoratedFields();
				}
				getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
				initEtatBouton();
				break;
			case C_MO_EDITION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("TypeWeb.Message.Annuler")))) {
					int rang = modelTypeWeb.getListeObjet().indexOf(selectedTypeWeb.getValue());
					remetTousLesChampsApresAnnulationSWT(dbc);
					modelTypeWeb.getListeObjet().set(rang, swtOldTypeWeb);
					writableList = new WritableList(realm, modelTypeWeb.getListeObjet(), classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldTypeWeb), true);
					dao.annuler(taTWeb);
					hideDecoratedFields();
				}
				getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
				initEtatBouton();

				break;
			case C_MO_CONSULTATION:
				actFermer();
				break;
			default:
				break;
			}
		} finally {
			VerrouInterface.setVerrouille(false);
		}
	}

	@Override
	protected void actImprimer() throws Exception {
//passage ejb
//		TaInfoEntrepriseDAO taInfoEntrepriseDAO = new TaInfoEntrepriseDAO(getEm());
//		TaInfoEntreprise taInfoEntreprise = taInfoEntrepriseDAO.findInstance();
//
//		String nomChampIdTable =  dao.getChampIdTable();
//
//		FonctionGetInfosXmlAndProperties fonctionGetInfosXmlAndProperties = new FonctionGetInfosXmlAndProperties(mapperUIToModel);
//		fonctionGetInfosXmlAndProperties.cleanValueMapAttributeTable();
//
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaTWeb.class.getSimpleName()+".head");
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaTWeb.class.getSimpleName()+".detail");
//		
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablHead = fonctionGetInfosXmlAndProperties.getMapAttributeTablHead(); 
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablDetail = fonctionGetInfosXmlAndProperties.getMapAttributeTablDetail();
//		
//		Collection<TaTWeb> collectionTaTWeb = modelTypeWeb.getListeEntity();
//		
//		fonctionGetInfosXmlAndProperties.findInfosFileXml(TaTWeb.class.getName(),TaTWebDTO.class.getName(),
//				listeChamp,"mapping");
//		fonctionGetInfosXmlAndProperties.getInfosObjetJPA(taTWeb);
//
//		ConstEdition constEdition = new ConstEdition(); 
////		Impression impression = new Impression(constEdition,taTWeb,collectionTaTWeb,nomChampIdTable,taTWeb.getIdTWeb());
//		String nomDossier = null;
//
//		int nombreLine = collectionTaTWeb.size();
//
//		if(nombreLine==0){
//			MessageDialog.openWarning(vue.getShell(), ConstEdition.TITRE_MESSAGE_EDITION_VIDE,
//					ConstEdition.EDITION_VIDE);
//		}
//		else{
//			if(taInfoEntreprise.getIdInfoEntreprise()==0){
//				nomDossier = ConstEdition.INFOS_VIDE;
//			}
//			else{
//				nomDossier = taInfoEntreprise.getNomInfoEntreprise();	
//			}
//
//			constEdition.addValueList(tableViewer, nomClassController);
//
//			/**
//			 * pathFileReport ==> le path de ficher de edition dynamique
//			 */
//			String folderEditionDynamique = Const.C_RCP_INSTANCE_LOCATION+"/"+Const.C_NOM_PROJET_TMP+"/"+TaTWeb.class.getSimpleName();
//			constEdition.makeFolderEditions(folderEditionDynamique);
//			Path pathFileReport = new Path(folderEditionDynamique+"/"+Const.C_NOM_VU_T_WEB+".rptdesign");
//			final String pathFileReportDynamic = pathFileReport.toPortableString();
//
//			MakeDynamiqueReport DynamiqueReport = new MakeDynamiqueReport(constEdition.getNameTableEcran(),
//					constEdition.getNameTableBDD(),pathFileReportDynamic,Const.C_NOM_VU_T_WEB,
//					ConstEdition.PAGE_ORIENTATION_LANDSCAPE,nomDossier); 
//			DynamiqueReport.setSimpleNameEntity(TaTWeb.class.getSimpleName());
//			/**************************************************************/
//			DynamiqueReport.setFonctionGetInfosXml(fonctionGetInfosXmlAndProperties);
//			DynamiqueReport.setNomObjet(TaTWeb.class.getSimpleName());
//			/**************************************************************/
//
//			Map<String, AttributElementResport> attribuGridHeader = new LinkedHashMap<String, AttributElementResport>();
//			String nameHeaderTitle = ConstEditionTiers.TITLE_EDITION_TYPE_WEBS;
//			attribuGridHeader.put(nameHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_XX_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE,""));
//			String nameSousHeaderTitle = ConstEditionTiers.SOUS_TITLE_EDITION_TYPE_WEBS;
//			attribuGridHeader.put(nameSousHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_X_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE,ConstEdition.COLOR_GRAY));
//
//			//DynamiqueReport.buildDesignConfig();
//			ConstEdition.CONTENT_COMMENTS = ConstEditionTiers.COMMENTAIRE_EDITION_DEFAUT;
//			DynamiqueReport.initializeBuildDesignReportConfig();
//			DynamiqueReport.makePageMater("1", "1", "1", "1", "100");
//			DynamiqueReport.makeReportHeaderGrid(3,5,100,ConstEdition.UNITS_PERCENTAGE,attribuGridHeader);
////			DynamiqueReport.makeReportTableDB(100,ConstEdition.UNITS_PERCENTAGE,
////					Const.C_NOM_VU_T_TEL,attribuTabHeader,attribuTabDetail,1,1,2,5,"30");
//			DynamiqueReport.biuldTableReport("100", ConstEdition.UNITS_PERCENTAGE, 
//					Const.C_NOM_VU_T_WEB,1,1,2,"40", mapAttributeTablHead, mapAttributeTablDetail);
//			DynamiqueReport.savsAsDesignHandle();
////			impression.imprimer(true,pathFileReportDynamic,null,"Type Web",TaTWeb.class.getSimpleName(),false);
//			
//			/** 01/03/2010 modifier les editions (zhaolin) **/
//			BaseImpressionEdition impressionEdition = new BaseImpressionEdition(constEdition,taTWeb,
//					getEm(),collectionTaTWeb,taTWeb.getIdTWeb());
//			impressionEdition.impressionEditionTypeEntity(pathFileReportDynamic,"Web");
//		}
	}
	
	@Override
	protected boolean aideDisponible() {
		boolean result = false;
		switch ((SWTPaTypeWebController.this.getModeEcran().getMode())) {
		case C_MO_CONSULTATION:
			if(getFocusCourantSWT().equals(vue.getGrille()))
				result = true;
			break;
		case C_MO_EDITION:
		case C_MO_INSERTION:
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	protected void actAide() throws Exception {
		actAide(null);
	}

	@Override
	protected void actAide(String message) throws Exception {
		if(aideDisponible()){
			try {
				VerrouInterface.setVerrouille(true);
				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
				ParamAfficheAideRechercheSWT paramAfficheAideRecherche = new ParamAfficheAideRechercheSWT();
				paramAfficheAideRecherche.setMessage(message);
				// Creation de l'ecran d'aide principal
				Shell s = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),LgrShellUtil.styleLgr);
				PaAideSWT paAide = new PaAideSWT(s, SWT.NONE);
				SWTPaAideControllerSWT paAideController = new SWTPaAideControllerSWT(paAide);
				/***************************************************************/
				LgrPartListener.getLgrPartListener().setLgrActivePart(null);
				IEditorPart e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new EditorInputAide(), EditorAide.ID);
				LgrPartListener.getLgrPartListener().setLgrActivePart(e);
				LgrPartListener.getLgrPartListener().getLgrNavigation().add(e);
				paAideController = new SWTPaAideControllerSWT(((EditorAide)e).getComposite());
				((EJBLgrEditorPart)e).setController(paAideController);
				((EJBLgrEditorPart)e).setPanel(((EditorAide)e).getComposite());
				/***************************************************************/
				EJBBaseControllerSWTStandard controllerEcranCreation = null;
				ParamAffiche parametreEcranCreation = null;
				IEditorPart editorCreation = null;
				String editorCreationId = null;
				IEditorInput editorInputCreation = null;
				Shell s2 = new Shell(s, LgrShellUtil.styleLgr);

				switch ((SWTPaTypeWebController.this.getModeEcran().getMode())) {
				case C_MO_CONSULTATION:
					if(getFocusCourantSWT().equals(vue.getGrille())){
						PaTypeWebSWT paTypeWebSWT = new PaTypeWebSWT(s2,SWT.NULL);
						SWTPaTypeWebController swtPaTypeWebController = new SWTPaTypeWebController(paTypeWebSWT);

						editorCreationId = EditorTypeWeb.ID;
						editorInputCreation = new EditorInputTypeWeb();

						ParamAfficheTypeWeb paramAfficheTypeWeb = new ParamAfficheTypeWeb();
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAfficheTypeWeb.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheTypeWeb.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaTypeWebController;
						parametreEcranCreation = paramAfficheTypeWeb;

						paramAfficheAideRecherche.setTypeEntite(TaTWeb.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_T_WEB);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_T_WEB().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(swtPaTypeWebController.getModelTypeWeb());
						paramAfficheAideRecherche.setTypeObjet(swtPaTypeWebController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_T_WEB);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
					break;
				default:
					break;
				}

				if (paramAfficheAideRecherche.getJPQLQuery() != null) {

					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s,SWT.NULL);
					SWTPaAideRechercheControllerSWT paAideRechercheController1 = new SWTPaAideRechercheControllerSWT(
							paAideRecherche1);

					// Parametrage de la recherche
					paramAfficheAideRecherche.setFocusSWT(((PaAideRechercheSWT) paAideRechercheController1.getVue()).getTfChoix());
					paramAfficheAideRecherche.setRefCreationSWT(controllerEcranCreation);
					paramAfficheAideRecherche.setEditorCreation(editorCreation);
					paramAfficheAideRecherche.setEditorCreationId(editorCreationId);
					paramAfficheAideRecherche.setEditorInputCreation(editorInputCreation);
					paramAfficheAideRecherche.setParamEcranCreation(parametreEcranCreation);
					paramAfficheAideRecherche.setShellCreation(s2);
					paAideRechercheController1.configPanel(paramAfficheAideRecherche);

					// Ajout d'une recherche
					paAideController.addRecherche(paAideRechercheController1,
							paramAfficheAideRecherche.getTitreRecherche());

					// Parametrage de l'ecran d'aide principal
					ParamAfficheAideSWT paramAfficheAideSWT = new ParamAfficheAideSWT();
					ParamAfficheAide paramAfficheAide = new ParamAfficheAide();

					// enregistrement pour le retour de l'ecran d'aide
					paAideController.addRetourEcranListener(getThis());
					Control focus = vue.getShell().getDisplay().getFocusControl();
					// affichage de l'ecran d'aide principal (+ ses recherches)

					dbc.getValidationStatusMap().removeMapChangeListener(changeListener);
					/*****************************************************************/
					paAideController.configPanel(paramAfficheAide);
					/*****************************************************************/
					dbc.getValidationStatusMap().addMapChangeListener(changeListener);

				}

			} finally {
				VerrouInterface.setVerrouille(false);
				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
			}
		}
	}
	public IStatus validateUI() {
		return null;
	}
	
	public IStatus validateUIField(String nomChamp,Object value) {
		IStatus resultat = new Status(IStatus.OK,TiersPlugin.PLUGIN_ID,"validateUIField champ : "+nomChamp+" valeur : "+value!=null?value.toString():"valeur nulle"+" validation OK");
		
		int VALIDATION_CLIENT = 1;
		int VALIDATION_SERVEUR = 2;
		int VALIDATION_CLIENT_ET_SERVEUR = 3;
		
		//int TYPE_VALIDATION = VALIDATION_CLIENT;
		//int TYPE_VALIDATION = VALIDATION_SERVEUR;
		int TYPE_VALIDATION = VALIDATION_CLIENT_ET_SERVEUR;
		
		AbstractApplicationDAOClient<TaTWebDTO> a = new AbstractApplicationDAOClient<TaTWebDTO>();
		
		if(TYPE_VALIDATION==VALIDATION_CLIENT || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR) {
			//BeanValidatorJFaceDatabinding validator = new BeanValidatorJFaceDatabinding(TaTCiviliteDTO.class,nomChamp);
			//resultat = validator.validate(selectedTypeCivilite.getValue());
			try {
				TaTWebDTO f = new TaTWebDTO();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				//a.validate((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp, null);
				a.validate(f, nomChamp, ITaTWebServiceRemote.validationContext);
			} catch(Exception e) {
				//if(resultat==null) {
					//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				//}
			}
		}
		if(resultat==null && (TYPE_VALIDATION==VALIDATION_SERVEUR || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR)) {
			try {
				TaTWebDTO f = new TaTWebDTO();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				//dao.validateDTOProperty((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp);
				dao.validateDTOProperty(f, nomChamp,ITaTWebServiceRemote.validationContext);
			} catch(Exception e) {
				//if(resultat==null) {
					//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				//}
				//e.printStackTrace();
			}
		}
		
		return resultat;
		
//		try {
//			IStatus s = null;
//			boolean verrouilleModifCode = false;
//			int change=0;
//				TaTWeb u = new TaTWeb();
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//				if(((TaTWebDTO) selectedTypeWeb.getValue()).getIdTWeb()!=null) {
//					u.setIdTWeb(((TaTWebDTO) selectedTypeWeb.getValue()).getIdTWeb());
//				}
//				if(nomChamp.equals(Const.C_CODE_T_WEB)){
//					verrouilleModifCode = true;
//				}
//
//				s = dao.validate(u,nomChamp,validationContext,verrouilleModifCode);
//				if(s.getSeverity()!=IStatus.ERROR && change!=0) {
//					  
//				}
//			return s;
//		} catch (IllegalAccessException e) {
//			logger.error("",e);
//		} catch (InvocationTargetException e) {
//			logger.error("",e);
//		} catch (NoSuchMethodException e) {
//			logger.error("",e);
//		}
//		return null;
	}
	
	@Override
	protected void actEnregistrer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();
		try {
			ctrlUnChampsSWT(getFocusCourantSWT());
			ctrlTousLesChampsAvantEnregistrementSWT();
//			dao.begin(transaction);
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
//				LgrDozerMapper<TaTWebDTO,TaTWeb> mapper = new LgrDozerMapper<TaTWebDTO,TaTWeb>();
//				mapper.map((TaTWebDTO) selectedTypeWeb.getValue(),taTWeb);
//				taTWeb=dao.enregistrerMerge(taTWeb);
				
//mapper sur client, envoi d'une entité					
//				TaTWebMapper mapper = new TaTWebMapper();
//				mapper.mapDtoToEntity((TaTWebDTO) selectedTypeWeb.getValue(),taTWeb);
//				taTWeb=dao.enregistrerMerge(taTWeb);
				
				dao.enregistrerMergeDTO((TaTWebDTO) selectedTypeWeb.getValue(),ITaTWebServiceRemote.validationContext);

			} else if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)) {
				
//mapper sur client, envoi d'une entité					
//				TaTWebMapper mapper = new TaTWebMapper();
//				mapper.mapDtoToEntity((TaTWebDTO) selectedTypeWeb.getValue(),taTWeb);
//				dao.enregistrerPersist(taTWeb);
				
				dao.enregistrerPersistDTO((TaTWebDTO) selectedTypeWeb.getValue(),ITaTWebServiceRemote.validationContext);

			} 
//			dao.commit(transaction);
			modelTypeWeb.addEntity(taTWeb);
			getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
//			transaction = null;
			actRefresh();
			
			//nettoyage affichage erreur possible
			hideDecoratedFields();
			vue.getLaMessage().setText("");
		} catch(Exception e) {
			logger.error("",e);
			if(e.getMessage()!=null)
				vue.getLaMessage().setText(e.getMessage());
			
			afficheDecoratedField(vue.getTfCODE_T_WEB(),e.getMessage());

		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
		}
	}


	public void initEtatComposant() {
		try {
			vue.getTfCODE_T_WEB().setEditable(!isUtilise());
			//vue.getTfLABELLE_T_WEB().setEditable(!isUtilise());
			changeCouleur(vue);
	    } catch (Exception e) {
		vue.getLaMessage().setText(e.getMessage());
	    }
	}

	public boolean isUtilise(){
		return (((TaTWebDTO)selectedTypeWeb.getValue()).getId()!=null &&
		!dao.recordModifiable(dao.getNomTable(),
				((TaTWebDTO)selectedTypeWeb.getValue()).getId()))||
				!dao.autoriseModification(taTWeb);		
	}
	
	public TaTWebDTO getSwtOldTypeWeb() {
		return swtOldTypeWeb;
	}

	public void setSwtOldTypeWeb(TaTWebDTO swtOldTypeWeb) {
		this.swtOldTypeWeb = swtOldTypeWeb;
	}
	

	public void setSwtOldTypeWeb() {
		if (selectedTypeWeb.getValue() != null)
			this.swtOldTypeWeb = TaTWebDTO.copy((TaTWebDTO) selectedTypeWeb.getValue());
		else {
			if(tableViewer.selectionGrille(0)){
				this.swtOldTypeWeb = TaTWebDTO.copy((TaTWebDTO) selectedTypeWeb.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(TaTWebDTO) selectedTypeWeb.getValue()), true);
			}
		}
	}
	public void setVue(PaTypeWebSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();
		mapComposantDecoratedField.put(vue.getTfCODE_T_WEB(), vue.getFieldCODE_T_WEB());
		mapComposantDecoratedField.put(vue.getTfLABELLE_T_WEB(), vue.getFieldLABELLE_T_WEB());
	}

	public Class getClassModel() {
		return classModel;
	}

	@Override
	protected void sortieChamps() {
		// TODO Raccord de methode auto-genere
		
	}

	@Override
	protected void actRefresh() throws Exception {
		TaTWeb u = taTWeb;
		if (u!=null && u.getIdTWeb()==0)
			u=dao.findById(u.getIdTWeb());
		writableList = new WritableList(realm, modelTypeWeb.remplirListe(), classModel);
		tableViewer.setInput(writableList);
		tableViewer.refresh();
		tableViewer.selectionGrille(
				tableViewer.selectionGrille(selectedTypeWeb.getValue()));
		
		if(u != null) {
			Iterator<TaTWebDTO> ite = modelTypeWeb.getListeObjet().iterator();
			TaTWebDTO tmp = null;
			int i = 0;
			boolean trouve = false;
			while (ite.hasNext() && !trouve) {
				tmp = ite.next();
				if(tmp.getId()==u.getIdTWeb()) {
					tableViewer.setSelection(new StructuredSelection(tmp));
					trouve = true;
				}
				i++;
			}
		}	
	}

	public ModelGeneralObjetEJB<TaTWeb,TaTWebDTO> getModelTypeWeb() {
		return modelTypeWeb;
	}

	public ITaTWebServiceRemote getDao() {
		return dao;
	}


	public void actAfficherTous() throws Exception{
		vue.getGrille().setVisible(true);  
		vue.getBtnTous().setVisible(false);
		vue.getLaTitreGrille().setVisible(true);
		vue.getCompositeForm().setWeights(new int[]{50,100});
		dao.setJPQLQuery(dao.getJPQLQueryInitial());
		modelTypeWeb.setJPQLQuery(null);
		modelTypeWeb.setListeEntity(null);
		try {
			actRefresh();
		} catch (Exception e1) {
			logger.error("", e1);
		}
	}

}
