package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*GamePanel erbt von JPanel und implementiert Runnable
 * Runnable f�gt eine run Methode hinzu, welche gestartet wird sobalt man das gameThread aktiviert.*/
public class GamePanel extends JPanel implements Runnable{

	/*Bildschirmeinstellungen:
	 * Jedes Feld soll originel mit 16x16 pixel erstellt werden, da das aber viel zu klein f�r heutige Bildschirme ist
	   wird das noch auf eine andere ger��e mit "skala" hoch skaliert.*/
	public final int originaleFeldGroe�e = 16; 
	public final int skala = 5;
	
	/*Die endg�ltige Feldgr��e betr�gt sich dann auf die originaleFeldgr��e * die Skala*/
	final int feldGroe�e = originaleFeldGroe�e * skala;
	
	/*Das Fenster soll eine spalten anzahl von 20 haben, das w�ren maxBildSpalte * feldgroe�e = 1600 pixel.
	 * Das selbe auch mit der Reihe. Sie hat maxBildReihe * feldGroe�e = 960 pixel.
	 * Das Fenster soll also f�rs erste 1600 pixel auf 960 pixel haben*/
	final int maxBildSpalte = 20;
	final int maxBildReihe = 12;
	final int BildBreite = feldGroe�e * maxBildSpalte;
	final int BildHoehe = feldGroe�e * maxBildReihe;
	
	/*FPS einstellen*/
	public int FPS = 60;
	
	/*GamePanel erstellt den KeyHandler und kennt ihn und seine methoden dadurch.*/
	KeyHandler keyH = new KeyHandler();
	
	/*Ein Thread wird erstellt
	 *Es ist daf�r da, dass nachdem es gestartet wird, einen Prozess solange zu wiederhohlen bis es wieder geschlossen wird.*/
	Thread gameThread;
	
	/*Der Konstruktor f�r GamePanel wird erstellt.*/
	public GamePanel() {
		/*Dieser Befehl setzt die gew�nschten Dimensionen dieser Klasse (JPanel's, da geerbt wird) fest.*/
		this.setPreferredSize(new Dimension(BildBreite,BildHoehe));
		
		/*Hintergrundfarbe schwarz
		 * "setDoubleBuffered" kann die render performance verbessern*/
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		/*Der KeyListener wird zu dem gamePanel hinzugef�gt.*/
		this.addKeyListener(keyH);
		
		/*Mit diesem Befehl kann sich GamePanel darauf fokusieren Tasten input zu bekommen*/
		this.setFocusable(true);
	}
	/*Eine Methode welche das gameThread startet*/
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	/*Diese Methode wird aufgerufen sobalt das Thread gestartet wird.*/
	@Override
	public void run() {
		
		/*Der Zeichenintervall wann gezeichnet werden soll, in dem fall alle 0.01666667 sekunden*/
		double zeichenInterval = 1000000000/FPS;
		double delta = 0;
		
		/*In die letzteZeit soll die Momentane Zeit gespeichert werden.*/
		long letzteZeit = System.nanoTime();
		long momentaneZeit;
		
		/*Solange das gameThread existiert wird die GameLoop wiederhohlt*/
		while(gameThread != null) {
			
			/*Es wird geschaut wie viel zeit bisher vergangen ist und das wird durch den zeicheninterval geteilt.
			 *Der daraus entstehende wert wird auf delta addiert.
			 *wenn delta == 1 ist, dann wird geupdated und gezeichnet, also alle 0.016666667 sekunden.*/
			momentaneZeit = System.nanoTime();
			
			delta += (momentaneZeit - letzteZeit) / zeichenInterval;
			letzteZeit = momentaneZeit;
			
			if(delta >= 1) {
				
				/*1. Es werden Informationen wie zum Beispiel die Spieler position geupdated.*/
				update();
				/*2. Der Bildschirm wird mithilfe der geupdateten Informationen Gezeichnet
				 *"repaint" ist mit "paintComponent" verkn�pft.*/
				repaint();
				
				delta--;
			}

			
		
		}
	}
	/*In dieser Methode werden Informationen geupdated*/
	public void update() {
		
		
	}
	/*In dieser Methode wird gezeichnet*/
	public void paintComponent(Graphics g) {
		/*greift auf die superclass paintComponent zu.*/
		super.paintComponent(g);
		
		/*Erlaubt 2D grafiken zu zeichnen*/ 
		Graphics2D g2 = (Graphics2D)g;
		
	
	}
}
