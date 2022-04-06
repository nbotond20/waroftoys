package model.utility;

public class Position{

    public double x, y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Position(final Position p) {
        this.x = p.x;
        this.y = p.y;
    }
}
