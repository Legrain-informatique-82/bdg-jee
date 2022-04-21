package fr.legrain.rappeler.editor;



import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import fr.legrain.articles.dao.TaArticle;
import fr.legrain.articles.dao.TaArticleDAO;
import fr.legrain.gestCom.librairiesEcran.swt.JPABaseControllerSWTStandard;
import fr.legrain.gestCom.librairiesEcran.workbench.AbstractLgrMultiPageEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDePageEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent;
import fr.legrain.gestCom.librairiesEcran.workbench.IChangementDePageListener;
import fr.legrain.gestCom.librairiesEcran.workbench.IChangementDeSelectionListener;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrRetourEditeur;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.rappeler.dao.TaEmailRappeler;
import fr.legrain.rappeler.dao.TaEmailRappelerDAO;
import fr.legrain.rappeler.dao.TaUserRappeler;
import fr.legrain.rappeler.dao.TaUserRappelerDAO;
import fr.legrain.rappeler.ecran.SWTPaEmailRappelerController;
import fr.legrain.rappeler.ecran.SWTPaUserRappelerController;


/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class EditorMultiPageRappeler extends AbstractLgrMultiPageEditor implements ILgrRetourEditeur, IChangementDePageListener, IChangementDeSelectionListener{
//public class MultiPageEditor extends FormEditor {
//public class MultiPageEditor extends SharedHeaderFormEditor {

	public static final String ID_EDITOR = "fr.legrain.rappeler.multiPage"; //$NON-NLS-1$
	static Logger logger = Logger.getLogger(EditorMultiPageRappeler.class.getName());
	
	private ILgrEditor editeurCourant;
	private int currentPage;
	//private TaUserRappeler masterEntity = null;
	//private TaUserRappelerDAO masterDAO = null;
	
	
	private EditorUserRappeler editorUserRappeler = null;
	private EditorEmailRappeler editorEmailRappeler = null;

	/**
	 * Creates a multi-page editor example.
	 */
	public EditorMultiPageRappeler() {
		super();
		setID_EDITOR(ID_EDITOR);
	}
	

//	@Override
//	protected void createHeaderContents(IManagedForm headerForm) {
//		// TODO Auto-generated method stub
//		new Composite1(headerForm.getForm().getParent(), SWT.NONE);
//	}


//	/**
//	 * Creates page 0 of the multi-page editor,
//	 * which contains a text editor.
//	 */
//	void createPage0() {
//		try {
//			editor = new SWTEditorFacture();
//			int index = addPage(editor, getEditorInput());
//			//setPageText(index, editor.getTitle());
//			setPageText(index, "TestEditor");
//		} catch (PartInitException e) {
//			ErrorDialog.openError(
//				getSite().getShell(),"Error creating nested text editor",null,e.getStatus());
//		}
//	}
	
	void createPageQueEditeur() {
		try {
			
			String mailLabel="Utilisateurs";
			String emailPersoLabel="gestion des emails";
		
			
			//Initialisation des pages/editeurs composant le multipage editor
			editorUserRappeler = new EditorUserRappeler(this);
			editorEmailRappeler = new EditorEmailRappeler(this);
			
			listePageEditeur.add(editorUserRappeler);
			listePageEditeur.add(editorEmailRappeler);
			
			
			int index = addPage(editorUserRappeler, getEditorInput());
			setPageText(index, mailLabel);
			index = addPage(editorEmailRappeler, getEditorInput());
			setPageText(index, emailPersoLabel);
			
		
			//setPageImage(index, ArticlesPlugin.getImageDescriptor(iconPageArticle).createImage());
			//index = addPage(editorNewMailRappeler, getEditorInput());
			//liaison entre la selection du controller principal et le multipage editor
			editorUserRappeler.getController().addChangementDeSelectionListener(this);
			mapEditorController.put(editorUserRappeler, editorUserRappeler.getController());
			
			
			editorEmailRappeler.getController().addChangementDePageListener(this);
			mapEditorController.put(editorEmailRappeler, editorEmailRappeler.getController());
			//Initialisation des pages/editeurs composant le multipage editor
//			String labelPageArticle = "Articles";
//			String labelPagePrix = "Prix";
//			String labelPageCommentaireArticle = "Commentaires";
//			
//			String iconPageArticle = "/icons/package.png";
//			String iconPagePrix = "/icons/money.png";
//			String iconPageCommentaireArticle = "/icons/note.png";
//			
//			editorArticle = new EditorArticle(this);
//			editorNewmailRappeler = new EditorPrix(this);
//			editorCommentairesArticle = new EditorCommentairesArticle(this);
//			
//			listePageEditeur.add(editorArticle);
//			listePageEditeur.add(editorNewmailRappeler);
//			listePageEditeur.add(editorCommentairesArticle);
//			
//			int index = addPage(editorArticle, getEditorInput());
//			setPageText(index, labelPageArticle);
//			setPageImage(index, ArticlesPlugin.getImageDescriptor(iconPageArticle).createImage());
//			index = addPage(editorNewmailRappeler, getEditorInput());
//			setPageText(index, labelPagePrix);
//			setPageImage(index, ArticlesPlugin.getImageDescriptor(iconPagePrix).createImage());
//			index = addPage(editorCommentairesArticle, getEditorInput());
//			setPageText(index, labelPageCommentaireArticle);
//			setPageImage(index, ArticlesPlugin.getImageDescriptor(iconPageCommentaireArticle).createImage());
//			
//			//liaison entre la selection du controller principal et le multipage editor
//			editorArticle.getController().addChangementDeSelectionListener(this);
//			
//			mapEditorController.put(editorArticle, editorArticle.getController());
//			mapEditorController.put(editorNewmailRappeler, editorNewmailRappeler.getController());
//			mapEditorController.put(editorCommentairesArticle, editorCommentairesArticle.getController());
//			
//			editorArticle.getController().addChangementDePageListener(this);
//			editorNewmailRappeler.getController().addChangementDePageListener(this);
//			editorCommentairesArticle.getController().addChangementDePageListener(this);
			
			//liaison entre l'editeur principal et les editeur secondaire par injection de l'entite principale dans les editeurs secondaires
	//		masterDAO = ((SWTPaUserRappelerController)editorUserRappeler.getController()).getDao();
//			((SWTPaEmailRappelerController)editorEmailRappeler.getController()).setMasterDAO(masterDAO);
//			((SWTPaCommentairesArticleController)editorCommentairesArticle.getController()).setMasterDAO(masterDAO);
			
			editorUserRappeler.getController().addDeclencheCommandeControllerListener(editorUserRappeler.getController());
			editorEmailRappeler.getController().addDeclencheCommandeControllerListener(editorEmailRappeler.getController());
			
			//Boolean affiche_onglets = DocumentPlugin.getDefault().getPreferenceStore().getBoolean(fr.legrain.document.preferences.PreferenceConstants.P_ONGLETS_DOC);
			//if(!affiche_onglets)
			//	hideTabs();		
			
			editeurCourant = editorUserRappeler;
			//changeEditeurCourant(editorEntete);
			
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
			if (oldFormPage.canLeaveThePage() == false ) {
				setActivePage(oldPageIndex);
				return;
			}
		}
		
		
		super.pageChange(newPageIndex);
		
		editeurCourant = listePageEditeur.get(newPageIndex);
		setCurrentPage(listePageEditeur.indexOf(editeurCourant));
		
		if (oldPageIndex != -1 && listePageEditeur.size() > oldPageIndex
				&& listePageEditeur.get(oldPageIndex) instanceof ILgrEditor
				&& oldPageIndex != newPageIndex) {
			//evite l'appel a refresh lors de l'ouverture du multipage editor (la commande n'a pas encore de handler actif)
			//mapEditorController.get(getActiveEditor()).executeCommand(JPABaseControllerSWTStandard.C_COMMAND_GLOBAL_REFRESH_ID);
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
	}

	/*
	 * Reaction au changement de selection dans l'editeur principal
	 * (non-Javadoc)
	 * @see fr.legrain.gestCom.librairiesEcran.workbench.IChangementDeSelectionListener#changementDeSelection(fr.legrain.gestCom.librairiesEcran.workbench.ChangementDeSelectionEvent)
	 */
	public void changementDeSelection(ChangementDeSelectionEvent evt) {
//		masterEntity = ((SWTPaArticlesController)editorArticle.getController()).getTaArticle();
//		if(masterEntity!=null && masterEntity.getCodeArticle()!=null) {
//			setPartName(masterEntity.getCodeArticle());
//		} else {
//			setPartName("");
//		}
//		//maj de l'editeur prix
//		ParamAffichePrix paramAffichePrix = new ParamAffichePrix();
//		paramAffichePrix.setIdArticle(LibConversion.integerToString(masterEntity.getIdArticle()));
//		((SWTPaPrixController)editorNewMailRappeler.getController()).setMasterEntity(masterEntity);
//		((SWTPaPrixController)editorNewMailRappeler.getController()).configPanel(paramAffichePrix);
//		//maj de l'editeur commentaire
//		ParamAfficheCommentairesArticle paramAfficheCommentairesArticle = new ParamAfficheCommentairesArticle();
//		paramAffichePrix.setIdArticle(LibConversion.integerToString(masterEntity.getIdArticle()));
//		((SWTPaCommentairesArticleController)editorCommentairesArticle.getController()).setMasterEntity(masterEntity);
//		((SWTPaCommentairesArticleController)editorCommentairesArticle.getController()).configPanel(paramAfficheCommentairesArticle);
	}

//	@Override
//	protected void addPages() {
//		// TODO Auto-generated method stub
//		try {
//			int index = addPage(new FormPageA(this,"aa", "desc"));
//			setPageText(index, "formPage");
//			index = addPage(new FormPageA(this,"bb", "descb"));
//			setPageText(index, "formPageb");
//		} catch (PartInitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
