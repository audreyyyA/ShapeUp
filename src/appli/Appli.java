package appli;

import java.util.Scanner;

import jeu.FormePlateau;
import jeu.Partie;
import jeu.Regle;
import jeu.Variante1;
import jeu.Variante2;

public class Appli {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean incorrectInput = true;
		
		System.out.println("Debut partie: ");
		
		String choix = null;
		
		while(incorrectInput) {
			
			System.out.println("Quel type de partie voulez-vous jouer ?");
			System.out.println("1 - Mode normal");
			System.out.println("2 - Mode avancé");

			Scanner sc =  new Scanner(System.in);
			choix = sc.next();
			
			switch(choix) {
			case "1":
				incorrectInput = false;
				break;
			case "2":
				incorrectInput = false;
				break;
			default:
				incorrectInput = true;
				System.out.println("Votre input est incorrect");
				break;
			}
		}
		
		Regle regle = null;
		
		switch(choix) {
		case "1" :
			regle = new Variante1();
			break;
		case "2" :
			regle = new Variante2();
			break;
		default :
			break;
		}
		
		System.out.println("combien de manches voulez-vous jouer?");
		
		int nb = 0;
		while(nb<=0) {
			
			Scanner sc = new Scanner(System.in);
			nb = sc.nextInt();
		}
		
		
		Partie partie = new Partie(nb, regle);
		
		//demander quelle forme de plateau on veut
		
		
		while(incorrectInput) {
			
			System.out.println("Choisissez la forme de votre plateau :");
			System.out.println("1 - Rectangle");
			System.out.println("2 - Hexagone");
			System.out.println("3 - Cercle");
			System.out.println("4 - Jeu libre");
			
			Scanner sc =  new Scanner(System.in);
			choix = sc.next();
			
			incorrectInput = false;

			switch(choix) {
			case "1" :
				partie.debutPartie(FormePlateau.RECTANGLE);
				break;
			case "2" :
				partie.debutPartie(FormePlateau.HEXAGONE);
				break;
			case "3" :
				partie.debutPartie(FormePlateau.CERCLE);
				break;
			case "4" :
				// Rajouter le mode libre
				break;
			default :
				incorrectInput = true;
				System.out.println("Votre input est incorrect");
				break;
			}
		}

		partie.finPartie();
		partie.afficherScore();
	
	}
}
