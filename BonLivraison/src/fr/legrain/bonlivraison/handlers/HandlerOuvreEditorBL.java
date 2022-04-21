package fr.legrain.bonlivraison.handlers;
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

import fr.legrain.bonlivraison.editor.BLMultiPageEditor;
import fr.legrain.bonlivraison.editor.EditorInputBonliv;
import fr.legrain.document.handlers.LgrAbstractDocumentHandler;
import fr.legrain.gestCom.librairiesEcran.workbench.AbstractLgrMultiPageEditor;
import fr.legrain.lib.data.ModeObjet.EnumModeObjet;
import fr.legrain.lib.gui.ParamAffiche;


public class HandlerOuvreEditorBL extends LgrAbstractDocumentHandler {

	static Logger logger = Logger.getLogger(HandlerOuvreEditorBL.class.getName());

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
				IEditorPart editor = AbstractLgrMultiPageEditor.chercheEditeurDocumentOuvert(BLMultiPageEditor.ID_EDITOR);
				if(editor==null) {
					EditorInputBonliv input = new EditorInputBonliv("");
					IEditorPart e = page.openEditor(input, BLMultiPageEditor.ID_EDITOR);
					ParamAffiche param = new ParamAffiche();
					param.setModeEcran(EnumModeObjet.C_MO_INSERTION);
					((BLMultiPageEditor)e).findMasterController().configPanel(param);
				} else {
					page.activate(editor);
				}
			} catch (WorkbenchException e) {
				logger.error("Erreur pendant l'ouverture de la perspective Bon de livraison",e);
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
