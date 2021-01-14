package Modèle;

public class StrategieFacile extends Strategie{
	
	
	/*
	 * va seulement poser une carte en fonction du nombre de pts max que cela permet de créer 
	 */

	@Override
	public void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException {
		
		int nbPtMax = 0;
		int xMax = 0;
		int yMax = 0;
		boolean tour1boucle = true;
		Carte carte = j.getMain().getCarte(j.getMain().getCartes().size()-1);
		
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
