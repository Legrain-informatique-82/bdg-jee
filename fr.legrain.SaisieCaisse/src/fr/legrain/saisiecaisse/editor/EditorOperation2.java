package fr.legrain.saisiecaisse.editor;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import fr.legrain.gestCom.librairiesEcran.workbench.ILgrEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrRetourEditeur;
import fr.legrain.gestCom.librairiesEcran.workbench.JPALgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;
import fr.legrain.saisiecaisse.controller.PaOperationController;
import fr.legrain.saisiecaisse.ecran.PaOperation;

public class EditorOperation2 extends JPALgrEditorPart implements ILgrEditor, ILgrRetourEditeur {
	
	static Logger logger = Logger.getLogger(EditorOperation2.class.getName());
	
	public static final String ID = "fr.legrain.saisiecaisse.editor.EditorOperation";
	
	private boolean enabled = true;
	private PaOperation composite = null;
	private EntityManager em = null;
	
	public EditorOperation2() {
		super();
	}
	public EditorOperation2(EditorPart parent) {
		super(parent);
	}
	
	public EditorOperation2(EditorPart parent,EntityManager em) {
		super(parent);
		this.em=em;
	}
	
	@Override
	protected void initEditor() {
		setPanel(composite);
	}
	
	@Override
	protected String getID() {
		return ID;
	}


	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
	    setInput(input);
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().
	    getPartService().addPartListener(LgrPartListener.getLgrPartListener());
	} 

	@Override
	public void createPartControl(Composite parent) {
		composite = new PaOperation(parent,SWT.NONE);
		if(getController()==null) {
			setController(new PaOperationController(composite,em));
		} else {
			getController().setEm(em);
		}
		messageEditeur(composite);
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		composite.setEnabled(enabled);
		this.enabled = enabled;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	public PaOperation getComposite() {
		return composite;
	}

	public boolean canLeaveThePage() {
		// TODO Auto-generated method stub
		return true;
	}

	public Object retour() {
		// TODO Auto-generated method stub
		//return c.getText1().getText();
		return null;
	}

	public void utiliseRetour(Object r) {
		// TODO Auto-generated method stub
		//c.getText2().setText(r.toString());
	}


	

}
