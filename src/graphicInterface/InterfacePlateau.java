package graphicInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class InterfacePlateau implements Observer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public InterfacePlateau() {
		initialize();
		this.frame.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Shape Up");
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		new ControllerPlateau();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
