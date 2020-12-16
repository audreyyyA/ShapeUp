package jeu;

import java.util.ArrayList;

public class PlateauHexagonal extends Plateau{

	public PlateauHexagonal(FormePlateau forme) {
		super(forme);
		this.initialiser();
	}

	@Override
	public boolean checkPose(int x, int y) {
		if(x == -1 || y == -1 || y == this.remplissage.size() || x == this.remplissage.get(y).size()) {
			System.out.println("EXTREMITEEE");
			return checkPosExtremite(x,y);		
		}
				
		if(super.getCarte(x, y) != null) { 
			System.out.println("T CON YA DEJA UNE CARTE ICI !!!");
			return false;
		}
		if(y <= (int) this.remplissage.size()/2) {
			if(super.getCarte(x-1, y) != null || super.getCarte(x+1, y) != null || super.getCarte(x, y-1)!= null || super.getCarte(x-1, y-1) != null || super.getCarte(x, y+1) != null || super.getCarte(x+1, y+1) != null) {
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
	

	public boolean checkPosExtremite(int x, int y) {
		
		if(y==-1) {
			boolean lastRowEmpty = true;
			int nbligne = this.remplissage.size() - 1;
			for(int i=0; i<remplissage.get(nbligne).size(); i++) { //boucle de 0 à la taille de la derniere ligne
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
					System.out.println("T'as le droit à gauche");
					return true; //Deplacer le plateau en diago bas gauche
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
					System.out.println("T'as le droit à droite");
					return true; //Deplacer le plateau en diago bas gauche
				}
			}
			else {
				return false;
			}
			return true;
		}
		
		else if(y == remplissage.size()) {
			for(int i=0; i< this.remplissage.get(0).size(); i++) {
				if(this.getCarte(i,0) != null) {
					return false;
				}
			}
			return true;
		}
		
		else if(x == remplissage.get(y).size()) {
			for(int i=0;i<this.remplissage.size(); i++) {
				if(this.getCarte(0,i) != null) {
					return false;
				}
			}
			return true;
		}
		
		
		else if(x == -1) {
			for(int i=0; i<this.remplissage.size(); i++) {
				//int p = this.remplissage.get(0).size()-1;
				if(this.getCarte(this.remplissage.get(0).size()-1, i) != null) {
					return false;
				}
			}
			return true;
		}
			
		return false;
	}

	@Override
	public void deplacerPlateau(int x, int y) {
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
	
	//FORCEMENT DIFF

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
		
	}

}
