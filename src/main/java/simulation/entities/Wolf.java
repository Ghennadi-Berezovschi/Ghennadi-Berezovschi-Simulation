package simulation.entities;

import simulation.GameController;
import simulation.GameMap;
import simulation.Position;

// Wolf class, which represents a wolf in the simulation and extends the Creature class
public class Wolf extends Creature {
    private Position previousPosition; // Tracks the wolf's previous position

    // Constructor to initialize the wolf's starting position and HP
    public Wolf(Position position) {
        super(position, 100);  // Calls the parent Creature constructor to set the position and HP
        this.previousPosition = null; // Initially, the wolf has no previous position
    }

    // Method where the wolf hunts a rabbit at a given position
    public void huntRabbit(Position position, GameMap map) {
        map.removeEntity(position); // Remove the rabbit from the map (wolf eats the rabbit)
        this.decreaseHunger(50); // Decrease wolf's hunger by 50
        System.out.println("Wolf ate rabbit at position " + position + " hunger: " + getHunger() + " HP: " + getHp());
    }

    // Method to find a position where the rabbit or an empty spot is available for the wolf to move
    private Position findPositionRabbitOrEmpty(GameMap map) {
        Position[] possiblePositions = {
                new Position(getPosition().getRow(), getPosition().getColumn() - 1), // left
                new Position(getPosition().getRow(), getPosition().getColumn() + 1), // right
                new Position(getPosition().getRow() - 1, getPosition().getColumn()), // up
                new Position(getPosition().getRow() + 1, getPosition().getColumn()), // down
                new Position(getPosition().getRow(), getPosition().getColumn() - 2), // left (2 steps)
                new Position(getPosition().getRow(), getPosition().getColumn() + 2), // right (2 steps)
                new Position(getPosition().getRow() - 2, getPosition().getColumn()), // up (2 steps)
                new Position(getPosition().getRow() + 2, getPosition().getColumn()) // down (2 steps)
        };
        Position emptyPosition = null; // Track the first found empty position

        // Loop through all possible positions
        for (Position pos : possiblePositions) {
            if (map.isPositionValid(pos)) { // Check if the position is valid (within the map boundaries)
                Entity entity = map.getEntityAt(pos);
                if (entity instanceof Rabbit) {
                    return pos; // If a rabbit is found, return its position
                } else if (entity == null && emptyPosition == null) { // If an empty spot is found, save it
                    if (!pos.equals(previousPosition)) { // Ensure the wolf does not return to its previous position
                        emptyPosition = pos;
                    }
                }
            }
        }
        return emptyPosition; // Return the first found empty position if no rabbit was found
    }

    // Method to simulate the wolf's movement and actions on its turn
    @Override
    public void makeMove(GameMap map) {
        System.out.println("__________________________________");
        System.out.println("Wolf's turn: ");

        // If the wolf's HP is 0 or below, it dies and is removed from the map
        if (getHp() <= 0) {
            System.out.println("GameOver! Wolf has died and cannot move.");
            die(map);  // Call the die method to remove the wolf from the map
            return; // Exit the method as the wolf is dead
        }

        increaseHunger(); // Increase the wolf's hunger at each turn

        // Find a new position with a rabbit or an empty cell
        Position newPosition = findPositionRabbitOrEmpty(map);

        if (newPosition != null) { // If a valid new position is found
            Entity entityAtNewPosition = map.getEntityAt(newPosition);

            // If a rabbit is found at the new position, the wolf hunts it
            if (entityAtNewPosition instanceof Rabbit) {
                huntRabbit(newPosition, map); // Wolf hunts and eats the rabbit
            }

            // Update the wolf's previous position to its current position
            previousPosition = getPosition();

            // Remove the wolf from its old position
            map.removeEntity(getPosition());

            // Move the wolf to the new position
            setPosition(newPosition);
            map.setEntity(newPosition, this); // Update the map with the wolf's new position

            System.out.println("Wolf moved to position " + newPosition);
        } else {
            // If no valid new position is found, print a message
            System.out.println("Wolf cannot move, no free space available.");
        }

        // Print the wolf's current hunger and HP after the move
        System.out.println("Current wolf's hunger: " + getHunger() + " HP: " + getHp());
    }

    // Method to handle the wolf's death and remove it from the map
    @Override
    public void die(GameMap map) {
        System.out.println("Wolf has died at position " + getPosition());
        map.removeEntity(getPosition()); // Remove the wolf from the map
    }

    // toString method to represent the wolf with an emoji
    @Override
    public String toString() {
        return "\uD83D\uDC3A"; // Emoji representation of the wolf
    }
}