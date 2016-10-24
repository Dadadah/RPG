package net.jacob.rpg;

import java.io.IOException;
import javax.swing.JOptionPane;

public class RPG {
	static int cont = 0;
	static int no = 0;
	static int yes = 0;
	static Player ply;
	
	static final int startLevel = 1;
	static final int startMaxHealth = 50+(startLevel*50);
	
	static String aa [] = {"Load game", "New game"};
	
	public static RPGGui rpg;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int k = 0;
		rpg = new RPGGui();
		String a = (String)JOptionPane.showInputDialog(null, "Load game or New game?", "Start", JOptionPane.INFORMATION_MESSAGE,null, aa, aa[1]);
		if (a == "New game"){
			String name = (String)JOptionPane.showInputDialog("What is your name?");
			ply = new Player(name, startLevel, 0, startMaxHealth);
			rpg.healthchange();
			int g = JOptionPane.showConfirmDialog(null, "Would you like to go out and adventure?", "Begin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (g == JOptionPane.YES_OPTION){
				GameCycle.mobs();
			}
		}else{
			FileManager.load();
			ply = new Player(FileManager.name, FileManager.lvl, FileManager.esp, (50+(FileManager.lvl*50)));
			rpg.lvlchange();
			rpg.xpchange();
			rpg.healthchange();
			rpg.no.setVisible(false);
			while (cont == 0) {
				rpg.setDialog("Hello, welcome back " + ply.getName() + "!");
				Thread.sleep(250);
			}
		}
		while (k == 0) {
			if (ply.getHealth() <= 0) {
				k++;
			}else{
				rpg.no.setVisible(false);
				cont = 0;
				yes = 0;
				no = 0;
				RPGGui.ticker = 0;
				while (cont == 0){
					rpg.setDialog("Welcome to town!");
					Thread.sleep(250);
				}
				ply.heal();
				rpg.healthchange();
				while (cont == 1){
					rpg.setDialog("You have been fully healed!");
					Thread.sleep(250);
				}
				RPGGui.ticker = 2;
				rpg.forward.setText("Yes");
				rpg.no.setText("No");
				rpg.no.setVisible(true);
				while (cont == 2){
					rpg.setDialog("Go back and fight?");
					Thread.sleep(250);
					if (yes == 1) {
						rpg.forward.setText("Continue");
						rpg.no.setText("Back to town");
						GameCycle.mobs();
					}
					while(no == 1) {
						rpg.setDialog("Save Game?");
						if (yes == 1){
							FileManager.save();
							rpg.dispose();
							return;
						}
					}
					if (no == 2){
						rpg.dispose();
						return;
					}
				}
			}
		}	
	}
}