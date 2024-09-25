package simulation;

import java.util.Objects;

public class Position {
    int column;
    int row;

    public Position(int row, int column) {
        this.column = column;
        this.row = row;
    }
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return getColumn() == position.getColumn() && getRow() == position.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getRow());
    }
}
