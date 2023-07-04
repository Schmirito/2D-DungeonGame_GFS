package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class UtilityTool {

	/**Hier weden in der Methode alle felder, welche im FeldManager erstellt werden sollen, skaliert. Performance kann besser werden.
	 * @param original
	 * @param breite
	 * @param hoehe
	 * @return
	 * */
	public BufferedImage skalaBild(BufferedImage original, int breite, int hoehe){
		
		BufferedImage skaliertesBild = new BufferedImage(breite, hoehe, original.getType());
		Graphics2D g2 = skaliertesBild.createGraphics();
		g2.drawImage(original,0,0,breite,hoehe,null);
		g2.dispose();
		
		return skaliertesBild;
	}
}
