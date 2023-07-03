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
	public Feld[] feld;
	public int mapFeldNr[][];
	public int groeﬂe = 30;

	public int startRaum = 0;
	public int mapNr = 0;
	public int raumZaeler = 4;
	public int raeumeGesamt = 0;
	public int neueNummer;
	public int vorherigeNummer;
	public boolean start = true;

	// Etwas unschˆn gelˆﬂt, aber funktioniert.
	public String[] mapAuswahl = { "/maps/Startraum-Test.txt", "/maps/Room1.txt", "/maps/Room2.txt", "/maps/Room3.txt",
			"/maps/Room4.txt", "/maps/Room5.txt", "/maps/Room6.txt", "/maps/Room7.txt", "/maps/Room8.txt",
			"/maps/Room9.txt", "/maps/Room10.txt", "/maps/Room11.txt", "/maps/Room12.txt", "/maps/Safezone1-O.txt",
			"/maps/Safezone2-O.txt", "/maps/Safezone4-O.txt", "/maps/Safezone1-R.txt", "/maps/Safezone2-R.txt",
			"/maps/Safezone3-R.txt", "/maps/Safezone2-U.txt", "/maps/Safezone3-U.txt", "/maps/Safezone4-U.txt",
			"/maps/Safezone1-L.txt", "/maps/Safezone3-L.txt", "/maps/Safezone4-L.txt" };

	public FeldManager(GamePanel gp) {
		this.gp = gp;

		feld = new Feld[60];

		mapFeldNr = new int[30][30];

		getFeldBild();
	}

	/** Die angegebene .txt datei wird ausgelesen, skaliert und gezeichnet. */
	public void loadMap() {
		switch (mapNr) {
		case 0: // Startraum implementieren
			// Zum Testen
			if (start) {
				start = false;
			} else {
				neueNummer = (int) (Math.random() * 3);
				if (neueNummer == 0) {
					mapNr = 10;
				}
				if (neueNummer == 1) {
					mapNr = 11;
				}
				if (neueNummer == 2) {
					mapNr = 12;
				}
			}
			break;
		case 2:
		case 4:
		case 10:
			do {
				neueNummer = (int) (Math.random() * 3 + 1);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raumZaeler++;
			raeumeGesamt++;
			if (raumZaeler == 5) {
				mapNr = (int) (Math.random() * 3 + 13);
				raumZaeler = 1;
			}
			break;

		case 1:
		case 5:
		case 7:
			do {
				neueNummer = (int) (Math.random() * 3 + 4);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raumZaeler++;
			raeumeGesamt++;

			if (raumZaeler == 5) {
				mapNr = (int) (Math.random() * 3 + 16);
				raumZaeler = 1;
			}

			break;

		case 6:
		case 8:
		case 12:
			do {
				neueNummer = (int) (Math.random() * 3 + 7);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raumZaeler++;
			raeumeGesamt++;

			if (raumZaeler == 5) {
				mapNr = (int) (Math.random() * 3 + 19);
				raumZaeler = 1;
			}

			break;

		case 3:
		case 9:
		case 11:
			do {
				neueNummer = (int) (Math.random() * 3 + 10);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raumZaeler++;
			raeumeGesamt++;

			if (raumZaeler == 5) {
				mapNr = (int) (Math.random() * 3 + 22);
				raumZaeler = 1;
			}

			break;

		case 13:
		case 16:
		case 22:
			do {
				neueNummer = (int) (Math.random() * 3 + 1);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raeumeGesamt++;
			break;

		case 14:
		case 17:
		case 19:
			do {
				neueNummer = (int) (Math.random() * 3 + 4);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raeumeGesamt++;
			break;

		case 18:
		case 20:
		case 23:
			do {
				neueNummer = (int) (Math.random() * 3 + 7);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raeumeGesamt++;
			break;

		case 15:
		case 21:
		case 24:
			do {
				neueNummer = (int) (Math.random() * 3 + 10);
			} while (mapNr == neueNummer && mapNr == vorherigeNummer);
			vorherigeNummer = mapNr;
			mapNr = neueNummer;
			raeumeGesamt++;
			break;

		default:
			break;
		} // switch case klammer

		try {

			InputStream is = getClass().getResourceAsStream(mapAuswahl[mapNr]);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int spalte = 0;
			int reihe = 0;

			while (spalte < gp.mapGroeﬂe && reihe < gp.mapGroeﬂe) {
				String line = br.readLine();

				while (spalte < gp.mapGroeﬂe) {
					String nummern[] = line.split(" ");

					int num = Integer.parseInt(nummern[spalte]);

					mapFeldNr[spalte][reihe] = num;
					spalte++;
				}
				if (spalte >= gp.mapGroeﬂe) {
					spalte = 0;
					reihe++;
				}
			}
		} catch (Exception e) {

		}
		System.out.println("mapNr: "+mapNr);
		System.out.println("raumz‰hler: "+raumZaeler);
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
			feld[index].image = ImageIO.read(getClass().getResourceAsStream("/feld/" + bildName + ".png"));
			feld[index].image = uTool.skalaBild(feld[index].image, gp.feldGroeﬂe, gp.feldGroeﬂe);
			feld[index].bildName = bildName;
			feld[index].kollision = kollision;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getFeldIndex(String bildName) {
		int index = -1;
		for (int i = 0; i < feld.length; i++) {
			if (feld[i] != null) {
				if (feld[i].bildName.equals(bildName)) {
					index = i;
					i = feld.length;
				}
			}
		}
		return index;
	}

	public void draw(Graphics2D g2) {

		int spalte = 0;
		int reihe = 0;
		int x = 0 - gp.kamera.weltX + gp.kamera.bildschirmX;
		int y = 0 - gp.kamera.weltY + gp.kamera.bildschirmY;

		while (spalte < gp.mapGroeﬂe && reihe < gp.mapGroeﬂe) {

			if (x + gp.feldGroeﬂe > 0 && x - gp.feldGroeﬂe < gp.BildBreite && y + gp.feldGroeﬂe > 0
					&& y - gp.feldGroeﬂe < gp.BildHoehe) {
				int feldNr = mapFeldNr[spalte][reihe];
				g2.drawImage(feld[feldNr].image, x, y, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
			}
			// g2.drawRect(x, y, gp.feldGroeﬂe, gp.feldGroeﬂe);
			spalte++;
			x += gp.feldGroeﬂe;

			if (spalte == gp.mapGroeﬂe) {
				spalte = 0;
				x = 0 - gp.kamera.weltX + gp.kamera.bildschirmX;
				reihe++;
				y += gp.feldGroeﬂe;
			}
		}
	}
}
