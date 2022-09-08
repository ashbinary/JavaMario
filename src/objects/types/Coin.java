package objects.types;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import objects.GameObject;
import objects.ObjectID;

public class Coin extends GameObject {

	int curFrameInt;
	
	public Coin(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		//velX = 1;
	}

	public void tick() {
		// TODO Auto-generated method stub
		curFrameInt = (int) (System.currentTimeMillis() / 200) % 4;
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
		
		if (visible) {
			Image image = new ImageIcon("src/resources/coin/coin_" + (curFrameInt  + 4) + ".png").getImage();
			width = image.getWidth(null); height = image.getHeight(null);
			g2D.drawImage(image, (x % 4 == 0) ? x : x - (x % 4), (y % 4 == 0) ? y : y - (y % 4), width * 4, height * 4, null);
		}
	}

	public void keyPressed(int pressedKey) {
		// TODO Auto-generated method stub
	}
	
	public void keyReleased(int releasedKey) {
		// TODO Auto-generated method stub
	}

	@Override
	public Rectangle getHitBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getDeathBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}
