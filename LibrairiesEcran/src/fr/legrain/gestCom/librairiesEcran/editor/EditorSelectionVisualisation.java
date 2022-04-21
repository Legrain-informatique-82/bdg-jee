package fr.legrain.gestCom.librairiesEcran.editor;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.part.EditorPart;

import fr.legrain.gestCom.librairiesEcran.editor.IContexteVisualisationId;
import fr.legrain.gestCom.librairiesEcran.swt.EJBBaseControllerSelection;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBLgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrRetourEditeur;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.lib.gui.PaSelectionVisualisation;

public class EditorSelectionVisualisation extends EJBLgrEditorPart implements ILgrEditor, ILgrRetourEditeur, IContexteVisualisationId  {
	
	static Logger logger = Logger.getLogger(EditorSelectionVisualisation.class.getName());

	public static final String ID = "fr.legrain.gestCom.librairiesEcran.editor.EditorSelectionVisualisation";
	
	private boolean enabled = true;
	private PaSelectionVisualisation composite = null;
	private EntityManager em = null;
	
	//private PaArticleSWT composite = null;
	public EditorSelectionVisualisation() {
		super();
	}
	
	public EditorSelectionVisualisation(EditorPart parent) {
		super(parent);
	}
	
	public EditorSelectionVisualisation(EditorPart parent,EntityManager em) {
		super(parent);
		this.em=em;
	}
	
	@Override
	protected void initEditor() {
		setPanel(composite);
	}


	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
	    setInput(input);
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(LgrPartListener.getLgrPartListener());
	   
	    IContextService ics = (IContextService)getSite().getService(IContextService.class);
	    IContextActivation iac = ics.activateContext(C_CONTEXT_ID_VISUALISATION);

	}


	@Override
	public void createPartControl(Composite parent) {
		composite = new PaSelectionVisualisation(parent,SWT.NONE);

		if(getControllerSelection()==null) {
//			setControllerSelection(new BaseControllerSelection(composite,"V2_BONLIV","PaEditorBLController"));
			setControllerSelection(new EJBBaseControllerSelection(composite));
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		composite.setEnabled(enabled);
		this.enabled = enabled;
	}

	public boolean validate() {
		return true;
	}

//	public selectionVisualisation getComposite() {
//		return composite;
//	}
	public PaSelectionVisualisation getComposite() {
		return composite;
	}
	public boolean canLeaveThePage() {
		return true;
	}

	public Object retour() {
		return null;
	}

	public void utiliseRetour(Object r) {

	}

	@Override
	protected String getID() {
		return ID;
	}
	

}
