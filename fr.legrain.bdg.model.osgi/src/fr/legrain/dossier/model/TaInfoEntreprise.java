package fr.legrain.dossier.model;

// Generated 14 mai 2009 11:08:14 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import fr.legrain.tiers.model.TaTiers;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaInfoEntreprise generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_info_entreprise")
//@SequenceGenerator(name = "gen_info_entreprise", sequenceName = "num_id_info_entreprise", allocationSize = 1)
public class TaInfoEntreprise implements java.io.Serializable {

	private int idInfoEntreprise;
	private String version;
	private String nomInfoEntreprise;
	private String adresse1InfoEntreprise;
	private String adresse2InfoEntreprise;
	private String adresse3InfoEntreprise;
	private String codepostalInfoEntreprise;
	private String villeInfoEntreprise;
	private String paysInfoEntreprise;
	private String siretInfoEntreprise;
	private String capitalInfoEntreprise;
	private String apeInfoEntreprise;
	private String tvaIntraInfoEntreprise;
	private String accise;
	private String telInfoEntreprise;
	private String faxInfoEntreprise;
	private String emailInfoEntreprise;
	private String webInfoEntreprise;
	private String rcsInfoEntreprise;
	private Date datedebInfoEntreprise;
	private Date datefinInfoEntreprise;
	private Date datedebRegInfoEntreprise;
	private Date datedebRelInfoEntreprise;
	private String codexoInfoEntreprise;
	private TaTiers taTiers;
	private String quiCree;
	private Date quandCree;
	private String quiModif;
	private Date quandModif;
	private String ipAcces;
	private Integer versionObj;

	public TaInfoEntreprise() {
	}

	public TaInfoEntreprise(int idInfoEntreprise) {
		this.idInfoEntreprise = idInfoEntreprise;
	}

	public TaInfoEntreprise(int idInfoEntreprise, String nomInfoEntreprise,
			String adresse1InfoEntreprise, String adresse2InfoEntreprise,
			String adresse3InfoEntreprise, String codepostalInfoEntreprise,
			String villeInfoEntreprise, String paysInfoEntreprise,
			String siretInfoEntreprise, String capitalInfoEntreprise,
			String apeInfoEntreprise, String tvaIntraInfoEntreprise,
			String telInfoEntreprise, String faxInfoEntreprise,
			String emailInfoEntreprise, String webInfoEntreprise,
			String rcsInfoEntreprise, Date datedebInfoEntreprise,
			Date datefinInfoEntreprise, Date datedebRegInfoEntreprise, Date datedebRelInfoEntreprise, String codexoInfoEntreprise,
			TaTiers taTiers,
			String quiCreeInfoEntreprise, Date quandCreeInfoEntreprise,
			String quiModifInfoEntreprise, Date quandModifInfoEntreprise,
			String ipAcces, Integer versionObj) {
		this.idInfoEntreprise = idInfoEntreprise;
		this.nomInfoEntreprise = nomInfoEntreprise;
		this.adresse1InfoEntreprise = adresse1InfoEntreprise;
		this.adresse2InfoEntreprise = adresse2InfoEntreprise;
		this.adresse3InfoEntreprise = adresse3InfoEntreprise;
		this.codepostalInfoEntreprise = codepostalInfoEntreprise;
		this.villeInfoEntreprise = villeInfoEntreprise;
		this.paysInfoEntreprise = paysInfoEntreprise;
		this.siretInfoEntreprise = siretInfoEntreprise;
		this.capitalInfoEntreprise = capitalInfoEntreprise;
		this.apeInfoEntreprise = apeInfoEntreprise;
		this.tvaIntraInfoEntreprise = tvaIntraInfoEntreprise;
		this.telInfoEntreprise = telInfoEntreprise;
		this.faxInfoEntreprise = faxInfoEntreprise;
		this.emailInfoEntreprise = emailInfoEntreprise;
		this.webInfoEntreprise = webInfoEntreprise;
		this.rcsInfoEntreprise = rcsInfoEntreprise;
		this.datedebInfoEntreprise = datedebInfoEntreprise;
		this.datedebRegInfoEntreprise = datedebRegInfoEntreprise;
		this.datedebRelInfoEntreprise = datedebRelInfoEntreprise;
		this.datefinInfoEntreprise = datefinInfoEntreprise;
		this.codexoInfoEntreprise = codexoInfoEntreprise;
		this.taTiers = taTiers;
		this.quiCree = quiCreeInfoEntreprise;
		this.quandCree = quandCreeInfoEntreprise;
		this.quiModif = quiModifInfoEntreprise;
		this.quandModif = quandModifInfoEntreprise;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_info_entreprise", unique = true, nullable = false)
	public int getIdInfoEntreprise() {
		return this.idInfoEntreprise;
	}

	public void setIdInfoEntreprise(int idInfoEntreprise) {
		this.idInfoEntreprise = idInfoEntreprise;
	}

	@Column(name = "version", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "nom_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "nom_info_entreprise",table = "ta_info_entreprise", champEntite="nomInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getNomInfoEntreprise() {
		return this.nomInfoEntreprise;
	}

	public void setNomInfoEntreprise(String nomInfoEntreprise) {
		this.nomInfoEntreprise = nomInfoEntreprise;
	}

	@Column(name = "adresse1_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "adresse1_info_entreprise",table = "ta_info_entreprise", champEntite="adresse1InfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getAdresse1InfoEntreprise() {
		return this.adresse1InfoEntreprise;
	}

	public void setAdresse1InfoEntreprise(String adresse1InfoEntreprise) {
		this.adresse1InfoEntreprise = adresse1InfoEntreprise;
	}

	@Column(name = "adresse2_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "adresse2_info_entreprise",table = "ta_info_entreprise", champEntite="adresse2InfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getAdresse2InfoEntreprise() {
		return this.adresse2InfoEntreprise;
	}

	public void setAdresse2InfoEntreprise(String adresse2InfoEntreprise) {
		this.adresse2InfoEntreprise = adresse2InfoEntreprise;
	}

	@Column(name = "adresse3_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "adresse3_info_entreprise",table = "ta_info_entreprise", champEntite="adresse3InfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getAdresse3InfoEntreprise() {
		return this.adresse3InfoEntreprise;
	}

	public void setAdresse3InfoEntreprise(String adresse3InfoEntreprise) {
		this.adresse3InfoEntreprise = adresse3InfoEntreprise;
	}

	@Column(name = "codepostal_info_entreprise", length = 25)
	@LgrHibernateValidated(champBd = "codepostal_info_entreprise",table = "ta_info_entreprise", champEntite="codepostalInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getCodepostalInfoEntreprise() {
		return this.codepostalInfoEntreprise;
	}

	public void setCodepostalInfoEntreprise(String codepostalInfoEntreprise) {
		this.codepostalInfoEntreprise = codepostalInfoEntreprise;
	}

	@Column(name = "ville_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "pays_info_entreprise",table = "ta_info_entreprise", champEntite="villeInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getVilleInfoEntreprise() {
		return this.villeInfoEntreprise;
	}

	public void setVilleInfoEntreprise(String villeInfoEntreprise) {
		this.villeInfoEntreprise = villeInfoEntreprise;
	}

	@Column(name = "pays_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "pays_info_entreprise",table = "ta_info_entreprise", champEntite="paysInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getPaysInfoEntreprise() {
		return this.paysInfoEntreprise;
	}

	public void setPaysInfoEntreprise(String paysInfoEntreprise) {
		this.paysInfoEntreprise = paysInfoEntreprise;
	}

	@Column(name = "siret_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "siret_info_entreprise",table = "ta_info_entreprise", champEntite="siretInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getSiretInfoEntreprise() {
		return this.siretInfoEntreprise;
	}

	public void setSiretInfoEntreprise(String siretInfoEntreprise) {
		this.siretInfoEntreprise = siretInfoEntreprise;
	}

	@Column(name = "capital_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "capital_info_entreprise",table = "ta_info_entreprise", champEntite="capitalInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getCapitalInfoEntreprise() {
		return this.capitalInfoEntreprise;
	}

	public void setCapitalInfoEntreprise(String capitalInfoEntreprise) {
		this.capitalInfoEntreprise = capitalInfoEntreprise;
	}

	@Column(name = "ape_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "ape_info_entreprise",table = "ta_info_entreprise", champEntite="apeInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getApeInfoEntreprise() {
		return this.apeInfoEntreprise;
	}

	public void setApeInfoEntreprise(String apeInfoEntreprise) {
		this.apeInfoEntreprise = apeInfoEntreprise;
	}

	@Column(name = "tva_intra_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "tva_intra_info_entreprise",table = "ta_info_entreprise", champEntite="tvaIntraInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getTvaIntraInfoEntreprise() {
		return this.tvaIntraInfoEntreprise;
	}

	public void setTvaIntraInfoEntreprise(String tvaIntraInfoEntreprise) {
		this.tvaIntraInfoEntreprise = tvaIntraInfoEntreprise;
	}

	@Column(name = "tel_info_entreprise", length = 20)
	@LgrHibernateValidated(champBd = "tel_info_entreprise",table = "ta_info_entreprise", champEntite="telInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getTelInfoEntreprise() {
		return this.telInfoEntreprise;
	}

	public void setTelInfoEntreprise(String telInfoEntreprise) {
		this.telInfoEntreprise = telInfoEntreprise;
	}

	@Column(name = "fax_info_entreprise", length = 20)
	@LgrHibernateValidated(champBd = "fax_info_entreprise",table = "ta_info_entreprise", champEntite="faxInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getFaxInfoEntreprise() {
		return this.faxInfoEntreprise;
	}

	public void setFaxInfoEntreprise(String faxInfoEntreprise) {
		this.faxInfoEntreprise = faxInfoEntreprise;
	}

	@Column(name = "email_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "email_info_entreprise",table = "ta_info_entreprise", champEntite="emailInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getEmailInfoEntreprise() {
		return this.emailInfoEntreprise;
	}

	public void setEmailInfoEntreprise(String emailInfoEntreprise) {
		this.emailInfoEntreprise = emailInfoEntreprise;
	}

	@Column(name = "web_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "web_info_entreprise",table = "ta_info_entreprise", champEntite="webInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getWebInfoEntreprise() {
		return this.webInfoEntreprise;
	}

	public void setWebInfoEntreprise(String webInfoEntreprise) {
		this.webInfoEntreprise = webInfoEntreprise;
	}

	@Column(name = "rcs_info_entreprise", length = 100)
	@LgrHibernateValidated(champBd = "rcs_info_entreprise",table = "ta_info_entreprise", champEntite="rcsInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getRcsInfoEntreprise() {
		return this.rcsInfoEntreprise;
	}

	public void setRcsInfoEntreprise(String rcsInfoEntreprise) {
		this.rcsInfoEntreprise = rcsInfoEntreprise;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "datedeb_info_entreprise", length = 19)
	@LgrHibernateValidated(champBd = "datedeb_info_entreprise",table = "ta_info_entreprise", champEntite="datedebInfoEntreprise", clazz = TaInfoEntreprise.class)
	public Date getDatedebInfoEntreprise() {
		return this.datedebInfoEntreprise;
	}

	public void setDatedebInfoEntreprise(Date datedebInfoEntreprise) {
		this.datedebInfoEntreprise = datedebInfoEntreprise;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "datefin_info_entreprise", length = 19)
	@LgrHibernateValidated(champBd = "datefin_info_entreprise",table = "ta_info_entreprise", champEntite="datefinInfoEntreprise", clazz = TaInfoEntreprise.class)
	public Date getDatefinInfoEntreprise() {
		return this.datefinInfoEntreprise;
	}

	public void setDatefinInfoEntreprise(Date datefinInfoEntreprise) {
		this.datefinInfoEntreprise = datefinInfoEntreprise;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datedeb_reg_info_entreprise", length = 19)
	@LgrHibernateValidated(champBd = "datedeb_reg_info_entreprise",table = "ta_info_entreprise", champEntite="datedebRegInfoEntreprise", clazz = TaInfoEntreprise.class)
	public Date getDatedebRegInfoEntreprise() {
		return this.datedebRegInfoEntreprise;
	}

	public void setDatedebRegInfoEntreprise(Date datedebRegInfoEntreprise) {
		this.datedebRegInfoEntreprise = datedebRegInfoEntreprise;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datedeb_rel_info_entreprise", length = 19)
	@LgrHibernateValidated(champBd = "datedeb_rel_info_entreprise",table = "ta_info_entreprise", champEntite="datedebRelInfoEntreprise", clazz = TaInfoEntreprise.class)
	public Date getDatedebRelInfoEntreprise() {
		return this.datedebRelInfoEntreprise;
	}

	public void setDatedebRelInfoEntreprise(Date datedebRelInfoEntreprise) {
		this.datedebRelInfoEntreprise = datedebRelInfoEntreprise;
	}

	@Column(name = "codexo_info_entreprise", length = 2)
	@LgrHibernateValidated(champBd = "codexo_info_entreprise",table = "ta_info_entreprise", champEntite="codexoInfoEntreprise", clazz = TaInfoEntreprise.class)
	public String getCodexoInfoEntreprise() {
		return this.codexoInfoEntreprise;
	}

	public void setCodexoInfoEntreprise(String codexoInfoEntreprise) {
		this.codexoInfoEntreprise = codexoInfoEntreprise;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tiers")
	@LgrHibernateValidated(champBd = "id_tiers",table = "ta_tiers", champEntite="taTiers.idTiers", clazz = TaTiers.class)
	public TaTiers getTaTiers() {
		return this.taTiers;
	}

	public void setTaTiers(TaTiers taTiers) {
		this.taTiers = taTiers;
	}

	@Transient
	public String getAccise() {
//ejb
//		TaTiersDAO daoTiers = new TaTiersDAO();
//		TaTiers tiersEntreprise=null;
//		tiersEntreprise=daoTiers.findById(TaTiersDAO.C_ID_IDENTITE_ENTREPRISE_INT);
//		if(tiersEntreprise!=null && tiersEntreprise.getTaCompl()!=null)
//			return tiersEntreprise.getTaCompl().getAccise();
//		return null;
		return null;
	}

	
	@Column(name = "qui_cree", length = 50)
	public String getQuiCree() {
		return this.quiCree;
	}

	public void setQuiCree(String quiCreeInfoEntreprise) {
		this.quiCree = quiCreeInfoEntreprise;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_cree", length = 19)
	public Date getQuandCree() {
		return this.quandCree;
	}

	public void setQuandCree(Date quandCreeInfoEntreprise) {
		this.quandCree = quandCreeInfoEntreprise;
	}

	@Column(name = "qui_modif", length = 50)
	public String getQuiModif() {
		return this.quiModif;
	}

	public void setQuiModif(String quiModifInfoEntreprise) {
		this.quiModif = quiModifInfoEntreprise;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "quand_modif", length = 19)
	public Date getQuandModif() {
		return this.quandModif;
	}

	public void setQuandModif(Date quandModifInfoEntreprise) {
		this.quandModif = quandModifInfoEntreprise;
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

	@Version
	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}
	
	public Boolean changementDateDebutReglement(Date newDate){
		if(newDate==null)return false;
		return datedebRegInfoEntreprise==null || datedebRegInfoEntreprise.getDate()!=newDate.getDate();
	}

//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setNomInfoEntreprise(nomInfoEntreprise.toUpperCase());
		   this.setCodepostalInfoEntreprise(codepostalInfoEntreprise.toUpperCase());
		   this.setVilleInfoEntreprise(villeInfoEntreprise.toUpperCase());
		   this.setPaysInfoEntreprise(paysInfoEntreprise.toUpperCase());
		   this.setCodexoInfoEntreprise(codexoInfoEntreprise.toUpperCase());
	}



}
