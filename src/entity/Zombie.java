package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Zombie extends Entity {

	int diffSpielerX;
	int diffSpielerY;
	String richtungX, richtungY; 
	
	public Zombie(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX*gp.feldGroe�e;
		this.weltY = weltY*gp.feldGroe�e;
	
		richtung = "unten";
		geschwindigkeit = gp.skala;
		
		getImage();
	}
	
	public void update() {
		
		laufeZumSpieler();
		
		// MOVEMENT
		if (rundenAnzahlGetroffen > 0) {
			rundenAnzahlGetroffen--;
			switch (sto�Richtung) {
			case "oben":
				if (gp.kPruefer.pruefeFeld(this, sto�Richtung, entityGetroffen.r�cksto�)==false) {
					weltY -= gp.skala*entityGetroffen.r�cksto�/4;
				}
				break;
				
			case "unten":
				if (gp.kPruefer.pruefeFeld(this, sto�Richtung, entityGetroffen.r�cksto�)==false) {
					weltY += gp.skala*entityGetroffen.r�cksto�/4;
				}
				break;
				
			case "links":
				if (gp.kPruefer.pruefeFeld(this, sto�Richtung, entityGetroffen.r�cksto�)==false) {
					weltX -= gp.skala*entityGetroffen.r�cksto�/4;
				}
				break;
				
			case "rechts":
				if (gp.kPruefer.pruefeFeld(this, sto�Richtung, entityGetroffen.r�cksto�)==false) {
					weltX += gp.skala*entityGetroffen.r�cksto�/4;
				}
				break;
				
			}
		} else {
			
		}
		
	}
	
	public void laufeZumSpieler() {
		diffSpielerX = weltX - gp.player.weltX;
		diffSpielerY = weltY - gp.player.weltY;
		
		if (diffSpielerX <= 0) {
			richtungX = "rechts";
			diffSpielerX = gp.player.weltX - weltX;
		}
		else {
			richtungX = "links";
		}
		
	    if  (diffSpielerY <= 0) {
	    	richtungY = "unten";
	    	diffSpielerY = gp.player.weltY - weltY;
	    }
		else {
			richtungY = "oben";	
		}
	    
	    if (diffSpielerX > diffSpielerY) {
			richtung = richtungX;
		}
	    else if(diffSpielerY > diffSpielerX){
			richtung = richtungY;
		}
	    
	    if (kollidiert == false) {
			switch (richtung) {
			case "oben":
				weltY -= geschwindigkeit;
				break;
			case "unten":
				weltY += geschwindigkeit;
				break;
			case "links":
				weltX -= geschwindigkeit;
				break;
			case "rechts":
				weltX += geschwindigkeit;
				break;
			}
	    }
	    
	    int framesUnbewegt = 0;
		frameCounter++;
		if (frameCounter > 8) {
			spriteNumber++;
			if (spriteNumber >= 4) {
				spriteNumber = 0;
			}
			frameCounter = 0;
		}

	framesUnbewegt++;
	if (framesUnbewegt >= 16) {
		spriteNumber = 0;
	}
	    
	    BufferedImage zombSprite = null;

		switch (richtung) {
		case "oben":
			switch (spriteNumber) {
			case 0:
				zombSprite = up;
				break;
			case 1:
				zombSprite = upLV;
				break;
			case 2:
				zombSprite = up;
				break;
			case 3:
				zombSprite = upRV;
				break;
			}
			break;
		case "unten":
			switch (spriteNumber) {
			case 0:
				zombSprite = down;
				break;
			case 1:
				zombSprite = downLV;
				break;
			case 2:
				zombSprite = down;
				break;
			case 3:
				zombSprite = downRV;
				break;
			}
			break;
		case "links":
			switch (spriteNumber) {
			case 0:
				zombSprite = left;
				break;
			case 1:
				zombSprite = leftLV;
				break;
			case 2:
				zombSprite = left;
				break;
			case 3:
				zombSprite = leftRV;
				break;
			}
			break;
		case "rechts":
			switch (spriteNumber) {
			case 0:
				zombSprite = right;
				break;
			case 1:
				zombSprite = rightLV;
				break;
			case 2:
				zombSprite = right;
				break;
			case 3:
				zombSprite = rightRV;
				break;
			}
			break;
		}
	    
	}
	
	public void getImage() {

		up = setup("/zombie/zombie-Up");
		upLV = setup("/zombie/zombie-UpLV");
		upRV = setup("/zombie/zombie-UpRV");
		down = setup("/zombie/zombie-Down");
		downLV = setup("/zombie/zombie-DownLV");
		downRV = setup("/zombie/zombie-DownRV");
		left = setup("/zombie/zombie-Left");
		leftLV = setup("/zombie/zombie-LeftLV");
		leftRV = setup("/zombie/zombie-LeftRV");
		right = setup("/zombie/zombie-Right");
		rightLV = setup("/zombie/zombie-RightLV");
		rightRV = setup("/zombie/zombie-RightRV");

	}

}
