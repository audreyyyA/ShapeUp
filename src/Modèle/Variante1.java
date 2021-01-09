package Modèle;

import java.util.ArrayList;
import java.util.Scanner;

public class Variante1 extends Regle{

	@Override
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche) {
		
		for(Joueur j : tabJoueur) { // = foreach pour set les cartes victoires
			j.setCarteVictoire(j.piocherCarte(pioche));
			System.out.println("Carte Victoire de "+j.getNom() +" est : "+j.getCarteVictoire());
		}
	}

	@Override
	public boolean isDone(Manche manche) {
		if(manche.getPioche().getListeCarte().size() != 0) {
			return false;
		}
		return true;
	}
	

}
