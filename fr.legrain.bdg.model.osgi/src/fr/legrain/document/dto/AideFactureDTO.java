package fr.legrain.document.dto;

// Generated 20 mars 2007 09:51:19 by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

/**
 * IHMEnteteFacture generated by hbm2java
 */
public class AideFactureDTO extends AideDocumentCommunDTO implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7623009515855125495L;
	private BigDecimal regleDocument=new BigDecimal(0);
	private BigDecimal regleCompletDocument=new BigDecimal(0);
	private BigDecimal resteAReglerComplet=new BigDecimal(0);
	private Boolean export=false;
	
	public AideFactureDTO() {
	}



	public AideFactureDTO(Integer idDocument, String codeDocument,
			Date dateDocument, Date dateEchDocument, Date dateLivDocument,
			String libelleDocument, String codeTiers, String nomTiers,
			String codeCompta, String compte, BigDecimal regleDocument,
			BigDecimal netTtcCalc, BigDecimal netHtCalc, String commentaire,
			 BigDecimal regleCompletDocument,
			BigDecimal resteAReglerComplet, Boolean export) {
		super();
		this.setId(idDocument);
		this.setCodeDocument(codeDocument);
		this.setDateDocument(dateDocument);
		this.setDateEchDocument(dateEchDocument);
		this.setDateLivDocument(dateLivDocument);
		this.setLibelleDocument(libelleDocument);
		this.setCodeTiers(codeTiers);
		this.setNomTiers(nomTiers);
		this.setCodeCompta(codeCompta);
		this.setCompte( compte);
		this.setRegleDocument( regleDocument);
		this.setNetTtcCalc( netTtcCalc);
		this.setNetHtCalc( netHtCalc);
		this.setCommentaire( commentaire);
		this.setResteAReglerComplet(regleCompletDocument);
		this.setRegleCompletDocument(regleCompletDocument);
		this.setExport(export);
	}



	public void setIHMEnteteFacture(AideFactureDTO ihmEnteteDocument){
		setId(ihmEnteteDocument.getId());
		setCodeDocument(ihmEnteteDocument.getCodeDocument());
		setDateDocument(ihmEnteteDocument.getDateDocument());
		setDateEchDocument(ihmEnteteDocument.getDateEchDocument());
		setDateLivDocument(ihmEnteteDocument.getDateLivDocument());
		setLibelleDocument(ihmEnteteDocument.getLibelleDocument());
		setCodeTiers(ihmEnteteDocument.getCodeTiers());
		setNomTiers(ihmEnteteDocument.getNomTiers());
		setCodeCompta(ihmEnteteDocument.getCodeCompta());
		setCompte(ihmEnteteDocument.getCompte());
		setRegleDocument(ihmEnteteDocument.getRegleDocument());
		setRegleCompletDocument(ihmEnteteDocument.getRegleCompletDocument());
		setResteAReglerComplet(ihmEnteteDocument.getResteAReglerComplet());
		setExport(ihmEnteteDocument.getExport());
		setCommentaire(ihmEnteteDocument.getCommentaire());
		setNetHtCalc(ihmEnteteDocument.getNetHtCalc());
		setNetTtcCalc(ihmEnteteDocument.getNetTtcCalc());
	}
	
	
	public static AideFactureDTO copy(AideFactureDTO ihmEnteteDocument){
		AideFactureDTO ihmEnteteDocumentLoc = new AideFactureDTO();
		ihmEnteteDocumentLoc.setId(ihmEnteteDocument.getId());
		ihmEnteteDocumentLoc.setCodeDocument(ihmEnteteDocument.getCodeDocument());
		ihmEnteteDocumentLoc.setDateDocument(ihmEnteteDocument.getDateDocument());
		ihmEnteteDocumentLoc.setDateEchDocument(ihmEnteteDocument.getDateEchDocument());
		ihmEnteteDocumentLoc.setDateLivDocument(ihmEnteteDocument.getDateLivDocument());
		ihmEnteteDocumentLoc.setLibelleDocument(ihmEnteteDocument.getLibelleDocument());
		ihmEnteteDocumentLoc.setCodeTiers(ihmEnteteDocument.getCodeTiers());
		ihmEnteteDocumentLoc.setNomTiers(ihmEnteteDocument.getNomTiers());
		ihmEnteteDocumentLoc.setCodeCompta(ihmEnteteDocument.getCodeCompta());
		ihmEnteteDocumentLoc.setCompte(ihmEnteteDocument.getCompte());
		ihmEnteteDocumentLoc.setRegleDocument(ihmEnteteDocument.getRegleDocument());		
		ihmEnteteDocumentLoc.setRegleCompletDocument(ihmEnteteDocument.getRegleCompletDocument());
		ihmEnteteDocumentLoc.setResteAReglerComplet(ihmEnteteDocument.getResteAReglerComplet());
		ihmEnteteDocumentLoc.setExport(ihmEnteteDocument.getExport());		
		ihmEnteteDocumentLoc.setCommentaire(ihmEnteteDocument.getCommentaire());
		ihmEnteteDocumentLoc.setNetHtCalc(ihmEnteteDocument.getNetHtCalc());
		ihmEnteteDocumentLoc.setNetTtcCalc(ihmEnteteDocument.getNetTtcCalc());
		return ihmEnteteDocumentLoc;
	}
	
	
	

	public BigDecimal getRegleDocument() {
		return this.regleDocument;
	}

	public void setRegleDocument(BigDecimal REGLE_FACTURE) {
		firePropertyChange("regleDocument", this.regleDocument, this.regleDocument = REGLE_FACTURE);
	}


	public BigDecimal getRegleCompletDocument() {
		return regleCompletDocument;
	}

	public void setRegleCompletDocument(BigDecimal regleCompletDocument) {
		firePropertyChange("regleCompletDocument", this.regleCompletDocument, this.regleCompletDocument = regleCompletDocument);
	}

	public BigDecimal getResteAReglerComplet() {
		return resteAReglerComplet;
	}

	public void setResteAReglerComplet(BigDecimal resteAReglerComplet) {
		firePropertyChange("resteAReglerComplet", this.resteAReglerComplet, this.resteAReglerComplet = resteAReglerComplet);
	}

	public Boolean getExport() {
		return this.export;
	}

	public void setExport(Boolean EXPORT) {
		firePropertyChange("export", this.export, this.export = EXPORT);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((export == null) ? 0 : export.hashCode());
		result = prime
				* result
				+ ((regleCompletDocument == null) ? 0 : regleCompletDocument
						.hashCode());
		result = prime * result
				+ ((regleDocument == null) ? 0 : regleDocument.hashCode());
		result = prime
				* result
				+ ((resteAReglerComplet == null) ? 0 : resteAReglerComplet
						.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AideFactureDTO other = (AideFactureDTO) obj;
		if (export == null) {
			if (other.export != null)
				return false;
		} else if (!export.equals(other.export))
			return false;
		if (regleCompletDocument == null) {
			if (other.regleCompletDocument != null)
				return false;
		} else if (!regleCompletDocument.equals(other.regleCompletDocument))
			return false;
		if (regleDocument == null) {
			if (other.regleDocument != null)
				return false;
		} else if (!regleDocument.equals(other.regleDocument))
			return false;
		if (resteAReglerComplet == null) {
			if (other.resteAReglerComplet != null)
				return false;
		} else if (!resteAReglerComplet.equals(other.resteAReglerComplet))
			return false;
		return true;
	}

	
}
