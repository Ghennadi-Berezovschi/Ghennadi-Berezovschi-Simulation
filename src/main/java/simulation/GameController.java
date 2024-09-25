package simulation;

import simulation.entities.Entity;
import simulation.entities.Rabbit;
import simulation.entities.RandomEvents;
import simulation.entities.Wolf;

import java.util.Scanner;

public class GameController {
    private boolean isRunning;  // Flag to check if the game is still running

    private GameMap map;  // The game map where rabbits and wolves interact
    private MapConsoleRenderer renderer;  // Responsible for displaying the map in the console
    private RandomEvents randomEvents;  // Handles random events like grass spawning

    public GameController() {
        this.isRunning = true;  // Initialize game state as running
        this.map = new GameMap(8, 8);  // Create a map of size 8x8
        this.renderer = new MapConsoleRenderer();  // Initialize the renderer for console display
        this.randomEvents = new RandomEvents();  // Initialize random events
    }

    // Start the game

    public void startGame() {
        System.out.println("Welcome to the game!");
        System.out.println("Press 's' to start the game.");

        Scanner scanner = new Scanner(System.in);

        // Initialize the default entities (rabbits, wolves, etc.) on the map
        map.setupDefaultEntitiesPosition();
        renderer.displayMap(map, 8, 8);  // Display the initial state of the map

        // Wait for the user to start the game
        while (isRunning) {
            String command = scanner.nextLine().toLowerCase();

            if ("s".equals(command)) {
                System.out.println("Game started.");
                gameLoop();  // Start the main game loop
            } else {
                System.out.println("Invalid command. Please enter 's' to start the game.");
            }
        }

        scanner.close();  // Close the scanner when the game ends
    }

    // The main game loop
    private void gameLoop() {
        while (isRunning) {
            boolean isWolfOrRabbitAlive = checkForRabbitsAndWolves();  // Check if both wolves and rabbits are still alive
            if (!isWolfOrRabbitAlive) {
                break;  // End the game loop if either wolves or rabbits are extinct
            }

            // Execute one turn of the game
            simulateTurn();

            // Delay for 1 second to make the turns more visible
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  // Print the error if there's an interruption in the delay
            }
        }
    }

    // Simulate one turn of the game
    private void simulateTurn() {
        // Find the first rabbit on the map
        Rabbit rabbit = null;
        for (Entity entity : map.entities.values()) {
            if (entity instanceof Rabbit) {
                rabbit = (Rabbit) entity;
                break;  // Stop after finding the first rabbit
            }
        }

        // Make the rabbit take its turn (move, eat, etc.)
        if (rabbit != null) {
            rabbit.makeMove(map);
        } else {
            System.out.println("No rabbit found.");
        }

        // Find the first wolf on the map
        Wolf wolf = null;
        for (Entity entity : map.entities.values()) {
            if (entity instanceof Wolf) {
                wolf = (Wolf) entity;
                break;  // Stop after finding the first wolf
            }
        }

        // Make the wolf take its turn (hunt, move, etc.)
        if (wolf != null) {
            wolf.makeMove(map);
        } else {
            System.out.println("No wolf found.");
        }

        // Handle random events like new grass growing
        randomEvents.spawnGrass(map);

        // Display the updated map after the turn
        renderer.displayMap(map, 8, 8);
    }

    // Check if there are still rabbits and wolves alive
    private boolean checkForRabbitsAndWolves() {
        boolean hasRabbit = false;
        boolean hasWolf = false;

        // Loop through all entities on the map
        for (Entity entity : map.entities.values()) {
            if (entity instanceof Rabbit) {
                hasRabbit = true;  // At least one rabbit is alive
            }
            if (entity instanceof Wolf) {
                hasWolf = true;  // At least one wolf is alive
            }

            // If both a rabbit and a wolf are found, continue the game
            if (hasRabbit && hasWolf) {
                return true;
            }
        }

        // If no rabbits are found, end the game
        if (!hasRabbit) {
            System.out.println("Game Over! No rabbits left on the map.");
        }
        // If no wolves are found, end the game
        if (!hasWolf) {
            System.out.println("Game Over! No wolves left on the map.");
        }

        // Stop the game since one or both species are extinct
        isRunning = false;
        return false;
    }
}