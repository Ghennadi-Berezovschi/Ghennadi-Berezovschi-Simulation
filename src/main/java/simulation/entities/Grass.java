package simulation.entities;

import simulation.Position;

public class Grass extends EnvironmentObject {

    public Grass(Position position) {
        super(position);

    }

    @Override
    public String toString() {
        return "\uD83C\uDF3F";
    }
}
