package Modèle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import Vue.VueTexte;

public abstract class Plateau extends Observable implements Cloneable {
	
	protected FormePlateau forme;
	protected ArrayList<ArrayList<Carte>> remplissage;
	private VueTexte vueTexte = new VueTexte();
	
	public Plateau(FormePlateau forme){
		this.forme = forme;
		System.out.println("Le plateau est déjà crée");
	}

	public FormePlateau getForme() {
		return forme;
	}

	public void setForme(FormePlateau forme) {
		this.forme = forme;
	}

	
	public Carte getCarte(int x, int y) {
		if(y >= this.remplissage.size() || y<0 || x<0 || x >= this.remplissage.get(y).size()) {
			return null;
		}
		return this.remplissage.get(y).get(x);
	}
	
	public void setCarte(int x, int y, Carte carte) {
		this.remplissage.get(y).set(x, carte);
		this.setChanged();
		this.notifyObservers();
	}
	
	
	public ArrayList<ArrayList<Carte>> getRemplissage() {
	
		return this.remplissage;
		
	}
	
	public void setRemplissageTotalCopie(ArrayList<ArrayList<Carte>> liste) {
		
		for(int y = 0; y < liste.size(); y++) {
			
			for(int x = 0; x < liste.get(y).size(); x++) {
				
				if(liste.get(y).get(x) != null) {
					FormeCarte fCopie = liste.get(y).get(x).getForme();
					Couleur couleurCopie = liste.get(y).get(x).getCouleur();
					boolean rempliCopie = liste.get(y).get(x).isRempli();
					Carte carteCopie = new Carte(couleurCopie, fCopie, rempliCopie);
					
					this.setCarte(x, y, carteCopie);
				}
	
			}
		}
		
	}
	
	public abstract boolean checkPose(int x,int y);
	public abstract Direction checkPosExtremiteHex(int x,int y);
	public abstract void deplacerPlateau(int x,int y);
	public abstract void deplacerPlateau(Direction direction);
	
	
	
	public void setRemplissage(int x, int y, Carte carte) {
		
		if(this.remplissage.size()<= y || this.remplissage.get(y).size() <= x) {
			this.vueTexte.poseCarteImpossible();
		}
		else {
			if(this.getCarte(x, y) != null) {
				this.vueTexte.casePrise();
			}
			else {
				this.setCarte(x, y, carte);
				this.vueTexte.poseCarte(carte, x, y);
			}
		}
	}
	
	public abstract void afficherPlateau();
	public abstract void initialiser();
	/*
	 * Methode de clonage d'un plateau
	 * @return : une instance clone d'un plateau 
	 */
	
	public abstract Plateau copiePlateau();
}
