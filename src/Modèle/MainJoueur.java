package Modèle;

import java.util.ArrayList;
import java.util.Observable;

import Vue.VueTexte;

public class MainJoueur extends Observable{
	
	private ArrayList<Carte> cartes;
	private int num;
	
	public MainJoueur(int num) {
		this.cartes = new ArrayList<Carte>();
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void ajouterCarte(Carte newCard) {
		cartes.add(newCard);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void retirerCarte(int index) {
		this.setChanged();
		this.notifyObservers();
		cartes.remove(index);
	}
	
	public void afficherMain() {
		VueTexte vue = new VueTexte();
		vue.afficherMain(this.cartes);
	}
	
	public Carte getCarte(int index) {
		
		return cartes.get(index);
	}
	
	public ArrayList<Carte> getCartes(){
		return cartes;
	}
}
