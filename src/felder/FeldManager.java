package felder;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


import main.GamePanel;
import main.UtilityTool;

public class FeldManager {

	GamePanel gp;
	Feld[] feld;
	int mapFeldNr[][];
	public int groe�e = 50;
	
	public FeldManager(GamePanel gp) {
		this.gp = gp;
		
		feld = new Feld[60];
		
		mapFeldNr = new int[50][50];
		
		getFeldBild();
		loadMap();
	}
	/**Die angegebene .txt datei wird ausgelesen, skaliert und gezeichnet.*/
	public void loadMap() {
		
		try {
			InputStream is = getClass().getResourceAsStream("/maps/11_E-L_A-R.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int spalte = 0;
			int reihe = 0;
			
			
			while(spalte < gp.mapGroe�e && reihe < gp.mapGroe�e) {
				String line = br.readLine();
				
				while(spalte < gp.mapGroe�e) {
					String nummern[] = line.split(" ");
					
					int num = Integer.parseInt(nummern[spalte]);
					
					mapFeldNr[spalte][reihe] = num;
					spalte++;
				}
				if (spalte >= gp.mapGroe�e) {
					spalte  = 0;
					reihe++;
				}
			}
		} catch(Exception e) {
			
		}
	}
	public void getFeldBild() {
			setup(0, "D001Boden", false);
			setup(1, "D002Kiste", true);
			setup(2, "D003Stein", true);
			setup(3, "D004TuerLE", true);
			setup(4, "D004TuerOE", true);
			setup(5, "D004TuerRE", true);
			setup(6, "D004TuerUE", true);
			setup(7, "D005TuerLA", true);
			setup(8, "D005TuerOA", true);
			setup(9, "D005TuerRA", true);
			setup(10, "D005TuerUA", true);
			setup(11, "D006Wand", true);
			setup(12, "D006WandGELO", true);
			setup(13, "D006WandGELU", true);
			setup(14, "D006WandGERO", true);
			setup(15, "D006WandGERU", true);
			setup(16, "D006WandKELO", true);
			setup(17, "D006WandKELU", true);
			setup(18, "D006WandKERO", true);
			setup(19, "D006WandKERU", true);
			setup(20, "D006WandL", true);
			setup(21, "D006WandO", true);
			setup(22, "D006WandR", true);
			setup(23, "D006WandU", true);
			setup(24, "D007Wasser", true);
			setup(25, "D007WasserGELO", true);
			setup(26, "D007WasserGELU", true);
			setup(27, "D007WasserGERO", true);
			setup(28, "D007WasserGERU", true);
			setup(29, "D007WasserKELO", true);
			setup(30, "D007WasserKELU", true);
			setup(31, "D007WasserKERO", true);
			setup(32, "D007WasserKERU", true);
			setup(33, "D007WasserSL", true);
			setup(34, "D007WasserSO", true);
			setup(35, "D007WasserSR", true);
			setup(36, "D007WasserSU", true);
			setup(37, "S001Gras", false);
			setup(38, "S002Gehweg", false);
			setup(39, "S003Baum", true);
			setup(40, "S004Stein", true);
			setup(41, "S005Wasser", true);
			setup(42, "S005WasserGELO", true);
			setup(43, "S005WasserGELU", true);
			setup(44, "S005WasserGERO", true);
			setup(45, "S005WasserGERU", true);
			setup(46, "S005WasserKELO", true);
			setup(47, "S005WasserKELU", true);
			setup(48, "S005WasserKERO", true);
			setup(49, "S005WasserKERU", true);
			setup(50, "S005WasserWL", true);
			setup(51, "S005WasserWO", true);
			setup(52, "S005WasserWR", true);
			setup(53, "S005WasserWU", true);
			
	}
	public void setup(int index, String bildName, boolean kollision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			feld[index] = new Feld();
			feld[index].image = ImageIO.read(getClass().getResourceAsStream("/feld/"+bildName+".png"));
			feld[index].image = uTool.skalaBild(feld[index].image, gp.feldGroe�e, gp.feldGroe�e);
			feld[index].kollision = kollision;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		
		int spalte = 0;
		int reihe = 0;
		int x = 0 - gp.kamera.weltX + (gp.BildBreite / 2);
		int y = 0 - gp.kamera.weltY + (gp.BildHoehe / 2);
		
		
		while(spalte < gp.mapGroe�e && reihe < gp.mapGroe�e) {
			
			int feldNr = mapFeldNr[spalte][reihe];
			
			g2.drawImage(feld[feldNr].image, x, y, gp.feldGroe�e, gp.feldGroe�e, null);
			spalte++;
			x += gp.feldGroe�e;
			
			if (spalte == gp.mapGroe�e) {
				spalte =0;
				x = 0 - gp.kamera.weltX + (gp.BildBreite / 2);
				reihe ++;
				y += gp.feldGroe�e;
			}
		}
	}
}
