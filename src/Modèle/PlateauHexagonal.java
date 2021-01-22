package Modèle;

import java.util.ArrayList;

/**
 * Classe représentant un plateau de type Hexagone et héritant de Plateau
 * @author ALCARAZ, DUTOUR
 */
public class PlateauHexagonal extends Plateau{

	public PlateauHexagonal(FormePlateau forme) {
		super(forme);
		this.initialiser();
	}

	/**
	 * Permet d'effectuer les différents vérification sur le plateau avant de poser une carte
	 * @return vrai ou faux si la carte peut être posée ou pas
	 * @param l'abscisse int x et l'ordonnee int y de pose que le joueur a entré au préalable
	 */
	@Override
	public boolean checkPose(int x, int y) {
		if(x == -1 || y == -1 || y == this.remplissage.size() || x == this.remplissage.get(y).size()) {
			if (checkPosExtremiteHex(x,y) == null) {
				return false;
			}
			
		}
				
		if(super.getCarte(x, y) != null) { 
			return false;
		}
		if(y < (int) this.remplissage.size()/2) {
			if(super.getCarte(x-1, y) != null || super.getCarte(x+1, y) != null || super.getCarte(x, y-1)!= null || super.getCarte(x-1, y-1) != null || super.getCarte(x, y+1) != null || super.getCarte(x+1, y+1) != null) {
				return true;
			}
		}
		else if(y == (int) this.remplissage.size()/2) {
			if(super.getCarte(x-1, y) != null || super.getCarte(x+1, y) != null || super.getCarte(x, y-1)!= null || super.getCarte(x-1, y-1) != null || super.getCarte(x, y+1) != null || super.getCarte(x-1, y+1) != null) {
				return true;
			}
		}
		else {
			if(super.getCarte(x-1, y) != null || super.getCarte(x+1, y) != null || super.getCarte(x, y-1)!= null || super.getCarte(x+1, y-1) != null || super.getCarte(x, y+1) != null || super.getCarte(x-1, y+1) != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Permet d'effectuer les différents vérification sur les extrémitées du plateau avant de poser une carte
	 * @return vrai ou faux si la carte peut être posée ou pas
	 * @param l'abscisse int x et l'ordonnee int y de pose que le joueur a entré au préalable
	 */
	public Direction checkPosExtremiteHex(int x, int y) {
		
		if(y==-1) {
			boolean lastRowEmpty = true;
			int nbligne = this.remplissage.size() - 1;
			for(int i=0; i<remplissage.get(nbligne).size(); i++) { 
				if(this.getCarte(i,nbligne) != null) {
					lastRowEmpty = false;
				}
			}
			
			if(lastRowEmpty) {
				boolean breaker = false;
				for(int j= (int)remplissage.size()/2; j< remplissage.size(); j++) {
					if(this.getCarte(0, j) != null) {
						breaker = true;
						break;
					}
				}
				if(!breaker) {
					return Direction.BASGAUCHE; 
				}
			}
			
			if(lastRowEmpty) {
				boolean breaker = false;
				for(int j= (int)remplissage.size()/2; j< remplissage.size(); j++) {
					if(this.getCarte(remplissage.get(j).size()-1, j) != null) {
						breaker = true;
						break;
					}
				}
				if(!breaker) {
					return Direction.BASDROITE; 
				}
			}
			else {
				return null;
			}
		}
		
		else if(y == remplissage.size()) {
			boolean firstRowEmpty = true;
			for(int i=0; i<remplissage.get(0).size(); i++) { 
				if(this.getCarte(i,0) != null) {
					firstRowEmpty = false;
				}
			}
			
			if(firstRowEmpty) {
				boolean breaker = false;
				for(int j= (int)remplissage.size()/2; j>= 0; j--) {
					if(this.getCarte(0, j) != null) {
						breaker = true;
						break;
					}
				}
				if(!breaker) {
					return Direction.HAUTGAUCHE; 
				}
			}
			
			if(firstRowEmpty) {
				boolean breaker = false;
				for(int j= (int)remplissage.size()/2; j>=0 ; j--) {
					if(this.getCarte(remplissage.get(j).size()-1, j) != null) {
						breaker = true;
						break;
					}
				}
				if(!breaker) {
					return Direction.HAUTDROITE; 
				}
			}
			else {
				return null;
			}
		}
		
		else if(x == remplissage.get(y).size()) {
			for(int i=0;i<this.remplissage.size(); i++) {
				if(this.getCarte(0,i) != null) {
					return null;
				}
			}
			return Direction.GAUCHE;
		}
		
		
		else if(x == -1) {
			for(int i=0; i<this.remplissage.size(); i++) {
				if(this.getCarte(this.remplissage.get(0).size()-1, i) != null) {
					return null;
				}
			}
			return Direction.DROITE;
		}
			
		return null;
	}

	/**
	 * Déplace les cartes du plateau si le joueur joue en dehors du plateau (et si cela est possible)
	 * @param l'abscisse int x et l'ordonnee int y de pose que le joueur a entré au préalable
	 */
	@Override
	public void deplacerPlateau(Direction direction) {
		
		if(direction == Direction.DROITE) { 
			for(int i=0; i<this.remplissage.size(); i++) {
				for(int j=this.remplissage.get(i).size()-2; j>=0 ; j--) {
					this.setCarte(j+1, i,this.getCarte(j, i));
					this.setCarte(j, i, null);
				}
			}
		}
		
		else if(direction == Direction.HAUTDROITE) {
			for(int i=1; i<this.remplissage.size();i++) {
				if(i >(int) this.remplissage.size()/2) {
					for(int j=0; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j+1, i-1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
				else {
					for(int j=0; j<this.remplissage.get(i).size()-1; j++) {
						this.setCarte(j, i-1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
		}
		
		else if(direction == Direction.HAUTGAUCHE) {
			for(int i=1; i<this.remplissage.size();i++) {
				if(i >(int) this.remplissage.size()/2) {
					for(int j=0; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j, i+1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
				else {
					for(int j=1; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j-1, i-1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
		}
		
		else if(direction == Direction.BASDROITE) {
			for(int i=this.remplissage.size()-2; i>=0; i--) {
				if(i >=(int) this.remplissage.size()/2) {
					for(int j=0; j<this.remplissage.get(i).size()-1; j++) {
						this.setCarte(j, i+1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
				else {
					for(int j=0; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j+1, i+1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
		}
		
		else if(direction == Direction.BASGAUCHE) {
			for(int i=this.remplissage.size()-2; i>=0; i--) {
				if(i >=(int) this.remplissage.size()/2) {
					for(int j=1; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j-1, i+1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
				else {
					for(int j=0; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j, i+1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
		}
		
		else if(direction == Direction.GAUCHE) {
			for(int i=0; i<this.remplissage.size(); i++) {
				for(int j=1; j<this.remplissage.get(i).size(); j++) {
					this.setCarte(j-1, i, this.getCarte(j, i));
					this.setCarte(j, i, null);
				}
			}
		}
	}
	

	/**
	 * Affiche un plateau de type hexagone
	 */
	@Override
	public void afficherPlateau() {
		
		int milieu = this.remplissage.size()/2;
		for(int i=0; i < this.remplissage.size(); i++) {
			String espace = "  ";
			String s ="";
			if(i == milieu) {
				s = (i) +" "+ espace.repeat(Math.abs(milieu-i))+"|";
			}
			else {
				s = (i) +"  "+ espace.repeat(Math.abs(milieu-i))+"|";
			}
			for(int j=0; j< this.remplissage.get(i).size(); j++) {
				if(this.remplissage.get(i).get(j) == null) {
					s+= "    |";
				}
				else {
					s+= " "+this.remplissage.get(i).get(j).toString() + " |";
				}
			}
			System.out.println(s);
		}
		
	}

	/**
	 * Initialise le remplissage d'un plateau 
	 */
	@Override
	public void initialiser() {

		this.remplissage = new ArrayList<ArrayList<Carte>>();
		int i = 3;
		for(int k=0; k<5; k++) {
			ArrayList<Carte> l=new ArrayList<>();
			for(int j = 0; j<i; j++) {
				l.add(null);
			}
			
			this.remplissage.add(k,l);
	
			if(k>1) {
				i-=1;
			}
			else {
				i+=1;
			}
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Effectue une copie du plateau
	 * @return Renvoie la copie du plateau
	 */
	public  Plateau copiePlateau() {
		
		FormePlateau copieForme = FormePlateau.HEXAGONE;
		Plateau pCopie = new PlateauHexagonal(copieForme);
		
		pCopie.setRemplissageTotalCopie(this.getRemplissage());

		return pCopie;
	}

	@Override
	public void deplacerPlateau(int x, int y) {
		// TODO Auto-generated method stub
	}

}
