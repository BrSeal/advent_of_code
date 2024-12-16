package year_2024.day16;

import common.InputParser;

public class Main {

    static final int EMPTY = -1;
    static final int WALL = -2;

    private static final int ROTATE_COST = 1000;
    private static final int STEP_COST = 1;

    private static final String RESOURCE_PATH = "src/main/resources/2024/16.txt";

    private static final int[][] map = InputParser.parseToList(RESOURCE_PATH, str -> str.chars()
                    .map(ch -> ((char) ch) == '#' ? WALL : Integer.MAX_VALUE).toArray())
            .toArray(int[][]::new);

    public static void main(String[] args) {
        System.out.println(solve());
    }

    private static int solve() {
        goRight(map.length-2, 1, 0);
        return map[1][map.length-2];
    }

    private static void goLeft(int x, int y, int score) {
        if (!processCell(x, y, score)) {
            return;
        }

        goUp(x - 1, y, score + ROTATE_COST + STEP_COST);
        goDown(x + 1, y, score + ROTATE_COST + STEP_COST);
        goLeft(x, y - 1, score + STEP_COST);
    }

    private static void goRight(int x, int y, int score) {
        if (!processCell(x, y, score)) {
            return;
        }

        goUp(x - 1, y, score + ROTATE_COST + STEP_COST);
        goDown(x + 1, y, score + ROTATE_COST + STEP_COST);
        goRight(x, y + 1, score + STEP_COST);
    }

    private static void goUp(int x, int y, int score) {
        if (!processCell(x, y, score)) {
            return;
        }

        goLeft(x, y - 1, score + ROTATE_COST + STEP_COST);
        goRight(x, y + 1, score + ROTATE_COST + STEP_COST);
        goUp(x - 1, y, score + STEP_COST);
    }

    private static void goDown(int x, int y, int score) {
        if (!processCell(x, y, score)) {
            return;
        }

        goLeft(x, y - 1, score + ROTATE_COST + STEP_COST);
        goRight(x, y + 1, score + ROTATE_COST + STEP_COST);
        goDown(x + 1, y, score + STEP_COST);

    }

    private static boolean processCell(int x, int y, int score) {
        if (map[x][y] == WALL || map[x][y] <= score) {
            return false;
        }

        map[x][y] = score;

        return true;
    }

    private static void printMap() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == EMPTY) {
                    System.out.print(" ");
                } else if (map[x][y] == WALL) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
