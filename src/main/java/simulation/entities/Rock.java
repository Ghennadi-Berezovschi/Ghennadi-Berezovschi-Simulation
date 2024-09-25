package simulation.entities;

import simulation.Position;

public class Rock extends EnvironmentObject{


    public Rock(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "\uD83E\uDEA8";
    }
}
