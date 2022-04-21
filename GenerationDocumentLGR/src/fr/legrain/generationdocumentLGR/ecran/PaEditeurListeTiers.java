package fr.legrain.generationdocumentLGR.ecran;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.cloudgarden.resource.SWTResourceManager;

import fr.legrain.lib.gui.PaBtnReduit;
import fr.legrain.lib.gui.cdatetimeLgr;

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
public class PaEditeurListeTiers extends org.eclipse.swt.widgets.Composite {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}
	
	private PaBtnReduit paBtn1;
	private List tfListeTiers;
	private Button BtnReinitialiser;
	private Composite composite1;
	private StyledText tfStyleEditeur;
	private Label label1;
	private DateTime tfDateDocument;
	private Label laDateDocument;
	private Text tfLibelle;
	private Label laLibelleDocument;
	private Text tfRecherche;
	private Button btnReinitDocCree;
	private Button getCbReExport;
	private Button cbPrint;
	private Button BtnImprimerDoc;
	private Button BtnRemonterDoc;
	private List tfListeDoc;
	private Button BtnChargerFichier;
	private Button BtnRajouter;
	private Label laCodeTiersSelection;
	private Text tfTPaiement;
	private Label laTPaiement;
	private Label laTotalDoc;
	private Label laNbFacture;
	private Label laSumWlgr;
	private Label laNbWlgr;
	private Composite PaSelection;
	private Text tfCode_Tiers;
	private Label laCodeTiers;
	private Label laListeCodeTiers;
	private Composite paCorpsFormulaire;
	private Composite PaEntete;
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
		PaEditeurListeTiers inst = new PaEditeurListeTiers(shell, SWT.NULL);
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

	public PaEditeurListeTiers(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {

		try {
			GridLayout thisLayout = new GridLayout();
			this.setLayout(thisLayout);
			thisLayout.numColumns = 8;
			thisLayout.horizontalSpacing = 4;
			thisLayout.makeColumnsEqualWidth = true;
			this.setSize(699, 334);
			{
				PaEntete = new Composite(this, SWT.NONE);
				GridLayout PaEnteteLayout = new GridLayout();
				GridData PaEnteteLData = new GridData();
				PaEnteteLData.horizontalSpan = 10;
				PaEnteteLData.verticalAlignment = GridData.FILL;
				PaEnteteLData.horizontalAlignment = GridData.FILL;
				PaEnteteLData.grabExcessHorizontalSpace = true;
				PaEnteteLayout.numColumns = 8;
				PaEntete.setLayout(PaEnteteLayout);
				PaEntete.setLayoutData(PaEnteteLData);
				{
					laLibelleDocument = new Label(PaEntete, SWT.NONE);
					GridData laLibelleDocumentLData = new GridData();
					laLibelleDocumentLData.heightHint = 13;
					laLibelleDocument.setLayoutData(laLibelleDocumentLData);
					laLibelleDocument.setText("Libellé");
				}
				{
					GridData tfLibelleLData = new GridData();
					tfLibelleLData.grabExcessHorizontalSpace = true;
					tfLibelleLData.heightHint = 13;
					tfLibelleLData.horizontalAlignment = GridData.FILL;
					tfLibelle = new Text(PaEntete, SWT.BORDER);
					tfLibelle.setLayoutData(tfLibelleLData);
				}
				{
					laTPaiement = new Label(PaEntete, SWT.NONE);
					GridData laTPaiementLData = new GridData();
					laTPaiementLData.heightHint = 13;
					laTPaiementLData.horizontalAlignment = GridData.CENTER;
					laTPaiement.setLayoutData(laTPaiementLData);
					laTPaiement.setText("Type de paiement");
				}
				{
					GridData tfTPaiementLData = new GridData();
					tfTPaiementLData.heightHint = 13;
					tfTPaiement = new Text(PaEntete, SWT.BORDER);
					tfTPaiement.setLayoutData(tfTPaiementLData);
				}
				{
					laDateDocument = new Label(PaEntete, SWT.NONE);
					GridData laDateDocumentLData = new GridData();
					laDateDocumentLData.heightHint = 13;
					laDateDocumentLData.horizontalAlignment = GridData.CENTER;
					laDateDocument.setLayoutData(laDateDocumentLData);
					laDateDocument.setText("Date du document");
				}
				{
					GridData tfDateDocumentLData = new GridData();
					tfDateDocumentLData.heightHint = 17;
					tfDateDocument = new DateTime(PaEntete, SWT.NONE);
					tfDateDocument.setLayoutData(tfDateDocumentLData);
//					tfDateDocument.setPattern("dd/MM/yyyy");	
				}				
				PaSelection = new Composite(this, SWT.NONE);
				GridLayout PaSelectionLayout = new GridLayout();
				PaSelectionLayout.makeColumnsEqualWidth = true;
				PaSelectionLayout.numColumns = 2;
				GridData PaSelectionLData = new GridData();
				PaSelectionLData.verticalAlignment = GridData.FILL;
				PaSelectionLData.grabExcessHorizontalSpace = true;
				PaSelectionLData.horizontalAlignment = GridData.FILL;
				PaSelectionLData.horizontalSpan = 2;
				PaSelectionLData.grabExcessVerticalSpace = true;
				PaSelection.setLayoutData(PaSelectionLData);
				PaSelection.setLayout(PaSelectionLayout);
				{
					laCodeTiersSelection = new Label(PaSelection, SWT.NONE);
					laCodeTiersSelection.setText("Code tiers à rajouter");
					GridData laCodeTiersSelectionLData = new GridData();
					laCodeTiersSelectionLData.horizontalSpan = 2;
					laCodeTiersSelectionLData.grabExcessHorizontalSpace = true;
					laCodeTiersSelectionLData.horizontalAlignment = GridData.FILL;
					laCodeTiersSelection.setLayoutData(laCodeTiersSelectionLData);
					laCodeTiersSelection.setFont(SWTResourceManager.getFont("Tahoma", 8, 1, false, false));
				}
				{
					tfRecherche = new Text(PaSelection, SWT.BORDER);
					GridData tfRechercheLData = new GridData();
					tfRechercheLData.horizontalAlignment = GridData.FILL;
					tfRechercheLData.heightHint = 13;
					tfRechercheLData.grabExcessHorizontalSpace = true;
					tfRechercheLData.horizontalSpan = 2;
					tfRecherche.setLayoutData(tfRechercheLData);
					tfRecherche.setSize(143, 19);
				}
				{
					GridData tfListeTiersLData = new GridData();
					tfListeTiersLData.verticalAlignment = GridData.FILL;
					tfListeTiersLData.grabExcessVerticalSpace = true;
					tfListeTiersLData.horizontalAlignment = GridData.FILL;
					tfListeTiersLData.grabExcessHorizontalSpace = true;
					tfListeTiersLData.horizontalSpan = 2;
					tfListeTiers = new List(PaSelection, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
					tfListeTiers.setLayoutData(tfListeTiersLData);
				}
				{
					laNbWlgr = new Label(PaSelection, SWT.BORDER);
					laNbWlgr.setToolTipText("Nombre de bons non traités");
				}
				{
					laSumWlgr = new Label(PaSelection, SWT.BORDER);
					laSumWlgr.setToolTipText("Somme restante à venir");
				}
				{
					GridData BtnRajouterLData = new GridData();
					BtnRajouterLData.heightHint = 23;
					BtnRajouterLData.widthHint = 87;
					BtnRajouter = new Button(PaSelection, SWT.PUSH | SWT.CENTER);
					BtnRajouter.setLayoutData(BtnRajouterLData);
					BtnRajouter.setText("Rajouter");
				}
				{
					BtnReinitialiser = new Button(PaSelection, SWT.PUSH | SWT.CENTER);
					GridData BtnReinitialiserLData = new GridData();
					BtnReinitialiserLData.widthHint = 89;
					BtnReinitialiserLData.heightHint = 23;
					BtnReinitialiser.setLayoutData(BtnReinitialiserLData);
					BtnReinitialiser.setText("Réinitialiser");
				}
			}
			{
				GridData composite1LData = new GridData();
				composite1LData.verticalAlignment = GridData.FILL;
				composite1LData.horizontalAlignment = GridData.FILL;
				composite1LData.horizontalSpan = 4;
				composite1LData.grabExcessHorizontalSpace = true;
				composite1LData.grabExcessVerticalSpace = true;
				paCorpsFormulaire = new Composite(this, SWT.NONE);
				GridLayout composite1Layout = new GridLayout();
				composite1Layout.makeColumnsEqualWidth = true;
				paCorpsFormulaire.setLayout(composite1Layout);
				paCorpsFormulaire.setLayoutData(composite1LData);
				{
					laListeCodeTiers = new Label(paCorpsFormulaire, SWT.NONE);
					GridData laCODE_ARTICLELData = new GridData();
					laCODE_ARTICLELData.widthHint = 200;
					laCODE_ARTICLELData.verticalAlignment = GridData.BEGINNING;
					laCODE_ARTICLELData.heightHint = 13;
					laListeCodeTiers.setLayoutData(laCODE_ARTICLELData);
					laListeCodeTiers.setText("Liste des tiers sélectionnés");
					laListeCodeTiers.setFont(SWTResourceManager.getFont("Tahoma", 8, 1, false, false));
				}
				{
					GridData tfStyleEditeurLData = new GridData();
					tfStyleEditeurLData.verticalAlignment = GridData.FILL;
					tfStyleEditeurLData.horizontalAlignment = GridData.FILL;
					tfStyleEditeurLData.grabExcessVerticalSpace = true;
					tfStyleEditeurLData.grabExcessHorizontalSpace = true;
					tfStyleEditeur = new StyledText(paCorpsFormulaire, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
					tfStyleEditeur.setLayoutData(tfStyleEditeurLData);
				}
				{
					BtnChargerFichier = new Button(paCorpsFormulaire, SWT.PUSH | SWT.CENTER);
					GridData BtnChargerFichierLData = new GridData();
					BtnChargerFichierLData.widthHint = 129;
					BtnChargerFichierLData.heightHint = 23;
					BtnChargerFichier.setLayoutData(BtnChargerFichierLData);
					BtnChargerFichier.setText("Charger le fichier");
				}
			}
			{
				GridData composite1LData1 = new GridData();
				composite1LData1.verticalAlignment = GridData.FILL;
				composite1LData1.horizontalSpan = 2;
				composite1LData1.horizontalAlignment = GridData.FILL;
				composite1LData1.grabExcessHorizontalSpace = true;
				composite1LData1.grabExcessVerticalSpace = true;
				composite1 = new Composite(this, SWT.NONE);
				GridLayout composite1Layout1 = new GridLayout();
				composite1Layout1.numColumns = 2;
				composite1Layout1.horizontalSpacing = 2;
				composite1Layout1.makeColumnsEqualWidth = true;
				composite1.setLayout(composite1Layout1);
				composite1.setLayoutData(composite1LData1);
				{
					label1 = new Label(composite1, SWT.NONE);
					label1.setText("Documents créés");
					GridData label1LData = new GridData();
					label1LData.widthHint = 164;
					label1LData.heightHint = 13;
					label1LData.horizontalSpan = 3;
					label1.setLayoutData(label1LData);
					label1.setFont(SWTResourceManager.getFont("Tahoma",8,1,false,false));
				}
				{
					GridData list1LData = new GridData();
					list1LData.verticalAlignment = GridData.FILL;
					list1LData.grabExcessVerticalSpace = true;
					list1LData.horizontalSpan = 2;
					list1LData.horizontalAlignment = GridData.FILL;
					list1LData.grabExcessHorizontalSpace = true;
					tfListeDoc = new List(composite1, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
					tfListeDoc.setLayoutData(list1LData);
				}
				{
					laNbFacture = new Label(composite1, SWT.BORDER);
					GridData laNbFactureLData = new GridData();
					laNbFactureLData.grabExcessHorizontalSpace = true;
					laNbFacture.setLayoutData(laNbFactureLData);
					laNbFacture.setToolTipText("Nombre de documents créés");
				}
				{
					laTotalDoc = new Label(composite1, SWT.BORDER);
					GridData laTotalDocLData = new GridData();
					laTotalDocLData.widthHint = 64;
					laTotalDocLData.heightHint = 13;
					laTotalDocLData.grabExcessHorizontalSpace = true;
					laTotalDoc.setLayoutData(laTotalDocLData);
					laTotalDoc.setToolTipText("Total facturé");
				}
				{
					BtnRemonterDoc = new Button(composite1, SWT.PUSH | SWT.CENTER);
					GridData button2LData = new GridData();
					button2LData.heightHint = 23;
					button2LData.grabExcessHorizontalSpace = true;
					button2LData.horizontalAlignment = GridData.FILL;
					BtnRemonterDoc.setLayoutData(button2LData);
					BtnRemonterDoc.setText("Remonter");
				}
				{
					btnReinitDocCree = new Button(composite1, SWT.PUSH | SWT.CENTER);
					GridData btnReinitDocCreeLData = new GridData();
					btnReinitDocCreeLData.grabExcessHorizontalSpace = true;
					btnReinitDocCreeLData.heightHint = 23;
					btnReinitDocCreeLData.horizontalAlignment = GridData.FILL;
					btnReinitDocCree.setLayoutData(btnReinitDocCreeLData);
					btnReinitDocCree.setText("Réinitialiser");
				}
				{
					BtnImprimerDoc = new Button(composite1, SWT.PUSH | SWT.CENTER);
					GridData button1LData1 = new GridData();
					button1LData1.horizontalSpan = 2;
					button1LData1.heightHint = 23;
					button1LData1.horizontalAlignment = GridData.FILL;
					BtnImprimerDoc.setLayoutData(button1LData1);
					BtnImprimerDoc.setText("Imprimer");
				}
				{
					GridData cbLData = new GridData();
					cbLData.heightHint = 16;
					cbLData.horizontalAlignment = GridData.FILL;
					getCbReExport = new Button(composite1, SWT.CHECK | SWT.LEFT);
					getCbReExport.setLayoutData(cbLData);
					getCbReExport.setText("Prévisualiser");
					getCbReExport.setSelection(true);
				}
				{
					GridData cbLData = new GridData();
					cbLData.heightHint = 16;
					cbLData.horizontalAlignment = GridData.FILL;
					cbPrint = new Button(composite1, SWT.CHECK | SWT.LEFT);
					cbPrint.setLayoutData(cbLData);
					cbPrint.setText("Imprimer direct");
					cbPrint.setSelection(false);
				}
			}
			{
				GridData paBtn1LData = new GridData();
				paBtn1LData.horizontalSpan = 8;
				paBtn1LData.heightHint = 33;
				paBtn1LData.horizontalAlignment = GridData.CENTER;
				paBtn1LData.grabExcessHorizontalSpace = true;
				paBtn1 = new PaBtnReduit(this, SWT.NONE);
				paBtn1.getBtnImprimer().setText("Valider[F3]"); 
				GridData btnFermerLData = new GridData();
				btnFermerLData.widthHint = 102;
				btnFermerLData.heightHint = 23;
				paBtn1.setLayoutData(paBtn1LData);
				paBtn1.getBtnFermer().setLayoutData(btnFermerLData);
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	public PaBtnReduit getPaBtn1() {
		return paBtn1;
	}
	

	public Label getLaCODE_TIERS() {
		return laListeCodeTiers;
	}
	

	
	public Label getLaListeCodeTiers() {
		return laCodeTiers;
	}
	
	public Composite getPaSelection() {
		return PaSelection;
	}
	
	public Label getLaCodeTiersSelection() {
		return laCodeTiersSelection;
	}
	
	
	public List getTfListeTiers() {
		return tfListeTiers;
	}
	
	public Button getBtnRajouter() {
		return BtnRajouter;
	}
	
	public Button getBtnReinitialiser() {
		return BtnReinitialiser;
	}
	
	public Button getBtnChargerFichier() {
		return BtnChargerFichier;
	}
	
	public StyledText getTfStyleEditeur() {
		return tfStyleEditeur;
	}

	public List getTfListeDoc() {
		return tfListeDoc;
	}

	public Button getBtnImprimerDoc() {
		return BtnImprimerDoc;
	}

	public Button getBtnRemonterDoc() {
		return BtnRemonterDoc;
	}
	
	public Button getCbReExport() {
		return getCbReExport;
	}
	
	public Button getBtnReinitDocCree() {
		return btnReinitDocCree;
	}
	
	public Text getTfRecherche() {
		return tfRecherche;
	}
	
	public Label getLaLibelleDocument() {
		return laLibelleDocument;
	}
	
	public Text getTfLibelle() {
		return tfLibelle;
	}
	
	public Label getLaDateDocument() {
		return laDateDocument;
	}
	
	public DateTime getTfDateDocument() {
		return tfDateDocument;
	}

	public Composite getPaEntete() {
		return PaEntete;
	}
	
	public Label getLaNbWlgr() {
		return laNbWlgr;
	}
	
	public Label getLaSumWlgr() {
		return laSumWlgr;
	}
	
	public Label getLaNbFacture() {
		return laNbFacture;
	}
	
	public Label getLaTotalDoc() {
		return laTotalDoc;
	}
	
	public Label getLaTPaiement() {
		return laTPaiement;
	}
	
	public Text getTfTPaiement() {
		return tfTPaiement;
	}

	public Button getCbPrint() {
		return cbPrint;
	}

	public void setCbPrint(Button cbPrint) {
		this.cbPrint = cbPrint;
	}

}
