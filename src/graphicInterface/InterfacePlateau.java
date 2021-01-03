package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.ControllerPlateau;
import jeu.Carte;
import jeu.Manche;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("deprecation")
public class InterfacePlateau implements Observer {

	private JFrame frame;
	private ArrayList<ArrayList<Shapes>> remplissagePlateau = new ArrayList<>();
	private int width, height;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public InterfacePlateau() {
		initialize();
		this.frame.setVisible(true);
		
		
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
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/backgroundPlateau.jpg")));
		background.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(background);
		
		
		new ControllerPlateau(remplissagePlateau);
	}

	public void drawPlateau(int size, JPanel Plateau) {
		int i = 5;
		int x = 407;
		int y = 140;
		int xEcartement = 10;
		int yEcartement = 10;
		
		
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
		
	}
	
	@Override
	public void update(Observable Obs, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg == null) {
			System.out.println("Pas d'arguments");
		}
		else {
			
		}

	}
}
