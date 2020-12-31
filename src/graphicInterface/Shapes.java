package graphicInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Shapes extends JPanel {


public Shapes() {
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(400, 400));
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        new Hexagone(50,50,50).draw(g);
    }


    public static void main(String[]args) {

        JFrame frame = new JFrame();
        frame.add(new Shapes());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}