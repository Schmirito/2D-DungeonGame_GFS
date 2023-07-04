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
		// gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroe�e,
		// 10*gp.feldGroe�e);
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
					gp.entities[anzahl] = new Ghost(gp, randomFeldX * gp.feldGroe�e + (gp.feldGroe�e/2), randomFeldY * gp.feldGroe�e + (gp.feldGroe�e/2));
					anzahl--;
				}
				if (nr == 2) {
					gp.entities[anzahl] = new Zombie(gp, randomFeldX * gp.feldGroe�e + (gp.feldGroe�e/2), randomFeldY * gp.feldGroe�e + (gp.feldGroe�e/2));
					anzahl--;
				}
			} while (anzahl > 0);
		} else if (gp.feldM.mapNr > 12) {
			randomFeld();
			gp.entities[0] = new NPC(gp, randomFeldX * gp.feldGroe�e + (gp.feldGroe�e/2), randomFeldY * gp.feldGroe�e + (gp.feldGroe�e/2));
		}
	}

	public void randomFeld() {
		do {
			randomFeldX = (int) (Math.random() * 20 + 5);
			randomFeldY = (int) (Math.random() * 20 + 5);
		} while (gp.feldM.feld[gp.feldM.mapFeldNr[randomFeldX][randomFeldY]].kollision == true);
	}

	public void setzeAusgang() {

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
