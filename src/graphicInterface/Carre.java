package graphicInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Carre extends JPanel{
	
	private int x, y, size;
	
	public Carre(int x, int y, int size) {
		  this.x = x;
	      this.y = y;
		  this.size = size;
	}
	
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Polygon Carre = new Polygon();
    	Carre.addPoint(0, 0);
    	Carre.addPoint(size, 0);
    	Carre.addPoint(size, size);
    	Carre.addPoint(0, size);
    	g2d.setColor(new Color(173, 173, 173));
    	g2d.fill(Carre);
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}

