package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	public final int originaleFeldGroeße = 16; 
	public final int skala = 5;
	
	final int feldGroeße = originaleFeldGroeße * skala;
	final int maxBildSpalte = 20;
	final int maxBildReihe = 12;
	final int BildBreite = feldGroeße * maxBildSpalte;
	final int BildHoehe = feldGroeße * maxBildReihe;
	
	public int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	
	Thread gameThread;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(BildBreite,BildHoehe));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		
		double zeichenInterval = 1000000000/FPS;
		double delta = 0;
		long letzteZeit = System.nanoTime();
		long momentaneZeit;
		
		while(gameThread != null) {
			
			momentaneZeit = System.nanoTime();
			
			delta += (momentaneZeit - letzteZeit) / zeichenInterval;
			letzteZeit = momentaneZeit;
			
			if(delta >= 1) {
				
				update();
				
				repaint();
				
				delta--;
			}

			
		
		}
	}
	public void update() {
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
	
	}
}
