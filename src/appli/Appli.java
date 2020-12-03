package appli;

import jeu.FormePlateau;
import jeu.Partie;
import jeu.Regle;

public class Appli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Debut partie: ");
		Regle regle = new Regle();
		Partie partie = new Partie(2, regle);
		partie.debutPartie(FormePlateau.RECTANGLE);
		partie.finPartie();
		partie.afficherScore();
	
	}

}
