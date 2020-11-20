package joueur;

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
	private int [] nbPointsManches; //� voir
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
	
	public void poserCarte(Carte carte, Plateau plateauActuel, int x,int y) {
		
		
		plateauActuel.setRemplissage(x, y, carte);
		
	}
	
	public boolean askDeplacer() {
		
		System.out.println("Voulez vous d�placer une carte ? (O/N)");
		Scanner sc = new Scanner(System.in);
		
		if(sc.next().substring(0, 1).toUpperCase() == "O") {
			return true;
		}
		else if(sc.next().substring(0, 1).toUpperCase() == "N") {
			return false;
		}
		else {
			
			System.out.println("Je n'ai rien capt�!!!");
			return askDeplacer();
		}
	}
	
	public void finTour() {
		
		//appellera une autre m�thode pour changer de joueur
	}
	
	public void deplacerCarte(int xCarte, int yCarte, int xDeplacer, int yDeplacer, Plateau plateau) {
		
		//tester si on peut poser ici (dimension plateau)
		if(plateau.getCarte(xDeplacer, yDeplacer) == null) {
			System.out.println("La case o� tu veux d�placer la carte est d�j� prise");
		}
		else{
			plateau.setCarte(xDeplacer, yDeplacer, plateau.getCarte(xCarte, yCarte)); // d�place la carte
			plateau.setCarte(xCarte, yCarte, null); //enl�ve l'ancinne carte 
			plateau.afficherPlateau();
		}
	}
	
	
}
