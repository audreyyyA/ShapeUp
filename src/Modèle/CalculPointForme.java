package Modèle;

import java.util.ArrayList;
import java.util.Map;

public abstract class CalculPointForme implements IVisitor{

	protected Map <Integer,Integer> pointRempli;
	protected Map <Integer,Integer> pointCouleur;
	protected Map <Integer,Integer> pointForme;
	
	public CalculPointForme() {
		
		this.pointRempli =  Map.of(0, 0, 1, 0, 2, 0, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7);
		this.pointForme =  Map.of(0, 0, 1, 0, 2, 1, 3, 2, 4, 3, 5, 4, 6, 5, 7, 6);
		this.pointCouleur = Map.of(0, 0, 1, 0, 2, 0, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8);
		
	} 
	
	/**
	 * retourne le joueur ayec le meilleur score
	 */
	@Override
	public ArrayList<Joueur> calculnbPoints(ArrayList <Joueur>tabJoueur, Plateau plateau) {
		ArrayList <Joueur> jGagnant = new ArrayList<Joueur>();
		int tmp = 0;
		for(Joueur j : tabJoueur) {
			if(tmp < calculPointJoueur(j.getCarteVictoire(),plateau)) {
				jGagnant.clear();
				jGagnant.add(j);
			}
			else if(tmp == calculPointJoueur(j.getCarteVictoire(),plateau)) {
				jGagnant.add(j);
			}
			tmp = calculPointJoueur(j.getCarteVictoire(),plateau);
			j.setPoints(tmp);
			j.setNbPointTotal(tmp);
		}
		
		return jGagnant;
	}

	public abstract int calculPointJoueur(Carte carteVictoire, Plateau plateau);

	public Map<Integer, Integer> getPointRempli() {
		return pointRempli;
	}

	public void setPointRempli(Map<Integer, Integer> pointRempli) {
		this.pointRempli = pointRempli;
	}

	public Map<Integer, Integer> getPointCouleur() {
		return pointCouleur;
	}

	public void setPointCouleur(Map<Integer, Integer> pointCouleur) {
		this.pointCouleur = pointCouleur;
	}

	public Map<Integer, Integer> getPointForme() {
		return pointForme;
	}

	public void setPointForme(Map<Integer, Integer> pointForme) {
		this.pointForme = pointForme;
	}
	
}
