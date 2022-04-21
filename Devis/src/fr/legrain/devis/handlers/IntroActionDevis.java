package fr.legrain.devis.handlers;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class IntroActionDevis implements IIntroAction {
	
	static Logger logger = Logger.getLogger(IntroActionDevis.class.getName());

	public void run(IIntroSite site, Properties params) {
		try {
			IHandlerService handlerService = (IHandlerService)PlatformUI.getWorkbench().getService(IHandlerService.class);
			handlerService.executeCommand("fr.legrain.devis", null);
		} catch (ExecutionException e) {
			logger.error("",e);
		} catch (NotDefinedException e) {
			logger.error("",e);
		} catch (NotEnabledException e) {
			logger.error("",e);
		} catch (NotHandledException e) {
			logger.error("",e);
		}
	}

}
