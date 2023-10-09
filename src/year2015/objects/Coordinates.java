package year2015.objects;

public class Coordinates {
    private final int x;

    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || ((o instanceof Coordinates that) && x == that.x && y == that.y);
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
