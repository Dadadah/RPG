package net.jacob.rpg;

import java.io.IOException;
import javax.swing.JOptionPane;

public class RPG {
	static int level;
	static int health;
	static int damage;
	static int cont = 0;
	static int no = 0;
	static int yes = 0;
	static int experience;
	static String Name;
	static String aa [] = {"Load game", "New game"};
	
	public static RPGGui rpg;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int k = 0;
		rpg = new RPGGui();
		String a = (String)JOptionPane.showInputDialog(null, "Load game or New game?", "Start", JOptionPane.INFORMATION_MESSAGE,null, aa, aa[1]);
		if (a == "New game"){
			level = 1;
			experience = 0;
			health = 50+(level*50);
	    	rpg.healthchange();
			damage = level;
			Name = (String)JOptionPane.showInputDialog("What is your name?");
			int g = JOptionPane.showConfirmDialog(null, "Are you ready to begin "+Name+"?\nYour stats begin at Level 1, Damage 1, and 100 HP.", "Begin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (g == JOptionPane.YES_OPTION){
				GameCycle.mobs();
			}
		}else{
			FileManager.load();	
			level = FileManager.lvl;
			rpg.lvlchange();
			experience = FileManager.esp;
			rpg.xpchange();
			health = 50+(level*50);
			rpg.healthchange();
			damage = level;
			Name = FileManager.name;
			rpg.no.setVisible(false);
			while (cont == 0) {
				rpg.setDialog("Hello, welcome back " + Name + "!");
				Thread.sleep(250);
			}
		}
		while (k == 0) {
			if (health <= 0) {
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
				health = 50+(level*50);
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