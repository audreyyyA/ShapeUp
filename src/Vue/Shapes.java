package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import Modèle.FormePlateau;

/**
 * Classe permettant d'afficher la vue graphique des plateaux/cartes selon sa forme et courleur
 * @author ALCARAZ, DUTOUR
 */
public class Shapes extends JPanel{

	private Color color;
	private int x, y, size;
	private Formes forme;
	private Graphics2D g2d;
	private boolean pointille;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shapes(int x, int y, int size,Color color, boolean pointille, Formes forme) {

		this.size = size;
		this.x = x+ (int) (this.size*Math.cos(Math.toRadians(30)));
		this.y = y+this.size;
		this.color = color;
		this.forme = forme;
		this.pointille = pointille;
	}

	/**
	 * Creer le plateau/carte selon sa forme et couleur
	 * @param Object Graphics 
	 */
	public void draw(Graphics g) {
		if(forme == Formes.HEXAGONE) {
			Graphics2D g2d = (Graphics2D) g;
			if(pointille) {
				this.size =size;
			}
			Polygon Hexagone = new Polygon();
			int petiteDiago = size /2;
			int diagoSecond = (int) (size*Math.cos(Math.toRadians(30)));
			Hexagone.addPoint(0, petiteDiago);
			Hexagone.addPoint(diagoSecond, 0);
			Hexagone.addPoint(diagoSecond*2, petiteDiago);
			Hexagone.addPoint(diagoSecond*2, petiteDiago+ size);
			Hexagone.addPoint(diagoSecond, size*2);
			Hexagone.addPoint(0, size + petiteDiago);

			if(this.pointille) {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(2));
				g2d.draw(Hexagone); 
			}
			else {
				g2d.setColor(color);
				g2d.fill(Hexagone);
			}
		}

		else if(forme == Formes.CARRE) {

			Graphics2D g2d = (Graphics2D) g;
			Polygon Carre = new Polygon();

			Carre.addPoint(5, 5);
			Carre.addPoint(size+5, 5);
			Carre.addPoint(size+5, size+5);
			Carre.addPoint(5, size+5);

			if(this.pointille) {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(5));
				g2d.draw(Carre); 
			}
			else {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(5));
				g2d.draw(Carre); 
				g2d.fill(Carre);
			}
		}

		else if(forme == Formes.TRIANGLE) {
			Graphics2D g2d = (Graphics2D) g;
			Polygon Triangle = new Polygon();
			
			Triangle.addPoint(5+size/2, 10);
			Triangle.addPoint(5, size);
			Triangle.addPoint(size+5,size);
			
			if(this.pointille) {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(5));
				g2d.draw(Triangle); 
			}
			else { 
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(5));
				g2d.draw(Triangle); 
				g2d.fill(Triangle);
			}
		}

		else if(forme == Formes.CERCLE) {
			Graphics2D g2d = (Graphics2D) g;
			if(this.pointille) {
				g2d.setStroke(new BasicStroke(7));
				g2d.setColor(color);
				g2d.drawOval(5,5, this.size-2, this.size-2);
			}
			else {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(7));
				g2d.drawOval(5,5, this.size-2, this.size-2);
				g2d.fillOval(5,5, this.size, this.size);
			}
		}
		else if(forme == Formes.RECTANGLE) {
			Graphics2D g2d = (Graphics2D) g;
			Polygon Rectangle = new Polygon();

			Rectangle.addPoint(5, 5);
			Rectangle.addPoint(size+5, 5);
			Rectangle.addPoint(size+5, (int) (size*1.22+5));
			Rectangle.addPoint(5, (int) (size*1.22+5));

			if(this.pointille) {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(2));
				g2d.draw(Rectangle); 
			}
			else {
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(2));
				g2d.draw(Rectangle); 
				g2d.fill(Rectangle);
			}
		}
	}

	/**
	 * Change la couleur selon celle passee en parametre
	 * @param la couleur 
	 */
	public void changeColor(Color color) {
		this.color = color;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draw(g);
	}

}
