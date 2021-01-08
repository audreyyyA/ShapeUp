package Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphicInterface.Accueil;
import graphicInterface.Shapes;
import jeu.FormePlateau;

public class ControllerPlateau {

	private ArrayList<ArrayList<Shapes>> remplissagePlateau;
	private JLabel turn;
	
	public ControllerPlateau(ArrayList<ArrayList<Shapes>> remplissagePlateau2, JLabel turn) {
		
		this.turn = turn;
		this.remplissagePlateau=remplissagePlateau2;
		initializeHandler();
	}

	public void initializeHandler() {
		for(ArrayList<Shapes> substring : remplissagePlateau) {
			for(Shapes cases : substring) {
				cases.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						cases.changeColor(Color.red);
					}
				});
			}
		}
	}
	
	public void updateTurn(int tour, String name) {
		turn.setText("  Tour "+tour+" - "+name);
	}
	
}
