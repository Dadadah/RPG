package space.jacobschlecht.www;

public abstract class AbstractEntity {
	protected String name;
	protected int level;
	protected int maxHealth;
	protected int experience;
	protected int health;
	
	public AbstractEntity() {

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

	public int getExperience() {
		return experience;
	}
	
	public void applyDamage(int damage) {
		health -= damage;
		if (health < 0) health = 0;
	}
	
	public void heal() {
		this.health = this.maxHealth;
	}
}
