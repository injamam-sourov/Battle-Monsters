import java.text.DecimalFormat;

/* 
    generating random value between x and y: (y - x) * Math.random() + x
 */
public abstract class Character {
    // super class attributes
    private int attack, defence;
    private double maxHealth, currentHealth;
    // methods to be defined by sub classes
    abstract double takeAttack();
    abstract void takeDamage(double base);
    // common methods
    public boolean isDead() {
        if(this.currentHealth <= 0) {
            return true;
        } else {
            return false;
        }
    }
    public String stats() {
        DecimalFormat df = new DecimalFormat( "#.##" );
        double health = this.currentHealth;
        double maxHealth = this.maxHealth;
        return "ℹ️ attack: " + this.attack + ", defence: " + this.defence + ", health: " + df.format(health) + ", max health: " + df.format(maxHealth);
    }
    // gettrs and setters
    public int getAttack() {
        return this.attack;
    }
    public int getDefence() {
        return this.defence;
    }
    public double getCurrentHealth() {
        return this.currentHealth;
    }
    public double getMaxHealth() {
        return this.maxHealth;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }
    public void setMaxHealth(double health) {
        this.maxHealth = health;
    }
    public void setCurrentHealth(double health) {
        this.currentHealth = health;
    }
}

class Hero extends Character {
    // additional attributes
    private String name;
    private int level;
    // constructor
    public Hero(String name) {
        this.name = name;
        this.setLevel(1);
        this.setAttack(1);
        this.setDefence(1);
        this.setMaxHealth(10.0);
        this.setCurrentHealth(this.getMaxHealth());
    }
    // print Hero
    public String toString() {
        // format current heath to two decimal places
        DecimalFormat df = new DecimalFormat( "#.##" );
        double health = this.getCurrentHealth();
        if(health < 0) {health = 0;}
        
        return " " + this.name + ", health: " + df.format(health);
    }
    // superclass abstact method definitions
    public double takeAttack() {
        // damage =  hero attack * random [0.7 to 1]
        return this.getAttack() * (0.3 * Math.random() + 0.7);
    }
    public void takeDamage(double base) {
        // damage = base - hero defence * [0.2 to 0.4]
        double damage = base - this.getDefence() * (0.2 * Math.random() + 0.2);
        double health = this.getCurrentHealth() - Math.abs(damage);
        this.setCurrentHealth(health);
    }
    // class methods
    public void levelUp() {
        // attack, defence += 1, max health += 2, restore half of missing health
        this.setAttack(this.getAttack() + 1);
        this.setDefence(this.getDefence() + 1);
        this.setMaxHealth(this.getMaxHealth() + 2);
        double restore = (this.getMaxHealth() - this.getCurrentHealth()) / 2;
        this.setCurrentHealth(this.getCurrentHealth() + restore);
    }
    public void heal() throws NullPointerException {
        // throw Exception if already at max health
        if(this.getCurrentHealth() == this.getMaxHealth()) {
            throw new NullPointerException();
            // thowing exception auto returns
        }
        // restore 1/4th of max health
        double restore = this.getMaxHealth() / 4;
        // don't exceede max health
        if(this.getCurrentHealth() + restore >= this.getMaxHealth()) {
            this.setCurrentHealth(this.getMaxHealth());
        } else {
            this.setCurrentHealth(this.getCurrentHealth() + restore);
        }
    }
    // getters and setters
    public void setLevel(int level) {
        this.level = level;
    }
}

class Monster extends Character {
    // additional attributes
    private String breed;
    // constructors
    public Monster(String breed) {
        // default attack, defence = 1, max/curent health = 10
        this.breed = breed;
        this.setAttack(1);
        this.setDefence(1);
        this.setMaxHealth(10.0);
        this.setCurrentHealth(this.getMaxHealth());
    }
    public Monster() {
        // default breed 'Minion', attack, defence = 1, max/curent health = 2
        this.breed = "Minion";
        this.setAttack(1);
        this.setDefence(1);
        this.setMaxHealth(2.0);
        this.setCurrentHealth(this.getMaxHealth());
    }
    // toString() for print
    public String toString() {
        // format current heath to two decimal places
        DecimalFormat df = new DecimalFormat( "#.##" );
        double health = this.getCurrentHealth();
        if(health < 0) {health = 0;}
        
        return " " + this.breed + ", health: " + df.format(health);
    }
    // superclass abstact method definitions
    public double takeAttack() {
        // damage = monster attack * random [0.5 to 1]
        return this.getAttack() * (0.5 * Math.random() + 0.5); 
    }
    public void takeDamage(double base) {
        // damage = base - hero defence * [0.2 to 0.5]
        double damage = base - this.getDefence() * (0.3 * Math.random() + 0.2);
        double health = this.getCurrentHealth() - Math.abs(damage);
        this.setCurrentHealth(health);
    }
}