package year_2024.day15;

abstract class Robot {
    static final char EMPTY = '.';
    static final char ROBOT = '@';
    static final char WALL = '#';

    char[][] map;
    int x;
    int y;

    protected Robot(char[][] map) {
        this.map = map;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == ROBOT) {
                    x = i;
                    y = j;
                    map[i][j] = EMPTY;
                    return;
                }
            }
        }
    }

    void move(Direction[] directions) {
        for (Direction direction : directions) {
            move(direction);
        }
    }


    abstract void move(Direction direction);
}
