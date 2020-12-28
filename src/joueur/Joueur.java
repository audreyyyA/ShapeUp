package joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import jeu.Carte;
import jeu.FormePlateau;
import jeu.Pioche;
import jeu.Plateau;

/**
 * 
 * @author dedeyy
 * @version 1.0
 *
 */

public abstract class Joueur{
	
	/*
	 * changer tout en protected??
	 */
	private String nom;
	private int NbPointTotal;
	private Carte carteVictoire;
	private ArrayList<Integer> nbPointsManches; //à voir
	protected MainJoueur main;
	protected boolean isVirtuel;
	
	public Joueur (String nom) {
		
		this.nom = nom;
		this.NbPointTotal = 0;
		this.carteVictoire = null;
		this.main = new MainJoueur();
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
	
	public Carte piocherCarte(Pioche pioche) {
		Carte newCard = pioche.getRandomCarte();
		//this.main.ajouterCarte(newCard);
		return newCard;
	}
	
	public boolean getIsVirtuel() {
		
		return this.isVirtuel;
	}
	
	/*
	 * DEPLACé DANS JOUEUR reel !
	 */
	public abstract void poserCarte(Plateau plateauActuel, int tour);
	
	
	public abstract boolean deplacerCarte(Plateau plateau);
	
	public String toString() {
		
		String s = this.nom + ", total des points gagnés : " + this.NbPointTotal;
		return s;
		
	}
	
}
