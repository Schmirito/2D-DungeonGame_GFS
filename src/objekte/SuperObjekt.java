package objekte;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObjekt {
	
	GamePanel gp;
	public BufferedImage bild;
	public String name;
	public boolean kollision = false;
	public int weltX, weltY;
	public Rectangle hitBox;
	
	public SuperObjekt(GamePanel gp) {
		this.gp = gp;
		hitBox = new Rectangle(0,0,gp.feldGroe�e,gp.feldGroe�e);
	}
	
	public void draw(Graphics2D g2) {
		if(bild!=null) {
			
			int bildschirmX = weltX - gp.kamera.weltX - gp.kamera.bildschirmX;
			int bildschirmY = weltY - gp.kamera.weltY - gp.kamera.bildschirmY;
			
			if (weltX + gp.feldGroe�e > gp.kamera.weltX - gp.kamera.bildschirmX && 
				weltX - gp.feldGroe�e < gp.kamera.weltX + gp.kamera.bildschirmX && 
				weltY + gp.feldGroe�e > gp.kamera.weltY - gp.kamera.bildschirmY && 
				weltY - gp.feldGroe�e < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(bild, bildschirmX, bildschirmY, gp.feldGroe�e, gp.feldGroe�e, null);
			}
		}
	}
}
