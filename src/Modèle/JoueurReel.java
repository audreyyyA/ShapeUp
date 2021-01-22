package Mod�le;

import java.io.IOException;
import java.util.Scanner;

import Vue.VueTexte;

/**
 * Repr�sente un joueur r�el 
 * @author ALCARAZ, DUTOUR
 */
public class JoueurReel extends Joueur{
	
	private boolean CVchang;
	private int xPose,yPose,index,xCarte,xDep,yDep;
	private int yCarte = -1;
	private boolean pose;
	private boolean choixPosDep = false;
	
	
	
	public int getxDep() {
		return xDep;
	}


	public void setxDep(int xDep) {
		this.xDep = xDep;
	}


	public int getyDep() {
		return yDep;
	}


	public void setyDep(int yDep) {
		this.yDep = yDep;
	}


	public int getIndex() {
		return index;
	}
	

	public int getxCarte() {
		return xCarte;
	}

	public boolean isChoixPosDep() {
		return choixPosDep;
	}


	public void setChoixPosDep(boolean choixPosDep) {
		this.choixPosDep = choixPosDep;
	}


	public void setxCarte(int xCarte) {
		this.xCarte = xCarte;
	}



	public int getyCarte() {
		return yCarte;
	}



	public void setyCarte(int yCarte) {
		this.yCarte = yCarte;
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


	public JoueurReel(String nom, int num) {
		
		super(nom,num);
		this.setCVchang(false);
		
	}
	
	
	public void abandonner() {
		
	}
	
	public void afficherCarteVictoire() {
		
		
	}
	
	/**
	 * Permet au joueur de poser une carte
	 * @param le plateau actuel et le num�ro du tour
	 */
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
			this.pose = false;
			if(tour !=1) {
				if(plateauActuel.checkPose(xPose, yPose)) {
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

		plateauActuel.setRemplissage(xPose, yPose, this.main.getCarte(index));
		super.main.retirerCarte(index);
		
	}

	/**
	 * Demande au joueur s'il veut d�placer une carte
	 */
	public boolean askDeplacer(){
		
		this.setChanged();
		this.notifyObservers("ask");
		this.setChanged();
		this.notifyObservers(this.vueTexte.getThread());
		vueTexte.askDeplacer(this);
		return this.deplacer;	
	}
	
	/**
	 * Permet � un joueur r�el de d�placer une carte
	 * @param le plateau actuel
	 */
	public boolean deplacerCarte(Plateau plateau) {
		boolean deplacer = true;
		int xDeplacer=0,yDeplacer=0,xCarte=0,yCarte=0;
		Scanner sc = new Scanner(System.in);
		boolean incorrectInput1 = true;
		boolean incorrectInput = true;
		
		this.setChanged();
		this.notifyObservers("ChoixCarteDeplacer");
		this.setChanged();
		this.notifyObservers(this.vueTexte.getThread());
		
		
		this.vueTexte.choixXDeplacer();
		
		if(this.yCarte == -1) {
			this.xCarte = this.vueTexte.getxDeplacer();
			this.vueTexte.choixYDeplacer();
			this.yCarte = this.vueTexte.getyDeplacer();
		}
		
		System.out.println(this.xCarte+","+this.yCarte);
		
		if(plateau.getCarte(this.xCarte, this.yCarte) == null) {
			this.vueTexte.noCard();
			return false;
		}
		
		
		this.vueTexte.carteVoulue(plateau.getCarte(this.xCarte, this.yCarte));
		Carte carteTemp = plateau.getCarte(this.xCarte, this.yCarte);
			
		while(incorrectInput) {
			
			this.vueTexte.choixXPose();			
			if(!this.choixPosDep) {
				xDeplacer = this.vueTexte.getxPose();
				yDeplacer = this.vueTexte.getyPose();
				System.out.println(xDeplacer + " " + yDeplacer);
				this.vueTexte.choixYPose();
			}
			else {
				xDeplacer = this.xDep;
				yDeplacer = this.yDep;
			}

			
			plateau.setCarte(this.xCarte, this.yCarte, null);
			if(plateau.getForme() == FormePlateau.HEXAGONE) {
				plateau.deplacerPlateau(plateau.checkPosExtremiteHex(xDeplacer,yDeplacer));
				incorrectInput = false;
			}
			else {
				if(plateau.checkPose(xDeplacer, yDeplacer)) {
					plateau.deplacerPlateau(xDeplacer,yDeplacer);
					incorrectInput = false;
				}
			
				if(incorrectInput){
					plateau.setRemplissage(this.xCarte, this.yCarte, carteTemp);
					this.vueTexte.poseCarteImpossible();
					
					if(!askDeplacer()) {
						return true;
					}
				}
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
		
		plateau.setRemplissage(xDeplacer, yDeplacer, carteTemp);
		plateau.afficherPlateau();
		this.setChanged();
		this.notifyObservers("EndDeplacement");
		return true;
	}
	
	public boolean isCVchang() {
		return CVchang;
	}


	public void setCVchang(boolean cVchang) {
		CVchang = cVchang;
	}

}
