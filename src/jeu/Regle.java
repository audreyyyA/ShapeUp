package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import joueur.Strategie;
import joueur.StrategieDifficile;
import joueur.StrategieFacile;

public abstract class Regle {
	
	public abstract void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche);
	public abstract boolean isDone(Manche manche);
	public abstract void jouer(Joueur joueur, int tour, Pioche pioche, Plateau plateau, IVisitor visitor);
	public abstract ArrayList<Joueur> initJoueur();
}
