package fr.legrain.abonnement.editors;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import fr.legrain.abonnement.controllers.SWTPaAbonnementController;
import fr.legrain.abonnement.controllers.SWTPaListeAbonnementController;
import fr.legrain.abonnement.ecrans.PaAbonnementSWT;
import fr.legrain.abonnement.ecrans.PalisteAbonnementSWT;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrEditor;
import fr.legrain.gestCom.librairiesEcran.workbench.ILgrRetourEditeur;
import fr.legrain.gestCom.librairiesEcran.workbench.JPALgrEditorPart;
import fr.legrain.gestCom.librairiesEcran.workbench.LgrPartListener;

public class EditorListeAbonnement extends JPALgrEditorPart implements ILgrEditor, ILgrRetourEditeur {
	
	static Logger logger = Logger.getLogger(EditorListeAbonnement.class.getName());
	
	public static final String ID = "fr.legrain.listeAbonnement";
	
	private boolean enabled = true;
	private PalisteAbonnementSWT composite = null;

	private EntityManager em = null;

	public EditorListeAbonnement() {
		super();
	}
	
	public EditorListeAbonnement(EditorPart parent) {
		super(parent);
	}
	
	public EditorListeAbonnement(EditorPart parent,EntityManager em) {
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
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(LgrPartListener.getLgrPartListener());
	}



	@Override
	public void createPartControl(Composite parent) {
		composite = new PalisteAbonnementSWT(parent,SWT.NONE);
		if(getController()==null) {
			setController(new SWTPaListeAbonnementController(composite,em));
		} else {
			getController().setEm(em);
		}
		messageEditeur(composite);
	}

//	@Override
//	public void setFocus() {
//		// TODO Auto-generated method stub
//	}

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

	public PalisteAbonnementSWT getComposite() {
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
	
	@Override
	public String getTitle() {
		return "Gestion des abonnements";
	}
}
