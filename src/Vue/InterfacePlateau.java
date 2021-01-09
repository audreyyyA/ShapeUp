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
import Modèle.Carte;
import Modèle.Couleur;
import Modèle.FormeCarte;
import Modèle.Joueur;
import Modèle.MainJoueur;
import Modèle.Manche;

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
	private int width, height;
	private ControllerPlateau controller;
	private JPanel mainJoueur,deplacer;
	private Reader reader;
	private Thread thread;
	
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

		JPanel Plateau = new JPanel();
		Plateau.setBounds(0, 0, 1295, 758);
		Plateau.setOpaque(false);
		Plateau.setLayout(null);

		height = frame.getHeight();
		width = frame.getWidth();	

		this.drawPlateau(50, Plateau);

		JPanel panel = new JPanel();
		Plateau.add(panel);

		JPanel carte = new Triangle(0,0,60,new Color(0,91,171));
		carte.setBounds(15,20,70,60);
		carte.setOpaque(false);
		remplissagePlateau.get(0).get(0).add(carte);

		frame.getContentPane().add(Plateau);
		
		JLabel pioche_1 = new JLabel("");
		pioche_1.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/pioche.png")));
		pioche_1.setBounds(240, 281, 137, 190);
		Plateau.add(pioche_1);
		
		JLabel turn = new JLabel("  Tour 0 - Name");
		turn.setBounds(0, 226, 390, 36);
		Plateau.add(turn);
		turn.setForeground(Color.WHITE);
		turn.setBackground(new Color(122, 20, 12));
		turn.setOpaque(true);
		turn.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		
		JLabel regle = new JLabel("");
		regle.setBounds(10, 11, 410, 195);
		Plateau.add(regle);
		regle.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/regle.png")));
		
		JLabel pioche = new JLabel("");
		pioche.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/pioche.png")));
		pioche.setBounds(50, 281, 137, 190);
		Plateau.add(pioche);
		
		this.mainJoueur = new JPanel();
		this.mainJoueur.setBounds(10, 534, 410, 200);
		this.mainJoueur.setOpaque(false);
		Plateau.add(this.mainJoueur);
		this.mainJoueur.setLayout(null);
		
		this.deplacer = new JPanel();
		this.deplacer.setBounds(10, 478, 410, 49);
		this.deplacer.setBackground(new Color(32,32,32,200));
		this.deplacer.setVisible(false);

		Plateau.add(this.deplacer);
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
		
		this.controller = new ControllerPlateau(remplissagePlateau, turn,valide,refuse,deplacer);
	}

	public void drawMain(ArrayList<Carte> mainJoueur,int num) {
		int nbCarte = mainJoueur.size();
		int compteur = 0;
		int start = (410 - mainJoueur.size()*130)/2;
		
		
		for(Carte c : mainJoueur) {
			System.out.print(c+",");
			
			JPanel carte = new JPanel();
			carte.setBounds(start + compteur*130,5,130,190);
			carte.setBorder(BorderFactory.createLineBorder(Color.black));
			carte.setBackground(new Color(255, 245, 208));
			carte.setOpaque(true);
			carte.setLayout(null);
		
			JPanel content = createContent(c);
			content.setBounds(15,45,100,100);
			content.setOpaque(false);
			carte.add(content);
			carte.repaint();
	
			this.mainJoueur.add(carte);
			compteur ++;
		}
		
		
		/*
		for(JPanel carte : mainsJoueur) {
			JPanel content = createContent(mainJoueur.get(nbCarte));
			content.setBounds(0,0,100,160);
			content.setOpaque(true);
			content.setBackground(Color.red);
			carte.add(content);
			nbCarte ++;
		}
		*/
		
	}
	
	
	public JPanel createContent(Carte c) {
		if(c.getForme() == FormeCarte.ROND) {
			if(c.getCouleur() == Couleur.BLEU) {
				return new Cercle(0,0,50,Color.blue);
			}
			else if(c.getCouleur() == Couleur.ROUGE) {
				return new Cercle(0,0,50,Color.red);
			}
			else if(c.getCouleur() == Couleur.VERT) {
				return new Cercle(0,0,50,Color.red);
			}
		}
		
		else if(c.getForme() == FormeCarte.TRIANGLE) {
			if(c.getCouleur() == Couleur.BLEU) {
				return new Triangle(0,0,100,Color.BLUE);
			}
			else if(c.getCouleur() == Couleur.ROUGE) {
				return new Triangle(0,0,100,Color.RED);
			}
			else if(c.getCouleur() == Couleur.VERT) {
				return new Triangle(0,0,100,Color.GREEN);
			}
		}
		
		else if(c.getForme() == FormeCarte.CARRE) {
			if(c.getCouleur() == Couleur.BLEU) {
				return new Carre(0,0,100, Color.red);
			}
			else if(c.getCouleur() == Couleur.ROUGE) {
				return new Carre(0,0,100,Color.red);
			}
			else if(c.getCouleur() == Couleur.VERT) {
				return new Carre(0,0,100,Color.red);
			}
		}
		
		return null;
	}
	
	public void drawPlateau(int size, JPanel Plateau) {
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

	@Override
	public void update(Observable Obs, Object arg) {
		// TODO Auto-generated method stub
		if(arg == null) {
			System.out.println("Pas d'arguments");
			if(Obs instanceof MainJoueur) {
				this.mainJoueur.removeAll();
				this.drawMain(((MainJoueur) Obs).getCartes(),((MainJoueur) Obs).getNum());
				System.out.println(((MainJoueur) Obs).getNum());
				frame.repaint();
			}
		}
		else {
			if(arg.equals("initialize")) {
				frame.setVisible(true);
			}
			
			else if(Obs instanceof Manche) {
				if(arg instanceof Joueur) {
					this.controller.updateTurn(((Manche) Obs).getNbTour(), ((Joueur) arg).getNom());
				}
			}
			
			
			else if(Obs instanceof Joueur) {
				if(arg.equals("ask")) {
					this.deplacer.setVisible(true);
				}
				else if(arg instanceof Thread) {
					System.out.println("ça rentre ici");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.thread = (Thread) arg;
					this.thread.interrupt();
				}
			}
			
			else if(Obs instanceof ControllerPlateau) {
				if(arg.equals("Thread")) {
					System.out.println("Ca rentre bien la dedans");
					
				}
			}
			
		}

	}
}
