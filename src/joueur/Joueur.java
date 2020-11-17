package joueur;

import jeu.Carte;

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
		this.main = null;
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
	
	public Carte piocherCarte() {
		
		return null;
	}
	
	public void poserCarte() {
		
		
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
