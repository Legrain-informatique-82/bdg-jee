package fr.legrain.document.divers;

import fr.legrain.documents.dao.TaTPaiement;

public class ConstEditionDocument {
	
	
	public static String COMMENTAIRE_EDITION_DEFAUT = "Edition Document";
	/**
	 * parametre of edition (TIERS)
	 */
	public static final String PARAM_ID_DOCUMENT= "paramID_DOC";
	public static final String PARAM_ID_DOC="paramID_DOC";
	public static final String PARAM_CHOIX_DEST="ParamChoix";
	
	public static final String PARAM_CAPITAL="capital";
	public static final String PARAM_APE="ape";
	public static final String PARAM_SIRET="siret";
	public static final String PARAM_RCS="rcs";
	public static final String PARAM_NOM_ENTREPRISE="nomEntreprise";
	
	/**
	 * message edition
	 */
	public static final String MESSAGE_EDITION_PREFERENCE= "Le chemin d'edition n'est pas correct !! ";
	public static final String TITLE_MESSAGE_EDITION_PREFERENCE = "Erreur chemin edition";
	

	
	public static final String[] arrayTypeEntity = {TaTPaiement.class.getSimpleName()};

}
