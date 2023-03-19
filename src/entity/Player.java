package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	/** Deklaration der Variablen */
	GamePanel gp;
	KeyHandler keyH;
	public int bildX;
	public int bildY;
	public int framesUnbewegt;

	/** Constructor mit Uebergabeparametern GamePanel und KeyHandler */
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		bildX = gp.BildBreite / 2 - (gp.feldGroeße / 2);
		bildY = gp.BildHoehe / 2 - (gp.feldGroeße / 2);
		framesUnbewegt = 0;

		setDefaultValuables();
		getPlayerImage();
	}

	public void setDefaultValuables() {
		weltX = bildX; //13 * gp.feldGroeße;
		weltY = bildY; //13 * gp.feldGroeße;
		geschwindigkeit = gp.skala;
		richtung = "steht";
	}

	/** Die Charactersprites werden aus dem res Ordner in deren variablen geladen */
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/player/char-Up.png"));
			upLV = ImageIO.read(getClass().getResourceAsStream("/player/char-UpLV.png"));
			upRV = ImageIO.read(getClass().getResourceAsStream("/player/char-UpRV.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/player/char-Down.png"));
			downLV = ImageIO.read(getClass().getResourceAsStream("/player/char-DownLV.png"));
			downRV = ImageIO.read(getClass().getResourceAsStream("/player/char-DownRV.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/player/char-Left.png"));
			leftLV = ImageIO.read(getClass().getResourceAsStream("/player/char-LeftLV.png"));
			leftRV = ImageIO.read(getClass().getResourceAsStream("/player/char-LeftRV.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/player/char-Right.png"));
			rightLV = ImageIO.read(getClass().getResourceAsStream("/player/char-RightLV.png"));
			rightRV = ImageIO.read(getClass().getResourceAsStream("/player/char-RightRV.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Wenn tasten in die entsprechende Richtung gedrückt wurden, wird die Spielerposition neu berechnet.
	 * Außerdem wird einer Anzahl an frames die Variable für die Animation erhöht bzw zurückgesetzt. 
	 * Wenn der Character eine gewisse Anzahl frames steht, so wird die Richtung auf "steht" gesetzt. */
	public void update() {
		if (keyH.obenGedrückt || keyH.untenGedrückt || keyH.linksGedrückt || keyH.rechtsGedrückt) {
			if (keyH.obenGedrückt == true) {
				richtung = "oben";
				weltY -= geschwindigkeit;
			} else if (keyH.untenGedrückt) {
				richtung = "unten";
				weltY += geschwindigkeit;
			} else if (keyH.linksGedrückt) {
				richtung = "links";
				weltX -= geschwindigkeit;
			} else if (keyH.rechtsGedrückt) {
				richtung = "rechts";
				weltX += geschwindigkeit;
			}
			bildX = weltX;
			bildY = weltY;
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
			richtung = "steht";
		}
		
	}
	 
	/** Mithilfe von verschachtelten switch-case Verzweigungen wird das zu Ladende Sprite ausgewählt und
	 * Schlussendlich an entsprechender Position angezeigt. */
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
		case "steht":
			charSprite = down;
			break;
		}

		g2.drawImage(charSprite, bildX, bildY, gp.feldGroeße, gp.feldGroeße, null);
	}

}
