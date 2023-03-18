package main;

import java.awt.Color;

import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Dungeon Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
		
		gamePanel.startGameThread();
	}

}
