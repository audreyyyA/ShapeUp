package jeu;

import java.util.ArrayList;
import joueur.Joueur;

public class CalculPointCercle extends CalculPointForme {
	
	public CalculPointCercle() {
		super();
	}
	
	public void Calculpoint(ArrayList <Joueur>tabJoueur, Plateau plateau){
		for(Joueur j : tabJoueur) {
			j.getNbPointsManches().add(calculPointJoueur(j.getCarteVictoire(),plateau)); // juste changer le getter suffit ou il faut le set aussi ?
		}
	}
	
	public int calculPointJoueur(Carte CV, Plateau plateau) {
		boolean rempli = CV.isRempli();
		Couleur color = CV.getCouleur();
		FormeCarte forme = CV.getForme();
		ArrayList<ArrayList<Carte>> remplissage = plateau.getRemplissage();
		
		int nbPoint = 0;
		int milieu = remplissage.size();
		
		int Ypos = remplissage.size();

		for(int x =0; x<remplissage.get(remplissage.size()).size()/2;x++){// Calcul des points sur les lignes transverse
			
			int streakRempli = 0;
			int streakForme = 0;
			int streakColor = 0;
			
			
			for(int j = 0; j<(remplissage.size()*2)-1; j++) {
				
				if(j<milieu) {
					Ypos --;
				}
				else {
					Ypos ++;
				}
				
				System.out.println(Ypos);
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
					
					if(Ypos == remplissage.size()-1) {//Au cas o� la derni�re carte de la colonne continue la streak
						nbPoint += super.pointRempli.get(streakRempli);   //Il faut la compter avant de sortir de la boucle et de la reset
						nbPoint += super.pointForme.get(streakForme);
						nbPoint += super.pointCouleur.get(streakColor);
						streakRempli =0;
						streakForme = 0;
						streakColor = 0;
					}
				}
			}
		}
		
		System.out.println(nbPoint);
		return 0;
	}
}
