package joueur;

import java.util.ArrayList;
import java.util.Observable;

import jeu.Carte;

public class MainJoueur extends Observable{
	
	private ArrayList<Carte> cartes;
	private int num;
	
	public MainJoueur(int num) {
		this.cartes = new ArrayList<Carte>();
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void ajouterCarte(Carte newCard) {
		cartes.add(newCard);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void retirerCarte(int index) {
		this.setChanged();
		this.notifyObservers();
		cartes.remove(index);
	}
	
	public void afficherMain() {
		System.out.print("        ");
		for(int i=0; i<this.cartes.size(); i++) {
			System.out.print("    "+i);
		}
		System.out.println("\nTa main : " + this.cartes+"\n");
	}
	
	public Carte getCarte(int index) {
		
		return cartes.get(index);
	}
	
	public ArrayList<Carte> getCartes(){
		return cartes;
	}
}
