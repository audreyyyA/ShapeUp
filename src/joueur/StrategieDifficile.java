package joueur;

import jeu.Carte;
import jeu.FormePlateau;
import jeu.IVisitor;
import jeu.Plateau;

public class StrategieDifficile extends Strategie{
	
	/*
	 * de mani�re random va choisir de d�placer une carte
	 * va poser une carte en fonction du nombre de pts max que cela permet de cr�er 
	 */

	@Override
	public void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException {
		
		/*
		 * faire le random pour d�cider s'il d�placer
		 * random pour la carte � d�placer
		 * random pour l'endroit o� la poser (ou l� o� y'a le + de pt...?)
		 */
		
		
		
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

}
