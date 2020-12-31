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

public class InterfacePlateau implements Observer {

	private JFrame frame;
	private ArrayList<JPanel> remplissagePlateau = new ArrayList<>();

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
		frame.setBounds(0, 0, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.drawPlateau(70);
		
		
		new ControllerPlateau(remplissagePlateau);
	}

	public void drawPlateau(int size) {
		int i = 3;
		int x = 400;
		int y = -5;
		for(int k=0; k<5; k++) {
			ArrayList<JPanel> l =new ArrayList<>();
			int xTemp = x;
			for(int j = 0; j<i; j++) {
				JPanel cases = new Hexagone(0,0,size);
				cases.setBounds(xTemp, y, size*2, (int) (size*2 + size*Math.cos(Math.toRadians(30))));
				cases.setOpaque(false);
				frame.getContentPane().add(cases);
				frame.repaint();
				xTemp += size*2;
				l.add(cases);
			}
			
			this.remplissagePlateau.addAll(l);
	
			if(k>1) {
				i-=1;
				x+= size;
				y+= size*2-20;
			}
			else {
				x-= size;
				y+= size*2-20;
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
