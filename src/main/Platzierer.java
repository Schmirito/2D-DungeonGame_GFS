package main;

import entity.Entity;
import entity.Ghost;
import entity.NPC;
import entity.Zombie;
import felder.FeldManager;
import objekte.Obj_AusgangsTuer;
import objekte.SuperObjekt;

public class Platzierer {

	GamePanel gp;
	FeldManager fm;
	int indexObjekte;
	int randomFeldX;
	int randomFeldY;

	public int randomMonsterAnzahl;
	public int aktuelleMonsterImRaum;

	public Platzierer(GamePanel gp) {
		this.gp = gp;
		indexObjekte = 0;
	}

	public void setzeObjekt() {
		// gp.objekte[0] = new Objekt(gp, weltX, weltY);
		indexObjekte = 4;
		// gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroeße,
		// 10*gp.feldGroeße);
		// indexObjekte++;

	}

	public void setzeEntity() {
		for (int i = 0; i < gp.entities.length; i++) {
			gp.entities[i] = null;
		}
		if (gp.feldM.mapNr != 0 && gp.feldM.mapNr <= 12) {
			randomMonsterAnzahl = (int) ((Math.random() * 5) + 1 + gp.feldM.raeumeGesamt);
			int anzahl = randomMonsterAnzahl;
			do {
				randomFeld();

				int nr = (int) (Math.random() * 2 + 1);

				if (nr == 1) {
					gp.entities[anzahl] = new Ghost(gp, randomFeldX * gp.feldGroeße, randomFeldY * gp.feldGroeße);
					anzahl--;
				}
				if (nr == 2) {
					gp.entities[anzahl] = new Zombie(gp, randomFeldX * gp.feldGroeße, randomFeldY * gp.feldGroeße);
					anzahl--;
				}
			} while (anzahl > 0);
		} else if (gp.feldM.mapNr > 12) {
			gp.entities[0] = new NPC(gp, 15 * gp.feldGroeße, 15 * gp.feldGroeße);
		}
	}

	public void randomFeld() {
		do {
			randomFeldX = (int) (Math.random() * 29 + 0.5);
			randomFeldY = (int) (Math.random() * 29 + 0.5);
		} while (gp.feldM.feld[gp.feldM.mapFeldNr[randomFeldX][randomFeldY]].kollision == true);
	}

	public void setzeAusgang() {
		// Wenn in der alten map mehr ausgaenge als in der neuen map sind, so werden die
		// alten überschüssigen Ausgänge nicht gelöscht

		for (int i = 0; i < 4; i++) {
			gp.objekte[i] = null;
		}
		indexObjekte = 0;
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D005TuerOA");
		int indexTuerUnten = gp.feldM.getFeldIndex("D005TuerUA");
		int indexTuerLinks = gp.feldM.getFeldIndex("D005TuerLA");
		int indexTuerRechts = gp.feldM.getFeldIndex("D005TuerRA");

		while (spalte < gp.mapGroeße && reihe < gp.mapGroeße) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];

			if (feldNr == indexTuerOben || feldNr == indexTuerUnten || feldNr == indexTuerLinks
					|| feldNr == indexTuerRechts) {
				int weltX = spalte * gp.feldGroeße;
				int weltY = reihe * gp.feldGroeße;
				gp.objekte[indexObjekte] = new Obj_AusgangsTuer(gp, weltX, weltY);
				indexObjekte++;
			}

			spalte++;
			if (spalte == gp.mapGroeße) {
				spalte = 0;
				reihe++;
			}
		}
	}
}
