package jeu;

import java.util.ArrayList;

public class PlateauRectangle extends Plateau{

	public PlateauRectangle(FormePlateau forme) {
		super(forme);
		// TODO Auto-generated constructor stub
	}
	
	public Carte getCarte(int x, int y) {
		if(y >= this.remplissage.size() || y<0 || x<0 || x >= this.remplissage.get(y).size()) {
			return null;
		}
		return this.remplissage.get(y).get(x);
	}
	
	
	
	public void initialiser() {
		super.remplissage = new ArrayList<ArrayList<Carte>>();	
		for(int k=0; k<3; k++) {
			ArrayList<Carte> l=new ArrayList<>();
			
			for(int j=0; j<5; j++) {
			    l.add(null);
			}
			super.remplissage.add(k,l);
		}
	}
	
	
	
	public void afficherPlateau() {
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
	
	
	
	public boolean checkPose(int x,int y) {
		if(x == -1 || y == -1 || y == this.remplissage.size() || x == this.remplissage.get(0).size()) {
			checkPosExtremite(x,y);		
		}

		if(super.getCarte(x, y) != null) { 
			return false;
		}
		if(super.getCarte(x-1, y) != null || super.getCarte(x, y+1) != null || super.getCarte(x+1, y) != null || super.getCarte(x, y-1) != null) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean checkPosExtremite(int x, int y) {
		switch(x) {
			case -1: 
				if(super.getCarte(0, y) && ())
		}
		return false;
	}
}
