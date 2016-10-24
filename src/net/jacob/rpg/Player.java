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

	public Player(String name) {
		this.name = name;
		this.level = 1;
		this.experience = 0;
		this.strength = 1;
		this.intillect = 1;
		this.stamina = 1;
		this.defence = 1;
		this.maxHealth = 50+(stamina*13);
		this.health = this.maxHealth;
		this.damage = this.strength;
	}
	
	public Player(String name, int level, int experience, int maxHealth) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.strength = 3;
		this.intillect = 5;
		this.stamina = 1;
		this.defence = 1;
		this.maxHealth = 50+(stamina*13);
		this.health = this.maxHealth;
		this.damage = strength;	
		
	}
	
	public Player(String name, int level, int experience, int strength, int intillect, int stamina, int defence) {
		this.name = name;
		this.level = level;
		this.experience = experience;
		this.strength = strength;
		this.intillect = intillect;
		this.stamina = stamina;
		this.defence = defence;
		this.maxHealth = 50+(stamina*13);
		this.health = this.maxHealth;
		this.damage = this.level;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getIntillect(){
		return intillect;
	}
	
	public int getStamina(){
		return stamina;
	}
	
	public int getDefence(){
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
		maxHealth = 50+(stamina*13);
		heal();
	}
}
