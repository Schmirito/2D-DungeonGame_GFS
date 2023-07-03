package objekte;

import main.GamePanel;

public class Obj_AusgangsTuer extends SuperObjekt {

	/**Constructor für Ausgangs objekte. */
	public Obj_AusgangsTuer(GamePanel gp, int weltX, int weltY) {
		super(gp, weltX, weltY);
		name = "Ausgang";
		bild = null;
		kollision = true;
		
	}
}
