/*
 * cf : http://dev.eclipse.org/viewcvs/index.cgi/org.eclipse.jface.snippets/Eclipse%20JFace%20Snippets/org/eclipse/jface/snippets/window/Snippet020CustomizedControlTooltips.java?view=markup
 */
package fr.legrain.document.etat.devis.ecrans;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ToolTipDetail extends ToolTip {
	
	private Shell parentShell;
	
	private String headerText = "Détail";
	
	public static final String HEADER_BG_COLOR = Policy.JFACE + ".TOOLTIP_HEAD_BG_COLOR";
	
	public static final String HEADER_FG_COLOR = Policy.JFACE + ".TOOLTIP_HEAD_FG_COLOR";
	
	public static final String HEADER_FONT = Policy.JFACE + ".TOOLTIP_HEAD_FONT";
	
	public static final String HEADER_CLOSE_ICON = Policy.JFACE + ".TOOLTIP_CLOSE_ICON";
	public static final String HEADER_HELP_ICON = Policy.JFACE + ".TOOLTIP_HELP_ICON";
	
	public ToolTipDetail(Control control) {
		super(control);
		this.parentShell = control.getShell();
	}

	protected Composite createToolTipContentArea(Event event,
			Composite parent) {
		JFaceResources.getColorRegistry().put(ToolTipDetail.HEADER_BG_COLOR, new RGB(255,255,255));
		JFaceResources.getFontRegistry().put(ToolTipDetail.HEADER_FONT, JFaceResources.getFontRegistry().getBold(JFaceResources.getDefaultFont().getFontData()[0].getName()).getFontData());

		
		JFaceResources.getImageRegistry().put(ToolTipDetail.HEADER_CLOSE_ICON,ImageDescriptor.createFromFile(ToolTipDetail.class, "logo_lgr_16.png"));
		JFaceResources.getImageRegistry().put(ToolTipDetail.HEADER_HELP_ICON,ImageDescriptor.createFromFile(ToolTipDetail.class, "sample.gif"));

		Composite comp = new Composite(parent,SWT.NONE);
		
		GridLayout gl = new GridLayout(1,false);
		gl.marginBottom=0;
		gl.marginTop=0;
		gl.marginHeight=0;
		gl.marginWidth=0;
		gl.marginLeft=0;
		gl.marginRight=0;
		gl.verticalSpacing=1;
		comp.setLayout(gl);
		
		Composite topArea = new Composite(comp,SWT.NONE);
		GridData data = new GridData(SWT.FILL,SWT.FILL,true,false);
		data.widthHint=200;
		topArea.setLayoutData(data);
		topArea.setBackground(JFaceResources.getColorRegistry().get(HEADER_BG_COLOR));
		
		gl = new GridLayout(2,false);
		gl.marginBottom=2;
		gl.marginTop=2;
		gl.marginHeight=0;
		gl.marginWidth=0;
		gl.marginLeft=5;
		gl.marginRight=2;
		
		topArea.setLayout(gl);
		
		Label l = new Label(topArea,SWT.NONE);
		l.setText(headerText);
		l.setBackground(JFaceResources.getColorRegistry().get(HEADER_BG_COLOR));
		l.setFont(JFaceResources.getFontRegistry().get(HEADER_FONT));
		l.setForeground(JFaceResources.getColorRegistry().get(HEADER_FG_COLOR));
		l.setLayoutData(new GridData(GridData.FILL_BOTH));
		
//		Composite iconComp = new Composite(topArea,SWT.NONE);
//		iconComp.setLayoutData(new GridData());
//		iconComp.setLayout(new GridLayout(2,false));
//		iconComp.setBackground(JFaceResources.getColorRegistry().get(HEADER_BG_COLOR));
//		
//		gl = new GridLayout(2,false);
//		gl.marginBottom=0;
//		gl.marginTop=0;
//		gl.marginHeight=0;
//		gl.marginWidth=0;
//		gl.marginLeft=0;
//		gl.marginRight=0;
//		iconComp.setLayout(gl);
//		
//		Label helpIcon = new Label(iconComp,SWT.NONE);
//		helpIcon.setBackground(JFaceResources.getColorRegistry().get(HEADER_BG_COLOR));
//		helpIcon.setImage(JFaceResources.getImage(HEADER_HELP_ICON));
//		helpIcon.addMouseListener(new MouseAdapter() {
//
//			public void mouseDown(MouseEvent e) {
//				hide();
//				openHelp();
//			}
//		});
//		
//		Label closeIcon = new Label(iconComp,SWT.NONE);
//		closeIcon.setBackground(JFaceResources.getColorRegistry().get(HEADER_BG_COLOR));
//		closeIcon.setImage(JFaceResources.getImage(HEADER_CLOSE_ICON));
//		closeIcon.addMouseListener(new MouseAdapter() {
//
//			public void mouseDown(MouseEvent e) {
//				parentShell.setFocus();
//				hide();
//			}
//		});
		
		
		createContentArea(comp).setLayoutData(new GridData(GridData.FILL_BOTH));
		
		return comp;
	}
	
	protected Composite createContentArea(Composite parent) {
		return new Composite(parent,SWT.NONE);
	}
	
	protected void openHelp() {
		parentShell.setFocus();
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
}