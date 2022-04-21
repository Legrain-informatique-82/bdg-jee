/**
 * DocumentsController.java
 */
package fr.legrain.document.etat.boncde.controllers;

import java.util.HashMap;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Control;

import fr.legrain.document.etat.devis.ecrans.PaFormPage;
import fr.legrain.gestCom.librairiesEcran.swt.AbstractControllerMini;
import fr.legrain.lib.data.ModelObject;

/**
 * Classe controller de la section documents
 * @author nicolas
 *
 */
public abstract class TotauxControllerMini extends AbstractControllerMini{
	static Logger logger = Logger.getLogger(TotauxControllerMini.class.getName());	

	private Class objetIHM = null;
	protected FormPageControllerBoncde masterController = null;
	protected PaFormPage vue = null;

	/* Constructeur */
	public TotauxControllerMini(FormPageControllerBoncde masterContoller, PaFormPage vue, EntityManager em) {
		this.vue = vue;
		this.masterController = masterContoller;
	}

	public abstract void initialiseModelIHM(); 

	@Override
	public void bind(){
		if(mapComposantChamps==null) {
			initMapComposantChamps();
		}
		setObjetIHM(DocIHM.class);
		bindForm(mapComposantChamps, DocIHM.class, getSelectedObject(), vue.getSctnDocuments().getDisplay());
	}


	@Override
	protected void initMapComposantChamps() {
		mapComposantChamps = new HashMap<Control,String>();
		mapComposantChamps.put(vue.getCompositeSectionDoc().getLblNew1Content(), "document1");
		mapComposantChamps.put(vue.getCompositeSectionDoc().getLblNew2Content(), "document2");
		mapComposantChamps.put(vue.getCompositeSectionDoc().getLblNew3Content(), "document3");
		mapComposantChamps.put(vue.getCompositeSectionDoc().getLblNew4Content(), "document4");
		mapComposantChamps.put(vue.getCompositeSectionDoc().getLblNew5Content(), "document5");
		mapComposantChamps.put(vue.getCompositeSectionDoc().getLblNew6(), "document6");
	}
	


	/**
	 * Classe permettant l'affichage des informations documents
	 * @version 1.1 : généralisation, possibilité d'utilisation pour section factures par exemple
	 * @version 1.2 : ne prend que des String pour l'affichage
	 */
	public class DocIHM extends ModelObject {
		String document1 = null;
		String document2 = null;
		String document3 = null;
		String document4 = null;
		String document5 = null;
		String document6 = null;

		public DocIHM() {}
		
		public DocIHM(String document1) {
			this(document1,null,null,null,null,null);
		}
		
		public DocIHM(String document1, String document2) {
			this(document1,document2,null,null,null,null);
		}
		
		public DocIHM(String document1, String document2, String document3) {
			this(document1,document2,document3,null,null,null);
		}

		public DocIHM(String document1, String document2, String document3, String document4) {
			this(document1,document2,document3,document4,null,null);
		}
		
		public DocIHM(String document1, String document2, String document3, String document4, String document5) {
			this(document1,document2,document3,document4,document5,null);
		}
		
		public DocIHM(String document1, String document2, String document3, String document4, String document5, String document6) {
			super();
			this.document1 = document1;
			this.document2 = document2;
			this.document3 = document3;
			this.document4 = document4;
			this.document5 = document5;
			this.document6 = document6;
		}

		public String getDocument1() {
			return document1;
		}

		public void setDocument1(String document1) {
			firePropertyChange("document1", this.document1, this.document1 = document1);
		}

		public String getDocument2() {
			return document2;
		}

		public void setDocument2(String document2) {
			firePropertyChange("document2", this.document2, this.document2 = document2);
		}

		public String getDocument3() {
			return document3;
		}

		public void setDocument3(String document3) {
			firePropertyChange("document3", this.document3, this.document3 = document3);
		}
		
		public String getDocument4() {
			return document4;
		}

		public void setDocument4(String document4) {
			firePropertyChange("document4", this.document4, this.document4 = document4);
		}
		
		public String getDocument5() {
			return document5;
		}

		public void setDocument5(String document5) {
			firePropertyChange("document5", this.document5, this.document5 = document5);
		}
		
		public String getDocument6() {
			return document6;
		}

		public void setDocument6(String document6) {
			firePropertyChange("document6", this.document6, this.document6 = document6);
		}
	}

}
