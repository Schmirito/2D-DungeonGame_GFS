package main;

import entity.Entity;

public class KollisionPruefer {

	GamePanel gp;

	public KollisionPruefer(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Kollision zwischen dem übergeben Entity und anderen Feldern mit Kollision wird geprüft. Wenn zutreffend wird Entity.kollidiert auf "true" gesetzt.
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
	public boolean pruefeFeld(Entity entity) {

		int hitBoxLinkesWeltX = entity.weltX - (gp.feldGroeße / 2) + entity.hitBox.x;
		int hitBoxRechtesWeltX = entity.weltX - (gp.feldGroeße / 2) + entity.hitBox.x + entity.hitBox.width - 1;
		int hitBoxOberesWeltY = entity.weltY - (gp.feldGroeße / 2) + entity.hitBox.y;
		int hitBoxUnteresWeltY = entity.weltY - (gp.feldGroeße / 2) + entity.hitBox.y + entity.hitBox.height - 1;

		int hitBoxLinkeSpalte = hitBoxLinkesWeltX / gp.feldGroeße;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX / gp.feldGroeße;
		int hitBoxObereReihe = hitBoxOberesWeltY / gp.feldGroeße;
		int hitBoxUntereReihe = hitBoxUnteresWeltY / gp.feldGroeße;

		int feldNr1, feldNr2;

		boolean kollidiert = false;

		switch (entity.richtung) {
		case "oben":
			hitBoxObereReihe = (hitBoxOberesWeltY - entity.geschwindigkeit) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
				kollidiert = true;
			}
			break;
		case "unten":
			hitBoxUntereReihe = (hitBoxUnteresWeltY + entity.geschwindigkeit) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
				kollidiert = true;
			}
			break;
		case "links":
			hitBoxLinkeSpalte = (hitBoxLinkesWeltX - entity.geschwindigkeit) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
				kollidiert = true;
			}
			break;
		case "rechts":
			hitBoxRechteSpalte = (hitBoxRechtesWeltX + entity.geschwindigkeit) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
				kollidiert = true;
			}
			break;
		default:
			System.out.println("In " + this.getClass().getSimpleName()
					+ "wurde in 'PruefeFeld' kein case für diese Richtung gewählt.");
			break;
		}
		return kollidiert;

	}
	/**
	 * Kollision zwischen dem übergeben Entity und anderen Entities im Entity-Array wird geprüft. Wenn zutreffend wird Entity.kollidiert auf "true" gesetzt.
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
	public void pruefeEntity(Entity entity) {

		for (int i = 0; i < gp.entities.length; i++) {
			if (gp.entities[i] != null && !(entity.equals(gp.entities[i]))) {

				// Alte Werte Sichern
				int altesEntityHitboxX = entity.hitBox.x;
				int altesEntityHitboxY = entity.hitBox.y;
				// Alte Werte Sichern
				int altesObjektHitboxX = gp.entities[i].hitBox.x;
				int altesObjektHitboxY = gp.entities[i].hitBox.y;

				// Hitbox Weltposition bestimmen
				entity.hitBox.x = entity.weltX + entity.hitBox.x - (gp.feldGroeße / 2);
				entity.hitBox.y = entity.weltY + entity.hitBox.y - (gp.feldGroeße / 2);
				// Hitbox Weltposition bestimmen
				gp.entities[i].hitBox.x = gp.entities[i].weltX + gp.entities[i].hitBox.x - (gp.feldGroeße / 2);
				gp.entities[i].hitBox.y = gp.entities[i].weltY + gp.entities[i].hitBox.y - (gp.feldGroeße / 2);

				switch (entity.richtung) {
				case "oben":
					entity.hitBox.y -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							entity.kollidiert = true;
						}
					}
					break;
				case "unten":
					entity.hitBox.y += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							entity.kollidiert = true;
						}
					}
					break;
				case "links":
					entity.hitBox.x -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							entity.kollidiert = true;
						}
					}
					break;
				case "rechts":
					entity.hitBox.x += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							entity.kollidiert = true;
						}
					}
					break;
				default:
					System.out.println("Fehler in: Kollisionpruefer -> pruefeObjekt -> switch-case");
					break;
				}
				// Hitbox X und Y zuruecksetzen
				entity.hitBox.x = altesEntityHitboxX;
				entity.hitBox.y = altesEntityHitboxY;
				gp.entities[i].hitBox.x = altesObjektHitboxX;
				gp.entities[i].hitBox.y = altesObjektHitboxY;
			}
		}
	}
	/**
	 * Kollision zwischen dem übergeben Entity und anderen Entities im Entity-Array wird geprüft. Wenn zutreffend wird "true" zurückgegeben, ansonsten "false".
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
	public boolean pruefeEntityNurRückgabe(Entity entity, String richtung, int bewegung) {
		boolean kollidiert = false;
		for (int i = 0; i < gp.entities.length; i++) {
			if (gp.entities[i] != null && !(entity.equals(gp.entities[i]))) {

				// Alte Werte Sichern
				int altesEntityHitboxX = entity.hitBox.x;
				int altesEntityHitboxY = entity.hitBox.y;
				// Alte Werte Sichern
				int altesObjektHitboxX = gp.entities[i].hitBox.x;
				int altesObjektHitboxY = gp.entities[i].hitBox.y;

				// Hitbox Weltposition bestimmen
				entity.hitBox.x = entity.weltX + entity.hitBox.x - (gp.feldGroeße / 2);
				entity.hitBox.y = entity.weltY + entity.hitBox.y - (gp.feldGroeße / 2);
				// Hitbox Weltposition bestimmen
				gp.entities[i].hitBox.x = gp.entities[i].weltX + gp.entities[i].hitBox.x - (gp.feldGroeße / 2);
				gp.entities[i].hitBox.y = gp.entities[i].weltY + gp.entities[i].hitBox.y - (gp.feldGroeße / 2);

				switch (richtung) {
				case "oben":
					entity.hitBox.y -= bewegung;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							kollidiert = true;
						}

					}
					break;
				case "unten":
					entity.hitBox.y += bewegung;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							kollidiert = true;
						}

					}
					break;
				case "links":
					entity.hitBox.x -= bewegung;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							kollidiert = true;
						}

					}
					break;
				case "rechts":
					entity.hitBox.x += bewegung;
					if (entity.hitBox.intersects(gp.entities[i].hitBox)) {
						if (gp.entities[i].kollision == true) {
							kollidiert = true;
						}

					}
					break;
				default:
					System.out.println("Fehler in: Kollisionpruefer -> pruefeObjekt -> switch-case");
					break;
				}
				// Hitbox X und Y zuruecksetzen
				entity.hitBox.x = altesEntityHitboxX;
				entity.hitBox.y = altesEntityHitboxY;
				gp.entities[i].hitBox.x = altesObjektHitboxX;
				gp.entities[i].hitBox.y = altesObjektHitboxY;
			}
		}
		return kollidiert;
	}
	/**
	 * Kollision zwischen dem übergeben Entity und dem Player wird geprüft. Wenn zutreffend wird Entity.kollidiert auf "true" gesetzt.
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
	public void pruefePlayer(Entity entity) {

		if (true) {

			// Alte Werte Sichern
			int altesEntityHitboxX = entity.hitBox.x;
			int altesEntityHitboxY = entity.hitBox.y;
			// Alte Werte Sichern
			int altesObjektHitboxX = gp.player.hitBox.x;
			int altesObjektHitboxY = gp.player.hitBox.y;

			// Hitbox Weltposition bestimmen
			entity.hitBox.x = entity.weltX + entity.hitBox.x - (gp.feldGroeße / 2);
			entity.hitBox.y = entity.weltY + entity.hitBox.y - (gp.feldGroeße / 2);
			// Hitbox Weltposition bestimmen
			gp.player.hitBox.x = gp.player.weltX + gp.player.hitBox.x - (gp.feldGroeße / 2);
			gp.player.hitBox.y = gp.player.weltY + gp.player.hitBox.y - (gp.feldGroeße / 2);

			switch (entity.richtung) {
			case "oben":
				entity.hitBox.y -= entity.geschwindigkeit;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
					if (gp.player.kollision == true) {
						entity.kollidiert = true;
					}
				}
				break;
			case "unten":
				entity.hitBox.y += entity.geschwindigkeit;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
					if (gp.player.kollision == true) {
						entity.kollidiert = true;
					}
				}
				break;
			case "links":
				entity.hitBox.x -= entity.geschwindigkeit;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
					if (gp.player.kollision == true) {
						entity.kollidiert = true;
					}
				}
				break;
			case "rechts":
				entity.hitBox.x += entity.geschwindigkeit;
				if (entity.hitBox.intersects(gp.player.hitBox)) {
					if (gp.player.kollision == true) {
						entity.kollidiert = true;
					}
				}
				break;
			default:
				System.out.println("Fehler in: Kollisionpruefer -> pruefeObjekt -> switch-case");
				break;
			}
			// Hitbox X und Y zuruecksetzen
			entity.hitBox.x = altesEntityHitboxX;
			entity.hitBox.y = altesEntityHitboxY;
			gp.player.hitBox.x = altesObjektHitboxX;
			gp.player.hitBox.y = altesObjektHitboxY;
		}
	}
	/**
	 * Kollision zwischen dem übergeben Entity und anderen Feldern mit Kollision wird geprüft. Wenn zutreffend wird "true" zurückgegeben, ansonsten "false".
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
	public boolean pruefeFeldOnlyRückgabe(Entity entity, String richtung, int bewegung) {

		int hitBoxLinkesWeltX = entity.weltX - (gp.feldGroeße / 2) + entity.hitBox.x;
		int hitBoxRechtesWeltX = entity.weltX - (gp.feldGroeße / 2) + entity.hitBox.x + entity.hitBox.width - 1;
		int hitBoxOberesWeltY = entity.weltY - (gp.feldGroeße / 2) + entity.hitBox.y;
		int hitBoxUnteresWeltY = entity.weltY - (gp.feldGroeße / 2) + entity.hitBox.y + entity.hitBox.height - 1;

		int hitBoxLinkeSpalte = hitBoxLinkesWeltX / gp.feldGroeße;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX / gp.feldGroeße;
		int hitBoxObereReihe = hitBoxOberesWeltY / gp.feldGroeße;
		int hitBoxUntereReihe = hitBoxUnteresWeltY / gp.feldGroeße;

		int feldNr1, feldNr2;

		boolean kollidiert = false;

		switch (richtung) {
		case "oben":
			hitBoxObereReihe = (hitBoxOberesWeltY - bewegung) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				kollidiert = true;

			}
			break;
		case "unten":
			hitBoxUntereReihe = (hitBoxUnteresWeltY + bewegung) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				kollidiert = true;
			}
			break;
		case "links":
			hitBoxLinkeSpalte = (hitBoxLinkesWeltX - bewegung) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				kollidiert = true;
			}
			break;
		case "rechts":
			hitBoxRechteSpalte = (hitBoxRechtesWeltX + bewegung) / gp.feldGroeße;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				kollidiert = true;
			}
			break;
		default:
			System.out.println("In " + this.getClass().getSimpleName()
					+ "wurde in 'PruefeFeld' kein case für diese Richtung gewählt.");
			break;
		}
		return kollidiert;

	}
	/**
	 * Kollision zwischen dem übergeben Entity und Objekten im Objekte-Array wird geprüft. Wenn zutreffend wird Entity.kollidiert auf "true" gesetzt.
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
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
				entity.hitBox.x = entity.weltX + entity.hitBox.x - (gp.feldGroeße / 2);
				entity.hitBox.y = entity.weltY + entity.hitBox.y - (gp.feldGroeße / 2);

				// Alte Werte Sichern
				int altesObjektHitboxX = gp.objekte[i].hitBox.x;
				int altesObjektHitboxY = gp.objekte[i].hitBox.y;
				// Hitbox Weltposition bestimmen
				gp.objekte[i].hitBox.x = gp.objekte[i].weltX + gp.objekte[i].hitBox.x;
				gp.objekte[i].hitBox.y = gp.objekte[i].weltY + gp.objekte[i].hitBox.y;

				switch (entity.richtung) {
				case "oben":
					entity.hitBox.y -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}

					}
					break;
				case "unten":
					entity.hitBox.y += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}

					}
					break;
				case "links":
					entity.hitBox.x -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}

					}
					break;
				case "rechts":
					entity.hitBox.x += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							entity.kollidiert = true;
						}
						if (kannInteragieren) {
							objGetroffen[i] = true;
						}

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
	/**
	 * Kollision zwischen dem übergeben Entity und Objekten im Objekte-Array wird geprüft. Wenn zutreffend wird "true" zurückgegeben, ansonsten "false".
	 * @param entity Entity, welches die Kollision überprüfen will.
	 */
	public boolean pruefeObjektOnlyKollidiert(Entity entity, String richtung) {
		boolean kollidiert = false;

		for (int i = 0; i < gp.objekte.length; i++) {
			if (gp.objekte[i] != null) {

				// Alte Werte Sichern
				int altesEntityHitboxX = entity.hitBox.x;
				int altesEntityHitboxY = entity.hitBox.y;
				// Hitbox Weltposition bestimmen
				entity.hitBox.x = entity.weltX + entity.hitBox.x - (gp.feldGroeße / 2);
				entity.hitBox.y = entity.weltY + entity.hitBox.y - (gp.feldGroeße / 2);

				// Alte Werte Sichern
				int altesObjektHitboxX = gp.objekte[i].hitBox.x;
				int altesObjektHitboxY = gp.objekte[i].hitBox.y;
				// Hitbox Weltposition bestimmen
				gp.objekte[i].hitBox.x = gp.objekte[i].weltX + gp.objekte[i].hitBox.x;
				gp.objekte[i].hitBox.y = gp.objekte[i].weltY + gp.objekte[i].hitBox.y;

				switch (richtung) {
				case "oben":
					entity.hitBox.y -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							kollidiert = true;
						}

					}
					break;
				case "unten":
					entity.hitBox.y += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							kollidiert = true;
						}


					}
					break;
				case "links":
					entity.hitBox.x -= entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							kollidiert = true;
						}

					}
					break;
				case "rechts":
					entity.hitBox.x += entity.geschwindigkeit;
					if (entity.hitBox.intersects(gp.objekte[i].hitBox)) {
						if (gp.objekte[i].kollision == true) {
							kollidiert = true;
						}

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
			}
		}

		return kollidiert;
	}
}
