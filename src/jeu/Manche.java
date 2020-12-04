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
	
	public void demarrerManche(ArrayList<Joueur> tabJoueur, FormePlateau forme, Regle regle) {
		
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
		
		regle.demarrerManche(tabJoueur, this.pioche);
		
		
		if(tabJoueur.size() < 3) {
			this.carteDefausse = this.pioche.getRandomCarte();
		}
		
		jouer(tabJoueur, regle);
		
		
		System.out.println("La manches est terminée");
		this.finManche(tabJoueur);
	}
	
	public void jouer(ArrayList<Joueur> tabJoueur, Regle regle) {
		
		int nbTour = 0;
		
		while(this.pioche.getListeCarte().size() != 0) {
			for(Joueur j : tabJoueur) {
				if(this.pioche.getListeCarte().size() != 0) {
					nbTour ++;
					System.out.println("Tour "+nbTour+"\n");
					regle.jouer(j, nbTour, this.pioche, this.plateau);
				}
				else {
					break;
				}
			}
		}
<<<<<<< HEAD
=======
>>>>>>> 072e0132c8a3c81715c816947b4d08e7f8e0271e
		
		
	}
	
	public void finManche(ArrayList<Joueur> tabJoueur) {
		
		//comptage des points des joueurs
		Joueur j = visitor.calculnbPoints(tabJoueur, this.plateau);
		this.setGagnant(j);
		
		System.out.println("Le joueur gagnant de cette manche est : " + this.gagnant.getNom());
	}

	
}
