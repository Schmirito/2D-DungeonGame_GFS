package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	/** Deklaration der Variablen */
	GamePanel gp;
	KeyHandler keyH;
	Kamera kamera;
	public int bildX;
	public int bildY;
	public int framesUnbewegt;

	/** Constructor mit Uebergabeparametern GamePanel und KeyHandler */
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		bildX = gp.BildBreite / 2;
		bildY = gp.BildHoehe / 2;
		framesUnbewegt = 0;

		// Hitbox des Spielers festlegen
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroeﬂe / 4;
		hitBox.y = gp.feldGroeﬂe / 2;
		hitBox.height = gp.feldGroeﬂe / 2;
		hitBox.width = gp.feldGroeﬂe / 2;

		setDefaultValuables();
		getPlayerImage();
	}

	public void receiveKamera() {
		kamera = gp.giveKamera();
	}

	public void setDefaultValuables() {
		weltX = bildX; // 13 * gp.feldGroeﬂe;
		weltY = bildY; // 13 * gp.feldGroeﬂe;
		geschwindigkeit = gp.skala;
		richtung = "unten";
	}

	/** Die Charactersprites werden aus dem res Ordner in deren variablen geladen */
	public void getPlayerImage() {

		up = setup("char-Up");
		upLV = setup("char-UpLV");
		upRV = setup("char-UpRV");
		down = setup("char-Down");
		downLV = setup("char-DownLV");
		downRV = setup("char-DownRV");
		left = setup("char-Left");
		leftLV = setup("char-LeftLV");
		leftRV = setup("char-LeftRV");
		right = setup("char-Right");
		rightLV = setup("char-RightLV");
		rightRV = setup("char-RightRV");

	}

	/**
	 * Der Player wird jetzt in der methode setup skaliert, was die performance
	 * verbessern kann.
	 */
	public BufferedImage setup(String bildName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage bild = null;

		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/player/" + bildName + ".png"));
			bild = uTool.skalaBild(bild, gp.feldGroeﬂe, gp.feldGroeﬂe);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bild;
	}

	/**
	 * Wenn tasten in die entsprechende Richtung gedr¸ckt wurden, wird die
	 * Spielerposition neu berechnet. Auﬂerdem wird einer Anzahl an frames die
	 * Variable f¸r die Animation erhˆht bzw zur¸ckgesetzt. Wenn der Character eine
	 * gewisse Anzahl frames steht, so wird die Richtung auf "steht" gesetzt.
	 */
	public void update() {
		if (keyH.obenGedr¸ckt || keyH.untenGedr¸ckt || keyH.linksGedr¸ckt || keyH.rechtsGedr¸ckt) {
			if (keyH.obenGedr¸ckt == true) {
				richtung = "oben";
			} else if (keyH.untenGedr¸ckt) {
				richtung = "unten";
			} else if (keyH.linksGedr¸ckt) {
				richtung = "links";
			} else if (keyH.rechtsGedr¸ckt) {
				richtung = "rechts";
			}
			// PRUEFE KOLLISION
			kollisionAn = false;
			gp.kPruefer.pruefeFeld(this);
			// WENN PLAYER NICHT KOLLIDIERT
			if (kollisionAn == false) {
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

	}

	/**
	 * Mithilfe von verschachtelten switch-case Verzweigungen wird das zu Ladende
	 * Sprite ausgew‰hlt und Schlussendlich an entsprechender Position angezeigt.
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
		bildX = weltX - kamera.weltX - (gp.feldGroeﬂe / 2) + kamera.bildschirmX;
		bildY = weltY - kamera.weltY - (gp.feldGroeﬂe / 2) + kamera.bildschirmY;

		g2.drawImage(charSprite, bildX, bildY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
	}

}
