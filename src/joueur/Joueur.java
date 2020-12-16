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
	
	public Joueur (String nom) {
		
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

	public void setNbPointTotal(int pt) {
		this.NbPointTotal += pt;
	}

	public ArrayList<Integer> getNbPointsManches() {
		return nbPointsManches;
	}

	public void setPoints(int pt) {
		this.nbPointsManches.add(pt);
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
		
		while (index <0 || index > this.getMain().getCartes().size()-1) { // verif si string
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
				//on vérifie si c'est possible de poser la carte 
				if(plateauActuel.checkPose(xPose, yPose)) {
					
					//changer ... tout avec le mm nom de méthode plus tard
					plateauActuel.deplacerPlateau(xPose,yPose);
					/*
					if(plateauActuel.getForme()!= FormePlateau.CERCLE) {
						plateauActuel.deplacerPlateau(xPose,yPose);
					}
					else {
						plateauActuel.deplacerPlateauCercle(xPose);
					}*/
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
		
		//on change les abscisses ou ordonnées si nécessaire
	
		if(xPose == -1) {
			xPose =0;
		}
		if(yPose == -1) {
			yPose =0;
		}
		if(xPose == plateauActuel.getRemplissage().get(yPose).size()) {
			xPose = plateauActuel.getRemplissage().get(yPose).size() -1;
		}
		if(yPose == plateauActuel.getRemplissage().size()) {
			yPose = plateauActuel.getRemplissage().size()-1;
		}
		
		
		//on pose la carte
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
	
	public boolean deplacerCarte(Plateau plateau) {
		boolean deplacer = true;
		int xDeplacer=0,yDeplacer=0,xCarte=0,yCarte=0;
		Scanner sc = new Scanner(System.in);
		boolean incorrectInput = true;
		
		System.out.print("Abcisse de la carte ? : ");
		xCarte = sc.nextInt();
		System.out.print("Ordonnée de la carte ? : ");
		yCarte = sc.nextInt();
		
		if(plateau.getCarte(xCarte, yCarte) == null) {
			System.out.println("Tu as choisis un emplacement sans cartes !\n");
			return false;

		}
		
		System.out.println("la carte que tu veux déplacer : " + plateau.getCarte(xCarte, yCarte));
		Carte carteTemp = plateau.getCarte(xCarte, yCarte);
			
		while(incorrectInput) {
			System.out.print("Abscisse de pose : ");
			xDeplacer = sc.nextInt();
			System.out.print("Ordonnée de pose : ");
			yDeplacer = sc.nextInt();
			plateau.setCarte(xCarte, yCarte, null);
				//on vérifie si c'est possible de poser la carte 
			if(plateau.checkPose(xDeplacer, yDeplacer)) {
				//changer ... tout avec le mm nom de méthode plus tard
				plateau.deplacerPlateau(xDeplacer,yDeplacer);
				incorrectInput = false;
			}
			else {
				plateau.setRemplissage(xCarte, yCarte, carteTemp);
				//plateau.setCarte(xCarte, yCarte, carteTemp);
				System.out.println("Tu ne peux pas poser de carte ici");
			}
		}
			
		
		if(xDeplacer == -1) {
			xDeplacer =0;
		}
		if(yDeplacer == -1) {
			yDeplacer =0;
		}
		if(xDeplacer == plateau.getRemplissage().get(plateau.getRemplissage().size()-1).size()) {
			xDeplacer = plateau.getRemplissage().get(plateau.getRemplissage().size()-1).size() -1;
		}
		if(yDeplacer == plateau.getRemplissage().size()) {
			yDeplacer = plateau.getRemplissage().size()-1;
		}
		
		//on pose la carte
		plateau.setRemplissage(xDeplacer, yDeplacer, carteTemp);
		plateau.afficherPlateau();
		return true;
	}
	
	public String toString() {
		
		String s = this.nom + ", total des points gagnés : " + this.NbPointTotal;
		return s;
		
	}
	
}
