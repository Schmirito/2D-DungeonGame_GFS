package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Ghost extends Entity {

	int diffSpielerX;
	int diffSpielerY;
	String richtungX, richtungY;

	int zaeler = 0;

	public Ghost(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroeße / 4;
		hitBox.y = gp.feldGroeße / 2;
		hitBox.height = gp.feldGroeße / 2;
		hitBox.width = gp.feldGroeße / 2;

		richtung = "unten";
		geschwindigkeit = gp.skala;

		getImage();
	}

	public void update() {
		diffSpielerX = weltX - gp.player.weltX;
		diffSpielerY = weltY - gp.player.weltY;

		if (diffSpielerX <= 0) {
			richtungX = "rechts";
			diffSpielerX = gp.player.weltX - weltX;
		} else {
			richtungX = "links";
		}

		if (diffSpielerY <= 0) {
			richtungY = "unten";
			diffSpielerY = gp.player.weltY - weltY;
		} else {
			richtungY = "oben";
		}

		if (diffSpielerX > diffSpielerY) {
			richtung = richtungX;
		} else if (diffSpielerY > diffSpielerX) {
			richtung = richtungY;
		}

		kollidiert = false;
		
		
		// PRUEFE FELD KOLLISION
		//gp.kPruefer.pruefeFeld(this);
		
		// PRUEFE OBJEKT KOLLISION
		gp.kPruefer.pruefeObjekt(this, false);
		// PRUEFE ENTITY KOLLISION
		gp.kPruefer.pruefeEntity(this);
		
		// PRUEFE PLAYER KOLLISION
		gp.kPruefer.pruefePlayer(this);
		
		
		laufeZumSpieler();

		// MOVEMENT
		if (rundenAnzahlGetroffen > 0) {
			rundenAnzahlGetroffen--;
			switch (stoßRichtung) {
			case "oben":

				if (gp.kPruefer.pruefeFeldOnlyRückgabe(this, stoßRichtung,
						gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurRückgabe(this, stoßRichtung,
								gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stoßRichtung) == false) {
					weltY -= gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen;

				}
				break;

			case "unten":

				if (gp.kPruefer.pruefeFeldOnlyRückgabe(this, stoßRichtung,
						gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurRückgabe(this, stoßRichtung,
								gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stoßRichtung) == false) {
					weltY += gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen;

				}
				break;

			case "links":

				if (gp.kPruefer.pruefeFeldOnlyRückgabe(this, stoßRichtung,
						gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurRückgabe(this, stoßRichtung,
								gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stoßRichtung) == false) {
					weltX -= gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen;

				}
				break;

			case "rechts":

				if (gp.kPruefer.pruefeFeldOnlyRückgabe(this, stoßRichtung,
						gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurRückgabe(this, stoßRichtung,
								gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stoßRichtung) == false) {
					weltX += gp.skala * entityGetroffen.rückstoß / rundenMaxAnzahlgetroffen;

				}
				break;

			}
		} else {

		}
		if (diffSpielerX <= gp.feldGroeße && diffSpielerY <= gp.feldGroeße) {
			zaeler++;
			if (zaeler == 60) {
				zaeler = 0;
				schlageSpieler();
			}
		} else {
			zaeler = 0;
		}

	}

	public void laufeZumSpieler() {
		diffSpielerX = weltX - gp.player.weltX;
		diffSpielerY = weltY - gp.player.weltY;

		if (diffSpielerX <= 0) {
			richtungX = "rechts";
			diffSpielerX = gp.player.weltX - weltX;
		} else {
			richtungX = "links";
		}

		if (diffSpielerY <= 0) {
			richtungY = "unten";
			diffSpielerY = gp.player.weltY - weltY;
		} else {
			richtungY = "oben";
		}

		if (diffSpielerX > diffSpielerY) {
			richtung = richtungX;
		} else if (diffSpielerY > diffSpielerX) {
			richtung = richtungY;
		}

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

		int framesUnbewegt = 0;
		frameCounter++;

		if (frameCounter > 8) {
			spriteNumber++;
			if (spriteNumber >= 4) {
				spriteNumber = 0;
			}
			frameCounter = 0;
		}

		framesUnbewegt++;
		if (framesUnbewegt >= 16) {
			spriteNumber = 0;
		}

		BufferedImage ghostSprite = null;

		switch (richtung) {
		case "oben":
			switch (spriteNumber) {
			case 0:
				ghostSprite = up;
				break;
			case 1:
				ghostSprite = upLV;
				break;
			case 2:
				ghostSprite = up;
				break;
			case 3:
				ghostSprite = upRV;
				break;
			}
			break;
		case "unten":
			switch (spriteNumber) {
			case 0:
				ghostSprite = down;
				break;
			case 1:
				ghostSprite = downLV;
				break;
			case 2:
				ghostSprite = down;
				break;
			case 3:
				ghostSprite = downRV;
				break;
			}
			break;
		case "links":
			switch (spriteNumber) {
			case 0:
				ghostSprite = left;
				break;
			case 1:
				ghostSprite = leftLV;
				break;
			case 2:
				ghostSprite = left;
				break;
			case 3:
				ghostSprite = leftRV;
				break;
			}
			break;
		case "rechts":
			switch (spriteNumber) {
			case 0:
				ghostSprite = right;
				break;
			case 1:
				ghostSprite = rightLV;
				break;
			case 2:
				ghostSprite = right;
				break;
			case 3:
				ghostSprite = rightRV;
				break;
			}
			break;
		}

	}

	public void schlageSpieler() {
		gp.player.leben -= gp.skala;
	}

	public void getImage() {

		up = setup("/ghost/ghost-up1");
		upLV = setup("/ghost/ghost-up2");
		upRV = setup("/ghost/ghost-up3");
		down = setup("/ghost/ghost-down1");
		downLV = setup("/ghost/ghost-down2");
		downRV = setup("/ghost/ghost-down3");
		left = setup("/ghost/ghost-left1");
		leftLV = setup("/ghost/ghost-left2");
		leftRV = setup("/ghost/ghost-left3");
		right = setup("/ghost/ghost-right1");
		rightLV = setup("/ghost/ghost-right2");
		rightRV = setup("/ghost/ghost-right3");

	}
}
