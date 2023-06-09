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

	/** Deklaration der Variablen */
	KeyHandler keyH;
	Kamera kamera;
	public int bildschirmX;
	public int bildschirmY;
	public int framesUnbewegt;

	

	/** Constructor mit Uebergabeparametern GamePanel und KeyHandler */
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

	public void receiveKamera() {
		kamera = gp.giveKamera();
	}

	public void setDefaultValuables() {

		weltX = gp.feldGroeße*5-(gp.feldGroeße/2); // 13 * gp.feldGroeße;
		weltY = gp.feldGroeße*16; // 13 * gp.feldGroeße;
		geschwindigkeit = gp.skala*2;
		richtung = "unten";
		
		leben = gp.feldGroeße;
		lebensanzeigeBreite = gp.feldGroeße;
		lebensanzeigeHoehe = gp.skala*2;
	}

	/** Die Charactersprites werden aus dem res Ordner in deren variablen geladen */
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
		
		if ((keyH.obenGedrückt || keyH.untenGedrückt || keyH.linksGedrückt || keyH.rechtsGedrückt) && kollidiert == false) {
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

	}

	/**
	 * Mithilfe von verschachtelten switch-case Verzweigungen wird das zu Ladende
	 * Sprite ausgewählt und Schlussendlich an entsprechender Position angezeigt.
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
		if (schlag != null) {
			schlag.draw(g2,this);
		}
		g2.drawImage(charSprite, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);

		lebensanzeige(g2, bildschirmX, bildschirmY-gp.skala*3, lebensanzeigeBreite, lebensanzeigeHoehe, leben);

		//g2.drawRect(bildschirmX + hitBox.x, bildschirmY + hitBox.y, hitBox.width, hitBox.height);


	}

}
