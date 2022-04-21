package fr.legrain.tiers.ecran;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import fr.legrain.gestCom.Appli.Const;
//import fr.legrain.gestCom.Appli.LgrDozerMapper;
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
import fr.legrain.gestCom.librairiesEcran.workbench.IChangementMasterEntityListener;
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
import fr.legrain.tiers.dto.TaFamilleTiersDTO;
import fr.legrain.tiers.editor.EditorFamilleTiers;
import fr.legrain.tiers.editor.EditorInputFamilleTiers;
import fr.legrain.tiers.model.TaFamilleTiers;


public class PaTypeFamilleTiersController extends EJBBaseControllerSWTStandard
implements RetourEcranListener {

	static Logger logger = Logger.getLogger(PaTypeFamilleTiersController.class.getName());
	private PaTypeFamilleTiers vue = null;
	//private TaFamilleTiersDAO dao = new TaFamilleTiersDAO();
	private ITaFamilleTiersServiceRemote dao;;

	private Object ecranAppelant = null;
	private TaFamilleTiersDTO familleDTO;
	private TaFamilleTiersDTO swtOldFamille;
	private Realm realm;
	private DataBindingContext dbc;

	private Class classModel = TaFamilleTiersDTO.class;
	private ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO> modelFamille = null;
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedFamille;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();

	private TaFamilleTiers taFamilleTiers = null;

	private MapChangeListener changeListener = new MapChangeListener();

	private LgrDozerMapper<TaFamilleTiersDTO,TaFamilleTiers> mapperUIToModel = new LgrDozerMapper<TaFamilleTiersDTO,TaFamilleTiers>();
	
	public PaTypeFamilleTiersController(PaTypeFamilleTiers vue) {
		this(vue,null);
	}
	
	public PaTypeFamilleTiersController(PaTypeFamilleTiers vue,EntityManager em) {
		try {
			dao = new EJBLookup<ITaFamilleTiersServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_FAMILLE_TIERS_SERVICE, ITaFamilleTiersServiceRemote.class.getName());
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		modelFamille = new ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO>(dao);
		setVue(vue);

		// a faire avant l'initialisation du Binding
		vue.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldFamille();
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
			setTaFamilleTiers(new TaFamilleTiers());
			
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

			addDestroyListener(this);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			logger.error("Erreur : PaArticlesController", e);
		}
	}

	public void bind(PaTypeFamilleTiers paFamilleSWT) {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(paFamilleSWT.getGrille());
			tableViewer.createTableCol(classModel,paFamilleSWT.getGrille(), nomClassController,
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
//			writableList = new WritableList(realm, modelFamille.remplirListe(), classModel);
//			tableViewer.setInput(writableList);
			
			// Set up data binding.
			LgrViewerSupport.bind(tableViewer, 
					new WritableList(modelFamille.remplirListe(), classModel),
					BeanProperties.values(listeChamp)
					);

			selectedFamille = ViewersObservables.observeSingleSelection(tableViewer);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedFamille,classModel);
			
			selectedFamille.addChangeListener(new IChangeListener() {

				public void handleChange(ChangeEvent event) {
					if(selectedFamille!=null && selectedFamille.getValue()!=null) {
						if(((TaFamilleTiersDTO) selectedFamille.getValue()).getId()!=null) {
							try {
								taFamilleTiers = dao.findById(((TaFamilleTiersDTO) selectedFamille.getValue()).getId());
							} catch (FinderException e) {
								logger.error("", e);
							}
						}
						//null a tester ailleurs, car peut etre null en cas d'insertion
						fireChangementDeSelection(new ChangementDeSelectionEvent(PaTypeFamilleTiersController.this));
					}
				}

			});


		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			logger.error("", e);
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			if (param.getFocusDefautSWT() != null && !param.getFocusDefautSWT().isDisposed())
				param.getFocusDefautSWT().setFocus();
			else
				param.setFocusDefautSWT(vue
						.getGrille());
			if(param.getTitreFormulaire()!=null){
				vue.getLaTitreFormulaire().setText(param.getTitreFormulaire());
			} else {
				vue.getLaTitreFormulaire().setText(ParamAfficheFamilleTiers.C_TITRE_FORMULAIRE);
			}

			if(param.getTitreGrille()!=null){
				vue.getLaTitreGrille().setText(param.getTitreGrille());
			} else {
				vue.getLaTitreGrille().setText(ParamAfficheFamilleTiers.C_TITRE_GRILLE);
			}

			if(param.getSousTitre()!=null){
				vue.getLaTitreFenetre().setText(param.getSousTitre());
			} else {
				vue.getLaTitreFenetre().setText(ParamAfficheFamilleTiers.C_SOUS_TITRE);
			}

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}
			Map<String,String[]> map = dao.getParamWhereSQL();
			if(param.getIdFiche()!=null &&  !param.getIdFiche().equals("")) {
				if(map==null) map = new HashMap<String,String[]>();
				map.clear();
				map.put("idFamille",new String[]{"=",param.getIdFiche()});
				dao.setParamWhereSQL(map);
				vue.getBtnTous().setVisible(true);
				vue.getGrille().setVisible(false);
				vue.getLaTitreGrille().setVisible(false);
				vue.getCompositeForm().setWeights(new int[]{0,100});					
			}
			bind(vue);
			// permet de se positionner sur le 1er enregistrement et de remplir
			// le formulaire
			tableViewer.selectionGrille(0);
			tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
			setSwtOldFamille();

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
		
		mapInfosVerifSaisie.put(vue.getTfCODE_FAMILLE(), new InfosVerifSaisie(new TaFamilleTiers(),Const.C_CODE_FAMILLE,null));
		mapInfosVerifSaisie.put(vue.getTfLIBC_FAMILLE(), new InfosVerifSaisie(new TaFamilleTiers(),Const.C_LIBC_FAMILLE,null));
		mapInfosVerifSaisie.put(vue.getTfLIBL_FAMILLE(), new InfosVerifSaisie(new TaFamilleTiers(),Const.C_LIBL_FAMILLE,null));

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

		vue.getTfCODE_FAMILLE().setToolTipText(Const.C_CODE_FAMILLE);
		vue.getTfLIBC_FAMILLE().setToolTipText(Const.C_LIBC_FAMILLE);
		vue.getTfLIBL_FAMILLE().setToolTipText(Const.C_LIBL_FAMILLE);

		mapComposantChamps.put(vue.getTfCODE_FAMILLE(), Const.C_CODE_FAMILLE);
		mapComposantChamps.put(vue.getTfLIBC_FAMILLE(), Const.C_LIBC_FAMILLE);
		mapComposantChamps.put(vue.getTfLIBL_FAMILLE(), Const.C_LIBL_FAMILLE);

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
				.getTfCODE_FAMILLE());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfCODE_FAMILLE());
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
		mapCommand.put(C_COMMAND_DOCUMENT_AFFICHER_TOUS_ID, handlerAfficherTous);
		
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
		mapActions.put(vue.getBtnTous(), C_COMMAND_DOCUMENT_AFFICHER_TOUS_ID);

		Object[] tabActionsAutres = new Object[] { C_COMMAND_GLOBAL_AIDE_ID, C_COMMAND_GLOBAL_REFRESH_ID };
		mapActions.put(null, tabActionsAutres);

	}

	public PaTypeFamilleTiersController getThis() {
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
					.getString("Famille.Message.Enregistrer"))) {

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
				setListeEntity(getModelFamille().remplirListe());
				dao.initValeurIdTable(taFamilleTiers);
				fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
						dao.getChampIdEntite(), dao.getValeurIdTable(),
						selectedFamille.getValue())));

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
					((Text) getFocusAvantAideSWT()).setText(((ResultAffiche) evt
							.getRetour()).getResult());					
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
				
			}
			VerrouInterface.setVerrouille(true);
			setSwtOldFamille();
			familleDTO = new TaFamilleTiersDTO();
			taFamilleTiers = new TaFamilleTiers();

			//dao.inserer(taFamilleTiers);
			modelFamille.getListeObjet().add(familleDTO);
			writableList = new WritableList(realm, modelFamille.getListeObjet(), classModel);
			tableViewer.setInput(writableList);
			tableViewer.refresh();
			tableViewer.setSelection(new StructuredSelection(familleDTO));
			getModeEcran().setMode(EnumModeObjet.C_MO_INSERTION);//ejb
			initEtatBouton();
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
//		try {
//			setSwtOldFamille();
//			taFamilleTiers = dao.findById(((TaFamilleTiersDTO) selectedFamille.getValue()).getIdFamille());
		try {
			if(!LgrMess.isDOSSIER_EN_RESEAU()){
				setSwtOldFamille();
				taFamilleTiers = dao.findById(((TaFamilleTiersDTO) selectedFamille.getValue()).getId());
			}else{
				if(!setSwtOldFamilleRefresh())throw new Exception();
			}
			dao.modifier(taFamilleTiers);
			initEtatBouton();
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		}
	}
	public boolean containsEntity(TaFamilleTiers entite){
		if(modelFamille.getListeEntity()!=null){
			for (Object e : modelFamille.getListeEntity()) {
				if(((TaFamilleTiers)e).getIdFamille()==
					entite.getIdFamille())return true;
			}
		}
		return false;
	}

	public boolean setSwtOldFamilleRefresh() {
		try {	
			if (selectedFamille.getValue()!=null){
				TaFamilleTiers taOld =dao.findById(taFamilleTiers.getIdFamille());
//				taOld=dao.refresh(taOld);
//				if(containsEntity(taFamilleTiers)) 
//					modelFamille.getListeEntity().remove(taFamilleTiers);
//				if(!taFamilleTiers.getVersionObj().equals(taOld.getVersionObj())){
//					taFamilleTiers=taOld;
//					if(!containsEntity(taFamilleTiers)) 
//						modelFamille.getListeEntity().add(taFamilleTiers);					
//					actRefresh();
//					dao.messageNonAutoriseModification();
//				}
				taFamilleTiers=taOld;
				if(!containsEntity(taFamilleTiers)) 
					modelFamille.getListeEntity().add(taFamilleTiers);
				changementDeSelection();
				this.swtOldFamille=TaFamilleTiersDTO.copy((TaFamilleTiersDTO)selectedFamille.getValue());
			}
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	private void changementDeSelection() {
		if(selectedFamille!=null && selectedFamille.getValue()!=null) {
			if(((TaFamilleTiersDTO) selectedFamille.getValue()).getId()!=null) {
				try {
					taFamilleTiers = dao.findById(((TaFamilleTiersDTO) selectedFamille.getValue()).getId());
				} catch (FinderException e) {
					logger.error("", e);
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(PaTypeFamilleTiersController.this));
		}
	}

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
						.getString("Famille.Message.Supprimer"))) {
//					dao.begin(transaction);
					TaFamilleTiers u = dao.findById(((TaFamilleTiersDTO) selectedFamille.getValue()).getId());
					dao.supprimer(u);
//					dao.commit(transaction);
					Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
					modelFamille.removeEntity(taFamilleTiers);
					taFamilleTiers=null;
//					dao.getModeObjet().setMode(EnumModeObjet.C_MO_CONSULTATION);
					tableViewer.selectionGrille(0);
					actRefresh(); //ajouter pour tester jpa
					if(suivant!=null)tableViewer.setSelection(new StructuredSelection(suivant),true);
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
				if (MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("Famille.Message.Annuler"))) {
					if(((TaFamilleTiersDTO) selectedFamille.getValue()).getId()==null){
						modelFamille.getListeObjet().remove(
								((TaFamilleTiersDTO) selectedFamille.getValue()));
						writableList = new WritableList(realm, modelFamille
								.getListeObjet(), classModel);
						tableViewer.setInput(writableList);
						tableViewer.refresh();
						tableViewer.selectionGrille(0);
					}
					dao.annuler(taFamilleTiers);
					hideDecoratedFields();
				}
				initEtatBouton();
				break;
			case C_MO_EDITION:
				if (MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("Famille.Message.Annuler"))) {
					int rang = modelFamille.getListeObjet().indexOf(selectedFamille.getValue());
					modelFamille.getListeObjet().set(rang, swtOldFamille);
					writableList = new WritableList(realm, modelFamille.getListeObjet(), classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldFamille), true);
					dao.annuler(taFamilleTiers);
					hideDecoratedFields();
				}
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
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaFamilleTiers.class.getSimpleName()+".head");
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaFamilleTiers.class.getSimpleName()+".detail");
//		
//
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablHead = fonctionGetInfosXmlAndProperties.getMapAttributeTablHead(); 
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablDetail = fonctionGetInfosXmlAndProperties.getMapAttributeTablDetail();
//		
//		Collection<TaFamilleTiers> collectionTaFamille = modelFamille.getListeEntity();
//		
//		fonctionGetInfosXmlAndProperties.findInfosFileXml(TaFamilleTiers.class.getName(),TaFamilleTiersDTO.class.getName(),
//				listeChamp,"mapping");
//		fonctionGetInfosXmlAndProperties.getInfosObjetJPA(taFamilleTiers);
//		
//		ConstEdition constEdition = new ConstEdition(); 
////		Impression impression = new Impression(constEdition,taFamilleTiers,collectionTaFamille,nomChampIdTable,taFamilleTiers.getIdFamille());
//		
//		String nomDossier = null;
//		 
//		int nombreLine = collectionTaFamille.size();
//
//
//		if(nombreLine==0){
//			MessageDialog.openWarning(vue.getShell(), ConstEdition.TITRE_MESSAGE_EDITION_VIDE,
//					ConstEdition.EDITION_VIDE);
//		}
//		else{
//			if(taInfoEntreprise.getIdInfoEntreprise()==0){
//				nomDossier = constEdition.INFOS_VIDE;
//			}
//			else{
//				nomDossier = taInfoEntreprise.getNomInfoEntreprise();	
//			}
//
//			constEdition.addValueList(tableViewer, nomClassController);
//
//
//			String folderEditionDynamique = Const.C_RCP_INSTANCE_LOCATION+"/"+Const.C_NOM_PROJET_TMP+"/"+TaFamilleTiers.class.getSimpleName();
//			constEdition.makeFolderEditions(folderEditionDynamique);
//			Path pathFileReport = new Path(folderEditionDynamique+"/"+Const.C_NOM_VU_FAMILLE+".rptdesign");
////			Path pathFileReport = new Path(Const.C_RCP_INSTANCE_LOCATION+"/"+Const.C_NOM_PROJET_TMP+"/"
////					+Const.C_NOM_VU_FAMILLE+".rptdesign");
//			final String pathFileReportDynamic = pathFileReport.toPortableString();
//			
//			MakeDynamiqueReport DynamiqueReport = new MakeDynamiqueReport(constEdition.getNameTableEcran(),
//					constEdition.getNameTableBDD(),pathFileReportDynamic,Const.C_NOM_VU_FAMILLE,
//					ConstEdition.PAGE_ORIENTATION_LANDSCAPE,nomDossier); 
//			
//			/**************************************************************/
//			DynamiqueReport.setFonctionGetInfosXml(fonctionGetInfosXmlAndProperties);
//			DynamiqueReport.setNomObjet(TaFamilleTiers.class.getSimpleName());
//			/**************************************************************/
//			
//
//			Map<String, AttributElementResport> attribuGridHeader = new LinkedHashMap<String, AttributElementResport>();
//			String nameHeaderTitle = ConstEditionTiers.TITLE_EDITION_FAMILLE_TIERS;
//			attribuGridHeader.put(nameHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_XX_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//					,""));
//			String nameSousHeaderTitle = ConstEditionTiers.SOUS_TITLE_EDITION_FAMILLE_TIERS;
//			attribuGridHeader.put(nameSousHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_X_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//					,ConstEdition.COLOR_GRAY));
//
//			//DynamiqueReport.buildDesignConfig();
//
//			ConstEdition.CONTENT_COMMENTS = ConstEditionTiers.COMMENTAIRE_EDITION_DEFAUT;
//
//			DynamiqueReport.setSimpleNameEntity(TaFamilleTiers.class.getSimpleName());
//			DynamiqueReport.initializeBuildDesignReportConfig();
//			DynamiqueReport.makePageMater("1", "1", "1", "1", "100");
//			DynamiqueReport.makeReportHeaderGrid(3,5,100,ConstEdition.UNITS_PERCENTAGE,attribuGridHeader);
//			DynamiqueReport.biuldTableReport("100", ConstEdition.UNITS_PERCENTAGE, 
//					Const.C_NOM_VU_FAMILLE,1,1,2,"40", mapAttributeTablHead, mapAttributeTablDetail);
//			DynamiqueReport.savsAsDesignHandle();
////			impression.imprimer(true,pathFileReportDynamic,null,"Famille",TaFamilleTiers.class.getSimpleName(),false);
//			
//
//			/** 01/03/2010 modifier les editions (zhaolin) **/
//			BaseImpressionEdition impressionEdition = new BaseImpressionEdition(constEdition,taFamilleTiers,
//					getEm(),collectionTaFamille,taFamilleTiers.getIdFamille());
//			
//			impressionEdition.impressionEditionTypeEntity(pathFileReportDynamic,"Famille Tiers");
//		}
	}

	@Override
	protected boolean aideDisponible() {
		boolean result = false;
		switch ((getThis().getModeEcran().getMode())) {
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
				//				paramAfficheAideRecherche.setDb(getThis().getIbTaTable().getFIBBase());
				paramAfficheAideRecherche.setMessage(message);
				// Cration de l'ecran d'aide principal
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

				switch ((getThis().getModeEcran().getMode())) {
				case C_MO_CONSULTATION:
					if(getFocusCourantSWT().equals(vue.getGrille())){
						PaTypeFamilleTiers paFamilleSWT = new PaTypeFamilleTiers(s2,SWT.NULL);
						PaTypeFamilleTiersController swtPaFamilleController = new PaTypeFamilleTiersController(paFamilleSWT);

						editorCreationId = EditorFamilleTiers.ID;
						editorInputCreation = new EditorInputFamilleTiers();

						ParamAfficheFamilleTiers paramAfficheFamille = new ParamAfficheFamilleTiers();
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAfficheFamille.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheFamille.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaFamilleController;
						parametreEcranCreation = paramAfficheFamille;

						paramAfficheAideRecherche.setTypeEntite(TaFamilleTiers.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_FAMILLE);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_FAMILLE().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(swtPaFamilleController.getModelFamille());
						paramAfficheAideRecherche.setTypeObjet(swtPaFamilleController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_FAMILLE);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
					break;
				default:
					break;
				}

				if (paramAfficheAideRecherche.getJPQLQuery() != null) {

					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s,
							SWT.NULL);
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
		String validationContext = "FAMILLE_TIERS";
//		try {
//			IStatus s = null;
//			boolean verrouilleModifCode = false;
//			int change=0;
//			TaFamilleTiers u = new TaFamilleTiers();
//			PropertyUtils.setSimpleProperty(u, nomChamp, value);
//			if(((TaFamilleTiersDTO) selectedFamille.getValue()).getIdFamille()!=null) {
//				u.setIdFamille(((TaFamilleTiersDTO) selectedFamille.getValue()).getIdFamille());
//			}
//
//			if(nomChamp.equals(Const.C_CODE_FAMILLE)){
//				verrouilleModifCode = true;
//			}
//
//			s = dao.validate(u,nomChamp,validationContext,verrouilleModifCode);
//			if(s.getSeverity()!=IStatus.ERROR && change!=0) {
//
//			}
//			return s;
//		} catch (IllegalAccessException e) {
//			logger.error("",e);
//		} catch (InvocationTargetException e) {
//			logger.error("",e);
//		} catch (NoSuchMethodException e) {
//			logger.error("",e);
//		}
//		return null;
		
		//IStatus resultat = null;
		IStatus resultat = new Status(IStatus.OK,TiersPlugin.PLUGIN_ID,"validateUIField champ : "+nomChamp+" valeur : "+value!=null?value.toString():"valeur nulle"+" validation OK");
		
		int VALIDATION_CLIENT = 1;
		int VALIDATION_SERVEUR = 2;
		int VALIDATION_CLIENT_ET_SERVEUR = 3;
		
		//int TYPE_VALIDATION = VALIDATION_CLIENT;
		//int TYPE_VALIDATION = VALIDATION_SERVEUR;
		int TYPE_VALIDATION = VALIDATION_CLIENT_ET_SERVEUR;
		
		AbstractApplicationDAOClient<TaFamilleTiersDTO> a = new AbstractApplicationDAOClient<TaFamilleTiersDTO>();
		
		//validation client
		if(TYPE_VALIDATION==VALIDATION_CLIENT || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR) {
			//BeanValidatorJFaceDatabinding validator = new BeanValidatorJFaceDatabinding(TaTCiviliteDTO.class,nomChamp);
			//resultat = validator.validate(selectedTypeCivilite.getValue());
			try {
				TaFamilleTiersDTO f = new TaFamilleTiersDTO();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				//a.validate((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp, null);
				a.validate(f, nomChamp, validationContext);
			} catch(Exception e) {
				//if(resultat==null) {
					//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				//}
			}
		}
		//validation serveur
		if(resultat==null && (TYPE_VALIDATION==VALIDATION_SERVEUR || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR)) {
			try {
				TaFamilleTiersDTO f = new TaFamilleTiersDTO();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				//dao.validateDTOProperty((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp);
				dao.validateDTOProperty(f, nomChamp,validationContext);
			} catch(Exception e) {
				//if(resultat==null) {
					//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
					resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
				//}
				//e.printStackTrace();
			}
		}
		
		return resultat;
	}

	@Override
	protected void actEnregistrer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();
		try {
			//TODO ejb, controle à remettre
//			ctrlUnChampsSWT(getFocusCourantSWT());
			ctrlTousLesChampsAvantEnregistrementSWT();
			
//			dao.begin(transaction);
			//TaFamille u = null;
			TaFamilleTiersMapper mapper= new TaFamilleTiersMapper();
			//mapper sur client, envoi d'une entité					
//			mapper.mapDtoToEntity((TaFamilleTiersDTO) selectedFamille.getValue(),taFamilleTiers);
//			
//			if (modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)
//				taFamilleTiers=dao.enregistrerMerge(taFamilleTiers);
//			else if (modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)
//				dao.enregistrerPersist(taFamilleTiers);
				
//mapper sur serveur, envoi d'un DTO					
				if (modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)
					dao.enregistrerMergeDTO((TaFamilleTiersDTO) selectedFamille.getValue());
				else if (modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)
					dao.enregistrerPersistDTO((TaFamilleTiersDTO) selectedFamille.getValue());

				//dao.getEntityManager().flush();
//			dao.commit(transaction);
			modelFamille.addEntity(taFamilleTiers);
//			transaction = null;	
			
			getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
			
			actRefresh(); //deja present

		} catch(Exception e) {
			throw e;
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

//	protected void initEtatBouton() {
////		initEtatBouton(modelFamille.getListeObjet());
//		initEtatBouton();
//	}
	
	public void initEtatComposant() {
		try {			
			vue.getTfCODE_FAMILLE().setEditable(!isUtilise());
			changeCouleur(vue);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
		}

	}
	public boolean isUtilise(){
		return ((TaFamilleTiersDTO)selectedFamille.getValue()).getId()!=null && 
		!dao.recordModifiable(dao.getNomTable(),
				((TaFamilleTiersDTO)selectedFamille.getValue()).getId());		
	}
	public TaFamilleTiersDTO getSwtOldFamille() {
		return swtOldFamille;
	}

	public void setSwtOldFamille(TaFamilleTiersDTO swtOldFamille) {
		this.swtOldFamille = swtOldFamille;
	}

	public void setSwtOldFamille() {
		if (selectedFamille.getValue() != null){
			this.swtOldFamille = TaFamilleTiersDTO.copy((TaFamilleTiersDTO) selectedFamille.getValue());
		}
		else {
			if(tableViewer.selectionGrille(0)){
				this.swtOldFamille = TaFamilleTiersDTO.copy((TaFamilleTiersDTO) selectedFamille.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(TaFamilleTiersDTO) selectedFamille.getValue()), true);
			}}
	}

	public void setVue(PaTypeFamilleTiers vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();

		mapComposantDecoratedField.put(vue.getTfCODE_FAMILLE(), vue.getFieldCODE_FAMILLE());
		mapComposantDecoratedField.put(vue.getTfLIBC_FAMILLE(), vue.getFieldLIBC_FAMILLE());
		mapComposantDecoratedField.put(vue.getTfLIBL_FAMILLE(), vue.getFieldLIBL_FAMILLE());
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
		//rafraichissement des valeurs dans la grille
		writableList = new WritableList(realm, modelFamille.remplirListe(), classModel);
		tableViewer.setInput(writableList);
		tableViewer.refresh();

		//repositionnement sur la valeur courante
		int idActuel = 0;
		if (taFamilleTiers!=null) { //enregistrement en cours de modification/insertion
			idActuel = taFamilleTiers.getIdFamille();
		} else if(selectedFamille!=null && (TaFamilleTiersDTO) selectedFamille.getValue()!=null) {
			idActuel = ((TaFamilleTiersDTO) selectedFamille.getValue()).getId();
		}

		if(idActuel!=0) {
			tableViewer.setSelection(new StructuredSelection(modelFamille.recherche(Const.C_ID_FAMILLE
					, idActuel)));
		}else
			tableViewer.selectionGrille(0);				
	}

	public ModelGeneralObjetEJB<TaFamilleTiers,TaFamilleTiersDTO> getModelFamille() {
		return modelFamille;
	}


	public void destroy(DestroyEvent evt) {

		//		tableViewer.destroy();
		//		ibTaTableStandard.destroy();
		//		ibTaTable.destroy();
		//		vue.getGrille().dispose();
		//		mapComposantChamps.clear();
		//		mapComposantChamps=null;
		//		mapComposantDecoratedField.clear();
		//		mapComposantDecoratedField=null;
		//		listeComposantFocusable.clear();
		//		listeComposantFocusable=null;
		//		mapInitFocus.clear();
		//		mapInitFocus=null;
		//		mapActions.clear();
		//		mapActions=null;
		//		ecranAppelant=null;
		//		getVue().dispose();
		//		modelFamille.destroy();
		//		swtFamille=null;
		//		swtOldFamille=null;
		//		realm=null;
		//		dbc.dispose();
		//		writableList.dispose();
		//		selectedFamille.dispose();		
	}	

	@Override
	protected void finalize() throws Throwable {
		destroy(new DestroyEvent(this));
		super.finalize();
	}

	public TaFamilleTiers getTaFamilleUnite() {
		return taFamilleTiers;
	}

	public void actAfficherTous() throws Exception{
		vue.getGrille().setVisible(true);  
		vue.getBtnTous().setVisible(false);
		vue.getLaTitreGrille().setVisible(true);
		vue.getCompositeForm().setWeights(new int[]{50,100});
		dao.setJPQLQuery(dao.getJPQLQueryInitial());
		modelFamille.setJPQLQuery(null);
		modelFamille.setListeEntity(null);
		try {
			actRefresh();
		} catch (Exception e1) {
			logger.error("", e1);
		}
	}

	public void addChangementMasterEntityListener(IChangementMasterEntityListener l) {
		listenerList.add(IChangementMasterEntityListener.class, l);
	}
	
	public void removeChangementMasterEntityListener(IChangementMasterEntityListener l) {
		listenerList.remove(IChangementMasterEntityListener.class, l);
	}

	public TaFamilleTiers getTaFamilleTiers() {
		return taFamilleTiers;
	}

	public void setTaFamilleTiers(TaFamilleTiers taFamilleTiers) {
		this.taFamilleTiers = taFamilleTiers;
	}

}
