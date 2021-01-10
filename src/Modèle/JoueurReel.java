package Modèle;

import java.io.IOException;
import java.util.Scanner;

import Vue.VueTexte;

public class JoueurReel extends Joueur{
	
	private boolean CVchang;
	private boolean askChange, stopThread;
	private VueTexte vueTexte = new VueTexte();
	
	public boolean isAskChange() {
		return askChange;
	}


	public void setAskChange(boolean askChange) {
		this.askChange = askChange;
	}


	public boolean isStopThread() {
		return stopThread;
	}


	public void setStopThread(boolean stopThread) {
		this.stopThread = stopThread;
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
		int index=-1; 
		boolean incorrectInput = true;
		int xPose=0 ,yPose=0;
		
		index = this.vueTexte.choixCartePose(this.getMain());
		
		
		while(incorrectInput) {
			xPose = this.vueTexte.choixXPose();
			yPose = this.vueTexte.choixYPose();
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
		return vueTexte.askDeplacer();
		
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
			xCarte = this.vueTexte.choixXPose();
			yCarte = this.vueTexte.choixYPose();
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
