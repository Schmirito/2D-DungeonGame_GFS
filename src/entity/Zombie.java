package entity;

import main.GamePanel;

public class Zombie extends Entity {

	public Zombie(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX*gp.feldGroeße;
		this.weltY = weltY*gp.feldGroeße;
		
		richtung = "unten";
		geschwindigkeit = gp.skala;
		
		getImage();
	}
	
	public void update() {
		// MOVEMENT
		if (rundenAnzahlGetroffen > 0) {
			rundenAnzahlGetroffen--;
			switch (stoßRichtung) {
			case "oben":
				if (gp.kPruefer.pruefeFeld(this, stoßRichtung, entityGetroffen.rückstoß)==false) {
					weltY -= gp.skala*entityGetroffen.rückstoß/4;
				}
				break;
				
			case "unten":
				if (gp.kPruefer.pruefeFeld(this, stoßRichtung, entityGetroffen.rückstoß)==false) {
					weltY += gp.skala*entityGetroffen.rückstoß/4;
				}
				break;
				
			case "links":
				if (gp.kPruefer.pruefeFeld(this, stoßRichtung, entityGetroffen.rückstoß)==false) {
					weltX -= gp.skala*entityGetroffen.rückstoß/4;
				}
				break;
				
			case "rechts":
				if (gp.kPruefer.pruefeFeld(this, stoßRichtung, entityGetroffen.rückstoß)==false) {
					weltX += gp.skala*entityGetroffen.rückstoß/4;
				}
				break;
				
			}
		} else {
			
		}
		
	}
	
	public void getImage() {

		up = setup("/zombie/zombie-Down.png");
		upLV = setup("/zombie/zombie-DownLV.png");
		upRV = setup("/zombie/zombie-DownRV.png");
		down = setup("/zombie/zombie-Down.png");
		downLV = setup("/zombie/zombie-DownLV.png");
		downRV = setup("/zombie/zombie-DownRV.png");
		left = setup("/zombie/zombie-Left.png");
		leftLV = setup("/zombie/zombie-LeftLV.png");
		leftRV = setup("/zombie/zombie-LeftRV.png");
		right = setup("/zombie/zombie-Right.png");
		rightLV = setup("/zombie/zombie-RightLV.png");
		rightRV = setup("/zombie/zombie-RightRV.png");

	}

}
