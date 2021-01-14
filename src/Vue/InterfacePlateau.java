package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.ControllerPlateau;
import Mod�le.Carte;
import Mod�le.Couleur;
import Mod�le.FormeCarte;
import Mod�le.Joueur;
import Mod�le.JoueurReel;
import Mod�le.MainJoueur;
import Mod�le.Manche;
import Mod�le.Plateau;
import Mod�le.PlateauRectangle;

import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.Reader;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("deprecation")
public class InterfacePlateau implements Observer {

	private JFrame frame;
	private ArrayList<ArrayList<Shapes>> remplissagePlateau = new ArrayList<>();
	private int tour,x,y;
	private boolean first = true;
	private boolean deplacement = false;
	private ControllerPlateau controller;
	private JPanel mainJoueur,deplacer, info, plateauPanel,plateauCarte;
	private JLabel infoText;
	private JoueurReel joueur;
	private Thread thread;
	private ArrayList<JPanel> cartes = new ArrayList<>();
	private Plateau plateau;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public InterfacePlateau() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Shape Up");
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(0, 0, 1295, 758);
		frame.getContentPane().setLayout(null);

		this.plateauPanel = new JPanel();
		this.plateauPanel.setBounds(0, 0, 1295, 758);
		this.plateauPanel.setOpaque(false);
		this.plateauPanel.setLayout(null);

		this.plateauCarte = new JPanel();
		this.plateauCarte.setBounds(0, 0, 1295, 758);
		this.plateauCarte.setOpaque(false);
		this.plateauPanel.add(plateauCarte);
		this.plateauCarte.setLayout(null);

		this.drawPlateau(50, plateauCarte,null);


		frame.getContentPane().add(plateauPanel);

		this.info = new JPanel();
		this.info.setBounds(10, 463, 410, 49);
		this.info.setBackground(new Color(32,32,32,200));
		this.info.setVisible(false);
		plateauPanel.add(this.info);
		this.info.setLayout(null);

		this.infoText = new JLabel("Cliquez sur la carte que vous voulez poser");
		this.infoText.setBounds(0, 0, 410, 49);
		this.infoText.setHorizontalAlignment(SwingConstants.CENTER);
		this.infoText.setForeground(Color.WHITE);
		this.infoText.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		this.infoText.setHorizontalAlignment(SwingConstants.CENTER);
		this.info.add(this.infoText);

		JLabel pioche_1 = new JLabel("");
		pioche_1.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/pioche.png")));
		pioche_1.setBounds(240, 265, 137, 190);
		plateauPanel.add(pioche_1);

		JLabel turn = new JLabel("  Tour 0 - Name");
		turn.setBounds(0, 216, 390, 36);
		plateauPanel.add(turn);
		turn.setForeground(Color.WHITE);
		turn.setBackground(new Color(122, 20, 12));
		turn.setOpaque(true);
		turn.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));

		JLabel regle = new JLabel("");
		regle.setBounds(10, 11, 410, 195);
		plateauPanel.add(regle);
		regle.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/regle.png")));

		JLabel pioche = new JLabel("");
		pioche.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/pioche.png")));
		pioche.setBounds(50, 265, 137, 190);
		plateauPanel.add(pioche);

		this.mainJoueur = new JPanel();
		this.mainJoueur.setBounds(10, 514, 410, 220);
		this.mainJoueur.setOpaque(false);
		plateauPanel.add(this.mainJoueur);
		this.mainJoueur.setLayout(null);

		this.deplacer = new JPanel();
		this.deplacer.setBounds(10, 463, 410, 49);
		this.deplacer.setBackground(new Color(32,32,32,200));
		this.deplacer.setVisible(false);

		plateauPanel.add(this.deplacer);
		this.deplacer.setLayout(null);

		JLabel askDeplacer = new JLabel("Voulez-vous d\u00E9placer une carte ? ");
		askDeplacer.setBounds(3, 11, 301, 24);
		askDeplacer.setForeground(Color.WHITE);
		askDeplacer.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		askDeplacer.setHorizontalAlignment(SwingConstants.CENTER);
		deplacer.add(askDeplacer);

		JLabel valide = new JLabel("");
		valide.setBackground(new Color(112,173,71));
		valide.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/valide.png")));
		valide.setBounds(309, 4, 38, 38);
		deplacer.add(valide);

		JLabel refuse = new JLabel("");
		refuse.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/refuse.png")));
		refuse.setBounds(355, 4, 38, 38);
		refuse.setBackground(new Color(192,0,0));
		deplacer.add(refuse);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/backgroundPlateau.jpg")));
		background.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(background);



		this.controller = new ControllerPlateau(this.mainJoueur,this.info,infoText,this.joueur,this.thread, remplissagePlateau, turn,valide,refuse,deplacer);
		this.controller.addObserver(this);
	}

	public void drawMain(ArrayList<Carte> mainJoueur,int num) {
		System.out.println(mainJoueur);
		for(int i=0; i<mainJoueur.size();i++) {
			if(mainJoueur.get(i) == null) {
				mainJoueur.remove(i);
			}
		}
		System.out.println(mainJoueur);
		int nbCarte = mainJoueur.size();
		int compteur = 0;
		int start = (410 - mainJoueur.size()*130)/2;


		for(Carte c : mainJoueur) {
			
			JPanel carte = new JPanel();
			carte.setBounds(start + compteur*130,15,130,190);
			carte.setBorder(BorderFactory.createLineBorder(Color.black));
			carte.setBackground(new Color(255, 245, 208));
			carte.setOpaque(true);
			carte.setLayout(null);

			JPanel content = CarteToJPanel(c,100);
			content.setBounds(10,45,110,110);
			content.setOpaque(false);
			carte.add(content);
			carte.repaint();

			this.mainJoueur.add(carte);
			this.cartes.add(carte);
			compteur ++;
		}
		this.controller.cardHandler(cartes);
	}


	public JPanel CarteToJPanel(Carte c,int size) {
		if(c.getForme() == FormeCarte.ROND) {
			if(c.isRempli()) {
				if(c.getCouleur() == Couleur.BLEU) {
					return new Shapes(0,0,size,new Color(0, 130, 255),false,Formes.CERCLE);
				}
				else if(c.getCouleur() == Couleur.ROUGE) {
					return new Shapes(0,0,size,new Color(217, 11, 0),false,Formes.CERCLE);
				}
				else if(c.getCouleur() == Couleur.VERT) {
					return new Shapes(0,0,size,new Color(0, 168, 39),false,Formes.CERCLE);
				}
			}
			else {
				if(c.getCouleur() == Couleur.BLEU) {
					return new Shapes(0,0,size,new Color(0, 130, 255),true,Formes.CERCLE);
				}
				else if(c.getCouleur() == Couleur.ROUGE) {
					return new Shapes(0,0,size,new Color(217, 11, 0),true,Formes.CERCLE);
				}
				else if(c.getCouleur() == Couleur.VERT) {
					return new Shapes(0,0,size,new Color(0, 168, 39),true,Formes.CERCLE);
				}
			}
		}

		else if(c.getForme() == FormeCarte.TRIANGLE) {
			if(c.isRempli()) {
				if(c.getCouleur() == Couleur.BLEU) {
					return new Shapes(0,0,size,new Color(0, 130, 255),false,Formes.TRIANGLE);
				}
				else if(c.getCouleur() == Couleur.ROUGE) {
					return new Shapes(0,0,size,new Color(217, 11, 0),false,Formes.TRIANGLE);
				}
				else if(c.getCouleur() == Couleur.VERT) {
					return new Shapes(0,0,size,new Color(0, 168, 39),false,Formes.TRIANGLE);
				}
			}
			else {
				if(c.getCouleur() == Couleur.BLEU) {
					return new Shapes(0,0,size,new Color(0, 130, 255),true,Formes.TRIANGLE);
				}
				else if(c.getCouleur() == Couleur.ROUGE) {
					return new Shapes(0,0,size,new Color(217, 11, 0),true,Formes.TRIANGLE);
				}
				else if(c.getCouleur() == Couleur.VERT) {
					return new Shapes(0,0,size,new Color(0, 168, 39),true,Formes.TRIANGLE);
				}
			}

		}

		else if(c.getForme() == FormeCarte.CARRE) {
			if(c.isRempli()) {
				if(c.getCouleur() == Couleur.BLEU) {
					return new Shapes(0,0,size,new Color(0, 130, 255),false,Formes.CARRE);
				}
				else if(c.getCouleur() == Couleur.ROUGE) {
					return new Shapes(0,0,size,new Color(217, 11, 0),false,Formes.CARRE);
				}
				else if(c.getCouleur() == Couleur.VERT) {
					return new Shapes(0,0,size,new Color(0, 168, 39),false,Formes.CARRE);
				}
			}
			else {
				if(c.getCouleur() == Couleur.BLEU) {
					return new Shapes(0,0,size,new Color(0, 130, 255),true,Formes.CARRE);
				}
				else if(c.getCouleur() == Couleur.ROUGE) {
					return new Shapes(0,0,size,new Color(217, 11, 0),true,Formes.CARRE);
				}
				else if(c.getCouleur() == Couleur.VERT) {
					return new Shapes(0,0,size,new Color(0, 168, 39),true,Formes.CARRE);
				}
			}
		}

		return null;
	}

	public void drawPlateau(int size, JPanel Plateau, ArrayList<ArrayList<Carte>> remplissage) {
		int i = 5;
		int x = 659;
		int y = 60;
		int xEcartement = 10;
		int yEcartement = 10;

		ArrayList<Shapes> temp = new ArrayList<>();
		for(int l=0;l<3;l++) {
			Shapes cases;
			cases = new Shapes(0,0,size, new Color(173, 173, 173),true, Formes.HEXAGONE);
			cases.setBounds(x, y, (int)(size*2*Math.cos(Math.toRadians(30))), size*2);
			cases.setOpaque(false);
			cases.setLayout(null);
			Plateau.add(cases);
			Plateau.repaint();
			x += (int) (size*2*Math.cos(Math.toRadians(30))) +xEcartement;
			temp.add(cases);
		}

		x = 612;
		y+= size*2 - (int) size*Math.sin(Math.toRadians(30)) + yEcartement;

		this.remplissagePlateau.add(temp);

		for(int k=0; k<5; k++) {
			ArrayList<Shapes> l = new ArrayList<>();
			int xTemp = x;
			for(int j = 0; j<i; j++) {
				Shapes cases;
				if(j ==0 || j==i-1) {
					cases = new Shapes(0,0,size, new Color(173, 173, 173),true, Formes.HEXAGONE);
				}
				else {
					cases = new Shapes(0,0,size, new Color(173, 173, 173),false, Formes.HEXAGONE);
				}
				cases.setBounds(xTemp, y, (int)(size*2*Math.cos(Math.toRadians(30))), size*2);
				cases.setOpaque(false);
				cases.setLayout(null);
				Plateau.add(cases);
				Plateau.repaint();
				xTemp += (int) (size*2*Math.cos(Math.toRadians(30))) +xEcartement;
				l.add(cases);
			}

			this.remplissagePlateau.add(l);

			if(k>1) {
				i-=1;
				x+= size*Math.cos(Math.toRadians(30))+ xEcartement/2;
				y+= size*2 - (int) size*Math.sin(Math.toRadians(30)) + yEcartement;
			}
			else {
				x-= size*Math.cos(Math.toRadians(30)) + xEcartement/2;
				y+= size*2 - (int) size*Math.sin(Math.toRadians(30)) + yEcartement;
				i+=1;

			}
		}

		x += (int) (size*2*Math.cos(Math.toRadians(30))) +xEcartement;

		for(int l=0;l<3;l++) {
			Shapes cases;
			cases = new Shapes(0,0,size, new Color(173, 173, 173),true, Formes.HEXAGONE);
			cases.setBounds(x, y, (int)(size*2*Math.cos(Math.toRadians(30))), size*2);
			cases.setOpaque(false);
			cases.setLayout(null);
			Plateau.add(cases);
			Plateau.repaint();
			x += (int) (size*2*Math.cos(Math.toRadians(30))) +xEcartement;
			temp.add(cases);
		}


	}


	public void updatePlateau(ArrayList<ArrayList<Carte>> remplissageCarte) {
		for(ArrayList<Shapes> subList : remplissagePlateau) {
			for(Shapes cases : subList) {
				cases.setLayout(null);
				int x=subList.indexOf(cases);
				int y= remplissagePlateau.indexOf(subList);
				if(!(y == 0 || y == remplissagePlateau.size() || x ==0 || x == subList.size()-1)) {
					if(remplissageCarte.get(y-1).get(x-1) != null) {
						JPanel carte = this.CarteToJPanel(remplissageCarte.get(y-1).get(x-1), 50);
						carte.setBounds(13, 20, 70, 70);
						carte.setOpaque(false);
						cases.add(carte);
						cases.repaint();
					}
					else {
						cases.removeAll();
					}
				}
			}
		}
		this.plateauCarte.repaint();
	}

	@Override
	public void update(Observable Obs, Object arg) {

		// TODO Auto-generated method stub
		if(arg == null) {
			if(Obs instanceof MainJoueur) {
				this.mainJoueur.removeAll();
				this.cartes.clear();
				System.out.println("La main change");
				System.out.println(((MainJoueur) Obs).getCartes());
				this.drawMain(((MainJoueur) Obs).getCartes(),((MainJoueur) Obs).getNum());
				frame.repaint();
			}

			else if(Obs instanceof Plateau) {
				this.plateau = (Plateau) Obs;
				System.out.println("Modification du plateau");
				this.updatePlateau(this.plateau.getRemplissage());
			}
		}

		else {
			if(arg.equals("initialize")) {
				frame.setVisible(true);
			}

			else if(Obs instanceof Manche) {
				if(arg instanceof Joueur) {
					this.tour = ((Manche) Obs).getNbTour();
					this.controller.updateTurn(((Manche) Obs).getNbTour(), ((Joueur) arg).getNom());
				}
			}



			else if(Obs instanceof JoueurReel) {
				this.joueur = (JoueurReel) Obs; 
				if(arg.equals("ask")) {
					this.deplacer.setVisible(true);		
					this.info.setVisible(false);
				}
				else if(arg instanceof Thread) {
					this.thread = (Thread) arg; 
				}

				else if(arg.equals("Pose")){
					this.infoText.setText("Cliquez sur la carte que vous voulez poser");
					this.info.setVisible(true);
				}

				else if(arg.equals("ChoixCarteDeplacer")) {
					this.infoText.setText("Cliquez sur la carte que vous voulez d�placer");
					this.info.setVisible(true);
					this.deplacement = false;
					this.controller.setDeplacement(true);
				}
				
				else if(arg.equals("EndDeplacement")) {
					this.joueur.setChoixPosDep(false);
					this.joueur.setDeplacer(false);
					this.controller.setDeplacement(false);
					this.controller.setCarteDepSelected(false);
					this.deplacement = false;
					this.info.setVisible(false);
					this.joueur.setyCarte(-1);
				}
			}

			else if(Obs instanceof ControllerPlateau) {
				if(arg.equals("Thread")) {
					this.thread.interrupt();
					this.joueur.setVueTexte(new VueTexte());
				}
				else if(arg.equals("Deplacer")) {
					this.joueur.setDeplacer(true);
					this.infoText.setText("Cliquez sur la carte que vous voulez d�placer");
					this.info.setVisible(true);
					this.deplacement = false;
					this.controller.setDeplacement(true);
				}
				else if(arg.equals("PasDeplacer")) {
					this.joueur.setDeplacer(false);
				}

				else if(arg instanceof ArrayList) {
					if((int)((ArrayList) arg).get(0) == 0) {
						int x = (int) ((ArrayList) arg).get(1);
						int y = (int) ((ArrayList) arg).get(2);
						if(!first) {
							if(this.plateau.checkPose(x,y)) {
								this.remplissagePlateau.get(y+1).get(x+1).changeColor(new Color(112,173,71));
							}
							else{
								this.remplissagePlateau.get(y+1).get(x+1).changeColor(new Color(255, 86, 86));
							};
						}
						else {
							if(!(x == -1 || y ==-1 || y == this.remplissagePlateau.size() || x == this.remplissagePlateau.get(y+1).size() -2)) {
								this.remplissagePlateau.get(y+1).get(x+1).changeColor(new Color(112,173,71));
							}
							else {
								this.remplissagePlateau.get(y+1).get(x+1).changeColor(new Color(255, 86, 86));
							}
						}
					}

					else if((int)((ArrayList) arg).get(0) == 1) {

						this.x = (int) ((ArrayList) arg).get(1);
						this.y = (int) ((ArrayList) arg).get(2);
						System.out.println(this.controller.getIndex());
						System.out.println("Abcisse de pose: "+x);
						System.out.println("Ordonn�e de pose: "+y);
						if(!first) {
							if(this.plateau.checkPose(this.x,this.y)) {
								this.thread.interrupt();
								this.joueur.setPose(true);
								this.joueur.setVueTexte(new VueTexte());
								this.joueur.getVueTexte().setIndexCarte(this.controller.getIndex());
								this.thread = this.joueur.getVueTexte().getThread();
								this.joueur.setxPose(this.x);
								this.joueur.setyPose(this.y);
								this.info.setVisible(false);
							}
						}
						else {
							this.thread.interrupt();
							this.joueur.setPose(true);
							this.first = false;
							this.joueur.setVueTexte(new VueTexte());
							this.joueur.getVueTexte().setIndexCarte(this.controller.getIndex());
							this.thread = this.joueur.getVueTexte().getThread();
							this.joueur.setxPose(this.x);
							this.joueur.setyPose(this.y);
							this.info.setVisible(false);
						}
					}

					else if((int)((ArrayList) arg).get(0) == 2) {
						int x = (int) ((ArrayList) arg).get(1);
						int y = (int) ((ArrayList) arg).get(2);
						if(!(x == -1 || y ==-1 || y == this.remplissagePlateau.size() || x == this.remplissagePlateau.get(y+1).size() -2)) {
							if((this.plateau.getRemplissage().get(y).get(x) != null)) {
								this.remplissagePlateau.get(y+1).get(x+1).changeColor(new Color(112,173,71));
							}
						}
					}

					else if((int)((ArrayList) arg).get(0) == 3) {
						this.x = (int) ((ArrayList) arg).get(1);
						this.y = (int) ((ArrayList) arg).get(2);
						if(!(x == -1 || y ==-1 || y == this.remplissagePlateau.size() || x == this.remplissagePlateau.get(y+1).size() -2)) {
							if((this.plateau.getRemplissage().get(y).get(x) != null)) {
								System.out.println(x);
								System.out.println("Ordonn�e de la carte � deplacer : "+y);
								this.thread.interrupt();
								this.joueur.setVueTexte(new VueTexte());
								this.thread = this.joueur.getVueTexte().getThread();
								this.joueur.setxCarte(this.x);
								this.joueur.setyCarte(this.y);
								this.controller.setDeplacement(false);
								this.controller.setCarteDepSelected(true);
							}
						}
					}

					else if((int)((ArrayList) arg).get(0) == 4) {
						this.x = (int) ((ArrayList) arg).get(1);
						this.y = (int) ((ArrayList) arg).get(2);
						System.out.println(x);
						System.out.println("Ordonn�e de pose: "+y);
						if(this.plateau.checkPose(this.x,this.y)) {
							System.out.println("On passe ici");
							this.thread.interrupt();
							this.joueur.setChoixPosDep(true);
							this.joueur.setxDep(this.x);
							this.joueur.setyDep(this.y);
							this.joueur.setVueTexte(new VueTexte());
							this.thread = this.joueur.getVueTexte().getThread();
						}
					}
				}
			}
		}

	}
}
