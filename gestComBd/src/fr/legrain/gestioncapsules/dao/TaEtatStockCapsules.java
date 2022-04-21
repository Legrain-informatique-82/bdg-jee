package fr.legrain.gestioncapsules.dao;

// Generated 4 juin 2009 10:11:38 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * TaStock generated by hbm2java
 */

public class TaEtatStockCapsules implements java.io.Serializable {

	//private TaArticle taArticle;
	private String codeTitreTransport;

	private String libelleTitreTransport;
	private String mouvStock;
	private Date dateStock;
	private Date dateStockDeb;
	private String libelleStock;
	private BigDecimal qteCapsuleStock;
//	private BigDecimal qte1Stock;
//	private String un1Stock;
//	private BigDecimal qte2Stock;
//	private String un2Stock;
	private String codeDocument;
	private String typeDocument;
	private String ordre;
	
	private Boolean exclusionQteSsUnite=false;

	public TaEtatStockCapsules() {
	}

//	public TaArticle getTaArticle() {
//		return taArticle;
//	}
//
//	public void setTaArticle(TaArticle taArticle) {
//		this.taArticle = taArticle;
//	}

	public String getMouvStock() {
		return mouvStock;
	}

	public void setMouvStock(String mouvStock) {
		this.mouvStock = mouvStock;
	}

	public Date getDateStock() {
		return dateStock;
	}

	public void setDateStock(Date dateStock) {
		this.dateStock = dateStock;
	}

	public String getLibelleStock() {
		return libelleStock;
	}

	public void setLibelleStock(String libelleStock) {
		this.libelleStock = libelleStock;
	}
	
	public BigDecimal getQteCapsuleStock() {
		return qteCapsuleStock;
	}

	public void setQteCapsuleStock(BigDecimal qteCapsuleStock) {
		this.qteCapsuleStock = qteCapsuleStock;
	}
	
	public String getLibelleTitreTransport() {
		return libelleTitreTransport;
	}

	public void setLibelleTitreTransport(String libelleTitreTransport) {
		this.libelleTitreTransport = libelleTitreTransport;
	}

//	public BigDecimal getQte1Stock() {
//		return qte1Stock;
//	}
//
//	public void setQte1Stock(BigDecimal qte1Stock) {
//		this.qte1Stock = qte1Stock;
//	}
//
//	public String getUn1Stock() {
//		return un1Stock;
//	}
//
//	public void setUn1Stock(String un1Stock) {
//		this.un1Stock = un1Stock;
//	}

//	public BigDecimal getQte2Stock() {
//		return qte2Stock;
//	}
//
//	public void setQte2Stock(BigDecimal qte2Stock) {
//		this.qte2Stock = qte2Stock;
//	}
//
//	public String getUn2Stock() {
//		return un2Stock;
//	}
//
//	public void setUn2Stock(String un2Stock) {
//		this.un2Stock = un2Stock;
//	}


	public String getCodeTitreTransport() {
		return codeTitreTransport;
	}

	public void setCodeTitreTransport(String codeArticle) {
		this.codeTitreTransport = codeArticle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeTitreTransport == null) ? 0 : codeTitreTransport.hashCode());
//		result = prime * result
//				+ ((un1Stock == null) ? 0 : un1Stock.hashCode());
//		result = prime * result
//				+ ((un2Stock == null) ? 0 : un2Stock.hashCode());
		return result;
	}

	
	public boolean equalsPartiel(Object obj) {
		boolean forcer=false;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaEtatStockCapsules other = (TaEtatStockCapsules) obj;
		if (codeTitreTransport == null) {
			if (other.getCodeTitreTransport() != null)
				return false;
		} else if (!codeTitreTransport.equals(other.getCodeTitreTransport()))
			return false;
		if (mouvStock == null) {
			if (other.getMouvStock() != null)
				return false;
		} else if (!mouvStock.equals(other.getMouvStock()))
			return false;	
		if (qteCapsuleStock == null) {
			if (other.getQteCapsuleStock() != null)
				return false;
		} else if (!qteCapsuleStock.equals(other.getQteCapsuleStock()))
			return false;	
		
//		if(other.getQteCapsuleStock()!=null && other.getQteCapsuleStock().signum()!=0){
//			if (un1Stock == null || un1Stock.equals("")) {
//				if (other.getUn1Stock() == null || other.getUn1Stock().equals(""))
//					forcer = true;
//				if (other.getUn1Stock() != null)
//					return false;
//			} else if (!un1Stock.equals(other.getUn1Stock())&&!forcer)
//				return false;
//		}

//		if(other.getQte1Stock()!=null && other.getQte1Stock().signum()!=0){
//		if (un1Stock == null || un1Stock.equals("")) {
//			if (other.getUn1Stock() == null || other.getUn1Stock().equals(""))
//				forcer = true;
//			if (other.getUn1Stock() != null)
//				return false;
//		} else if (!un1Stock.equals(other.getUn1Stock())&&!forcer)
//			return false;
//		}
//		if(other.getQte2Stock()!=null && other.getQte2Stock().signum()!=0){
//		if (un2Stock == null||un2Stock.equals("")) {
//			if (other.getUn2Stock() == null || other.getUn2Stock().equals(""))
//				return true;
//			if (other.getUn2Stock() != null ) 
//				return false;
//		} else if (!un2Stock.equals(other.getUn2Stock()))
//			return false;
//		}
		return true;
	}
	
	public boolean equalsPartielCodeArticle(Object obj) {
		boolean forcer=false;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaEtatStockCapsules other = (TaEtatStockCapsules) obj;
		if (codeTitreTransport == null) {
			if (other.getCodeTitreTransport() != null)
				return false;
		} else if (!codeTitreTransport.equals(other.getCodeTitreTransport()))
			return false;

		return true;
	}

	public TaEtatStockCapsules(String codeTitreTransport, String libelleTitreTransport, String mouvStock, Date dateStock,
			String libelleStock, BigDecimal qte1Stock, String un1Stock/*,
			BigDecimal qte2Stock, String un2Stock*/) {
		super();
		this.codeTitreTransport = codeTitreTransport;
		this.libelleTitreTransport = libelleTitreTransport;
		this.mouvStock = mouvStock;
		this.dateStock = dateStock;
		this.libelleStock = libelleStock;
//		this.qte1Stock = qte1Stock;
//		this.un1Stock = un1Stock;
//		this.qte2Stock = qte2Stock;
//		this.un2Stock = un2Stock;
	}
	
	public TaEtatStockCapsules(String codeTitreTransport, String libelleTitreTransport, String mouvStock, Date dateStock, BigDecimal qteCapsuleStock,
			 /*BigDecimal qte1Stock, String un1Stock,*/
			/*BigDecimal qte2Stock, String un2Stock,*/String codeDocument,String libelleStock,
			String typeDocument, String ordre) {
		super();
		this.codeTitreTransport = codeTitreTransport;
		this.libelleTitreTransport = libelleTitreTransport;
		this.mouvStock = mouvStock;
		this.dateStock = dateStock;
		this.qteCapsuleStock = qteCapsuleStock;
//		this.qte1Stock = qte1Stock;
//		this.un1Stock = un1Stock;
//		this.qte2Stock = qte2Stock;
//		this.un2Stock = un2Stock;
		this.libelleStock = libelleStock;
		this.codeDocument = codeDocument;
		this.ordre = ordre;
		this.typeDocument = typeDocument;
	}

	public Boolean getExclusionQteSsUnite() {
		return exclusionQteSsUnite;
	}

	public void setExclusionQteSsUnite(Boolean exclusionQteSsUnite) {
		this.exclusionQteSsUnite = exclusionQteSsUnite;
	}

	public String getCodeDocument() {
		return codeDocument;
	}

	public void setCodeDocument(String codeDocument) {
		this.codeDocument = codeDocument;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getOrdre() {
		return ordre;
	}

	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	public Date getDateStockDeb() {
		return dateStockDeb;
	}

	public void setDateStockDeb(Date dateStockDeb) {
		this.dateStockDeb = dateStockDeb;
	}

}
