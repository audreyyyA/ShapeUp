package Mod�le;

/**
 * @author ALCARAZ, DUTOUR
 * Super Classe des Strat�gie d'une IA
 * 
 */
public abstract class Strategie {
	
	/**
	 * Determine la mniere de jouer d'une IA 
	 * @param le plateau, le joueur IA, le numero du tour, le visitor pour calculer le nombre de points
	 */
	public abstract void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException;

}
