package jeu;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau {
	
	private FormePlateau forme;
	//private Carte [][] remplissage;
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
	
	/*public Carte[][] getRemplissage() {
		
		return this.remplissage;
	}

	public void setRemplissage(Carte[][] remplissage) {
		
		this.remplissage = remplissage;
	}*/
	
	public void afficherPlateau() {
		
	}
	
	public void initialiser() {
		
		/*if(this.forme == FormePlateau.RECTANGLE){
			
			this.remplissage = new Carte[3][5];
			
			for(int i=0; i<3; i++) {
				for(int k=0; k<5; k++) {
					
					this.remplissage[i][k] = new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false);
				}
			}
			
			
		}
		else if(this.forme == FormePlateau.TRIANGLE) {
			
			this.remplissage = new Carte[4][7];
			
			int i = 1;
			for(int k=0; k<4; k++) {
				
				for(int j = 0; j<i; j++) {
				
					this.remplissage[k][j] = new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false);
					
				}
				i+=2;
			}
		}
		else {
			
			this.remplissage = new Carte[3][7];
			int i = 1;
			for(int k=0; k<3; k++) {
				
				for(int j = 0; j<i; j++) {
				
					this.remplissage[k][j] = new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false);
					
				}
				i=7;
			}
		}*/
		

		if(this.forme == FormePlateau.RECTANGLE){
			
			ArrayList<ArrayList<Carte>> r = new ArrayList<ArrayList<Carte>>();
			this.remplissage = r;
			
			for(int i=0; i<3; i++) {
				ArrayList<Carte> l=new ArrayList<>();
				
				for(int k=0; k<5; k++) {
				    l.add(new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false));
				    this.remplissage.add(i,l);
					
				}
			}
			
		}
		else if(this.forme == FormePlateau.TRIANGLE) {
			
			ArrayList<ArrayList<Carte>> r = new ArrayList<ArrayList<Carte>>();
			this.remplissage = r;
			
			int i = 1;
			for(int k=0; k<4; k++) {
				ArrayList<Carte> l=new ArrayList<>();
				
				for(int j = 0; j<i; j++) {
				
					l.add(new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false));
				    this.remplissage.add(k,l);
					//this.remplissage[k][j] = new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false);
					
				}
				i+=2;
			}
		}
		else {
			
			ArrayList<ArrayList<Carte>> r = new ArrayList<ArrayList<Carte>>();
			this.remplissage = r;
			
			int i = 1;
			for(int k=0; k<3; k++) {
				ArrayList<Carte> l=new ArrayList<>();
				for(int j = 0; j<i; j++) {
				
					l.add(new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false));
				    this.remplissage.add(k,l);
					//this.remplissage[k][j] = new Carte(Couleur.BLEU, FormeCarte.TRIANGLE, false);
					
				}
				i=7;
			}
		}
	}
	
	public String toString() {
		
		String s = null;
		
		/*for(int i = 0; i < this.remplissage.length; i++) {
			for(int j = 0; j < this.remplissage[]; i++) {
				
				
			}
			
		}*/
		
		//System.out.println(Arrays.deepToString(this.remplissage));
		
		return s;
	}
	

}
