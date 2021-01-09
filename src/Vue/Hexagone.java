package Vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Hexagone extends JPanel{
	
	private Color color;
	private int x, y, size;
	
	public Hexagone(int x, int y, int size,Color color, boolean pointille) {
		  this.x = x+ (int) (size*Math.cos(Math.toRadians(30)));
	      this.y = y+size;
	      this.size = size;
	      this.color = color;
	}
	
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Polygon Hexagone = new Polygon();
        int petiteDiago = size /2;
        int diagoSecond = (int) (size*Math.cos(Math.toRadians(30)));
        Hexagone.addPoint(0, petiteDiago);
        Hexagone.addPoint(diagoSecond, 0);
        Hexagone.addPoint(diagoSecond*2, petiteDiago);
        Hexagone.addPoint(diagoSecond*2, petiteDiago+ size);
        Hexagone.addPoint(diagoSecond, size*2);
        Hexagone.addPoint(0, size + petiteDiago);
    	g2d.setColor(color);
    	g2d.fill(Hexagone);
    }
	
	public void changeColor(Component c, Color color) {
		this.color = color;
		Graphics g = c.getGraphics();
		paintComponent(g);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}

