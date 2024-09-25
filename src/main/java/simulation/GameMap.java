package simulation;

import simulation.entities.*;
import java.util.HashMap;

// Class representing the game map, which stores entities and handles their positions
public class GameMap {
    private int rows; // Number of rows on the map
    private int columns; // Number of columns on the map
    HashMap<Position, Entity> entities = new HashMap<>(); // Stores entities on the map with their positions

    // Constructor to initialize the size of the map
    public GameMap(int rows, int columns) {
        this.rows = rows;  // Sets the number of rows
        this.columns = columns;  // Sets the number of columns
    }

    // Getter for the number of rows on the map
    public int getRows() {
        return rows;
    }

    // Getter for the number of columns on the map
    public int getColumns() {
        return columns;
    }

    // Method to place an entity at a specific position on the map
    public void setEntity(Position position, Entity entity) {
        entity.setPosition(position);  // Update the entity's position
        entities.put(position, entity);  // Add or update the entity in the map
    }

    // Method to remove an entity from a specific position on the map
    public void removeEntity(Position position) {
        entities.remove(position);  // Removes the entity at the given position
    }

    // Method to retrieve the entity at a specific position on the map
    public Entity getEntityAt(Position position) {
        return entities.get(position);  // Returns the entity at the position (or null if empty)
    }

    // Method to check if a position on the map is empty (no entity present)
    public boolean isPositionEmpty(Position position) {
        return !entities.containsKey(position);  // Returns true if no entity is present
    }

    // Method to check if a position is within the bounds of the map
    public boolean isPositionValid(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        // Returns true if the position is inside the map boundaries
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // Method to find the nearest empty and valid position for rabbit reproduction
    public Position findEmptyNearestValidPosition(Position position) {
        int row = position.getRow();
        int column = position.getColumn();

        // Possible positions for reproduction (left, right, up, down)
        Position[] possiblePositions = new Position[] {
                new Position(row, column - 1), // left
                new Position(row, column + 1), // right
                new Position(row - 1, column), // up
                new Position(row + 1, column)  // down
        };

        // Loop through possible positions and return the first valid empty position
        for (Position p : possiblePositions) {
            if (isPositionValid(p) && isPositionEmpty(p)) {
                return p;  // Returns the first found valid and empty position
            }
        }
        System.out.println("Not found cell for reproducing");  // Logs if no valid position is found
        return null; // Returns null if no valid position is available
    }

    // Method to set up default positions for entities at the start of the game
    public void setupDefaultEntitiesPosition() {
        // Places various entities like wolves, trees, rocks, grass, and rabbits on the map
        setEntity(new Position(2, 5), new Wolf(new Position(2, 5)));
        setEntity(new Position(1, 1), new Tree(new Position(1, 1)));
        setEntity(new Position(5, 4), new Tree(new Position(5, 4)));
        setEntity(new Position(0, 3), new Grass(new Position(0, 3)));
        setEntity(new Position(3, 3), new Grass(new Position(3, 3)));
        setEntity(new Position(1, 4), new Rock(new Position(1, 4)));
        setEntity(new Position(2, 2), new Rock(new Position(2, 2)));
        setEntity(new Position(6, 1), new Rabbit(new Position(6, 1)));
        setEntity(new Position(6, 6), new Grass(new Position(6, 6)));
        setEntity(new Position(6, 7), new Grass(new Position(6, 7)));
    }
}