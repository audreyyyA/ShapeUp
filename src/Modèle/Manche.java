package Modèle;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Vue.VueTexte;

public class Manche extends Observable{
	
	private ArrayList<Joueur> gagnant;
	private boolean estTerminé;
	private IVisitor visitor;
	private Carte carteDefausse;
	private Pioche pioche;
	private Plateau plateau;
	private int nbTour;
	ArrayList<Joueur> tabJoueurs;
	private VueTexte vueTexte = new VueTexte();
	
	public Manche(FormePlateau forme) {
		
		this.gagnant = null;
		this.estTerminé = false;
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

	public boolean isEstTerminé() {
		return estTerminé;
	}

	public void setEstTerminé(boolean estTerminé) {
		this.estTerminé = estTerminé;
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
		
		
		System.out.println("La manche est terminée");
		this.finManche(tabJoueur);
	}
	
	@SuppressWarnings("deprecation")
	public void jouer(ArrayList<Joueur> tabJoueur, Regle regle) {
		
		this.nbTour = 0;
		
		while(regle.isDone(this) == false) {
			for(Joueur j : tabJoueur) {
				if(regle.isDone(this) == false) {
					this.nbTour ++;
					this.vueTexte.printTour(this.nbTour,j.getNom());
					this.setChanged();
					this.notifyObservers(j);
					regle.jouer(j, this.nbTour, this.pioche, this.plateau, this.visitor);
				}
				else {
					break;
				}
			}
		}
		
		
	}
	
	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public void finManche(ArrayList<Joueur> tabJoueur) {
		this.vueTexte.carteDefausse(this.carteDefausse);
		//comptage des points des joueurs
		ArrayList<Joueur> j = visitor.calculnbPoints(tabJoueur, this.plateau);
		this.setGagnant(j);
		this.vueTexte.printGagnantManche(this.gagnant);
		this.vueTexte.printPoints(tabJoueur);
	}
}
