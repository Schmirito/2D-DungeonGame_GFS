package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import objekte.Schlag;

public class NPC extends Entity {

	public boolean ichRede = false;
	public String dialogTexte[] = { "Oh Hallo!", "M�chtest du f�r 150 M�nzchen ein omin�ses Tr�nkchen trinken?",
			"Gute Entscheidung, HeHe", "Tu dir keinen Zwang an.", "HeHe","Hm das sind leider zu wenig M�nzen..."};
	public int dialogIndex = 0;
	/**
	 * Konstruktor f�r NPC. Geht bidirektionale Beziehung mit GamePanel ein. Weltposition wird initialisiert. Bilder werden geladen.
	 * @param gp Das GamePanel.
	 * @param weltX Die X-Koordinaate auf der Map.
	 * @param weltY Die Y-Koordinate auf der Map.
	 */
	public NPC(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;

		getImage();
	}
	/**
	 * Update-Methode des NPC. Hier wird das Wippen des Kopfs des NPC veranlasst und im Dialogfall dieser erm�glicht.
	 */
	public void update() {
		frameCounter++;
		if (frameCounter >= 30) {
			spriteNumber++;
			if (spriteNumber >= 4) {
				spriteNumber = 0;
			}
			frameCounter = 0;
		}
		if (ichRede) {
			// DIALOG
			switch (dialogIndex) {
			case 0:
				if (gp.keyH.enterGedrueckt) {
					dialogIndex++;
					gp.keyH.enterGedrueckt = false;
				}
				break;
			case 1:
				if (gp.keyH.enterGedrueckt) {
					if (Entity.muenzen -150 >=0) {
						dialogIndex = 2;
						Entity.muenzen -= 150;
						gp.player.schaden++;
					} else {
						dialogIndex = 5;
					}
					gp.keyH.enterGedrueckt = false;
				} else if (gp.keyH.escGedrueckt) {
					dialogIndex = 3;
					gp.keyH.escGedrueckt = false;
				}
				break;
			case 2:
				if (gp.keyH.enterGedrueckt) {
					dialogIndex = 4;
					gp.gStatus = 0;
					ichRede = false;
					gp.keyH.enterGedrueckt = false;
				}
				break;
			case 3:
				if (gp.keyH.enterGedrueckt) {
					dialogIndex = 1;
					gp.gStatus = 0;
					ichRede = false;
					gp.keyH.enterGedrueckt = false;
				}
				break;
			case 4:
				if (gp.keyH.enterGedrueckt) {
					gp.gStatus = 0;
					ichRede = false;
					gp.keyH.enterGedrueckt = false;
				}
				break;
			case 5:
				if (gp.keyH.enterGedrueckt) {
					gp.gStatus = 0;
					ichRede = false;
					gp.keyH.enterGedrueckt = false;
				}
				break;
			}
		}

	}
	/**
	 * Wird der NPC von der in der Safe-Zone unsichtbaren Faust getroffen, so verliert er nicht an Leben, sondern der Mechanismus wird zum Ansprechen des NPC genutzt.
	 * Game-Status wird ge�ndert und boolean ichRede auf true gesetzt.
	 */
	public void getroffen(Entity entity, Schlag schlag) { // DIALOG MIT NPC
		ichRede = true;
		gp.gStatus = 2;
	}
	/**
	 * NPC-Sprite wird re-initialisiert und, wenn innerhalb des Bildschirms, gezeichnet.
	 * @param Graphics2D-Objekt, welches zum Zeichnen verwendet wird.
	 */
	public void draw(Graphics2D g2) {
		BufferedImage charSprite = null;
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
		int bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX - (gp.feldGroesse / 2);
		int bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY - (gp.feldGroesse / 2);
		if (charSprite != null) {
			if (weltX + gp.feldGroesse > gp.kamera.weltX - gp.kamera.bildschirmX
					&& weltX - gp.feldGroesse < gp.kamera.weltX + gp.kamera.bildschirmX
					&& weltY + gp.feldGroesse > gp.kamera.weltY - gp.kamera.bildschirmY
					&& weltY - gp.feldGroesse < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(charSprite, bildschirmX, bildschirmY, gp.feldGroesse, gp.feldGroesse, null);
			}
		}
		if (ichRede) {
			int width = (int)(gp.feldGroesse * (dialogTexte[dialogIndex].length() / 7) + (gp.feldGroesse*1.25));
			int height = (int) (gp.feldGroesse * 1);
			gp.drawDialogWindow(g2, bildschirmX - (width / 2), bildschirmY - height, width, height);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
			g2.setColor(Color.WHITE);
			g2.drawString(dialogTexte[dialogIndex], bildschirmX - (width / 2) + (int)(gp.feldGroesse*0.25),
					(int) (bildschirmY - (gp.feldGroesse * 0.5)));
			if (dialogIndex == 1) {
				g2.drawString("[No: ESC]             [Yes: ENTER]", bildschirmX - (width / 2) + (int)(gp.feldGroesse*0.25),
						(int) (bildschirmY - (gp.feldGroesse * 0.25)));
			}
		}

	}
	/**
	 * Die NPC-Sprites werden aus dem res-Ordner mithilfe der Setup-Methode in deren Variablen geladen.
	 */
	public void getImage() {
		up = setup("/objekte/Obj001Test");
		upLV = setup("/objekte/Obj001Test");
		upRV = setup("/objekte/Obj001Test");
		down = setup("/npc/haendlerNPCVorne-Sammy");
		downLV = setup("/npc/haendlerNPCVorneLinks-Sammy");
		downRV = setup("/npc/haendlerNPCVorneRechts-Sammy");
		left = setup("/objekte/Obj001Test");
		leftLV = setup("/objekte/Obj001Test");
		leftRV = setup("/objekte/Obj001Test");
		right = setup("/objekte/Obj001Test");
		rightLV = setup("/objekte/Obj001Test");
		rightRV = setup("/objekte/Obj001Test");

	}
}
