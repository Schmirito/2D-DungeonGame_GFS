package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import objekte.Schlag;

public class NPC extends Entity {
	
	public boolean ichRede = false;
	public String dialogTexte[] = { "Oh Hallo!", "Mˆchtest du f¸r 150 M¸nzchen ein ominˆses Tr‰nkchen trinken?",
			"Gute Entscheidung, HeHe", "Tu dir keinen Zwang an." };
	public int dialogIndex = 0;
	Font schriftgroesse;

	/**Constructor des NPC's
	 * @param GamePanel
	 * @param weltX
	 * @param weltY
	 *  */
	public NPC(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;

		getImage();
	}

	public void update() {

	}
	
	public void getroffen(Entity entity, Schlag schlag) { // DIALOG MIT NPC
		ichRede = true;
		gp.gStatus = 2;
	}

	public void draw(Graphics2D g2) {
		BufferedImage charSprite = down;
		int bildschirmX = weltX - gp.kamera.weltX + gp.kamera.bildschirmX - (gp.feldGroeﬂe / 2);
		int bildschirmY = weltY - gp.kamera.weltY + gp.kamera.bildschirmY - (gp.feldGroeﬂe / 2);
		if (charSprite != null) {
			if (weltX + gp.feldGroeﬂe > gp.kamera.weltX - gp.kamera.bildschirmX
					&& weltX - gp.feldGroeﬂe < gp.kamera.weltX + gp.kamera.bildschirmX
					&& weltY + gp.feldGroeﬂe > gp.kamera.weltY - gp.kamera.bildschirmY
					&& weltY - gp.feldGroeﬂe < gp.kamera.weltY + gp.kamera.bildschirmY) {

				g2.drawImage(charSprite, bildschirmX, bildschirmY, gp.feldGroeﬂe, gp.feldGroeﬂe, null);
			}
		}
		if (ichRede) {
			int width = gp.feldGroeﬂe * 2;
			int height = gp.feldGroeﬂe * 1;
			gp.drawDialogWindow(g2, bildschirmX - (width / 2), bildschirmY - height, width, height);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
			g2.setColor(Color.WHITE);
			g2.drawString(dialogTexte[dialogIndex], bildschirmX - (width / 2), bildschirmY - gp.feldGroeﬂe);
		}

	}

	public void getImage() {
		up = setup("/objekte/Obj001Test");
		upLV = setup("/objekte/Obj001Test");
		upRV = setup("/objekte/Obj001Test");
		down = setup("/npc/haendlerNPCVorne-Sammy");
		downLV = setup("/objekte/Obj001Test");
		downRV = setup("/objekte/Obj001Test");
		left = setup("/objekte/Obj001Test");
		leftLV = setup("/objekte/Obj001Test");
		leftRV = setup("/objekte/Obj001Test");
		right = setup("/objekte/Obj001Test");
		rightLV = setup("/objekte/Obj001Test");
		rightRV = setup("/objekte/Obj001Test");

	}
}
