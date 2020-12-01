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
		this.visitor = new CalculPointRectangle();
		
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
		
		Carte test = new Carte(Couleur.BLEU,FormeCarte.CARRE,true);
		
		/* Pour tester le calcul des points 
		plateau.setCarte(0, 0, test);
		plateau.setCarte(1, 0, test);
		plateau.setCarte(2, 0, test);
		plateau.setCarte(0, 1, test);
		plateau.setCarte(1, 1, test);
		plateau.setCarte(2, 1, test);
		plateau.setCarte(3, 1, test);
		plateau.setCarte(0, 2, test);
		plateau.setCarte(1, 2, test);
		plateau.setCarte(2, 2, test);
		plateau.setCarte(3, 2, test);
		plateau.setCarte(4, 2, test);
		plateau.setCarte(0, 3, test);
		plateau.setCarte(1, 3, test);
		plateau.setCarte(2, 3, test);
		plateau.setCarte(3, 3, test);
		plateau.setCarte(0, 4, test);
		plateau.setCarte(1, 4, test);
		plateau.setCarte(2, 4, test);
		
		CalculPointHexagone calcul = new CalculPointHexagone(); // A faire varier selon la forme du plateau
		calcul.calculPointJoueur(test, plateau);
		*/
		
		for(Joueur j : tabJoueur) { // = foreach pour set les cartes victoires
			j.setCarteVictoire(j.piocherCarte(this.pioche));
		}
		
		//on défausse une carte si ils sont que 2
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
		System.out.println("Partie terminée");
		
		/*ArrayList<ArrayList<Carte>> k = plateau.getRemplissage();
		plateau.afficherPlateau();*/
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
	
	public void finManche() {
		
	}

	
}
