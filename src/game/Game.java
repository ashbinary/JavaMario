package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;

import objects.ObjectID;
import objects.ObjectHandler;
import objects.types.Background;
import objects.types.Player;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -8584703749668887689L;
	public static final int gameWidth = 700;
	public static final int gameHeight = 600;
	
	private Thread mainThread;
	private boolean threadRunning;
	private ObjectHandler objectHandler;
	
	public Game() {
		new Window(gameWidth, gameHeight, "Super Mario Bros.", this);
		objectHandler = new ObjectHandler();
		this.addKeyListener(new KeyInput(objectHandler));
		
		objectHandler.addObj(new Background(0, 0, ObjectID.Background));
		objectHandler.addObj(new Player(100, 415, ObjectID.Player));
	}
	
	public synchronized void start() {
		mainThread = new Thread(this);
		mainThread.start();
		threadRunning = true;
	}
	
	private void tick() {
		objectHandler.tick();
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
            }
            
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " ticks, " + frames + " frames");
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
