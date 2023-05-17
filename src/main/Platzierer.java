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

		// gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroe�e,
		// 10*gp.feldGroe�e);
		// indexObjekte++;
	}

	public void setzeEntity() {

		gp.entities[0] = new Zombie(gp, 20 * gp.feldGroe�e, 20 * gp.feldGroe�e);
		gp.entities[1] = new Zombie(gp, 18 * gp.feldGroe�e, 20 * gp.feldGroe�e);
		gp.entities[2] = new Zombie(gp, 13 * gp.feldGroe�e, 10 * gp.feldGroe�e);
		gp.entities[3] = new Zombie(gp, 17 * gp.feldGroe�e, 15 * gp.feldGroe�e);
		gp.entities[4] = new Zombie(gp, 15 * gp.feldGroe�e, 20 * gp.feldGroe�e);
		gp.entities[5] = new Zombie(gp, 19 * gp.feldGroe�e, 18 * gp.feldGroe�e);
		gp.entities[6] = new Zombie(gp, 15 * gp.feldGroe�e, 13 * gp.feldGroe�e);

	}

	public void setzeAusgang() {

		// Wenn in der alten map mehr ausgaenge als in der neuen map sind, so werden die
		// alten �bersch�ssigen Ausg�nge nicht gel�scht

		indexObjekte = 0;
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D005TuerOA");
		int indexTuerUnten = gp.feldM.getFeldIndex("D005TuerUA");
		int indexTuerLinks = gp.feldM.getFeldIndex("D005TuerLA");
		int indexTuerRechts = gp.feldM.getFeldIndex("D005TuerRA");

		while (spalte < gp.mapGroe�e && reihe < gp.mapGroe�e) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];


			if (feldNr == indexTuerOben || feldNr == indexTuerUnten || feldNr == indexTuerLinks
					|| feldNr == indexTuerRechts) {

				int weltX = spalte * gp.feldGroe�e;
				int weltY = reihe * gp.feldGroe�e;
				gp.objekte[indexObjekte] = new Obj_AusgangsTuer(gp, weltX, weltY);
				indexObjekte++;
			}

			spalte++;
			if (spalte == gp.mapGroe�e) {
				spalte = 0;
				reihe++;
			}
		}
	}
}
