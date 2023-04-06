package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Entity;

public class KollisionPruefer {

	GamePanel gp;

	public KollisionPruefer(GamePanel gp) {
		this.gp = gp;
	}

	public void pruefeFeld(Entity entity) {

		int hitBoxLinkesWeltX = entity.weltX - (gp.feldGroe�e/2) + entity.hitBox.x;
		int hitBoxRechtesWeltX = entity.weltX - (gp.feldGroe�e/2) + entity.hitBox.x + entity.hitBox.width;
		int hitBoxOberesWeltY = entity.weltY - (gp.feldGroe�e/2) + entity.hitBox.y;
		int hitBoxUnteresWeltY = entity.weltY - (gp.feldGroe�e/2) + entity.hitBox.y + entity.hitBox.height;

		int hitBoxLinkeSpalte = hitBoxLinkesWeltX / gp.feldGroe�e;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX / gp.feldGroe�e;
		int hitBoxObereReihe = hitBoxOberesWeltY / gp.feldGroe�e;
		int hitBoxUntereReihe = hitBoxUnteresWeltY / gp.feldGroe�e;

		int feldNr1, feldNr2;

		switch (entity.richtung) {
		case "oben":
			hitBoxObereReihe = (hitBoxOberesWeltY - entity.geschwindigkeit) / gp.feldGroe�e;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		case "unten":
			hitBoxUntereReihe = (hitBoxUnteresWeltY + entity.geschwindigkeit) / gp.feldGroe�e;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		case "links":
			hitBoxLinkeSpalte = (hitBoxLinkesWeltX - entity.geschwindigkeit) / gp.feldGroe�e;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		case "rechts":
			hitBoxRechteSpalte = (hitBoxRechtesWeltX + entity.geschwindigkeit) / gp.feldGroe�e;
			feldNr1 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			feldNr2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNr1].kollision == true || gp.feldM.feld[feldNr2].kollision == true) {
				entity.kollidiert = true;
			}
			break;
		default:
			System.out.println("In "+ this.getClass().getSimpleName() + "wurde in 'PruefeFeld' kein case f�r diese Richtung gew�hlt.");
			break;
		}

	}
}
