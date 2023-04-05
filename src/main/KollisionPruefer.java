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
		
		int hitBoxLinkeSpalte = hitBoxLinkesWeltX/gp.feldGroe�e;
		int hitBoxRechteSpalte = hitBoxRechtesWeltX/gp.feldGroe�e;
		int hitBoxObereReihe = hitBoxOberesWeltY/gp.feldGroe�e;
		int hitBoxUntereReihe = hitBoxUnteresWeltY/gp.feldGroe�e;
		
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
