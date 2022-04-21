package fr.legrain.devis.editor;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import fr.legrain.devis.controller.PaLigneDevisController;
import fr.legrain.devis.ecran.PaSaisieLigneDevis;
import fr.legrain.gestCom.librairiesEcran.workbench.EJBLgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrRetourEditeur;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;

public class EditorLigne extends EJBLgrEditorPart implements ILgrEditor, ILgrRetourEditeur {
	
	static Logger logger = Logger.getLogger(EditorLigne.class.getName());
	
	public static final String ID = "fr.legrain.editor.devis.EditorLigne";
	
	private boolean enabled = true;
	private PaSaisieLigneDevis composite = null;
	private EntityManager em = null;

	
	public EditorLigne(EditorPart parent) {
		super(parent);
	}
	
	public EditorLigne(EditorPart parent,EntityManager em) {
		super(parent);
		this.em=em;
	}

	public EditorLigne() {
		super();
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
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(LgrPartListener.getLgrPartListener());
	    
	}

	@Override
	public void createPartControl(Composite parent) {
		composite = new PaSaisieLigneDevis(parent,SWT.NONE);
		if(getController()==null) {
			setController(new PaLigneDevisController(composite,em));
		} else {
//			getController().setEm(em);
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
		try {
			getController().validateUI();
		} catch (Exception e) {
			return false;
		}
		if(!getController().changementPageValide())return false;
		return true;
	}

	public PaSaisieLigneDevis getComposite() {
		return composite;
	}

	public boolean canLeaveThePage() {
		// TODO Auto-generated method stub
		return validate();
	}

	public Object retour() {
		// TODO Auto-generated method stub

		return null;
	}

	public void utiliseRetour(Object r) {
		// TODO Auto-generated method stub

	}
	

}
