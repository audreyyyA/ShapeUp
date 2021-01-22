package Modèle;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author ALCARAZ, DUTOUR
 * Interface de la classe visitor pour calculer les différents types de points au sein du jeu
 * 
 */
public interface IVisitor {

	public ArrayList<Joueur> calculnbPoints(ArrayList <Joueur>tabJoueur, Plateau plateau);
	public int calculPointJoueur(Carte carteVictoire, Plateau plateau);
	public Map<Integer, Integer> getPointRempli();

	public void setPointRempli(Map<Integer, Integer> pointRempli);
	public Map<Integer, Integer> getPointCouleur();

	public void setPointCouleur(Map<Integer, Integer> pointCouleur);
	public Map<Integer, Integer> getPointForme();
	public void setPointForme(Map<Integer, Integer> pointForme);
	int[] calculnbPoints();
	
}
