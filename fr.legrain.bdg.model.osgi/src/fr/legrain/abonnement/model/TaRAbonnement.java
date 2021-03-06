package fr.legrain.abonnement.model;

// Generated 11 août 2009 15:52:23 by Hibernate Tools 3.2.4.GA

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

import fr.legrain.supportAbonnement.model.TaSupportAbon;
import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaWlgr generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_r_abonnement")
//@SequenceGenerator(name = "gen_r_abonnement", sequenceName = "num_id_r_abonnement", allocationSize = 1)

public class TaRAbonnement implements java.io.Serializable {

   
	/**
	 * 
	 */
	private static final long serialVersionUID = 4361417718572096445L;
	private Integer idRAbonnement;
	private TaAbonnementOld taAbonnement;
	 private TaSupportAbon taSupportAbon;

	
	public TaRAbonnement() {
	}

	public TaRAbonnement(Integer idRAbonnement) {
		this.idRAbonnement = idRAbonnement;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_r_abonnement", unique = true)
	public Integer getIdRAbonnement() {
		return this.idRAbonnement;
	}

	public void setIdRAbonnement(Integer idRAbonnement) {
		this.idRAbonnement = idRAbonnement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_support_abon")
	@LgrHibernateValidated(champBd = "id_support_abon",table = "ta_support_abon", champEntite="taSupportAbon.idSupportAbon", clazz = TaSupportAbon.class)
	public TaSupportAbon getTaSupportAbon() {
		return this.taSupportAbon;
	}

	public void setTaSupportAbon(TaSupportAbon taSupportAbon) {
		this.taSupportAbon = taSupportAbon;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_abonnement")
	@LgrHibernateValidated(champBd = "id_abonnement",table = "ta_abonnement", champEntite="taAbonnement.idAbonnement", clazz = TaAbonnementOld.class)
	public TaAbonnementOld getTaAbonnement() {
		return this.taAbonnement;
	}

	public void setTaAbonnement(TaAbonnementOld taAbonnement) {
		this.taAbonnement = taAbonnement;
	}
	

	
	
}
