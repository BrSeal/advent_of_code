package year_2024.day15;

public class PartOneRobot extends Robot {
    static final char BOX = 'O';

    protected PartOneRobot(char[][] map) {
        super(map);
    }

    @Override
    void move(Direction direction) {
        int distanceToEmptyCell = getDistanceFromEmptyCell(direction);

        if (distanceToEmptyCell > 1) {
            map[x + direction.dX * distanceToEmptyCell][y + direction.dY * distanceToEmptyCell] = map[x + direction.dX][y + direction.dY];
            map[x + direction.dX][y + direction.dY] = EMPTY;
        }

        if (distanceToEmptyCell != 0) {
            x += direction.dX;
            y += direction.dY;
        }
    }

    int getDistanceFromEmptyCell(Direction direction) {
        int distance = 1;
        while (map[x + direction.dX * distance][y + direction.dY * distance] == BOX) {
            distance++;
        }
        return map[x + direction.dX * distance][y + direction.dY * distance] == EMPTY ? distance : 0;
    }

}
