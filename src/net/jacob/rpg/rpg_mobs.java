package net.jacob.rpg;
import java.util.Random;


public class rpg_mobs {
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
		rpg_pane.ticker = 1;
		while(k == 0) {
			d = 0;
			searchamt = 1+search.nextInt(rpg_base.level+1);
			nomob = 0;
			if (searchamt > 0 && searchamt < 11) {
				if (rpg_base.level >= 1 && searchamt == 1) {
					name = "Bat";
					attackType = "bites";
					moblvl = 1;
					mobhealth = 10;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 2 && searchamt == 2) {
					name = "Scorpion";
					attackType = "stings";
					moblvl = 2;
					mobhealth = 30;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 3 && searchamt == 3) {
					name = "Wolf";
					attackType = "claws";
					moblvl = 3;
					mobhealth = 50;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 4 && searchamt == 4) {
					name = "Giant Snail";
					attackType = "runs over";
					moblvl = 4;
					mobhealth = 80;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 5 && searchamt == 5) {
					name = "FLYING FROG!";
					attackType = "licks";
					moblvl = 5;
					mobhealth = 100;
					mobamt = (10*moblvl)+10;
				}else if (rpg_base.level >= 6 && searchamt == 6) {
					name = "Crab";
					attackType = "snaps at";
					moblvl = 6;
					mobhealth = 120;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 7 && searchamt == 7) {
					name = "Duck";
					attackType = "bites";
					moblvl = 7;
					mobhealth = 150;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 8 && searchamt == 8) {
					name = "Penguin";
					attackType = "smacks";
					moblvl = 8;
					mobhealth = 230;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 9 && searchamt == 9) {
					name = "Platapus";
					attackType = "smacks";
					moblvl = 9;
					mobhealth = 270;
					mobamt = 10*moblvl;
				}else if (rpg_base.level >= 10 && searchamt == 10) {
					name = "TURTLE!";
					attackType = "bites";
					moblvl = 10;
					mobhealth = 500;
					mobamt = (10*moblvl)+10;
				}else{
					while (d == 0){
						rpg_pane.setDialog("Searching...");
						Thread.sleep(250);
					}
					while(d == 1){
						rpg_pane.setDialog("You couldn't find anything!");
						Thread.sleep(250);
					}
					mobamt = 0;
					mobhealth = 1;
					nomob = 1;
				}
				if (nomob == 0) {
					while (d == 0){
						rpg_pane.setDialog("Searching...");
						Thread.sleep(250);
					}
					while (d == 1){
						rpg_pane.setDialog("Found One!");
						Thread.sleep(250);
					}
					if (d == 2){
						rpg_pane.mobhealth.setVisible(true);
					    rpg_pane.mobhealthchange(mobhealth);
					}
					while (d == 2){
						rpg_pane.setDialog("Monster: " +name+" Level: "+moblvl+" Damage: "+moblvl+" Health: "+mobhealth);
						Thread.sleep(250);
					}
					while(mobhealth >=1){
						if(d != -100)d=0;
						playerdamage = 1+search.nextInt(10)*rpg_base.damage;
					    mobhealth = mobhealth-playerdamage;
					    if (mobhealth < 0) {
					    	mobhealth = 0;
					    }
					    rpg_pane.mobhealthchange(mobhealth);
						while (d == 0){
							rpg_pane.setDialog("You attack the "+name+" and deal "+playerdamage+" damage!");
							Thread.sleep(250);
						}
						mobdamage = 1+search.nextInt(10)*moblvl;
					    rpg_base.health = rpg_base.health-mobdamage;
				    	rpg_pane.healthchange();
						while (d == 1){
							rpg_pane.setDialog("The "+name+" " + attackType +" you and does "+mobdamage+" damage!");
							Thread.sleep(250);
						}
					    if (rpg_base.health <=0){
					    	d = 0;
					    	while (d == 0){
					    		rpg_pane.setDialog("Sorry You Lost!");
								Thread.sleep(250);
					    	}
					    	mobhealth = 0;
					    	k++;
					    }
					    if (d == -100) {
							rpg_pane.mobhealth.setVisible(false);
					    	break;
					    }
					}
					if (rpg_base.health >= 1 && mobhealth <=0){
						rpg_pane.mobhealth.setVisible(false);
						exp = mobamt/rpg_base.level;
					    rpg_base.experience = rpg_base.experience+exp;
					    rpg_pane.xpchange();
					    d = 0;
					    while (d == 0){
					    	rpg_pane.setDialog("Gratz, you won! You gained "+exp+" experience!");
							Thread.sleep(250);
					    }
					    if (rpg_base.experience >= 100){
					    	rpg_base.level = rpg_base.level + 1;
					    	rpg_pane.lvlchange();
					    	rpg_base.experience = 0;
						    rpg_pane.xpchange();
					    	rpg_base.damage = rpg_base.damage + 1;
					    	while (d == 1){
					    		rpg_pane.setDialog("CONGRATZ YOU LEVELED UP!!! Your attack has increased to "+rpg_base.damage+" and your health has increased by 50!!!");
								Thread.sleep(250);
					    	}
					    	rpg_base.health = 50+(rpg_base.level*50);
					    	rpg_pane.healthchange();
					    }
					}
				}
			}
			
		}
	}
}