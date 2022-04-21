package fr.legrain.gestioncapsules.dao;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.math.BigDecimal;
import java.util.Date;

import fr.legrain.lib.data.ModelObject;

/**
 * SWTPrix generated by hbm2java
 */
public class SWTStocksCapsules extends ModelObject {

	private Integer idStock;
	private Integer idArticleStock;
	private String codeTitreTransport;
	private String mouvStock ;	
	private Date dateStock = new Date();
	private String libelleStock;
	private BigDecimal qte1Stock;
	private BigDecimal qte2Stock;
	private String un1Stock;
	private String un2Stock;
	private String libelleArticle;

	public SWTStocksCapsules() {
	}

	public SWTStocksCapsules(Integer ID_STOCK,
	Integer ID_ARTICLE_STOCK,
	String codeTitreTransport,
	String MOUV_STOCK,	
	Date DATE_STOCK,
	String LIBELLE_STOCK,
	BigDecimal QTE1_STOCK,
	BigDecimal QTE2_STOCK,
	String UN1_STOCK,
	String UN2_STOCK,String LIBELLE_ARTICLE) {
		this.idStock = ID_STOCK;
		this.idArticleStock = ID_ARTICLE_STOCK;
		this.codeTitreTransport = codeTitreTransport;
		this.mouvStock = MOUV_STOCK;	
		this.dateStock = DATE_STOCK;
		this.libelleStock = LIBELLE_STOCK;
		this.qte1Stock = QTE1_STOCK;
		this.qte2Stock = QTE2_STOCK;
		this.un1Stock = UN1_STOCK;
		this.un2Stock = UN2_STOCK;
		this.libelleArticle = LIBELLE_ARTICLE;
	}
	
	public void setSWTStocks(SWTStocksCapsules swtStocks) {
		this.idStock = swtStocks.idStock;
		this.idArticleStock = swtStocks.idArticleStock;
		this.codeTitreTransport = swtStocks.codeTitreTransport;
		this.dateStock = swtStocks.dateStock;
		this.libelleStock = swtStocks.libelleStock;
		this.mouvStock = swtStocks.mouvStock;
		this.qte1Stock = swtStocks.qte1Stock;
		this.qte2Stock = swtStocks.qte2Stock;
		this.un1Stock = swtStocks.un1Stock;
		this.un2Stock = swtStocks.un2Stock;
		this.libelleArticle = swtStocks.libelleArticle;

	}
	
	public static SWTStocksCapsules copy(SWTStocksCapsules swtStocks){
		SWTStocksCapsules swtStocksLoc = new SWTStocksCapsules();
		swtStocksLoc.setIdStock(swtStocks.idStock);
		swtStocksLoc.setIdArticleStock(swtStocks.idArticleStock);
		swtStocksLoc.setCodeTitreTransport(swtStocks.codeTitreTransport);
		swtStocksLoc.setDateStock(swtStocks.dateStock);
		swtStocksLoc.setLibelleStock(swtStocks.libelleStock);
		swtStocksLoc.setMouvStock(swtStocks.mouvStock);
		swtStocksLoc.setQte1Stock(swtStocks.qte1Stock);
		swtStocksLoc.setQte2Stock(swtStocks.qte2Stock);	
		swtStocksLoc.setUn1Stock(swtStocks.un1Stock);
		swtStocksLoc.setUn2Stock(swtStocks.un2Stock);
		swtStocksLoc.setLibelleArticle(swtStocks.libelleArticle);
		return swtStocksLoc;
	}




	public Integer getIdStock() {
		return idStock;
	}

	public void setIdStock(Integer ID_STOCK) {
		firePropertyChange("idStock", this.idStock, this.idStock = ID_STOCK);
	}

	public Integer getIdArticleStock() {
		return idArticleStock;
	}

	public void setIdArticleStock(Integer ID_ARTICLE_STOCK) {
		firePropertyChange("idArticleStock", this.idArticleStock, this.idArticleStock = ID_ARTICLE_STOCK);
	}

	public String getCodeTitreTransport() {
		return codeTitreTransport;
	}

	public void setCodeTitreTransport(String codeTitreTransport) {
		firePropertyChange("codeTitreTransport", this.codeTitreTransport, this.codeTitreTransport = codeTitreTransport);
	}

	public String getMouvStock() {
		return mouvStock;
	}

	public void setMouvStock(String MOUV_STOCK) {
		firePropertyChange("mouvStock", this.mouvStock, this.mouvStock = MOUV_STOCK);
	}

	public Date getDateStock() {
		return this.dateStock;
	}

	public void setDateStock(Date DATE_STOCK) {
		firePropertyChange("dateStock", this.dateStock, this.dateStock = DATE_STOCK);
	}
	public String getLibelleStock() {
		return libelleStock;
	}

	public void setLibelleStock(String LIBELLE_STOCK) {
		firePropertyChange("libelleStock", this.libelleStock, this.libelleStock = LIBELLE_STOCK);
	}

	public BigDecimal getQte1Stock() {
		return qte1Stock;
	}

	public void setQte1Stock(BigDecimal QTE1_STOCK) {
		firePropertyChange("qte1Stock", this.qte1Stock, this.qte1Stock = QTE1_STOCK);
	}

	public BigDecimal getQte2Stock() {
		return qte2Stock;
	}

	public void setQte2Stock(BigDecimal QTE2_STOCK) {
		firePropertyChange("qte2Stock", this.qte2Stock, this.qte2Stock = QTE2_STOCK);
	}

	public String getUn1Stock() {
		return un1Stock;
	}

	public void setUn1Stock(String UN1_STOCK) {
		firePropertyChange("un1Stock", this.un1Stock, this.un1Stock = UN1_STOCK);
	}

	public String getUn2Stock() {
		return un2Stock;
	}

	public void setUn2Stock(String UN2_STOCK) {
		firePropertyChange("un2Stock", this.un2Stock, this.un2Stock = UN2_STOCK);
	}

	public String getLibelleArticle() {
		return libelleArticle;
	}

	public void setLibelleArticle(String LIBELLE_ARTICLE) {
		firePropertyChange("libelleArticle", this.libelleArticle, this.libelleArticle = LIBELLE_ARTICLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeTitreTransport == null) ? 0 : codeTitreTransport.hashCode());
		result = prime * result
				+ ((dateStock == null) ? 0 : dateStock.hashCode());
		result = prime * result
				+ ((idArticleStock == null) ? 0 : idArticleStock.hashCode());
		result = prime * result + ((idStock == null) ? 0 : idStock.hashCode());
		result = prime * result
				+ ((libelleArticle == null) ? 0 : libelleArticle.hashCode());
		result = prime * result
				+ ((libelleStock == null) ? 0 : libelleStock.hashCode());
		result = prime * result
				+ ((mouvStock == null) ? 0 : mouvStock.hashCode());
		result = prime * result
				+ ((qte1Stock == null) ? 0 : qte1Stock.hashCode());
		result = prime * result
				+ ((qte2Stock == null) ? 0 : qte2Stock.hashCode());
		result = prime * result
				+ ((un1Stock == null) ? 0 : un1Stock.hashCode());
		result = prime * result
				+ ((un2Stock == null) ? 0 : un2Stock.hashCode());
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
		SWTStocksCapsules other = (SWTStocksCapsules) obj;
		if (codeTitreTransport == null) {
			if (other.codeTitreTransport != null)
				return false;
		} else if (!codeTitreTransport.equals(other.codeTitreTransport))
			return false;
		if (dateStock == null) {
			if (other.dateStock != null)
				return false;
		} else if (!dateStock.equals(other.dateStock))
			return false;
		if (idArticleStock == null) {
			if (other.idArticleStock != null)
				return false;
		} else if (!idArticleStock.equals(other.idArticleStock))
			return false;
		if (idStock == null) {
			if (other.idStock != null)
				return false;
		} else if (!idStock.equals(other.idStock))
			return false;
		if (libelleArticle == null) {
			if (other.libelleArticle != null)
				return false;
		} else if (!libelleArticle.equals(other.libelleArticle))
			return false;
		if (libelleStock == null) {
			if (other.libelleStock != null)
				return false;
		} else if (!libelleStock.equals(other.libelleStock))
			return false;
		if (mouvStock == null) {
			if (other.mouvStock != null)
				return false;
		} else if (!mouvStock.equals(other.mouvStock))
			return false;
		if (qte1Stock == null) {
			if (other.qte1Stock != null)
				return false;
		} else if (!qte1Stock.equals(other.qte1Stock))
			return false;
		if (qte2Stock == null) {
			if (other.qte2Stock != null)
				return false;
		} else if (!qte2Stock.equals(other.qte2Stock))
			return false;
		if (un1Stock == null) {
			if (other.un1Stock != null)
				return false;
		} else if (!un1Stock.equals(other.un1Stock))
			return false;
		if (un2Stock == null) {
			if (other.un2Stock != null)
				return false;
		} else if (!un2Stock.equals(other.un2Stock))
			return false;
		return true;
	}



}
