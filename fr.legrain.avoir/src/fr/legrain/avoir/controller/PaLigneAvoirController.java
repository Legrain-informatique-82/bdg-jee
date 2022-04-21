package fr.legrain.avoir.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.DecoratedField;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
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
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.expressions.ActivePartExpression;
import org.eclipse.ui.part.WorkbenchPart;

import fr.legrain.articles.dao.TaArticle;
import fr.legrain.articles.dao.TaArticleDAO;
import fr.legrain.articles.dao.TaPrix;
import fr.legrain.articles.dao.TaPrixDAO;
import fr.legrain.articles.dao.TaTvaDAO;
import fr.legrain.articles.dao.TaUnite;
import fr.legrain.articles.dao.TaUniteDAO;
import fr.legrain.articles.ecran.IExtensionEcran;
import fr.legrain.articles.ecran.PaArticleSWT;
import fr.legrain.articles.ecran.PaPrixSWT;
import fr.legrain.articles.ecran.PaUniteSWT;
import fr.legrain.articles.ecran.ParamAfficheArticles;
import fr.legrain.articles.ecran.ParamAffichePrix;
import fr.legrain.articles.ecran.ParamAfficheUnite;
import fr.legrain.articles.ecran.SWTPaArticlesController;
import fr.legrain.articles.ecran.SWTPaPrixController;
import fr.legrain.articles.ecran.SWTPaUniteController;
import fr.legrain.articles.editor.ArticleMultiPageEditor;
import fr.legrain.articles.editor.EditorInputArticle;
import fr.legrain.articles.editor.EditorInputPrix;
import fr.legrain.articles.editor.EditorInputUnite;
import fr.legrain.articles.editor.EditorPrix;
import fr.legrain.articles.editor.EditorUnite;
import fr.legrain.avoir.divers.ParamAfficheLAvoir;
import fr.legrain.avoir.ecran.PaSaisieLignesAvoir;
import fr.legrain.calcultva.controller.PaAideCalcTVAController;
import fr.legrain.calcultva.divers.ParamAideCalcTVA;
import fr.legrain.calcultva.divers.ResultAideCalcTVA;
import fr.legrain.calcultva.ecran.PaAideCalcTVASWT;
import fr.legrain.document.controller.MessagesEcran;
import fr.legrain.documents.dao.TaAvoir;
import fr.legrain.documents.dao.TaAvoirDAO;
import fr.legrain.documents.dao.TaLAvoir;
import fr.legrain.documents.dao.TaLAvoirDAO;
import fr.legrain.documents.dao.TaLFacture;
import fr.legrain.documents.dao.TaTLigneDAO;
import fr.legrain.documents.events.SWTModificationDocumentEvent;
import fr.legrain.documents.events.SWTModificationDocumentListener;
import fr.legrain.gestCom.Appli.Const;
//import fr.legrain.gestCom.Appli.LgrDozerMapper;
import fr.legrain.gestCom.Appli.ModelGeneralObjetEJB;
import fr.legrain.gestCom.Module_Articles.SWTArticle;
import fr.legrain.gestCom.Module_Articles.SWTPrix;
import fr.legrain.gestCom.Module_Document.IHMLAvoir;
import fr.legrain.gestCom.Module_Document.IHMTotauxLignesDocument;
import fr.legrain.gestCom.Module_GestionCapsules.SWTTitreTransport;
import fr.legrain.gestCom.gestComBd.gestComBdPlugin;
import fr.legrain.gestCom.librairiesEcran.LibrairiesEcranPlugin;
import fr.legrain.gestCom.librairiesEcran.editor.EditorAide;
import fr.legrain.gestCom.librairiesEcran.editor.EditorInputAide;
import fr.legrain.gestCom.librairiesEcran.swt.DeclencheCommandeControllerEvent;
import fr.legrain.gestCom.librairiesEcran.swt.JPABaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
import fr.legrain.gestCom.librairiesEcran.swt.LgrShellUtil;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheAideRechercheSWT;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheAideSWT;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideControllerSWT;
import fr.legrain.gestCom.librairiesEcran.swt.SWTPaAideRechercheControllerSWT;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDePageEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.gestionCommerciale.GestionCommercialePlugin;
import fr.legrain.gestioncapsules.controllers.SWTPaTitreTransport;
import fr.legrain.gestioncapsules.dao.TaTitreTransport;
import fr.legrain.gestioncapsules.dao.TaTitreTransportDAO;
import fr.legrain.gestioncapsules.ecrans.PaTitreTransport;
import fr.legrain.gestioncapsules.ecrans.ParamAfficheTitreTansport;
import fr.legrain.gestioncapsules.editors.EditorInputTitreTransport;
import fr.legrain.gestioncapsules.editors.EditorTitreTransport;
import fr.legrain.lib.data.ContentProposalProvider;
import fr.legrain.lib.data.ExceptLgr;
import fr.legrain.lib.data.InfosVerifSaisie;
import fr.legrain.lib.data.LibChaine;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.ModeObjet;
import fr.legrain.lib.data.ModeObjet.EnumModeObjet;
import fr.legrain.lib.data.VerrouInterface;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.lib.gui.ParamAfficheSWT;
import fr.legrain.lib.gui.ResultAffiche;
import fr.legrain.lib.gui.RetourEcranEvent;
import fr.legrain.lib.gui.RetourEcranListener;
import fr.legrain.lib.gui.aide.PaAideRechercheSWT;
import fr.legrain.lib.gui.aide.PaAideSWT;
import fr.legrain.lib.gui.aide.ParamAfficheAide;
import fr.legrain.lib.gui.grille.LgrTableViewer;
import fr.legrain.lib.gui.grille.LgrViewerSupport;
import fr.legrain.libMessageLGR.LgrMess;

public class PaLigneAvoirController extends JPABaseControllerSWTStandard
implements RetourEcranListener, SWTModificationDocumentListener, ISelectionProvider {

	static Logger logger = Logger.getLogger(PaLigneAvoirController.class.getName());

	public static final String C_COMMAND_LIGNE_FACTURE_AIDE_ID 			= "fr.legrain.ligneFacture.aide";
	public static final String C_COMMAND_LIGNE_FACTURE_ANNULER_ID 		= "fr.legrain.ligneFacture.annuler";
	public static final String C_COMMAND_LIGNE_FACTURE_ENREGISTRER_ID 	= "fr.legrain.ligneFacture.enregsitrer";
	public static final String C_COMMAND_LIGNE_FACTURE_FERMER_ID 		= "fr.legrain.ligneFacture.fermer";
	public static final String C_COMMAND_LIGNE_FACTURE_IMPRIMER_ID 		= "fr.legrain.ligneFacture.imprimer";
	public static final String C_COMMAND_LIGNE_FACTURE_INSERER_ID 		= "fr.legrain.ligneFacture.inserer";
	public static final String C_COMMAND_LIGNE_FACTURE_MODIFIER_ID 		= "fr.legrain.ligneFacture.modifier";
	public static final String C_COMMAND_LIGNE_FACTURE_SUPPRIMER_ID 	= "fr.legrain.ligneFacture.supprimer";
	public static final String C_COMMAND_LIGNE_FACTURE_REFRESH_ID 	    = "fr.legrain.ligneFacture.refresh";
	public static final String C_COMMAND_LIGNE_FACTURE_ENTETE_ENREGISTRER_ID 	= "fr.legrain.ligneFactureEntete.enregsitrer";
	protected ActionEnregistrerFacture actionEnregistrerFacture = new ActionEnregistrerFacture("Enregistrer Facture [Ctrl+F3]");
	protected HandlerEnregistrerFacture handlerEnregistrerFacture = new HandlerEnregistrerFacture();
	public List<TaArticle> listeArticles = null;

	public static final String C_COMMAND_LIGNE_FACTURE_AJOUT_ID 		= "fr.legrain.ligneFacture.ajout";
	protected ActionAjouterLigne actionAjouter = new ActionAjouterLigne("Ajouter [F6]");
	protected HandlerAjouter handlerAjouter = new HandlerAjouter();

	public static final String C_COMMAND_LIGNE_FACTURE_AIDETVA_ID 		= "fr.legrain.ligneFacture.aideTva";

	protected ActionAideCalcTVA actionAideTva = new ActionAideCalcTVA("Aide Tva [F8]");
	protected HandlerAideTva handlerAideTva = new HandlerAideTva();

	public static final String C_COMMAND_LIGNE_FACTURE_CHANGETVA_ID 		= "fr.legrain.ligneFacture.changeTva";

	protected HandlerChangeTva handlerChangeTva = new HandlerChangeTva();
	private PaSaisieLignesAvoir vue = null;


	private TaLAvoirDAO dao = null;//new TaLAvoirDAO();


	private Object ecranAppelant = null;
	private IHMLAvoir ihmLAvoir;
	private IHMTotauxLignesDocument ihmTotauxLignesAvoir;
	private IHMLAvoir ihmOldLAvoir;
	private Realm realm;
	private DataBindingContext dbc;
	private DataBindingContext dbcTotaux = null;

	//	private ModelLigneFacture modelLAvoir /*=  new ModelLigneFacture(ibTaTable)*/;
	private Class classModel = IHMLAvoir.class;
	private ModelGeneralObjet<IHMLAvoir,TaLAvoirDAO,TaLAvoir> modelLAvoir /*= new ModelGeneralObjet<IHMLAvoir,TaLAvoirDAO,TaLAvoir>(dao,classModel)*/;
	private LgrTableViewer tableViewer;
	private WritableList writableList;
	private IObservableValue selectedLigneAvoir;
	private Object selectedTotauxLigneDocument;
	private String[] listeChamp;
	private String nomClassController = this.getClass().getSimpleName();
	private SelectionGrille select = new SelectionGrille();

	private ContentProposalAdapter articleContentProposal;

	private MapChangeListener changeListener = new MapChangeListener();
	public boolean forceAnnuler=false;
	public JPABaseControllerSWTStandard parentEcran = null;
	public static IContextActivation iContext;
	public List<IContextActivation> listeContextEntete;


	protected Map<Control, String> mapComposantChampsTotauxLignes = null;

	private TaLAvoir taLAvoir = null;

	private TaAvoir masterEntity = null;
	private TaAvoirDAO masterDAO = null;


	String[] listeCodeArticles=null;
	String[] listeDescriptionArticles=null;

	private LgrDozerMapper<IHMLAvoir,TaLAvoir> mapperUIToModel = new LgrDozerMapper<IHMLAvoir,TaLAvoir>();
	private LgrDozerMapper<TaLAvoir,IHMLAvoir> mapperModelToUI = new LgrDozerMapper<TaLAvoir,IHMLAvoir>();

	private LgrDozerMapper<TaAvoir,IHMTotauxLignesDocument> mapperModelToUITotauxLignes = new LgrDozerMapper<TaAvoir,IHMTotauxLignesDocument>();
	
	private List<IExtensionEcran> liste = null;

	public PaLigneAvoirController(){
	}

	public PaLigneAvoirController(final PaSaisieLignesAvoir vue) {
		this(vue,null);
	}

	public PaLigneAvoirController(final PaSaisieLignesAvoir vue,EntityManager em) {
		if(em!=null) {
			setEm(em);
		}
		createContributors();
		dao = new TaLAvoirDAO(getEm());
		try {
			setVue(vue);
			// a faire avant l'initialisation du Binding
			vue.getGrille().addSelectionListener(select);
			vue.getShell().addShellListener(this);

			//			// Branchement action annuler : empêche la fermeture automatique de la
			//			// fenetre sur ESC
			//vue.getShell().addTraverseListener(new Traverse());

			vue.getGrille().addMouseListener(new MouseAdapter(){
				int rang;
				int rangTmp;
				public void mouseDown(MouseEvent e) {
					rang = vue.getGrille().getSelectionIndex();					
				}

				public void mouseUp(MouseEvent e) {
					vue.getGrille().setSelection(vue.getGrille().getItem(new Point(e.x,e.y)));
					rangTmp = vue.getGrille().getSelectionIndex();					
					if(rang!=rangTmp){
						try {
							masterEntity.getLigne(rang).setModeLigne(EnumModeObjet.C_MO_EDITION);
							//#AFAIRE
							masterEntity.deplaceLigne(masterEntity.getLigne(rang), rangTmp);
							//							ibTaTable.inverseNumLigne(rang,rangTmp, ((SWTLigneFacture)masterEntity.getLigne(rang)).getID_L_FACTURE());
							masterEntity.getLigne(rang).setModeLigne(EnumModeObjet.C_MO_CONSULTATION);
							masterEntity.setModeDocument(EnumModeObjet.C_MO_EDITION);
							actRefresh();
							//							initLigneCourantSurRow();
							//							ibTaTable.initLigneCourantSurRow();
						} catch (Exception e1) {
							logger.error("", e1);
						}
					}
				}				
			});

			initController();
		}
		catch (Exception e) {
			logger.debug(e);
		}
	}
	
	private void createContributors() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint("Articles.editorEcranArticles"); //$NON-NLS-1$
		if (point != null) {
			IExtension[] extensions = point.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IConfigurationElement confElements[] = extensions[i].getConfigurationElements();
				for (int jj = 0; jj < confElements.length; jj++) {
					try {
						String editorClass = confElements[jj].getAttribute("classe");//$NON-NLS-1$

						if (editorClass == null)
							continue;

						Object o = confElements[jj].createExecutableExtension("classe");
						if(liste == null)
							liste = new ArrayList<IExtensionEcran>();
						liste.add(((IExtensionEcran)o));

					} catch (Exception e) {
						logger.error("Erreur : SWTPaLigneController", e);
					}
				}
			}
		}
	}

	private void initController() {
		try {
			setGrille(vue.getGrille());
			
			if(liste!=null) {
				vue.extensionTitreTransport(vue);
			}
			
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
			vue.getPaSaisie().setMenu(popupMenuFormulaire);
			vue.getGrille().setMenu(popupMenuGrille);
			bind(vue);
			initEtatComposant();
			initEtatBouton(false);
			initTTC();
		} catch (Exception e) {
			//vue.getLaMessage().setText(e.getMessage());
			logger.error("Erreur : PaArticlesController", e);
		}
	}

	/**
	 * Creation des objet pour l'interface, a partir de l'entite principale.<br>
	 * Ici : creation d'une liste de ligne pour l'IHM, a partir de la liste des lignes contenue dans l'entite facture.
	 * @return
	 */
	public List<IHMLAvoir> IHMmodel() {
		LinkedList<TaLAvoir> ldao = new LinkedList<TaLAvoir>();
		LinkedList<IHMLAvoir> lswt = new LinkedList<IHMLAvoir>();

		if(masterEntity!=null && !masterEntity.getLignes().isEmpty()) {

			ldao.addAll(masterEntity.getLignes());

			LgrDozerMapper<TaLAvoir,IHMLAvoir> mapper = new LgrDozerMapper<TaLAvoir,IHMLAvoir>();
			for (TaLAvoir o : ldao) {
				IHMLAvoir t = null;
				t = (IHMLAvoir) mapper.map(o, IHMLAvoir.class);
				lswt.add(t);
			}

		}
		return lswt;
	}

	public void bind(PaSaisieLignesAvoir paArticleSWT) {
		try {
			if (modelLAvoir==null)modelLAvoir = new ModelGeneralObjet<IHMLAvoir, TaLAvoirDAO, TaLAvoir>(dao,classModel);
			if (realm==null)realm = SWTObservables.getRealm(vue.getParent().getDisplay());

			if (tableViewer==null){
				tableViewer = new LgrTableViewer(paArticleSWT.getGrille());
				tableViewer.createTableCol(paArticleSWT.getGrille(), nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
				listeChamp = tableViewer.setListeChampGrille(nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
				if(liste!=null) {
					tableViewer.createTableCol(paArticleSWT.getGrille(), nomClassController+"Viti",Const.C_FICHIER_LISTE_CHAMP_GRILLE, true);

					String[] listeChamp2 = new String[listeChamp.length+2];
					System.arraycopy(listeChamp, 0, listeChamp2, 0, listeChamp.length);
					listeChamp2[listeChamp.length] = Const.C_QTE_TITRE_TRANSPORT_L_DOCUMENT;
					listeChamp2[listeChamp.length+1] = Const.C_CODE_TITRE_TRANSPORT;
					listeChamp = listeChamp2;
				}

				//				ObservableListContentProvider viewerContent = new ObservableListContentProvider();
				//				tableViewer.setContentProvider(viewerContent);
				//
				//				IObservableMap[] attributeMaps = BeansObservables.observeMaps(viewerContent.getKnownElements(), classModel, listeChamp);
				//
				//				tableViewer.setLabelProvider(new ObservableMapLabelProvider(attributeMaps));
				//				writableList = new WritableList(realm, IHMmodel(), classModel);
				//				tableViewer.setInput(writableList);

				// Set up data binding.
				LgrViewerSupport.bind(tableViewer, 
						new WritableList(IHMmodel(), classModel),
						BeanProperties.values(listeChamp)
				);

				selectedLigneAvoir = ViewersObservables.observeSingleSelection(tableViewer);
			}
			dbc = new DataBindingContext(realm);

			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			tableViewer.selectionGrille(0);

			setTableViewerStandard(tableViewer);
			setDbcStandard(dbc);

			bindingFormMaitreDetail(dbc, realm, selectedLigneAvoir,classModel);

			tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					if(masterEntity!=null) {
						masterEntity.setLigneCourante(vue.getGrille().getSelectionIndex());
						//#JPA a remettre ??
						//						select.widgetSelected(null);
					}
				}
			});

			bindTotaux();

		} catch (Exception e) {
			//vue.getLaMessage().setText(e.getMessage());
			logger.error("", e);
		}
	}

	public void bindTotaux() {
		try {
			realm = SWTObservables.getRealm(vue.getParent().getDisplay());


			if(ihmTotauxLignesAvoir==null) ihmTotauxLignesAvoir = new IHMTotauxLignesDocument();
			if(masterEntity!=null)
				mapperModelToUITotauxLignes.map(masterEntity,ihmTotauxLignesAvoir);

			selectedTotauxLigneDocument = ihmTotauxLignesAvoir;
			dbcTotaux = new DataBindingContext(realm);

			//			dbc.getValidationStatusMap().addMapChangeListener(changeListener);
			//			setDbcStandard(dbc);

			bindingFormSimple(masterDAO, mapComposantChampsTotauxLignes, dbcTotaux, realm, selectedTotauxLigneDocument, IHMTotauxLignesDocument.class);

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public Composite getVue() {
		return vue;
	}

	public ResultAffiche configPanel(ParamAffiche param) {
		if (param != null) {
			//			ibTaTable.ouvreDataset();
			if (((ParamAfficheLAvoir)param).getFocusSWT()!=null)
				((ParamAfficheLAvoir)param).getFocusSWT().forceFocus();
			else ((ParamAfficheLAvoir)param).setFocusSWT(vue.getTfCODE_ARTICLE());			
			if(param.getEcranAppelant()!=null) {
				ecranAppelant = param.getEcranAppelant();
			}

			param.setFocus(dao.getModeObjet().getFocusCourant());

			if (param.getEcranAppelant() != null) {
				ecranAppelant = param.getEcranAppelant();
			}


		}
		//		else {
		//			ibTaTable.getFIBQuery().close();
		//			ibTaTable.getFIBQuery().setQuery(new QueryDescriptor(ibTaTable.getFIBBase(),ibTaTable.getRequete(),true));
		//			ibTaTable.getFIBQuery().open();
		//			ibTaTable.getParamSQL().remove(Const.C_ID_FACTURE);
		//			ibTaTable.getParamSQL().put(Const.C_ID_FACTURE,new String[]{"=",String.valueOf(((SWTEnteteFacture)masterEntity.getEntete()).getID_FACTURE())});
		//			//ibTaTable.getParamSQL().put("",new String[]{" order by ",Const.C_NUM_LIGNE_L_FACTURE});
		//			ibTaTable.setParamWhereSQL(ibTaTable.getParamSQL());
		//			ibTaTable.getFIBQuery().setRowId(ibTaTable.champIdTable, true);
		//			ibTaTable.getFIBQuery().setSort(null);
		//		}	

		if(tableViewer==null) {
			//databinding pas encore realise
			bind(vue);
		} else {
			try {
				actRefresh();
				refreshTotauxLignes();
			} catch (Exception e) {
				logger.error("",e);
			}
		}

		if (param.getModeEcran() != null
				&& param.getModeEcran().compareTo(EnumModeObjet.C_MO_INSERTION) == 0) {
			try {
				insertionLigne(false,((ParamAfficheLAvoir)param).getInitFocus());
				//actInserer(((ParamAfficheLAvoir)param).getInitFocus());
			} catch (Exception e) {
				//vue.getLaMessage().setText(e.getMessage());
				logger.error("", e);
			}
		} else {
			initEtatBouton();
		}
		// permet de se positionner sur le 1er enregistrement et de remplir
		// le formulaire
		tableViewer.selectionGrille(0);
		tableViewer.tri(classModel, nomClassController,Const.C_FICHIER_LISTE_CHAMP_GRILLE);
		VerrouInterface.setVerrouille(false);
		setIhmOldLAvoir();

		return null;
	}

	protected void initVerifySaisie() {
		if (mapInfosVerifSaisie == null)
			mapInfosVerifSaisie = new HashMap<Control, InfosVerifSaisie>();

		mapInfosVerifSaisie.put(vue.getTfQTE_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_QTE_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		mapInfosVerifSaisie.put(vue.getTfQTE2_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_QTE2_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		mapInfosVerifSaisie.put(vue.getTfMT_HT_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_MT_HT_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		mapInfosVerifSaisie.put(vue.getTfMT_TTC_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_MT_TTC_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		mapInfosVerifSaisie.put(vue.getTfPRIX_U_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_PRIX_U_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES}));
		mapInfosVerifSaisie.put(vue.getTfREM_TX_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_REM_TX_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		mapInfosVerifSaisie.put(vue.getTfNUM_LIGNE_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_NUM_LIGNE_L_DOCUMENT,new int[]{InfosVerifSaisie.CTRL_QUE_DES_CHIFFRES_POSITIFS}));
		mapInfosVerifSaisie.put(vue.getTfCODE_ARTICLE(), new InfosVerifSaisie(new TaArticle(),Const.C_CODE_ARTICLE,null));
		mapInfosVerifSaisie.put(vue.getTfLIB_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_LIB_L_DOCUMENT,null));
		mapInfosVerifSaisie.put(vue.getTfU1_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_U1_L_DOCUMENT,null));
		mapInfosVerifSaisie.put(vue.getTfU2_L_AVOIR(), new InfosVerifSaisie(new TaLAvoir(),Const.C_U1_L_DOCUMENT,null));

		initVerifyListener(mapInfosVerifSaisie, dao);
	}

	protected void initComposantsVue() throws ExceptLgr {
	}

	protected void initMapComposantChamps() {
		if (mapComposantChamps == null)
			mapComposantChamps = new LinkedHashMap<Control, String>();

		if (mapComposantChampsTotauxLignes == null)
			mapComposantChampsTotauxLignes = new LinkedHashMap<Control, String>();

		if (listeComposantFocusable == null)
			listeComposantFocusable = new ArrayList<Control>();
		listeComposantFocusable.add(vue.getGrille());

		mapComposantChampsTotauxLignes.put(vue.getTfMT_HT_CALC(),Const.C_MT_HT_CALC);
		mapComposantChampsTotauxLignes.put(vue.getTfMT_TTC_CALC(),Const.C_MT_TTC_AVANTREMISEGLOBALE_CALC);
		mapComposantChampsTotauxLignes.put(vue.getTfNET_HT_CALC(),Const.C_NET_HT_CALC);
		mapComposantChampsTotauxLignes.put(vue.getTfNET_TTC_CALC(),Const.C_NET_TTC_CALC);
		mapComposantChampsTotauxLignes.put(vue.getTfNET_TVA_CALC(),Const.C_NET_TVA_CALC);

		mapComposantChamps.put(vue.getTfCODE_ARTICLE(), Const.C_CODE_ARTICLE);
		mapComposantChamps.put(vue.getTfLIB_L_AVOIR(), Const.C_LIB_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfQTE_L_AVOIR(), Const.C_QTE_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfU1_L_AVOIR(), Const.C_U1_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfQTE2_L_AVOIR(), Const.C_QTE2_L_DOCUMENT);		
		mapComposantChamps.put(vue.getTfU2_L_AVOIR(), Const.C_U2_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfPRIX_U_L_AVOIR(), Const.C_PRIX_U_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfREM_TX_L_AVOIR(), Const.C_REM_TX_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfMT_HT_L_AVOIR(), Const.C_MT_HT_L_DOCUMENT);
		mapComposantChamps.put(vue.getTfMT_TTC_L_AVOIR(), Const.C_MT_TTC_L_DOCUMENT);
		
		if(liste!=null) {
			mapComposantChamps.put(vue.getTfQteTitreTransportU1(), Const.C_QTE_TITRE_TRANSPORT_L_DOCUMENT);
			mapComposantChamps.put(vue.getTfTitreTransportU1(), Const.C_CODE_TITRE_TRANSPORT);
		}

		vue.getTfCODE_ARTICLE().addFocusListener(new org.eclipse.swt.events.FocusAdapter(){
			public void focusGained(FocusEvent e) {
//				String[][] TabArticles = initAdapterArticle();
//				String[] listeCodeArticles=null;
//				String[] listeDescriptionArticles=null;
//				if(TabArticles!=null){
//					listeCodeArticles=new String[TabArticles.length];
//					listeDescriptionArticles=new String[TabArticles.length];
//					for (int i=0; i<TabArticles.length; i++) {
//						listeCodeArticles [i]=TabArticles[i][0];
//						listeDescriptionArticles [i]=TabArticles[i][1];
//					}
//				}
				articleContentProposal.setContentProposalProvider(
						contentProposalProviderArticles());
			}
		});

		for (Control c : mapComposantChamps.keySet()) {
			c.setToolTipText(mapComposantChamps.get(c));
		}

		for (Control c : mapComposantChamps.keySet()) {
			listeComposantFocusable.add(c);
		}

		mapComposantChamps.put(vue.getTfNUM_LIGNE_L_AVOIR(), Const.C_NUM_LIGNE_L_DOCUMENT);



		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnEnregistrer());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtnAssitant().getBtnSuivant());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnInserer());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnModifier());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnSupprimer());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnFermer());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnAnnuler());
		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtn().getBtnImprimer());

		listeComposantFocusable.add(vue.getPaBtnAvecAssistant().getPaBtnAssitant().getBtnPrecedent());
		class TraverseDown implements org.eclipse.swt.events.KeyListener {

			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ARROW_DOWN) {
					try {
						actDown();
					} catch (Exception e1) {
						logger.error("", e1);
					}
				}

			}

			public void keyReleased(KeyEvent e) {
				// TODO Raccord de méthode auto-généré

			}

		};

		class  TraverseUp implements org.eclipse.swt.events.KeyListener {

			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ARROW_UP ) {
					try {
						actUp();
					} catch (Exception e1) {
						logger.error("", e1);
					}

				}
			}

			public void keyReleased(KeyEvent e) {
		}
		};

		for (Control element : mapComposantChamps.keySet()) {
			element.addKeyListener(new TraverseUp());
		}
		for (Control element : mapComposantChamps.keySet()) {
			element.addKeyListener(new TraverseDown());
		}
		vue.getGrille().addKeyListener(new TraverseUp());
		vue.getGrille().addKeyListener(new TraverseDown());

		if (mapInitFocus == null)
			mapInitFocus = new LinkedHashMap<ModeObjet.EnumModeObjet, Control>();
		mapInitFocus.put(ModeObjet.EnumModeObjet.C_MO_INSERTION, vue.getTfCODE_ARTICLE());
		mapInitFocus.put(ModeObjet.EnumModeObjet.C_MO_EDITION, vue.getTfCODE_ARTICLE());
		mapInitFocus.put(ModeObjet.EnumModeObjet.C_MO_CONSULTATION, vue.getTfCODE_ARTICLE());


		activeModifytListener();

		vue.getTfQTE_L_AVOIR().addVerifyListener(queDesChiffresPositifs);
		vue.getTfQTE2_L_AVOIR().addVerifyListener(queDesChiffresPositifs);
		vue.getTfMT_HT_L_AVOIR().addVerifyListener(queDesChiffresPositifs);
		vue.getTfMT_TTC_L_AVOIR().addVerifyListener(queDesChiffresPositifs);
		vue.getTfPRIX_U_L_AVOIR().addVerifyListener(queDesChiffres);
		vue.getTfREM_TX_L_AVOIR().addVerifyListener(queDesChiffresPositifs);
		vue.getTfNUM_LIGNE_L_AVOIR().addVerifyListener(queDesChiffresPositifs);

		KeyStroke keyStroke;
		try {
			keyStroke = KeyStroke.getInstance("Ctrl+Space");

//			String[][] TabArticles = initAdapterArticle();
//			if(TabArticles!=null){
//				listeCodeArticles=new String[TabArticles.length];
//				listeDescriptionArticles=new String[TabArticles.length];
//				for (int i=0; i<TabArticles.length; i++) {
//					listeCodeArticles [i]=TabArticles[i][0];
//					listeDescriptionArticles [i]=TabArticles[i][1];
//				}
//			}

			articleContentProposal = new ContentProposalAdapter(
					vue.getTfCODE_ARTICLE(), new TextContentAdapter(), 
					contentProposalProviderArticles(),keyStroke, null);
			articleContentProposal.setFilterStyle(ContentProposalAdapter.FILTER_NONE);
			articleContentProposal.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
			articleContentProposal.addContentProposalListener(new IContentProposalListener2(){

				public void proposalPopupClosed(ContentProposalAdapter adapter) {
					articleContentProposal.setEnabled(true);					
				}

				public void proposalPopupOpened(ContentProposalAdapter adapter) {
					if(masterEntity.getLigne(masterEntity.getLigneCourante())==null ) {
						if(!dao.dataSetEnModif())articleContentProposal.setEnabled(false);
					}else
						if(!dao.dataSetEnModif()&& taLAvoir.ligneEstVide()  )
							articleContentProposal.setEnabled(false);
					articleContentProposal.setEnabled(true);
				}


			});
		} catch (ParseException e1) {
			logger.error("",e1);
		}

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
		mapCommand.put(C_COMMAND_GLOBAL_IMPRIMER_ID, handlerImprimer);
		mapCommand.put(C_COMMAND_LIGNE_FACTURE_CHANGETVA_ID, handlerChangeTva);
		


		mapCommand.put(C_COMMAND_GLOBAL_PRECEDENT_ID, handlerPrecedent);
		mapCommand.put(C_COMMAND_GLOBAL_SUIVANT_ID, handlerSuivant);

		initFocusCommand(String.valueOf(this.hashCode()),listeComposantFocusable,mapCommand);

		if (mapActions == null)
			mapActions = new LinkedHashMap<Button, Object>();

		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnAnnuler(), C_COMMAND_GLOBAL_ANNULER_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnEnregistrer(), C_COMMAND_GLOBAL_ENREGISTRER_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnInserer(), C_COMMAND_GLOBAL_INSERER_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnModifier(), C_COMMAND_GLOBAL_MODIFIER_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnSupprimer(), C_COMMAND_GLOBAL_SUPPRIMER_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnFermer(), C_COMMAND_GLOBAL_FERMER_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtn().getBtnImprimer(), C_COMMAND_GLOBAL_IMPRIMER_ID);

		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtnAssitant().getBtnPrecedent(), C_COMMAND_GLOBAL_PRECEDENT_ID);
		mapActions.put(vue.getPaBtnAvecAssistant().getPaBtnAssitant().getBtnSuivant(), C_COMMAND_GLOBAL_SUIVANT_ID);

		Object[] tabActionsAutres = new Object[] { C_COMMAND_GLOBAL_AIDE_ID, C_COMMAND_GLOBAL_REFRESH_ID,C_COMMAND_LIGNE_FACTURE_CHANGETVA_ID };
		mapActions.put(null, tabActionsAutres);
	}	

	@Override
	protected void initImageBouton() {
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnAnnuler().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_ANNULER));
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnEnregistrer().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_ENREGISTRER));
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnFermer().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_FERMER));
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnInserer().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_INSERER));
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnModifier().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_MODIFIER));
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnSupprimer().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_SUPPRIMER));
		vue.getPaBtnAvecAssistant().getPaBtn().getBtnImprimer().setImage(
				LibrairiesEcranPlugin.ir.get(LibrairiesEcranPlugin.IMAGE_IMPRIMER));
		vue.layout(true);
	}

	public void initWorkenchPartCommands(WorkbenchPart part){
		if(handlerService == null)
			handlerService = (IHandlerService)PlatformUI.getWorkbench().getService(IHandlerService.class);
		if(contextService == null)
			contextService = (IContextService) PlatformUI.getWorkbench().getService(IContextService.class);
		activePartExpression = new ActivePartExpression(part);

		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_AIDE_ID, handlerAide,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_ANNULER_ID, handlerAnnuler,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_ENREGISTRER_ID, handlerEnregistrer,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_FERMER_ID, handlerFermer,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_IMPRIMER_ID, handlerImprimer,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_INSERER_ID, handlerInserer,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_AJOUT_ID, handlerAjouter,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_MODIFIER_ID, handlerModifier,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_SUPPRIMER_ID, handlerSupprimer,activePartExpression);	
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_REFRESH_ID, handlerRefresh,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_AIDETVA_ID, handlerAideTva,activePartExpression);
		handlerService.activateHandler(C_COMMAND_LIGNE_FACTURE_ENTETE_ENREGISTRER_ID, handlerEnregistrerFacture,activePartExpression);

	}

	public PaLigneAvoirController getThis() {
		return this;
	}

	@Override
	public boolean onClose() throws ExceptLgr {
		return false;
	}

	public void retourEcran(final RetourEcranEvent evt) {
		try {
			if (evt.getRetour() != null && (evt.getSource() instanceof SWTPaAideControllerSWT)) {
				if (getFocusAvantAideSWT() instanceof Text) {
					try {
						((Text) getFocusAvantAideSWT()).setText(((ResultAffiche) evt
								.getRetour()).getResult());	
						if(getFocusAvantAideSWT().equals(vue.getTfCODE_ARTICLE())||getFocusAvantAideSWT().equals(vue.getTfLIB_L_AVOIR())){
							TaArticle f = null;
							TaArticleDAO t = new TaArticleDAO(getEm());
							f = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
							t = null;
							taLAvoir.setLegrain(true);
								taLAvoir.setTaArticle(f);
						}
						if(getFocusAvantAideSWT().equals(vue.getTfU1_L_AVOIR())
								||getFocusAvantAideSWT().equals(vue.getTfU2_L_AVOIR())){
							TaUnite f = null;
							TaUniteDAO t = new TaUniteDAO(getEm());
							f = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
							t = null;
							if(getFocusAvantAideSWT().equals(vue.getTfU1_L_AVOIR()))
								taLAvoir.setU1LDocument(f.getCodeUnite());
							else
								taLAvoir.setU2LDocument(f.getCodeUnite());
						}					
						if(getFocusAvantAideSWT().equals(vue.getTfPRIX_U_L_AVOIR())){
							TaPrix f = null;
							TaPrixDAO t = new TaPrixDAO(getEm());
							f = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
							t = null;
							taLAvoir.setLegrain(true);
							if(masterEntity.getTtc()==1)
								taLAvoir.setPrixULDocument(f.getPrixttcPrix());
							else
								taLAvoir.setPrixULDocument(f.getPrixPrix());
						}		
						if(getFocusAvantAideSWT().equals(vue.getTfTitreTransportU1())){
							TaTitreTransport f = null;
							TaTitreTransportDAO t = new TaTitreTransportDAO(getEm());
							f = t.findById(Integer.parseInt(((ResultAffiche) evt.getRetour()).getIdResult()));
							t = null;
							taLAvoir.setLegrain(true);
							taLAvoir.setCodeTitreTransport(f.getCodeTitreTransport());
						}	
						ctrlUnChampsSWT(getFocusAvantAideSWT());
					} catch (Exception e) {
						logger.error("",e);
					}
					String[][] TabArticles = initAdapterArticle();
					if(TabArticles!=null){
					String[] listeCodeArticles=new String[TabArticles.length];
					String[] listeDescriptionArticles=new String[TabArticles.length];
					for (int i=0; i<TabArticles.length; i++) {
						listeCodeArticles [i]=TabArticles[i][0];
						listeDescriptionArticles [i]=TabArticles[i][1];
					}
					articleContentProposal.setContentProposalProvider(new ContentProposalProvider(listeCodeArticles,listeDescriptionArticles));
					}
				}
			} else if(evt.getRetour()!=null && (evt.getSource() instanceof PaAideCalcTVAController)) { 
				if(this.getFocusAvantAideSWT() instanceof Text && this.getFocusAvantAideSWT()==vue.getTfPRIX_U_L_AVOIR()){
					if(masterEntity.getTtc()==0)
						((Text)this.getFocusAvantAideSWT()).setText(((ResultAideCalcTVA)evt.getRetour()).getMtHT());
					else
						((Text)this.getFocusAvantAideSWT()).setText(((ResultAideCalcTVA)evt.getRetour()).getMtTTC());
					try {
						ctrlUnChampsSWT(this.getFocusAvantAideSWT())	;
					} catch (Exception e) {
						logger.error("",e);
					}
				}
			} else if(evt.getRetour()!=null){
				if (getFocusAvantAideSWT() instanceof Table) {
					if (getFocusAvantAideSWT() == vue.getGrille()) {
						//					ibTaTable.lgrLocateID(((ResultAffiche) evt.getRetour()).getIdResult());
					}
				}
			}
			//super.retourEcran(evt);
		}finally{
			//ne pas enlever car sur demande de l'aide, je rends enable false tous les boutons
			//donc au retour de l'aide, je reinitialise les boutons suivant l'état du dataset
			//activeWorkenchPartCommands(true);
			initEtatBouton(false);
			if(parentEcran!=null) {
				//((PaEditorAvoirController)parentEcran).activeWorkenchPartCommands(true);
				((PaEditorAvoirController)parentEcran).initEtatBouton(false);
			}
		}
	}


	protected void actAideTva() throws Exception{
		//		org.eclipse.ui.PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable(){

		//		public void run() {
		if (getFocusCourantSWT()==vue.getTfPRIX_U_L_AVOIR()){
			try {
				Shell s = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),LgrShellUtil.styleLgr);
				PaAideCalcTVASWT paAideCalcTVA = new PaAideCalcTVASWT(s,SWT.NONE);
				PaAideCalcTVAController paAideCalcTVAController = new PaAideCalcTVAController(paAideCalcTVA);
				ParamAideCalcTVA paramAideCalcTVA= new ParamAideCalcTVA();

				if(!LibChaine.empty(vue.getTfCODE_ARTICLE().getText())&& masterEntity.getLigne(masterEntity.getLigneCourante())!=null) {
					TaArticleDAO taArticleDAO = new TaArticleDAO(getEm());
					TaArticle taArticle = taArticleDAO.findById(((TaLFacture)masterEntity.getLigne(masterEntity.getLigneCourante())).getTaArticle().getIdArticle());
					if(taArticle.getTaTva().getTauxTva()!=null)
						paramAideCalcTVA.setTaux(LibConversion.bigDecimalToString(taArticle.getTaTva().getTauxTva()));
					else
						paramAideCalcTVA.setTaux("0.0");
				}
				if (masterEntity.getTtc()==0){
					paramAideCalcTVA.setFocusSWT(paAideCalcTVA.getTfMtTTC());
					paramAideCalcTVA.setMtHT(vue.getTfPRIX_U_L_AVOIR().getText());
				}
				else{
					paramAideCalcTVA.setFocusSWT(paAideCalcTVA.getTfMtHT());
					paramAideCalcTVA.setMtTTC(vue.getTfPRIX_U_L_AVOIR().getText());
				}
				paramAideCalcTVA.setEcranAppelant(this);
				paAideCalcTVAController.addRetourEcranListener(getThis());

				ParamAfficheSWT paramAfficheSWT = new ParamAfficheSWT();
				paramAfficheSWT.setHauteur(180);
				paramAfficheSWT.setLargeur(260);
				paramAfficheSWT.setTitre("Aide au calcul de la TVA.");
				LgrShellUtil.afficheAideSWT(paramAideCalcTVA, paramAfficheSWT, paAideCalcTVA,paAideCalcTVAController, s);
				if(paramAideCalcTVA.getFocusSWT()!=null)
					paramAideCalcTVA.getFocusSWT().forceFocus();
			} catch (Exception e1) {
				logger.error("Erreur : actionPerformed", e1);
			}
		}
		//	}
		//	);
	};

	protected class HandlerAideTva extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				actAideTva();
			} catch (Exception e) {
				logger.error("",e);
			}
			return event;
		}
	}
	protected class HandlerChangeTva extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				actChangeTva();
			} catch (Exception e) {
				logger.error("",e);
			}
			return event;
		}
	}
	
	protected void actChangeTva() throws Exception{
		if(!LibChaine.empty(((IHMLAvoir)selectedLigneAvoir.getValue()).getCodeArticle())
				&& masterEntity.getLigne(masterEntity.getLigneCourante())!=null) {
			InputDialog input = new InputDialog(vue.getShell()
					,"Code Tva","Indiquer le code tva que vous souhaitez affecter à la ligne.",
					((IHMLAvoir)selectedLigneAvoir.getValue()).getCodeTvaLDocument(),new IInputValidator() {

				@Override
				public String isValid(String newText) {
					// TODO Auto-generated method stub
					modifMode();
					TaTvaDAO daoTva = new TaTvaDAO(getEm());
					newText=newText.toUpperCase();
					try{
						if(daoTva.findByCode(newText)!=null)return null;
					}catch (Exception e) {
						logger.error("", e);
						return "Ce code n'est pas valide.";
					}
					return null;
				}
			});
			modifMode();
			if(masterDAO.dataSetEnModif()){
				input.open();
				if(input.getValue()!=null ){
					if(MessageDialog.openConfirm(vue.getShell()
							,"Modification du code Tva","Etes-vous sûr de vouloir modifier le code Tva de cette ligne ?")){
						String retour =input.getValue().toUpperCase();
						((IHMLAvoir)selectedLigneAvoir.getValue()).setCodeTvaLDocument(retour);
						if(input.getValue().equals("")){
							taLAvoir.setCodeTvaLDocument("");
							taLAvoir.setTauxTvaLDocument(BigDecimal.valueOf(0));
						}else{
							TaTvaDAO daoTva = new TaTvaDAO(getEm());
							taLAvoir.setCodeTvaLDocument(retour);
							taLAvoir.setTauxTvaLDocument(daoTva.findByCode(retour).getTauxTva());
						}
						masterEntity.calculTvaTotal();
					}
				}
			}
		}

	}

	@Override
	protected void actInserer() throws Exception {
		insertionLigne(false,true);
	}

	@Override
	protected void actModifier() throws Exception {
		try {
			boolean continuer=true;
			setIhmOldLAvoir();
//			if (LgrMess.isDOSSIER_EN_RESEAU()){
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
				continuer=masterDAO.dataSetEnModif();
//			}
			if(continuer){
				masterDAO.verifAutoriseModification(masterEntity);
				dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_EDITION);
				for (TaLAvoir p : masterEntity.getLignes()) {
					if(p.getNumLigneLDocument().equals(((IHMLAvoir) selectedLigneAvoir.getValue()).getNumLigneLDocument())) {
						taLAvoir = p;
						taLAvoir.setLegrain(true);
						ihmLAvoir= IHMLAvoir.copy((IHMLAvoir) selectedLigneAvoir.getValue());
					}				
				}
				initEtatBouton(false);
			}

		} catch (Exception e1) {
			//vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		}
	}

	@Override
	protected void actSupprimer() throws Exception {
		boolean verrouLocal=VerrouInterface.isVerrouille();
		VerrouInterface.setVerrouille(true);
		try {
			if (LgrMess.isDOSSIER_EN_RESEAU())masterDAO.verifAutoriseModification(masterEntity);

			try {
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
			} catch (Exception e) {
				logger.error("",e);
			}
			if(masterDAO.dataSetEnModif()){
				for (TaLAvoir p : masterEntity.getLignes()) {
					if(p.getNumLigneLDocument().equals(((IHMLAvoir) selectedLigneAvoir.getValue()).getNumLigneLDocument())) {
						taLAvoir = p;
					}				
				}			

				dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_SUPPRESSION);
				dao.supprimer(taLAvoir);
				taLAvoir.setTaDocument(null);
				masterEntity.removeLigne(taLAvoir);
				taLAvoir=null;
				actRefresh();
				refreshTotauxLignes();
				initEtatBouton();
				dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
			}
		} catch (ExceptLgr e1) {
			//vue.getLaMessage().setText(e1.getMessage());
			logger.error("Erreur : actionPerformed", e1);
		} finally {
			initEtatBouton();
			VerrouInterface.setVerrouille(verrouLocal);
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
		if((focusDansLigne()/*&& ibTaTable.dataSetEnModif()*/)||forceAnnuler){
			forceAnnuler=false;
			boolean repondu = true;
			boolean message =false; 
			boolean verrouLocal = VerrouInterface.isVerrouille();
			try {
				VerrouInterface.setVerrouille(true);
				//int lignesupprimee = masterEntity.getLigneCourante();
				switch (dao.getModeObjet().getMode()) {
				case C_MO_INSERTION:
					message =!taLAvoir.ligneEstVide();
					//message =!((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).ligneEstVide();
					if(message)repondu=MessageDialog.openQuestion(vue.getShell(), MessagesEcran
							.getString("Message.Attention"), MessagesEcran.getString("Ligne.Message.Annuler"));
					if(repondu){
						if(selectedLigneAvoir!=null && selectedLigneAvoir.getValue() != null
								//&& ((IHMLAvoir) selectedLigneAvoir.getValue()).getID_ARTICLE()!=null
						){
							//						modelLAvoir.getListeObjet().remove(((IHMLAvoir) selectedLigneAvoir.getValue()));
							//						writableList = new WritableList(realm, modelLAvoir.getListeObjet(), classModel);
							//						tableViewer.setInput(writableList);
							//						tableViewer.refresh();
							//						tableViewer.selectionGrille(0);
							masterEntity.removeLigne(taLAvoir);
							actRefresh();
						}
						//					dao.annuler(taLAvoir);
						//					masterEntity.removeLigne(lignesupprimee);
						//					initLigneCourantSurRow();
						//					ibTaTable.initLigneCourantSurRow();
						dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
						hideDecoratedFields();
					}
					initEtatBouton(focusDansLigne());
					break;
				case C_MO_EDITION:
					message =!taLAvoir.ligneEstVide();
					//message =!((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).ligneEstVide();
					if(message)repondu=MessageDialog.openQuestion(vue.getShell(), MessagesEcran
							.getString("Message.Attention"), MessagesEcran
							.getString("Ligne.Message.Annuler"));
					if(repondu){
						//					//int rang = getGrille().getSelectionIndex();
						//					int rang = modelLAvoir.getListeObjet().indexOf(selectedLigneAvoir.getValue());
						//					// selectedLigneAvoir.setValue(ihmOldLAvoir);
						//					modelLAvoir.getListeObjet().set(rang, ihmOldLAvoir);
						//					writableList = new WritableList(realm, modelLAvoir.getListeObjet(), classModel);
						//					tableViewer.setInput(writableList);
						//					tableViewer.refresh();
						//					tableViewer.setSelection(new StructuredSelection(ihmOldLAvoir), true);
						//					dao.annuler(taLAvoir);
						//					masterEntity.removeLigne(lignesupprimee);
						////					initLigneCourantSurRow();
						////					ibTaTable.initLigneCourantSurRow();
						//					hideDecoratedFields();
						int rang =((WritableList)tableViewer.getInput()).indexOf(selectedLigneAvoir.getValue());
						List<IHMLAvoir> l = IHMmodel();
						if(rang!=-1)
							l.set(rang, ihmOldLAvoir);

						writableList = new WritableList(realm, l, classModel);
						tableViewer.setInput(writableList);
						tableViewer.refresh();
						tableViewer.setSelection(new StructuredSelection(ihmOldLAvoir), true);
						mapperUIToModel.map(ihmOldLAvoir,taLAvoir);
//						ctrlTousLesChampsAvantEnregistrementSWT();//si on contrôle tous les champs
						//il réinitialise l'article entièrement

						dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
						hideDecoratedFields();
					}
					initEtatBouton(focusDansLigne());

					break;
				case C_MO_CONSULTATION:
					if(vue.getTfCODE_ARTICLE().isFocusControl())
						fireChangementDePage(new ChangementDePageEvent(this,ChangementDePageEvent.PRECEDENT));
					break;
				default:
					break;
				}
				// getFocusCourant().setInputVerifier(inputVerifier);
				// initEtatBouton();
			} finally {
				initEtatBouton(focusDansLigne());
				VerrouInterface.setVerrouille(verrouLocal);
			}
		}
	}

	@Override
	protected void actImprimer() throws Exception {
		// // TODO procedure d'impression
		// JOptionPane.showMessageDialog(vue, Const.C_A_IMPLEMENTER,
		// MessagesEcran.getString("Message.Attention"),
		// JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		initFocusSWT(dao, mapInitFocus);
	}

	@Override
	protected boolean aideDisponible() {
		boolean result = false;
		switch (dao.getModeObjet().getMode()) {
		case C_MO_CONSULTATION:
		case C_MO_EDITION:
		case C_MO_INSERTION:
			if (getFocusCourantSWT()==vue.getTfCODE_ARTICLE() 
					||getFocusCourantSWT()==vue.getTfLIB_L_AVOIR()
					|| getFocusCourantSWT()==vue.getTfU1_L_AVOIR() 
					|| getFocusCourantSWT()==vue.getTfU2_L_AVOIR() 
					|| getFocusCourantSWT()==vue.getTfPRIX_U_L_AVOIR()
					|| getFocusCourantSWT().equals(vue.getTfTitreTransportU1())
					)
				result = true;
			break;
		default:
			break;
		}

		return result;
	}

	@Override
	protected void actPrecedent() throws Exception {
		if(taLAvoir!=null && taLAvoir.ligneEstVide()){
			vue.getPaBtnAvecAssistant().getPaBtnAssitant().getBtnPrecedent().forceFocus();
			actAnnuler();			
		}
		else
			actEnregistrer(false);
		ChangementDePageEvent evt = new ChangementDePageEvent(this,ChangementDePageEvent.PRECEDENT);
		fireChangementDePage(evt);
	}

	@Override
	protected void actSuivant() throws Exception {
		if(taLAvoir!=null && taLAvoir.ligneEstVide()){
			vue.getPaBtnAvecAssistant().getPaBtnAssitant().getBtnSuivant().forceFocus();
			actAnnuler();			
		}
		else
			actEnregistrer(false);
		ChangementDePageEvent evt = new ChangementDePageEvent(this,ChangementDePageEvent.SUIVANT);
		fireChangementDePage(evt);
	}

	@Override
	protected void actAide() throws Exception {
		actAide(null);
	}

	//#AFAIRE
	@Override
	protected void actAide(String message) throws Exception {
		if(aideDisponible()) {		
			setActiveAide(true);
			boolean verrouLocal=VerrouInterface.isVerrouille();
			VerrouInterface.setVerrouille(true);
			try {
				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT));
				ParamAfficheAideRechercheSWT paramAfficheAideRecherche = new ParamAfficheAideRechercheSWT();
				//				paramAfficheAideRecherche.setDb(getThis().getIbTaTable().getFIBBase());
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
				((LgrEditorPart)e).setController(paAideController);
				((LgrEditorPart)e).setPanel(((EditorAide)e).getComposite());
				/***************************************************************/
				JPABaseControllerSWTStandard controllerEcranCreation = null;
				ParamAffiche parametreEcranCreation = null;
				IEditorPart editorCreation = null;
				String editorCreationId = null;
				IEditorInput editorInputCreation = null;
				Shell s2 = new Shell(s, LgrShellUtil.styleLgr);

				switch ((PaLigneAvoirController.this.dao.getModeObjet().getMode())) {
				case C_MO_CONSULTATION:

				case C_MO_EDITION:
				case C_MO_INSERTION:
					if (getFocusCourantSWT()==vue.getTfCODE_ARTICLE()){
						PaArticleSWT paArticleSWT = new PaArticleSWT(s2,SWT.NULL);
						SWTPaArticlesController swtPaArticlesController = new SWTPaArticlesController(paArticleSWT);
						paramAfficheAideRecherche.setForceAffichageAideRemplie(false);

						editorCreationId = ArticleMultiPageEditor.ID_EDITOR;
						editorInputCreation = new EditorInputArticle();

						ParamAfficheArticles paramAfficheArticles = new ParamAfficheArticles();
						paramAfficheAideRecherche.setJPQLQuery(new TaArticleDAO(getEm()).getJPQLQuery());
						paramAfficheArticles.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheArticles.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaArticlesController;
						parametreEcranCreation = paramAfficheArticles;

						paramAfficheAideRecherche.setTypeEntite(TaArticle.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_ARTICLE);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfCODE_ARTICLE().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTArticle,TaArticleDAO,TaArticle>(SWTArticle.class,getEm()));
						//paramAfficheAideRecherche.setModel(new ModelGeneral<SWTArticle>(swtPaArticlesController.getIbTaTable().getFIBQuery(),SWTArticle.class));
						paramAfficheAideRecherche.setTypeObjet(swtPaArticlesController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_ARTICLE);
					}	
					if (getFocusCourantSWT()==vue.getTfLIB_L_AVOIR()){
						PaArticleSWT paArticleSWT = new PaArticleSWT(s2,SWT.NULL);
						SWTPaArticlesController swtPaArticlesController = new SWTPaArticlesController(paArticleSWT);
						paramAfficheAideRecherche.setForceAffichageAideRemplie(false);

						editorCreationId = ArticleMultiPageEditor.ID_EDITOR;
						editorInputCreation = new EditorInputArticle();

						ParamAfficheArticles paramAfficheArticles = new ParamAfficheArticles();
						paramAfficheAideRecherche.setJPQLQuery(new TaArticleDAO(getEm()).getJPQLQuery());
						paramAfficheArticles.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheArticles.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaArticlesController;
						parametreEcranCreation = paramAfficheArticles;

						paramAfficheAideRecherche.setTypeEntite(TaArticle.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_LIBELLEC_ARTICLE);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfLIB_L_AVOIR().getText());
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTArticle,TaArticleDAO,TaArticle>(new TaArticleDAO(getEm()).findByActif(true),SWTArticle.class,getEm()));
						//paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTArticle,TaArticleDAO,TaArticle>(SWTArticle.class,getEm()));
						//paramAfficheAideRecherche.setModel(new ModelGeneral<SWTArticle>(swtPaArticlesController.getIbTaTable().getFIBQuery(),SWTArticle.class));
						paramAfficheAideRecherche.setTypeObjet(swtPaArticlesController.getClassModel());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_ARTICLE);
					}					
					if (getFocusCourantSWT()==vue.getTfU1_L_AVOIR()||
							getFocusCourantSWT()==vue.getTfU2_L_AVOIR()){
						PaUniteSWT paUniteSWT = new PaUniteSWT(s2,SWT.NULL);
						SWTPaUniteController swtPaUniteController = new SWTPaUniteController(paUniteSWT);

						editorCreationId = EditorUnite.ID;
						editorInputCreation = new EditorInputUnite();

						ParamAfficheUnite paramAfficheUnite = new ParamAfficheUnite();
						paramAfficheUnite.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheUnite.setEcranAppelant(paAideController);
						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);
						
						paramAfficheAideRecherche.setJPQLQuery(swtPaUniteController.getDao().getJPQLQuery());
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_UNITE);

						paramAfficheAideRecherche.setTypeEntite(TaUnite.class);
						if(getFocusCourantSWT()==vue.getTfU1_L_AVOIR())
							paramAfficheAideRecherche.setDebutRecherche(vue.getTfU1_L_AVOIR().getText());
						else
							paramAfficheAideRecherche.setDebutRecherche(vue.getTfU2_L_AVOIR().getText());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_UNITE);
						controllerEcranCreation = swtPaUniteController;
						parametreEcranCreation = paramAfficheUnite;
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(new TaUniteDAO(getEm()).modelObjetUniteArticle(taLAvoir.getTaArticle().getCodeArticle()));
						paramAfficheAideRecherche.setTypeObjet(swtPaUniteController.getClassModel());
					}					
					if (getFocusCourantSWT()==vue.getTfPRIX_U_L_AVOIR()){
						PaPrixSWT paPrixSWT = new PaPrixSWT(s2,SWT.NULL);
						SWTPaPrixController swtPaPrixController = new SWTPaPrixController(paPrixSWT);

						editorCreationId = EditorPrix.ID;
						editorInputCreation = new EditorInputPrix();
						paramAfficheAideRecherche.setForceAffichageAideRemplie(true);

						paramAfficheAideRecherche.setAfficheDetail(false);

						ParamAffichePrix paramAffichePrix = new ParamAffichePrix();
						paramAffichePrix.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAffichePrix.setEcranAppelant(paAideController);
						paramAffichePrix.setIdArticle(LibConversion.integerToString(getSelectedLigneAvoir().getIdArticle()));
						paramAfficheAideRecherche.setJPQLQuery(swtPaPrixController.getDao().getJPQLQuery());
						if(masterEntity.getTtc()==1)
							paramAfficheAideRecherche.setChampsRecherche(Const.C_PRIXTTC_PRIX);
						else
							paramAfficheAideRecherche.setChampsRecherche(Const.C_PRIX_PRIX);

						paramAfficheAideRecherche.setTypeEntite(TaPrix.class);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfPRIX_U_L_AVOIR().getText());
						paramAfficheAideRecherche.setChampsIdentifiant(Const.C_ID_PRIX);
						controllerEcranCreation = swtPaPrixController;
						parametreEcranCreation = paramAffichePrix;
						paramAfficheAideRecherche.setControllerAppelant(getThis());
						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTPrix,TaPrixDAO,TaPrix>(taLAvoir.getTaArticle().getTaPrixes(),SWTPrix.class,getEm()));
						paramAfficheAideRecherche.setTypeObjet(swtPaPrixController.getClassModel());
					}
					if(getFocusCourantSWT().equals(vue.getTfTitreTransportU1())){
						PaTitreTransport paTitreTransport = new PaTitreTransport(s2,SWT.NULL);
						SWTPaTitreTransport swtPaTitreTransport = new SWTPaTitreTransport(paTitreTransport);

						editorCreationId = EditorTitreTransport.ID;
						editorInputCreation = new EditorInputTitreTransport();

						ParamAfficheTitreTansport paramAfficheTitreTansport = new ParamAfficheTitreTansport();
						paramAfficheAideRecherche.setJPQLQuery(new TaTitreTransportDAO(getEm()).getJPQLQuery());
						paramAfficheTitreTansport.setModeEcran(EnumModeObjet.C_MO_INSERTION);
						paramAfficheTitreTansport.setEcranAppelant(paAideController);
						controllerEcranCreation = swtPaTitreTransport;
						parametreEcranCreation = paramAfficheTitreTansport;

						paramAfficheAideRecherche.setTypeEntite(TaTitreTransport.class);
						paramAfficheAideRecherche.setChampsRecherche(Const.C_CODE_TITRE_TRANSPORT);
						paramAfficheAideRecherche.setDebutRecherche(vue.getTfTitreTransportU1().getText());
						paramAfficheAideRecherche.setControllerAppelant(PaLigneAvoirController.this);
//						paramAfficheAideRecherche.setModel(new TaTitreTransportDAO(getEm()).modelObjetUniteArticle(taArticle.getCodeArticle()));
						paramAfficheAideRecherche.setModel(new ModelGeneralObjet<SWTTitreTransport,TaTitreTransportDAO,TaTitreTransport>(SWTTitreTransport.class,dao.getEntityManager()));
						paramAfficheAideRecherche.setTypeObjet(swtPaTitreTransport.getClassModel());

						paramAfficheAideRecherche.setChampsIdentifiant(swtPaTitreTransport.getDao().getChampIdTable());
					}
					break;
				default:
					break;
				}

				if (paramAfficheAideRecherche.getJPQLQuery() != null) {

					PaAideRechercheSWT paAideRecherche1 = new PaAideRechercheSWT(s, SWT.NULL);
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

					dbc.getValidationStatusMap().removeMapChangeListener(changeListener);
					//LgrShellUtil.afficheAideSWT(paramAfficheAide, null, paAide,paAideController, s);
					/*****************************************************************/
					paAideController.configPanel(paramAfficheAide);
					/*****************************************************************/	
					dbc.getValidationStatusMap().addMapChangeListener(changeListener);

					// je rends enable false tous les boutons avant de passer dans l'écran d'aide
					// pour ne pas que les actions de l'écran des factures interfèrent ceux de l'écran d'aide
					//activeWorkenchPartCommands(false);
					//if(parentEcran!=null) ((PaEditorAvoirController)parentEcran).activeWorkenchPartCommands(false);
				}

			} finally {
				VerrouInterface.setVerrouille(verrouLocal);
				vue.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
			}
		}
	}

	public IStatus validateUI() throws Exception {
		if ((dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) == 0)
				|| (dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) == 0)) {
			ctrlTousLesChampsAvantEnregistrementSWT();
		}
		return null;
	}

	public IStatus validateUIField(String nomChamp,Object value) {
		String validationContext = "L_AVOIR";
		try {
			IStatus s = null;
			boolean change=true;
			if(nomChamp.equals(Const.C_CODE_ARTICLE)) {
				if(value=="")return new org.eclipse.core.runtime.Status(org.eclipse.core.runtime.Status.OK,gestComBdPlugin.PLUGIN_ID,"OK"); 
				TaArticleDAO dao = new TaArticleDAO(getEm());

				dao.setModeObjet(getMasterDAO().getModeObjet());
				TaArticle f = new TaArticle();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				s = dao.validate(f,nomChamp,validationContext);

				if(s.getSeverity()!=IStatus.ERROR) {
					
					f = dao.findByCode((String)value);
				if(ihmLAvoir!=null)
					change = !f.getCodeArticle().equals(ihmLAvoir.getCodeArticle());
				if(change){
					taLAvoir.setTaArticle(f);
					taLAvoir.initQte2(f);
					mapperModelToUI.map(taLAvoir, ihmLAvoir);
				}
				}
				dao = null;
			} else if(nomChamp.equals(Const.C_CODE_TITRE_TRANSPORT)) {
				TaTitreTransportDAO dao = new TaTitreTransportDAO(getEm());
				
				dao.setModeObjet(getDao().getModeObjet());
				TaTitreTransport f = new TaTitreTransport();
				PropertyUtils.setSimpleProperty(f, nomChamp, value);
				s = dao.validate(f,nomChamp,validationContext);

				if(s.getSeverity()!=IStatus.ERROR) {
					// on verifie sur la table titre de transport mais on affecte uniquement la valeur et non l'objet
					PropertyUtils.setSimpleProperty(taLAvoir, nomChamp, value);
				}
				dao = null;
			} else {
				TaLAvoir u = new TaLAvoir();
				u.setTaDocument(masterEntity);
				if(nomChamp.equals(Const.C_NUM_LIGNE_L_DOCUMENT) && value instanceof String) {
					value = LibConversion.stringToInteger(value.toString());
				}
				PropertyUtils.setSimpleProperty(u, nomChamp, value);
				s = dao.validate(u,nomChamp,validationContext);
				
				if(nomChamp.equals(Const.C_QTE_L_DOCUMENT)){
					if (ihmLAvoir!=null)
						change =u.getQteLDocument()!=null&& !u.getQteLDocument().equals(ihmLAvoir.getQteLDocument());
				}				
				if(s.getSeverity()!=IStatus.ERROR) {
					PropertyUtils.setSimpleProperty(taLAvoir, nomChamp, value);
				}
			}

			if(s.getSeverity()!=IStatus.ERROR) {
				if(nomChamp.equals(Const.C_QTE_L_DOCUMENT)&& change) {
					if(taLAvoir.getTaArticle()!=null){
						taLAvoir.initQte2(taLAvoir.getTaArticle());
					}
				}				
				mapperModelToUI.map(taLAvoir, ((IHMLAvoir)selectedLigneAvoir.getValue()));
			}
			//			new Status(Status.OK,gestComBdPlugin.PLUGIN_ID,"OK");
			return s;
		} catch (IllegalAccessException e) {
			logger.error("",e);
		} catch (InvocationTargetException e) {
			logger.error("",e);
		} catch (NoSuchMethodException e) {
			logger.error("",e);
		}
		return null;
	}

	@Override
	protected void actEnregistrer() throws Exception {
		actEnregistrer(true);
	}

	protected void actEnregistrer(boolean insertion) throws Exception {
		try {
			if (dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) == 0
					|| dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) == 0) {

				if(insertion)
					insertion=dao.getModeObjet().getMode()==ModeObjet.EnumModeObjet.C_MO_INSERTION;
				//				ctrlUnChampsSWT(getFocusCourantSWT());
				//				ibTaTable.setChamp_Model_Obj(((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).setTaLAvoir(((IHMLAvoir)selectedLigneAvoir.getValue())));
				//				if ((ibTaTable.getFModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) == 0)
				//				|| (ibTaTable.getFModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) == 0)) {

				//				ctrlTousLesChampsAvantEnregistrementSWT();
				////				ibTaTable.remplissageLigneSurObjetQuery(facture.getLigneCourante());
				//				ibTaTable.enregistrement(((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())));

				//				ibTaTable.getFIBQuery().refresh();
				//				DataRow courant = new DataRow(ibTaTable.getFIBQuery(),ibTaTable.champIdTable);
				//				courant.setInt(ibTaTable.champIdTable, LibConversion.stringToInteger(ibTaTable.valeurIdTable));
				//				ibTaTable.getFIBQuery().locate(courant, Const.C_LOCATE_OPTION_BORLAND_FIRST);
				//				((IHMLAvoir) selectedLigneAvoir.getValue()).setIdLFacture(LibConversion.stringToInteger(ibTaTable.valeurIdTable));
				//				courant = new DataRow(ibTaTable.getFIBQuery());
				//				ibTaTable.getFIBQuery().getDataRow(courant);
				//				((IHMLAvoir) selectedLigneAvoir.getValue()).setIHMLAvoir(modelLAvoir.remplirObjet(courant));

				//				select.widgetSelected(null);
				//				try {
				//				ibTaTable.setChamp_Model_Obj(((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())));
				//				} catch (Exception e1) {
				//				logger.error("",e1);
				//				}

				//				}
				//				actRefresh();
				//				if(focusDansLigne()&& insertion)insertionLigne(true,true);//ajout d'une ligne à la suite des autres

				ctrlUnChampsSWT(getFocusCourantSWT());
				ctrlTousLesChampsAvantEnregistrementSWT();
				//				EntityTransaction transaction = dao.getEntityManager().getTransaction();
				//				dao.begin(transaction);
				if ((dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) == 0)) {
					LgrDozerMapper<IHMLAvoir,TaLAvoir> mapper = new LgrDozerMapper<IHMLAvoir,TaLAvoir>();
					mapper.map((IHMLAvoir) selectedLigneAvoir.getValue(),taLAvoir);

				} else if ((dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) == 0)) {
					LgrDozerMapper<IHMLAvoir,TaLAvoir> mapper = new LgrDozerMapper<IHMLAvoir,TaLAvoir>();
					mapper.map((IHMLAvoir) selectedLigneAvoir.getValue(),taLAvoir);
					//					taLAvoir.setTaFacture(masterEntity);
					//					masterEntity.addLigne(taLAvoir);
				}

				//				try {
				//				sortieChamps();
				//				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_ENREGISTRER_ID);
				//				fireDeclencheCommandeController(e);
				//				} catch (Exception e) {
				//				logger.error("",e);
				//				}		
				//				dao.commit(transaction);
				//				actRefresh();
				//				transaction = null;
				masterEntity.enregistreLigne(taLAvoir);
				dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
				if(focusDansLigne()&& insertion)insertionLigne(true,true);//ajout d'une ligne à la suite des autres
			}
		} finally {
			initEtatBouton(IHMmodel());
			VerrouInterface.setVerrouille(false);
		}
	}

	//	public SWT_IB_TA_L_FACTURE getIbTaTable() {
	//		return ibTaTable;
	//	}

	public void initEtatComposant(String type) throws SQLException{		
		initEtatComposant();
		//		System.out.println("initEtatComposant : type ligne "+type);
		vue.getTfQTE_L_AVOIR().setEditable(!type.equals(Const.C_CODE_T_LIGNE_C));
		vue.getTfQTE2_L_AVOIR().setEditable(!type.equals(Const.C_CODE_T_LIGNE_C));
		vue.getTfU1_L_AVOIR().setEditable(!type.equals(Const.C_CODE_T_LIGNE_C));
		vue.getTfU2_L_AVOIR().setEditable(!type.equals(Const.C_CODE_T_LIGNE_C));
		vue.getTfPRIX_U_L_AVOIR().setEditable(!type.equals(Const.C_CODE_T_LIGNE_C));
		vue.getTfREM_TX_L_AVOIR().setEditable(!type.equals(Const.C_CODE_T_LIGNE_C));
	}

	public void initEtatComposant() {
		try {
			vue.getTfNUM_LIGNE_L_AVOIR().setEnabled(false);
			vue.getTfMT_HT_L_AVOIR().setEditable(false);
			vue.getTfMT_TTC_L_AVOIR().setEditable(false);
			//			if (ibTaTable.getFIBQuery().isOpen()) {
			//				vue.getTfCODE_ARTICLE().setEditable(
			//						ibTaTable.recordModifiable(ibTaTable.nomTable, ibTaTable
			//								.getChamp_Obj(ibTaTable.champIdTable)));
			//			}
			vue.getTfCODE_ARTICLE().setEditable(!isUtilise());
			changeCouleur(vue);
			boolean editable =true;
			//			if(((TaLAvoir)swtFacture.getLigne(swtFacture.getLigneCourante())).ligneEstVide()) {
			//				if(!ibTaTable.dataSetEnModif())editable=false;
			//			}
			vue.getTfCODE_ARTICLE().setEditable(editable);
			vue.getTfLIB_L_AVOIR().setEditable(editable);
			vue.getTfPRIX_U_L_AVOIR().setEditable(editable);
			vue.getTfQTE_L_AVOIR().setEditable(editable);
			vue.getTfQTE2_L_AVOIR().setEditable(editable);
			vue.getTfU1_L_AVOIR().setEditable(editable);
			vue.getTfU2_L_AVOIR().setEditable(editable);
			vue.getTfREM_TX_L_AVOIR().setEditable(editable);
			vue.getTfMT_HT_L_AVOIR().setEditable(false);
			vue.getTfMT_TTC_L_AVOIR().setEditable(false);

		} catch (Exception e) {
			//vue.getLaMessage().setText(e.getMessage());
		}
	}

	public boolean isUtilise(){
		return ((IHMLAvoir)selectedLigneAvoir.getValue()).getIdLDocument()!=null && 
		!dao.recordModifiable(dao.getNomTable(),
				((IHMLAvoir)selectedLigneAvoir.getValue()).getIdLDocument());		
	}

	public IHMLAvoir getIhmOldLAvoir() {
		return ihmOldLAvoir;
	}

	public void setIhmOldLAvoir(IHMLAvoir swtOldArticle) {
		this.ihmOldLAvoir = swtOldArticle;
	}

	public void setIhmOldLAvoir() {
		if (selectedLigneAvoir!=null && selectedLigneAvoir.getValue() != null)
			this.ihmOldLAvoir = IHMLAvoir.copy((IHMLAvoir) selectedLigneAvoir.getValue());
		else {
			if(tableViewer!=null && tableViewer.selectionGrille(0)){
				this.ihmOldLAvoir = IHMLAvoir.copy((IHMLAvoir) selectedLigneAvoir.getValue());
				tableViewer.setSelection(new StructuredSelection(
						(IHMLAvoir) selectedLigneAvoir.getValue()), true);
			}}
	}

	public void setVue(final PaSaisieLignesAvoir vue) {
		super.setVue(vue);
		this.vue = vue;
	}

	@Override
	protected void initMapComposantDecoratedField() {
		if (mapComposantDecoratedField == null)
			mapComposantDecoratedField = new LinkedHashMap<Control, DecoratedField>();

		mapComposantDecoratedField.put(vue.getTfCODE_ARTICLE(), vue.getFieldCODE_ARTICLE());
		mapComposantDecoratedField.put(vue.getTfLIB_L_AVOIR(), vue.getFieldLIB_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfMT_HT_L_AVOIR(), vue.getFieldMT_HT_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfMT_TTC_L_AVOIR(), vue.getFieldMT_TTC_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfNUM_LIGNE_L_AVOIR(), vue.getFieldNUM_LIGNE_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfPRIX_U_L_AVOIR(), vue.getFieldPRIX_U_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfQTE_L_AVOIR(), vue.getFieldQTE_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfQTE2_L_AVOIR(), vue.getFieldQTE2_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfREM_TX_L_AVOIR(), vue.getFieldREM_TX_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfU1_L_AVOIR(), vue.getFieldU1_L_AVOIR());
		mapComposantDecoratedField.put(vue.getTfU2_L_AVOIR(), vue.getFieldU2_L_AVOIR());
	}

	public Class getClassModel() {
		return classModel;
	}

	@Override
	protected void sortieChamps() {
		try {
			if (masterEntity.getLigne(masterEntity.getLigneCourante())!=null && 
					( (TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).getTaTLigne().getIdTLigne()!=0)
				initEtatComposant(( (TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).getTaTLigne().getCodeTLigne());

			//vide les lignes
			//			annulerListeContext();

			//vide l'entête à partir de la liste des context de l'entête
			//			getParentEcran().annulerListeContext();

			//active les lignes
			//			activationContext();

		} catch (SQLException e) {
			logger.debug("",e);
		} catch (Exception e) {
			logger.debug("",e);
		}
	}

	@Override
	protected void actRefresh() throws Exception {	
		//		int row = vue.getGrille().getSelectionIndex();
		writableList = new WritableList(realm, IHMmodel(), classModel);
		tableViewer.setInput(writableList);
		tableViewer.refresh();
		tableViewer.selectionGrille(
				tableViewer.selectionGrille(selectedLigneAvoir.getValue()));
		//		tableViewer.selectionGrille(row);	
	}

	public void initTTC() {
		boolean ttc = false;
		if(masterEntity!=null && masterEntity.getTtc()==1) {
			ttc = true;
		} 
		//		if(((SWTEnteteFacture)masterEntity.getEntete()).getTTC()!=null){
		//			vue.getTfMT_HT_L_FACTURE().setEnabled(!((SWTEnteteFacture)masterEntity.getEntete()).getTTC());
		//			vue.getTfMT_TTC_L_FACTURE().setEnabled(((SWTEnteteFacture)masterEntity.getEntete()).getTTC());
		//			vue.getLaMT_HT_L_FACTURE().setEnabled(!((SWTEnteteFacture)masterEntity.getEntete()).getTTC());
		//			vue.getLaMT_TTC_L_FACTURE().setEnabled(((SWTEnteteFacture)masterEntity.getEntete()).getTTC());
		//		}
		vue.getTfMT_HT_L_AVOIR().setEnabled(!ttc);
		vue.getTfMT_TTC_L_AVOIR().setEnabled(ttc);
		vue.getLaMT_HT_L_AVOIR().setEnabled(!ttc);
		vue.getLaMT_TTC_L_AVOIR().setEnabled(ttc);

		//System.out.println("PaLigneAvoirController.initTTC() = "+((SWTEnteteFacture)swtFacture.getEntete()).getTTC());
	}

	public void modificationDocument(SWTModificationDocumentEvent evt) {
		if(dao.getModeObjet().getMode()!=ModeObjet.EnumModeObjet.C_MO_EDITION
				&& dao.getModeObjet().getMode()!=ModeObjet.EnumModeObjet.C_MO_INSERTION) {
			try {
				actRefresh();
			} catch (Exception e) {
				logger.error("",e);
			}
		} //else { //edition ou insertion
		refreshTotauxLignes();//doit se produire aussi quand il y a une modification de la 
		//remise globale dans l'écran des totaux et dans ce cas, l'état est en "consultation"
		//}
	}

	/**
	 * Mise a jour des totaux a l'ecran, en fonction du contenu de l'entite principale.
	 * Cette fonction ne declanche aucun calcul, elle utilise les valeurs telles qu'elles sont.
	 */
	private void refreshTotauxLignes() {
		if(masterEntity!=null && ihmTotauxLignesAvoir!=null) {
			mapperModelToUITotauxLignes.map(masterEntity,ihmTotauxLignesAvoir);
		}
	}

	private ListenerList selectionChangedListeners = new ListenerList(3);

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
		return null;
	}

	protected void fireSelectionChanged(final SelectionChangedEvent event) {
		Object[] listeners = selectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					l.selectionChanged(event);
				}
			});
		}
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	protected void updateSelection(ISelection selection) {
		SelectionChangedEvent event = new SelectionChangedEvent(this, selection);
		fireSelectionChanged(event);
	}

	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub
	}

	public TaAvoir getTaAvoir() {
		return masterEntity;
	}

	public void setTaAvoir(TaAvoir masterEntity) {
		this.masterEntity = masterEntity;
	}

	//	public int recupLignes(SWTFacture swtFacture) {
	//		int nbLignes = 0;
	//		try {
	//			
	//			setTaFacture(swtFacture);
	//			ibTaTable.setFacture(swtFacture);
	//			ibTaTable.videTableTemp();
	//			swtFacture.getLignes().clear();
	//			swtFacture.getLignesTVA().clear();
	//		
	//			ibTaTable.recupLignesFacture(swtFacture.getEntete().getCODE());
	//			initLigneCourantSurRow();
	//			ibTaTable.initLigneCourantSurRow();
	//			writableList = new WritableList(realm, modelLAvoir.remplirListe(), classModel);
	//			tableViewer.setInput(writableList);
	//			nbLignes = modelLAvoir.getListeObjet().size();
	//
	//			for (IHMLAvoir ihmLigne : modelLAvoir.getListeObjet()) {
	//				TaLAvoir swtLigne = new TaLAvoir((IHMLAvoir)selectedLigneAvoir.getValue());
	//				swtLigne.setTaLAvoir(ihmLigne);
	//				swtFacture.addLigne(swtLigne);
	//			}
	//			tableViewer.selectionGrille(0);
	//			initEtatBouton(false);
	//			return nbLignes;
	//		} catch(Exception e) {
	//			logger.error("",e);
	//			return nbLignes;
	//		}
	//	}

	//	public WorkbenchPart getWorkbenchPart() {
	//		return workbenchPart;
	//	}
	//
	//	public void setWorkbenchPart(WorkbenchPart workbenchPart) {
	//		this.workbenchPart = workbenchPart;
	//	}

	protected class ActionInsererLigne extends ActionInserer {
		public ActionInsererLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_INSERER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionAjouterLigne extends ActionInserer {
		public ActionAjouterLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_AJOUT_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionAideCalcTVA extends Action {
		public ActionAideCalcTVA(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_AIDETVA_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionEnregistrerLigne extends ActionEnregistrer {
		public ActionEnregistrerLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_ENREGISTRER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionEnregistrerFacture extends ActionEnregistrer {
		public ActionEnregistrerFacture(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_ENREGISTRER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionAnnulerLigne extends ActionAnnuler {
		public ActionAnnulerLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				actAnnuler();
			} catch (Exception e) {
				logger.error("",e);
			}
			//handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_ANNULER_ID, null);
			//			} catch (ExecutionException e) {
			//				logger.error("",e);
			//			} catch (NotDefinedException e) {
			//				logger.error("",e);
			//			} catch (NotEnabledException e) {
			//				logger.error("",e);
			//			} catch (NotHandledException e) {
			//				logger.error("",e);
			//			}
		}
	}

	protected class ActionSupprimerLigne extends ActionSupprimer {
		public ActionSupprimerLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_SUPPRIMER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionModifierLigne extends ActionModifier {
		public ActionModifierLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_MODIFIER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionFermerLigne extends ActionFermer {
		public ActionFermerLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_FERMER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionImprimerLigne extends ActionImprimer {
		public ActionImprimerLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_IMPRIMER_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	protected class ActionAideLigne extends ActionAide {
		public ActionAideLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_AIDE_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}


	protected class ActionRefreshLigne extends ActionRefresh {
		public ActionRefreshLigne(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				handlerService.executeCommand(C_COMMAND_LIGNE_FACTURE_REFRESH_ID, null);
			} catch (ExecutionException e) {
				logger.error("",e);
			} catch (NotDefinedException e) {
				logger.error("",e);
			} catch (NotEnabledException e) {
				logger.error("",e);
			} catch (NotHandledException e) {
				logger.error("",e);
			}
		}
	}

	private class SelectionGrille extends SelectionAdapter {
		public void widgetSelected(SelectionEvent e) {
			setIhmOldLAvoir();
			if(selectedLigneAvoir.getValue()!=null && masterEntity.getLignes().size()>vue.getGrille().getSelectionIndex()){
				//				((TaLAvoir)masterEntity.getLigne(vue.getGrille().getSelectionIndex())).setIhmLFacture(((IHMLAvoir)selectedLigneAvoir.getValue()));
				//				((TaLAvoir)masterEntity.getLigne(vue.getGrille().getSelectionIndex())).setTaLAvoir(((IHMLAvoir)selectedLigneAvoir.getValue()));
				//				mapperUIToModel.map((IHMLAvoir)selectedLigneAvoir.getValue(), taLAvoir);
				for (TaLAvoir p : masterEntity.getLignes()) {
					if(p.getNumLigneLDocument()==((IHMLAvoir) selectedLigneAvoir.getValue()).getNumLigneLDocument()) {
						taLAvoir = p;
					}				
				}
			}
		}
	}

	public IHMLAvoir getSelectedLigneAvoir() {
		IHMLAvoir resultat = null;
		if(selectedLigneAvoir!=null && selectedLigneAvoir.getValue()!=null)
			resultat = (IHMLAvoir)selectedLigneAvoir.getValue();
		return resultat;
	};	

	public void initLigneCourantSurRow(){
		masterEntity.setLigneCourante(vue.getGrille().getSelectionIndex());
	}


	protected void actAjouter() throws Exception{
		insertionLigne(true,true);
	}

	protected class HandlerAjouter extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				actAjouter();
			} catch (Exception e) {
				logger.error("",e);
			}
			return event;
		}
	}

	protected void insertionLigne(Boolean ajout, boolean initFocus) throws Exception{
		boolean verrouLocal = VerrouInterface.isVerrouille();
		try {
			VerrouInterface.setVerrouille(true);
			if (LgrMess.isDOSSIER_EN_RESEAU())masterDAO.verifAutoriseModification(masterEntity);
			try {
				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				fireDeclencheCommandeController(e);
			} catch (Exception e) {
				logger.error("",e);
			}		
			if(masterDAO.dataSetEnModif()){
				setIhmOldLAvoir();
				ihmLAvoir = new IHMLAvoir();
				dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_INSERTION);
				taLAvoir = new TaLAvoir(true); 
				taLAvoir.setTaTLigne(new TaTLigneDAO(getEm()).findByCode(Const.C_CODE_T_LIGNE_C));

				List<IHMLAvoir> modelListeLigneAvoir = IHMmodel();

				if(ajout)
					masterEntity.addLigne(taLAvoir);
				else{
					if(vue.getGrille().getItemCount()!=0) 
						masterEntity.insertLigne(taLAvoir,vue.getGrille().getSelectionIndex());
					else
						masterEntity.insertLigne(taLAvoir,0);

				}
				ihmLAvoir.setNumLigneLDocument(taLAvoir.getNumLigneLDocument());
				taLAvoir.setTaDocument(masterEntity);
				taLAvoir.initialiseLigne(true);

				modelListeLigneAvoir.add(ihmLAvoir.getNumLigneLDocument(),ihmLAvoir);

				//			taLAvoir.setTaLAvoir(ihmLAvoir);
				//			ibTaTable.insertion(null,true);
				//			modelLAvoir.getListeObjet().add(ihmLAvoir);
				writableList = new WritableList(realm, modelListeLigneAvoir, classModel);
				tableViewer.setInput(writableList);
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(ihmLAvoir),true);
				initEtatComposant(taLAvoir.getTaTLigne().getCodeTLigne());
				//initLigneCourantSurRow();
				//			ibTaTable.initLigneCourantSurRow();

				//			masterEntity.insertLigne(taLAvoir,masterEntity.getLigneCourante());
				//			masterEntity.getLigne(masterEntity.getLigneCourante()).setModeLigne(EnumModeObjet.C_MO_INSERTION);

				//			try{
				//				ibTaTable.insertion(ligneFacture,true);
				//			}catch (Exception ex1){
				//				masterEntity.removeLigne(masterEntity.getLigneCourante());
				//				throw new Exception();
				//			}
				taLAvoir.setNumLigneLDocument(ihmLAvoir.getNumLigneLDocument());
				//			ihmLAvoir.setNumLigneLFacture(taLAvoir.getNumLigneLFacture());
				//actualiserForm();
				initEtatBouton(initFocus);
				//initEtatBouton(initFocus,true);

				//			try {
				//				DeclencheCommandeControllerEvent e = new DeclencheCommandeControllerEvent(this,C_COMMAND_GLOBAL_MODIFIER_ID);
				//				fireDeclencheCommandeController(e);
				//			} catch (Exception e) {
				//				logger.error("",e);
				//			}		
			}
		} finally {
			initEtatComposant();
			VerrouInterface.setVerrouille(verrouLocal);
		}

	}

	/**
	 * Initialisation des boutons suivant l'ï¿½tat de l'objet "ibTaTable"
	 * @param initFocus - si vrai initialise le focus en fonction du mode
	 *///,boolean etatFacture
	protected void initEtatBouton(boolean initFocus) {
		//super.initEtatBouton(initFocus);
		List l = IHMmodel();
		super.initEtatBoutonCommand(initFocus,l);
		enableActionAndHandler(C_COMMAND_GLOBAL_PRECEDENT_ID,true);
		enableActionAndHandler(C_COMMAND_GLOBAL_SUIVANT_ID,true);
		enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_CHANGETVA_ID,true);
		boolean trouve = false;
		boolean etatAvoir = true;
		if(masterEntity!=null) {
			trouve = l.size()>0;
		} else {
			//trouve = dao.selectAll().size()>0;
			trouve = false;
		}
		switch (daoStandard.getModeObjet().getMode()) {
		case C_MO_INSERTION:
			//			enableActionAndHandler(actionInserer,handlerInserer,false);
			//			enableActionAndHandler(actionModifier,handlerModifier,false);				
			//			enableActionAndHandler(actionAjouter,handlerAjouter,false);
			//			//enableActionAndHandler(actionAideTva,handlerAideTva,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_IMPRIMER_ID,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_ENREGISTRER_ID,true);
			enableActionAndHandler(C_COMMAND_GLOBAL_INSERER_ID,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_MODIFIER_ID,false);
			//enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_AJOUT_ID,false);
			//enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_AIDETVA_ID,false);
			break;
		case C_MO_EDITION:
			//			enableActionAndHandler(actionInserer,handlerInserer,false);
			//			enableActionAndHandler(actionModifier,handlerModifier,false);			
			//			enableActionAndHandler(actionAjouter,handlerAjouter,false);				
			//			//enableActionAndHandler(actionAideTva,handlerAideTva,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_IMPRIMER_ID,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_INSERER_ID,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_MODIFIER_ID,false);
			//enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_AJOUT_ID,false);
			//enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_AIDETVA_ID,false);
			break;
		case C_MO_CONSULTATION:
			//			enableActionAndHandler(actionInserer,handlerInserer,etatFacture);
			//			enableActionAndHandler(actionModifier,handlerModifier,(trouve && etatFacture));				
			//			enableActionAndHandler(actionAjouter,handlerAjouter,etatFacture);
			//			//enableActionAndHandler(actionAideTva,handlerAideTva,trouve);
			enableActionAndHandler(C_COMMAND_GLOBAL_IMPRIMER_ID,false);
			enableActionAndHandler(C_COMMAND_GLOBAL_INSERER_ID,etatAvoir);
			enableActionAndHandler(C_COMMAND_GLOBAL_MODIFIER_ID,(trouve && etatAvoir));
			//enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_AJOUT_ID,etatFacture);
			//enableActionAndHandler(C_COMMAND_LIGNE_FACTURE_AIDETVA_ID,trouve);
			break;
		default:
			break;
		}

		boolean editable =true;
		//		if(swtFacture.getLigne(swtFacture.getLigneCourante())==null ||((TaLAvoir)swtFacture.getLigne(swtFacture.getLigneCourante())).ligneEstVide()) {
		//		if(!ibTaTable.dataSetEnModif())editable=false;
		//		}
		vue.getTfCODE_ARTICLE().setEditable(editable);
		vue.getTfLIB_L_AVOIR().setEditable(editable);
		vue.getTfPRIX_U_L_AVOIR().setEditable(editable);
		vue.getTfQTE_L_AVOIR().setEditable(editable);
		vue.getTfQTE2_L_AVOIR().setEditable(editable);
		vue.getTfU1_L_AVOIR().setEditable(editable);
		vue.getTfU2_L_AVOIR().setEditable(editable);
		vue.getTfREM_TX_L_AVOIR().setEditable(editable);
		vue.getTfMT_HT_L_AVOIR().setEditable(editable);
		vue.getTfMT_TTC_L_AVOIR().setEditable(editable);
		if (initFocus)initFocusSWT(dao, mapInitFocus);
	}	

//	public String[][] initAdapterArticle() {
//		String[][] valeurs = null;
//		boolean affichageCtrlEspace = GestionCommercialePlugin.getDefault().getPreferenceStore().getBoolean(fr.legrain.gestionCommerciale.preferences.PreferenceConstants.AFFICHAGE_CTRL_ESPACE);
//		if(affichageCtrlEspace){
//		if(listeArticles==null){
//			TaArticleDAO taArticleDAO = new TaArticleDAO(getEm());
//			//listeArticles =taArticleDAO.selectAll(); 
//			listeArticles =taArticleDAO.findByActif(true);
//			taArticleDAO = null;
//		}		
//		valeurs = new String[listeArticles.size()][2];
//		int i = 0;
//		String description = "";
//		for (TaArticle taArticle : listeArticles) {
//			valeurs[i][0] = taArticle.getCodeArticle();
//
//			description = "";
//			description += taArticle.getLibellecArticle();
//			if(taArticle.getTaFamille()!=null)
//				description += " \r\n Famille = " + taArticle.getTaFamille().getCodeFamille();
//			description += " \r\n Compte = " + taArticle.getNumcptArticle();
//			if(taArticle.getTaPrix()!=null) {
//				description += " \r\n Prix HT = " + taArticle.getTaPrix().getPrixPrix();
//				description += " \r\n Prix TTC = " + taArticle.getTaPrix().getPrixttcPrix();
//				if(taArticle.getTaPrix().getTaUnite()!=null) {
//					description += " \r\n Unité = " + taArticle.getTaPrix().getTaUnite().getCodeUnite();
//				}
//			}
//			if(taArticle.getTaTva()!=null)
//				description += " \r\n Code tva = " + taArticle.getTaTva().getCodeTva();
//			if(taArticle.getTaTTva()!=null)
//				description += " \r\n Exigibilité = " + taArticle.getTaTTva().getCodeTTva();
//			description += " \r\n Divers = " + taArticle.getDiversArticle();
//			description += " \r\n commentaire = " + taArticle.getCommentaireArticle();
//			valeurs[i][1] = description;
//
//			i++;
//		}
//		}
//		return valeurs;
//	}

	public String[][] initAdapterArticle() {
		String[][] valeurs = null;
		List<Object[]> listeTemp = null;
		boolean affichageCtrlEspace = GestionCommercialePlugin.getDefault().getPreferenceStore().getBoolean(fr.legrain.gestionCommerciale.preferences.PreferenceConstants.AFFICHAGE_CTRL_ESPACE);
		if(affichageCtrlEspace){
		if(listeArticles==null){
			TaArticleDAO taArticleDAO = new TaArticleDAO(getEm());
			//listeArticles =taArticleDAO.selectAll(); 
			listeTemp =taArticleDAO.findByActifLight(true);
			taArticleDAO = null;
		}		
		valeurs = new String[listeTemp.size()][2];
		int i = 0;
		String description = "";
		for (Object[] taArticle : listeTemp) {
			valeurs[i][0] = (String)taArticle[0];
//			 a.codeArticle,a.libellecArticle,fam.codeFamille,a.numcptArticle,p.prixPrix,p.prixttcPrix,un.codeUnite,tva.codeTva,ttva.codeTTva,a.diversArticle,a.commentaireArticle		
			description = "";
			description += (String)taArticle[1];
			if((String)taArticle[2]!=null)
				description += " \r\n Famille = " + (String)taArticle[2];
			description += " \r\n Compte = " + (String)taArticle[3];
			if((BigDecimal)taArticle[4]!=null) description += " \r\n Prix HT = " + LibConversion.bigDecimalToString((BigDecimal)taArticle[4]);
			if((BigDecimal)taArticle[5]!=null)description += " \r\n Prix TTC = " + LibConversion.bigDecimalToString((BigDecimal)taArticle[5]);
			if((String)taArticle[6]!=null) 	description += " \r\n Unité = " + (String)taArticle[6];
				
			
			if((String)taArticle[7]!=null)
				description += " \r\n Code tva = " +(String)taArticle[7];
			if((String)taArticle[8]!=null)
				description += " \r\n Exigibilité = " + (String)taArticle[8];
			description += " \r\n Divers = " + (String)taArticle[9];
			description += " \r\n commentaire = " + (String)taArticle[10];
			valeurs[i][1] = description;
			
			i++;
		}
		}
		return valeurs;
	}
	public boolean focusDansLigne(){
		for (Control element : listeComposantFocusable) {
			if(element.isFocusControl())
				return true;
		}
		return false;
	}


	protected void actDown() throws Exception{
		boolean annule=false;
		int selectionGrille=getGrille().getSelectionIndex();
		try {
			if(dao.dataSetEnModif()) {
//				ctrlUnChampsSWT(getFocusCourantSWT());
				//				ibTaTable.setChamp_Model_Obj(((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).setTaLAvoir(((IHMLAvoir)selectedLigneAvoir.getValue())));					
				if(taLAvoir.ligneEstVide()){
					actAnnuler();
					annule=true;
				}else {
					ctrlUnChampsSWT(getFocusCourantSWT());
					actEnregistrer();
				}
			}
			if(!dao.dataSetEnModif()){
				if(selectionGrille==getGrille().getItemCount()-1){
					if(actionAjouter.isEnabled())
						actAjouter();
					initFocusSWT(dao,mapInitFocus);
				}else{ 
					if(!annule)
						tableViewer.selectionGrille(selectionGrille+1);	
					else
						tableViewer.selectionGrille(0);
				}
				initEtatComposant(taLAvoir.getTaTLigne().getCodeTLigne());
				getFocusCourantSWT().forceFocus();
			}
		} catch (Exception e1) {
			logger.error("Erreur : actionPerformed", e1);
		}
	}	


	protected void actUp() throws Exception{
		int selectionGrille=getGrille().getSelectionIndex();
		try {
			if(dao.dataSetEnModif()) {
//				ctrlUnChampsSWT(getFocusCourantSWT());
				//						dao.setChamp_Model_Obj(((TaLAvoir)masterEntity.getLigne(masterEntity.getLigneCourante())).setTaLAvoir(((IHMLAvoir)selectedLigneAvoir.getValue())));					
				if(taLAvoir.ligneEstVide()){
					actAnnuler();
				}else {
					ctrlUnChampsSWT(getFocusCourantSWT());
					actEnregistrer();
				}
			}
			if(!dao.dataSetEnModif()){
				if(selectionGrille>0)
					tableViewer.selectionGrille(selectionGrille-1);
				else
					tableViewer.selectionGrille(getGrille().getItemCount()-1);
			}
			getFocusCourantSWT().forceFocus();
			initEtatComposant(taLAvoir.getTaTLigne().getCodeTLigne());
		} catch (Exception e1) {
			logger.error("Erreur : actionPerformed", e1);
		}
	}



	/**
	 * Initialisation des touches qui déclenchent le transfert du focus au composant suivant
	 * @param listeComponent - composants focusable
	 */
	public void initDeplacementSaisie(List<Control> listeComponent) {
		TraverseListener btnTraverseListener = (new TraverseListener(){
			public void keyTraversed(TraverseEvent e) {
				if(e.keyCode == SWT.ARROW_RIGHT || e.keyCode == SWT.ARROW_DOWN ) {
					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
				} else if(e.keyCode == SWT.ARROW_LEFT || e.keyCode == SWT.ARROW_UP) {
					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_PREVIOUS);
				} 				
			}
		});

		TraverseListener textTraverseListener = (new TraverseListener(){
			public void keyTraversed(TraverseEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
				}
				//							if(e.detail!=SWT.TRAVERSE_ESCAPE )	
				//								e.doit=false;
			}
		});

		for (Control component : listeComponent) {
			if (!(component instanceof Table) ) { //Text, Button, ...
				if (component instanceof Button) {
					if( (((Button)component).getStyle() & SWT.CHECK) != 0 )
						component.addTraverseListener(textTraverseListener);	
					else
						component.addTraverseListener(btnTraverseListener);					
				} else if (component instanceof Text){
					component.addTraverseListener(textTraverseListener);	
				} else {
					//component.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,setForward);
				}
				//component.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,setBackward);			
			}			
		}		
		//changeCouleur(vue);
	}



	public JPABaseControllerSWTStandard getParentEcran() {
		return parentEcran;
	}

	public void setParentEcran(JPABaseControllerSWTStandard parentEcran) {
		this.parentEcran = parentEcran;
	}


	public List<IContextActivation> getListeContextEntete() {
		return listeContextEntete;
	}

	public void setListeContextEntete(List<IContextActivation> listeContextEntete) {
		this.listeContextEntete = listeContextEntete;
	}

	protected class HandlerEnregistrerFacture extends LgrAbstractHandler {

		public Object execute(ExecutionEvent event) throws ExecutionException{
			try {
				getParentEcran().Enregistrer();
			} catch (Exception e) {
				logger.error("",e);
			}
			return event;
		}
	}


	public TaAvoir getMasterEntity() {
		return masterEntity;
	}

	public void setMasterEntity(TaAvoir masterEntity) {
		this.masterEntity = masterEntity;
	}

	public TaAvoirDAO getMasterDAO() {
		return masterDAO;
	}

	public void setMasterDAO(TaAvoirDAO masterDAO) {
		this.masterDAO = masterDAO;
	}

	public TaLAvoirDAO getDao() {
		return dao;
	}

	public void setDao(TaLAvoirDAO dao) {
		this.dao = dao;
	}

	public boolean changementPageValide(){
		if (dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) == 0
				|| dao.getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) == 0) {
			//mise a jour de l'entite principale
			if(taLAvoir!=null && selectedLigneAvoir!=null && ((IHMLAvoir) selectedLigneAvoir.getValue())!=null) {
				mapperUIToModel.map((IHMLAvoir) selectedLigneAvoir.getValue(),taLAvoir);
			}
		}
		//				dao.getModeObjet().setMode(ModeObjet.EnumModeObjet.C_MO_CONSULTATION);
		//				initEtatBouton();
		return true;
	};

	public ContentProposalProvider contentProposalProviderArticles(){
		String[][] TabArticles = initAdapterArticle();
		if(TabArticles!=null){
			listeCodeArticles=new String[TabArticles.length];
			listeDescriptionArticles=new String[TabArticles.length];
			for (int i=0; i<TabArticles.length; i++) {
				listeCodeArticles [i]=TabArticles[i][0];
				listeDescriptionArticles [i]=TabArticles[i][1];
			}
		}
		return new ContentProposalProvider(listeCodeArticles,
				listeDescriptionArticles);	
		}
		
		public void raffraichitListeArticles(){
			listeArticles=null;
			articleContentProposal.setContentProposalProvider(contentProposalProviderArticles());
		}
		
		public void ctrlTouslesChampsToutesLesLignes() throws Exception{
			for (int i = 0; i < ((LgrTableViewer)tableViewer).getTable().getItemCount(); i++) {
				tableViewer.selectionGrille(i);
				for (TaLAvoir p : masterEntity.getLignes()) {
					if(p.getNumLigneLDocument().equals(((IHMLAvoir) selectedLigneAvoir.getValue()).getNumLigneLDocument())) {
						taLAvoir = p;
						//taLAvoir.setLegrain(true);
						ihmLAvoir= IHMLAvoir.copy((IHMLAvoir) selectedLigneAvoir.getValue());;
					}				
				}
				ctrlTousLesChampsAvantEnregistrementSWT();
			}

		}
		
		
		@Override
		public void modifMode(){
			if (!VerrouInterface.isVerrouille() ){
				try {
					if(!daoStandard.dataSetEnModif()) {
						if(!(IHMmodel().size()==0)) {
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

