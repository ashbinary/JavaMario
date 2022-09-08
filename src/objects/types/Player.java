package objects.types;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import game.Game;
import objects.GameObject;
import objects.ObjectID;

public class Player extends GameObject {

	private boolean isAirborne = false;
	private boolean flipped = false;
	
	protected boolean pressedUp, pressedDown, pressedLeft, pressedRight;
	
	boolean dieOnce;
	String curFrame = "";
	int curFrameInt;
	
	public Player(int x, int y, ObjectID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		//velX = 1;
	}

	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if (!Game.isDying) {
			if (isAirborne) {
				curFrame = "jump";
			} else if (velX != 0) {
				curFrame = "walk_" + (curFrameInt + 3);
			} else if (pressedDown) {
				curFrame = "crouch";
			} else {
				curFrame = "stand";
			}
			
			if (velX < 0) flipped = true;
			if (velX > 0) flipped = false;
			
			if (pressedRight && velX < 6) velX += 1;
			if (pressedLeft && velX > -6) velX -= 1;
			if (pressedUp && isAirborne == false) {
				velY = -20;
				y -= 10;
				isAirborne = true;
			}
			
			if ((isAirborne) && (y < 405)) velY += 1;
			else {
				velY = 0;
				isAirborne = false;
			}
			
			if (x > 740) {
				x = -30;
			} else if (x < -100) {
				x = 710;
			}
			
			if ((!pressedRight) && (!pressedLeft)) velX *= 0.8;
		} else {
			curFrame = "die";
			if (!dieOnce) {
				velY = -25;
				velX *= 0.2;
			}
			dieOnce = true;
			velY += 1;
			if (y > 1000) System.exit(0);
		}
		
		curFrameInt = (int) (System.currentTimeMillis() / 100) % 3;
	}
	
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
		
		Image image = new ImageIcon("src/resources/mario/mario_" + curFrame + ((flipped && !Game.isDying) ? "_flip" : "") + ".png").getImage();
		//System.out.print(curFrame);
		width = image.getWidth(null); height = image.getHeight(null);
		g2D.drawImage(image, (x % 4 == 0) ? x : x - (x % 4), (y % 4 == 0) ? y : y - (y % 4), width * 4, height * 4, null);
	}

	public void keyPressed(int pressedKey) {
		// TODO Auto-generated method stub
		switch (pressedKey) {
			case 37 -> pressedLeft = true;
			case 38 -> pressedUp = true;
			case 39 -> pressedRight = true;
			case 40 -> pressedDown = true;
		}
	}
	
	public void keyReleased(int releasedKey) {
		// TODO Auto-generated method stub
		switch (releasedKey) {
			case 37 -> pressedLeft = false;
			case 38 -> pressedUp = false;
			case 39 -> pressedRight = false;
			case 40 -> pressedDown = false;
		}
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
