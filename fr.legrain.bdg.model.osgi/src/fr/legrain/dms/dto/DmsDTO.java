package fr.legrain.dms.dto;

import java.math.BigDecimal;

import fr.legrain.bdg.model.ModelObject;

public class DmsDTO extends ModelObject implements java.io.Serializable {

	private String codeArticle;
	private String codeFamille;


	private BigDecimal qte1;
	private String un1;
	private BigDecimal qte2;
	private String un2;
	private BigDecimal ht;
	private BigDecimal tva;
	private BigDecimal ttc;
	


	public DmsDTO() {
	}


	public String getCodeArticle() {
		return codeArticle;
	}


	public void setCodeArticle(String codeArticle) {
		firePropertyChange("codeArticle", this.codeArticle, this.codeArticle = codeArticle);
	}


	public String getCodeFamille() {
		return codeFamille;
	}


	public void setCodeFamille(String codeFamille) {
		firePropertyChange("codeFamille", this.codeFamille, this.codeFamille = codeFamille);
	}


	public BigDecimal getQte1() {
		return qte1;
	}


	public void setQte1(BigDecimal qte1) {
		firePropertyChange("qte1", this.qte1, this.qte1 = qte1);
	}


	public String getUn1() {
		return un1;
	}


	public void setUn1(String un1) {
		firePropertyChange("un1", this.un1, this.un1 = un1);
	}


	public BigDecimal getQte2() {
		return qte2;
	}


	public void setQte2(BigDecimal qte2) {
		firePropertyChange("qte2", this.qte2, this.qte2 = qte2);
	}


	public String getUn2() {
		return un2;
	}


	public void setUn2(String un2) {
		firePropertyChange("un2", this.un2, this.un2 = un2);
	}


	public BigDecimal getHt() {
		return ht;
	}


	public void setHt(BigDecimal ht) {
		firePropertyChange("ht", this.ht, this.ht = ht);
	}


	public BigDecimal getTva() {
		return tva;
	}


	public void setTva(BigDecimal tva) {
		firePropertyChange("tva", this.tva, this.tva = tva);
	}


	public BigDecimal getTtc() {
		return ttc;
	}


	public void setTtc(BigDecimal ttc) {
		firePropertyChange("ttc", this.ttc, this.ttc = ttc);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeArticle == null) ? 0 : codeArticle.hashCode());
		result = prime * result
				+ ((codeFamille == null) ? 0 : codeFamille.hashCode());
		result = prime * result + ((ht == null) ? 0 : ht.hashCode());
		result = prime * result + ((qte1 == null) ? 0 : qte1.hashCode());
		result = prime * result + ((qte2 == null) ? 0 : qte2.hashCode());
		result = prime * result + ((ttc == null) ? 0 : ttc.hashCode());
		result = prime * result + ((tva == null) ? 0 : tva.hashCode());
		result = prime * result + ((un1 == null) ? 0 : un1.hashCode());
		result = prime * result + ((un2 == null) ? 0 : un2.hashCode());
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
		DmsDTO other = (DmsDTO) obj;
		if (codeArticle == null) {
			if (other.codeArticle != null)
				return false;
		} else if (!codeArticle.equals(other.codeArticle))
			return false;
		if (codeFamille == null) {
			if (other.codeFamille != null)
				return false;
		} else if (!codeFamille.equals(other.codeFamille))
			return false;
		if (ht == null) {
			if (other.ht != null)
				return false;
		} else if (!ht.equals(other.ht))
			return false;
		if (qte1 == null) {
			if (other.qte1 != null)
				return false;
		} else if (!qte1.equals(other.qte1))
			return false;
		if (qte2 == null) {
			if (other.qte2 != null)
				return false;
		} else if (!qte2.equals(other.qte2))
			return false;
		if (ttc == null) {
			if (other.ttc != null)
				return false;
		} else if (!ttc.equals(other.ttc))
			return false;
		if (tva == null) {
			if (other.tva != null)
				return false;
		} else if (!tva.equals(other.tva))
			return false;
		if (un1 == null) {
			if (other.un1 != null)
				return false;
		} else if (!un1.equals(other.un1))
			return false;
		if (un2 == null) {
			if (other.un2 != null)
				return false;
		} else if (!un2.equals(other.un2))
			return false;
		return true;
	}


	public DmsDTO(String codeArticle, String codeFamille, BigDecimal qte1,
			String un1, BigDecimal qte2, String un2, BigDecimal ht,
			BigDecimal tva, BigDecimal ttc) {
		super();
		this.codeArticle = codeArticle;
		this.codeFamille = codeFamille;
		this.qte1 = qte1;
		this.un1 = un1;
		this.qte2 = qte2;
		this.un2 = un2;
		this.ht = ht;
		this.tva = tva;
		this.ttc = ttc;
	}

}
