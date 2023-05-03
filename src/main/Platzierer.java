package main;

import entity.Entity;
import entity.Zombie;
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

		//gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroe�e, 10*gp.feldGroe�e);
		//indexObjekte++;
		
	}
	
	public void setzeEntity() {
		gp.entities[0] = new Zombie(gp, 20, 20);

		
	}
	
	public void setzeAusgang() {
		// Wenn in der alten map mehr ausgaenge als in der neuen map sind, so werden die alten �bersch�ssigen Ausg�nge nicht gel�scht
		indexObjekte = 0;
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D005TuerOA");
		int indexTuerUnten = gp.feldM.getFeldIndex("D005TuerUA");
		int indexTuerLinks = gp.feldM.getFeldIndex("D005TuerLA");
		int indexTuerRechts = gp.feldM.getFeldIndex("D005TuerRA");
		
		while(spalte < gp.mapGroe�e && reihe < gp.mapGroe�e) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];
			
			if (feldNr == indexTuerOben || feldNr == indexTuerUnten || feldNr == indexTuerLinks || feldNr == indexTuerRechts) {
				int weltX = spalte * gp.feldGroe�e;
				int weltY = reihe * gp.feldGroe�e;
				gp.objekte[indexObjekte] = new Obj_AusgangsTuer(gp, weltX, weltY);
				indexObjekte++;
			}
			
			spalte++;
			if (spalte == gp.mapGroe�e) {
				spalte =0;
				reihe ++;
			}
		}
	}
}
