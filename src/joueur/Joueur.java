package joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import jeu.Carte;
import jeu.Pioche;
import jeu.Plateau;

/**
 * 
 * @author dedeyy
 * @version 1.0
 *
 */

public class Joueur{
	
	private String nom;
	private int NbPointTotal;
	private Carte carteVictoire;
	private int [] nbPointsManches; //à voir
	private MainJoueur main;
	
	public Joueur (String nom, boolean v) {
		
		this.nom = nom;
		this.NbPointTotal = 0;
		this.carteVictoire = null;
		this.main = new MainJoueur();
	}

	public Carte getCarteVictoire() {
		
		return carteVictoire;
	}

	public void setCarteVictoire(Carte carteVictoire) {
		this.carteVictoire = carteVictoire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbPointTotal() {
		return NbPointTotal;
	}

	public void setNbPointTotal(int nbPointTotal) {
		NbPointTotal = nbPointTotal;
	}

	public int[] getNbPointsManches() {
		return nbPointsManches;
	}

	public void setNbPointsManches(int[] nbPointsManches) {
		this.nbPointsManches = nbPointsManches;
	}

	public MainJoueur getMain() {
		return main;
	}

	public void setMain(MainJoueur main) {
		this.main = main;
	}
	
	public Carte piocherCarte(Pioche pioche) {
		Carte newCard = pioche.getRandomCarte();
		//this.main.ajouterCarte(newCard);
		return newCard;
	}
	
	public void poserCarte(int index, Plateau plateauActuel, int x,int y) {
		
		
		plateauActuel.setRemplissage(x, y, this.main.getCarte(index));
		this.main.retirerCarte(index);
		
	}
	
	public boolean askDeplacer(){
		
		System.out.println("Voulez vous déplacer une carte ? (O/N)");
		Scanner sc = new Scanner(System.in);
		//String s= "";
		//s = sc.next();
		String s = sc.nextLine();
			
		System.out.println("la reponse  : " + s);
		
		if(s.equals("O") || s.equals("o")) {
			System.out.println("ta reponse frereee ets un oui et c : " );
			
			return true;
		}
		else if(s.equals("N") || s.equals("n")) {
			System.out.println("ta reponse frereee NON: ");
			return false;
		}
		else {
			
			System.out.println("Je n'ai rien capté!!!");
			return askDeplacer();
		}
		
	}
	
	public void finTour() {
		
		//appellera une autre méthode pour changer de joueur
	}
	
	public void deplacerCarte(int xCarte, int yCarte, int xDeplacer, int yDeplacer, Plateau plateau) {
		
		//tester si on peut poser ici (dimension plateau)
		if(plateau.getCarte(xDeplacer, yDeplacer) != null) {
			System.out.println("La case où tu veux déplacer la carte est déjà prise");
		}
		else{
			plateau.setCarte(xDeplacer, yDeplacer, plateau.getCarte(xCarte, yCarte)); // déplace la carte
			plateau.setCarte(xCarte, yCarte, null); //enlève l'ancinne carte 
			plateau.afficherPlateau();
		}
	}
	
	
}
