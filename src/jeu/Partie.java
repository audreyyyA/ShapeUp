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
		
		
		while(!this.estTerminee) {	
			Manche manche = this.creerManche();
			manche.demarrerManche(tabJoueur, forme);
		
		}
		
	}
	
	public void finPartie() {
		
		int tmp = 0;
		Joueur gagnant = null;
		//on détermine le gagnant de la partie
		for(Joueur j : this.tabJoueur) {
			if(j.getNbPointTotal() > tmp) {
				gagnant = j;
			}
			tmp = j.getNbPointTotal();
		}
		
		System.out.println("Le joueur gagnant de la partie est : " + gagnant.getNom() + " BRAVO CHAKAL!!");
	}
	
	public void afficherScore() {
		
		for(Joueur j : this.tabJoueur) {
			
		}
	}
	
	public Manche creerManche() {
		
		Manche manche = new Manche();
		return manche;
	}

}
