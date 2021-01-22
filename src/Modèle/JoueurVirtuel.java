package Modèle;

import java.util.Scanner;

/**
 * @author ALCARAZ, DUTOUR
 * Représente un joueur virtuel (IA)
 * 
 */
public class JoueurVirtuel extends Joueur{
	
	private Strategie strategie;
	
	
	public JoueurVirtuel(String nom, boolean v, Strategie strat, int num) {
		
		super(nom, num);
		super.isVirtuel = true;
		this.setStrategie(strat);
	}


	public Strategie getStrategie() {
		return strategie;
	}


	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}
	
	
	
	public void poserCarte(Plateau plateauActuel, int tour) {
		
	}
	
	public boolean deplacerCarte(Plateau plateau) {
		 return false;
	}
}
