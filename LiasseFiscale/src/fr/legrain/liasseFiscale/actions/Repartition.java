package fr.legrain.liasseFiscale.actions;

import java.util.ArrayList;

//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Repartition.java
//  @ Date : 29/09/2006
//  @ Author : 

public class Repartition {
	private ArrayList<Compte> detail = new ArrayList<Compte>(); //liste des comptes utilisés
	private Double montant = 0d;
	private Double sansArrondi = 0d;
	private String valeur;
	
	public Double getSansArrondi() {
		return sansArrondi;
	}

	public void setSansArrondi(Double sansArrondi) {
		this.sansArrondi = sansArrondi;
	}

	public Repartition() {}
	
	public ArrayList<Compte> getDetail() {
		return detail;
	}
	public void setDetail(ArrayList<Compte> detail) {
		this.detail = detail;
	}
	public Double getMontant() {
		return getMontant(true);
	}
	public Double getMontant(boolean arrondi) {
		if(arrondi)
			return montant;
		else
			return sansArrondi;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getValeur() {
		return ( valeur!=null ? valeur : String.valueOf(montant) );
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
}
