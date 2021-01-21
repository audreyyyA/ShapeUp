package Modèle;

import java.util.ArrayList;
import java.util.Scanner;

public class Variante3 extends Regle{

	@Override
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche) {
		// TODO Auto-generated method stub
		
		System.out.println("Dans le mode ultime, tu pioches deux cartes par tour et choisis laquelle tu veux poser. Tu défausses l'autre.");
		System.out.println("1 fois dans la partie, tu as le droit de changer de carte victoire.");
		for(Joueur j : tabJoueur) {
			for(int i=0; i<3; i++) {
				j.getMain().ajouterCarte(j.piocherCarte(pioche));
			}
		}
	}

	@Override
	public boolean isDone(Manche manche) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void jouer(Joueur joueur, int tour, Pioche pioche, Plateau plateau, IVisitor visitor) {
		
		Carte carte1 = joueur.piocherCarte(pioche);
		Carte carte2 = joueur.piocherCarte(pioche);
		joueur.getMain().ajouterCarte(carte1);
		joueur.getMain().ajouterCarte(carte2);
		
		joueur.getMain().afficherMain();
		plateau.afficherPlateau();
		
		if(joueur.getIsVirtuel()) {
			
			try {
				((JoueurVirtuel) joueur).getStrategie().Algorithme(plateau, joueur, tour, visitor);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			
			Scanner sc = new Scanner(System.in);
			boolean deplacer = false;
			int nbDep = 0;
		
			while(((JoueurReel) joueur).askDeplacer() && !deplacer) {
				deplacer = joueur.deplacerCarte(plateau);
				if(deplacer) {
					nbDep = 1;
					break;
				}
		}

		if(!((JoueurReel) joueur).isCVchang()) {
			boolean repp = true;
			while(repp) {
				
				System.out.println("Voulez-vous changer de carte victoire (O/N) ?");
				Scanner scVictoire = new Scanner(System.in);
				String rep = scVictoire.nextLine();
				
				if(rep.equals("O") || rep.equals("o")) {
					((JoueurReel) joueur).setCVchang(true);
					changerCarteVictoire(joueur, pioche);
					repp = false;
				}
				else if(rep.equals("N") || rep.equals("n")) {
					repp = false;
				}
				else {
					System.out.println("Rentrez O ou N");
					repp = true;
				}
			}	
		}

		joueur.getMain().afficherMain();
		joueur.poserCarte(plateau,tour);
		
		Carte c = joueur.getMain().getCarte(0);
		pioche.getListeCarte().add(c);
		joueur.getMain().retirerCarte(0);
		plateau.afficherPlateau();
		
		while(((JoueurReel) joueur).askDeplacer() && !deplacer) {
			deplacer = joueur.deplacerCarte(plateau);
			if(deplacer) {
				break;
			}
		}
		
			plateau.afficherPlateau();
			
		}	
	}
	
	public void changerCarteVictoire(Joueur j, Pioche pioche) {
		
		System.out.println("Quelle carte veux-tu pour remplacer ta carte victoire ?");
		int index = 0;
		Scanner sc = new Scanner(System.in);
		
		try {
			index = sc.nextInt();
		}
		catch(Exception e) {}
		
		while (index <0 || index > j.getMain().getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosis en un entre 0 et " + (j.getMain().getCartes().size()-1));// verif si string
			changerCarteVictoire(j, pioche);
		}
		
		Carte c = j.getCarteVictoire();
		Carte cv = j.getMain().getCarte(index);
		pioche.getListeCarte().add(c);
		j.setCarteVictoire(cv);
		
		System.out.println("Ta nouvelle carte victoire est : " + j.getCarteVictoire());
		
	}
	
}
