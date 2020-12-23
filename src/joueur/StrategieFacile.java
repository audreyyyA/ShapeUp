package joueur;

import jeu.Carte;
import jeu.FormePlateau;
import jeu.Plateau;

public class StrategieFacile extends Strategie{
	
	
	
	
	/*
	 * va seulement poser une carte en fonction du nombre de pts max que cela permet de créer 
	 */

	@Override
	public void Algorithme(Plateau p, Joueur j, int tour) {
		
		int nbPtMax = 0;
		
		for(int y = 0; y<p.getRemplissage().size(); y++) {
			for(int x = 0; x<p.getRemplissage().get(y).size(); x++) {
				
				boolean incorrectInput = false;
				if(p.checkPose(x, y)) {
					
					//changer ... tout avec le mm nom de méthode plus tard
					if(p.getForme() == FormePlateau.HEXAGONE) {
						p.deplacerPlateau(p.checkPosExtremiteHex(x,y));
					}
					else if((x == -1) || (y == -1) || (x == p.getRemplissage().get(1).size()) || (y == p.getRemplissage().size())){
						p.deplacerPlateau(x,y);
					}
					incorrectInput = false;
				}
				else {
					//la carte ne peut pas être posé ici
					incorrectInput = true;
				}
					
				if(!incorrectInput) {
					
					//faire condition pour comparer les pts 
					//Attribuer le ptMax selon le résultat
					continue;
				}
			}
		}
		
	}
	
	
}
