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
		
		while(this.pioche.getListeCarte().size() != 0) {
			for(Joueur j : tabJoueur) {
				if(this.pioche.getListeCarte().size() != 0) {
					jouer(j);
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
	
	public void jouer(Joueur joueur) {
		
		Carte c = joueur.piocherCarte(this.pioche);
		joueur.getMain().ajouterCarte(c); 
		joueur.getMain().afficherMain();
		plateau.afficherPlateau();
		
		if(joueur.askDeplacer()) {
			
			System.out.println("Abcisse de la carte? : ");
			Scanner sc = new Scanner(System.in);
			int xCarte = sc.nextInt();
			
			System.out.println("Ordonn�e de la carte? : ");
			int yCarte = sc.nextInt();
			
			System.out.println("la carte que tu veux d�placer : " + xCarte + ", " + yCarte);
			
			System.out.println("Ou veux tu la poser ? Abcisse: ");
			int xDeplacer = sc.nextInt();
			
			System.out.println("Ordonn�e: ");
			int yDeplacer = sc.nextInt();
			
			joueur.deplacerCarte(xCarte,yCarte,xDeplacer,yDeplacer,this.plateau);
		}
		
		joueur.getMain().afficherMain();
		System.out.println("Quelle carte voulez vous poser ?");
		Scanner sc = new Scanner(System.in);
		int index = sc.nextInt();
		
		System.out.println("Abscisse de pose : ");
		int xPose = sc.nextInt();
		
		System.out.println("Ordonn�e de pose : ");
		int yPose = sc.nextInt();
		joueur.poserCarte(index, plateau, xPose, yPose);
		
		plateau.afficherPlateau();
	}
	
	public void finManche() {
		
	}

	
}
