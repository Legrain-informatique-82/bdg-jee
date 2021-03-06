package fr.legrain.gestCom.Module_Tiers;


// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

/**
 * SWTEmail generated by hbm2java
 */
public class SWTSupportAbonLogiciel extends SWTSupportAbon {
	
	private Integer idUtilisateur;
	private String nom;
    private String prenom;
    private String tel;
    private String email;
	private int idFamille;
	private String codeFamille;
	private Boolean telechargement;

	public SWTSupportAbonLogiciel() {
	}




	public SWTSupportAbonLogiciel(Integer idUtilisateur, String nom,
			String prenom, String tel, String email, int idFamille,
			String codeFamille, Boolean telechargement) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.idFamille = idFamille;
		this.codeFamille = codeFamille;
		this.telechargement = telechargement;
	}




	public void setSWTEpicea(SWTSupportAbonLogiciel swtEpicea) {
		this.setIdSupportAbon(swtEpicea.getIdSupportAbon());		
		this.setCodeSupportAbon( swtEpicea.getCodeSupportAbon());
		this.setCodeArticle( swtEpicea.getCodeArticle());
		this.setIdArticle( swtEpicea.getIdArticle());
		this.setCodeTiers( swtEpicea.getCodeTiers()) ;
		this.setIdTiers( swtEpicea.getIdTiers()) ;
		this.setCommentaire( swtEpicea.getCommentaire()) ;
		this.setIdCommercial( swtEpicea.getIdCommercial()) ;
		this.setCommercial( swtEpicea.getCommercial()) ;
		this.setDateAcquisition(swtEpicea.getDateAcquisition()) ;
		this.setIdLDocument(swtEpicea.getIdLDocument()) ;
		this.setLibelle( swtEpicea.getLibelle()) ;
		
		this.setEntreprise( swtEpicea.getEntreprise()) ;
		this.setNomTiers( swtEpicea.getNomTiers()) ;
		this.setPrenomTiers( swtEpicea.getPrenomTiers()) ;
		this.setCodeEntite( swtEpicea.getCodeEntite()) ;
		
		this.idUtilisateur = swtEpicea.idUtilisateur;
		this.codeFamille = swtEpicea.codeFamille ;
		this.email = swtEpicea.email ;
		this.idFamille = swtEpicea.idFamille ;
		this.nom = swtEpicea.nom ;
		this.prenom = swtEpicea.prenom ;
		this.tel = swtEpicea.tel ;
		this.telechargement=swtEpicea.telechargement;
	}

	
	public static SWTSupportAbonLogiciel copy(SWTSupportAbonLogiciel swtEpicea) {
		SWTSupportAbonLogiciel swtEpiceaLoc = new SWTSupportAbonLogiciel();
		swtEpiceaLoc.setIdUtilisateur(swtEpicea.getIdUtilisateur());
		swtEpiceaLoc.setNom(swtEpicea.getNom());
		swtEpiceaLoc.setPrenom(swtEpicea.getPrenom());
		swtEpiceaLoc.setTel(swtEpicea.getTel());
		swtEpiceaLoc.setEmail(swtEpicea.getEmail());
		swtEpiceaLoc.setIdFamille(swtEpicea.getIdFamille());
		swtEpiceaLoc.setCodeFamille(swtEpicea.getCodeFamille());
		
		swtEpiceaLoc.setIdSupportAbon(swtEpicea.getIdSupportAbon());
		swtEpiceaLoc.setCodeSupportAbon(swtEpicea.getCodeSupportAbon());
		swtEpiceaLoc.setCodeTiers(swtEpicea.getCodeTiers());
		swtEpiceaLoc.setIdTiers(swtEpicea.getIdTiers());		
		swtEpiceaLoc.setCodeArticle(swtEpicea.getCodeArticle());
		swtEpiceaLoc.setCommentaire(swtEpicea.getCommentaire());
		swtEpiceaLoc.setCommercial(swtEpicea.getCommercial());
		swtEpiceaLoc.setDateAcquisition(swtEpicea.getDateAcquisition());
		swtEpiceaLoc.setIdArticle(swtEpicea.getIdArticle());
		swtEpiceaLoc.setIdCommercial(swtEpicea.getIdCommercial());
		swtEpiceaLoc.setIdLDocument(swtEpicea.getIdLDocument());
		swtEpiceaLoc.setLibelle(swtEpicea.getLibelle());
		
		
		swtEpiceaLoc.setEntreprise( swtEpicea.getEntreprise()) ;
		swtEpiceaLoc.setNomTiers( swtEpicea.getNomTiers()) ;
		swtEpiceaLoc.setPrenomTiers( swtEpicea.getPrenomTiers()) ;
		swtEpiceaLoc.setCodeEntite( swtEpicea.getCodeEntite()) ;
		swtEpiceaLoc.setTelechargement(swtEpicea.getTelechargement());
		return swtEpiceaLoc;
	}



	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		firePropertyChange("idUtilisateur", this.idUtilisateur, this.idUtilisateur = idUtilisateur);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		firePropertyChange("nom", this.nom, this.nom = nom);
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		firePropertyChange("prenom", this.prenom, this.prenom = prenom);
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		firePropertyChange("tel", this.tel, this.tel = tel);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		firePropertyChange("email", this.email, this.email = email);
	}

	public int getIdFamille() {
		return idFamille;
	}

	public void setIdFamille(int idFamille) {
		firePropertyChange("idFamille", this.idFamille, this.idFamille = idFamille);
	}

	public String getCodeFamille() {
		return codeFamille;
	}

	public void setCodeFamille(String codeFamille) {
		firePropertyChange("codeFamille", this.codeFamille, this.codeFamille = codeFamille);
	}

	public Boolean getTelechargement() {
		return telechargement;
	}


	public void setTelechargement(Boolean telechargement) {
		firePropertyChange("telechargement", this.telechargement, this.telechargement = telechargement);
	}



//	public Integer getIdSupportAbon() {
//		return idSupportAbon;
//	}
//
//	public void setIdSupportAbon(Integer idSupportAbon) {
//		firePropertyChange("idSupportAbon", this.idSupportAbon, this.idSupportAbon = idSupportAbon);
//	}
//
//	public String getCodeSupportAbon() {
//		return codeSupportAbon;
//	}
//
//	public void setCodeSupportAbon(String codeSupportAbon) {
//		firePropertyChange("codeSupportAbon", this.codeSupportAbon, this.codeSupportAbon = codeSupportAbon);
//	}
//
//	public Date getDateAcquisition() {
//		return dateAcquisition;
//	}
//
//	public void setDateAcquisition(Date dateAcquisition) {
//		firePropertyChange("dateAcquisition", this.dateAcquisition, this.dateAcquisition = dateAcquisition);
//	}
//
//	public String getCodeArticle() {
//		return codeArticle;
//	}
//
//	public void setCodeArticle(String codeArticle) {
//		firePropertyChange("codeArticle", this.codeArticle, this.codeArticle = codeArticle);
//	}
//
//	public Integer getIdArticle() {
//		return idArticle;
//	}
//
//	public void setIdArticle(Integer idArticle) {
//		firePropertyChange("idArticle", this.idArticle, this.idArticle = idArticle);
//	}
//
//	public String getCodeTiers() {
//		return codeTiers;
//	}
//
//	public void setCodeTiers(String codeTiers) {
//		firePropertyChange("codeTiers", this.codeTiers, this.codeTiers = codeTiers);
//	}
//
//	public Integer getIdTiers() {
//		return idTiers;
//	}
//
//	public void setIdTiers(Integer idTiers) {
//		firePropertyChange("idTiers", this.idTiers, this.idTiers = idTiers);
//	}
//
//	public String getCommentaire() {
//		return commentaire;
//	}
//
//	public void setCommentaire(String commentaire) {
//		firePropertyChange("commentaire", this.commentaire, this.commentaire = commentaire);
//	}
//
//	public String getLibelle() {
//		return libelle;
//	}
//
//	public void setLibelle(String libelle) {
//		firePropertyChange("libelle", this.libelle, this.libelle = libelle);
//	}
//
//	public String getCommercial() {
//		return commercial;
//	}
//
//	public void setCommercial(String commercial) {
//		firePropertyChange("commercial", this.commercial, this.commercial = commercial);
//	}
//
//	public Integer getIdCommercial() {
//		return idCommercial;
//	}
//
//	public void setIdCommercial(Integer idCommercial) {
//		firePropertyChange("idCommercial", this.idCommercial, this.idCommercial = idCommercial);
//	}
//
//	public Integer getIdLDocument() {
//		return idLDocument;
//	}
//
//	public void setIdLDocument(Integer idLDocument) {
//		firePropertyChange("idLDocument", this.idLDocument, this.idLDocument = idLDocument);
//	}
//
//	public Date getDateDebAbon() {
//		return dateDebAbon;
//	}
//
//
//
//	public void setDateDebAbon(Date dateDebAbon) {
//		firePropertyChange("dateDebAbon", this.dateDebAbon, this.dateDebAbon = dateDebAbon);
//	}
//
//
//
//	public Date getDateFinAbon() {
//		return dateFinAbon;
//	}
//
//
//
//	public void setDateFinAbon(Date dateFinAbon) {
//		firePropertyChange("dateFinAbon", this.dateFinAbon, this.dateFinAbon = dateFinAbon);
//	}






	

	
	
}
