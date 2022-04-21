package fr.legrain.document.model;

// Generated Apr 9, 2009 2:12:06 PM by Hibernate Tools 3.2.0.CR1

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.legrain.article.model.TaArticle;
import fr.legrain.article.model.TaEntrepot;
import fr.legrain.article.model.TaLot;
import fr.legrain.article.model.TaPrix;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.dto.ILigneDocumentTiers;
import fr.legrain.document.dto.TaLigneALigneSupplementDTO;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.stock.model.TaMouvementStock;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaLPanier generated by hbm2java
 */
@Entity
@EntityListeners(SwtLDocumentListener.class)
@Table(name = "ta_l_panier")
public class TaLPanier extends SWTLigneDocument implements java.io.Serializable, ILigneDocumentTiers {

	private static final long serialVersionUID = -5562741737115826023L;
	
	static Logger logger = Logger.getLogger(TaLPanier.class.getName());

	private TaTLigne taTLigne;
	private TaArticle taArticle;
	
	private TaLot taLot;
	private TaEntrepot taEntrepot;
	private String emplacementLDocument;
	private TaMouvementStock taMouvementStock;
	
	private TaPanier taDocument;
	private Integer numLigneLDocument;
//	private String libLDocument;
	private BigDecimal qteLDocument;
	private BigDecimal qte2LDocument;
	private String u1LDocument;
	private String u2LDocument;
	private BigDecimal prixULDocument;
	private BigDecimal tauxTvaLDocument;
	private String compteLDocument;
	private String libTvaLDocument;
	private String codeTvaLDocument;
	private String codeTTvaLDocument;
	private BigDecimal mtHtLDocument;
	private BigDecimal mtTtcLDocument;
	private BigDecimal remTxLDocument;
	private BigDecimal remHtLDocument;

	
	private String uSaisieLDocument;
	private BigDecimal qteSaisieLDocument;

	private String codeTitreTransport;
	private BigDecimal qteTitreTransport;

	
	private String codeBarre;
	
	private Set<TaLigneALigne> taLigneALignes = new HashSet<TaLigneALigne>(0);
	private Set<TaREtatLigneDocument> taREtatLigneDocuments = new HashSet<TaREtatLigneDocument>(0);
	private Set<TaHistREtatLigneDocument> taHistREtatLigneDocuments = new HashSet<TaHistREtatLigneDocument>(0);
	private Set<TaLigneALigneEcheance> taLigneALignesEcheance = new HashSet<TaLigneALigneEcheance>(0);
	


	@Transient
	private boolean legrain = false;
	@Transient
	private BigDecimal txRemHtDocument;
	@Transient
	private BigDecimal mtHtLApresRemiseGlobaleDocument;
	@Transient
	private BigDecimal mtTtcLApresRemiseGlobaleDocument;
	@Transient
	private BigDecimal prixU2LDocument;
	@Transient
	private List<TaLigneALigneSupplementDTO> listeSupplement=new LinkedList<>();
	@Transient
	private List<TaLigneALigneSupplementDTO> listeLigneAIntegrer=new LinkedList<>();

	
	private boolean abonnement;
	
	private String codeLiaisonLigne;


	public TaLPanier() {
	}
	
	public TaLPanier	(boolean legrain) {
		this.legrain = legrain;
	}
	
	public TaLPanier(int idLPanier) {
		this.idLDocument = idLPanier;
	}

	public TaLPanier(int idLPanier, TaTLigne taTLigne, TaArticle taArticle,
			TaPanier taPanier, Integer numLigneLPanier, String libLPanier,
			BigDecimal qteLPanier, BigDecimal qte2LPanier, String u1LPanier,
			String u2LPanier, BigDecimal prixULPanier, BigDecimal tauxTvaLPanier,
			String compteLPanier, String codeTvaLPanier, String codeTTvaLPanier,
			BigDecimal mtHtLPanier, BigDecimal mtTtcLPanier,
			BigDecimal remTxLPanier, BigDecimal remHtLPanier,
			String quiCreeLPanier, Date quandCreeLPanier, String quiModifLPanier,
			Date quandModifLPanier, String ipAcces, Integer versionObj) {
		this.idLDocument = idLPanier;
		this.taTLigne = taTLigne;
		this.taArticle = taArticle;
		this.taDocument = taPanier;
		this.numLigneLDocument = numLigneLPanier;
		this.libLDocument = libLPanier;
		this.qteLDocument = qteLPanier;
		this.qte2LDocument = qte2LPanier;
		this.u1LDocument = u1LPanier;
		this.u2LDocument = u2LPanier;
		this.prixULDocument = prixULPanier;
		this.tauxTvaLDocument = tauxTvaLPanier;
		this.compteLDocument = compteLPanier;
		this.codeTvaLDocument = codeTvaLPanier;
		this.codeTTvaLDocument = codeTTvaLPanier;
		this.mtHtLDocument = mtHtLPanier;
		this.mtTtcLDocument = mtTtcLPanier;
		this.remTxLDocument = remTxLPanier;
		this.remHtLDocument = remHtLPanier;

		this.versionObj = versionObj;
	}



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_t_ligne")
	@LgrHibernateValidated(champBd = "id_t_ligne",table = "ta_t_ligne", champEntite="taTLigne.idTLigne", clazz = TaTLigne.class)
	public TaTLigne getTaTLigne() {
		return this.taTLigne;
	}

	public void setTaTLigne(TaTLigne taTLigne) {
		this.taTLigne = taTLigne;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_article")
	@LgrHibernateValidated(champBd = "id_article",table = "ta_article", champEntite="taArticle.idArticle", clazz = TaArticle.class)
	public TaArticle getTaArticle() {
		return this.taArticle;
	}



	public void setTaArticle(TaArticle taArticle) {
		this.taArticle = taArticle;

		if(legrain) {
			if(taArticle!=null) {
				
				System.out.println("===****=== Transfert de code metier des entites vers les services, à bien vérifier");
				
				boolean commentaire=false;
				commentaire=taArticle.getTaFamille()!=null && taArticle.getTaFamille().getCodeFamille().equals("&&&");
				if(!commentaire){
					TaPrix prix = null;
					if(taDocument!=null && taDocument.getTaTiers()!=null)
						prix = taArticle.chercheTarif(taDocument.getTaTiers());
					if(prix!=null) {
						if(taDocument!=null) { //#JPA
							if(taDocument.getTtc()==1)
								setPrixULDocument(prix.getPrixttcPrix());
							else
								setPrixULDocument(prix.getPrixPrix());
						}

						if(taArticle.getTaUnite1()!=null) {
							setU1LDocument(taArticle.getTaUnite1().getCodeUnite());
						}
					}else{
						setPrixULDocument(BigDecimal.valueOf(0));
						setU1LDocument("");
					}
					
					if(taArticle.getTaTva()!=null && taDocument.isGestionTVA()) {
						setCodeTvaLDocument(taArticle.getTaTva().getCodeTva());
						setTauxTvaLDocument(taArticle.getTaTva().getTauxTva());
					}else{
						setCodeTvaLDocument("");
						setTauxTvaLDocument(BigDecimal.valueOf(0));
					}
					setQteLDocument(new BigDecimal(1));
					setCompteLDocument(taArticle.getNumcptArticle());
					calculMontant();
					setLibLDocument(taArticle.getLibellecArticle());
				}
				else {
				setLibLDocument(taArticle.getLibellecArticle());
				setTaArticle(null);
				}
			}
		}
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_document")
	@LgrHibernateValidated(champBd = "id_document",table = "ta_panier", champEntite="taDocument.idDocument", clazz = TaPanier.class)
	public TaPanier getTaDocument() {
		return this.taDocument;
	}

	public void setTaDocument(TaPanier taPanier) {
		this.taDocument = taPanier;
	}
	
	public void setTaDocumentGeneral(IDocumentTiers taDocument) {
		this.taDocument = (TaPanier) taDocument;
	}

	@Column(name = "num_ligne_l_document")
	@LgrHibernateValidated(champBd = "num_ligne_l_document",table = "ta_l_panier", champEntite="numLigneLDocument", clazz = TaLPanier.class)
	public Integer getNumLigneLDocument() {
		return this.numLigneLDocument;
	}

	public void setNumLigneLDocument(Integer numLigneLPanier) {
		this.numLigneLDocument = numLigneLPanier;
	}

	@Column(name = "lib_l_document")
	@LgrHibernateValidated(champBd = "lib_l_document",table = "ta_l_panier", champEntite="libLDocument", clazz = TaLPanier.class)
	public String getLibLDocument() {
		return this.libLDocument;
	}

	public void setLibLPanier(String libLPanier) {
		setLibLDocument(libLPanier);
	}

	public void setLibLDocument(String libLPanier) {
		this.libLDocument = libLPanier;
	}

	@Column(name = "qte_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte_l_document",table = "ta_l_panier", champEntite="qteLDocument", clazz = TaLPanier.class)
	public BigDecimal getQteLDocument() {
		return this.qteLDocument;
	}

	public void setQteLDocument(BigDecimal qteLPanier) {		
		BigDecimal qteLDocumentOld = this.qteLDocument;
		this.qteLDocument = qteLPanier;
		if(legrain) {
			calculMontant();
			firePropertyChange(new PropertyChangeEvent(this,"qteLDocument",qteLDocumentOld,qteLPanier));
		}
	}

	@Column(name = "qte2_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte2_l_document",table = "ta_l_panier", champEntite="qte2LDocument", clazz = TaLPanier.class)
	public BigDecimal getQte2LDocument() {
		return this.qte2LDocument;
	}

	public void setQte2LDocument(BigDecimal qte2LPanier) {
		this.qte2LDocument = qte2LPanier;
	}

	@Column(name = "u1_l_document", length = 20)
	@LgrHibernateValidated(champBd = "u1_l_document",table = "ta_l_panier", champEntite="u1LDocument", clazz = TaLPanier.class)
	public String getU1LDocument() {
		return this.u1LDocument;
	}

	public void setU1LDocument(String u1LPanier) {
		this.u1LDocument = u1LPanier;
	}

	@Column(name = "u2_l_document", length = 20)
	@LgrHibernateValidated(champBd = "u2_l_document",table = "ta_l_panier", champEntite="u2LDocument", clazz = TaLPanier.class)
	public String getU2LDocument() {
		return this.u2LDocument;
	}

	public void setU2LDocument(String u2LPanier) {
		this.u2LDocument = u2LPanier;
	}

	@Column(name = "prix_u_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "prix_u_l_document",table = "ta_l_panier", champEntite="prixULDocument", clazz = TaLPanier.class)
	public BigDecimal getPrixULDocument() {
		return this.prixULDocument;
	}

	public void setPrixULDocument(BigDecimal prixULPanier) {
		this.prixULDocument = prixULPanier;
		if(legrain) {
			calculMontant();
		}
	}
	
	@Transient
	public BigDecimal getPrixU2LDocument() {
		return prixU2LDocument;
	}
	@Transient
	public void setPrixU2LDocument(BigDecimal prixU2LDocument) {
		this.prixU2LDocument = prixU2LDocument;
	}
	
	@Column(name = "taux_tva_l_document", precision = 15, scale = 4)
	@LgrHibernateValidated(champBd = "taux_tva_l_document",table = "ta_l_panier", champEntite="tauxTvaLDocument", clazz = TaLPanier.class)
	public BigDecimal getTauxTvaLDocument() {
		return this.tauxTvaLDocument;
	}

	public void setTauxTvaLDocument(BigDecimal tauxTvaLPanier) {
		this.tauxTvaLDocument = tauxTvaLPanier;
	}

	@Column(name = "compte_l_document", length = 8)
	@LgrHibernateValidated(champBd = "compte_l_document",table = "ta_l_panier", champEntite="compteLDocument", clazz = TaLPanier.class)
	public String getCompteLDocument() {
		return this.compteLDocument;
	}

	public void setCompteLDocument(String compteLPanier) {
		this.compteLDocument = compteLPanier;
	}

	@Column(name = "code_tva_l_document", length = 20)
	@LgrHibernateValidated(champBd = "code_tva_l_document",table = "ta_l_panier", champEntite="codeTvaLDocument", clazz = TaLPanier.class)
	public String getCodeTvaLDocument() {
		return this.codeTvaLDocument;
	}

	public void setCodeTvaLDocument(String codeTvaLPanier) {
		this.codeTvaLDocument = codeTvaLPanier;
	}
	
	@Column(name = "lib_tva_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "lib_tva_l_document",table = "ta_l_devis", champEntite="libTvaLDocument", clazz = TaLPanier.class)
	public String getLibTvaLDocument() {
		return this.libTvaLDocument;
	}
	public void setLibTvaLDocument(String libTvaLDocument) {
		this.libTvaLDocument = libTvaLDocument;
	}

	@Column(name = "code_t_tva_l_document", length = 1)
	@LgrHibernateValidated(champBd = "code_t_tva_l_document",table = "ta_l_panier", champEntite="codeTTvaLDocument", clazz = TaLPanier.class)
	public String getCodeTTvaLDocument() {
		return this.codeTTvaLDocument;
	}

	public void setCodeTTvaLDocument(String codeTTvaLPanier) {
		this.codeTTvaLDocument = codeTTvaLPanier;
	}

	@Column(name = "mt_ht_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "mt_ht_l_document",table = "ta_l_panier", champEntite="mtHtLDocument", clazz = TaLPanier.class)
	public BigDecimal getMtHtLDocument() {
		return this.mtHtLDocument;
	}

	public void setMtHtLDocument(BigDecimal mtHtLPanier) {
		this.mtHtLDocument = LibCalcul.arrondi(mtHtLPanier);
	}

	@Column(name = "mt_ttc_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "mt_ttc_l_document",table = "ta_l_panier", champEntite="mtTtcLDocument", clazz = TaLPanier.class)
	public BigDecimal getMtTtcLDocument() {
		return this.mtTtcLDocument;
	}

	public void setMtTtcLDocument(BigDecimal mtTtcLPanier) {
		this.mtTtcLDocument = LibCalcul.arrondi(mtTtcLPanier);
	}

	@Column(name = "rem_tx_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "rem_tx_l_document",table = "ta_l_panier", champEntite="remTxLDocument", clazz = TaLPanier.class)
	public BigDecimal getRemTxLDocument() {
		return this.remTxLDocument;
	}

	public void setRemTxLDocument(BigDecimal remTxLPanier) {
		this.remTxLDocument = remTxLPanier;
		if(legrain) {
			//calculMontant();
			
			System.out.println("===****=== Transfert de code metier des entites vers les services, à bien vérifier");
			//passage ejb => dans TaXXXService
//			taDocument.calculeTvaEtTotaux();
		}
		
	}

	@Column(name = "rem_ht_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "rem_ht_l_document",table = "ta_l_panier", champEntite="remHtLDocument", clazz = TaLPanier.class)
	public BigDecimal getRemHtLDocument() {
		return this.remHtLDocument;
	}

	public void setRemHtLDocument(BigDecimal remHtLPanier) {
		this.remHtLDocument = remHtLPanier;
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

	public void calculMontant() {
		if(qteLDocument!=null && prixULDocument!=null) {
			if(taDocument!=null) {//#JPA
				setTxRemHtDocument(taDocument.getTxRemHtDocument());
				prixULDocument=prixULDocument.setScale(taDocument.getNbDecimalesPrix(),RoundingMode.DOWN);
				if(taDocument.getTtc()==1) {					
					setMtTtcLDocument(qteLDocument.multiply(prixULDocument));
					if(remTxLDocument!=null && remTxLDocument.signum()!=0){
						setMtTtcLDocument(getMtTtcLDocument().subtract(getMtTtcLDocument().multiply(remTxLDocument.divide(new BigDecimal(100)))));
					}
					if( tauxTvaLDocument!=null && tauxTvaLDocument.signum()!=0){
						setMtHtLDocument(mtTtcLDocument.divide(BigDecimal.valueOf(1).add((tauxTvaLDocument.divide(new BigDecimal(100),MathContext.DECIMAL128).setScale(3,BigDecimal.ROUND_HALF_UP)
								)),MathContext.DECIMAL128).setScale(2,BigDecimal.ROUND_HALF_UP));
					} else {
						setMtHtLDocument(mtTtcLDocument);
					}
					
				} else {
					setMtHtLDocument(qteLDocument.multiply(prixULDocument));
					if(remTxLDocument!=null && remTxLDocument.signum()!=0){
						setMtHtLDocument(getMtHtLDocument().subtract(getMtHtLDocument().multiply(remTxLDocument.divide(new BigDecimal(100)))));
					}
					if( tauxTvaLDocument!=null && tauxTvaLDocument.signum()!=0){
						setMtTtcLDocument(mtHtLDocument.add((mtHtLDocument.multiply(tauxTvaLDocument.divide(new BigDecimal(100))))));
					} else {
						setMtTtcLDocument(mtHtLDocument);
					}
				
				}
				setMtTtcLApresRemiseGlobaleDocument(getMtTtcLDocument());
				setMtHtLApresRemiseGlobaleDocument(getMtHtLDocument());
			}
		}
	}
	
	@Transient
	public BigDecimal getTxRemHtDocument() {
		return txRemHtDocument;
	}

	public void setTxRemHtDocument(BigDecimal txRemHtPanier) {
		this.txRemHtDocument = txRemHtPanier;
	}

	//@Transient
	@Column(name = "mt_ht_apr_rem_globale", precision = 15)
	@LgrHibernateValidated(champBd = "mt_apr_rem_globale",table = "ta_l_panier", champEntite="mtHtLApresRemiseGlobaleDocument", clazz = TaLPanier.class)
	public BigDecimal getMtHtLApresRemiseGlobaleDocument() {
		return mtHtLApresRemiseGlobaleDocument;
	}

	public void setMtHtLApresRemiseGlobaleDocument(
			BigDecimal mtHtLApresRemiseGlobaleDocument) {
		this.mtHtLApresRemiseGlobaleDocument = mtHtLApresRemiseGlobaleDocument;
	}

	//@Transient
	@Column(name = "mt_ttc_apr_rem_globale", precision = 15)
	@LgrHibernateValidated(champBd = "mt_ttc_apr_rem_globale",table = "ta_l_panier", champEntite="mtTtcLApresRemiseGlobaleDocument", clazz = TaLPanier.class)
	public BigDecimal getMtTtcLApresRemiseGlobaleDocument() {
		return mtTtcLApresRemiseGlobaleDocument;
	}

	public void setMtTtcLApresRemiseGlobaleDocument(
			BigDecimal mtTtcLApresRemiseGlobaleDocument) {
		this.mtTtcLApresRemiseGlobaleDocument = mtTtcLApresRemiseGlobaleDocument;
	}

	public void setLegrain(boolean legrain) {
		this.legrain = legrain;
	}

	public TaLPanier clone() {
		TaLPanier ligne = new TaLPanier();
		try {
			//ligne = (TaLFacture)super.clone();
			ligne.setIdLDocument(0);
			ligne.setTaTLigne(taTLigne);
			ligne.setTaDocument(taDocument);
			ligne.setTaArticle(taArticle);
			ligne.setNumLigneLDocument(numLigneLDocument);
			ligne.setLibLDocument(libLDocument);
			ligne.setQteLDocument(qteLDocument);
			ligne.setQte2LDocument(qte2LDocument);
			ligne.setU1LDocument(u1LDocument);
			ligne.setU2LDocument(u2LDocument);
			ligne.setPrixULDocument(prixULDocument);
			ligne.setTauxTvaLDocument(tauxTvaLDocument);
			ligne.setTxRemHtDocument(txRemHtDocument);
			ligne.setCompteLDocument(compteLDocument);
			ligne.setCodeTvaLDocument(codeTvaLDocument);
			ligne.setCodeTTvaLDocument(codeTTvaLDocument);
			ligne.setMtHtLDocument(mtHtLDocument);
			ligne.setMtTtcLDocument(mtTtcLDocument);
			ligne.setMtHtLApresRemiseGlobaleDocument(mtHtLApresRemiseGlobaleDocument);
			ligne.setMtTtcLApresRemiseGlobaleDocument(mtTtcLApresRemiseGlobaleDocument);
			ligne.setRemTxLDocument(remTxLDocument);
			ligne.setRemHtLDocument(remHtLDocument);
			ligne.setVersionObj(versionObj);			
		} catch(Exception cnse) {
			logger.error("",cnse);
		}
		// on renvoie le clone
		return ligne;
	}

	@NotNull(message="Le lot ne peut être nul")
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "id_lot")
	@LgrHibernateValidated(champBd = "id_lot",table = "ta_lot", champEntite="taLot.idLot", clazz = TaLot.class)
	@Valid
	public TaLot getTaLot() {
		return taLot;
	}

	public void setTaLot(TaLot taLot) {
		this.taLot = taLot;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_entrepot")
	@LgrHibernateValidated(champBd = "id_entrepot",table = "ta_entrepot", champEntite="taEntrepot.idEntrepot", clazz = TaEntrepot.class)
	public TaEntrepot getTaEntrepot() {
		return taEntrepot;
	}

	public void setTaEntrepot(TaEntrepot taEntrepot) {
		this.taEntrepot = taEntrepot;
	}

	@Column(name = "emplacement_l_document")
	public String getEmplacementLDocument() {
		return emplacementLDocument;
	}

	public void setEmplacementLDocument(String emplacementLDocument) {
		this.emplacementLDocument = emplacementLDocument;
	}

	@NotNull
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_mouvement_stock")
//	@PrimaryKeyJoinColumn
	@LgrHibernateValidated(champBd = "id_mouvement_stock",table = "ta_mouvement_stock", champEntite="taMouvementStock.idMouvementStock", clazz = TaMouvementStock.class)
	public TaMouvementStock getTaMouvementStock() {
		return taMouvementStock;
	}

	public void setTaMouvementStock(TaMouvementStock taMouvementStock) {
		this.taMouvementStock = taMouvementStock;
	}

	@Column(name = "code_barre")
	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taLPanier", orphanRemoval=true)
	@XmlTransient
	public Set<TaLigneALigne> getTaLigneALignes() {
		return this.taLigneALignes;
	}

	public void setTaLigneALignes(Set<TaLigneALigne> taLigneALignes) {
		this.taLigneALignes = taLigneALignes;
	}


	@Transient
	public List<TaLigneALigneSupplementDTO> getListeSupplement() {
		// TODO Auto-generated method stub
		return listeSupplement;
	}

	@Transient
	public void setListeSupplement(List<TaLigneALigneSupplementDTO> listeSupplement) {
		// TODO Auto-generated method stub
		this.listeSupplement=listeSupplement;
	}


	@Transient
	public List<TaLigneALigneSupplementDTO> getListeLigneAIntegrer() {
		// TODO Auto-generated method stub
		return listeLigneAIntegrer;
	}

	@Transient
	public void setListeLigneAIntegrer(List<TaLigneALigneSupplementDTO> listeLigneAIntegrer) {
		// TODO Auto-generated method stub
		this.listeLigneAIntegrer=listeLigneAIntegrer;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taLPanier", orphanRemoval = true)
	public Set<TaREtatLigneDocument> getTaREtatLigneDocuments() {
		return this.taREtatLigneDocuments;
	}

	public void setTaREtatLigneDocuments(Set<TaREtatLigneDocument> taREtats) {
		this.taREtatLigneDocuments = taREtats;
	}
	

	
	public void addREtatLigneDoc(TaEtat taEtat) {
		TaREtatLigneDocument rEtat=getTaREtatLigneDocument();
		if(rEtat!=null) {
			TaHistREtatLigneDocument hist=new TaHistREtatLigneDocument();
			hist.setTaEtat(getTaREtatLigneDocument().getTaEtat());
			hist.setTaLPanier(this);
			this.getTaHistREtatLigneDocuments().add(hist);
		}else rEtat=new TaREtatLigneDocument();
		
		rEtat.setTaEtat(taEtat);
		rEtat.setTaLPanier(this);
		this.setTaREtatLigneDocument(rEtat);
		this.getTaREtatLigneDocuments().add(rEtat);
	}

	
		

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taLPanier", orphanRemoval = true)
	public Set<TaHistREtatLigneDocument> getTaHistREtatLigneDocuments() {
		return this.taHistREtatLigneDocuments;
	}

	public void setTaHistREtatLigneDocuments(Set<TaHistREtatLigneDocument> taHistREtatLigneDocuments) {
		this.taHistREtatLigneDocuments = taHistREtatLigneDocuments;
	}

	@Transient
	public String getQuiCree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setQuiCree(String quiCreelApporteur) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	public Date getQuandCree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setQuandCree(Date quandCreeLApporteur) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	public String getQuiModif() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setQuiModif(String quiModifLApporteur) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	public Date getQuandModif() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setQuandModif(Date quandModifLApporteur) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	public String getIpAcces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setIpAcces(String ipAcces) {
		// TODO Auto-generated method stub
		
	}

	@Transient
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	public void setVersion(String version) {
		// TODO Auto-generated method stub
		
	}
	
	@Column(name = "abonnement")
	public Boolean getAbonnement() {
		return abonnement;
	}
	
	public void setAbonnement(Boolean abonnement) {
		this.abonnement = abonnement;
	}
	
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taLPanier", orphanRemoval=true)
	@XmlTransient
	@Fetch(FetchMode.SUBSELECT)	
	public Set<TaLigneALigneEcheance> getTaLigneALignesEcheance() {
		return taLigneALignesEcheance;
	}

	public void setTaLigneALignesEcheance(Set<TaLigneALigneEcheance> taLigneALignesEcheance) {
		this.taLigneALignesEcheance = taLigneALignesEcheance;
	}
	@Column(name = "code_liaison_ligne")
	public String getCodeLiaisonLigne() {
		return codeLiaisonLigne;
	}

	public void setCodeLiaisonLigne(String codeLiaisonLigne) {
		this.codeLiaisonLigne = codeLiaisonLigne;
	}
	
	@Column(name = "u_saisie_l_document", length = 20)
	@LgrHibernateValidated(champBd = "u_saisie_l_document",table = "ta_l_panier", champEntite="uSaisieLDocument", clazz = TaLPanier.class)
	public String getUSaisieLDocument() {
		return uSaisieLDocument;
	}

	public void setUSaisieLDocument(String uSaisieLDocument) {
		this.uSaisieLDocument = uSaisieLDocument;
	}

	@Column(name = "qte_saisie_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte_saisie_l_document",table = "ta_l_panier", champEntite="qteSaisieLDocument", clazz = TaLPanier.class)
	public BigDecimal getQteSaisieLDocument() {
		return qteSaisieLDocument;
	}

	public void setQteSaisieLDocument(BigDecimal qteSaisieLDocument) {
		this.qteSaisieLDocument = qteSaisieLDocument;
	}


	
	
	@Column(name = "titre_transport_l_document", length = 20)
	@LgrHibernateValidated(champBd = "titre_transport_l_document",table = "ta_l_panier", champEntite="codeTitreTransport", clazz = TaLPanier.class)
	public String getCodeTitreTransport() {
		return this.codeTitreTransport;
	}

	public void setCodeTitreTransport(String titreTransport) {
		this.codeTitreTransport = titreTransport;
	}
	
	@Column(name = "qte_titre_transport_l_document", precision = 15)
	@LgrHibernateValidated(champBd = "qte_titre_transport_l_document",table = "ta_l_panier", champEntite="qteTitreTransport", clazz = TaLPanier.class)
	public BigDecimal getQteTitreTransport() {
		return this.qteTitreTransport;
	}

	public void setQteTitreTransport(BigDecimal qteTitreTransport) {
		this.qteTitreTransport = qteTitreTransport;
	}

}


