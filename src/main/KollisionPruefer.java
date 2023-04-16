package main;



import entity.Entity;

public class KollisionPruefer {

	GamePanel gp;

	public KollisionPruefer(GamePanel gp) {
		this.gp = gp;
	}
	/** Es wird gepr¸ft ob ein Feld auf welches das Entity laufen will Kollision hat, dementsprechend 'entity.kollidiert' gesetzt*/
	public void pruefeFeld(Entity entity) {

		int hitBoxLinkesWeltX = entity.weltX - (gp.feldGroeﬂe/2) + entity.hitBox.x;
		int hitBoxRechtesWeltX = entity.weltX - (gp.feldGroeﬂe/2) + entity.hitBox.x + entity.hitBox.width-1;
		int hitBoxOberesWeltY = entity.weltY - (gp.feldGroeﬂe/2) + entity.hitBox.y;
		int hitBoxUnteresWeltY = entity.weltY - (gp.feldGroeﬂe/2) + entity.hitBox.y + entity.hitBox.height-1;

		int hitBoxLinkeSpalte = hitBoxLinkesWeltX / gp.feldGroeﬂe;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX / gp.feldGroeﬂe;
		int hitBoxObereReihe = hitBoxOberesWeltY / gp.feldGroeﬂe;
		int hitBoxUntereReihe = hitBoxUnteresWeltY / gp.feldGroeﬂe;

		int feldNr1, feldNr2;

		switch (entity.richtung) {
		case "oben":
			hitBoxObereReihe = (hitBoxOberesWeltY - entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		case "unten":
			hitBoxUntereReihe = (hitBoxUnteresWeltY + entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		case "links":
			hitBoxLinkeSpalte = (hitBoxLinkesWeltX - entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		case "rechts":
			hitBoxRechteSpalte = (hitBoxRechtesWeltX + entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		default:
			System.out.println("In "+ this.getClass().getSimpleName() + "wurde in 'PruefeFeld' kein case f¸r diese Richtung gew‰hlt.");
			break;
		}

	}
	public boolean[] pruefeObjekt(Entity entity, boolean kannInteragieren) {
		boolean objGetroffen[] = new boolean[gp.objekte.length];
		for (int i = 0; i < objGetroffen.length; i++) {
			objGetroffen[i] = false;
		}
		
		for (int i = 0; i < objGetroffen.length; i++) {
			if (gp.objekte[i] != null) {
				
				// Alte Werte Sichern
				int altesEntityHitboxX = entity.hitBox.x;
				int altesEntityHitboxY = entity.hitBox.y;
				// Hitbox Weltposition bestimmen
				entity.hitBox.x = entity.weltX + entity.hitBox.x - (gp.feldGroeﬂe/2);
				entity.hitBox.y = entity.weltY + entity.hitBox.y - (gp.feldGroeﬂe/2);
				
				// Alte Werte Sichern
				int altesObjektHitboxX =  gp.objekte[i].hitBox.x;
				int altesObjektHitboxY =  gp.objekte[i].hitBox.y;
				// Hitbox Weltposition bestimmen
				gp.objekte[i].hitBox.x = gp.objekte[i].weltX + gp.objekte[i].hitBox.x;
				gp.objekte[i].hitBox.y = gp.objekte[i].weltY + gp.objekte[i].hitBox.y;
				
				switch (entity.richtung) {
				case "oben":
					entity.hitBox.y -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision==true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}
						System.out.println("oben Kollision");
					}
					break;
				case "unten":
					entity.hitBox.y += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision==true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}
						System.out.println("unten Kollision");
					}
					break;
				case "links":
					entity.hitBox.x -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision==true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}
						System.out.println("links Kollision");
					}
					break;
				case "rechts":
					entity.hitBox.x += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision==true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}
						System.out.println("rechts Kollision");
					}
					break;
				default:
					System.out.println("Fehler in: Kollisionpruefer -> pruefeObjekt -> switch-case");
					break;
				}
				// Hitbox X und Y zuruecksetzen
				entity.hitBox.x = altesEntityHitboxX;
				entity.hitBox.y = altesEntityHitboxY;
				gp.objekte[i].hitBox.x = altesObjektHitboxX;
				gp.objekte[i].hitBox.y = altesObjektHitboxY;
			} else {
				if (kannInteragieren) {
					objGetroffen[i] = false;
				}
			}
		}
		
		return objGetroffen;
	}
}
