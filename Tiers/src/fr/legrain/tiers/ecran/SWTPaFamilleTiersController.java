package fr.legrain.tiers.ecran;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
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
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
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

import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.model.mapping.mapper.TaFamilleTiersMapper;
import fr.legrain.bdg.tiers.service.remote.ITaFamilleTiersServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTiersServiceRemote;
import fr.legrain.gestCom.Appli.Const;
//import fr.legrain.gestCom.Appli.LgrDozerMapper;
import fr.legrain.gestCom.Appli.ModelGeneralObjetEJB;
import fr.legrain.gestCom.librairiesEcran.editor.EditorAide;
import fr.legrain.gestCom.librairiesEcran.editor.EditorInputAide;
import fr.legrain.gestCom.librairiesEcran.swt.DeclencheCommandeControllerEvent;
import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.LgrShellUtil;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheAideRechercheSWT;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheAideSWT;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideControllerSWT;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideRechercheControllerSWT;
import fr.legrain.gestCom.librairiesEcran.workbench.AnnuleToutEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDePageEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBLgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.InfosVerifSaisie;
import fr.legrain.lib.data.ModeObjet;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.VerrouInterface;
import fr.legrain.lib.ejb.EJBLookup;
import fr.legrain.lib.gui.DestroyEvent;
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
import fr.legrain.tiers.dto.TaAdresseDTO;
import fr.legrain.tiers.dto.TaFamilleTiersDTO;
import fr.legrain.tiers.editor.EditorFamille;
import fr.legrain.tiers.editor.EditorFamilleTiers;
import fr.legrain.tiers.editor.EditorInputFamille;
import fr.legrain.tiers.editor.EditorInputFamilleTiers;
import fr.legrain.tiers.model.TaFamilleTiers;
import fr.legrain.tiers.model.TaTiers;

public class SWTPaFamilleTiersController extends EJBBaseControllerSWTStandard
		implements RetourEcranListener {

	static Logger logger = Logger.getLogger(SWTPaFamilleTiersController.class.getName());
	private PaFamilleSWT vue = null;
//	private SWT_IB_TA_R_COMMERCIAL dao = new SWT_IB_TA_R_COMMERCIAL();
	private ITaFamilleTiersServiceRemote dao = null;//new TaFamilleTiersDAO();
	
	private Integer idTiers = null;

	// private String idCommentaire = null;
	// private String idTypeTiers = null;
	private Object ecranAppelant = null;
	private TaFamilleTiersDTO swtFamilleTiers;
	private TaFamilleTiersDTO swtOldFamilleTiers;
	private Realm realm;
	private DataBindingContext dbc;

	private Class classModel = TaFamilleTiersDTO.class;
//	private ModelGeneral<TaFamilleTiersDTO> modelCommercial = new ModelGeneral<TaFamilleTiersDTO>(dao.getFIBQuery(),classModel);
	private ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO> modelFamilleTiers = null;//new ModelGeneralObjet<TaFamilleTiersDTO,TaFamilleTiersDAO,TaFamilleTiers>(dao,classModel);
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedFamilleTiers;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();
	
	private TaTiers masterEntity = null;
	private ITaTiersServiceRemote masterDAO = null;

	private LgrDozerMapper<TaFamilleTiersDTO,TaFamilleTiers> mapperUIToModel  = new LgrDozerMapper<TaFamilleTiersDTO,TaFamilleTiers>();
	private LgrDozerMapper<TaFamilleTiers,TaFamilleTiersDTO> mapperModelToUI  = new LgrDozerMapper<TaFamilleTiers,TaFamilleTiersDTO>();
	private TaFamilleTiers taFamilleTiers = null;
	
	private MapChangeListener changeListener = new MapChangeListener();

	public SWTPaFamilleTiersController(PaFamilleSWT vue) {
		this(vue,null);
	}

	public SWTPaFamilleTiersController(PaFamilleSWT vue,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
//		dao = new TaFamilleTiersDAO(getEm());
		
		try {
			dao = new EJBLookup<ITaFamilleTiersServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_FAMILLE_TIERS_SERVICE, ITaFamilleTiersServiceRemote.class.getName());
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		modelFamilleTiers = new ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO>(dao);
		setVue(vue);

		// a faire avant l'initialisation du Binding
		vue.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldCommercial();
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
			Menu[] tabPopups = new Menu[] { popupMenuFormulaire,
					popupMenuGrille };
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
	public List<TaFamilleTiersDTO> IHMmodel() {
		LinkedList<TaFamilleTiers> ldao = new LinkedList<TaFamilleTiers>();
		LinkedList<TaFamilleTiersDTO> lswt = new LinkedList<TaFamilleTiersDTO>();

		if(masterEntity!=null && !masterEntity.getTaFamilleTierses().isEmpty()) {

			ldao.addAll(masterEntity.getTaFamilleTierses());

//			LgrDozerMapper<TaFamilleTiers,TaFamilleTiersDTO> mapper = new LgrDozerMapper<TaFamilleTiers,TaFamilleTiersDTO>();
			TaFamilleTiersMapper mapper = new TaFamilleTiersMapper();
			for (TaFamilleTiers o : ldao) {
				TaFamilleTiersDTO t = null;
//				t = (TaFamilleTiersDTO) mapper.map(o, TaFamilleTiersDTO.class);
				t = (TaFamilleTiersDTO) mapper.mapEntityToDto(o, null);
				lswt.add(t);
			}

		}
		return lswt;
	}
	
	protected void initEtatBouton() {
		initEtatBouton(IHMmodel());
	}
	
	public void bind(PaFamilleSWT paCommercialSWT) {
		try {
			// modelCommercial = new ModelGeneral<TaFamilleTiersDTO>(ibTaTable.getFIBQuery(),classModel);
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(paCommercialSWT.getGrille());
			tableViewer.createTableCol(classModel,paCommercialSWT.getGrille(), nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE,0);
			listeChamp = tableViewer.setListeChampGrille(nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE);

			ObservableListContentProvider viewerContent = new ObservableListContentProvider();
			tableViewer.setContentProvider(viewerContent);

//			IObservableMap[] attributeMaps = BeansObservables.observeMaps(
//					viewerContent.getKnownElements(), classModel,
//					listeChamp);
//
//			tableViewer.setLabelProvider(new ObservableMapLabelProvider(attributeMaps));
//			List<TaFamilleTiersDTO> lswt = IHMmodel();
//			writableList = new WritableList(realm, lswt, classModel);
//			tableViewer.setInput(writableList);
			
			// Set up data binding.
			LgrViewerSupport.bind(tableViewer, 
					new WritableList(IHMmodel(), classModel),
					BeanProperties.values(listeChamp)
					);

			selectedFamilleTiers = ViewersObservables
					.observeSingleSelection(tableViewer);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedFamilleTiers,classModel);
			changementDeSelection();
			selectedFamilleTiers.addChangeListener(new IChangeListener() {

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
		if(selectedFamilleTiers!=null && selectedFamilleTiers.getValue()!=null) {
			if(((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId()!=null) {
				try {
					taFamilleTiers = dao.findById(((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId());
					
					if(taFamilleTiers!=null 
						&& masterEntity.getTaFamilleTiers()!=null 
						&& taFamilleTiers.getCodeFamille().equals(masterEntity.getTaFamilleTiers().getCodeFamille())) {
							((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).setDefaut(true);
					} else {
						((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).setDefaut(false);
					}
				} catch (FinderException e) {
					logger.error("",e);
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaFamilleTiersController.this));
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		Map map=dao.getParamWhereSQL();
		if (param != null) {
//			dao.ouvreDataset();
			if (((ParamAfficheFamille) param).getFocusDefautSWT() != null && !((ParamAfficheCommercial) param).getFocusDefautSWT().isDisposed())
				((ParamAfficheFamille) param).getFocusDefautSWT().setFocus();
			else
				((ParamAfficheFamille) param).setFocusDefautSWT(vue
						.getGrille());
			vue.getLaTitreFormulaire().setText(
					((ParamAfficheFamille) param).getTitreFormulaire());
			vue.getLaTitreGrille().setText(
					((ParamAfficheFamille) param).getTitreGrille());
			vue.getLaTitreFenetre().setText(
					((ParamAfficheFamille) param).getSousTitre());
//			if(((ParamAfficheFamille)param).getIdTiers()!=null) {
//				this.idTiers = LibConversion.stringToInteger(((ParamAfficheFamille)param).getIdTiers());
//				if(map==null)map=new HashMap();
//				if (!map.containsKey(Const.C_ID_TIERS))
//					map.put(Const.C_ID_TIERS,new String[]{"=",String.valueOf(idTiers)});
//				dao.setParamWhereSQL(map);
//			}
			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}

			if(tableViewer==null) {
				//databinding pas encore realise
				bind(vue);
				tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			} else {
				try {
					taFamilleTiers=null;
					selectedFamilleTiers.setValue(null);
					actRefresh();
				} catch (Exception e) {
					logger.error("",e);
				}
			}
			// permet de se positionner sur le 1er enregistrement et de remplir
			// le formulaire
//			tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
			setSwtOldCommercial();

			if (param.getModeEcran() != null
					&& param.getModeEcran().compareTo(
							EnumModeObjet.C_MO_INSERTION) == 0) {
				try {
					actInserer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			}
			
//			param.setFocus(dao.getFModeObjet().getFocusCourant());
		}
		initEtatBouton();
		return null;
	}
	
	protected void initVerifySaisie() {
		if (mapInfosVerifSaisie == null)
			mapInfosVerifSaisie = new HashMap<Control, InfosVerifSaisie>();
		
		mapInfosVerifSaisie.put(vue.getTfFAMILLE(), new InfosVerifSaisie(new TaFamilleTiers(),Const.C_CODE_FAMILLE,null));
		
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

		vue.getTfFAMILLE().setToolTipText(Const.C_CODE_FAMILLE);
		vue.getCbDefaut().setToolTipText(Const.C_FAMILLE_DEFAUT);


		mapComposantChamps.put(vue.getTfFAMILLE(), Const.C_CODE_FAMILLE);
		mapComposantChamps.put(vue.getCbDefaut(), Const.C_FAMILLE_DEFAUT);

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

		if (mapInitFocus == null)
			mapInitFocus = new LinkedHashMap<EnumModeObjet, Control>();
		mapInitFocus.put(EnumModeObjet.C_MO_INSERTION, vue
				.getTfFAMILLE());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfFAMILLE());
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
		
		mapCommand.put(C_COMMAND_GLOBAL_PRECEDENT_ID, handlerPrecedent);
		mapCommand.put(C_COMMAND_GLOBAL_SUIVANT_ID, handlerSuivant);
		
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

		Object[] tabActionsAutres = new Object[] { C_COMMAND_GLOBAL_AIDE_ID, C_COMMAND_GLOBAL_REFRESH_ID };
		mapActions.put(null, tabActionsAutres);
	}

	public SWTPaFamilleTiersController getThis() {
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
					.getString("Commercial.Message.Enregistrer"))) {

				try {
					actEnregistrer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			} else {
				fireAnnuleTout(new AnnuleToutEvent(this,true));
			}
//				dao.annuler();

			break;
		case C_MO_CONSULTATION:
			break;
		default:
			break;
		}

		if (retour) {
			if (ecranAppelant instanceof SWTPaAideControllerSWT) {
				setListeEntity(getModelFamilleTiers().remplirListe());
				dao.initValeurIdTable(taFamilleTiers);
				fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
						dao.getChampIdEntite(), dao.getValeurIdTable(),
						selectedFamilleTiers.getValue())));
//				vue.getDisplay().asyncExec(new Runnable() {
//					public void run() {
//						vue.getShell().setVisible(false);
//					}
//				});
//				retour = false;
				retour = true;
			} else {
				fireRetourEcran(new RetourEcranEvent(this,new ResultAffiche()));
			}
		}
		if (retour && !(ecranAppelant instanceof SWTPaAideControllerSWT)) {
			fireDestroy(new DestroyEvent(dao));
		}
		return retour;
	}

	public void retourEcran(final RetourEcranEvent evt) {
		if (evt.getRetour() != null
				&& (evt.getSource() instanceof SWTPaAideControllerSWT)) {
			if (getFocusAvantAideSWT() instanceof Text) {
				try {
					((Text) getFocusAvantAideSWT()).setText(((ResultAffiche) evt.getRetour()).getResult());	
					if(getFocusAvantAideSWT().equals(vue.getTfFAMILLE())){
						TaFamilleTiers entity = null;
						//TaFamilleTiersDAO dao = new TaFamilleTiersDAO();
						entity = dao.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
						//dao = null;
						taFamilleTiers = entity;
					}
					ctrlUnChampsSWT(getFocusAvantAideSWT());
				} catch (Exception e) {
					logger.error("",e);
					vue.getLaMessage().setText(e.getMessage());
				}
			}
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
//					dao.lgrLocateID(((ResultAffiche) evt.getRetour()).getIdResult());
					if(((ResultAffiche) evt.getRetour()).getSelection()!=null)
						tableViewer.setSelection(((ResultAffiche) evt.getRetour()).getSelection(),true);
				}
			}
		} else if (evt.getRetour() != null) {
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
//					dao.lgrLocateID(((ResultAffiche) evt.getRetour()).getIdResult());
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
			boolean continuer=true;
			VerrouInterface.setVerrouille(true);
			masterDAO.verifAutoriseModification(masterEntity);
			setSwtOldCommercial();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer = getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
				//if(masterEntity.getIdTiers()!=0)  setMasterEntity(masterDAO.findById(masterEntity.getIdTiers()));
			swtFamilleTiers = new TaFamilleTiersDTO();			
//			dao.getModeObjet().setMode(EnumModeObjet.C_MO_INSERTION);
			List l = IHMmodel();
			l.add(swtFamilleTiers);
			writableList = new WritableList(realm, l, classModel);
			tableViewer.setInput(writableList);
			tableViewer.refresh();
			tableViewer.setSelection(new StructuredSelection(swtFamilleTiers));
			
			getModeEcran().setMode(EnumModeObjet.C_MO_INSERTION);//ejb
			initEtatBouton();
			
			try {
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
			} catch (Exception e) {
				logger.error("",e);
			}
			}
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			throw e;
		} finally {
			initEtatComposant();
			VerrouInterface.setVerrouille(false);
		}
	}

	@Override
	protected void actModifier() throws Exception {
		try {
			boolean continuer=true;
			setSwtOldCommercial();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer = getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
//				setMasterEntity(masterDAO.findById(masterEntity.getIdTiers()));
			for (TaFamilleTiers p : masterEntity.getTaFamilleTierses()) {
				if(p.getIdFamille()==((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId()) {
					taFamilleTiers = p;
//					if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
				}
			}
			masterDAO.verifAutoriseModification(masterEntity);
			DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
			fireDeclencheCommandeController(e);
			modeEcran.setMode(EnumModeObjet.C_MO_EDITION);//ejb
			initEtatBouton();
			}
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			throw e;
		}
	}

	@Override
	protected void actSupprimer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();		
		try {
			boolean continuer=!isUtilise();
			VerrouInterface.setVerrouille(true);
			if(!continuer)MessageDialog.openInformation(vue.getShell(), MessagesEcran
					.getString("Message.Attention"), MessagesEcran
					.getString("Message.suppression"));
			else
				if(LgrMess.isDOSSIER_EN_RESEAU()){
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
					fireDeclencheCommandeController(e);
					continuer = getMasterModeEcran().dataSetEnModif();
				}
				
				if(continuer){
//					setMasterEntity(masterDAO.findById(masterEntity.getIdTiers()));
					if (MessageDialog.openConfirm(vue.getShell(), MessagesEcran
							.getString("Message.Attention"), MessagesEcran
							.getString("Famille.Message.Supprimer"))) {				
//						dao.getModeObjet().setMode(EnumModeObjet.C_MO_SUPPRESSION);
						try {
							DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
							fireDeclencheCommandeController(e);
						} catch (Exception e) {
							logger.error("",e);
						}
				for (TaFamilleTiers p : masterEntity.getTaFamilleTierses()) {
					if(p.getIdFamille()==((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId()) {
						taFamilleTiers = p;
					}
				}
				
//				dao.begin(transaction);
				masterEntity.removeFamilleTiers(taFamilleTiers);
//				dao.commit(transaction);
				modelFamilleTiers.removeEntity(taFamilleTiers);
				Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
				taFamilleTiers=null;
				if(suivant!=null)tableViewer.setSelection(new StructuredSelection(suivant),true);
				else tableViewer.selectionGrille(0);
			
				try {
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_ENREGISTRER_ID);
					fireDeclencheCommandeController(e,false);
				} catch (Exception e) {
					logger.error("",e);
				}
				
				modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
				actRefresh();		
				initEtatBouton();

			}
			}
		} catch (ExceptLgr e) {
			vue.getLaMessage().setText(e.getMessage());
			throw e;
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
			switch (getModeEcran().getMode()) {
			case C_MO_INSERTION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("Famille.Message.Annuler")))) {
					remetTousLesChampsApresAnnulationSWT(dbc);
					actRefresh();
					
					getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
					
					hideDecoratedFields();
					if(!annuleToutEnCours) {
						fireAnnuleTout(new AnnuleToutEvent(this));
					}
				}
				initEtatBouton();
				break;
			case C_MO_EDITION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("Commercial.Message.Annuler")))) {
					int rang =((WritableList)tableViewer.getInput()).indexOf(selectedFamilleTiers.getValue());
					List<TaFamilleTiersDTO> l = IHMmodel();
					if(rang!=-1)
						l.set(rang, swtOldFamilleTiers);
					remetTousLesChampsApresAnnulationSWT(dbc);
					writableList = new WritableList(realm, l, classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();

					tableViewer.setSelection(new StructuredSelection(swtOldFamilleTiers), true);
					//remetTousLesChampsApresAnnulationSWT(dbc);
					
					getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
					
					hideDecoratedFields();
					if(!annuleToutEnCours) {
						fireAnnuleTout(new AnnuleToutEvent(this));
					}
				}
				initEtatBouton();

				break;
			case C_MO_CONSULTATION:
//				actionFermer.run();
				fireChangementDePage(new ChangementDePageEvent(this,ChangementDePageEvent.DEBUT));
				break;
			default:
				break;
			}
			// getFocusCourant().setInputVerifier(inputVerifier);
			// initEtatBouton();
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			throw e;
		} finally {
			VerrouInterface.setVerrouille(false);
		}
	}

	@Override
	protected void actImprimer() throws Exception {
//		// // TODO procï¿½dure d'impression
//		// JOptionPane.showMessageDialog(vue, Const.C_A_IMPLEMENTER,
//		// MessagesEcran.getString("Message.Attention"),
//		// JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
//		//initFocusSWT(ibTaTable, mapInitFocus);
//		/**************************************************/
////		if(vue.getGrille()==getFocusCourantSWT()){
//
//		TaInfoEntrepriseDAO taInfoEntrepriseDAO = new TaInfoEntrepriseDAO();
//		TaInfoEntreprise taInfoEntreprise = taInfoEntrepriseDAO.findInstance();
//			String nomDossier = null;
//			ConstEdition objetConstEdition = new ConstEdition(); 
//			int nombreLine = objetConstEdition.nombreLineTable(tableViewer);
//			if(nombreLine==0){
//				MessageDialog.openError(vue.getShell(), "ALTER",
//						"Il n'y a rien dans Cette Edition !");
//			}
//			else{
//				if(taInfoEntreprise.getIdInfoEntreprise()==0){
//					nomDossier = objetConstEdition.INFOS_VIDE;
//				}
//				else{
//					nomDossier = taInfoEntreprise.getNomInfoEntreprise();	
//				}
//				String querySql = dao.getFIBQuery().getQuery().getQueryString();//select * from V_COMMERCIAL where ID_TIERS = 1256
//				String nameTable = dao.nomTable;
//				System.out.println(querySql+"----"+nameTable+"----"+nomDossier);
//				
//				String nameClass = SWTPaCommercialController.class.getSimpleName();
//				String sqlQueryStart = "SELECT ";
//				String sqlQueryEnd = " FROM "+Const.C_NOM_VU_COMMERCIAL;
//
//				String sqlQueryMiddle = objetConstEdition.addValueList(tableViewer, nameClass);
//				ArrayList<String> nameTableEcran = objetConstEdition.getNameTableEcran();
//				ArrayList<String> nameTableBDD = objetConstEdition.getNameTableBDD();
//				/*
//				 * name ecran ==> COMMERCIAL--NOM TIERS--TYPE TIERS
//				 */
////				for(int i=0;i<nameTableEcran.size();i++){
////				System.out.println(nameTableEcran.get(i));
////				}
//				/*
//				 * name BDD ==> COMMERCIAL(string)--NOM_TIERS(string)--CODE_T_TIERS(string)
//				 */
////				System.out.println("***********");
////				for(int i=0;i<nameTableBDD.size();i++){
////				System.out.println(nameTableBDD.get(i));
////				}
//				/*
//				 * SELECT COMMERCIAL,NOM_TIERS,CODE_T_TIERS FROM V_COMMERCIAL where ID_TIERS = 1256;
//				 */
//				String sqlQuery =  objetConstEdition.obtainSubString(sqlQueryStart,
//						sqlQueryMiddle,sqlQueryEnd,querySql);
//				//System.out.println(sqlQuery);
//				
//				String  C_FICHIER_BDD = Const.C_FICHIER_BDD.replaceFirst(":", "/");
//				//System.out.println(C_FICHIER_BDD);
//				String FILE_BDD = Const.C_URL_BDD+"//"+C_FICHIER_BDD;//jdbc:firebirdsql://localhost/C:/runtime-GestionCommercialeComplet.product/dossier/Bd/GEST_COM.FDB
//				//System.out.println(FILE_BDD);
//				
//				Path pathFileReport = new Path(Const.C_RCP_INSTANCE_LOCATION+"/"+Const.C_NOM_PROJET_TMP+"/"
//						+Const.C_NOM_VU_COMMERCIAL+ConstEdition.TYPE_FILE_REPORT);
//				final String PATH_FILE_REPORT = pathFileReport.toPortableString();
//				
//				Map<String, AttributElementResport> attribuTabHeader = new LinkedHashMap<String, AttributElementResport>();
//				attribuTabHeader.put("COMMERCIAL", new AttributElementResport("33",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_MEDIUM,ConstEdition.FONT_WEIGHT_BOLD,
//						ConstEdition.UNITS_PERCENTAGE,ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				attribuTabHeader.put("NOM TIERS", new AttributElementResport("33",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_MEDIUM,ConstEdition.FONT_WEIGHT_BOLD,
//						ConstEdition.UNITS_PERCENTAGE,ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				attribuTabHeader.put("TYPE TIERS", new AttributElementResport("33",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_MEDIUM,ConstEdition.FONT_WEIGHT_BOLD,
//						ConstEdition.UNITS_PERCENTAGE,ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				
//				Map<String, AttributElementResport> attribuTabDetail = new LinkedHashMap<String, AttributElementResport>();
//				attribuTabDetail.put("COMMERCIAL", new AttributElementResport("33",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_MEDIUM,ConstEdition.FONT_WEIGHT_NORMAL,
//						ConstEdition.UNITS_PERCENTAGE,ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				attribuTabDetail.put("NOM_TIERS", new AttributElementResport("33",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_MEDIUM,ConstEdition.FONT_WEIGHT_NORMAL,
//						ConstEdition.UNITS_PERCENTAGE,ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				attribuTabDetail.put("CODE_T_TIERS", new AttributElementResport("33",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_MEDIUM,ConstEdition.FONT_WEIGHT_NORMAL,
//						ConstEdition.UNITS_PERCENTAGE,ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				
//				MakeDynamiqueReport DynamiqueReport = new MakeDynamiqueReport(nameTableEcran,nameTableBDD,
//						sqlQuery/*,ConstEdition.BIRT_HOME*/,PATH_FILE_REPORT,Const.
//						C_DRIVER_JDBC,FILE_BDD,Const.C_USER,Const.C_PASS,
//						Const.C_NOM_VU_COMMERCIAL,ConstEdition.PAGE_ORIENTATION_PORTRAIT,
//						nomDossier);
//				
//				Map<String, AttributElementResport> attribuGridHeader = new LinkedHashMap<String, AttributElementResport>();
//				String nameHeaderTitle = ConstEditionTiers.TITLE_EDITION_TIERS_COMERCIAUX;
//				attribuGridHeader.put(nameHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_XX_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//						ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,""));
//				String nameSousHeaderTitle = ConstEditionTiers.SOUS_TITLE_EDITION_TIERS_COMERCIAUX;
//				attribuGridHeader.put(nameSousHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//						ConstEdition.FONT_SIZE_X_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//						ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//						,ConstEdition.COLOR_GRAY));
//				
//				ConstEdition.CONTENT_COMMENTS = ConstEditionTiers.COMMENTAIRE_EDITION_DEFAUT;
//				DynamiqueReport.initializeBuildDesignReportConfig();
//				DynamiqueReport.makePageMater("1", "1", "1", "1", "100");
//				DynamiqueReport.makeReportHeaderGrid(3,5,100,ConstEdition.UNITS_PERCENTAGE,attribuGridHeader);
//				
//				DynamiqueReport.makeReportTableDB(100,ConstEdition.UNITS_PERCENTAGE,
//						Const.C_NOM_VU_COMMERCIAL,attribuTabHeader,attribuTabDetail,1,1,2,5,"30");
//				
//				DynamiqueReport.savsAsDesignHandle();
//				Bundle bundleCourant = TiersPlugin.getDefault().getBundle();
//				
////				String reportFileLocation = ConstEdition.FOLDER_REPORT_PLUGIN;
////				
////				URL urlReportFile = Platform.asLocalURL(bundleCourant.getEntry(reportFileLocation));
////				URI uriReportFile = new URI("file", urlReportFile.getAuthority(),
////						urlReportFile.getPath(), urlReportFile.getQuery(), null);
////
////				File pathReport = new File(uriReportFile);
////				String pathFileEdition = pathReport.toString()+ConstEditionTiers.SOUS_REPORT_COMMERCIAL;
////				
////				File reportFile = objetConstEdition.makeFolderEditions(pathFileEdition);
//				
//				File reportFile = objetConstEdition.findPathReportPlugin(bundleCourant, 
//						ConstEdition.FOLDER_REPORT_PLUGIN, ConstEditionTiers.SOUS_REPORT_COMMERCIAL);
//				
//				final String namePlugin = bundleCourant.getSymbolicName();//name plugin is <<Tiers>>
//				
//				
//				objetConstEdition.makeFolderEditions(Const.C_REPERTOIRE_BASE+
//						Const.C_REPERTOIRE_PROJET.replaceFirst("/", "")+
//						ConstEdition.FOLDER_EDITION);
//				
//				String FloderEdition = Const.C_REPERTOIRE_BASE+
//				Const.C_REPERTOIRE_PROJET.replaceFirst("/", "")+ConstEdition.FOLDER_EDITION+
//				namePlugin+"/"+ConstEditionTiers.TIERS_COMERCIAUX;
//				
//				File FloderFileEditions = objetConstEdition.makeFolderEditions(FloderEdition);
//
//				String nomOnglet = ConstEdition.EDITION+ConstEditionTiers.TIERS_COMERCIAUX;
//
//				Shell dialogShell = new Shell(vue.getShell(),
//						//SWT.DIALOG_TRIM |SWT.APPLICATION_MODAL);
//						SWT.RESIZE | SWT.CLOSE | SWT.MAX | SWT.APPLICATION_MODAL);
//				dialogShell.setText(ConstEdition.TITLE_SHELL);
//				dialogShell.setLayout(new FillLayout());
//				//dialogShell
//				SwtCompositeReport_new dialogReport = new SwtCompositeReport_new(dialogShell,SWT.NULL);
//				objetConstEdition.openDialogChoixEdition_new(dialogReport, FloderFileEditions, 
//						PATH_FILE_REPORT, ConstEditionTiers.TIERS_COMERCIAUX,nomOnglet,dialogShell,reportFile);
//			}
////		}
////		else{
////		
//////			MessageDialog.openWarning(vue.getShell(), "ALTER",
//////			"Cette Edition n'esdddt pas disponible en ce moment !");
////		}
	}
	
	@Override
	protected boolean aideDisponible() {
		boolean result = false;
		switch (getModeEcran().getMode()) {
		case C_MO_CONSULTATION:
			if(getFocusCourantSWT().equals(vue.getGrille()))
				result = true;
			break;
		case C_MO_EDITION:
		case C_MO_INSERTION:
			if(getFocusCourantSWT().equals(vue.getTfFAMILLE()))
				result = true;
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	protected void actPrecedent() throws Exception {
		ChangementDePageEvent evt = new ChangementDePageEvent(this,
				ChangementDePageEvent.PRECEDENT);
		fireChangementDePage(evt);
	}

	@Override
	protected void actSuivant() throws Exception {
		ChangementDePageEvent evt = new ChangementDePageEvent(this,
				ChangementDePageEvent.SUIVANT);
		fireChangementDePage(evt);
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
//				paramAfficheAideRecherche.setDb(getThis().getDao().getFIBBase());
				paramAfficheAideRecherche.setMessage(message);
				//Crï¿½ation de l'ecran d'aide principal
				Shell s = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),LgrShellUtil.styleLgr);
				PaAideSWT paAide = new PaAideSWT(s,SWT.NONE);
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

				switch (getModeEcran().getMode()) {
				case C_MO_CONSULTATION:
					if(getFocusCourantSWT().equals(vue.getGrille())){
						PaFamilleSWT paFamilleTiers = new PaFamilleSWT(s2,SWT.NULL);
						SWTPaFamilleTiersController paFamilleTiersController = new SWTPaFamilleTiersController(paFamilleTiers);
						
						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);
						paramAfficheAideRecherche.setAfficheDetail(false);

						editorCreationId = EditorFamille.ID;
						editorInputCreation = new EditorInputFamille();

						ParamAfficheFamille paramAfficheFamilleTiers = new ParamAfficheFamille();
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAfficheFamilleTiers.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheFamilleTiers.setEcranAppelant(paAideController);
						controllerEcranCreation = paFamilleTiersController;
						parametreEcranCreation = paramAfficheFamilleTiers;

						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_FAMILLE);
						
						paramAfficheAideRecherche.setTypeEntite(TaFamilleTiers.class);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfFAMILLE().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(paFamilleTiersController.getModelFamilleTiers());
						paramAfficheAideRecherche.setTypeObjet(paFamilleTiersController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_DTO_GENERAL);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
//					if(getFocusCourantSWT().equals(vue.getTfFAMILLE())){
//						PaTypeFamilleTiers paFamilleTiers = new PaTypeFamilleTiers(s2,SWT.NULL);
//						PaTypeFamilleTiersController paFamilleTiersController = new PaTypeFamilleTiersController(paFamilleTiers);
//
//						editorCreationId = EditorFamilleTiers.ID;
//						editorInputCreation = new EditorInputFamilleTiers();
//
//						ParamAfficheFamilleTiers paramAfficheFamilleTiers = new ParamAfficheFamilleTiers();
//						paramAfficheAideRecherche.setJPQLQuery(new TaFamilleTiersDAO().getJPQLQuery());
//						paramAfficheFamilleTiers.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAfficheFamilleTiers.setEcranAppelant(paAideController);
//						controllerEcranCreation = paFamilleTiersController;
//						parametreEcranCreation = paramAfficheFamilleTiers;
//
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_FAMILLE);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfFAMILLE().getText());
//						paramAfficheAideRecherche.setControllerAppelant(getThis());
//						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTFamille,TaFamilleTiersDAO,TaFamilleTiers>(SWTFamille.class));
//						paramAfficheAideRecherche.setTypeObjet(paFamilleTiersController.getClass());
//
//						paramAfficheAideRecherche.setChampsIdentifiant(paFamilleTiersController.getDao().getChampIdTable());
//					}
					if(getFocusCourantSWT().equals(vue.getTfFAMILLE())){

						PaTypeFamilleTiers paFamilleSWT = new PaTypeFamilleTiers(s2,SWT.NULL); 
						PaTypeFamilleTiersController swtPaFamilleController = new PaTypeFamilleTiersController(paFamilleSWT);

						editorCreationId = EditorFamilleTiers.ID;
						editorInputCreation = new EditorInputFamilleTiers();

						ParamAfficheFamille paramAfficheFamille = new ParamAfficheFamille();
						ITaFamilleTiersServiceRemote dao = new EJBLookup<ITaFamilleTiersServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_FAMILLE_TIERS_SERVICE, ITaFamilleTiersServiceRemote.class.getName());
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAfficheFamille.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheFamille.setEcranAppelant(paAideController);
						/* 
						 * controllerEcranCreation ne sert plus a rien, pour l'editeur de creation, on creer un nouveau controller
						 */
						controllerEcranCreation = swtPaFamilleController;
						parametreEcranCreation = paramAfficheFamille;

						paramAfficheAideRecherche.setTypeEntite(TaFamilleTiers.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_FAMILLE);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfFAMILLE().getText());
						paramAfficheAideRecherche.setControllerAppelant(SWTPaFamilleTiersController.this);
						ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO> modelFamilleTiers = new ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO>(dao);
						paramAfficheAideRecherche.setModel(modelFamilleTiers);
						paramAfficheAideRecherche.setTypeObjet(TaFamilleTiersDTO.class);

						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_DTO_GENERAL);
					}

					break;
				default:
					break;
				}
//				if (paramAfficheAideRecherche.getJPQLQuery()!=null){				

					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s,SWT.NULL);	
					SWTPaAideRechercheControllerSWT paAideRechercheController1 = new SWTPaAideRechercheControllerSWT(paAideRecherche1);

					//Paramï¿½trage de la recherche
					paramAfficheAideRecherche.setFocusSWT(((PaAideRechercheSWT)paAideRechercheController1.getVue()).getTfChoix());
					paramAfficheAideRecherche.setRefCreationSWT(controllerEcranCreation);
					paramAfficheAideRecherche.setEditorCreation(editorCreation);
					paramAfficheAideRecherche.setEditorCreationId(editorCreationId);
					paramAfficheAideRecherche.setEditorInputCreation(editorInputCreation);
					paramAfficheAideRecherche.setParamEcranCreation(parametreEcranCreation);
					paramAfficheAideRecherche.setShellCreation(s2);
					paAideRechercheController1.configPanel(paramAfficheAideRecherche);
					//paramAfficheAideRecherche.setFocusDefaut(paAideRechercheController1.getVue().getTfChoix());

					//Ajout d'une recherche
					paAideController.addRecherche(paAideRechercheController1, paramAfficheAideRecherche.getTitreRecherche());

					//Paramï¿½trage de l'ï¿½cran d'aide principal
					ParamAfficheAideSWT paramAfficheAideSWT = new ParamAfficheAideSWT();
					ParamAfficheAide paramAfficheAide = new ParamAfficheAide();

					//enregistrement pour le retour de l'ecran d'aide
					paAideController.addRetourEcranListener(getThis());
					Control focus = vue.getShell().getDisplay().getFocusControl();
					//affichage de l'ecran d'aide principal (+ ses recherches)

					dbc.getValidationStatusMap().removeMapChangeListener(changeListener);
					//LgrShellUtil.afficheAideSWT(paramAfficheAide, null, paAide,paAideController, s);
					/*****************************************************************/
					paAideController.configPanel(paramAfficheAide);
					/*****************************************************************/
					dbc.getValidationStatusMap().addMapChangeListener(changeListener);

//				}
			} catch (Exception e) {
				vue.getLaMessage().setText(e.getMessage());
				throw e;
			}finally{
				VerrouInterface.setVerrouille(false);
				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
			}
		}
	}
	
	public IStatus validateUI() throws Exception {
		if ((getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)
				|| (getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
			ctrlTousLesChampsAvantEnregistrementSWT();
		}
		return null;
	}

	public IStatus validateUIField(String nomChamp,Object value) {
		
		try {
			//IStatus s = null;
			
			IStatus resultat = new Status(IStatus.OK,TiersPlugin.PLUGIN_ID,"validateUIField champ : "+nomChamp!=null?nomChamp:"null"+" valeur : "+value!=null?value.toString():"valeur nulle"+" validation OK");
			
			int VALIDATION_CLIENT = 1;
			int VALIDATION_SERVEUR = 2;
			int VALIDATION_CLIENT_ET_SERVEUR = 3;
			
			//int TYPE_VALIDATION = VALIDATION_CLIENT;
			//int TYPE_VALIDATION = VALIDATION_SERVEUR;
			int TYPE_VALIDATION = VALIDATION_CLIENT_ET_SERVEUR;
			
			AbstractApplicationDAOClient<TaAdresseDTO> a = new AbstractApplicationDAOClient<TaAdresseDTO>();
			
//			IStatus s = null;
//			boolean verrouilleModifCode = false;
//			{
//				TaFamilleTiers u = new TaFamilleTiers();
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//				
//				if(nomChamp.equals(Const.C_CODE_FAMILLE)){
//					TaFamilleTiersDAO dao = new TaFamilleTiersDAO(getEm());
//
//					dao.setModeObjet(getDao().getModeObjet());
//					TaFamilleTiers f = new TaFamilleTiers();
//					PropertyUtils.setSimpleProperty(f, nomChamp, value);
//					s = dao.validate(f,nomChamp,validationContext);
//
//					if(s.getSeverity()!=IStatus.ERROR) {
//						f = dao.findByCode((String)value);
//						taFamilleTiers = f;
//					}
//					dao = null;
//				}
//
//				s = dao.validate(u,nomChamp,validationContext,verrouilleModifCode);
//							
//			}
			if(nomChamp.equals(Const.C_FAMILLE_DEFAUT)) {
				//	s = new Status(IStatus.OK, TiersPlugin.PLUGIN_ID, "ok");
			} else if(nomChamp.equals(Const.C_CODE_FAMILLE)) {
				//ITaFamilleTiersServiceRemote dao = new EJBLookup<ITaFamilleTiersServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_FAMILLE_TIERS_SERVICE, ITaFamilleTiersServiceRemote.class.getName());
				TaFamilleTiersDTO dto = new TaFamilleTiersDTO();
				try {
					if(TYPE_VALIDATION==VALIDATION_CLIENT || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR) {
						AbstractApplicationDAOClient<TaFamilleTiersDTO> validationClient = new AbstractApplicationDAOClient<TaFamilleTiersDTO>();
						PropertyUtils.setSimpleProperty(dto, nomChamp, value);
						validationClient.validate(dto,nomChamp,ITaFamilleTiersServiceRemote.validationContext);
					}
					if(resultat==null && (TYPE_VALIDATION==VALIDATION_SERVEUR || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR)) {
						PropertyUtils.setSimpleProperty(dto, nomChamp, value);
						dao.validateDTOProperty(dto, nomChamp,ITaFamilleTiersServiceRemote.validationContext);
					}
				} catch(Exception e) {
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				}
//				if(resultat!=null && resultat.getSeverity()!=IStatus.ERROR ){
//					TaFamilleTiers entity = new TaFamilleTiers();
//					entity = dao.findByCode((String)value);
//					taFamilleTiers.setTaTAdr(entity);
//				}
				dao = null;
			} else {
//				TaAdresse u = new TaAdresse();
//				u.setTaTiers(masterEntity);
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//				s = dao.validate(u,nomChamp,validationContext);
				try {
					TaFamilleTiersDTO u = new TaFamilleTiersDTO();
					//u.setTaTiers(masterEntity);
					PropertyUtils.setSimpleProperty(u, nomChamp, value);
					dao.validateDTOProperty(u,nomChamp,ITaFamilleTiersServiceRemote.validationContext);
				} catch(Exception e) {
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				}

			}
//				//return s;
				return resultat;
//			} catch (IllegalAccessException e) {
//				logger.error("",e);
//			} catch (InvocationTargetException e) {
//				logger.error("",e);
//			} catch (NoSuchMethodException e) {
//				logger.error("",e);
			} catch (Exception e) {
				logger.error("",e);
			}
			return null;
	}

	@Override
	protected void actEnregistrer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();
		try {
			//TODO ejb, controle à remettre
//			ctrlUnChampsSWT(getFocusCourantSWT());
			ctrlTousLesChampsAvantEnregistrementSWT();
			
//			dao.begin(transaction);
			TaFamilleTiersMapper mapper = new TaFamilleTiersMapper();
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
				if( ((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId()!= taFamilleTiers.getIdFamille()) {
					//l'entité a bien été modifiée, il faut la changer dans la liste de la masterEntity
					//traitement spécial pour les listes où le F1 ou le validateUI change entièrement l'entité de l'écran et non uniquement une propriété (type) de celle-ci
					boolean trouve = false;
					TaFamilleTiers tmp = null;
					for (TaFamilleTiers p : masterEntity.getTaFamilleTierses()) {
						if(p.getIdFamille()==((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId()) {
							tmp = p;
							trouve = true;
						}
					}
					if(trouve) {
						masterEntity.removeFamilleTiers(tmp);
						masterEntity.addFamilleTiers(taFamilleTiers);
						//mapperModelToUI.map(taFamilleTiers,(TaFamilleTiersDTO) selectedFamilleTiers.getValue());
						mapper.mapEntityToDto(taFamilleTiers, (TaFamilleTiersDTO) selectedFamilleTiers.getValue());
					}
					tmp = null;
				}

			} else if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)) {
				//mapperUIToModel.map((TaFamilleTiersDTO) selectedCommercial.getValue(),taCommercial);
//				taCommercial.setTaFamilleTiers(masterEntity);
				masterEntity.addFamilleTiers(taFamilleTiers);				
			}
			boolean mapping=modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0;
			try {
				if(!enregistreToutEnCours) {
					sortieChamps();
					fireEnregistreTout(new AnnuleToutEvent(this,true));
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_ENREGISTRER_ID);
					fireDeclencheCommandeController(e,mapping);
				}
			} catch (Exception e) {
				logger.error("",e);
			}		

//			dao.commit(transaction);
			getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
			changementDeSelection();
			actRefresh();
//			transaction = null;
			

			
			hideDecoratedFields();
			vue.getLaMessage().setText("");


		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
		}
	}

	public ITaFamilleTiersServiceRemote getDao() {
		return dao;
	}

	public void initEtatComposant() {
		try {
//			vue.getTfCOMMERCIAL().setEditable(!isUtilise());
			changeCouleur(vue);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
		}
	}
	
	public boolean isUtilise(){
		return 
//		((TaFamilleTiersDTO)selectedFamilleTiers.getValue()).getIdFamille()!=null  
//		&&!dao.recordModifiable(dao.getNomTable(),
//				((TaFamilleTiersDTO)selectedFamilleTiers.getValue()).getIdFamille()))||
				!masterDAO.autoriseModification(masterEntity);		
	}

	public TaFamilleTiersDTO getSwtOldFamilleTiers() {
		return swtOldFamilleTiers;
	}

	public void setSwtOldTypeCivilite(TaFamilleTiersDTO swtOldCommercial) {
		this.swtOldFamilleTiers = swtOldCommercial;
	}

	public void setSwtOldCommercial() {
		if (selectedFamilleTiers.getValue() != null)
			this.swtOldFamilleTiers = TaFamilleTiersDTO.copy((TaFamilleTiersDTO) selectedFamilleTiers.getValue());
		else {
			if(tableViewer.selectionGrille(0)){
			this.swtOldFamilleTiers = TaFamilleTiersDTO.copy((TaFamilleTiersDTO) selectedFamilleTiers.getValue());
			tableViewer.setSelection(new StructuredSelection(
					(TaFamilleTiersDTO) selectedFamilleTiers.getValue()), true);
		}}
	}

	public void setVue(PaFamilleSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();
		mapComposantDecoratedField.put(vue.getTfFAMILLE(), vue
				.getFieldFAMILLE());
	}

	public Integer getIdTiers() {
		return idTiers;
	}

	public void setIdTiers(Integer idTiers) {
		this.idTiers = idTiers;
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

		//repositionnement sur la valeur courante
		int idActuel = 0;
		if (taFamilleTiers!=null) { //enregistrement en cours de modification/insertion
			idActuel = taFamilleTiers.getIdFamille();
		} else if(selectedFamilleTiers!=null && (TaFamilleTiersDTO) selectedFamilleTiers.getValue()!=null) {
			if(((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId()!=null)
				idActuel = ((TaFamilleTiersDTO) selectedFamilleTiers.getValue()).getId();
		}

		//rafraichissement des valeurs dans la grille
		writableList = new WritableList(realm, IHMmodel(), classModel);
		tableViewer.setInput(writableList);


		if(idActuel!=0) {
			tableViewer.setSelection(new StructuredSelection(recherche(Const.C_ID_DTO_GENERAL, idActuel)));
		}else
			tableViewer.selectionGrille(0);	
	}
	
	public Object recherche(String propertyName, Object value) {
		boolean trouve = false;
		int i = 0;
		while(!trouve && i<IHMmodel().size()){
			try {
				if(PropertyUtils.getProperty(IHMmodel().get(i), propertyName).equals(value)) {
					trouve = true;
				} else {
					i++;
				}
			} catch (IllegalAccessException e) {
				logger.error("",e);
			} catch (InvocationTargetException e) {
				logger.error("",e);
			} catch (NoSuchMethodException e) {
				logger.error("",e);
			}
		}

		if(trouve)
			return IHMmodel().get(i);
		else 
			return null;

	}

	public ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO> getModelFamilleTiers() {
		return modelFamilleTiers;
	}

	public TaTiers getMasterEntity() {
		return masterEntity;
	}

	public void setMasterEntity(TaTiers masterEntity) {
		this.masterEntity = masterEntity;
	}

	public ITaTiersServiceRemote getMasterDAO() {
		return masterDAO;
	}

	public void setMasterDAO(ITaTiersServiceRemote masterDAO) {
		this.masterDAO = masterDAO;
	}

	public void modifMode(){
		if (!VerrouInterface.isVerrouille() ){
			try {
				if(!getModeEcran().dataSetEnModif()) {
					if(!masterEntity.getTaFamilleTierses().isEmpty()) {
						actModifier();
					} else {
						actInserer();								
					}
					initEtatBouton(false);
				}
			} catch (Exception e1) {
				logger.error("",e1);
			}				
		} 
	}
}
