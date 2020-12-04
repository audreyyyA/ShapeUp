package appli;

import java.util.Scanner;

import jeu.FormePlateau;
import jeu.Partie;
import jeu.Regle;
import jeu.Variante1;

public class Appli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Debut partie: ");
		System.out.println("Vous allez jouer une partie classique ");
		
		Regle regle = new Variante1();
		System.out.println("combien de manches voulez-vous jouer?");
		
		int nb = 0;
		while(nb<=0) {
			
			Scanner sc = new Scanner(System.in);
			nb = sc.nextInt();
		}
		
		
		Partie partie = new Partie(nb, regle);
		partie.debutPartie(FormePlateau.RECTANGLE);
		partie.finPartie();
		partie.afficherScore();
	
	}

}
