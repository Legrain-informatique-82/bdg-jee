package fr.legrain.stocks.handlers;
import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;


import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
import fr.legrain.gestCom.librairiesEcran.swt.LgrShellUtil;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.lib.data.MessageDialogLGR;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.lib.gui.ParamAfficheSWT;
import fr.legrain.stocks.controllers.PaCalculReportController;
import fr.legrain.stocks.divers.ParamAfficheStocks;
import fr.legrain.stocks.ecran.PaCalculReportSWT;
import fr.legrain.stocks.editor.EditorInputStocks;
import fr.legrain.stocks.editor.EditorStocks;


public class HandlerCalculReportStocks extends LgrAbstractHandler {
	
	static Logger logger = Logger.getLogger(HandlerCalculReportStocks.class.getName());

	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {								
			Shell s = new Shell(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),LgrShellUtil.styleLgr);		
			if(MessageDialogLGR.openQuestion(Workbench.getInstance()
					.getActiveWorkbenchWindow().getShell(),
					"Calcul des reports de stocks", "Etes-vous sûr de vouloir recalculer les reports de stocks ?",1)){
				PaCalculReportSWT paCalculReport = new PaCalculReportSWT(s,SWT.NULL);
				PaCalculReportController paCalculReportController = new PaCalculReportController(paCalculReport);
				ParamAffiche paramCalculReport= new ParamAffiche();
				paramCalculReport.setFocusSWT(paCalculReport.getLaDATEFIN());
				ParamAfficheSWT paramAfficheSWT = new ParamAfficheSWT();
				paramAfficheSWT.setHauteur(150);
				paramAfficheSWT.setLargeur(400);
				paramAfficheSWT.setTitre("Choix de la date fin des reports de stocks.");
				LgrShellUtil.afficheSWT(paramCalculReport, paramAfficheSWT, paCalculReport, paCalculReportController, s);
				if(paramCalculReport.getFocus()!=null)
					paramCalculReport.getFocusDefaut().requestFocus();
			}
		} catch (Exception e1) {
			logger.error("Erreur : actionPerformed", e1);
		}
		return event;
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
