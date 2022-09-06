package objects.types;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import objects.GameObject;
import objects.ObjectID;

public class Background extends GameObject {

	public Background(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		//velX = 1;
	}

	public void tick() {
		// TODO Auto-generated method stub
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
		
		Image image = new ImageIcon("src/resources/bg.png").getImage();
		int width = image.getWidth(null); int height = image.getHeight(null);
		g2D.drawImage(image, 0, 0, width * 4, height * 4, null);
	}

	public void keyPressed(int pressedKey) {
		// TODO Auto-generated method stub
	}
	
	public void keyReleased(int releasedKey) {
		// TODO Auto-generated method stub
	}
}
