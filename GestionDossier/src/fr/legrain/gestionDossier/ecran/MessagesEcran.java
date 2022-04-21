package fr.legrain.gestionDossier.ecran;


import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessagesEcran {
	private static final String BUNDLE_NAME = "fr.legrain.gestionDossier.ecran.messagesEcran"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private MessagesEcran() {
	}

	public static String getString(String key) {
		// TODO Raccord de méthode auto-généré
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
