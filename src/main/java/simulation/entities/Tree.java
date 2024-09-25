package simulation.entities;

import simulation.Position;

public class Tree extends EnvironmentObject {


    public Tree(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "\uD83C\uDF33";
    }
}
