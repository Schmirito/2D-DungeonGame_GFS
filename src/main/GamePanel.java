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
	public UI ui = new UI(this);

	Thread gameThread;
	public KollisionPruefer kPruefer = new KollisionPruefer(this);
	public Platzierer platzierer = new Platzierer(this);
	public Player player = new Player(this, keyH);
	public Kamera kamera = new Kamera(this, keyH, player);
	// ENTITY, OBJEKTE, ...
	public SuperObjekt objekte[] = new SuperObjekt[10]; // maximale Anzahl an Objekten: 10
	public Entity entities[] = new Entity[20];


	/**Constructor des GamePanels.*/
	public GamePanel() {
		player.receiveKamera();
		setupGame();
		this.setPreferredSize(new Dimension(BildBreite, BildHoehe));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	
	public Kamera giveKamera() {
		return kamera;
	}

	/**Start-Variablen für das Spiel werden festgelegt. */
	public void setupGame() {
		feldM = new FeldManager(this);
		keyH.linksGedrückt = false;
		keyH.rechtsGedrückt = false;
		keyH.obenGedrückt = false;
		keyH.untenGedrückt = false;

		player.weltX = 5 * feldGroeße + (feldGroeße/2);
		player.weltY = 16 * feldGroeße + (feldGroeße/2);
		
		kamera.weltX = 9 * feldGroeße;
		kamera.weltY = 17 * feldGroeße;
		player.leben = feldGroeße;
		Entity.setBesiegteMonster(0);

		Entity.monsterBesiegt = 0;
		
		feldM.loadMap();

		platzierer.setzeAusgang();
		platzierer.setzeObjekt();
		platzierer.setzeEntity();
		Entity.muenzen = 0;
	}

	/**Startet ein Thread durch welches das spiel gestartet wird.
	 * Sobald es erstellt wird, springt das programm in die run Methode. */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**Es werden die FPS eingestellt, sodass das spiel nur so oft pro sekunde, wie gewünscht, gezeichnet wird. */
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

	/**Je nach momentanem Spiel status wird das Spiel anders geupdated. */
	public void update() {

		if (gStatus == 0) { // GAME LÄUFT
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

	/** Methode in der g2 die gewünschten sachen zeichnen kann. 
	 * @param g2
	 * */
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

	/**Zeichnet das Dialogfenster des NPC's 
	 * @param g2
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * */
	public void drawDialogWindow(Graphics2D g2, int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
	}
}
