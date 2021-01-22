package Mod�le;

import java.util.ArrayList;
import java.util.Arrays;

import Vue.InterfacePlateau;
import Vue.VueTexte;
import appli.Appli;

/**
 * Classe repr�sentant une partie
 * @author ALCARAZ, DUTOUR
 */
public class Partie {
	
	/**
	 * @see liens avec les classes suivantes : Regle, Joueur, FormePlateau, Manche et VueTexte
	 * 
	 */
	private boolean estTerminee;
	private int nbManches;
	private int numManche;
	private Regle regle;
	ArrayList<Joueur> tabJoueur;
	private FormePlateau forme;
	private Manche manche;
	private VueTexte vueTexte = new VueTexte();
	
	public Partie(int nbM, Regle regle,FormePlateau forme, ArrayList tabJoueur) {
		
		this.forme = forme;
		this.estTerminee = false;
		this.nbManches = nbM;
		this.numManche = 0;
		this.regle = regle;
		this.tabJoueur = tabJoueur;
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
	
	/**
	 * Permet de d�buter une partie
	 * @param L'interface repr�sentant un plateau et l'application 
	 */
	public void debutPartie(InterfacePlateau interfacePlateau, Appli app) {
		
		while(!this.estTerminee) {	
			this.numManche+=1;
			
			Manche manche = new Manche(this.forme);
			
			manche.addObserver(interfacePlateau);
	    	manche.getPlateau().addObserver(interfacePlateau);
	    	manche.getPlateau().setCarte(0, 0, null);
	    	manche.getPioche().addObserver(interfacePlateau);
	    	
	    	manche.addObserver(app);
	    	manche.getPlateau().addObserver(app);
	    	manche.getPioche().addObserver(app);
			
			manche.demarrerManche(tabJoueur, this.regle);
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

	/**
	 * Met fin � une partie
	 */
	public void finPartie() {
		
		int tmp = 0;
		Joueur gagnant = null;
		for(Joueur j : this.tabJoueur) {
			if(j.getNbPointTotal() > tmp) {
				gagnant = j;
			}
			tmp = j.getNbPointTotal();
		}

		
	}
	
	/**
	 * Affiche les scores finaux de la partie
	 */
	public void afficherScore() {
		this.vueTexte.afficherScore(this.tabJoueur);
	}


}
