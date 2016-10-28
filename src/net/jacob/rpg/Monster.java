package net.jacob.rpg;

import java.util.Random;

public class Monster extends AbstractEntity{
	
	private static String[] namesAttacks = {"Bat,bites", "Scorpion,stings", "Wolf,claws", "Giant Snail,runs over", "Frog,licks", "Crab,snaps at", "Duck,bites", "Penguin,smacks", "Platapus,smacks", "Turtle,bites"};
	
	private String attackType;

	public Monster(int level) {
		Random choose = new Random();
		String nameAttack = namesAttacks[choose.nextInt(namesAttacks.length)];
		String[] nameAndAttack = nameAttack.split(",");

		this.level = level;
		this.maxHealth = (level*10);
		this.setHealth(maxHealth);
		this.experience = (level*10);
		this.name = nameAndAttack[0];
		this.attackType = nameAndAttack[1];
	}

	public String getAttackType() {
		return attackType;
	}
}
