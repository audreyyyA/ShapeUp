package Modèle;

import java.io.IOException;
import java.util.Scanner;

import Vue.VueTexte;

public class JoueurReel extends Joueur{
	
	private boolean CVchang;
	private VueTexte vueTexte = new VueTexte();
	private int xPose,yPose,index;
	private boolean pose;
	
	
	
	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getxPose() {
		return xPose;
	}


	public void setxPose(int xPose) {
		this.xPose = xPose;
	}


	public int getyPose() {
		return yPose;
	}


	public void setyPose(int yPose) {
		this.yPose = yPose;
	}
	
	
	public boolean isPose() {
		return pose;
	}


	public void setPose(boolean pose) {
		this.pose = pose;
	}


	public VueTexte getVueTexte() {
		return vueTexte;
	}


	public void setVueTexte(VueTexte vueTexte) {
		this.vueTexte = vueTexte;
	}


	public JoueurReel(String nom, int num) {
		
		super(nom,num);
		this.setCVchang(false);
		
	}
	
	
	public void abandonner() {
		
	}
	
	public void afficherCarteVictoire() {
		
		
	}
	
	public void poserCarte(Plateau plateauActuel, int tour) {
		this.index=-1; 
		boolean incorrectInput = true;
		this.xPose=0;
		this.yPose=0;
		
		this.setChanged();
		this.notifyObservers("Pose");
		this.setChanged();
		this.notifyObservers(this.vueTexte.getThread());
		
		this.vueTexte.choixCartePose(this.getMain());
		index = this.vueTexte.getIndexCarte();
		
		while(incorrectInput) {
			if(!this.pose) {
				this.vueTexte.choixXPose();
				this.xPose = this.vueTexte.getxPose();

				this.vueTexte.choixYPose();
				this.yPose = this.vueTexte.getyPose();
			}
			if(tour !=1) {
				//on vérifie si c'est possible de poser la carte 
				if(plateauActuel.checkPose(xPose, yPose)) {
					
					//changer ... tout avec le mm nom de méthode plus tard
					if(plateauActuel.getForme() == FormePlateau.HEXAGONE) {
						plateauActuel.deplacerPlateau(plateauActuel.checkPosExtremiteHex(xPose,yPose));
					}
					else if((xPose == -1) || (yPose == -1) || (xPose == plateauActuel.getRemplissage().get(1).size()) || (yPose == plateauActuel.getRemplissage().size())){
						plateauActuel.deplacerPlateau(xPose,yPose);
					}
					this.setChanged();
					this.notifyObservers("PoseRealisee");
					incorrectInput = false;
				}
				else {
					this.vueTexte.poseCarteImpossible();
				}
			}
			else {
				incorrectInput = false;
			}
		}
		
		//on change les abscisses ou ordonnées si nécessaire
	
		if(xPose == -1) {
			xPose =0;
		}
		else if(yPose == -1) {
			yPose =0;
		}
		else if(yPose == plateauActuel.getRemplissage().size()) {
			yPose = plateauActuel.getRemplissage().size()-1;
		}
		else if(xPose == plateauActuel.getRemplissage().get(yPose).size()) {
			xPose = plateauActuel.getRemplissage().get(yPose).size() -1;
		}

		
		//on pose la carte
		plateauActuel.setRemplissage(xPose, yPose, this.main.getCarte(index));
		super.main.retirerCarte(index);
		
	}


	public boolean askDeplacer(){
		
		this.setChanged();
		this.notifyObservers("ask");
		this.setChanged();
		this.notifyObservers(this.vueTexte.getThread());
		vueTexte.askDeplacer(this);
		return this.deplacer;	
	}
	
	public boolean deplacerCarte(Plateau plateau) {
		boolean deplacer = true;
		int xDeplacer=0,yDeplacer=0,xCarte=0,yCarte=0;
		Scanner sc = new Scanner(System.in);
		boolean incorrectInput = true;
		
		xCarte = this.vueTexte.choixXDeplacer();
		yCarte = this.vueTexte.choixYDeplacer();
		
		if(plateau.getCarte(xCarte, yCarte) == null) {
			this.vueTexte.noCard();
			return false;

		}
		
		this.vueTexte.carteVoulue(plateau.getCarte(xCarte, yCarte));
		Carte carteTemp = plateau.getCarte(xCarte, yCarte);
			
		while(incorrectInput) {
			this.vueTexte.choixXPose();
			xDeplacer = this.vueTexte.getxPose();
			
			this.vueTexte.choixYPose();
			yDeplacer = this.vueTexte.getyPose();
			
			plateau.setCarte(xCarte, yCarte, null);
				//on vérifie si c'est possible de poser la carte 
			if(plateau.getForme() == FormePlateau.HEXAGONE) {
				plateau.deplacerPlateau(plateau.checkPosExtremiteHex(xDeplacer,yDeplacer));
				incorrectInput = false;
			}
			else {
				if(plateau.checkPose(xDeplacer, yDeplacer)) {
					//changer ... tout avec le mm nom de méthode plus tard
					plateau.deplacerPlateau(xDeplacer,yDeplacer);
					incorrectInput = false;
				}
			}

			if(incorrectInput){
				plateau.setRemplissage(xCarte, yCarte, carteTemp);
				//plateau.setCarte(xCarte, yCarte, carteTemp);
				this.vueTexte.poseCarteImpossible();
			}
		}
			
		
		if(xDeplacer == -1) {
			xDeplacer =0;
		}
		if(yDeplacer == -1) {
			yDeplacer =0;
		}
		if(xDeplacer == plateau.getRemplissage().get(plateau.getRemplissage().size()-1).size()) {
			xDeplacer = plateau.getRemplissage().get(plateau.getRemplissage().size()-1).size() -1;
		}
		if(yDeplacer == plateau.getRemplissage().size()) {
			yDeplacer = plateau.getRemplissage().size()-1;
		}
		
		//on pose la carte
		plateau.setRemplissage(xDeplacer, yDeplacer, carteTemp);
		plateau.afficherPlateau();
		return true;
	}
	
	public boolean isCVchang() {
		return CVchang;
	}


	public void setCVchang(boolean cVchang) {
		CVchang = cVchang;
	}

}
