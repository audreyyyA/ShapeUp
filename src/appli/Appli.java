package appli;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Mod�le.FormePlateau;
import Mod�le.Joueur;
import Mod�le.MainJoueur;
import Mod�le.Partie;
import Mod�le.Regle;
import Mod�le.Variante1;
import Mod�le.Variante2;
import Vue.Accueil;
import Vue.InterfacePlateau;

/**
 * @author ALCARAZ, DUTOUR
 * Repr�sente l'application g�n�rale du jeu
 * 
 */
public class Appli implements Observer,Runnable {

	public static String QUITTER = "Quit";
    public static String COMMUTER = "C";
    public static String PROMPT = ">";
    private int nb;
    private Regle regle;
    private FormePlateau forme;
    private ArrayList<Joueur> tabJoueur;
    private Partie partie;
    private InterfacePlateau interfacePlateau;
    
    public Appli(InterfacePlateau interfacePlateau,int nb, Regle regle,FormePlateau forme,ArrayList<Joueur> tabJoueur) {
       
    	this.nb = nb;
    	this.regle = regle;
    	this.forme = forme;
    	this.tabJoueur = tabJoueur;
    	this.interfacePlateau = interfacePlateau;
    	
    	this.partie = new Partie(this.nb, this.regle, this.forme, this.tabJoueur);
    	
    	for(Joueur j : tabJoueur) {
    		j.getMain().addObserver(this.interfacePlateau);
    		j.addObserver(this.interfacePlateau);
    	}
    	
    	Thread t = new Thread(this);
    	t.start();
    }
	
    @Override
	public void run() {
		partie.debutPartie(this.interfacePlateau, this);
		partie.finPartie();
		partie.afficherScore();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
