package objekte;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Schlag extends SuperObjekt {
	public BufferedImage bildO, bildU, bildL, bildR;
	public String schlagRichtung;
	int animationFrames = 0;
	int maxAnimationFrames = 4;
	int frameCounter = 0;

	public Schlag(GamePanel gp, int weltX, int weltY) {
		super(gp, weltX, weltY);

		bildO = setup("FaustHoch");
		bildU = setup("FaustRunter");
		bildL = setup("FaustLinks");
		bildR = setup("FaustRechts");
	}

	public void draw(Graphics2D g2, Entity entity) {
		if (bild != null) {
			if (animationFrames < maxAnimationFrames) {
				
				switch (schlagRichtung) {
				case "oben":
					bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
					bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY + maxAnimationFrames*gp.skala - animationFrames*gp.skala;
					g2.drawImage(bildO, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				case "unten":
					bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
					bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY - maxAnimationFrames*gp.skala + animationFrames*gp.skala;
					g2.drawImage(bildU, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				case "links":
					bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX + maxAnimationFrames*gp.skala - animationFrames*gp.skala;
					bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
					g2.drawImage(bildL, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				case "rechts":
					bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX - maxAnimationFrames*gp.skala + animationFrames*gp.skala;
					bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
					g2.drawImage(bildR, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				}
				frameCounter++;
				if (frameCounter >= 0) {
					frameCounter = 0;
					animationFrames++;
				}
			} else {
				bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
				bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
				
				switch (schlagRichtung) {
				case "oben":
					g2.drawImage(bildO, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				case "unten":
					g2.drawImage(bildU, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				case "links":
					g2.drawImage(bildL, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				case "rechts":
					g2.drawImage(bildR, bildschirmX, bildschirmY, gp.feldGroeße, gp.feldGroeße, null);
					break;
				} 
			}
			
			

		}
	}
}
