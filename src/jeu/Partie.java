package jeu;

import java.util.ArrayList;
import java.util.Arrays;

import joueur.Joueur;

public class Partie {
	
	private boolean estTerminee;
	private int nbManches;
	private int numManche;
	private Regle regle;
	
	public Partie(int nbM, Regle regle) {
		
		this.estTerminee = false;
		this.nbManches = nbM;
		this.numManche = 1;
		this.regle = regle;
	}

	public boolean getTerminee() {
		return estTerminee;
	}

	public void getTerminee(boolean estTerminee) {
		this.estTerminee = estTerminee;
	}

	public int getNbManches() {
		return nbManches;
	}

	public void setNbManches(int nbManches) {
		this.nbManches = nbManches;
	}

	public int getNumManche() {
		return numManche;
	}

	public void setNumManche(int numManche) {
		this.numManche = numManche;
	}
	
	public void debutPartie(FormePlateau forme) {
		
		//permet de jouer la partie
		Joueur joueur1 = new Joueur("Alex",false);
		Manche manche = this.creerManche();
		Plateau plateau = new Plateau(forme);
		Pioche pioche = new Pioche();
		
		ArrayList<ArrayList<Carte>> k = plateau.getRemplissage();
		plateau.afficherPlateau();
		
		joueur1.piocherCarte(pioche,joueur1.getMain());
		joueur1.getMain().afficherMain();
		joueur1.getMain().retirerCarte(0);
		joueur1.getMain().afficherMain();
		
		
		
		
		
		/*while(!this.estTerminee) {	
			
		}*/
		
		
	}
	
	public void finPartie() {
		
	}
	
	public void afficherScore() {
		
	}
	
	public Manche creerManche() {
		
		Manche manche = new Manche();
		return manche;
	}

}
