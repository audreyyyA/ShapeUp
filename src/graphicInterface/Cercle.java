package graphicInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Cercle extends JPanel{
	
	private int x, y, size;
	
	public Cercle(int x, int y, int rayon) {

	}
	
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval(50, 50, 50 , 50);
    	g2d.setColor(new Color(173, 173, 173));
    	g2d.fillOval(50, 100, 100, 50);
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}

