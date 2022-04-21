package fr.legrain.document.model;

// Generated Apr 7, 2009 3:27:23 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTPaiement generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_paiement", uniqueConstraints = @UniqueConstraint(columnNames = "code_t_paiement"))
//@SequenceGenerator(name = "gen_t_paiement", sequenceName = "num_id_t_paiement", allocationSize = 1)
public class TaTPaiement implements java.io.Serializable {

	private static final String CODE_T_PAIEMENT_DEFAUT="code_t_paiement_defaut";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3996894122321322929L;
	
	private int idTPaiement;
	private String version;
	private String codeTPaiement;
	private String libTPaiement;
	private String compte;
	private Integer reportTPaiement;
	private Integer finMoisTPaiement;
	
	private TaTMoyenPaiement taTMoyenPaiement;
	
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;
	//private Set<TaFacture> taFactures = new HashSet<TaFacture>(0);
	
	//passage ejb
//	private Set<TaAvoir> taAvoirs = new HashSet<TaAvoir>(0);
//	private Set<TaBonliv> taBonlivs = new HashSet<TaBonliv>(0);
//	private Set<TaBoncde> taBoncdes = new HashSet<TaBoncde>(0);
//	private Set<TaApporteur> taApporteurs = new HashSet<TaApporteur>(0);
//	private Set<TaProforma> taProformas = new HashSet<TaProforma>(0);

	public TaTPaiement() {
	}

	public TaTPaiement(int idTPaiement) {
		this.idTPaiement = idTPaiement;
	}

	public TaTPaiement(int idTPaiement, String codeTPaiement,
			String libTPaiement, String quiCreeTPaiement,
			Date quandCreeTPaiement, String quiModifTPaiement,
			Date quandModifTPaiement, String ipAcces, Integer versionObj
			//,
			/*Set<TaFacture> taFactures, Set<TaAvoir> taAvoirs,
			Set<TaBonliv> taBonlivs, Set<TaBoncde> taBoncdes,
			Set<TaApporteur> taApporteurs, Set<TaProforma> taProformas*/) {
		this.idTPaiement = idTPaiement;
		this.codeTPaiement = codeTPaiement;
		this.libTPaiement = libTPaiement;
		this.quiCree = quiCreeTPaiement;
		this.quandCree = quandCreeTPaiement;
		this.quiModif = quiModifTPaiement;
		this.quandModif = quandModifTPaiement;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		//this.taFactures = taFactures;
//		this.taAvoirs = taAvoirs;
//		this.taBonlivs = taBonlivs;
//		this.taBoncdes = taBoncdes;
//		this.taApporteurs = taApporteurs;
//		this.taProformas = taProformas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_paiement", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_paiement",table = "ta_t_paiement", champEntite="idTPaiement", clazz = TaTPaiement.class)
	public int getIdTPaiement() {
		return this.idTPaiement;
	}

	public void setIdTPaiement(int idTPaiement) {
		this.idTPaiement = idTPaiement;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "code_t_paiement", unique = true, length = 50)
	@LgrHibernateValidated(champBd = "code_t_paiement",table = "ta_t_paiement", champEntite="codeTPaiement", clazz = TaTPaiement.class)
	public String getCodeTPaiement() {
		return this.codeTPaiement;
	}

	public void setCodeTPaiement(String codeTPaiement) {
		this.codeTPaiement = codeTPaiement;
	}

	@Column(name = "lib_t_paiement")
	@LgrHibernateValidated(champBd = "lib_t_paiement",table = "ta_t_paiement", champEntite="libTPaiement", clazz = TaTPaiement.class)
	public String getLibTPaiement() {
		return this.libTPaiement;
	}

	public void setLibTPaiement(String libTPaiement) {
		this.libTPaiement = libTPaiement;
	}

	@Column(name = "compte")
	@LgrHibernateValidated(champBd = "compte",table = "ta_t_paiement", champEntite="compte", clazz = TaTPaiement.class)
	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}
	
	
	@Column(name = "report_t_paiement")
	@LgrHibernateValidated(champBd = "report_t_paiement",table = "ta_t_paiement" ,champEntite="reportTPaiement", clazz = TaTPaiement.class)
	public Integer getReportTPaiement() {
		return this.reportTPaiement;
	}

	public void setReportTPaiement(Integer reportCPaiement) {
		this.reportTPaiement = reportCPaiement;
	}

	@Column(name = "fin_mois_t_paiement")
	@LgrHibernateValidated(champBd = "fin_mois_t_paiement",table = "ta_t_paiement" ,champEntite="finMoisTPaiement", clazz = TaTPaiement.class)
	public Integer getFinMoisTPaiement() {
		return this.finMoisTPaiement;
	}

	public void setFinMoisTPaiement(Integer finMoisCPaiement) {
		this.finMoisTPaiement = finMoisCPaiement;
	}
	
	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeTPaiement) {
		this.quiCree = quiCreeTPaiement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeTPaiement) {
		this.quandCree = quandCreeTPaiement;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifTPaiement) {
		this.quiModif = quiModifTPaiement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifTPaiement) {
		this.quandModif = quandModifTPaiement;
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

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaFacture> getTaFactures() {
//		return this.taFactures;
//	}
//
//	public void setTaFactures(Set<TaFacture> taFactures) {
//		this.taFactures = taFactures;
//	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaAvoir> getTaAvoirs() {
//		return this.taAvoirs;
//	}
//
//	public void setTaAvoirs(Set<TaAvoir> taAvoirs) {
//		this.taAvoirs = taAvoirs;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaBonliv> getTaBonlivs() {
//		return this.taBonlivs;
//	}
//
//	public void setTaBonlivs(Set<TaBonliv> taBonlivs) {
//		this.taBonlivs = taBonlivs;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaBoncde> getTaBoncdes() {
//		return this.taBoncdes;
//	}
//
//	public void setTaBoncdes(Set<TaBoncde> taBoncdes) {
//		this.taBoncdes = taBoncdes;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaApporteur> getTaApporteurs() {
//		return this.taApporteurs;
//	}
//
//	public void setTaApporteurs(Set<TaApporteur> taApporteurs) {
//		this.taApporteurs = taApporteurs;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTPaiement")
//	public Set<TaProforma> getTaProformas() {
//		return this.taProformas;
//	}
//
//	public void setTaProformas(Set<TaProforma> taProformas) {
//		this.taProformas = taProformas;
//	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTPaiement(codeTPaiement.toUpperCase());
	}

	public static String getCodeTPaiementDefaut() {
		return CODE_T_PAIEMENT_DEFAUT;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_t_moyen_paiement")
	public TaTMoyenPaiement getTaTMoyenPaiement() {
		return taTMoyenPaiement;
	}

	public void setTaTMoyenPaiement(TaTMoyenPaiement taTMoyenPaiement) {
		this.taTMoyenPaiement = taTMoyenPaiement;
	}

}