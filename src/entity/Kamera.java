package entity;

import main.GamePanel;

public class Kamera {
	
	public int bildschirmX;
	public int bildschirmY;
	public int weltX;
	public int weltY;
	GamePanel gp;
	public Kamera(GamePanel gp) {
		this.gp = gp;
		bildschirmX = gp.BildBreite/2;
		bildschirmY = gp.BildHoehe/2;
	}
	
}
