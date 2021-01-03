package jeu;

import java.util.ArrayList;
import java.util.Scanner;

import joueur.Joueur;
import joueur.JoueurReel;
import joueur.JoueurVirtuel;
import joueur.Strategie;
import joueur.StrategieDifficile;
import joueur.StrategieFacile;

public class Variante3 extends Regle{

	@Override
	public void demarrerManche(ArrayList<Joueur> tabJoueur, Pioche pioche) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDone(Manche manche) {
		// TODO Auto-generated method stub
		return false;
	}
	
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
			
		
			while(((JoueurReel) joueur).askDeplacer() && !deplacer) {
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
			while(((JoueurReel) joueur).askDeplacer() && !deplacer) {
				deplacer = joueur.deplacerCarte(plateau);
				if(deplacer) {
					break;
				}
			}
			
			plateau.afficherPlateau();
			
		}
		
	}
	
	@Override
	public ArrayList<Joueur> initJoueur(){
		
		ArrayList<Joueur> t = new ArrayList<>();
		
		System.out.println("Dans une partie spéciale, vous pouvez jouer à maximum 5 joueurs.");
		System.out.println("Choisissez le nombre de joueurs : ");
		int nbJoueur = 0;
		boolean tmp = true;
		while(tmp) {
			try {
				Scanner sc =  new Scanner(System.in);
				nbJoueur = sc.nextInt();	
				if(nbJoueur >0 && nbJoueur<6) {
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
					
					System.out.println("Quelle stratégie ? : 1 : FACILE ou 2 : AVANCEE");
					
					//verifier si c pas une mauvaise entrée..
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
							Joueur j = new JoueurVirtuel(n, true, facile);
							t.add(j);
						}else {
							Strategie difficile = new StrategieDifficile();
							Joueur j = new JoueurVirtuel(n, true, difficile);
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
			Joueur j = new JoueurReel(n);
			t.add(j);
		}
		
		return t;
	}

}
