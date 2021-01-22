package Modèle;

import java.util.ArrayList;

/**
 * Classe représentant un plateau de type Rectangle et héritant de Plateau
 * @author ALCARAZ, DUTOUR
 */
public class PlateauRectangle extends Plateau implements Cloneable{

	public PlateauRectangle(FormePlateau forme) {
		super(forme);
		this.initialiser();

	}

	/**
	 * Permet d'effectuer les différents vérification sur le plateau avant de poser une carte
	 * @return vrai ou faux si la carte peut être posée ou pas
	 * @param l'abscisse et l'ordonnee de pose que le joueur a entré au préalable
	 */
	public boolean checkPose(int x,int y) {
		if(x == -1 || y == -1 || y == this.remplissage.size() || x == this.remplissage.get(0).size()) {
			return checkPosExtremite(x,y);		
		}

		if(super.getCarte(x, y) != null) { 
			return false;
		}
		if(super.getCarte(x-1, y) != null || super.getCarte(x, y+1) != null || super.getCarte(x+1, y) != null || super.getCarte(x, y-1) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Permet d'effectuer les différents vérification sur les extrémitées du plateau avant de poser une carte
	 * @return vrai ou faux si la carte peut être posée ou pas
	 * @param l'abscisse et l'ordonnee de pose que le joueur a entré au préalable
	 */
	private boolean checkPosExtremite(int x, int y) {

		if(y==-1) {
			if(this.getCarte(x, y+1) != null) {
				int nbligne = this.remplissage.size() - 1;
				for(int i=0; i<remplissage.get(nbligne).size(); i++) { 
					if(this.getCarte(i,nbligne) != null) {
						return false;
					}
				}
				return true;
			}
			else {
				return false;
			}
		}

		else if(y == remplissage.size()) {
			if(this.getCarte(x, y-1) != null) {
				for(int i=0; i< this.remplissage.get(0).size(); i++) {
					if(this.getCarte(i,0) != null) {
						return false;
					}
				}
				return true;
			}
			else {
				return false;
			}
		}

		else if(x == remplissage.get(y).size()) {
			if(this.getCarte(x-1, y) != null) {
				for(int i=0;i<this.remplissage.size(); i++) {
					if(this.getCarte(0,i) != null) {
						return false;
					}
				}
				return true;
			}
			else {
				return false;
			}
		}


		else if(x == -1) {
			if(this.getCarte(x+1, y) != null) {
				for(int i=0; i<this.remplissage.size(); i++) {
					if(this.getCarte(this.remplissage.get(0).size()-1, i) != null) {
						return false;
					}
				}
				return true;
			}
			else {
				return false;
			}
		}

		return false;
	}

	/**
	 * Déplace les cartes du plateau si le joueur joue en dehors du plateau (et si cela est possible)
	 * @param l'abscisse et l'ordonnee de pose que le joueur a entré au préalable
	 */
	public void deplacerPlateau(int x,int y) {

		if(x == -1) { 
			for(int i=0; i<this.remplissage.size(); i++) {
				for(int j=this.remplissage.get(i).size()-2; j>=0 ; j--) {
					this.setCarte(j+1, i,this.getCarte(j, i));
					this.setCarte(j, i, null);
				}
			}
		}


		else if(y == -1) {
			int nbligne = this.remplissage.size() - 1;
			for(int i=nbligne-1; i>=0; i--) {
				for(int j=0; j<this.remplissage.get(i).size(); j++) {
					this.setCarte(j, i+1, this.getCarte(j, i));
					this.setCarte(j, i, null);
				}
			}
		}


		else if(y == this.remplissage.size()) {
			int nbligne = this.remplissage.size();
			for(int i=1; i<nbligne; i++) {
				for(int j=0; j<this.remplissage.get(i).size(); j++) {
					this.setCarte(j, i-1, this.getCarte(j, i));
					this.setCarte(j, i, null);
				}
			}
		}


		else if(x == this.remplissage.get(y).size()) {
			int nbligne = this.remplissage.size();
			for(int i=0; i<nbligne; i++) {
				for(int j=1; j<this.remplissage.get(i).size(); j++) {
					this.setCarte(j-1, i, this.getCarte(j, i));
					this.setCarte(j, i, null);
				}
			}
		}

	}
	
	/**
	 * Initialise le remplissage d'un plateau 
	 */
	public void initialiser() {

		this.remplissage = new ArrayList<ArrayList<Carte>>();
		if(this.forme == FormePlateau.RECTANGLE){

			for(int k=0; k<3; k++) {
				ArrayList<Carte> l=new ArrayList<>();

				for(int j=0; j<5; j++) {
					l.add(null);
				}
				this.remplissage.add(k,l);
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Affiche un plateau de type rectangle
	 */
	public void afficherPlateau() {

		if(this.forme == FormePlateau.RECTANGLE){
			for(int i=0; i < this.remplissage.size(); i++) {
				String abcisse = "";
				String s = (i) + " |";
				for(int j=0; j< this.remplissage.get(i).size(); j++) {
					if(i == 0 ) {
						abcisse += "    "+(j);
					}
					if(this.remplissage.get(i).get(j) == null) {
						s+= "    |";
					}
					else {
						s+= this.remplissage.get(i).get(j).toString() + " |";
					}
				}
				if(i==0) {
					System.out.println(abcisse);	
				}
				System.out.println(s);
			}
		}
	}

	/**
	 * Effectue une copie du plateau
	 * @return Renvoie la copie du plateau
	 */
	public  Plateau copiePlateau() {

		FormePlateau copieForme = FormePlateau.RECTANGLE;
		Plateau pCopie = new PlateauRectangle(copieForme);

		pCopie.setRemplissageTotalCopie(this.getRemplissage());

		return pCopie;
	}

	@Override
	public void deplacerPlateau(Direction direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public Direction checkPosExtremiteHex(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
