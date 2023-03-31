package felder;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;


import main.GamePanel;
import main.UtilityTool;

public class FeldManager {

	GamePanel gp;
	Feld[] feld;
	int mapFeldNr[][];
	public int groeße = 30;
	
	public int mapNr = 1;
	public int neueNummer;
	
	//Etwas unschön gelößt, aber keine zeit.
	public String[] mapAuswahl = {"/maps/Room1-Test.txt","/maps/Room2-Test.txt","/maps/Room3-Test.txt",
								"/maps/Room4-Test.txt","/maps/Room5-Test.txt","/maps/Room6-Test.txt",
								"/maps/Room7-Test.txt","/maps/Room8-Test.txt","/maps/Room9-Test.txt",
								"/maps/Room10-Test.txt","/maps/Room11-Test.txt","/maps/Room12-Test.txt"};
	
	public FeldManager(GamePanel gp) {
		this.gp = gp;
		
		feld = new Feld[60];
		
		mapFeldNr = new int[30][30];
		
		getFeldBild();
		loadMap();
	}
	/**Die angegebene .txt datei wird ausgelesen, skaliert und gezeichnet.*/
	public void loadMap() {
		
		switch (mapNr) {
		case 2:
		case 4:
		case 10:
			//Könnte man in Methode auslagern, ist aber nicht nötig. (weil zu wenig code = lohnt sich nicht.
			neueNummer = (int) (Math.random()*3);

			if (neueNummer == 0) {
				mapNr = 1; 
			}
			if (neueNummer == 1) {
				mapNr = 2;
			}
			if (neueNummer == 2) {
				mapNr = 3;
			}
			break;
			
		case 1:
		case 5:
		case 7:
			neueNummer = (int) (Math.random()*3);

			if (neueNummer == 0) {
				mapNr = 4; 
			}
			if (neueNummer == 1) {
				mapNr = 5;
			}
			if (neueNummer == 2) {
				mapNr = 6;
			}
			break;
		
		case 6:
		case 8:
		case 12:
			int neueNummer = (int) (Math.random()*3);

			if (neueNummer == 0) {
				mapNr = 7;
			}
			if (neueNummer == 1) {
				mapNr = 8;
			}
			if (neueNummer == 2) {
				mapNr = 9;
			}
			break;
			
		case 3:
		case 9:
		case 11:
			neueNummer = (int) (Math.random()*3);

			if (neueNummer == 0) {
				mapNr = 10; 
			}
			if (neueNummer == 1) {
				mapNr = 11;
			}
			if (neueNummer == 2) {
				mapNr = 12;
			}
			break;
			
		default:
			break;
		}	//switch case klammer
		
		
		try {
			InputStream is = getClass().getResourceAsStream(mapAuswahl[mapNr]);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int spalte = 0;
			int reihe = 0;
			
			
			while(spalte < gp.mapGroeße && reihe < gp.mapGroeße) {
				String line = br.readLine();
				
				while(spalte < gp.mapGroeße) {
					String nummern[] = line.split(" ");
					
					int num = Integer.parseInt(nummern[spalte]);
					
					mapFeldNr[spalte][reihe] = num;
					spalte++;
				}
				if (spalte >= gp.mapGroeße) {
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
			feld[index].image = uTool.skalaBild(feld[index].image, gp.feldGroeße, gp.feldGroeße);
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
		
		
		while(spalte < gp.mapGroeße && reihe < gp.mapGroeße) {
			
			int feldNr = mapFeldNr[spalte][reihe];
			
			g2.drawImage(feld[feldNr].image, x, y, gp.feldGroeße, gp.feldGroeße, null);
			spalte++;
			x += gp.feldGroeße;
			
			if (spalte == gp.mapGroeße) {
				spalte =0;
				x = 0 - gp.kamera.weltX + (gp.BildBreite / 2);
				reihe ++;
				y += gp.feldGroeße;
			}
		}
	}
}
