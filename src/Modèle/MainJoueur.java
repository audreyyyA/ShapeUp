package Modèle;

import java.util.ArrayList;
import java.util.Observable;

import Vue.VueTexte;

/**
 * Représente la main d'un joueur
 * @author ALCARAZ, DUTOUR
 */
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

	/**
	 * Ajoute une carte à la main
	 * @param la carte à ajouter
	 */
	public void ajouterCarte(Carte newCard) {
		cartes.add(newCard);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Retire une carte de la main
	 * @param l'index de la main
	 */
	public void retirerCarte(int index) {
		cartes.remove(index);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Permet d'afficher le contneu de la main
	 */
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
