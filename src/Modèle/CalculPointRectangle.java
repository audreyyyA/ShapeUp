package Modèle;

import java.util.ArrayList;
import java.util.Map;

/**
 * Classe définissant la manière de compter les points sur un plateau retangulaire
 * @author ALCARAZ, DUTOUR
 */
public class CalculPointRectangle extends CalculPointForme{
	
	public CalculPointRectangle() {
		super();
	}
	
	/**
	 * Calcul le nombres de points de tous les joueurs
	 * @param la liste des joueurs et le plateau
	 * @return la liste du/des joueurs ayant le plus de points
	 */
	public ArrayList<Joueur> calculnbPoints(ArrayList <Joueur>tabJoueur, Plateau plateau) {
		
		return super.calculnbPoints(tabJoueur, plateau);
	}
	
	/**
	 * Calcule les points d'un joueur à la fin d'une manche selon sa carte victoire et les cartes présentes sur le plateau
	 * @param la carte victoire du joueur et le plateau
	 * @return retourne le nombre de points
	 */
	public int calculPointJoueur(Carte CV,Plateau plateau) {
		boolean rempli = CV.isRempli();
		Couleur color = CV.getCouleur();
		FormeCarte forme = CV.getForme();
		ArrayList<ArrayList<Carte>> remplissage = plateau.getRemplissage();
		
		int nbPoint = 0;

		for(int y=0; y< remplissage.size(); y++) { 
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			for(int x=0; x<remplissage.get(y).size(); x++) {
				if(plateau.getCarte(x, y) != null) {
					if(plateau.getCarte(x, y).isRempli() == rempli) { 
						streakRempli ++;
					}
					else {
						nbPoint += super.pointRempli.get(streakRempli);
						streakRempli =0;
					}
					if(plateau.getCarte(x, y).getCouleur() == color) { 
						streakColor ++;
					}
					else {
						nbPoint += super.pointCouleur.get(streakColor);
						streakColor =0;
					}
					if(plateau.getCarte(x, y).getForme() == forme) { 
						streakForme ++;
					}
					else {
						nbPoint += super.pointForme.get(streakForme);
						streakForme =0;
					}
					
					if(x == remplissage.get(y).size()-1) {
						nbPoint += super.pointRempli.get(streakRempli);   
						nbPoint += super.pointForme.get(streakForme);
						nbPoint += super.pointCouleur.get(streakColor);
						streakRempli =0;
						streakForme = 0;
						streakColor = 0;
					}
				}
				else { 
					nbPoint += super.pointRempli.get(streakRempli); 
					nbPoint += super.pointForme.get(streakForme);
					nbPoint += super.pointCouleur.get(streakColor);
					streakRempli =0;
					streakForme = 0;
					streakColor = 0;
				}
			}
		}
		
		for(int x=0; x< remplissage.get(0).size(); x++) { 
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			for(int y=0; y<remplissage.size(); y++) {
				if(plateau.getCarte(x, y) != null) {
					if(plateau.getCarte(x, y).isRempli() == rempli) { 
						streakRempli ++;
					}
					else {
						nbPoint += super.pointRempli.get(streakRempli);
						streakRempli =0;
					}
					if(plateau.getCarte(x, y).getCouleur() == color) { 
						streakColor ++;
					}
					else {
						nbPoint += super.pointCouleur.get(streakColor);
						streakColor =0;
					}
					if(plateau.getCarte(x, y).getForme() == forme) { 
						streakForme ++;
					}
					else {
						nbPoint += super.pointForme.get(streakForme);
						streakForme =0;
					}
					if(y == remplissage.size()-1) {
						nbPoint += super.pointRempli.get(streakRempli);   
						nbPoint += super.pointForme.get(streakForme);
						nbPoint += super.pointCouleur.get(streakColor);
						streakRempli =0;
						streakForme = 0;
						streakColor = 0;
					}
					
				}
				else { 
					nbPoint += super.pointRempli.get(streakRempli); 
					nbPoint += super.pointForme.get(streakForme);
					nbPoint += super.pointCouleur.get(streakColor);
					streakRempli =0;
					streakForme = 0;
					streakColor = 0;
				}
			}
		}
		
		ArrayList <Integer> emptyStreak = new ArrayList<>();  
															 
		return nbPoint;
	}

	@Override
	public int[] calculnbPoints() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
