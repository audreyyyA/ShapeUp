package Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import graphicInterface.Accueil;
import jeu.FormePlateau;

public class ControllerPlateau {

	private ArrayList<JPanel> remplissagePlateau;
	
	public ControllerPlateau(ArrayList<JPanel> remplissagePlateau) {
		
		this.remplissagePlateau=remplissagePlateau;
		initializeHandler();
	}
	
	public void initializeHandler() {
		
		for(JPanel panel : remplissagePlateau) {
			panel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					
				}
			});
		}
		
	}
	
}
