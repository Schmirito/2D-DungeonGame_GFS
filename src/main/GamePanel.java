package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Entity;
import entity.Kamera;
import entity.Player;
import felder.FeldManager;
import objekte.SuperObjekt;

public class GamePanel extends JPanel implements Runnable {

	public final int originaleFeldGroe�e = 16;
	public final int skala = 7;

	public final int feldGroe�e = originaleFeldGroe�e * skala;
	public final int maxBildSpalte = 20;
	public final int maxBildReihe = 12;
	public final int BildBreite = 1920;// feldGroe�e * maxBildSpalte;
	public final int BildHoehe = 1080;// feldGroe�e * maxBildReihe;
	public final int mapGroe�e = 30;

	public int FPS = 60;
	public int gStatus = 0;

	public FeldManager feldM = new FeldManager(this);
	public KeyHandler keyH = new KeyHandler();
	public UI ui = new UI(this);

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
		keyH = new KeyHandler();
		player.weltX = 5 * feldGroe�e;
		player.weltY = 16 * feldGroe�e;
		kamera.weltX = 9 * feldGroe�e;
		kamera.weltY = 17 * feldGroe�e;
		player.leben = feldGroe�e;
		Entity.setBesiegteMonster(0);
		feldM.loadMap();
		platzierer.setzeAusgang();
		platzierer.setzeObjekt();
		platzierer.setzeEntity();
		Entity.muenzen = 0;
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

		if (gStatus == 0) { // GAME L�UFT
			if (player.leben <= 0) {
				//JLabel label = new JLabel("MESSAGE");
				//label.setBackground(new Color(0,0,0));
				//label.setFont(new Font("Arial", Font.BOLD, 18));
				//JOptionPane.showMessageDialog(null,label,"Game Over",JOptionPane.OK_OPTION);
				JOptionPane.showMessageDialog(null,"Game Over");
				setupGame();
			} else if (keyH.escGedrueckt) {
				gStatus = 1;
				keyH.escGedrueckt = false;
			}

			player.update();
			kamera.update();
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
		}
	}

	/** Methode in der g2 die gew�nschten sachen zeichnen kann. */
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

		// UI
		ui.draw(g2);

		g2.dispose();
	}
}
