package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Mod�le.FormePlateau;
import Mod�le.Joueur;
import Mod�le.JoueurReel;
import Mod�le.JoueurVirtuel;
import Mod�le.Partie;
import Mod�le.Regle;
import Mod�le.StrategieDifficile;
import Mod�le.StrategieFacile;
import Mod�le.Variante1;
import Mod�le.Variante2;
import Vue.Accueil;
import Vue.InterfacePlateau;
import appli.Appli;

/**
 * @author ALCARAZ, DUTOUR
 * Repr�sente le controleur dans le mod�le MVC faisant le lien entre les classes du mod�le et celles des vues de l'accueil
 * 
 */
public class ControllerAccueil {
	
	private JPanel name1;
	private JPanel name2;
	private JPanel name3;
	private JLabel Cercle;
	private JLabel Rectangle;
	private JLabel Hexagone;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JTextField askName1;
	private JTextField askName2;
	private JTextField askName3;
	private JCheckBox checkAdvance;
	private JCheckBox checkNormal;
	private JButton creerPartie;
	private JTextPane jeuLibre;
	private JTextField nbManche;
	private JCheckBox chckbxIa3;
	private JCheckBox IAFacile3;
	private JCheckBox IADifficile3;
	private JCheckBox chckbxIa2;
	private JCheckBox IAFacile2;
	private JCheckBox IADifficile2;
	private JCheckBox chckbxIa1;
	private JCheckBox IAFacile1;
	private JCheckBox IADifficile1;
	private JButton btnPlus;
	private JButton btnMoins;
	private JFrame frame;

	
	private FormePlateau forme = FormePlateau.RECTANGLE;
	
	private int nbPlayer =2;
	private int nbManches = 0;
	private ArrayList<JTextField> askNames = new ArrayList<>();
	private ArrayList<JCheckBox> IAFacile = new ArrayList<>();
	private ArrayList<JCheckBox> IADifficile = new ArrayList<>();
	private ArrayList<JCheckBox> IACheckbox= new ArrayList<>();
	ArrayList<Joueur> tabJoueur = new ArrayList<>();
	
	
	public ControllerAccueil(JFrame frame, JButton btnPlus, JButton btnMoins, JCheckBox chckbxIa3, JCheckBox IAFacile3, JCheckBox IADifficile3, JCheckBox chckbxIa2, JCheckBox IAFacile2, JCheckBox IADifficile2, JCheckBox chckbxIa1, JCheckBox IAFacile1, JCheckBox IADifficile1, JTextField nbManche, JTextPane jeuLibre, JButton CreerPartie, JCheckBox checkNormal, JCheckBox checkAdvance, JPanel Conteneur, JPanel name1, JPanel name2, JPanel name3,JLabel Cercle,JLabel Rectangle, JLabel Hexagone, JButton button1, JButton button2,JButton button3,JTextField askName1,JTextField askName2,JTextField askName3) {
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
		this.Cercle = Cercle;
		this.Rectangle = Rectangle;
		this.Hexagone = Hexagone;
		this.button1 = button1;
		this.button2 = button2;
		this.button3 = button3;
		this.askName1 = askName1;
		this.askName2 = askName2;
		this.askName3 = askName3;
		this.checkAdvance = checkAdvance;
		this.checkNormal = checkNormal;
		this.creerPartie = CreerPartie;
		this.jeuLibre = jeuLibre;
		this.nbManche = nbManche;
		this.chckbxIa3 = chckbxIa3;
		this.IAFacile3 = IAFacile3;
		this.IADifficile3 = IADifficile3;
		this.chckbxIa2 = chckbxIa2;
		this.IAFacile2 = IAFacile2;
		this.IADifficile2 = IADifficile2;
		this.chckbxIa1 = chckbxIa1;
		this.IAFacile1 = IAFacile1;
		this.IADifficile1 = IADifficile1;
		this.btnMoins = btnMoins;
		this.btnPlus = btnPlus;
		this.frame = frame;
		
		askNames.addAll(Arrays.asList(askName1,askName2,askName3));
		IAFacile.addAll(Arrays.asList(IAFacile1,IAFacile2,IAFacile3));
		IADifficile.addAll(Arrays.asList(IADifficile1,IADifficile2,IADifficile3));
		IACheckbox.addAll(Arrays.asList(chckbxIa1,chckbxIa2,chckbxIa3));
		initialiserHandler();
	}
	
	/**
	 * Initialise les actions listeners
	 */
	
	public void initialiserHandler() {
		
		/**
		 * Affiche la premi�re ligne de cr�ation de joueurs et chache les autres
		 */
		this.button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(new Color(78,168,50));
				button2.setBackground(Color.WHITE);
				button3.setBackground(Color.WHITE);
				name1.setVisible(true);
				name2.setVisible(false);
				name3.setVisible(false);
				nbPlayer =1;
			}
		});
		
		/**
		 * Affiche les deux premi�res lignes de cr�ation de joueurs et chache les autres
		 */
		this.button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(Color.WHITE);
				button2.setBackground(new Color(78,168,50));
				button3.setBackground(Color.WHITE);
				name1.setVisible(true);
				name2.setVisible(true);
				name3.setVisible(false);
				nbPlayer =2;
			}
		});
		
		/**
		 * Affiche les trois lignes de cr�ation de joueurs et chache les autres
		 */
		this.button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(Color.WHITE);
				button2.setBackground(Color.WHITE);
				button3.setBackground(new Color(78,168,50));
				name1.setVisible(true);
				name2.setVisible(true);
				name3.setVisible(true);
				nbPlayer =3;
			}
		});
		
		/**
		 * Augmente de 1 le nombre de manche
		 */
		this.btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nbManche.setText(String.valueOf(Integer.parseInt(nbManche.getText()) +1));
				}
				catch(Exception ex) {}
			}
		});
		
		/**
		 * Diminue de 1 le nombre de manche
		 */
		this.btnMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Integer.parseInt(nbManche.getText())>1){
						nbManche.setText(String.valueOf(Integer.parseInt(nbManche.getText()) -1));
					}
				}
				catch(Exception ex) {}
			}
		});
		
		/**
		 * Action Listener sur l'image de l'hexagone
		 * Au clic : Met l'image de l'hexagone en vert et selectionne l'hexagone comme type de plateau de jeu
		 * A l'entr�e de la souris : Met l'image de l'hexagone en vert
		 * A la sortie de la souris : Remet l'image de l'hexagone en blanc
		 */
		this.Hexagone.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone_green.png")));
				Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_reverse.png")));
				Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle.png")));
				jeuLibre.setForeground(Color.WHITE);
				
				forme = FormePlateau.HEXAGONE;
			}

			public void mouseEntered(MouseEvent e) {
				Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone_green.png")));
			}
			
			public void mouseExited(MouseEvent e) {
				if(forme != FormePlateau.HEXAGONE) {
					Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone2.png")));
				}
			}
		});
		
		/**
		 * Action Listener sur l'image du rectangle
		 * Au clic : Met l'image du rectangle en vert et selectionne l'hexagone comme type de plateau de jeu
		 * A l'entr�e de la souris : Met l'image du rectanlge en vert
		 * A la sortie de la souris : Remet l'image du rectangle en blanc
		 */
		this.Rectangle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle_green.png")));
				Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_reverse.png")));
				Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone2.png")));
				jeuLibre.setForeground(Color.WHITE);
				
				forme = FormePlateau.RECTANGLE;
			}
			
			public void mouseEntered(MouseEvent e) {
				Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle_green.png")));
			}
			
			public void mouseExited(MouseEvent e) {
				if(forme != FormePlateau.RECTANGLE) {
					Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle.png")));
				}
			}
			
		});
		
		/**
		 * Action Listener sur l'image du cercle
		 * Au clic : Met l'image de cercle en vert et selectionne l'hexagone comme type de plateau de jeu
		 * A l'entr�e de la souris : Met l'image du cerlce en vert
		 * A la sortie de la souris : Remet l'image du cercle en blanc
		 */
		this.Cercle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_green.png")));
				Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle.png")));
				Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone2.png")));
				jeuLibre.setForeground(Color.WHITE);
				
				forme = FormePlateau.CERCLE;
			}
			public void mouseEntered(MouseEvent e) {
				Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_green.png")));
			}
			
			public void mouseExited(MouseEvent e) {
				if(forme != FormePlateau.CERCLE) {
					Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_reverse.png")));
				}
			}
		});
		
		/**
		 * Checkbox qui met le jeu en mode normal au clic et d�coche la checbox du mode avanc�
		 */
		this.checkNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkNormal.isOpaque()) {
					checkNormal.setSelected(true);
				}
				else {
					checkAdvance.setSelected(false);
					checkNormal.setBackground(new Color(78,168,50));
					checkNormal.setOpaque(true);
					checkAdvance.setOpaque(false);
				}
			}
		});
		
		/**
		 * Checkbox qui met le jeu en mode avanc� au clic et d�coche la checbox du mode normal
		 */
		this.checkAdvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkAdvance.isOpaque()) {
					checkAdvance.setSelected(true);
				}
				else {
				checkNormal.setSelected(false);
				checkAdvance.setBackground(new Color(78,168,50));
				checkAdvance.setOpaque(true);
				checkNormal.setOpaque(false);
				}
			}
		});
		
		/**
		 * Action Listener sur le texte Jeu Libre
		 * Au clic : Met la couleur de police en vert et met jeu libre comme type de plateau
		 * A l'entr�e de la souris : Met la couleur de police en vert
		 * A la sortie de la souris : Remet Met la couleur de police en blanc
		 */
		this.jeuLibre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_reverse.png")));
				Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle.png")));
				Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone2.png")));
				jeuLibre.setForeground(new Color(78,168,50));
				
				forme = FormePlateau.LIBRE;
			}
			public void mouseEntered(MouseEvent e) {
				jeuLibre.setForeground(new Color(78,168,50));
			}
			
			public void mouseExited(MouseEvent e) {
				if(forme != FormePlateau.LIBRE) {
					jeuLibre.setForeground(Color.WHITE);
				}
			}
		});
		
		/**
		 * Au clic : Met le joueur 1 en tant qu'IA. Si la checbox �tait d�j� 
		 * selectionn�e, repasse le joueur 1 en joueur r�el
		 */
		this.chckbxIa1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIa1.isOpaque()) {
					chckbxIa1.setSelected(false);
					chckbxIa1.setOpaque(false);
					IAFacile1.setVisible(false);
					IADifficile1.setVisible(false);
					IAFacile1.setSelected(false);
					IADifficile1.setSelected(false);
				}
				else {
					chckbxIa1.setSelected(true);
					chckbxIa1.setOpaque(true);
					IAFacile1.setVisible(true);
					IADifficile1.setVisible(true);
					IAFacile1.setSelected(true);
					IAFacile1.setOpaque(true);
				}
			}
		});
		
		/**
		 * Au clic : Met le joueur 2 en tant qu'IA. Si la checbox �tait d�j� 
		 * selectionn�e, repasse le joueur 2 en joueur r�el
		 */
		this.chckbxIa2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIa2.isOpaque()) {
					chckbxIa2.setSelected(false);
					chckbxIa2.setOpaque(false);
					IAFacile2.setVisible(false);
					IADifficile2.setVisible(false);
					IAFacile2.setSelected(false);
					IADifficile2.setSelected(false);
				}
				else {
					chckbxIa2.setSelected(true);
					chckbxIa2.setOpaque(true);
					IAFacile2.setVisible(true);
					IADifficile2.setVisible(true);
					IAFacile2.setSelected(true);
					IAFacile2.setOpaque(true);
				}
			}
		});
		
		/**
		 * Au clic : Met le joueur 3 en tant qu'IA. Si la checbox �tait d�j� 
		 * selectionn�e, repasse le joueur 3 en joueur r�el
		 */
		this.chckbxIa3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIa3.isOpaque()) {
					chckbxIa3.setSelected(false);
					chckbxIa3.setOpaque(false);
					IAFacile3.setVisible(false);
					IADifficile3.setVisible(false);
					IAFacile3.setSelected(false);
					IADifficile3.setSelected(false);
				}
				else {
					chckbxIa3.setSelected(true);
					chckbxIa3.setOpaque(true);
					IAFacile3.setVisible(true);
					IADifficile3.setVisible(true);
					IAFacile3.setSelected(true);
					IAFacile3.setOpaque(true);
				}
			}
		});
		
		/**
		 * Au clic : Met la difficult� du joueur virtuel 1 en mode facile et 
		 * d�coche la checbox de l'IA difficle
		 */
		this.IAFacile1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IAFacile1.isOpaque()) {
					IAFacile1.setSelected(true);
				}
				else {
				IADifficile1.setSelected(false);
				IAFacile1.setBackground(new Color(78,168,50));
				IAFacile1.setOpaque(true);
				IADifficile1.setOpaque(false);
				}
			}
		});
		
		/**
		 * Au clic : Met la difficult� du joueur virtuel 2 en mode facile et 
		 * d�coche la checbox de l'IA difficle
		 */
		this.IAFacile2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IAFacile2.isOpaque()) {
					IAFacile2.setSelected(true);
				}
				else {
				IADifficile2.setSelected(false);
				IAFacile2.setBackground(new Color(78,168,50));
				IAFacile2.setOpaque(true);
				IADifficile2.setOpaque(false);
				}
			}
		});
		
		/**
		 * Au clic : Met la difficult� du joueur virtuel 3 en mode facile et 
		 * d�coche la checbox de l'IA difficle
		 */
		this.IAFacile3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IAFacile3.isOpaque()) {
					IAFacile3.setSelected(true);
				}
				else {
				IADifficile3.setSelected(false);
				IAFacile3.setBackground(new Color(78,168,50));
				IAFacile3.setOpaque(true);
				IADifficile3.setOpaque(false);
				}
			}
		});
		
		/**
		 * Au clic : Met la difficult� du joueur virtuel 1 en mode difficile et 
		 * d�coche la checbox de l'IA facile
		 */
		this.IADifficile1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IADifficile1.isOpaque()) {
					IADifficile1.setSelected(true);
				}
				else {
				IAFacile1.setSelected(false);
				IADifficile1.setBackground(new Color(78,168,50));
				IADifficile1.setOpaque(true);
				IAFacile1.setOpaque(false);
				}
			}
		});
		
		/**
		 * Au clic : Met la difficult� du joueur virtuel 2 en mode difficile et 
		 * d�coche la checbox de l'IA facile
		 */
		this.IADifficile2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IADifficile2.isOpaque()) {
					IADifficile2.setSelected(true);
				}
				else {
				IAFacile2.setSelected(false);
				IADifficile2.setBackground(new Color(78,168,50));
				IADifficile2.setOpaque(true);
				IAFacile2.setOpaque(false);
				}
			}
		});
		
		/**
		 * Au clic : Met la difficult� du joueur virtuel 3 en mode difficile et 
		 * d�coche la checbox de l'IA facile
		 */
		this.IADifficile3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IADifficile3.isOpaque()) {
					IADifficile3.setSelected(true);
				}
				else {
				IAFacile3.setSelected(false);
				IADifficile3.setBackground(new Color(78,168,50));
				IADifficile3.setOpaque(true);
				IAFacile3.setOpaque(false);
				}
			}
		});
		
		/**
		 * Au clic : V�rifie que tous les param�tres ont bien �t�s remplis
		 * et lance la partie.
		 * Si il manque des param�tres, les mettre en rouge
		 */
		this.creerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regle regle = null;
				int nb;
				
				if(!checkNormal.isSelected() && !checkAdvance.isSelected()){
					System.out.println("Veuillez choisir un mode de jeu");
					checkNormal.setOpaque(true);
					checkAdvance.setOpaque(true);
					checkNormal.setBackground(new Color(201,26,26));
					checkAdvance.setBackground(new Color(201,26,26));
					return;
				}
				else if(checkNormal.isSelected()) {
					regle = new Variante1();
				}
				else {
					regle = new Variante2();
				}
				
				try {
					nb = Integer.parseInt(nbManche.getText());
					nbManche.setOpaque(true);
					nbManche.setBackground(Color.DARK_GRAY);
				}
				catch (Exception ex){
					System.out.println("Veuillez rentrer un chiffre dans la case nombre de manches");
					nbManche.setOpaque(true);
					nbManche.setBackground(new Color(201,26,26));
					return;
				}
				
				for(int i=0; i<nbPlayer; i++) {
					if(askNames.get(i).getText().equals("")) {
						askNames.get(i).setOpaque(true);
						askNames.get(i).setBackground(new Color(201,26,26));
						return;
					}
					else {
						askNames.get(i).setBackground(Color.DARK_GRAY);
					}
				}
				
				for(int i=0; i<nbPlayer; i++) {
					Joueur j = null;
					if(IACheckbox.get(i).isSelected()) {
						if(IAFacile.get(i).isSelected()) {
							j = new JoueurVirtuel(askNames.get(i).getText(),true, new StrategieFacile(),i);
						}
						else{
							j = new JoueurVirtuel(askNames.get(i).getText(),true, new StrategieDifficile(),i);					
						}
					}
					else {
						j = new JoueurReel(askNames.get(i).getText(),i);
					}
					tabJoueur.add(j);
				}
				
				InterfacePlateau interfacePlateau = new InterfacePlateau(forme);
				new Appli(interfacePlateau, nb, regle, forme, tabJoueur);
				frame.dispose();
			}
		});
	}
}
