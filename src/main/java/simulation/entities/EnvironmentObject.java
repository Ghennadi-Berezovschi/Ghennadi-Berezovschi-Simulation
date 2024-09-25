
package simulation.entities;

import simulation.Position;

// Class representing objects in the environment, like trees, rocks, or grass
public class EnvironmentObject extends Entity {

    // Constructor to initialize the environment object with its position
    public EnvironmentObject(Position position) {
        super(position);  // Calls the constructor of the Entity class to set the position
    }

    // Method to return a string representation of the environment object
    // Currently, it returns null, meaning no specific string is assigned yet
    @Override
    public String toString() {
        return null;
    }
}

