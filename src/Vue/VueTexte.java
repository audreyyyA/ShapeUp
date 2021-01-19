package Vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Mod�le.Carte;
import Mod�le.Joueur;
import Mod�le.MainJoueur;



public class VueTexte extends Observable implements Runnable {

	public static String QUITTER = "Quit";
	public static String COMMUTER = "C";
	public static String PROMPT = ">";
	private int indexCarte,xPose,yPose,yDeplacer,xDeplacer;
	private Thread thread;
	
	public int getyPose() {
		return yPose;
	}

	public void setyPose(int yPose) {
		this.yPose = yPose;
	}

	public int getyDeplacer() {
		return yDeplacer;
	}

	public void setyDeplacer(int yDeplacer) {
		this.yDeplacer = yDeplacer;
	}

	public int getxDeplacer() {
		return xDeplacer;
	}

	public void setxDeplacer(int xDeplacer) {
		this.xDeplacer = xDeplacer;
	}

	public int getxPose() {
		return xPose;
	}

	public void setxPose(int xPose) {
		this.xPose = xPose;
	}

	public int getIndexCarte() {
		return indexCarte;
	}

	public void setIndexCarte(int indexCarte) {
		this.indexCarte = indexCarte;
	}

	
	public VueTexte() {
		Thread t = new Thread(this);
		this.thread = t;
		t.start();
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public void closeThread() {
		this.thread.interrupt();
	}
	
	public void choixCartePose(MainJoueur main) {
		int index = 0;
		
		System.out.print("Quelle carte voulez vous poser ? ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		
		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("interrupt");
					return;
				}
			}
			if(this.thread.isInterrupted()) {
				return;
			}
			else {
				try {
					this.indexCarte = sc.nextInt();
				}catch(Exception e) {}
			}
		} catch (IOException e) {}
		
		index = this.indexCarte;
		System.out.println(index);
		
		while (index <0 || index > main.getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosis en un entre 0 et " + (main.getCartes().size()-1));// verif si string
			System.out.print("Quelle carte voulez vous poser ? ");
			try {
				reader = new BufferedReader(new InputStreamReader(System.in));
				sc = new Scanner(System.in);
				try {
					while(!reader.ready() && !this.thread.isInterrupted()) {
						try {
							this.thread.sleep(100);
						} catch (InterruptedException e) {
							System.out.println("interrupt");
							return;
						}
					}
					if(this.thread.isInterrupted()) {
						return;
					}
					else {
						try {
							this.indexCarte = sc.nextInt();
							index = this.indexCarte;
						}catch(Exception e) {}
						
					}
				} catch (IOException e) {}
			}
			catch(Exception e) {}
		}
	}

	
	public void choixXPose() {
		
		int x = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		System.out.print("Abscisse de pose : ");
		System.out.print(this.thread.isInterrupted());
		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("interrupt");
					return;
				}
			}
			if(this.thread.isInterrupted()) {
				System.out.print("interrupt");
				return;
			}
			else {
				try {
					x = sc.nextInt();
					this.setxPose(x);
					return;
				}catch(Exception e) {
					System.out.println("Veuillez rentrer un chiffre");
					choixXPose();
					return;
				}
			}
		} catch (IOException e) {}
	}

	
	public void choixYPose() {
		//this.yPose = 0;
		int y = 0;
		this.setChanged();
		this.notifyObservers(this.thread);
		this.setChanged();
		this.notifyObservers("YPoseChoice");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		System.out.print("Ordonn�e de pose : ");
		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("interrupt");
					return;
				}
			}
			if(this.thread.isInterrupted()) {
				return;
			}
			else {
				try {
					y = sc.nextInt();
					this.setyPose(y);
					return;
				}catch(Exception e) {
					System.out.println("Veuillez rentrer un chiffre");
					choixYPose();
					return;
				}
			}
		} catch (IOException e) {}
	}

	public void poseCarteImpossible() {
		System.out.println("Tu ne peux pas poser de carte ici");
	}

	public void askDeplacer(Joueur j) {
		System.out.print("Voulez vous d�placer une carte ? (O/N) ");
		String s = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);

		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("interrupt");
					// TODO Auto-generated catch block
					return;
				}
			}
			if(this.thread.isInterrupted()) {
				return;
			}
			else {
				s = sc.nextLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		if(s.equals("O") || s.equals("o")) {
			j.setDeplacer(true);
		}
		else if(s.equals("N") || s.equals("n")) {
			j.setDeplacer(false);
		}
		else {
			System.out.println("Veuillez r�pondre par O ou N");
			askDeplacer(j);
		}
	}

	public void choixYDeplacer() {
		this.yDeplacer = 0;
		this.setChanged();
		this.notifyObservers(this.thread);
		this.setChanged();
		this.notifyObservers("YMoveChoice");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		System.out.println("Ordonn�e de la carte � deplacer : ");
		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("interrupt");
					return;
				}
			}
			if(this.thread.isInterrupted()) {
				return;
			}
			else {
				try {
					this.yDeplacer = sc.nextInt();
					return;
				}catch(Exception e) {
					System.out.println("Veuillez rentrer un chiffre");
					choixYDeplacer();
					return;
				}
			}
		} catch (IOException e) {}
	}

	public void choixXDeplacer() {
		this.xDeplacer = 0;
		this.setChanged();
		this.notifyObservers(this.thread);
		this.setChanged();
		this.notifyObservers("YMoveChoice");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		System.out.print("Abcisse de la carte � deplacer : ");
		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("interrupt");
					return;
				}
			}
			if(this.thread.isInterrupted()) {
				return;
			}
			else {
				try {
					this.xDeplacer = sc.nextInt();
					return;
				}catch(Exception e) {
					System.out.println("Veuillez rentrer un chiffre");
					choixXDeplacer();
					return;
				}
			}
		} catch (IOException e) {}
	}

	public void noCard() {
		System.out.println("Tu as choisis un emplacement sans cartes !\n");
	}

	public void carteVoulue(Carte carte) {
		System.out.println("la carte que tu veux d�placer : " + carte);
	}

	public void afficherMain(ArrayList<Carte> cartes) {
		System.out.print("\n        ");
		for(int i=0; i<cartes.size(); i++) {
			System.out.print("    "+i);
		}
		System.out.println("\nTa main : " + cartes+"\n");
	}

	public void printTour(int nbTour, String name) {
		System.out.println("Tour "+nbTour/2+" - "+ name+ "\n");
	}

	public void carteDefausse(Carte carte) {
		System.out.println("La carte d�fauss�e �tait "+ carte);
	}

	public void printGagnantManche(ArrayList<Joueur> gagnant) {
		if(gagnant.size()>1) { // �galit�
			System.out.print("Les joueurs gagnants � �galit� de cette manche sont : ");
			for(Joueur g : gagnant) {
				System.out.print(g.getNom());
			}
		}
		else {
			System.out.println("Le joueur gagnant de cette manche est : " + gagnant.get(0).getNom());
		}
	}

	public void printPoints(ArrayList<Joueur> tabJoueur) {
		for(Joueur t : tabJoueur) {
			System.out.println("Carte victoire de " + t.getNom() + "� cette manche �tait : " + t.getCarteVictoire());
			System.out.println("Points de " + t.getNom() + " : " + t.getNbPointsManches());
		}
	}

	public void printGagnantPartie(Joueur gagnant) {
		System.out.println("Bravo ! Le joueur gagnant de la partie est : " + gagnant.getNom());
	}

	public void afficherScore(ArrayList<Joueur> tabJoueur) {
		System.out.println("Voici le tableau des scores :");
		for(Joueur j : tabJoueur) {
			System.out.println(j.toString());
		}
		
		
	}

	public void piocheVide() {
		System.out.println("La pioche est vide");
	}
	
	public void poseCarte(Carte carte, int x, int y) {
		System.out.println("La carte : "+carte+" a �t� pos�e en "+x+","+y);
	}
	
	public void casePrise() {
		System.out.println("Cette case est d�j� prise");
	}
	
	public void avantPoseIA(Joueur j) {
		System.out.println("Le joueur virtuel " + j.getNom() + " va poser une carte ...");
	}
	
	public void poseIA(Carte carte, int xMax, int yMax) {
		System.out.println("il pose la carte : " + carte + " en : " + xMax + " , " + yMax);
	}
	
	public void deplacementIA(Carte carte, int xMax, int yMax) {
		System.out.println("il deplace la carte : " + carte + " en : " + xMax + " , " + yMax);
	}
	
	@Override
	public void run() {
		while(!this.thread.isInterrupted()) {
			try {
				this.thread.sleep(100);
			} catch (InterruptedException e) {
				this.thread.interrupt();
			}
		}
	}


}
