package year_2024.day15;

public class PartTwoRobot extends Robot {
    static final char BOX_LEFT_HALF = '[';
    static final char BOX_RIGHT_HALF = ']';

    protected PartTwoRobot(char[][] map) {
        super(map);
    }

    @Override
    void move(Direction direction) {
        if ((direction == Direction.UP || direction == Direction.DOWN) && canMoveVertically(x, y, direction)) {
            moveRecursiveVertically(x, y, direction);
            super.x += direction.dX;
            super.y += direction.dY;
        } else if ((direction == Direction.LEFT || direction == Direction.RIGHT) && canMoveHorizontally(x, y, direction)) {
            moveRecursiveHorizontally(x, y, direction);
            super.x += direction.dX;
            super.y += direction.dY;
        }
    }

    private boolean canMoveHorizontally(int x, int y, Direction direction) {
        return switch (map[x][y + direction.dY]) {
            case WALL -> false;
            case EMPTY -> true;
            default -> canMoveHorizontally(x, y + direction.dY, direction);
        };
    }

    private boolean canMoveVertically(int x, int y, Direction direction) {
        int leftY = y;
        int rightY = y;

        if (map[x + direction.dX][y] == BOX_LEFT_HALF) {
            rightY = y + 1;
        } else if (map[x + direction.dX][y] == BOX_RIGHT_HALF) {
            leftY = y - 1;
        }
        if (map[x + direction.dX][leftY] == WALL || map[x + direction.dX][rightY] == WALL) {
            return false;
        }

        boolean canMoveLeftHalf = switch (map[x + direction.dX][leftY]) {
            case WALL -> false;
            case EMPTY -> true;
            default -> canMoveVertically(x + direction.dX, leftY, direction);
        };

        boolean canMoveRightHalf = switch (map[x + direction.dX][rightY]) {
            case WALL -> false;
            case EMPTY -> true;
            default -> canMoveVertically(x + direction.dX, rightY, direction);
        };

        return canMoveLeftHalf && canMoveRightHalf;
    }

    private void moveRecursiveHorizontally(int x, int y, Direction direction) {
        if (map[x][y + direction.dY] == WALL) {
            return;
        }

        if (map[x][y + direction.dY] != EMPTY) {
            moveRecursiveHorizontally(x, y + direction.dY * 2, direction);
        }

        map[x][y + direction.dY] = map[x][y];
        if (map[x][y] != EMPTY) {
            map[x][y] = map[x][y - direction.dY];
            map[x][y - direction.dY] = EMPTY;
        }
    }

    private void moveRecursiveVertically(int x, int y, Direction direction) {
        int leftY = y;
        int rightY = y;

        if (map[x][y] == BOX_LEFT_HALF) {
            rightY = y + 1;
        } else if (map[x][y] == BOX_RIGHT_HALF) {
            leftY = y - 1;
        }

        if (map[x + direction.dX][leftY] != EMPTY) {
            moveRecursiveVertically(x + direction.dX, leftY, direction);
        }

        if (map[x + direction.dX][rightY] == BOX_LEFT_HALF) {
            moveRecursiveVertically(x + direction.dX, rightY, direction);
        }

        map[x + direction.dX][leftY] = map[x][leftY];
        map[x + direction.dX][rightY] = map[x][rightY];
        map[x][leftY] = EMPTY;
        map[x][rightY] = EMPTY;
    }
}
