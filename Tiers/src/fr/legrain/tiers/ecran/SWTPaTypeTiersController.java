package fr.legrain.tiers.ecran;

import java.util.ArrayList;
import java.util.HashMap;
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

import fr.legrain.bdg.tiers.service.remote.ITaTTiersServiceRemote;
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
import fr.legrain.tiers.dto.TaTTiersDTO;
import fr.legrain.tiers.editor.EditorInputTypeTiers;
import fr.legrain.tiers.editor.EditorTypeTiers;
import fr.legrain.tiers.model.TaTTiers;

public class SWTPaTypeTiersController extends EJBBaseControllerSWTStandard
		implements RetourEcranListener {

	static Logger logger = Logger.getLogger(SWTPaTypeTiersController.class.getName());
	private PaTypeTiersSWT vue = null;
	//private TaTTiersDAO dao = null;//new TaTTiersDAO();
	
	private Object ecranAppelant = null;
	private TaTTiersDTO taTTiersDTO;
	private TaTTiersDTO swtOldTypeTiers;
	private Realm realm;
	private DataBindingContext dbc;
	//private ModelTypeTiers modelTypeTiers = new ModelTypeTiers(ibTaTable);

	//private ModelGeneral<TaTTiersDTO> modelTypeTiers;
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedTypeTiers;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();
	private MapChangeListener changeListener = new MapChangeListener();
	private Class classModel = TaTTiersDTO.class;
	private ModelGeneralObjetEJB<TaTTiers,TaTTiersDTO> modelTypeTiers = null;//new ModelGeneralObjet<TaTTiersDTO,TaTTiersDAO,TaTTiers>(dao,classModel);
	
	//private LgrDozerMapper<TaTTiersDTO,TaTTiers> mapperUIToModel  = new LgrDozerMapper<TaTTiersDTO,TaTTiers>();
	private TaTTiers taTTiers = null;
	
	private ITaTTiersServiceRemote dao = null;
	
	private static ITaTTiersServiceRemote doLookup() {
		Context context = null;
		ITaTTiersServiceRemote bean = null;
		try {
			// 1. Obtaining Context
			context = JNDILookupClass.getInitialContext();
			// 2. Generate JNDI Lookup name
			String beanName = "TaTTiersService";
			final String interfaceName = ITaTTiersServiceRemote.class.getName();
			String lookupName = getLookupName(beanName,interfaceName);
			// 3. Lookup and cast
			bean = (ITaTTiersServiceRemote) context.lookup(lookupName);

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public SWTPaTypeTiersController(PaTypeTiersSWT vue) {
		this(vue,null);
	}

	public SWTPaTypeTiersController(PaTypeTiersSWT vue,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
//		dao = new TaTTiersDAO(getEm());
		dao = doLookup();
		modelTypeTiers = new ModelGeneralObjetEJB<TaTTiers,TaTTiersDTO>(dao);
		setVue(vue);

		// a faire avant l'initialisation du Binding
		vue.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldTypeTiers();
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

	public void bind(PaTypeTiersSWT paTypeTiersSWT) {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(paTypeTiersSWT.getGrille());
			tableViewer.createTableCol(classModel,paTypeTiersSWT.getGrille(), nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE,0);
			listeChamp = tableViewer.setListeChampGrille(nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE);

//			ObservableListContentProvider viewerContent = new ObservableListContentProvider();
//			tableViewer.setContentProvider(viewerContent);
//
//			IObservableMap[] attributeMaps = BeansObservables.observeMaps(
//					viewerContent.getKnownElements(), classModel,
//					listeChamp);
//
//			tableViewer.setLabelProvider(new ObservableMapLabelProvider(
//					attributeMaps));
//			writableList = new WritableList(realm, modelTypeTiers
//					.remplirListe(), classModel);
//			tableViewer.setInput(writableList);

			// Set up data binding.
			LgrViewerSupport.bind(tableViewer, 
					new WritableList(modelTypeTiers.remplirListe(), classModel),
					BeanProperties.values(listeChamp)
					);
			selectedTypeTiers = ViewersObservables.observeSingleSelection(tableViewer);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedTypeTiers,classModel);
			changementDeSelection();
			selectedTypeTiers.addChangeListener(new IChangeListener() {

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
		if(selectedTypeTiers!=null && selectedTypeTiers.getValue()!=null) {
			if(((TaTTiersDTO) selectedTypeTiers.getValue()).getId()!=null) {
				try {
					taTTiers = dao.findById(((TaTTiersDTO) selectedTypeTiers.getValue()).getId());
				} catch (FinderException e) {
					logger.error("", e);
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaTypeTiersController.this));
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			if (((ParamAfficheTypeTiers) param).getFocusDefautSWT() != null && !((ParamAfficheTypeTiers) param).getFocusDefautSWT().isDisposed())
				((ParamAfficheTypeTiers) param).getFocusDefautSWT().setFocus();
			else
				((ParamAfficheTypeTiers) param).setFocusDefautSWT(vue.getGrille());
			vue.getLaTitreFormulaire().setText(((ParamAfficheTypeTiers) param).getTitreFormulaire());
			vue.getLaTitreGrille().setText(((ParamAfficheTypeTiers) param).getTitreGrille());
			vue.getLaTitreFenetre().setText(((ParamAfficheTypeTiers) param).getSousTitre());

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}
			
			/*
			 * passage ejb
			Map<String,String[]> map = dao.getParamWhereSQL();
			if(param.getIdFiche()!=null &&  !param.getIdFiche().equals("")) {
				if(map==null) map = new HashMap<String,String[]>();
				map.clear();
				map.put("idTTiers",new String[]{"=",param.getIdFiche()});
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
			tableViewer.tri(classModel, nomClassController, Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
			setSwtOldTypeTiers();

			if (param.getModeEcran() != null
					&& param.getModeEcran().compareTo(EnumModeObjet.C_MO_INSERTION) == 0) {
				try {
					actInserer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			}

			//param.setFocus(ibTaTable.getFModeObjet().getFocusCourant());
		}
		return null;
	}
	
	protected void initVerifySaisie() {
		if (mapInfosVerifSaisie == null)
			mapInfosVerifSaisie = new HashMap<Control, InfosVerifSaisie>();
		
		mapInfosVerifSaisie.put(vue.getTfCODE_T_TIERS(), new InfosVerifSaisie(new TaTTiers(),Const.C_CODE_T_TIERS,null));
		mapInfosVerifSaisie.put(vue.getTfCOMPTE_T_TIERS(), new InfosVerifSaisie(new TaTTiers(),Const.C_COMPTE_T_TIERS,null));
		mapInfosVerifSaisie.put(vue.getTfLIBELLE_T_TIERS(), new InfosVerifSaisie(new TaTTiers(),Const.C_LIBELLE_T_TIERS,null));

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

		vue.getTfCODE_T_TIERS().setToolTipText("CODE_T_TIERS");
		vue.getTfCOMPTE_T_TIERS().setToolTipText("COMPTE_T_TIERS");
		vue.getTfLIBELLE_T_TIERS().setToolTipText("LIBELLE_T_TIERS");

		mapComposantChamps.put(vue.getTfCODE_T_TIERS(), Const.C_CODE_T_TIERS);
		mapComposantChamps.put(vue.getTfCOMPTE_T_TIERS(), Const.C_COMPTE_T_TIERS);
		mapComposantChamps.put(vue.getTfLIBELLE_T_TIERS(),
				Const.C_LIBELLE_T_TIERS);

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
				.getTfCODE_T_TIERS());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfCODE_T_TIERS());
		mapInitFocus.put(EnumModeObjet.C_MO_CONSULTATION, vue
				.getGrille());

		activeModifytListener();
		
		vue.getTfCOMPTE_T_TIERS().addVerifyListener(queDesChiffres);
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

	public SWTPaTypeTiersController getThis() {
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
					.getString("TypeTiers.Message.Enregistrer"))) {

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
				setListeEntity(getModelTypeTiers().remplirListe());
				dao.initValeurIdTable(taTTiers);
				fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
						dao.getChampIdEntite(), dao.getValeurIdTable(),
						selectedTypeTiers.getValue())));
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
				setSwtOldTypeTiers();
				
				taTTiersDTO = new TaTTiersDTO();
				
				taTTiers = new TaTTiers();
				//dao.inserer(taTTiers);
				
				modelTypeTiers.getListeObjet().add(taTTiersDTO);
				writableList = new WritableList(realm, modelTypeTiers.getListeObjet(),classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(taTTiersDTO));
				
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
				setSwtOldTypeTiers();
				taTTiers = dao.findById(((TaTTiersDTO) selectedTypeTiers.getValue()).getId());
			}else{
				if(!setSwtOldTypeTiersRefresh())throw new Exception();
			}						
			dao.modifier(taTTiers);
			
			modeEcran.setMode(EnumModeObjet.C_MO_EDITION);//ejb
			initEtatBouton();
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		}
	}
	
	public boolean containsEntity(TaTTiers entite){
		if(modelTypeTiers.getListeEntity()!=null){
			for (Object e : modelTypeTiers.getListeEntity()) {
				if(((TaTTiers)e).getIdTTiers()==
					entite.getIdTTiers())return true;
			}
		}
		return false;
	}

	public boolean setSwtOldTypeTiersRefresh() {
//passage ejb
//		try {	
//			if (selectedTypeTiers.getValue()!=null){
//				TaTTiers taArticleOld =dao.findById(taTTiers.getIdTTiers());
//				taArticleOld=dao.refresh(taArticleOld);
//				if(containsEntity(taTTiers)) 
//					modelTypeTiers.getListeEntity().remove(taTTiers);
//				if(!taTTiers.getVersionObj().equals(taArticleOld.getVersionObj())){
//					taTTiers=taArticleOld;
//					if(!containsEntity(taTTiers)) 
//						modelTypeTiers.getListeEntity().add(taTTiers);					
//					actRefresh();
//					dao.messageNonAutoriseModification();
//				}
//				taTTiers=taArticleOld;
//				if(!containsEntity(taTTiers)) 
//					modelTypeTiers.getListeEntity().add(taTTiers);
//				changementDeSelection();
//				this.swtOldTypeTiers=taTTiersDTO.copy((TaTTiersDTO)selectedTypeTiers.getValue());
//			}
			return true;
//		} catch (Exception e) {
//			return false;
//		}		
	}

//	public void setSwtOldTypeTiersRefresh() {
//		if (selectedTypeTiers.getValue()!=null){
//			if(LgrMess.isDOSSIER_EN_RESEAU())dao.refresh(dao.findById(((TaTTiersDTO) selectedTypeTiers.getValue()).getIdTTiers()));
//			taTTiers=dao.findById(((TaTTiersDTO) selectedTypeTiers.getValue()).getIdTTiers());
//			try {
//				if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
//			} catch (Exception e) {
//				logger.error("",e);
//			}			
//			this.swtOldTypeTiers=TaTTiersDTO.copy((TaTTiersDTO)selectedTypeTiers.getValue());
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
					.getString("TypeTiers.Message.Supprimer"))) {

//			dao.begin(transaction);
				TaTTiers u = dao.findById(((TaTTiersDTO) selectedTypeTiers.getValue()).getId());
				dao.supprimer(u);
				
//			dao.commit(transaction);
			Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
			modelTypeTiers.removeEntity(taTTiers);
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
			// //InputVerifier inputVerifier =
			// getFocusCourant().getInputVerifier();
			// //getFocusCourant().setInputVerifier(null);
			//switch (ibTaTable.getFModeObjet().getMode()) {
			switch (getModeEcran().getMode()) {
			case C_MO_INSERTION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("TypeTiers.Message.Annuler")))) {
					remetTousLesChampsApresAnnulationSWT(dbc);
					if(((TaTTiersDTO) selectedTypeTiers.getValue()).getId()==null){
					modelTypeTiers.getListeObjet().remove(
							((TaTTiersDTO) selectedTypeTiers.getValue()));
					writableList = new WritableList(realm, modelTypeTiers
							.getListeObjet(), classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.selectionGrille(0);
					}
					dao.annuler(taTTiers);
					hideDecoratedFields();
				}
				getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
				initEtatBouton();
				break;
			case C_MO_EDITION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("TypeTiers.Message.Annuler")))) {
					int rang = modelTypeTiers.getListeObjet().indexOf(selectedTypeTiers.getValue());
					remetTousLesChampsApresAnnulationSWT(dbc);
					modelTypeTiers.getListeObjet().set(rang, swtOldTypeTiers);
					writableList = new WritableList(realm, modelTypeTiers.getListeObjet(), classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldTypeTiers), true);
					dao.annuler(taTTiers);
					hideDecoratedFields();
				}
				getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
				initEtatBouton();

				break;
			case C_MO_CONSULTATION:
				actionFermer.run();
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
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaTTiers.class.getSimpleName()+".head");
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaTTiers.class.getSimpleName()+".detail");
//		
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablHead = fonctionGetInfosXmlAndProperties.getMapAttributeTablHead(); 
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablDetail = fonctionGetInfosXmlAndProperties.getMapAttributeTablDetail();
//		
//		Collection<TaTTiers> collectionTaTTiers = modelTypeTiers.getListeEntity();
//		
//		fonctionGetInfosXmlAndProperties.findInfosFileXml(TaTTiers.class.getName(),TaTTiersDTO.class.getName(),
//				listeChamp,"mapping");
//		fonctionGetInfosXmlAndProperties.getInfosObjetJPA(taTTiers);
//
//		ConstEdition constEdition = new ConstEdition(); 
////		Impression impression = new Impression(constEdition,taTTiers,collectionTaTTiers,nomChampIdTable,taTTiers.getIdTTiers());
//		String nomDossier = null;
//
//		int nombreLine = collectionTaTTiers.size();
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
//			String folderEditionDynamique = Const.C_RCP_INSTANCE_LOCATION+"/"+Const.C_NOM_PROJET_TMP+"/"+TaTTiers.class.getSimpleName();
//			constEdition.makeFolderEditions(folderEditionDynamique);
//			Path pathFileReport = new Path(folderEditionDynamique+"/"+Const.C_NOM_VU_T_TIERS+".rptdesign");
//			final String pathFileReportDynamic = pathFileReport.toPortableString();
//
//			MakeDynamiqueReport DynamiqueReport = new MakeDynamiqueReport(constEdition.getNameTableEcran(),
//					constEdition.getNameTableBDD(),pathFileReportDynamic,Const.C_NOM_VU_T_TIERS,
//					ConstEdition.PAGE_ORIENTATION_LANDSCAPE,nomDossier); 
//			DynamiqueReport.setSimpleNameEntity(TaTTiers.class.getSimpleName());
//			/**************************************************************/
//			DynamiqueReport.setFonctionGetInfosXml(fonctionGetInfosXmlAndProperties);
//			DynamiqueReport.setNomObjet(TaTTel.class.getSimpleName());
//			/**************************************************************/
//
//			Map<String, AttributElementResport> attribuGridHeader = new LinkedHashMap<String, AttributElementResport>();
//			String nameHeaderTitle = ConstEditionTiers.TITLE_EDITION_TYPE_TELEPHONES;
//			attribuGridHeader.put(nameHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_XX_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE,""));
//			String nameSousHeaderTitle = ConstEditionTiers.SOUS_TITLE_EDITION_TYPE_TELEPHONES;
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
//					Const.C_NOM_VU_T_TIERS,1,1,2,"40", mapAttributeTablHead, mapAttributeTablDetail);
//			DynamiqueReport.savsAsDesignHandle();
////			impression.imprimer(true,pathFileReportDynamic,null,"Type Tiers",TaTTiers.class.getSimpleName(),false);
//
//			/** 01/03/2010 modifier les editions (zhaolin) **/
//			BaseImpressionEdition impressionEdition = new BaseImpressionEdition(constEdition,taTTiers,
//					getEm(),collectionTaTTiers,taTTiers.getIdTTiers());
//			impressionEdition.impressionEditionTypeEntity(pathFileReportDynamic,"Tiers");
//		}
	}
	
	@Override
	protected boolean aideDisponible() {
		boolean result = false;
		//switch ((getThis().ibTaTable.getFModeObjet().getMode())) {
		switch (SWTPaTypeTiersController.this.getModeEcran().getMode()) {
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
				//paramAfficheAideRecherche.setDb(getThis().getIbTaTable().getFIBBase());
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
				//SWTBaseControllerSWTStandard controllerEcranCreation = null;
				EJBBaseControllerSWTStandard controllerEcranCreation = null;
				ParamAffiche parametreEcranCreation = null;
				IEditorPart editorCreation = null;
				String editorCreationId = null;
				IEditorInput editorInputCreation = null;
				Shell s2 = new Shell(s, LgrShellUtil.styleLgr);

				//switch ((getThis().ibTaTable.getFModeObjet().getMode())) {
				switch ((SWTPaTypeTiersController.this.getModeEcran().getMode())) {
				case C_MO_CONSULTATION:
					if(getFocusCourantSWT().equals(vue.getGrille())){
						PaTypeTiersSWT paTTiersSWT = new PaTypeTiersSWT(s2,SWT.NULL);
						SWTPaTypeTiersController swtPaTTiersController = new SWTPaTypeTiersController(paTTiersSWT);

						editorCreationId = EditorTypeTiers.ID;
						editorInputCreation = new EditorInputTypeTiers();

						ParamAfficheTypeTiers paramAfficheTTiers = new ParamAfficheTypeTiers();
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAfficheTTiers.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheTTiers.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaTTiersController;
						parametreEcranCreation = paramAfficheTTiers;

						paramAfficheAideRecherche.setTypeEntite(TaTTiers.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_T_TIERS);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_T_TIERS().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(swtPaTTiersController.getModelTypeTiers());
						paramAfficheAideRecherche.setTypeObjet(swtPaTTiersController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_T_TIERS);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
					break;
				default:
					break;
				}

				//if (paramAfficheAideRecherche.getQuery() != null) {
				if (paramAfficheAideRecherche.getJPQLQuery() != null) {

					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s,
							SWT.NULL);
					SWTPaAideRechercheControllerSWT paAideRechercheController1 = new SWTPaAideRechercheControllerSWT(
							paAideRecherche1);

					// Parametrage de la recherche
					paramAfficheAideRecherche
					.setFocusSWT(((PaAideRechercheSWT) paAideRechercheController1
							.getVue()).getTfChoix());
					paramAfficheAideRecherche
					.setRefCreationSWT(controllerEcranCreation);
					paramAfficheAideRecherche.setEditorCreation(editorCreation);
					paramAfficheAideRecherche.setEditorCreationId(editorCreationId);
					paramAfficheAideRecherche.setEditorInputCreation(editorInputCreation);
					paramAfficheAideRecherche.setParamEcranCreation(parametreEcranCreation);
					paramAfficheAideRecherche.setShellCreation(s2);
					paAideRechercheController1.configPanel(paramAfficheAideRecherche);
					// paramAfficheAideRecherche.setFocusDefaut(paAideRechercheController1.getVue().getTfChoix());

					// Ajout d'une recherche
					paAideController.addRecherche(paAideRechercheController1,
							paramAfficheAideRecherche.getTitreRecherche());

					// Parametrage de l'ecran d'aide principal
					ParamAfficheAideSWT paramAfficheAideSWT = new ParamAfficheAideSWT();
					ParamAfficheAide paramAfficheAide = new ParamAfficheAide();

					// enregistrement pour le retour de l'ecran d'aide
					paAideController.addRetourEcranListener(getThis());
					//Control focus = vue.getShell().getDisplay().getFocusControl();
					// affichage de l'ecran d'aide principal (+ ses recherches)

					dbc.getValidationStatusMap().removeMapChangeListener(changeListener);
					//LgrShellUtil.afficheAideSWT(paramAfficheAide, null, paAide,paAideController, s);
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
		
		AbstractApplicationDAOClient<TaTTiersDTO> a = new AbstractApplicationDAOClient<TaTTiersDTO>();
		
		if(TYPE_VALIDATION==VALIDATION_CLIENT || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR) {
			//BeanValidatorJFaceDatabinding validator = new BeanValidatorJFaceDatabinding(TaTCiviliteDTO.class,nomChamp);
			//resultat = validator.validate(selectedTypeCivilite.getValue());
			try {
				TaTTiersDTO f = new TaTTiersDTO();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				//a.validate((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp, null);
				a.validate(f, nomChamp, ITaTTiersServiceRemote.validationContext);
			} catch(Exception e) {
				//if(resultat==null) {
					//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				//}
			}
		}
		if(resultat==null && (TYPE_VALIDATION==VALIDATION_SERVEUR || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR)) {
			try {
				TaTTiersDTO f = new TaTTiersDTO();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				//dao.validateDTOProperty((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp);
				dao.validateDTOProperty(f, nomChamp,ITaTTiersServiceRemote.validationContext);
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
//			//nomChamp = "codeUnite";
////			IStatus s = ValidationStatus.ok();
////			MultiStatus m = new MultiStatus("1",0,new IStatus[]{s},"Interface",null);
//			IStatus s = null;
//			boolean verrouilleModifCode = false;
//			int change=0;
//
//				//TaArticle u = null;
//				//u = dao.findById(((SWTArticle) selectedArticle.getValue()).getIdArticle());
//				TaTTiers u = new TaTTiers();
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//				if(((TaTTiersDTO) selectedTypeTiers.getValue()).getId()!=null) {
//					u.setIdTTiers(((TaTTiersDTO) selectedTypeTiers.getValue()).getId());
//				}
//				
//				//LgrDozerMapper<SWTArticle,TaArticle> mapper = new LgrDozerMapper<SWTArticle,TaArticle>();
//				//mapper.map((SWTArticle) selectedArticle.getValue(),taArticle);
//				//dao.validate(u,nomChamp);
//				if(nomChamp.equals(Const.C_CODE_T_TIERS)){
//					verrouilleModifCode = true;
//				}
//
//				s = dao.validate(u,nomChamp,validationContext,verrouilleModifCode);
//				
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
		
//		return s;
	}
	
	@Override
	protected void actEnregistrer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();
		try {
//			dao.begin(transaction);
			
			//TODO ejb, controle à remettre
//			ctrlUnChampsSWT(getFocusCourantSWT());
			ctrlTousLesChampsAvantEnregistrementSWT();
			
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
				//u = dao.findById(((SWTUnite) selectedUnite.getValue()).getIdUnite());

//				LgrDozerMapper<TaTTiersDTO,TaTTiers> mapper = new LgrDozerMapper<TaTTiersDTO,TaTTiers>();
//				mapper.map((TaTTiersDTO) selectedTypeTiers.getValue(),taTTiers);
//				
//				taTTiers=dao.enregistrerMerge(taTTiers);
				
//mapper sur client, envoi d'une entité					
//				TaTTiersMapper mapper = new TaTTiersMapper();
//				mapper.mapDtoToEntity((TaTTiersDTO) selectedTypeTiers.getValue(),taTTiers);
//				taTTiers=dao.enregistrerMerge(taTTiers);
				
				dao.enregistrerMergeDTO((TaTTiersDTO) selectedTypeTiers.getValue(),ITaTTiersServiceRemote.validationContext);

				
				
			} else if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)) {
//				LgrDozerMapper<TaTTiersDTO,TaTTiers> mapper = new LgrDozerMapper<TaTTiersDTO,TaTTiers>();
//				mapper.map((TaTTiersDTO) selectedTypeTiers.getValue(),taTTiers);
//
//				taTTiers=dao.enregistrerMerge(taTTiers);
//				modelTypeTiers.getListeEntity().add(taTTiers);
				
//mapper sur client, envoi d'une entité					
//				TaTTiersMapper mapper = new TaTTiersMapper();
//				mapper.mapDtoToEntity((TaTTiersDTO) selectedTypeTiers.getValue(),taTTiers);
//				dao.enregistrerPersist(taTTiers);
				
				dao.enregistrerPersistDTO((TaTTiersDTO) selectedTypeTiers.getValue(),ITaTTiersServiceRemote.validationContext);

			}
//			dao.commit(transaction);
			
			modelTypeTiers.addEntity(taTTiers);
			
			getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
//			transaction = null;
			
			actRefresh(); //deja present
			
			//nettoyage affichage erreur possible
			hideDecoratedFields();
			vue.getLaMessage().setText("");
		} catch(Exception e) {
			logger.error("",e);
			if(e.getMessage()!=null)
				vue.getLaMessage().setText(e.getMessage());
			
			afficheDecoratedField(vue.getTfCODE_T_TIERS(),e.getMessage());
			
		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
		}
	}

	public void initEtatComposant() {
		try {
			vue.getTfCODE_T_TIERS().setEditable(!isUtilise());
			changeCouleur(vue);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
		}
	}
	
	public boolean isUtilise() {
		return (
				selectedTypeTiers !=null
				&& ((TaTTiersDTO)selectedTypeTiers.getValue()).getId()!=null 
				&& !dao.recordModifiable(
					dao.getNomTable(),
					((TaTTiersDTO)selectedTypeTiers.getValue()).getId()
					)
				)
				|| !dao.autoriseModification(taTTiers);		
	}
	
	public TaTTiersDTO getSwtOldTypeTiers() {
		return swtOldTypeTiers;
	}

	public void setSwtOldTypeTiers(TaTTiersDTO swtOldTypeTiers) {
		this.swtOldTypeTiers = swtOldTypeTiers;
	}

	public void setSwtOldTypeTiers() {
		if (selectedTypeTiers.getValue() != null)
			this.swtOldTypeTiers = taTTiersDTO.copy((TaTTiersDTO) selectedTypeTiers.getValue());
		else {
			if (tableViewer.selectionGrille(0)){
			this.swtOldTypeTiers = taTTiersDTO.copy((TaTTiersDTO) selectedTypeTiers.getValue());
			tableViewer.setSelection(new StructuredSelection(
					(TaTTiersDTO) selectedTypeTiers.getValue()), true);
		}}
	}

	public void setVue(PaTypeTiersSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();
		mapComposantDecoratedField.put(vue.getTfCODE_T_TIERS(), vue.getFieldCODE_T_TIERS());
		mapComposantDecoratedField.put(vue.getTfCOMPTE_T_TIERS(), vue.getFieldCOMPTE_T_TIERS());
		mapComposantDecoratedField.put(vue.getTfLIBELLE_T_TIERS(), vue.getFieldLIBELLE_T_TIERS());
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
//		int row = vue.getGrille().getSelectionIndex();
		writableList = new WritableList(realm, modelTypeTiers.remplirListe(), classModel);
//		int i=0;
//		for (Object element : modelTypeTiers.remplirListe()) {
//			if(((TaTTiersDTO)element).getID_T_TIERS().equals(LibConversion.stringToInteger(ibTaTable.valeurIdTable)))
//				row=i;
//			i++;
//		}
		tableViewer.setInput(writableList);
		tableViewer.refresh();
		tableViewer.selectionGrille(
				tableViewer.selectionGrille(selectedTypeTiers.getValue()));
//		tableViewer.selectionGrille(row);	
	}

	//public ModelTypeTiers getModelTypeTiers() {
	public ModelGeneralObjetEJB<TaTTiers,TaTTiersDTO> getModelTypeTiers() {
		return modelTypeTiers;
	}

	public ITaTTiersServiceRemote getDao() {
		return dao;
	}

	public void actAfficherTous() throws Exception{
		vue.getGrille().setVisible(true);  
		vue.getBtnTous().setVisible(false);
		vue.getLaTitreGrille().setVisible(true);
		vue.getCompositeForm().setWeights(new int[]{50,100});
		dao.setJPQLQuery(dao.getJPQLQueryInitial());
		modelTypeTiers.setJPQLQuery(null);
		modelTypeTiers.setListeEntity(null);
		try {
			actRefresh();
		} catch (Exception e1) {
			logger.error("", e1);
		}
	}
}
