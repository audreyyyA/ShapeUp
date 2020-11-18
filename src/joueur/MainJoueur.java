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
		System.out.println(cartes);
		
	}
	
	public Carte getCarte(int index) {
		
		return cartes.get(index);
	}
}
