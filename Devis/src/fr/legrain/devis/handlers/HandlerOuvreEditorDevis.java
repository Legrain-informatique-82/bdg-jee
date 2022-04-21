package fr.legrain.devis.handlers;
import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import fr.legrain.devis.editor.DevisMultiPageEditor;
import fr.legrain.devis.editor.EditorInputDevis;
import fr.legrain.document.handlers.LgrAbstractDocumentHandler;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBAbstractLgrMultiPageEditor;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.gui.ParamAffiche;


public class HandlerOuvreEditorDevis extends LgrAbstractDocumentHandler {

	static Logger logger = Logger.getLogger(HandlerOuvreEditorDevis.class.getName());

	private IWorkbenchWindow window;

	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		this.window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
			try {
				//PlatformUI.getWorkbench().showPerspective(PerspectiveFacture.ID, window);
				IEditorPart editor = EJBAbstractLgrMultiPageEditor.chercheEditeurDocumentOuvert(DevisMultiPageEditor.ID_EDITOR);
				if(editor==null) {
					EditorInputDevis input = new EditorInputDevis("");
					IEditorPart e = page.openEditor(input, DevisMultiPageEditor.ID_EDITOR);
					ParamAffiche param = new ParamAffiche();
					param.setModeEcran(EnumModeObjet.C_MO_INSERTION);
					((DevisMultiPageEditor)e).findMasterController().configPanel(param);
				} else {
					page.activate(editor);
				}
			} catch (WorkbenchException e) {
				logger.error("Erreur pendant l'ouverture de la perspective Devis",e);
			}
		return null;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
	}



	//Deletes all files and subdirectories under dir.
	// Returns true if all deletions were successful.
	// If a deletion fails, the method stops attempting to delete and returns false.
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

}
