package simulation.entities;

import simulation.GameMap;
import simulation.Position;

import java.util.Random;

// Class that handles random events in the game, like grass spawning
public class RandomEvents {
    private Random random = new Random();  // Random number generator for creating random events

    // Method to spawn grass on the map with a small probability (1%)
    public void spawnGrass(GameMap map) {
        int chance = 1; // 1% chance for grass to spawn on each tile

        // Loop through every tile on the map
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getColumns(); col++) {
                Position position = new Position(row, col);  // Create a position object for each tile

                // If the position is empty and the random chance condition is met
                if (map.isPositionEmpty(position) && random.nextInt(100) < chance) {
                    map.setEntity(position, new Grass(position));  // Spawn a grass object at this position
                    System.out.println("Grass spawned at position: " + position);  // Log the grass spawning
                }
            }
        }
    }
}