package Modèle;

import java.util.ArrayList;
import java.util.Map;

/**
 * Super Classe permettant de calculer les differents points durant une partie
 * @author ALCARAZ, DUTOUR
 */
public class Visitor implements IVisitor{

	@Override
	public int[] calculnbPoints() {
		
		return null;
	}

	/**
	 * Permet de calculer les points des joueurs
	 * @param la liste des joueurs ArrayList<Joueur> tabJoueur et le Plateau plateau actuel
	 */
	@Override
	public ArrayList<Joueur> calculnbPoints(ArrayList<Joueur> tabJoueur, Plateau plateau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculPointJoueur(Carte carteVictoire, Plateau plateau) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Integer, Integer> getPointRempli() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointRempli(Map<Integer, Integer> pointRempli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Integer> getPointCouleur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointCouleur(Map<Integer, Integer> pointCouleur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Integer> getPointForme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPointForme(Map<Integer, Integer> pointForme) {
		// TODO Auto-generated method stub
		
	}

}
