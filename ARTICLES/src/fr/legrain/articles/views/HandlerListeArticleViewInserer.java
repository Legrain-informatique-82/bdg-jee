package fr.legrain.articles.views;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import fr.legrain.articles.editor.ArticleMultiPageEditor;
import fr.legrain.gestCom.librairiesEcran.swt.LgrAbstractHandler;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBAbstractLgrMultiPageEditor;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.tiers.editor.TiersMultiPageEditor;

public class HandlerListeArticleViewInserer extends LgrAbstractHandler {
	
	static Logger logger = Logger.getLogger(HandlerListeArticleViewInserer.class.getName());

	public Object execute(ExecutionEvent event) throws ExecutionException{
		try {
			
			IViewReference[] vr = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
			ISelection selection = null;
			for (int i = 0; i < vr.length; i++) {
				if(vr[i].getId().endsWith(ListeArticleView.ID)) {
//					selection = ((ListeTiersView)vr[i].getView(false)).getController().getView().getViewSite().getSelectionProvider().getSelection();
//					ParamAffiche param = new ParamAffiche();
//					param.setSelection(selection);
//					AbstractLgrMultiPageEditor.ouvreFiche(null, TiersMultiPageEditor.ID_EDITOR,param,false);
					
					ParamAffiche param = new ParamAffiche();
					param.setModeEcran(EnumModeObjet.C_MO_INSERTION);
					
					IEditorPart editor = EJBAbstractLgrMultiPageEditor.chercheEditeurDocumentOuvert(ArticleMultiPageEditor.ID_EDITOR);
					if(editor==null){
//						IEditorPart e = null;
//
//						e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
//								openEditor(new EditorInputTiers(), TiersMultiPageEditor.ID_EDITOR);

//						LgrPartListener.getLgrPartListener().getLgrNavigation().add(e);
						
//						((AbstractLgrMultiPageEditor)e).findMasterController().configPanel(param);
						EJBAbstractLgrMultiPageEditor.ouvreFiche(null,null, ArticleMultiPageEditor.ID_EDITOR,param,false);
					} else {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(editor);
						((EJBAbstractLgrMultiPageEditor)editor).findMasterController().configPanel(param);
					}
				}
				break;
			}
			
		} catch (Exception e) {
			logger.error("",e);
		}
		return event;
	}
} 
