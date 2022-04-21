package fr.legrain.bdg.rest.model;

public class ParamConfirmationCommandeBoutique {
	private Integer id;
	
	private Integer idTiers;
	private String codeTiers;
	private Integer idTypePaiementPrevu;
	private Integer idTypeLivraisonPrevu;
	private Integer idcompteEspaceClient;
	private String emailCompteEspaceClient;
	private String codePanier;
	private String codeCommande;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdTiers() {
		return idTiers;
	}
	public void setIdTiers(Integer idTiers) {
		this.idTiers = idTiers;
	}
	public String getCodeTiers() {
		return codeTiers;
	}
	public void setCodeTiers(String codeTiers) {
		this.codeTiers = codeTiers;
	}

	public Integer getIdcompteEspaceClient() {
		return idcompteEspaceClient;
	}
	public void setIdcompteEspaceClient(Integer idcompteEspaceClient) {
		this.idcompteEspaceClient = idcompteEspaceClient;
	}
	public String getEmailCompteEspaceClient() {
		return emailCompteEspaceClient;
	}
	public void setEmailCompteEspaceClient(String emailCompteEspaceClient) {
		this.emailCompteEspaceClient = emailCompteEspaceClient;
	}
	public String getCodePanier() {
		return codePanier;
	}
	public void setCodePanier(String codePanier) {
		this.codePanier = codePanier;
	}
	public String getCodeCommande() {
		return codeCommande;
	}
	public void setCodeCommande(String codeCommande) {
		this.codeCommande = codeCommande;
	}
	
	public Integer getIdTypePaiementPrevu() {
		return idTypePaiementPrevu;
	}
	public void setIdTypePaiementPrevu(Integer idTypePaiementPrevu) {
		this.idTypePaiementPrevu = idTypePaiementPrevu;
	}
	public Integer getIdTypeLivraisonPrevu() {
		return idTypeLivraisonPrevu;
	}
	public void setIdTypeLivraisonPrevu(Integer idTypeLivraisonPrevu) {
		this.idTypeLivraisonPrevu = idTypeLivraisonPrevu;
	}
	
}
