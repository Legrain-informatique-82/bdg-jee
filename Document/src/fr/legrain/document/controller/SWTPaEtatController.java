package fr.legrain.document.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.FinderException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.RollbackException;

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
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.DecoratedField;
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

import fr.legrain.articles.divers.Impression;
import fr.legrain.articles.editor.EditorFamilleUnite;
import fr.legrain.articles.editor.EditorInputFamille;
import fr.legrain.articles.editor.EditorInputUnite;
import fr.legrain.articles.editor.EditorUnite;
import fr.legrain.bdg.documents.service.remote.ITaReglementServiceRemote;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.tiers.service.remote.ITaEtatServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTCiviliteServiceRemote;
import fr.legrain.document.DocumentPlugin;
import fr.legrain.document.divers.ParamAfficheEtat;
import fr.legrain.document.dto.TaEtatDTO;
import fr.legrain.document.ecran.PaEtatSWT;
import fr.legrain.document.model.TaEtat;
//import fr.legrain.edition.actions.AttributElementResport;
//import fr.legrain.edition.actions.BaseImpressionEdition;
//import fr.legrain.edition.actions.ConstEdition;
//import fr.legrain.edition.actions.MakeDynamiqueReport;
//import fr.legrain.edition.dynamique.FonctionGetInfosXmlAndProperties;
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
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.InfosVerifSaisie;
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
import fr.legrain.tiers.dao.TaAdresse;
import fr.legrain.tiers.dao.TaTAdr;
import fr.legrain.tiers.dto.TaTCiviliteDTO;
import fr.legrain.tiers.ecran.SWTPaTiersController;

public class SWTPaEtatController extends EJBBaseControllerSWTStandard
implements RetourEcranListener {

	static Logger logger = Logger.getLogger(SWTPaEtatController.class.getName());
	private PaEtatSWT vue = null;
	private ITaEtatServiceRemote dao = null;//new TaEtatDAO(getEm());

	private Object ecranAppelant = null;
	private TaEtatDTO swtEtat;
	private TaEtatDTO swtOldEtat;
	private Realm realm;
	private DataBindingContext dbc;

	private Class classModel = TaEtatDTO.class;
	private ModelGeneralObjetEJB<TaEtat,TaEtatDTO> modelEtat = null;//new ModelGeneralObjet<TaEtatDTO,TaEtatDAO,TaEtat>(dao,classModel);
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedEtat;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();

	private TaEtat taEtat = null;

	private MapChangeListener changeListener = new MapChangeListener();
	private LgrDozerMapper<TaEtatDTO,TaEtat> mapperUIToModel = new LgrDozerMapper<TaEtatDTO,TaEtat>();
	
	public SWTPaEtatController(PaEtatSWT vue) {
		this(vue,null);
	}

	public SWTPaEtatController(PaEtatSWT vue,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
		try {
			dao = new EJBLookup<ITaEtatServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_ETAT_SERVICE, ITaEtatServiceRemote.class.getName());
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		modelEtat = new ModelGeneralObjetEJB<TaEtat,TaEtatDTO>(dao);
		setVue(vue);

		int nb = 10;

		// a faire avant l'initialisation du Binding
		vue.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldUnite();
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
			Menu[] tabPopups = new Menu[] { popupMenuFormulaire, popupMenuGrille };
			this.initPopupAndButtons(mapActions, tabPopups);
			vue.getPaCorpsFormulaire().setMenu(popupMenuFormulaire);
			vue.getPaGrille().setMenu(popupMenuGrille);

			initEtatBouton();
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
			logger.error("Erreur : PaArticlesController", e);
		}
	}

	public void bind(PaEtatSWT paUniteSWT) {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(paUniteSWT.getGrille());
			tableViewer.createTableCol(classModel,paUniteSWT.getGrille(), nomClassController,
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
//			writableList = new WritableList(realm, modelUnite.remplirListe(), classModel);
//			tableViewer.setInput(writableList);
			
			// Set up data binding.
			LgrViewerSupport.bind(tableViewer, 
					new WritableList(modelEtat.remplirListe(), classModel),
					BeanProperties.values(listeChamp)
					);

			selectedEtat = ViewersObservables.observeSingleSelection(tableViewer);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedEtat,classModel);
			changementDeSelection();
			selectedEtat.addChangeListener(new IChangeListener() {

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
		if(selectedEtat!=null && selectedEtat.getValue()!=null) {
			if(((TaEtatDTO) selectedEtat.getValue()).getIdEtat()!=null) {
				try {
					taEtat = dao.findById(((TaEtatDTO) selectedEtat.getValue()).getIdEtat());
				} catch (FinderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaEtatController.this));
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			if (((ParamAfficheEtat) param).getFocusDefautSWT() != null && !((ParamAfficheEtat) param).getFocusDefautSWT().isDisposed())
				((ParamAfficheEtat) param).getFocusDefautSWT().setFocus();
			else
				((ParamAfficheEtat) param).setFocusDefautSWT(vue.getGrille());
			vue.getLaTitreFormulaire().setText(((ParamAfficheEtat) param).getTitreFormulaire());
			vue.getLaTitreGrille().setText(((ParamAfficheEtat) param).getTitreGrille());
			vue.getLaTitreFenetre().setText(((ParamAfficheEtat) param).getSousTitre());

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}
			Map<String,String[]> map = dao.getParamWhereSQL();
			if(param.getIdFiche()!=null &&  !param.getIdFiche().equals("")) {
				if(map==null) map = new HashMap<String,String[]>();
				map.clear();
				map.put("idUnite",new String[]{"=",param.getIdFiche()});
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
			setSwtOldUnite();

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
		
		mapInfosVerifSaisie.put(vue.getTfCODE(), new InfosVerifSaisie(new TaEtat(),Const.C_CODE_ETAT,null));
		mapInfosVerifSaisie.put(vue.getTfLIBL(), new InfosVerifSaisie(new TaEtat(),Const.C_LIBL_ETAT,null));

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

		vue.getTfCODE().setToolTipText(Const.C_CODE_ETAT);
		vue.getTfLIBL().setToolTipText(Const.C_LIBL_ETAT);

		mapComposantChamps.put(vue.getTfCODE(), Const.C_CODE_ETAT);
		mapComposantChamps.put(vue.getTfLIBL(), Const.C_LIBL_ETAT);

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
				.getTfCODE());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfCODE());
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

	public SWTPaEtatController getThis() {
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
					.getString("Unite.Message.Enregistrer"))) {

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
			if(getModeEcran().dataSetEnModif())
				try {
					dao.annuler(taEtat);
				} catch (Exception e) {
					throw new ExceptLgr();
				}
			if (ecranAppelant instanceof SWTPaAideControllerSWT) {
				setListeEntity(getModelEtat().remplirListe());
				dao.initValeurIdTable(taEtat);
				fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
						dao.getChampIdEntite(), dao.getValeurIdTable(),
						selectedEtat.getValue())));

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
//					if(getFocusAvantAideSWT().equals(vue.getTfCODE_FAMILLE())){
//						TaFamilleUnite f = null;
//						TaFamilleUniteDAO t = new TaFamilleUniteDAO(getEm());
//						f = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
//						t = null;
//						taEtat.setTaFamilleUnite(f);
//					}					
//					ctrlUnChampsSWT(getFocusAvantAideSWT());
					
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
		} else if (evt.getRetour() != null){
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
				setSwtOldUnite();
				swtEtat = new TaEtatDTO();

				taEtat = new TaEtat();
				dao.inserer(taEtat);
				modelEtat.getListeObjet().add(swtEtat);
				writableList = new WritableList(realm, modelEtat.getListeObjet(), classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(swtEtat));
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
				setSwtOldUnite();
				taEtat = dao.findById(((TaEtatDTO) selectedEtat.getValue()).getIdEtat());
			}else{
				if(!setSwtOldUniteRefresh())throw new Exception();
			}
			dao.modifier(taEtat);
			initEtatBouton();
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		}
	}
	
	public boolean containsEntity(TaEtat entite){
		if(modelEtat.getListeEntity()!=null){
			for (Object e : modelEtat.getListeEntity()) {
				if(((TaEtat)e).getIdEtat()==
					entite.getIdEtat())return true;
			}
		}
		return false;
	}


	public boolean setSwtOldUniteRefresh() {
		try {	
			if (selectedEtat.getValue()!=null){
				TaEtat taArticleOld =dao.findById(taEtat.getIdEtat());
//				taArticleOld=dao.refresh(taArticleOld);
//				if(containsEntity(taEtat)) 
//					modelEtat.getListeEntity().remove(taEtat);
//				if(!taEtat.getVersionObj().equals(taArticleOld.getVersionObj())){
//					taEtat=taArticleOld;
//					if(!containsEntity(taEtat)) 
//						modelEtat.getListeEntity().add(taEtat);					
//					actRefresh();
//					dao.messageNonAutoriseModification();
//				}
				taEtat=taArticleOld;
				if(!containsEntity(taEtat)) 
					modelEtat.getListeEntity().add(taEtat);
				changementDeSelection();
				this.swtOldEtat=TaEtatDTO.copy((TaEtatDTO)selectedEtat.getValue());
			}
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
//	public void setSwtOldUniteRefresh() {
//		if (selectedUnite.getValue()!=null){
//			if(LgrMess.isDOSSIER_EN_RESEAU())dao.refresh(dao.findById(((TaEtatDTO) selectedUnite.getValue()).getIdUnite()));
//			taUnite=dao.findById(((TaEtatDTO) selectedUnite.getValue()).getIdUnite());
//			try {
//				if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
//			} catch (Exception e) {
//				logger.error("",e);
//			}			
//			this.swtOldUnite=TaEtatDTO.copy((TaEtatDTO)selectedUnite.getValue());
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
						.getString("Unite.Message.Supprimer"))) {

//					dao.begin(transaction);
					TaEtat u = dao.findById(((TaEtatDTO) selectedEtat.getValue()).getIdEtat());
					dao.supprimer(u);
//					dao.commit(transaction);
					Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
					modelEtat.removeEntity(u);
					taEtat=null;
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
						.getString("Unite.Message.Annuler")))) {
					remetTousLesChampsApresAnnulationSWT(dbc);
					if(((TaEtatDTO) selectedEtat.getValue()).getIdEtat()==null){
						modelEtat.getListeObjet().remove(
								((TaEtatDTO) selectedEtat.getValue()));
						writableList = new WritableList(realm, modelEtat
								.getListeObjet(), classModel);
						tableViewer.setInput(writableList);
						tableViewer.refresh();
						if(swtOldEtat!=null)
							tableViewer.setSelection(new StructuredSelection(swtOldEtat), true);
						else
							tableViewer.selectionGrille(0);
					}
					dao.annuler(taEtat);
					hideDecoratedFields();
				}
				initEtatBouton();
				break;
			case C_MO_EDITION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("Unite.Message.Annuler")))) {
					int rang = modelEtat.getListeObjet().indexOf(selectedEtat.getValue());
					remetTousLesChampsApresAnnulationSWT(dbc);
					modelEtat.getListeObjet().set(rang, swtOldEtat);
					writableList = new WritableList(realm, modelEtat.getListeObjet(), classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldEtat), true);
					dao.annuler(taEtat);
					hideDecoratedFields();
				}
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
//		/******************************/
//		TaInfoEntrepriseDAO taInfoEntrepriseDAO = new TaInfoEntrepriseDAO(getEm());
//		TaInfoEntreprise taInfoEntreprise = taInfoEntrepriseDAO.findInstance();
//
//		String nomChampIdTable =  dao.getChampIdTable();
//		
//		FonctionGetInfosXmlAndProperties fonctionGetInfosXmlAndProperties = new FonctionGetInfosXmlAndProperties(mapperUIToModel);
//		fonctionGetInfosXmlAndProperties.cleanValueMapAttributeTable();
//
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaEtat.class.getSimpleName()+".head");
//		fonctionGetInfosXmlAndProperties.setValueMapAttributeTable(TaEtat.class.getSimpleName()+".detail");
//		
//
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablHead = fonctionGetInfosXmlAndProperties.getMapAttributeTablHead(); 
//		LinkedHashMap<String,AttributElementResport> mapAttributeTablDetail = fonctionGetInfosXmlAndProperties.getMapAttributeTablDetail();
//		
//		Collection<TaEtat> collectionTaUnite = modelEtat.getListeEntity();
//
//		fonctionGetInfosXmlAndProperties.findInfosFileXml(TaEtat.class.getName(),TaEtatDTO.class.getName(),
//				listeChamp,"mapping");
//		fonctionGetInfosXmlAndProperties.getInfosObjetJPA(taEtat);
//		
//		ConstEdition constEdition = new ConstEdition(); 
////		Impression impression = new Impression(constEdition,taUnite,collectionTaUnite,nomChampIdTable,
////				taUnite.getIdUnite());
//		
//		String nomDossier = null;
//		
//		int nombreLine = collectionTaUnite.size();
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
//			String folderEditionDynamique = Const.C_RCP_INSTANCE_LOCATION+ConstEdition.SEPARATOR+
//					Const.C_NOM_PROJET_TMP+ConstEdition.SEPARATOR+TaEtat.class.getSimpleName();
//			constEdition.makeFolderEditions(folderEditionDynamique);
//			Path pathFileReport = new Path(folderEditionDynamique+ConstEdition.SEPARATOR+Const.C_NOM_VU_UNITE+".rptdesign");
//			final String pathFileReportDynamic = pathFileReport.toPortableString();
//
//			MakeDynamiqueReport DynamiqueReport = new MakeDynamiqueReport(constEdition.getNameTableEcran(),
//					constEdition.getNameTableBDD(),pathFileReportDynamic,Const.C_NOM_VU_UNITE,
//					ConstEdition.PAGE_ORIENTATION_LANDSCAPE,nomDossier); 
//			
//			/**************************************************************/
//			DynamiqueReport.setFonctionGetInfosXml(fonctionGetInfosXmlAndProperties);
//			DynamiqueReport.setNomObjet(TaEtat.class.getSimpleName());
//			/**************************************************************/
//
//			Map<String, AttributElementResport> attribuGridHeader = new LinkedHashMap<String, AttributElementResport>();
//			String nameHeaderTitle = ConstEditionArticle.TITLE_EDITION_ARTICLE_UNITE;
//			attribuGridHeader.put(nameHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_XX_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//					,""));
//			String nameSousHeaderTitle = ConstEditionArticle.SOUS_TITLE_EDITION_ARTICLE_UNITE;
//			attribuGridHeader.put(nameSousHeaderTitle, new AttributElementResport("",ConstEdition.TEXT_ALIGN_CENTER,
//					ConstEdition.FONT_SIZE_X_LARGE,ConstEdition.FONT_WEIGHT_BOLD,"",
//					ConstEdition.COLUMN_DATA_TYPE_STRING,ConstEdition.UNITS_VIDE
//					,ConstEdition.COLOR_GRAY));
//
//			//DynamiqueReport.buildDesignConfig();
//			ConstEdition.CONTENT_COMMENTS = ConstEditionArticle.COMMENTAIRE_EDITION_DEFAUT;
//
//			DynamiqueReport.setSimpleNameEntity(TaEtat.class.getSimpleName());
//			
//			DynamiqueReport.initializeBuildDesignReportConfig();
//			DynamiqueReport.makePageMater("1", "1", "1", "1", "100");
//			DynamiqueReport.makeReportHeaderGrid(3,5,100,ConstEdition.UNITS_PERCENTAGE,attribuGridHeader);
//			DynamiqueReport.biuldTableReport("100", ConstEdition.UNITS_PERCENTAGE, 
//					Const.C_NOM_VU_UNITE,1,1,2,"40", mapAttributeTablHead, mapAttributeTablDetail);
//			DynamiqueReport.savsAsDesignHandle();
////			impression.imprimer(true,pathFileReportDynamic,null,"Unite",TaEtat.class.getSimpleName(),false);
//			
//			BaseImpressionEdition impressionEdition = new BaseImpressionEdition(constEdition,taEtat,
//					getEm(),collectionTaUnite,taEtat.getIdEtat());
//			
//			impressionEdition.impressionEditionTypeEntity(pathFileReportDynamic,"Unité");
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
//			if(getFocusCourantSWT().equals(vue.getTfCODE_FAMILLE()))
//				result = true;			
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

				switch ((SWTPaEtatController.this.getModeEcran().getMode())) {
				case C_MO_CONSULTATION:
					if(getFocusCourantSWT().equals(vue.getGrille())){
//						PaUniteSWT paUniteSWT = new PaUniteSWT(s2,SWT.NULL);
//						SWTPaUniteController swtPaUniteController = new SWTPaUniteController(paUniteSWT);
//
//						editorCreationId = EditorUnite.ID;
//						editorInputCreation = new EditorInputUnite();
//
//						ParamAfficheUnite paramAfficheUnite = new ParamAfficheUnite();
//						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
//						paramAfficheUnite.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAfficheUnite.setEcranAppelant(paAideController);
//						controllerEcranCreation = swtPaUniteController;
//						parametreEcranCreation = paramAfficheUnite;
//						
//						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);
//
//
//						paramAfficheAideRecherche.setTypeEntite(TaEtat.class);
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_UNITE);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_UNITE().getText());
//						paramAfficheAideRecherche.setControllerAppelant(SWTPaUniteController.this);
//						paramAfficheAideRecherche.setModel(swtPaUniteController.getModelUnite());
//						paramAfficheAideRecherche.setTypeObjet(swtPaUniteController.getClassModel());
//						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_UNITE);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
//					if(getFocusCourantSWT().equals(vue.getTfCODE_FAMILLE())){
//
//						PaFamilleUnite paFamilleSWT = new PaFamilleUnite(s2,SWT.NULL); 
//						PaFamilleUniteController swtPaFamilleController = new PaFamilleUniteController(paFamilleSWT);
//
//						editorCreationId = EditorFamilleUnite.ID;
//						editorInputCreation = new EditorInputFamille();
//
//						ParamAfficheFamille paramAfficheFamille = new ParamAfficheFamille();
//						paramAfficheAideRecherche.setJPQLQuery(new TaFamilleUniteDAO(getEm()).getJPQLQuery());
//						paramAfficheFamille.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAfficheFamille.setEcranAppelant(paAideController);
//						/* 
//						 * controllerEcranCreation ne sert plus a rien, pour l'editeur de creation, on creer un nouveau controller
//						 */
//						controllerEcranCreation = swtPaFamilleController;
//						parametreEcranCreation = paramAfficheFamille;
//
//						paramAfficheAideRecherche.setTypeEntite(TaFamilleUnite.class);
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_FAMILLE);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_FAMILLE().getText());
//						paramAfficheAideRecherche.setControllerAppelant(SWTPaEtatController.this);
//						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTFamille,TaFamilleUniteDAO,TaFamilleUnite>(SWTFamille.class,dao.getEntityManager()));
//						paramAfficheAideRecherche.setTypeObjet(swtPaFamilleController.getClassModel());
//
//						paramAfficheAideRecherche.setChampsIdentifiant(swtPaFamilleController.getDao().getChampIdTable());
//					}
					
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
					paAideController.addRetourEcranListener(SWTPaEtatController.this);
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
		String validationContext = "TA_ETAT";
			
			IStatus resultat = new Status(IStatus.OK,TiersPlugin.PLUGIN_ID,"validateUIField champ : "+nomChamp+" valeur : "+value!=null?value.toString():"valeur nulle"+" validation OK");
			
			int VALIDATION_CLIENT = 1;
			int VALIDATION_SERVEUR = 2;
			int VALIDATION_CLIENT_ET_SERVEUR = 3;
			
			//int TYPE_VALIDATION = VALIDATION_CLIENT;
			//int TYPE_VALIDATION = VALIDATION_SERVEUR;
			int TYPE_VALIDATION = VALIDATION_CLIENT_ET_SERVEUR;
			
			AbstractApplicationDAOClient<TaEtatDTO> validationClient = new AbstractApplicationDAOClient<TaEtatDTO>();
			
			//validation client
			if(TYPE_VALIDATION==VALIDATION_CLIENT || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR) {
				//BeanValidatorJFaceDatabinding validator = new BeanValidatorJFaceDatabinding(TaTCiviliteDTO.class,nomChamp);
				//resultat = validator.validate(selectedTypeCivilite.getValue());
				try {
					TaEtatDTO f = new TaEtatDTO();
					PropertyUtils.setSimpleProperty(f, nomChamp, value);
					//a.validate((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp, null);
					validationClient.validate(f, nomChamp, ITaEtatServiceRemote.validationContext);
				} catch(Exception e) {
					//if(resultat==null) {
						//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
						resultat = new Status(IStatus.ERROR,DocumentPlugin.PLUGIN_ID, e.getMessage(), e);
					//}
				}
			}
			//validation serveur
			if(resultat==null && (TYPE_VALIDATION==VALIDATION_SERVEUR || TYPE_VALIDATION==VALIDATION_CLIENT_ET_SERVEUR)) {
				try {
					TaEtatDTO f = new TaEtatDTO();
					PropertyUtils.setSimpleProperty(f, nomChamp, value);
					//dao.validateDTOProperty((TaTCiviliteDTO)selectedTypeCivilite.getValue(), nomChamp);
					dao.validateDTOProperty(f, nomChamp, ITaEtatServiceRemote.validationContext);
				} catch(Exception e) {
					//if(resultat==null) {
						//resultat = new MultiStatus(TiersPlugin.PLUGIN_ID, 0, e.getMessage(), e);
						resultat = new Status(IStatus.ERROR,TiersPlugin.PLUGIN_ID, e.getMessage(), e);
					//}
					//e.printStackTrace();
				}
			}
			
			return resultat;
			
			//passage ejb
//			setActiveAide(true);
//			IStatus s = null;
//			int change=0;
////			if(nomChamp.equals(Const.C_CODE_FAMILLE)) {
////				TaFamilleUniteDAO dao = new TaFamilleUniteDAO(getEm());
////
////				dao.setModeObjet(getDao().getModeObjet());
////				TaFamilleUnite f = new TaFamilleUnite();
////				PropertyUtils.setSimpleProperty(f, nomChamp, value);
////				s = dao.validate(f,nomChamp,validationContext);
////
////				if(s.getSeverity()!=IStatus.ERROR ){
////					f = dao.findByCode((String)value);
////					taEtat.setTaFamilleUnite(f);
////				}
////				dao = null;
////			} else {
//				boolean verrouilleModifCode = false;
//				TaEtat u = new TaEtat();
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//				if(((TaEtatDTO) selectedEtat.getValue()).getIdEtat()!=null) {
//					u.setIdEtat(((TaEtatDTO) selectedEtat.getValue()).getIdEtat());
//				}
//				if(nomChamp.equals(Const.C_CODE_ETAT)){
//					verrouilleModifCode = true;
//				}
//
//				s = dao.validate(u,nomChamp,validationContext,verrouilleModifCode);
//				if(s.getSeverity()!=IStatus.ERROR && change!=0) {
//
//				}
////			}
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
//			EntityTransaction transaction = dao.getEntityManager().getTransaction();
			try {
			ctrlUnChampsSWT(getFocusCourantSWT());
			ctrlTousLesChampsAvantEnregistrementSWT();
//			dao.begin(transaction);

			if ( (modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
				LgrDozerMapper<TaEtatDTO,TaEtat> mapper = new LgrDozerMapper<TaEtatDTO,TaEtat>();
				mapper.map((TaEtatDTO) selectedEtat.getValue(),taEtat);

				dao.enregistrerMerge(taEtat);


			}
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)
						|| (modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
				mapperUIToModel.map((TaEtatDTO) selectedEtat.getValue(),taEtat);

				taEtat=dao.enregistrerMerge(taEtat);
//				modelUnite.getListeEntity().add(taUnite);
			}
			
//			dao.commit(transaction);
			modelEtat.addEntity(taEtat);
//			transaction = null;
			actRefresh(); 

		} catch (RollbackException e) {	
			logger.error("",e);
			if(e.getCause() instanceof OptimisticLockException)
				MessageDialog.openError(vue.getShell(), "", e.getMessage()+"\n"+e.getCause().getMessage());
		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
		}
	}

	public void initEtatComposant() {
		try {
			vue.getTfCODE().setEditable(!isUtilise());
			changeCouleur(vue);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
		}
	}
	public boolean isUtilise(){
		return (((TaEtatDTO)selectedEtat.getValue()).getIdEtat()!=null &&
		!dao.recordModifiable(dao.getNomTable(),
				((TaEtatDTO)selectedEtat.getValue()).getIdEtat()))||
				!dao.autoriseModification(taEtat);		
	}
	public TaEtatDTO getSwtOldEtat() {
		return swtOldEtat;
	}

	public void setSwtOldEtat(TaEtatDTO swtOldUnite) {
		this.swtOldEtat = swtOldUnite;
	}

	public void setSwtOldUnite() {
		if (selectedEtat.getValue() != null)
			this.swtOldEtat = TaEtatDTO.copy((TaEtatDTO) selectedEtat.getValue());
		else {
			if(tableViewer.selectionGrille(0)){
				this.swtOldEtat = TaEtatDTO.copy((TaEtatDTO) selectedEtat.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(TaEtatDTO) selectedEtat.getValue()), true);
			}
		}
	}

	public void setVue(PaEtatSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null) 
			mapComposantDecoratedField = new LinkedHashMap<Control,ControlDecoration>();

		mapComposantDecoratedField.put(vue.getTfCODE(), vue.getFieldCODE());
		mapComposantDecoratedField.put(vue.getTfLIBL(), vue.getFieldLIBL());

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
		if (taEtat!=null) { //enregistrement en cours de modification/insertion
			idActuel = taEtat.getIdEtat();
		} else if(selectedEtat!=null && (TaEtatDTO) selectedEtat.getValue()!=null) {
			idActuel = ((TaEtatDTO) selectedEtat.getValue()).getIdEtat();
		}

		if (getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_CONSULTATION) == 0) {
			//rafraichissement des valeurs dans la grille
			writableList = new WritableList(realm, modelEtat.remplirListe(), classModel);
			tableViewer.setInput(writableList);
		} else {
			if (taEtat!=null && selectedEtat!=null && (TaEtatDTO) selectedEtat.getValue()!=null) {
				new LgrDozerMapper<TaEtat, TaEtatDTO>().
					map(taEtat, (TaEtatDTO) selectedEtat.getValue());
			}
		}

		if(idActuel!=0) {
			tableViewer.setSelection(new StructuredSelection(modelEtat.recherche(Const.C_ID_ETAT, idActuel)));
		}else
			tableViewer.selectionGrille(0);

	}

	public ModelGeneralObjetEJB<TaEtat,TaEtatDTO> getModelEtat() {
		return modelEtat;
	}

	public ITaEtatServiceRemote getDao() {
		return dao;
	}

	public TaEtat getTaEtat() {
		return taEtat;
	}
	public void actAfficherTous() throws Exception{
		vue.getGrille().setVisible(true);  
		vue.getBtnTous().setVisible(false);
		vue.getLaTitreGrille().setVisible(true);
		vue.getCompositeForm().setWeights(new int[]{50,100});
		dao.setJPQLQuery(dao.getJPQLQueryInitial());
		modelEtat.setJPQLQuery(null);
		modelEtat.setListeEntity(null);
		try {
			actRefresh();
		} catch (Exception e1) {
			logger.error("", e1);
		}
	}
}

