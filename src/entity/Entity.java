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
	public double hitCooldownSekunden = 0.5;
	public Schlag schlag;
	public int r¸ckstoﬂ;
	public int rundenAnzahlGetroffen;
	public int rundenMaxAnzahlgetroffen = 4;
	public String stoﬂRichtung;
	public Entity entityGetroffen;
	public int framesBewegungsunfaehig = 0;

	public Rectangle hitBox;

	public boolean kollidiert = false;
	GamePanel gp;

	public int leben;
	public int lebensanzeigeBreite;
	public int lebensanzeigeHoehe;
	public int bogenBreite = 4;
	public int bogenHoehe = 4;

	public int zaeler = 0;

	public static int monsterBesiegt;
	int besiegt;

	public Entity(GamePanel gp) {
		this.gp = gp;
		leben = gp.skala;
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroeﬂe / 4;
		hitBox.y = gp.feldGroeﬂe / 2;
		hitBox.height = gp.feldGroeﬂe / 2;
		hitBox.width = gp.feldGroeﬂe / 2;

		r¸ckstoﬂ = 16;

	}

	public void update() {

	}

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

			int bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX - (gp.feldGroeﬂe / 2);
			int bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY - (gp.feldGroeﬂe / 2);

			if (weltX + gp.feldGroeﬂe > gp.kamera.weltX - gp.kamera.bildschirmX
					&& weltX - gp.feldGroeﬂe < gp.kamera.weltX + gp.kamera.bildschirmX
					&& weltY + gp.feldGroeﬂe > gp.kamera.weltY - gp.kamera.bildschirmY
					&& weltY - gp.feldGroeﬂe < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(charSprite, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
			}
		}

	}

	public void schlage() {
		bildschirmX = weltX - gp.kamera.weltX - (gp.feldGroeﬂe / 2) + gp.kamera.bildschirmX;
		bildschirmY = weltY - gp.kamera.weltY - (gp.feldGroeﬂe / 2) + gp.kamera.bildschirmY;
		if (
		// gp.keyH.obenGedr¸ckt == false && gp.keyH.untenGedr¸ckt == false &&
		// gp.keyH.linksGedr¸ckt == false && gp.keyH.rechtsGedr¸ckt == false &&
		hitCooldownFrames <= 0) {

			if (gp.keyH.pfeilHochGedr¸ckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroeﬂe / 2), weltY - (gp.feldGroeﬂe / 2) - (gp.feldGroeﬂe / 2));
				richtung = "oben";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
				System.out.println("schlag");
			} else if (gp.keyH.pfeilRunterGedr¸ckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroeﬂe / 2), weltY - (gp.feldGroeﬂe / 2) + (gp.feldGroeﬂe));
				richtung = "unten";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
				System.out.println("schlag");
			} else if (gp.keyH.pfeilLinksGedr¸ckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroeﬂe / 2) - (gp.feldGroeﬂe / 2) - (gp.feldGroeﬂe / 4),
						weltY - (gp.feldGroeﬂe / 2));
				richtung = "links";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
				System.out.println("schlag");
			} else if (gp.keyH.pfeilRechtsGedr¸ckt) {
				schlag = new Schlag(gp, weltX - (gp.feldGroeﬂe / 2) + (gp.feldGroeﬂe / 2) + (gp.feldGroeﬂe / 4),
						weltY - (gp.feldGroeﬂe / 2));
				richtung = "rechts";
				hitCooldownFrames = (int) (hitCooldownSekunden * gp.FPS);
				System.out.println("schlag");
			}

			if (schlag != null) {
				schlag.schlagRichtung = richtung;
				for (int i = 0; i < gp.entities.length; i++) {
					if (gp.entities[i] != null) {
						int altEntHitBoxX = gp.entities[i].hitBox.x;
						int altEntHitBoxY = gp.entities[i].hitBox.y;
						gp.entities[i].hitBox.x += gp.entities[i].weltX - (gp.feldGroeﬂe / 2);
						gp.entities[i].hitBox.y += gp.entities[i].weltY - (gp.feldGroeﬂe / 2);
						if (schlag.hitBox.intersects(gp.entities[i].hitBox)) {
							gp.entities[i].hitBox.x = altEntHitBoxX;
							gp.entities[i].hitBox.y = altEntHitBoxY;
							gp.entities[i].getroffen(this, schlag);

							if (gp.entities[i].leben <= 0) {
								gp.entities[i] = null;
								besiegt++;
								setBesiegteMonster(besiegt);
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

	public void getroffen(Entity entity, Schlag schlag) {
		rundenAnzahlGetroffen = 4;
		stoﬂRichtung = schlag.schlagRichtung;
		entityGetroffen = entity;

		leben -= 1;
		System.out.println("Leben =" + leben);

	}

	public BufferedImage setup(String bildName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage bild = null;

		try {
			bild = ImageIO.read(getClass().getResourceAsStream(bildName + ".png"));
			bild = uTool.skalaBild(bild, gp.feldGroeﬂe, gp.feldGroeﬂe);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bild;
	}

	public void lebensanzeige(Graphics2D g2, int bildschirmX, int bildschirmY, int breite, int hoehe, int leben) {
		g2.setColor(Color.BLACK);
		g2.drawRoundRect(bildschirmX, bildschirmY, breite, hoehe, bogenBreite, bogenHoehe);
		g2.setColor(Color.RED);
		g2.fillRoundRect(bildschirmX, bildschirmY, leben, hoehe, bogenBreite, bogenHoehe);

	}

	public void interagiereMitObjekt(boolean objGetroffen[]) {
		for (int i = 0; i < objGetroffen.length; i++) {
			if (objGetroffen[i] == true) {
				System.out.println("objekt getroffen: " + i);
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

	public void geheZuEingang() {
		// ERMITTLE KOODRINATEN DES EINGANGS
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D004TuerOE");
		int indexTuerUnten = gp.feldM.getFeldIndex("D004TuerUE");
		int indexTuerLinks = gp.feldM.getFeldIndex("D004TuerLE");
		int indexTuerRechts = gp.feldM.getFeldIndex("D004TuerRE");
		while (spalte < gp.mapGroeﬂe && reihe < gp.mapGroeﬂe) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];

			if (feldNr == indexTuerOben) {
				weltX = spalte * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
				weltY = (reihe + 1) * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
			} else if (feldNr == indexTuerUnten) {
				weltX = spalte * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
				weltY = (reihe - 1) * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
			} else if (feldNr == indexTuerLinks) {
				weltX = (spalte + 1) * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
				weltY = reihe * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);

			} else if (feldNr == indexTuerRechts) {
				weltX = (spalte - 1) * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
				weltY = reihe * gp.feldGroeﬂe + (gp.feldGroeﬂe / 2);
			}

			spalte++;
			if (spalte == gp.mapGroeﬂe) {
				spalte = 0;
				reihe++;
			}
		}
	}

	public static void setBesiegteMonster(int besiegt) {
		monsterBesiegt = besiegt;
	}

	public static int getBesiegteMonster() {

		return monsterBesiegt;
	}
}
