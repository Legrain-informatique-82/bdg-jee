package fr.legrain.tiers.ecran;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.cloudgarden.resource.SWTResourceManager;

import fr.legrain.libMessageLGR.LgrMess;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PaTypeNoteTiersSWT extends fr.legrain.lib.gui.DefaultFrameFormulaireSWT {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	private Composite paCorpsFormulaire;
	private Label laCODE_T_NOTE_TIERS;
	private Label laLabelle_T_NOTE_TIERS;

	private Text tfCODE_T_NOTE_TIERS;
	private Text tfLIBELLE_T_NOTE_TIERS;

	private ControlDecoration fieldCODE_T_NOTE_TIERS;
	private ControlDecoration fieldLABELLE_T_NOTE_TIERS;
	
	final private boolean decore = LgrMess.isDECORE();
	//final private boolean decore = false;

	/**
	* Auto-generated main method to display this 
	* fr.legrain.lib.gui.DefaultFrameFormulaireSWT inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}
		
	/**
	* Auto-generated method to display this 
	* fr.legrain.lib.gui.DefaultFrameFormulaireSWT inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		PaTypeNoteTiersSWT inst = new PaTypeNoteTiersSWT(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public PaTypeNoteTiersSWT(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			{
				paCorpsFormulaire = new Composite(super.getPaFomulaire(), SWT.NONE);
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.numColumns = 2;
				GridData composite1LData = new GridData();
				composite1LData.horizontalAlignment = GridData.FILL;
				composite1LData.grabExcessHorizontalSpace = true;
				composite1LData.verticalAlignment = GridData.FILL;
				composite1LData.grabExcessVerticalSpace = true;
				paCorpsFormulaire.setLayoutData(composite1LData);
				paCorpsFormulaire.setLayout(composite1Layout);

				{
					laCODE_T_NOTE_TIERS = new Label(paCorpsFormulaire, SWT.NONE);
					laCODE_T_NOTE_TIERS.setText("Code Type Note");
				}
				{
					GridData tfCODE_T_NOTEData = new GridData();
					tfCODE_T_NOTEData.horizontalAlignment = GridData.CENTER;
					tfCODE_T_NOTEData.widthHint = 188;
					tfCODE_T_NOTEData.verticalAlignment = GridData.FILL;
//					if(!decore) {
						tfCODE_T_NOTE_TIERS = new Text(paCorpsFormulaire, SWT.BORDER);
						tfCODE_T_NOTE_TIERS.setLayoutData(tfCODE_T_NOTEData);
						fieldCODE_T_NOTE_TIERS = new ControlDecoration(tfCODE_T_NOTE_TIERS, SWT.TOP | SWT.RIGHT);
//					} else {					
//					fieldCODE_T_NOTE_TIERS = new DecoratedField(paCorpsFormulaire, SWT.BORDER, new TextControlCreator());
//					tfCODE_T_NOTE_TIERS = (Text)fieldCODE_T_NOTE_TIERS.getControl();
//					fieldCODE_T_NOTE_TIERS.getLayoutControl().setLayoutData(tfCODE_T_NOTEData);
//					}
				}
				{
					laLabelle_T_NOTE_TIERS = new Label(paCorpsFormulaire, SWT.NONE);
					laLabelle_T_NOTE_TIERS.setText("Libelle Type Note");
				}
				{
					GridData tfLABELLE_T_NOTEData = new GridData();
					tfLABELLE_T_NOTEData.horizontalAlignment = GridData.CENTER;
					tfLABELLE_T_NOTEData.widthHint = 188;
					tfLABELLE_T_NOTEData.verticalAlignment = GridData.FILL;
//					if(!decore) {
						tfLIBELLE_T_NOTE_TIERS = new Text(paCorpsFormulaire, SWT.BORDER);
						tfLIBELLE_T_NOTE_TIERS.setLayoutData(tfLABELLE_T_NOTEData);
						fieldLABELLE_T_NOTE_TIERS = new ControlDecoration(tfLIBELLE_T_NOTE_TIERS, SWT.TOP | SWT.RIGHT);
//					} else {					
//					fieldLABELLE_T_NOTE_TIERS = new DecoratedField(paCorpsFormulaire, SWT.BORDER, new TextControlCreator());
//					tfLIBELLE_T_NOTE_TIERS = (Text)fieldLABELLE_T_NOTE_TIERS.getControl();
//					fieldLABELLE_T_NOTE_TIERS.getLayoutControl().setLayoutData(tfLABELLE_T_NOTEData);
//					}
				}

//				paCorpsFormulaire
//					.setTabList(new org.eclipse.swt.widgets.Control[] {
//							tfLIBELLE_T_TIERS, tfCODE_T_CIVILITE, tfCODE_T_TIERS,
//							tfPRENOM_TIERS, tfSURNOM_TIERS, composite2,
//							tfCODE_T_CIVILITE, tfCODE_ENTREPRISE, tfCODE_T_TIERS, tfCOMPTE,
//							tfLIBL_COMMENTAIRE, tfCODE_BANQUE, tfEntite, composite3 });
			}
			
			this.setLayout(new GridLayout());
			GridLayout compositeFormLayout = new GridLayout();
			compositeFormLayout.numColumns = 2;
			this.setSize(913, 439);
			GridData paGrilleLData = new GridData();
			paGrilleLData.grabExcessHorizontalSpace = true;
			paGrilleLData.horizontalAlignment = GridData.FILL;
			paGrilleLData.verticalAlignment = GridData.FILL;
			paGrilleLData.grabExcessVerticalSpace = true;
			super.getCompositeForm().setLayout(compositeFormLayout);
			GridData paFomulaireLData = new GridData();
			paFomulaireLData.verticalAlignment = GridData.FILL;
			paFomulaireLData.grabExcessVerticalSpace = true;
			paFomulaireLData.horizontalAlignment = GridData.FILL;
			super.getPaGrille().setLayoutData(paGrilleLData);
			GridData compositeFormLData = new GridData();
			compositeFormLData.grabExcessHorizontalSpace = true;
			compositeFormLData.verticalAlignment = GridData.FILL;
			compositeFormLData.horizontalAlignment = GridData.FILL;
			compositeFormLData.grabExcessVerticalSpace = true;
			super.getPaFomulaire().setLayoutData(paFomulaireLData);
			GridData grilleLData = new GridData();
			grilleLData.verticalAlignment = GridData.FILL;
			grilleLData.horizontalAlignment = GridData.FILL;
			grilleLData.grabExcessVerticalSpace = true;
			super.getCompositeForm().setLayoutData(compositeFormLData);
			super.getGrille().setLayoutData(grilleLData);
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Composite getPaCorpsFormulaire() {
		return paCorpsFormulaire;
	}

	public Label getLaCODE_T_NOTE_TIERS() {
		return laCODE_T_NOTE_TIERS;
	}


	public Text getTfCODE_T_NOTE_TIERS() {
		return tfCODE_T_NOTE_TIERS;
	}



	public void setPaCorpsFormulaire(Composite paCorpsFormulaire) {
		this.paCorpsFormulaire = paCorpsFormulaire;
	}

	public ControlDecoration getFieldCODE_T_NOTE_TIERS() {
		return fieldCODE_T_NOTE_TIERS;
	}

	public Label getLaLabelle_T_NOTE_TIERS() {
		return laLabelle_T_NOTE_TIERS;
	}

	public void setLaLabelle_T_NOTE_TIERS(Label laLabelle_T_NOTE_TIERS) {
		this.laLabelle_T_NOTE_TIERS = laLabelle_T_NOTE_TIERS;
	}

	public Text getTfLIBELLE_T_NOTE_TIERS() {
		return tfLIBELLE_T_NOTE_TIERS;
	}

	public void setTfLIBELLE_T_NOTE_TIERS(Text tfLABELLE_T_NOTE_TIERS) {
		this.tfLIBELLE_T_NOTE_TIERS = tfLABELLE_T_NOTE_TIERS;
	}

	public ControlDecoration getFieldLABELLE_T_NOTE_TIERS() {
		return fieldLABELLE_T_NOTE_TIERS;
	}

	public void setFieldLABELLE_T_NOTE_TIERS(ControlDecoration fieldLABELLE_T_NOTE_TIERS) {
		this.fieldLABELLE_T_NOTE_TIERS = fieldLABELLE_T_NOTE_TIERS;
	}

	public void setLaCODE_T_NOTE_TIERS(Label laCODE_T_NOTE_TIERS) {
		this.laCODE_T_NOTE_TIERS = laCODE_T_NOTE_TIERS;
	}

	public void setTfCODE_T_NOTE_TIERS(Text tfCODE_T_NOTE_TIERS) {
		this.tfCODE_T_NOTE_TIERS = tfCODE_T_NOTE_TIERS;
	}

	public void setFieldCODE_T_NOTE_TIERS(ControlDecoration fieldCODE_T_NOTE_TIERS) {
		this.fieldCODE_T_NOTE_TIERS = fieldCODE_T_NOTE_TIERS;
	}



}
