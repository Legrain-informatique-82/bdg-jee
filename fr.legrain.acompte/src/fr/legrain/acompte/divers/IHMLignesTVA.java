package fr.legrain.acompte.divers;

import java.math.BigDecimal;

import fr.legrain.documents.dao.LigneTva;
import fr.legrain.lib.data.LibCalcul;
import fr.legrain.lib.data.ModelObject;

public class IHMLignesTVA extends ModelObject{
	private String code;
	private String libelle;
	private BigDecimal taux;
	private BigDecimal montant;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		firePropertyChange("code", this.code, this.code = code);
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		firePropertyChange("libelle", this.libelle, this.libelle = libelle);
	}
	
	public BigDecimal getMontant() {
		return montant;
	}
	
	public void setMontant(BigDecimal montant) {
		firePropertyChange("montant", this.montant, this.montant = montant);
	}
	
	public BigDecimal getTaux() {
		return taux;
	}
	
	public void setTaux(BigDecimal taux) {
		firePropertyChange("taux", this.taux, this.taux = taux);
	}
	
	public IHMLignesTVA setIHMFactureLignesTVA(LigneTva swtLigneFacture){
		setCode(swtLigneFacture.getCodeTva());
		setLibelle(swtLigneFacture.getLibelle());
		setMontant(LibCalcul.arrondi(swtLigneFacture.getMtTva()));
		setTaux(swtLigneFacture.getTauxTva());
		return this;
	}

}
