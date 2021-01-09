package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Cercle extends JPanel{
	
	private int x, y, rayon;
	private Color color;
	
	public Cercle(int x, int y, int rayon, Color color) {
		this.x = x;
		this.y = y;
		this.rayon = rayon;
		this.color = color;
	}
	
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval(this.x,this.y, this.rayon*2, this.rayon*2);
    	g2d.setColor(color);
    	g2d.fillOval(this.x,this.y, this.rayon*2, this.rayon*2);
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }
}

