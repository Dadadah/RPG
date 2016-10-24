package net.jacob.rpg;

import java.util.Random;

public class Monster {
	
	private static String[] namesAttacks = {"Bat,bites", "Scorpion,stings", "Wolf,claws", "Giant Snail,runs over", "Frog,licks", "Crab,snaps at", "Duck,bites", "Penguins,smacks", "Platapus,smacks", "Turtle,bites"};
	
	private final String name;
	private final String attackType;
	private final int level;
	private final int maxHealth;
	private final int experience;
	private int health;
	
	public Monster(int level) {
		this.level = level;
		this.maxHealth = (level*10);
		this.setHealth(maxHealth);
		this.experience = level*10;
		
		Random choose = new Random();
		String nameAttack = namesAttacks[choose.nextInt(namesAttacks.length)];
		String[] nameAndAttack = nameAttack.split(",");
		
		this.name = nameAndAttack[0];
		this.attackType = nameAndAttack[1];
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public String getAttackType() {
		return attackType;
	}

	public int getExperience() {
		return experience;
	}
}
