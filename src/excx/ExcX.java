package excx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import excx.data.Keyboard;
import excx.state.*;
/**
 * 
 * @author Exception
 * DISCLAIMER: Some really dumb stuff exists that I forgot to fix. 
 * If you find any, please inform me.
 * I can fix then, I just forgot where they are.
 */

public class ExcX extends Canvas implements Runnable{
	//TODO: TESTING ONLY
	
	public static StateGame game = new StateGame();
	public static StateMainMenu mainMenu = new StateMainMenu();
	public static StatePlayerSelect playerSelect = new StatePlayerSelect();
	public static StateControls controls = new StateControls();
	public static StateWin win = new StateWin();
	
	public static ExcX me = new ExcX();
	
	public static State currentState;
	
	public Keyboard keyboard;
	
	public static double delta;
	public static long lastTime;
	
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH*3/4;//480
	public static final double SCALE = 1;
			
	public static final String NAME = "External Xylene Combustion 10";
	public static final String VERSION = "Demo";
	private boolean isRunning;

	private int updates, frames;
	
	private JFrame frame;
	
	public static void main(String[] args) {
		ExcX.getInstance().start();
	}
	
	private ExcX(){
		//TODO: CREATION STUFF
		
		//Sets the size of the canvas.
		Dimension frameSize = new Dimension((int)(WIDTH*SCALE), (int)(HEIGHT*SCALE));
		this.setMinimumSize(frameSize);
		this.setPreferredSize(frameSize);
		this.setMaximumSize(frameSize);
		this.setSize(frameSize);
		
		//Creates and sets up the frame.
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		keyboard = Keyboard.getInstance();
		this.addKeyListener(keyboard);
		frame.setResizable(false);
		//frame.setTitle(NAME + " - " + VERSION + " | " + updates + " ticks, " + frames + " frames.");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		this.requestFocus();
		
		
		setState(mainMenu);
		
	}
	
	//Sets the state of the game
	public static void setState(State state) {
		currentState = state;
		lastTime = System.nanoTime();//So loading time does not influence game speed.
	}

	public void start(){
		isRunning = true;
		Thread thread = new Thread(this);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}
	
	public void stop(){
		isRunning = false;
	}

	@Override
	public void run() {
		AffineTransform sc = new AffineTransform();
		sc.scale(SCALE,SCALE);
		frame.setTitle(NAME + " - " + VERSION + " | " + updates + " ticks, " + frames + " frames.");
		long timer = System.currentTimeMillis();
		lastTime = System.nanoTime();
		long now = 0;
		double nsPerTick = 1000000000D/60D;
		delta = 0;
		//TODO:???
		while(isRunning){
			now = System.nanoTime();
			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			
			if(delta>=1){
				update();
				updates++;
				delta--;
			}
			while(delta>=1){
				delta--;
			}
			//TODO Check sleep tactics.
			//try {Thread.sleep((int)((1-delta)));} catch (InterruptedException e) {e.printStackTrace();}
			//try {Thread.sleep((int)((1-delta)/2));} catch (InterruptedException e) {e.printStackTrace();}
			{
				BufferStrategy bs = getBufferStrategy();
				if (bs == null) {
					createBufferStrategy(3);
					continue;
				}
				frames++;
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				g.transform(sc);
				render(g);
				g.dispose();
				bs.show();
			}
			if (System.currentTimeMillis()-timer >= 1000){
				timer+= 1000;
				frame.setTitle(NAME + " - " + VERSION + " | " + updates + " ticks, " + frames + " frames.");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	
	public void update(){
		keyboard.update();
		getState().tick();
		
		//TODO: Update
	}
	
	private State getState() {
		return currentState;
	}

	public void render(Graphics2D g){
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		getState().render(g);
	}

	public static ExcX getInstance() {
		return me;
	}
}
