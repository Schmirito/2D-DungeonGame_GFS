package main;

import entity.Entity;

public class KollisionPruefer {

	GamePanel gp;

	public KollisionPruefer(GamePanel gp) {
		this.gp = gp;
	}

	public void pruefeFeld(Entity entity) {

		int hitBoxLinkesWeltX = entity.weltX + entity.hitBox.x;
		int hitBoxRechtesWeltX = entity.weltX + entity.hitBox.x + entity.hitBox.width;
		int hitBoxOberesWeltY = entity.weltY + entity.hitBox.y;
		int hitBoxUnteresWeltY = entity.weltY + entity.hitBox.y + entity.hitBox.height;

		int hitBoxLinkeSpalte = hitBoxLinkesWeltX / gp.feldGroeﬂe;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX / gp.feldGroeﬂe;
		int hitBoxObereReihe = hitBoxOberesWeltY / gp.feldGroeﬂe;
		int hitBoxUntereReihe = hitBoxUnteresWeltY / gp.feldGroeﬂe;

		int feldNum1, feldNum2;

		switch (entity.richtung) {
		case "oben":
			hitBoxObereReihe = (hitBoxOberesWeltY - entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNum1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNum2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			if (gp.feldM.feld[feldNum1].kollision == true || gp.feldM.feld[feldNum2].kollision == true) {
				entity.kollisionAn = true;
			}
			break;
		case "unten":
			hitBoxUntereReihe = (hitBoxUnteresWeltY + entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNum1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNum2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxObereReihe];
			if (gp.feldM.feld[feldNum1].kollision == true || gp.feldM.feld[feldNum2].kollision == true) {
				entity.kollisionAn = true;
			}
			break;
		case "links":
			hitBoxLinkeSpalte = (hitBoxLinkesWeltX - entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNum1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNum2 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNum1].kollision == true || gp.feldM.feld[feldNum2].kollision == true) {
				entity.kollisionAn = true;
			}
			break;
		case "rechts":
			hitBoxRechteSpalte = (hitBoxRechtesWeltX + entity.geschwindigkeit) / gp.feldGroeﬂe;
			feldNum1 = gp.feldM.mapFeldNr[hitBoxLinkeSpalte][hitBoxObereReihe];
			feldNum2 = gp.feldM.mapFeldNr[hitBoxRechteSpalte][hitBoxUntereReihe];
			if (gp.feldM.feld[feldNum1].kollision == true || gp.feldM.feld[feldNum2].kollision == true) {
				entity.kollisionAn = true;
			}
			break;
		default:
			System.out.println("In "+ this.getClass().getSimpleName() + "wurde in 'PruefeFeld' kein switch-case f¸r diese Richtung gew‰hlt.");
			break;
		}

	}
}
