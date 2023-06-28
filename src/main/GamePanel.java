package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Entity;
import entity.Kamera;
import entity.Player;
import felder.FeldManager;
import objekte.SuperObjekt;

public class GamePanel extends JPanel implements Runnable {

	public final int originaleFeldGroeße = 16;
	public final int skala = 7;

	public final int feldGroeße = originaleFeldGroeße * skala;
	public final int maxBildSpalte = 20;
	public final int maxBildReihe = 12;
	public final int BildBreite = 1920;// feldGroeße * maxBildSpalte;
	public final int BildHoehe = 1080;// feldGroeße * maxBildReihe;
	public final int mapGroeße = 30;

	public int FPS = 60;
	public int gStatus = 0;

	public FeldManager feldM = new FeldManager(this);
	public KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public KollisionPruefer kPruefer = new KollisionPruefer(this);
	public Platzierer platzierer = new Platzierer(this);
	public Player player = new Player(this, keyH);
	public Kamera kamera = new Kamera(this, keyH, player);
	// ENTITY, OBJEKTE, ...
	public SuperObjekt objekte[] = new SuperObjekt[10]; // maximale Anzahl an Objekten: 10
	public Entity entities[] = new Entity[20];
	
	public GamePanel() {
		setupGame();
		this.setPreferredSize(new Dimension(BildBreite, BildHoehe));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		player.receiveKamera();

	}

	public Kamera giveKamera() {
		return kamera;
	}

	public void setupGame() {
		feldM = new FeldManager(this);
		player.weltX = 14*feldGroeße;
		player.weltY = 16*feldGroeße;
		kamera.weltX = 14*feldGroeße;
		kamera.weltY = 16*feldGroeße;
		player.leben = feldGroeße;
		platzierer.setzeAusgang();
		platzierer.setzeObjekt();
		platzierer.setzeEntity();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double zeichenInterval = 1000000000 / FPS;
		double delta = 0;
		long letzteZeit = System.nanoTime();
		long momentaneZeit;

		while (gameThread != null) {

			momentaneZeit = System.nanoTime();

			delta += (momentaneZeit - letzteZeit) / zeichenInterval;
			letzteZeit = momentaneZeit;

			if (delta >= 1) {

				update();
	
				repaint();

				delta--;
			}

		}
	}

	public void update() {

		if (gStatus == 0) { // GAME LÄUFT
			if (player.leben <= 0) {
				gStatus = 2;
				JOptionPane.showMessageDialog(null, "Game Over");
				setupGame();
				gStatus = 0;
			} else if (keyH.escGedrueckt) {
				gStatus = 1;
				keyH.escGedrueckt = false;
			}
			
			player.update();
			kamera.update();
			System.out.println(feldM.dungeonRaumAnz);
			// ENTITIES
			for (int i = 0; i < objekte.length; i++) {
				if (entities[i] != null) {
					entities[i].update();
				}
			}
		} else if (gStatus == 1) { // GAME PAUSIERT
			if (keyH.escGedrueckt) {
				gStatus = 0;
				keyH.escGedrueckt = false;
			}
		} else if (gStatus == 2) { // SPIELER GESTORBEN
			if (keyH.escGedrueckt) {
				gStatus = 0;
				keyH.escGedrueckt = false;
				setupGame();
			}
		}

	}

	/** Methode in der g2 die gewünschten sachen zeichnen kann. */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// FELDER
		feldM.draw(g2);
		// OBJEKTE
		for (int i = 0; i < objekte.length; i++) {
			if (objekte[i] != null) {
				objekte[i].draw(g2);
			}
		}
		// ENTITIES
		for (int i = 0; i < objekte.length; i++) {
			if (entities[i] != null) {
				entities[i].draw(g2);
			}
		}
		// SPIELER
		player.draw(g2);

		g2.dispose();
	}
}
