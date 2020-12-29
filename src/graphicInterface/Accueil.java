package graphicInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JProgressBar;
import java.awt.Canvas;

public class Accueil {

	private JFrame frmShapeUp;
	private JTextField askName1;
	private JTextField askName2;
	private JTextField askName3;
	private JTextField nbManche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accueil window = new Accueil();
					window.frmShapeUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShapeUp = new JFrame();
		frmShapeUp.setTitle("Shape Up");
		frmShapeUp.getContentPane().setForeground(Color.DARK_GRAY);
		frmShapeUp.getContentPane().setBackground(Color.DARK_GRAY);
		frmShapeUp.setBackground(Color.DARK_GRAY);
		frmShapeUp.setBounds(100, 100, 1280, 720);
		frmShapeUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShapeUp.getContentPane().setLayout(null);
		
		JTextPane Signature = new JTextPane();
		Signature.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 13));
		Signature.setForeground(Color.WHITE);
		Signature.setText("Audrey ALCARAZ & Alexandre DUTOUR");
		Signature.setBounds(521, 660, 237, 21);
		Signature.setOpaque(false);
		frmShapeUp.getContentPane().add(Signature);
		
		JPanel Conteneur = new JPanel();
		Conteneur.setBackground(new Color(65,65,65));
		Conteneur.setBounds(266, 137, 747, 518);
		frmShapeUp.getContentPane().add(Conteneur);
		Conteneur.setLayout(null);
		
		JPanel nbJoueur = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) nbJoueur.getLayout();
		nbJoueur.setBounds(0, 10, 747, 43);
		nbJoueur.setOpaque(false);
		Conteneur.add(nbJoueur);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre de joueurs  ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		nbJoueur.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		nbJoueur.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(78,168,50));
		nbJoueur.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("3");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		btnNewButton_2.setBorderPainted(false);
		nbJoueur.add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(75, 55, 597, 2);
		Conteneur.add(separator);
		
		JLabel Hexagone = new JLabel("");
		Hexagone.setHorizontalAlignment(SwingConstants.CENTER);
		Hexagone.setIcon(new ImageIcon(Accueil.class.getResource("/images/hexagone2.png")));
		Hexagone.setBounds(75, 344, 100, 95);
		Hexagone.setBackground(Color.RED);
		Conteneur.add(Hexagone);
		
		JLabel Rectangle = new JLabel("");
		Rectangle.setIcon(new ImageIcon(Accueil.class.getResource("/images/rectangle_green.png")));
		Rectangle.setBounds(241, 344, 132, 95);
		Conteneur.add(Rectangle);
		
		
		JLabel Cercle = new JLabel("");
		Cercle.setIcon(new ImageIcon(Accueil.class.getResource("/images/cercle_reverse.png")));
		Cercle.setBounds(433, 344, 95, 95);
		Conteneur.add(Cercle);
		
		JPanel name1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) name1.getLayout();
		flowLayout.setAlignOnBaseline(true);
		name1.setOpaque(false);
		name1.setBounds(0, 65, 747, 43);
		Conteneur.add(name1);
		
		JLabel NomJoueur1 = new JLabel("Nom joueur 1   ");
		NomJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
		NomJoueur1.setForeground(Color.WHITE);
		NomJoueur1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		name1.add(NomJoueur1);
		
		askName1 = new JTextField();
		askName1.setHorizontalAlignment(SwingConstants.CENTER);
		askName1.setForeground(Color.WHITE);
		askName1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		askName1.setBackground(Color.DARK_GRAY);
		name1.add(askName1);
		askName1.setColumns(10);
		
		JPanel name2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) name2.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		name2.setOpaque(false);
		name2.setBounds(0, 106, 747, 43);
		Conteneur.add(name2);
		
		JLabel NomJoueur2 = new JLabel("Nom joueur 2  ");
		NomJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
		NomJoueur2.setForeground(Color.WHITE);
		NomJoueur2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		name2.add(NomJoueur2);
		
		askName2 = new JTextField();
		askName2.setHorizontalAlignment(SwingConstants.CENTER);
		askName2.setForeground(Color.WHITE);
		askName2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		askName2.setColumns(10);
		askName2.setBackground(Color.DARK_GRAY);
		name2.add(askName2);
		
		JPanel name3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) name3.getLayout();
		flowLayout_2.setAlignOnBaseline(true);
		name3.setBounds(0, 147, 747, 43);
		Conteneur.add(name3);
		name3.setVisible(false);
		name3.setOpaque(false);
		
		JLabel nomJoueur3 = new JLabel("Nom joueur 3  ");
		nomJoueur3.setHorizontalAlignment(SwingConstants.CENTER);
		nomJoueur3.setForeground(Color.WHITE);
		nomJoueur3.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		name3.add(nomJoueur3);
		
		askName3 = new JTextField();
		askName3.setHorizontalAlignment(SwingConstants.CENTER);
		askName3.setForeground(Color.WHITE);
		askName3.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		askName3.setColumns(10);
		askName3.setBackground(Color.DARK_GRAY);
		name3.add(askName3);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(75, 195, 597, 2);
		Conteneur.add(separator2);
		
		JPanel nbManches = new JPanel();
		FlowLayout fl_nbManches = (FlowLayout) nbManches.getLayout();
		fl_nbManches.setAlignOnBaseline(true);
		nbManches.setOpaque(false);
		nbManches.setBounds(0, 203, 747, 43);
		Conteneur.add(nbManches);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Nombre de manches   ");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		nbManches.add(lblNewLabel_2_1_2);
		
		JButton btnMoins = new JButton("-");
		btnMoins.setBackground(Color.DARK_GRAY);
		btnMoins.setForeground(Color.WHITE);
		btnMoins.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		btnMoins.setVerticalAlignment(SwingConstants.TOP);
		nbManches.add(btnMoins);
		
		nbManche = new JTextField();
		nbManche.setText("3");
		nbManche.setHorizontalAlignment(SwingConstants.CENTER);
		nbManche.setForeground(Color.WHITE);
		nbManche.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		nbManche.setColumns(4);
		nbManche.setBackground(Color.DARK_GRAY);
		nbManches.add(nbManche);
		
		
		JButton btnPlus = new JButton("+");
		btnPlus.setBackground(Color.DARK_GRAY);
		btnPlus.setForeground(Color.WHITE);
		btnPlus.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		nbManches.add(btnPlus);
		
		JSeparator separator2_1 = new JSeparator();
		separator2_1.setBounds(75, 245, 597, 2);
		Conteneur.add(separator2_1);
		
		JLabel Titre = new JLabel("");
		Titre.setHorizontalAlignment(SwingConstants.CENTER);
		Titre.setIcon(new ImageIcon(Accueil.class.getResource("/images/titre2.png")));
		Titre.setBounds(0, 10, 1276, 117);
		frmShapeUp.getContentPane().add(Titre);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Accueil.class.getResource("/images/background.png")));
		background.setBounds(0, 0, 1276, 692);
		frmShapeUp.getContentPane().add(background);
		
		JButton CreerPartie = new JButton("Cr\u00E9er la partie");
		CreerPartie.setForeground(Color.WHITE);
		CreerPartie.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		CreerPartie.setBackground(new Color(78,168,50));
		
		CreerPartie.setBounds(289, 465, 169, 40);
		Conteneur.add(CreerPartie);
		
		JCheckBox checkAdvance = new JCheckBox("  Mode avanc\u00E9");
		checkAdvance.setForeground(Color.WHITE);
		checkAdvance.setHorizontalAlignment(SwingConstants.CENTER);
		checkAdvance.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		checkAdvance.setBounds(412, 258, 181, 21);
		checkAdvance.setOpaque(false);
		Conteneur.add(checkAdvance);
		
		JCheckBox checkNormal = new JCheckBox("  Mode normal");
		checkNormal.setSelected(true);
		checkNormal.setForeground(Color.WHITE);
		checkNormal.setHorizontalAlignment(SwingConstants.CENTER);
		checkNormal.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		checkNormal.setBounds(192, 258, 181, 21);
		checkNormal.setOpaque(true);
		checkNormal.setBackground(new Color(78,168,50));
		Conteneur.add(checkNormal);
		
		JSeparator separator2_1_1 = new JSeparator();
		separator2_1_1.setBounds(75, 289, 597, 2);
		Conteneur.add(separator2_1_1);
		
		JTextPane jeuLibre = new JTextPane();
		jeuLibre.setEditable(false);
		jeuLibre.setForeground(Color.WHITE);
		jeuLibre.setFont(new Font("Ka Blam", Font.PLAIN, 34));
		jeuLibre.setText(" JEU LIBRE");
		jeuLibre.setBounds(590, 344, 82, 86);
		jeuLibre.setOpaque(false);
		Conteneur.add(jeuLibre);
		
		
		JCheckBox chckbxIa_2 = new JCheckBox(" IA");
		chckbxIa_2.setOpaque(false);
		chckbxIa_2.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxIa_2.setForeground(Color.WHITE);
		chckbxIa_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		chckbxIa_2.setBackground(new Color(78,168,50));
		name3.add(chckbxIa_2);
		
		JCheckBox IAFacile1_2 = new JCheckBox("Facile");
		IAFacile1_2.setOpaque(false);
		IAFacile1_2.setHorizontalAlignment(SwingConstants.CENTER);
		IAFacile1_2.setForeground(Color.WHITE);
		IAFacile1_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		IAFacile1_2.setVisible(false);
		IAFacile1_2.setBackground(new Color(78,168,50));
		name3.add(IAFacile1_2);
		
		JCheckBox IADifficile2_2 = new JCheckBox("Difficile");
		IADifficile2_2.setOpaque(false);
		IADifficile2_2.setHorizontalAlignment(SwingConstants.CENTER);
		IADifficile2_2.setForeground(Color.WHITE);
		IADifficile2_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		IADifficile2_2.setVisible(false);
		IADifficile2_2.setBackground(new Color(78,168,50));
		name3.add(IADifficile2_2);
		
		JCheckBox chckbxIa_1 = new JCheckBox(" IA");
		chckbxIa_1.setOpaque(false);
		chckbxIa_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxIa_1.setForeground(Color.WHITE);
		chckbxIa_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		chckbxIa_1.setBackground(new Color(78,168,50));
		name2.add(chckbxIa_1);
		
		JCheckBox IAFacile1_1 = new JCheckBox("Facile");
		IAFacile1_1.setOpaque(false);
		IAFacile1_1.setHorizontalAlignment(SwingConstants.CENTER);
		IAFacile1_1.setForeground(Color.WHITE);
		IAFacile1_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		IAFacile1_1.setVisible(false);
		IAFacile1_1.setBackground(new Color(78,168,50));
		name2.add(IAFacile1_1);
		
		JCheckBox IADifficile2_1 = new JCheckBox("Difficile");
		IADifficile2_1.setOpaque(false);
		IADifficile2_1.setHorizontalAlignment(SwingConstants.CENTER);
		IADifficile2_1.setForeground(Color.WHITE);
		IADifficile2_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		IADifficile2_1.setVisible(false);
		IADifficile2_1.setBackground(new Color(78,168,50));
		name2.add(IADifficile2_1);
		
		JCheckBox chckbxIa = new JCheckBox(" IA");
		chckbxIa.setOpaque(false);
		chckbxIa.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxIa.setForeground(Color.WHITE);
		chckbxIa.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		chckbxIa.setBackground(new Color(78,168,50));
		name1.add(chckbxIa);
		
		JCheckBox IAFacile1 = new JCheckBox("Facile");
		IAFacile1.setOpaque(false);
		IAFacile1.setHorizontalAlignment(SwingConstants.CENTER);
		IAFacile1.setForeground(Color.WHITE);
		IAFacile1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		IAFacile1.setVisible(false);
		IAFacile1.setBackground(new Color(78,168,50));
		name1.add(IAFacile1);
		
		JCheckBox IADifficile2 = new JCheckBox("Difficile");
		IADifficile2.setOpaque(false);
		IADifficile2.setHorizontalAlignment(SwingConstants.CENTER);
		IADifficile2.setForeground(Color.WHITE);
		IADifficile2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		IADifficile2.setVisible(false);
		IADifficile2.setBackground(new Color(78,168,50));
		name1.add(IADifficile2);
		
		JLabel lblSlectionneUnPlateau = new JLabel("S\u00E9lectionne un plateau en cliquant dessus");
		lblSlectionneUnPlateau.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlectionneUnPlateau.setForeground(Color.WHITE);
		lblSlectionneUnPlateau.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
		lblSlectionneUnPlateau.setBounds(0, 300, 747, 28);
		Conteneur.add(lblSlectionneUnPlateau);
		
		Controller controller = new Controller(btnPlus, btnMoins, chckbxIa_2, IAFacile1_2, IADifficile2_2, chckbxIa_1, IAFacile1_1, IADifficile2_1, chckbxIa, IAFacile1, IADifficile2,nbManche,jeuLibre, CreerPartie, checkNormal,checkAdvance,Conteneur,name1,name2,name3,Cercle,Rectangle,Hexagone,btnNewButton,btnNewButton_1,btnNewButton_2,askName1,askName2,askName3);

		
		
		
	}
}
