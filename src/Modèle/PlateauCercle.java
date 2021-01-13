package Modèle;

import java.util.ArrayList;

public class PlateauCercle extends Plateau{

	public PlateauCercle(FormePlateau forme) {
		super(forme);
		this.initialiser();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public boolean checkPose(int x, int y) {
		// TODO Auto-generated method stub
		if(y == this.remplissage.size()) { 
			if(this.getCarte(x, y-1) != null && this.checkPosExtremite(x, y)) {
				return true;
			}
			
		}
		if(this.getCarte(x-1, y) != null || this.getCarte(x+1, y) != null || this.getCarte(x, y+1) != null || this.getCarte(x, y-1) != null) {
			return true;
		}
		if(x == this.remplissage.get(1).size()-1) {
			if(this.getCarte(0, y) != null) {
				return true;
			}
		}
		if(y ==1) {
			if(this.getCarte(0, 0) != null) {
				return true;
			}
		}
		if(y == 0 && x == 0) {
			 for(int i=0; i<this.remplissage.get(1).size(); i++) {
				 if(this.getCarte(i, 1) != null) {
					 return true;
				 }
			 }
		}
		if(x ==0) {
			if(this.getCarte(this.remplissage.get(1).size()-1, y) != null) {
				return true;
			}
		}
		
		return false;

	}
 
	
	private boolean checkPosExtremite(int x, int y) {
		int milieu = this.remplissage.get(this.remplissage.size()-1).size()/2;
		int xOpposite = 0;
		
		if(x < milieu) {
			 xOpposite = x + milieu;
		}
		else {
			xOpposite = x - milieu;
		}
		
		if(this.getCarte(xOpposite, y-1) == null) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void deplacerPlateau(int x, int y) {
		// TODO Auto-generated method stub
		
		int milieu = this.remplissage.get(this.remplissage.size()-1).size()/2;
		int xOpposite = 0;
		int xTemp = 0;
		int yTemp = this.remplissage.size()-1;
		
		if(x < milieu) {
			 xOpposite = x + milieu;
		}
		else {
			xOpposite = x - milieu;
		}
		
		for(int i=0; i<(this.remplissage.size()*2)-1; i++){
			if(i<this.remplissage.size()-2) {
				yTemp --;
				xTemp = xOpposite;
			}
			else if(i == this.remplissage.size()-2){
				yTemp =0;
				xTemp = 0;
			}
			else {
				xTemp =x;
				yTemp ++;
			}
			
			
			if(yTemp ==1 && i>=this.remplissage.size()-1) {
				this.setCarte(xTemp, yTemp, this.getCarte(0, 0));
			}
			else if(yTemp ==0) {
				this.setCarte(xTemp, yTemp, this.getCarte(x, yTemp+1));
			}
			else {
				if(i<this.remplissage.size()/2) {
					this.setCarte(xTemp, yTemp+1,this.getCarte(xTemp, yTemp) );
				}
				else {
					this.setCarte(xTemp, yTemp-1, this.getCarte(xTemp, yTemp));
				}
			}
		}
		this.setCarte(x, this.remplissage.size()-1, null);
	}

	@Override
	public void afficherPlateau() {
		// TODO Auto-generated method stub
		System.out.println(this.remplissage);
	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		this.remplissage = new ArrayList<ArrayList<Carte>>();
		int i = 1;
		for(int k=0; k<4; k++) {
			ArrayList<Carte> l=new ArrayList<>();
			for(int j = 0; j<i; j++) {
				l.add(null);
			}
			this.remplissage.add(k,l);
			i=6;
		}
		this.setChanged();
		this.notifyObservers();
	}

	public  Plateau copiePlateau() {
		
		FormePlateau copieForme = FormePlateau.CERCLE;
		Plateau pCopie = new PlateauCercle(copieForme);
		
		pCopie.setRemplissageTotalCopie(this.getRemplissage());

		return pCopie;
	}

	@Override
	public Direction checkPosExtremiteHex(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deplacerPlateau(Direction direction) {
		// TODO Auto-generated method stub
		
	}


}
