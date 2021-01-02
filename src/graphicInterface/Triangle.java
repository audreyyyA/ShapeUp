package graphicInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Triangle extends JPanel{
	
	private int x, y, size;
	private Color color;
	
	public Triangle(int x, int y, int size,Color color) {
		  this.x =x;
		  this.y = y;
	      this.size = size;
	      this.color = color;
	}
	
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Polygon Triangle = new Polygon();
    	Triangle.addPoint(x,(int) (y + size*Math.cos(Math.toRadians(30))));
    	Triangle.addPoint(x +size/2, y);
    	Triangle.addPoint(x + size,(int) (y + size*Math.cos(Math.toRadians(30))));
    	g2d.setColor(color);
    	g2d.fill(Triangle);
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}

