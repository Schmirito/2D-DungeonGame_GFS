package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		/*Es wird ein neues Fenster namens window erstellt. Drückt man den close button, dann wird das Programm gestoppt*/
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Das Fenster so eingestellt, das man die größe nicht variable verstellen kann.
		 * Der Titel des Fensters soll "2D Dungeon Game" sein.*/
		window.setResizable(false);
		window.setTitle("2D Dungeon Game");
		
		/*Es wird ein GamePanel objekt erstellt, welches in window "eingepflegt" wird.
		 * GamePanel erbt von JPanel woruch es quasi die anzeige von dem JFrame ist.*/
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		/*Das Fenster wird auf die eingestellte Größe festgestellt.*/ 
		window.pack();
		
		/*Die standard position des Fensters ist im Mittelpunkt des Bildschirms.
		 *Das Fenster wird angezeigt.*/
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		/*Es wird über gamePanel die MEthode startGameThread aufgerufen.*/
		gamePanel.startGameThread();
	}

}
