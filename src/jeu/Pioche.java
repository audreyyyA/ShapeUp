package jeu;
import java.util.ArrayList;

public class Pioche {
	
	private int nbCarteRestantes;
	private ArrayList<Carte> listeCarte;
	
	public Pioche() {
		
		this.nbCarteRestantes = 18; 
		this.initialiser();
	}
	
	
	public Carte getRandomCarte() {
		if(this.nbCarteRestantes == 0) {
			System.out.println("La pioche est vide");
			return null;
		}
		else {
			int random = (int)(Math.random()*this.nbCarteRestantes);
			Carte randomCard = listeCarte.get(random);
			listeCarte.remove(random);
			this.nbCarteRestantes -=1;
			System.out.println(this.listeCarte);
			return randomCard;
		}
	}
	
	public ArrayList<Carte> getListeCarte(){
		
		return this.listeCarte;
	}
	
	public void initialiser() {
		this.listeCarte = new ArrayList<Carte>();
			
		listeCarte.add(0,new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,true));
		listeCarte.add(1,new Carte(Couleur.BLEU,FormeCarte.CARRE,true));
		listeCarte.add(2,new Carte(Couleur.BLEU,FormeCarte.ROND,true));
		listeCarte.add(3,new Carte(Couleur.BLEU,FormeCarte.TRIANGLE,false));
		listeCarte.add(4,new Carte(Couleur.BLEU,FormeCarte.CARRE,false));
		listeCarte.add(5,new Carte(Couleur.BLEU,FormeCarte.ROND,false));
		listeCarte.add(6,new Carte(Couleur.ROUGE,FormeCarte.TRIANGLE,true));
		listeCarte.add(7,new Carte(Couleur.ROUGE,FormeCarte.CARRE,true));
		listeCarte.add(8,new Carte(Couleur.ROUGE,FormeCarte.ROND,true));
		listeCarte.add(9,new Carte(Couleur.ROUGE,FormeCarte.TRIANGLE,false));
		listeCarte.add(10,new Carte(Couleur.ROUGE,FormeCarte.CARRE,false));
		listeCarte.add(11,new Carte(Couleur.ROUGE,FormeCarte.ROND,false));
		listeCarte.add(12,new Carte(Couleur.VERT,FormeCarte.TRIANGLE,true));
		listeCarte.add(13,new Carte(Couleur.VERT,FormeCarte.CARRE,true));
		listeCarte.add(14,new Carte(Couleur.VERT,FormeCarte.ROND,true));
		listeCarte.add(15,new Carte(Couleur.VERT,FormeCarte.TRIANGLE,false));
		listeCarte.add(16,new Carte(Couleur.VERT,FormeCarte.CARRE,false));
		listeCarte.add(17,new Carte(Couleur.VERT,FormeCarte.ROND,false));
		
	}

}
