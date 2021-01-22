package Modèle;

import java.util.ArrayList;

/**
 * @author ALCARAZ, DUTOUR
 * Classe permettant d'elaborer la strategie d'une IA (Joueur virtuel) en mode difficile
 * 
 */
public class StrategieDifficile extends Strategie implements Runnable{
	
	private Thread thread;
	
	public StrategieDifficile() {
		Thread t = new Thread(this);
		this.thread = t;
		t.start();
	}
	
	/**
	 * Determine la maniere de jouer d'une IA en mode difficile
	 * @param le plateau, le joueur IA, le numero du tour, le visitor pour calculer le nombre de points
	 * @throws erreur possible au moment du sleep du thread
	 */

	@Override
	public void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException {

		int random = (int)(Math.random()*10);
		
		if(random >3 && tour > 2) {
			AlgorithmeDeplacement(p, j, tour, visitor);
		}

		int nbPtMax = 0;
		int xMax = 0;
		int yMax = 0;
		boolean tour1boucle = true;
		Carte carte = j.getMain().getCarte(0);
		
		j.vueTexte.avantPoseIA(j);
		
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
		
		try {
			this.thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("interrupt");
			return;
		}
		j.vueTexte.poseIA(carte, xMax, yMax);
		
		p.setCarte(xMax, yMax, carte);
		j.getMain().retirerCarte(0);
		
	}
	
	/**
	 * Permet à une IA de se déplacer
	 * @param le plateau, le joueur IA, le numero du tour, le visitor pour calculer le nombre de points
	 */

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
		
		if(nbCartePlacee != 0) {
			
			int randomCarteIndex = (int)(Math.random()*(listeIndex.size()-1));
			Carte carte = p.getCarte(listeIndex.get(randomCarteIndex).get(1), listeIndex.get(randomCarteIndex).get(0));
			p.setCarte(listeIndex.get(randomCarteIndex).get(1), listeIndex.get(randomCarteIndex).get(0), null);
			
			int nbPtMax = 0;
			int xMax = 0;
			int yMax = 0;
			boolean tour1boucle = true;
			
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
			
			
			j.vueTexte.deplacementIA(carte, xMax, yMax);
			p.setCarte(xMax, yMax, carte);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
