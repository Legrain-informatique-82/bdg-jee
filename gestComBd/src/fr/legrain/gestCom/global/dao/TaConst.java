package fr.legrain.gestCom.global.dao;

// Generated 14 mai 2009 11:08:14 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.legrain.articles.dao.TaArticle;
import fr.legrain.validator.LgrHibernateValidated;
/**
 * TaInfoEntreprise generated by hbm2java
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ta_const")
public class TaConst implements java.io.Serializable {

	private int id;
	private String nomConst;
	private Integer type;
	private String vVarchar;
	private BigDecimal vNumericCourt;
	private BigDecimal vNumericLong;
	private Date vTimestamp;
	private Date vDate;
	private Integer vInteger;
	private Integer vBool;
//	private Integer versionObj;


	
	public TaConst() {
	}

	public TaConst(int id) {
		this.id = id;
	}


	public TaConst(int id, String nomConst, Integer type, String vVarchar,
			BigDecimal vNumericCourt, BigDecimal vNumericLong,
			Timestamp vTimestamp, Date vDate, Integer vInteger, Integer vBool) {
		super();
		this.id = id;
		this.nomConst = nomConst;
		this.type = type;
		this.vVarchar = vVarchar;
		this.vNumericCourt = vNumericCourt;
		this.vNumericLong = vNumericLong;
		this.vTimestamp = vTimestamp;
		this.vDate = vDate;
		this.vInteger = vInteger;
		this.vBool = vBool;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "non_const", length = 100)
	public String getNomConst() {
		return nomConst;
	}

	public void setNomConst(String nomConst) {
		this.nomConst = nomConst;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "vvarchar", length = 255)
	public String getvVarchar() {
		return vVarchar;
	}

	public void setvVarchar(String vVarchar) {
		this.vVarchar = vVarchar;
	}

	@Column(name = "vnumeric_court")
	public BigDecimal getvNumericCourt() {
		return vNumericCourt;
	}

	public void setvNumericCourt(BigDecimal vNumericCourt) {
		this.vNumericCourt = vNumericCourt;
	}

	@Column(name = "vnumeric_long")
	public BigDecimal getvNumericLong() {
		return vNumericLong;
	}

	public void setvNumericLong(BigDecimal vNumericLong) {
		this.vNumericLong = vNumericLong;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "vtimestamp", length = 19)
	public Date getvTimestamp() {
		return vTimestamp;
	}

	public void setvTimestamp(Date vTimestamp) {
		this.vTimestamp = vTimestamp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vdate", length = 19)
	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	@Column(name = "vinteger")
	public Integer getvInteger() {
		return vInteger;
	}

	public void setvInteger(Integer vInteger) {
		this.vInteger = vInteger;
	}

	@Column(name = "vbool")
	public Integer getvBool() {
		return vBool;
	}

	public void setvBool(Integer vBool) {
		this.vBool = vBool;
	}

}
