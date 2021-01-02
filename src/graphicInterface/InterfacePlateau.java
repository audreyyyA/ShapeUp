package graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.ControllerPlateau;
import jeu.Carte;
import jeu.Manche;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("deprecation")
public class InterfacePlateau implements Observer {

	private JFrame frame;
	private ArrayList<ArrayList<JPanel>> remplissagePlateau = new ArrayList<>();
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
		frame.setBounds(0, 0, 1600, 900);
		frame.getContentPane().setLayout(null);
		
		JPanel Plateau = new JPanel();
		Plateau.setBounds(0, 0, 1600, 900);
		Plateau.setOpaque(false);
		Plateau.setLayout(null);
		
		height = frame.getHeight();
		width = frame.getWidth();	
		
		this.drawPlateau(70, Plateau);
		
		JPanel panel = new JPanel();
		Plateau.add(panel);
		
		JPanel carte = new Triangle(0,0,80,new Color(0,91,171));
		carte.setBounds(20,30,80,70);
		carte.setOpaque(false);
		remplissagePlateau.get(0).get(0).add(carte);
		
		frame.getContentPane().add(Plateau);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(InterfacePlateau.class.getResource("/images/background_extended.png")));
		background.setBounds(0, 0, 1600, 900);
		frame.getContentPane().add(background);
		
		JPanel test = remplissagePlateau.get(0).set(1,new Hexagone(0,0,70,Color.blue));
		test.setOpaque(true);
		Plateau.add(test);
		
		new ControllerPlateau(remplissagePlateau);
	}

	public void drawPlateau(int size, JPanel Plateau) {
		int i = 3;
		int x = 590;
		int y = 120;
		int xEcartement = 10;
		int yEcartement = 10;
		
		
		for(int k=0; k<5; k++) {
			ArrayList<JPanel> l = new ArrayList<>();
			int xTemp = x;
			for(int j = 0; j<i; j++) {
				JPanel cases = new Hexagone(0,0,size, new Color(173, 173, 173));
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
