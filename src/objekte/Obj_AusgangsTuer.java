package objekte;

import main.GamePanel;

public class Obj_AusgangsTuer extends SuperObjekt {

	public Obj_AusgangsTuer(GamePanel gp, int weltX, int weltY) {
		super(gp);
		name = "AusgangsTuer";
		bild = null;
		this.weltX = weltX;
		this.weltY = weltY;
		
	}
}
