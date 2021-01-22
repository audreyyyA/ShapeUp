package appli;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Modèle.FormePlateau;
import Modèle.Joueur;
import Modèle.MainJoueur;
import Modèle.Partie;
import Modèle.Regle;
import Modèle.Variante1;
import Modèle.Variante2;
import Vue.Accueil;
import Vue.InterfacePlateau;

/**
 * @author ALCARAZ, DUTOUR
 * Représente l'application générale du jeu
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
