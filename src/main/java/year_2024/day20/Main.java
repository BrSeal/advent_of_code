package year_2024.day20;

import common.InputParser;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/20.txt";

    private static final int WALL = -1;
    private static final int EMPTY = -2;

    private static final int DIFF = 100 ;

    private static final int BIG_SHORTCUT_LENGTH = 20;

    private int[][] map;
    private int x;
    private int y;

    private int endX;
    private int endY;

    public Main() {
        char[][] input = InputParser.parseToList(RESOURCE_PATH, String::toCharArray).toArray(char[][]::new);
        map = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '#') {
                    map[i][j] = WALL;
                }
                if (input[i][j] == 'S') {
                    x = i;
                    y = j;
                }

                if (input[i][j] == 'E') {
                    endX = i;
                    endY = j;
                    map[i][j] = EMPTY;
                }

                if(input[i][j] == '.') {
                    map[i][j] = EMPTY;
                }
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();

        int[] result = main.solve();
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    private int[] solve() {
        countSteps();

        int total = 0;
        int totalBig = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!isWall(i, j)) {
                    if (isShortCut(i, j, i - 2, j)) {
                        total++;
                    }
                    if (isShortCut(i, j, i + 2, j)) {
                        total++;
                    }
                    if (isShortCut(i, j, i, j - 2)) {
                        total++;
                    }
                    if (isShortCut(i, j, i, j + 2)) {
                        total++;
                    }

                    totalBig += countBigShortcuts(i, j);
                }
            }
        }

        return new int[]{total, totalBig};
    }

    private int countBigShortcuts(int x, int y) {
        int count = 0;

        for (int i = 1; i <= BIG_SHORTCUT_LENGTH; i++) {
            if (isShortCut(x, y, x + i, y, i)) {
                count++;
            }
            if (isShortCut(x, y, x - i, y, i)) {
                count++;
            }
            if (isShortCut(x, y, x, y - i, i)) {
                count++;
            }

            if (isShortCut(x, y, x, y + i, i)) {
                count++;
            }


            for (int j = 1; j <= BIG_SHORTCUT_LENGTH; j++) {
                if (i + j <= BIG_SHORTCUT_LENGTH) {
                    if (isShortCut(x, y, x - i, y - j, i + j)) {
                        count++;
                    }
                    if (isShortCut(x, y, x + i, y - j, i + j)) {
                        count++;
                    }
                    if (isShortCut(x, y, x + i, y + j, i + j)) {
                        count++;
                    }
                    if (isShortCut(x, y, x - i, y + j, i + j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void countSteps() {
        int localX = x;
        int localY = y;
        int score = 0;

        while (localX != endX || localY != endY) {
            if (isEmpty(localX + 1, localY)) {
                localX++;
            } else if (isEmpty(localX - 1, localY)) {
                localX--;
            } else if (isEmpty(localX, localY + 1)) {
                localY++;
            } else if (isEmpty(localX, localY - 1)) {
                localY--;
            }
            score++;
            map[localX][localY] = score;
        }
    }

    private boolean isWall(int x, int y) {
        return map[x][y] == WALL;
    }

    private boolean isEmpty(int x, int y) {
        return map[x][y] == EMPTY;
    }

    private boolean isOutBound(int n) {
        return n < 0 || n >= map.length;
    }

    private boolean isShortCut(int sourceX, int sourceY, int targetX, int targetY){
        return isShortCut(sourceX, sourceY, targetX, targetY, 2);
    }

    private boolean isShortCut(int sourceX, int sourceY, int targetX, int targetY, int delta) {

        if (isOutBound(sourceX) || isOutBound(sourceY) || isOutBound(targetX) || isOutBound(targetY)) {
            return false;
        }

        if (isWall(targetX, targetY) || isWall(sourceX, sourceY)) {
            return false;
        }

        if (map[targetX][targetY] - map[sourceX][sourceY] < DIFF + delta) {
            return false;
        }

        return true;
    }
}
