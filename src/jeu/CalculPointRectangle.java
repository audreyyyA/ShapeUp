package jeu;

import java.util.ArrayList;
import java.util.Map;

import joueur.Joueur;

public class CalculPointRectangle extends CalculPointForme{
	
	public CalculPointRectangle() {
		super();
	}
	
	public Joueur calculnbPoints(ArrayList <Joueur>tabJoueur, Plateau plateau) {
		
		return super.calculnbPoints(tabJoueur, plateau);
	}
	
	public int calculPointJoueur(Carte CV,Plateau plateau) {
		boolean rempli = CV.isRempli();
		Couleur color = CV.getCouleur();
		FormeCarte forme = CV.getForme();
		ArrayList<ArrayList<Carte>> remplissage = plateau.getRemplissage();
		
		int nbPoint = 0;

		for(int y=0; y< remplissage.size(); y++) { //Calcul des points sur les lignes
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			for(int x=0; x<remplissage.get(y).size(); x++) {
				if(plateau.getCarte(x, y) != null) {
					if(plateau.getCarte(x, y).isRempli() == rempli) { // check streak remplissage
						streakRempli ++;
					}
					else {
						nbPoint += super.pointRempli.get(streakRempli);
						streakRempli =0;
					}
					if(plateau.getCarte(x, y).getCouleur() == color) { // check streak color
						streakColor ++;
					}
					else {
						nbPoint += super.pointCouleur.get(streakColor);
						streakColor =0;
					}
					if(plateau.getCarte(x, y).getForme() == forme) { // check streak forme
						streakForme ++;
					}
					else {
						nbPoint += super.pointForme.get(streakForme);
						streakForme =0;
					}
					
					if(x == remplissage.get(y).size()-1) {//Au cas où la dernière carte de la colonne continue la streak
						nbPoint += super.pointRempli.get(streakRempli);   //Il faut la compter avant de sortir de la boucle et de la reset
						nbPoint += super.pointForme.get(streakForme);
						nbPoint += super.pointCouleur.get(streakColor);
						streakRempli =0;
						streakForme = 0;
						streakColor = 0;
					}
				}
				else { // Si carte nulle, on casse toutes les streaks 
					nbPoint += super.pointRempli.get(streakRempli); 
					nbPoint += super.pointForme.get(streakForme);
					nbPoint += super.pointCouleur.get(streakColor);
					streakRempli =0;
					streakForme = 0;
					streakColor = 0;
				}
			}
		}
		
		for(int x=0; x< remplissage.get(0).size(); x++) { //Calcul des points sur les colonnes
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			for(int y=0; y<remplissage.size(); y++) {
				if(plateau.getCarte(x, y) != null) {
					if(plateau.getCarte(x, y).isRempli() == rempli) { // check streak remplissage
						streakRempli ++;
					}
					else {
						nbPoint += super.pointRempli.get(streakRempli);
						streakRempli =0;
					}
					if(plateau.getCarte(x, y).getCouleur() == color) { // check streak color
						streakColor ++;
					}
					else {
						nbPoint += super.pointCouleur.get(streakColor);
						streakColor =0;
					}
					if(plateau.getCarte(x, y).getForme() == forme) { // check streak forme
						streakForme ++;
					}
					else {
						nbPoint += super.pointForme.get(streakForme);
						streakForme =0;
					}
					if(y == remplissage.size()-1) {//Au cas où la dernière carte de la colonne continue la streak
						nbPoint += super.pointRempli.get(streakRempli);   //Il faut la compter avant de sortir de la boucle et de la reset
						nbPoint += super.pointForme.get(streakForme);
						nbPoint += super.pointCouleur.get(streakColor);
						streakRempli =0;
						streakForme = 0;
						streakColor = 0;
					}
					
				}
				else { // Si carte nulle, on casse toutes les streaks 
					nbPoint += super.pointRempli.get(streakRempli); 
					nbPoint += super.pointForme.get(streakForme);
					nbPoint += super.pointCouleur.get(streakColor);
					streakRempli =0;
					streakForme = 0;
					streakColor = 0;
				}
			}
		}
		
		ArrayList <Integer> emptyStreak = new ArrayList<>(); // a voir si on veut juste retourner les points ou une liste avec le 
															 // détail
		return nbPoint;
	}

	@Override
	public int[] calculnbPoints() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
