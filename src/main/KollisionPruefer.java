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
		
		int hitBoxLinkeSpalte = hitBoxLinkesWeltX/gp.feldGroeße;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX/gp.feldGroeße;
		int hitBoxObereReihe = hitBoxOberesWeltY/gp.feldGroeße;
		int hitBoxUntereReihe = hitBoxUnteresWeltY/gp.feldGroeße;
		
		int feldNum1, feldNum2;
		
		switch (entity.richtung) {
		case "oben":
			
			break;

		case "unten":
			
			break;
		case "links":
			
			break;
		case "rechts":
			
			break;
		default:
			
			break;
		}
		
	}
}
