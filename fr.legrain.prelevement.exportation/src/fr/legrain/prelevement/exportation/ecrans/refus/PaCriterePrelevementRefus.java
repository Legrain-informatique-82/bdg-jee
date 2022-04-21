package fr.legrain.prelevement.exportation.ecrans.refus;

import org.eclipse.jface.fieldassist.DecoratedField;
import org.eclipse.jface.fieldassist.TextControlCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import fr.legrain.lib.gui.cdatetimeLgr;
import fr.legrain.lib.gui.fieldassist.CDateTimeControlCreator;
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
public class PaCriterePrelevementRefus extends org.eclipse.swt.widgets.Composite {
	private Group groupParam;
	private Label laTiers;
	private Label laCodePrelevement;
	private Text tfCodePrelevement;
	private Combo cbListeReponse;
	private Label laListePaiement;
	private Text tfTiers;
	private Label laFinPeriode;
	private Label laDateDebut;
	private Group groupFactureNonRegle;

	private Label laMessage;
	private DateTime tfDateDebutPeriode;
	private DateTime tfDateFinPeriode;
	private Button btnValiderParam;
	private Button btnSuivant;
	private Button btnFermer;
	private Composite paBtn;
	private DecoratedField fieldDateDebutPeriode;
	private DecoratedField fieldDateFinPeriode;

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
	 * Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	 */	
	protected void checkSubclass() {
	}

	/**
	 * Auto-generated method to display this 
	 * org.eclipse.swt.widgets.Composite inside a new Shell.
	 */
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		PaCriterePrelevementRefus inst = new PaCriterePrelevementRefus(shell, SWT.NULL);
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

	public PaCriterePrelevementRefus(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout();
			thisLayout.numColumns = 2;
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			{
				groupParam = new Group(this, SWT.NONE);
				GridLayout groupParamLayout = new GridLayout();
				groupParamLayout.numColumns =4;
				groupParam.setLayout(groupParamLayout);
				GridData groupParamLData = new GridData();
				groupParamLData.horizontalAlignment = GridData.FILL;
				groupParamLData.grabExcessHorizontalSpace = true;
				groupParamLData.horizontalSpan = 2;
				groupParam.setLayoutData(groupParamLData);
				groupParam.setText("Paramètres");
				{
					laDateDebut = new Label(groupParam, SWT.NONE);
					GridData laDateDebutLData = new GridData();
					laDateDebutLData.widthHint = 99;
					laDateDebutLData.heightHint = 22;
					laDateDebut.setLayoutData(laDateDebutLData);
					laDateDebut.setText("Début");
				}
				{
					GridData cdatetimeLgr2LData = new GridData();
					cdatetimeLgr2LData.heightHint = 23;
				if(!decore) {
					tfDateDebutPeriode = new DateTime(groupParam, SWT.BORDER | SWT.DROP_DOWN);
					tfDateDebutPeriode.setLayoutData(cdatetimeLgr2LData);
					} else {
					fieldDateDebutPeriode = new DecoratedField(groupParam, SWT.BORDER | SWT.DROP_DOWN, new DateTimeControlCreator());
					tfDateDebutPeriode = (DateTime)fieldDateDebutPeriode.getControl();
					fieldDateDebutPeriode.getLayoutControl().setLayoutData(cdatetimeLgr2LData);
					}
//				tfDateDebutPeriode.setPattern("dd/MM/yyyy");
				}
				
				{
					laFinPeriode = new Label(groupParam, SWT.NONE);
					GridData laFinLData = new GridData();
					laFinLData.widthHint = 91;
					laFinLData.heightHint = 22;
					laFinPeriode.setLayoutData(laFinLData);
					laFinPeriode.setText("Fin");
				}
				{
					GridData cdatetimeLgr2LData = new GridData();
					cdatetimeLgr2LData.heightHint = 23;
				if(!decore) {
					tfDateFinPeriode = new DateTime(groupParam, SWT.BORDER | SWT.DROP_DOWN);
					tfDateFinPeriode.setLayoutData(cdatetimeLgr2LData);
					} else {
					fieldDateFinPeriode = new DecoratedField(groupParam, SWT.BORDER | SWT.DROP_DOWN, new DateTimeControlCreator());
					tfDateFinPeriode = (DateTime)fieldDateFinPeriode.getControl();
					fieldDateFinPeriode.getLayoutControl().setLayoutData(cdatetimeLgr2LData);
					}
//				tfDateFinPeriode.setPattern("dd/MM/yyyy");

				}
				{
				laTiers = new Label(groupParam, SWT.NONE);
				GridData laCodeRelanceLData = new GridData();
				laCodeRelanceLData.horizontalSpan = 2;
				laTiers.setLayoutData(laCodeRelanceLData);
				laTiers.setText("Code Tiers");
			}
			{
				tfTiers = new Text(groupParam,  SWT.BORDER);
				GridData tfCodeRelanceLData = new GridData();
				tfCodeRelanceLData.horizontalSpan = 2;
				tfCodeRelanceLData.horizontalAlignment = GridData.FILL;
				tfTiers.setLayoutData(tfCodeRelanceLData);
			}
				{
				laCodePrelevement = new Label(groupParam, SWT.NONE);
				GridData laCodeRelanceLData = new GridData();
				laCodeRelanceLData.horizontalSpan = 2;
				laCodePrelevement.setLayoutData(laCodeRelanceLData);
				laCodePrelevement.setText("Code Prélèvement");
			}
			{
				tfCodePrelevement = new Text(groupParam,  SWT.BORDER);
				GridData tfCodeRelanceLData = new GridData();
				tfCodeRelanceLData.horizontalSpan = 2;
				tfCodeRelanceLData.horizontalAlignment = GridData.FILL;
//				tfCodeRelanceLData.heightHint = 19;
				tfCodePrelevement.setLayoutData(tfCodeRelanceLData);
			}
				/*
				{
					btnListeDoc = new Button(groupParam, SWT.PUSH | SWT.CENTER);
					GridData btnListeDocLData = new GridData();
					btnListeDocLData.verticalAlignment = GridData.BEGINNING;
					btnListeDocLData.horizontalAlignment = GridData.END;
					btnListeDoc.setLayoutData(btnListeDocLData);
					btnListeDoc.setText("Liste documents déjà exportés");
				} 
				*/
				{
					laListePaiement = new Label(groupParam, SWT.NONE);
					GridData laCodeRelanceLData = new GridData();
					laListePaiement.setLayoutData(laCodeRelanceLData);
					laListePaiement.setText("Type de réponse");
				} 
				{
					GridData cbListeDocLData = new GridData();
					cbListeDocLData.heightHint = 22;
					cbListeDocLData.horizontalAlignment = GridData.FILL;
					cbListeReponse = new Combo(groupParam, SWT.NONE);
					cbListeReponse.setLayoutData(cbListeDocLData);
				} 
//								{
//					laListeCompte = new Label(groupParam, SWT.NONE);
//					GridData laCodeRelanceLData = new GridData();
//					laCodeRelanceLData.widthHint = 95;
//					laCodeRelanceLData.heightHint = 14;
//					laListeCompte.setLayoutData(laCodeRelanceLData);
//					laListeCompte.setText("Compte bancaire");
//				}
//				{
//					GridData cbListeDocLData = new GridData();
//					cbListeDocLData.heightHint = 22;
//					cbListeDocLData.widthHint = 146;
//					cbListeCompte = new Combo(groupParam, SWT.NONE);
//					cbListeCompte.setLayoutData(cbListeDocLData);
//				}
				{
					btnValiderParam = new Button(groupParam, SWT.PUSH | SWT.CENTER);
					GridData btnValiderParamLData = new GridData();
					btnValiderParamLData.widthHint = 147;
//					btnValiderParamLData.verticalAlignment = GridData.FILL;
					btnValiderParamLData.horizontalAlignment = GridData.BEGINNING;
					btnValiderParam.setLayoutData(btnValiderParamLData);
					btnValiderParam.setText("Valider");
				}
//				{
//					prelevExport = new Button(groupParam, SWT.CHECK | SWT.CENTER);
//					GridData prelevExportLData = new GridData();
////					prelevExportLData.widthHint = 147;
////					prelevExportLData.verticalAlignment = GridData.FILL;
////					prelevExportLData.horizontalAlignment = GridData.END;
//					prelevExport.setLayoutData(prelevExportLData);
//					prelevExport.setText("Déjà Exportés");
//				}
//				{
//					prelevNonExport = new Button(groupParam, SWT.CHECK | SWT.CENTER);
//					GridData prelevNonExportLData = new GridData();
////					prelevNonExportLData.widthHint = 147;
////					prelevNonExportLData.verticalAlignment = GridData.FILL;
////					prelevNonExportLData.horizontalAlignment = GridData.END;
//					prelevNonExport.setLayoutData(prelevNonExportLData);
//					prelevNonExport.setText("Non Exportés");
//				}

			}
			{
				groupFactureNonRegle = new Group(this, SWT.NONE);
				GridLayout groupFactureNonRegleLayout = new GridLayout();
				groupFactureNonRegleLayout.numColumns = 4;
				groupFactureNonRegle.setLayout(groupFactureNonRegleLayout);
				GridData groupFactureNonRegleLData = new GridData();
				groupFactureNonRegleLData.horizontalAlignment = GridData.FILL;
				groupFactureNonRegleLData.grabExcessHorizontalSpace = true;
				groupFactureNonRegleLData.verticalAlignment = GridData.FILL;
				groupFactureNonRegleLData.horizontalSpan = 2;
				groupFactureNonRegleLData.grabExcessVerticalSpace = true;
				groupFactureNonRegle.setLayoutData(groupFactureNonRegleLData);
				groupFactureNonRegle.setText("Liste des prélèvements");

//				/*{
//					laDate = new Label(groupFactureNonRegle, SWT.NONE);
//					GridData laDateLData = new GridData();
//					laDate.setLayoutData(laDateLData);
//					laDate.setText("date de la remise");
//				}
//				{
//					GridData tfDateRemiseLData = new GridData();
//					tfDateRemiseLData.widthHint = 130;
//					tfDateRemiseLData.heightHint = 20;
//					tfDateRemise = new DateTime(groupFactureNonRegle,  SWT.BORDER | SWT.DROP_DOWN);
//					tfDateRemise.setLayoutData(tfDateRemiseLData);
//				}*/

			}

//			{
//				GridData expandBarLData = new GridData();
//				expandBarLData.horizontalAlignment = GridData.FILL;
//				expandBarLData.horizontalSpan = 2;
//				expandBarLData.verticalAlignment = GridData.BEGINNING;
//				expandBarLData.verticalSpan = 2;
//				expandBar = new ExpandBar(this, SWT.V_SCROLL);
//				expandBar.setLayoutData(expandBarLData);
//			}
			{
				GridData paBtnLData = new GridData();
				paBtnLData.horizontalAlignment = GridData.CENTER;
				paBtnLData.grabExcessHorizontalSpace = true;
				paBtnLData.verticalAlignment = GridData.END;
				paBtnLData.widthHint = 181;
				paBtnLData.heightHint = 35;
				paBtnLData.horizontalSpan = 2;
				paBtn = new Composite(this, SWT.NONE);
				GridLayout paBtnLayout = new GridLayout();
				paBtnLayout.makeColumnsEqualWidth = true;
				paBtnLayout.numColumns = 2;
				paBtn.setLayout(paBtnLayout);
				paBtn.setLayoutData(paBtnLData);
				{
					btnSuivant = new Button(paBtn, SWT.PUSH | SWT.CENTER);
					GridData btnSuivantLData = new GridData();
					btnSuivantLData.widthHint = 81;
					btnSuivantLData.horizontalAlignment = GridData.CENTER;
					btnSuivantLData.verticalAlignment = GridData.FILL;
					btnSuivant.setLayoutData(btnSuivantLData);
					btnSuivant.setText("Suivant");
				}
				{
					btnFermer = new Button(paBtn, SWT.PUSH | SWT.CENTER);
					GridData btnFermerLData = new GridData();
					btnFermerLData.horizontalAlignment = GridData.CENTER;
					btnFermerLData.widthHint = 81;
					btnFermerLData.verticalAlignment = GridData.FILL;
					btnFermer.setLayoutData(btnFermerLData);
					btnFermer.setText("Fermer");
				}
			}
			{
				GridData laMessageLData = new GridData();
				laMessageLData.horizontalAlignment = GridData.FILL;
				laMessageLData.grabExcessHorizontalSpace = true;
				laMessageLData.horizontalSpan = 2;
				laMessage = new Label(this, SWT.NONE);
				laMessage.setLayoutData(laMessageLData);
			}

			this.layout();
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DateTime getTfDateDebutPeriode() {
		return tfDateDebutPeriode;
	}

	public DateTime getTfDateFinPeriode() {
		return tfDateFinPeriode;
	}


	public Group getGroupParam() {
		return groupParam;
	}



	public Group getGroupFactureNonRegle() {
		return groupFactureNonRegle;
	}



	public Label getLaDateDebut() {
		return laDateDebut;
	}

	public Label getLaFinPeriode() {
		return laFinPeriode;
	}

//	public Label getLaTiers() {
//		return laTiers;
//	}
//
//	public Text getTfTiers() {
//		return tfTiers;
//	}

	public Composite getPaBtn() {
		return paBtn;
	}

	public Button getBtnFermer() {
		return btnFermer;
	}

	public Button getBtnSuivant() {
		return btnSuivant;
	}

	public Button getBtnValiderParam() {
		return btnValiderParam;
	}
	
	public Label getLaMessage() {
		return laMessage;
	}

	

	public DecoratedField getFieldDateDebutPeriode() {
		return fieldDateDebutPeriode;
	}

	public DecoratedField getFieldDateFinPeriode() {
		return fieldDateFinPeriode;
	}
	
	public Text getTfCodePrelevement() {
		return tfCodePrelevement;
	}
	
	public Label getLaCodePrelevement() {
		return laCodePrelevement;
	}

	public Combo getCbListeReponse() {
		return cbListeReponse;
	}

	public Text getTfTiers() {
		return tfTiers;
	}



}
