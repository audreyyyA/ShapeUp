package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import joueur.Joueur;
import joueur.JoueurReel;

public class Variante1 extends Regle{
	
	@Override
	public void jouer(Joueur joueur, int tour, Pioche pioche, Plateau plateau) {
		/*
		 * On va appeler la methode algo de strategie si le joueur est virtuel
		 * mettre condition ou Switch
		 */
		Carte c = joueur.piocherCarte(pioche);
		joueur.getMain().ajouterCarte(c); 
		joueur.getMain().afficherMain();
		plateau.afficherPlateau();
		Scanner sc = new Scanner(System.in);
		boolean deplacer = false;
		int nbDep = 0;
		
	
		while(joueur.askDeplacer() && !deplacer) {
			deplacer = joueur.deplacerCarte(plateau);
			if(deplacer) {
				nbDep = 1;
				break;
			}
		}
		
		//mettre condition tour 1
		joueur.getMain().afficherMain();
		joueur.poserCarte(plateau,tour); 
		plateau.afficherPlateau();
		
		//déplace la carte seulement s'il l'a pas fait avant
		while(joueur.askDeplacer() && !deplacer) {
			deplacer = joueur.deplacerCarte(plateau);
			if(deplacer) {
				break;
			}
		}
		
		plateau.afficherPlateau();
		
	}

	@Override
	public ArrayList<Joueur> initJoueur() {
		
		ArrayList<Joueur> t = new ArrayList<>();
		
		System.out.println("Dans une partie classique, vous pouvez jouer à maximum 3 joueurs.");
		System.out.println("Choisissez le nombre de joueurs : ");
		int nb = 0;
		boolean tmp = true;
		while(tmp) {
			Scanner sc =  new Scanner(System.in);
			nb = sc.nextInt();
			
			if(nb <= 0 || nb > 3) {
				
				System.out.println("Le nombre de joueurs est incorrect ! Recommence ");
			}
			else {
				tmp = false;
			}
		}
		
		for(int i = 1; i<= nb; i++) {
			
			System.out.println("Entrez le nom du joueur " + i);
			String n = "";
			boolean tmp2 = true;
			while(tmp2) {
				Scanner sc2 = new Scanner(System.in);
				n = sc2.next();
				if(n.isEmpty()) {
					System.out.println("il te faut un nom pour ce joueur !");
				}
				else {
					tmp2 = false;
				}
			}
			Joueur j = new JoueurReel(n);
			t.add(j);
		}
		
		return t;
			
		
	}

	@Override
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche) {
		

		for(Joueur j : tabJoueur) { // = foreach pour set les cartes victoires
			j.setCarteVictoire(j.piocherCarte(pioche));
			System.out.println("Carte Victoire de "+j.getNom() +" est : "+j.getCarteVictoire());
		}
		
		
	}

}
