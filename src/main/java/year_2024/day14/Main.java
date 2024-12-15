package year_2024.day14;

import common.InputParser;
import common.Point;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/14.txt";

    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;

    private static final int SECONDS = 100;
    private static final int LOWER_BOUND = 600;
    private static final int UPPER_BOUND = 10000;

    private static final char[][] MAP = new char[HEIGHT][WIDTH];


    private static final char[][] SHAPE_TO_FIND = {
            {'*','*','*'},
            {'*','*','*'},
            {'*','*','*'}
    };

    private static final List<Robot> input = InputParser.parseToList(RESOURCE_PATH, robotStr -> {
        int[] split = Arrays.stream(robotStr.split("[^\\d\\-]"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();

        return new Robot(new Point(split[0], split[1]), split[2], split[3]);
    });

    public static void main(String[] args) {
        System.out.println(solve(SECONDS));
        solve2();
    }

    private static long solve(int seconds) {
        long[] safetyFactors = new long[4];
        for (Robot robot : input) {
            Point position = robot.getPosition();

            int finalX = (position.x() + seconds * robot.dX) % WIDTH;
            if (finalX < 0) {
                finalX += WIDTH;
            }

            int finalY = (position.y() + seconds * robot.dY) % HEIGHT;
            if (finalY < 0) {
                finalY += HEIGHT;
            }
            MAP[finalY][finalX] = '*';
            int quadrant = getQuadrant(finalX, finalY);
            if (quadrant >= 0) {
                safetyFactors[quadrant]++;
            }

        }

        return safetyFactors[0] * safetyFactors[1] * safetyFactors[2] * safetyFactors[3];
    }

    private static void solve2(){
        for (int i = LOWER_BOUND; i < UPPER_BOUND; i++) {
            for (char[] chars : MAP) {
                Arrays.fill(chars, '.');
            }
            solve(i);

            if(hasChristmasTree()){
                printMap();
                System.out.println(i);
            }
        }
    }

    private static int getQuadrant(int width, int height) {
        boolean inUpperHalf = height < HEIGHT / 2;
        boolean inLowerHalf = height > HEIGHT / 2;
        boolean inLeftHalf = width < WIDTH / 2;
        boolean inRightHalf = width > WIDTH / 2;

        if (inUpperHalf && inLeftHalf) {
            return 0;
        } else if (inUpperHalf && inRightHalf) {
            return 1;
        } else if (inLowerHalf && inLeftHalf) {
            return 2;
        } else if (inLowerHalf && inRightHalf) {
            return 3;
        }
        return -1;
    }

    // код позаимствован из Day 4
    private static boolean hasChristmasTree() {
        for (int i = 0; i < MAP.length-2; i++) {
            for (int j = 0; j < MAP[i].length-2; j++) {
                if(
                        SHAPE_TO_FIND[0][0] == MAP[i][j]   && SHAPE_TO_FIND[0][1] == MAP[i][j+1]   && SHAPE_TO_FIND[0][2] == MAP[i][j+2]    &&
                        SHAPE_TO_FIND[1][0] == MAP[i+1][j] && SHAPE_TO_FIND[1][1] == MAP[i+1][j+1] &&  SHAPE_TO_FIND[1][2] == MAP[i+1][j+2] &&
                        SHAPE_TO_FIND[2][0] == MAP[i+2][j] && SHAPE_TO_FIND[2][1] == MAP[i+2][j+1] && SHAPE_TO_FIND[2][2] == MAP[i+2][j+2]
                ){
                    return true;
                }
            }
        }
        return false;
    }

    private static void printMap() {
        for (char[] i : MAP) {
            for (char j : i) {
                    System.out.print(j);
            }
            System.out.println();
        }
    }
}
