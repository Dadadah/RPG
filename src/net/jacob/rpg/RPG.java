package net.jacob.rpg;

import javax.swing.JOptionPane;

public class RPG {
	static int cont = 0;
	static Player ply;
	
	static String aa [] = {"Load game", "New game"};
	
	public static RPGGui rpg;
	
	public static GameCycle cycle;
	
	public static void main(String[] args) throws InterruptedException {
		rpg = new RPGGui();
		cycle = new GameCycle();
		String a = (String)JOptionPane.showInputDialog(null, "Load game or New game?", "Start", JOptionPane.INFORMATION_MESSAGE,null, aa, aa[1]);
		if (a == "New game"){
			String name = (String)JOptionPane.showInputDialog("What is your name?");
			ply = new Player(name);
			rpg.healthchange();
		}else{
			ply = FileManager.load();
			rpg.refreshGUI();
			rpg.healthchange();
			rpg.no.setVisible(false);
			while (cont == 0) {
				rpg.setDialog("Hello, welcome back " + ply.getName() + "!");
				Thread.sleep(250);
			}
			rpg.no.setVisible(true);
		}
		Thread thread = new Thread(cycle);
        thread.start();
	}
}