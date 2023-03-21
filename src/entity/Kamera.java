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
		if (keyH.obenGedrückt || keyH.untenGedrückt || keyH.linksGedrückt || keyH.rechtsGedrückt) {
			if (keyH.obenGedrückt == true) {
				weltY -= player.geschwindigkeit;
			} else if (keyH.untenGedrückt) {
				weltY += player.geschwindigkeit;
			} else if (keyH.linksGedrückt) {
				weltX -= player.geschwindigkeit;
			} else if (keyH.rechtsGedrückt) {
				weltX += player.geschwindigkeit;
			}
		}	
	}
	
}
