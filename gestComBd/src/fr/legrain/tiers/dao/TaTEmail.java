package fr.legrain.tiers.dao;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTEmail generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_T_EMAIL")
@SequenceGenerator(name = "GEN_T_EMAIL", sequenceName = "NUM_ID_T_EMAIL", allocationSize = 1)
public class TaTEmail implements java.io.Serializable {

	private int idTEmail;
	private String version;
	private String codeTEmail;
	private String liblTEmail;
	private String quiCreeTEmail;
	private Date quandCreeTEmail;
	private String quiModifTEmail;
	private Date quandModifTEmail;
	private String ipAcces;
	private Integer versionObj;
	private Set<TaEmail> taEmails = new HashSet<TaEmail>(0);

	public TaTEmail() {
	}

	public TaTEmail(int idTEmail) {
		this.idTEmail = idTEmail;
	}

	public TaTEmail(int idTEmail, String codeTEmail, String liblTEmail,
			String quiCreeTEmail, Date quandCreeTEmail, String quiModifTEmail,
			Date quandModifTEmail, String ipAcces, Integer versionObj,
			Set<TaEmail> taEmails) {
		this.idTEmail = idTEmail;
		this.codeTEmail = codeTEmail;
		this.liblTEmail = liblTEmail;
		this.quiCreeTEmail = quiCreeTEmail;
		this.quandCreeTEmail = quandCreeTEmail;
		this.quiModifTEmail = quiModifTEmail;
		this.quandModifTEmail = quandModifTEmail;
		this.ipAcces = ipAcces;
		this.versionObj = versionObj;
		this.taEmails = taEmails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_T_EMAIL")
	@Column(name = "ID_T_EMAIL", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_EMAIL",table = "TA_T_EMAIL",clazz = TaTEmail.class)
	public int getIdTEmail() {
		return this.idTEmail;
	}

	public void setIdTEmail(int idTEmail) {
		this.idTEmail = idTEmail;
	}

	
	@Column(name = "VERSION", length = 20)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "CODE_T_EMAIL", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_EMAIL",table = "TA_T_EMAIL",clazz = TaTEmail.class)
	public String getCodeTEmail() {
		return this.codeTEmail;
	}

	public void setCodeTEmail(String codeTEmail) {
		this.codeTEmail = codeTEmail;
	}

	@Column(name = "LIBL_T_EMAIL", length = 100)
	@LgrHibernateValidated(champEntite = "", champBd = "LIBL_T_EMAIL",table = "TA_T_EMAIL",clazz = TaTEmail.class)
	public String getLiblTEmail() {
		return this.liblTEmail;
	}

	public void setLiblTEmail(String liblTEmail) {
		this.liblTEmail = liblTEmail;
	}

	@Column(name = "QUI_CREE_T_EMAIL", length = 50)
	public String getQuiCreeTEmail() {
		return this.quiCreeTEmail;
	}

	public void setQuiCreeTEmail(String quiCreeTEmail) {
		this.quiCreeTEmail = quiCreeTEmail;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_CREE_T_EMAIL", length = 19)
	public Date getQuandCreeTEmail() {
		return this.quandCreeTEmail;
	}

	public void setQuandCreeTEmail(Date quandCreeTEmail) {
		this.quandCreeTEmail = quandCreeTEmail;
	}

	@Column(name = "QUI_MODIF_T_EMAIL", length = 50)
	public String getQuiModifTEmail() {
		return this.quiModifTEmail;
	}

	public void setQuiModifTEmail(String quiModifTEmail) {
		this.quiModifTEmail = quiModifTEmail;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "QUAND_MODIF_T_EMAIL", length = 19)
	public Date getQuandModifTEmail() {
		return this.quandModifTEmail;
	}

	public void setQuandModifTEmail(Date quandModifTEmail) {
		this.quandModifTEmail = quandModifTEmail;
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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "taTEmail")
	public Set<TaEmail> getTaEmails() {
		return this.taEmails;
	}

	public void setTaEmails(Set<TaEmail> taEmails) {
		this.taEmails = taEmails;
	}


//	@PrePersist
//	@PreUpdate
	public void beforePost ()throws Exception{
		this.setCodeTEmail(codeTEmail.toUpperCase());
	}
}
