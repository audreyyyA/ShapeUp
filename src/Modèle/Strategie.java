package Mod�le;

/**
 * Super Classe des Strat�gie d'une IA
 * @author ALCARAZ, DUTOUR
 */
public abstract class Strategie {
	
	public abstract void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException;

}
