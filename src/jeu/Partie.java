package jeu;

import java.util.ArrayList;
import java.util.Arrays;

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
		
		Manche manche = this.creerManche();
		Plateau plateau = new Plateau(forme);
		//System.out.println(Arrays.deepToString(plateau.getRemplissage()));
		ArrayList<ArrayList<Carte>> k = plateau.getRemplissage();
		
		for(ArrayList p : k){
            for(int i=0;i<p.size();i++)
            	System.out.println("["+k.indexOf(p)+"]"+"["+ p.indexOf(p.get(i))+"]  ="+p.get(i) );
            	//System.out.println(p.get(i) + " ");
         
        }
		
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
