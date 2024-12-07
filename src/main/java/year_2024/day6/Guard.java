package year_2024.day6;

import lombok.Getter;
import lombok.Setter;


import static year_2024.day6.Direction.*;

@Getter
@Setter
public class Guard {
    public static final int ALL_DIRECTIONS = 1 + 2 + 4 + 8;

    public static final int OBSTACLE = -1;

    final int[][] map;
    int x;
    int y;
    Direction direction;
    long count;

    public Guard(int[][] map) {
        this.map = map;

        for (int h = 0; h < map.length; h++) {
            for (int w = 0; w < map[0].length; w++) {
                Direction d = Direction.getDirectionByMarker(map[h][w]);
                if (d != null) {
                    direction = d;
                    x = h;
                    y = w;
                    map[h][w] = 0;
                    return;
                }
            }
        }
    }

    public void move() {
        markCurrentSpotAsVisited();
        if (map[x + direction.getDx()][y + direction.getDy()] == OBSTACLE) {
            turnRight();
        } else {
            x += direction.getDx();
            y += direction.getDy();
        }
        isCycled();
    }

    private void markCurrentSpotAsVisited() {
        if (map[x][y] == 0) {
            count++;
        }
        map[x][y] = ALL_DIRECTIONS & (direction.getMarker() + map[x][y]);
    }

    private void isCycled() {
        if(
                (direction == UP && (map[x][y] & UP.getMarker()) == UP.getMarker()) ||
                (direction == RIGHT && (map[x][y] & RIGHT.getMarker()) == RIGHT.getMarker()) ||
                (direction == DOWN && (map[x][y] & DOWN.getMarker()) == DOWN.getMarker()) ||
                (direction == LEFT && (map[x][y] & LEFT.getMarker()) == LEFT.getMarker())
        ){
            throw new IllegalStateException("cycled");
        }
    }

    public void turnRight() {
        switch (direction) {
            case RIGHT -> direction = DOWN;
            case DOWN -> direction = LEFT;
            case LEFT -> direction = UP;
            case UP -> direction = RIGHT;
        }
    }
}
