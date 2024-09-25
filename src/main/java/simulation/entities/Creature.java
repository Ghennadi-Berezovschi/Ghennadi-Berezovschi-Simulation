package simulation.entities;

import simulation.GameMap;
import simulation.Position;

// Abstract class Creature that extends Entity. Represents creatures like Rabbit and Wolf.
abstract public class Creature extends Entity {
    private int hp;     // Health Points of the creature
    private int hunger; // Hunger level of the creature

    // Constructor to initialize the creature's position and HP
    public Creature(Position position, int hp) {
        super(position);  // Calls the constructor of the Entity class to set the position
        this.hp = hp;     // Sets the initial health points
        this.hunger = 0;  // Initializes hunger to 0
    }

    // Getter for hunger
    public int getHunger() {
        return hunger;
    }

    // Getter for health points
    public int getHp() {
        return hp;
    }

    // Setter for health points
    public void setHp(int hp) {
        this.hp = hp;
    }

    // Method to decrease health by a certain amount
    public void decreaseHp(int amount) {
        this.hp -= amount;  // Subtracts the given amount from HP
    }

    // Method to increase health by a certain amount
    public void increaseHp(int amount) {
        setHp(getHp() + amount);  // Increases the HP by the given amount
    }

    // Method to increase hunger level and possibly reduce health if hunger reaches 100
    public void increaseHunger() {
        this.hunger += 15;  // Increases hunger by 15 every time it's called
        System.out.println("Hunger increased to: " + this.hunger);

        // If hunger reaches 100, decrease health and reset hunger
        if (this.hunger >= 100) {
            decreaseHp(15);  // Reduce HP by 15 if hunger maxes out
            System.out.println("Hunger reached 100. HP decreased to: " + this.hp);
            this.hunger = 0;  // Reset hunger to 0
        }
    }

    // Abstract method for the creature to die (implementation will be in subclasses)
    public abstract void die(GameMap map);

    // Abstract method for the creature to make a move (implementation will be in subclasses)
    public abstract void makeMove(GameMap map);

    // Method to decrease hunger by a certain amount
    protected void decreaseHunger(int amount) {
        this.hunger -= amount;  // Reduces the hunger by the given amount
        if (this.hunger < 0) {
            this.hunger = 0;  // Ensures hunger doesn't go below 0
        }
    }
}