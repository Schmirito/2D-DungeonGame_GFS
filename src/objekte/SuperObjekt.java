package objekte;

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
	
	public void draw() {
		if(bild!=null) {
			
		}
	}
}
