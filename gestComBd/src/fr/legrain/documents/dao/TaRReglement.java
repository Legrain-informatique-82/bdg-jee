package fr.legrain.documents.dao;

// Generated Apr 7, 2009 3:27:23 PM by Hibernate Tools 3.2.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import fr.legrain.gestCom.Module_Document.IRelationDoc;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaRDocument generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_R_REGLEMENT")
@SequenceGenerator(name = "GEN_R_REGLEMENT", sequenceName = "NUM_ID_R_REGLEMENT", allocationSize = 1)
public class TaRReglement implements java.io.Serializable,IRelationDoc {

	private int id;
	private String version;
	private TaReglement taReglement;
	private TaFacture taFacture;
	private TaBoncde taBoncde;
	private TaProforma taProforma;
	private TaDevis taDevis;
	private TaApporteur taApporteur;
	private TaAvoir taAvoir;
	private TaBonliv taBonliv;
//	private String codeReglement="";
//	private Date dateReglement;
//	private Date dateEncaissement;
//	private TaTPaiement taTPaiement;
//	private String libellePaiement;
//	private TaCompteBanque taCompteBanque;
//	private Integer etat= 0;
	private Integer export= 0;
//	private BigDecimal montantReglement  = new BigDecimal(0);
	private BigDecimal affectation  = new BigDecimal(0);
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	@Transient
	private Boolean accepte=true;
		
    
//    @Transient
//    private List<TaRReglement> taRReglements = new LinkedList<TaRReglement>();
    
    @Transient
	private int EtatDeSuppression =0;
    
	public TaRReglement() {
	}

	public TaRReglement(int idRAcompte) {
		this.id = idRAcompte;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_R_REGLEMENT")
	@Column(name = "ID", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID",table = "TA_R_REGLEMENT",clazz = TaRReglement.class)
	public int getId() {
		return this.id;
	}

	public void setId(int idRReglement) {
		this.id = idRReglement;
	}

	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
//	CascadeType.PERSIST, CascadeType.MERGE, *** enlever car probleme avec remise, les rAvoir et Racompte etaient
	//déjà comme cela. Voir si pas de problème avec les modifications dans les réglements !!!
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
	@JoinColumn(name = "ID_REGLEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_REGLEMENT",table = "TA_REGLEMENT",clazz = TaReglement.class)
	public TaReglement getTaReglement() {
		return this.taReglement;
	}


	
	public void setTaReglement(TaReglement taReglement) {
		this.taReglement = taReglement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FACTURE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_FACTURE",table = "TA_FACTURE",clazz = TaFacture.class)
	public TaFacture getTaFacture() {
		return this.taFacture;
	}

	public void setTaFacture(TaFacture taFacture) {
		this.taFacture = taFacture;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_BONCDE")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_BONCDE",table = "TA_BONCDE",clazz = TaBoncde.class)
	public TaBoncde getTaBoncde() {
		return this.taBoncde;
	}

	public void setTaBoncde(TaBoncde taBoncde) {
		this.taBoncde = taBoncde;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROFORMA")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_PROFORMA",table = "TA_PROFORMA",clazz = TaProforma.class)
	public TaProforma getTaProforma() {
		return this.taProforma;
	}

	public void setTaProforma(TaProforma taProforma) {
		this.taProforma = taProforma;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DEVIS")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_DEVIS",table = "TA_DEVIS",clazz = TaDevis.class)
	public TaDevis getTaDevis() {
		return this.taDevis;
	}

	public void setTaDevis(TaDevis taDevis) {
		this.taDevis = taDevis;
	}
	
	//
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_APPORTEUR")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_APPORTEUR",table = "TA_APPORTEUR",clazz = TaApporteur.class)
	public TaApporteur getTaApporteur() {
		return this.taApporteur;
	}

	public void setTaApporteur(TaApporteur taApporteur) {
		this.taApporteur = taApporteur;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVOIR")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_AVOIR",table = "TA_AVOIR",clazz = TaAvoir.class)
	public TaAvoir getTaAvoir() {
		return this.taAvoir;
	}

	public void setTaAvoir(TaAvoir taAvoir) {
		this.taAvoir = taAvoir;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_BONLIV")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_BONLIV",table = "TA_BONLIV",clazz = TaBonliv.class)
	public TaBonliv getTaBonliv() {
		return this.taBonliv;
	}

	public void setTaBonliv(TaBonliv taBonliv) {
		this.taBonliv = taBonliv;
	}


	@Column(name = "EXPORT")
	@LgrHibernateValidated(champEntite = "", champBd = "EXPORT",table = "TA_R_REGLEMENT",clazz = TaRReglement.class)
	public Integer getExport() {
		return export;
	}

	public void setExport(Integer export) {
		this.export = export;
	}
	
	@Column(name = "AFFECTATION", precision = 15)
	@LgrHibernateValidated(champEntite = "", champBd = "AFFECTATION",table = "TA_R_REGLEMENT",clazz = TaRReglement.class)
	public BigDecimal getAffectation() {
		return affectation ;
	}

	public void setAffectation(BigDecimal affectation) {
		this.affectation = affectation;
	}

//	@Column(name = "MONTANT_REGLEMENT", precision = 15)
//	@LgrHibernateValidated(champEntite = "", champ = "MONTANT_REGLEMENT",table = "TA_R_ACOMPTE",clazz = TaRReglement.class)
//	public BigDecimal getMontantReglement() {
//		return montantReglement;
//	}
//
//	public void setMontantReglement(BigDecimal montantReglement) {
//		this.montantReglement = montantReglement;
//	}
	

	@Column(name = "QUI_CREE", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeRDocument) {
		this.quiCree = quiCreeRDocument;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeRDocument) {
		this.quandCree = quandCreeRDocument;
	}

	@Column(name = "QUI_MODIF", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifRDocument) {
		this.quiModif = quiModifRDocument;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifRDocument) {
		this.quandModif = quandModifRDocument;
	}

	@Column(name = "IP_ACCES", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@Version
	@Column(name = "VERSION_OBJ")
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	
	public BigDecimal calculAffectationEnCours(TaFacture taDocument){
		if(this.getId()==0)
			return getAffectation();
			else
		return BigDecimal.valueOf(0);
	}

	@Transient
	public int getEtatDeSuppression() {
		return EtatDeSuppression;
	}
	
	@Transient
	public void setEtatDeSuppression(int etatDeSuppression) {
		EtatDeSuppression = etatDeSuppression;
	}

	@Transient
	public Boolean getAccepte() {
		return accepte;
	}

	@Transient
	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}
//	@Transient
//	public List<TaRReglement> getTaRReglements() {
//		return taRReglements;
//	}
//
//	public void setTaRReglements(List<TaRReglement> taRReglements) {
//		this.taRReglements = taRReglements;
//	}

//	@Transient
//	public void remonteListReglements(){
//		EntityManager em= new TaRReglementDAO().getEntityManager();
//		taRReglements=new TaRReglementDAO(em).findByCode(getCodeReglement());
//	}



	@PreRemove
	public void verifRemove()throws Exception{
		if(this.getTaFacture()!=null ){
			if(this.getTaFacture().getTaTiers().getIdTiers()!=this.getTaReglement().getTaTiers().getIdTiers()){
				throw new Exception("verifRemove");
			}
		}
	}

	
	@PrePersist
	public void verifPersist() throws Exception{

		if(this.getTaFacture()!=null ){
			if(this.getTaFacture().getTaTiers().getIdTiers()!=this.getTaReglement().getTaTiers().getIdTiers()){
				throw new Exception("verifPersist");
			}
		}
		if(this.getTaFacture().getRegleCompletDocument(this).add(this.affectation).compareTo(this.getTaFacture().getNetAPayer())>0){
			throw new Exception("Total affectation supérieure à la facture");
		}
		if(this.getTaReglement().calculAffectationTotale(this).add(this.affectation).compareTo(this.getTaReglement().getNetTtcCalc())>0){
			throw new Exception("Total affectation supérieure au règlement");
		}		
	}
	
//	@PreUpdate
	public void verifUpdate() throws Exception{
		if(this.getTaFacture()!=null ){
			if(this.getTaFacture().getTaTiers().getIdTiers()!=this.getTaReglement().getTaTiers().getIdTiers()){
				throw new Exception("verifUpdate");
			}
		}
		if(this.getTaFacture().getRegleCompletDocument(this).add(this.affectation).compareTo(this.getTaFacture().getNetAPayer())>0){
			throw new Exception("Total affectation supérieure à la facture");
		}
		if(this.getTaReglement().calculAffectationTotale(this).add(this.affectation).compareTo(this.getTaReglement().getNetTtcCalc())>0){
			throw new Exception("Total affectation supérieure au règlement");
		}
	}

	


}
