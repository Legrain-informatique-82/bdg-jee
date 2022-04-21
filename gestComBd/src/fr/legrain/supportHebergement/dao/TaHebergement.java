package fr.legrain.supportHebergement.dao;

// Generated 11 août 2009 15:52:23 by Hibernate Tools 3.2.4.GA

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.legrain.SupportAbon.dao.TaSupportAbon;
import fr.legrain.SupportAbonLegrain.dao.TaServeur;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaWlgr generated by hbm2java
 */
@Entity
@Table(name = "TA_HEBERGEMENT")
public  class TaHebergement extends TaSupportAbon implements java.io.Serializable {

	private TaServeur taServeur;

	
	public TaHebergement() {
	}

	


public TaHebergement(TaServeur taServeur) {
		super();
		this.taServeur = taServeur;
	}




@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
@JoinColumn(name = "ID_SERVEUR")
@LgrHibernateValidated(champEntite = "", champBd = "ID_SERVEUR",table = "TA_SERVEUR",clazz = TaServeur.class)
public TaServeur getTaServeur() {
	return taServeur;
}


public void setTaServeur(TaServeur taServeur) {
	this.taServeur = taServeur;
}



}
