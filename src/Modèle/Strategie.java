package Modèle;

/**
 * Super Classe des Stratégie d'une IA
 * @author ALCARAZ, DUTOUR
 */
public abstract class Strategie {
	
	/**
	 * Determine la mniere de jouer d'une IA 
	 * @param le plateau, le joueur IA, le numero du tour, le visitor pour calculer le nombre de points
	 */
	public abstract void Algorithme(Plateau p, Joueur j, int tour, IVisitor visitor) throws CloneNotSupportedException;

}
