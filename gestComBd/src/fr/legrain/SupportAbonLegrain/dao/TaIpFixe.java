package fr.legrain.SupportAbonLegrain.dao;

// Generated 11 août 2009 15:52:23 by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.legrain.validator.LgrHibernateValidated;


/**
 * TaWlgr generated by hbm2java
 */
@Entity
@Table(name = "ta_ip_fixe")

public class TaIpFixe implements java.io.Serializable {
    
	private Integer idIpFixe;
	private String IpFixe;


	
	public TaIpFixe() {
	}

	public TaIpFixe(Integer idIpFixe) {
		this.idIpFixe = idIpFixe;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ip_fixe", unique = true)
	public Integer getIdIpFixe() {
		return this.idIpFixe;
	}

	public void setIdIpFixe(Integer idIpFixe) {
		this.idIpFixe = idIpFixe;
	}

	@Column(name = "ip_fixe")
	@LgrHibernateValidated(champEntite = "", champBd = "ip_fixe",table = "ta_ip_fixe",clazz = TaIpFixe.class)
	public String getIpFixe() {
		return this.IpFixe;
	}

	public void setIpFixe(String IpFixe) {
		this.IpFixe = IpFixe;
	}
	
	
	
}
