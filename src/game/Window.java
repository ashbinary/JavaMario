package game;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = -4369730830015653927L;
	
	public Window(int windowWidth, int windowHeight, String windowTitle, Game game) {
		JFrame gameFrame = new JFrame(windowTitle);
		gameFrame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		gameFrame.setMinimumSize(new Dimension(windowWidth, windowHeight));
		gameFrame.setMaximumSize(new Dimension(windowWidth, windowHeight));
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		
		gameFrame.add(game);
		gameFrame.setVisible(true);
		game.start();
	}
	
	public void changeVisibility(boolean bool) {
		this.setVisible(bool);
	}
}
