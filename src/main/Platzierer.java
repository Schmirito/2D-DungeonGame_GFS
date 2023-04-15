package main;

import objekte.SuperObjekt;

public class Platzierer {
	
	GamePanel gp;
	
	public Platzierer(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setzeObjekt() {
		// gp.objekte[0] = new Objekt(gp, weltX, weltY);
		gp.objekte[0] = new SuperObjekt(gp, 10, 10);
		
	}
}
