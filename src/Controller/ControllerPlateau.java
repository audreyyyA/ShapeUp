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
import Modèle.Plateau;
import Vue.Accueil;
import Vue.Shapes;

/**
 * @author ALCARAZ, DUTOUR
 * Représente le controleur dans le modèle MVC faisant le lien entre les classes du modèle et celles des vues du plateau
 * 
 */
public class ControllerPlateau extends Observable{

	private ArrayList<ArrayList<Shapes>> remplissagePlateau;
	private JLabel turn,valide,refuse, infoText,carteVictoireDos;
	private Thread thread;
	private Joueur joueur;
	private JPanel carteSelected = null;
	private JPanel deplacer, info;
	private ArrayList<JPanel> cartes;
	private JPanel mainJoueur,carteVictoireRecto;
	private Plateau plateau;
	private boolean deplacement,carteDepSelected;
	private int index;
	private FormePlateau forme;
	
	public boolean isCarteDepSelected() {
		return carteDepSelected;
	}

	public void setCarteDepSelected(boolean carteDepSelected) {
		this.carteDepSelected = carteDepSelected;
	}

	public boolean isDeplacement() {
		return deplacement;
	}

	public void setDeplacement(boolean deplacement) {
		this.deplacement = deplacement;
	}

	/**
	 * Notifie l'observer d'arrêter le thread
	 */
	private void NotifyThread() {
		this.setChanged();
		this.notifyObservers("Thread");
	}

	/**
	 * Notifie et effectue les changements nécessaires lorsqu'une carte va être déplacée
	 * @param l'abscisse et  l'ordonnée pour déplacer la carte
	 */
	private void NotifyChoixCarteDeplacer(int x,int y) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(3);
		l.add(x);
		l.add(y);
		this.setChanged();
		this.notifyObservers(l);
	}
	
	/**
	 * Notifie et effectue les changements nécessaires lorsqu'une carte va être placée
	 * @param l'abscisse et  l'ordonnée pour placer la carte
	 */
	private void NotifyChoixPlacement(int x,int y) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(4);
		l.add(x);
		l.add(y);
		this.setChanged();
		this.notifyObservers(l);
	}
	
	/**
	 * Notifie l'observer d'afficher ou cacher la carte victoire
	 */
	private void NotifySeeCV() {
		this.setChanged();
		this.notifyObservers("CV");
	}
	
	 /**
	 * Notifie et effectue les changements nécessaires lorsque les emplacements ont été vérifié
	 * @param l'abscisse et  l'ordonnée de l'emplacement
	 */
	private void NotifyCheck(int x,int y) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(0);
		l.add(x);
		l.add(y);
		this.setChanged();
		this.notifyObservers(l);
	}

	/**
	 * Notifie et effectue les changements nécessaires lorsque les emplacements ont été vérifié pour déplacer une carte
	 * @param l'abscisse et  l'ordonnée de l'emplacement
	 */
	private void NotifyCheckDeplacement(int x,int y) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(2);
		l.add(x);
		l.add(y);
		this.setChanged();
		this.notifyObservers(l);
	}
	
	/**
	 * Notifie et effectue les changements nécessaires lorsque les emplacements ont été vérifié pour poser une carte
	 * @param l'abscisse et  l'ordonnée de l'emplacement
	 */
	private void NotifyPose(int x,int y) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(x);
		l.add(y);
		this.setChanged();
		this.notifyObservers(l);
	}

	/**
	 * Notifie et effectue les changements nécessaires lorsqu'une carte va être déplacée
	 * @param boolean si la carte a été déplacée ou pas
	 */
	private void NotifyDeplacer(boolean deplacer) {
		this.setChanged();
		if(deplacer) {
			this.notifyObservers("Deplacer");
		}
		else {
			this.notifyObservers("PasDeplacer");
		}
	}

	public ControllerPlateau(JPanel carteVictoireRecto,JLabel carteVictoireDos,FormePlateau forme, JPanel mainJoueur, JPanel info, JLabel infoText, Joueur joueur, Thread thread, ArrayList<ArrayList<Shapes>> remplissagePlateau2, JLabel turn,JLabel valide,JLabel refuse,JPanel deplacer) {

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
		this.forme = forme;
		this.carteVictoireDos = carteVictoireDos;
		this.carteVictoireRecto = carteVictoireRecto;
		initializeHandler();
	}

	/**
	 * Initialise pour l'interface graphique du plateau
	 */
	public void initializeHandler() {
		for(ArrayList<Shapes> subList : remplissagePlateau) {
			for(Shapes cases : subList) {
				cases.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(carteSelected != null && !deplacement && !carteDepSelected) {
							int y = remplissagePlateau.indexOf(subList)-1;
							int x = subList.indexOf(cases)-1;
							if((y==-1 || y == remplissagePlateau.size()-2)) {
								x ++;
							}
							NotifyPose(x,y);
						}
						else if(deplacement && !carteDepSelected) {
							int y = remplissagePlateau.indexOf(subList)-1;
							int x = subList.indexOf(cases)-1;
							NotifyChoixCarteDeplacer(x,y);
						}
						else if(carteDepSelected) {
							int y = remplissagePlateau.indexOf(subList)-1;
							int x = subList.indexOf(cases)-1;
							NotifyChoixPlacement(x,y);
						}
					}

					public void mouseEntered(MouseEvent e) {
						int y = remplissagePlateau.indexOf(subList)-1;
						int x = subList.indexOf(cases)-1;
						if(y==-1 || y == remplissagePlateau.size()-2) {
							x ++;
						}
						if(deplacement) {
							NotifyCheckDeplacement(x,y);
						}
						else {
							NotifyCheck(x,y);
						}
					}
					public void mouseExited(MouseEvent e){
						cases.changeColor(new Color(173, 173, 173));
					}
				});
			}
		}

		
		this.carteVictoireDos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				NotifySeeCV();
			}
		});
		
		this.carteVictoireRecto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				NotifySeeCV();
			}
		});
		
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

	/**
	 * Permet de sélectionner des cartes dans sa main 
	 * @param les cartes
	 */
	public void cardHandler(ArrayList<JPanel> cartes) {
		for(JPanel carte : cartes) {
			carte.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(!deplacer.isVisible()) {
						for(JPanel carte : cartes) {
							carte.setBorder(BorderFactory.createLineBorder(Color.black));
							carte.setBounds(carte.getX(),15,carte.getWidth(),190);

						}
						if(carteSelected == null) {
							index = cartes.indexOf(carte);
							carteSelected = carte;
							carte.setBounds(carte.getX(), carte.getY()-15, carte.getWidth(), carte.getHeight()+15);
							carte.setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(78,168,50)));
						}
						else if(carteSelected.equals(carte)) {
							carteSelected = null;
							index =-1;
						}
						else {
							index = cartes.indexOf(carte);
							carteSelected = carte;
							carte.setBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(78,168,50)));
							carte.setBounds(carte.getX(), carte.getY()-15, carte.getWidth(), carte.getHeight()+15);
						}
					}
				}
				public void mouseEntered(MouseEvent e) {
					if(!(carte.equals(carteSelected)) && !deplacer.isVisible()) {
						carte.setBounds(carte.getX(), carte.getY()-15, carte.getWidth(), carte.getHeight()+15);
					}	
				}

				public void mouseExited(MouseEvent e) {
					if(!(carte.equals(carteSelected)) && !deplacer.isVisible()) {
						carte.setBounds(carte.getX(), carte.getY()+15, carte.getWidth(), carte.getHeight()-15);
					}
				}
			});

		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void updateTurn(int tour, String name) {
		turn.setText("  Tour "+tour+" - "+name);
	}

}
