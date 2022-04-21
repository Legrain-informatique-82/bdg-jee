package fr.legrain.tiers.dao;

// Generated Mar 25, 2009 10:06:49 AM by Hibernate Tools 3.2.0.CR1

import java.util.Calendar;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaCPaiement generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_C_PAIEMENT")
@SequenceGenerator(name = "GEN_C_PAIEMENT", sequenceName = "NUM_ID_C_PAIEMENT", allocationSize = 1)
@NamedQueries(value = { 
		@NamedQuery(name=TaCPaiement.QN.FIND_BY_TYPE, query="select a from TaCPaiement a where a.taTCPaiement.codeTCPaiement = ?")
		})
public class TaCPaiement implements java.io.Serializable {
	
	public static class QN {
		public static final String FIND_BY_TYPE = "TaCPaiement.findByType";
	}

	private int idCPaiement;
	private String version;
	private String codeCPaiement;
	private String libCPaiement;
	private Integer reportCPaiement=0;
	private Integer finMoisCPaiement=0;
	private TaTCPaiement taTCPaiement;
	private String quiCreeCPaiement;
	private Date quandCreeCPaiement;
	private String quiModifCPaiement;
	private Date quandModifCPaiement;
	private String ipAcces;
	private Integer versionObj;
	private Set<TaTiers> taTierses = new HashSet<TaTiers>(0);
	private Boolean defaut = true;

	public TaCPaiement() {
	}

	public TaCPaiement(int idCPaiement) {
		this.idCPaiement = idCPaiement;
	}

	public TaCPaiement(int idCPaiement, String codeCPaiement,
			String libCPaiement, Integer reportCPaiement,
			Integer finMoisCPaiement, TaTCPaiement taTCPaiement, String quiCreeCPaiement,
			Date quandCreeCPaiement, String quiModifCPaiement,
			Date quandModifCPaiement, String ipAcces, Integer versionObj,
			Set<TaTiers> taTierses) {
		this.idCPaiement = idCPaiement;
		this.codeCPaiement = codeCPaiement;
		this.libCPaiement = libCPaiement;
		this.reportCPaiement = reportCPaiement;
		this.finMoisCPaiement = finMoisCPaiement;
		this.taTCPaiement = taTCPaiement;
		this.quiCreeCPaiement = quiCreeCPaiement;
		this.quandCreeCPaiement = quandCreeCPaiement;
		this.quiModifCPaiement = quiModifCPaiement;
		this.quandModifCPaiement = quandModifCPaiement;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.taTierses = taTierses;
	}
	
	@Transient
	public Boolean getDefaut() {
		if(taTCPaiement!=null && taTCPaiement.getTaCPaiement()!=null)
		  return taTCPaiement.getTaCPaiement().equals(this);
		else return false;
	}

	public void setDefaut(Boolean defaut) {
		this.defaut = defaut;
		if(defaut!=null && defaut) {
			if(taTCPaiement!=null && taTCPaiement.getTaCPaiement()!=null)
				taTCPaiement.setTaCPaiement(this);
		} else {
			if(taTCPaiement!=null && taTCPaiement.getTaCPaiement()==null) {
				this.defaut = true; //il n'y a pas de valeur par defaut pour ce type, donc on force cette valeur (condition de paiement) à etre celle par defaut
				taTCPaiement.setTaCPaiement(this);
			}
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_C_PAIEMENT")
	@Column(name = "ID_C_PAIEMENT", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_C_PAIEMENT",table = "TA_C_PAIEMENT",clazz = TaCPaiement.class)
	public int getIdCPaiement() {
		return this.idCPaiement;
	}

	public void setIdCPaiement(int idCPaiement) {
		this.idCPaiement = idCPaiement;
	}

	
	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "CODE_C_PAIEMENT", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_C_PAIEMENT",table = "TA_C_PAIEMENT",clazz = TaCPaiement.class)
	public String getCodeCPaiement() {
		return this.codeCPaiement;
	}

	public void setCodeCPaiement(String codeCPaiement) {
		this.codeCPaiement = codeCPaiement;
	}

	@Column(name = "LIB_C_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "LIB_C_PAIEMENT",table = "TA_C_PAIEMENT",clazz = TaCPaiement.class)
	public String getLibCPaiement() {
		return this.libCPaiement;
	}

	public void setLibCPaiement(String libCPaiement) {
		this.libCPaiement = libCPaiement;
	}

	@Column(name = "REPORT_C_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "REPORT_C_PAIEMENT",table = "TA_C_PAIEMENT",clazz = TaCPaiement.class)
	public Integer getReportCPaiement() {
		return this.reportCPaiement;
	}

	public void setReportCPaiement(Integer reportCPaiement) {
		this.reportCPaiement = reportCPaiement;
	}

	@Column(name = "FIN_MOIS_C_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "FIN_MOIS_C_PAIEMENT",table = "TA_C_PAIEMENT",clazz = TaCPaiement.class)
	public Integer getFinMoisCPaiement() {
		return this.finMoisCPaiement;
	}

	public void setFinMoisCPaiement(Integer finMoisCPaiement) {
		this.finMoisCPaiement = finMoisCPaiement;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "ID_T_C_PAIEMENT")
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_C_PAIEMENT",table = "TA_T_C_PAIEMENT",clazz = TaTCPaiement.class)
	public TaTCPaiement getTaTCPaiement() {
		return this.taTCPaiement;
	}

	public void setTaTCPaiement(TaTCPaiement taTCPaiement) {
		this.taTCPaiement = taTCPaiement;
	}

	@Column(name = "QUI_CREE_C_PAIEMENT", length = 50)
	public String getQuiCreeCPaiement() {
		return this.quiCreeCPaiement;
	}

	public void setQuiCreeCPaiement(String quiCreeCPaiement) {
		this.quiCreeCPaiement = quiCreeCPaiement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_C_PAIEMENT", length = 19)
	public Date getQuandCreeCPaiement() {
		return this.quandCreeCPaiement;
	}

	public void setQuandCreeCPaiement(Date quandCreeCPaiement) {
		this.quandCreeCPaiement = quandCreeCPaiement;
	}

	@Column(name = "QUI_MODIF_C_PAIEMENT", length = 50)
	public String getQuiModifCPaiement() {
		return this.quiModifCPaiement;
	}

	public void setQuiModifCPaiement(String quiModifCPaiement) {
		this.quiModifCPaiement = quiModifCPaiement;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_C_PAIEMENT", length = 19)
	public Date getQuandModifCPaiement() {
		return this.quandModifCPaiement;
	}

	public void setQuandModifCPaiement(Date quandModifCPaiement) {
		this.quandModifCPaiement = quandModifCPaiement;
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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taCPaiement")
	public Set<TaTiers> getTaTierses() {
		return this.taTierses;
	}

	public void setTaTierses(Set<TaTiers> taTierses) {
		this.taTierses = taTierses;
	}
	
	/**
	 * Calcule une date à partir des paramètres de la condtion de paiement<br><br>
	 * Report - nb jour net => combien de jour en plus pour payer (ex: 30j)<br>
     * Fin de mois - facultatif => on applique d'abord le repport puis date dans le mois en cours avant laquelle il faut payer<br>
     * Les 2 se cumulents.<br>
     * Ex : <br>
     * facture le 05/01 + condition r30j => avant le 04/02<br>
     * facture le 05/01 + condition r30j,fdm31 => r30j => 04/02 => fdm31 => 28/02<br>
	 * @param d - date d'origine
	 * @return 
	 */
	public Date calculeNouvelleDate(Date d) {
		if(d!=null){
		Date nouvelleDate = d;
		Calendar c = Calendar.getInstance();
		c.setTime(nouvelleDate);
		if(reportCPaiement!=null && reportCPaiement>0) {
			c.add(Calendar.DAY_OF_MONTH, reportCPaiement);
			//nouvelleDate = LibDate.incrementDate(d, reportCPaiement, 0, 0);
		}
		if(finMoisCPaiement!=null && finMoisCPaiement>0) {
			if(finMoisCPaiement>31)finMoisCPaiement=31;
			if(c.get(Calendar.DAY_OF_MONTH)>finMoisCPaiement) {
				c.roll(Calendar.MONTH, true);
			}
			if(finMoisCPaiement>c.getActualMaximum(Calendar.DAY_OF_MONTH))finMoisCPaiement=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, finMoisCPaiement);
		}
		//return nouvelleDate;	
		return c.getTime();
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeCPaiement == null) ? 0 : codeCPaiement.hashCode());
		result = prime * result + ((defaut == null) ? 0 : defaut.hashCode());
		result = prime
				* result
				+ ((finMoisCPaiement == null) ? 0 : finMoisCPaiement.hashCode());
		result = prime * result + idCPaiement;
		result = prime * result + ((ipAcces == null) ? 0 : ipAcces.hashCode());
		result = prime * result
				+ ((libCPaiement == null) ? 0 : libCPaiement.hashCode());
		result = prime
				* result
				+ ((quandCreeCPaiement == null) ? 0 : quandCreeCPaiement
						.hashCode());
		result = prime
				* result
				+ ((quandModifCPaiement == null) ? 0 : quandModifCPaiement
						.hashCode());
		result = prime
				* result
				+ ((quiCreeCPaiement == null) ? 0 : quiCreeCPaiement.hashCode());
		result = prime
				* result
				+ ((quiModifCPaiement == null) ? 0 : quiModifCPaiement
						.hashCode());
		result = prime * result
				+ ((reportCPaiement == null) ? 0 : reportCPaiement.hashCode());
		result = prime * result
				+ ((taTCPaiement == null) ? 0 : taTCPaiement.hashCode());
		result = prime * result
				+ ((taTierses == null) ? 0 : taTierses.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result
				+ ((versionObj == null) ? 0 : versionObj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaCPaiement other = (TaCPaiement) obj;
		if (codeCPaiement == null) {
			if (other.codeCPaiement != null)
				return false;
		} else if (!codeCPaiement.equals(other.codeCPaiement))
			return false;
		if (defaut == null) {
			if (other.defaut != null)
				return false;
		} else if (!defaut.equals(other.defaut))
			return false;
		if (finMoisCPaiement == null) {
			if (other.finMoisCPaiement != null)
				return false;
		} else if (!finMoisCPaiement.equals(other.finMoisCPaiement))
			return false;
		if (idCPaiement != other.idCPaiement)
			return false;
		if (ipAcces == null) {
			if (other.ipAcces != null)
				return false;
		} else if (!ipAcces.equals(other.ipAcces))
			return false;
		if (libCPaiement == null) {
			if (other.libCPaiement != null)
				return false;
		} else if (!libCPaiement.equals(other.libCPaiement))
			return false;
		if (quandCreeCPaiement == null) {
			if (other.quandCreeCPaiement != null)
				return false;
		} else if (!quandCreeCPaiement.equals(other.quandCreeCPaiement))
			return false;
		if (quandModifCPaiement == null) {
			if (other.quandModifCPaiement != null)
				return false;
		} else if (!quandModifCPaiement.equals(other.quandModifCPaiement))
			return false;
		if (quiCreeCPaiement == null) {
			if (other.quiCreeCPaiement != null)
				return false;
		} else if (!quiCreeCPaiement.equals(other.quiCreeCPaiement))
			return false;
		if (quiModifCPaiement == null) {
			if (other.quiModifCPaiement != null)
				return false;
		} else if (!quiModifCPaiement.equals(other.quiModifCPaiement))
			return false;
		if (reportCPaiement == null) {
			if (other.reportCPaiement != null)
				return false;
		} else if (!reportCPaiement.equals(other.reportCPaiement))
			return false;
		if (taTCPaiement == null) {
			if (other.taTCPaiement != null)
				return false;
		} else if (!taTCPaiement.equals(other.taTCPaiement))
			return false;
		return true;
	}

}
