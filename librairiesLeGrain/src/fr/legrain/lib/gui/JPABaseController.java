//package fr.legrain.lib.gui;
//
////import java.awt.Component;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.jface.action.ActionContributionItem;
//import org.eclipse.jface.action.IAction;
//import org.eclipse.jface.action.IContributionItem;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.PaintEvent;
//import org.eclipse.swt.events.PaintListener;
//import org.eclipse.swt.events.TraverseEvent;
//import org.eclipse.swt.events.TraverseListener;
//import org.eclipse.swt.graphics.GC;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.graphics.Rectangle;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.DateTime;
//import org.eclipse.swt.widgets.ExpandBar;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Menu;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.commands.ICommandService;
//import org.eclipse.ui.menus.CommandContributionItem;
//
//import fr.legrain.lib.data.AbstractLgrDAO;
//import fr.legrain.lib.data.LgrConstantesSWT;
//import fr.legrain.lib.data.ModeObjetEcran;
//
//
///**
// * <p>Title: </p>
// * <p>Description: </p>
// * <p>Copyright: Copyright (c) 2005</p>
// * <p>Company: Le Grain SA</p>
// * @author nicolas
// * @version 1.0
// */
//
//public abstract class JPABaseController {
//
//	protected Composite vue = null;
//	private Control focusCourantSWT=null; //composant qui possède le focus actuellement
//	private Control focusAvantAideSWT=null; //composant qui possède le focus actuellement
//    protected List listeEntity;
//    protected List<Composite> listeCompositeExcluCouleur = new ArrayList<Composite>();
//	static Logger logger = Logger.getLogger(JPABaseController.class.getName());
//	//private static Component focusedContainer = null; //parent/Container (Swing) le plus éloigné du JComponent qui possède le focus actuellement
//
//	public JPABaseController() {}
//
//	/**
//	 * Configuration du JPanel
//	 */
//	abstract public ResultAffiche configPanel(ParamAffiche param);
//	abstract protected void sortieChamps();
//	/**
//	 * @return - Vue gérée par ce controller
//	 */
//	abstract public Composite getVue();
//	
//	/**
//	 * 
//	 * @param vue - vue du controller
//	 * @param exclusion - liste des composite, pour les quels ont ne souhaite pas appliquer les couleurs par defaut.
//	 */
//	protected void setVue(Composite vue, List<Composite> exclusion) {
//		this.vue = vue;
//		changeCouleur(vue);
//	}
//
//	protected void setVue(Composite vue) {
//		this.vue = vue;
//		changeCouleur(vue);
//	}
//
//	/**
//	 * Initialisation des menus popups et boutons
//	 */
//	public void initPopupAndButtons (Map m, Menu[] popups) {
//				try{
//		Iterator iteKeyActions = m.keySet().iterator();
//
//		int[] positionDansMenu = new int[popups.length];
//		for (int i = 0; i < positionDansMenu.length; i++) {
//			positionDansMenu[i] = 0;
//		}
//
//		IContributionItem menuItem = null;
//		ICommandService cs = (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
//		while(iteKeyActions.hasNext()) {			
//			Object keyCourante = (Object)iteKeyActions.next();
//			if (keyCourante == null){
//				//on traite les actions autres qui ne sont pas liées à des boutons
//				Object [] Tab =((Object [])m.get(keyCourante)); 
//				for(int i = 0; i < Tab.length; i++)
//					if(Tab[i] != null){
//						for (int j = 0; j < popups.length; j++) {
//							if(Tab[i] instanceof IAction)
//								menuItem = new ActionContributionItem((IAction)Tab[i]); 
//							else if(Tab[i] instanceof String) {
//								menuItem = new CommandContributionItem(
//										PlatformUI.getWorkbench(),
//										null,
//										(String)Tab[i],
//										null,
//										null,
//										null,
//										null,
//										cs.getCommand((String)Tab[i]).getName(),
//										null,
//										cs.getCommand((String)Tab[i]).getName(),
//										SWT.PUSH
//								);
//							}
//							menuItem.fill(popups[j],positionDansMenu[j]);
//							positionDansMenu[j]++;
//						}				    	   
//					}
//			}
//			else {
//				for (int j = 0; j < popups.length; j++) {
//					if(m.get(keyCourante) instanceof IAction)
//						menuItem = new ActionContributionItem((IAction)m.get(keyCourante));
//					else if(m.get(keyCourante) instanceof String) {
//						menuItem = new CommandContributionItem(
//								PlatformUI.getWorkbench(),
//								null,
//								(String)m.get(keyCourante),
//								null,
//								null,
//								null,
//								null,
//								cs.getCommand((String)m.get(keyCourante)).getName(),
//								null,
//								cs.getCommand((String)m.get(keyCourante)).getName(),
//								SWT.PUSH
//						);
//					}
//					menuItem.fill(popups[j],positionDansMenu[j]);
//					positionDansMenu[j]++;
//				}				    	   
//				//((JButton)keyCourante).setAction((AbstractAction)m.get(keyCourante));
//			}
//		}		
//				} catch(Exception e) {
//					logger.error("",e);
//				}
//	}
//
////	protected void initFocusSWT(AbstractLgrDAO dao, Map<ModeObjet.EnumModeObjet,Control> focus) {
////		initFocusSWT(dao,focus,false);
////	}
//	/**
//	 * Positionnement du focus en fonction du mode de l'écran
//	 * @param dao - Ensemble de données utilisé dans l'écran du controller
//	 * @param focus - [clé : mode] [valeur : composant qui à le focus par défaut pour ce mode]
//	 */
//	protected void initFocusSWT(AbstractLgrDAO dao, Map<ModeObjetEcran.EnumModeObjet,Control> focus) {
//		switch (dao.getModeObjet().getMode()) {
//		case C_MO_INSERTION:
//			if (dao.getModeObjet().getFocusCourantSWT()==null )
//				dao.getModeObjet().setFocusCourantSWT(focus.get(ModeObjetEcran.EnumModeObjet.C_MO_INSERTION));
//			break;
//		case C_MO_EDITION:
//			if (dao.getModeObjet().getFocusCourantSWT()==null){
//				if (focusCourantSWT instanceof Text){
//					if (((Text)focusCourantSWT).getEditable())
//						dao.getModeObjet().setFocusCourantSWT(focusCourantSWT);
//				}
//				if (focusCourantSWT instanceof DateTime){
//						break;
//				}					
//			}
//			if (dao.getModeObjet().getFocusCourantSWT()==null )
//				dao.getModeObjet().setFocusCourantSWT(focus.get(ModeObjetEcran.EnumModeObjet.C_MO_EDITION));
//			break;
//		default:
//			dao.getModeObjet().setFocusCourantSWT(focus.get(ModeObjetEcran.EnumModeObjet.C_MO_CONSULTATION));
//		break;
//		}
//		setFocusCourantSWT(dao.getModeObjet().getFocusCourantSWT());
//		if(dao.getModeObjet().getFocusCourantSWT()!=null)
//			dao.getModeObjet().getFocusCourantSWT().forceFocus();
//	}
//
//	/**
//	 * Initialisation des touches qui déclenchent le transfert du focus au composant suivant
//	 * @param listeComponent - composants focusable
//	 */
//	public void initDeplacementSaisie(List<Control> listeComponent) {
//		TraverseListener btnTraverseListener = (new TraverseListener(){
//			public void keyTraversed(TraverseEvent e) {
//				if(e.keyCode == SWT.ARROW_RIGHT || e.keyCode == SWT.ARROW_DOWN ) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
//				} else if(e.keyCode == SWT.ARROW_LEFT || e.keyCode == SWT.ARROW_UP) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_PREVIOUS);
//				} 				
//			}
//		});
//
//		TraverseListener textTraverseListener = (new TraverseListener(){
//			public void keyTraversed(TraverseEvent e) {
//				if(e.keyCode == SWT.ARROW_DOWN) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
//				} else if(e.keyCode == SWT.ARROW_UP) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_PREVIOUS);
//				} 
//				else if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
//				}
//			}
//		});
//		TraverseListener dateTraverseListener = (new TraverseListener(){
//			public void keyTraversed(TraverseEvent e) {
//				if(e.keyCode == SWT.ARROW_DOWN) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
//				} else if(e.keyCode == SWT.ARROW_UP) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_PREVIOUS);
//				} 
//				else
//					if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
//					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
//				}
//			}
//		});
//		TraverseListener comboTraverseListener = (new TraverseListener(){
//			public void keyTraversed(TraverseEvent e) {
////				if(e.keyCode == SWT.ARROW_DOWN) {
////					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
////				} else if(e.keyCode == SWT.ARROW_UP) {
////					((Control)e.widget).traverse(SWT.TRAVERSE_TAB_PREVIOUS);
////				} 
////				else
//					if(e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
//						((Control)e.widget).traverse(SWT.TRAVERSE_TAB_NEXT);
//						e.doit = false;
//				}
//			}
//		});
//
//
//	
//		for (Control component : listeComponent) {
//			if (!(component instanceof Table) ) { //Text, Button, ...
//				if (component instanceof Button) {
//					if( (((Button)component).getStyle() & SWT.CHECK) != 0 )
//						component.addTraverseListener(textTraverseListener);	
//					else
//						component.addTraverseListener(btnTraverseListener);					
//				} else if (component instanceof Text && (((Text)component).getStyle()& SWT.MULTI)==0){
//					component.addTraverseListener(textTraverseListener);	
//				} else if (component instanceof DateTime){
//					component.addTraverseListener(dateTraverseListener);
//				} else if (component instanceof Combo){
//					component.addTraverseListener(comboTraverseListener);
//				}
//				//component.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,setBackward);			
//			}			
//		}		
//		//changeCouleur(vue);
//	}
//
//	/**
//	 * Listes des composants focusable. Nécessaire pour la gestion de la propriété <code>focusCourant</code>
//	 * @param listeComponent
//	 */
//	public void listeComponentFocusableSWT(List<Control> listeComponent){		
//		VueFocusListenerSWT vueFocusListenerSWT=new VueFocusListenerSWT();
//		for (Control components : listeComponent) {
//			components.addFocusListener(vueFocusListenerSWT);
//		}
//		
//	}	
//
//	/**
//	 * Mise à jour de la propriété <code>focusCourant</code>
//	 */
//	private class VueFocusListenerSWT implements org.eclipse.swt.events.FocusListener{
//
//		public void focusGained(org.eclipse.swt.events.FocusEvent e) {
//			try{
//				sortieChamps();
//				setFocusCourantSWT((Control)e.getSource());
//				if(e.getSource() instanceof Text) {
//					((Text)e.getSource()).selectAll();
//					if(((Text)e.getSource()).getEditable())
//						((Text)e.getSource()).setBackground(LgrConstantesSWT.colorChampsPointeSWT);
//					else
//						((Text)e.getSource()).setBackground(LgrConstantesSWT.enabled);
//				}
//
//				if(e.getSource() instanceof Control) {
//					((Control)e.getSource()).addPaintListener(paintListener);
//				}
//				System.out.println("focusGained()"+getFocusCourantSWT());
////				LgrPartListener.getTestPartListener().getLgrActivePart().setFocus();
//			}catch (Exception e1) {
//				logger.error("", e1);
//			}
//		}
//
//		public void focusLost(org.eclipse.swt.events.FocusEvent e) {
//			if(e.getSource() instanceof Text) {
//				if(((Text)e.getSource()).getEditable())
//					((Text)e.getSource()).setBackground(LgrConstantesSWT.colorFondCompSaisieSWT);
//				else
//					((Text)e.getSource()).setBackground(LgrConstantesSWT.enabled);				
//			}
//			if(e.getSource() instanceof Control) {
//				final Control c = ((Control)e.getSource());
//				c.removePaintListener(paintListener);
//				if(vue != null) {
//					vue.redraw(0,0,vue.getBounds().width,vue.getBounds().height,true);
//					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().redraw(0,0,PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getBounds().width,PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getBounds().height,true);
//					//c.getParent().redraw(0,0,c.getParent().getBounds().width,c.getParent().getBounds().height,true);
//					//vue.getDisplay().update();
//				}
//			}
//		}
//	}
//	
//	private PaintListener paintListener = new PaintListener() {
//		public void paintControl(PaintEvent e) {
////			//GC gc = e.gc;
////			if(e.display.getFocusControl()!=null){
////				GC gc = new GC(e.display);
////
////				if("win32".equals(SWT.getPlatform())) {
////					gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED));
////					gc.setLineWidth(2);
////
////					Rectangle coordFocus = e.display.getFocusControl().getBounds();
////					Point coordFocusDansEcran = e.display.getFocusControl().toDisplay(new Point(0,0));
////
////					if((e.display.getFocusControl().getStyle() & SWT.BORDER) !=0) {
////						gc.drawRoundRectangle(coordFocusDansEcran.x-3,coordFocusDansEcran.y-3,coordFocus.width+2,coordFocus.height+2,5,5);
////					} else {
////						gc.drawRoundRectangle(coordFocusDansEcran.x,coordFocusDansEcran.y,coordFocus.width,coordFocus.height,5,5);
////					}
////				} else if("gtk".equals(SWT.getPlatform())) {
////					gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED));
////					gc.setLineWidth(2);
////
////					Rectangle coordFocus = e.display.getFocusControl().getBounds();
////					Point coordFocusDansEcran = e.display.getFocusControl().toDisplay(new Point(0,0));
////
////					if(e.display.getFocusControl() instanceof Table) {
////						coordFocus.height -= ((Table)e.display.getFocusControl()).getHeaderHeight();
////					}
////					
////					if((e.display.getFocusControl().getStyle() & SWT.BORDER) !=0) {
////						//gc.drawRoundRectangle(coordFocusDansEcran.x-2,coordFocusDansEcran.y-2,coordFocus.width+3,coordFocus.height+3,5,5);
////						gc.drawRoundRectangle(coordFocusDansEcran.x-3,coordFocusDansEcran.y-3,coordFocus.width,coordFocus.height,5,5);
////					} else {
////						gc.drawRoundRectangle(coordFocusDansEcran.x,coordFocusDansEcran.y,coordFocus.width,coordFocus.height,5,5);
////					}
////				}
////
////				gc.dispose();
////			}
//		}
//	};
//	
//	/**
//	 * Initialisation des couleurs d'un Container ainsi que de tous les composants
//	 * qu'il contient
//	 * @param c - Container dont on souhaite changer la couleur
//	 */
//	public void changeCouleur(Composite c) {
//		changeCouleur(c, null);
//	}
//
//	/**
//	 * Initialisation des couleurs d'un Container ainsi que de tous les composants
//	 * qu'il contient
//	 * @param c - Container dont on souhaite changer la couleur
//	 * @param exclusion - Container dont on ne souhaite pas changer la couleur, à exclure du traitement
//	 */
//	public void changeCouleur(Composite c, List<Composite> exclusion) {
//		
//		if(c==null) c = this.getVue();
//		
//		c.setBackground(LgrConstantesSWT.colorFondPanelSWT);
//		for (int i = 0; i < c.getChildren().length; i++) {
//			//Affectation des couleurs suivant le composant
//			if(c.getChildren()[i] instanceof Composite) {
//				((Composite)c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondPanelSWT);
//			} else if(c.getChildren()[i] instanceof Button) {
//				if((c.getChildren()[i].getStyle() & SWT.CHECK) !=0) {
//					(c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondPanelSWT);
//				}
//				//((Button)c.getChildren()[i]).setBackground(fondBouton);
//				//((Button)c.getChildren()[i]).setForeground(blanc);
//			} else
//				if(c.getChildren()[i] instanceof Text) {
//				if(((Text)c.getChildren()[i]).getEditable())//isa le 18/03/2009	
//				  ((Text)c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondCompSaisieSWT);
//				else
//					((Text)c.getChildren()[i]).setBackground(LgrConstantesSWT.enabled);
//			} else if(c.getChildren()[i] instanceof Label) {
//				((Label)c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondPanelSWT);
//			} else if(c.getChildren()[i] instanceof Table) {
//				//((Table)c.getChildren()[i]).getTableHeader().setBackground(fondCompSaisie);
//				((Table)c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondCompSaisieSWT);
//				//((Table)c.getChildren()[i]).setGridColor(quadrillage);
//				//((Table)c.getChildren()[i]).setSelectionBackground(fondBouton);
//			} else if(c.getChildren()[i] instanceof TabFolder) {
//				((TabFolder)c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondPanelSWT);
//			} else if(c.getChildren()[i] instanceof ExpandBar) {
//				((ExpandBar)c.getChildren()[i]).setBackground(LgrConstantesSWT.colorFondPanelSWT);
//			}
//
//			//Appel récursif sur les containers
//			if(c.getChildren()[i] instanceof Composite) {
//				if((exclusion!=null && !exclusion.contains(c.getChildren()[i]))
//						|| !listeCompositeExcluCouleur.contains(c.getChildren()[i])) //le composite ne fait pas parti de la liste des composite exclu
//					changeCouleur((Composite)c.getChildren()[i]);
//			}
//
////			//Modification des titres pour les bordures avec titre
////			if(c.getChildren()[i] instanceof Composite) {
////				if(((JComponent)c.getComponents()[i]).getBorder()!=null) {
////					if(((JComponent)c.getComponents()[i]).getBorder() instanceof TitledBorder) {
////						((TitledBorder)((JComponent)c.getComponents()[i]).getBorder()).setTitleColor(LgrConstantes.fondBouton);
////						((TitledBorder)((JComponent)c.getComponents()[i]).getBorder()).setTitle(((TitledBorder)((JComponent)c.getComponents()[i]).getBorder()).getTitle().toUpperCase());
////						((TitledBorder)((JComponent)c.getComponents()[i]).getBorder()).setTitleFont(new Font(null,Font.BOLD,12));
////					}
////				}
////			}
//		}
//
//	}
//
////	public static Component getFocusedContainer() {
////		return focusedContainer;
////	}
//
//	public void setFocusCourantSWT(Control focusCourantSWT) {
//		if (focusCourantSWT!=null)
//			this.focusCourantSWT = focusCourantSWT;
//		System.out.println("focusCourant ToolTip= "+this.focusCourantSWT.getToolTipText());
//		System.out.println("focusCourant = "+this.focusCourantSWT);
//	}
//
//	public void setFocusCourantSWTHorsApplication(Control focusCourantSWT) {
//		if (focusCourantSWT!=null)
//			this.focusCourantSWT = focusCourantSWT;
//		if (focusCourantSWT!=null){
////			focusedContainer = focusCourantSWT.getParent();
////			while(focusedContainer.getParent()!=null)
////			focusedContainer = focusedContainer.getParent();
//		}
////		LgrConstantes.setFocusedLgr(focusedContainer);
//	}
//	
//	public Control getFocusCourantSWT() {
//		return focusCourantSWT;
//	}
//
//	public Control getFocusAvantAideSWT() {
//		return focusAvantAideSWT;
//	}
//
//	public void setFocusAvantAideSWT(Control focusAvantAideSWT) {
//		if (focusAvantAideSWT!=null){
//			this.focusAvantAideSWT = focusAvantAideSWT;
//			System.out.println("focusAvantAideSWT ToolTip= "+this.focusAvantAideSWT.getToolTipText());
//		}
//	}
//	
//	/**
//	 * Controle toute l'interface
//	 * @return
//	 * @throws Exception
//	 */
//	public IStatus validateUI() throws Exception {
//		return null;
//	}
//	
//	/**
//	 * Controle un champ de l'interface
//	 * @param nomChamp - nom du champ
//	 * @param value - valeur du champ
//	 * @return
//	 */
//	public IStatus validateUIField(String nomChamp, Object value) {
//		return null;
//	}
//	public List getListeEntity() {
//		return listeEntity;
//	}
//	public void setListeEntity(List listeEntity) {
//		this.listeEntity = listeEntity;
//	}
//
//	public List<Composite> getListeCompositeExcluCouleur() {
//		return listeCompositeExcluCouleur;
//	}
//}
//
