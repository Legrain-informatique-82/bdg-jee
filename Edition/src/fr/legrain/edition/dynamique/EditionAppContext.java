package fr.legrain.edition.dynamique;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.birt.report.viewer.api.AppContextExtension;

public class EditionAppContext extends AppContextExtension {
	
	public static final String APP_CONTEXT_NAME = "EditionAppContext";
	
	public Map getAppContext(Map appContext) {
		Map hm = super.getAppContext(appContext);
		hm.putAll(getExtensionMap());
		return hm;			
	}
	
	public String getName() {
		return APP_CONTEXT_NAME;
	}
	
	public HashMap getExtensionMap() {
		HashMap m = new HashMap();
		m.put("PARENT_CLASSLOADER", this.getClass().getClassLoader());
		return m;
	}
	
}
