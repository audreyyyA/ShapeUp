package jeu;

import joueur.Joueur;

public class Manche {
	
	private Joueur gagnant;
	private boolean estTerminé;
	private IVisitor visitor;
	
	public Manche() {
		
		this.gagnant = null;
		this.estTerminé = false;
		this.visitor = new Visitor();
	}

	public Joueur getGagnant() {
		return gagnant;
	}

	public void setGagnant(Joueur gagnant) {
		
		this.gagnant = gagnant;
	}

	public boolean isEstTerminé() {
		return estTerminé;
	}

	public void setEstTerminé(boolean estTerminé) {
		this.estTerminé = estTerminé;
	}
	
	public void demarrerManche() {
		
	}
	
	public void finManche() {
		
	}

	
}
