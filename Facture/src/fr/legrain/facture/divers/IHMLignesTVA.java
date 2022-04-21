package fr.legrain.facture.divers;

import java.math.BigDecimal;

import fr.legrain.bdg.model.ModelObject;
import fr.legrain.document.model.LigneTva;
import fr.legrain.lib.data.LibCalcul;

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
