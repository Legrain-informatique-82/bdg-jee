/**
 * DocumentsController.java
 */
package fr.legrain.statistiques.commande.controllers;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import fr.legrain.departement.dao.TaDepartementsDAO;
import fr.legrain.documents.dao.TaBoncdeDAO;
import fr.legrain.documents.dao.TaBonlivDAO;
import fr.legrain.documents.dao.TaDevisDAO;
import fr.legrain.documents.dao.TaFacture;
import fr.legrain.documents.dao.TaRDocumentDAO;
import fr.legrain.statistiques.GoogleMapChart;
import fr.legrain.statistiques.Outils;
import fr.legrain.statistiques.GoogleMapChart.ResultRepartition;
import fr.legrain.statistiques.controllers.FormPageControllerPrincipal;
import fr.legrain.statistiques.controllers.RepartitionControllerMini;
import fr.legrain.statistiques.ecrans.PaFormPage;

/**
 * Classe controller de la section documents
 * @author nicolas²
 *
 */
public class RepartitionControllerCommandes extends RepartitionControllerMini{
	static Logger logger = Logger.getLogger(RepartitionControllerCommandes.class.getName());	

	private Class objetIHM = null;
	private TaRDocumentDAO taRDocumentDAO = null;
	private TaFacture taFacture = null;
	private TaDevisDAO taDevisDAO = null;
	private TaBoncdeDAO taBoncdeDAO = null;
	private TaBonlivDAO taBonlivDAO = null;
	

	
	public RepartitionControllerCommandes(FormPageControllerPrincipal masterContoller,
			PaFormPage vue, EntityManager em) {
		super(masterContoller, vue, em);
		// TODO Auto-generated constructor stub
	}

	public void initialiseModelIHM() {
		// Affichage page internet dans le navigateur
		if (Outils.isInternetReachable()){	// Connexion internet active
			
			taDepartementsDAO = new TaDepartementsDAO(masterController.getMasterDAOEM());
			// On récupère les CA sur les régions
			List<Object> listeRegions = taDepartementsDAO.chiffreDocParRegions("TaBoncde",
					Outils.extractDate(vue.getCompositeSectionParam().getCdateDeb()),
					Outils.extractDate(vue.getCompositeSectionParam().getCdatefin()));

			// Liste qui va contenir les informations utiles pour la répartition
			LinkedList<ResultRepartition> listResultRepartition = new LinkedList<ResultRepartition>();
			for (Object object : listeRegions) {
				listResultRepartition.add(
						new ResultRepartition(
								(BigDecimal)((Object[])object)[0], 
								(String)((Object[])object)[1],
								(String)((Object[])object)[2])
				);
			}
			mapChart = new GoogleMapChart(listResultRepartition,vue.getCompositeSectionRepartition().getMapBrowser());
			mapChart.setLabel("Montant Bons Commande");
			vue.getCompositeSectionRepartition().setPage(mapChart.getPageHTML());
			

		} else { // Connexion internet inactive
			vue.getCompositeSectionRepartition().setPage("L'affichage de la" +
			" carte des bons de commande requiert une connexion internet active.");
		}


	}
}
