package space.jacobschlecht.www;

import java.util.Random;

public class GameCycle implements Runnable{
	int searchamt;
	int d;
	String k = "Town";
	int no = 0;
	Random search = new Random();
	Monster mob;
	int playerdamage;
	int exp;
	boolean firstRun = true;
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
            
            if (firstRun) {
            	RPG.rpg.setDialog("Hello, welcome back " + RPG.ply.getName() + "!");
            	firstRun = false;
                threadSuspended = !threadSuspended;
            	continue;
            }
            
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
		String[] dialogs = {"Searching...", "Found One!"};
		if (d < 2) {
			RPG.rpg.no.setVisible(true);
			RPG.rpg.setDialog(dialogs[d]);
		}
		if (d == 2){
			mob = new Monster(RPG.ply.getLevel());
			RPG.rpg.mobhealth.setVisible(true);
			RPG.rpg.mobhealthchange(mob.getHealth());
			RPG.rpg.setDialog("Monster: " + mob.getName() + " Level: " + mob.getLevel() + " Damage: " + mob.getLevel() + " Health: " + mob.getHealth());
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
        	setToTown();
        	RPG.rpg.mobhealth.setVisible(false);
    	}
	}
	
	public void town() {
		if (d == 0) {
			RPG.rpg.no.setVisible(false);
			RPG.rpg.setDialog("Welcome to town!");
		}
		if (d == 1) {
			RPG.ply.heal();
			RPG.rpg.healthchange();
			RPG.rpg.setDialog("You have been fully healed!");
		}
		if (d == 2) {
			RPG.rpg.forward.setText("Yes");
			RPG.rpg.no.setText("No");
			RPG.rpg.no.setVisible(true);
			RPG.rpg.setDialog("Go back and fight?");
			no = 0;
			RPGGui.ticker = 2;
		}
		if (no == 0 && d == 3) {
			setToDungeon();
		}
		if (no == 1 && d == 3) {
			RPG.rpg.setDialog("Save Game?");
		}
		if (no == 1 && d == 4){
			FileManager.save(RPG.ply);
			RPG.rpg.dispose();
			return;
		}
		if (no == 2 && d == 4){
			RPG.rpg.dispose();
			return;
		}
	}
	
	public void setToDungeon() {
		RPG.rpg.forward.setText("Continue");
		RPG.rpg.no.setText("Back to town");
		k = "Dungeon";
		RPGGui.ticker = 1;
		d = 0;
		dungeon();
	}
	
	public void setToTown() {
		RPG.rpg.forward.setText("Continue");
		k = "Town";
		RPGGui.ticker = 1;
		d = 0;
		town();
	}
}