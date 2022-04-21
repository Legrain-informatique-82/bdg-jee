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
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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

import fr.legrain.bdg.dossier.service.remote.ITaInfoEntrepriseServiceRemote;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.model.mapping.mapper.TaAdresseMapper;
import fr.legrain.bdg.tiers.service.remote.ITaAdresseServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTAdrServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTiersServiceRemote;
import fr.legrain.gestCom.Appli.Const;
//import fr.legrain.gestCom.Appli.LgrDozerMapper;
import fr.legrain.gestCom.Appli.ModelGeneralObjetEJB;
import fr.legrain.gestCom.librairiesEcran.editor.EditorAide;
import fr.legrain.gestCom.librairiesEcran.editor.EditorInputAide;
import fr.legrain.gestCom.librairiesEcran.swt.DeclencheCommandeControllerEvent;
import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.IDetailController;
import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
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
import fr.legrain.tiers.dto.TaTAdrDTO;
import fr.legrain.tiers.editor.EditorInputTypeAdresse;
import fr.legrain.tiers.editor.EditorTypeAdresse;
import fr.legrain.tiers.model.TaAdresse;
import fr.legrain.tiers.model.TaTAdr;
import fr.legrain.tiers.model.TaTiers;
import fr.legrain.tiers.preferences.PreferenceConstants;
import googlemap.navigateur.GoogleMapInterface;

public class SWTPaAdresseController extends EJBBaseControllerSWTStandard
implements RetourEcranListener,IDetailController {

	static Logger logger = Logger.getLogger(SWTPaAdresseController.class.getName());
	private PaAdresseSWT vue = null;
	private ITaAdresseServiceRemote dao = null;//new TaAdresseDAO();


	private Object ecranAppelant = null;
	private TaAdresseDTO swtAdresse;
	private TaAdresseDTO swtOldAdresse;
	private Realm realm;
	private DataBindingContext dbc;

	private Class  classModel = TaAdresseDTO.class;
	private ModelGeneralObjetEJB<TaAdresse,TaAdresseDTO> modelAdresse = null;//new ModelGeneralObjet<TaTCiviliteDTO,TaTCiviliteDAO,TaTCivilite>(dao,classModel);
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedAdresse;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();

	private TaTiers masterEntity = null;
	private ITaTiersServiceRemote masterDAO = null;

	private LgrDozerMapper<TaAdresseDTO,TaAdresse> mapperUIToModel  = new LgrDozerMapper<TaAdresseDTO,TaAdresse>();
	private TaAdresse taAdresse = null;

	public static final String C_COMMAND_GEOLOCALISATION_ID = "fr.legrain.gestionCommerciale.tiers.adresse.geolocalisation";
	public static final String C_COMMAND_ITINERAIRE_ID = "fr.legrain.gestionCommerciale.tiers.adresse.itineraire";
	private HandlerGeolocalisation handlerGeolocalisation = new HandlerGeolocalisation();
	private HandlerItineraire handlerItineraire = new HandlerItineraire();

	private MapChangeListener changeListener = new MapChangeListener();

	private Integer idTiers = null;

	public SWTPaAdresseController(PaAdresseSWT vue) {
		this(vue,null);
	}

	public SWTPaAdresseController(PaAdresseSWT vue,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
		
		//dao = new TaAdresseDAO(getEm());
		try {
			dao = new EJBLookup<ITaAdresseServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_ADRESSE_SERVICE, ITaAdresseServiceRemote.class.getName());
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		modelAdresse = new ModelGeneralObjetEJB<TaAdresse,TaAdresseDTO>(dao);
		setVue(vue);

		// a faire avant l'initialisation du Binding
		vue.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldAdresse();
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
	public List<TaAdresseDTO> IHMmodel() {
		LinkedList<TaAdresse> ldao = new LinkedList<TaAdresse>();
		LinkedList<TaAdresseDTO> lswt = new LinkedList<TaAdresseDTO>();

		if(masterEntity!=null && !masterEntity.getTaAdresses().isEmpty()) {

			ldao.addAll(masterEntity.getTaAdresses());

			//LgrDozerMapper<TaAdresse,TaAdresseDTO> mapper = new LgrDozerMapper<TaAdresse,TaAdresseDTO>();
			TaAdresseMapper mapper = new TaAdresseMapper();
			for (TaAdresse o : ldao) {
				TaAdresseDTO t = null;
				//t = (TaAdresseDTO) mapper.map(o, TaAdresseDTO.class);
				t = (TaAdresseDTO) mapper.mapEntityToDto(o, null);
				lswt.add(t);
			}

		}
		return lswt;
	}

	protected void initEtatBouton() {
		initEtatBouton(IHMmodel());

		boolean trouve = contientDesEnregistrement(IHMmodel());
		switch (getModeEcran().getMode()) {
		case C_MO_INSERTION:
			enableActionAndHandler(C_COMMAND_GEOLOCALISATION_ID,false);
			enableActionAndHandler(C_COMMAND_ITINERAIRE_ID,false);
			break;
		case C_MO_EDITION:
			enableActionAndHandler(C_COMMAND_GEOLOCALISATION_ID,false);
			enableActionAndHandler(C_COMMAND_ITINERAIRE_ID,false);
			break;
		case C_MO_CONSULTATION:
			enableActionAndHandler(C_COMMAND_GEOLOCALISATION_ID,trouve);
			enableActionAndHandler(C_COMMAND_ITINERAIRE_ID,trouve);
			break;
		default:
			break;
		}
	}

	@Override
	protected void initImageBouton() {
		super.initImageBouton();
		String imageCarte = "/icons/map_magnify.png";
		String imageIti = "/icons/map.png";
		vue.getBtnGeolocalisation().setImage(TiersPlugin.getImageDescriptor(imageCarte).createImage());
		vue.getBtnItineraire().setImage(TiersPlugin.getImageDescriptor(imageIti).createImage());
		vue.layout(true);
	}

	public void bind(PaAdresseSWT paAdresseSWT) {
		try {
			//modelAdresse = new ModelTypeTiers(ibTaTable);
			//			 modelAdresse = new ModelGeneral<TaAdresseDTO>(ibTaTable.getFIBQuery(),classModel);
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(paAdresseSWT.getGrille());
			tableViewer.createTableCol(classModel,paAdresseSWT.getGrille(), nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE,-1);
			listeChamp = tableViewer.setListeChampGrille(nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE);

			//			ObservableListContentProvider viewerContent = new ObservableListContentProvider();
			//			tableViewer.setContentProvider(viewerContent);
			//
			//			IObservableMap[] attributeMaps = BeansObservables.observeMaps(
			//					viewerContent.getKnownElements(), classModel,
			//					listeChamp);
			//
			//			tableViewer.setLabelProvider(new ObservableMapLabelProvider(attributeMaps));
			//			List<TaAdresseDTO> lswt = IHMmodel();
			//			writableList = new WritableList(realm, lswt, classModel);
			//			tableViewer.setInput(writableList);

			// Set up data binding.
			LgrViewerSupport.bind(tableViewer, 
					new WritableList(IHMmodel(), classModel),
					BeanProperties.values(listeChamp)
			);

			selectedAdresse = ViewersObservables.observeSingleSelection(tableViewer);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			selectedAdresse.getValue();
			bindingFormMaitreDetail(dbc, realm, selectedAdresse,classModel);
			changementDeSelection();
			selectedAdresse.addChangeListener(new IChangeListener() {

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
		if(selectedAdresse!=null && selectedAdresse.getValue()!=null) {
			if(((TaAdresseDTO) selectedAdresse.getValue()).getId()!=null) {
				try {
					taAdresse = dao.findById(((TaAdresseDTO) selectedAdresse.getValue()).getId());
				} catch (FinderException e) {
					logger.error("",e);
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaAdresseController.this));
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			Map<String,String[]> map = dao.getParamWhereSQL();
			//			ibTaTable.ouvreDataset();
			if (((ParamAfficheAdresse) param).getFocusDefautSWT() != null && !((ParamAfficheAdresse) param).getFocusDefautSWT().isDisposed())
				((ParamAfficheAdresse) param).getFocusDefautSWT().setFocus();
			else
				((ParamAfficheAdresse) param).setFocusDefautSWT(vue
						.getGrille());
			vue.getLaTitreFormulaire().setText(
					((ParamAfficheAdresse) param).getTitreFormulaire());
			vue.getLaTitreGrille().setText(
					((ParamAfficheAdresse) param).getTitreGrille());
			vue.getLaTitreFenetre().setText(
					((ParamAfficheAdresse) param).getSousTitre());


			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}

			if(tableViewer==null) {
				//databinding pas encore realise
				bind(vue);
				tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			} else {
				try {
					taAdresse=null;
					selectedAdresse.setValue(null);
					actRefresh();
				} catch (Exception e) {
					logger.error("",e);
				}
			}
			// permet de se positionner sur le 1er enregistrement et de remplir
			// le formulaire

			//			tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
			setSwtOldAdresse();

			if (param.getModeEcran() != null
					&& param.getModeEcran().compareTo(EnumModeObjet.C_MO_INSERTION) == 0) {
				try {
					actInserer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			} else {
				initEtatBouton();
			}
			//param.setFocus(ibTaTable.getFModeObjet().getFocusCourant());
		}
		return null;
	}
	
	protected void initVerifySaisie() {
		if (mapInfosVerifSaisie == null)
			mapInfosVerifSaisie = new HashMap<Control, InfosVerifSaisie>();
		
		mapInfosVerifSaisie.put(vue.getTfADRESSE1_ADRESSE(), new InfosVerifSaisie(new TaAdresse(),Const.C_ADRESSE1_ADRESSE,null));
		mapInfosVerifSaisie.put(vue.getTfADRESSE2_ADRESSE(), new InfosVerifSaisie(new TaAdresse(),Const.C_ADRESSE2_ADRESSE,null));
		mapInfosVerifSaisie.put(vue.getTfADRESSE3_ADRESSE(), new InfosVerifSaisie(new TaAdresse(),Const.C_ADRESSE3_ADRESSE,null));
		mapInfosVerifSaisie.put(vue.getTfCODEPOSTAL_ADRESSE(), new InfosVerifSaisie(new TaAdresse(),Const.C_CODEPOSTAL_ADRESSE,null));
		mapInfosVerifSaisie.put(vue.getTfVILLE_ADRESSE(), new InfosVerifSaisie(new TaAdresse(),Const.C_VILLE_ADRESSE,null));
		mapInfosVerifSaisie.put(vue.getTfPAYS_ADRESSE(), new InfosVerifSaisie(new TaAdresse(),Const.C_PAYS_ADRESSE,null));
		mapInfosVerifSaisie.put(vue.getTfCODE_T_ADR(), new InfosVerifSaisie(new TaTAdr(),Const.C_CODE_T_ADR,null));

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

		vue.getTfADRESSE1_ADRESSE().setToolTipText(Const.C_ADRESSE1_ADRESSE);
		vue.getTfADRESSE2_ADRESSE().setToolTipText(Const.C_ADRESSE2_ADRESSE);
		vue.getTfADRESSE3_ADRESSE().setToolTipText(Const.C_ADRESSE3_ADRESSE);
		vue.getTfCODEPOSTAL_ADRESSE().setToolTipText(Const.C_CODEPOSTAL_ADRESSE);
		vue.getTfVILLE_ADRESSE().setToolTipText(Const.C_VILLE_ADRESSE);
		vue.getTfPAYS_ADRESSE().setToolTipText(Const.C_PAYS_ADRESSE);
		vue.getTfCODE_T_ADR().setToolTipText(Const.C_CODE_T_ADR);

		mapComposantChamps.put(vue.getTfADRESSE1_ADRESSE(), Const.C_ADRESSE1_ADRESSE);
		mapComposantChamps.put(vue.getTfADRESSE2_ADRESSE(), Const.C_ADRESSE2_ADRESSE);
		mapComposantChamps.put(vue.getTfADRESSE3_ADRESSE(),Const.C_ADRESSE3_ADRESSE);
		mapComposantChamps.put(vue.getTfCODEPOSTAL_ADRESSE(),Const.C_CODEPOSTAL_ADRESSE);
		mapComposantChamps.put(vue.getTfVILLE_ADRESSE(),Const.C_VILLE_ADRESSE);
		mapComposantChamps.put(vue.getTfPAYS_ADRESSE(),Const.C_PAYS_ADRESSE);
		mapComposantChamps.put(vue.getTfCODE_T_ADR(),Const.C_CODE_T_ADR);

		for (Control c : mapComposantChamps.keySet()) {
			listeComposantFocusable.add(c);
		}

		listeComposantFocusable.add(vue.getBtnGeolocalisation());
		listeComposantFocusable.add(vue.getBtnItineraire());

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
				.getTfADRESSE1_ADRESSE());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfADRESSE1_ADRESSE());
		mapInitFocus.put(EnumModeObjet.C_MO_CONSULTATION, vue
				.getGrille());

		activeModifytListener();
	}

	protected void initActions() {
		mapCommand = new HashMap<String, IHandler>();

		mapCommand.put(C_COMMAND_GEOLOCALISATION_ID, handlerGeolocalisation);
		mapCommand.put(C_COMMAND_ITINERAIRE_ID, handlerItineraire);

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

		mapActions.put(vue.getBtnGeolocalisation(), C_COMMAND_GEOLOCALISATION_ID);
		mapActions.put(vue.getBtnItineraire(), C_COMMAND_ITINERAIRE_ID);

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

	public SWTPaAdresseController getThis() {
		return this;
	}

	@Override
	public boolean onClose() throws ExceptLgr {
		boolean retour = true;
		VerrouInterface.setVerrouille(true);
		//switch (ibTaTable.getFModeObjet().getMode()) {
		switch (getModeEcran().getMode()) {
		case C_MO_INSERTION:
		case C_MO_EDITION:
			if (MessageDialog.openQuestion(vue.getShell(), MessagesEcran
					.getString("Message.Attention"), MessagesEcran
					.getString("Adresse.Message.Enregistrer"))) {

				try {
					actEnregistrer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			} else {
				fireAnnuleTout(new AnnuleToutEvent(this,true));
			}
			//ibTaTable.annuler();

			break;
		case C_MO_CONSULTATION:
			break;
		default:
			break;
		}

		if (retour) {
			if (ecranAppelant instanceof SWTPaAideControllerSWT) {
				setListeEntity(getModelAdresse().remplirListe());
				dao.initValeurIdTable(taAdresse);
				fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
						dao.getChampIdEntite(), dao.getValeurIdTable(),
						selectedAdresse.getValue())));

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
					if(getFocusAvantAideSWT().equals(vue.getTfCODE_T_ADR())){
						TaTAdr u = null;
						//TaTAdrDAO t = new TaTAdrDAO(getEm());
						ITaTAdrServiceRemote t = new EJBLookup<ITaTAdrServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_T_ADRESSE_SERVICE, ITaTAdrServiceRemote.class.getName());
						u = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
						t = null;
						taAdresse.setTaTAdr(u);
					}
					ctrlUnChampsSWT(getFocusAvantAideSWT());
				} catch (Exception e) {
					logger.error("",e);
					vue.getLaMessage().setText(e.getMessage());
				}
			}
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
					//ibTaTable.lgrLocateID(((ResultAffiche) evt.getRetour()).getIdResult());
					if(((ResultAffiche) evt.getRetour()).getSelection()!=null)
						tableViewer.setSelection(((ResultAffiche) evt.getRetour()).getSelection(),true);
				}
			}
		} else if (evt.getRetour() != null) {
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
					//ibTaTable.lgrLocateID(((ResultAffiche) evt.getRetour()).getIdResult());
					if(((ResultAffiche) evt.getRetour()).getSelection()!=null)
						tableViewer.setSelection(((ResultAffiche) evt.getRetour()).getSelection(),true);
				}
			}
		}
		super.retourEcran(evt);
	}

	protected class HandlerGeolocalisation extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				actGeolocalisation();
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}

	protected class HandlerItineraire extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				actItineraire();
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}

	protected void actGeolocalisation() {
		if(taAdresse!=null) {
			//if(swtAdresse!=null) {
			GoogleMapInterface.afficheMap(taAdresse);
		}
	}

	protected void actItineraire() {
		if(taAdresse!=null) {
			//if(swtAdresse!=null) {
			ITaInfoEntrepriseServiceRemote taInfoEntrepriseDAO;
			try {
				taInfoEntrepriseDAO = new EJBLookup<ITaInfoEntrepriseServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_INFO_ENTREPRISE_SERVICE, ITaInfoEntrepriseServiceRemote.class.getName());
				//TaInfoEntrepriseDAO taInfoEntrepriseDAO = new TaInfoEntrepriseDAO(getEm());
				GoogleMapInterface.afficheMapIti(taAdresse,taInfoEntrepriseDAO.findInstance());
				//new GoogleMapInterface().afficheGoogleMap();
			} catch (NamingException e) {
				logger.error("",e);
			}
			
		}
	}

	@Override
	protected void actInserer() throws Exception {
		try {
			boolean continuer=true;
			VerrouInterface.setVerrouille(true);
			masterDAO.verifAutoriseModification(masterEntity);
			setSwtOldAdresse();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer = getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
//				setMasterEntity(masterDAO.findById(masterEntity.getIdTiers()));
				swtAdresse = new TaAdresseDTO();
				swtAdresse.setIdTiers(idTiers);
				
				
				
				taAdresse = new TaAdresse();
				if(TiersPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.CORRESPONDANCE_ADRESSE_ADMINISTRATIF_DEFAUT)) {
					taAdresse.setCommAdministratifAdresse(1);
				} else {
					taAdresse.setCommAdministratifAdresse(0);
				}
				
				if(TiersPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.CORRESPONDANCE_ADRESSE_COMMERCIAL_DEFAUT)) {
					taAdresse.setCommCommercialAdresse(1);
				} else {
					taAdresse.setCommCommercialAdresse(0);
				}
				taAdresse.setPaysAdresse("FRANCE");
				
				List l = IHMmodel();
				l.add(swtAdresse);
				writableList = new WritableList(realm, l, classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(swtAdresse));
				
				getModeEcran().setMode(EnumModeObjet.C_MO_INSERTION);//ejb
				initEtatBouton();

				try {
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
					fireDeclencheCommandeController(e);
				} catch (Exception e) {
					logger.error("",e);
				}
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
			boolean continuer=true;
			setSwtOldAdresse();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer = getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
//				setMasterEntity(masterDAO.findById(masterEntity.getIdTiers()));
				for (TaAdresse p : masterEntity.getTaAdresses()) {
					if(p.getIdAdresse()==((TaAdresseDTO) selectedAdresse.getValue()).getId()) {
						taAdresse = p;
//						if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
					}
				}
				masterDAO.verifAutoriseModification(masterEntity);
				
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				modeEcran.setMode(EnumModeObjet.C_MO_EDITION);//ejb
				initEtatBouton();	
			}
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		}
	}

	@Override
	protected void actSupprimer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();		
		try {
			boolean continuer=true;
			VerrouInterface.setVerrouille(true);
			if(isUtilise())MessageDialog.openInformation(vue.getShell(), MessagesEcran
					.getString("Message.Attention"), MessagesEcran
					.getString("Message.suppression"));
			else
				if(LgrMess.isDOSSIER_EN_RESEAU()){
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
					fireDeclencheCommandeController(e);
					continuer = getMasterModeEcran().dataSetEnModif();
				}

			if(continuer){
					//setMasterEntity(masterDAO.findById(masterEntity.getIdTiers())); 
					if (MessageDialog.openConfirm(vue.getShell(), MessagesEcran
							.getString("Message.Attention"), MessagesEcran
							.getString("Adresse.Message.Supprimer"))) {				
//						dao.getModeObjet().setMode(EnumModeObjet.C_MO_SUPPRESSION);
						try {
							DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
							fireDeclencheCommandeController(e);
						} catch (Exception e) {
							logger.error("",e);
						}
						for (TaAdresse p : masterEntity.getTaAdresses()) {
							if(p.getIdAdresse()==((TaAdresseDTO) selectedAdresse.getValue()).getId()) {
								taAdresse = p;
							}
						}
//						dao.begin(transaction);
						//dao.supprimer(taAdresse);//ejb commentaire
						taAdresse.setTaTiers(null);
						masterEntity.removeAdresse(taAdresse);
						Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
						taAdresse=masterEntity.getTaAdresse();
//						dao.commit(transaction);
//						transaction=null;
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
		} catch (Exception e1) {
			modelAdresse.addEntity(taAdresse);
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
			switch (getModeEcran().getMode()) {
			case C_MO_INSERTION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("Adresse.Message.Annuler")))) {
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
						.getString("Adresse.Message.Annuler")))) {
					int rang =((WritableList)tableViewer.getInput()).indexOf(selectedAdresse.getValue());
					remetTousLesChampsApresAnnulationSWT(dbc);
					List<TaAdresseDTO> l = IHMmodel();
					if(rang!=-1)
						l.set(rang, swtOldAdresse);

					writableList = new WritableList(realm, l, classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldAdresse), true);
					//					remetTousLesChampsApresAnnulationSWT(dbc);
					
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
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaAdresse.class.getSimpleName()+".head");
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaAdresse.class.getSimpleName()+".detail");
//
//
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablHead = fonctionGetInfosXmlAndProperties.getMapAttributeTablHead(); 
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablDetail = fonctionGetInfosXmlAndProperties.getMapAttributeTablDetail();
//
//		Collection<TaAdresse> collectionTaTelephone = masterEntity.getTaAdresses();
//
//		fonctionGetInfosXmlAndProperties.findInfosFileXml(TaAdresse.class.getName(),TaAdresseDTO.class.getName(),
//				listeChamp,"mapping");
//		fonctionGetInfosXmlAndProperties.getInfosObjetJPA(taAdresse);
//
//		ConstEdition constEdition = new ConstEdition(); 
//		Impression impression = new Impression(constEdition,taAdresse,collectionTaTelephone,nomChampIdTable,taAdresse.getIdAdresse());
//		String nomDossier = null;
//
//		int nombreLine = collectionTaTelephone.size();
//
//
//		if(nombreLine==0){
//			MessageDialog.openWarning(vue.getShell(), ConstEdition.TITRE_MESSAGE_EDITION_VIDE,
//					ConstEdition.EDITION_VIDE);
//		}
//		else{
//
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
//			String folderEditionDynamique = Const.C_RCP_INSTANCE_LOCATION+"/"+Const.C_NOM_PROJET_TMP+"/"+TaAdresse.class.getSimpleName();
//			constEdition.makeFolderEditions(folderEditionDynamique);
//			Path pathFileReport = new Path(folderEditionDynamique+"/"+Const.C_NOM_VU_ADRESSE+".rptdesign");
//			final String pathFileReportDynamic = pathFileReport.toPortableString();
//
//
//			MakeDynamiqueReport DynamiqueReport = new MakeDynamiqueReport(constEdition.getNameTableEcran(),
//					constEdition.getNameTableBDD(),pathFileReportDynamic,Const.C_NOM_VU_ADRESSE,
//					ConstEdition.PAGE_ORIENTATION_LANDSCAPE,nomDossier); 
//
//			/**************************************************************/
//			DynamiqueReport.setFonctionGetInfosXml(fonctionGetInfosXmlAndProperties);
//			DynamiqueReport.setNomObjet(TaAdresse.class.getSimpleName());
//			/**************************************************************/
//
//
//			Map<String, AttributElementResport> attribuGridHeader = new LinkedHashMap<String, AttributElementResport>();
//			String nameHeaderTitle = ConstEditionTiers.TITLE_EDITION_TIERS_ADRESSE;
//			attribuGridHeader.put(nameHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_XX_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//					,""));
//			String nameSousHeaderTitle = ConstEditionTiers.SOUS_TITLE_EDITION_TIERS_ADRESSE;
//			attribuGridHeader.put(nameSousHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_X_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//					,ConstEdition.COLOR_GRAY));
//
//			ConstEdition.CONTENT_COMMENTS = ConstEditionTiers.COMMENTAIRE_EDITION_DEFAUT;
//			DynamiqueReport.setSimpleNameEntity(TaAdresse.class.getSimpleName());
//			DynamiqueReport.initializeBuildDesignReportConfig();
//			DynamiqueReport.makePageMater("1", "1", "1", "1", "100");
//			DynamiqueReport.makeReportHeaderGrid(3,5,100,ConstEdition.UNITS_PERCENTAGE,attribuGridHeader);
//
//			//			DynamiqueReport.makeReportTableDB(100,ConstEdition.UNITS_PERCENTAGE,
//			//					Const.C_NOM_VU_ADRESSE,attribuTabHeader,attribuTabDetail,1,1,2,5,"30");
//			DynamiqueReport.biuldTableReport("100", ConstEdition.UNITS_PERCENTAGE, 
//					Const.C_NOM_VU_ADRESSE,1,1,2,"40", mapAttributeTablHead, mapAttributeTablDetail);
//
//			DynamiqueReport.savsAsDesignHandle();
//			impression.imprimer(true,pathFileReportDynamic,null,"Adresses",TaAdresse.class.getSimpleName(),false);
//		}

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
			if(getFocusCourantSWT().equals(vue.getTfCODE_T_ADR()))
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
				//				paramAfficheAideRecherche.setDb(getThis().getIbTaTable().getFIBBase());
				paramAfficheAideRecherche.setMessage(message);
				// Creation de l'ecran d'aide principal
				Shell s = new Shell(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(),
						LgrShellUtil.styleLgr);
				PaAideSWT paAide = new PaAideSWT(s, SWT.NONE);
				SWTPaAideControllerSWT paAideController = new SWTPaAideControllerSWT(
						paAide);
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
//						PaAdresseSWT paAdresseSWT = new PaAdresseSWT(s2,SWT.NULL);
//						SWTPaAdresseController swtPaAdresseController = new SWTPaAdresseController(paAdresseSWT);
//
////						editorCreationId = EditorAdresse.ID;
////						editorInputCreation = new EditorInputAdresse();
//						
//						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);
//						paramAfficheAideRecherche.setAfficheDetail(false);
//
//						ParamAfficheAdresse paramAfficheAdresse = new ParamAfficheAdresse();
//						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
//						paramAfficheAdresse.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAfficheAdresse.setEcranAppelant(paAideController);
//						controllerEcranCreation = swtPaAdresseController;
//						parametreEcranCreation = paramAfficheAdresse;
//
//						paramAfficheAideRecherche.setTypeEntite(TaAdresse.class);
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_ADRESSE1_ADRESSE);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfADRESSE1_ADRESSE().getText());
//						paramAfficheAideRecherche.setControllerAppelant(getThis());
//						paramAfficheAideRecherche.setModel(swtPaAdresseController.getModelAdresse());
//						paramAfficheAideRecherche.setTypeObjet(swtPaAdresseController.getClassModel());
//						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_ADRESSE);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
					if(getFocusCourantSWT().equals(vue.getTfCODE_T_ADR())){
						PaTAdrSWT paTAdrSWT = new PaTAdrSWT(s2,SWT.NULL);
						SWTPaTAdrController swtPaTAdrController = new SWTPaTAdrController(paTAdrSWT);

						editorCreationId = EditorTypeAdresse.ID;
						editorInputCreation = new EditorInputTypeAdresse();


						ParamAfficheTypeAdresse paramAfficheTAdr = new ParamAfficheTypeAdresse();
						//paramAfficheAideRecherche.setJPQLQuery(new TaTAdrDAO(getEm()).getJPQLQuery());
						ITaTAdrServiceRemote dao = new EJBLookup<ITaTAdrServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_T_ADRESSE_SERVICE, ITaTAdrServiceRemote.class.getName());
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAfficheTAdr.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheTAdr.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaTAdrController;
						parametreEcranCreation = paramAfficheTAdr;

						paramAfficheAideRecherche.setTypeEntite(TaTAdr.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_T_ADR);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_T_ADR().getText());
						paramAfficheAideRecherche.setControllerAppelant(SWTPaAdresseController.this);
						//paramAfficheAideRecherche.setModel(new ModelGeneral<SWTTAdr>(swtPaTAdrController.getIbTaTable().getFIBQuery(),SWTTAdr.class));
						ModelGeneralObjetEJB<TaTAdr,TaTAdrDTO> modelTypeAdresse = new ModelGeneralObjetEJB<TaTAdr,TaTAdrDTO>(dao);
						paramAfficheAideRecherche.setModel(modelTypeAdresse);
						//paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTTAdr,TaTAdrDAO,TaTAdr>(SWTTAdr.class,dao.getEntityManager()));

						paramAfficheAideRecherche.setTypeObjet(swtPaTAdrController.getClassModel());
						
						//paramAfficheAideRecherche.setChampsIdentifiant(swtPaTAdrController.getDao().getChampIdTable());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_DTO_GENERAL);
					}
					break;
				default:
					break;
				}

//				if (paramAfficheAideRecherche.getJPQLQuery() != null) {

					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s,SWT.NULL);
					SWTPaAideRechercheControllerSWT paAideRechercheController1 = new SWTPaAideRechercheControllerSWT(paAideRecherche1);

					// Parametrage de la recherche
					paramAfficheAideRecherche
					.setFocusSWT(((PaAideRechercheSWT) paAideRechercheController1
							.getVue()).getTfChoix());
					paramAfficheAideRecherche.setRefCreationSWT(controllerEcranCreation);
					paramAfficheAideRecherche.setEditorCreation(editorCreation);
					paramAfficheAideRecherche.setEditorCreationId(editorCreationId);
					paramAfficheAideRecherche.setEditorInputCreation(editorInputCreation);
					paramAfficheAideRecherche
					.setParamEcranCreation(parametreEcranCreation);
					paramAfficheAideRecherche.setShellCreation(s2);
					paAideRechercheController1
					.configPanel(paramAfficheAideRecherche);
					// paramAfficheAideRecherche.setFocusDefaut(paAideRechercheController1.getVue().getTfChoix());

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

					dbc.getValidationStatusMap().removeMapChangeListener(
							changeListener);
					//LgrShellUtil.afficheAideSWT(paramAfficheAide, null, paAide,paAideController, s);
					/*****************************************************************/
					paAideController.configPanel(paramAfficheAide);
					/*****************************************************************/
					dbc.getValidationStatusMap().addMapChangeListener(
							changeListener);

//				}

			} finally {
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
			

			if(nomChamp.equals(Const.C_CODE_T_ADR)) {
//				TaTAdrDAO dao = new TaTAdrDAO(getEm());
//
//				dao.setModeObjet(getDao().getModeObjet());
//				TaTAdr f = new TaTAdr();
//				PropertyUtils.setSimpleProperty(f, nomChamp, value);
//				s = dao.validate(f,nomChamp,validationContext);
//
//				if(s.getSeverity()!=IStatus.ERROR) {
//					f = dao.findByCode((String)value);
//					taAdresse.setTaTAdr(f);
//				}
//				dao = null;
				
				ITaTAdrServiceRemote dao = new EJBLookup<ITaTAdrServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_T_ADRESSE_SERVICE, ITaTAdrServiceRemote.class.getName());
				TaTAdrDTO dto = new TaTAdrDTO();
				try {
					if(TYPE_VALIDATION==VALIDATION_CLIENT || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR) {
						AbstractApplicationDAOClient<TaTAdrDTO> validationClient = new AbstractApplicationDAOClient<TaTAdrDTO>();
						PropertyUtils.setSimpleProperty(dto, nomChamp, value);
						validationClient.validate(dto,nomChamp,ITaAdresseServiceRemote.validationContext);
					}
					if(resultat==null && (TYPE_VALIDATION==VALIDATION_SERVEUR || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR)) {
						PropertyUtils.setSimpleProperty(dto, nomChamp, value);
						dao.validateDTOProperty(dto, nomChamp,ITaAdresseServiceRemote.validationContext);
					}
				} catch(Exception e) {
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				}
				if(resultat!=null && resultat.getSeverity()!=IStatus.ERROR ){
					TaTAdr entity = new TaTAdr();
					entity = dao.findByCode((String)value);
					taAdresse.setTaTAdr(entity);
				}
				dao = null;
			} else {
//				TaAdresse u = new TaAdresse();
//				u.setTaTiers(masterEntity);
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//				s = dao.validate(u,nomChamp,validationContext);
				try {
					TaAdresseDTO u = new TaAdresseDTO();
					//u.setTaTiers(masterEntity);
					PropertyUtils.setSimpleProperty(u, nomChamp, value);
					dao.validateDTOProperty(u,nomChamp,ITaAdresseServiceRemote.validationContext);
				} catch(Exception e) {
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				}
				if(resultat!=null && resultat.getSeverity()!=IStatus.ERROR ){
					PropertyUtils.setSimpleProperty(taAdresse, nomChamp, value);
				}
			}
			//return s;
			return resultat;
//		} catch (IllegalAccessException e) {
//			logger.error("",e);
//		} catch (InvocationTargetException e) {
//			logger.error("",e);
//		} catch (NoSuchMethodException e) {
//			logger.error("",e);
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
			TaAdresseMapper mapper = new TaAdresseMapper();
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
				//LgrDozerMapper<TaAdresseDTO,TaAdresse> mapper = new LgrDozerMapper<TaAdresseDTO,TaAdresse>();
				//mapper.map((TaAdresseDTO) selectedAdresse.getValue(),taAdresse);
				mapper.mapDtoToEntity((TaAdresseDTO) selectedAdresse.getValue(), taAdresse);
				masterEntity.addAdresse(taAdresse);

			} else if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)) {
//				LgrDozerMapper<TaAdresseDTO,TaAdresse> mapper = new LgrDozerMapper<TaAdresseDTO,TaAdresse>();
//				mapper.map((TaAdresseDTO) selectedAdresse.getValue(),taAdresse);
				mapper.mapDtoToEntity((TaAdresseDTO) selectedAdresse.getValue(), taAdresse);
				taAdresse.setTaTiers(masterEntity);
				masterEntity.addAdresse(taAdresse);				
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
			taAdresse=masterEntity.getTaAdresse();
			changementDeSelection();
			actRefresh();
//			transaction = null;
			
			getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
			
			hideDecoratedFields();
			vue.getLaMessage().setText("");


		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
		}
	}

	public void initEtatComposant() {
		try {
			vue.getTfCODE_T_ADR().setEditable(!isUtilise());
			changeCouleur(vue);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
		}
	}

	public boolean isUtilise(){
		return (((TaAdresseDTO)selectedAdresse.getValue()).getId()!=null && 
				!dao.recordModifiable(dao.getNomTable(),
						((TaAdresseDTO)selectedAdresse.getValue()).getId()))||
						!masterDAO.autoriseModification(masterEntity);		
	}

	public TaAdresseDTO getSwtOldAdresse() {
		return swtOldAdresse;
	}

	public void setSwtOldAdresse(TaAdresseDTO swtOldAdresse) {
		this.swtOldAdresse = swtOldAdresse;
	}

	public void setSwtOldAdresse() {
		if (selectedAdresse.getValue() != null)
			this.swtOldAdresse = TaAdresseDTO.copy((TaAdresseDTO) selectedAdresse.getValue());
		else {
			if (tableViewer.selectionGrille(0)){
				this.swtOldAdresse = TaAdresseDTO.copy((TaAdresseDTO) selectedAdresse.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(TaAdresseDTO) selectedAdresse.getValue()), true);
			}
		}
	}

	public void setVue(PaAdresseSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();
		mapComposantDecoratedField.put(vue.getTfADRESSE1_ADRESSE(), vue
				.getFieldADRESSE1_ADRESSE());
		mapComposantDecoratedField.put(vue.getTfADRESSE2_ADRESSE(), vue
				.getFieldADRESSE2_ADRESSE());
		mapComposantDecoratedField.put(vue.getTfADRESSE3_ADRESSE(), vue
				.getFieldADRESSE3_ADRESSE());
		mapComposantDecoratedField.put(vue.getTfCODEPOSTAL_ADRESSE(), vue
				.getFieldCODEPOSTAL_ADRESSE());
		mapComposantDecoratedField.put(vue.getTfVILLE_ADRESSE(), vue
				.getFieldVILLE_ADRESSE());
		mapComposantDecoratedField.put(vue.getTfPAYS_ADRESSE(), vue
				.getFieldPAYS_ADRESSE());
		mapComposantDecoratedField.put(vue.getTfCODE_T_ADR(), vue
				.getFieldCODE_T_ADR());
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

		//		//repositionnement sur la valeur courante
		int idActuel = 0;
		if (taAdresse!=null) { //enregistrement en cours de modification/insertion
			idActuel = taAdresse.getIdAdresse();
		} else 
			if(selectedAdresse!=null && selectedAdresse.getValue()!=null) {
				idActuel = ((TaAdresseDTO) selectedAdresse.getValue()).getId();
			}
		//rafraichissement des valeurs dans la grille
		//tableViewer.setInput(null);
		writableList = new WritableList(realm,IHMmodel(), classModel);
		tableViewer.setInput(writableList);


		if(idActuel!=0) {
			tableViewer.setSelection(new StructuredSelection(recherche(/*Const.C_ID_ADRESSE*/"id", idActuel)));
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

	public ModelGeneralObjetEJB<TaAdresse,TaAdresseDTO> getModelAdresse() {
		return modelAdresse;
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

	public ITaAdresseServiceRemote getDao() {
		return dao;
	}
	
	public void modifMode(){
		if (!VerrouInterface.isVerrouille() ){
			try {
				if(!getModeEcran().dataSetEnModif()) {
					if(!masterEntity.getTaAdresses().isEmpty()) {
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
