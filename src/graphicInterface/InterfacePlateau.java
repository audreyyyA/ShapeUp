package graphicInterface;

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
		frame.getContentPane().setLayout(null);
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(0, 0, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		height = frame.getHeight();
		width = frame.getWidth();	
		
		this.drawPlateau(70);
		frame.repaint();
		
		JPanel carte = new Triangle(0,0,80,Color.blue);
		carte.setBounds(30,30,80,80);
		
		remplissagePlateau.get(0).get(1).setLayout(null);
		remplissagePlateau.get(0).get(1).add(carte);
		
		new ControllerPlateau(remplissagePlateau);
	}

	public void drawPlateau(int size) {
		int i = 3;
		int x = 415;
		int y = 45;
		int xEcartement = 10;
		int yEcartement = 10;
		
		for(int k=0; k<5; k++) {
			ArrayList<JPanel> l = new ArrayList<>();
			int xTemp = x;
			for(int j = 0; j<i; j++) {
				JPanel cases = new Hexagone(0,0,size);
				cases.setBounds(xTemp, y, (int)(size*2*Math.cos(Math.toRadians(30))), size*2);
				cases.setOpaque(false);
				frame.getContentPane().add(cases);
				frame.repaint();
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
		System.out.print(remplissagePlateau);
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
