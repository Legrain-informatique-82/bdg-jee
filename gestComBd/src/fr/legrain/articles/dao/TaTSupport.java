package fr.legrain.articles.dao;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTNote generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TA_T_SUPPORT")
@SequenceGenerator(name = "GEN_T_SUPPORT", sequenceName = "NUM_ID_T_SUPPORT", allocationSize = 1)
public class TaTSupport implements java.io.Serializable {

	private int idTSupport;
	private String codeTSupport;
	private String types;
//	private Set<TaRTSupport> taRTSupports = new HashSet<TaRTSupport>(0);
	private Set<TaArticle> taArticles = new HashSet<TaArticle>(0);
	
	public TaTSupport() {
	}

	public TaTSupport(int idTArticle) {
		this.idTSupport = idTArticle;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_T_SUPPORT")
	@Column(name = "ID_T_SUPPORT", unique = true, nullable = false)
	@LgrHibernateValidated(champEntite = "", champBd = "ID_T_SUPPORT",table = "TA_T_SUPPORT",clazz = TaTSupport.class)
	public int getIdTSupport() {
		return this.idTSupport;
	}

	public void setIdTSupport(int idTArticle) {
		this.idTSupport = idTArticle;
	}


	@Column(name = "CODE_T_SUPPORT", length = 20)
	@LgrHibernateValidated(champEntite = "", champBd = "CODE_T_SUPPORT",table = "TA_T_SUPPORT",clazz = TaTSupport.class)
	public String getCodeTSupport() {
		return this.codeTSupport;
	}

	public void setCodeTSupport(String codeTSupport) {
		this.codeTSupport = codeTSupport;
	}

//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taTSupport")
//	public Set<TaRTSupport> getTaRTSupports() {
//		return this.taRTSupports;
//	}
//
//	public void setTaRTSupports(Set<TaRTSupport> taRTSupports) {
//		this.taRTSupports = taRTSupports;
//	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TA_R_T_SUPPORT",
			joinColumns = {@JoinColumn(name = "ID_T_SUPPORT")},inverseJoinColumns = {@JoinColumn(name = "ID_ARTICLE")})
	public Set<TaArticle> getTaArticles(){
		return this.taArticles;
	}

	public void setTaArticles(Set<TaArticle> taArticles) {
		this.taArticles = taArticles;
	}
	
	@Column(name = "TYPES", length = 1)
	@LgrHibernateValidated(champEntite = "", champBd = "TYPES",table = "TA_T_SUPPORT",clazz = TaTSupport.class)
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
}
