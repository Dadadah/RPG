package net.jacob.rpg;

public class Player {
	
	private int level;
	private int experience;
	private int maxHealth;
	private int health;
	private int damage;
	private int strength;
	private int intillect;
	private int stamina;
	private int defence;
	
	private final String name;
	
	public Player(String name, int level, int experience, int maxHealth, int strength, int intillect, int stamina, int defence) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.damage = this.level;
		this.strength = strength;
		this.intillect = intillect;
		this.stamina = stamina;
		this.defence = defence;
	}
	
	public Player(String name, int level, int experience, int maxHealth) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.maxHealth = 50+(stamina*13);
		this.health = this.maxHealth;
		this.damage = this.level;
		this.strength = 3;
		this.intillect = 5;
		this.stamina = 1;
		this.defence = 1;	
		
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	
	public int getstrength() {
		return strength;
	}
	
	public int getintillect(){
		return intillect;
	}
	
	public int getstamina(){
		return stamina;
	}
	
	public int getdefence(){
		return defence;
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
		strength++;
		intillect++;
		stamina++;
		defence++;
		maxHealth = 50+(level*50);
		heal();
	}
}
