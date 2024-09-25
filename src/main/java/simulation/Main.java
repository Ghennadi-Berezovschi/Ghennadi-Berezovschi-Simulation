package simulation;

import simulation.entities.*;


/* This is a simple simulation game where rabbits and wolves interact on a game map.
   The rabbits reproducing, eating a grass and move around, while the wolf hunt rabbits
   for food. The game continues as both rabbit and wolf on the map.
   If rabbits or wolf are gone, the game ends.
 */


public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }

}





