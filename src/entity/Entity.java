package entity;

import java.awt.image.BufferedImage;

public class Entity {
	/** Deklaration der Variablen **/
	public int worldX, worldY;
	public int geschwindigkeit;

	public BufferedImage up, upLV, upRV, down, downLV, downRV, left, leftLV, leftRV, right, rightLV, rightRV;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
