package fr.legrain.document.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TooManyListenersException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.swing.event.EventListenerList;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaFamille;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.dossier.model.TaVersion;
import fr.legrain.lib.data.ChangeModeEvent;
import fr.legrain.lib.data.ChangeModeListener;
//import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.LibChaine;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.validator.LgrHibernateValidated;

@Entity
@SequenceGenerator(name = "gen_l_facture", sequenceName = "num_id_l_document", allocationSize = 1)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SWTLigneDocument  implements  Serializable, ILigneDocumentTiers {

	private static final long serialVersionUID = 3738738932385965035L;
	
	//passage ejb
//	protected EnumModeObjet modeLigne;
//	protected EnumModeObjet modeDocument;
	
	protected EventListenerList listenerList = new EventListenerList();
	//protected Update updateLigne = new Update();
	protected TaArticle taArticle = null;
	protected Integer rowGrille = -1;
	
	//TODO arriver à supprimer ces 2 propriétés et n'utiliser que numLigneDocument et idDocument => demande pas mal de tests/verifications
	protected Integer ID_DOCUMENT = 0;
	//protected Integer NUM_LIGNE = 0;
	
	protected String libLDocument;
	protected int idLDocument;
	protected Integer versionObj;
//	protected TaEtat taEtat;
	protected TaREtatLigneDocument taREtat;
	
//	@Transient
//	protected Integer nbDecimalesQte = 3;
//	@Transient
//	protected Integer nbDecimalesPrix = 2;
	
	@Transient
	protected boolean genereLigne = true;
	@Transient
	protected BigDecimal qteGenere;
	@Transient
//	protected String uniteGenere="";
	protected String uniteGenere; // isa le 04/03/2021 car sinon dans toutes les générations, les unités ne sont pas repris (contrôle si ==null)
	@Transient
	protected String numlotGenere="";

	@Transient
	private int numLigneImpression;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_l_facture")
	@Column(name = "id_l_document", unique = true, nullable = false)
	public int getIdLDocument() {
		return this.idLDocument;
	}

	public void setIdLDocument(int idLFacture) {
		this.idLDocument = idLFacture;
	}
	
	@Version
	@Column(name = "version_obj")
	public Integer getVersionObj() {
		return this.versionObj;
	}
	
	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	
//	public abstract boolean ligneEstVide();
	public boolean ligneEstVide() {
		if (taArticle!=null)return false;
		if (libLDocument!=null && !libLDocument.equals(""))return false;	
		return true;
	}
	
	public void addChangeModeListener(ChangeModeListener l) throws TooManyListenersException {
		listenerList.add(ChangeModeListener.class, l);
//		if(listenerList.getListenerCount()>1) {
//			throw new TooManyListenersException("Un seul listener par ligne");
//		}
	}
	
	public void removeChangeModeListener(ChangeModeListener l) {
		listenerList.remove(ChangeModeListener.class, l);
	}
	
	protected void fireChangeMode(ChangeModeEvent e) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeModeListener.class) {
				if (e == null)
					e = new ChangeModeEvent(this,null);
				( (ChangeModeListener) listeners[i + 1]).changementMode(e);
			}
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		listenerList.add(PropertyChangeListener.class, l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l) {
		listenerList.remove(PropertyChangeListener.class, l);
	}
	
	protected void firePropertyChange(PropertyChangeEvent e) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == PropertyChangeListener.class) {
				if (e == null)
					e = new PropertyChangeEvent(this,null,null,null);
				( (PropertyChangeListener) listeners[i + 1]).propertyChange(e);
			}
		}
	}
	
//	public void enregistrer() throws ExceptLgr {
//		beforeEnregistrerEntete();
//		//TODO enregistrement du document dans les bonnes tables
//		
//		//update.createQuery();
//		afterEnregistrerEntete();
//	}
//	
//	public void modifier() throws ExceptLgr {
//		beforeModifierEntete();
//		//TODO modification du document dans les bonnes tables
//		afterModifierEntete();
//	}
//	
//	public void supprimer() throws ExceptLgr {
//		beforeSupprimerEntete();
//		//TODO suppression du document
//		afterSupprimerEntete();
//	}

	//passage ejb
//	@Transient
//	public EnumModeObjet getModeLigne() {
//		return modeLigne;
//	}

	//passage ejb
	public void setModeLigne(/*EnumModeObjet mode*/) {
		//passage ejb
//		this.modeLigne = mode;
		fireChangeMode(new ChangeModeEvent(this/*,mode*/));
		//passage ejb
		//firePropertyChange(new PropertyChangeEvent(this,"modeLigne",mode,mode));
	}

	@Transient
	public Integer getID_DOCUMENT() {
		return ID_DOCUMENT;
	}

	public void setID_DOCUMENT(Integer id_document) {
		ID_DOCUMENT = id_document;
	}

	


	@Transient
	public boolean getGenereLigne() {
		return genereLigne;
	}

	@Transient
	public void setGenereLigne(boolean genereLigne) {
		this.genereLigne = genereLigne;
	}
	
	//passage ejb
//	@Transient
//	public EnumModeObjet getModeDocument() {
//		return modeDocument;
//	}
//
//	public void setModeDocument(EnumModeObjet modeDocument) {
//		this.modeDocument = modeDocument;
//	}

	@Transient
	public TaArticle getTaArticle() {
		return taArticle;
	}

	public void setTaArticle(TaArticle article) {
		this.taArticle = article;
	}

	@Transient
	public Integer getRowGrille() {
		return rowGrille;
	}

	public void setRowGrille(Integer rowGrille) {
		this.rowGrille = rowGrille;
	}

//	@Transient
//	public Integer getNUM_LIGNE() {
//		return NUM_LIGNE;
//	}
//
//	public void setNUM_LIGNE(Integer num_ligne) {
//		NUM_LIGNE = num_ligne;
//		setNumLigneLDocument(num_ligne);
//	}
	
	@Transient
	public void initQte2(TaArticle article){
		boolean commentaire=false;
		commentaire=article.getTaFamille()!=null && article.getTaFamille().getCodeFamille().equals("&&&");
		if(!commentaire && article.getTaRapportUnite()!=null && article.getTaRapportUnite().getTaUnite2()!=null
				&& article.getTaRapportUnite().getTaUnite1().getCodeUnite().equals(getU1LDocument())){
			int nbDecimal = article.getTaRapportUnite().getNbDecimal(); 
			//int nbDecimal = 2;
			setU2LDocument(article.getTaRapportUnite().getTaUnite2().getCodeUnite());
			boolean sens = LibConversion.intToBoolean(article.getTaRapportUnite().getSens());
			BigDecimal Qte2 = new BigDecimal(0);
			if(sens) {
				if(getQteLDocument().compareTo(new BigDecimal(0))!=0)
					//Qte2 = LibCalcul.arrondi(getQteLDocument().divide(article.getTaRapportUnite().getRapport()),nbDecimal);
					if(article.getTaRapportUnite().getRapport().compareTo(new BigDecimal(0))!=0)
						Qte2 = getQteLDocument().divide(article.getTaRapportUnite().getRapport(),MathContext.DECIMAL128).setScale(nbDecimal,BigDecimal.ROUND_HALF_UP);
					else
						Qte2 = new BigDecimal(0);
			} else {
				//Qte2 = LibCalcul.arrondi(getQteLDocument().multiply(article.getTaRapportUnite().getRapport()),nbDecimal);
				Qte2 = getQteLDocument().multiply(article.getTaRapportUnite().getRapport(),MathContext.DECIMAL128).setScale(nbDecimal,BigDecimal.ROUND_HALF_UP);
			}
			setQte2LDocument(Qte2);
		}else{
			setU2LDocument(null);
			setQte2LDocument(null);		
		}
	}
	
	
	@Transient
	public int getNumLigneImpression() {
		return numLigneImpression;
	}
	@Transient
	public void setNumLigneImpression(int numLigneImpression) {
		this.numLigneImpression = numLigneImpression;
	}
	
//	@Transient
//	public Integer getNbDecimalesQte() {
//		return nbDecimalesQte;
//	}
//
//	@Transient
//	public void setNbDecimalesQte(Integer nbDecimalesQte) {
//		this.nbDecimalesQte = nbDecimalesQte;
//	}
//
//	@Transient
//	public Integer getNbDecimalesPrix() {
//		return nbDecimalesPrix;
//	}
//
//	@Transient
//	public void setNbDecimalesPrix(Integer nbDecimalesPrix) {
//		this.nbDecimalesPrix = nbDecimalesPrix;
//	}

	


	@Transient
	public LinkedList<ILigneDocumentTiers> creeLigneImpression_indirect(int coupure, int rangImpression){
		LinkedList<ILigneDocumentTiers> listeNewLigne=new LinkedList<ILigneDocumentTiers>();
		try {
			if(coupure>0 && this.getLibLDocument()!=null && !this.getLibLDocument().equals("")){
			ArrayList<String> listPhrases =new ArrayList<String>();
			String phrase="";
			String ligneAParcourir=this.getLibLDocument();
			String lettre="";
			int nbMinus=0;
			int nbMaj=0;
			int nbDemisMinus=0;
			int rang=0;
			int j=0;
			String ligneParcourue="";
			String ligneDejaParcourue="";
			String[]mots=ligneAParcourir.split(" ");

			while(rang<=ligneAParcourir.length()-1){
				lettre=ligneAParcourir.substring(rang, rang+1);
				ligneParcourue+=lettre;
				if (LibChaine.formatDemisMinus(lettre))nbDemisMinus++;
				else
				if (LibChaine.formatChiffre(lettre))nbMinus++;
			else{
				if(lettre.equals(lettre.toUpperCase()))
					nbMaj++;
				else
					nbMinus++;
			}
				boolean trouve=true;
				String ligneAEcrire="";
				if((nbMaj*1.3)+nbMinus+(nbDemisMinus*0.6)>=coupure){
					j=0;
					//on récupère tous les mots qui sont contenus dans la ligneParcourue
					//jusqu'à l'avant dernier
					j=ligneParcourue.lastIndexOf(" ");
					if(j>0)ligneAEcrire=ligneParcourue.substring(0, j);
					else ligneAEcrire=ligneParcourue;
					ligneDejaParcourue+=ligneAEcrire;
					listPhrases.add(ligneAEcrire);
					rang=ligneDejaParcourue.length();
					ligneParcourue="";
					ligneAEcrire="";
					nbMaj=0;
					nbMinus=0;
					nbDemisMinus=0;
				}

				rang++;
			}
			if(!ligneParcourue.equals(""))listPhrases.add(ligneParcourue);		
			rang=0;
			for (String phrases : listPhrases) {
				if(rang==0){//on traite la ligne initiale
					ILigneDocumentTiers lf=creationNewLigne(true, rangImpression);
					lf.setLibLDocument(phrases);					
					listeNewLigne.add(lf);
				}
				else{
					//création de la nouvelle ligne
					ILigneDocumentTiers newLigne=creationNewLigne(false, rangImpression);
					((SWTLigneDocument)newLigne).initialiseLigne(true);
					newLigne.setTaTLigne(getTaTLigne());
					newLigne.setLibLDocument(phrases);
					listeNewLigne.add(newLigne);
				}
				rang++;
			}
		}else listeNewLigne.add(this);
		} catch (Exception e) {
			return null;
		}
		return listeNewLigne;
	}
	
	public ILigneDocumentTiers creationNewLigne(boolean avecClonage,int rangImpression) throws CloneNotSupportedException{
		ILigneDocumentTiers lf=null;
		if(this.getTaDocument().getTypeDocument().equals(TaFacture.TYPE_DOC)){
			lf=new TaLFacture();
			if(avecClonage)	lf=(TaLFacture)this.clone();
			else ((TaLFacture)lf).setTaDocument((TaFacture)this.getTaDocument());
			((TaLFacture)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaTicketDeCaisse.TYPE_DOC)){
			lf=new TaLTicketDeCaisse();
			if(avecClonage)	lf=(TaLTicketDeCaisse)this.clone();
			else ((TaLTicketDeCaisse)lf).setTaDocument((TaTicketDeCaisse)this.getTaDocument());
			((TaLTicketDeCaisse)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaAvoir.TYPE_DOC)){
			lf=new TaLAvoir();
			if(avecClonage)	lf=(TaLAvoir)this.clone();
			else ((TaLAvoir)lf).setTaDocument((TaAvoir)this.getTaDocument());
			((TaLAvoir)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaApporteur.TYPE_DOC)){
			lf=new TaLApporteur();
			if(avecClonage)	lf=(TaLApporteur)this.clone();
			else ((TaLApporteur)lf).setTaDocument((TaApporteur)this.getTaDocument());
			((TaLApporteur)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaBoncde.TYPE_DOC)){
			lf=new TaLBoncde();
			if(avecClonage)	lf=(TaLBoncde)this.clone();
			else ((TaLBoncde)lf).setTaDocument((TaBoncde)this.getTaDocument());
			((TaLBoncde)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaBonliv.TYPE_DOC)){
			lf=new TaLBonliv();
			if(avecClonage)	lf=(TaLBonliv)this.clone();
			else ((TaLBonliv)lf).setTaDocument((TaBonliv)this.getTaDocument());
			((TaLBonliv)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaDevis.TYPE_DOC)){
			lf=new TaLDevis();
			if(avecClonage)	lf=(TaLDevis)this.clone();
			else ((TaLDevis)lf).setTaDocument((TaDevis)this.getTaDocument());
			((TaLDevis)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaAcompte.TYPE_DOC)){
			lf=new TaLAcompte();
			if(avecClonage)	lf=(TaLAcompte)this.clone();
			else ((TaLAcompte)lf).setTaDocument((TaAcompte)this.getTaDocument());
			((TaLAcompte)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaPrelevement.TYPE_DOC)){
			lf=new TaLPrelevement();
			if(avecClonage)	lf=(TaLPrelevement)this.clone();
			else ((TaLPrelevement)lf).setTaDocument((TaPrelevement)this.getTaDocument());
			((TaLPrelevement)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaProforma.TYPE_DOC)){
			lf=new TaLProforma();
			if(avecClonage)	lf=(TaLProforma)this.clone();
			else ((TaLProforma)lf).setTaDocument((TaProforma)this.getTaDocument());
			((TaLProforma)lf).setNumLigneImpression(rangImpression);
		}
		if(this.getTaDocument().getTypeDocument().equals(TaAvisEcheance.TYPE_DOC)){
			lf=new TaLAvisEcheance();
			if(avecClonage)	lf=(TaLAvisEcheance)this.clone();
			else ((TaLAvisEcheance)lf).setTaDocument((TaAvisEcheance)this.getTaDocument());
			((TaLAvisEcheance)lf).setNumLigneImpression(rangImpression);
		}	
		if(this.getTaDocument().getTypeDocument().equals(TaBoncdeAchat.TYPE_DOC)){
			lf=new TaLBoncdeAchat();
			if(avecClonage)	lf=(TaLBoncdeAchat)this.clone();
			else ((TaLBoncdeAchat)lf).setTaDocument((TaBoncdeAchat)this.getTaDocument());
			((TaLBoncdeAchat)lf).setNumLigneImpression(rangImpression);
		}			
		return lf;
	}


	
	public void initialiseLigne(boolean reset){
		if (reset){
			setLibLDocument("");
			setQteLDocument(null);
			setQte2LDocument(null);
			setU1LDocument("") ;
			setU2LDocument("") ;
			setPrixULDocument(null);
			setTauxTvaLDocument(null);
			setCodeTvaLDocument("");
			setCodeTTvaLDocument("");
			setMtHtLDocument(null);
			setMtTtcLDocument(null);
			setRemTxLDocument(null);
			setRemHtLDocument(null);
		}else {
			setLibLDocument("");
			setQteLDocument(new BigDecimal(0));
			setQte2LDocument(new BigDecimal(0));
			setU1LDocument("");
			setU2LDocument("");
			setPrixULDocument(new BigDecimal(0)) ;
			setTauxTvaLDocument(new BigDecimal(0));
			setCodeTvaLDocument("");
			setCodeTTvaLDocument("");
			setMtHtLDocument(new BigDecimal(0));
			setMtTtcLDocument(new BigDecimal(0));
			setRemTxLDocument(new BigDecimal(0));
			setRemHtLDocument(new BigDecimal(0)) ;
		}
		setTaArticle(null);	
	}

	//@PrePersist
	//@PreUpdate
	public void beforePost()throws Exception{
//passage ejb
//		TaTLigneDAO dao =new TaTLigneDAO();
//		TaTLigne ligneH=dao.findByCode("H");
//		TaTLigne ligneC=dao.findByCode("C");
//		TaFamilleDAO daoFamille = new TaFamilleDAO();
//		Integer idFamilleCommentaire=0;
//		try {
//			TaFamille familleCommentaire = daoFamille.findByCode("&&&");
//			if(familleCommentaire!=null)idFamilleCommentaire=familleCommentaire.getIdFamille();
//		} catch (Exception e) {	}
//		
//		if(getTaArticle()!=null){
//			if(getTaTLigne().equals(ligneC) || 
//					(getTaArticle().getTaFamille()!=null && getTaArticle().getTaFamille().getIdFamille()==idFamilleCommentaire) ){
//		        setTaArticle(null);
//		        setQteLDocument(null);
//		        setU1LDocument(null);
//		        setU2LDocument(null);
//		        setPrixULDocument(null);
//		        setTauxTvaLDocument(null);
//		        setCompteLDocument(null);
//		        setCodeTvaLDocument(null);
//		        setCodeTTvaLDocument(null);
//		        setMtHtLDocument(null);
//		        setMtHtLApresRemiseGlobaleDocument(null);
//		        setMtTtcLDocument(null);
//		        setMtTtcLApresRemiseGlobaleDocument(null);
//		        setRemTxLDocument(null);
//		        setRemHtLDocument(null);	
//			}
//		}
	}
	
//	@PrePersist
	public void beforeInsert()throws Exception{
//passage ejb
//		TaVersionDAO daoVersion =new TaVersionDAO();
//		TaVersion version= daoVersion.findInstance();
//		this.setQuiCreeLDocument("");
//		this.setQuandCreeLDocument(new Date());
//		this.setQuiModifLDocument("");
//		this.setQuandModifLDocument(new Date());
//		this.setIpAcces("");
//		this.setVersion(version.getNumVersion());
	}
	
//	@PreUpdate
	public void beforeUpdate()throws Exception{
//passage ejb
//		TaVersionDAO daoVersion =new TaVersionDAO();
//		TaVersion version= daoVersion.findInstance();
//		this.setQuiModifLDocument("");
//		this.setQuandModifLDocument(new Date());
//		this.setIpAcces("");
//		this.setVersion(version.getNumVersion());
	}


	@Transient
	public String getUniteGenere() {
		return uniteGenere;
	}

	public void setUniteGenere(String uniteGenere) {
		this.uniteGenere = uniteGenere;
	}
	
	@Transient
	public BigDecimal getQteGenere() {
		return qteGenere;
	}

	public void setQteGenere(BigDecimal qteGenere) {
		this.qteGenere = qteGenere;
	}
	
	@Transient
	public String getNumlotGenere() {
		return numlotGenere;
	}

	public void setNumlotGenere(String numlotGenere) {
		this.numlotGenere = numlotGenere;
	}	


	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "id_etat")
//	@LgrHibernateValidated(champBd = "id_etat",table = "ta_etat",champEntite="TaEtat.idEtat", clazz = TaEtat.class)
//	public TaEtat getTaEtat() {
//		return this.taEtat;
//	}
//
//	public void setTaEtat(TaEtat taEtat) {
//		this.taEtat = taEtat;
//	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_r_etat")
	public TaREtatLigneDocument getTaREtatLigneDocument() {
		return this.taREtat;
	}

	public void setTaREtatLigneDocument(TaREtatLigneDocument taREtat) {
		this.taREtat = taREtat;
	}
	


}
