package graphicInterface;

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

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.ControllerPlateau;
import jeu.Carte;
import jeu.Couleur;
import jeu.FormeCarte;
import jeu.Manche;
import joueur.Joueur;
import joueur.MainJoueur;

import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.Toolkit;

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
	private ArrayList<JPanel> mainsJoueur = new ArrayList<>(); 
	
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
		
		this.controller = new ControllerPlateau(remplissagePlateau, turn);
		
		JLabel regle = new JLabel("");
		regle.setBounds(10, 11, 410, 195);
		Plateau.add(regle);
		regle.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/regle.png")));
		
		JLabel pioche = new JLabel("");
		pioche.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/pioche.png")));
		pioche.setBounds(50, 281, 137, 190);
		Plateau.add(pioche);
		
		JPanel mainJoueur1 = new JPanel();
		mainJoueur1.setBounds(10, 534, 410, 200);
		mainJoueur1.setOpaque(false);
		Plateau.add(mainJoueur1);
		mainJoueur1.setVisible(false);
		mainJoueur1.setLayout(null);
		mainsJoueur.add(mainJoueur1);
		
		JPanel mainJoueur2 = new JPanel();
		mainJoueur2.setBounds(10, 534, 410, 200);
		mainJoueur2.setOpaque(false);
		Plateau.add(mainJoueur2);
		mainJoueur2.setLayout(null);
		mainJoueur2.setVisible(false);
		mainsJoueur.add(mainJoueur2);
		
		JPanel mainJoueur3 = new JPanel();
		mainJoueur3.setBounds(10, 534, 410, 200);
		mainJoueur3.setOpaque(false);
		Plateau.add(mainJoueur3);
		mainJoueur3.setVisible(false);
		mainJoueur3.setLayout(null);
		mainsJoueur.add(mainJoueur3);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/backgroundPlateau.jpg")));
		background.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(background);
		
	}

	public void drawMain(ArrayList<Carte> mainJoueur,int num) {
		int nbCarte = mainJoueur.size();
		int compteur = 0;
		int start = (410 - mainJoueur.size()*130)/2;
		System.out.print(mainsJoueur);
		this.mainsJoueur.get(num).removeAll();
		
		for(JPanel main : mainsJoueur) {
			main.setVisible(false);
		}
		
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
			
			this.mainsJoueur.get(num).add(carte);
			compteur ++;
		}
		
		System.out.println(this.mainsJoueur);
		
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
		
		System.out.print("ça marche pas");
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
		System.out.print(arg);
		if(arg == null) {
			System.out.println("Pas d'arguments");
			if(Obs instanceof MainJoueur) {
				System.out.println("modification de la main");
				this.drawMain(((MainJoueur) Obs).getCartes(),((MainJoueur) Obs).getNum());
				System.out.println(((MainJoueur) Obs).getNum());
				for (JPanel j : mainsJoueur) {
					j.setVisible(false);
				}
				this.mainsJoueur.get(((MainJoueur) Obs).getNum()).setVisible(true);
				frame.repaint();
			}
		}
		else {
			if(arg.equals("initialize")) {
				frame.setVisible(true);
			}
			else if(Obs instanceof Manche) {
				if(arg instanceof Joueur) {
					for(int i=0; i<this.mainsJoueur.size(); i++) {
						this.mainsJoueur.get(i).setVisible(false);
					}
					this.mainsJoueur.get(((Joueur) arg).getNum()).setVisible(true);
					this.controller.updateTurn(((Manche) Obs).getNbTour(), ((Joueur) arg).getNom());
				}
			}
			
		}

	}
}
