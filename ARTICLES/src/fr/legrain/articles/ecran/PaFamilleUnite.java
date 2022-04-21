package fr.legrain.articles.ecran;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.TextControlCreator;
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
public class PaFamilleUnite extends fr.legrain.lib.gui.DefaultFrameFormulaireSWT {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	private Composite paCorpsFormulaire;
	private Label laCODE_FAMILLE;
	private Label laLIBC_FAMILLE;
	private Label laLIBL_FAMILLE;

	private Text tfLIBC_FAMILLE;
	private Text tfCODE_FAMILLE;
	private Text tfLIBL_FAMILLE;

	private ControlDecoration fieldLIBC_FAMILLE;
	private ControlDecoration fieldCODE_FAMILLE;
	private ControlDecoration fieldLIBL_FAMILLE;

	
	final private boolean decore = LgrMess.isDECORE();

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
		PaFamilleUnite inst = new PaFamilleUnite(shell, SWT.NULL);
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

	public PaFamilleUnite(org.eclipse.swt.widgets.Composite parent, int style) {
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
					laCODE_FAMILLE = new Label(paCorpsFormulaire, SWT.NONE);
					laCODE_FAMILLE.setText("Code");
				}
				{
					GridData tfCODE_FAMILLELData = new GridData();
					tfCODE_FAMILLELData.horizontalAlignment = GridData.CENTER;
					tfCODE_FAMILLELData.widthHint = 177;
//					if(!decore) {
						tfCODE_FAMILLE = new Text(paCorpsFormulaire, SWT.BORDER);
						tfCODE_FAMILLE.setLayoutData(tfCODE_FAMILLELData);
						fieldCODE_FAMILLE = new ControlDecoration(tfCODE_FAMILLE, SWT.TOP | SWT.RIGHT);
//					} else {					
//					fieldCODE_FAMILLE = new ControlDecoration(paCorpsFormulaire, SWT.BORDER, new TextControlCreator());
//					tfCODE_FAMILLE = (Text)fieldCODE_FAMILLE.getControl();
//					fieldCODE_FAMILLE.getLayoutControl().setLayoutData(tfCODE_FAMILLELData);
//					}
				}
				{
					laLIBC_FAMILLE = new Label(paCorpsFormulaire, SWT.NONE);
					laLIBC_FAMILLE.setText("Libellé");
				}
				{
					GridData tfLIBC_FAMILLELData = new GridData();
					tfLIBC_FAMILLELData.horizontalAlignment = GridData.FILL;
//					if(!decore) {
						tfLIBC_FAMILLE = new Text(paCorpsFormulaire, SWT.BORDER);
						tfLIBC_FAMILLE.setLayoutData(tfLIBC_FAMILLELData);
						fieldLIBC_FAMILLE = new ControlDecoration(tfLIBC_FAMILLE, SWT.TOP | SWT.RIGHT);
//					} else {					
//					fieldLIBC_FAMILLE = new ControlDecoration(paCorpsFormulaire, SWT.BORDER, new TextControlCreator());
//					tfLIBC_FAMILLE = (Text)fieldLIBC_FAMILLE.getControl();
//					fieldLIBC_FAMILLE.getLayoutControl().setLayoutData(tfLIBC_FAMILLELData);
//					}
				}
				{
					laLIBL_FAMILLE = new Label(paCorpsFormulaire, SWT.NONE);
					laLIBL_FAMILLE.setText("Description");
				}
				{
					GridData tfLIBL_FAMILLELData = new GridData();
					tfLIBL_FAMILLELData.horizontalAlignment = GridData.FILL;
//					if(!decore) {
						tfLIBL_FAMILLE = new Text(paCorpsFormulaire, SWT.BORDER);
						tfLIBL_FAMILLE.setLayoutData(tfLIBL_FAMILLELData);
						fieldLIBL_FAMILLE = new ControlDecoration(tfLIBL_FAMILLE, SWT.TOP | SWT.RIGHT);
//					} else {					
//					fieldLIBL_FAMILLE = new ControlDecoration(paCorpsFormulaire, SWT.BORDER, new TextControlCreator());
//					tfLIBL_FAMILLE = (Text)fieldLIBL_FAMILLE.getControl();
//					fieldLIBL_FAMILLE.getLayoutControl().setLayoutData(tfLIBL_FAMILLELData);
//					}
				}
			}

			this.setLayout(new GridLayout());
			GridLayout compositeFormLayout = new GridLayout();
			compositeFormLayout.numColumns = 2;
			this.setSize(646, 690);
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

	public Label getLaLIBC_FAMILLE() {
		return laLIBC_FAMILLE;
	}

	public Label getLaCODE_FAMILLE() {
		return laCODE_FAMILLE;
	}

	public Label getLaLIBL_FAMILLE() {
		return laLIBL_FAMILLE;
	}

	public Text getTfLIBC_FAMILLE() {
		return tfLIBC_FAMILLE;
	}

	public Text getTfLIBL_FAMILLE() {
		return tfLIBL_FAMILLE;
	}

	public Text getTfCODE_FAMILLE() {
		return tfCODE_FAMILLE;
	}

	public void setPaCorpsFormulaire(Composite paCorpsFormulaire) {
		this.paCorpsFormulaire = paCorpsFormulaire;
	}

	public ControlDecoration getFieldLIBC_FAMILLE() {
		return fieldLIBC_FAMILLE;
	}

	public ControlDecoration getFieldLIBL_FAMILLE() {
		return fieldLIBL_FAMILLE;
	}

	public ControlDecoration getFieldCODE_FAMILLE() {
		return fieldCODE_FAMILLE;
	}


}
