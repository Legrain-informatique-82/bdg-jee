package fr.legrain.libMessageLGR;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class libmessagelgr extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "LibMessageLGR";

	// The shared instance
	private static libmessagelgr plugin;
	
	/**
	 * The constructor
	 */
	public libmessagelgr() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static libmessagelgr getDefault() {
		return plugin;
	}

}
