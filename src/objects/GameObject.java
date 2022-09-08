package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class GameObject {

	protected int x, y;
	protected ObjectID id;
	protected int velX, velY;
	protected int width, height;
	public boolean visible = true;
	
	public GameObject(int x, int y, ObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void keyPressed(int pressedKey);
	public abstract void keyReleased(int releasedKey);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setID(ObjectID id) {
		this.id = id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ObjectID getID() {
		return id;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, width * 4, height * 4);
	}

	public abstract Rectangle getHitBounds();
	public abstract Rectangle getDeathBounds();
	
}
