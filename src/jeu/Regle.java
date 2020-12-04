package jeu;

import java.util.ArrayList;

import joueur.Joueur;

public abstract class Regle {
	
	public abstract ArrayList<Joueur> initJoueur();
	public abstract void jouer(Joueur joueur, int tour, Pioche pioche, Plateau plateau);
	public abstract void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche);

}
