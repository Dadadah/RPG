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
	
	public volatile boolean threadSuspended;
	public void run() {
		k = "Dungeon";
		RPGGui.ticker = 1;
		while (true) {
            try {
                Thread.sleep(250);

                synchronized(this) {
                    while (!threadSuspended)
                        wait();
                }
            } catch (InterruptedException e){
            	
            }
            
            if (k.equals("Dungeon")) {
            	dungeon();
            }else if (k.equals("Town")) {
            	town();
            }
        }
	}
	
	public void dungeon() {
		String[] dialogs = {"Searching...", "You couldn't find anything!", "Found One!", "Monster: " + mob.getName() + " Level: " + mob.getLevel() + " Damage: " + mob.getLevel() + " Health: " + mob.getHealth(), };
		d = 0;
		searchamt = 1+search.nextInt(RPG.ply.getLevel()+1);
		if (searchamt > 0 && searchamt < 11) {
			if (searchamt < RPG.ply.getLevel()+1) {
				mob = new Monster(searchamt);
			}else{
				return;
			}
			if (d == 2){
				RPG.rpg.mobhealth.setVisible(true);
				RPG.rpg.mobhealthchange(mob.getHealth());
			}
			while(mob.getHealth() >=1){
				d = 0;
				playerdamage = 1+search.nextInt(10)*RPG.ply.getDamage();
				mob.applyDamage(playerdamage);
			    RPG.rpg.mobhealthchange(mob.getHealth());
				while (d == 0){
					RPG.rpg.setDialog("You attack the " + mob.getName() + " and deal " + playerdamage + " damage!");
				}
				int mobdamage = 1+search.nextInt(10)*mob.getLevel();
				RPG.ply.applyDamage(mobdamage);
			    RPG.rpg.healthchange();
				while (d == 1){
					RPG.rpg.setDialog("The " + mob.getName() + " " + mob.getAttackType() +" you and does "+mobdamage+" damage!");
				}
			    if (RPG.ply.getHealth() <=0){
			    	d = 0;
			    	while (d == 0){
			    		RPG.rpg.setDialog("You have died.");
			    	}
			    	k = "Town";
			    	RPG.rpg.mobhealth.setVisible(false);
			    	break;
			    }
			}
			if (RPG.ply.getHealth() >= 1 && mob.getHealth() <=0){
				RPG.rpg.mobhealth.setVisible(false);
				exp = mob.getExperience()/RPG.ply.getLevel();
			    boolean leveled = RPG.ply.addExperience(exp);
			    RPG.rpg.refreshGUI();
			    d = 0;
			    while (d == 0){
			    	RPG.rpg.setDialog("The " + mob.getName() + " has been slain. You gain " + exp + " experience.");
			    }
			    while (d == 1 && leveled){
			    	RPG.rpg.setDialog("Level up!");
			    }
			    leveled = false;
			}
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