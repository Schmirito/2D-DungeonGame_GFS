package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
}
