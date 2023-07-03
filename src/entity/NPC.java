package entity;

import main.GamePanel;
import objekte.Schlag;

public class NPC extends Entity {

	public NPC(GamePanel gp) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;
		
		getImage();
	}
	
	
	public void update() {
		
	}
	public void getroffen(Entity entity, Schlag schlag) {
	}
	public void getImage() {
		up = setup("/objekte/Obj001Test");
		upLV = setup("/objekte/Obj001Test");
		upRV = setup("/objekte/Obj001Test");
		down = setup("/npc/haendlerNPCVorne-Sammy");
		downLV = setup("/objekte/Obj001Test");
		downRV = setup("/objekte/Obj001Test");
		left = setup("/objekte/Obj001Test");
		leftLV = setup("/objekte/Obj001Test");
		leftRV = setup("/objekte/Obj001Test");
		right = setup("/objekte/Obj001Test");
		rightLV = setup("/objekte/Obj001Test");
		rightRV = setup("/objekte/Obj001Test");

	}
}
