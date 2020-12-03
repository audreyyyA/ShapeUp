package jeu;

import java.util.ArrayList;
import java.util.Map;

import joueur.Joueur;

public interface IVisitor {

	public Joueur calculnbPoints(ArrayList <Joueur>tabJoueur, Plateau plateau);
	public int calculPointJoueur(Carte carteVictoire, Plateau plateau);
	public Map<Integer, Integer> getPointRempli();

	public void setPointRempli(Map<Integer, Integer> pointRempli);
	public Map<Integer, Integer> getPointCouleur();

	public void setPointCouleur(Map<Integer, Integer> pointCouleur);
	public Map<Integer, Integer> getPointForme();
	public void setPointForme(Map<Integer, Integer> pointForme);
	
}
