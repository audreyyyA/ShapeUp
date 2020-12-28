package joueur;

import java.util.Scanner;

import jeu.Carte;
import jeu.FormePlateau;
import jeu.Plateau;

public class JoueurVirtuel extends Joueur{
	
	private Strategie strategie;
	
	
	public JoueurVirtuel(String nom, boolean v, Strategie strat) {
		
		super(nom);
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
		
		//voir si on met qqchose ici
	}
	
	/*
	 * CHANGER LE CONTENU POUR UNE STRAT DIFFICILE ...
	 */
	public boolean deplacerCarte(Plateau plateau) {
		 return false;
	}
}
