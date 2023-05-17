package objekte;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class SuperObjekt {
	
	GamePanel gp;
	public BufferedImage bild;
	public String name;
	public boolean kollision = true;
	public int weltX, weltY;
	public Rectangle hitBox;
	
	public int bildschirmX;
	public int bildschirmY;
	
	public SuperObjekt(GamePanel gp, int weltX, int weltY) {
		this.gp = gp;
		name = "SuperObjekt";
		hitBox = new Rectangle(0,0,gp.feldGroeﬂe,gp.feldGroeﬂe);
		this.weltX = weltX;
		this.weltY = weltY;
		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/objekte/Obj001Test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(Graphics2D g2) {
		if(bild != null) {
			
			bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
			bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
			
			if (weltX + gp.feldGroeﬂe > gp.kamera.weltX - gp.kamera.bildschirmX && 
				weltX - gp.feldGroeﬂe < gp.kamera.weltX + gp.kamera.bildschirmX && 
				weltY + gp.feldGroeﬂe > gp.kamera.weltY - gp.kamera.bildschirmY && 
				weltY - gp.feldGroeﬂe < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(bild, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
			}
		}
	}
	public BufferedImage setup(String bildName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage bild = null;

		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/objekte/" + bildName + ".png"));
			bild = uTool.skalaBild(bild, gp.feldGroeﬂe, gp.feldGroeﬂe);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bild;
	}
}
