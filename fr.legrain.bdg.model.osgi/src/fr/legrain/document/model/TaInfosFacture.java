package fr.legrain.document.model;

// Generated Apr 7, 2009 3:27:23 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

import org.apache.log4j.Logger;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.legrain.document.dto.IInfosDocumentTiers;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaInfosFacture generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_infos_facture")
//@SequenceGenerator(name = "gen_infos_facture", sequenceName = "num_id_infos_facture", allocationSize = 1)
public class TaInfosFacture extends SWTInfosDocument implements java.io.Serializable, IInfosDocumentTiers {

	private static final long serialVersionUID = 3951473546734847952L;
	
	//	private int idInfosDocument;
//	private String version;
	private TaFacture taDocument;
	private String adresse1;
	private String adresse2;
	private String adresse3;
	private String codepostal;
	private String ville;
	private String pays;
	private String adresse1Liv;
	private String adresse2Liv;
	private String adresse3Liv;
	private String codepostalLiv;
	private String villeLiv;
	private String paysLiv;
	private String codeCompta;
	private String compte;
	private String nomTiers;
	private String prenomTiers;
	private String surnomTiers;
	private String codeTCivilite;
	private String codeTEntite;
	private String tvaIComCompl;
	private String codeCPaiement;
	private String libCPaiement;
	private Integer reportCPaiement;
	private Integer finMoisCPaiement;
	private String nomEntreprise;
	private String codeTTvaDoc;
//	private String quiCreeInfosDocument;
//	private Date quandCreeInfosDocument;
//	private String quiModifInfosDocument;
//	private Date quandModifInfosDocument;
//	private String ipAcces;
//	private Integer versionObj;

	@Transient
	static Logger logger = Logger.getLogger(TaInfosFacture.class.getName());
	public TaInfosFacture() {
	}

	public TaInfosFacture(int idInfosFacture) {
		this.idInfosDocument = idInfosFacture;
	}

	public TaInfosFacture(int idInfosFacture, TaFacture taFacture,
			String adresse1, String adresse2, String adresse3,
			String codepostal, String ville, String pays, String adresse1Liv,
			String adresse2Liv, String adresse3Liv, String codepostalLiv,
			String villeLiv, String paysLiv, String codeCompta, String compte,
			String nomTiers, String prenomTiers, String surnomTiers,
			String codeTCivilite, String codeTEntite, String tvaIComCompl,
			String codeCPaiement, String libCPaiement, Integer reportCPaiement,
			Integer finMoisCPaiement, String quiCreeInfosFacture,
			Date quandCreeInfosFacture, String quiModifInfosFacture,
			Date quandModifInfosFacture, String ipAcces, Integer versionObj,
			String nomEntreprise) {
		this.idInfosDocument = idInfosFacture;
		this.taDocument = taFacture;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.adresse3 = adresse3;
		this.codepostal = codepostal;
		this.ville = ville;
		this.pays = pays;
		this.adresse1Liv = adresse1Liv;
		this.adresse2Liv = adresse2Liv;
		this.adresse3Liv = adresse3Liv;
		this.codepostalLiv = codepostalLiv;
		this.villeLiv = villeLiv;
		this.paysLiv = paysLiv;
		this.codeCompta = codeCompta;
		this.compte = compte;
		this.nomTiers = nomTiers;
		this.prenomTiers = prenomTiers;
		this.surnomTiers = surnomTiers;
		this.codeTCivilite = codeTCivilite;
		this.codeTEntite = codeTEntite;
		this.tvaIComCompl = tvaIComCompl;
		this.codeCPaiement = codeCPaiement;
		this.libCPaiement = libCPaiement;
		this.reportCPaiement = reportCPaiement;
		this.finMoisCPaiement = finMoisCPaiement;
		this.quiCree = quiCreeInfosFacture;
		this.quandCree = quandCreeInfosFacture;
		this.quiModif = quiModifInfosFacture;
		this.quandModif = quandModifInfosFacture;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.nomEntreprise = nomEntreprise;
	}

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_infos_facture")
//	@Column(name = "id_infos_document", unique = true, nullable = false)
//	@LgrHibernateValidated(champ = "id_infos_document",table = "ta_infos_facture", champEntite="xxxxxxxxxxxxxxx", clazz = TaInfosFacture.class)
//	public int getIdInfosDocument() {
//		return this.idInfosDocument;
//	}
//
//	public void setIdInfosDocument(int idInfosFacture) {
//		this.idInfosDocument = idInfosFacture;
//	}
//
//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},  orphanRemoval=true)
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "taInfosDocument",  orphanRemoval=true)
	@JoinColumn(name = "id_document",unique = true)
//	@LgrHibernateValidated(champBd = "id_document",table = "ta_facture", champEntite="taDocument.idDocument", clazz = TaFacture.class)
	@XmlElement
	@JsonIgnore
	@XmlInverseReference(mappedBy="taInfosDocument")
	public TaFacture getTaDocument() {
		return this.taDocument;
	}

	public void setTaDocument(TaFacture taFacture) {
		this.taDocument = taFacture;
	}
	
	public void setTaDocumentGeneral(SWTDocument taDocument) {
		this.taDocument = (TaFacture) taDocument;
	}

	@Column(name = "adresse1", length = 100)
	@LgrHibernateValidated(champBd = "adresse1",table = "ta_infos_facture", champEntite="adresse1", clazz = TaInfosFacture.class)
	public String getAdresse1() {
		return this.adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	@Column(name = "adresse2", length = 100)
	@LgrHibernateValidated(champBd = "adresse2",table = "ta_infos_facture", champEntite="adresse2", clazz = TaInfosFacture.class)
	public String getAdresse2() {
		return this.adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	@Column(name = "adresse3", length = 100)
	@LgrHibernateValidated(champBd = "adresse3",table = "ta_infos_facture", champEntite="adresse3", clazz = TaInfosFacture.class)
	public String getAdresse3() {
		return this.adresse3;
	}

	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}

	@Column(name = "codepostal", length = 25)
	@LgrHibernateValidated(champBd = "codepostal",table = "ta_infos_facture", champEntite="codepostal", clazz = TaInfosFacture.class)
	public String getCodepostal() {
		return this.codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	@Column(name = "ville", length = 100)
	@LgrHibernateValidated(champBd = "ville",table = "ta_infos_facture", champEntite="ville", clazz = TaInfosFacture.class)
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Column(name = "pays", length = 100)
	@LgrHibernateValidated(champBd = "pays",table = "ta_infos_facture", champEntite="pays", clazz = TaInfosFacture.class)
	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Column(name = "adresse1_liv", length = 100)
	@LgrHibernateValidated(champBd = "adresse1_liv",table = "ta_infos_facture", champEntite="adresse1Liv", clazz = TaInfosFacture.class)
	public String getAdresse1Liv() {
		return this.adresse1Liv;
	}

	public void setAdresse1Liv(String adresse1Liv) {
		this.adresse1Liv = adresse1Liv;
	}

	@Column(name = "adresse2_liv", length = 100)
	@LgrHibernateValidated(champBd = "adresse2_liv",table = "ta_infos_facture", champEntite="adresse2Liv", clazz = TaInfosFacture.class)
	public String getAdresse2Liv() {
		return this.adresse2Liv;
	}

	public void setAdresse2Liv(String adresse2Liv) {
		this.adresse2Liv = adresse2Liv;
	}

	@Column(name = "adresse3_liv", length = 100)
	@LgrHibernateValidated(champBd = "adresse3_liv",table = "ta_infos_facture", champEntite="adresse3Liv", clazz = TaInfosFacture.class)
	public String getAdresse3Liv() {
		return this.adresse3Liv;
	}

	public void setAdresse3Liv(String adresse3Liv) {
		this.adresse3Liv = adresse3Liv;
	}

	@Column(name = "codepostal_liv", length = 25)
	@LgrHibernateValidated(champBd = "codepostal_liv",table = "ta_infos_facture", champEntite="codepostalLiv", clazz = TaInfosFacture.class)
	public String getCodepostalLiv() {
		return this.codepostalLiv;
	}

	public void setCodepostalLiv(String codepostalLiv) {
		this.codepostalLiv = codepostalLiv;
	}

	@Column(name = "ville_liv", length = 100)
	@LgrHibernateValidated(champBd = "ville_liv",table = "ta_infos_facture", champEntite="villeLiv", clazz = TaInfosFacture.class)
	public String getVilleLiv() {
		return this.villeLiv;
	}

	public void setVilleLiv(String villeLiv) {
		this.villeLiv = villeLiv;
	}

	@Column(name = "pays_liv", length = 100)
	@LgrHibernateValidated(champBd = "pays_liv",table = "ta_infos_facture", champEntite="paysLiv", clazz = TaInfosFacture.class)
	public String getPaysLiv() {
		return this.paysLiv;
	}

	public void setPaysLiv(String paysLiv) {
		this.paysLiv = paysLiv;
	}

	@Column(name = "code_compta", length = 8)
	@LgrHibernateValidated(champBd = "code_compta",table = "ta_infos_facture", champEntite="codeCompta", clazz = TaInfosFacture.class)
	public String getCodeCompta() {
		return this.codeCompta;
	}

	public void setCodeCompta(String codeCompta) {
		this.codeCompta = codeCompta;
	}

	@Column(name = "compte", length = 8)
	@LgrHibernateValidated(champBd = "compte",table = "ta_infos_facture", champEntite="compte", clazz = TaInfosFacture.class)
	public String getCompte() {
		return this.compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	@Column(name = "nom_tiers", length = 100)
	@LgrHibernateValidated(champBd = "nom_tiers",table = "ta_infos_facture", champEntite="nomTiers", clazz = TaInfosFacture.class)
	public String getNomTiers() {
		return this.nomTiers;
	}

	public void setNomTiers(String nomTiers) {
		this.nomTiers = nomTiers;
	}

	@Column(name = "prenom_tiers", length = 100)
	@LgrHibernateValidated(champBd = "prenom_tiers",table = "ta_infos_facture", champEntite="prenomTiers", clazz = TaInfosFacture.class)
	public String getPrenomTiers() {
		return this.prenomTiers;
	}

	public void setPrenomTiers(String prenomTiers) {
		this.prenomTiers = prenomTiers;
	}

	@Column(name = "surnom_tiers", length = 20)
	@LgrHibernateValidated(champBd = "surnom_tiers",table = "ta_infos_facture", champEntite="surnomTiers", clazz = TaInfosFacture.class)
	public String getSurnomTiers() {
		return this.surnomTiers;
	}

	public void setSurnomTiers(String surnomTiers) {
		this.surnomTiers = surnomTiers;
	}

	@Column(name = "code_t_civilite", length = 20)
	@LgrHibernateValidated(champBd = "code_t_civilite",table = "ta_infos_facture", champEntite="codeTCivilite", clazz = TaInfosFacture.class)
	public String getCodeTCivilite() {
		return this.codeTCivilite;
	}

	public void setCodeTCivilite(String codeTCivilite) {
		this.codeTCivilite = codeTCivilite;
	}

	@Column(name = "code_t_entite", length = 20)
	@LgrHibernateValidated(champBd = "code_t_entite",table = "ta_infos_facture", champEntite="codeTEntite", clazz = TaInfosFacture.class)
	public String getCodeTEntite() {
		return this.codeTEntite;
	}

	public void setCodeTEntite(String codeTEntite) {
		this.codeTEntite = codeTEntite;
	}

	@Column(name = "tva_i_com_compl", length = 50)
	@LgrHibernateValidated(champBd = "tva_i_com_compl",table = "ta_infos_facture", champEntite="tvaIComCompl", clazz = TaInfosFacture.class)
	public String getTvaIComCompl() {
		return this.tvaIComCompl;
	}

	public void setTvaIComCompl(String tvaIComCompl) {
		this.tvaIComCompl = tvaIComCompl;
	}

	@Column(name = "code_c_paiement", length = 20)
	@LgrHibernateValidated(champBd = "code_c_paiement",table = "ta_infos_facture", champEntite="codeCPaiement", clazz = TaInfosFacture.class)
	public String getCodeCPaiement() {
		return this.codeCPaiement;
	}

	public void setCodeCPaiement(String codeCPaiement) {
		this.codeCPaiement = codeCPaiement;
	}

	@Column(name = "lib_c_paiement")
	@LgrHibernateValidated(champBd = "lib_c_paiement",table = "ta_infos_facture", champEntite="libCPaiement", clazz = TaInfosFacture.class)
	public String getLibCPaiement() {
		return this.libCPaiement;
	}

	public void setLibCPaiement(String libCPaiement) {
		this.libCPaiement = libCPaiement;
	}

	@Column(name = "report_c_paiement")
	@LgrHibernateValidated(champBd = "report_c_paiement",table = "ta_infos_facture", champEntite="reportCPaiement", clazz = TaInfosFacture.class)
	public Integer getReportCPaiement() {
		return this.reportCPaiement;
	}

	public void setReportCPaiement(Integer reportCPaiement) {
		this.reportCPaiement = reportCPaiement;
	}

	@Column(name = "fin_mois_c_paiement")
	@LgrHibernateValidated(champBd = "fin_mois_c_paiement",table = "ta_infos_facture", champEntite="finMoisCPaiement", clazz = TaInfosFacture.class)
	public Integer getFinMoisCPaiement() {
		return this.finMoisCPaiement;
	}

	public void setFinMoisCPaiement(Integer finMoisCPaiement) {
		this.finMoisCPaiement = finMoisCPaiement;
	}

	@Column(name = "nom_entreprise")
	@LgrHibernateValidated(champBd = "nom_entreprise",table = "ta_infos_facture", champEntite="nomEntreprise", clazz = TaInfosFacture.class)
	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	
	@Column(name = "code_t_tva_doc")
	@LgrHibernateValidated(champBd = "code_t_tva_doc",table = "ta_infos_facture", champEntite="codeTTvaDoc", clazz = TaInfosFacture.class)
	public String getCodeTTvaDoc() {
		return codeTTvaDoc;
	}

	public void setCodeTTvaDoc(String codeTTvaDoc) {
		this.codeTTvaDoc = codeTTvaDoc;
	}
	
//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCreeInfosDocument() {
//		return this.quiCreeInfosDocument;
//	}
//
//	public void setQuiCreeInfosDocument(String quiCreeInfosFacture) {
//		this.quiCreeInfosDocument = quiCreeInfosFacture;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCreeInfosDocument() {
//		return this.quandCreeInfosDocument;
//	}
//
//	public void setQuandCreeInfosDocument(Date quandCreeInfosFacture) {
//		this.quandCreeInfosDocument = quandCreeInfosFacture;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModifInfosDocument() {
//		return this.quiModifInfosDocument;
//	}
//
//	public void setQuiModifInfosDocument(String quiModifInfosFacture) {
//		this.quiModifInfosDocument = quiModifInfosFacture;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif_infos_document", length = 19)
//	public Date getQuandModifInfosDocument() {
//		return this.quandModifInfosDocument;
//	}
//
//	public void setQuandModifInfosDocument(Date quandModifInfosFacture) {
//		this.quandModifInfosDocument = quandModifInfosFacture;
//	}
//
//	@Column(name = "ip_acces", length = 50)
//	public String getIpAcces() {
//		return this.ipAcces;
//	}
//
//	public void setIpAcces(String ipAcces) {
//		this.ipAcces = ipAcces;
//	}

//	@Version
//	@Column(name = "version_obj")
//	public Integer getVersionObj() {
//		return this.versionObj;
//	}
//
//	public void setVersionObj(Integer versionObj) {
//		this.versionObj = versionObj;
//	}

	/**
	 * Vide toutes les infos concernant le tiers
	 */
	public void clear() {
		setAdresse1(null);
		setAdresse2(null);
		setAdresse3(null);
		setCodepostal(null);
		setVille(null);
		setPays(null);
		setAdresse1Liv(null);
		setAdresse2Liv(null);
		setAdresse3Liv(null);
		setCodepostalLiv(null);
		setVilleLiv(null);
		setPaysLiv(null);
		setCodeCompta(null);
		setCompte(null);
		setNomTiers(null);
		setPrenomTiers(null);
		setSurnomTiers(null);
		setCodeTCivilite(null);
		setCodeTEntite(null);
		setTvaIComCompl(null);
		setCodeCPaiement(null);
		setLibCPaiement(null);
		setReportCPaiement(null);
		setFinMoisCPaiement(null);
		setNomEntreprise(null);
		setCodeTTvaDoc(null);
	}


	public TaInfosFacture clone() {
		TaInfosFacture infos = new TaInfosFacture();
		try {
//			infos = (TaInfosFacture)super.clone();
			infos.setIdInfosDocument(0);
			infos.setVersion(version);
			infos.setTaDocument(taDocument);
			infos.setAdresse1(adresse1);
			infos.setAdresse2(adresse2);
			infos.setAdresse3(adresse3);
			infos.setCodepostal(codepostal);
			infos.setVille(ville);
			infos.setPays(pays);
			infos.setAdresse1Liv(adresse1Liv);
			infos.setAdresse2Liv(adresse2Liv);
			infos.setAdresse3Liv(adresse3Liv);
			infos.setCodepostalLiv(codepostalLiv);
			infos.setVilleLiv(villeLiv);
			infos.setPaysLiv(paysLiv);
			infos.setCodeCompta(codeCompta);
			infos.setCompte(compte);
			infos.setNomTiers(nomTiers);
			infos.setPrenomTiers(prenomTiers);
			infos.setSurnomTiers(surnomTiers);
			infos.setCodeTCivilite(codeTCivilite);
			infos.setCodeTEntite(codeTEntite);
			infos.setTvaIComCompl(tvaIComCompl);
			infos.setCodeCPaiement(codeCPaiement);
			infos.setLibCPaiement(libCPaiement);
			infos.setReportCPaiement(reportCPaiement);
			infos.setFinMoisCPaiement(finMoisCPaiement);
			infos.setQuiCree(quiCree);
			infos.setQuandCree(quandCree);
			infos.setQuiModif(quiModif);
			infos.setQuandModif(quandModif);
			infos.setIpAcces(ipAcces);
			infos.setVersionObj(versionObj);
			infos.setNomEntreprise(nomEntreprise);
			infos.setCodeTTvaDoc(codeTTvaDoc);
		} catch(Exception cnse) {
			logger.error("",cnse);
		}
		// on renvoie le clone
		return infos;
	}

}