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
		
		boolean deplacer = joueur.askDeplacer();
		while(deplacer) {
			System.out.print("Abcisse de la carte ? : ");
			int xCarte = sc.nextInt();
			System.out.print("Ordonnée de la carte ? : ");
			int yCarte = sc.nextInt();
			
			if(plateau.getCarte(xCarte, yCarte) != null) {
				System.out.println("la carte que tu veux déplacer : " + plateau.getCarte(xCarte, yCarte));
				System.out.print("Ou veux tu la poser ? Abcisse: ");
				int xDeplacer = sc.nextInt();
				System.out.print("Ordonnée: ");
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
		
		int index=-1; 
		boolean incorrectInput = true;
		int xPose=0 ,yPose=0;
		
		System.out.print("Quelle carte voulez vous poser ? ");
		index = sc.nextInt();
		
		while (index <0 || index > joueur.getMain().getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosis en un autre");
			System.out.print("Quelle carte voulez vous poser ? ");
			index = sc.nextInt();
		}
	
		while(incorrectInput) {
			System.out.print("Abscisse de pose : ");
			xPose = sc.nextInt();
			System.out.print("Ordonnée de pose : ");
			yPose = sc.nextInt();
			if(tour !=1) {
				System.out.println(plateau.checkDeplacement(Position.DROITE));
				System.out.println(plateau.getCarte(0, yPose));
				if(plateau.checkPose(xPose, yPose)) {
					System.out.println("adjacent");
					incorrectInput = false;
				}
				else {
					System.out.println("Tu ne peux pas poser de carte ici");
				}
			}
			else {
				incorrectInput = false;
			}
		}
		
		joueur.poserCarte(index, plateau, xPose, yPose); //Faire en sorte que xPose et Ypose =0 si initialement à -1 et idem si 1 trop grand
		plateau.afficherPlateau();
	}
	
	public void finManche() {
		
	}

	
}
