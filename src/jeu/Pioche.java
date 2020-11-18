package jeu;
import java.util.ArrayList;

public class Pioche {
	
	private int nbCarteRestantes;
	private ArrayList<Carte> pioche;
	
	public Pioche() {
		
		this.nbCarteRestantes = 17; 
		this.initialiser();
	}
	
	
	public Carte getRandomCarte() {
		if(this.nbCarteRestantes == 0) {
			System.out.println("La pioche est vide");
			return null;
		}
		else {
			int random = (int)(Math.random()*this.nbCarteRestantes);
			Carte randomCard = pioche.get(random);
			pioche.remove(random);
			this.nbCarteRestantes -=1;
			return randomCard;
		}
	}
	
	public void initialiser() {
		this.pioche = new ArrayList<Carte>();
			
		pioche.add(0,new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,true));
		pioche.add(1,new Carte(Couleur.BLEU,FormeCarte.CARRE,true));
		pioche.add(2,new Carte(Couleur.BLEU,FormeCarte.CERCLE,true));
		pioche.add(3,new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false));
		pioche.add(4,new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false));
		pioche.add(5,new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false));
		pioche.add(6,new Carte(Couleur.ROUGE,FormeCarte.TRIANGLE,true));
		pioche.add(7,new Carte(Couleur.ROUGE,FormeCarte.CARRE,true));
		pioche.add(8,new Carte(Couleur.ROUGE,FormeCarte.CERCLE,true));
		pioche.add(9,new Carte(Couleur.ROUGE,FormeCarte.TRIANGLE,false));
		pioche.add(10,new Carte(Couleur.ROUGE,FormeCarte.CARRE,false));
		pioche.add(11,new Carte(Couleur.ROUGE,FormeCarte.CERCLE,false));
		pioche.add(12,new Carte(Couleur.VERT,FormeCarte.TRIANGLE,true));
		pioche.add(13,new Carte(Couleur.VERT,FormeCarte.CARRE,true));
		pioche.add(14,new Carte(Couleur.VERT,FormeCarte.CERCLE,true));
		pioche.add(15,new Carte(Couleur.VERT,FormeCarte.TRIANGLE,false));
		pioche.add(16,new Carte(Couleur.VERT,FormeCarte.CARRE,false));
		pioche.add(17,new Carte(Couleur.VERT,FormeCarte.CERCLE,false));
		
	}

}
