package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Kamera;
import entity.Player;
import felder.FeldManager;

public class GamePanel extends JPanel implements Runnable{

	public final int originaleFeldGroeße = 16; 
	public final int skala = 7;
	
	public final int feldGroeße = originaleFeldGroeße * skala;
	public final int maxBildSpalte = 20;
	public final int maxBildReihe = 12;
	public final int BildBreite = 1920;//feldGroeße * maxBildSpalte;
	public final int BildHoehe = 1080;//feldGroeße * maxBildReihe;
	public final int mapGroeße = 30;
	
	public int FPS = 60;
	
	public FeldManager feldM = new FeldManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public Player player = new Player(this, keyH);
	public Kamera kamera = new Kamera(this, keyH, player);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(BildBreite,BildHoehe));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		player.receiveKamera();
		
	}
	public Kamera giveKamera() {
		return kamera;
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
		
		player.update();
		kamera.update();
		
	}
	/**Methode in der g2 die gewünschten sachen zeichnen kann.*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		feldM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
}
