package fr.legrain.tiers.handlers;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBLgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.tiers.ecran.ParamAfficheTypeTarif;
import fr.legrain.tiers.editor.EditorInputTypeTarif;
import fr.legrain.tiers.editor.EditorTypeTarif;


public class HandlerTypeTarif extends LgrAbstractHandler {
	
	static Logger logger = Logger.getLogger(HandlerTypeTarif.class.getName());
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
			IEditorPart editor = EJBLgrEditorPart.verifEditeurOuvert(EditorTypeTarif.ID);
			if(editor==null){
			IEditorPart e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new EditorInputTypeTarif(), EditorTypeTarif.ID);
			LgrPartListener.getLgrPartListener().getLgrNavigation().add(e);
			ParamAfficheTypeTarif paramAfficheTypeTarif = new ParamAfficheTypeTarif();
			((EJBLgrEditorPart)e).setPanel(((EJBLgrEditorPart)e).getController().getVue());
			((EJBLgrEditorPart)e).getController().configPanel(paramAfficheTypeTarif);	
			} else {
				page.activate(editor);
			}
		} catch (WorkbenchException e) {
			logger.error("Erreur pendant l'ouverture de l'éditeur ",e);
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

}
