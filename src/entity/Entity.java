package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import objekte.Schlag;

public class Entity {
	/** Deklaration der Variablen */
	public int weltX, weltY;
	public int bildschirmX;
	public int bildschirmY;
	public int geschwindigkeit;
	public boolean kollision = true;

	public BufferedImage up, upLV, upRV, down, downLV, downRV, left, leftLV, leftRV, right, rightLV, rightRV;
	public String richtung;

	public int frameCounter = 0;
	public int spriteNumber = 0;

	public int hitCooldownFrames = 0;
	public double hitCooldownSekunden = 0.7;
	public Schlag schlag;
	public int rueckstoss;
	public int rundenAnzahlGetroffen;
	public int rundenMaxAnzahlgetroffen = 4;
	public String stossRichtung;
	public Entity entityGetroffen;
	public int framesBewegungsunfaehig = 0;
	public double schlageSpielerCooldownSec = 0.5;

	public Rectangle hitBox;

	public boolean kollidiert = false;
	GamePanel gp;

	public int leben;
	public int lebensanzeigeBreite;
	public int lebensanzeigeHoehe;
	public int bogenBreite = 4;
	public int bogenHoehe = 4;

	public int zaeler = 0;

	public static int monsterBesiegt = 0;
	int besiegt;

	int muenzenAnzahlProMonster = 0;
	public static int muenzen = 130;

	/**
	 * Constructor von Entity, bei dem bestimmte Variablen festgelegt werden.
	 * @param gp GamePanel wird �bergeben.
	 */
	public Entity(GamePanel gp) {
		this.gp = gp;
		leben = gp.skala;
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroesse / 4;
		hitBox.y = gp.feldGroesse / 2;
		hitBox.height = gp.feldGroesse / 2;
		hitBox.width = gp.feldGroesse / 2;

		rueckstoss = 16;

	}

	/**
	 * Update Methode von Entity, diese ist leer. Wird von erbenden
	 * Klassen genutzt, wenn diese keine Aktionen ausf�hren oder andernfalls
	 * �berschrieben. ======= Super-update Methode von Entity, welche von erbenden
	 * Klassen genutzt werden kann. 
	 */
	public void update() {

	}

	/**
	 * Es wird das Bild des Entities mit verschachtelten Switch-Case
	 * Verzeigungen bestimmt und, wenn es sich innerhalb des Bildschirms befindet,
	 * gezeichnet.
	 * @param g2 Graphics2D wird zum Zeichnen verwendet ======= Es wird das Bild des
	 *           Entities bestimmt und daraufhin, wenn es sich innerhalb des
	 *           Bildschirms befindet, gezeichnet.
	 * @param g2 Graphics2D wird zum zeichnen verwendet.
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
		if (charSprite != null) {

			int bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX - (gp.feldGroesse / 2);
			int bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY - (gp.feldGroesse / 2);

			if (weltX + gp.feldGroesse > gp.kamera.weltX - gp.kamera.bildschirmX
					&& weltX - gp.feldGroesse < gp.kamera.weltX + gp.kamera.bildschirmX
					&& weltY + gp.feldGroesse > gp.kamera.weltY - gp.kamera.bildschirmY
					&& weltY - gp.feldGroesse < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(charSprite, bildschirmX, bildschirmY, gp.feldGroesse, gp.feldGroesse, null);
			}
		}

	}

	/**
	 * Wenn eine der Pfeiltasten gedr�ckt wird, wird ein Schlag ausgef�hrt, also ein
	 * Faust-Objekt erstellt. Trifft dieses ein Entity, wird die getroffen()-Methode
	 * des Entity ausgef�hrt. Der Schlag hat einen Cooldown.
	 */
	public void schlage() {
		bildschirmX = weltX - gp.kamera.weltX - (gp.feldGroesse / 2) + gp.kamera.bildschirmX;
		bildschirmY = weltY - gp.kamera.weltY - (gp.feldGroesse / 2) + gp.kamera.bildschirmY;
		if (hitCooldownFrames <= 0) {

			if (gp.keyH.pfeilHochGedrueckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroesse / 2), weltY - (gp.feldGroesse / 2) - (gp.feldGroesse / 2));
				richtung = "oben";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
			} else if (gp.keyH.pfeilRunterGedrueckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroesse / 2), weltY - (gp.feldGroesse / 2) + (gp.feldGroesse));
				richtung = "unten";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
			} else if (gp.keyH.pfeilLinksGedrueckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroesse / 2) - (gp.feldGroesse / 2) - (gp.feldGroesse / 4),
						weltY - (gp.feldGroesse / 2));
				richtung = "links";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
			} else if (gp.keyH.pfeilRechtsGedrueckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroesse / 2) + (gp.feldGroesse / 2) + (gp.feldGroesse / 4),
						weltY - (gp.feldGroesse / 2));
				richtung = "rechts";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
			}

			if (schlag != null) {
				schlag.schlagRichtung = richtung;
				for (int i = 0; i < gp.entities.length; i++) {
					if (gp.entities[i] != null) {
						int altEntHitBoxX = gp.entities[i].hitBox.x;
						int altEntHitBoxY = gp.entities[i].hitBox.y;
						gp.entities[i].hitBox.x += gp.entities[i].weltX - (gp.feldGroesse / 2);
						gp.entities[i].hitBox.y += gp.entities[i].weltY - (gp.feldGroesse / 2);
						if (schlag.hitBox.intersects(gp.entities[i].hitBox)) {
							gp.entities[i].hitBox.x = altEntHitBoxX;
							gp.entities[i].hitBox.y = altEntHitBoxY;
							gp.entities[i].getroffen(this, schlag);

							if (gp.entities[i].leben <= 0) {
								gp.entities[i] = null;
								besiegt++;
								setBesiegteMonster(besiegt);

								muenzenAnzahlProMonster = (int) (Math.random() * 3 + 1);
								muenzen += muenzenAnzahlProMonster;
							}

						} else {
							gp.entities[i].hitBox.x = altEntHitBoxX;
							gp.entities[i].hitBox.y = altEntHitBoxY;
						}

					}

				}
			}
		}

		if (hitCooldownFrames < 9 * ((hitCooldownSekunden * gp.FPS) / 10)) {
			schlag = null;
			kollidiert = false;
		} else {
			kollidiert = true;
		}
		if (hitCooldownFrames > 0) {
			hitCooldownFrames--;
		}
	}

	/**
	 * Die Leben des getroffenen Entity werden verringert und Variablen f�r die
	 * R�cksto�animation gesetzt.
	 * 
	 * @param entity Das Entity, welches dieses Entity, in dem diese Methode
	 *               ausgef�hrt wird, geschlagen hat. Bisher immer der Spieler.
	 * @param schlag Das Schlag-Objekt, welches das Entity getroffen hat.
	 */
	public void getroffen(Entity entity, Schlag schlag) {
		rundenAnzahlGetroffen = 4;
		stossRichtung = schlag.schlagRichtung;
		entityGetroffen = entity;

		leben -= gp.player.schaden;

	}

	/**
	 * Die Bilder des Entity wird �ber einen Dateipfad aufgerufen, skaliert und
	 * zur�ckgegeben.
	 * 
	 * @param bildName Der Bildname mit Dateipfad.
	 * @return BufferedImage Das zur�ckgegebene Bild.
	 */
	public BufferedImage setup(String bildName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage bild = null;

		try {
			bild = ImageIO.read(getClass().getResourceAsStream(bildName + ".png"));
			bild = uTool.skalaBild(bild, gp.feldGroesse, gp.feldGroesse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bild;
	}

	/**
	 * Eine Lebensanzeige wird gezichnet, daf�r werden verschiedene Parameter
	 * �bergeben, sodass diese theoretisch von allen Entities genutzt werden kann.
	 * 
	 * @param g2          Das Graphics2D-Objekt, mit dem gezeichnet wird.
	 * @param bildschirmX Position auf dem Bildschirm auf der X-Achse.
	 * @param bildschirmY Position auf dem Bildschirm auf der Y-Achse.
	 * @param breite      Breite des Hintergrunds der Lebensanzeige.
	 * @param hoehe       H�he der Lebensanzeige.
	 * @param leben       Breite der Lebensanzeige bzw. die Leben.
	 */
	public void lebensanzeige(Graphics2D g2, int bildschirmX, int bildschirmY, int breite, int hoehe, int leben) {
		g2.setColor(Color.BLACK);
		g2.drawRoundRect(bildschirmX, bildschirmY, breite, hoehe, bogenBreite, bogenHoehe);
		g2.setColor(Color.RED);
		g2.fillRoundRect(bildschirmX, bildschirmY, leben, hoehe, bogenBreite, bogenHoehe);
	}

	/**
	 * F�hrt f�r die ber�hrten Objekte bestimmte Aktionen aus, aktuell nur die
	 * Ausgangst�r: Das Entity kommt bei der Eingangst�r der Map wieder raus.
	 * 
	 * @param objGetroffen[] Boolean-Array, welches die Information beinhaltet,
	 *        welche Objekte ausgel�st/ber�hrt wurden.
	 */
	public void interagiereMitObjekt(boolean objGetroffen[]) {
		for (int i = 0; i < objGetroffen.length; i++) {
			if (objGetroffen[i] == true) {
				// System.out.println("objekt getroffen: " + i);
				switch (gp.objekte[i].name) {
				case "Ausgang":
					geheZuEingang();
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Das Entity wird an die Stelle neben dem Eingang des Raums gesetzt.
	 */
	public void geheZuEingang() {
		// ERMITTLE KOODRINATEN DES EINGANGS
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D004TuerOE");
		int indexTuerUnten = gp.feldM.getFeldIndex("D004TuerUE");
		int indexTuerLinks = gp.feldM.getFeldIndex("D004TuerLE");
		int indexTuerRechts = gp.feldM.getFeldIndex("D004TuerRE");
		while (spalte < gp.mapGroesse && reihe < gp.mapGroesse) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];

			if (feldNr == indexTuerOben) {
				weltX = spalte * gp.feldGroesse + (gp.feldGroesse / 2);
				weltY = (reihe + 1) * gp.feldGroesse + (gp.feldGroesse / 2);
			} else if (feldNr == indexTuerUnten) {
				weltX = spalte * gp.feldGroesse + (gp.feldGroesse / 2);
				weltY = (reihe - 1) * gp.feldGroesse + (gp.feldGroesse / 2);
			} else if (feldNr == indexTuerLinks) {
				weltX = (spalte + 1) * gp.feldGroesse + (gp.feldGroesse / 2);
				weltY = reihe * gp.feldGroesse + (gp.feldGroesse / 2);

			} else if (feldNr == indexTuerRechts) {
				weltX = (spalte - 1) * gp.feldGroesse + (gp.feldGroesse / 2);
				weltY = reihe * gp.feldGroesse + (gp.feldGroesse / 2);
			}

			spalte++;
			if (spalte == gp.mapGroesse) {
				spalte = 0;
				reihe++;
			}
		}
	}

	/**
	 * Setzt die Klassenvariable auf den �bergebenen Wert. Eigentlich nicht n�tig,
	 * da diese public ist, entstand durch Tests und wurde nicht mehr ge�ndert.
	 * 
	 * @param besiegt Anzahl, wieviele Monster im aktuellen Raum besiegt wurden.
	 */
	public static void setBesiegteMonster(int besiegt) {
		monsterBesiegt = besiegt;
	}

	/**
	 * Gibt die Klassenvariable monsterBesiegt zur�ck. Eigentlich nicht n�tigt, da
	 * public, entstand durch Tests.
	 * 
	 * @return Der Wert der Klassenvariable monsterBesiegt.
	 */
	public static int getBesiegteMonster() {

		return monsterBesiegt;
	}
}
