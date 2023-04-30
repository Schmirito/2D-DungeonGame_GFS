package entity;

import main.GamePanel;

public class Zombie extends Entity {

	public Zombie(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX*gp.feldGroeﬂe;
		this.weltY = weltY*gp.feldGroeﬂe;
		
		richtung = "unten";
		geschwindigkeit = gp.skala;
		
		getImage();
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
