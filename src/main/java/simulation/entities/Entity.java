package simulation.entities;

import simulation.Position;
import simulation.GameMap;

// Abstract class Entity representing any object or creature in the game world
abstract public class Entity {
    protected Position position;  // The position of the entity on the map

    // Constructor to initialize the entity's position
    public Entity(Position position) {
        this.position = position;  // Sets the initial position of the entity
    }

    // Getter method to return the current position of the entity
    public Position getPosition() {
        return position;
    }

    // Setter method to update the position of the entity
    public void setPosition(Position position) {
        this.position = position;  // Updates the position of the entity
    }

    // Abstract method to return a string representation of the entity
    // Subclasses (like Rabbit, Wolf, etc.) must implement this method
    public abstract String toString();
}