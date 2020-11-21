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
		this.visitor = new Visitor();
		
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
		
		for(Joueur j : tabJoueur) { // = foreach
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
					jouer(j);
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
	
	public void jouer(Joueur joueur) {
		
		Carte c = joueur.piocherCarte(this.pioche);
		joueur.getMain().ajouterCarte(c); 
		joueur.getMain().afficherMain();
		plateau.afficherPlateau();
		
		if(joueur.askDeplacer()) {
			System.out.print("Abcisse de la carte ? : ");
			Scanner sc = new Scanner(System.in);
			int xCarte = sc.nextInt();
			System.out.print("Ordonnée de la carte ? : ");
			int yCarte = sc.nextInt();
			System.out.println("la carte que tu veux déplacer : " + xCarte + ", " + yCarte);
			System.out.print("Ou veux tu la poser ? Abcisse: ");
			int xDeplacer = sc.nextInt();
			System.out.print("Ordonnée: ");
			int yDeplacer = sc.nextInt();
			
			joueur.deplacerCarte(xCarte,yCarte,xDeplacer,yDeplacer,this.plateau);
		}
		
		joueur.getMain().afficherMain();
		System.out.print("Quelle carte voulez vous poser ? "); // Pas laisser le choix si une seule carte ?
		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt(); 
		
		while (index <0 || index > joueur.getMain().getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosisi en un autre");
			System.out.print("Quelle carte voulez vous poser ? ");
			index = sc.nextInt();
		}
		
		System.out.print("Abscisse de pose : ");
		int xPose = sc.nextInt();
		
		System.out.print("Ordonnée de pose : ");
		int yPose = sc.nextInt();
		joueur.poserCarte(index, plateau, xPose, yPose);
		
		plateau.afficherPlateau();
	}
	
	public void finManche() {
		
	}

	
}
