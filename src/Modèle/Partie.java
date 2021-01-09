package Modèle;

import java.util.ArrayList;
import java.util.Arrays;

public class Partie {
	
	private boolean estTerminee;
	private int nbManches;
	private int numManche;
	private Regle regle;
	ArrayList<Joueur> tabJoueur;
	private FormePlateau forme;
	private Manche manche;
	
	public Partie(int nbM, Regle regle,FormePlateau forme, ArrayList tabJoueur) {
		
		this.forme = forme;
		this.estTerminee = false;
		this.nbManches = nbM;
		this.numManche = 0;
		this.regle = regle;
		this.tabJoueur = tabJoueur;
		this.manche = new Manche(forme);
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
	
	public void debutPartie() {
		
		while(!this.estTerminee) {	
			this.numManche+=1;
			this.manche.demarrerManche(tabJoueur, this.regle);
			if(this.numManche==this.nbManches) {
				this.estTerminee=true;
			}
		}
	}
	
	public Manche getManche() {
		return manche;
	}

	public void setManche(Manche manche) {
		this.manche = manche;
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
		
		System.out.println("Voici le tableau des scores :");
		
		for(Joueur j : this.tabJoueur) {
			
			System.out.println(j.toString());
		}
	}


}
