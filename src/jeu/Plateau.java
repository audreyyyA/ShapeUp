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
		if(y >= this.remplissage.size() || y<0 || x<0 || x >= this.remplissage.get(y).size()) {
			return null;
		}
		return this.remplissage.get(y).get(x);
	}
	
	public void setCarte(int x, int y, Carte carte) {
		this.remplissage.get(y).set(x, carte);
	}
	
	
	public ArrayList<ArrayList<Carte>> getRemplissage() {
	
		return this.remplissage;
		
	}
	
	public void setRemplissage(int x, int y, Carte carte) {
		
		if(this.remplissage.size()<= y || this.remplissage.get(y).size() <= x) {
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
	
	
	public boolean checkPose(int x, int y) { //Check si une carte est adjacente à l'endroit de pose
		if(x == -1) { 
			if(this.getCarte(0, y) !=null && this.checkDeplacement(Position.DROITE)) {
				this.deplacerPlateau(Position.DROITE);
				this.afficherPlateau();
				return true;
			}
		}
		else if(y == -1) {
			if(this.getCarte(x,0) != null && this.checkDeplacement(Position.HAUT)) {
				this.deplacerPlateau(Position.HAUT);
				return true;
			}
		}
		else if(x == this.remplissage.size()) {
			if(this.getCarte(x-1, y) != null && this.checkDeplacement(Position.BAS)) {
				this.deplacerPlateau(Position.BAS);
				return true;
			}
		}
		else if(y == this.remplissage.get(x).size()) {
			if(this.getCarte(x, y-1) != null && this.checkDeplacement(Position.GAUCHE)) {
				this.deplacerPlateau(Position.GAUCHE);
				return true;
			}
		}
		else if(this.getCarte(x-1, y) != null || this.getCarte(x+1, y) != null || this.getCarte(x, y+1) != null || this.getCarte(x, y-1) != null) {
			return true;
		}
		return false; 
	}
	
	
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
		
		else if(this.forme == FormePlateau.HEXAGONE) {
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
		System.out.print("\n");
	}
	
	public boolean checkDeplacement(Position position) {
		
		if(position == Position.HAUT) {
			int nbligne = this.remplissage.size() - 1;
			for(int i=0; i<remplissage.get(nbligne).size(); i++) { //boucle de 0 à la taille de la derniere ligne
				if(this.getCarte(nbligne,i) != null) {
					return false;
				}
			}
			return true;
		}
		
		else if(position == Position.BAS) {
			for(int i=0; i< this.remplissage.get(0).size(); i++) {
				if(this.getCarte(0,i) != null) {
					return false;
				}
			}
			return true;
		}
		
		else if(position == Position.GAUCHE) {
			for(int i=0;i<this.remplissage.size()-1; i++) {
				if(this.getCarte(0,i) != null) {
					return false;
				}
			}
			return true;
		}
		
		
		else if(position == Position.DROITE) {
			for(int i=0; i<this.remplissage.size(); i++) {
				if(this.getCarte(this.remplissage.get(i).size()-1,i) != null) {
					return false;
				}
			}
			return true;
		}
			
		return false;
	}
	
	
	public void deplacerPlateau(Position position) {
		
			if(position == Position.HAUT) {
				int nbligne = this.remplissage.size() - 1;
				for(int i=nbligne-1; i>=0; i--) {
					for(int j=0; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j, i+1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
			
			else if(position == Position.BAS) {
				int nbligne = this.remplissage.size() - 1;
				for(int i=1; i<nbligne; i++) {
					for(int j=0; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j, i-1, this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
			
			
			else if(position == Position.GAUCHE) {
				for(int i=0; i<this.remplissage.size(); i++) {
					for(int j=1; j<this.remplissage.get(i).size(); j++) {
						this.setCarte(j-1, i,this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
			}
			
			
			else if(position == Position.DROITE) {
				for(int i=0; i<this.remplissage.size(); i++) {
					for(int j=this.remplissage.get(i).size()-2; j>=0 ; j--) {
						this.setCarte(j+1, i,this.getCarte(j, i));
						this.setCarte(j, i, null);
					}
				}
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
