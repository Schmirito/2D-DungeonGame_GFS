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
		gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroeﬂe, 10*gp.feldGroeﬂe);
		indexObjekte++;
		
	}
	
	public void setzeAusgang() {
		indexObjekte = 0;
		int spalte = 0;
		int reihe = 0;
		
		while(spalte < gp.mapGroeﬂe && reihe < gp.mapGroeﬂe) {
			
			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];
			
			if (feldNr>6 && feldNr<11) {
				int weltX = spalte * gp.feldGroeﬂe;
				int weltY = reihe * gp.feldGroeﬂe;
				gp.objekte[indexObjekte] = new Obj_AusgangsTuer(gp, weltX, weltY);
				indexObjekte++;
			}
			
			spalte++;
			if (spalte == gp.mapGroeﬂe) {
				spalte =0;
				reihe ++;
			}
		}
	}
}
