package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.OptionPaneUI;

import entity.Entity;
import entity.Kamera;
import entity.Player;
import felder.FeldManager;
import objekte.SuperObjekt;

public class GamePanel extends JPanel implements Runnable {

	public final int originaleFeldGroesse = 16;
	public final int skala = 7;

	public final int feldGroesse = originaleFeldGroesse * skala;
	public final int maxBildSpalte = 20;
	public final int maxBildReihe = 12;
	public final int BildBreite = 1920;// feldGroe�e * maxBildSpalte;
	public final int BildHoehe = 1080;// feldGroe�e * maxBildReihe;
	public final int mapGroesse = 30;

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

	/**
	 * Konstruktor des GamePanels. Ruft setupGame() auf und veranlasst
	 * Einstellungen.
	 */
	public GamePanel() {
		setupGame();
		this.setPreferredSize(new Dimension(BildBreite, BildHoehe));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	/** Start-Variablen f�r das Spiel werden festgelegt, die Map geladen und Entity, Objekte und Ausg�nge gesetzt. */
	public void setupGame() {
		player.weltX = 5 * feldGroesse + (feldGroesse / 2);
		player.weltY = 16 * feldGroesse + (feldGroesse / 2);

		kamera.weltX = 9 * feldGroesse;
		kamera.weltY = 17 * feldGroesse;
		player.leben = feldGroesse;
		feldM.mapNr = 0;
		feldM.start = true;
		feldM.loadMap();

		platzierer.setzeAusgang();
		platzierer.setzeObjekt();
		platzierer.setzeEntity();
		Entity.muenzen = 0;
		
		feldM.raeumeGesamt = 0;
		
	}

	/**
	 * Startet einen Thread in welchem das Spiel l�uft. Der Thread durchl�uft die run()-Methode.
	 */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Es werden die FPS eingestellt, sodass das spiel nur so oft pro sekunde, wie
	 * gew�nscht, gezeichnet wird. Hier 60 mal.
	 */
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

	/**
	 * Wird von der run()-Methode aufgerufen. Veranlasst je nach Spielstatus z.B. den normalen Spielablauf durch aufrufen der update()-Methoden der einzelnen Klassen.
	 */
	public void update() {

		if (gStatus == 0) { // GAME L�UFT
			if (player.leben <= 0) {
				JLabel label = new JLabel("YOU DIED");
				label.setBackground(new Color(0, 0, 0));
				label.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, label, "Game Over", JOptionPane.OK_OPTION, null);
				// JOptionPane.showMessageDialog(null,"Game Over");
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
		} else if (gStatus == 2) { // DIALOG MIT NPC
			for (int i = 0; i < objekte.length; i++) {
				if (entities[i] != null) {
					if (entities[i].getClass().getSimpleName().equals("NPC")) {
						entities[i].update();
					}
				}
			}
		}
	}

	/**
	 * Wird von der Methode repaint() in run() aufgerufen. Hier wird das Spiel durch Aufrufen der draw()-Methoden der Klassen gerendert/gezeichnet.
	 * @param g Wird von der Bibliothek mitgegeben. Wird durch typecasting zum Graphics2D-Objekt.
	 */
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

	/**
	 * Hiermit lassen sich verschiedene Diaglog-Hintergr�nde rendern.
	 * 
	 * @param g2 Graphics2D-Objekt zum Render.
	 * @param x Parameter zur X-Position auf dem Bildschirm.
	 * @param y Parameter zur Y-Position auf dem Bildschirm.
	 * @param width Parameter zur Breite des Fensters.
	 * @param height Parameter zur H�he des Fensters.
	 */
	public void drawDialogWindow(Graphics2D g2, int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
	}
}
