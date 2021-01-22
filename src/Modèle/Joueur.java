package Modèle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Vue.VueTexte;

/**
 * @author ALCARAZ, DUTOUR
 * Super classe représentant un joueur
 * 
 */
public abstract class Joueur extends Observable{
	
	private String nom;
	private int NbPointTotal;
	private Carte carteVictoire;
	private ArrayList<Integer> nbPointsManches; 
	protected MainJoueur main;
	protected boolean isVirtuel, deplacer;
	private int num;
	protected boolean scanClose;
	protected VueTexte vueTexte = new VueTexte();
	
	public boolean isScanClose() {
		return scanClose;
	}

	public void setScanClose(boolean scanClose) {
		this.scanClose = scanClose;
	}

	public boolean isDeplacer() {
		return deplacer;
	}

	public void setDeplacer(boolean deplacer) {
		this.deplacer = deplacer;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Joueur (String nom, int num) {
		
		this.num = num;
		this.nom = nom;
		this.NbPointTotal = 0;
		this.carteVictoire = null;
		this.main = new MainJoueur(num);
		this.nbPointsManches = new ArrayList<>();
		this.isVirtuel = false;
	}

	public Carte getCarteVictoire() {
		
		return carteVictoire;
	}

	public void setCarteVictoire(Carte carteVictoire) {
		this.carteVictoire = carteVictoire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbPointTotal() {
		return NbPointTotal;
	}

	public void setNbPointTotal(int pt) {
		this.NbPointTotal += pt;
	}

	public ArrayList<Integer> getNbPointsManches() {
		return nbPointsManches;
	}

	public void setPoints(int pt) {
		this.nbPointsManches.add(pt);
	}

	public MainJoueur getMain() {
		return main;
	}

	public void setMain(MainJoueur main) {
		this.main = main;
	}
	
	/**
	 * Permet de picoher une carte au hasard dans la pioche
	 * @param la pioche du jeu
	 */
	public Carte piocherCarte(Pioche pioche) {
		Carte newCard = pioche.getRandomCarte();
		return newCard;
	}
	
	public boolean getIsVirtuel() {
		
		return this.isVirtuel;
	}
	
	public VueTexte getVueTexte() {
		return this.vueTexte;
	}


	public void setVueTexte(VueTexte vueTexte) {
		this.vueTexte = vueTexte;
	}
	
	public abstract void poserCarte(Plateau plateauActuel, int tour);
	
	
	public abstract boolean deplacerCarte(Plateau plateau);
	
	public String toString() {
		
		String s = this.nom + ", total des points gagnés : " + this.NbPointTotal;
		return s;
		
	}
	
}
