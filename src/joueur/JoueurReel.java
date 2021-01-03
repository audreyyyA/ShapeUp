package joueur;

import java.util.Scanner;

import jeu.Carte;
import jeu.FormePlateau;
import jeu.Plateau;

public class JoueurReel extends Joueur{
	
	private boolean CVchang;
	
	public JoueurReel(String nom) {
		
		super(nom);
		this.setCVchang(false);
		
	}
	
	
	public void abandonner() {
		
	}
	
	public void afficherCarteVictoire() {
		
		
	}
	
	public void poserCarte(Plateau plateauActuel, int tour) {
		int index=-1; 
		boolean incorrectInput = true;
		int xPose=0 ,yPose=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Quelle carte voulez vous poser ? ");
		try {
			index = sc.nextInt();
		}
		catch(Exception e) {}
		
		while (index <0 || index > this.getMain().getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosis en un entre 0 et " + (this.getMain().getCartes().size()-1));// verif si string
			System.out.print("Quelle carte voulez vous poser ? ");
			try {
				sc = new Scanner(System.in);
				index = sc.nextInt();
			}
			catch(Exception e) {}
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
					if(plateauActuel.getForme() == FormePlateau.HEXAGONE) {
						plateauActuel.deplacerPlateau(plateauActuel.checkPosExtremiteHex(xPose,yPose));
					}
					else if((xPose == -1) || (yPose == -1) || (xPose == plateauActuel.getRemplissage().get(1).size()) || (yPose == plateauActuel.getRemplissage().size())){
						plateauActuel.deplacerPlateau(xPose,yPose);
					}
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
		else if(yPose == -1) {
			yPose =0;
		}
		else if(yPose == plateauActuel.getRemplissage().size()) {
			yPose = plateauActuel.getRemplissage().size()-1;
		}
		else if(xPose == plateauActuel.getRemplissage().get(yPose).size()) {
			xPose = plateauActuel.getRemplissage().get(yPose).size() -1;
		}

		
		//on pose la carte
		System.out.println(xPose + ","+yPose);
		plateauActuel.setRemplissage(xPose, yPose, this.main.getCarte(index));
		super.main.retirerCarte(index);
		
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
			if(plateau.getForme() == FormePlateau.HEXAGONE) {
				plateau.deplacerPlateau(plateau.checkPosExtremiteHex(xDeplacer,yDeplacer));
				incorrectInput = false;
			}
			else {
				if(plateau.checkPose(xDeplacer, yDeplacer)) {
					//changer ... tout avec le mm nom de méthode plus tard
					plateau.deplacerPlateau(xDeplacer,yDeplacer);
					incorrectInput = false;
				}
			}

			if(incorrectInput){
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
	
	public boolean isCVchang() {
		return CVchang;
	}


	public void setCVchang(boolean cVchang) {
		CVchang = cVchang;
	}

}
