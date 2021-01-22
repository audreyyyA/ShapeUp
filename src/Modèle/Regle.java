package Mod�le;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ALCARAZ, DUTOUR
 * Super classe des variantes de jeu et d�terminant la mani�re dont se d�roule une partie sans variante
 * 
 */
public abstract class Regle {
	
	public abstract void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche);
	public abstract boolean isDone(Manche manche);
	
	/**
	 * Logique de jeu d'une partie avec une r�gle classique
	 * @param le joueur j, le numero du tour, la pioche, le palteau, le visitor pour le nombre de points
	 * @throws erreur possible selon les actions effectu�es par le joueur (IA ou r�el)
	 */
	public void jouer(Joueur joueur, int tour, Pioche pioche, Plateau plateau, IVisitor visitor) {
		
		Carte c = joueur.piocherCarte(pioche);
		joueur.getMain().ajouterCarte(c); 
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
			
			if(tour != 1) {
				while(((JoueurReel) joueur).askDeplacer() && !deplacer) {
					deplacer = joueur.deplacerCarte(plateau);
					if(deplacer) {
						nbDep = 1;
						break;
					}
				}
			}
			
			joueur.poserCarte(plateau,tour); 
			plateau.afficherPlateau();
			
			if(nbDep == 0 && tour != 1) {
				while(((JoueurReel) joueur).askDeplacer() && !deplacer) {
					deplacer = joueur.deplacerCarte(plateau);
					if(deplacer) {
						break;
					}
				}
			}
			
		}
		
	}
	
	/**
	 * Initialise la liste des joueurs 
	 * @return retourne une liste de type joueur
	 * @throws erreur possible sur le choix du nombre de joueurs (IA ou r�els) ainsi que sur leur nom puis leur cr�ation
	 */
	public ArrayList<Joueur> initJoueur(){
		
		ArrayList<Joueur> t = new ArrayList<>();
		
		System.out.println("Dans une partie classique, vous pouvez jouer � maximum 3 joueurs.");
		System.out.println("Choisissez le nombre de joueurs : ");
		int nbJoueur = 0;
		boolean tmp = true;
		while(tmp) {
			try {
				Scanner sc =  new Scanner(System.in);
				nbJoueur = sc.nextInt();	
				if(nbJoueur >0 && nbJoueur<4) {
					tmp = false;
				}
				else {
					System.out.println("Veuillez choisir un nombre de joueur entre 1 et 3");
					System.out.println("Choisissez le nombre de joueurs : ");
				}
			}
			catch(Exception e) {
				System.out.println("Veuillez rentrer un chiffre");
				System.out.println("Choisissez le nombre de joueurs : ");
			}
		}
		
		if(nbJoueur != 1) {
			
			boolean inputNul = true;
			while(inputNul) {
				
				System.out.println("Combien de joueurs virtuels voulez-vous ? : ");
				Scanner scvirtuel = new Scanner(System.in);
				int nbVirtuel = scvirtuel.nextInt();
				
				if(nbVirtuel >= nbJoueur) {
					
					System.out.println("Veuillez choisir un nombre de joueur virtuel correct");
					inputNul = true;
				}else if(nbVirtuel == 0) {
					System.out.println("Vous voulez jouer sans joueur virtuel");
					inputNul = false;
				}
				else{
					inputNul = false;
					nbJoueur -= nbVirtuel;
					
					System.out.println("Quelle strat�gie ? : 1 : FACILE ou 2 : AVANCEE");
					Scanner scStrat = new Scanner(System.in);
					int typeStrat = scStrat.nextInt();

					for(int i = 1; i<= nbVirtuel; i++) {
						System.out.println("Entrez le nom du joueur viturel " + i);
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
						if(typeStrat == 1) {
							Strategie facile = new StrategieFacile();
							Joueur j = new JoueurVirtuel(n, true, facile,0);
							t.add(j);
						}else {
							Strategie difficile = new StrategieDifficile();
							Joueur j = new JoueurVirtuel(n, true, difficile,0);
							t.add(j);
						}

					}
				}
			}
		}
	
		
		for(int i = 1; i<= nbJoueur; i++) {
			
			System.out.println("Entrez le nom du joueur reel " + i);
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
			Joueur j = new JoueurReel(n,0);
			t.add(j);
		}
		
		return t;
	}
}
