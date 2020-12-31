package jeu;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import joueur.Joueur;

public class Manche extends Observable{
	
	private ArrayList<Joueur> gagnant;
	private boolean estTermin�;
	private IVisitor visitor;
	private Carte carteDefausse;
	private Pioche pioche;
	private Plateau plateau;
	ArrayList<Joueur> tabJoueurs;
	
	public Manche(FormePlateau forme) {
		
		this.gagnant = null;
		this.estTermin� = false;
		this.pioche = new Pioche();
		this.creerPlateau(forme);
		//regle normale
		
	}

	
	
	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public ArrayList<Joueur> getGagnant() {
		return gagnant;
	}

	public void setGagnant(ArrayList<Joueur> j) {
		this.gagnant = j;
	}

	public Pioche getPioche() {
		return pioche;
	}

	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	public boolean isEstTermin�() {
		return estTermin�;
	}

	public void setEstTermin�(boolean estTermin�) {
		this.estTermin� = estTermin�;
	}
	
	public void creerPlateau(FormePlateau forme) {
		switch(forme) {
		case RECTANGLE:
			 this.plateau = new PlateauRectangle(forme);
			 this.visitor = new CalculPointRectangle();
			 break;
		case HEXAGONE:
			this.plateau = new PlateauHexagonal(forme);
			this.visitor = new CalculPointHexagone();
			 break;
		case CERCLE:
			this.plateau = new PlateauHexagonal(forme);
			this.visitor = new CalculPointCercle();
			 break;
		case LIBRE:
			 // creer un plateau libre rectangulaire
			 break;
		default :
			break;
		}	
	}
	
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Regle regle) {
		
		this.setChanged();
		this.notifyObservers("initialize");
		
		this.tabJoueurs = tabJoueur;
		regle.demarrerManche(tabJoueur, this.pioche);
		
		if(tabJoueur.size() < 3) {
			this.carteDefausse = this.pioche.getRandomCarte();
		}
		
		jouer(tabJoueur, regle);
		
		
		System.out.println("La manches est termin�e");
		this.finManche(tabJoueur);
	}
	
	public void jouer(ArrayList<Joueur> tabJoueur, Regle regle) {
		
		int nbTour = 0;
		System.out.println(plateau.getRemplissage());
		while(regle.isDone(this) == false) {
			for(Joueur j : tabJoueur) {
				if(regle.isDone(this) == false) {
					nbTour ++;
					System.out.println("Tour "+nbTour/2+" - "+ j.getNom()+ "\n");
					regle.jouer(j, nbTour, this.pioche, this.plateau, this.visitor);
				}
				else {
					break;
				}
			}
		}
		
		
	}
	
	public void finManche(ArrayList<Joueur> tabJoueur) {
		
		System.out.println("La carte d�fauss�e �tait : " + this.carteDefausse);
		//comptage des points des joueurs
		ArrayList<Joueur> j = visitor.calculnbPoints(tabJoueur, this.plateau);
		this.setGagnant(j);
		
		if(this.gagnant.size()>1) { // �galit�
			System.out.print("Les joueurs gagnants � �galit� de cette manche sont : ");
			for(Joueur g : this.gagnant) {
				System.out.print(g.getNom());
			}
		}
		else {
			System.out.println("Le joueur gagnant de cette manche est : " + this.gagnant.get(0).getNom());
		}
		for(Joueur t : tabJoueur) {
			System.out.println("Carte victoire de " + t.getNom() + "� cette manche �tait : " + t.getCarteVictoire());
			System.out.println("Points de " + t.getNom() + " : " + t.getNbPointsManches());
		}
		
	}

	
}
