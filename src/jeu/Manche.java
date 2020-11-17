package jeu;

import joueur.Joueur;

public class Manche {
	
	private Joueur gagnant;
	private boolean estTermin�;
	private IVisitor visitor;
	
	public Manche() {
		
		this.gagnant = null;
		this.estTermin� = false;
		this.visitor = new Visitor();
	}

	public Joueur getGagnant() {
		return gagnant;
	}

	public void setGagnant(Joueur gagnant) {
		
		this.gagnant = gagnant;
	}

	public boolean isEstTermin�() {
		return estTermin�;
	}

	public void setEstTermin�(boolean estTermin�) {
		this.estTermin� = estTermin�;
	}
	
	public void demarrerManche() {
		
	}
	
	public void finManche() {
		
	}

	
}
