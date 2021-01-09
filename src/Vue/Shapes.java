package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import Modèle.FormePlateau;

public class Shapes extends JPanel{

	private Color color;
	private int x, y, size;
	private Formes forme;
	private Graphics2D g2d;
	private boolean pointille;
	
	public Shapes(int x, int y, int size,Color color, boolean pointille, Formes forme) {
		  if(pointille) {
			  this.size =size;
		  }
		  else {
			  this.size = size;
		  }
		  this.x = x+ (int) (this.size*Math.cos(Math.toRadians(30)));
	      this.y = y+this.size;
	      this.color = color;
	      this.forme = forme;
	      this.pointille = pointille;
	}
	
	public void draw(Graphics g) {
		this.g2d = (Graphics2D) g;
		if(forme == Formes.HEXAGONE) {
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
	    	Carre.addPoint(0, 0);
	    	Carre.addPoint(size, 0);
	    	Carre.addPoint(size, size);
	    	Carre.addPoint(0, size);
	    	g2d.setColor(color);
	    	g2d.fill(Carre);
		}
		else if(forme == Formes.CERCLE) {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawOval(50, 50, 50 , 50);
	    	g2d.setColor(color);
	    	g2d.fillOval(50, 100, 100, 50);
		}
    }
	
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
