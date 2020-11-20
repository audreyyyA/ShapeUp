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

	
	public Carte getCarte(int x, int y) {
		return this.remplissage.get(x).get(y);
	}
	
	public void setCarte(int x, int y, Carte carte) {
		this.remplissage.get(x).set(y, carte);
	}
	
	
	public ArrayList<ArrayList<Carte>> getRemplissage() {
	
		return this.remplissage;
		
	}
	
	public void setRemplissage(int x, int y, Carte carte) {
		
		if(this.remplissage.size()<= x || this.remplissage.get(x).size() <= y) {
			System.out.println("Nique ta mere t'as pas le droit joué là");
		}
		else {
			if(this.getCarte(x, y) != null) {
				System.out.println("Cette case est déjà prise");
			}
			else {
				this.setCarte(x, y, carte);
				System.out.println("La carte : "+carte+" a été posée en "+x+","+y);
				
			}
		}
	}
	
	public void afficherPlateau() {
		for(int i=0; i < this.remplissage.size(); i++) {
			String s = "|";
			for(int j=0; j< this.remplissage.get(i).size(); j++) {
				
				if(this.remplissage.get(i).get(j) == null) {
					s+= " ,";
				}
				else {
					s+= this.remplissage.get(i).get(j).toString() + ",";
				}
			}
			System.out.println(s + "|");
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
		else if(this.forme == FormePlateau.HEXAGONE) {
			
			int i = 2;
			
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
