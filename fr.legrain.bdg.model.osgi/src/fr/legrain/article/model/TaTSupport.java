package fr.legrain.article.model;

// Generated Mar 24, 2009 4:44:04 PM by Hibernate Tools 3.2.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.legrain.validator.LgrHibernateValidated;

/**
 * TaTNote generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_t_support")
//@SequenceGenerator(name = "gen_t_support", sequenceName = "num_id_t_support", allocationSize = 1)
public class TaTSupport implements java.io.Serializable {

	private static final long serialVersionUID = -3763010905466612713L;
	
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_t_support", unique = true, nullable = false)
	@LgrHibernateValidated(champBd = "id_t_support",table = "ta_t_support", champEntite="idTSupport", clazz = TaTSupport.class)
	public int getIdTSupport() {
		return this.idTSupport;
	}

	public void setIdTSupport(int idTArticle) {
		this.idTSupport = idTArticle;
	}


	@Column(name = "code_t_support", length = 20)
	@LgrHibernateValidated(champBd = "code_t_support",table = "ta_t_support", champEntite="codeTSupport", clazz = TaTSupport.class)
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
	@JoinTable(name = "ta_r_t_support",
			joinColumns = {@JoinColumn(name = "id_t_support")},inverseJoinColumns = {@JoinColumn(name = "id_article")})
	public Set<TaArticle> getTaArticles(){
		return this.taArticles;
	}

	public void setTaArticles(Set<TaArticle> taArticles) {
		this.taArticles = taArticles;
	}
	
	@Column(name = "types", length = 1)
	@LgrHibernateValidated(champBd = "types",table = "ta_t_support", champEntite="types", clazz = TaTSupport.class)
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
}
