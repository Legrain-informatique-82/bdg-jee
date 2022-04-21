package fr.legrain.SupportAbon.dao;

// Generated 11 août 2009 15:52:23 by Hibernate Tools 3.2.4.GA

import javax.persistence.CascadeType;
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

import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaWlgr generated by hbm2java
 */
@Entity
@Table(name = "ta_r_option_abon")

public  class TaROptionAbon implements java.io.Serializable {

   
	private Integer idROptionAbon;
    private TaSupportAbon taSupportAbon;
    private TaOptionAbon taOptionAbon;
	
    
	public TaROptionAbon() {
	}

	public TaROptionAbon(Integer idROptionAbon) {
		this.idROptionAbon = idROptionAbon;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_r_option_abon", unique = true)
	public Integer getIdROptionAbon() {
		return this.idROptionAbon;
	}

	public void setIdROptionAbon(Integer idROptionAbon) {
		this.idROptionAbon = idROptionAbon;
	}

	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_support_abon")
	@LgrHibernateValidated(champEntite = "", champBd = "id_support_abon",table = "ta_support_abon",clazz = TaSupportAbon.class)
	public TaSupportAbon getTaSupportAbon() {
		return taSupportAbon;
	}

	public void setTaSupportAbon(TaSupportAbon taSupportAbon) {
		this.taSupportAbon = taSupportAbon;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_option_abon")
	@LgrHibernateValidated(champEntite = "", champBd = "id_option_abon",table = "ta_option_abon",clazz = TaOptionAbon.class)
	public TaOptionAbon getTaOptionAbon() {
		return taOptionAbon;
	}

	public void setTaOptionAbon(TaOptionAbon taOptionAbon) {
		this.taOptionAbon = taOptionAbon;
	}
	
	
}
