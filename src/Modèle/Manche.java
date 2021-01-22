package Mod�le;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Vue.VueTexte;

/**
 * @author ALCARAZ, DUTOUR
 * Repr�sente une manche au sein d'une partie
 * 
 */
public class Manche extends Observable{
	/**
	 * @see diff�rents liens avec d'autres classes : liste de type joueurs gagnant, IVisitor, Carte, Pioche, Plateau, vue texte
	 * 
	 */
	
	private ArrayList<Joueur> gagnant;
	private boolean estTermin�;
	private IVisitor visitor;
	private Carte carteDefausse;
	private Pioche pioche;
	private Plateau plateau;
	private int nbTour;
	ArrayList<Joueur> tabJoueurs;
	private VueTexte vueTexte = new VueTexte();
	
	public Manche(FormePlateau forme) {
		
		this.gagnant = null;
		this.estTermin� = false;
		this.pioche = new Pioche();
		this.creerPlateau(forme);
		
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
	
	/**
	 * Permet de chosir quelle m�thode de cr�ation de plateau appliquer selon la forme du plateau
	 * @param la forme du plateau souhait�e 
	 */
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
			 break;
		default :
			break;
		}	
	}
	
	/**
	 * Permet de d�marrer une manche 
	 * @param la liste des joueurs et la r�gle de jeu
	 */
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Regle regle) {
		
		this.setChanged();
		this.notifyObservers("initialize");
		
		this.tabJoueurs = tabJoueur;
		regle.demarrerManche(tabJoueur, this.pioche);
		
		if(tabJoueur.size() < 3) {
			this.carteDefausse = this.pioche.getRandomCarte();
		}
		
		jouer(tabJoueur, regle);
		
		
		System.out.println("La manche est termin�e");
		this.finManche(tabJoueur);
	}
	
	/**
	 * Parcours la liste des joueurs et les fait jouer tant que la manche n'est pas termin�e
	 * @param la liste des joueurs et la r�gle de jeu
	 */
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

	/**
	 * Permet de mettre fin � une manche
	 * @param la liste des joueurs de la partie
	 */
	public void finManche(ArrayList<Joueur> tabJoueur) {
		this.vueTexte.carteDefausse(this.carteDefausse);
		//comptage des points des joueurs
		ArrayList<Joueur> j = visitor.calculnbPoints(tabJoueur, this.plateau);
		this.setGagnant(j);
		this.vueTexte.printGagnantManche(this.gagnant);
		this.vueTexte.printPoints(tabJoueur);
	}
}
