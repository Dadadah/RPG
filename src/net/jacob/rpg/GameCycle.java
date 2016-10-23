package net.jacob.rpg;

import java.util.Random;

public class GameCycle {
	static int searchamt;
	static int d;
	public static int k = 0;
	public static void mobs () throws InterruptedException {
		Random search = new Random();
		int mobdamage;
		int mobhealth = 10;
		int playerdamage;
		int mobamt = 10;
		int exp;
		int moblvl = 1;
		int nomob;
		k = 0;
		String name = "";
		String attackType = "";
		RPGGui.ticker = 1;
		while(k == 0) {
			d = 0;
			searchamt = 1+search.nextInt(RPG.level+1);
			nomob = 0;
			if (searchamt > 0 && searchamt < 11) {
				if (RPG.level >= 1 && searchamt == 1) {
					name = "Bat";
					attackType = "bites";
					moblvl = 1;
					mobhealth = 10;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 2 && searchamt == 2) {
					name = "Scorpion";
					attackType = "stings";
					moblvl = 2;
					mobhealth = 30;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 3 && searchamt == 3) {
					name = "Wolf";
					attackType = "claws";
					moblvl = 3;
					mobhealth = 50;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 4 && searchamt == 4) {
					name = "Giant Snail";
					attackType = "runs over";
					moblvl = 4;
					mobhealth = 80;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 5 && searchamt == 5) {
					name = "FLYING FROG!";
					attackType = "licks";
					moblvl = 5;
					mobhealth = 100;
					mobamt = (10*moblvl)+10;
				}else if (RPG.level >= 6 && searchamt == 6) {
					name = "Crab";
					attackType = "snaps at";
					moblvl = 6;
					mobhealth = 120;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 7 && searchamt == 7) {
					name = "Duck";
					attackType = "bites";
					moblvl = 7;
					mobhealth = 150;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 8 && searchamt == 8) {
					name = "Penguin";
					attackType = "smacks";
					moblvl = 8;
					mobhealth = 230;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 9 && searchamt == 9) {
					name = "Platapus";
					attackType = "smacks";
					moblvl = 9;
					mobhealth = 270;
					mobamt = 10*moblvl;
				}else if (RPG.level >= 10 && searchamt == 10) {
					name = "TURTLE!";
					attackType = "bites";
					moblvl = 10;
					mobhealth = 500;
					mobamt = (10*moblvl)+10;
				}else{
					while (d == 0){
						RPG.rpg.setDialog("Searching...");
						Thread.sleep(250);
					}
					while(d == 1){
						RPG.rpg.setDialog("You couldn't find anything!");
						Thread.sleep(250);
					}
					mobamt = 0;
					mobhealth = 1;
					nomob = 1;
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
						RPG.rpg.mobhealthchange(mobhealth);
					}
					while (d == 2){
						RPG.rpg.setDialog("Monster: " +name+" Level: "+moblvl+" Damage: "+moblvl+" Health: "+mobhealth);
						Thread.sleep(250);
					}
					while(mobhealth >=1){
						if(d != -100)d=0;
						playerdamage = 1+search.nextInt(10)*RPG.damage;
					    mobhealth = mobhealth-playerdamage;
					    if (mobhealth < 0) {
					    	mobhealth = 0;
					    }
					    RPG.rpg.mobhealthchange(mobhealth);
						while (d == 0){
							RPG.rpg.setDialog("You attack the "+name+" and deal "+playerdamage+" damage!");
							Thread.sleep(250);
						}
						mobdamage = 1+search.nextInt(10)*moblvl;
					    RPG.health = RPG.health-mobdamage;
					    RPG.rpg.healthchange();
						while (d == 1){
							RPG.rpg.setDialog("The "+name+" " + attackType +" you and does "+mobdamage+" damage!");
							Thread.sleep(250);
						}
					    if (RPG.health <=0){
					    	d = 0;
					    	while (d == 0){
					    		RPG.rpg.setDialog("Sorry You Lost!");
								Thread.sleep(250);
					    	}
					    	mobhealth = 0;
					    	k++;
					    }
					    if (d == -100) {
					    	RPG.rpg.mobhealth.setVisible(false);
					    	break;
					    }
					}
					if (RPG.health >= 1 && mobhealth <=0){
						RPG.rpg.mobhealth.setVisible(false);
						exp = mobamt/RPG.level;
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