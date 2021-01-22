package Modèle;

/**
 * Classe représentant une carte du jeu
 * @author ALCARAZ, DUTOUR
 */
public class Carte {
	
	/**
	 * @see fait référence à : Couleur, FormeCarte
	 * 
	 */
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
			s = this.forme.toString().toCharArray()[0] +""+ this.couleur.toString().toCharArray()[0] + "R";
		}
		else {
			s = this.forme.toString().toCharArray()[0] + "" + this.couleur.toString().toCharArray()[0] + "V";
		}
		
		return s;
	}

}
