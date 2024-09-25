package simulation;

import simulation.entities.*;

public class MapConsoleRenderer {
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String GRAY = "\u001B[90m";
    private static final String WHITE = "\u001B[97m";

    private String getColoredSymbol(String symbol, Entity entity) {
        if (entity instanceof Tree) {
            return GREEN + symbol + RESET;
        } else if (entity instanceof Grass) {
            return GREEN + symbol + RESET;
        } else if (entity instanceof Rock) {
            return GRAY + symbol + RESET;
        } else if (entity instanceof Rabbit) {
            return WHITE + symbol + RESET;
        } else if (entity instanceof Wolf) {
            return GRAY + symbol + RESET;
        } else {
            return symbol;
        }
    }

    public void displayMap(GameMap map, int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Position position = new Position(row, column);
                Entity entity = map.getEntityAt(position);
                if (entity != null) {
                    String symbol = entity.toString();
                    System.out.print(getColoredSymbol(symbol, entity) + " "); // Add a space after each character
                } else {
                    System.out.print(" . "); // Printing  an empty cell
                }
            }
            System.out.println(); // Switch to a new line
        }
    }
}