package fr.legrain.gestCom.Module_Document;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;

import fr.legrain.documents.dao.TaAcompte;
import fr.legrain.documents.dao.TaAvoir;
import fr.legrain.lib.data.ModelObject;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class IHMRDocument extends ModelObject {

	
	private Integer id=0;
	private TaAcompte taAcompte;
	private TaAvoir taAvoir;
	private Integer idDocument=0;
	private String codeDocument;
	private String libelleDocument;
	private String typeDocument;
	private BigDecimal affectation;
	private BigDecimal resteARegler;
	
	public IHMRDocument() {
	}




	
	public static IHMRDocument copy(IHMRDocument ihmRAcompte){
		IHMRDocument ihmRAcompteLoc = new IHMRDocument();
		ihmRAcompteLoc.setId(ihmRAcompte.id);
		ihmRAcompteLoc.setTaAcompte(ihmRAcompte.taAcompte);
		ihmRAcompteLoc.setIdDocument(ihmRAcompte.idDocument);
		ihmRAcompteLoc.setCodeDocument(ihmRAcompte.codeDocument);
		ihmRAcompteLoc.setLibelleDocument(ihmRAcompte.libelleDocument);
		ihmRAcompteLoc.setAffectation(ihmRAcompte.affectation);
		ihmRAcompteLoc.setTypeDocument(ihmRAcompte.typeDocument);
		ihmRAcompteLoc.setResteARegler(ihmRAcompte.resteARegler);
		return ihmRAcompteLoc;
	}
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		firePropertyChange("id", this.id, this.id = id);
	}


	public TaAcompte getTaAcompte() {
		return taAcompte;
	}


	public void setTaAcompte(TaAcompte taAcompte) {
		firePropertyChange("taAcompte", this.taAcompte, this.taAcompte = taAcompte);
	}


	public BigDecimal getAffectation() {
		return affectation;
	}


	public void setAffectation(BigDecimal affectation) {
		firePropertyChange("affectation", this.affectation, this.affectation = affectation);
	}




	public String getCodeDocument() {
		return codeDocument;
	}





	public void setCodeDocument(String codeDocument) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = codeDocument);
	}





	public String getLibelleDocument() {
		return libelleDocument;
	}





	public void setLibelleDocument(String libelleDocument) {
		firePropertyChange("libelleDocument", this.libelleDocument, this.libelleDocument = libelleDocument);
	}





	public String getTypeDocument() {
		return typeDocument;
	}





	public void setTypeDocument(String typeDocument) {
		firePropertyChange("typeDocument", this.typeDocument, this.typeDocument = typeDocument);
	}





	public Integer getIdDocument() {
		return idDocument;
	}





	public void setIdDocument(Integer idDocument) {
		firePropertyChange("idDocument", this.idDocument, this.idDocument = idDocument);
	}




	
	public BigDecimal getResteARegler() {
		return resteARegler;
	}





	public void setResteARegler(BigDecimal resteARegler) {
		firePropertyChange("resteARegler", this.resteARegler, this.resteARegler = resteARegler);
	}





	public TaAvoir getTaAvoir() {
		return taAvoir;
	}





	public void setTaAvoir(TaAvoir taAvoir) {
		firePropertyChange("taAvoir", this.taAvoir, this.taAvoir = taAvoir);

	}


	

}