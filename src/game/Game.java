package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import objects.ObjectID;
import objects.GameObject;
import objects.ObjectHandler;
import objects.types.*;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -8584703749668887689L;
	public static final int gameWidth = 700;
	public static final int gameHeight = 600;
	
	public static boolean isDying = false;
	private Thread mainThread;
	private boolean threadRunning;
	private ObjectHandler objectHandler;
	
	public Game() {
		new Window(gameWidth, gameHeight, "Mario Simulation", this);
		objectHandler = new ObjectHandler();
		this.addKeyListener(new KeyInput(objectHandler));
		
		objectHandler.addObj(new Background(0, 0, ObjectID.Background));
		for (int i = 0; i <= 10; i++) {
			objectHandler.addObj(new Coin(((int) (Math.random() * 160)) * 4, ((int) (Math.random() * 50)) * 4 + 200, ObjectID.Coin));
			objectHandler.addObj(new Enemy(((int) (Math.random() * 160)) * 4 + 800, 415, ObjectID.Enemy));
		}
		
		objectHandler.addObj(new Player(335, 415, ObjectID.Player));
	}
	
	public synchronized void start() {
		mainThread = new Thread(this);
		mainThread.start();
		threadRunning = true;
	}
	
	private void tick() {
		objectHandler.tick();
	}
	
	public void collide() {
		Rectangle playerHitbox = null;
		ArrayList<Rectangle> coinHitboxes = new ArrayList<Rectangle>();
		ArrayList<Rectangle> enemyHitBoxes = new ArrayList<Rectangle>();
		ArrayList<Rectangle> enemyDeadBoxes = new ArrayList<Rectangle>();
		
		for (int curObj = 0; curObj < objectHandler.objectList.size(); curObj++) {
			GameObject tempObj = objectHandler.objectList.get(curObj);
			
			if (tempObj.getID() == ObjectID.Player) {
				playerHitbox = tempObj.getBounds();
			}
			if (tempObj.getID() == ObjectID.Coin) {
				coinHitboxes.add(tempObj.getBounds());
			}
			if (tempObj.getID() == ObjectID.Enemy) {
				enemyHitBoxes.add(tempObj.getHitBounds());
				enemyDeadBoxes.add(tempObj.getDeathBounds());
			}
		}
		
		for (int coinHitbox = 0; coinHitbox < coinHitboxes.size(); coinHitbox++) {
			if (playerHitbox.intersects(coinHitboxes.get(coinHitbox))) {
				for (int curObj = 0; curObj < objectHandler.objectList.size(); curObj++) {
					GameObject tempObj = objectHandler.objectList.get(curObj);
				
					if ((tempObj.getID() == ObjectID.Coin) && (tempObj.visible) && (tempObj.getBounds().intersects(coinHitboxes.get(coinHitbox)))) {
						objectHandler.removeObj(tempObj);
					}
				}
			}
		}
		
		for (int enemyHitbox = 0; enemyHitbox < enemyHitBoxes.size(); enemyHitbox++) {
			if (playerHitbox.intersects(enemyHitBoxes.get(enemyHitbox))) {
				for (int curObj = 0; curObj < objectHandler.objectList.size(); curObj++) {
					GameObject tempObj = objectHandler.objectList.get(curObj);
				
					if ((tempObj.getID() == ObjectID.Enemy) && (tempObj.visible) && (tempObj.getBounds().intersects(enemyHitBoxes.get(enemyHitbox)))) {
						objectHandler.removeObj(tempObj);
					}
					if ((tempObj.getID() == ObjectID.Player) && (tempObj.visible) && (tempObj.getBounds().intersects(enemyDeadBoxes.get(enemyHitbox)))) {
						isDying = true;
					}
				}
			}
		}
	}
	
	public synchronized void stop() {
		try {
			mainThread.join();
			threadRunning = false;
		} catch (Exception threadingFailed) {
			threadingFailed.printStackTrace();
		}
	}

	@Override
	public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 64;
        
        int ticks = 0;
        int frames = 0;
        
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        
        while (threadRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }
            
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            if (shouldRender) {
                frames++;
                render();
                if (!isDying) collide();
            }
            
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                //System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }
	
	public void render() {
		BufferStrategy memOrganize = this.getBufferStrategy();
		
		if (memOrganize == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = memOrganize.getDrawGraphics();
		
		//graphics.setColor(Color.green);
		//graphics.fillRect(0, 0, gameWidth, gameHeight);
		objectHandler.render(graphics);
		
		graphics.dispose();
		memOrganize.show();
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
