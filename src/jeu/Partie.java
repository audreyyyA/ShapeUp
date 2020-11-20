package jeu;

import java.util.ArrayList;
import java.util.Arrays;

import joueur.Joueur;

public class Partie {
	
	private boolean estTerminee;
	private int nbManches;
	private int numManche;
	private Regle regle;
	ArrayList<Joueur> tabJoueur;
	
	public Partie(int nbM, Regle regle) {
		
		this.estTerminee = false;
		this.nbManches = nbM;
		this.numManche = 1;
		this.regle = regle;
		this.tabJoueur = new ArrayList<>();
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
		this.tabJoueur.add(joueur1);
		
		Manche manche = this.creerManche();
		manche.demarrerManche(tabJoueur, forme);
		
		
		
		
		
		
		
		
		
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
