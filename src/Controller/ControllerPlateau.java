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

	private ArrayList<ArrayList<JPanel>> remplissagePlateau;
	
	public ControllerPlateau(ArrayList<ArrayList<JPanel>> remplissagePlateau2) {
		
		this.remplissagePlateau=remplissagePlateau2;
		initializeHandler();
	}
	
	public void initializeHandler() {
		
		
	}
	
}
