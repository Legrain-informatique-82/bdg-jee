package fr.legrain.document.dto;


import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.bdg.model.ModelObject;


public class AideDocumentCommunDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = 8382325814252245663L;
	
	private Integer id=0;
	private String codeDocument;
	private Date dateDocument=new Date();
	private Date dateEchDocument=new Date();
	private Date dateLivDocument=new Date();
	private String libelleDocument;
	private String codeTiers;
	private String nomTiers;
	private String codeCompta;
	private String compte;
	private BigDecimal netTtcCalc=new BigDecimal(0);
	private BigDecimal netHtCalc=new BigDecimal(0);
	private String commentaire;
	
	public AideDocumentCommunDTO() {
	}


	public AideDocumentCommunDTO(Integer idDocument, String codeDocument,
		Date dateDocument, Date dateEchDocument, Date dateLivDocument,
		String libelleDocument, String codeTiers, String nomTiers,
		String codeCompta, String compte, BigDecimal netTtcCalc,
		BigDecimal netHtCalc, String commentaire) {
	super();
	this.id = id;
	this.codeDocument = codeDocument;
	this.dateDocument = dateDocument;
	this.dateEchDocument = dateEchDocument;
	this.dateLivDocument = dateLivDocument;
	this.libelleDocument = libelleDocument;
	this.codeTiers = codeTiers;
	this.nomTiers = nomTiers;
	this.codeCompta = codeCompta;
	this.compte = compte;
	this.netTtcCalc = netTtcCalc;
	this.netHtCalc = netHtCalc;
	this.commentaire = commentaire;
}



	public void setIHMEnteteFacture(AideDocumentCommunDTO ihmEnteteFacture){
		setId(ihmEnteteFacture.id);
		setCodeDocument(ihmEnteteFacture.codeDocument);
		setDateDocument(ihmEnteteFacture.dateDocument);
		setDateEchDocument(ihmEnteteFacture.dateEchDocument);
		setDateLivDocument(ihmEnteteFacture.dateLivDocument);
		setLibelleDocument(ihmEnteteFacture.libelleDocument);
		setCodeTiers(ihmEnteteFacture.codeTiers);
		setNomTiers(ihmEnteteFacture.nomTiers);
		setCodeCompta(ihmEnteteFacture.codeCompta);
		setCompte(ihmEnteteFacture.compte);
		setCommentaire(ihmEnteteFacture.commentaire);
		setNetTtcCalc(ihmEnteteFacture.netTtcCalc);
		setNetHtCalc(ihmEnteteFacture.netHtCalc);
	}
	
	
	public static AideDocumentCommunDTO copy(AideDocumentCommunDTO ihmEnteteFacture){
		AideDocumentCommunDTO ihmEnteteFactureLoc = new AideDocumentCommunDTO();
		ihmEnteteFactureLoc.setId(ihmEnteteFacture.id);
		ihmEnteteFactureLoc.setCodeDocument(ihmEnteteFacture.codeDocument);
		ihmEnteteFactureLoc.setDateDocument(ihmEnteteFacture.dateDocument);
		ihmEnteteFactureLoc.setDateEchDocument(ihmEnteteFacture.dateEchDocument);
		ihmEnteteFactureLoc.setDateLivDocument(ihmEnteteFacture.dateLivDocument);
		ihmEnteteFactureLoc.setLibelleDocument(ihmEnteteFacture.libelleDocument);
		ihmEnteteFactureLoc.setCodeTiers(ihmEnteteFacture.codeTiers);
		ihmEnteteFactureLoc.setNomTiers(ihmEnteteFacture.nomTiers);
		ihmEnteteFactureLoc.setCodeCompta(ihmEnteteFacture.codeCompta);
		ihmEnteteFactureLoc.setCompte(ihmEnteteFacture.compte);
		ihmEnteteFactureLoc.setCommentaire(ihmEnteteFacture.commentaire);
		ihmEnteteFactureLoc.setNetTtcCalc(ihmEnteteFacture.netTtcCalc);
		ihmEnteteFactureLoc.setNetHtCalc(ihmEnteteFacture.netHtCalc);
		return ihmEnteteFactureLoc;
	}
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer ID_FACTURE) {
		firePropertyChange("id", this.id, this.id = ID_FACTURE);
	}

	public String getCodeDocument() {
		return this.codeDocument;
	}

	public void setCodeDocument(String CODE_FACTURE) {
		firePropertyChange("codeDocument", this.codeDocument, this.codeDocument = CODE_FACTURE);
	}

	public Date getDateDocument() {
		return this.dateDocument;
	}

	public void setDateDocument(Date DATE_FACTURE) {
		firePropertyChange("dateDocument", this.dateDocument, this.dateDocument = DATE_FACTURE);
	}

	
	public Date getDateEchDocument() {
		return this.dateEchDocument;
	}

	public void setDateEchDocument(Date DATE_ECH_FACTURE) {
		firePropertyChange("dateEchDocument", this.dateEchDocument, this.dateEchDocument = DATE_ECH_FACTURE);
	}

	public Date getDateLivDocument() {
		return this.dateLivDocument;
	}

	public void setDateLivDocument(Date DATE_LIV_FACTURE) {
		firePropertyChange("dateLivDocument", this.dateLivDocument, this.dateLivDocument = DATE_LIV_FACTURE);
	}

	public String getLibelleDocument() {
		return this.libelleDocument;
	}

	public void setLibelleDocument(String LIBELLE_FACTURE) {
		firePropertyChange("libelleDocument", this.libelleDocument, this.libelleDocument = LIBELLE_FACTURE);
	}

	public String getCodeTiers() {
		return this.codeTiers;
	}

	public void setCodeTiers(String CODE_TIERS) {
		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = CODE_TIERS);
	}

	public String getNomTiers() {
		return this.nomTiers;
	}

	public void setNomTiers(String NOM_TIERS) {
		firePropertyChange("nomTiers", this.nomTiers, this.nomTiers = NOM_TIERS);
	}

	public String getCodeCompta() {
		return this.codeCompta;
	}

	public void setCodeCompta(String CODE_COMPTA) {
		firePropertyChange("codeCompta", this.codeCompta, this.codeCompta = CODE_COMPTA);
	}

	public String getCompte() {
		return this.compte;
	}

	public void setCompte(String COMPTE) {
		firePropertyChange("compte", this.compte, this.compte = COMPTE);
	}


	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String COMMENTAIRE) {
		firePropertyChange("commentaire", this.commentaire, this.commentaire = COMMENTAIRE);
	}

	public BigDecimal getNetTtcCalc() {
		return netTtcCalc;
	}

	public void setNetTtcCalc(BigDecimal netTtcCalc) {
		firePropertyChange("netTtcCalc", this.netTtcCalc, this.netTtcCalc = netTtcCalc);
	}

	public BigDecimal getNetHtCalc() {
		return netHtCalc;
	}

	public void setNetHtCalc(BigDecimal netHtCalc) {
		firePropertyChange("netHtCalc", this.netHtCalc, this.netHtCalc = netHtCalc);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeCompta == null) ? 0 : codeCompta.hashCode());
		result = prime * result
				+ ((codeDocument == null) ? 0 : codeDocument.hashCode());
		result = prime * result
				+ ((codeTiers == null) ? 0 : codeTiers.hashCode());
		result = prime * result
				+ ((commentaire == null) ? 0 : commentaire.hashCode());
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result
				+ ((dateDocument == null) ? 0 : dateDocument.hashCode());
		result = prime * result
				+ ((dateEchDocument == null) ? 0 : dateEchDocument.hashCode());
		result = prime * result
				+ ((dateLivDocument == null) ? 0 : dateLivDocument.hashCode());
		result = prime * result
				+ ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((libelleDocument == null) ? 0 : libelleDocument.hashCode());
		result = prime * result
				+ ((netHtCalc == null) ? 0 : netHtCalc.hashCode());
		result = prime * result
				+ ((netTtcCalc == null) ? 0 : netTtcCalc.hashCode());
		result = prime * result
				+ ((nomTiers == null) ? 0 : nomTiers.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AideDocumentCommunDTO other = (AideDocumentCommunDTO) obj;
		if (codeCompta == null) {
			if (other.codeCompta != null)
				return false;
		} else if (!codeCompta.equals(other.codeCompta))
			return false;
		if (codeDocument == null) {
			if (other.codeDocument != null)
				return false;
		} else if (!codeDocument.equals(other.codeDocument))
			return false;
		if (codeTiers == null) {
			if (other.codeTiers != null)
				return false;
		} else if (!codeTiers.equals(other.codeTiers))
			return false;
		if (commentaire == null) {
			if (other.commentaire != null)
				return false;
		} else if (!commentaire.equals(other.commentaire))
			return false;
		if (compte == null) {
			if (other.compte != null)
				return false;
		} else if (!compte.equals(other.compte))
			return false;
		if (dateDocument == null) {
			if (other.dateDocument != null)
				return false;
		} else if (!dateDocument.equals(other.dateDocument))
			return false;
		if (dateEchDocument == null) {
			if (other.dateEchDocument != null)
				return false;
		} else if (!dateEchDocument.equals(other.dateEchDocument))
			return false;
		if (dateLivDocument == null) {
			if (other.dateLivDocument != null)
				return false;
		} else if (!dateLivDocument.equals(other.dateLivDocument))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libelleDocument == null) {
			if (other.libelleDocument != null)
				return false;
		} else if (!libelleDocument.equals(other.libelleDocument))
			return false;
		if (netHtCalc == null) {
			if (other.netHtCalc != null)
				return false;
		} else if (!netHtCalc.equals(other.netHtCalc))
			return false;
		if (netTtcCalc == null) {
			if (other.netTtcCalc != null)
				return false;
		} else if (!netTtcCalc.equals(other.netTtcCalc))
			return false;
		if (nomTiers == null) {
			if (other.nomTiers != null)
				return false;
		} else if (!nomTiers.equals(other.nomTiers))
			return false;
		return true;
	}

	

}
