package entity;

import main.GamePanel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Zombie extends Entity {

	int diffSpielerX;
	int diffSpielerY;
	String richtungX, richtungY;

	int zaeler = 0;

	public Zombie(GamePanel gp, int weltX, int weltY) {
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
		gp.kPruefer.pruefeFeld(this);
		// PRUEFE OBJEKT KOLLISION
		boolean objGetroffen[] = gp.kPruefer.pruefeObjekt(this, true);
		interagiereMitObjekt(objGetroffen);
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
			if (zaeler == 35) {
				zaeler = 0;
				schlageSpieler();
			}
		}
		else {
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



	int framesUnbewegt = 0;frameCounter++;if(frameCounter>8)
	{
		spriteNumber++;
		if (spriteNumber >= 4) {
			spriteNumber = 0;
		}
		frameCounter = 0;
	}

	framesUnbewegt++;if(framesUnbewegt>=16)
	{
		spriteNumber = 0;
	}

	BufferedImage zombSprite = null;

	switch(richtung)
	{
		case "oben":
			switch (spriteNumber) {
			case 0:
				zombSprite = up;
				break;
			case 1:
				zombSprite = upLV;
				break;
			case 2:
				zombSprite = up;
				break;
			case 3:
				zombSprite = upRV;
				break;
			}
			break;
		case "unten":
			switch (spriteNumber) {
			case 0:
				zombSprite = down;
				break;
			case 1:
				zombSprite = downLV;
				break;
			case 2:
				zombSprite = down;
				break;
			case 3:
				zombSprite = downRV;
				break;
			}
			break;
		case "links":
			switch (spriteNumber) {
			case 0:
				zombSprite = left;
				break;
			case 1:
				zombSprite = leftLV;
				break;
			case 2:
				zombSprite = left;
				break;
			case 3:
				zombSprite = leftRV;
				break;
			}
			break;
		case "rechts":
			switch (spriteNumber) {
			case 0:
				zombSprite = right;
				break;
			case 1:
				zombSprite = rightLV;
				break;
			case 2:
				zombSprite = right;
				break;
			case 3:
				zombSprite = rightRV;
				break;
			}
			break;
		}

	}

	public void schlageSpieler() {
		gp.player.leben -= gp.skala;
	}

	public void getImage() {

		up = setup("/zombie/zombie-Up");
		upLV = setup("/zombie/zombie-UpLV");
		upRV = setup("/zombie/zombie-UpRV");
		down = setup("/zombie/zombie-Down");
		downLV = setup("/zombie/zombie-DownLV");
		downRV = setup("/zombie/zombie-DownRV");
		left = setup("/zombie/zombie-Left");
		leftLV = setup("/zombie/zombie-LeftLV");
		leftRV = setup("/zombie/zombie-LeftRV");
		right = setup("/zombie/zombie-Right");
		rightLV = setup("/zombie/zombie-RightLV");
		rightRV = setup("/zombie/zombie-RightRV");

	}
}
