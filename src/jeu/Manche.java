package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import joueur.Joueur;

public class Manche {
	
	private Joueur gagnant;
	private boolean estTerminé;
	private IVisitor visitor;
	private Carte carteDefausse;
	private Pioche pioche;
	private Plateau plateau;
	
	public Manche() {
		
		this.gagnant = null;
		this.estTerminé = false;
		//regle normale
		
	}

	public Joueur getGagnant() {
		return gagnant;
	}

	public void setGagnant(Joueur gagnant) {
		
		this.gagnant = gagnant;
	}

	public boolean isEstTerminé() {
		return estTerminé;
	}

	public void setEstTerminé(boolean estTerminé) {
		this.estTerminé = estTerminé;
	}
	
	public void demarrerManche(ArrayList<Joueur> tabJoueur, FormePlateau forme) {
		
		this.plateau = new Plateau(forme);
		this.pioche = new Pioche();
		
		
		if(forme.equals(FormePlateau.RECTANGLE)) {
			this.visitor = new CalculPointRectangle();
		}
		else if(forme.equals(FormePlateau.HEXAGONE)) {
			this.visitor = new CalculPointHexagone();
		}
		else {
			this.visitor = new CalculPointCercle();
		}
		
		
		
		for(Joueur j : tabJoueur) { // = foreach pour set les cartes victoires
			j.setCarteVictoire(j.piocherCarte(this.pioche));
		}
		
		
		if(tabJoueur.size() < 3) {
			this.carteDefausse = this.pioche.getRandomCarte();
		}
		
		int nbTour = 0;
		
		while(this.pioche.getListeCarte().size() != 0) {
			for(Joueur j : tabJoueur) {
				if(this.pioche.getListeCarte().size() != 0) {
					nbTour ++;
					System.out.println("Tour "+nbTour+"\n");
					jouer(j,nbTour);
				}
				else {
					break;
				}
			}
		}
		System.out.println("La manches est terminée");
		this.finManche(tabJoueur);
	}
	
	public void jouer(Joueur joueur, int tour) {
		
		Carte c = joueur.piocherCarte(this.pioche);
		joueur.getMain().ajouterCarte(c); 
		joueur.getMain().afficherMain();
		plateau.afficherPlateau();
		Scanner sc = new Scanner(System.in);
		
		if(joueur.askDeplacer()) {
			joueur.deplacerCarte(plateau);
		}
		
		joueur.getMain().afficherMain();
		joueur.poserCarte(plateau,tour); 

		plateau.afficherPlateau();
	}
	
	public void finManche(ArrayList<Joueur> tabJoueur) {
		
		//comptage des points des joueurs
		Joueur j = visitor.calculnbPoints(tabJoueur, this.plateau);
		this.setGagnant(j);
		
		System.out.println("Le joueur gagnant de cette manche est : " + this.gagnant.getNom());
	}

	
}
