package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import joueur.Joueur;

public class Manche {
	
	private Joueur gagnant;
	private boolean estTermin�;
	private IVisitor visitor;
	private Carte carteDefausse;
	private Pioche pioche;
	private Plateau plateau;
	
	public Manche() {
		
		this.gagnant = null;
		this.estTermin� = false;
		this.visitor = new Visitor();
		
		//regle normale
		
	}

	public Joueur getGagnant() {
		return gagnant;
	}

	public void setGagnant(Joueur gagnant) {
		
		this.gagnant = gagnant;
	}

	public boolean isEstTermin�() {
		return estTermin�;
	}

	public void setEstTermin�(boolean estTermin�) {
		this.estTermin� = estTermin�;
	}
	
	public void demarrerManche(ArrayList<Joueur> tabJoueur, FormePlateau forme) {
		
		this.plateau = new Plateau(forme);
		this.pioche = new Pioche();
		
		for(Joueur j : tabJoueur) { // = foreach
			j.setCarteVictoire(j.piocherCarte(this.pioche));
		}
		
		//on d�fausse une carte si ils sont que 2
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
		System.out.println("Partie termin�e");
		
		/*ArrayList<ArrayList<Carte>> k = plateau.getRemplissage();
		plateau.afficherPlateau();*/
	}
	
	public void jouer(Joueur joueur, int tour) {
		
		Carte c = joueur.piocherCarte(this.pioche);
		joueur.getMain().ajouterCarte(c); 
		joueur.getMain().afficherMain();
		plateau.afficherPlateau();
		Scanner sc = new Scanner(System.in);
		
		boolean deplacer = joueur.askDeplacer();
		while(deplacer) {
			System.out.print("Abcisse de la carte ? : ");
			int xCarte = sc.nextInt();
			System.out.print("Ordonn�e de la carte ? : ");
			int yCarte = sc.nextInt();
			
			if(plateau.getCarte(xCarte, yCarte) != null) {
				System.out.println("la carte que tu veux d�placer : " + plateau.getCarte(xCarte, yCarte));
				System.out.print("Ou veux tu la poser ? Abcisse: ");
				int xDeplacer = sc.nextInt();
				System.out.print("Ordonn�e: ");
				int yDeplacer = sc.nextInt();
				
				joueur.deplacerCarte(xCarte,yCarte,xDeplacer,yDeplacer,this.plateau);
				deplacer = false;
			}
			
			else{
				System.out.println("Tu as choisis un emplacement sans cartes !\n");
				deplacer = joueur.askDeplacer();
			}
		}
		
		joueur.getMain().afficherMain();
		
		joueur.poserCarte(plateau,tour); 
		plateau.afficherPlateau();
	}
	
	public void finManche() {
		
	}

	
}
