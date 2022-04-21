package fr.legrain.articles.ecran;

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
import org.eclipse.core.databinding.validation.ValidationStatus;
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

import fr.legrain.article.dto.TaTConditionnementDTO;
import fr.legrain.article.dto.TaUniteDTO;
import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaUnite;
import fr.legrain.articles.ArticlesPlugin;
import fr.legrain.articles.editor.EditorConditionnementArticle;
import fr.legrain.articles.editor.EditorInputConditionnementArticle;
import fr.legrain.bdg.article.service.remote.ITaArticleServiceRemote;
import fr.legrain.bdg.article.service.remote.ITaTConditionnementServiceRemote;
import fr.legrain.bdg.article.service.remote.ITaUniteServiceRemote;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.model.mapping.mapper.TaUniteMapper;
import fr.legrain.gestCom.Appli.Const;
//import fr.legrain.gestCom.Appli.LgrDozerMapper;
import fr.legrain.gestCom.Appli.ModelGeneralObjetEJB;
import fr.legrain.gestCom.librairiesEcran.editor.EditorAide;
import fr.legrain.gestCom.librairiesEcran.editor.EditorInputAide;
import fr.legrain.gestCom.librairiesEcran.swt.DeclencheCommandeControllerEvent;
import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.IDetailController;
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
import fr.legrain.libMessageLGR.LgrMess;
import fr.legrain.tiers.clientutility.JNDILookupClass;


public class SWTPaConditionnementController extends EJBBaseControllerSWTStandard
implements RetourEcranListener,IDetailController {

	static Logger logger = Logger.getLogger(SWTPaConditionnementController.class.getName());
	private PaConditionnementArticleSWT vue = null;
	private ITaUniteServiceRemote dao = null;
	private String idArticle = null;

	private Object ecranAppelant = null;
	private TaUniteDTO swtUnite;
	private TaUniteDTO swtOldUnite;
	private Realm realm;
	private DataBindingContext dbc;

	private Class classModel = TaUniteDTO.class;
	private ModelGeneralObjetEJB<TaUnite,TaUniteDTO> modelUnite = null;
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedUnite;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();

	private TaArticle masterEntity = null;
	private ITaArticleServiceRemote masterDAO = null;

	private TaUnite taUnite = null;

	private MapChangeListener changeListener = new MapChangeListener();

	public SWTPaConditionnementController(PaConditionnementArticleSWT vue) {
		this(vue,null);
	}

	public SWTPaConditionnementController(PaConditionnementArticleSWT composite,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
		try {
			dao = new EJBLookup<ITaUniteServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_UNITE_SERVICE, ITaUniteServiceRemote.class.getName());
		} catch (NamingException e1) {
			logger.error("",e1);
		}
		modelUnite = new ModelGeneralObjetEJB<TaUnite,TaUniteDTO>(dao);
		setVue(composite);
		// a faire avant l'initialisation du Binding
		composite.getGrille().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSwtOldConditionnementArticle();
			}
		});
		composite.getShell().addShellListener(this);

		// Branchement action annuler : empeche la fermeture automatique de la
		// fenetre sur ESC
		composite.getShell().addTraverseListener(new Traverse());

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
			logger.error("Erreur : SWTPaPrixController", e);
		}
	}

	/**
	 * Creation des objet pour l'interface, a partir de l'entite principale.<br>
	 * Ici : creation d'une liste de prix pour l'IHM, a partir de la liste des prix contenue dans l'entite article.
	 * @return
	 */
	public List<TaUniteDTO> IHMmodel() {
		LinkedList<TaUnite> ldao = new LinkedList<TaUnite>();
		LinkedList<TaUniteDTO> lswt = new LinkedList<TaUniteDTO>();
		//masterEntity.getTaUnitees().clear();
		//if(masterEntity!=null)	masterDAO.refresh(masterEntity);
		if(masterEntity!=null && !masterEntity.getTaConditionnementsArticle().isEmpty()) {

			ldao.addAll(masterEntity.getTaConditionnementsArticle());

			TaUniteMapper mapper = new TaUniteMapper();
//			LgrDozerMapper<TaUnite,TaUniteDTO> mapper = new LgrDozerMapper<TaUnite,TaUniteDTO>();
			for (TaUnite o : ldao) {
				TaUniteDTO t = null;
				t =  mapper.mapEntityToDto(o, t);
				lswt.add(t);
			}

		}
		return lswt;
	}

	public void bind(PaConditionnementArticleSWT PaConditionnementArticleSWT) {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			tableViewer = new LgrTableViewer(PaConditionnementArticleSWT.getGrille());
			tableViewer.createTableCol(classModel,PaConditionnementArticleSWT.getGrille(), nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE,-1);
			listeChamp = tableViewer.setListeChampGrille(nomClassController,
					Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			selectedUnite = ViewersObservables.observeSingleSelection(tableViewer);

			LgrViewerSupport.bind(tableViewer, 
					new WritableList(IHMmodel(), classModel),
					BeanProperties.values(listeChamp)
			);

			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedUnite,classModel);
			tableViewer.setChecked(tableViewer.getTable().getColumn(1),true);
			changementDeSelection();
			selectedUnite.addChangeListener(new IChangeListener() {

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
		if(selectedUnite!=null && selectedUnite.getValue()!=null) {
			if(((TaUniteDTO) selectedUnite.getValue()).getId()!=null) {
				try {
					taUnite = dao.findById(((TaUniteDTO) selectedUnite.getValue()).getId());
				} catch (FinderException e) {
					logger.error("",e);
				}
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaConditionnementController.this));
		}
	}
	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			Map<String,String[]> map = dao.getParamWhereSQL();
			if (param.getFocusDefautSWT() != null && !param.getFocusDefautSWT().isDisposed())
				param.getFocusDefautSWT().setFocus();
			else
				param.setFocusDefautSWT(vue
						.getGrille());
			if(param.getTitreFormulaire()!=null){
				vue.getLaTitreFormulaire().setText(param.getTitreFormulaire());
			} else {
				vue.getLaTitreFormulaire().setText(ParamAffichePrix.C_TITRE_FORMULAIRE);
			}

			if(param.getTitreGrille()!=null){
				vue.getLaTitreGrille().setText(param.getTitreGrille());
			} else {
				vue.getLaTitreGrille().setText(ParamAffichePrix.C_TITRE_GRILLE);
			}

			if(param.getSousTitre()!=null){
				vue.getLaTitreFenetre().setText(param.getSousTitre());
			} else {
				vue.getLaTitreFenetre().setText(ParamAffichePrix.C_SOUS_TITRE);
			}

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}

			if(tableViewer==null) {
				//databinding pas encore realise
				bind(vue);
				tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			} else {
				try {
					taUnite=null;
					selectedUnite.setValue(null);
					actRefresh();
				} catch (Exception e) {
					logger.error("",e);
				}
			}
			// permet de se positionner sur le 1er enregistrement et de remplir
			// le formulaire
			//			tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
			setSwtOldConditionnementArticle();

			if (param.getModeEcran() != null
					&& param.getModeEcran().compareTo(
							EnumModeObjet.C_MO_INSERTION) == 0) {
				try {
					actInserer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			} else {
				initEtatBouton();
			}

		}
		return null;
	}

	protected void initVerifySaisie() {
		if (mapInfosVerifSaisie == null)
			mapInfosVerifSaisie = new HashMap<Control, InfosVerifSaisie>();

//		mapInfosVerifSaisie.put(vue.getTfPRIX_PRIX(), new InfosVerifSaisie(new TaUnite(),Const.C_PRIX_PRIX,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
//		mapInfosVerifSaisie.put(vue.getTfLargeur(), new InfosVerifSaisie(new TaUnite(),Const.C_LARGEUR_UNITE,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		
//		mapInfosVerifSaisie.put(vue.getTfLongueur(), new InfosVerifSaisie(new TaUnite(),Const.C_LONGUEUR_UNITE,null));

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

		vue.getTfCode().setToolTipText(Const.C_CODE_UNITE);
//		vue.getTfCodeType().setToolTipText(Const.C_CODE_T_CONDITIONNEMENT);
		vue.getTfHauteur().setToolTipText(Const.C_HAUTEUR_UNITE);
		vue.getTfLargeur().setToolTipText(Const.C_LARGEUR_UNITE);
		vue.getTfLibelle().setToolTipText(Const.C_LIBL_UNITE);
		vue.getTfLongueur().setToolTipText(Const.C_LONGUEUR_UNITE);
		vue.getTfNbUnite().setToolTipText(Const.C_NB_UNITE_UNITE);
		vue.getTfPoids().setToolTipText(Const.C_POIDS_UNITE);
//		vue.getTfReduction().setToolTipText(Const.C_REDUCTION_CONDITIONNEMENT_ARTICLE);

		mapComposantChamps.put(vue.getTfCode(), Const.C_CODE_UNITE);
//		mapComposantChamps.put(vue.getTfCodeType(), Const.C_CODE_T_CONDITIONNEMENT);
		mapComposantChamps.put(vue.getTfHauteur(), Const.C_HAUTEUR_UNITE);
		mapComposantChamps.put(vue.getTfLargeur(), Const.C_LARGEUR_UNITE);
		mapComposantChamps.put(vue.getTfLibelle(), Const.C_LIBL_UNITE);
		mapComposantChamps.put(vue.getTfLongueur(), Const.C_LONGUEUR_UNITE);
		mapComposantChamps.put(vue.getTfNbUnite(), Const.C_NB_UNITE_UNITE);
		mapComposantChamps.put(vue.getTfPoids(), Const.C_POIDS_UNITE);
//		mapComposantChamps.put(vue.getTfReduction(), Const.C_REDUCTION_CONDITIONNEMENT_ARTICLE);


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
				.getTfLongueur());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue
				.getTfLongueur());
		mapInitFocus.put(EnumModeObjet.C_MO_CONSULTATION, vue
				.getGrille());

		activeModifytListener();

//		vue.getTfPRIX_PRIX().addVerifyListener(queDesChiffres);
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

//	protected void initEtatBouton() {
//		//initEtatBoutonCommand();
//		initEtatBouton(IHMmodel());
//		enableActionAndHandler(C_COMMAND_GLOBAL_IMPRIMER_ID,false);
//		
//		switch (daoStandard.getModeObjet().getMode()) {
//		case C_MO_INSERTION:
//			enableActionAndHandler(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID,true);
//			break;
//		case C_MO_EDITION:
//			enableActionAndHandler(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID,true);
//			break;
//		case C_MO_CONSULTATION:
//			enableActionAndHandler(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID,false);
//			break;
//		default:
//			break;
//		}
//	}	

	public SWTPaConditionnementController getThis() {
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
					.getString("ImageArticle.Message.Enregistrer"))) {

				try {
					actEnregistrer();
				} catch (Exception e) {
					vue.getLaMessage().setText(e.getMessage());
					logger.error("", e);
				}
			} else {
				fireAnnuleTout(new AnnuleToutEvent(this,true));
			}
			break;
		case C_MO_CONSULTATION:
			break;
		default:
			break;
		}
		if (retour) {
			if(getModeEcran().dataSetEnModif())
				try {
					dao.annuler(taUnite);
				} catch (Exception e) {
					throw new ExceptLgr();
				}
				if (ecranAppelant instanceof SWTPaAideControllerSWT) {
					setListeEntity(getModelConditionnementArticle().remplirListe());
					dao.initValeurIdTable(taUnite);
					fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
							dao.getChampIdEntite(), dao.getValeurIdTable(),
							selectedUnite.getValue())));

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
//					if(getFocusAvantAideSWT().equals(vue.getTfCodeType())){
//						TaTConditionnement u = null;
//						TaTConditionnementDAO t = new TaTConditionnementDAO(getEm());
//						u = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
//						t = null;
//						taUnite.setTaTConditionnement(u);
//					}
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
		} else if (evt.getRetour() != null){
			if (getFocusAvantAideSWT() instanceof Table) {
				if (getFocusAvantAideSWT() == vue.getGrille()) {
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
			setSwtOldConditionnementArticle();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer=getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
				//setMasterEntity(masterDAO.findById(masterEntity.getIdArticle()));
				swtUnite = new TaUniteDTO();
//				if (masterEntity.getTaConditionnementArticle()==null){
//					swtUnite.setDefaut(true);
//				} else
//					swtUnite.setDefaut(false);
//				dao.getModeObjet().setMode(EnumModeObjet.C_MO_INSERTION);
				taUnite = new TaUnite();
				taUnite.setTaArticle(masterEntity);
				List l = IHMmodel();
				l.add(swtUnite);
				writableList = new WritableList(realm, l, classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(swtUnite));
				
				getModeEcran().setMode(EnumModeObjet.C_MO_INSERTION);//ejb
				initEtatBouton();

				try {
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
					fireDeclencheCommandeController(e);
				} catch (Exception e) {
					logger.error("",e);
				}
			}
		} finally {
			initEtatComposant();
			VerrouInterface.setVerrouille(false);
		}
	}

	@Override
	protected void actModifier() throws Exception {
		try {
			boolean continuer=true;
			setSwtOldConditionnementArticle();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer=getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
//				setMasterEntity(masterDAO.findById(masterEntity.getIdArticle()));
				for (TaUnite p : masterEntity.getTaConditionnementsArticle()) {
					if(p.getIdUnite()==((TaUniteDTO) selectedUnite.getValue()).getId()) {
						taUnite = p;
						//					if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
					}
				}			
				masterDAO.verifAutoriseModification(masterEntity);
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				
				getModeEcran().setMode(EnumModeObjet.C_MO_INSERTION);//ejb
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
					continuer=getMasterModeEcran().dataSetEnModif();
				}

			if(continuer){
//				setMasterEntity(masterDAO.findById(masterEntity.getIdArticle()));
				if (MessageDialog.openConfirm(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("ImageArticle.Message.Supprimer"))) {				
//					dao.getModeObjet().setMode(EnumModeObjet.C_MO_SUPPRESSION);
					try {
						DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
						fireDeclencheCommandeController(e);
					} catch (Exception e) {
						logger.error("",e);
					}
					for (TaUnite p : masterEntity.getTaConditionnementsArticle()) {
						if(p.getIdUnite()==((TaUniteDTO) selectedUnite.getValue()).getId()) {
							taUnite = p;
						}
					}
//					dao.begin(transaction);
					dao.supprimer(taUnite);
					taUnite.setTaArticle(null);
					masterEntity.removeContiditionnementArticle(taUnite);
//					dao.commit(transaction);
					Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
					modelUnite.removeEntity(taUnite);			
//					taUnite=masterEntity.getTaConditionnementArticle();
					if(suivant!=null)tableViewer.setSelection(new StructuredSelection(suivant),true);
					else tableViewer.selectionGrille(0);
					try {
						DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_ENREGISTRER_ID);
						fireDeclencheCommandeController(e);
					} catch (Exception e) {
						logger.error("",e);
					}	
					modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
					actRefresh();		
					initEtatBouton();

				}
			}
		} catch (Exception e1) {
			vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		} finally {
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
						.getString("ImageArticle.Message.Annuler")))) {
					remetTousLesChampsApresAnnulationSWT(dbc);
					actRefresh();
					getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);
					hideDecoratedFields();
				}
				initEtatBouton();
				if(!annuleToutEnCours) {
					fireAnnuleTout(new AnnuleToutEvent(this));
				}
				break;
			case C_MO_EDITION:
				if (silencieu || (!silencieu && MessageDialog.openQuestion(vue.getShell(), MessagesEcran
						.getString("Message.Attention"), MessagesEcran
						.getString("ImageArticle.Message.Annuler")))) {
					int rang =((WritableList)tableViewer.getInput()).indexOf(selectedUnite.getValue());
					List<TaUniteDTO> l = IHMmodel();
					if(rang!=-1)
						l.set(rang, swtOldUnite);
					remetTousLesChampsApresAnnulationSWT(dbc);
					writableList = new WritableList(realm, l, classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldUnite), true);

					ctrlTousLesChampsAvantEnregistrementSWT();

					getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);
					hideDecoratedFields();
					if(!annuleToutEnCours) {
						fireAnnuleTout(new AnnuleToutEvent(this));
					}
				}
				initEtatBouton();

				break;
			case C_MO_CONSULTATION:
				//actionFermer.run();
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
//			if(getFocusCourantSWT().equals(vue.getTfCodeType()))
//				result = true;
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
				paramAfficheAideRecherche.setMessage(message);
				// Creation de l'ecran d'aide principal
				Shell s = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),LgrShellUtil.styleLgr);
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

				switch ((getThis().getModeEcran().getMode())) {
				case C_MO_CONSULTATION:
					if(getFocusCourantSWT().equals(vue.getGrille())){
						PaConditionnementArticleSWT paConditionnementArticleSWT = new PaConditionnementArticleSWT(s2,SWT.NULL);
						SWTPaConditionnementController swtPaConditionnementController = new SWTPaConditionnementController(paConditionnementArticleSWT);

						editorCreationId = EditorConditionnementArticle.ID;
						editorInputCreation = new EditorInputConditionnementArticle();

						
						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);
						paramAfficheAideRecherche.setAfficheDetail(false);
						
						
						ParamAffichePrix paramAffichePrix = new ParamAffichePrix();
						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
						paramAffichePrix.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAffichePrix.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaConditionnementController;
						parametreEcranCreation = paramAffichePrix;

						paramAfficheAideRecherche.setTypeEntite(TaUnite.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_LIBELLE_CONDITIONNEMENT_ARTICLE);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfLibelle().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(swtPaConditionnementController.getModelConditionnementArticle());
						paramAfficheAideRecherche.setTypeObjet(swtPaConditionnementController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_CONDITIONNEMENT_ARTICLE);
					}
					break;
				case C_MO_EDITION:
				case C_MO_INSERTION:
//					if(getFocusCourantSWT().equals(vue.getTfCodeType())){
//						PaTConditionnementSWT paTConditionnementSWT = new PaTConditionnementSWT(s2,SWT.NULL);
//						SWTPaTypeConditionnementController swtPaTypeConditionnementController = new SWTPaTypeConditionnementController(paTConditionnementSWT);
//
//						editorCreationId = EditorTypeConditionnement.ID;
//						editorInputCreation = new EditorInputTypeConditionnement();
//
//						ParamAfficheTypeConditionnement paramAfficheTypeConditionnement = new ParamAfficheTypeConditionnement();
//						paramAfficheAideRecherche.setJPQLQuery(new TaTConditionnementDAO(getEm()).getJPQLQuery());
//						paramAfficheTypeConditionnement.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAfficheTypeConditionnement.setEcranAppelant(paAideController);
//						controllerEcranCreation = swtPaTypeConditionnementController;
//						parametreEcranCreation = paramAfficheTypeConditionnement;
//
//						paramAfficheAideRecherche.setTypeEntite(TaUnite.class);
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_T_CONDITIONNEMENT);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCodeType().getText());
//						paramAfficheAideRecherche.setControllerAppelant(SWTPaConditionnementController.this);
//						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTTConditionnement,TaTConditionnementDAO,TaTConditionnement>(SWTTConditionnement.class,dao.getEntityManager()));
//						paramAfficheAideRecherche.setTypeObjet(swtPaTypeConditionnementController.getClassModel());
//
//						paramAfficheAideRecherche.setChampsIdentifiant(swtPaTypeConditionnementController.getDao().getChampIdTable());
//					}
					break;
				default:
					break;
				}

//				if (paramAfficheAideRecherche.getJPQLQuery() != null) {

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
		String validationContext = "CONDITIONNEMENT_ARTICLE";
		try {

			IStatus resultat = new Status(IStatus.OK,ArticlesPlugin.PLUGIN_ID,"validateUIField champ : "+nomChamp!=null?nomChamp:"null"+" valeur : "+value!=null?value.toString():"valeur nulle"+" validation OK");
			
			int VALIDATION_CLIENT = 1;
			int VALIDATION_SERVEUR = 2;
			int VALIDATION_CLIENT_ET_SERVEUR = 3;
			
			//int TYPE_VALIDATION = VALIDATION_CLIENT;
			//int TYPE_VALIDATION = VALIDATION_SERVEUR;
			int TYPE_VALIDATION = VALIDATION_CLIENT_ET_SERVEUR;
			
			
			if(nomChamp.equals(Const.C_CODE_T_CONDITIONNEMENT)) {
				try {
					TaTConditionnementDTO u = new TaTConditionnementDTO();
					ITaTConditionnementServiceRemote dao = new EJBLookup<ITaTConditionnementServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_T_CONDITIONNMENT_SERVICE, ITaTConditionnementServiceRemote.class.getName());
					PropertyUtils.setSimpleProperty(u, nomChamp, value);
					dao.validateDTOProperty(u,nomChamp,ITaTConditionnementServiceRemote.validationContext);
				} catch(Exception e) {
					resultat = new Status(IStatus.ERROR,ArticlesPlugin.PLUGIN_ID, e.getMessage(), e);
				}
				if(resultat!=null && resultat.getSeverity()!=IStatus.ERROR ){

				}
			} else if(nomChamp.equals(Const.C_CODE_ARTICLE)) {
				resultat = ValidationStatus.ok();
			} else {
				TaUnite u = new TaUnite();
				u.setTaArticle(masterEntity);
			//return s;
			return resultat;
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		return null;
	}

	
//	public IStatus validateUIField(String nomChamp,Object value) {
//		String validationContext = "CONDITIONNEMENT_ARTICLE";
//		try {
//			IStatus s = null;
//
//			if(nomChamp.equals(Const.C_CODE_T_CONDITIONNEMENT)) {
//				TaTConditionnementDAO dao = new TaTConditionnementDAO(getEm());
//
//				dao.setModeObjet(getDao().getModeObjet());
//				TaTConditionnement f = new TaTConditionnement();
//				PropertyUtils.setSimpleProperty(f, nomChamp, value);
//				s = dao.validate(f,nomChamp,validationContext);
//
//				if(s.getSeverity()!=IStatus.ERROR ){
////					f = dao.findByCode((String)value);
////					taUnite.setTaTConditionnement(f);
//				}
//				dao = null;
//			} else if(nomChamp.equals(Const.C_CODE_ARTICLE)) {
//				s = ValidationStatus.ok();
//			} else {
//				TaUnite u = new TaUnite();
//				u.setTaArticle(masterEntity);
//				
////				if(nomChamp.equals(Const.C_DEFAUT_IMAGE_ARTICLE) ) {
////					//if((Boolean)value==true) value=new Integer(1); else value=new Integer(0);
////					value = ((Boolean)value) ? new Integer(1) : new Integer(0);
////				}
//
//				
//				PropertyUtils.setSimpleProperty(u, nomChamp, value);
//
//				int change=0;
////				if(nomChamp.equals(Const.C_PRIX_PRIX)) {
////					u.setPrixttcPrix(((TaUniteDTO) selectedImageArticle.getValue()).getPrixttcPrix());
////					change =u.getPrixPrix().compareTo(((TaUniteDTO) selectedImageArticle.getValue()).getPrixPrix());
////				}
////				if(nomChamp.equals(Const.C_PRIXTTC_PRIX)) {
////					u.setPrixPrix(((TaUniteDTO) selectedImageArticle.getValue()).getPrixPrix());
////					change =u.getPrixttcPrix().compareTo(((TaUniteDTO) selectedImageArticle.getValue()).getPrixttcPrix());
////				}
////
////				if(((TaUniteDTO) selectedImageArticle.getValue()).getIdImageArticle()!=null) {
////					u.setIdPrix(((TaUniteDTO) selectedImageArticle.getValue()).getIdImageArticle());
////				}
//
//
//				s = dao.validate(u,nomChamp,validationContext);
//				if(s.getSeverity()!=IStatus.ERROR && change!=0) {
////					((TaUniteDTO) selectedImageArticle.getValue()).setPrixPrix(u.getPrixPrix());				
////					((TaUniteDTO) selectedImageArticle.getValue()).setPrixttcPrix(u.getPrixttcPrix());
//				}				
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
//	}

	@Override
	protected void actEnregistrer() throws Exception {
//		EntityTransaction transaction = dao.getEntityManager().getTransaction();
		try {
			//TODO ejb, controle à remettre
//			ctrlUnChampsSWT(getFocusCourantSWT());
			ctrlTousLesChampsAvantEnregistrementSWT();
			
//			dao.begin(transaction);
			//TaUnite u = null;
			TaUniteMapper mapper = new TaUniteMapper();
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
//				LgrDozerMapper<TaUniteDTO,TaUnite> mapper = new LgrDozerMapper<TaUniteDTO,TaUnite>();
//				mapper.map((TaUniteDTO) selectedUnite.getValue(),taUnite);
				mapper.mapDtoToEntity((TaUniteDTO) selectedUnite.getValue(), taUnite);

			} else if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)) {
//				LgrDozerMapper<TaUniteDTO,TaUnite> mapper = new LgrDozerMapper<TaUniteDTO,TaUnite>();
//				mapper.map((TaUniteDTO) selectedUnite.getValue(),taUnite);
				mapper.mapDtoToEntity((TaUniteDTO) selectedUnite.getValue(), taUnite);
				taUnite.setTaArticle(masterEntity);
				masterEntity.addContiditionnementArticle(taUnite);
//				if(((TaUniteDTO) selectedUnite.getValue()).getDefaut())
//					masterEntity.setTaUnite(taUnite);				
			}

			try {
				if(!enregistreToutEnCours) {
					sortieChamps();
					fireEnregistreTout(new AnnuleToutEvent(this,true));
					DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_ENREGISTRER_ID);
					fireDeclencheCommandeController(e);
				}
			} catch (Exception e) {
				logger.error("",e);
			}		
//			taUnite=masterEntity.getTaConditionnementArticle();
//			dao.commit(transaction);
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
			vue.getTfLongueur().setEditable(!isUtilise());
			changeCouleur(vue);
		} catch (Exception e) {
			vue.getLaMessage().setText(e.getMessage());
		}

	}
	public boolean isUtilise(){
		return (((TaUniteDTO)selectedUnite.getValue()).getId()!=null && 
				!dao.recordModifiable(dao.getNomTable(),
						((TaUniteDTO)selectedUnite.getValue()).getId()))||
						!masterDAO.autoriseModification(masterEntity);		
	}
	public TaUniteDTO getSwtOldPrix() {
		return swtOldUnite;
	}

	public void setSwtOldUnite(TaUniteDTO swtOldImageArticle) {
		this.swtOldUnite = swtOldImageArticle;
	}

	public void setSwtOldConditionnementArticle() {
		if (selectedUnite.getValue() != null)
			this.swtOldUnite = TaUniteDTO.copy((TaUniteDTO) selectedUnite.getValue());
		else {
			if(tableViewer.selectionGrille(0)){
				this.swtOldUnite = TaUniteDTO.copy((TaUniteDTO) selectedUnite.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(TaUniteDTO) selectedUnite.getValue()), true);
			}}
	}

	public void setVue(PaConditionnementArticleSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();

		mapComposantDecoratedField.put(vue.getTfCode(), vue.getFieldCode());
//		mapComposantDecoratedField.put(vue.getTfCodeType(), vue.getFieldCodeType());
		mapComposantDecoratedField.put(vue.getTfHauteur(), vue.getFieldHauteur());
		mapComposantDecoratedField.put(vue.getTfLargeur(), vue.getFieldLargeur());
		mapComposantDecoratedField.put(vue.getTfLibelle(), vue.getFieldLibelle());
		mapComposantDecoratedField.put(vue.getTfLongueur(), vue.getFieldLongueur());
		mapComposantDecoratedField.put(vue.getTfNbUnite(), vue.getFieldNbUnite());
		mapComposantDecoratedField.put(vue.getTfPoids(), vue.getFieldPoids());
//		mapComposantDecoratedField.put(vue.getTfReduction(), vue.getFieldReduction());
	}

	public Class getClassModel() {
		return classModel;
	}



	@Override
	protected void sortieChamps() {
//		if(selectedImageArticle!=null && selectedImageArticle.getValue()!=null){
//			if(vue.getTfPRIX_PRIX().equals(getFocusCourantSWT())){
//				if(((TaUniteDTO)selectedImageArticle.getValue()).getTauxTva()!=null)
//					((TaUniteDTO)selectedImageArticle.getValue()).setPrixttcPrix(((TaUniteDTO)selectedImageArticle.getValue()).getPrixPrix().multiply(new BigDecimal(1).
//							add(((TaUniteDTO)selectedImageArticle.getValue()).getTauxTva().divide(new BigDecimal(100)))));
//				vue.getTfPRIXTTC_PRIX().setText(LibConversion.bigDecimalToString(((TaUniteDTO)selectedImageArticle.getValue()).getPrixttcPrix()));
//			} else if(vue.getTfPRIXTTC_PRIX().equals(getFocusCourantSWT())){
//				if(((TaUniteDTO)selectedImageArticle.getValue()).getTauxTva()!=null)
//					((TaUniteDTO)selectedImageArticle.getValue()).setPrixPrix(((TaUniteDTO)selectedImageArticle.getValue()).getPrixttcPrix().divide(new BigDecimal(1).
//							add(((TaUniteDTO)selectedImageArticle.getValue()).getTauxTva().divide(new BigDecimal(100))),MathContext.DECIMAL32));
//				vue.getTfPRIX_PRIX().setText(LibConversion.bigDecimalToString(((TaUniteDTO)selectedImageArticle.getValue()).getPrixPrix()));
//			}
//		}		
	}

	@Override
	protected void actRefresh() throws Exception {
		//repositionnement sur la valeur courante
		int idActuel = 0;
		if (taUnite!=null) { //enregistrement en cours de modification/insertion
			idActuel = taUnite.getIdUnite();
		} else if(selectedUnite!=null && selectedUnite.getValue()!=null) {
			idActuel = ((TaUniteDTO) selectedUnite.getValue()).getId();
		}

		//rafraichissement des valeurs dans la grille
		writableList = new WritableList(realm, IHMmodel(), classModel);
		tableViewer.setInput(writableList);

		if(idActuel!=0) {
			tableViewer.setSelection(new StructuredSelection(recherche(Const.C_ID_DTO_GENERAL, idActuel)));
		}else
			tableViewer.selectionGrille(0);				
	}

	public ModelGeneralObjetEJB<TaUnite,TaUniteDTO> getModelConditionnementArticle() {
		return modelUnite;
	}

	public TaArticle getMasterEntity() {
		return masterEntity;
	}

	public void setMasterEntity(TaArticle taArticle) {
		this.masterEntity = taArticle;
	}

	public ITaArticleServiceRemote getMasterDAO() {
		return masterDAO;
	}

	public void setMasterDAO(ITaArticleServiceRemote masterDAO) {
		this.masterDAO = masterDAO;
	}

	public TaUnite getTaUnite() {
		return taUnite;
	}

	public ITaUniteServiceRemote getDao() {
		return dao;
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

	public boolean changementPageValide(){
		if (getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0
				|| getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0) {
			//mise a jour de l'entite principale
			if(taUnite!=null && selectedUnite!=null && ((TaUniteDTO) selectedUnite.getValue())!=null) {
				LgrDozerMapper<TaUniteDTO,TaUnite> mapper = new LgrDozerMapper<TaUniteDTO,TaUnite>();
				mapper.map((TaUniteDTO) selectedUnite.getValue(),taUnite);
			}
		}
		//		dao.getModeObjet().setMode(EnumModeObjet.C_MO_CONSULTATION);
		//		initEtatBouton();
		return true;
	};

}
