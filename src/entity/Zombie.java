package entity;

import main.GamePanel;

public class Zombie extends Entity {

	public Zombie(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;
		
		richtung = "unten";
		geschwindigkeit = gp.skala;
		
		getImage();
	}
	
	public void getImage() {

		up = setup("/zombie/zombie-Down");
		upLV = setup("/zombie/zombie-DownLV");
		upRV = setup("/zombie/zombie-DownRV");
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
