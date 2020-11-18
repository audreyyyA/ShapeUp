package jeu;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau {
	
	private FormePlateau forme;
	private ArrayList<ArrayList<Carte>> remplissage;
	
	public Plateau(FormePlateau forme){
		
		this.forme = forme;
		this.initialiser();
	}

	public FormePlateau getForme() {
		return forme;
	}

	public void setForme(FormePlateau forme) {
		this.forme = forme;
	}

	
	public ArrayList<ArrayList<Carte>> getRemplissage() {
	
		return this.remplissage;
		
	}
	
	public void setRemplissage(ArrayList<ArrayList<Carte>> remplissage) {
		
		this.remplissage = remplissage;
	}
	
	public void afficherPlateau() {
		for(int i=0; i < this.remplissage.size(); i++) {
			
			System.out.println(this.remplissage.get(i));
		}
	}
	
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
		else if(this.forme == FormePlateau.TRIANGLE) {
			
			int i = 1;
			for(int k=0; k<4; k++) {
				ArrayList<Carte> l=new ArrayList<>();
				
				for(int j = 0; j<i; j++) {
				
					l.add(null);
				}
				this.remplissage.add(k,l);
				i+=2;
			}
		}
		else {
			
			int i = 1;
			for(int k=0; k<3; k++) {
				ArrayList<Carte> l=new ArrayList<>();
				for(int j = 0; j<i; j++) {
					l.add(null);
				}
				this.remplissage.add(k,l);
				i=7;
			}
		}
	}
}
