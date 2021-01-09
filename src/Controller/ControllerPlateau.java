package Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modèle.FormePlateau;
import Vue.Accueil;
import Vue.Shapes;

public class ControllerPlateau extends Observable{

	private ArrayList<ArrayList<Shapes>> remplissagePlateau;
	private JLabel turn,valide,refuse;
	JPanel deplacer;
	
	private void NotifyObs() {
		this.setChanged();
		this.notifyObservers("Thread");
	}
	
	public ControllerPlateau(ArrayList<ArrayList<Shapes>> remplissagePlateau2, JLabel turn,JLabel valide,JLabel refuse,JPanel deplacer) {
		
		this.turn = turn;
		this.remplissagePlateau=remplissagePlateau2;
		this.refuse = refuse;
		this.valide = valide;
		this.deplacer = deplacer;
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
		
		this.valide.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				NotifyObs();
			}
			public void mouseEntered(MouseEvent e) {
				valide.setOpaque(true);
				valide.repaint();
			}
			
			public void mouseExited(MouseEvent e) {
				valide.setOpaque(false);
				valide.repaint();
			}
		});
		
		this.refuse.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
			public void mouseEntered(MouseEvent e) {
				refuse.setOpaque(true);
				refuse.repaint();
			}
			
			public void mouseExited(MouseEvent e) {
				refuse.setOpaque(false);
				refuse.repaint();
			}
		});
	}
	
	public void updateTurn(int tour, String name) {
		turn.setText("  Tour "+tour+" - "+name);
	}
	
}
