package objekte;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

/**
 * 
 * @author programmierer
 * @version 04/07/2023
 *
 */
public class SuperObjekt {
	
	GamePanel gp;
	public BufferedImage bild;
	public String name;
	public boolean kollision = true;
	public int weltX, weltY;
	public Rectangle hitBox;
	
	public int bildschirmX;
	public int bildschirmY;
	
	/**
	 * Constructor SuperObjects
	 * @param gp GamePanel wird �bergeben
	 * @param weltX weltX Koordinate des SuperObj
	 * @param weltY weltY Koordinate des SuperObj
	 */
	public SuperObjekt(GamePanel gp, int weltX, int weltY) {
		this.gp = gp;
		name = "SuperObjekt";
		hitBox = new Rectangle(0,0,gp.feldGroesse,gp.feldGroesse);
		this.weltX = weltX;
		this.weltY = weltY;
		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/objekte/Obj001Test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**Zeichen-Methode welche die erbenden klassen nutzen k�nnen.
	 * @param g2 Graphics2D wird zum zeichnen �bergeben*/
	public void draw(Graphics2D g2) {
		if(bild != null) {
			
			bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX;
			bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY;
			
			if (weltX + gp.feldGroesse > gp.kamera.weltX - gp.kamera.bildschirmX && 
				weltX - gp.feldGroesse < gp.kamera.weltX + gp.kamera.bildschirmX && 
				weltY + gp.feldGroesse > gp.kamera.weltY - gp.kamera.bildschirmY && 
				weltY - gp.feldGroesse < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(bild, bildschirmX, bildschirmY, gp.feldGroesse, gp.feldGroesse, null);
			}
		}
	}
	
	/**Auslesen und skalieren der Objekte.
	 * @param bildName Der bildname wird �bergeben um das jeweilige bild zu skalieren
	 * @return bild Das bild wird zur�ckgegeben
	 */
	public BufferedImage setup(String bildName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage bild = null;

		try {
			bild = ImageIO.read(getClass().getResourceAsStream("/objekte/" + bildName + ".png"));
			bild = uTool.skalaBild(bild, gp.feldGroesse, gp.feldGroesse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bild;
	}
}
