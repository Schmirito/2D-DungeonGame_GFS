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
		//gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroeße, 10*gp.feldGroeße);
		//indexObjekte++;
		
	}
	
	public void setzeEntity() {
		gp.entities[0] = new Zombie(gp, 20, 20);
		gp.entities[1] = new Zombie(gp, 30, 20);
		gp.entities[2] = new Zombie(gp, 23, 10);
		gp.entities[3] = new Zombie(gp, 27, 5);
		gp.entities[4] = new Zombie(gp, 15, 20);
		gp.entities[5] = new Zombie(gp, 19, 18);
		gp.entities[6] = new Zombie(gp, 7, 16);
		gp.entities[7] = new Zombie(gp, 25, 23);
		
	}
	
	public void setzeAusgang() {
		// Wenn in der alten map mehr ausgaenge als in der neuen map sind, so werden die alten überschüssigen Ausgänge nicht gelöscht
		indexObjekte = 0;
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D005TuerOA");
		int indexTuerUnten = gp.feldM.getFeldIndex("D005TuerUA");
		int indexTuerLinks = gp.feldM.getFeldIndex("D005TuerLA");
		int indexTuerRechts = gp.feldM.getFeldIndex("D005TuerRA");
		
		while(spalte < gp.mapGroeße && reihe < gp.mapGroeße) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];
			
			if (feldNr == indexTuerOben || feldNr == indexTuerUnten || feldNr == indexTuerLinks || feldNr == indexTuerRechts) {
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
