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

	public int randomMonsterAnzahl = 0;
	public int aktuelleMonsterImRaum;

	/**
	 * Konstruktor des Platzierers. Bidirektionale Assoziation mit GamePanel wird
	 * erstellt.
	 * 
	 * @param gp GamePanel-Objekt für bidirektionale Berziehung.
	 */
	public Platzierer(GamePanel gp) {
		this.gp = gp;
		indexObjekte = 0;
	}

	/**
	 * Beliebige Objekte können hier zum Platzieren codiert werden. Die ersten vier
	 * Plätze im Objekte-Array sind für Türen und Specials reserviert.
	 */
	public void setzeObjekt() {
		// gp.objekte[0] = new Objekt(gp, weltX, weltY);
		indexObjekte = 4;
		// gp.objekte[indexObjekte] = new SuperObjekt(gp, 10*gp.feldGroeße,
		// 10*gp.feldGroeße);
		// indexObjekte++;

	}

	/**
	 * Die Monster sowie der NPC werden hier an zufällig freier Stelle platziert.
	 */
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

					gp.entities[anzahl] = new Ghost(gp, randomFeldX * gp.feldGroeße + (gp.feldGroeße / 2),
							randomFeldY * gp.feldGroeße + (gp.feldGroeße / 2));
					anzahl--;
				}
				if (nr == 2) {
					gp.entities[anzahl] = new Zombie(gp, randomFeldX * gp.feldGroeße + (gp.feldGroeße / 2),
							randomFeldY * gp.feldGroeße + (gp.feldGroeße / 2));
					anzahl--;
				}
			} while (anzahl > 0);
		} else if (gp.feldM.mapNr > 12) {
			randomFeld();
			gp.entities[0] = new NPC(gp, randomFeldX * gp.feldGroeße + (gp.feldGroeße / 2),
					randomFeldY * gp.feldGroeße + (gp.feldGroeße / 2));

		}
	}

	/**
	 * Ermittelt zufällig freie Felder
	 */
	public void randomFeld() {
		do {
			randomFeldX = (int) (Math.random() * 20 + 5);
			randomFeldY = (int) (Math.random() * 20 + 5);
		} while (gp.feldM.feld[gp.feldM.mapFeldNr[randomFeldX][randomFeldY]].kollision == true);
	}

	/**
	 * Setzt die Ausgang-Objekte an die Stellen, an welchen sich auch Ausgang-Felder
	 * befinden. Zur ermittlung des Index des Felds nach dem Bildnamen wird hier die
	 * getFeldIndex()-Methode aus dem Feldmanager benutzt.
	 */
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
