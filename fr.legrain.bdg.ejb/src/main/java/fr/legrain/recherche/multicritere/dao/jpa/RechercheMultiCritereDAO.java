package fr.legrain.recherche.multicritere.dao.jpa;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;

import fr.legrain.article.model.TaArticle;

import fr.legrain.recherche.multicritere.dao.IRechercheMultiCritereDAO;
import fr.legrain.recherche.multicritere.model.GroupeLigne;
import fr.legrain.recherche.multicritere.model.LigneArticle;
import fr.legrain.recherche.multicritere.model.LigneCritere;
import fr.legrain.recherche.multicritere.model.LigneDocument;
import fr.legrain.recherche.multicritere.model.LigneTiers;


public class RechercheMultiCritereDAO implements IRechercheMultiCritereDAO, Serializable {

	private static final long serialVersionUID = -4208864100983087548L;

	//private static final Log logger = LogFactory.getLog(TaArticleDAO.class);
	static Logger logger = Logger.getLogger(RechercheMultiCritereDAO.class);
	
	@PersistenceContext(unitName = "bdg")
	private EntityManager entityManager;

	private String defaultJPQLQuery = "select a from TaArticle a order by codeArticle";
	
	private String defaultLightJPQLQueryEcran = TaArticle.QN.FIND_BY_ECRAN_LIGHT;
	
	public RechercheMultiCritereDAO(){
//		this(null);
	}
	
	/*
	 * -------------------------------------------------------------------------------------------------------------------------------
	 */
	
	private Map<String,String> corresDocTable = new LinkedHashMap<String, String>();
	private Map<String,String> corresDocPrefixe = new LinkedHashMap<String, String>();
	private List<GroupeLigne> groupe = new ArrayList<>();
	private List<LigneArticle> lignesArt = new ArrayList<>();
	private List<LigneDocument> lignesDoc = new ArrayList<>();
	private List<LigneTiers> lignesTiers = new ArrayList<>();
	private List<ArrayList<String>> listeDifferentsDocs = new ArrayList<ArrayList<String>>();
	private List<List<Object>> listeResultats = new ArrayList<List<Object>>();
	private List<Object> listeFinale = new ArrayList<Object>();
	private boolean isSimplifie = false;    // Boolean permettant d'??valuer si la requ??te ?? ??t?? simplifi??e ou non
	private String resultat = null;
	private String[] selects = null;
	private String[] froms = null;
	private String[] joins = null;
	private String[] wheres = null;
	private String[] havings = null;
	private String[] groupbys = null;
	private String[] rqtGroupes = null;
	private boolean whereIsDeclared = false;


//	/** 
//	 * Constructeur de la requ??te
//	 * @param resultat
//	 * @param groupe
//	 */
//	public Requete(String resultat,List<GroupeLigne> groupe){
//		// Le r??sultat doit ??tre valide
//		// Le groupe doit contenir au moins une ligne
//		if (resultat != null && groupe.size() != 0) {
//			this.groupe = groupe; 		// Affectation du groupe
//			this.resultat = resultat; 	// Affectation du r??sultat
//			selects = new String[groupe.size()]; // Initialisation des Selects
//			froms = new String[groupe.size()];	 // Initialisation des Froms
//			joins = new String[groupe.size()]; 	 // Initialisation des Joins
//			wheres = new String[groupe.size()];	 // Initialisation des Wheres
//			havings = new String[groupe.size()];	 // Initialisation des Having
//			groupbys = new String[groupe.size()];	 // Initialisation des GB
//			rqtGroupes = new String[groupe.size()]; // Initialisation requ??tes
//			initMaps(); 	  // Initialisation des Maps
//			typeResultat();   // Initialisation du SFW ( Select From Where )
//			analyseGroupes(); // D??marrage de l'analyse de la requ??te
//			initRequete();	  // Lance la requ??te
//			traiteRequete();  // Traite la requ??te par groupes
//
//		} 
//	}

	/**
	 * M??thode d'initialisation de la requ??te
	 * Permet d'??tablir le SFW de base
	 */
	private void initRequete(){
		//listeDifferentsDocs = new ArrayList<ArrayList<String>>();
		
		listeResultats = new ArrayList<List<Object>>();
		for (int i =0 ; i < groupe.size(); i++){
			// On recompose la requ??te
			List<Object> listeresult = new ArrayList<Object>();
			rqtGroupes[i] 	= "";
			rqtGroupes[i]	+= selects[i];
			rqtGroupes[i]  	+= froms[i];
			rqtGroupes[i]  	+= joins[i];
			rqtGroupes[i]  	+= wheres[i];
			rqtGroupes[i]   += groupbys[i];
			rqtGroupes[i] 	+= havings[i];
			System.err.println(rqtGroupes[i]);
			Query requetePrincipale = entityManager.createQuery(rqtGroupes[i]);
			listeresult =requetePrincipale.getResultList();
			int nbresult = listeresult.size();
			boolean tropComplexe = false ; 	// Boolean permettant de mettre fin ?? la requ??te
											// en cas de complexit?? trop ??lev??e
			boolean isComplexe = false;     // Boolean permettant d'??valuer la complexit?? de la requ??te
			System.err.println(nbresult);	
			// Cas d'une requ??te complexe
			// R??sultat s??lectionn?? : Tiers | Crit??res chiffre d'affaires : multiples
			if (!havings[i].equals(" ") && resultat.equals("Tiers") && nbresult !=0 && listeDifferentsDocs.get(i).size()!=1){
				for(int tabres = 0; tabres < listeresult.size();tabres++){ // On parcourt les diff??rents tableaux de r??sultat
					for(int res=0; res < ((Object[])listeresult.get(0)).length;res++){ // On parcourt les diff??rents r??sultat
						if (tabres == 0 && res ==0){ // Premier r??sultat, construction de la requ??te
							rqtGroupes[i] = "SELECT DISTINCT t FROM TaTiers t WHERE t.codeTiers ='"+(String)((Object[])listeresult.get(tabres))[res]+"'";
						} else { // Ajout du crit??re ?? la requ??te
							rqtGroupes[i]+=" OR t.codeTiers ='"+(String)((Object[])listeresult.get(tabres))[res]+"'";
						}

					}
				}
				isSimplifie = true ; // Requ??te simplifi??e ( un seul objet dans le select )
				isComplexe = true; // Bool??en de confirmation de complexit??
				// R??sultat s??lectionn?? : Tiers | Crit??res chiffre d'affaires : simple
			} else if (!havings[i].equals(" ") && resultat.equals("Tiers") && nbresult !=0){
				for(int res=0; res < listeresult.size();res++){
					if (res ==0){
						rqtGroupes[i] = "SELECT DISTINCT t FROM TaTiers t WHERE t.codeTiers ='"+listeresult.get(res)+"'";
					} else {
						rqtGroupes[i]+=" OR t.codeTiers ='"+listeresult.get(res)+"'";
					}

				}
				isSimplifie = true ; // Requ??te simplifi??e ( un seul objet dans le select )
				isComplexe = true;
				// R??sultat s??lectionn?? : Articles | Crit??res chiffre d'affaires : multiples
			} else if (!havings[i].equals(" ") && resultat.equals("Articles") && nbresult !=0 && listeDifferentsDocs.get(i).size() !=1) {
				for(int tabres = 0; tabres < listeresult.size();tabres++){
					for(int res=0; res< ((Object[])listeresult.get(0)).length;res++){
						if (tabres == 0 && res ==0){
							rqtGroupes[i] = "SELECT DISTINCT a FROM TaArticle a WHERE a.codeArticle ='"+(String)((Object[])listeresult.get(tabres))[res]+"'";
						} else {
							rqtGroupes[i]+=" OR a.codeArticle ='"+(String)((Object[])listeresult.get(tabres))[res]+"'";
						}

					}
				}
				isSimplifie = true ; // Requ??te simplifi??e ( un seul objet dans le select )
				isComplexe = true;
				// R??sultat s??lectionn?? : Articles | Crit??res chiffre d'affaires : simples
			} else if (!havings[i].equals(" ") && resultat.equals("Articles") && nbresult !=0) {
				for(int res=0; res< listeresult.size();res++){
					if (res ==0){
						rqtGroupes[i] = "SELECT DISTINCT a FROM TaArticle a WHERE a.codeArticle ='"+listeresult.get(res)+"'";
					} else {
						rqtGroupes[i]+=" OR a.codeArticle ='"+listeresult.get(res)+"'";
					}

				}
				isSimplifie = true ; // Requ??te simplifi??e ( un seul objet dans le select )
				isComplexe = true;
				// R??sultat s??lectionn?? : Documents | Crit??res chiffre d'affaires : simples
			} else if (!havings[i].equals(" ") && resultat.equals("Documents")&& nbresult>0 && listeDifferentsDocs.get(i).size()==1) {
				rqtGroupes[i] = " SELECT DISTINCT  ";
				rqtGroupes[i]+= corresDocPrefixe.get(listeDifferentsDocs.get(i).get(0));
				for(int doc=1;doc<listeDifferentsDocs.get(i).size();doc++){
					rqtGroupes[i]+= ", "+corresDocPrefixe.get(listeDifferentsDocs.get(i).get(doc));
				}
				rqtGroupes[i] += " FROM ";
				rqtGroupes[i]+= "Ta"+corresDocTable.get(listeDifferentsDocs.get(i).get(0));
				rqtGroupes[i]+= " "+corresDocPrefixe.get(listeDifferentsDocs.get(i).get(0));
				for(int doc=1;doc<listeDifferentsDocs.get(i).size();doc++){
					rqtGroupes[i]+= ", Ta"+corresDocTable.get(listeDifferentsDocs.get(i).get(doc));
					rqtGroupes[i]+= " "+corresDocPrefixe.get(listeDifferentsDocs.get(i).get(doc));
				}

				for(int res=0; res< listeresult.size();res++){
					if (res ==0){
						rqtGroupes[i] += " WHERE "+corresDocPrefixe.get(listeDifferentsDocs.get(i).get(0))+".codeDocument ='"+listeresult.get(res)+"'";
						for(int doc=1;doc<listeDifferentsDocs.get(i).size();doc++){
							rqtGroupes[i]+= "OR "+corresDocPrefixe.get(listeDifferentsDocs.get(i).get(doc))+".codeDocument ='"+listeresult.get(res)+"'";;
						}
					} else {
						for(int doc=0;doc<listeDifferentsDocs.get(i).size();doc++){
							rqtGroupes[i]+= "OR "+corresDocPrefixe.get(listeDifferentsDocs.get(i).get(doc))+".codeDocument ='"+listeresult.get(res)+"'";;
						}
					}

				}
				isComplexe = true;


				// R??sultat s??lectionn?? : Documents | Crit??res chiffre d'affaires : trop complexes
			} else if (!havings[i].equals(" ") && resultat.equals("Documents")&& nbresult>0 && listeDifferentsDocs.get(i).size()!=1) {
				int groupe = i+1;
				String ttl = "Complexit?? de la recherche trop ??lev??e";
				String msg = "Le niveau de complexit?? de la recherche est trop ??lev??.\n"
					+"Une recherche portant sur des documents (Etape 1) et incluant un crit??re sur chiffre d'affaire (Groupe "+
					+groupe+") ne peut porter que sur un seul document.";

				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ttl, msg));
				tropComplexe = true;
				isComplexe = true;
			}

			if (nbresult != 0 && !tropComplexe && isComplexe){
				System.err.println(rqtGroupes[i]);
				Query query = entityManager.createQuery(rqtGroupes[i]);
				listeresult = query.getResultList();
				System.err.println(listeresult.size());
			}

			// Simplification des ??l??ments de la liste : Elimination des doublons, rassemblement des listes
			if (listeDifferentsDocs.get(i).size()!=1 && !isSimplifie ){
				List<Object> listeresultcopy = new ArrayList<Object>();
				for (int k=0; k<listeresult.size();k++){

					for (int j=0; j < ((Object[])listeresult.get(k)).length;j++){
						if (!listeresultcopy.contains(((Object[])listeresult.get(k))[j])){
							listeresultcopy.add(((Object[])listeresult.get(k))[j]);
						}
					}

				}	
				listeresult = listeresultcopy;
			}


			if (!tropComplexe){
				listeResultats.add(listeresult);
			}

		}
	}


	/**
	 * G??n??re un select et un from de base en fonction du type de resultat
	 */
	private void typeResultat() {
		if (resultat.equals("Tiers")){ 			// R??sultat contenant Tiers
			for (int i = 0; i< groupe.size();i++){
				listeDifferentsDocs.add(new ArrayList<String>());
				selects[i] = "SELECT t";
				froms[i] = " ";
				joins[i] = " ";
				wheres[i] = " ";
				havings[i] = " ";
				groupbys[i] = " ";
			}
		} else if (resultat.equals("Articles")){ // R??sultat contenant Articles
			for (int i = 0; i< groupe.size();i++){
				listeDifferentsDocs.add(new ArrayList<String>());
				selects[i] = "SELECT a";
				froms[i] = " ";
				joins[i] = " ";
				wheres[i] = " ";
				havings[i] = " ";
				groupbys[i] = " ";
			}
		} else { // Document
			for (int i = 0; i< groupe.size();i++){ // R??sultat contenant Documents
				listeDifferentsDocs.add(new ArrayList<String>());
				selects[i] = "SELECT ";
				froms[i] = " ";
				joins[i] = " ";
				wheres[i] = " ";
				havings[i] = " ";
				groupbys[i] = " ";
			}

		}
	}

	/**
	 * G??n??re un select de base en fonction du type de resultat
	 * @param typeRes 	t pour tiers
	 * 					a pour articles
	 * 					"" pour documents
	 */
	private void genereSelect(String typeRes, String suffixe,int legroupe) {
		if(listeDifferentsDocs.get(legroupe).size()!=0){ // Pr??caution : crit??re document existant
			selects[legroupe] = "SELECT DISTINCT "+typeRes+corresDocPrefixe.get(listeDifferentsDocs.get(legroupe).get(0))+suffixe;
			for(int j=1;j<listeDifferentsDocs.get(legroupe).size();j++){
				String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(legroupe).get(j));
				selects[legroupe] += ", "+typeRes+prefixe+suffixe;
			}
		}
	}

	/**
	 * G??n??re un group by de base en fonction du type de resultat
	 * @param typeRes 	t pour tiers
	 * 					a pour articles
	 * 					"" pour documents
	 */
	private void genereGB(String typeRes, String suffixe, int legroupe) {
		if(listeDifferentsDocs.get(legroupe).size()!=0){ // Pr??caution : crit??re document existant
			groupbys[legroupe] = " GROUP BY "+typeRes+corresDocPrefixe.get(listeDifferentsDocs.get(legroupe).get(0))+suffixe;
			for(int j=1;j<listeDifferentsDocs.get(legroupe).size();j++){
				String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(legroupe).get(j));
				groupbys[legroupe] += ", "+typeRes+prefixe+suffixe;
			}
		}


	}


//	/**
//	 * M??thode renvoyant une liste d'object
//	 * contenant le r??sultat de la requ??te
//	 * @return la liste d'object contenant le r??sultat
//	 */
//	public ArrayList<Object> getResultat() {
//		return (ArrayList<Object>) listeFinale;
//	}
	
	/**
	 * M??thode renvoyant une liste d'object
	 * contenant le r??sultat de la requ??te
	 * @return la liste d'object contenant le r??sultat
	 */
	public ArrayList<Object> getResultat(String resultat,List<GroupeLigne> groupe) {
		// Le r??sultat doit ??tre valide
		// Le groupe doit contenir au moins une ligne
		listeDifferentsDocs = new ArrayList<ArrayList<String>>();
		if (resultat != null && groupe.size() != 0) {
			this.groupe = groupe; 		// Affectation du groupe
			this.resultat = resultat; 	// Affectation du r??sultat
			selects = new String[groupe.size()]; // Initialisation des Selects
			froms = new String[groupe.size()];	 // Initialisation des Froms
			joins = new String[groupe.size()]; 	 // Initialisation des Joins
			wheres = new String[groupe.size()];	 // Initialisation des Wheres
			havings = new String[groupe.size()];	 // Initialisation des Having
			groupbys = new String[groupe.size()];	 // Initialisation des GB
			rqtGroupes = new String[groupe.size()]; // Initialisation requ??tes
			initMaps(); 	  // Initialisation des Maps
			typeResultat();   // Initialisation du SFW ( Select From Where )
			analyseGroupes(); // D??marrage de l'analyse de la requ??te
			initRequete();	  // Lance la requ??te
			traiteRequete();  // Traite la requ??te par groupes
			return (ArrayList<Object>) listeFinale;
		} 
		return null;
	}


	/**
	 * M??thode permettant d'appliquer le format avec % pour les Like
	 * @param comparateur le comparateur pour le type de format ?? appliquer
	 * @param valeur la valeur sur laquelle on doit appliquer le format
	 * @return la valeur format??e
	 */
	private String appliTransfo(String comparateur, String valeur) {
		if (comparateur.equals("contient") || comparateur.equals("ne contient pas")){
			valeur = "%"+valeur+"%";
		} else if (comparateur.equals("commence par") || comparateur.equals("ne commence pas par")){
			valeur = valeur+"%";
		} else if (comparateur.equals("fini par") || comparateur.equals("ne fini pas par") ){
			valeur = "%"+valeur;
		} else if (valeur.equals(LigneCritere.BOOLEAN_TRUE_TEXT)) {
			valeur = LigneCritere.BOOLEAN_TRUE_VALUE;
		} else if (valeur.equals(LigneCritere.BOOLEAN_FALSE_TEXT)) {
			valeur = LigneCritere.BOOLEAN_FALSE_VALUE;
		}
		return valeur;

	}


	/*** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * M??thode permettant l'analyse des groupes afin d'??tablir la requ??te  *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  
	 * * * * Premi??re ??tape : Phase de tri * * * * * * * * * * * * * * * * *
	 * Lors de cette ??tape, les lignes sont tri??es par type pour faciliter *
	 * le d??coupage de la requ??te. Avant leur tri, leurs informations      *
	 * sont r??cup??r??es afin de les ajout??es ?? la clause WHERE de la requ??te*
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * Deuxi??me ??tape : Froms et Jointures * * * * * * * * * * * * * *
	 * Durant cette seconde ??tape, en fonction des crit??res rentr??s, la    *
	 * m??thode va proc??der ?? la construction du from et des joins de la    *
	 * requ??te.                                                            *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	private void analyseGroupes() {
		// On parcourt les groupes
		for (int igroupe =0; igroupe<groupe.size(); igroupe++){
			whereIsDeclared=false;
			// * ----------- PHASE DE TRI ----------- * //
			// Tri les lignes du groupe
			// On parcourt les lignes
			// * ----------- PHASE DE TRI ----------- * //
			// Tri les lignes du groupe
			// On parcourt les lignes
			for (int iligne = 0; iligne<groupe.get(igroupe).getGroupe().size();
			iligne++){
				// On r??cup??re la ligne trait??e
				// Et on l'ajoute dans la liste qui convient
				LigneCritere laligne =  groupe.get(igroupe).getGroupe().get(iligne);		
				if (laligne instanceof LigneTiers){
					lignesTiers.add((LigneTiers) laligne);
				} else if (laligne instanceof LigneArticle) { 
					lignesArt.add((LigneArticle) laligne);
				} else {
					lignesDoc.add((LigneDocument) laligne);
					ajouteSiNonPresent((LigneDocument) laligne, igroupe);
				}
			}
			// On v??rifie s'il y a des incoh??rence dans la requ??te
			// ex : requ??te sur tiers avec articles en argument
			verifIncoherences();

			if(resultat.equals("Documents")){
				genereSelect("","",igroupe);
			} else if (resultat.equals("Tiers") && lignesDoc.size()!=0){
				genereSelect("t","",igroupe);
			} else if (resultat.equals("Articles") && lignesDoc.size()!=0){
				genereSelect("a","",igroupe);
			}

			// On refait le tour des lignes pour traiter les selects / where
			for (int iligne = 0; iligne<groupe.get(igroupe).getGroupe().size(); iligne++){
				// On r??cup??re la ligne trait??e
				LigneCritere laligne =  groupe.get(igroupe).getGroupe().get(iligne);		
				if (laligne instanceof LigneTiers){
					// La ligne est une ligne de tiers
					if (listeDifferentsDocs.get(igroupe).size() == 0){
						ajoutWhereTiers(laligne,"", iligne, igroupe);
					}
					for(int i = 0; i<listeDifferentsDocs.get(igroupe).size();i++){
						String prefix = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
						ajoutWhereTiers(laligne,prefix, iligne, igroupe);
					}

				} else if (laligne instanceof LigneArticle) { 
					// La ligne est une ligne d'articles 
					if (listeDifferentsDocs.get(igroupe).size() == 0){
						ajoutWhereArticle(laligne,"", iligne, igroupe);
					}
					for(int i = 0; i<listeDifferentsDocs.get(igroupe).size();i++){
						String prefix = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
						ajoutWhereArticle(laligne,prefix, iligne, igroupe);
					}
				} else {
					// La ligne est une ligne de document
					ajoutWhereDocument(laligne, laligne.getType(),
							iligne, igroupe);
				}
			}

			// * ----------- PHASE DE PRE-CONSTRUCTION ----------- * //
			// Cr??ation du SFW
			// -- CAS TIERS + DOC + ART --
			if (lignesTiers.size() != 0 && lignesArt.size() != 0 
					&& lignesDoc.size() != 0 ){ 
				// Construction FROMS
				// On ajoute les tables utiles pour les lignes et les docs
				froms[igroupe] += " FROM TaL"
					+corresDocTable.get(listeDifferentsDocs.get(igroupe).get(0))
					+" l"+corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(0));
				for(int i=1;i<listeDifferentsDocs.get(igroupe).size();i++){
					String table = corresDocTable.get(listeDifferentsDocs.get(igroupe).get(i));
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					froms[igroupe] += ", TaL"+table+" l"+prefixe;
				}
				// Construction JOINS
				for(int i=0;i<listeDifferentsDocs.get(igroupe).size();i++){
					String table = corresDocTable.get(listeDifferentsDocs.get(igroupe).get(i));
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					joins[igroupe] += " LEFT JOIN "
						+" l"+prefixe+".taDocument "+prefixe
						+" LEFT JOIN "
						+" l"+prefixe+".taArticle a"+prefixe
						+" LEFT JOIN "
						+" "+prefixe+".taTiers t"+prefixe;
				}
				
				// Cas requ??te simplifi??e
				if (!resultat.equals("Documents")){
					isSimplifie = true;
				}

				// -- CAS TIERS + DOC --
			} else if (lignesTiers.size() != 0
					&& lignesDoc.size() != 0 
					&& lignesArt.size() == 0){ 
				// Construction FROMS
				froms[igroupe] += " FROM Ta"
					+corresDocTable.get(listeDifferentsDocs.get(igroupe).get(0))+" "
					+corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(0));
				// On ajoute les tables utiles pour les lignes et les docs
				for(int i=1;i<listeDifferentsDocs.get(igroupe).size();i++){
					String table = corresDocTable.get(listeDifferentsDocs.get(igroupe).get(i));
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					froms[igroupe] += ", Ta"+table+" "+prefixe;
				}
				// Construction JOINS
				for(int i=0;i<listeDifferentsDocs.get(igroupe).size();i++){
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					joins[igroupe] += " LEFT JOIN "
						+" "+prefixe+".taTiers t"+prefixe;
				}
				

				// -- CAS ARTICLES + DOC --
			} else if (lignesArt.size() != 0
					&& lignesDoc.size() != 0 
					&& lignesTiers.size() == 0){ 
				// Construction FROMS
				froms[igroupe] += " FROM TaL"+corresDocTable.get(listeDifferentsDocs.get(igroupe).get(0))
				+" l"+corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(0));
				// On ajoute les tables utiles pour les lignes et les docs
				for(int i=1;i<listeDifferentsDocs.get(igroupe).size();i++){
					String table = corresDocTable.get(listeDifferentsDocs.get(igroupe).get(i));
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					froms[igroupe] += " , TaL"+table+" l"+prefixe;
				}
				// Construction JOINS
				for(int i=0;i<listeDifferentsDocs.get(igroupe).size();i++){
					String table = corresDocTable.get(listeDifferentsDocs.get(igroupe).get(i));
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					joins[igroupe] += " LEFT JOIN "
						+" l"+prefixe+".taDocument "+prefixe
						+" LEFT JOIN "
						+" l"+prefixe+".taArticle a"+prefixe;
				}

				// -- CAS TIERS --
			} else if (lignesTiers.size() != 0
					&& lignesDoc.size() == 0 
					&& lignesArt.size() == 0){ 
				// Construction FROMS
				froms[igroupe] += " FROM TaTiers t";
				isSimplifie = true;
				// -- CAS ARTICLES --
			} else if (lignesTiers.size() == 0
					&& lignesDoc.size() == 0 
					&& lignesArt.size() != 0){ 
				// Construction FROMS
				froms[igroupe] += " FROM TaArticle a";
				isSimplifie = true;
				// -- CAS DOCUMENTS --
			} 	else if (lignesTiers.size() == 0
					&& lignesDoc.size() != 0 
					&& lignesArt.size() == 0){ 
				// Construction FROMS
				froms[igroupe] += " FROM  Ta"
					+corresDocTable.get(listeDifferentsDocs.get(igroupe).get(0))
					+" "+corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(0));
				for(int i=1;i<listeDifferentsDocs.get(igroupe).size();i++){
					String table = corresDocTable.get(listeDifferentsDocs.get(igroupe).get(i));
					String prefixe = corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(i));
					froms[igroupe] += ", Ta"+table+" "+prefixe;
				}
			}

			if(resultat.equals("Documents")){

			} else if (resultat.equals("Tiers")){
				for(int typedoc = 1; typedoc < listeDifferentsDocs.get(igroupe).size() ; typedoc++){
					wheres[igroupe] += " AND t"+ corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(0))+" = t"
					+ corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(typedoc));
				}
			} else if (resultat.equals("Articles")){
				for(int typedoc = 1; typedoc < listeDifferentsDocs.get(igroupe).size() ; typedoc++){
					wheres[igroupe] += " AND a"+ corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(0))+" = a"
					+ corresDocPrefixe.get(listeDifferentsDocs.get(igroupe).get(typedoc));
				}
			}

		}
	}

	/**
	 * M??thode permettant l'ajout du type de document
	 * A la liste des types de documents diff??rents
	 * @param laligne la ligne contenant le type qui sera ajout??
	 *  	  Si celui ci n'est pas d??j?? pr??sent
	 */
	private void ajouteSiNonPresent(LigneDocument laligne, int legroupe){
		boolean ajoutOK = true;
		for(int i=0;i<listeDifferentsDocs.get(legroupe).size() && ajoutOK;i++){
			// On parcourt les diff??rents types de documents
			ajoutOK = laligne.getType() !=  listeDifferentsDocs.get(legroupe).get(i);
		}
		if (ajoutOK){ // La ligne n'est pas d??j?? pr??sente dans la liste
			listeDifferentsDocs.get(legroupe).add(laligne.getType());
		}
	}

	/**
	 * M??thode permettant l'initialisation des maps pour les diff??rents docs
	 */
	private void initMaps() {
		corresDocTable.put("Facture","Facture");
		corresDocTable.put("Devis","Devis");
		corresDocTable.put("Commande","Boncde");
		corresDocTable.put("Avoir","Avoir");		
		corresDocTable.put("Apporteur","Apporteur");
		corresDocTable.put("Acompte","Acompte");
		corresDocTable.put("Proforma","Proforma");
		corresDocTable.put("Livraison","Bonliv");

		corresDocPrefixe.put("Facture","fa");
		corresDocPrefixe.put("Devis","de");
		corresDocPrefixe.put("Commande","co");
		corresDocPrefixe.put("Avoir","av");
		corresDocPrefixe.put("Apporteur","ap");
		corresDocPrefixe.put("Acompte","ac");
		corresDocPrefixe.put("Proforma","pr");
		corresDocPrefixe.put("Livraison","li");
	}


	/**
	 * M??thode permettant l'analyse d'une ligne tiers dans le where
	 * @param laligne la ligne ?? analyser
	 * @param iligne l'indice de la ligne dans le groupe
	 * @param igroupe l'indice du groupe
	 */
	private void ajoutWhereTiers(LigneCritere laligne,String prefixe,int iligne,int igroupe) {
		String critere = laligne.getSelectedCritere();	
		String comparateur = laligne.getSelectedComparaison();
		String andOr = laligne.getSelectedAndOr();
		//String valeur1 = laligne.getValeur1();
		String valeur1 = laligne.isValeur1Visible() ? laligne.getValeur1() : laligne.getSelectedBooleen();
		valeur1 = appliTransfo(comparateur,valeur1);
		String valeur2 = laligne.getValeur2();

		if (!whereIsDeclared){
			wheres[igroupe] = " WHERE t"+prefixe+"."+((LigneTiers) laligne).transformSQLTiers(critere)+
			" "+((LigneTiers) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
			whereIsDeclared = true;
		} else if (iligne==0 && whereIsDeclared){
			wheres[igroupe] += " AND t"+prefixe+"."+((LigneTiers) laligne).transformSQLTiers(critere)+
			" "+((LigneTiers) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
		} else {
			wheres[igroupe] += " "+laligne.transformSQLandOr(andOr)+
			" t"+prefixe+"."+((LigneTiers) laligne).transformSQLTiers(critere)+
			" "+((LigneTiers) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
		}
	}

	/**
	 * M??thode permettant l'analyse d'une ligne article dans le where
	 * @param laligne la ligne ?? analyser
	 * @param iligne l'indice de la ligne dans le groupe
	 * @param igroupe l'indice du groupe
	 */
	private void ajoutWhereArticle(LigneCritere laligne,String prefixe,int iligne,int igroupe) {
		String critere = laligne.getSelectedCritere();	
		String comparateur = laligne.getSelectedComparaison();
		String andOr = laligne.getSelectedAndOr();
		String valeur1 = laligne.getValeur1();
		valeur1 = appliTransfo(comparateur,valeur1);
		String valeur2 = laligne.getValeur2();

		if (!whereIsDeclared){
			wheres[igroupe] = " WHERE a"+prefixe+"."+((LigneArticle) laligne).transformSQLArticle(critere)+
			" "+((LigneArticle) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
			whereIsDeclared = true;
		} else if (iligne==0 && whereIsDeclared){
			wheres[igroupe] += " AND a"+prefixe+"."+((LigneArticle) laligne).transformSQLArticle(critere)+
			" "+((LigneArticle) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
			whereIsDeclared = true;
		}else {
			wheres[igroupe] += " "+laligne.transformSQLandOr(andOr)+
			" a"+prefixe+"."+((LigneArticle) laligne).transformSQLArticle(critere)+
			" "+((LigneArticle) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
		}
	}

	/**
	 * M??thode permettant l'analyse d'une ligne document dans le where
	 * @param laligne la ligne ?? analyser
	 * @param iligne l'indice de la ligne dans le groupe
	 * @param igroupe l'indice du groupe
	 */
	private void ajoutWhereDocument(LigneCritere laligne,String type, int iligne,int igroupe) {
		String critere = laligne.getSelectedCritere();	
		String comparateur = laligne.getSelectedComparaison();
		String andOr = laligne.getSelectedAndOr();
		String valeur1 = laligne.getValeur1();
		valeur1 = appliTransfo(comparateur,valeur1);
		String valeur2 = laligne.getValeur2();
		String prefixe = corresDocPrefixe.get(type);
		boolean complexe = false;

		if(critere.equals("Date Document") || critere.equals("Date Ech??ance")
				|| critere.equals("Date Livraison")){
			String[] temp = new String[3];
			temp = valeur1.split("/", 3);
			valeur1 = temp[2]+"-"+temp[1]+"-"+temp[0];
			if (comparateur.equals("est compris entre")){
				temp = valeur2.split("/", 3);
				valeur2 = temp[2]+"-"+temp[1]+"-"+temp[0];
			}
		}


		if (!whereIsDeclared && !critere.equals("Chiffre d'Affaires")){
			wheres[igroupe] = " WHERE "+((LigneDocument) laligne).transformSQLDoc(critere)+
			" "+((LigneDocument) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
			whereIsDeclared = true;
		} else if (!critere.equals("Chiffre d'Affaires")) {
			wheres[igroupe] += " "+laligne.transformSQLandOr(andOr)+
			" "+((LigneDocument) laligne).transformSQLDoc(critere)+
			" "+((LigneDocument) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			wheres[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
		} else if (havings[igroupe].equals(" ")){
			complexe=true;
			havings[igroupe] = " HAVING "+((LigneDocument) laligne).transformSQLDoc(critere)+
			" "+((LigneDocument) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			havings[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
		} else {
			complexe=true;
			havings[igroupe] += " "+laligne.transformSQLandOr(andOr)+
			" "+((LigneDocument) laligne).transformSQLDoc(critere)+
			" "+((LigneDocument) laligne).transformSQLcomparateur(comparateur)+
			" '"+valeur1+"'";
			havings[igroupe]+= laligne.isValeur2Visible() ? " AND '"+valeur2+"'" : "";
		}

		// On requiert un Group By Having, requ??te complexe,
		// Modification des ??l??ments correspondants
		if(complexe){
			if(resultat.equals("Documents")){
				genereSelect("",".codeDocument",igroupe);
				genereGB("",".codeDocument",igroupe);
			} else if (resultat.equals("Tiers") && lignesDoc.size()!=0){
				genereSelect("t",".codeTiers",igroupe);
				genereGB("t",".codeTiers",igroupe);
			} else if (resultat.equals("Articles") && lignesDoc.size()!=0){
				genereSelect("a",".codeArticle",igroupe);
				genereGB("a",".codeArticle",igroupe);
			}
		}
	}

	/**
	 * M??thode de traite de la requ??te
	 * Effectue les Unions/Intersection sur les groupes
	 */
	private void traiteRequete() {
		List<Object> laListe = new ArrayList<Object>();		
		laListe = listeResultats.get(0);
		for(int i=1;i<listeResultats.size();i++){
			if (groupe.get(i).getSelectedAndOrGroup().equals("ET")){
				laListe = ListUtils.intersection(laListe, listeResultats.get(i));
			} else {
				laListe = ListUtils.sum(laListe, listeResultats.get(i));
			}
		}
		listeFinale = laListe;

	}

	/**
	 * M??thode permettant de v??rifier si des incoh??rences subsistent
	 * entre le r??sultat demand?? et les crit??res
	 */
	private void verifIncoherences() {
		String ttl = null; // titre de la fen??tre
		String msg = null ; // message de la fen??tre
		if (lignesTiers.size() != 0 && lignesArt.size() !=0
				&& lignesDoc.size() == 0 ) {
			ttl = "Incoh??rence d??tect??e";
			msg = "Les articles et les tiers sont li??s par les documents.\n"
				+"Pour rendre votre recherche valide, veuillez y int??grer un crit??re sur les documents.";
			
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ttl, msg));
		} else if (resultat.equals("Tiers") && lignesTiers.size() == 0) {
			ttl = "Incoh??rence d??tect??e";
			msg = "Afin d'??ffectuer une requ??te sur les tiers, veuillez renseigner au moins un crit??re sur ceux ci.\n";

			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ttl, msg));
		} else if (resultat.equals("Articles") && lignesArt.size() == 0) {
			ttl = "Incoh??rence d??tect??e";
			msg = "Afin d'??ffectuer une requ??te sur les articles, veuillez renseigner au moins un crit??re sur ceux ci.\n";

			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ttl, msg));
		} else if (resultat.equals("Documents") && lignesDoc.size() == 0) {
			ttl = "Incoh??rence d??tect??e";
			msg = "Afin d'??ffectuer une requ??te sur les documents, veuillez renseigner au moins un crit??re sur ceux ci.\n";

			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ttl, msg));
		}
	}


	


}
