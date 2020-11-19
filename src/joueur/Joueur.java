package joueur;

import java.util.ArrayList;

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
	
	public Carte piocherCarte(Pioche pioche,MainJoueur main) {
		Carte newCard = pioche.getRandomCarte();
		main.ajouterCarte(newCard);
		return null;
	}
	
	public void poserCarte(Carte carte, Plateau plateauActuel, int x,int y) {
		if(plateauActuel.getRemplissage().size()<= x || plateauActuel.getRemplissage().get(x).size() <= y) {
			System.out.println("Nique ta mere t'as pas le droit joué là");
		}
		else {
			ArrayList<ArrayList<Carte>> newRemplissage;
			newRemplissage = plateauActuel.getRemplissage();
			if(newRemplissage.get(x).get(y) == null) {
				newRemplissage.get(x).set(y,carte);
				System.out.println("La carte : "+carte+" a été posée en "+x+","+y);
			}
			else {
				System.out.println("Cette case est déjà prise");
			}
		}
	}
	
	public boolean askDeplacer() {
		
		boolean deplacer = false;
		return deplacer;
	}
	
	public void finTour() {
		
		//appellera une autre méthode pour changer de joueur
	}

	public void deplacerCarte() {
		
		
	}
	
	
}
