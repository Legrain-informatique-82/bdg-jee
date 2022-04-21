package fr.legrain.facture.ecran;
import com.cloudgarden.resource.SWTResourceManager;

import org.eclipse.jface.fieldassist.ControlDecoration;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import fr.legrain.articles.ecran.PaArticleSWT;
import fr.legrain.lib.gui.PaBtnAvecAssistant;
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
public class PaSaisieLigneSWT extends org.eclipse.swt.widgets.Composite {

	 {
		 //Register as a resource user - SWTResourceManager will
		 //handle the obtaining and disposing of resources
		 SWTResourceManager.registerResourceUser(this);
	 }
	 
	 private Label laNUM_LIGNE_L_FACTURE;
	private Text tfNUM_LIGNE_L_FACTURE;
	private Label laCODE_ARTICLE;
	private Label laLIB_L_FACTURE;
	private Text tfLIB_L_FACTURE;
	private Label laMT_TTC_L_FACTURE;
	private PaBtnAvecAssistant paBtnAvecAssistant;
	private Text tfMT_TTC_L_FACTURE;
	private Table grille;
	private Composite paSaisie;
	private Text tfREM_TX_L_FACTURE;
	private Label laREM_TX_L_FACTURE;
	private Text tfMT_HT_L_FACTURE;
	private Label laMT_HT_L_FACTURE;
	private Text tfPRIX_U_L_FACTURE;
	private Label laPRIX_U_L_FACTURE;
	private Text tfU2_L_FACTURE;
	private Label laU2_L_FACTURE;
	private Text tfU1_L_FACTURE;
	private Label laU1_L_FACTURE;
	private Text tfQTE_L_FACTURE;
	private Text tfQTE2_L_FACTURE;
	private Label laQTE_L_FACTURE;
	private Label laQTE2_L_FACTURE;
	private Text tfCODE_ARTICLE;
	
	private Label laMT_TVA_AVANT_REMISE;
	private Text tfMT_TVA_AVANT_REMISE;
	
//	private Text tfNET_TVA_CALC;
//	private Label laNET_TVA_CALC;
	private Text tfMT_TTC_CALC;
	private Label laMT_TTC_CALC;
	private Text tfMT_HT_CALC;
	private Label laMT_HT_CALC;
//	private Text tfNET_HT_CALC;
//	private Label laNET_HT_CALC;
//	private Text tfNET_TTC_CALC;
//	private Label laNET_TTC_CALC;	
	
	private Label laTitreTransportU1 = null;
	private Label laQteTitreTransportU1 = null;
	private Text tfTitreTransportU1 = null;
	private Text tfQteTitreTransportU1 = null;
	private ControlDecoration fieldTitreTransportU1 = null;
	private ControlDecoration fieldQteTitreTransportU1 = null;

	private ControlDecoration fieldNUM_LIGNE_L_FACTURE;
	private ControlDecoration fieldLIB_L_FACTURE;
	private ControlDecoration fieldREM_TX_L_FACTURE;
	private ControlDecoration fieldMT_HT_L_FACTURE;
	private Group paTotauxLignes;
	private ControlDecoration fieldMT_TTC_L_FACTURE;
	private ControlDecoration fieldPRIX_U_L_FACTURE;
	private ControlDecoration fieldU2_L_FACTURE;
	private ControlDecoration fieldU1_L_FACTURE;
	private ControlDecoration fieldQTE_L_FACTURE;
	private ControlDecoration fieldQTE2_L_FACTURE;
	private ControlDecoration fieldCODE_TIERS;
	private ControlDecoration fieldCODE_ARTICLE;
	
	private GridData tfMT_HT_L_FACTURELData;
	private GridData laMT_HT_L_FACTURELData;
	private GridData tfMT_TTC_L_FACTURELData;
	private GridData laMT_TTC_L_FACTURELData;
	
//	private ControlDecoration fieldMT_TTC_CALC;
//	private ControlDecoration fieldMT_HT_CALC;
//	private ControlDecoration fieldMT_TVA_CALC;
//	private ControlDecoration fieldNET_HT_CALC;
//	private ControlDecoration fieldNET_TTC_CALC;
	
	final private boolean decore = LgrMess.isDECORE();
	//final private boolean decore = false;
	//public Object getTfCODE_ARTICLE;
	
	public void reinitialiseLargeurMontant() {
		//a modifier en même temps que les valeurs dans initGUI
//		tfMT_HT_L_FACTURELData.widthHint = 106;
//		laMT_HT_L_FACTURELData.widthHint = 86;
//		tfMT_TTC_L_FACTURELData.widthHint = 120;
//		laMT_TTC_L_FACTURELData.widthHint = 86;
		
		tfMT_HT_L_FACTURELData.widthHint = SWT.DEFAULT;
		laMT_HT_L_FACTURELData.widthHint = SWT.DEFAULT;
		tfMT_TTC_L_FACTURELData.widthHint = SWT.DEFAULT;
		laMT_TTC_L_FACTURELData.widthHint = SWT.DEFAULT;
		
		tfMT_HT_L_FACTURELData.horizontalAlignment = GridData.FILL;
		tfMT_TTC_L_FACTURELData.horizontalAlignment = GridData.FILL;
		
//		getTfMT_HT_L_FACTURE().pack();
//		getLaMT_HT_L_FACTURE().pack();
//		getTfMT_TTC_L_FACTURE().pack();
//		getLaMT_TTC_L_FACTURE().pack();
	}

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
		PaSaisieLigneSWT inst = new PaSaisieLigneSWT(shell, SWT.NULL);
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

	public PaSaisieLigneSWT(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			this.setLayout(thisLayout);
			thisLayout.numColumns = 2;
			this.setSize(1066, 255);
			{
				paSaisie = new Composite(this, SWT.NONE);
				GridLayout paSaisieLayout = new GridLayout();
				paSaisieLayout.numColumns = 11;
				GridData paSaisieLData = new GridData();
				paSaisieLData.horizontalAlignment = GridData.FILL;
				paSaisieLData.heightHint = 48;
				paSaisieLData.grabExcessHorizontalSpace = true;
				paSaisie.setLayoutData(paSaisieLData);
				paSaisie.setLayout(paSaisieLayout);
				{
					laNUM_LIGNE_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laNUM_LIGNE_L_FACTURE.setText("N°");
				}
				{
					laCODE_ARTICLE = new Label(paSaisie, SWT.NONE);
					laCODE_ARTICLE.setText("Code article");
				}
				{
					laLIB_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laLIB_L_FACTURE.setText("Libellé");
				}
				{
					laQTE_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laQTE_L_FACTURE.setText("Qté 1");
				}
				{
					laU1_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laU1_L_FACTURE.setText("U1");
				}
				{
					laQTE2_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laQTE2_L_FACTURE.setText("Qté 2");
				}				
				{
					laU2_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laU2_L_FACTURE.setText("U2");
				}
				{
					laPRIX_U_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laPRIX_U_L_FACTURE.setText("Prix unitaire");
				}
				{
					laREM_TX_L_FACTURE = new Label(paSaisie, SWT.NONE);
					laREM_TX_L_FACTURE.setText("Remise %");
				}
				{
					laMT_HT_L_FACTURE = new Label(paSaisie, SWT.NONE);
					/*GridData*/ laMT_HT_L_FACTURELData = new GridData();
					laMT_HT_L_FACTURELData.widthHint = 86;
					laMT_HT_L_FACTURELData.heightHint = 13;
					laMT_HT_L_FACTURE.setLayoutData(laMT_HT_L_FACTURELData);
					laMT_HT_L_FACTURE.setText("Montant HT");
				}
				{
					laMT_TTC_L_FACTURE = new Label(paSaisie, SWT.NONE);
					/*GridData*/ laMT_TTC_L_FACTURELData = new GridData();
					laMT_TTC_L_FACTURELData.horizontalSpan = 2;
					laMT_TTC_L_FACTURE.setText("Montant TTC");
				}
				
				{
					GridData tfNUM_LIGNE_L_FACTURELData = new GridData();
					tfNUM_LIGNE_L_FACTURELData.widthHint = 23;
//					if(!decore){
						tfNUM_LIGNE_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfNUM_LIGNE_L_FACTURE.setLayoutData(tfNUM_LIGNE_L_FACTURELData);
						tfNUM_LIGNE_L_FACTURE.setText("NUM_LIGNE");
						fieldNUM_LIGNE_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else {
//						
//						tfNUM_LIGNE_L_FACTURE = (Text)fieldNUM_LIGNE_L_FACTURE.getControl();
//						fieldNUM_LIGNE_L_FACTURE.getLayoutControl().setLayoutData(tfNUM_LIGNE_L_FACTURELData);
//					}
				}
				{
					GridData tfCODE_ARTICLELData = new GridData();
					tfCODE_ARTICLELData.widthHint = 78;
//					if(!decore) {
						tfCODE_ARTICLE = new Text(paSaisie, SWT.BORDER);
						tfCODE_ARTICLE.setLayoutData(tfCODE_ARTICLELData);
						tfCODE_ARTICLE.setText("CODE_ARTICLE");
						fieldCODE_ARTICLE = new ControlDecoration(paSaisie, SWT.BORDER);
//					} else {
//						
//						tfCODE_ARTICLE = (Text)fieldCODE_ARTICLE.getControl();
//						fieldCODE_ARTICLE.getLayoutControl().setLayoutData(tfCODE_ARTICLELData);
//					}
				}
				{
					GridData tfLIB_L_FACTURELData = new GridData();
					tfLIB_L_FACTURELData.horizontalAlignment = GridData.FILL;
					tfLIB_L_FACTURELData.grabExcessHorizontalSpace = true;
//					if(!decore) {
						tfLIB_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfLIB_L_FACTURE.setLayoutData(tfLIB_L_FACTURELData);
						tfLIB_L_FACTURE.setText("LIB_L_FACTURE");
						fieldLIB_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					} else {
//						
//						tfLIB_L_FACTURE = (Text)fieldLIB_L_FACTURE.getControl();
//						fieldLIB_L_FACTURE.getLayoutControl().setLayoutData(tfLIB_L_FACTURELData);
//					}
				}
				{
					GridData tfQTE_L_FACTURELData = new GridData();
					tfQTE_L_FACTURELData.widthHint = 67;
//					if(!decore){
						tfQTE_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfQTE_L_FACTURE.setLayoutData(tfQTE_L_FACTURELData);
						tfQTE_L_FACTURE.setText("QTE_L_FACTURE");
						fieldQTE_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfQTE_L_FACTURE = (Text)fieldQTE_L_FACTURE.getControl();
//						fieldQTE_L_FACTURE.getLayoutControl().setLayoutData(tfQTE_L_FACTURELData);
//					}
				}
				{
					GridData tfU1_L_FACTURELData = new GridData();
					tfU1_L_FACTURELData.widthHint = 44;
//					if(!decore){
						tfU1_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfU1_L_FACTURE.setLayoutData(tfU1_L_FACTURELData);
						tfU1_L_FACTURE.setText("U1_L_FACTURE");
						fieldU1_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfU1_L_FACTURE = (Text)fieldU1_L_FACTURE.getControl();
//						fieldU1_L_FACTURE.getLayoutControl().setLayoutData(tfU1_L_FACTURELData);
//					}
				}
				{
					GridData tfQTE2_L_FACTURELData = new GridData();
					tfQTE2_L_FACTURELData.widthHint = 67;
//					if(!decore){
						tfQTE2_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfQTE2_L_FACTURE.setLayoutData(tfQTE2_L_FACTURELData);
						tfQTE2_L_FACTURE.setText("QTE2_L_FACTURE");
						fieldQTE2_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfQTE2_L_FACTURE = (Text)fieldQTE2_L_FACTURE.getControl();
//						fieldQTE2_L_FACTURE.getLayoutControl().setLayoutData(tfQTE2_L_FACTURELData);
//					}
				}
				
				{
					GridData tfU2_L_FACTURELData = new GridData();
					tfU2_L_FACTURELData.widthHint = 47;
//					if(!decore){
						tfU2_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfU2_L_FACTURE.setLayoutData(tfU2_L_FACTURELData);
						tfU2_L_FACTURE.setText("U2_L_FACTURE");
						fieldU2_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfU2_L_FACTURE = (Text)fieldU2_L_FACTURE.getControl();
//						fieldU2_L_FACTURE.getLayoutControl().setLayoutData(tfU2_L_FACTURELData);
//					}
				}
				{
					GridData tfPRIX_U_L_FACTURELData = new GridData();
					tfPRIX_U_L_FACTURELData.widthHint = 70;
//					if(!decore){
						tfPRIX_U_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfPRIX_U_L_FACTURE.setLayoutData(tfPRIX_U_L_FACTURELData);
						tfPRIX_U_L_FACTURE.setText("PRIX_U_L_FACTURE");
						fieldPRIX_U_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfPRIX_U_L_FACTURE = (Text)fieldPRIX_U_L_FACTURE.getControl();
//						fieldPRIX_U_L_FACTURE.getLayoutControl().setLayoutData(tfPRIX_U_L_FACTURELData);
//					}
				}
				{
					GridData tfREM_TX_L_FACTURELData = new GridData();
					tfREM_TX_L_FACTURELData.widthHint = 54;
//					if(!decore){
						tfREM_TX_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfREM_TX_L_FACTURE.setLayoutData(tfREM_TX_L_FACTURELData);
						tfREM_TX_L_FACTURE.setText("REM_TX_L_FACTURE");
						fieldREM_TX_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfREM_TX_L_FACTURE = (Text)fieldREM_TX_L_FACTURE.getControl();
//						fieldREM_TX_L_FACTURE.getLayoutControl().setLayoutData(tfREM_TX_L_FACTURELData);
//					}
				}
				{
					/*GridData*/ tfMT_HT_L_FACTURELData = new GridData();
					tfMT_HT_L_FACTURELData.horizontalAlignment = GridData.CENTER;
					tfMT_HT_L_FACTURELData.widthHint = 106;
//					if(!decore){
						tfMT_HT_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfMT_HT_L_FACTURE.setLayoutData(tfMT_HT_L_FACTURELData);
						tfMT_HT_L_FACTURE.setText("MT_HT_L_FACTURE");
						fieldMT_HT_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);
//					}else{
//						
//						tfMT_HT_L_FACTURE = (Text)fieldMT_HT_L_FACTURE.getControl();
//						fieldMT_HT_L_FACTURE.getLayoutControl().setLayoutData(tfMT_HT_L_FACTURELData);
//					}
				}
				{
					/*GridData*/ tfMT_TTC_L_FACTURELData = new GridData();
					tfMT_TTC_L_FACTURELData.horizontalAlignment = GridData.CENTER;
					tfMT_TTC_L_FACTURELData.widthHint = 120;
//					if(!decore){
						tfMT_TTC_L_FACTURE = new Text(paSaisie, SWT.BORDER);
						tfMT_TTC_L_FACTURE.setLayoutData(tfMT_TTC_L_FACTURELData);
						tfMT_TTC_L_FACTURE.setText("MT_TTC_L_FACTURE");
						fieldMT_TTC_L_FACTURE = new ControlDecoration(paSaisie, SWT.BORDER);

//					}else{
//						
//						tfMT_TTC_L_FACTURE = (Text)fieldMT_TTC_L_FACTURE.getControl();
//						fieldMT_TTC_L_FACTURE.getLayoutControl().setLayoutData(tfMT_TTC_L_FACTURELData);
//					}
				}
			}
			{
				GridData grilleLData = new GridData();
				grilleLData.horizontalAlignment = GridData.FILL;
				grilleLData.verticalAlignment = GridData.FILL;
				grilleLData.grabExcessVerticalSpace = true;
				grilleLData.grabExcessHorizontalSpace = true;
				grilleLData.horizontalSpan = 2;
				grille = new Table(this, SWT.SINGLE
					| SWT.FULL_SELECTION
					| SWT.H_SCROLL
					| SWT.V_SCROLL
					| SWT.BORDER);
				grille.setLayoutData(grilleLData);
				grille.setLinesVisible(true);
				grille.setHeaderVisible(true);
			}
			
			{
				paTotauxLignes = new Group(this, SWT.NONE);
				GridLayout paTotauxLignesLayout = new GridLayout();
				paTotauxLignesLayout.numColumns = 10;
				GridData paTotauxLignesLData = new GridData();
				paTotauxLignesLData.verticalAlignment = GridData.END;
				paTotauxLignesLData.horizontalAlignment = GridData.CENTER;
				paTotauxLignesLData.horizontalSpan = 2;
				paTotauxLignesLData.heightHint = 37;
				paTotauxLignesLData.widthHint = 510;
				paTotauxLignes.setLayoutData(paTotauxLignesLData);
				paTotauxLignes.setLayout(paTotauxLignesLayout);
				paTotauxLignes.setText("Totaux");
			}
			
			{
				laMT_HT_CALC = new Label(paTotauxLignes, SWT.NONE);
				laMT_HT_CALC.setText("HT");
			}
			{
				GridData tfMT_HT_CALCLData = new GridData();
				tfMT_HT_CALCLData.widthHint = 127;
				tfMT_HT_CALCLData.verticalAlignment = GridData.BEGINNING;
				tfMT_HT_CALC = new Text(paTotauxLignes, SWT.BORDER);
				tfMT_HT_CALC.setLayoutData(tfMT_HT_CALCLData);
				tfMT_HT_CALC.setText("MT_HT_CALC");
				tfMT_HT_CALC.setFont(SWTResourceManager.getFont("Tahoma", 8, 1, false, false));
				tfMT_HT_CALC.setEditable(false);

			}
			{
				laMT_TVA_AVANT_REMISE = new Label(paTotauxLignes, SWT.NONE);
				laMT_TVA_AVANT_REMISE.setText("TVA");
			}
			{
				GridData tfMT_TVA_AVANT_REMISELData = new GridData();
				tfMT_TVA_AVANT_REMISELData.widthHint = 127;
				tfMT_TVA_AVANT_REMISE = new Text(paTotauxLignes, SWT.BORDER);
				tfMT_TVA_AVANT_REMISE.setLayoutData(tfMT_TVA_AVANT_REMISELData);
				tfMT_TVA_AVANT_REMISE.setText("MT_TVA_AVANT_REMISE");
				tfMT_TVA_AVANT_REMISE.setFont(SWTResourceManager.getFont("Tahoma", 8, 1, false, false));
				tfMT_TVA_AVANT_REMISE.setEditable(false);

			}
			{
				laMT_TTC_CALC = new Label(paTotauxLignes, SWT.NONE);
				laMT_TTC_CALC.setText("TTC");
			}
			{
				GridData tfMT_TTC_CALCLData = new GridData();
				tfMT_TTC_CALCLData.widthHint = 125;
				tfMT_TTC_CALC = new Text(paTotauxLignes, SWT.BORDER);
				tfMT_TTC_CALC.setLayoutData(tfMT_TTC_CALCLData);
				tfMT_TTC_CALC.setText("MT_TTC_CALC");
				tfMT_TTC_CALC.setFont(SWTResourceManager.getFont("Tahoma", 8, 1, false, false));
				tfMT_TTC_CALC.setOrientation(SWT.HORIZONTAL);
				tfMT_TTC_CALC.setEditable(false);

			}
//			{
//				laNET_HT_CALC = new Label(paTotauxLignes, SWT.NONE);
//				laNET_HT_CALC.setText("NET HT");
//			}
//			{
//				GridData tfNET_HT_CALCLData = new GridData();
//				tfNET_HT_CALCLData.widthHint = 127;
//				tfNET_HT_CALCLData.verticalAlignment = GridData.BEGINNING;
//				tfNET_HT_CALCLData.horizontalAlignment = GridData.BEGINNING;
//
//				tfNET_HT_CALC = new Text(paTotauxLignes, SWT.BORDER);
//				tfNET_HT_CALC.setLayoutData(tfNET_HT_CALCLData);
//				tfNET_HT_CALC.setText("NET_HT_CALC");
//				tfNET_HT_CALC.setEditable(false);
//				tfNET_HT_CALC.setEnabled(false);
//
//			}
//			{
//				laNET_TVA_CALC = new Label(paTotauxLignes, SWT.NONE);
//				laNET_TVA_CALC.setText("NET TVA");
//			}
//			{
//				GridData tfMT_TVA_CALCLData = new GridData();
//				tfMT_TVA_CALCLData.widthHint = 127;
//				tfNET_TVA_CALC = new Text(paTotauxLignes, SWT.BORDER);
//				tfNET_TVA_CALC.setLayoutData(tfMT_TVA_CALCLData);
//				tfNET_TVA_CALC.setText("MT_TVA_CALC");
//				tfNET_TVA_CALC.setEditable(false);
//				tfNET_TVA_CALC.setEnabled(false);
//
//			}
//			{
//				laNET_TTC_CALC = new Label(paTotauxLignes, SWT.NONE);
//				laNET_TTC_CALC.setText("NET TTC");
//			}
//			{
//				GridData tfNET_TTC_CALCLData = new GridData();
//				tfNET_TTC_CALCLData.widthHint = 127;
//				tfNET_TTC_CALCLData.verticalAlignment = GridData.BEGINNING;
//				tfNET_TTC_CALCLData.horizontalAlignment = GridData.BEGINNING;
//
//				tfNET_TTC_CALC = new Text(paTotauxLignes, SWT.BORDER);
//				tfNET_TTC_CALC.setLayoutData(tfNET_TTC_CALCLData);
//				tfNET_TTC_CALC.setText("tfNET_TTC_CALC");
//				tfNET_TTC_CALC.setEditable(false);
//				tfNET_TTC_CALC.setEnabled(false);
//
//			}			

			
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
	
	public void extensionTitreTransport(PaSaisieLigneSWT vue) {
		
		GridLayout paSaisieLayout = new GridLayout();
		paSaisieLayout.numColumns = ((GridLayout)paSaisie.getLayout()).numColumns+2; //12
		paSaisie.setLayout(paSaisieLayout);
		
		{
			laTitreTransportU1 = new Label(vue.getPaSaisie(), SWT.NONE);
			laTitreTransportU1.setText("Type CRD");
			laTitreTransportU1.moveBelow(laMT_TTC_L_FACTURE);
			//laTitreTransportU1.moveAbove(laQTE_L_FACTURE);
		}
		{
			laQteTitreTransportU1 = new Label(vue.getPaSaisie(), SWT.NONE);
			laQteTitreTransportU1.setText("Qte CRD");
			laQteTitreTransportU1.moveAbove(laTitreTransportU1);
		}
		{
			GridData tfTitreTransportU1LData = new GridData();
			tfTitreTransportU1LData.horizontalAlignment = GridData.FILL;
			//tfTitreTransportU1LData.grabExcessHorizontalSpace = true;
//			if(!decore) {
				tfTitreTransportU1 = new Text(paSaisie, SWT.BORDER);
				tfTitreTransportU1.setLayoutData(tfTitreTransportU1LData);
				fieldTitreTransportU1 = new ControlDecoration(paSaisie, SWT.BORDER);
//			} else {					
//				
//				tfTitreTransportU1 = (Text)fieldTitreTransportU1.getControl();
//				fieldTitreTransportU1.getLayoutControl().setLayoutData(tfTitreTransportU1LData);
//			}
			tfTitreTransportU1.getParent().moveBelow(tfMT_TTC_L_FACTURE.getParent());
			//tfTitreTransportU1.getParent().moveAbove(tfQTE_L_FACTURE.getParent()); //getParent à cause des champs décorés
		}
		{
			GridData tfQteTitreTransportU1LData = new GridData();
			tfQteTitreTransportU1LData.horizontalAlignment = GridData.FILL;
			//tfTitreTransportU1LData.grabExcessHorizontalSpace = true;
//			if(!decore) {
				tfQteTitreTransportU1 = new Text(paSaisie, SWT.BORDER);
				tfQteTitreTransportU1.setLayoutData(tfQteTitreTransportU1LData);
				fieldQteTitreTransportU1 = new ControlDecoration(paSaisie, SWT.BORDER);
//			} else {					
//				
//				tfQteTitreTransportU1 = (Text)fieldQteTitreTransportU1.getControl();
//				fieldQteTitreTransportU1.getLayoutControl().setLayoutData(tfQteTitreTransportU1LData);
//			}
			tfQteTitreTransportU1.getParent().moveAbove(tfTitreTransportU1.getParent()); //getParent à cause des champs décorés
		}
		vue.layout();
	}

	public Label getLaNUM_LIGNE_L_FACTURE() {
		return laNUM_LIGNE_L_FACTURE;
	}

	public Text getTfNUM_LIGNE_L_FACTURE() {
		return tfNUM_LIGNE_L_FACTURE;
	}

	public Label getLaCODE_ARTICLE() {
		return laCODE_ARTICLE;
	}

	public Text getTfCODE_ARTICLE() {
		return tfCODE_ARTICLE;
	}

	public Label getLaLIB_L_FACTURE() {
		return laLIB_L_FACTURE;
	}

	public Text getTfLIB_L_FACTURE() {
		return tfLIB_L_FACTURE;
	}

	public Label getLaQTE_L_FACTURE() {
		return laQTE_L_FACTURE;
	}

	public Label getLaU1_L_FACTURE() {
		return laU1_L_FACTURE;
	}

	public Text getTfMT_HT_L_FACTURE() {
		return tfMT_HT_L_FACTURE;
	}

	public Composite getPaSaisie() {
		return paSaisie;
	}

	public Table getGrille() {
		return grille;
	}

	public Label getLaMT_HT_L_FACTURE() {
		return laMT_HT_L_FACTURE;
	}

	public Label getLaPRIX_U_L_FACTURE() {
		return laPRIX_U_L_FACTURE;
	}

	public Label getLaREM_TX_L_FACTURE() {
		return laREM_TX_L_FACTURE;
	}

	public Label getLaU2_L_FACTURE() {
		return laU2_L_FACTURE;
	}

	public Text getTfPRIX_U_L_FACTURE() {
		return tfPRIX_U_L_FACTURE;
	}

	public Text getTfQTE_L_FACTURE() {
		return tfQTE_L_FACTURE;
	}

	public Text getTfREM_TX_L_FACTURE() {
		return tfREM_TX_L_FACTURE;
	}

	public Text getTfU1_L_FACTURE() {
		return tfU1_L_FACTURE;
	}

	public Text getTfU2_L_FACTURE() {
		return tfU2_L_FACTURE;
	}

	public ControlDecoration getFieldCODE_ARTICLE() {
		return fieldCODE_ARTICLE;
	}

	public ControlDecoration getFieldCODE_TIERS() {
		return fieldCODE_TIERS;
	}

	public ControlDecoration getFieldLIB_L_FACTURE() {
		return fieldLIB_L_FACTURE;
	}

	public ControlDecoration getFieldMT_HT_L_FACTURE() {
		return fieldMT_HT_L_FACTURE;
	}

	public ControlDecoration getFieldNUM_LIGNE_L_FACTURE() {
		return fieldNUM_LIGNE_L_FACTURE;
	}

	public ControlDecoration getFieldPRIX_U_L_FACTURE() {
		return fieldPRIX_U_L_FACTURE;
	}

	public ControlDecoration getFieldQTE_L_FACTURE() {
		return fieldQTE_L_FACTURE;
	}

	public ControlDecoration getFieldREM_TX_L_FACTURE() {
		return fieldREM_TX_L_FACTURE;
	}

	public ControlDecoration getFieldU1_L_FACTURE() {
		return fieldU1_L_FACTURE;
	}

	public ControlDecoration getFieldU2_L_FACTURE() {
		return fieldU2_L_FACTURE;
	}

	public Text getTfMT_TTC_L_FACTURE() {
		return tfMT_TTC_L_FACTURE;
	}

	public Label getLaMT_TTC_L_FACTURE() {
		return laMT_TTC_L_FACTURE;
	}

	public ControlDecoration getFieldMT_TTC_L_FACTURE() {
		return fieldMT_TTC_L_FACTURE;
	}

	public void setFieldMT_TTC_L_FACTURE(ControlDecoration fieldMT_TTC_L_FACTURE) {
		this.fieldMT_TTC_L_FACTURE = fieldMT_TTC_L_FACTURE;
	}
	
	public PaBtnAvecAssistant getPaBtnAvecAssistant() {
		return paBtnAvecAssistant;
	}

//	public Text getTfNET_TVA_CALC() {
//		return tfNET_TVA_CALC;
//	}
//
//	public Label getLaNET_TVA_CALC() {
//		return laNET_TVA_CALC;
//	}

	public Text getTfMT_TTC_CALC() {
		return tfMT_TTC_CALC;
	}

	public Label getLaMT_TTC_CALC() {
		return laMT_TTC_CALC;
	}

	public Text getTfMT_HT_CALC() {
		return tfMT_HT_CALC;
	}

	public Label getLaMT_HT_CALC() {
		return laMT_HT_CALC;
	}

	public Group getPaTotauxLignes() {
		return paTotauxLignes;
	}

//	public ControlDecoration getFieldMT_TTC_CALC() {
//		return fieldMT_TTC_CALC;
//	}
//
//	public ControlDecoration getFieldMT_HT_CALC() {
//		return fieldMT_HT_CALC;
//	}
//
//	public ControlDecoration getFieldMT_TVA_CALC() {
//		return fieldMT_TVA_CALC;
//	}

	public boolean isDecore() {
		return decore;
	}

	public Text getTfQTE2_L_FACTURE() {
		return tfQTE2_L_FACTURE;
	}

	public ControlDecoration getFieldQTE2_L_FACTURE() {
		return fieldQTE2_L_FACTURE;
	}

	public Label getLaQTE2_L_FACTURE() {
		return laQTE2_L_FACTURE;
	}

//	public Text getTfNET_HT_CALC() {
//		return tfNET_HT_CALC;
//	}
//
//	public Label getLaNET_HT_CALC() {
//		return laNET_HT_CALC;
//	}

//	public ControlDecoration getFieldNET_HT_CALC() {
//		return fieldNET_HT_CALC;
//	}

//	public Text getTfNET_TTC_CALC() {
//		return tfNET_TTC_CALC;
//	}
//
//	public Label getLaNET_TTC_CALC() {
//		return laNET_TTC_CALC;
//	}

	public Label getLaMT_TVA_AVANT_REMISE() {
		return laMT_TVA_AVANT_REMISE;
	}

	public Text getTfMT_TVA_AVANT_REMISE() {
		return tfMT_TVA_AVANT_REMISE;
	}

	public GridData getTfMT_HT_L_FACTURELData() {
		return tfMT_HT_L_FACTURELData;
	}

	public GridData getLaMT_HT_L_FACTURELData() {
		return laMT_HT_L_FACTURELData;
	}

	public GridData getTfMT_TTC_L_FACTURELData() {
		return tfMT_TTC_L_FACTURELData;
	}

	public GridData getLaMT_TTC_L_FACTURELData() {
		return laMT_TTC_L_FACTURELData;
	}

	public Label getLaTitreTransportU1() {
		return laTitreTransportU1;
	}

	public Text getTfTitreTransportU1() {
		return tfTitreTransportU1;
	}

	public ControlDecoration getFieldTitreTransportU1() {
		return fieldTitreTransportU1;
	}

	public Label getLaQteTitreTransportU1() {
		return laQteTitreTransportU1;
	}

	public Text getTfQteTitreTransportU1() {
		return tfQteTitreTransportU1;
	}

	public ControlDecoration getFieldQteTitreTransportU1() {
		return fieldQteTitreTransportU1;
	}

//	public ControlDecoration getFieldNET_TTC_CALC() {
//		return fieldNET_TTC_CALC;
//	}

}
