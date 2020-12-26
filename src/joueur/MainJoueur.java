package joueur;

import java.util.ArrayList;

import jeu.Carte;

public class MainJoueur {
	
	private ArrayList<Carte> cartes;
	
	public MainJoueur() {
		this.cartes = new ArrayList<Carte>();
	}

	public void ajouterCarte(Carte newCard) {
		cartes.add(newCard);
	}
	
	public void retirerCarte(int index) {
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
