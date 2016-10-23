import java.io.IOException;
import javax.swing.JOptionPane;


public class rpg_base {
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
		rpg_pane.createFrame();
		String a = (String)JOptionPane.showInputDialog(null, "Load game or New game?", "Start", JOptionPane.INFORMATION_MESSAGE,null, aa, aa[1]);
		if (a == "New game"){
			level = 1;
			experience = 0;
			health = 50+(level*50);
	    	rpg_pane.healthchange();
			damage = level;
			Name =  (String)JOptionPane.showInputDialog("What is your name?");
			int g = JOptionPane.showConfirmDialog(null, "Are you ready to begin "+Name+"?\nYour stats begin at Level 1, Damage 1, and 100 HP.", "Begin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (g == JOptionPane.YES_OPTION){
				rpg_mobs.mobs();
			}
		}else{
			rpg_save.load();	
			level = rpg_save.lvl;
	    	rpg_pane.lvlchange();
			experience = rpg_save.esp;
	    	rpg_pane.xpchange();
			health = 50+(level*50);
	    	rpg_pane.healthchange();
			damage = level;
			Name = rpg_save.name;
			rpg_pane.no.setVisible(false);
			while (cont == 0) {
				rpg_pane.setDialog("Hello, welcome back "+Name+"!");
				Thread.sleep(250);
			}
		}
		while (k == 0) {
			if (health <= 0) {
				k++;
			}else{
				rpg_pane.no.setVisible(false);
				cont = 0;
				yes = 0;
				no = 0;
				rpg_pane.ticker = 0;
				while (cont == 0){
					rpg_pane.setDialog("Welcome to town!");
					Thread.sleep(250);
				}
				health = 50+(level*50);
		    	rpg_pane.healthchange();
				while (cont == 1){
					rpg_pane.setDialog("You have been fully healed!");
					Thread.sleep(250);
				}
				rpg_pane.ticker = 2;
				rpg_pane.forward.setText("Yes");
				rpg_pane.no.setText("No");
				rpg_pane.no.setVisible(true);
				while (cont == 2){
					rpg_pane.setDialog("Go back and fight?");
					Thread.sleep(250);
					if (yes == 1) {
						rpg_pane.forward.setText("Continue");
						rpg_pane.no.setText("Back to town");
						rpg_mobs.mobs();
					}
					while(no == 1) {
						rpg_pane.setDialog("Save Game?");
						if (yes == 1){
							rpg_save.save();
							rpg_pane.rpg.dispose();
							return;
						}
					}
					if (no == 2){
						rpg_pane.rpg.dispose();
						return;
					}
				}
			}
		}	
	}
}