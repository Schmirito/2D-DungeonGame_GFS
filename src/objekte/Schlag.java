package objekte;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Schlag extends SuperObjekt {
	public BufferedImage bildO, bildU, bildL, bildR;
	public String schlagRichtung;

	public Schlag(GamePanel gp, int weltX, int weltY) {
		super(gp, weltX, weltY);

		bildO = setup("FaustHoch");
		bildU = setup("FaustRunter");
		bildL = setup("FaustLinks");
		bildR = setup("FaustRechts");
	}

	public void draw(Graphics2D g2, Entity entity) {
		if (bild != null) {
			if (entity.hitCooldownFrames/entity.hitCooldownSekunden > 0.1) {
				
			}
			bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
			bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
			
			switch (schlagRichtung) {
			case "oben":
				g2.drawImage(bildO, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
				break;
			case "unten":
				g2.drawImage(bildU, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
				break;
			case "links":
				g2.drawImage(bildL, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
				break;
			case "rechts":
				g2.drawImage(bildR, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
				break;
			}
			

		}
	}
}
