package Modèle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant de déterminer une 2e variante de jeu
 * @author ALCARAZ, DUTOUR
 */
public class Variante2 extends Regle{

	/**
	 * Demarre une manche en variante 2
	 * @param la liste des joueurs et la pioche
	 */
	@Override
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche) {
		// TODO Auto-generated method stub
		for(Joueur j : tabJoueur) {
			for(int i=0; i<2; i++) {
				j.getMain().ajouterCarte(j.piocherCarte(pioche));
			}
			if(j.getIsVirtuel()) {
				j.setCarteVictoire(j.getMain().getCarte(0));
			}
		}
	}

	@Override
	public boolean isDone(Manche manche) {
		for(Joueur j : manche.tabJoueurs) {
			if(j.getMain().getCartes().size() != 1){
				return false;
			}
		}
		for(Joueur j : manche.tabJoueurs) {
			j.setCarteVictoire(j.getMain().getCarte(0));
		}
		return true;
	}

}
