package joueur;

public class JoueurVirtuel extends Joueur{
	
	private Strategie strategie;
	
	public JoueurVirtuel(String nom, boolean v, Strategie strat) {
		
		super(nom);
		this.strategie = strat;
	}
	
	

}
