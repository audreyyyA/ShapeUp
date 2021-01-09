package Vue;

import java.util.ArrayList;
import java.util.Scanner;

import Mod�le.Carte;
import Mod�le.MainJoueur;



public class VueTexte implements Runnable {

	public static String QUITTER = "Quit";
    public static String COMMUTER = "C";
    public static String PROMPT = ">";
    
    public VueTexte() {
    	Thread t = new Thread(this);
    	t.start();
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
		System.out.print("Ordonn�e de pose : ");
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
		System.out.print("Voulez vous d�placer une carte ? (O/N) ");

		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		if(s.equals("O") || s.equals("o")) {
			return true;
		}
		else if(s.equals("N") || s.equals("n")) {
			return false;
		}
		else {
			System.out.println("Veuillez r�pondre par O ou N");
			return askDeplacer();
		}
	}
	
	public int choixYDeplacer() {
		int yDeplacer = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Ordonn�e de pose : ");
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
		System.out.println("la carte que tu veux d�placer : " + carte);
	}

	public void afficherMain(ArrayList<Carte> cartes) {
		System.out.print("        ");
		for(int i=0; i<cartes.size(); i++) {
			System.out.print("    "+i);
		}
		System.out.println("\nTa main : " + cartes+"\n");
	}
	
	@Override
	public void run() {
		
	}
	
	
}
