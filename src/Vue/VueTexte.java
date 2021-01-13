package Vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import Modèle.Carte;
import Modèle.Joueur;
import Modèle.MainJoueur;



public class VueTexte extends Observable implements Runnable {

	public static String QUITTER = "Quit";
	public static String COMMUTER = "C";
	public static String PROMPT = ">";
	private int indexCarte,xPose,yPose;
	private Thread thread;
	
	public int getyPose() {
		return yPose;
	}

	public void setyPose(int yPose) {
		this.yPose = yPose;
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
		
		
		try {
			index = sc.nextInt();
		}
		catch(Exception e) {}
		
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
						}catch(Exception e) {}
						
					}
				} catch (IOException e) {}
			}
			catch(Exception e) {}
		}
	}

	
	public void choixXPose() {
		this.xPose = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		System.out.print("Abscisse de pose : ");
		System.out.print(this.thread.isInterrupted());
		try {
			while(!reader.ready() && !this.thread.isInterrupted()) {
				try {
					this.thread.sleep(100);
					System.out.println("J'attends");
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
					this.xPose = sc.nextInt();
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
		this.yPose = 0;
		this.setChanged();
		this.notifyObservers(this.thread);
		this.setChanged();
		this.notifyObservers("YPoseChoice");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(reader);
		System.out.print("Ordonnée de pose : ");
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
					this.yPose = sc.nextInt();
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
		System.out.print("Voulez vous déplacer une carte ? (O/N) ");
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
			System.out.println("Veuillez répondre par O ou N");
			askDeplacer(j);
		}
	}

	public int choixYDeplacer() {
		int yDeplacer = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ordonnée de la carte à deplacer : ");
		try {
			yDeplacer = sc.nextInt();
		}catch(Exception e) {
			System.out.println("Veuillez rentrer un chiffre");
			return choixYDeplacer();
		}
		return yDeplacer;
	}

	public int choixXDeplacer() {
		int xDeplacer = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Abcisse de la carte à deplacer : ");
		try {
			xDeplacer = sc.nextInt();
		}catch(Exception e) {
			System.out.println("Veuillez rentrer un chiffre");
			xDeplacer = choixXDeplacer();
		}
		return xDeplacer;
	}

	public void noCard() {
		System.out.println("Tu as choisis un emplacement sans cartes !\n");
	}

	public void carteVoulue(Carte carte) {
		System.out.println("la carte que tu veux déplacer : " + carte);
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
		System.out.println("La carte défaussée était "+ carte);
	}

	public void printGagnantManche(ArrayList<Joueur> gagnant) {
		if(gagnant.size()>1) { // égalité
			System.out.print("Les joueurs gagnants à égalité de cette manche sont : ");
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
			System.out.println("Carte victoire de " + t.getNom() + "à cette manche était : " + t.getCarteVictoire());
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
		System.out.println("La carte : "+carte+" a été posée en "+x+","+y);
	}
	
	public void casePrise() {
		System.out.println("Cette case est déjà prise");
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
