package fr.legrain.article.model;

// Generated Sep 1, 2008 3:06:27 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.log4j.Logger;

import fr.legrain.general.model.TaObjetLgr;
import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaFamille generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_famille")
//@SequenceGenerator(name = "gen_famille", sequenceName = "num_id_famille", allocationSize = 1)

// Dima - Début
@NamedQueries(value = { 
		@NamedQuery(name=TaFamille.QN.FIND_BY_CODE_LIGHT, query="select new fr.legrain.article.dto.TaFamilleDTO(f.idFamille,f.codeFamille,f.libcFamille,f.liblFamille) from TaFamille f where f.codeFamille like :codeFamille order by f.codeFamille"),
		@NamedQuery(name=TaFamille.QN.FIND_ALL_LIGHT, query="select new fr.legrain.article.dto.TaFamilleDTO(f.idFamille,f.codeFamille,f.libcFamille,f.liblFamille) from TaFamille f order by f.codeFamille"),
		@NamedQuery(name=TaFamille.QN.FIND_LIGHT_CODE_T_TARIF, query="select new fr.legrain.article.dto.TaFamilleDTO(f.idFamille,f.codeFamille,f.libcFamille,f.liblFamille) "
				+ "  from TaFamille f where exists(select rp from TaRPrix rp join rp.taPrix p join p.taArticle aa join aa.taFamille fam join rp.taTTarif tt  where fam.idFamille=f.idFamille  and tt.codeTTarif like :codeTTarif)"
				+ "  order by f.codeFamille"),
		@NamedQuery(name=TaFamille.QN.FIND_LIGHT_CODE_T_TARIF_TIERS, query="select new fr.legrain.article.dto.TaFamilleDTO(f.idFamille,f.codeFamille,f.libcFamille,f.liblFamille) "
				+ "  from TaFamille f where exists(select rp from TaRPrixTiers rp join rp.taPrix p join p.taArticle aa join aa.taFamille fam   join rp.taTTarif tt join rp.taTiers tiers  where fam.idFamille=f.idFamille and tt.codeTTarif like :codeTTarif and tiers.codeTiers like :codeTiers)"
				+ "  order by f.codeFamille")
})
// Dima -  Fin

public class TaFamille extends TaObjetLgr   implements java.io.Serializable {

	private static final long serialVersionUID = -4897795823316741710L;
	
	// Dima - Début
	public static class QN {
		public static final String FIND_BY_CODE_LIGHT = "TaFamille.findByCodeLight";
		public static final String FIND_ALL_LIGHT = "TaFamille.findAllFamilleLight";
		public static final String FIND_LIGHT_CODE_T_TARIF = "TaFamille.findLightCodeTTarif";
		public static final String FIND_LIGHT_CODE_T_TARIF_TIERS = "TaFamille.findLightCodeTTarifTiers";
	}
	
	@Transient
	static Logger logger = Logger.getLogger(TaFamille.class.getName());

	// Dima -  Fin
	
	private int idFamille;
//	private String version;
	private String codeFamille;
	private String libcFamille;
	private String liblFamille;
//	private String quiCree;
//	private Date quandCree;
//	private String quiModif;
//	private Date quandModif;
//	private String ipAcces;
//	private Set<TaArticle> taArticles = new HashSet<TaArticle>(0);
	private Integer versionObj;

	public TaFamille() {
	}

	public TaFamille(int idFamille) {
		this.idFamille = idFamille;
	}

	public TaFamille(int idFamille, String codeFamille, String libcFamille,
			String liblFamille, String quiCreeFamille, Date quandCreeFamille,
			String quiModifFamille, Date quandModifFamille, String ipAcces,
			Set<TaArticle> taArticles) {
		this.idFamille = idFamille;
		this.codeFamille = codeFamille;
		this.libcFamille = libcFamille;
		this.liblFamille = liblFamille;
		this.quiCree = quiCreeFamille;
		this.quandCree = quandCreeFamille;
		this.quiModif = quiModifFamille;
		this.quandModif = quandModifFamille;
		this.ipAcces = ipAcces;
//		this.taArticles = taArticles;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_famille", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_famille",table = "ta_famille", champEntite="idFamille", clazz = TaFamille.class)
	public int getIdFamille() {
		return this.idFamille;
	}

	public void setIdFamille(int idFamille) {
		this.idFamille = idFamille;
	}

//	//@Version
//	@Column(name = "version", length = 20)
//	public String getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}
	
	@Version
	@Column(name = "version_obj", precision = 15)
	public Integer getVersionObj() {
		return this.versionObj;
	}

	public void setVersionObj(Integer versionObj) {
		this.versionObj = versionObj;
	}

	@Column(name = "code_famille", length = 20)
	@LgrHibernateValidated(champBd = "code_famille",table = "ta_famille", champEntite="codeFamille", clazz = TaFamille.class)
	public String getCodeFamille() {
		return this.codeFamille;
	}

	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}

	@Column(name = "libc_famille", length = 50)
	@LgrHibernateValidated(champBd = "libc_famille",table = "ta_famille", champEntite="libcFamille", clazz = TaFamille.class)
	public String getLibcFamille() {
		return this.libcFamille;
	}

	public void setLibcFamille(String libcFamille) {
		this.libcFamille = libcFamille;
	}

	@Column(name = "libl_famille")
	@LgrHibernateValidated(champBd = "libl_famille",table = "ta_famille", champEntite="liblFamille", clazz = TaFamille.class)
	public String getLiblFamille() {
		return this.liblFamille;
	}

	public void setLiblFamille(String liblFamille) {
		this.liblFamille = liblFamille;
	}

//	@Column(name = "qui_cree", length = 50)
//	public String getQuiCree() {
//		return this.quiCree;
//	}
//
//	public void setQuiCree(String quiCreeFamille) {
//		this.quiCree = quiCreeFamille;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_cree", length = 19)
//	public Date getQuandCree() {
//		return this.quandCree;
//	}
//
//	public void setQuandCree(Date quandCreeFamille) {
//		this.quandCree = quandCreeFamille;
//	}
//
//	@Column(name = "qui_modif", length = 50)
//	public String getQuiModif() {
//		return this.quiModif;
//	}
//
//	public void setQuiModif(String quiModifFamille) {
//		this.quiModif = quiModifFamille;
//	}
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "quand_modif", length = 19)
//	public Date getQuandModif() {
//		return this.quandModif;
//	}
//
//	public void setQuandModif(Date quandModifFamille) {
//		this.quandModif = quandModifFamille;
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

//	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "taFamille")
//	public Set<TaArticle> getTaArticles() {
//		return this.taArticles;
//	}
//
//	public void setTaArticles(Set<TaArticle> taArticles) {
//		this.taArticles = taArticles;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeFamille == null) ? 0 : codeFamille.hashCode());
		return result;
	}


}
