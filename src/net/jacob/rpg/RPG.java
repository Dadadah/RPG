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
	public static void main(String[] args) throws IOException, InterruptedException {
		int k = 0;
		RPGGui.createFrame();
		String a = (String)JOptionPane.showInputDialog(null, "Load game or New game?", "Start", JOptionPane.INFORMATION_MESSAGE,null, aa, aa[1]);
		if (a == "New game"){
			level = 1;
			experience = 0;
			health = 50+(level*50);
	    	RPGGui.healthchange();
			damage = level;
			Name = (String)JOptionPane.showInputDialog("What is your name?");
			int g = JOptionPane.showConfirmDialog(null, "Are you ready to begin "+Name+"?\nYour stats begin at Level 1, Damage 1, and 100 HP.", "Begin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (g == JOptionPane.YES_OPTION){
				GameCycle.mobs();
			}
		}else{
			FileManager.load();	
			level = FileManager.lvl;
	    	RPGGui.lvlchange();
			experience = FileManager.esp;
	    	RPGGui.xpchange();
			health = 50+(level*50);
	    	RPGGui.healthchange();
			damage = level;
			Name = FileManager.name;
			RPGGui.no.setVisible(false);
			while (cont == 0) {
				RPGGui.setDialog("Hello, welcome back " + Name + "!");
				Thread.sleep(250);
			}
		}
		while (k == 0) {
			if (health <= 0) {
				k++;
			}else{
				RPGGui.no.setVisible(false);
				cont = 0;
				yes = 0;
				no = 0;
				RPGGui.ticker = 0;
				while (cont == 0){
					RPGGui.setDialog("Welcome to town!");
					Thread.sleep(250);
				}
				health = 50+(level*50);
		    	RPGGui.healthchange();
				while (cont == 1){
					RPGGui.setDialog("You have been fully healed!");
					Thread.sleep(250);
				}
				RPGGui.ticker = 2;
				RPGGui.forward.setText("Yes");
				RPGGui.no.setText("No");
				RPGGui.no.setVisible(true);
				while (cont == 2){
					RPGGui.setDialog("Go back and fight?");
					Thread.sleep(250);
					if (yes == 1) {
						RPGGui.forward.setText("Continue");
						RPGGui.no.setText("Back to town");
						GameCycle.mobs();
					}
					while(no == 1) {
						RPGGui.setDialog("Save Game?");
						if (yes == 1){
							FileManager.save();
							RPGGui.rpg.dispose();
							return;
						}
					}
					if (no == 2){
						RPGGui.rpg.dispose();
						return;
					}
				}
			}
		}	
	}
}