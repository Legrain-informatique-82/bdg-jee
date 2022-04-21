package fr.legrain.document.model;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import fr.legrain.document.dto.IRelationDoc;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaRDocument generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_r_avoir")
//@SequenceGenerator(name = "gen_r_avoir", sequenceName = "num_id_r_avoir", allocationSize = 1)
public class TaRAvoir implements java.io.Serializable,IRelationDoc {

	private static final long serialVersionUID = 6314078004076629326L;
	
	private int id;
	private String version;
	private TaFacture taFacture;
	private TaTicketDeCaisse taTicketDeCaisse;
	private TaAvoir taAvoir;
	private BigDecimal affectation  = new BigDecimal(0);
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
//	private Integer export= 0;
	
	private Date dateExport;
	private Date dateVerrouillage;
	
	private TaMiseADisposition taMiseADisposition;
	private int Etat =0;
	
    @Transient
	private int EtatDeSuppression =0;
	public static final String TYPE_DOC = "RAvoir";
	@Transient
	private String typeDocument;
    
	public TaRAvoir() {
	}

	public TaRAvoir(int idRAvoir) {
		this.id = idRAvoir;
	}

//public TaRAvoir(int id, String version, TaFacture taFacture,
//			TaBoncde taBoncde, TaProforma taProforma, TaDevis taDevis,
//			TaApporteur taApporteur, TaAvoir taAvoir, TaBonliv taBonliv,
//			TaAvoir taAvoir, BigDecimal affectation, String quiCree,
//			Date quandCree, String quiModif, Date quandModif, String ipAcces,
//			Integer versionObj, boolean etatDeSuppression) {
//		super();
//		this.id = id;
//		this.version = version;
//		this.taFacture = taFacture;
//		this.taBoncde = taBoncde;
//		this.taProforma = taProforma;
//		this.taDevis = taDevis;
//		this.taApporteur = taApporteur;
//		this.taAvoir = taAvoir;
//		this.taBonliv = taBonliv;
//		this.taAvoir = taAvoir;
//		this.affectation = affectation;
//		this.quiCree = quiCree;
//		this.quandCree = quandCree;
//		this.quiModif = quiModif;
//		this.quandModif = quandModif;
//		this.ipAcces = ipAcces;
//		this.versionObj = versionObj;
//		EtatDeSuppression = etatDeSuppression;
//	}

//	public TaRAvoir(int idRAvoir, TaFacture taFacture, TaBoncde taBoncde,
//			TaProforma taProforma, 
//			TaBonliv taBonliv, TaAvoir taAvoir,TaApporteur taApporteur,
//			TaDevis taDevis, String quiCreeRDocument,
//			Date quandCreeRDocument, String quiModifRDocument,
//			Date quandModifRDocument, String ipAcces, Integer versionObj) {
//		this.id = idRAvoir;
//		this.taFacture = taFacture;
//		this.taBoncde = taBoncde;
//		this.taProforma = taProforma;
//		this.taDevis = taDevis;		
//		this.taApporteur = taApporteur;
//		this.taAvoir = taAvoir;
//		this.taBonliv = taBonliv;
//		this.quiCree = quiCreeRDocument;
//		this.quandCree = quandCreeRDocument;
//		this.quiModif = quiModifRDocument;
//		this.quandModif = quandModifRDocument;
//		this.ipAcces = ipAcces;
//		this.versionObj = versionObj;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id",table = "ta_r_avoir", champEntite="id", clazz = TaRAvoir.class)
	public int getId() {
		return this.id;
	}

	public void setId(int idRAvoir) {
		this.id = idRAvoir;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_facture")
	@LgrHibernateValidated(champBd = "id_facture",table = "ta_facture", champEntite="taFacture.idDocument", clazz = TaFacture.class)
	@XmlElement
	@XmlInverseReference(mappedBy="taRAvoirs")
	public TaFacture getTaFacture() {
		return this.taFacture;
	}

	public void setTaFacture(TaFacture taFacture) {
		this.taFacture = taFacture;
	}
	
	//
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_avoir")
	@XmlElement
	@XmlInverseReference(mappedBy="taRAvoirs")
	@LgrHibernateValidated(champBd = "id_avoir",table = "ta_avoir", champEntite="taAvoir.idDocument", clazz = TaAvoir.class)
	public TaAvoir getTaAvoir() {
		return this.taAvoir;
	}

	public void setTaAvoir(TaAvoir taAvoir) {
		this.taAvoir = taAvoir;
	}
	
	@Column(name = "affectation", precision = 15)
	@LgrHibernateValidated(champBd = "affectation",table = "ta_r_avoir", champEntite="affectation", clazz = TaRAvoir.class)
	public BigDecimal getAffectation() {
		return affectation ;
	}

	public void setAffectation(BigDecimal montant) {
		this.affectation = montant;
	}


	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeRDocument) {
		this.quiCree = quiCreeRDocument;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeRDocument) {
		this.quandCree = quandCreeRDocument;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifRDocument) {
		this.quiModif = quiModifRDocument;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifRDocument) {
		this.quandModif = quandModifRDocument;
	}

	@Column(name = "ip_acces", length = 50)
	public String getIpAcces() {
		return this.ipAcces;
	}

	public void setIpAcces(String ipAcces) {
		this.ipAcces = ipAcces;
	}

	@Version
	@Column(name = "version_obj")
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

//	@Column(name = "export")
//	@LgrHibernateValidated(champBd = "export",table = "ta_r_avoir", champEntite="export", clazz = TaRAvoir.class)
//	public Integer getExport() {
//		return export;
//	}
//
//	public void setExport(Integer export) {
//		this.export = export;
//	}
	
	@Column(name = "etat")
	@LgrHibernateValidated(champBd = "etat",table = "ta_r_avoir", champEntite="Etat", clazz = TaRAvoir.class)
	public int getEtat() {
		return Etat;
	}
	
	
	public void setEtat(Integer etatDeSuppression) {
		if(etatDeSuppression!=null)
			Etat = etatDeSuppression;
	}
	


	public void setEtat(int etat) {
		Etat = etat;
	}


	@Transient
	public int getEtatDeSuppression() {
		return EtatDeSuppression;
	}
	
	@Transient
	public void setEtatDeSuppression(int etatDeSuppression) {
		EtatDeSuppression = etatDeSuppression;
	}
	
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL} , orphanRemoval=true )
	@JoinColumn(name = "id_mise_a_disposition")
	public TaMiseADisposition getTaMiseADisposition() {
		return taMiseADisposition;
	}

	public void setTaMiseADisposition(TaMiseADisposition taMiseADisposition) {
		this.taMiseADisposition = taMiseADisposition;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_export", length = 19)
	public Date getDateExport() {
		return dateExport;
	}

	public void setDateExport(Date dateExport) {
		this.dateExport = dateExport;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_verrouillage", length = 19)
	public Date getDateVerrouillage() {
		return dateVerrouillage;
	}

	public void setDateVerrouillage(Date dateVerrouillage) {
		this.dateVerrouillage = dateVerrouillage;
	}
	

	@Transient
	public String getTypeDocument() {
		return TYPE_DOC;
	}


	public void setTypeDocument(String typeDocument) {
		this.typeDocument=typeDocument;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ticket_caisse")
	@LgrHibernateValidated(champBd = "id_ticket_caisse",table = "ta_ticket_caisse", champEntite="taTicketDeCaisse.idDocument", clazz = TaTicketDeCaisse.class)
	@XmlElement
	@XmlInverseReference(mappedBy="taRAvoirs")
	public TaTicketDeCaisse getTaTicketDeCaisse() {
		return taTicketDeCaisse;
	}

	public void setTaTicketDeCaisse(TaTicketDeCaisse taTicketDeCaisse) {
		this.taTicketDeCaisse = taTicketDeCaisse;
	}
}
