package joueur;

import java.util.ArrayList;

import jeu.Carte;
import jeu.FormePlateau;
import jeu.IVisitor;
import jeu.Plateau;

public class StrategieDifficile extends Strategie{
	
	/*
	 * de manière random va choisir de déplacer une carte
	 * va poser une carte en fonction du nombre de pts max que cela permet de créer 
	 */

	@Override
	public void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException {

		int random = (int)(Math.random()*5);
		if(random >2 && tour > 2) {
			AlgorithmeDeplacement(p, j, tour, visitor);
		}

		int nbPtMax = 0;
		int xMax = 0;
		int yMax = 0;
		boolean tour1boucle = true;
		Carte carte = j.getMain().getCarte(0);
		
		System.out.println("Le joueur virtuel " + j.getNom() + " va poser une carte ...");
		
		for(int y = 0; y<p.getRemplissage().size(); y++) {
			for(int x = 0; x<p.getRemplissage().get(y).size(); x++) {
				
				boolean incorrectInput = false;
				if(p.checkPose(x, y)) {
					
					if(p.getForme() == FormePlateau.HEXAGONE) {
						p.deplacerPlateau(p.checkPosExtremiteHex(x,y));
					}
					else if((x == -1) || (y == -1) || (x == p.getRemplissage().get(1).size()) || (y == p.getRemplissage().size())){
						p.deplacerPlateau(x,y);
					}
					incorrectInput = false;
				}
				else {
					
					incorrectInput = true;
				}
					
				if(!incorrectInput) {
					
					Plateau plateauTmp = p.copiePlateau();
					plateauTmp.afficherPlateau();
					
					int ptTest = visitor.calculPointJoueur(j.getCarteVictoire(), plateauTmp);

					if(ptTest > nbPtMax) {
						nbPtMax = ptTest;
						xMax = x;
						yMax = y;
					}
					
					if(tour1boucle) {
						tour1boucle = false;
						nbPtMax = ptTest;
						xMax = x;
						yMax = y;
					}
				}
			}
		}
		
		System.out.println("il pose la carte : " + carte + " en : " + xMax + " , " + yMax);
		p.setCarte(xMax, yMax, carte);
		j.getMain().retirerCarte(0);
		
	}
	
	public void AlgorithmeDeplacement(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException{
		
		ArrayList<ArrayList<Integer>> listeIndex = new ArrayList<>();

		for(int yBis = 0; yBis < p.getRemplissage().size(); yBis++) {
			for(int xBis = 0; xBis < p.getRemplissage().get(yBis).size(); xBis++){
				
				if(p.getRemplissage().get(yBis).get(xBis) != null) {
					ArrayList<Integer> index = new ArrayList<>();
					index.add(yBis);
					index.add(xBis);
					listeIndex.add(index);
				}
			}
		}
		
		int nbCartePlacee = listeIndex.size();
		
		//adapter pour connaitre le nb de carte à placer sur chaque type de plateau
		//MODIF A FAIREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
		
		
		
		
		if(nbCartePlacee != 0 || nbCartePlacee != 14) {
			
			int randomCarteIndex = (int)(Math.random()*(listeIndex.size()-1));
			Carte carte = p.getCarte(listeIndex.get(randomCarteIndex).get(1), listeIndex.get(randomCarteIndex).get(0));
			p.setCarte(listeIndex.get(randomCarteIndex).get(1), listeIndex.get(randomCarteIndex).get(0), null);
			
			int nbPtMax = 0;
			int xMax = 0;
			int yMax = 0;
			boolean tour1boucle = true;
			
			System.out.println("Le joueur virtuel " + j.getNom() + " va deplacer la carte : " + carte);
			
			for(int y = 0; y<p.getRemplissage().size(); y++) {
				for(int x = 0; x<p.getRemplissage().get(y).size(); x++) {
					
					boolean incorrectInput = false;
					if(p.checkPose(x, y)) {
						
						if(p.getForme() == FormePlateau.HEXAGONE) {
							p.deplacerPlateau(p.checkPosExtremiteHex(x,y));
						}
						else if((x == -1) || (y == -1) || (x == p.getRemplissage().get(1).size()) || (y == p.getRemplissage().size())){
							p.deplacerPlateau(x,y);
						}
						incorrectInput = false;
					}
					else {
						
						incorrectInput = true;
					}
						
					if(!incorrectInput) {
						
						Plateau plateauTmp = p.copiePlateau();
						plateauTmp.afficherPlateau();
						
						int ptTest = visitor.calculPointJoueur(j.getCarteVictoire(), plateauTmp);

						if(ptTest > nbPtMax) {
							if(x != listeIndex.get(randomCarteIndex).get(1) &&  y != listeIndex.get(randomCarteIndex).get(0)) {
								nbPtMax = ptTest;
								xMax = x;
								yMax = y;
							}
						}
						
						if(tour1boucle) {
							tour1boucle = false;
							nbPtMax = ptTest;
							xMax = x;
							yMax = y;
						}
					}
				}
			}
			
			System.out.println("il deplace la carte : " + carte + " en : " + xMax + " , " + yMax);
			p.setCarte(xMax, yMax, carte);
		}
	}

}
