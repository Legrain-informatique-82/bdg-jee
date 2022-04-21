package fr.legrain.liasseFiscale.actions;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TooManyListenersException;

import javax.swing.event.EventListenerList;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import com.borland.dx.dataset.ReadRow;
import com.borland.dx.dataset.RowFilterListener;
import com.borland.dx.dataset.RowFilterResponse;
import com.borland.dx.dataset.Variant;

import fr.legrain.gestCom.librairiesEcran.swt.LgrWorkEvent;
import fr.legrain.gestCom.librairiesEcran.swt.LgrWorkListener;
import fr.legrain.liasseFiscale.LiasseFiscalePlugin;
import fr.legrain.liasseFiscale.db.ConstLiasse;
import fr.legrain.liasseFiscale.db.dao.Totaux;
import fr.legrain.liasseFiscale.db.ibTables.IbTaTotaux;
import fr.legrain.liasseFiscale.preferences.PreferenceConstants;
import fr.legrain.lib.data.IBQuLgr;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.lib.data.LibChaine;

//Generated by StarUML(tm) Java Add-In

//@ Project : Untitled
//@ File Name : Repart.java
//@ Date : 29/09/2006
//@ Author : 

public class Repart {

	static Logger logger = Logger.getLogger(Repart.class.getName());

	private HashMap<Cle,Repartition> listeRepartition = new HashMap<Cle,Repartition>();
	private final String valDefaut = "00";      //valeur dans la table si le compte n'est pas réparti
	private ConfigParamRepart listeParamRepart;

	protected EventListenerList listenerList = new EventListenerList();

	private String C_MAPPING_FILE = "/mapping/mapping-repart.xml";
	private Mapping mapping = null;
//	private int arrondi = 0; //nb de fois ou une regle d'arrondi a ete applique

	public Repart() {}

	/**
	 * Filtre la table contenant la description des repartitions
	 */
	protected class FiltreRepartDebitCredit implements RowFilterListener {
		private IBQuLgr dataset = null;
		private String compte   = null; //compte de répartition
		private String origine  = null; //origine du compte : balance, immo, ...
		private String regime   = null; //régime pour lequel le compte est répartition
		private String typeTraitement = null; //type de traitement en cours : liasse fiscale, tva, ...
		private String sousTypeTraitement = null; //sous type de traitement en cours :
		private Integer annee = null;

		/**
		 * @param dataset - l'ensemble de données contenant la liste des repartitions
		 * @param compte - le compte pour lequel on recherche les répartitions
		 * @param regime - le régime pour lequel ce compte doit être réparti
		 * @param typeTraitement - le traitement pour lequel ce compte doit être réparti
		 * @param sousTypeTraitement - le sous traitement pour lequel ce compte doit être réparti
		 */
		public FiltreRepartDebitCredit(IBQuLgr dataset, String compte, String origine, String regime, String typeTraitement, String sousTypeTraitement, Integer annee) {
			this.dataset = dataset;
			this.compte  = compte;
			this.origine = origine;
			this.regime  = regime;
			this.typeTraitement = typeTraitement;
			this.sousTypeTraitement = sousTypeTraitement;
			this.annee = annee;
		}

		public void filterRow(ReadRow row, RowFilterResponse response) {
			if(dataset!=null) {
				boolean validRow = true;
				Variant v = new Variant();
				if(!dataset.getFIBQuery().isOpen()){
					dataset.getFIBQuery().open();
				}
				if(validRow && compte!=null) {
					row.getVariant(ConstLiasse.C_REPART_NUM_CPT, v);
					if(!v.getString().equals(compte))
						validRow = false;
				}
				if(validRow && origine!=null) {
					row.getVariant(ConstLiasse.C_REPART_ORIGINE, v);
					if(!v.getString().equals(origine))
						validRow = false;
				}
//				if(validRow && regime!=null) {
//				row.getVariant(ConstLiasse.C_REPART_REGIME, v);
//				if(!v.getString().equals(regime))
//				validRow = false;
//				}
//				if(validRow && typeTraitement!=null) {
//				row.getVariant(ConstLiasse.C_REPART_TYPE_TRAITEMENT, v);
//				if(!v.getString().equals(typeTraitement))
//				validRow = false;
//				}
//				if(validRow && sousTypeTraitement!=null) {
//				row.getVariant(ConstLiasse.C_REPART_SOUS_TYPE_TRAITEMENT, v);
//				if(!v.getString().equals(sousTypeTraitement))
//				validRow = false;
//				}
//				if(validRow && annee!=null) {
//				row.getVariant(ConstLiasse.C_REPART_ANNEE, v);
//				if(! (v.getInt()==annee.intValue()))
//				validRow = false;
//				}
				if(validRow)	
					response.add();
			}
		}

		@Override
		public boolean equals(Object obj) {
			if ((this == obj))
				return true;
			if ((obj == null))
				return false;
			if (!(obj instanceof FiltreRepartDebitCredit))
				return false;
			FiltreRepartDebitCredit castOther = (FiltreRepartDebitCredit) obj;

			return (this.compte == castOther.compte || (this.compte != null
					&& castOther.compte != null && this.compte.equals(castOther.compte)))
					&& (this.regime == castOther.regime || (this.regime != null
							&& castOther.regime != null && this.regime.equals(castOther.regime)))
							&& (this.typeTraitement == castOther.typeTraitement || (this.typeTraitement != null
									&& castOther.typeTraitement != null && this.typeTraitement.equals(castOther.typeTraitement)))
									&& (this.sousTypeTraitement == castOther.sousTypeTraitement || (this.sousTypeTraitement != null
											&& castOther.sousTypeTraitement != null && this.sousTypeTraitement.equals(castOther.sousTypeTraitement)))
											&& (this.annee == castOther.annee || (this.annee != null
													&& castOther.annee != null && this.annee.equals(castOther.annee)))
													&& (this.dataset == castOther.dataset);
		}

	}

	/**
	 * retourne le code de la cellule de répartition du PDF correspondant au compte cpt au débit
	 * dans la table Répartition
	 * @param cpt - numero de compte recherché
	 * @param regime - régime pour lequel le compte doit être réparti
	 * @param nomCol - nom de la colonne de recherchée dans l'ensemble de données contenant les paramétrage de répartition
	 * @param repartition - ensemble de données contenant les paramétrage de répartition
	 * @return - code de la cellule de répartition du PDF
	 */
	private String estRepartiDebit(String cpt, String origine, String regime, String typeTraitement, String sousTypeTraitement, Integer annee, String nomCol, IBQuLgr repartition) {
		String result = valDefaut;
		try {
			FiltreRepartDebitCredit filtre = new FiltreRepartDebitCredit(repartition,cpt,origine,regime,typeTraitement,sousTypeTraitement,annee);

			repartition.getFIBQuery().addRowFilterListener(filtre);
			repartition.getFIBQuery().refilter();

			//FiltrageDataSet(Repartition,CreeFiltreEt(['Compte','Regime'],[Cpt,Regime]));
			if (repartition.getFIBQuery().rowCount()>0 && !repartition.getFIBQuery().getString(nomCol).equals("")
					&& !repartition.getFIBQuery().getString(nomCol).equals(valDefaut)) {
				//cpt existe et est réparti
				result = repartition.getFIBQuery().getString(nomCol);
			}
			repartition.getFIBQuery().removeRowFilterListener(filtre);
			repartition.getFIBQuery().refilter();
			filtre = null;

			return result;
		} catch (TooManyListenersException e) {
			logger.error("",e);
			return result;
		}
	}

	/**
	 * retourne le code de la cellule de répartition du PDF correspondant au compte cpt au crédit
	 * dans la table Répartition
	 * @param cpt - numero de compte recherché
	 * @param regime - régime pour lequel le compte doit être réparti
	 * @param nomCol - nom de la colonne de recherchée dans l'ensemble de données contenant les paramétrage de répartition
	 * @param repartition - ensemble de données contenant les paramétrage de répartition
	 * @return - code de la cellule de répartition du PDF
	 * @see estRepartiDebit
	 */
	private String estRepartiCredit(String cpt, String origine, String regime, String typeTraitement, String sousTypeTraitement, Integer annee, String nomCol, IBQuLgr repartition) {
		return estRepartiDebit(cpt, origine, regime, typeTraitement, sousTypeTraitement, annee, nomCol, repartition);
	}

	/**
	 * retourne le premier père du compte cpt qui est réparti dans la table Répartition
	 * @param cpt - numero de compte recherché
	 * @param regime - régime pour lequel le compte doit être réparti
	 * @param nomCol - nom de la colonne de recherchée dans l'ensemble de données contenant les paramétrage de répartition
	 * @param repartition - ensemble de données contenant les paramétrage de répartition
	 * @return - 1er père du compte <code>cpt</code> qui est réparti
	 */
	private String pereRepart(String cpt, String origine, String regime, String typeTraitement, String sousTypeTraitement, Integer annee, String nomCol, IBQuLgr repartition) {
		int i = cpt.length();
		boolean res = false;
		while (res==false && i>1) { //on enleve le dernier chiffre
			i--;
			cpt = cpt.substring(0,i);
			if (!estRepartiDebit(cpt,origine,regime,typeTraitement,sousTypeTraitement,annee,nomCol,repartition).equals(valDefaut)) {
				res = true;
			}
		}
		return cpt;
	}

	/**
	 * Cette fonction à uniquement pour but de centraliser la gestion des arrondis dans la liasse
	 * tant que l'on ne sait pas quelle méthode sera retenue. Dès que la méthode sera connue il ne devrait y avoir
	 * que cette methode à modifier.
	 * @param valeur - la valeur à arrondir
	 * @param precision
	 * @return - la valeur arrondie.
	 */
	public double arrondiLiasse(double valeur, int precision) {
		if(LiasseFiscalePlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_ARRONDI)) {
			double retour = 0d;
			retour = LibCalcul.arrondi(valeur,precision);
			//retour = Double.valueOf(valeur).intValue();
			//System.err.println(valeur+" =======> "+retour);
			return retour;
		} else {
			return valeur;
		}
	}

	/**
	 * Arrondi tous les montant
	 */
	public void arrondiFinalLiasse() {
		if(LiasseFiscalePlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.P_ARRONDI)) {
			for (Cle c : listeRepartition.keySet()) {
				listeRepartition.get(c).setMontant(arrondiLiasse(listeRepartition.get(c).getMontant(),0));
				listeRepartition.get(c).setValeur(String.valueOf(arrondiLiasse(listeRepartition.get(c).getMontant(),0)));
//				listeRepartition.get(c).setValeur(String.valueOf(listeRepartition.get(c).getMontant()));
			}
			//arrondi++;
		}
	}

	/**
	 * Fonction a appeler une seule fois, après les calculs et juste avant les premiers arrondis
//	 */
//	private void initValeurSansArrondi() {
//		if(arrondi!=0) {
//			for (Cle c : listeRepartition.keySet()) {
//				listeRepartition.get(c).setSansArrondi(listeRepartition.get(c).getMontant());
//			}
//		}
//	}

	/**
	 * // public void  AjouteCellule(NomPBilan_Debit,NomPBilan_Credit,cpt_a_repartir,Regime:String;Debit,Credit:Currency;Repartition:TDataset);
	 * // Remplit Une cellule de ListeCell	
	 * @param nomPBilanDebit - nom de la colonne de recherchée dans l'ensemble de données contenant les paramétrage de répartition
	 * @param nomPBilanCredit - nom de la colonne de recherchée dans l'ensemble de données contenant les paramétrage de répartition
	 * @param cptARepartir - compte traité
	 * @param regime - régime pour lequel le compte doit être réparti
	 * @param debit - valeur de <code>cptARepartir</code> au débit
	 * @param credit - valeur de <code>cptARepartir</code> au credit
	 * @param repartition - ensemble de données contenant les paramétrage de répartition
	 */
	//private
	public void addRepartition(String nomPBilanDebit,String nomPBilanCredit, String cptARepartir, String origine, String regime, String typeTraitement, String sousTypeTraitement, Integer annee, Double debit, Double credit, IBQuLgr repartition) { 
		//listeRepartition
		final String PLUS = "+"; 
		Repartition infoRepartD, infoRepartC;
		Double montantD, montantC;
		String cpt,                   	//compte réellement utilisé dans la répartition
		cellule,                      	//Nom de la cellule dans le PDF
		celluleComplet,					//Nom de la cellule ainsi que son signe (+/-), ie : comme dans la table de la base de donnees
		signe ;

		if(debit==null)
			debit=0d;
		if(credit==null)
			credit=0d;

		//debit=arrondiLiasse(debit,0);
		//credit=arrondiLiasse(credit,0);

		celluleComplet = estRepartiDebit(cptARepartir,origine,regime,typeTraitement,sousTypeTraitement,annee,nomPBilanDebit,repartition);

		if (!LibChaine.empty(celluleComplet)) {
			montantD = new Double(0);
			montantC = new Double(0);
			infoRepartD = new Repartition();
			infoRepartC = new Repartition();

			if (!celluleComplet.equals(valDefaut) ) {
				//le compte est réparti directement
				cpt = cptARepartir;
			} else {
				//le compte n'est pas réparti, il faut prendre son père
				cpt = pereRepart(cptARepartir,origine,regime,typeTraitement,sousTypeTraitement,annee,nomPBilanDebit,repartition);
			}
			if(cpt.length()>1) { //si cpt=1,2,3,4,5,6,7 ou 8 le compte n'est pas réparti, donc on continue
				/////////////////--DEBIT--//////////////////////////////////////////////
				celluleComplet = estRepartiDebit(cpt,origine,regime,typeTraitement,sousTypeTraitement,annee,nomPBilanDebit,repartition);
				cellule = celluleComplet.substring(1,celluleComplet.length());
				if (!LibChaine.empty(cellule)) { //si cellule non vide
					signe = celluleComplet.substring(0,1);
					if (signe.equals(PLUS)) 
						montantD = debit;
					else
						montantD = -debit;

					Cle c = new Cle(cellule,sousTypeTraitement);
					if(!listeRepartition.keySet().contains(c)) {
						//nouvelle cellule
						infoRepartD.getDetail().add(new Compte(null,cptARepartir,null,debit,null));
						if (signe.equals(PLUS)) 
							infoRepartD.setMontant(debit);
						else
							infoRepartD.setMontant(-debit);

						if (montantD != 0) 
							listeRepartition.put(c,infoRepartD);

					} else {
						//modification d'un cellule deja presente
						if (signe.equals(PLUS)) 
							listeRepartition.get(c).setMontant(LibCalcul.arrondi(listeRepartition.get(c).getMontant()+debit));
						else
							listeRepartition.get(c).setMontant(LibCalcul.arrondi(listeRepartition.get(c).getMontant()-debit));

						if (montantD != 0) 
							listeRepartition.get(c).getDetail().add(new Compte(null,cptARepartir,null,debit,null));
					}
				}//fin si cellule non vide
				//////////////////--CREDIT--////////////////////////////////////////////
				celluleComplet = estRepartiCredit(cpt,origine,regime,typeTraitement,sousTypeTraitement,annee,nomPBilanCredit,repartition);
				cellule = celluleComplet.substring(1,celluleComplet.length());
				if (!LibChaine.empty(cellule)) { //si cellule non vide
					signe = celluleComplet.substring(0,1);
					if (signe.equals(PLUS)) 
						montantC = credit;
					else
						montantC = -credit;
					Cle c = new Cle(cellule,sousTypeTraitement);
					if(!listeRepartition.keySet().contains(c)) {
						//nouvelle cellule
						infoRepartC.getDetail().add(new Compte(null,cptARepartir,null,null,credit));
						if (signe.equals(PLUS)) 
							infoRepartC.setMontant(credit);
						else
							infoRepartC.setMontant(-credit);

						if (montantC != 0) 
							listeRepartition.put(c,infoRepartC);

					} else {
						//modification d'un cellule deja presente
						if (signe.equals(PLUS)) 
							listeRepartition.get(c).setMontant(LibCalcul.arrondi(listeRepartition.get(c).getMontant()+credit));
						else
							listeRepartition.get(c).setMontant(LibCalcul.arrondi(listeRepartition.get(c).getMontant()-credit));

						if (montantC != 0) 
							listeRepartition.get(c).getDetail().add(new Compte(null,cptARepartir,null,null,credit));
					}
				} //fin si cellule non vide
			}
		} // Fin Empty
	}

	/**
	 * Si <code>listeRepartition</code> n'existe pas, elle est creee
	 * sinon, elle est videe
	 * @return HashMap<String,Repartition> - listeRepartition
	 */
	public HashMap<Cle,Repartition> initListeRepartition() {
		if(listeRepartition == null) {
			listeRepartition = new HashMap<Cle,Repartition>();
		} else {
			listeRepartition.clear();
		}
		return listeRepartition;
	}

//	/**
//	* 
//	* @param repartition
//	* @param aRepartir
//	* @param regime
//	* @return HashMap<String,Repartition>
//	* cf : liste_cellule
//	*/
//	public HashMap<Cle,Repartition> repartitionXML(IBQuLgr repartition,InfosCompta aRepartir, String regime, String typeTraitement, String sousTypeTraitement, Integer annee) {
//	String cptARepartir,            // Compte traité
//	nomPBilanDebit,             	// Nom de la colonne débit traité dans PBilan
//	nomPBilanCredit;             	// Nom de la colonne crédit traité dans PBilan
//	Double debit;                 	// Débit dans la balance
//	Double credit;               	// Crédit dans la balance

//	initListeRepartition();

//	/////////////////////////////////////////////////////////////////////////
//	//TODO traitement des comptes en fonction de leurs origines et du type de traitement en cours
//	// Traitement de la balance
//	fireBeginWork(new LgrWorkEvent(this,aRepartir.getListeCompte().size()+aRepartir.getListeSaisieComplement().size()));
//	for (Compte c : aRepartir.getListeCompte()) {
//	cptARepartir = c.getNumero();
//	//if(c.getOrigine().equals("Bal")) {}

//	debit = c.getMtDebit();
//	credit = c.getMtCredit();

//	//Traitement des soldes Report Debut
//	nomPBilanDebit = ConstLiasse.C_REPART_MT_DEBIT;
//	nomPBilanCredit = ConstLiasse.C_REPART_MT_CREDIT;

//	addRepartition(nomPBilanDebit,nomPBilanCredit,cptARepartir,regime,typeTraitement,sousTypeTraitement,annee,debit,credit,repartition);
//	/////////////////////////////////////////////////////////////////////////
//	//Traitement des soldes Fin
//	nomPBilanDebit = ConstLiasse.C_REPART_MT_DEBIT_3;
//	nomPBilanCredit = ConstLiasse.C_REPART_MT_CREDIT_3;
//	debit = c.getMtDebit3();
//	credit = c.getMtCredit3();

//	addRepartition(nomPBilanDebit,nomPBilanCredit,cptARepartir,regime,typeTraitement,sousTypeTraitement,annee,debit,credit,repartition);

//	fireWork(null);

//	}
//	/////////////////////////////////////////////////////////////////////////

//	//TODO ajout des "compléments"
//	Repartition compl;
//	//for (InfoComplement complement : aRepartir.getListeSaisieComplement()) {
//	InfoComplement complement;
//	for (Cle cle : aRepartir.getListeSaisieComplement().keySet()) {
//	complement = aRepartir.getListeSaisieComplement().get(cle);
//	compl = new Repartition();
//	compl.setValeur(complement.getValeur1());
//	listeRepartition.put(new Cle(complement.getCle()),compl);
//	fireWork(null);
//	}

//	//TODO calcul totaux
//	calculTotaux(null,regime,typeTraitement,sousTypeTraitement);

//	fireEndWork(null);
//	return listeRepartition;
//	}

	public void addLgrWorkListener(LgrWorkListener l) {
		listenerList.add(LgrWorkListener.class, l);
	}

	public void removeLgrWorkListener(LgrWorkListener l) {
		listenerList.remove(LgrWorkListener.class, l);
	}

	protected void fireWork(LgrWorkEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == LgrWorkListener.class) {
				if (evt == null)
					evt = new LgrWorkEvent(this);
				( (LgrWorkListener) listeners[i + 1]).work(evt);
			}
		}
	}

	protected void fireBeginWork(LgrWorkEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == LgrWorkListener.class) {
				if (evt == null)
					evt = new LgrWorkEvent(this);
				( (LgrWorkListener) listeners[i + 1]).beginWork(evt);
			}
		}
	}

	protected void fireEndWork(LgrWorkEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == LgrWorkListener.class) {
				if (evt == null)
					evt = new LgrWorkEvent(this);
				( (LgrWorkListener) listeners[i + 1]).endWork(evt);
			}
		}
	}

	/**
	 * Création et initialisation des informations de mapping si celles ci n'existe pas
	 * @throws IOException
	 * @throws MappingException
	 */
	private void initMapping() throws IOException, MappingException {
		if ( mapping == null ) {
			//URL mappingURL = LiasseFiscalePlugin.getDefault().find(new Path(C_MAPPING_FILE));
			URL mappingURL = FileLocator.find(LiasseFiscalePlugin.getDefault().getBundle(),new Path(C_MAPPING_FILE),null);
			mapping = new Mapping();
			mapping.loadMapping(mappingURL);
		}
	}

	/**
	 * 
	 * @param xmlFile - fichier destination
	 */
	public void sortieXML(String xmlFile) {
		//binding de this avec Castor XML
		try {
			initMapping();
			//Create a File to marshal to
			FileWriter writer = new FileWriter(xmlFile);
			//Marshal the person object
			Marshaller m = new Marshaller(writer); 
			m.setMapping(mapping); 
			m.marshal(this);
			writer.close();
		} catch (IOException e) {
			logger.error("",e);
		} catch (MappingException e) {
			logger.error("",e);
		} catch (MarshalException e) {
			logger.error("",e);
		} catch (ValidationException e) {
			logger.error("",e);
		}
	}

	/**
	 * 
	 * @param xmlFile - fichier source
	 * @return InfosCompta
	 */
	public Repart lectureXML(String xmlFile) {
		//binding de this avec Castor XML
		try {
			initMapping();
			FileReader reader = new FileReader(xmlFile);
			//Create a new Unmarshaller
			Unmarshaller unmarshaller = new Unmarshaller(Repart.class);
			unmarshaller.setMapping(mapping);
			//Unmarshal the object
			Repart rep = (Repart)unmarshaller.unmarshal(reader);
			reader.close();
			return rep;
		} catch (IOException e) {
			logger.error("",e);
		} catch (MappingException e) {
			logger.error("",e);
		} catch (MarshalException e) {
			logger.error("",e);
		} catch (ValidationException e) {
			logger.error("",e);
		}
		return null;
	}

	/**
	 * Effectue les sommes et les attribues aux Zones prévues
	 * cf : SommeCellule du code Delphi d'Epicea
	 */
	private void calculTotaux(IBQuLgr paramLia ,String regime, String typeTraitement, String sousTypeTraitement, boolean ouverture) {
		
			Map<String,Totaux> listeZoneTotaux;
			String[] listeZonePourSommeTotauxBilan;
			//int i,j;
			boolean k = false;
			String signe,descriptionCalculTotal;
			String chaine;
			int positionChaine = 0;
			String chaineVariableDep =null ,chaineVariableFin = null ,chaineFixe1= null, chaineFixe2 = null;
			Repartition infoRepartTotal;
			Str_PositionVariableResult str_PositionVariableResult;
			Cle cle = null; //objet cle utilise dans les boucles de recherche
			Iterator<Cle> iterateurCle; //itérateur sur les cles de "listeRepartition"
			String separateurFormule = ";";
			//IbTaTotaux ibTaTotaux = new IbTaTotaux();
//			boolean montantArrondi = false;

//			arrondiFinalLiasse();

//			n		if DMRepart = nil then DMRepart:=TDMRepart.Create(Application.MainForm);
			//IbTaTotaux ibTaTotaux = new IbTaTotaux();
			if(listeRepartition != null) {
				//initValeurSansArrondi();
				//if (!ouverture)
				listeZoneTotaux = IbTaTotaux.listeTotaux(regime,typeTraitement);
				//else
				//	listeZoneTotaux = IbTaTotaux.listeTotaux(regime,typeTraitement);

//				for (String nomZone : listeZoneTotaux.keySet()) {//i
//				infoRepartTotal = new Repartition();
//				listeRepartition.put(new Cle(nomZone,sousTypeTraitement),infoRepartTotal);
//				}

				for (String nomZone : listeZoneTotaux.keySet()) {//i
					listeZoneTotaux.get(nomZone).getCondition();
					if(analyseCondition(listeZoneTotaux.get(nomZone).getCondition()))  {
						Cle cleTotal = new Cle(nomZone,sousTypeTraitement);
						infoRepartTotal = new Repartition();
						listeRepartition.put(cleTotal,infoRepartTotal);
						// Récupération de la liste des zones à lire pour faire le total
//						debut n				//if (!ouverture)
						listeZonePourSommeTotauxBilan = listeZoneTotaux.get(nomZone).getListeCle().split(separateurFormule);
						//else
//						fin n						ListeZonePourSommeTotauxBilan:=DM_C_ListeZonePourSommeTotauxBilanOuverture_Bic(ListeZoneTotaux.Strings[i]);

						// Pour chaque élément de la somme, on fait l'analyse syntaxique de la formule
						for (int ind = 0; ind < listeZonePourSommeTotauxBilan.length; ind++) {
							descriptionCalculTotal = listeZonePourSommeTotauxBilan[ind];
							// Général : Chaque formule doit commencer par le signe de l'opération
							signe = descriptionCalculTotal.substring(0,1);  //str_n_prem_char(ListeZonePourSommeTotauxBilan.Strings[j],1);
							// 1° Cas : "+NomZone1:NomZone2", comme par exe : "+RN_A2_1_2:RN_A2_12_2"

							if (descriptionCalculTotal.indexOf(":")  > 0) {
								str_PositionVariableResult = Str_PositionVariable(descriptionCalculTotal,positionChaine,chaineVariableDep,chaineVariableFin,chaineFixe1,chaineFixe2);
								positionChaine = str_PositionVariableResult.positionChaine;
								chaineVariableDep = str_PositionVariableResult.chaineVariableDep;
								chaineVariableFin = str_PositionVariableResult.chaineVariableFin;
								chaineFixe1 = str_PositionVariableResult.chaineFixe1;
								chaineFixe2 = str_PositionVariableResult.chaineFixe2;

								if (str_PositionVariableResult.resultat) {
									for(int index=Integer.parseInt(chaineVariableDep); index<=Integer.parseInt(chaineVariableFin); index++) {
										cle = null;
										k = false;
										// On effectue ici le traitement de la liste des cellules
										iterateurCle = listeRepartition.keySet().iterator();
										while(iterateurCle.hasNext() && k==false) {
											cle= iterateurCle.next();
											if(cle.getCle().equals(chaineFixe1+index+chaineFixe2)) {
												k = true;
											}
										}
										//k=listeRepartition.keySet().contains(chaineFixe1+index+chaineFixe2);
										if(k) {
											// Ajout de la cellule courante au Total de la cellule courante Total
											if(signe.equals("+")) {
//												if(montantArrondi) { 
												listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant()+listeRepartition.get(cle).getMontant());
//												} else {
//												listeRepartition.get(cleTotal).setSansArrondi(listeRepartition.get(cleTotal).getSansArrondi()+listeRepartition.get(cle).getSansArrondi());
//												listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant(montantArrondi)+listeRepartition.get(cle).getMontant(montantArrondi));
//												}
											} else {
//												if(montantArrondi) { 
												listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant()-listeRepartition.get(cle).getMontant());
//												} else {
//												listeRepartition.get(cleTotal).setSansArrondi(listeRepartition.get(cleTotal).getSansArrondi()-listeRepartition.get(cle).getSansArrondi());
//												listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant(montantArrondi)-listeRepartition.get(cle).getMontant(montantArrondi));
//												}
											}
											listeRepartition.get(cleTotal).getDetail().add(new Compte(signe+chaineFixe1+index+chaineFixe2));
											//listeRepartition.get(cleTotal).getDetail().add(new Compte(null,signe+chaineFixe1+index+chaineFixe2,null,listeRepartition.get(cle).getMontant(montantArrondi),null));
										}
									}
								}
							}
							else {
								// Dernier cas, c'est une zone simple, ex : "+RN_A2_1_2"
								k = false;
								cle = null;
								chaine = descriptionCalculTotal.substring(1,descriptionCalculTotal.length());
								// On effectue ici le traitement de la liste des cellules
								iterateurCle = listeRepartition.keySet().iterator();
								while(iterateurCle.hasNext() && k==false) {
									cle= iterateurCle.next();
									if(cle.getCle().equals(chaine)) {
										k = true;
									}
								}
								if(k) {
									// Ajout de la cellule courante au Total de la cellule courante Total
									if(signe.equals("+")) {
//										if(montantArrondi) { 
										listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant()+listeRepartition.get(cle).getMontant());
//										} else {
//										listeRepartition.get(cleTotal).setSansArrondi(listeRepartition.get(cleTotal).getSansArrondi()+listeRepartition.get(cle).getSansArrondi());
//										listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant(montantArrondi)+listeRepartition.get(cle).getMontant(montantArrondi));
//										}
									} else {
//										if(montantArrondi) { 
										listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant()-listeRepartition.get(cle).getMontant());
//										} else {
//										listeRepartition.get(cleTotal).setSansArrondi(listeRepartition.get(cleTotal).getSansArrondi()-listeRepartition.get(cle).getSansArrondi());
//										listeRepartition.get(cleTotal).setMontant(listeRepartition.get(cleTotal).getMontant(montantArrondi)-listeRepartition.get(cle).getMontant(montantArrondi));
//										}
									}
									listeRepartition.get(cleTotal).getDetail().add(new Compte(signe+chaine));
								}

							}

						}
					}
				}


//				arrondiFinalLiasse();
			}
		
	}

	public Cle rechercheRepartition(String cleSimple) {
		Cle cle = null;
		Iterator<Cle> iterateurCle = listeRepartition.keySet().iterator();
		boolean trouve = false;
		while(iterateurCle.hasNext() && !trouve) {
			cle = iterateurCle.next();
			if(cle.getCle().equals(cleSimple)) {
				trouve = true;
			}
		}
		return (trouve ? cle : null);
	}

	private boolean analyseCondition(String condition) {
		//plusieur condition
		// < >
		// RN_B1_8_1>0
		boolean resultat = true;
		if(condition!=null) {
			String[] listeCondition = condition.split(";");
			String var1, var2;
			Cle cle1,cle2 = null;
			Double d1=0d,d2=0d;

			for (int i = 0; i < listeCondition.length; i++) {
				if(listeCondition[i].contains("<")) {
					listeCondition[i].split("<");
					var1 = listeCondition[i].split("<")[0];
					var2 = listeCondition[i].split("<")[1];
					if(!var1.equals("0")) {
						cle1 = rechercheRepartition(var1);
						if(cle1!=null) {
							d1 = listeRepartition.get(cle1).getMontant();
						}
					} 
					if(!var2.equals("0")) {
						cle2 = rechercheRepartition(var2);
						if(cle2!=null) {
							d2 = listeRepartition.get(cle2).getMontant();
						}
					}
					resultat = d1<d2;
				} else if (listeCondition[i].contains(">")){
					listeCondition[i].split(">");
					var1 = listeCondition[i].split(">")[0];
					var2 = listeCondition[i].split(">")[1];
					if(!var1.equals("0")) {
						cle1 = rechercheRepartition(var1);
						if(cle1!=null) {
							d1 = listeRepartition.get(cle1).getMontant();
						}
					} 
					if(!var2.equals("0")) {
						cle2 = rechercheRepartition(var2);
						if(cle2!=null) {
							d2 = listeRepartition.get(cle2).getMontant();
						}
					}
					resultat = d1>d2;
				}
			}
		}

		return resultat;
	}

	private class Str_PositionVariableResult {
		public int positionChaine = 0;
		public String chaineVariableDep = null;
		public String chaineVariableFin = null;
		public String chaineFixe1 = null;
		public String chaineFixe2 = null;
		public boolean resultat = false;
	}

	/**
	 * Analyse d'une chaine représentant un intervale de deux zones : 
	 * @param chaine - la chaine à analyser, ex: +RN_A2_1_1:RN_A2_12_1
	 * @param positionChaine - retourne la position de la chaine variable
	 * @param chaineVariableDep - le 1er "1" de RN_A2_1_1
	 * @param chaineVariableFin - le "12" de RN_A2_12_1
	 * @param chaineFixe1 - le debut commun aux deux nom de cellules : "RN_A2_"
	 * @param chaineFixe2 - le fin communne aux deux nom de cellules : "_1"
	 * @return Str_PositionVariableResult - contient : positionChaine,chaineVariableDep,chaineVariableFin,chaineFixe1,chaineFixe2 <br>
	 * Str_PositionVariableResult.resultat = true ssi ((chaineVariableDep.length()>0) && (chaineVariableFin.length()>0))
	 */	
	public Str_PositionVariableResult Str_PositionVariable(String chaine, int positionChaine, String chaineVariableDep,String chaineVariableFin,String chaineFixe1, String chaineFixe2) {
		String PLUS = "+";
		String MOINS = "-";
		String DEUX_POINT = ":";
		String UNDER_SCORE = "_";
		char CHAR_UNDER_SCORE = '_';
		int i,j;
		String chaine1,chaine2;
		boolean result = false;
		Str_PositionVariableResult retour = new Str_PositionVariableResult();		

		if (chaine.length() > 0) {
			i=1;
			if (chaine.substring(0,i).equals(PLUS))
				chaine = chaine.substring(1,chaine.length());
			if (chaine.substring(0,i).equals(MOINS))
				chaine = chaine.substring(1,chaine.length());

			chaine1 = chaine;
			chaine1 = chaine1.substring(0,chaine1.indexOf(DEUX_POINT));

			chaine2 = chaine;
			chaine2 = chaine2.substring(chaine2.indexOf(DEUX_POINT)+1,chaine2.length());

			// Partie gauche
			i=0;
			j=0;
			while ((i < 2) && (j < chaine1.length())) {
				if (chaine1.charAt(j) == CHAR_UNDER_SCORE)
					i++;
				j++;
			}
			chaineFixe1 = chaine1.substring(0,j);
			positionChaine = j;

			// Partie droite
			i=j;
			// Détermination de la 1° chaine variable
			chaineVariableDep="";
			while ((j < chaine1.length()) && (chaine1.charAt(j) != CHAR_UNDER_SCORE)) {
				chaineVariableDep+=chaine1.charAt(j);
				j++;
			}
			// Détermination de la 2° chaine variable
			chaineVariableFin="";
			//while ((chaine2.charAt(i-1) != CHAR_UNDER_SCORE) && (i < chaine2.length())) {
			while ((i < chaine2.length()) && (chaine2.charAt(i) != '_')) {
				chaineVariableFin+=chaine2.charAt(i);
				i++;
			}

			if (chaine1.split(UNDER_SCORE).length-1 == 2) 
				chaineFixe2 = "";

			if (chaine1.split(UNDER_SCORE).length-1 == 3) {
				j=chaine2.length()-1;
				while (!chaine2.substring(j,j+1).equals(UNDER_SCORE)) {
					j--;
				}   
				chaineFixe2 = chaine2.substring(chaine2.length()-(chaine2.length()-j), chaine2.length());
			}

			result = ((chaineVariableDep.length() > 0) && (chaineVariableFin.length() > 0));

		}

		retour.chaineFixe1 = chaineFixe1;
		retour.chaineFixe2 = chaineFixe2;
		retour.chaineVariableDep = chaineVariableDep;
		retour.chaineVariableFin = chaineVariableFin;
		retour.positionChaine = positionChaine;
		retour.resultat = result;

		return retour;
	}

	//private
	public void calculTotaux(IBQuLgr paramLia,String regime, String typeTraitement, String sousTypeTraitement) {
		calculTotaux(paramLia,regime,typeTraitement,sousTypeTraitement,false);
	}

	public HashMap<Cle, Repartition> getListeRepartition() {
		return listeRepartition;
	}

	public void setListeRepartition(HashMap<Cle, Repartition> listeRepartition) {
		this.listeRepartition = listeRepartition;
	}


	public void saveFDF(String fdfFile, String pdfFile) {
		try {
//			fdfFile = E.REpertoireFiscal+"BILAN AGRICOLE REEL.fdf";
			BufferedWriter bw;
			int i;
			Double Debit,Credit;

			bw = new BufferedWriter(new FileWriter(fdfFile));
			bw.write("%FDF-1.2"); bw.newLine();
			//bw.write("%âãÏÓ"); bw.newLine();
			bw.write("1 0 obj"); bw.newLine();
			bw.write("<<"); bw.newLine();
			bw.write("/FDF << /Fields ["); bw.newLine();
			/* nico		
		bw.write(" << /V ("+DateInfos(E.DatExoDebut).DateStr+") /T (A1_2_1)>>");
		bw.write(" << /V ("+DateInfos(E.DatExoFin).DateStr+") /T (A1_2_2)>>"); //
		bw.write(" << /V ("+" - DOSSIER : "+E.NomDossier+" - EXERCICE : "+E.NomExo+" - '+E.RaisonSociale+') /T (RN_ZRL_1)>>");
		i = NbMois(E.DatExoDebut,E.DatExoFin);
		if  (i > 9) {
			bw.write(" << /V ("+Copy(IntToStr(i),1,1)+") /T (PC_2)>>"); //
			bw.write(" << /V ("+Copy(IntToStr(i),2,1)+") /T (PC_1)>>"); //
		} else {
			bw.write(" << /V (0) /T (PC_2)>>"); //
			bw.write(" << /V ("+IntToStr(i)+") /T (PC_1)>>"); //
		}
			 */

//			DMDiocEtatBal.ResultatExercice(Debit,Credit,E.DatExoDebut,E.DatExoFin);
//			ListePdf2.Add(' << /V ('+CurrToStr(Debit+Credit)+') /T (RN_B1_8_1)>>');


//			Recalcul et réaffectation, cela permet
//			de ne pas perdre les référence des objets de chaque cellule
//			par rapport à DMRepart.ListeCell
//			nico		BtnCalculer.Click;

			if (listeRepartition.size()>0) {
				// Remplissage de la liste servant à générer le PDF
				for (Cle cellule : listeRepartition.keySet()) {

//					ListePdf2.Add(' << /V ('+E.FormatCurrSansDevise(TRepartition(DMRepart.ListeCell.Objects[i]).MontantTotal)+')/T ('+DMRepart.ListeCell.Strings[i]+')>>');
//					nico				if (CBEditionCentime.Checked) 
					if(listeRepartition.get(cellule).getMontant()!=0d) {
						bw.write(" << /V ("+LibCalcul.arrondi(listeRepartition.get(cellule).getMontant())+")/T ("+cellule.getCle()+")>>"); bw.newLine();
					} else {
						bw.write(" << /V ("+listeRepartition.get(cellule).getValeur()+")/T ("+cellule.getCle()+")>>"); bw.newLine();
					}
//					nico				else
//					nico					bw.write(" << /V ("+LibCalcul.arrondi(listeRepartition.get(cellule).getMontant(),0)+")/T ("+cellule+")>>"); bw.newLine();
//					nico				}
				}
			}
			bw.write(" ]");

			/* nico		
		if (Sender = BtnImprimerDetail) {

			switch (BtnImprimerDetail.Tag) {
				case EditionPartBilanActif: bw.write("/F ("+DM_C_NomBilanAGRReel2144+")/ID [ <387aec558f007ac48b5052a725e4afd1><7040395dab48dfebccddc82ce39a75f8>");
					break;
				case EditionPartBilanPassif: bw.write("/F ("+DM_C_NomBilanAGRReel2145+")/ID [ <387aec558f007ac48b5052a725e4afd1><7040395dab48dfebccddc82ce39a75f8>");
					break;
				case EditionPartCptResult1: bw.write("/F ("+DM_C_NomBilanAGRReel2146+")/ID [ <387aec558f007ac48b5052a725e4afd1><7040395dab48dfebccddc82ce39a75f8>");
					break;
				case EditionPartCptResult2: bw.write("/F ("+DM_C_NomBilanAGRReel21462+")/ID [ <387aec558f007ac48b5052a725e4afd1><7040395dab48dfebccddc82ce39a75f8>");
					break;
				default :
					bw.write("/F ("+DM_C_NomBilanAGRReel+")/ID [ <387aec558f007ac48b5052a725e4afd1><7040395dab48dfebccddc82ce39a75f8>");
					break;
			}
		}

		if (Sender = BtnImprimer) {
			bw.write("/F ("+DM_C_NomBilanAGRReel+")/ID [ <387aec558f007ac48b5052a725e4afd1><7040395dab48dfebccddc82ce39a75f8>");
		}
			 */		
			//nico
			bw.write("/F ("+pdfFile+")/ID [ <387aec558f007ac48b5052a725e4afd1><3bb486fc2b40ae11aed9513545836321>"); bw.newLine();

			bw.write("] >>"); bw.newLine();
			bw.write(">>"); bw.newLine();
			bw.write("endobj"); bw.newLine();
			bw.write("trailer"); bw.newLine();
			bw.write("<<"); bw.newLine();
			bw.write("/Root 1 0 R"); bw.newLine();
			bw.write(""); bw.newLine();
			bw.write(">>"); bw.newLine();
			bw.write("%%EOF"); bw.newLine();
			bw.write(""); bw.newLine();

//			nico		bw.write(" ]/F (BILAN AGRICOLE REEL.pdf)/ID [ <387aec558f007ac48b5052a725e4afd1><3bb486fc2b40ae11aed9513545836321>"); bw.newLine();
//			nico		bw.write("] >>>>endobjtrailer<</Root 1 0 R>>%%EOF"); bw.newLine();

			bw.close();

//			nico		if (DMRepart.ListeCell.Count > 0) {
//			nico			ShellExecute(handle,'open','AcroRd32.exe',PChar('"'+E.REpertoireFiscal+'BILAN AGRICOLE REEL.fdf"'),PChar(E.REpertoireFiscal),SW_SHOWNORMAL);
//			nico		}
		} catch(IOException e) {
			logger.error("",e);
		}
	}

//	Function VisuListeCompteDeZoneAffich(NomZone:String;ExercicePrecedent:Boolean):Boolean;
//	Function MaJReportChargeEtProduit(DataSet:TDataSet):Boolean;
//	Function AffichBilanOuvertureSurPeriode:Boolean;
//	Function AffichChargesEtProduitOuvertureSurPeriode:Boolean;
//	Procedure SommeCellule_Bic(ParamLia_Bic:TDataSet;Ouverture:boolean=false);
}