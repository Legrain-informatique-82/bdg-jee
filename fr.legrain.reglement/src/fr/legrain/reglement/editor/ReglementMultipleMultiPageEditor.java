package fr.legrain.reglement.editor;



import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import fr.legrain.documents.dao.TaFacture;
import fr.legrain.documents.dao.TaFactureDAO;
import fr.legrain.gestCom.librairiesEcran.swt.DeclencheCommandeControllerEvent;
import fr.legrain.gestCom.librairiesEcran.swt.JPABaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.swt.ParamAfficheReglementMultiple;
import fr.legrain.gestCom.librairiesEcran.workbench.AbstractLgrMultiPageEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.AnnuleToutEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDePageEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementMasterEntityEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.IAnnuleToutListener;
import fr.legrain.gestCom.librairiesEcran.workbench.IChangementDePageListener;
import fr.legrain.gestCom.librairiesEcran.workbench.IChangementDeSelectionListener;
import fr.legrain.gestCom.librairiesEcran.workbench.IChangementMasterEntityListener;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrRetourEditeur;
import fr.legrain.gestCom.librairiesEcran.workbench.JPALgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.ModeObjet;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.reglement.Activator;
import fr.legrain.reglement.ecran.PaGestionReglementController;
import fr.legrain.reglement.ecran.ParamAfficheValidationReglementMultiple;
import fr.legrain.reglement.ecran.SWTPaReglementMultipleController;
import fr.legrain.tiers.dao.TaTiersDAO;


/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class ReglementMultipleMultiPageEditor extends AbstractLgrMultiPageEditor implements 
ILgrRetourEditeur, IChangementDePageListener,IChangementMasterEntityListener , IChangementDeSelectionListener,
IAnnuleToutListener{
//public class MultiPageEditor extends FormEditor {
//public class MultiPageEditor extends SharedHeaderFormEditor {
	
	public static final String ID_EDITOR = "fr.legrain.reglement.multi"; //$NON-NLS-1$
	static Logger logger = Logger.getLogger(ReglementMultipleMultiPageEditor.class.getName());
	
	protected ILgrEditor editeurCourant;
	protected int currentPage;
	private TaFacture masterEntity = null;
	private TaFactureDAO masterDAO = null;
	protected EntityManager em = null;
	
	protected EditorReglementMultiple editorReglementMultiple = null;
	//protected EditorValidationReglementMultiple editorValidationReglementMultiple = null;
	protected EditorGestionReglement editorGestionReglement = null;
	
	/**
	 * Creates a multi-page editor example.
	 */
	public ReglementMultipleMultiPageEditor() {
		super();
		setID_EDITOR(ID_EDITOR);
	}	

	
	protected void createPageQueEditeur() {
		try {
			//Initialisation des pages/editeurs composant le multipage editor
			//String labelPageReglement = "Règlements sur liste";
//			String labelPageValidation = "Validation";
			//String labelPageGestionReglement = "Modification des réglements";
			
			String iconPageReglement= "/icons/logo_lgr_16.png";
			String iconPageValidation = "/icons/logo_lgr_16.png";
			String iconPageGestionReglement = "/icons/logo_lgr_16.png";

			em = new TaTiersDAO().getEntityManager();
			editorReglementMultiple = new EditorReglementMultiple(this,em);
//			editorValidationReglementMultiple = new EditorValidationReglementMultiple(this,em);
			editorGestionReglement = new EditorGestionReglement(this, em);
			
			
			
			listePageEditeur.add(editorReglementMultiple);
			listePageEditeur.add(editorGestionReglement);
			
			
			int index = addPage(editorReglementMultiple, getEditorInput());
			setPageText(index, editorReglementMultiple.getTitle());
			setPageImage(index, Activator.getImageDescriptor(iconPageReglement).createImage());
			index = addPage(editorGestionReglement, getEditorInput());
			setPageText(index, editorGestionReglement.getTitle());
			setPageImage(index, Activator.getImageDescriptor(iconPageGestionReglement).createImage());
			
			//liaison entre la selection du controller principal et le multipage editor
			editorReglementMultiple.getController().addChangementDeSelectionListener(this);
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).addChangementMasterEntityListener(this);

			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).
				getPaReglementController().addDeclencheCommandeControllerListener(editorReglementMultiple.getController());

			
			mapEditorController.put(editorReglementMultiple, editorReglementMultiple.getController());
//			mapEditorController.put(editorValidationReglementMultiple, editorValidationReglementMultiple.getController());
			mapEditorController.put(editorGestionReglement, editorGestionReglement.getController());


			
			editorReglementMultiple.getController().addChangementDePageListener(this);
//			editorValidationReglementMultiple.getController().addChangementDePageListener(this);
			editorGestionReglement.getController().addChangementDePageListener(this);
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getPaVisuReglementControlleur().addChangementDePageListener(this);
			
			
			
			//liaison entre l'editeur principal et les editeur secondaire par injection de l'entite principale dans les editeurs secondaires
//TODO a remettre quand tous les editeur embarques seront finis
			masterDAO = ((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getDao();
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getPaReglementController().setMasterDAO(masterDAO);
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationReglementController().setMasterDAO(masterDAO);
			
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAvoirController().setMasterDAO(masterDAO);
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAcompteController().setMasterDAO(masterDAO);
			((PaGestionReglementController)editorGestionReglement.getController()).setMasterDAO(masterDAO);
			//Enleve par Isa pour tests
//			((SWTPaValidationReglementMultipleController)editorValidationReglementMultiple.getController()).setMasterDAO(masterDAO);

			
//			editorValidationReglementMultiple.getController().addDeclencheCommandeControllerListener(editorReglementMultiple.getController());
			editorGestionReglement.getController().addDeclencheCommandeControllerListener(editorReglementMultiple.getController());
			
			editorReglementMultiple.getController().addAnnuleToutListener(this);
//			editorValidationReglementMultiple.getController().addAnnuleToutListener(this);
			editorGestionReglement.getController().addAnnuleToutListener(this);
			
			
			//Boolean affiche_onglets = DocumentPlugin.getDefault().getPreferenceStore().getBoolean(fr.legrain.document.preferences.PreferenceConstants.P_ONGLETS_DOC);
			//if(!affiche_onglets)
			//	hideTabs();		
			
			editeurCourant = editorReglementMultiple;
			ParamAfficheReglementMultiple paramAfficheReglementMultiple = new ParamAfficheReglementMultiple();
			editorGestionReglement.getController().configPanel(paramAfficheReglementMultiple);
			//changeEditeurCourant(editorTiers);
			
			//
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationReglementController().addUpdateMasterEntityListener((SWTPaReglementMultipleController)editorReglementMultiple.getController());
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAvoirController().addUpdateMasterEntityListener((SWTPaReglementMultipleController)editorReglementMultiple.getController());
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAcompteController().addUpdateMasterEntityListener((SWTPaReglementMultipleController)editorReglementMultiple.getController());

		} catch (PartInitException e) {
			ErrorDialog.openError(
				getSite().getShell(),"Error creating nested text editor",null,e.getStatus());
		}
	}
	
	/**
 	 * If there is just one page in the multi-page editor part,
 	 * this hides the single tab at the bottom.
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
 	protected void hideTabs() {
		//if (getPageCount() <= 0) {
 		//	setPageText(0, ""); //$NON-NLS-1$
 			if (getContainer() instanceof CTabFolder) {
 				((CTabFolder)getContainer()).setTabHeight(0);
 			}
		//}
 	}
	
	public void changeEditeurCourant(ILgrEditor ed) {
		
		//if(editeurCourant.validate()) {
			for (ILgrEditor e : listePageEditeur) {
				if(e!=ed) e.setEnabled(false);
			}
			ed.setEnabled(true);
			editeurCourant = ed;
			setCurrentPage(listePageEditeur.indexOf(editeurCourant));
		//}
	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPageQueEditeur();
	//	createPage0();
	//	super.createPages();
	}
	
	
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
	//	ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		findMasterController().setActiveAide(true);
		getEditor(0).doSave(monitor);
	}
	
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
//		IEditorPart editor = getEditor(0);
//		editor.doSaveAs();
//		setPageText(0, editor.getTitle());
//		setInput(editor.getEditorInput());
	}
	
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
//		if (!(editorInput instanceof IFileEditorInput))
//			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(LgrPartListener.getLgrPartListener());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		int oldPageIndex =   getCurrentPage();
		if (oldPageIndex != -1 && listePageEditeur.size() > oldPageIndex
				&& listePageEditeur.get(oldPageIndex) instanceof ILgrEditor
				&& oldPageIndex != newPageIndex) {
			// Check the old page
			ILgrEditor oldFormPage = (ILgrEditor) listePageEditeur.get(oldPageIndex);
			if (oldFormPage.canLeaveThePage() == false 
//					|| masterEntity==null
					) {
				setActivePage(oldPageIndex);	
				return;
			}
		}
		
		
		super.pageChange(newPageIndex);
		setPartName(((ILgrEditor) listePageEditeur.get(newPageIndex)).getTitle());

		editeurCourant = listePageEditeur.get(newPageIndex);
		setCurrentPage(listePageEditeur.indexOf(editeurCourant));
		
		if (oldPageIndex != -1 && listePageEditeur.size() > oldPageIndex
				&& listePageEditeur.get(oldPageIndex) instanceof ILgrEditor
				&& oldPageIndex != newPageIndex) {
			if(mapEditorController.get(getActiveEditor())!=null) { //on est bien sur un editeur "classique" et non une extension particuliere
				//evite l'appel a refresh lors de l'ouverture du multipage editor (la commande n'a pas encore de handler actif)
				if ((mapEditorController.get(getActiveEditor()).getDaoStandard().getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_INSERTION) != 0)
						&& (mapEditorController.get(getActiveEditor()).getDaoStandard().getModeObjet().getMode().compareTo(ModeObjet.EnumModeObjet.C_MO_EDITION) != 0)) {
					//on n'est pas en edition, ni en insertion
					mapEditorController.get(getActiveEditor()).executeCommand(JPABaseControllerSWTStandard.C_COMMAND_GLOBAL_REFRESH_ID);
				}
			}
		}

	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;	
	}


	public Object retour() {
		// TODO Auto-generated method stub
		return null;
	}


	public void utiliseRetour(Object r) {
		// TODO Auto-generated method stub
		if(editeurCourant instanceof ILgrRetourEditeur)
			((ILgrRetourEditeur)editeurCourant).utiliseRetour(r);
	}


	@Override
	protected Composite createPageContainer(Composite parent) {
		// TODO Auto-generated method stub
		return super.createPageContainer(parent);
	}

	public ILgrEditor findSuivant() {
		if(listePageEditeur.size()>getCurrentPage()+1) {
			if(listePageEditeur.get(getCurrentPage()+1)!=null) {
				return listePageEditeur.get(getCurrentPage()+1);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public ILgrEditor findPrecedent() {
		if(getCurrentPage()>0) {
			if(listePageEditeur.get(getCurrentPage()-1)!=null) {
				return listePageEditeur.get(getCurrentPage()-1);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public void annuleTout(AnnuleToutEvent evt) {
//		boolean autreOngletEnModif = false;
//		for (ILgrEditor ed : mapEditorController.keySet()) {
//			//if(mapEditorController.get(ed).getDaoStandard().getModeObjet()
//			switch (mapEditorController.get(ed).getDaoStandard().getModeObjet().getMode()) {
//			case C_MO_INSERTION:
//			case C_MO_EDITION:
//				if(mapEditorController.get(ed).getSourceDeclencheCommandeController()==null)
//					autreOngletEnModif = true;
//				break;
//			}
//		}
		
//		if(autreOngletEnModif) {
			MessageDialog dialog = new MessageDialog(getEditorSite().getShell(), 
					"Annulation",
					//null, "Il existe d'autres modifications/insertions en cours pour ce tiers. Voulez vous les annuler aussi ?",
					null, "Voulez vous annuler les modifications en cours ?",
					MessageDialog.QUESTION, 
					//new String [] {IDialogConstants.YES_LABEL,IDialogConstants.NO_LABEL},
					new String [] {IDialogConstants.YES_LABEL,IDialogConstants.YES_TO_ALL_LABEL,IDialogConstants.NO_LABEL,IDialogConstants.NO_TO_ALL_LABEL,IDialogConstants.CANCEL_LABEL},
					0);

			//final int dialogResult = dialog.open();
			int pourTous = -1;

			/*
			 * yes => boucle avec question
			 * yes_all => boucle sans question (annulation silencieuse)
			 * no => enregistre avec question
			 * no_all => enregistre tout
			 */

			//if(dialogResult ==0) {
			int dialogResult = -1;
			
			if(evt.isSilencieu())
				dialogResult = 1;
			
				for (ILgrEditor ed : mapEditorController.keySet()) {
					//if(mapEditorController.get(ed).getDaoStandard().getModeObjet()
					switch (mapEditorController.get(ed).getDaoStandard().getModeObjet().getMode()) {
					case C_MO_INSERTION:
					case C_MO_EDITION:
						if(!mapEditorController.get(ed).equals(evt.getSource())) {
							
							setActiveEditor(ed);
							
							if(dialogResult==-1)
								dialogResult = dialog.open();
							
							if(dialogResult ==0 || dialogResult==1) {
								
								
								boolean silencieu = mapEditorController.get(ed).isSilencieu();
								mapEditorController.get(ed).setSilencieu(true);
								//mapEditorController.get(ed).setSilencieu(evt.isSilencieu());
								mapEditorController.get(ed).setAnnuleToutEnCours(true);
								mapEditorController.get(ed).declencheCommandeController(new DeclencheCommandeControllerEvent(this,JPABaseControllerSWTStandard.C_COMMAND_GLOBAL_ANNULER_ID));
								mapEditorController.get(ed).setAnnuleToutEnCours(false);
								mapEditorController.get(ed).setSilencieu(silencieu);
								if( dialogResult==0)
									dialogResult = -1;
							} else if(dialogResult==2 || dialogResult==3) {
								//no
								if( dialogResult==2)
									dialogResult = -1;
							} else if(dialogResult==4) {
								//cancel
								break;
							}


						}
						break;
					}
				}
			//}
//		}
	}
	
	public void enregistreTout(AnnuleToutEvent evt) {
//		MessageDialog dialog = new MessageDialog(getEditorSite().getShell(), 
//				"Enregistrement",
//				null, "Voulez vous enregistrer les modifications ?",
//				MessageDialog.QUESTION, 
//				new String [] {IDialogConstants.YES_LABEL,IDialogConstants.NO_LABEL,IDialogConstants.CANCEL_LABEL},
//				0);
//		
//		final int dialogResult = dialog.open();
		
		/*
		 * yes => boucle avec question
		 * yes_all => boucle sans question (annulation silencieuse)
		 * no => enregistre avec question
		 * no_all => enregistre tout
		 */
		
//		if(dialogResult ==0) {
//			
//		}
		boolean pourTous = false;
		for (ILgrEditor ed : mapEditorController.keySet()) {
			//if(mapEditorController.get(ed).getDaoStandard().getModeObjet()
			switch (mapEditorController.get(ed).getDaoStandard().getModeObjet().getMode()) {
			case C_MO_INSERTION:
			case C_MO_EDITION:
				boolean silencieu = mapEditorController.get(ed).isSilencieu();
//				if(!pourTous) {
//					setActiveEditor(ed);
//				}
				
//				if(!pourTous) {
//					if(!mapEditorController.get(ed).equals(evt.getSource())) {
//						MessageDialog dialog = new MessageDialog(mapEditorController.get(ed).getVue().getShell(), 
//								"Attention",
//								null, "Enregistrer ?",
//								MessageDialog.QUESTION, 
//								new String [] {IDialogConstants.YES_LABEL,IDialogConstants.YES_TO_ALL_LABEL,IDialogConstants.NO_LABEL,IDialogConstants.NO_TO_ALL_LABEL,IDialogConstants.CANCEL_LABEL},
//								0);
//						final int dialogResult = dialog.open();
//						if(dialogResult ==0) {
//						} else if(dialogResult ==1) {
//							pourTous = true;
//						} else if(dialogResult ==2) {
//						} else if(dialogResult ==3) {
//						} else if(dialogResult ==4) {
//						}
//						silencieu = true;
//					}
//				}
				
				
				if(!mapEditorController.get(ed).equals(evt.getSource())) {
//					if(!(mapEditorController.get(ed) instanceof SWTPaTiersController)) {
					mapEditorController.get(ed).setSilencieu(evt.isSilencieu());
					mapEditorController.get(ed).setPrepareEnregistrement(true);
					mapEditorController.get(ed).declencheCommandeController(new DeclencheCommandeControllerEvent(this,JPABaseControllerSWTStandard.C_COMMAND_GLOBAL_ENREGISTRER_ID));
					mapEditorController.get(ed).setPrepareEnregistrement(false);
					mapEditorController.get(ed).setSilencieu(silencieu);
//					}
				}
				break;
			}
		}
	}
	
	@Override
	public int promptToSaveOnClose() {
		try {
			onClose();
		} catch (Exception e) {
			return ISaveablePart2.CANCEL;
		}
		return ISaveablePart2.NO; 
//		MessageDialog dialog = new MessageDialog(getEditorSite().getShell(), 
//				"Enregistrement",
//				null, "Voulez vous sauvegarder les modifications ?",
//				MessageDialog.QUESTION, 
//				new String [] {IDialogConstants.YES_LABEL,IDialogConstants.NO_LABEL,IDialogConstants.CANCEL_LABEL},
//				0);
//		MessageDialogWithToggle dialogt;
//		final int dialogResult = dialog.open();
//		
//		if(dialogResult ==0) {
//			if(true) { //bouchon, appeler une methode de verif des données
//				return ISaveablePart2.YES;
//			} else {
//				//showMessageErreur
//				return ISaveablePart2.CANCEL;
//			}
//		} else if(dialogResult ==1) {
//			annuleTout(new AnnuleToutEvent(this,true));
//			return ISaveablePart2.NO;
//		} else {
//			return ISaveablePart2.CANCEL;
//		}
	}

	public void changementDePage(ChangementDePageEvent evt) {
		if(evt.getSens() == ChangementDePageEvent.PRECEDENT) {
			if(findPrecedent()!=null) {
//				changeEditeurCourant(findPrecedent());
				setActiveEditor(findPrecedent());
			}
		} else if(evt.getSens() == ChangementDePageEvent.SUIVANT) {
			if(findSuivant()!=null) {
				//changeEditeurCourant(findSuivant());
				setActiveEditor(findSuivant());
			}
		} else if(evt.getSens() == ChangementDePageEvent.DEBUT) {
				//changeEditeurCourant(findSuivant());
			if(listePageEditeur.get(0)!=null)	
				setActiveEditor(listePageEditeur.get(0));
		} else if(evt.getSens() == ChangementDePageEvent.FIN) {
			if(listePageEditeur.size()!=0 && listePageEditeur.get(listePageEditeur.size())!=null)
				setActiveEditor(listePageEditeur.get(listePageEditeur.size()-1));
		} else if(evt.getSens() == ChangementDePageEvent.AUTRE) {
			if(listePageEditeur.get(evt.getPosition())!=null)
				setActiveEditor(listePageEditeur.get(evt.getPosition()));
			
		}
		
		if(evt.getParamPage()!=null && (evt.getParamPage() instanceof ParamAffiche)) {
			if(getActiveEditor() instanceof JPALgrEditorPart) {
				((JPALgrEditorPart)getActiveEditor()).getController().configPanel((ParamAffiche)evt.getParamPage());
			}
		}
	}

	
	/*
	 * Reaction au changement de selection dans l'editeur principal
	 * (non-Javadoc)
	 * @see fr.legrain.gestCom.librairiesEcran.workbench.IChangementDeSelectionListener#changementDeSelection(fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent)
	 */
	public void changementDeSelection(ChangementDeSelectionEvent evt) {
		masterEntity = ((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getTaDocument();
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).
		getPaReglementController().setMasterEntity(masterEntity);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).
		getAffectationReglementController().setMasterEntity(masterEntity);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).
		getAffectationAvoirController().setMasterEntity(masterEntity);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).
		getAffectationAcompteController().setMasterEntity(masterEntity);
		
		masterDAO=((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getDao();
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getPaReglementController().setMasterDAO(masterDAO);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationReglementController().setMasterDAO(masterDAO);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAvoirController().setMasterDAO(masterDAO);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAcompteController().setMasterDAO(masterDAO);
		((PaGestionReglementController)editorGestionReglement.getController()).setMasterDAO(masterDAO);
		
			setPartName("Règlement");
		try {
			((SWTPaReglementMultipleController)editorReglementMultiple.getController()).
			getPaReglementController().actRefresh();
		} catch (Exception e) {
			logger.error("",e);
		}
		//maj de l'editeur adresse
		ParamAfficheValidationReglementMultiple paramAfficheValidationReglementMultiple = new ParamAfficheValidationReglementMultiple();
		if(masterEntity!=null && masterEntity.getTaTiers()!=null)
			paramAfficheValidationReglementMultiple.setIdTiers(LibConversion.integerToString(masterEntity.getTaTiers().getIdTiers()));
		
		//Enleve par Isa pour tests
//		((SWTPaValidationReglementMultipleController)editorValidationReglementMultiple.getController()).setMasterEntity(masterEntity);
//		((SWTPaValidationReglementMultipleController)editorValidationReglementMultiple.getController()).configPanel(paramAfficheValidationReglementMultiple);

	}
	@Override
	protected void onClose() throws Exception {
		// TODO Auto-generated method stub
		try {
			if (!findMasterController().onClose())
				throw new Exception();
		} finally {
			em.close();
		}
	}

	@Override
	public void changementMasterEntity(ChangementMasterEntityEvent evt) {
		masterEntity = ((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getTaDocument();
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getPaReglementController().setMasterEntity(masterEntity);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationReglementController().setMasterEntity(masterEntity);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAvoirController().setMasterEntity(masterEntity);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAcompteController().setMasterEntity(masterEntity);
		
		masterDAO=((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getDao();
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getPaReglementController().setMasterDAO(masterDAO);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationReglementController().setMasterDAO(masterDAO);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAvoirController().setMasterDAO(masterDAO);
		((SWTPaReglementMultipleController)editorReglementMultiple.getController()).getAffectationAcompteController().setMasterDAO(masterDAO);
		((PaGestionReglementController)editorGestionReglement.getController()).setMasterDAO(masterDAO);
		
		setPartName("Règlement");
		
		
	}
	
}
