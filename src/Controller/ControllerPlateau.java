package Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modèle.Carte;
import Modèle.FormePlateau;
import Modèle.Joueur;
import Vue.Accueil;
import Vue.Shapes;

public class ControllerPlateau extends Observable{

	private ArrayList<ArrayList<Shapes>> remplissagePlateau;
	private JLabel turn,valide,refuse, infoText;
	private Thread thread;
	private Joueur joueur;
	private JPanel carteSelected = null;
	private JPanel deplacer, info;
	private ArrayList<JPanel> cartes;
	private JPanel mainJoueur;

	private void NotifyThread() {
		this.setChanged();
		this.notifyObservers("Thread");
	}

	public void NotifyDeplacer(boolean deplacer) {
		this.setChanged();
		if(deplacer) {
			this.notifyObservers("Deplacer");
		}
		else {
			this.notifyObservers("PasDeplacer");
		}
	}

	public ControllerPlateau(JPanel mainJoueur, JPanel info, JLabel infoText, Joueur joueur, Thread thread, ArrayList<ArrayList<Shapes>> remplissagePlateau2, JLabel turn,JLabel valide,JLabel refuse,JPanel deplacer) {

		this.turn = turn;
		this.remplissagePlateau=remplissagePlateau2;
		this.refuse = refuse;
		this.valide = valide;
		this.thread = thread;
		this.joueur = joueur;
		this.deplacer = deplacer;
		this.infoText = infoText;
		this.info = info;
		this.mainJoueur = mainJoueur;
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
				deplacer.setVisible(false);
				System.out.print("O\n");
				NotifyDeplacer(true);
				NotifyThread();		
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
				deplacer.setVisible(false);
				System.out.print("N\n");
				NotifyDeplacer(false);
				NotifyThread();
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

	public void cardHandler(ArrayList<JPanel> cartes) {
		for(JPanel carte : cartes) {
			carte.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for(JPanel carte : cartes) {
						carte.setBorder(BorderFactory.createLineBorder(Color.black));
						carte.setBounds(carte.getX(),15,carte.getWidth(),190);
					}
					if(carteSelected == null) {
						carteSelected = carte;
						carte.setBounds(carte.getX(), carte.getY()-15, carte.getWidth(), carte.getHeight()+15);
						carte.setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(78,168,50)));
					}
					else if(carteSelected.equals(carte)) {
						carteSelected = null;
					}
					else {
						carteSelected = carte;
						carte.setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(78,168,50)));
						carte.setBounds(carte.getX(), carte.getY()-15, carte.getWidth(), carte.getHeight()+15);
					}
				}
				public void mouseEntered(MouseEvent e) {
					if(!(carte.equals(carteSelected))) {
						carte.setBounds(carte.getX(), carte.getY()-15, carte.getWidth(), carte.getHeight()+15);
					}	
				}

				public void mouseExited(MouseEvent e) {
					if(!(carte.equals(carteSelected))) {
						carte.setBounds(carte.getX(), carte.getY()+15, carte.getWidth(), carte.getHeight()-15);
					}
				}
			});
			
		}
	}

	public void updateTurn(int tour, String name) {
		turn.setText("  Tour "+tour+" - "+name);
	}

}
