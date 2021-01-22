package Modèle;

/**
 * @author ALCARAZ, DUTOUR
 * Classe permettant d'elaborer la strategie d'une IA (Joueur virtuel) en mode facile
 * 
 */
public class StrategieFacile extends Strategie implements Runnable{
	
	private Thread thread;
	
	public StrategieFacile() {
		Thread t = new Thread(this);
		this.thread = t;
		t.start();
	}

	/**
	 * Determine la maniere de jouer d'une IA en mode facile
	 * @param le plateau, le joueur IA, le numero du tour, le visitor pour calculer le nombre de points
	 * @throws erreur possible au moment du sleep du thread
	 */
	@Override
	public void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException {
		
		int nbPtMax = 0;
		int xMax = 0;
		int yMax = 0;
		boolean tour1boucle = true;
		Carte carte = j.getMain().getCarte(j.getMain().getCartes().size()-1);
		
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
