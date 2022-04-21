package fr.legrain.gestCom.librairiesEcran.workbench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;

import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSWTStandard;
import fr.legrain.lib.gui.ParamAffiche;

public abstract class EJBAbstractLgrMultiPageEditor extends MultiPageEditorPart implements ISaveablePart2 {
	
	static Logger logger = Logger.getLogger(EJBAbstractLgrMultiPageEditor.class.getName());
	
	public String ID_EDITOR = null; //$NON-NLS-1$

	protected List<ILgrEditor> listePageEditeur = new ArrayList<ILgrEditor>();
	protected Map<ILgrEditor, EJBBaseControllerSWTStandard> mapEditorController = new HashMap<ILgrEditor, EJBBaseControllerSWTStandard>();

	abstract protected void onClose() throws Exception;
	
	public EJBAbstractLgrMultiPageEditor() {
		super();
		setID_EDITOR(ID_EDITOR);
	}
	
	@Override
	public int promptToSaveOnClose() {
		try {
			onClose();
		} catch (Exception e) {
			return ISaveablePart2.NO;
//			return ISaveablePart2.CANCEL;
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
//			//annuleTout();
//			return ISaveablePart2.NO;
//		} else {
//			return ISaveablePart2.CANCEL;
//		}
	}

	public String getID_EDITOR() {
		return ID_EDITOR;
	}

	public EJBBaseControllerSWTStandard findMasterController() {
		return mapEditorController.get(listePageEditeur.get(0));
	}

	protected void setID_EDITOR(String id_editor) {
		ID_EDITOR = id_editor;
	}
	
	static public IEditorPart chercheEditeurDocumentOuvert(String idEditor) {
		IEditorPart editor = null;
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			if(page.getEditorReferences().length>0){	//au moins 1 éditeur ouvert			
				int i = 0;
				while(i<page.getEditorReferences().length && editor==null){
					//if(page.getEditorReferences()[i].getEditor(false) instanceof AbstractMultiPageDocumentEditor) {
					if(page.getEditorReferences()[i].getEditor(false) instanceof EJBAbstractLgrMultiPageEditor) {
						if(idEditor!=null && ((EJBAbstractLgrMultiPageEditor)page.getEditorReferences()[i].getEditor(false)).getID_EDITOR()!=null){
							if (((EJBAbstractLgrMultiPageEditor)page.getEditorReferences()[i].getEditor(false)).getID_EDITOR().equals(idEditor))
								editor = page.getEditorReferences()[i].getEditor(false);
						} else {
							editor = page.getEditorReferences()[i].getEditor(false);
						}
					}
					i++;
				}
			}
			return editor;
		} catch (Exception e) {
			logger.error("",e);
		}
		return editor;
	}
	
	static public boolean isEditeurOuvert(String idEditor) {
		IEditorPart editor = EJBAbstractLgrMultiPageEditor.chercheEditeurDocumentOuvert(idEditor);
		return editor == null;
	}
	
	static public IEditorPart ouvreFiche(String valeurIdentifiant, String valeurCode,String idEditor,ParamAffiche paramAfficheIn,Boolean message,boolean attente) {
		IEditorPart editor = EJBAbstractLgrMultiPageEditor.chercheEditeurDocumentOuvert(idEditor);
		ParamAffiche paramAffiche;
		if (editor == null) {
			try {
				IEditorPart e = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage().openEditor(
								new IEditorInput() {

									public boolean exists() {
										return false;
									}

									public ImageDescriptor getImageDescriptor() {
										return null;
									}

									public String getName() {
										return "";
									}

									public IPersistableElement getPersistable() {
										return null;
									}

									public String getToolTipText() {
										return "";
									}

									public Object getAdapter(Class adapter) {
										return null;
									}
								}, idEditor);

				LgrPartListener.getLgrPartListener().getLgrNavigation().add(e);
				if(paramAfficheIn!=null)paramAffiche=paramAfficheIn;
				else paramAffiche = new ParamAffiche();
				if(valeurIdentifiant!=null) {
					paramAffiche.setIdFiche(valeurIdentifiant);
				} else if(valeurCode!=null) {
					paramAffiche.setCodeDocument(valeurCode);
				}
				if (e instanceof EJBAbstractLgrMultiPageEditor) {
					((EJBAbstractLgrMultiPageEditor) e).findMasterController().configPanel(paramAffiche);
				} else {
					((EJBLgrEditorPart) e).setPanel(((EJBLgrEditorPart) e).getController().getVue());
					((EJBLgrEditorPart) e).getController().configPanel(paramAffiche);
				}
				return e;
			} catch (PartInitException e1) {
				logger.error("", e1);
				
			}
			
		} else {
			Boolean continuer=true;
			if(message!=null && message)
				continuer=MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Affichage document",
					"Voulez-vous abandonner le document en cours de saisie ?");
				if(continuer)	{
				if(paramAfficheIn!=null)paramAffiche=paramAfficheIn;
					else paramAffiche = new ParamAffiche();
				if (editor instanceof EJBAbstractLgrMultiPageEditor) {
					if(valeurIdentifiant!=null) {
						paramAffiche.setIdFiche(valeurIdentifiant);
					} else if(valeurCode!=null) {
						paramAffiche.setCodeDocument(valeurCode);
					}
					((EJBAbstractLgrMultiPageEditor) editor).findMasterController().configPanel(paramAffiche);
				}
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(editor);
				}
				return editor;
		}
		return null;
	}
		
	static public IEditorPart ouvreFiche(String valeurIdentifiant, String valeurCode,String idEditor,ParamAffiche paramAfficheIn,Boolean message) {
		return ouvreFiche(valeurIdentifiant,valeurCode,idEditor,paramAfficheIn,message,false);
	}



}