package jeu;

import java.util.ArrayList;

import joueur.Joueur;

public class CalculPointHexagone extends CalculPointForme {
	
	public CalculPointHexagone() {
		super();
	}
	
	public ArrayList<Joueur> calculnbPoints(ArrayList <Joueur>tabJoueur, Plateau plateau) {
		
		return super.calculnbPoints(tabJoueur, plateau);
	}
	
	public int calculPointJoueur(Carte CV, Plateau plateau) {
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
		
		int milieu = remplissage.size()/2;
		int debutDiago = milieu+1;
		int finDiago = remplissage.size();
		
		for(int nbDiago=0; nbDiago <remplissage.size(); nbDiago ++) {// check les diagos orientées bas droite
			
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			int Xcase = 0;
			
			if(nbDiago>milieu) {
				Xcase = nbDiago - milieu;
			}
			
			if(nbDiago <= milieu ) {
				debutDiago --;
				for(int i = debutDiago; i < remplissage.size(); i ++) {
					if (plateau.getCarte(Xcase,i) != null){
						if(plateau.getCarte(Xcase,i).isRempli() == rempli) { // check streak remplissage
							streakRempli ++;
						}
						else {
							nbPoint += super.pointRempli.get(streakRempli);
							streakRempli =0;
						}
						if(plateau.getCarte(Xcase,i).getCouleur() == color) { // check streak color
							streakColor ++;
						}
						else {
							nbPoint += super.pointCouleur.get(streakColor);
							streakColor =0;
						}
						if(plateau.getCarte(Xcase,i).getForme() == forme) { // check streak forme
							streakForme ++;
						}
						else {
							nbPoint += super.pointForme.get(streakForme);
							streakForme =0;
						}
						
						if(i == remplissage.size()-1) {//Au cas où la dernière carte de la colonne continue la streak
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
					if(i<milieu) {
						Xcase ++;
					}
				}
			}
			else {
				finDiago --;
				
				for(int i = debutDiago; i < finDiago; i ++) {
					if (plateau.getCarte(Xcase,i) != null){
						if(plateau.getCarte(Xcase,i).isRempli() == rempli) { // check streak remplissage
							streakRempli ++;
						}
						else {
							nbPoint += super.pointRempli.get(streakRempli);
							streakRempli =0;
						}
						if(plateau.getCarte(Xcase,i).getCouleur() == color) { // check streak color
							streakColor ++;
						}
						else {
							nbPoint += super.pointCouleur.get(streakColor);
							streakColor =0;
						}
						if(plateau.getCarte(Xcase,i).getForme() == forme) { // check streak forme
							streakForme ++;
						}
						else {
							nbPoint += super.pointForme.get(streakForme);
							streakForme =0;
						}
						
						if(i == finDiago-1) {//Au cas où la dernière carte de la colonne continue la streak
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
					if(i<milieu) {
						Xcase ++;
					}
				}
			}
		}

		
		debutDiago = milieu+1;
		finDiago = remplissage.size();
		
		for(int nbDiago=remplissage.size(); nbDiago >0; nbDiago --) {// check les diagos orientées bas gauche
			
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			int Xcase = 0;
			
			if(nbDiago>=milieu) {
				Xcase = nbDiago - 1;
			}
			
			if(nbDiago > milieu ) {
				debutDiago --;
				for(int i = debutDiago; i < remplissage.size(); i ++) {
					if (plateau.getCarte(Xcase,i) != null){
						if(plateau.getCarte(Xcase,i).isRempli() == rempli) { // check streak remplissage
							streakRempli ++;
						}
						else {
							nbPoint += super.pointRempli.get(streakRempli);
							streakRempli =0;
						}
						if(plateau.getCarte(Xcase,i).getCouleur() == color) { // check streak color
							streakColor ++;
						}
						else {
							nbPoint += super.pointCouleur.get(streakColor);
							streakColor =0;
						}
						if(plateau.getCarte(Xcase,i).getForme() == forme) { // check streak forme
							streakForme ++;
						}
						else {
							nbPoint += super.pointForme.get(streakForme);
							streakForme =0;
						}
						
						if(i == remplissage.size()-1) {//Au cas où la dernière carte de la colonne continue la streak
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
					if(i>=milieu) {
						Xcase --;
					}
				}
			}
			else {
				finDiago --;
				
				for(int i = debutDiago; i < finDiago; i ++) {
					if (plateau.getCarte(Xcase,i) != null){
						if(plateau.getCarte(Xcase,i).isRempli() == rempli) { // check streak remplissage
							streakRempli ++;
						}
						else {
							nbPoint += super.pointRempli.get(streakRempli);
							streakRempli =0;
						}
						if(plateau.getCarte(Xcase,i).getCouleur() == color) { // check streak color
							streakColor ++;
						}
						else {
							nbPoint += super.pointCouleur.get(streakColor);
							streakColor =0;
						}
						if(plateau.getCarte(Xcase,i).getForme() == forme) { // check streak forme
							streakForme ++;
						}
						else {
							nbPoint += super.pointForme.get(streakForme);
							streakForme =0;
						}
						
						if(i == finDiago-1) {//Au cas où la dernière carte de la colonne continue la streak
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
					if(i>=milieu) {
						Xcase --;
					}
				}
			}
		}

		System.out.println(nbPoint);
		return nbPoint;
	}

	@Override
	public int[] calculnbPoints() {
		// TODO Auto-generated method stub
		return null;
	}
}
