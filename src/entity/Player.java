package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	KeyHandler keyH;
	Kamera kamera;
	public int bildschirmX;
	public int bildschirmY;
	public int framesUnbewegt;
	public int schaden = 1;

	/**
	 * Konstruktor für Player
	 * 
	 * @param gp
	 * @param keyH
	 */
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;

		bildschirmX = gp.BildBreite / 2;
		bildschirmY = gp.BildHoehe / 2;
		framesUnbewegt = 0;

		// Hitbox des Spielers festlegen
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroeße / 4;
		hitBox.y = gp.feldGroeße / 2;
		hitBox.height = gp.feldGroeße / 2;
		hitBox.width = gp.feldGroeße / 2;

		setDefaultValuables();
		getPlayerImage();
	}

	/**
	 * Holt sich das Kamera-Objekt vom GamePanel
	 */
	public void receiveKamera() {
		kamera = gp.giveKamera();
	}

	/**
	 * Setzt einige Standardwerte wie Geschwindigkeit und Leben.
	 */
	public void setDefaultValuables() {
		geschwindigkeit = gp.skala * 2;
		richtung = "unten";

		leben = gp.feldGroeße;
		lebensanzeigeBreite = gp.feldGroeße;
		lebensanzeigeHoehe = gp.skala * 2;
	}

	/**
	 * Die Charactersprites werden aus dem res-Ordner in deren Variablen geladen
	 */
	public void getPlayerImage() {

		up = setup("/player/char-Up");
		upLV = setup("/player/char-UpLV");
		upRV = setup("/player/char-UpRV");
		down = setup("/player/char-Down");
		downLV = setup("/player/char-DownLV");
		downRV = setup("/player/char-DownRV");
		left = setup("/player/char-Left");
		leftLV = setup("/player/char-LeftLV");
		leftRV = setup("/player/char-LeftRV");
		right = setup("/player/char-Right");
		rightLV = setup("/player/char-RightLV");
		rightRV = setup("/player/char-RightRV");

	}
	/**
	 * Für die Objekte, die ausgelöst wurden, wird eine spezifische Aktion ausgeführt. Aktuell nur Ausgangstüren: Neue Map und Türen, Monster usw. neu platziert.
	 * @param objGetroffen[] Boolean-Array, welches die Information beinhaltet, welche Objekte ausgelöst/berührt wurden.
	 */
	public void interagiereMitObjekt(boolean objGetroffen[]) {
		for (int i = 0; i < objGetroffen.length; i++) {
			if (objGetroffen[i] == true) {
				System.out.println("objekt getroffen: " + i);
				switch (gp.objekte[i].name) {
				case "Ausgang":
					gp.platzierer.aktuelleMonsterImRaum = gp.platzierer.randomMonsterAnzahl
							- Entity.getBesiegteMonster();

					if (Entity.getBesiegteMonster() >= gp.platzierer.aktuelleMonsterImRaum || gp.feldM.mapNr == 0
							|| gp.feldM.mapNr > 12) {
						//------------------------------------------------- NEUE MAP WIRD GELADEN
						Entity.monsterBesiegt = 0;
						gp.feldM.loadMap();
						gp.platzierer.setzeAusgang();
						gp.platzierer.setzeObjekt();
						gp.platzierer.setzeEntity();
						gp.player.geheZuEingang(true);
					}
					break;
				default:
					break;
				}
			}
		}
	}

	public void geheZuEingang(boolean auchKamera) {
		// ERMITTLE KOODRINATEN DES EINGANGS
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D004TuerOE");
		int indexTuerUnten = gp.feldM.getFeldIndex("D004TuerUE");
		int indexTuerLinks = gp.feldM.getFeldIndex("D004TuerLE");
		int indexTuerRechts = gp.feldM.getFeldIndex("D004TuerRE");
		while (spalte < gp.mapGroeße && reihe < gp.mapGroeße) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];

			if (feldNr == indexTuerOben) {
				weltX = spalte * gp.feldGroeße + (gp.feldGroeße / 2);
				weltY = (reihe + 1) * gp.feldGroeße + (gp.feldGroeße / 2);
				if (weltX > (gp.BildBreite / 2) && weltX < (gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2))) {
					gp.kamera.weltX = weltX;
				} else if (weltX < (gp.BildBreite / 2)) {
					gp.kamera.weltX = gp.BildBreite / 2;
				} else if (weltX > (gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2))) {
					gp.kamera.weltX = gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2);
				}
				gp.kamera.weltY = gp.BildHoehe / 2;
			} else if (feldNr == indexTuerUnten) {
				weltX = spalte * gp.feldGroeße + (gp.feldGroeße / 2);
				weltY = (reihe - 1) * gp.feldGroeße + (gp.feldGroeße / 2);
				if (weltX > (gp.BildBreite / 2) && weltX < (gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2))) {
					gp.kamera.weltX = weltX;
				} else if (weltX < (gp.BildBreite / 2)) {
					gp.kamera.weltX = gp.BildBreite / 2;
				} else if (weltX > (gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2))) {
					gp.kamera.weltX = gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2);
				}
				gp.kamera.weltY = gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2);
			} else if (feldNr == indexTuerLinks) {
				weltX = (spalte + 1) * gp.feldGroeße + (gp.feldGroeße / 2);
				weltY = reihe * gp.feldGroeße + (gp.feldGroeße / 2);
				gp.kamera.weltX = gp.BildBreite / 2;
				if (weltY > (gp.BildHoehe / 2) && weltY < (gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2))) {
					gp.kamera.weltY = weltY;
				} else if (weltY < (gp.BildHoehe / 2)) {
					gp.kamera.weltY = gp.BildHoehe / 2;
				} else if (weltY > (gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2))) {
					gp.kamera.weltY = gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2);
				}
			} else if (feldNr == indexTuerRechts) {
				weltX = (spalte - 1) * gp.feldGroeße + (gp.feldGroeße / 2);
				weltY = reihe * gp.feldGroeße + (gp.feldGroeße / 2);
				gp.kamera.weltX = gp.kamera.weltX = gp.mapGroeße * gp.feldGroeße - (gp.BildBreite / 2);
				if (weltY > (gp.BildHoehe / 2) && weltY < (gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2))) {
					gp.kamera.weltY = weltY;
				} else if (weltY < (gp.BildHoehe / 2)) {
					gp.kamera.weltY = gp.BildHoehe / 2;
				} else if (weltY > (gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2))) {
					gp.kamera.weltY = gp.mapGroeße * gp.feldGroeße - (gp.BildHoehe / 2);

				}
			}

			spalte++;
			if (spalte == gp.mapGroeße) {
				spalte = 0;
				reihe++;
			}
		}
	}

	/**
	 * Der Player wird jetzt in der methode setup skaliert, was die performance
	 * verbessern kann.
	 */

	/**
	 * Wenn tasten in die entsprechende Richtung gedrückt wurden, wird die
	 * Spielerposition neu berechnet. Außerdem wird einer Anzahl an frames die
	 * Variable für die Animation erhöht bzw zurückgesetzt. Wenn der Character eine
	 * gewisse Anzahl frames steht, so wird die Richtung auf "steht" gesetzt.
	 */
	public void update() {

		schlage();

		if ((keyH.obenGedrückt || keyH.untenGedrückt || keyH.linksGedrückt || keyH.rechtsGedrückt)
				&& kollidiert == false) {
			if (keyH.obenGedrückt == true) {
				richtung = "oben";
			} else if (keyH.untenGedrückt) {
				richtung = "unten";
			} else if (keyH.linksGedrückt) {
				richtung = "links";
			} else if (keyH.rechtsGedrückt) {
				richtung = "rechts";
			}
			kollidiert = false;

			// PRUEFE FELD KOLLISION
			gp.kPruefer.pruefeFeld(this);
			// PRUEFE OBJEKT KOLLISION
			boolean objGetroffen[] = gp.kPruefer.pruefeObjekt(this, true);
			interagiereMitObjekt(objGetroffen);
			// PRUEFE ENTITY KOLLISION
			gp.kPruefer.pruefeEntity(this);

			// WENN PLAYER NICHT KOLLIDIERT
			if (kollidiert == false) {
				switch (richtung) {
				case "oben":
					weltY -= geschwindigkeit;
					break;
				case "unten":
					weltY += geschwindigkeit;
					break;
				case "links":
					weltX -= geschwindigkeit;
					break;
				case "rechts":
					weltX += geschwindigkeit;
					break;
				}

			}
			framesUnbewegt = 0;
			frameCounter++;
			if (frameCounter > 8) {
				spriteNumber++;
				if (spriteNumber >= 4) {
					spriteNumber = 0;
				}
				frameCounter = 0;
			}
		}

		framesUnbewegt++;
		if (framesUnbewegt >= 16) {
			spriteNumber = 0;
		}

		if (keyH.hGedrückt == true) {
			gp.player.leben = gp.feldGroeße;
		}
	}

	/**
	 * Mithilfe von verschachtelten switch-case Verzweigungen wird das zu Ladende
	 * Sprite ausgewählt und Schlussendlich an entsprechender Position angezeigt.
	 * 
	 * @param g2 Das Graphics2D Objekt, mit welchem gezeichnet wird.
	 */
	public void draw(Graphics2D g2) {

		BufferedImage charSprite = null;

		switch (richtung) {
		case "oben":
			switch (spriteNumber) {
			case 0:
				charSprite = up;
				break;
			case 1:
				charSprite = upLV;
				break;
			case 2:
				charSprite = up;
				break;
			case 3:
				charSprite = upRV;
				break;
			}
			break;
		case "unten":
			switch (spriteNumber) {
			case 0:
				charSprite = down;
				break;
			case 1:
				charSprite = downLV;
				break;
			case 2:
				charSprite = down;
				break;
			case 3:
				charSprite = downRV;
				break;
			}
			break;
		case "links":
			switch (spriteNumber) {
			case 0:
				charSprite = left;
				break;
			case 1:
				charSprite = leftLV;
				break;
			case 2:
				charSprite = left;
				break;
			case 3:
				charSprite = leftRV;
				break;
			}
			break;
		case "rechts":
			switch (spriteNumber) {
			case 0:
				charSprite = right;
				break;
			case 1:
				charSprite = rightLV;
				break;
			case 2:
				charSprite = right;
				break;
			case 3:
				charSprite = rightRV;
				break;
			}
			break;
		}
		// bildX und bildY berechnen
		bildschirmX = weltX - kamera.weltX - (gp.feldGroeße / 2) + kamera.bildschirmX;
		bildschirmY = weltY - kamera.weltY - (gp.feldGroeße / 2) + kamera.bildschirmY;

		// ZEICHNE SCHLAG
		if (schlag != null && gp.feldM.mapNr <= 12) {
			schlag.draw(g2, this);
		}
		// ZEICHNE PLAYER

		g2.drawImage(charSprite, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
		// ZEICHNE LEBENSANZEIGE
		lebensanzeige(g2, bildschirmX, bildschirmY - gp.skala * 3, lebensanzeigeBreite, lebensanzeigeHoehe, leben);

		// g2.drawRect(bildschirmX + hitBox.x, bildschirmY + hitBox.y, hitBox.width,
		// hitBox.height);

	}

}
