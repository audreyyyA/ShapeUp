package Modèle;

import java.util.ArrayList;

public class CalculPointCercle extends CalculPointForme {
	
	public CalculPointCercle() {
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
		int milieu = remplissage.size();
		
		for(int y=1; y<remplissage.size(); y++) { // Boucle pour trouver les streaks de remplissage sur les cercles concentriques
			boolean endCircle = false;
			int xCercle = 0;
			int nbRepos =0;
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			int arret = 1;
			int nbPassage =0;
			
			while(!endCircle) { // Boucle Pour le remplissage 
				if(xCercle == arret) {
					nbPoint += super.pointRempli.get(streakRempli);
					break;
				}

				while(plateau.getCarte(xCercle, y) != null) { // permet de se mettre au début de la streak 
					if(plateau.getCarte(xCercle, y).isRempli() == true) {
						
						if(nbRepos > remplissage.get(y).size()-1) {
							break;
						}
						if(xCercle == 0) {
							xCercle = remplissage.get(y).size()-1;
						}
						else {
							xCercle --;
						}
						nbRepos ++;
					}
					else {
						break;
					}
				}
				
				
				if(nbPassage ==0) {
					arret = xCercle;
					nbPassage ++;
				}
				
				if(xCercle == remplissage.get(y).size()-1) {
					xCercle = 0;
				}
				else {
					xCercle ++;
				}
				
				nbRepos = 0;
				
				while(plateau.getCarte(xCercle, y) != null) {
					if(plateau.getCarte(xCercle, y).isRempli() == true) {
						if(nbRepos >= remplissage.get(y).size()) {
							nbPoint += super.pointRempli.get(streakRempli);
							streakRempli =0;
							break;
						}
						if(xCercle == remplissage.get(y).size()-1) {
							streakRempli ++;
							xCercle = 0;
						}
						else {
							streakRempli ++;
							xCercle ++;
						}
						nbRepos ++;
					}
					else {
						break;
					}
				}
				nbPoint += super.pointRempli.get(streakRempli);
				streakRempli =0;
				xCercle ++;
				break;
			}
			
			xCercle = 0;
			nbRepos =0;
			arret = 1;
			nbPassage =0;
			
			while(!endCircle) { // Boucle Pour la couleur 
				if(xCercle == arret) {
					nbPoint += super.pointCouleur.get(streakColor);
					break;
				}

				while(plateau.getCarte(xCercle, y) != null) { // permet de se mettre au début de la streak 
					if(plateau.getCarte(xCercle, y).getCouleur() == color) {
						
						if(nbRepos >= remplissage.get(y).size()-1) {
							break;
						}
						if(xCercle == 0) {
							xCercle = remplissage.get(y).size()-1;
						}
						else {
							xCercle --;
						}
						nbRepos ++;
					}
					else {
						break;
					}
				}
				
				
				if(nbPassage ==0) {
					arret = xCercle;
					nbPassage ++;
				}
				
				if(xCercle == remplissage.get(y).size()-1) {
					xCercle = 0;
				}
				else {
					xCercle ++;
				}
				
				nbRepos = 0;
				
				while(plateau.getCarte(xCercle, y) != null) {
					if(plateau.getCarte(xCercle, y).getCouleur() == color) {
						if(nbRepos >= remplissage.get(y).size()) {
							nbPoint += super.pointCouleur.get(streakColor);
							streakColor =0;
							break;
						}
						if(xCercle == remplissage.get(y).size()-1) {
							streakColor ++;
							xCercle = 0;
						}
						else {
							streakColor ++;
							xCercle ++;
						}
						nbRepos ++;
					}
					else {
						break;
					}
				}
				nbPoint += super.pointCouleur.get(streakColor);
				streakColor =0;
				xCercle ++;
				break;
			}
			
			xCercle = 0;
			nbRepos =0;
			arret = 1;
			nbPassage =0;	
			
			while(!endCircle) { // Boucle Pour la forme
				if(xCercle == arret) {
					nbPoint += super.pointForme.get(streakForme);
					break;
				}

				while(plateau.getCarte(xCercle, y) != null) { // permet de se mettre au début de la streak 
					if(plateau.getCarte(xCercle, y).getForme() == forme) {
						
						if(nbRepos > remplissage.get(y).size()-1) {
							break;
						}
						if(xCercle == 0) {
							xCercle = remplissage.get(y).size()-1;
						}
						else {
							xCercle --;
						}
						nbRepos ++;
					}
					else {
						break;
					}
				}
				
				
				if(nbPassage ==0) {
					arret = xCercle;
					nbPassage ++;
				}
				
				if(xCercle == remplissage.get(y).size()-1) {
					xCercle = 0;
				}
				else {
					xCercle ++;
				}
				
				nbRepos = 0;
				
				while(plateau.getCarte(xCercle, y) != null) {
					if(plateau.getCarte(xCercle, y).getForme() == forme) {
						if(nbRepos >= remplissage.get(y).size()) {
							nbPoint += super.pointForme.get(streakForme);
							streakForme =0;
							break;
						}
						if(xCercle == remplissage.get(y).size()-1) {
							streakForme ++;
							xCercle = 0;
						}
						else {
							streakForme ++;
							xCercle ++;
						}
						nbRepos ++;
					}
					else {
						break;
					}
				}
				nbPoint += super.pointForme.get(streakForme);
				streakForme =0;
				xCercle ++;
				break;
			}
		}

		for(int i =0; i<remplissage.get(remplissage.size()-1).size()/2;i++){// Calcul des points sur les lignes transverse
			
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			
			int x = i;
			int Ypos = remplissage.size();
			
			for(int j = 0; j<(remplissage.size()*2)-1; j++) {
				
				if(j<milieu) {
					Ypos --;
				}
				else {
					Ypos ++;
				}
				
				int oldX = 0;
				if(Ypos ==0) {
					oldX = x;
					x=0;
				}
				
				if(plateau.getCarte(x, Ypos) != null) {
					if(plateau.getCarte(x, Ypos).isRempli() == rempli) { // check streak remplissage
						streakRempli ++;
					}
					else {
						nbPoint += super.pointRempli.get(streakRempli);
						streakRempli =0;
					}
					if(plateau.getCarte(x, Ypos).getCouleur() == color) { // check streak color
						streakColor ++;
					}
					else {
						nbPoint += super.pointCouleur.get(streakColor);
						streakColor =0;
					}
					if(plateau.getCarte(x, Ypos).getForme() == forme) { // check streak forme
						streakForme ++;
					}
					else {
						nbPoint += super.pointForme.get(streakForme);
						streakForme =0;
					}
					
					
					if(Ypos == remplissage.size()-1 && x>=remplissage.get(Ypos).size()/2) {//Au cas où la dernière carte de la colonne continue la streak
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
				if(Ypos == 0) {
					x = oldX + milieu-1;
				}
				
			}
		}
		return nbPoint;
	}

	@Override
	public int[] calculnbPoints() {
		// TODO Auto-generated method stub
		return null;
	}
}
