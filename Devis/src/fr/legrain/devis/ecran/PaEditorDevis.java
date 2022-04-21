package fr.legrain.devis.ecran;
//import com.cloudgarden.resource.SWTResourceManager;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.TextControlCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import fr.legrain.lib.gui.PaBtnAvecAssistant;
import fr.legrain.lib.gui.fieldassist.ButtonControlCreator;
import fr.legrain.lib.gui.fieldassist.DateTimeControlCreator;
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
public class PaEditorDevis extends org.eclipse.swt.widgets.Composite {

//	{
//		//Register as a resource user - SWTResourceManager will
//		//handle the obtaining and disposing of resources
//		SWTResourceManager.registerResourceUser(this);
//	}

	private Group paEntete;
	private Label laDATE_LIV_DOCUMENT;
	private Button cbTTC;
	private Label laMessage;
	private Label laTTC;
	private Text tfCODE_TIERS;
	private Label laCODE_TIERS;
	private Text tfLIBELLE_DOCUMENT;
	private Label laLIBELLE_DOCUMENT;
	//private Text tfDATE_LIV_DEVIS;
	//private Text tfDATE_ECH_DEVIS;
	private Label laDATE_ECH_DOCUMENT;
	//private Text tfDATE_DEVIS;
	private Label laDATE_DOCUMENT;
	private Text tfCODE_DOCUMENT;
	private Label laCODE_DOCUMENT;
	
//
//	private Text tfNOM_TIERS;
//	private Label laNOM_TIERS;
	
	private Label laLocalisationTVA;
	private Combo comboLocalisationTVA;

	private DateTime dateTimeDATE_LIV_DOCUMENT;
	private DateTime dateTimeDATE_ECH_DOCUMENT;
	private DateTime dateTimeDATE_DOCUMENT;

	private ControlDecoration fieldTTC;
	private ControlDecoration fieldCODE_TIERS;
	private ControlDecoration fieldLIBELLE_DOCUMENT;
	private ControlDecoration fieldDATE_LIV_DOCUMENT;
	private ControlDecoration fieldDATE_ECH_DOCUMENT;
	private ControlDecoration fieldDATE_DOCUMENT;
	private ControlDecoration fieldCODE_DOCUMENT;
//	private ControlDecoration fieldNOM_TIERS;
	
	private ExpandBar expandBar;
	private PaBtnAvecAssistant paBtnAvecAssistant;

	final private boolean decore = LgrMess.isDECORE();
//	final private boolean decore = false;

	/**
	 * Auto-generated main method to display this 
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */
	public static void main(String[] args) {
		showGUI();
	}

	/**
	 * Auto-generated method to display this 
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		PaEditorDevis inst = new PaEditorDevis(shell, SWT.NULL);
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

	public PaEditorDevis(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			this.setLayout(new GridLayout());
			this.setSize(1059, 715);
			{
				paEntete = new Group(this, SWT.NONE);
				GridLayout PaEnteteLayout = new GridLayout();
				PaEnteteLayout.numColumns = 8;
				paEntete.setLayout(PaEnteteLayout);
				GridData PaEnteteLData = new GridData();
				//PaEnteteLData.heightHint = 107;
				PaEnteteLData.verticalAlignment = GridData.BEGINNING;
				PaEnteteLData.horizontalAlignment = GridData.FILL;
				PaEnteteLData.grabExcessHorizontalSpace = true;
				paEntete.setLayoutData(PaEnteteLData);
				paEntete.setText("Entête du devis");
				{
					laCODE_DOCUMENT = new Label(paEntete, SWT.NONE);
					laCODE_DOCUMENT.setText("Code Devis");
				}
				{


					GridData tfCODE_FACTURELData = new GridData();
					tfCODE_FACTURELData.horizontalAlignment = GridData.FILL;
//					if(!decore) {
						tfCODE_DOCUMENT = new Text(paEntete, SWT.BORDER);
						tfCODE_DOCUMENT.setLayoutData(tfCODE_FACTURELData);
						tfCODE_DOCUMENT.setText("CODE_DEVIS ");
						fieldCODE_DOCUMENT = new ControlDecoration(tfCODE_DOCUMENT, SWT.TOP | SWT.RIGHT);
//					} else {
//						fieldCODE_DOCUMENT = new ControlDecoration(paEntete, SWT.BORDER, new TextControlCreator());
//						tfCODE_DOCUMENT = (Text)fieldCODE_DOCUMENT.getControl();
//						fieldCODE_DOCUMENT.getLayoutControl().setLayoutData(tfCODE_FACTURELData);
//					}
					tfCODE_DOCUMENT.setSize(200, 17);
				}				
				{
					laCODE_TIERS = new Label(paEntete, SWT.NONE);
					laCODE_TIERS.setText("Code tiers");
				}
				{
					GridData tfCODE_TIERSLData = new GridData();
					tfCODE_TIERSLData.widthHint = 208;
//					if(!decore) {
						tfCODE_TIERS = new Text(paEntete, SWT.BORDER);
						tfCODE_TIERS.setLayoutData(tfCODE_TIERSLData);
						tfCODE_TIERS.setText("CODE_TIERS");
						fieldCODE_TIERS = new ControlDecoration(tfCODE_TIERS, SWT.TOP | SWT.RIGHT);
//					} else {
//						fieldCODE_TIERS = new ControlDecoration(paEntete, SWT.BORDER, new TextControlCreator());
//						tfCODE_TIERS = (Text)fieldCODE_TIERS.getControl();
//						fieldCODE_TIERS.getLayoutControl().setLayoutData(tfCODE_TIERSLData);
//					}
				}
//				{
//					laNOM_TIERS = new Label(paEntete, SWT.NONE);
//					GridData laNOM_TIERSLData = new GridData();
//					laNOM_TIERSLData.horizontalAlignment = GridData.END;
//					laNOM_TIERS.setLayoutData(laNOM_TIERSLData);
//					laNOM_TIERS.setText("Nom du Tiers");
//				}
//				{
//					GridData tfNOMLData = new GridData();
//					tfNOMLData.horizontalAlignment = GridData.FILL;
//					tfNOMLData.widthHint = 180;
////					tfNOMLData.horizontalSpan = 1;
//					//tfNOMLData.grabExcessHorizontalSpace = true;
//					if(!decore) {
//						tfNOM_TIERS = new Text(paEntete, SWT.BORDER);
//						tfNOM_TIERS.setLayoutData(tfNOMLData);
//						tfNOM_TIERS.setText("NOM TIERS");
//					} else {
//						fieldNOM_TIERS = new ControlDecoration(paEntete, SWT.BORDER, new TextControlCreator());
//						tfNOM_TIERS = (Text)fieldNOM_TIERS.getControl();
//						fieldNOM_TIERS.getLayoutControl().setLayoutData(tfNOMLData);
//					}
//				}				
				{
					laDATE_DOCUMENT = new Label(paEntete, SWT.NONE);
					laDATE_DOCUMENT.setText("Date devis");
				}
				{
					GridData dateTimeDATE_DEVISLData = new GridData();
					dateTimeDATE_DEVISLData.widthHint = 90;
					dateTimeDATE_DEVISLData.horizontalSpan = 1;
					dateTimeDATE_DEVISLData.verticalAlignment = SWT.LEFT;

//					if(!decore) {
						dateTimeDATE_DOCUMENT = new DateTime(paEntete, SWT.BORDER | SWT.DROP_DOWN);
						dateTimeDATE_DOCUMENT.setLayoutData(dateTimeDATE_DEVISLData);
						fieldDATE_DOCUMENT = new ControlDecoration(dateTimeDATE_DOCUMENT, SWT.TOP | SWT.RIGHT);
//					} else {
//						fieldDATE_DOCUMENT = new ControlDecoration(paEntete, SWT.BORDER | SWT.DROP_DOWN, new DateTimeControlCreator());
//						dateTimeDATE_DOCUMENT = (DateTime)fieldDATE_DOCUMENT.getControl();
//						fieldDATE_DOCUMENT.getLayoutControl().setLayoutData(dateTimeDATE_DEVISLData);
//					}
//						dateTimeDATE_DOCUMENT.setPattern("dd/MM/yyyy");	
				}
				{
					Label laVide = new Label(paEntete, SWT.NONE);
					laVide.setText("");

					GridData laVideLData = new GridData();
					laVideLData.widthHint = 90;
					laVideLData.horizontalSpan = 2;
					//laVideLData.verticalAlignment = GridData.FILL;
					
					laVide.setLayoutData(laVideLData);
				}
				{
					laLIBELLE_DOCUMENT = new Label(paEntete, SWT.NONE);
					laLIBELLE_DOCUMENT.setText("Libellé");
				}
				{
					GridData tfLIBELLE_FACTURELData = new GridData();
					tfLIBELLE_FACTURELData.horizontalSpan = 5;
					tfLIBELLE_FACTURELData.horizontalAlignment = GridData.FILL;
					tfLIBELLE_FACTURELData.grabExcessHorizontalSpace = true;
//					if(!decore) {
						tfLIBELLE_DOCUMENT = new Text(paEntete, SWT.BORDER);
						tfLIBELLE_DOCUMENT.setLayoutData(tfLIBELLE_FACTURELData);
						tfLIBELLE_DOCUMENT.setText("LIBELLE_DEVIS");
						fieldLIBELLE_DOCUMENT = new ControlDecoration(tfLIBELLE_DOCUMENT, SWT.TOP | SWT.RIGHT);
//					} else {
//						fieldLIBELLE_DOCUMENT = new ControlDecoration(paEntete, SWT.BORDER, new TextControlCreator());
//						tfLIBELLE_DOCUMENT = (Text)fieldLIBELLE_DOCUMENT.getControl();
//						fieldLIBELLE_DOCUMENT.getLayoutControl().setLayoutData(tfLIBELLE_FACTURELData);
//					}
					tfLIBELLE_DOCUMENT.setSize(199, 17);
				}
				{
					laTTC = new Label(paEntete, SWT.NONE);
					laTTC.setText("TTC");
				}
				{
					GridData cbTTCLData = new GridData();
					cbTTCLData.widthHint = 90;
					//cbTTCLData.horizontalSpan = 3;
					cbTTCLData.verticalAlignment = GridData.FILL;
//					if(!decore) {
						cbTTC = new Button(paEntete, SWT.CHECK | SWT.LEFT);
						cbTTC.setLayoutData(cbTTCLData);
						fieldTTC = new ControlDecoration(cbTTC, SWT.TOP | SWT.RIGHT);
//					} else {
//						 = new ControlDecoration(paEntete, SWT.CHECK | SWT.LEFT, new ButtonControlCreator());
//						cbTTC = (Button)fieldTTC.getControl();
//						fieldTTC.getLayoutControl().setLayoutData(cbTTCLData);
//					}
				}
				{
					laDATE_ECH_DOCUMENT = new Label(paEntete, SWT.NONE);
					laDATE_ECH_DOCUMENT.setText("Date de validité");
				}
				{
					GridData dateTimeDATE_ECH_DEVISLData = new GridData();
					dateTimeDATE_ECH_DEVISLData.widthHint = 90;
					dateTimeDATE_ECH_DEVISLData.verticalAlignment = GridData.FILL;

//				if(!decore) {
					dateTimeDATE_ECH_DOCUMENT = new DateTime(paEntete, SWT.BORDER | SWT.DROP_DOWN);
					dateTimeDATE_ECH_DOCUMENT.setLayoutData(dateTimeDATE_ECH_DEVISLData);
					fieldDATE_ECH_DOCUMENT = new ControlDecoration(dateTimeDATE_ECH_DOCUMENT, SWT.TOP | SWT.RIGHT);
//					} else {
//					fieldDATE_ECH_DOCUMENT = new ControlDecoration(paEntete, SWT.BORDER | SWT.DROP_DOWN, new DateTimeControlCreator());
//					dateTimeDATE_ECH_DOCUMENT = (DateTime)fieldDATE_ECH_DOCUMENT.getControl();
//					fieldDATE_ECH_DOCUMENT.getLayoutControl().setLayoutData(dateTimeDATE_ECH_DEVISLData);
//					}
//					dateTimeDATE_ECH_DOCUMENT.setPattern("dd/MM/yyyy");	
				}
				{
					laDATE_LIV_DOCUMENT = new Label(paEntete, SWT.NONE);
					laDATE_LIV_DOCUMENT.setText("Date livraison");
				}
				{
					GridData dateTimeDATE_LIV_DEVISLData = new GridData();
					dateTimeDATE_LIV_DEVISLData.widthHint = 90;
					dateTimeDATE_LIV_DEVISLData.verticalAlignment = GridData.FILL;

//				if(!decore) {
					dateTimeDATE_LIV_DOCUMENT = new DateTime(paEntete, SWT.BORDER | SWT.DROP_DOWN);
					dateTimeDATE_LIV_DOCUMENT.setLayoutData(dateTimeDATE_LIV_DEVISLData);
					fieldDATE_LIV_DOCUMENT = new ControlDecoration(dateTimeDATE_LIV_DOCUMENT, SWT.TOP | SWT.RIGHT);
//					} else {
//					fieldDATE_LIV_DOCUMENT = new ControlDecoration(paEntete, SWT.BORDER | SWT.DROP_DOWN, new DateTimeControlCreator());
//					dateTimeDATE_LIV_DOCUMENT = (DateTime)fieldDATE_LIV_DOCUMENT.getControl();
//					fieldDATE_LIV_DOCUMENT.getLayoutControl().setLayoutData(dateTimeDATE_LIV_DEVISLData);
//					}
//					dateTimeDATE_LIV_DOCUMENT.setPattern("dd/MM/yyyy");	
				}
				
				{
					laLocalisationTVA = new Label(paEntete, SWT.NONE);
					GridData laTTCLData = new GridData();
					//laTTCLData.horizontalAlignment = GridData.END;
					//laTTCLData.grabExcessHorizontalSpace = true;
					laLocalisationTVA.setLayoutData(laTTCLData);
					laLocalisationTVA.setText("Localisation TVA");
				}
				{
					GridData comboTVALData = new GridData();
					comboTVALData.widthHint = 180;
					comboTVALData.horizontalSpan = 3;
//					if(!decore) {
						comboLocalisationTVA = new Combo(paEntete, SWT.DROP_DOWN | SWT.READ_ONLY);
						comboLocalisationTVA.setLayoutData(comboTVALData);
//					} else {
//						fieldTTC = new ControlDecoration(paEntete, SWT.CHECK | SWT.LEFT, new ButtonControlCreator());
//						comboLocalisationTVA = (Combo)fieldTTC.getControl();
//					}
				}
			}

			{
				laMessage = new Label(this, SWT.NONE);
				GridData laMessageLData = new GridData();
				laMessageLData.horizontalAlignment = GridData.FILL;
				laMessageLData.grabExcessHorizontalSpace = true;
				laMessage.setLayoutData(laMessageLData);
			}
			{
				GridData expandBarLData = new GridData();
				expandBarLData.horizontalAlignment = GridData.FILL;
				expandBarLData.verticalAlignment = GridData.FILL;
				expandBarLData.grabExcessVerticalSpace = true;
				expandBar = new ExpandBar(this, SWT.V_SCROLL);
				expandBar.setLayoutData(expandBarLData);
			}
			{
				GridData paBtnAvecAssistantLData = new GridData();
				paBtnAvecAssistantLData.verticalAlignment = GridData.END;
				paBtnAvecAssistantLData.horizontalAlignment = GridData.CENTER;
				paBtnAvecAssistant = new PaBtnAvecAssistant(this, SWT.NONE);
				paBtnAvecAssistant.setLayoutData(paBtnAvecAssistantLData);
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Group getPaEntete() {
		return paEntete;
	}

	public Label getLaCODE_DOCUMENT() {
		return laCODE_DOCUMENT;
	}

	public Text getTfCODE_DOCUMENT() {
		return tfCODE_DOCUMENT;
	}

	public Label getLaDATE_DOCUMENT() {
		return laDATE_DOCUMENT;
	}



	public Label getLaDATE_ECH_DOCUMENT() {
		return laDATE_ECH_DOCUMENT;
	}



	public Label getLaDATE_LIV_DOCUMENT() {
		return laDATE_LIV_DOCUMENT;
	}



	public Label getLaLIBELLE_DOCUMENT() {
		return laLIBELLE_DOCUMENT;
	}

	public Text getTfLIBELLE_DOCUMENT() {
		return tfLIBELLE_DOCUMENT;
	}

	public Label getLaCODE_TIERS() {
		return laCODE_TIERS;
	}

	public Text getTfCODE_TIERS() {
		return tfCODE_TIERS;
	}

	public Label getLaTTC() {
		return laTTC;
	}

	public Button getCbTTC() {
		return cbTTC;
	}

	public DateTime getDateTimeDATE_ECH_DOCUMENT() {
		return dateTimeDATE_ECH_DOCUMENT;
	}

	public DateTime getDateTimeDATE_DOCUMENT() {
		return dateTimeDATE_DOCUMENT;
	}

	public DateTime getDateTimeDATE_LIV_DOCUMENT() {
		return dateTimeDATE_LIV_DOCUMENT;
	}


	public Label getLaMessage() {
		return laMessage;
	}

	public ControlDecoration getFieldCODE_DOCUMENT() {
		return fieldCODE_DOCUMENT;
	}

	public ControlDecoration getFieldCODE_TIERS() {
		return fieldCODE_TIERS;
	}

	public ControlDecoration getFieldDATE_ECH_DOCUMENT() {
		return fieldDATE_ECH_DOCUMENT;
	}

	public ControlDecoration getFieldDATE_DOCUMENT() {
		return fieldDATE_DOCUMENT;
	}

	public ControlDecoration getFieldDATE_LIV_DOCUMENT() {
		return fieldDATE_LIV_DOCUMENT;
	}

	public ControlDecoration getFieldLIBELLE_DOCUMENT() {
		return fieldLIBELLE_DOCUMENT;
	}

	public ControlDecoration getFieldTTC() {
		return fieldTTC;
	}

	public PaBtnAvecAssistant getPaBtnAvecAssistant() {
		return paBtnAvecAssistant;
	}

	public void setPaBtnAvecAssistant(PaBtnAvecAssistant paBtnAvecAssistant) {
		this.paBtnAvecAssistant = paBtnAvecAssistant;
	}
	
	public ExpandBar getExpandBar() {
		return expandBar;
	}
	
	public Label getLaLocalisationTVA() {
		return laLocalisationTVA;
	}

	public Combo getComboLocalisationTVA() {
		return comboLocalisationTVA;
	}

}
