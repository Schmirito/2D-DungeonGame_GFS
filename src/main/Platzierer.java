package main;

import objekte.Obj_AusgangsTuer;
import objekte.SuperObjekt;

public class Platzierer {
	
	GamePanel gp;
	int indexObjekte;
	public Platzierer(GamePanel gp) {
		this.gp = gp;
		indexObjekte = 0;
	}
	
	public void setzeObjekt() {
		// gp.objekte[0] = new Objekt(gp, weltX, weltY);
		indexObjekte = 4;
		gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroeße, 10*gp.feldGroeße);
		indexObjekte++;
		
	}
	
	public void setzeAusgang() {
		// Wenn in der alten map mehr ausgaenge als in der neuen map sind, so werden die alten überschüssigen Ausgänge nicht gelöscht
		indexObjekte = 0;
		int spalte = 0;
		int reihe = 0;
		
		while(spalte < gp.mapGroeße && reihe < gp.mapGroeße) {
			
			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];
			
			if (feldNr>6 && feldNr<11) {
				int weltX = spalte * gp.feldGroeße;
				int weltY = reihe * gp.feldGroeße;
				gp.objekte[indexObjekte] = new Obj_AusgangsTuer(gp, weltX, weltY);
				indexObjekte++;
			}
			
			spalte++;
			if (spalte == gp.mapGroeße) {
				spalte =0;
				reihe ++;
			}
		}
	}
}
