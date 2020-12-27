package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import jeu.FormePlateau;
import jeu.Partie;
import jeu.Regle;
import jeu.Variante1;
import jeu.Variante2;

public class Controller {
	
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
	
	private FormePlateau forme = FormePlateau.RECTANGLE;
	
	private int nbPlayer =1;
	private int nbManches = 0;
	
	public Controller(JTextField nbManche, JTextPane jeuLibre, JButton CreerPartie, JCheckBox checkNormal, JCheckBox checkAdvance, JPanel Conteneur, JPanel name1, JPanel name2, JPanel name3,JLabel Cercle,JLabel Rectangle, JLabel Hexagone, JButton button1, JButton button2,JButton button3,JTextField askName1,JTextField askName2,JTextField askName3) {
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
		
		initialiserHandler();
	}
	
	public void initialiserHandler() {
		
		this.button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(new Color(78,168,50));
				button2.setBackground(Color.WHITE);
				button3.setBackground(Color.WHITE);
				name1.setVisible(true);
				name2.setVisible(false);
				name3.setVisible(false);
			}
		});
		
		this.button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(Color.WHITE);
				button2.setBackground(new Color(78,168,50));
				button3.setBackground(Color.WHITE);
				name1.setVisible(true);
				name2.setVisible(true);
				name3.setVisible(false);
			}
		});
		
		this.button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setBackground(Color.WHITE);
				button2.setBackground(Color.WHITE);
				button3.setBackground(new Color(78,168,50));
				name1.setVisible(true);
				name2.setVisible(true);
				name3.setVisible(true);
			}
		});
		
		this.Hexagone.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Hexagone.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/hexagone_green.png")));
				Cercle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/cercle_reverse.png")));
				Rectangle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/rectangle.png")));
				jeuLibre.setForeground(Color.WHITE);
				
				forme = FormePlateau.HEXAGONE;
			}
		});
		
		this.Rectangle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Rectangle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/rectangle_green.png")));
				Cercle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/cercle_reverse.png")));
				Hexagone.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/hexagone2.png")));
				jeuLibre.setForeground(Color.WHITE);
				
				forme = FormePlateau.RECTANGLE;
			}
		});
		
		this.Cercle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Cercle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/cercle_green.png")));
				Rectangle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/rectangle.png")));
				Hexagone.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/hexagone2.png")));
				jeuLibre.setForeground(Color.WHITE);
				
				forme = FormePlateau.CERCLE;
			}
		});
		
		this.checkNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAdvance.setSelected(false);
				checkNormal.setBackground(new Color(78,168,50));
				checkNormal.setOpaque(true);
				checkAdvance.setOpaque(false);
			}
		});
		
		this.checkAdvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkNormal.setSelected(false);
				checkAdvance.setBackground(new Color(78,168,50));
				checkAdvance.setOpaque(true);
				checkNormal.setOpaque(false);
			}
		});
		
		this.jeuLibre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Cercle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/cercle_reverse.png")));
				Rectangle.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/rectangle.png")));
				Hexagone.setIcon(new ImageIcon(GraphicInterface.class.getResource("/images/hexagone2.png")));
				jeuLibre.setForeground(new Color(78,168,50));
				
				forme = FormePlateau.LIBRE;
			}
		});
		
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
				}
				catch (Exception ex){
					System.out.println("Veuillez rentrer un chiffre dans la case nombre de manches");
					nbManche.setOpaque(true);
					nbManche.setBackground(new Color(201,26,26));
					return;
				}
				
				Partie partie = new Partie(nb, regle);
				partie.debutPartie(forme);
				partie.finPartie();
				partie.afficherScore();
			}
		});
	}
}
