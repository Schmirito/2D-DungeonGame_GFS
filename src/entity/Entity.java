package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	/** Deklaration der Variablen */
	public int weltX, weltY;
	public int geschwindigkeit;

	public BufferedImage up, upLV, upRV, down, downLV, downRV, left, leftLV, leftRV, right, rightLV, rightRV;
	public String richtung;

	public int frameCounter = 0;
	public int spriteNumber = 0;

	public Rectangle hitBox;
	public boolean kollidiert = false;
	GamePanel gp;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void interagiereMitObjekt(boolean objGetroffen[]) {
		for (int i = 0; i < objGetroffen.length; i++) {
			if (objGetroffen[i] == true) {
				System.out.println("objekt getroffen: " + i);
				switch (gp.objekte[i].name) {
				case "Ausgang":
					gp.feldM.loadMap();
					
					geheZuEingang(true);
					// PLATZIERE NEUE AUSGAENGE
					gp.platzierer.setzeAusgang();
					
					i = objGetroffen.length;
					break;
				default:
					break;
				}
			}
		}
	}
	public void geheZuEingang(boolean auchKamera) {
		// ERMITTLE KOODRINATEN DES EINGANGS
		int spalte = 0;
		int reihe = 0;
		int indexTuerOben = gp.feldM.getFeldIndex("D004TuerOE");
		int indexTuerUnten = gp.feldM.getFeldIndex("D004TuerUE");
		int indexTuerLinks = gp.feldM.getFeldIndex("D004TuerLE");
		int indexTuerRechts = gp.feldM.getFeldIndex("D004TuerRE");
		while (spalte < gp.mapGroeﬂe && reihe < gp.mapGroeﬂe) {

			int feldNr = gp.feldM.mapFeldNr[spalte][reihe];

			if (feldNr == indexTuerOben) {
				weltX = spalte * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				weltY = (reihe+1) * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				if(weltX > (gp.BildBreite/2 ) && weltX < (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2))) {
					gp.kamera.weltX = weltX;
				} else if (weltX < (gp.BildBreite/2 )) {
					gp.kamera.weltX = gp.BildBreite/2 ;
				} else if (weltX > (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2))) {
					gp.kamera.weltX = gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2);
				}
				gp.kamera.weltY = gp.BildHoehe/2;
			} else if (feldNr == indexTuerUnten) {
				weltX = spalte * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				weltY = (reihe-1) * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				if(weltX > (gp.BildBreite/2 ) && weltX < (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2))) {
					gp.kamera.weltX = weltX;
				} else if (weltX < (gp.BildBreite/2 )) {
					gp.kamera.weltX = gp.BildBreite/2 ;
				} else if (weltX > (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2))) {
					gp.kamera.weltX = gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2);
				}
				gp.kamera.weltY = gp.mapGroeﬂe*gp.feldGroeﬂe-(gp.BildHoehe/2);
			} else if (feldNr == indexTuerLinks) {
				weltX = (spalte+1) * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				weltY = reihe * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				gp.kamera.weltX = gp.BildBreite/2;
				if(weltY > (gp.BildHoehe/2 ) && weltY < (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildHoehe/2))) {
					gp.kamera.weltY = weltY; 
				} else if (weltY < (gp.BildHoehe/2 )) {
					gp.kamera.weltY = gp.BildHoehe/2 ;
				} else if (weltY > (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildHoehe/2))) {
					gp.kamera.weltY = gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildHoehe/2);
				}
			} else if (feldNr == indexTuerRechts) {
				weltX = (spalte-1) * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				weltY = reihe * gp.feldGroeﬂe + (gp.feldGroeﬂe/2);
				gp.kamera.weltX = gp.kamera.weltX = gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildBreite/2);
				if(weltY > (gp.BildHoehe/2 ) && weltY < (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildHoehe/2))) {
					gp.kamera.weltY = weltY; 
				} else if (weltY < (gp.BildHoehe/2 )) {
					gp.kamera.weltY = gp.BildHoehe/2 ;
				} else if (weltY > (gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildHoehe/2))) {
					gp.kamera.weltY = gp.mapGroeﬂe*gp.feldGroeﬂe - (gp.BildHoehe/2);
				}
			}

			spalte++;
			if (spalte == gp.mapGroeﬂe) {
				spalte = 0;
				reihe++;
			}
		}
	}
}
