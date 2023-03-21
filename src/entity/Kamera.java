package entity;

import main.GamePanel;
import main.KeyHandler;

public class Kamera {
	
	public int bildschirmX;
	public int bildschirmY;
	public int weltX;
	public int weltY;
	GamePanel gp;
	KeyHandler keyH;
	Player player;
	public Kamera(GamePanel gp, KeyHandler keyH, Player player) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		bildschirmX = gp.BildBreite/2;
		bildschirmY = gp.BildHoehe/2;
		
	}
	
	public void update() {
		if (keyH.obenGedr�ckt || keyH.untenGedr�ckt || keyH.linksGedr�ckt || keyH.rechtsGedr�ckt) {
			if (keyH.obenGedr�ckt == true) {
				weltY -= player.geschwindigkeit;
			} else if (keyH.untenGedr�ckt) {
				weltY += player.geschwindigkeit;
			} else if (keyH.linksGedr�ckt) {
				weltX -= player.geschwindigkeit;
			} else if (keyH.rechtsGedr�ckt) {
				weltX += player.geschwindigkeit;
			}
		}	
	}
	
}
