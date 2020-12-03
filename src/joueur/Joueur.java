package joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import jeu.Carte;
import jeu.FormePlateau;
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
	private ArrayList<Integer> nbPointsManches; //à voir
	private MainJoueur main;
	
	public Joueur (String nom, boolean v) {
		
		this.nom = nom;
		this.NbPointTotal = 0;
		this.carteVictoire = null;
		this.main = new MainJoueur();
		this.nbPointsManches = new ArrayList<>();
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

	public ArrayList<Integer> getNbPointsManches() {
		return nbPointsManches;
	}

	public void setNbPointsManches(ArrayList<Integer> nbPointsManches) {
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
	
	public void poserCarte(Plateau plateauActuel, int tour) {
		int index=-1; 
		boolean incorrectInput = true;
		int xPose=0 ,yPose=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Quelle carte voulez vous poser ? ");
		index = sc.nextInt();
		
		while (index <0 || index > this.getMain().getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosis en un autre");
			System.out.print("Quelle carte voulez vous poser ? ");
			index = sc.nextInt();
		}
	
		while(incorrectInput) {
			System.out.print("Abscisse de pose : ");
			xPose = sc.nextInt();
			System.out.print("Ordonnée de pose : ");
			yPose = sc.nextInt();
			if(tour !=1) {
				if(plateauActuel.checkPose(xPose, yPose)) {
					incorrectInput = false;
				}
				else {
					System.out.println("Tu ne peux pas poser de carte ici");
				}
			}
			else {
				incorrectInput = false;
			}
		}
		
		
		if(plateauActuel.getForme() != FormePlateau.CERCLE) {
			if(xPose == -1) {
				xPose =0;
			}
			if(yPose == -1) {
				yPose =0;
			}
			if(xPose == plateauActuel.getRemplissage().get(yPose).size()) {
				yPose = plateauActuel.getRemplissage().get(yPose).size() -1;
			}
			if(yPose == plateauActuel.getRemplissage().size()) {
				xPose = plateauActuel.getRemplissage().size()-1;
			}
		}
		else {
			if(yPose == plateauActuel.getRemplissage().size()) {
				yPose = plateauActuel.getRemplissage().size()-1;
			}
		}
		
		
		plateauActuel.setRemplissage(xPose, yPose, this.main.getCarte(index));
		this.main.retirerCarte(index);
		
	}
	
	public boolean askDeplacer(){
		
		System.out.print("Voulez vous déplacer une carte ? (O/N) ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		if(s.equals("O") || s.equals("o")) {
			return true;
		}
		else if(s.equals("N") || s.equals("n")) {
			return false;
		}
		else {
			System.out.println("Rentrez O ou N");
			return askDeplacer();
		}
		
	}
	
	public void finTour() {
		
		//appellera une autre méthode pour changer de joueur
	}
	
	
	public void deplacerCarte(Plateau plateau) {
		boolean deplacer = true;
		int xDeplacer=0,yDeplacer=0,xCarte=0,yCarte=0;
		Scanner sc = new Scanner(System.in);
		while(deplacer) {
			System.out.print("Abcisse de la carte ? : ");
			xCarte = sc.nextInt();
			System.out.print("Ordonnée de la carte ? : ");
			yCarte = sc.nextInt();
			
			if(plateau.getCarte(xCarte, yCarte) != null) {
				System.out.println("la carte que tu veux déplacer : " + plateau.getCarte(xCarte, yCarte));
				System.out.print("Ou veux tu la poser ? Abcisse: ");
				xDeplacer = sc.nextInt();
				System.out.print("Ordonnée: ");
				yDeplacer = sc.nextInt();
				deplacer = false;
			}
			
			else{
				System.out.println("Tu as choisis un emplacement sans cartes !\n");
				deplacer = this.askDeplacer();
			}
		}
		
		plateau.checkPose(xDeplacer, yDeplacer);
		
		if(plateau.getForme() != FormePlateau.CERCLE) {
			if(xDeplacer == -1) {
				xDeplacer =0;
			}
			if(yDeplacer == -1) {
				yDeplacer =0;
			}
			if(xDeplacer == plateau.getRemplissage().get(yDeplacer).size()) {
				yDeplacer = plateau.getRemplissage().get(yDeplacer).size() -1;
			}
			if(yDeplacer == plateau.getRemplissage().size()) {
				xDeplacer = plateau.getRemplissage().size()-1;
			}
		}
		else {
			if(yDeplacer == plateau.getRemplissage().size()) {
				yDeplacer = plateau.getRemplissage().size()-1;
			}
		}
		
		
		if(plateau.getCarte(xDeplacer, yDeplacer) != null) {
			System.out.println("La case où tu veux déplacer la carte est déjà prise");
		}
		else{
			plateau.setCarte(xDeplacer, yDeplacer, plateau.getCarte(xCarte, yCarte)); // déplace la carte
			plateau.setCarte(xCarte, yCarte, null); //enlève l'ancienne carte 
			plateau.afficherPlateau();
		}
		//tester si on peut poser ici (dimension plateau)
	}
	
	
}
