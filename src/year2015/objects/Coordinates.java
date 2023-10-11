package year2015.objects;

public record Coordinates(int x, int y) {

    @Override
    public boolean equals(Object o) {
        return this == o || ((o instanceof Coordinates that) && x == that.x && y == that.y);
    }

}
