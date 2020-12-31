package appli;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import graphicInterface.Accueil;
import graphicInterface.InterfacePlateau;
import jeu.FormePlateau;
import jeu.Partie;
import jeu.Regle;
import jeu.Variante1;
import jeu.Variante2;
import joueur.Joueur;
import joueur.MainJoueur;

public class Appli implements Observer,Runnable {

	public static String QUITTER = "Quit";
    public static String COMMUTER = "C";
    public static String PROMPT = ">";
    private int nb;
    private Regle regle;
    private FormePlateau forme;
    private ArrayList<Joueur> tabJoueur;
    private Partie partie;
    private InterfacePlateau interfacePlateau;
    
    public Appli(InterfacePlateau interfacePlateau,int nb, Regle regle,FormePlateau forme,ArrayList<Joueur> tabJoueur) {
      // A compléter    
    	this.nb = nb;
    	this.regle = regle;
    	this.forme = forme;
    	this.tabJoueur = tabJoueur;
    	this.interfacePlateau = interfacePlateau;
    	
    	this.partie = new Partie(this.nb, this.regle, this.forme, this.tabJoueur);
    	
    	for(Joueur j : tabJoueur) {
    		j.getMain().addObserver(this);
    	}
    	
    	this.partie.getManche().addObserver(this.interfacePlateau);
    	this.partie.getManche().getPlateau().addObserver(this.interfacePlateau);
    	this.partie.getManche().getPioche().addObserver(this.interfacePlateau);
    	
    	this.partie.getManche().addObserver(this);
    	this.partie.getManche().getPlateau().addObserver(this);
    	this.partie.getManche().getPioche().addObserver(this);
    	
    	Thread t = new Thread(this);
    	t.start();
    }
	
	/*
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
			try {
				Scanner sc = new Scanner(System.in);
				nb = sc.nextInt();
			}
			catch (Exception e){
				System.out.println("Veuillez rentrer un chiffre");
				System.out.println("combien de manches voulez-vous jouer?");
			}
		}
		
		
		Partie partie = new Partie(nb, regle);
		
		//demander quelle forme de plateau on veut
		
		incorrectInput = true;
		
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
				incorrectInput = false;
				break;
			case "2" :
				partie.debutPartie(FormePlateau.HEXAGONE);
				incorrectInput = false;
				break;
			case "3" :
				partie.debutPartie(FormePlateau.CERCLE);
				incorrectInput = false;
				break;
			case "4" :
				// Rajouter le mode libre
				incorrectInput = false;
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
*/
	
    @Override
	public void run() {
		partie.debutPartie();
		partie.finPartie();
		partie.afficherScore();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
