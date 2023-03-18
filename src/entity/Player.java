package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	/** Deklaration der Variablen **/
	GamePanel gp;
	KeyHandler keyH;
	public int screenX;
	public int screenY;
	
	/** Constructor mit Uebergabeparametern GamePanel und KeyHandler **/
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		//screenX = gp.screenWidth/2 - (gp.tileSize/2);
		//screenY = gp.screenHeigth/2 - (gp.tileSize/2);

		//setDefaultValuables();
		//getPlayerImage();
	}
	
	
	public void setDefaultValuables() {
		worldX = 23  * gp.feldGroeﬂe;
		worldY = 21 * gp.feldGroeﬂe;
		geschwindigkeit = gp.skala;
		direction = "down";
	}
	
	
}
