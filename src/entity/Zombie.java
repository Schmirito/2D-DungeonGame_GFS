package entity;

import main.GamePanel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Zombie extends Entity {

	int diffSpielerX;
	int diffSpielerY;
	String richtungX, richtungY;
	int framesUnbewegt = 0;
	int zaeler = 0;

	/**Constructor von Zombie entities
	 * @param gp
	 * @param weltX
	 * @param weltY
	 *  */
	public Zombie(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroe�e / 4;
		hitBox.y = gp.feldGroe�e / 2;
		hitBox.height = gp.feldGroe�e / 2;
		hitBox.width = gp.feldGroe�e / 2;

		richtung = "unten";
		geschwindigkeit = gp.skala;

		getImage();
	}
	
	/**Update-Methode von Zombies. Die richtung in welche diese schauen wird bestimmt.
	 * Die Kollision mit Objekten, Entities und dem Spieler wird �berpr�ft.
	 * Die Methode "laufeZumSpieler" wird aufgerufen.
	 * Die bewegungen des Zombies werden bestimmt.
	 * Der Zombie schl�gt nach einiger Zeit den Spieler mithilfe der Methode "schlageSpieler". */
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
			switch (sto�Richtung) {
			case "oben":

				if (gp.kPruefer.pruefeFeldOnlyR�ckgabe(this, sto�Richtung,
						gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurR�ckgabe(this, sto�Richtung,
								gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, sto�Richtung) == false) {
					weltY -= gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen;

				}
				break;

			case "unten":

				if (gp.kPruefer.pruefeFeldOnlyR�ckgabe(this, sto�Richtung,
						gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurR�ckgabe(this, sto�Richtung,
								gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, sto�Richtung) == false) {
					weltY += gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen;

				}
				break;

			case "links":

				if (gp.kPruefer.pruefeFeldOnlyR�ckgabe(this, sto�Richtung,
						gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurR�ckgabe(this, sto�Richtung,
								gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, sto�Richtung) == false) {
					weltX -= gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen;

				}
				break;

			case "rechts":

				if (gp.kPruefer.pruefeFeldOnlyR�ckgabe(this, sto�Richtung,
						gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeEntityNurR�ckgabe(this, sto�Richtung,
								gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, sto�Richtung) == false) {
					weltX += gp.skala * entityGetroffen.r�cksto� / rundenMaxAnzahlgetroffen;

				}
				break;

			}
		} else {

		}
		if (diffSpielerX <= gp.feldGroe�e && diffSpielerY <= gp.feldGroe�e) {
			zaeler++;
			if (zaeler == schlageSpielerCooldownSec * 60) {
				zaeler = 0;
				schlageSpieler();
			}
		} else {
			zaeler = 0;
		}

	}

	/**Es wird �berpr�ft on die X oder Y differenz zwischen dem Spieler und dem Zombie gr��er ist.
	 * Der Geist bewegt sich in die jeweils gr��ere Koordinaten richtung und kommt somit zum Spieler.
	 * Es werden nacheinander verschiedene Bilder von den Zombies geladen, wodurch diese eine animation bei der Fortbewegung bekommen.*/
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
			framesUnbewegt=0;
			frameCounter++;
		} else {
			framesUnbewegt++;
			frameCounter = 0;
		}

		if (frameCounter > 8) {
			spriteNumber++;
			if (spriteNumber >= 4) {
				spriteNumber = 0;
			}
			frameCounter = 0;
		}
		if (framesUnbewegt >= 16) {
			spriteNumber = 0;
		}


	}

	/**Wenn der Zombie den Spieler schl�gt wird diese Methode aufgerufen. Sie zieht dem spieler eine gewisse menge an leben ab. */
	public void schlageSpieler() {
		gp.player.leben -= gp.skala;
	}

	/**Die Bilder des Zombies werden in der setup-Methode, von der Superklasse Entity, aufgerufen und skaliert.*/
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
