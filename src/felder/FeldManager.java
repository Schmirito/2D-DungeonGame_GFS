package felder;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class FeldManager {

	GamePanel gp;
	Feld[] feld;
	
	public FeldManager(GamePanel gp) {
		this.gp = gp;
		
		feld = new Feld[60];
		
		getFeldBild();
	}
	public void getFeldBild() {
		
		try {
			feld[0] = new Feld();
			feld[0].image = ImageIO.read(getClass().getResourceAsStream("/feld/grass.png"));
			
			feld[1] = new Feld();
			feld[1].image = ImageIO.read(getClass().getResourceAsStream("/feld/earth.png"));
			
			feld[2] = new Feld();
			feld[2].image = ImageIO.read(getClass().getResourceAsStream("/feld/sand.png"));
			
			feld[3] = new Feld();
			feld[3].image = ImageIO.read(getClass().getResourceAsStream("/feld/tree.png"));
			
			feld[4] = new Feld();
			feld[4].image = ImageIO.read(getClass().getResourceAsStream("/feld/wall.png"));
			
			feld[5] = new Feld();
			feld[5].image = ImageIO.read(getClass().getResourceAsStream("/feld/water.png"));
			
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
