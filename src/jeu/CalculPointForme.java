package jeu;

import java.util.ArrayList;
import java.util.Map;

public abstract class CalculPointForme implements IVisitor{

	protected Map <Integer,Integer> pointRempli;
	protected Map <Integer,Integer> pointCouleur;
	protected Map <Integer,Integer> pointForme;
	
	public CalculPointForme() {
		
		// Pour fixer les points associés aux différentes streaks
		this.pointRempli =  Map.of(0, 0, 1, 0, 2, 0, 3, 3, 4, 4, 5, 5);
		this.pointForme =  Map.of(0, 0, 1, 0, 2, 1, 3, 2, 4, 3, 5, 4);
		this.pointCouleur = Map.of(0, 0, 1, 0, 2, 0, 3, 4, 4, 5, 5, 6);
		
	} 
	
	@Override
	public ArrayList<Integer> calculnbPoints() {
		// TODO Auto-generated method stub
		return null;
	}

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
