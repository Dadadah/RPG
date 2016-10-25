package net.jacob.rpg;

import java.util.Random;

public class GameCycle implements Runnable{
	int searchamt;
	int d;
	String k = "Town";
	int no = 0;
	int yes = 0;
	Random search = new Random();
	Monster mob;
	int playerdamage;
	int exp;
	boolean leveled = false;
	
	public volatile boolean threadSuspended = false;
	public void run() {
		k = "Dungeon";
		RPGGui.ticker = 1;
		while (true) {
            try {
                Thread.sleep(250);

                synchronized(this) {
                    while (threadSuspended)
                        wait();
                }
            } catch (InterruptedException e){
            	
            }
            
            System.out.println(d);
            
            if (k.equals("Dungeon")) {
            	dungeon();
            }else if (k.equals("Town")) {
            	town();
            }
            d++;
            threadSuspended = !threadSuspended;
        }
	}
	
	public void dungeon() {
		String[] dialogs = {"Searching...", "Found One!"  };
		//"Monster: " + mob.getName() + " Level: " + mob.getLevel() + " Damage: " + mob.getLevel() + " Health: " + mob.getHealth()
		if (d < 2) {
			RPG.rpg.setDialog(dialogs[d]);
		}
		if (d == 2){
			mob = new Monster(RPG.ply.getLevel());
			RPG.rpg.mobhealth.setVisible(true);
			RPG.rpg.mobhealthchange(mob.getHealth());
			d = 0;
		}
		if (d == 3) d = 0;
		if (d == 4 || d == 5) killedMonster();
		if (d == 6 || d == 7) death();
		if (mob != null){
			fightMonster();
		}
	}
	
	public void fightMonster() {
		if (d == 0) {
			playerdamage = 1+search.nextInt(10)*RPG.ply.getDamage();
			mob.applyDamage(playerdamage);
		    RPG.rpg.mobhealthchange(mob.getHealth());
			RPG.rpg.setDialog("You attack the " + mob.getName() + " and deal " + playerdamage + " damage!");
			if (mob.getHealth() <=0 && RPG.ply.getHealth() > 0){
				d = 3;
			}else if(RPG.ply.getHealth() <= 0) {
				d = 5;
			}
		}else if (d == 1){
			int mobdamage = 1+search.nextInt(10)*mob.getLevel();
			RPG.ply.applyDamage(mobdamage);
		    RPG.rpg.healthchange();
			RPG.rpg.setDialog("The " + mob.getName() + " " + mob.getAttackType() +" you and does "+mobdamage+" damage!");
			d = -1;
		}
	}
	
	public void killedMonster() {
		if (d == 4) {
			RPG.rpg.mobhealth.setVisible(false);
			exp = mob.getExperience()/RPG.ply.getLevel();
			leveled = false;
		    leveled = RPG.ply.addExperience(exp);
		    if (!leveled) d = -1;
		    RPG.rpg.refreshGUI();
		    RPG.rpg.setDialog("The " + mob.getName() + " has been slain. You gain " + exp + " experience.");
		}
	    if (d == 5 && leveled){
	    	RPG.rpg.setDialog("Level up!");
	    }
	    mob = null;
	}
	
	public void death() {
    	if (d == 6){
    		RPG.rpg.setDialog("You have died.");
    	}
    	if (d == 7) {
        	k = "Town";
        	RPG.rpg.mobhealth.setVisible(false);
    	}
	}
	
	public void town() {
		RPG.rpg.no.setVisible(false);
		RPG.cont = 0;
		yes = 0;
		no = 0;
		RPGGui.ticker = 0;
		while (RPG.cont == 0){
			RPG.rpg.setDialog("Welcome to town!");
		}
		RPG.ply.heal();
		RPG.rpg.healthchange();
		while (RPG.cont == 1){
			RPG.rpg.setDialog("You have been fully healed!");
		}
		RPGGui.ticker = 2;
		RPG.rpg.forward.setText("Yes");
		RPG.rpg.no.setText("No");
		RPG.rpg.no.setVisible(true);
		while (RPG.cont == 2){
			RPG.rpg.setDialog("Go back and fight?");
			if (yes == 1) {
				RPG.rpg.forward.setText("Continue");
				RPG.rpg.no.setText("Back to town");
				k = "Dungeon";
				RPGGui.ticker = 1;
				RPG.cont = 0;
			}
			while(no == 1) {
				RPG.rpg.setDialog("Save Game?");
				if (yes == 1){
					FileManager.save(RPG.ply);
					RPG.rpg.dispose();
					return;
				}
			}
			if (no == 2){
				RPG.rpg.dispose();
				return;
			}
		}
	}
}