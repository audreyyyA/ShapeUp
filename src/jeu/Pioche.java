package jeu;

public class Pioche {
	
	private int nbCarteRestantes;
	private Carte [] pioche;
	
	public Pioche() {
		
		this.nbCarteRestantes = 15; //14 si 3 joueurs
		this.initialiser();
	}
	
	
	public Carte getRandomCarte() {
		
		Carte c = null;
		return c;
		
	}
	
	public void initialiser() {
		
		pioche[0] = new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,true);
		pioche[1] = new Carte(Couleur.BLEU,FormeCarte.CARRE,true);
		pioche[2] = new Carte(Couleur.BLEU,FormeCarte.CERCLE,true);
		pioche[3] = new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false);
		pioche[4] = new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false);
		pioche[5] = new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false);
		pioche[6] = new Carte(Couleur.ROUGE,FormeCarte.TRIANGLE,true);
		pioche[7] = new Carte(Couleur.ROUGE,FormeCarte.CARRE,true);
		pioche[8] = new Carte(Couleur.ROUGE,FormeCarte.CERCLE,true);
		pioche[9] = new Carte(Couleur.ROUGE,FormeCarte.TRIANGLE,false);
		pioche[10] = new Carte(Couleur.ROUGE,FormeCarte.CARRE,false);
		pioche[11] = new Carte(Couleur.ROUGE,FormeCarte.CERCLE,false);
		pioche[12] = new Carte(Couleur.VERT,FormeCarte.TRIANGLE,true);
		pioche[13] = new Carte(Couleur.VERT,FormeCarte.CARRE,true);
		pioche[14] = new Carte(Couleur.VERT,FormeCarte.CERCLE,true);
		pioche[15] = new Carte(Couleur.VERT,FormeCarte.TRIANGLE,false);
		pioche[16] = new Carte(Couleur.VERT,FormeCarte.CARRE,false);
		pioche[17] = new Carte(Couleur.VERT,FormeCarte.CERCLE,false);

	}

}
