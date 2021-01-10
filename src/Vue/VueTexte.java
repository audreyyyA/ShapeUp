package Vue;

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
	private Thread thread;
	
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

	public int choixCartePose(MainJoueur main) {
		int index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Quelle carte voulez vous poser ? ");
		try {
			index = sc.nextInt();
		}
		catch(Exception e) {}
		while (index <0 || index > main.getCartes().size()-1) {
			System.out.println("Tu as choisis un index incorrect. Chosis en un entre 0 et " + (main.getCartes().size()-1));// verif si string
			System.out.print("Quelle carte voulez vous poser ? ");
			try {
				sc = new Scanner(System.in);
				index = sc.nextInt();
			}
			catch(Exception e) {}
		}
		return index;
	}

	public int choixXPose() {
		int xPose = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Abscisse de pose : ");
		try {
			xPose = sc.nextInt();
		}catch(Exception e) {
			System.out.println("Veuillez rentrer un chiffre");
			return choixXPose();
		}

		return xPose;
	}

	public int choixYPose() {
		int yPose = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Ordonnée de pose : ");
		try {
			yPose = sc.nextInt();
		}catch(Exception e) {
			System.out.println("Veuillez rentrer un chiffre");
			return choixYPose();
		}
		return yPose;
	}

	public void poseCarteImpossible() {
		System.out.println("Tu ne peux pas poser de carte ici");
	}

	public boolean askDeplacer() {
		System.out.print("Voulez vous déplacer une carte ? (O/N) ");

		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		if(s.equals("O") || s.equals("o")) {
			return true;
		}
		else if(s.equals("N") || s.equals("n")) {
			return false;
		}
		else {
			System.out.println("Veuillez répondre par O ou N");
			return askDeplacer();
		}
	}

	public int choixYDeplacer() {
		int yDeplacer = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Ordonnée de pose : ");
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
		System.out.print("Abcisse de pose : ");
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
		System.out.print("        ");
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
		
	}


}
