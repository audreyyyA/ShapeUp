package graphicInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Hexagone extends JPanel{
	
	private int x, y, size;
	
	public Hexagone(int x, int y, int size) {
		  this.x = x+ (int) (size*Math.cos(Math.toRadians(30)));
	      this.y = y+size;
	      this.size = size;
	}
	
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Polygon Hexagone = new Polygon();
    	for (int i = 0; i < 6; i++){
    		Hexagone.addPoint((int) (x + size * Math.cos(i * 2 * Math.PI / 6)),
    				  (int) (y + size * Math.sin(i * 2 * Math.PI / 6)));
    	}
    	g2d.rotate(Math.toRadians(30),x,y);
    	g2d.setColor(new Color(173, 173, 173));
    	g2d.fill(Hexagone);
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}

