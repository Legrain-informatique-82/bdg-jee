package fr.legrain.supportNDD.dao;

// Generated 11 août 2009 15:52:23 by Hibernate Tools 3.2.4.GA

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.legrain.SupportAbon.dao.TaSupportAbon;
import fr.legrain.SupportAbonLegrain.dao.TaIpFixe;
import fr.legrain.SupportAbonLegrain.dao.TaRegistrar;
import fr.legrain.SupportAbonLegrain.dao.TaServeur;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaWlgr generated by hbm2java
 */
@Entity
@Table(name = "ta_ndd")
public  class TaNDD extends TaSupportAbon implements java.io.Serializable {


	private TaServeur taServeur;
	private String nomDomaine;
	private TaRegistrar taRegistrar;
	private TaIpFixe taIpFixe;
	private String idTitulaire;
	private String passeword;
	private String question;
	private String reponse;
	private String authInfo;
	
	public TaNDD() {
	}

	


public TaNDD(TaServeur taServeur, String domaine, TaRegistrar taRegistrar,
			TaIpFixe taIpFixe, String idTitulaire, String passeword,
			String question, String reponse, String authInfo) {
		super();
		this.taServeur = taServeur;
		this.nomDomaine = domaine;
		this.taRegistrar = taRegistrar;
		this.taIpFixe = taIpFixe;
		this.idTitulaire = idTitulaire;
		this.passeword = passeword;
		this.question = question;
		this.reponse = reponse;
		this.authInfo = authInfo;
	}




@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
@JoinColumn(name = "id_serveur")
@LgrHibernateValidated(champEntite = "", champBd = "id_serveur",table = "ta_serveur",clazz = TaServeur.class)
public TaServeur getTaServeur() {
	return taServeur;
}


public void setTaServeur(TaServeur taServeur) {
	this.taServeur = taServeur;
}


@Column(name = "nom_domaine", length = 255)
@LgrHibernateValidated(champEntite = "", champBd = "nom_domaine",table = "ta_ndd",clazz = TaNDD.class)
public String getNomDomaine() {
	return nomDomaine;
}



public void setNomDomaine(String domaine) {
	this.nomDomaine = domaine;
}



@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
@JoinColumn(name = "id_registrar")
@LgrHibernateValidated(champEntite = "", champBd = "id_registrar",table = "ta_registrar",clazz = TaRegistrar.class)
public TaRegistrar getRegistrar() {
	return taRegistrar;
}



public void setRegistrar(TaRegistrar taRegistrar) {
	this.taRegistrar = taRegistrar;
}



@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
@JoinColumn(name = "id_ip_fixe")
@LgrHibernateValidated(champEntite = "", champBd = "id_ip_fixe",table = "ta_ip_fixe",clazz = TaIpFixe.class)
public TaIpFixe getTaIpFixe() {
	return taIpFixe;
}



public void setTaIpFixe(TaIpFixe taIpFixe) {
	this.taIpFixe = taIpFixe;
}



@Column(name = "id_titulaire", length = 100)
@LgrHibernateValidated(champEntite = "", champBd = "id_titulaire",table = "ta_ndd",clazz = TaNDD.class)
public String getIdTitulaire() {
	return idTitulaire;
}



public void setIdTitulaire(String idTitulaire) {
	this.idTitulaire = idTitulaire;
}



@Column(name = "passeword", length = 100)
@LgrHibernateValidated(champEntite = "", champBd = "passeword",table = "ta_ndd",clazz = TaNDD.class)
public String getPasseword() {
	return passeword;
}



public void setPasseword(String passeword) {
	this.passeword = passeword;
}



@Column(name = "question", length = 255)
@LgrHibernateValidated(champEntite = "", champBd = "question",table = "ta_ndd",clazz = TaNDD.class)
public String getQuestion() {
	return question;
}



public void setQuestion(String question) {
	this.question = question;
}



@Column(name = "reponse", length = 255)
@LgrHibernateValidated(champEntite = "", champBd = "reponse",table = "ta_ndd",clazz = TaNDD.class)
public String getReponse() {
	return reponse;
}



public void setReponse(String reponse) {
	this.reponse = reponse;
}



@Column(name = "auth_info", length = 100)
@LgrHibernateValidated(champEntite = "", champBd = "auth_info",table = "ta_ndd",clazz = TaNDD.class)
public String getAuthInfo() {
	return authInfo;
}



public void setAuthInfo(String authInfo) {
	this.authInfo = authInfo;
}

	


}
