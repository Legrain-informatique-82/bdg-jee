package fr.legrain.document.divers;

import java.util.Collection;
import java.util.LinkedList;

import fr.legrain.gestCom.Module_Document.IDocumentTiers;
import fr.legrain.gestCom.Module_Document.SWTCreationDocLigne;

public class ModelCreationDoc {
	



	public ModelCreationDoc( ) {
	}


	private LinkedList<SWTCreationDocLigne> listeObjet = new LinkedList<SWTCreationDocLigne>();
	private Collection<TaCreationDoc> listeEntity = null;
	


	TypeDoc typeDocPresent = TypeDoc.getInstance();
//	EntityManager em =new TaFactureDAO().getEntityManager();


	
	public LinkedList<SWTCreationDocLigne> remplirListe() {
		int compteur=0;
		SWTCreationDocLigne ihmTiers=null;
		compteur++;
		SWTCreationDocLigne ihmligne=null;
		SWTCreationDocLigne ihmDoc=null;
		String codeTiersCourant="";
		int compteurDoc=0;
		if(listeObjet==null)listeObjet=new LinkedList<SWTCreationDocLigne>();
		String libelle="";
 		listeObjet.clear();
 		int compteurDocTiers=0;
		if(getListeEntity()!=null){
		for (Object t : getListeEntity()) {
			if(!codeTiersCourant.equals(((TaCreationDoc)t).getCodeTiers())){
				if(ihmTiers!=null)ihmTiers.setCodeDocument(ihmTiers.getCodeTiers()+" ("+compteurDocTiers+")");
				ihmTiers= new SWTCreationDocLigne(compteur);
				compteurDocTiers=0;
				compteur++;
				ihmTiers.setCodeTiers(((TaCreationDoc)t).getCodeTiers());
				ihmTiers.setCodeTiersParent(((TaCreationDoc)t).getCodeTiers());
				ihmTiers.setCodeDocument(((TaCreationDoc)t).getCodeTiers());
				ihmTiers.setAccepte(true);
				listeObjet.add(ihmTiers);
				codeTiersCourant=((TaCreationDoc)t).getCodeTiers();
			}
			ihmDoc=new SWTCreationDocLigne(compteur);
			compteur++;
			compteurDocTiers++;
			ihmDoc.setCodeDocument(((TaCreationDoc)t).getCodeDocument());
			//ihmDoc.setCodeTiers(ihmTiers.getCodeTiers());
			ihmDoc.setCodeTiersParent(ihmTiers.getCodeTiers());
			ihmDoc.setAccepte(true);
			ihmTiers.getList().add(ihmDoc);
			
			for (IDocumentTiers doc : ((TaCreationDoc)t).getListeDoc()) {
				//libelle+=doc.getCodeDocument()+"-";				
				ihmligne=new SWTCreationDocLigne(compteur);
				compteur++;
				ihmligne.setCodeDocument(ihmDoc.getCodeDocument()) ;
				ihmligne.setCodeTiersParent(ihmTiers.getCodeTiers()) ;
				ihmligne.setCodeDocumentRecup(doc.getCodeDocument());
				ihmligne.setLibelleLigne(doc.getLibelleDocument());
				ihmligne.setDateDocument(doc.getDateDocument());
				ihmligne.setNetTtc(doc.getNetTtcCalc());
				ihmligne.setAccepte(true);
				ihmDoc.getList().add(ihmligne);
				compteurDoc++;
				ihmligne=null;
			}
			libelle=((TaCreationDoc)t).getLibelleDocument()+libelle+
					" ("+compteurDoc+")";
			ihmDoc.setLibelleDocument(libelle);
			compteurDoc=0;
			libelle="";
		}
		if(ihmTiers!=null)ihmTiers.setCodeDocument(ihmTiers.getCodeTiers()+" ("+compteurDocTiers+")");
		}

		return listeObjet;
	}
//	public LinkedList<SWTCreationDocLigne> remplirListe() {
//		SWTCreationDocLigne ihm=null;
//		SWTCreationDocLigne ihmligne=null;
//		if(listeObjet==null)listeObjet=new LinkedList<SWTCreationDocLigne>();
//		String libelle="";
//		listeObjet.clear();
//		if(getListeEntity()!=null){
//		for (Object t : getListeEntity()) {
//			libelle="";
//			ihm=new SWTCreationDocLigne();
//			ihm.setCodeDocument(((TaCreationDoc)t).getCodeDocument()) ;
//			
//			ihm.setCodeTiers(((TaCreationDoc)t).getCodeDocument()) ;
//			for (IDocumentTiers doc : ((TaCreationDoc)t).getListeDoc()) {
//				ihm.setCodeTiers(doc.getTaTiers().getCodeTiers());
//				libelle+=" "+doc.getCodeDocument()+"-";
//			}
//			libelle=((TaCreationDoc)t).getLibelleDocument()+libelle+
//					" ("+((TaCreationDoc)t).getListeDoc().size()+")";
//			ihm.setCodeDocument(ihm.getCodeTiers()) ;
//			ihm.setLibelleDocument(libelle) ;
//			ihm.setAccepte(true);
//			listeObjet.add(ihm);
//			for (IDocumentTiers doc : ((TaCreationDoc)t).getListeDoc()) {				
//				ihmligne=new SWTCreationDocLigne();
//				ihmligne.setCodeDocument(((TaCreationDoc)t).getCodeDocument()) ;
//				ihmligne.setLibelleDocument(libelle) ;
//				ihmligne.setCodeDocumentRecup(doc.getCodeDocument());
//				ihmligne.setLibelleLigne(doc.getLibelleDocument());
//				ihmligne.setAccepte(true);
//				ihm.getList().add(ihmligne);
//				ihmligne=null;
//			}
//		}
//		}
//		return listeObjet;
//	}


	public LinkedList<SWTCreationDocLigne> getListeObjet() {
		return listeObjet;
	}





	public Collection<TaCreationDoc> getListeEntity() {
		return listeEntity;
	}


	public void setListeEntity(Collection<TaCreationDoc> listeEntity) {
		this.listeEntity = listeEntity;
	}


//	public EntityManager getEm() {
//		return em;
//	}
//
//
//	public void setEm(EntityManager em) {
//		this.em = em;
//	}

	
}
