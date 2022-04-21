package fr.legrain.article.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaFamille;
import fr.legrain.article.model.TaPrix;
import fr.legrain.article.model.TaRapportUnite;
import fr.legrain.article.model.TaTTva;
import fr.legrain.article.model.TaTva;
import fr.legrain.article.model.TaUnite;
import fr.legrain.article.titretransport.model.TaTitreTransport;
import fr.legrain.bdg.model.ModelObject;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * SWTArticle generated by hbm2java
 */
public class TaTArticleDTO extends ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = -589807224221680555L;
	
	private Integer id;
	private String codeArticle;
	private String libellecArticle;
	private String libellelArticle;
	private String numcptArticle;
	private String diversArticle;
	private Integer idTTva;
	private BigDecimal stockMinArticle;
	private Integer idFamille;
	private Integer idPrix;
	private BigDecimal prixPrix;
	private BigDecimal prixttcPrix;
	private BigDecimal longueur;
	private BigDecimal largeur;
	private BigDecimal hauteur;
	private BigDecimal poids;
	private Integer idUnite;
	private String codeUnite;
	private String codeFamille;
	private String libcFamille;
	private Integer idTva;
	private String codeTva;
	private BigDecimal tauxTva;
	private String numcptTva;
	private String codeTTva;
	private String libTTva;
	private Integer idRapportUnite;
	private Integer idUnite2;
	private String codeUnite2;
	private BigDecimal rapport;
	private Integer nbDecimal;
	private Boolean actif;
	private Boolean sens;
	private String codeTitreTransport;
	private BigDecimal qteTitreTransport;
	
	private Integer versionObj;
	
	
	public Integer getVersionObj() {
		return versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	private TaUniteDTO swtUnite = new TaUniteDTO();
	private TaPrixDTO swtPrix = new TaPrixDTO();
	private TaRefPrixDTO swtRefPrix = new TaRefPrixDTO();
	private TaRapportUniteDTO swtRapportUnite = new TaRapportUniteDTO();
	private ArrayList<TaPrixDTO> listePrix = new ArrayList<TaPrixDTO>();
	private ArrayList<TaRapportUniteDTO> listeRapportUnite = new ArrayList<TaRapportUniteDTO>();
	
	public TaTArticleDTO() {
	}
	
	public TaTArticleDTO(Integer ID_ARTICLE, String CODE_ARTICLE,
			String LIBELLEC_ARTICLE, String LIBELLEL_ARTICLE,
			String NUMCPT_ARTICLE, String DIVERS_ARTICLE,
			String COMMENTAIRE_ARTICLE, Integer ID_T_TVA,
			BigDecimal STOCK_MIN_ARTICLE, Integer ID_FAMILLE, Integer ID_PRIX,
			BigDecimal PRIX_PRIX,BigDecimal PRIXTTC_PRIX, Integer ID_UNITE, String CODE_UNITE,
			String CODE_FAMILLE, String LIBC_FAMILLE, Integer ID_TVA,
			String CODE_TVA, BigDecimal TAUX_TVA, String NUMCPT_TVA,
			String CODE_T_TVA, String LIB_T_TVA,TaUniteDTO swtUnite,TaPrixDTO swtPrix,
			TaRefPrixDTO swtRefPrix,ArrayList<TaPrixDTO> listePrix,
			ArrayList<TaRapportUniteDTO> listeRapportUnite,Integer idRapportUnite,
			Integer idUnite2,BigDecimal Rapport,Integer nbDecimal, Boolean actif) {
		this.id = ID_ARTICLE;
		this.codeArticle = CODE_ARTICLE;
		this.libellecArticle = LIBELLEC_ARTICLE;
		this.libellelArticle = LIBELLEL_ARTICLE;
		this.numcptArticle = NUMCPT_ARTICLE;
		this.diversArticle = DIVERS_ARTICLE;
//		this.commentaireArticle = COMMENTAIRE_ARTICLE;
		this.idTTva = ID_T_TVA;
		this.stockMinArticle = STOCK_MIN_ARTICLE;
		this.idFamille = ID_FAMILLE;
		this.idPrix = ID_PRIX;
		this.prixPrix = PRIX_PRIX;
		this.prixttcPrix = PRIXTTC_PRIX;
		this.idUnite = ID_UNITE;
		this.codeUnite = CODE_UNITE;
		this.codeFamille = CODE_FAMILLE;
		this.libcFamille = LIBC_FAMILLE;
		this.idTva = ID_TVA;
		this.codeTva = CODE_TVA;
		this.tauxTva = TAUX_TVA;
		this.numcptTva = NUMCPT_TVA;
		this.codeTTva = CODE_T_TVA;
		this.libTTva = LIB_T_TVA;
		this.swtUnite = swtUnite;
		this.swtPrix = swtPrix;
		this.swtRefPrix = swtRefPrix;
		this.listePrix = listePrix;
		this.listeRapportUnite = listeRapportUnite;
		this.idRapportUnite =idRapportUnite;
		this.idUnite2=idUnite2;
		this.rapport = Rapport;
		this.nbDecimal = nbDecimal;
		this.actif = actif;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer ID_ARTICLE) {
		firePropertyChange("id", this.id, this.id = ID_ARTICLE);
	}

	@NotNull
	@Size(min=1, max=20)
	@LgrHibernateValidated(champBd = "code_article",table = "ta_article", champEntite="codeArticle" , clazz = TaTArticleDTO.class)
	public String getCodeArticle() {
		return this.codeArticle;
	}

	public void setCodeArticle(String CODE_ARTICLE) {
		firePropertyChange("codeArticle", this.codeArticle, this.codeArticle = CODE_ARTICLE);
	}

	@LgrHibernateValidated(champBd = "libellec_article",table = "ta_article", champEntite="libellecArticle" , clazz = TaTArticleDTO.class)
	public String getLibellecArticle() {
		return this.libellecArticle;
	}

	public void setLibellecArticle(String LIBELLEC_ARTICLE) {
		firePropertyChange("libellecArticle", this.libellecArticle, this.libellecArticle = LIBELLEC_ARTICLE);
	}

	@LgrHibernateValidated(champBd = "libellel_article",table = "ta_article", champEntite="libellelArticle" , clazz = TaTArticleDTO.class)
	public String getLibellelArticle() {
		return this.libellelArticle;
	}

	public void setLibellelArticle(String LIBELLEL_ARTICLE) {
		firePropertyChange("libellelArticle", this.libellelArticle, this.libellelArticle = LIBELLEL_ARTICLE);
	}

	@Size(min=0, max=8)
	@LgrHibernateValidated(champBd = "numcpt_article",table = "ta_article", champEntite="numcptArticle" , clazz = TaTArticleDTO.class)
	public String getNumcptArticle() {
		return this.numcptArticle;
	}

	public void setNumcptArticle(String NUMCPT_ARTICLE) {
		firePropertyChange("numcptArticle", this.numcptArticle, this.numcptArticle = NUMCPT_ARTICLE);
	}

	public String getDiversArticle() {
		return this.diversArticle;
	}

	public void setDiversArticle(String DIVERS_ARTICLE) {
		firePropertyChange("diversArticle", this.diversArticle, this.diversArticle = DIVERS_ARTICLE);
	}

//	public String getCommentaireArticle() {
//		return this.commentaireArticle;
//	}
//
//	public void setCommentaireArticle(String COMMENTAIRE_ARTICLE) {
//		firePropertyChange("commentaireArticle", this.commentaireArticle, this.commentaireArticle = COMMENTAIRE_ARTICLE);
//	}

	public Integer getIdTTva() {
		return this.idTTva;
	}

	public void setIdTTva(Integer ID_T_TVA) {
		firePropertyChange("idTTva", this.idTTva, this.idTTva = ID_T_TVA);
	}

	@LgrHibernateValidated(champBd = "stock_min_article",table = "ta_article", champEntite="stockMinArticle" , clazz = TaTArticleDTO.class)
	public BigDecimal getStockMinArticle() {
		return this.stockMinArticle;
	}

	public void setStockMinArticle(BigDecimal STOCK_MIN_ARTICLE) {
		firePropertyChange("stockMinArticle", this.stockMinArticle, this.stockMinArticle = STOCK_MIN_ARTICLE);
	}

	public Integer getIdFamille() {
		return this.idFamille;
	}

	public void setIdFamille(Integer ID_FAMILLE) {
		firePropertyChange("idFamille", this.idFamille, this.idFamille = ID_FAMILLE);
	}

	public Integer getIdPrix() {
		return this.idPrix;
	}

	public void setIdPrix(Integer ID_PRIX) {
		firePropertyChange("idPrix", this.idPrix, this.idPrix = ID_PRIX);
	}

	@LgrHibernateValidated(champBd = "prix_prix",table = "ta_prix", champEntite="prixPrix" , clazz = TaTArticleDTO.class)
	public BigDecimal getPrixPrix() {
		return this.prixPrix;
	}

	public void setPrixPrix(BigDecimal PRIX_PRIX) {
		firePropertyChange("prixPrix", this.prixPrix, this.prixPrix = PRIX_PRIX);
	}

	public Integer getIdUnite() {
		return this.idUnite;
	}

	public void setIdUnite(Integer ID_UNITE) {
		firePropertyChange("idUnite", this.idUnite, this.idUnite = ID_UNITE);
	}

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_unite",table = "ta_unite", champEntite="codeUnite" , clazz = TaTArticleDTO.class)
	public String getCodeUnite() {
		return this.codeUnite;
	}

	public void setCodeUnite(String CODE_UNITE) {
		firePropertyChange("codeUnite", this.codeUnite, this.codeUnite = CODE_UNITE);
	}

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_famille",table = "ta_famille", champEntite="codeFamille" , clazz = TaTArticleDTO.class)
	public String getCodeFamille() {
		return this.codeFamille;
	}

	public void setCodeFamille(String CODE_FAMILLE) {
		firePropertyChange("codeFamille", this.codeFamille, this.codeFamille = CODE_FAMILLE);
	}

	@LgrHibernateValidated(champBd = "libc_Famille",table = "ta_article", champEntite="libcFamille" , clazz = TaTArticleDTO.class)
	public String getLibcFamille() {
		return this.libcFamille;
	}

	public void setLibcFamille(String LIBC_FAMILLE) {
		firePropertyChange("libcFamille", this.libcFamille, this.libcFamille = LIBC_FAMILLE);
	}

	public Integer getIdTva() {
		return this.idTva;
	}

	public void setIdTva(Integer ID_TVA) {
		firePropertyChange("idTva", this.idTva, this.idTva = ID_TVA);
	}

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_tva",table = "ta_tva", champEntite="codeTva" , clazz = TaTArticleDTO.class)
	public String getCodeTva() {
		return this.codeTva;
	}

	public void setCodeTva(String CODE_TVA) {
		firePropertyChange("codeTva", this.codeTva, this.codeTva = CODE_TVA);
	}

	@LgrHibernateValidated(champBd = "taux_tva",table = "ta_tva", champEntite="tauxTva" , clazz = TaTArticleDTO.class)
	public BigDecimal getTauxTva() {
		return this.tauxTva;
	}

	public void setTauxTva(BigDecimal TAUX_TVA) {
		firePropertyChange("tauxTva", this.tauxTva, this.tauxTva = TAUX_TVA);
	}

	@Size(min=0, max=8)
	@LgrHibernateValidated(champBd = "numcpt_tva",table = "ta_tva", champEntite="numcptTva" , clazz = TaTArticleDTO.class)
	public String getNumcptTva() {
		return this.numcptTva;
	}

	public void setNumcptTva(String NUMCPT_TVA) {
		firePropertyChange("numcptTva", this.numcptTva, this.numcptTva = NUMCPT_TVA);
	}

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_t_tva",table = "ta_t_tva", champEntite="codeTTva" , clazz = TaTArticleDTO.class)
	public String getCodeTTva() {
		return this.codeTTva;
	}

	public void setCodeTTva(String CODE_T_TVA) {
		firePropertyChange("codeTTva", this.codeTTva, this.codeTTva = CODE_T_TVA);
	}

	@LgrHibernateValidated(champBd = "lib_t_tva",table = "ta_t_tva", champEntite="libTTva" , clazz = TaTArticleDTO.class)
	public String getLibTTva() {
		return this.libTTva;
	}

	public void setLibTTva(String LIB_T_TVA) {
		firePropertyChange("libTTva", this.libTTva, this.libTTva = LIB_T_TVA);
	}
	
	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_titre_transport",table = "ta_titre_transport", champEntite="codeTitreTransport" , clazz = TaTArticleDTO.class)
	public String getCodeTitreTransport() {
		return this.codeTitreTransport;
	}

	public void setCodeTitreTransport(String titreTransport) {
		firePropertyChange("titreTransport", this.codeTitreTransport, this.codeTitreTransport = titreTransport);
	}
	
	@LgrHibernateValidated(champBd = "qte_min_titre_transport",table = "ta_titre_transport", champEntite="qteTitreTransport" , clazz = TaTArticleDTO.class)
	public BigDecimal getQteTitreTransport() {
		return this.qteTitreTransport;
	}

	public void setQteTitreTransport(BigDecimal qteTitreTransport) {
		firePropertyChange("qteTitreTransport", this.qteTitreTransport, this.qteTitreTransport = qteTitreTransport);
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaTArticleDTO))
			return false;
		TaTArticleDTO castOther = (TaTArticleDTO) other;

		return ((this.getId() == castOther.getId()) || (this
				.getId() != null
				&& castOther.getId() != null && this.getId()
				.equals(castOther.getId())))
				&& ((this.getCodeArticle() == castOther.getCodeArticle()) || (this
						.getCodeArticle() != null
						&& castOther.getCodeArticle() != null && this
						.getCodeArticle().equals(castOther.getCodeArticle())))
				&& ((this.getLibellecArticle() == castOther
						.getLibellecArticle()) || (this.getLibellecArticle() != null
						&& castOther.getLibellecArticle() != null && this
						.getLibellecArticle().equals(
								castOther.getLibellecArticle())))
				&& ((this.getLibellelArticle() == castOther
						.getLibellelArticle()) || (this.getLibellelArticle() != null
						&& castOther.getLibellelArticle() != null && this
						.getLibellelArticle().equals(
								castOther.getLibellelArticle())))
				&& ((this.getNumcptArticle() == castOther.getNumcptArticle()) || (this
						.getNumcptArticle() != null
						&& castOther.getNumcptArticle() != null && this
						.getNumcptArticle().equals(
								castOther.getNumcptArticle())))
				&& ((this.getDiversArticle() == castOther.getDiversArticle()) || (this
						.getDiversArticle() != null
						&& castOther.getDiversArticle() != null && this
						.getDiversArticle().equals(
								castOther.getDiversArticle())))
				&& ((this.getIdTTva() == castOther.getIdTTva()) || (this
						.getIdTTva() != null
						&& castOther.getIdTTva() != null && this
						.getIdTTva().equals(castOther.getIdTTva())))
				&& ((this.getStockMinArticle() == castOther
						.getStockMinArticle()) || (this
						.getStockMinArticle() != null
						&& castOther.getStockMinArticle() != null && this
						.getStockMinArticle().equals(
								castOther.getStockMinArticle())))
				&& ((this.getIdFamille() == castOther.getIdFamille()) || (this
						.getIdFamille() != null
						&& castOther.getIdFamille() != null && this
						.getIdFamille().equals(castOther.getIdFamille())))
				&& ((this.getIdPrix() == castOther.getIdPrix()) || (this
						.getIdPrix() != null
						&& castOther.getIdPrix() != null && this.getIdPrix()
						.equals(castOther.getIdPrix())))
				&& ((this.getPrixPrix() == castOther.getPrixPrix()) || (this
						.getPrixPrix() != null
						&& castOther.getPrixPrix() != null && this
						.getPrixPrix().equals(castOther.getPrixPrix())))
				&& ((this.getPrixttcPrix() == castOther.getPrixttcPrix()) || (this
						.getPrixttcPrix() != null
						&& castOther.getPrixttcPrix() != null && this
						.getPrixttcPrix().equals(castOther.getPrixttcPrix())))
				&& ((this.getIdUnite() == castOther.getIdUnite()) || (this
						.getIdUnite() != null
						&& castOther.getIdUnite() != null && this
						.getIdUnite().equals(castOther.getIdUnite())))
				&& ((this.getCodeUnite() == castOther.getCodeUnite()) || (this
						.getCodeUnite() != null
						&& castOther.getCodeUnite() != null && this
						.getCodeUnite().equals(castOther.getCodeUnite())))
				&& ((this.getCodeFamille() == castOther.getCodeFamille()) || (this
						.getCodeFamille() != null
						&& castOther.getCodeFamille() != null && this
						.getCodeFamille().equals(castOther.getCodeFamille())))
				&& ((this.getLibcFamille() == castOther.getLibcFamille()) || (this
						.getLibcFamille() != null
						&& castOther.getLibcFamille() != null && this
						.getLibcFamille().equals(castOther.getLibcFamille())))
				&& ((this.getIdTva() == castOther.getIdTva()) || (this
						.getIdTva() != null
						&& castOther.getIdTva() != null && this.getIdTva()
						.equals(castOther.getIdTva())))
				&& ((this.getCodeTva() == castOther.getCodeTva()) || (this
						.getCodeTva() != null
						&& castOther.getCodeTva() != null && this
						.getCodeTva().equals(castOther.getCodeTva())))
				&& ((this.getTauxTva() == castOther.getTauxTva()) || (this
						.getTauxTva() != null
						&& castOther.getTauxTva() != null && this
						.getTauxTva().equals(castOther.getTauxTva())))
				&& ((this.getNumcptTva() == castOther.getNumcptTva()) || (this
						.getNumcptTva() != null
						&& castOther.getNumcptTva() != null && this
						.getNumcptTva().equals(castOther.getNumcptTva())))
				&& ((this.getCodeTTva() == castOther.getCodeTTva()) || (this
						.getCodeTTva() != null
						&& castOther.getCodeTTva() != null && this
						.getCodeTTva().equals(castOther.getCodeTTva())))						
				&& ((this.getIdRapportUnite() == castOther.getIdRapportUnite()) || (this
						.getIdRapportUnite() != null
						&& castOther.getIdRapportUnite() != null && this
						.getIdRapportUnite().equals(castOther.getIdRapportUnite())))
				&& ((this.getIdUnite2() == castOther.getIdUnite2()) || (this
						.getIdUnite2() != null
						&& castOther.getIdUnite2() != null && this
						.getIdUnite2().equals(castOther.getIdUnite2())))
				&& ((this.getRapport() == castOther.getRapport()) || (this
						.getRapport() != null
						&& castOther.getRapport() != null && this
						.getRapport().equals(castOther.getRapport())))
				&& ((this.getNbDecimal() == castOther.getNbDecimal()) || (this
						.getNbDecimal() != null
						&& castOther.getNbDecimal() != null && this
						.getNbDecimal().equals(castOther.getNbDecimal())))						
				&& ((this.getLibTTva() == castOther.getLibTTva()) || (this
						.getLibTTva() != null
						&& castOther.getLibTTva() != null && this
						.getLibTTva().equals(castOther.getLibTTva())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getId() == null ? 0 : this.getId()
						.hashCode());
		result = 37
				* result
				+ (getCodeArticle() == null ? 0 : this.getCodeArticle()
						.hashCode());
		result = 37
				* result
				+ (getLibellecArticle() == null ? 0 : this
						.getLibellecArticle().hashCode());
		result = 37
				* result
				+ (getLibellelArticle() == null ? 0 : this
						.getLibellelArticle().hashCode());
		result = 37
				* result
				+ (getNumcptArticle() == null ? 0 : this.getNumcptArticle()
						.hashCode());
		result = 37
				* result
				+ (getDiversArticle() == null ? 0 : this.getDiversArticle()
						.hashCode());
		result = 37
		* result
		+ (getIdRapportUnite() == null ? 0 : this.getIdRapportUnite()
				.hashCode());
		result = 37
		* result
		+ (getIdUnite2() == null ? 0 : this.getIdUnite2()
				.hashCode());
		result = 37
		* result
		+ (getRapport() == null ? 0 : this.getRapport()
				.hashCode());
		result = 37
		* result
		+ (getNbDecimal() == null ? 0 : this.getNbDecimal()
				.hashCode());
		
		result = 37 * result
				+ (getIdTTva() == null ? 0 : this.getIdTTva().hashCode());
		result = 37
				* result
				+ (getStockMinArticle() == null ? 0 : this
						.getStockMinArticle().hashCode());
		result = 37
				* result
				+ (getIdFamille() == null ? 0 : this.getIdFamille()
						.hashCode());
		result = 37 * result
				+ (getIdPrix() == null ? 0 : this.getIdPrix().hashCode());
		result = 37 * result
				+ (getPrixPrix() == null ? 0 : this.getPrixPrix().hashCode());
		result = 37 * result
		+ (getPrixttcPrix() == null ? 0 : this.getPrixttcPrix().hashCode());
		result = 37 * result
				+ (getIdUnite() == null ? 0 : this.getIdUnite().hashCode());
		result = 37
				* result
				+ (getCodeUnite() == null ? 0 : this.getCodeUnite()
						.hashCode());
		result = 37
				* result
				+ (getCodeFamille() == null ? 0 : this.getCodeFamille()
						.hashCode());
		result = 37
				* result
				+ (getLibcFamille() == null ? 0 : this.getLibcFamille()
						.hashCode());
		result = 37 * result
				+ (getIdTva() == null ? 0 : this.getIdTva().hashCode());
		result = 37 * result
				+ (getCodeTva() == null ? 0 : this.getCodeTva().hashCode());
		result = 37 * result
				+ (getTauxTva() == null ? 0 : this.getTauxTva().hashCode());
		result = 37
				* result
				+ (getNumcptTva() == null ? 0 : this.getNumcptTva()
						.hashCode());
		result = 37
				* result
				+ (getCodeTTva() == null ? 0 : this.getCodeTTva()
						.hashCode());
		result = 37 * result
				+ (getLibTTva() == null ? 0 : this.getLibTTva().hashCode());
		return result;
	}
	
	public static TaTArticleDTO copy(TaTArticleDTO swtArticle){
		return copy(swtArticle, null);
	}
	
	public static TaTArticleDTO copy(TaTArticleDTO swtArticle, TaTArticleDTO articleDest){
		TaTArticleDTO swtArticleLoc = null;
		if(articleDest==null)
			swtArticleLoc = new TaTArticleDTO();
		else
			swtArticleLoc = articleDest;
		swtArticleLoc.setCodeArticle(swtArticle.codeArticle);
		swtArticleLoc.setCodeFamille(swtArticle.codeFamille);
		swtArticleLoc.setCodeTTva(swtArticle.codeTTva);
		swtArticleLoc.setCodeTva(swtArticle.codeTva);
		swtArticleLoc.setCodeUnite(swtArticle.codeUnite);
//		swtArticleLoc.setCommentaireArticle(swtArticle.commentaireArticle);
		swtArticleLoc.setDiversArticle(swtArticle.diversArticle);
		swtArticleLoc.setId(swtArticle.id);
		swtArticleLoc.setIdFamille(swtArticle.idFamille);
		swtArticleLoc.setIdPrix(swtArticle.idPrix);
		swtArticleLoc.setIdTTva(swtArticle.idTTva);
		swtArticleLoc.setIdTva(swtArticle.idTva);
		swtArticleLoc.setIdUnite(swtArticle.idUnite);
		swtArticleLoc.setLibTTva(swtArticle.libTTva);
		swtArticleLoc.setLibcFamille(swtArticle.libcFamille);
		swtArticleLoc.setLibellecArticle(swtArticle.libellecArticle);
		swtArticleLoc.setLibellelArticle(swtArticle.libellelArticle);
		swtArticleLoc.setNumcptArticle(swtArticle.numcptArticle);
		swtArticleLoc.setNumcptTva(swtArticle.numcptTva);
		swtArticleLoc.setPrixPrix(swtArticle.prixPrix);
		swtArticleLoc.setPrixttcPrix(swtArticle.prixttcPrix);
		swtArticleLoc.setStockMinArticle(swtArticle.stockMinArticle);
		swtArticleLoc.setTauxTva(swtArticle.tauxTva);
		swtArticleLoc.setSwtPrix(swtArticle.swtPrix);
		swtArticleLoc.setSwtRefPrix(swtArticle.swtRefPrix);
		swtArticleLoc.setSwtUnite(swtArticle.swtUnite);
		swtArticleLoc.setListePrix(swtArticle.listePrix);
		swtArticleLoc.setListeRapportUnite(swtArticle.listeRapportUnite);
		swtArticleLoc.setSwtRapportUnite(swtArticle.swtRapportUnite);
		swtArticleLoc.setIdRapportUnite(swtArticle.idRapportUnite);
		swtArticleLoc.setCodeUnite2(swtArticle.codeUnite2);
		swtArticleLoc.setIdUnite2(swtArticle.idUnite2);
		swtArticleLoc.setRapport(swtArticle.rapport);
		swtArticleLoc.setNbDecimal(swtArticle.nbDecimal);
		swtArticleLoc.setActif(swtArticle.actif);
		swtArticleLoc.setSens(swtArticle.sens);
		swtArticleLoc.setCodeTitreTransport(swtArticle.codeTitreTransport);
		swtArticleLoc.setQteTitreTransport(swtArticle.qteTitreTransport);
		return swtArticleLoc;
	}

	public TaPrixDTO getSwtPrix() {
		return swtPrix;
	}

	public void setSwtPrix(TaPrixDTO swtPrix) {
		this.swtPrix = swtPrix;
	}

	public TaRefPrixDTO getSwtRefPrix() {
		return swtRefPrix;
	}

	public void setSwtRefPrix(TaRefPrixDTO swtRefPrix) {
		this.swtRefPrix = swtRefPrix;
	}

	public TaUniteDTO getSwtUnite() {
		return swtUnite;
	}

	public void setSwtUnite(TaUniteDTO swtUnite) {
		this.swtUnite = swtUnite;
	}

	public ArrayList<TaPrixDTO> getListePrix() {
		return listePrix;
	}

	public void setListePrix(ArrayList<TaPrixDTO> listePrix) {
		this.listePrix = listePrix;
	}

	@LgrHibernateValidated(champBd = "prixttc_prix",table = "ta_prix", champEntite="prixttcPrix" , clazz = TaTArticleDTO.class)
	public BigDecimal getPrixttcPrix() {
		return prixttcPrix;
	}

	public void setPrixttcPrix(BigDecimal prixttcPrix) {
		firePropertyChange("prixttcPrix", this.prixttcPrix, this.prixttcPrix = prixttcPrix);
	}

	public Integer getIdRapportUnite() {
		return idRapportUnite;
	}

	public void setIdRapportUnite(Integer idRapportUnite) {
		firePropertyChange("idRapportUnite", this.idRapportUnite, this.idRapportUnite = idRapportUnite);
	}

	public Integer getIdUnite2() {
		return idUnite2;
	}

	public void setIdUnite2(Integer idUnite2) {
		firePropertyChange("idUnite2", this.idUnite2, this.idUnite2 = idUnite2);
	}

	@LgrHibernateValidated(champBd = "rapport",table = "ta_rapport_unite", champEntite="rapport" , clazz = TaTArticleDTO.class)
	public BigDecimal getRapport() {
		return rapport;
	}

	public void setRapport(BigDecimal rapport) {
		firePropertyChange("Rapport", this.rapport, this.rapport = rapport);
	}

	@LgrHibernateValidated(champBd = "nb_decimal",table = "ta_rapport_unite", champEntite="nbDecimal" , clazz = TaTArticleDTO.class)
	public Integer getNbDecimal() {
		return nbDecimal;
	}

	public void setNbDecimal(Integer nbDecimal) {
		firePropertyChange("nbDecimal", this.nbDecimal, this.nbDecimal = nbDecimal);
	}

	@Size(min=0, max=20)
	@LgrHibernateValidated(champBd = "code_unite",table = "ta_unite", champEntite="codeUnite" , clazz = TaTArticleDTO.class)
	public String getCodeUnite2() {
		return codeUnite2;
	}

	public void setCodeUnite2(String codeUnite2) {
		firePropertyChange("codeUnite2", this.codeUnite2, this.codeUnite2 = codeUnite2);
	}

	
	public TaRapportUniteDTO getSwtRapportUnite() {
		return swtRapportUnite;
	}

	public void setSwtRapportUnite(TaRapportUniteDTO swtRapportUnite) {
		this.swtRapportUnite = swtRapportUnite;
	}

	public ArrayList<TaRapportUniteDTO> getListeRapportUnite() {
		return listeRapportUnite;
	}

	public void setListeRapportUnite(ArrayList<TaRapportUniteDTO> listeRapportUnite) {
		this.listeRapportUnite = listeRapportUnite;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		firePropertyChange("actif", this.actif, this.actif = actif);
	}

	public Boolean getSens() {
		return sens;
	}

	public void setSens(Boolean sens) {
		firePropertyChange("sens", this.sens, this.sens = sens);
	}

	@LgrHibernateValidated(champBd = "longueur",table = "ta_article", champEntite="longueur" , clazz = TaTArticleDTO.class)
	public BigDecimal getLongueur() {
		return longueur;
	}

	public void setLongueur(BigDecimal longueur) {
		firePropertyChange("longueur", this.longueur, this.longueur = longueur);
	}

	@LgrHibernateValidated(champBd = "largeur",table = "ta_article", champEntite="largeur" , clazz = TaTArticleDTO.class)
	public BigDecimal getLargeur() {
		return largeur;
	}

	public void setLargeur(BigDecimal largeur) {
		firePropertyChange("largeur", this.largeur, this.largeur = largeur);
	}

	@LgrHibernateValidated(champBd = "hauteur",table = "ta_article", champEntite="hauteur" , clazz = TaTArticleDTO.class)
	public BigDecimal getHauteur() {
		return hauteur;
	}

	public void setHauteur(BigDecimal hauteur) {
		firePropertyChange("hauteur", this.hauteur, this.hauteur = hauteur);
	}

	@LgrHibernateValidated(champBd = "poids",table = "ta_article", champEntite="poids" , clazz = TaTArticleDTO.class)
	public BigDecimal getPoids() {
		return poids;
	}

	public void setPoids(BigDecimal poids) {
		firePropertyChange("poids", this.poids, this.poids = poids);
	}


}
