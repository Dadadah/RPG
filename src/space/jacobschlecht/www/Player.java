package space.jacobschlecht.www;

public class Player extends AbstractEntity{
	
	private int damage;
	private int strength;
	private int intillect;
	private int stamina;
	private int defence;

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
		this.strength = 1;
		this.intillect = 1;
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

	public int getDamage() {
		return damage;
	}
	
	public boolean addExperience(int experience) {
		this.experience += experience;
		if (this.experience >= 100) {
			levelUp();
			return true;
		}
		return false;
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
