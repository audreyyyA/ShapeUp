package jeu;

public class Carte {
	
	
	private Couleur couleur;
	private FormeCarte forme;
	private boolean rempli;
	
	public Carte(Couleur couleur, FormeCarte forme, boolean rempli) {
		
		this.couleur = couleur;
		this.forme = forme;
		this.rempli = rempli;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public FormeCarte getForme() {
		return forme;
	}

	public void setForme(FormeCarte forme) {
		this.forme = forme;
	}

	public boolean isRempli() {
		return rempli;
	}

	public void setRempli(boolean rempli) {
		this.rempli = rempli;
	}
	
	public String toString() {
		
		String s = null;
		
		if(this.rempli) {
			
			s = this.forme + " " + this.couleur + " REMPLI";
		}
		else {
			s = this.forme + " " + this.couleur + " VIDE";
		}
		
		
		return s;
	}

}
