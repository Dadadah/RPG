package net.jacob.rpg;

public class Player {
	
	private int level;
	private int experience;
	private int maxHealth;
	private int health;
	private int damage;
	private final String name;
	
	public Player(String name, int level, int experience, int maxHealth) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.damage = this.level;
	}
	
	public int getMaxHealth() {
		return maxHealth;
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

	public int getDamage() {
		return damage;
	}

	public int getExperience() {
		return experience;
	}
	
	public boolean addExperience(int experience) {
		this.experience += experience;
		if (this.experience >= 100) {
			levelUp();
			return true;
		}
		return false;
	}
	
	public void heal() {
		this.health = this.maxHealth;
	}
	
	public void levelUp() {
		experience = experience-100;
		level++;
		damage++;
		maxHealth = 50+(level*50);
		heal();
	}
}
