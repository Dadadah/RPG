package net.jacob.rpg;

import java.util.Random;

public class GameCycle {
	static int searchamt;
	static int d;
	public static int k = 0;
	public static void mobs () throws InterruptedException {
		Random search = new Random();
		int playerdamage;
		int exp;
		int nomob;
		Monster mob;
		k = 0;
		RPGGui.ticker = 1;
		while(k == 0) {
			d = 0;
			searchamt = 1+search.nextInt(RPG.level+1);
			nomob = 0;
			if (searchamt > 0 && searchamt < 11) {
				if (searchamt < RPG.level+1) {
					mob = new Monster(searchamt);
				}else{
					while (d == 0){
						RPG.rpg.setDialog("Searching...");
						Thread.sleep(250);
					}
					while(d == 1){
						RPG.rpg.setDialog("You couldn't find anything!");
						Thread.sleep(250);
					}
					continue;
				}
				if (nomob == 0) {
					while (d == 0){
						RPG.rpg.setDialog("Searching...");
						Thread.sleep(250);
					}
					while (d == 1){
						RPG.rpg.setDialog("Found One!");
						Thread.sleep(250);
					}
					if (d == 2){
						RPG.rpg.mobhealth.setVisible(true);
						RPG.rpg.mobhealthchange(mob.getHealth());
					}
					while (d == 2){
						RPG.rpg.setDialog("Monster: " + mob.getName() + " Level: " + mob.getLevel() + " Damage: " + mob.getLevel() + " Health: " + mob.getHealth());
						Thread.sleep(250);
					}
					while(mob.getHealth() >=1){
						if(d != -100)d=0;
						playerdamage = 1+search.nextInt(10)*RPG.damage;
						mob.setHealth(mob.getHealth()-playerdamage);
					    if (mob.getHealth() < 0) {
					    	mob.setHealth(0);
					    }
					    RPG.rpg.mobhealthchange(mob.getHealth());
						while (d == 0){
							RPG.rpg.setDialog("You attack the " + mob.getName() + " and deal " + playerdamage + " damage!");
							Thread.sleep(250);
						}
						int mobdamage = 1+search.nextInt(10)*mob.getLevel();
					    RPG.health = RPG.health-mobdamage;
					    RPG.rpg.healthchange();
						while (d == 1){
							RPG.rpg.setDialog("The " + mob.getName() + " " + mob.getAttackType() +" you and does "+mobdamage+" damage!");
							Thread.sleep(250);
						}
					    if (RPG.health <=0){
					    	d = 0;
					    	while (d == 0){
					    		RPG.rpg.setDialog("Sorry You Lost!");
								Thread.sleep(250);
					    	}
					    	mob.setHealth(0);
					    	k++;
					    }
					    if (d == -100) {
					    	RPG.rpg.mobhealth.setVisible(false);
					    	break;
					    }
					}
					if (RPG.health >= 1 && mob.getHealth() <=0){
						RPG.rpg.mobhealth.setVisible(false);
						exp = mob.getExperience()/RPG.level;
					    RPG.experience = RPG.experience+exp;
					    RPG.rpg.xpchange();
					    d = 0;
					    while (d == 0){
					    	RPG.rpg.setDialog("Gratz, you won! You gained "+exp+" experience!");
							Thread.sleep(250);
					    }
					    if (RPG.experience >= 100){
					    	RPG.level = RPG.level + 1;
					    	RPG.rpg.lvlchange();
					    	RPG.experience = 0;
					    	RPG.rpg.xpchange();
					    	RPG.damage = RPG.damage + 1;
					    	while (d == 1){
					    		RPG.rpg.setDialog("CONGRATZ YOU LEVELED UP!!! Your attack has increased to "+RPG.damage+" and your health has increased by 50!!!");
								Thread.sleep(250);
					    	}
					    	RPG.health = 50+(RPG.level*50);
					    	RPG.rpg.healthchange();
					    }
					}
				}
			}
			
		}
	}
}