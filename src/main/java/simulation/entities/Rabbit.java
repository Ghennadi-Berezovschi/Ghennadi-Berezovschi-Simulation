package simulation.entities;

import simulation.GameMap;
import simulation.Position;

public class Rabbit extends Creature {
    private int stepUntilReproduce;  // Counter to track when the rabbit can reproduce
    private Position previousPosition;  // Keeps track of the previous position of the rabbit


    // Constructor for initializing the rabbit with its starting position and HP
    public Rabbit(Position position) {
        super(position, 100);
        this.stepUntilReproduce = 2;  // Initialize reproduction counter
        this.previousPosition = null;  // Initially, the rabbit has no previous position
    }

    // Method for the rabbit to eat grass on a given position
    public void eatGrass(Position position, GameMap map) {
        map.removeEntity(position); // Remove the grass entity from the map
        this.decreaseHunger(20); // Decrease the rabbit's hunger
        System.out.println("Rabbit ate grass at position " + position + " hunger: " + getHunger() + " HP: " + getHp());
    }

    // Method for reproducing new rabbits if the conditions are met
    public void reproduceRabbits(GameMap map) {
        if (getHp() > 50 && getHunger() < 50) {
            System.out.println("Rabbit is reproducing");

            // Find nearest empty cell for the new rabbit
            Position newPosition = map.findEmptyNearestValidPosition(getPosition());

            if (newPosition != null) {
                Rabbit newRabbit = new Rabbit(newPosition);  // Create a new rabbit
                map.setEntity(newPosition, newRabbit);  // Place the new rabbit on the map
                System.out.println("New rabbit added at position " + newPosition);
            } else {
                System.out.println("No space available for new rabbit.");
            }
        }
    }

    // Method called when the rabbit dies
    @Override
    public void die(GameMap map) {
        System.out.println("Rabbit has died at position " + getPosition());
        map.removeEntity(getPosition()); // Remove the dead rabbit from the map
    }

    // Method to simulate a single move of the rabbit
    @Override
    public void makeMove(GameMap map) {
        System.out.println("Rabbit's turn: ");

        // If rabbit's HP is 0 or below, it dies
        if (getHp() <= 0) {
            die(map); // Removing rabbit from the map
            return;  // Stop further actions since the rabbit is dead
        }

        increaseHunger();  // Increase rabbit's hunger at each turn

        // Find a new position for the rabbit (either grass or empty)
        Position newPosition = findPositionGrassOrEmpty(map);

        if (newPosition != null && !newPosition.equals(previousPosition)) {  // If the new position is valid and not the previous one
            Entity entityAtNewPosition = map.getEntityAt(newPosition);  // Get the entity at the new position

            // If the new position has grass, the rabbit eats it
            if (entityAtNewPosition instanceof Grass) {
                eatGrass(newPosition, map);
            }

            // Update the previous position to the current position before moving
            previousPosition = getPosition();

            // Remove the rabbit from the current position
            map.removeEntity(getPosition());

            // Move the rabbit to the new position
            setPosition(newPosition);
            map.setEntity(newPosition, this);  // Update the map with the rabbit's new position

            System.out.println("Rabbit moved to position " + newPosition);
        } else {
            // If no suitable position is found, print a message
            System.out.println("Rabbit cannot move, no free space available.");
        }

        // Reproduction logic
        if (stepUntilReproduce <= 0) {
            reproduceRabbits(map);  // Rabbit reproduces
            stepUntilReproduce = 1;  // Reset the counter for the next reproduction
        } else {
            stepUntilReproduce--;  // Decrease the reproduction counter
        }

        // Print the current status of the rabbit (hunger and HP)
        System.out.println("Current rabbit's hunger: " + getHunger() + " HP: " + getHp());
    }

    // Find a new position with grass or an empty cell for the rabbit to move to
    private Position findPositionGrassOrEmpty(GameMap map) {
        // Possible movement directions: left, right, up, down
        Position[] possiblePositions = {
                new Position(getPosition().getRow(), getPosition().getColumn() - 1), // left
                new Position(getPosition().getRow(), getPosition().getColumn() + 1), // right
                new Position(getPosition().getRow() - 1, getPosition().getColumn()), // up
                new Position(getPosition().getRow() + 1, getPosition().getColumn())  // down
        };
        Position emptyPosition = null;  // Variable to store the first found empty position
        for (Position pos : possiblePositions) {
            if (map.isPositionValid(pos)) {  // Check if the position is valid (within map boundaries)
                Entity entity = map.getEntityAt(pos);  // Get the entity at the position
                if (entity instanceof Grass) {  // If it's grass, the rabbit will move there
                    return pos;
                } else if (entity == null && emptyPosition == null) {  // If the position is empty and no other empty position is found yet
                    if (!pos.equals(previousPosition)) {  // Ensure the rabbit is not moving to its previous position
                        emptyPosition = pos;
                    }
                }
            }
        }
        return emptyPosition;  // If no grass is found, return the first empty position
    }

    // Method to represent the rabbit as an emoji
    @Override
    public String toString() {
        return "\uD83D\uDC07"; // Rabbit emoji
    }
}