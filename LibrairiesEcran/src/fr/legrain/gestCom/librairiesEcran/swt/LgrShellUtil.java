package fr.legrain.gestCom.librairiesEcran.swt;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.contexts.IContextActivation;

import fr.legrain.lib.gui.ParamAffiche;
import fr.legrain.lib.gui.ParamAfficheSWT;
import fr.legrain.lib.gui.ResultAffiche;
import fr.legrain.lib.gui.aide.PaAideRechercheSWT;
import fr.legrain.lib.gui.aide.PaAideSWT;

/**
 * Classe utilitaire pour l'affichage de fenetre SWT
 * @author nicolas
 */
public class LgrShellUtil {
	
	//private JPanel panel;
	//private BaseController controller;
	public static int styleLgr = SWT.RESIZE | SWT.CLOSE | SWT.TITLE | SWT.MAX | SWT.APPLICATION_MODAL;
//	static {
//		//bug swt : #190480 (https://bugs.eclipse.org/bugs/show_bug.cgi?id=190480)
//      //http://dev.eclipse.org/newslists/news.eclipse.platform.swt/msg35186.html
//		if(Platform.getOS().equals(Platform.OS_LINUX)) {
//			styleLgr = SWT.RESIZE | SWT.CLOSE | SWT.TITLE | SWT.MAX;
//		}
//	}	
	private boolean block=false;
	private static Shell shell;
	static Logger logger = Logger.getLogger(LgrShellUtil.class.getName());
	
	//private Shell s;
	public static EJBBaseControllerSWT controllerLocalSWT = null;
	public static EJBBaseControllerSWT controllerLocalAideSWT = null;
	public LgrShellUtil() {}
	
	
	static public ResultAffiche afficheSWT(ParamAffiche paramComposite, ParamAfficheSWT paramShell,
			Composite composite, EJBBaseControllerSWT controller, Shell parent,boolean block) {
		setControllerLocalSWT(controller);

		if(paramShell==null) {
			paramShell = new ParamAfficheSWT();
			paramShell.setLargeur(1024);
			paramShell.setHauteur(768);
			paramShell.setTitre("");
		}
		paramShell.setShellStyle(styleLgr);
		shell=parent;
		//Creation et config du Shell
		parent.setText(paramShell.getTitre());
		parent.setSize(paramShell.getLargeur(),paramShell.getHauteur());
		parent.addShellListener(new ShellAdapter(){

			@Override
			public void shellClosed(ShellEvent e) {
				// TODO Auto-generated method stub
				super.shellClosed(e);
//				getControllerLocalSWT().annulerListeContext();
				((Shell)e.getSource()).dispose();
			}

			IContextActivation iContext;
			@Override
			public void shellActivated(ShellEvent e) {
				// TODO Auto-generated method stub
				super.shellActivated(e);
//				getControllerLocalSWT().annulerListeContext();
//				getControllerLocalSWT().activationContext();
			}			
		});
		//Config du panel
		controller.configPanel(paramComposite);
		
		FillLayout shellLayout = new FillLayout(SWT.HORIZONTAL | SWT.HORIZONTAL);
		parent.setLayout(shellLayout);	
		
		centreShell(parent);	
		
		parent.layout();
		parent.open();
		if (block) {
			runEventLoop(parent);
		}
        return null;
	}
	/**
	 * Affichage d'un shell contenant un unique composite,
	 * paramétrage du composite grace au controlleur qui lui est associé.
	 * @param paramComposite - paramètres pour le composite SWT
	 * @param paramShell - paramètres pour le Shell SWT
	 * @param composite - Composite SWT
	 * @param controller - gestion des evenements et de l'afficahge du Composite
	 * @param parent - Shell qui contient le composite à afficher
	 * @return - ResultAffiche
	 */
	static public ResultAffiche afficheSWT(ParamAffiche paramComposite, ParamAfficheSWT paramShell,
			Composite composite, EJBBaseControllerSWT controller, Shell parent) {
		
        return afficheSWT(paramComposite,paramShell,composite,controller,parent,false);
	}
	
	private static void runEventLoop(Shell loopShell) {

		//Use the display provided by the shell if possible
		Display display;
		if (shell == null) {
			display = Display.getCurrent();
		} else {
			display = loopShell.getDisplay();
		}

		while (loopShell != null && !loopShell.isDisposed()) {
			try {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			} catch (Throwable e) {
				//exceptionHandler.handleException(e);
			}
		}
		if (!display.isDisposed()) display.update();
	}
	/**
	 * Positionne un Shell au centre de l'écran en fonction de sa taille
	 * @param s
	 */
	static public void centreShell(Shell s) {
		Rectangle screenSize = s.getDisplay().getPrimaryMonitor().getBounds();
		Point shellSize = s.getSize();
		if (shellSize.y > screenSize.height) {
			shellSize.y = screenSize.height;
		}
		if (shellSize.x > screenSize.width) {
			shellSize.x = screenSize.width;
		}
		s.setLocation( (screenSize.width - shellSize.x) / 2, (screenSize.height - shellSize.y) / 2);
	}
	
	static public ResultAffiche afficheAideSWT(ParamAffiche paramComposite, ParamAfficheSWT paramShell,
			Composite composite, final EJBBaseControllerSWT controller, final Shell parent) {
		
		setControllerLocalAideSWT(controller);
		if(paramShell==null) {
			paramShell = new ParamAfficheAideSWT();
			paramShell.setLargeur(1024);
			paramShell.setHauteur(768);
			paramShell.setTitre("");
		}
		paramShell.setShellStyle(styleLgr);
		
		//Creation et config du Shell
		parent.setText(paramShell.getTitre());
		parent.setSize(paramShell.getLargeur(),paramShell.getHauteur());
		
		//Config du panel
		controller.configPanel(paramComposite);
		
		ShellAdapter shellAdapter = new ShellAdapter(){			

			@Override
			public void shellDeactivated(ShellEvent e) {
				// TODO Auto-generated method stub
				super.shellDeactivated(e);
//				getControllerLocalAideSWT().annulerListeContext();
				logger.debug("désactivation de l'aide ........................");
			}

			@Override
			public void shellClosed(ShellEvent e) {
				// TODO Auto-generated method stub
				super.shellClosed(e);
//				getControllerLocalAideSWT().annulerListeContext();
				((Shell)e.getSource()).dispose();
				logger.debug("fin de l'aide ????????????????????????????");
			}

			public void shellActivated(ShellEvent e) {
				super.shellActivated(e);
//				getControllerLocalAideSWT().annulerListeContext();
//				getControllerLocalAideSWT().activationContext();
				parent.getDisplay().asyncExec(new Runnable(){
					public void run() {
						((PaAideRechercheSWT)((PaAideSWT)controller.getVue()).getTabFolder().getItem(
								((PaAideSWT)controller.getVue()).getTabFolder().getSelectionIndex()).getControl())
								.getTfChoix().setFocus();
					}					
				});
				logger.debug("activation de l'aide !!!!!!!!!!!!!!!!!!!!!!!!!");
			}
				
		};
		parent.addShellListener(shellAdapter);
		
		FillLayout shellLayout = new FillLayout(SWT.HORIZONTAL | SWT.HORIZONTAL);
		parent.setLayout(shellLayout);	
		
		centreShell(parent);	
		
		parent.layout();
		parent.open();

        return null;
	}

	public static EJBBaseControllerSWT getControllerLocalSWT() {
		return controllerLocalSWT;
	}

	public static void setControllerLocalSWT(EJBBaseControllerSWT controllerLocalSWT) {
		if(LgrShellUtil.controllerLocalSWT!=null 
				&& !LgrShellUtil.controllerLocalSWT.equals(controllerLocalSWT)){
//			LgrShellUtil.controllerLocalSWT.annulerListeContext();
		}
		LgrShellUtil.controllerLocalSWT = controllerLocalSWT;
	}

	public static EJBBaseControllerSWT getControllerLocalAideSWT() {
		return controllerLocalAideSWT;
	}

	public static void setControllerLocalAideSWT(
			EJBBaseControllerSWT controllerLocalAideSWT) {
		if(LgrShellUtil.controllerLocalAideSWT!=null 
				&& !LgrShellUtil.controllerLocalAideSWT.equals(controllerLocalAideSWT)){
//			LgrShellUtil.controllerLocalAideSWT.annulerListeContext();
		}
		LgrShellUtil.controllerLocalAideSWT = controllerLocalAideSWT;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}




	
}
