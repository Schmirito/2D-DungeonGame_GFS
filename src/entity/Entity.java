package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	/** Deklaration der Variablen */
	public int weltX, weltY;
	public int geschwindigkeit;

	public BufferedImage up, upLV, upRV, down, downLV, downRV, left, leftLV, leftRV, right, rightLV, rightRV;
	public String richtung;
	
	public int frameCounter = 0;
	public int spriteNumber = 0;
	
	public Rectangle hitBox;
	public boolean kollidiert = false;
	GamePanel gp;
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void interagiereMitObjekt(boolean objGetroffen[]) {
		for (int i = 0; i < objGetroffen.length; i++) {
			if (objGetroffen[i] == true) {
				System.out.println("objekt getroffen: " + i);
				switch (gp.objekte[i].name) {
				case "Ausgang":
					gp.feldM.loadMap();
					gp.platzierer.setzeAusgang();
					weltX = gp.BildBreite/2;
					weltY = gp.BildHoehe/2;
					gp.kamera.weltX = gp.BildBreite/2;
					gp.kamera.weltY = gp.BildHoehe/2;
					i=objGetroffen.length;
					break;
				default:
					break;
				}
			}
		}
	}
}
