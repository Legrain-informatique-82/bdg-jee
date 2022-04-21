package fr.legrain.articles.ecran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.PlatformUI;

import fr.legrain.article.dto.TaImageArticleDTO;
import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaImageArticle;
import fr.legrain.articles.ArticlesPlugin;
import fr.legrain.articles.divers.FichierDonneesArticle;
import fr.legrain.articles.preferences.PreferenceConstants;
import fr.legrain.articles.preferences.UtilPreferenceImage;
import fr.legrain.bdg.article.service.remote.ITaArticleServiceRemote;
import fr.legrain.bdg.article.service.remote.ITaImageArticleServiceRemote;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.model.mapping.mapper.TaImageArticleMapper;
import fr.legrain.generationLabelEtiquette.ecrans.CompositeEtiquetteArticleController;
import fr.legrain.gestCom.Appli.Const;
//import fr.legrain.gestCom.Appli.LgrDozerMapper;
import fr.legrain.gestCom.Appli.ModelGeneralObjetEJB;
import fr.legrain.gestCom.librairiesEcran.swt.DeclencheCommandeControllerEvent;
import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.IDetailController;
import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideControllerSWT;
import fr.legrain.gestCom.librairiesEcran.swt.UtilImage;
import fr.legrain.gestCom.librairiesEcran.workbench.AnnuleToutEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDePageEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.ISelectionLgr;
import fr.legrain.lib.data.InfosVerifSaisie;
import fr.legrain.lib.data.VerrouInterface;
import fr.legrain.lib.ejb.EJBLookup;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.lib.gui.ResultAffiche;
import fr.legrain.lib.gui.RetourEcranEvent;
import fr.legrain.lib.gui.RetourEcranListener;
import fr.legrain.lib.gui.aide.ResultAide;
import fr.legrain.lib.gui.grille.LgrTableViewer;
import fr.legrain.libMessageLGR.LgrMess;
import fr.legrain.publipostage.gui.CompositePublipostageArticleController;
import fr.legrain.publipostage.gui.preferences.PreferenceInitializer;
import fr.legrain.tiers.clientutility.JNDILookupClass;


public class SWTPaActionArticleController extends EJBBaseControllerSWTStandard
implements RetourEcranListener,IDetailController, ISelectionLgr<TaArticle> {

	static Logger logger = Logger.getLogger(SWTPaActionArticleController.class.getName());
	private PaActionArticleSWT vue = null;
	private ITaImageArticleServiceRemote dao = null;
	private String idArticle = null;

	private Object ecranAppelant = null;
	private TaImageArticleDTO swtImageArticle;
	private TaImageArticleDTO swtOldImageArticle;
	private Realm realm;
	private DataBindingContext dbc;

	private Class classModel = TaImageArticleDTO.class;
	private ModelGeneralObjetEJB<TaImageArticle,TaImageArticleDTO> modelImageArticle = null;
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedImageArticle;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();

	private TaArticle masterEntity = null;
	private ITaArticleServiceRemote masterDAO = null;

	private TaImageArticle taImageArticle = null;

	private MapChangeListener changeListener = new MapChangeListener();
	
	public static final String C_COMMAND_CHOIX_IMAGE_ARTICLE_ID = "fr.legrain.gestionCommerciale.article.image";
	private HandlerChoixImage handlerChoixImage = new HandlerChoixImage();
	
	private Canvas imagePreview = null;
	private UtilPreferenceImage utilPreferenceImage = new UtilPreferenceImage();
	
	private CompositeEtiquetteArticleController compositeEtiquetteArticleController = null;
	private CompositePublipostageArticleController compositePublipostageArticleController = null;


	public SWTPaActionArticleController(PaActionArticleSWT vue) {
		this(vue,null);
	}

	public SWTPaActionArticleController(PaActionArticleSWT composite,EntityManager em) {
//		if(em!=null) {
//			setEm(em);
//		}
		try {
			dao = new EJBLookup<ITaImageArticleServiceRemote>().doLookup(JNDILookupClass.getInitialContext(),EJBLookup.EJB_NAME_TA_IMAGE_ARTICLE_SERVICE, ITaImageArticleServiceRemote.class.getName());
		} catch (NamingException e) {
			logger.error("",e);
		}
		modelImageArticle = new ModelGeneralObjetEJB<TaImageArticle,TaImageArticleDTO>(dao);
		setVue(composite);
		// a faire avant l'initialisation du Binding
//		composite.getGrille().addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				setSwtOldPrix();
//			}
//		});
		composite.getShell().addShellListener(this);

		// Branchement action annuler : empeche la fermeture automatique de la
		// fenetre sur ESC
		composite.getShell().addTraverseListener(new Traverse());

		initController();
	}

	private void initController() {
		try {
//			setGrille(vue.getGrille());
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
			
//			vue.getTfNomImageArticle().setEnabled(false);

			branchementBouton();
			
			PreferenceInitializer.initDefautProperties(false);
			compositeEtiquetteArticleController = new CompositeEtiquetteArticleController(
					vue.getCompositeEtiquette(), 
					null,this,
					ArticlesPlugin.getDefault().getPreferenceStore(),
					PreferenceConstants.P_DERNIER_MODELE_UTILISE_ETIQUETTE_ARTICLE, 
					new FichierDonneesArticle()
			);
			
//			String dernierModeleWord = fr.legrain.publipostage.gui.Activator.getDefault().getPreferenceStore().getString(
//					PreferenceConstants.P_DERNIER_MODELE_UTILISE_WORD_ARTICLE);
//			String dernierModeleOOo = fr.legrain.publipostage.gui.Activator.getDefault().getPreferenceStore().getString(
//							PreferenceConstants.P_DERNIER_MODELE_UTILISE_OPENOFFICE_ARTICLE);
//			
//			if(dernierModeleWord.equals("")) {
//				dernierModeleWord = fr.legrain.publipostage.gui.Activator.getDefault().getPreferenceStore().getDefaultString(
//						PreferenceConstants.P_DERNIER_MODELE_UTILISE_WORD_ARTICLE);
//			}
//			if(dernierModeleOOo.equals("")) {
//				dernierModeleOOo = fr.legrain.publipostage.gui.Activator.getDefault().getPreferenceStore().getDefaultString(
//						PreferenceConstants.P_DERNIER_MODELE_UTILISE_OPENOFFICE_ARTICLE);
//			}
							
			compositePublipostageArticleController = new CompositePublipostageArticleController(
					vue.getCompositePublipostage(), 
					null,this,
					ArticlesPlugin.getDefault().getPreferenceStore(),
					PreferenceConstants.P_DERNIER_MODELE_UTILISE_WORD_ARTICLE,
					PreferenceConstants.P_DERNIER_MODELE_UTILISE_OPENOFFICE_ARTICLE, 
					new FichierDonneesArticle(),
					fr.legrain.publipostage.gui.Activator.getDefault().getPreferenceStore().getString(
							fr.legrain.publipostage.gui.preferences.PreferenceConstants.P_DERNIER_MODELE_UTILISE_WORD_ARTICLE),
					fr.legrain.publipostage.gui.Activator.getDefault().getPreferenceStore().getString(
							fr.legrain.publipostage.gui.preferences.PreferenceConstants.P_DERNIER_MODELE_UTILISE_OPENOFFICE_ARTICLE)
					//dernierModeleWord,dernierModeleOOo
					//"defaut_word","defaut_oo"
									);

			Menu popupMenuFormulaire = new Menu(vue.getShell(), SWT.POP_UP);
			Menu popupMenuGrille = new Menu(vue.getShell(), SWT.POP_UP);
			Menu[] tabPopups = new Menu[] { popupMenuFormulaire, popupMenuGrille };
			this.initPopupAndButtons(mapActions, tabPopups);
//			vue.getPaCorpsFormulaire().setMenu(popupMenuFormulaire);
//			vue.getPaGrille().setMenu(popupMenuGrille);

			initEtatBouton();
		} catch (Exception e) {
//			vue.getLaMessage().setText(e.getMessage());
			logger.error("Erreur : SWTPaPrixController", e);
		}
	}

	/**
	 * Creation des objet pour l'interface, a partir de l'entite principale.<br>
	 * Ici : creation d'une liste de prix pour l'IHM, a partir de la liste des prix contenue dans l'entite article.
	 * @return
	 */
	public List<TaImageArticleDTO> IHMmodel() {
		LinkedList<TaImageArticle> ldao = new LinkedList<TaImageArticle>();
		LinkedList<TaImageArticleDTO> lswt = new LinkedList<TaImageArticleDTO>();
		//masterEntity.getTaImageArticlees().clear();
		//if(masterEntity!=null)	masterDAO.refresh(masterEntity);
		if(masterEntity!=null && !masterEntity.getTaImageArticles().isEmpty()) {

			for (TaImageArticle img : masterEntity.getTaImageArticles()) {
				if(img.getImageOrigine()==null) {
					ldao.add(img);
				}
			}
			//ldao.addAll(masterEntity.getTaImageArticles());

			LgrDozerMapper<TaImageArticle,TaImageArticleDTO> mapper = new LgrDozerMapper<TaImageArticle,TaImageArticleDTO>();
			for (TaImageArticle o : ldao) {
				TaImageArticleDTO t = null;
				t = (TaImageArticleDTO) mapper.map(o, TaImageArticleDTO.class);
				lswt.add(t);
			}

		}
		return lswt;
	}

	public void bind(PaActionArticleSWT PaActionArticleSWT) {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());

//			tableViewer = new LgrTableViewer(PaActionArticleSWT.getGrille());
//			tableViewer.createTableCol(classModel,PaActionArticleSWT.getGrille(), nomClassController,
//					Const.C_FICHIER_LISTE_CHAMP_GRILLE,-1);
//			listeChamp = tableViewer.setListeChampGrille(nomClassController,
//					Const.C_FICHIER_LISTE_CHAMP_GRILLE);
//			selectedImageArticle = ViewersObservables.observeSingleSelection(tableViewer);
//
//			LgrViewerSupport.bind(tableViewer, 
//					new WritableList(IHMmodel(), classModel),
//					BeanProperties.values(listeChamp)
//			);
//
//			dbc = new DataBindingContext(realm);
//
//			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
//			tableViewer.selectionGrille(0);
//
//			setTableViewerStandard(tableViewer);
//			setDbcStandard(dbc);
//
//			bindingFormMaitreDetail(dbc, realm, selectedImageArticle,classModel);
//			tableViewer.setChecked(tableViewer.getTable().getColumn(1),true);
//			changementDeSelection();
//			selectedImageArticle.addChangeListener(new IChangeListener() {
//
//				public void handleChange(ChangeEvent event) {
//					changementDeSelection();
//				}
//
//			});

		} catch (Exception e) {
//			vue.getLaMessage().setText(e.getMessage());
			logger.error("", e);
		}
	}

	private void changementDeSelection() {
		if(selectedImageArticle!=null && selectedImageArticle.getValue()!=null) {
			if(((TaImageArticleDTO) selectedImageArticle.getValue()).getId()!=null) {
				try {
					taImageArticle = dao.findById(((TaImageArticleDTO) selectedImageArticle.getValue()).getId());
				} catch (FinderException e) {
					logger.error("",e);
				}
//				changementImage(utilPreferenceImage.cheminCompletImageArticle(taImageArticle));
			}
			//null a tester ailleurs, car peut etre null en cas d'insertion
			fireChangementDeSelection(new ChangementDeSelectionEvent(SWTPaActionArticleController.this));
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
//			else
//				param.setFocusDefautSWT(vue.getGrille());
//			if(param.getTitreFormulaire()!=null){
//				vue.getLaTitreFormulaire().setText(param.getTitreFormulaire());
//			} else {
//				vue.getLaTitreFormulaire().setText(ParamAffichePrix.C_TITRE_FORMULAIRE);
//			}
//
//			if(param.getTitreGrille()!=null){
//				vue.getLaTitreGrille().setText(param.getTitreGrille());
//			} else {
//				vue.getLaTitreGrille().setText(ParamAffichePrix.C_TITRE_GRILLE);
//			}
//
//			if(param.getSousTitre()!=null){
//				vue.getLaTitreFenetre().setText(param.getSousTitre());
//			} else {
//				vue.getLaTitreFenetre().setText(ParamAffichePrix.C_SOUS_TITRE);
//			}

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}

			if(tableViewer==null) {
				//databinding pas encore realise
				bind(vue);
//				tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			} else {
				try {
					taImageArticle=null;
					selectedImageArticle.setValue(null);
					actRefresh();
				} catch (Exception e) {
					logger.error("",e);
				}
			}
			// permet de se positionner sur le 1er enregistrement et de remplir
			// le formulaire
			//			tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
			VerrouInterface.setVerrouille(false);
//			setSwtOldPrix();

			if (param.getModeEcran() != null
					&& param.getModeEcran().compareTo(
							EnumModeObjet.C_MO_INSERTION) == 0) {
				try {
					actInserer();
				} catch (Exception e) {
//					vue.getLaMessage().setText(e.getMessage());
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

//		mapInfosVerifSaisie.put(vue.getTfPRIX_PRIX(), new InfosVerifSaisie(new TaImageArticle(),Const.C_PRIX_PRIX,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
//		mapInfosVerifSaisie.put(vue.getTfNomImageArticle(), new InfosVerifSaisie(new TaImageArticle(),Const.C_NOM_IMAGE_ARTICLE,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
//		
//		mapInfosVerifSaisie.put(vue.getTfCheminImageArticle(), new InfosVerifSaisie(new TaImageArticle(),Const.C_CHEMIN_IMAGE_ARTICLE,null));

		initVerifyListener(mapInfosVerifSaisie, dao);
	}
	
	protected void initComposantsVue() throws ExceptLgr {
	}

	protected void initMapComposantChamps() {
		if (mapComposantChamps == null)
			mapComposantChamps = new LinkedHashMap<Control, String>();

		if (listeComposantFocusable == null)
			listeComposantFocusable = new ArrayList<Control>();
//		listeComposantFocusable.add(vue.getGrille());
//
//		vue.getTfCheminImageArticle().setToolTipText(Const.C_CHEMIN_IMAGE_ARTICLE);
////		vue.getTfPRIX_PRIX().setToolTipText(Const.C_PRIX_PRIX);
//		vue.getTfNomImageArticle().setToolTipText(Const.C_NOM_IMAGE_ARTICLE);
//		vue.getCbDefautImageArticle().setToolTipText(Const.C_DEFAUT_IMAGE_ARTICLE);
//
//		mapComposantChamps.put(vue.getTfCheminImageArticle(), Const.C_CHEMIN_IMAGE_ARTICLE);
////		mapComposantChamps.put(vue.getTfPRIX_PRIX(), Const.C_PRIX_PRIX);
//		mapComposantChamps.put(vue.getTfNomImageArticle(), Const.C_NOM_IMAGE_ARTICLE);
//		mapComposantChamps.put(vue.getCbDefautImageArticle(), Const.C_DEFAUT_IMAGE_ARTICLE);

		for (Control c : mapComposantChamps.keySet()) {
			listeComposantFocusable.add(c);
		}
		
//		listeComposantFocusable.add(vue.getBtnBrowseImage());
//
//		listeComposantFocusable.add(vue.getBtnEnregistrer());
//		listeComposantFocusable.add(vue.getBtnInserer());
//		listeComposantFocusable.add(vue.getBtnModifier());
//		listeComposantFocusable.add(vue.getBtnSupprimer());
//		listeComposantFocusable.add(vue.getBtnFermer());
//		listeComposantFocusable.add(vue.getBtnAnnuler());
//		listeComposantFocusable.add(vue.getBtnImprimer());

		if (mapInitFocus == null)
			mapInitFocus = new LinkedHashMap<EnumModeObjet, Control>();
		mapInitFocus.put(EnumModeObjet.C_MO_INSERTION, vue.getCompositePublipostage());
		mapInitFocus.put(EnumModeObjet.C_MO_EDITION, vue.getCompositePublipostage());
		mapInitFocus.put(EnumModeObjet.C_MO_CONSULTATION, vue.getCompositePublipostage());

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
		
		mapCommand.put(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID, handlerChoixImage);

		mapCommand.put(C_COMMAND_GLOBAL_PRECEDENT_ID, handlerPrecedent);
		mapCommand.put(C_COMMAND_GLOBAL_SUIVANT_ID, handlerSuivant);

		initFocusCommand(String.valueOf(this.hashCode()),listeComposantFocusable,mapCommand);

		if (mapActions == null)
			mapActions = new LinkedHashMap<Button, Object>();

//		mapActions.put(vue.getBtnAnnuler(), C_COMMAND_GLOBAL_ANNULER_ID);
//		mapActions.put(vue.getBtnEnregistrer(), C_COMMAND_GLOBAL_ENREGISTRER_ID);
//		mapActions.put(vue.getBtnInserer(), C_COMMAND_GLOBAL_INSERER_ID);
//		mapActions.put(vue.getBtnModifier(), C_COMMAND_GLOBAL_MODIFIER_ID);
//		mapActions.put(vue.getBtnSupprimer(), C_COMMAND_GLOBAL_SUPPRIMER_ID);
//		mapActions.put(vue.getBtnFermer(), C_COMMAND_GLOBAL_FERMER_ID);
//		mapActions.put(vue.getBtnImprimer(), C_COMMAND_GLOBAL_IMPRIMER_ID);
//		
//		mapActions.put(vue.getBtnBrowseImage(), C_COMMAND_CHOIX_IMAGE_ARTICLE_ID);

		Object[] tabActionsAutres = new Object[] { C_COMMAND_GLOBAL_AIDE_ID, C_COMMAND_GLOBAL_REFRESH_ID };
		mapActions.put(null, tabActionsAutres);
	}

	protected void initEtatBouton() {
		//initEtatBoutonCommand();
		initEtatBouton(IHMmodel());
		enableActionAndHandler(C_COMMAND_GLOBAL_IMPRIMER_ID,false);
		
		switch (getModeEcran().getMode()) {
		case C_MO_INSERTION:
			enableActionAndHandler(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID,true);
			break;
		case C_MO_EDITION:
			enableActionAndHandler(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID,true);
			break;
		case C_MO_CONSULTATION:
			enableActionAndHandler(C_COMMAND_CHOIX_IMAGE_ARTICLE_ID,false);
			break;
		default:
			break;
		}
	}	

	public SWTPaActionArticleController getThis() {
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
//					vue.getLaMessage().setText(e.getMessage());
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
					dao.annuler(taImageArticle);
				} catch (Exception e) {
					throw new ExceptLgr();
				}
				if (ecranAppelant instanceof SWTPaAideControllerSWT) {
					setListeEntity(getModelImageArticle().remplirListe());
					dao.initValeurIdTable(taImageArticle);
					fireRetourEcran(new RetourEcranEvent(this, new ResultAide(
							dao.getChampIdEntite(), dao.getValeurIdTable(),
							selectedImageArticle.getValue())));

					retour = true;
				}
		}
		return retour;
	}

	public void retourEcran(final RetourEcranEvent evt) {
//		if (evt.getRetour() != null
//				&& (evt.getSource() instanceof SWTPaAideControllerSWT)) {
//			if (getFocusAvantAideSWT() instanceof Text) {
//				try {
//					((Text) getFocusAvantAideSWT()).setText(((ResultAffiche) evt.getRetour()).getResult());	
////					if(getFocusAvantAideSWT().equals(vue.getTfCODE_UNITE())){
////						TaUnite u = null;
////						TaUniteDAO t = new TaUniteDAO(getEm());
////						u = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
////						t = null;
////						taImageArticle.setTaUnite(u);
////					}
//					ctrlUnChampsSWT(getFocusAvantAideSWT());
//				} catch (Exception e) {
//					logger.error("",e);
//					vue.getLaMessage().setText(e.getMessage());
//				}
//			}
//			if (getFocusAvantAideSWT() instanceof Table) {
//				if (getFocusAvantAideSWT() == vue.getGrille()) {
//					if(((ResultAffiche) evt.getRetour()).getSelection()!=null)
//						tableViewer.setSelection(((ResultAffiche) evt.getRetour()).getSelection(),true);
//				}
//			}			
//		} else if (evt.getRetour() != null){
//			if (getFocusAvantAideSWT() instanceof Table) {
//				if (getFocusAvantAideSWT() == vue.getGrille()) {
//				}
//			}
//		}
		super.retourEcran(evt);
	}

	@Override
	protected void actInserer() throws Exception {
		try {
			boolean continuer=true;
			VerrouInterface.setVerrouille(true);
			masterDAO.verifAutoriseModification(masterEntity);
			setSwtOldPrix();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer=getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
				//setMasterEntity(masterDAO.findById(masterEntity.getIdArticle()));
				swtImageArticle = new TaImageArticleDTO();
				if (masterEntity.getTaImageArticle()==null){
					swtImageArticle.setDefautImageArticle(true);
				} else
					swtImageArticle.setDefautImageArticle(false);
//				dao.getModeObjet().setMode(EnumModeObjet.C_MO_INSERTION);
				taImageArticle = new TaImageArticle();
				List l = IHMmodel();
				l.add(swtImageArticle);
				writableList = new WritableList(realm, l, classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(swtImageArticle));
				
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
			setSwtOldPrix();
			if(LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer=getMasterModeEcran().dataSetEnModif();
			}
			if(continuer){
//				setMasterEntity(masterDAO.findById(masterEntity.getIdArticle()));
				for (TaImageArticle p : masterEntity.getTaImageArticles()) {
					if(p.getIdImageArticle()==((TaImageArticleDTO) selectedImageArticle.getValue()).getId()) {
						taImageArticle = p;
						//					if(LgrMess.isDOSSIER_EN_RESEAU())actRefresh();
					}
				}		
				masterDAO.verifAutoriseModification(masterEntity);
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
//				dao.getModeObjet().setMode(EnumModeObjet.C_MO_EDITION);
				
				//Pour ne pas avoir de problème avec les chemins relatifs à l'enregistrement d'une modification
//				vue.getTfCheminImageArticle().setText(utilPreferenceImage.cheminCompletImageArticle(taImageArticle));
//				vue.getTfCheminImageArticle().setToolTipText(utilPreferenceImage.cheminCompletImageArticle(taImageArticle));
				
				modeEcran.setMode(EnumModeObjet.C_MO_EDITION);//ejb
				initEtatBouton();
			}
		} catch (Exception e1) {
//			vue.getLaMessage().setText(e1.getMessage());
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
					for (TaImageArticle p : masterEntity.getTaImageArticles()) {
						if(p.getIdImageArticle()==((TaImageArticleDTO) selectedImageArticle.getValue()).getId()) {
							taImageArticle = p;
						}
					}
					
					List<File> listeDeFichierImageASupprimer = new ArrayList<File>();
					for(TaImageArticle img : taImageArticle.getTaImagesGenere()) {
						listeDeFichierImageASupprimer.add(new File(utilPreferenceImage.cheminCompletImageArticle(img,true)));
					}
					listeDeFichierImageASupprimer.add(new File(utilPreferenceImage.cheminCompletImageArticle(taImageArticle)));
					
//					dao.begin(transaction);
					dao.supprimer(taImageArticle);
					taImageArticle.setTaArticle(null);
					masterEntity.removeImageArticle(taImageArticle);
					////////////////////////////////////////////////////////////////////////////////////
					for (File file : listeDeFichierImageASupprimer) {
						if(file.exists())
							file.delete();
					}
					////////////////////////////////////////////////////////////////////////////////////
//					dao.commit(transaction);
					Object suivant=tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex()+1);
					modelImageArticle.removeEntity(taImageArticle);			
					taImageArticle=masterEntity.getTaImageArticle();
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
//			vue.getLaMessage().setText(e1.getMessage());
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
					int rang =((WritableList)tableViewer.getInput()).indexOf(selectedImageArticle.getValue());
					List<TaImageArticleDTO> l = IHMmodel();
					if(rang!=-1)
						l.set(rang, swtOldImageArticle);
					remetTousLesChampsApresAnnulationSWT(dbc);
					writableList = new WritableList(realm, l, classModel);
					tableViewer.setInput(writableList);
					tableViewer.refresh();
					tableViewer.setSelection(new StructuredSelection(swtOldImageArticle), true);

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
//		switch ((getThis().dao.getModeObjet().getMode())) {
//		case C_MO_CONSULTATION:
//			if(getFocusCourantSWT().equals(vue.getGrille()))
//				result = true;
//			break;
//		case C_MO_EDITION:
//		case C_MO_INSERTION:
//			if(getFocusCourantSWT().equals(vue.getTfCheminImageArticle()))
//				result = true;
//			break;
//		default:
//			break;
//		}
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
//		if(aideDisponible()){
//			try {
//				VerrouInterface.setVerrouille(true);
//				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
//				ParamAfficheAideRechercheSWT paramAfficheAideRecherche = new ParamAfficheAideRechercheSWT();
//				paramAfficheAideRecherche.setMessage(message);
//				// Creation de l'ecran d'aide principal
//				Shell s = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),LgrShellUtil.styleLgr);
//				PaAideSWT paAide = new PaAideSWT(s, SWT.NONE);
//				SWTPaAideControllerSWT paAideController = new SWTPaAideControllerSWT(
//						paAide);
//				/***************************************************************/
//				LgrPartListener.getLgrPartListener().setLgrActivePart(null);
//				IEditorPart e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new EditorInputAide(), EditorAide.ID);
//				LgrPartListener.getLgrPartListener().setLgrActivePart(e);
//				LgrPartListener.getLgrPartListener().getLgrNavigation().add(e);
//				paAideController = new SWTPaAideControllerSWT(((EditorAide)e).getComposite());
//				((LgrEditorPart)e).setController(paAideController);
//				((LgrEditorPart)e).setPanel(((EditorAide)e).getComposite());
//				/***************************************************************/
//				JPABaseControllerSWTStandard controllerEcranCreation = null;
//				ParamAffiche parametreEcranCreation = null;
//				IEditorPart editorCreation = null;
//				String editorCreationId = null;
//				IEditorInput editorInputCreation = null;
//				Shell s2 = new Shell(s, LgrShellUtil.styleLgr);
//
//				switch ((getThis().dao.getModeObjet().getMode())) {
//				case C_MO_CONSULTATION:
//					if(getFocusCourantSWT().equals(vue.getGrille())){
//						PaActionArticleSWT PaActionArticleSWT = new PaActionArticleSWT(s2,SWT.NULL);
//						SWTPaImageArticleController swtPaPrixController = new SWTPaImageArticleController(PaActionArticleSWT);
//
//						editorCreationId = EditorPrix.ID;
//						editorInputCreation = new EditorInputPrix();
//
//						
//						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);
//						paramAfficheAideRecherche.setAfficheDetail(false);
//						
//						
//						ParamAffichePrix paramAffichePrix = new ParamAffichePrix();
//						paramAfficheAideRecherche.setJPQLQuery(dao.getJPQLQuery());
//						paramAffichePrix.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAffichePrix.setEcranAppelant(paAideController);
//						controllerEcranCreation = swtPaPrixController;
//						parametreEcranCreation = paramAffichePrix;
//
//						paramAfficheAideRecherche.setTypeEntite(TaImageArticle.class);
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_PRIX_PRIX);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfPRIX_PRIX().getText());
//						paramAfficheAideRecherche.setControllerAppelant(getThis());
//						paramAfficheAideRecherche.setModel(swtPaPrixController.getModelPrix());
//						paramAfficheAideRecherche.setTypeObjet(swtPaPrixController.getClassModel());
//						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_PRIX);
//					}
//					break;
//				case C_MO_EDITION:
//				case C_MO_INSERTION:
//					if(getFocusCourantSWT().equals(vue.getTfCheminImageArticle())){
//						PaUniteSWT paUniteSWT = new PaUniteSWT(s2,SWT.NULL);
//						SWTPaUniteController swtPaUniteController = new SWTPaUniteController(paUniteSWT);
//
//						editorCreationId = EditorUnite.ID;
//						editorInputCreation = new EditorInputUnite();
//
//						ParamAfficheUnite paramAfficheUnite = new ParamAfficheUnite();
//						paramAfficheAideRecherche.setJPQLQuery(new TaUniteDAO(getEm()).getJPQLQuery());
//						paramAfficheUnite.setModeEcran(EnumModeObjet.C_MO_INSERTION);
//						paramAfficheUnite.setEcranAppelant(paAideController);
//						controllerEcranCreation = swtPaUniteController;
//						parametreEcranCreation = paramAfficheUnite;
//
//						paramAfficheAideRecherche.setTypeEntite(TaUnite.class);
//						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_UNITE);
//						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCheminImageArticle().getText());
//						paramAfficheAideRecherche.setControllerAppelant(SWTPaImageArticleController.this);
//						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTUnite,TaUniteDAO,TaUnite>(SWTUnite.class,dao.getEntityManager()));
//						paramAfficheAideRecherche.setTypeObjet(swtPaUniteController.getClassModel());
//
//						paramAfficheAideRecherche.setChampsIdentifiant(swtPaUniteController.getDao().getChampIdTable());
//					}
//					break;
//				default:
//					break;
//				}
//
//				if (paramAfficheAideRecherche.getJPQLQuery() != null) {
//
//					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s,
//							SWT.NULL);
//					SWTPaAideRechercheControllerSWT paAideRechercheController1 = new SWTPaAideRechercheControllerSWT(
//							paAideRecherche1);
//
//					// Parametrage de la recherche
//					paramAfficheAideRecherche.setFocusSWT(((PaAideRechercheSWT) paAideRechercheController1.getVue()).getTfChoix());
//					paramAfficheAideRecherche.setRefCreationSWT(controllerEcranCreation);
//					paramAfficheAideRecherche.setEditorCreation(editorCreation);
//					paramAfficheAideRecherche.setEditorCreationId(editorCreationId);
//					paramAfficheAideRecherche.setEditorInputCreation(editorInputCreation);
//					paramAfficheAideRecherche.setParamEcranCreation(parametreEcranCreation);
//					paramAfficheAideRecherche.setShellCreation(s2);
//					paAideRechercheController1.configPanel(paramAfficheAideRecherche);
//
//					// Ajout d'une recherche
//					paAideController.addRecherche(paAideRechercheController1,
//							paramAfficheAideRecherche.getTitreRecherche());
//
//					// Parametrage de l'ecran d'aide principal
//					ParamAfficheAideSWT paramAfficheAideSWT = new ParamAfficheAideSWT();
//					ParamAfficheAide paramAfficheAide = new ParamAfficheAide();
//
//					// enregistrement pour le retour de l'ecran d'aide
//					paAideController.addRetourEcranListener(getThis());
//					// affichage de l'ecran d'aide principal (+ ses recherches)
//
//					dbc.getValidationStatusMap().removeMapChangeListener(changeListener);
//					/*****************************************************************/
//					paAideController.configPanel(paramAfficheAide);
//					/*****************************************************************/
//					dbc.getValidationStatusMap().addMapChangeListener(changeListener);
//
//				}
//
//			} finally {
//				VerrouInterface.setVerrouille(false);
//				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
//			}
//		}
	}

	public IStatus validateUI() throws Exception {
		if ((getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)
				|| (getModeEcran().getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
			ctrlTousLesChampsAvantEnregistrementSWT();
		}
		return null;
	}

	public IStatus validateUIField(String nomChamp,Object value) {
		String validationContext = "IMAGE_ARTICLE";
		try {
			IStatus s = null;
//passage ejb
//			if(nomChamp.equals(Const.C_CODE_UNITE)) {
////				TaUniteDAO dao = new TaUniteDAO(getEm());
////
////				dao.setModeObjet(getDao().getModeObjet());
////				TaUnite f = new TaUnite();
////				PropertyUtils.setSimpleProperty(f, nomChamp, value);
////				s = dao.validate(f,nomChamp,validationContext);
////
////				if(s.getSeverity()!=IStatus.ERROR) {
////					f = dao.findByCode((String)value);
////					taImageArticle.setTaUnite(f);
////				}
////				dao = null;
//			} else {
//				TaImageArticle u = new TaImageArticle();
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
////					u.setPrixttcPrix(((TaImageArticleDTO) selectedImageArticle.getValue()).getPrixttcPrix());
////					change =u.getPrixPrix().compareTo(((TaImageArticleDTO) selectedImageArticle.getValue()).getPrixPrix());
////				}
////				if(nomChamp.equals(Const.C_PRIXTTC_PRIX)) {
////					u.setPrixPrix(((TaImageArticleDTO) selectedImageArticle.getValue()).getPrixPrix());
////					change =u.getPrixttcPrix().compareTo(((TaImageArticleDTO) selectedImageArticle.getValue()).getPrixttcPrix());
////				}
////
////				if(((TaImageArticleDTO) selectedImageArticle.getValue()).getIdImageArticle()!=null) {
////					u.setIdPrix(((TaImageArticleDTO) selectedImageArticle.getValue()).getIdImageArticle());
////				}
//
//
//				s = dao.validate(u,nomChamp,validationContext);
//				if(s.getSeverity()!=IStatus.ERROR && change!=0) {
////					((TaImageArticleDTO) selectedImageArticle.getValue()).setPrixPrix(u.getPrixPrix());				
////					((TaImageArticleDTO) selectedImageArticle.getValue()).setPrixttcPrix(u.getPrixttcPrix());
//				}				
//			}
			return s;
//		} catch (IllegalAccessException e) {
//			logger.error("",e);
//		} catch (InvocationTargetException e) {
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
			//TaImageArticle u = null;
			TaImageArticleMapper mapper = new TaImageArticleMapper();
			if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION) == 0)) {
//				LgrDozerMapper<TaImageArticleDTO,TaImageArticle> mapper = new LgrDozerMapper<TaImageArticleDTO,TaImageArticle>();
//				mapper.map((TaImageArticleDTO) selectedImageArticle.getValue(),taImageArticle);
				mapper.mapDtoToEntity((TaImageArticleDTO) selectedImageArticle.getValue(), taImageArticle);
				
			} else if ((modeEcran.getMode().compareTo(EnumModeObjet.C_MO_INSERTION) == 0)) {
//				LgrDozerMapper<TaImageArticleDTO,TaImageArticle> mapper = new LgrDozerMapper<TaImageArticleDTO,TaImageArticle>();
//				mapper.map((TaImageArticleDTO) selectedImageArticle.getValue(),taImageArticle);
				mapper.mapDtoToEntity((TaImageArticleDTO) selectedImageArticle.getValue(), taImageArticle);
				taImageArticle.setTaArticle(masterEntity);
				masterEntity.addImageArticle(taImageArticle);
				if(((TaImageArticleDTO) selectedImageArticle.getValue()).getDefautImageArticle())
					masterEntity.setTaImageArticle(taImageArticle);	
				
				String md5 = DigestUtils.md5Hex(new FileInputStream(taImageArticle.getCheminImageArticle()));
				taImageArticle.setChecksum(md5);
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
			String baseCheminImg = utilPreferenceImage.getPathStockageImage();
			if(baseCheminImg!=null) {
				String repDestination = baseCheminImg+"/"+Const.FOLDER_IMAGES_ARTICLES;
				copieFichierImage(taImageArticle,repDestination);
			}
			
			if(taImageArticle.getTaImagesGenere().isEmpty()) {
				//pour l'instant, une seule image retaillé par image
				String cheminImageRetaille = retailleImage(taImageArticle);
				if(cheminImageRetaille!=null) {
					//image retaillée
					String md5ImageRetaille = DigestUtils.md5Hex(new FileInputStream(cheminImageRetaille));
					TaImageArticle imageRetaille = new TaImageArticle();
					imageRetaille.setNomImageArticle(new File(cheminImageRetaille).getName());
					imageRetaille.setImageOrigine(taImageArticle);
					imageRetaille.setTaArticle(masterEntity);
					imageRetaille.setChecksum(md5ImageRetaille);
					taImageArticle.getTaImagesGenere().add(imageRetaille);
					imageRetaille.setCheminImageArticle(cheminImageArticleAEnregistrer(imageRetaille));
				}
			}
			taImageArticle.setCheminImageArticle(cheminImageArticleAEnregistrer(taImageArticle));
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
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
			taImageArticle=masterEntity.getTaImageArticle();
//			dao.commit(transaction);
			changementDeSelection();
			actRefresh();
//			transaction = null;
			
			getModeEcran().setMode(EnumModeObjet.C_MO_CONSULTATION);//ejb
			
			hideDecoratedFields();
			//vue.getLaMessage().setText("");


		} finally {
//			if(transaction!=null && transaction.isActive()) {
//				transaction.rollback();
//			}
			initEtatBouton();
		}
	}
	
	/**
	 * Retourne le chemin a enregistrer réellement dans la base, absolu ou relatif en fonction des préférences
	 * @param imgAvecCheminAbsolu - Image avec un chemin absolu comme celui retourné par la fenêtre de dialogue de sélection de fichier.
	 * @return
	 */
	public String cheminImageArticleAEnregistrer(TaImageArticle imgAvecCheminAbsolu) {
		String retour = null;
		String baseCheminImg = utilPreferenceImage.getPathStockageImage();
		if(baseCheminImg!=null) {
			retour = Const.FOLDER_IMAGES_ARTICLES+"/"+imgAvecCheminAbsolu.getNomImageArticle();
		} else {
			retour = imgAvecCheminAbsolu.getCheminImageArticle();
		}
		return retour;
	}

	/**
	 * Copie du fichier images en fonction des préférences.
	 * @param taImageArticle
	 */
	public void copieFichierImage(TaImageArticle taImageArticle, String repDestination) {


			try {
				File fichierDestination = new File(repDestination+"/"+taImageArticle.getNomImageArticle());
				File fichierOrigine = new File(taImageArticle.getCheminImageArticle());
				
				if(fichierOrigine.exists()) {
					String md5Origine = DigestUtils.md5Hex(new FileInputStream(fichierOrigine));
					String md5Destination = "";
					if(fichierDestination.exists()) {
						md5Destination = DigestUtils.md5Hex(new FileInputStream(fichierDestination));
					}
					if(!fichierDestination.exists() 
							|| !md5Origine.equals(md5Destination)) {
						//le fichier n'existe pas ou n'est pas le même
						logger.debug("Le fichier de destination n'existe pas ou est différent.");
						FileUtils.copyFile(
								fichierOrigine, 
								fichierDestination
						);
					}
				}

			} catch (IOException e) {
				logger.error("",e);
			}
			//si vignette envoyé à la place de l'original, gestion de l'image par defaut
			//gestion de la suppression de l'image principale
			//si copier, suppression du fichier image
			//chemin relatif/absolu, les 2 ?
		
	}
	
	/**
	 * Redimensionnement du fichier images en fonction des préférences.
	 * @param taImageArticle
	 * @return - chemin absolu du nouveau fichier généré ou null si aucunne image généré
	 */
	public String retailleImage(TaImageArticle taImageArticle) {
		/* TODO
		 * Pour bien faire, il faudrait faire un point d'extension dans le plugin article,
		 * sur lequel viendrait se brancher tous les plugins qui ont besoin d'image redimensionné.
		 * En paramètre, on pourrait avoir pour chaque plugin, un modèle de nom, longeurMax, hauteurMax, tailleMax
		 */
		boolean retailleImg = ArticlesPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.RETAILLE_IMAGE_TROP_GROSSE_EXPORT);
		int longueurMaxImage = ArticlesPlugin.getDefault().getPreferenceStore().getInt(PreferenceConstants.LONGUEUR_MAX_IMAGE);
		int hauteurMaxImage = ArticlesPlugin.getDefault().getPreferenceStore().getInt(PreferenceConstants.HAUTEUR_MAX_IMAGE);
		int poidsMaxImageKo = ArticlesPlugin.getDefault().getPreferenceStore().getInt(PreferenceConstants.POIDS_MAX_IMAGE);
		String prefixe = ArticlesPlugin.getDefault().getPreferenceStore().getString(PreferenceConstants.PREFIXE_IMAGE_RETAILLE);
		
		String chemin = utilPreferenceImage.getPathStockageImage();
		String fichierGenere = null;
		
		if(retailleImg) {
			UtilImage utilImage = new UtilImage();

			ImageData imageData = new ImageData(taImageArticle.getCheminImageArticle());
			if(new File(taImageArticle.getCheminImageArticle()).length() > poidsMaxImageKo*1024
					|| imageData.height > hauteurMaxImage
					|| imageData.width > longueurMaxImage) {
				//fichier trop gros en poids ou dimensions de l'image trop grandes

				
				String nomFichierGenere = prefixe+taImageArticle.getNomImageArticle();
//				if(chemin!=null) {
//					fichierGenere = chemin+"/"+Const.FOLDER_IMAGES_ARTICLES+"/"+nomFichierGenere;
//				} else {
//					fichierGenere = Const.C_CHEMIN_REP_DOSSIER_COMPLET+"/"+Const.FOLDER_IMAGES+"/"+Const.FOLDER_IMAGES_ARTICLES+"/"+nomFichierGenere;
//				}
				fichierGenere = utilPreferenceImage.cheminCompletImageArticle(taImageArticle,true);
				fichierGenere = fichierGenere.replace(taImageArticle.getNomImageArticle(), nomFichierGenere);
				
				if(imageData.height > hauteurMaxImage){
					utilImage.scaleImage(taImageArticle.getCheminImageArticle(), 
							fichierGenere,
							PlatformUI.getWorkbench().getDisplay(), hauteurMaxImage, 0);
				} else if(imageData.width > longueurMaxImage){
					utilImage.scaleImage(taImageArticle.getCheminImageArticle(), 
							fichierGenere,
							PlatformUI.getWorkbench().getDisplay(), 0, longueurMaxImage);
				}
			} //else pas de génération car pas nécessaire
		}
		return fichierGenere;
	}

	public void initEtatComposant() {
//		try {
//			vue.getTfCheminImageArticle().setEditable(!isUtilise());
//			changeCouleur(vue);
//		} catch (Exception e) {
//			vue.getLaMessage().setText(e.getMessage());
//		}
	}
	
	public boolean isUtilise(){
		return (((TaImageArticleDTO)selectedImageArticle.getValue()).getId()!=null && 
				!dao.recordModifiable(dao.getNomTable(),
						((TaImageArticleDTO)selectedImageArticle.getValue()).getId()))||
						!masterDAO.autoriseModification(masterEntity);		
	}
	public TaImageArticleDTO getSwtOldPrix() {
		return swtOldImageArticle;
	}

	public void setSwtOldUnite(TaImageArticleDTO swtOldImageArticle) {
		this.swtOldImageArticle = swtOldImageArticle;
	}

	public void setSwtOldPrix() {
		if (selectedImageArticle.getValue() != null)
			this.swtOldImageArticle = TaImageArticleDTO.copy((TaImageArticleDTO) selectedImageArticle.getValue());
		else {
			if(tableViewer.selectionGrille(0)){
				this.swtOldImageArticle = TaImageArticleDTO.copy((TaImageArticleDTO) selectedImageArticle.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(TaImageArticleDTO) selectedImageArticle.getValue()), true);
			}}
	}

	public void setVue(PaActionArticleSWT vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, ControlDecoration>();

//		mapComposantDecoratedField.put(vue.getTfCheminImageArticle(), vue.getFieldCheminImageArticle());
////		mapComposantDecoratedField.put(vue.getTfPRIX_PRIX(), vue.getFieldPRIX_PRIX());
//		mapComposantDecoratedField.put(vue.getTfNomImageArticle(), vue.getFieldNomImageArticle());
//		mapComposantDecoratedField.put(vue.getCbDefautImageArticle(), vue.getFieldID_REF_PRIX());
	}

	public Class getClassModel() {
		return classModel;
	}



	@Override
	protected void sortieChamps() {
//		if(selectedImageArticle!=null && selectedImageArticle.getValue()!=null){
//			if(vue.getTfPRIX_PRIX().equals(getFocusCourantSWT())){
//				if(((TaImageArticleDTO)selectedImageArticle.getValue()).getTauxTva()!=null)
//					((TaImageArticleDTO)selectedImageArticle.getValue()).setPrixttcPrix(((TaImageArticleDTO)selectedImageArticle.getValue()).getPrixPrix().multiply(new BigDecimal(1).
//							add(((TaImageArticleDTO)selectedImageArticle.getValue()).getTauxTva().divide(new BigDecimal(100)))));
//				vue.getTfPRIXTTC_PRIX().setText(LibConversion.bigDecimalToString(((TaImageArticleDTO)selectedImageArticle.getValue()).getPrixttcPrix()));
//			} else if(vue.getTfPRIXTTC_PRIX().equals(getFocusCourantSWT())){
//				if(((TaImageArticleDTO)selectedImageArticle.getValue()).getTauxTva()!=null)
//					((TaImageArticleDTO)selectedImageArticle.getValue()).setPrixPrix(((TaImageArticleDTO)selectedImageArticle.getValue()).getPrixttcPrix().divide(new BigDecimal(1).
//							add(((TaImageArticleDTO)selectedImageArticle.getValue()).getTauxTva().divide(new BigDecimal(100))),MathContext.DECIMAL32));
//				vue.getTfPRIX_PRIX().setText(LibConversion.bigDecimalToString(((TaImageArticleDTO)selectedImageArticle.getValue()).getPrixPrix()));
//			}
//		}		
	}

	@Override
	protected void actRefresh() throws Exception {
		//repositionnement sur la valeur courante
		int idActuel = 0;
		if (taImageArticle!=null) { //enregistrement en cours de modification/insertion
			idActuel = taImageArticle.getIdImageArticle();
		} else if(selectedImageArticle!=null && (TaImageArticleDTO) selectedImageArticle.getValue()!=null) {
			idActuel = ((TaImageArticleDTO) selectedImageArticle.getValue()).getId();
		}

		//rafraichissement des valeurs dans la grille
		writableList = new WritableList(realm, IHMmodel(), classModel);
		tableViewer.setInput(writableList);

		if(idActuel!=0) {
			tableViewer.setSelection(new StructuredSelection(recherche(Const.C_ID_DTO_GENERAL, idActuel)));
		}else
			tableViewer.selectionGrille(0);				
	}
	
	protected class HandlerChoixImage extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				actChoixImage();
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
			return event;
		}
	}

	public void actChoixImage() {
//		FileDialog d = new FileDialog(vue.getShell());
//		d.setFilterExtensions(UtilImage.FILTRE_EXTENSION_IMAGE);
//		d.setFilterNames(UtilImage.FILTRE_LIBELLE_IMAGE);
//		String chemin = d.open();
//		if(chemin!=null) {
//			vue.getTfCheminImageArticle().setText(chemin);
//			vue.getTfCheminImageArticle().setToolTipText(chemin);
//			vue.getTfNomImageArticle().setText(new File(chemin).getName());
//			changementImage(chemin);
//		}
	}
	
//	/**
//	 * 
//	 * @param cheminImage - chemin du fichier image à afficher dans la zone de prévisualisation, NULL pour effacer.
//	 */
//	private void changementImage(String cheminImage) {
//		UtilImage util = new UtilImage();
//		imagePreview = util.changementImage(cheminImage, imagePreview, vue.getGrpPreview());
//	}
	
	public ModelGeneralObjetEJB<TaImageArticle,TaImageArticleDTO> getModelImageArticle() {
		return modelImageArticle;
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

	public TaImageArticle getTaImageArticle() {
		return taImageArticle;
	}

	public ITaImageArticleServiceRemote getDao() {
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
			if(taImageArticle!=null && selectedImageArticle!=null && ((TaImageArticleDTO) selectedImageArticle.getValue())!=null) {
				LgrDozerMapper<TaImageArticleDTO,TaImageArticle> mapper = new LgrDozerMapper<TaImageArticleDTO,TaImageArticle>();
				mapper.map((TaImageArticleDTO) selectedImageArticle.getValue(),taImageArticle);
			}
		}
		//		dao.getModeObjet().setMode(EnumModeObjet.C_MO_CONSULTATION);
		//		initEtatBouton();
		return true;
	};
	
	@Override
	public List<TaArticle> getSelection() {
		List<TaArticle> l = new ArrayList<TaArticle>();
		l.add(masterEntity);
		return l;
	}

}
