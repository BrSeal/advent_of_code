package year_2024.day16;

import common.InputParser;
import common.Point;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String RESOURCE_PATH = "src/main/resources/2024/16.txt";

    static final int WALL = -2;

    private static final int ROTATE_COST = 1000;
    private static final int STEP_COST = 1;

    private static final int[][] map = InputParser.parseToList(RESOURCE_PATH, str -> str.chars()
                    .map(ch -> ((char) ch) == '#' ? WALL : Integer.MAX_VALUE).toArray())
            .toArray(int[][]::new);

    private static final List<Set<Point>> minPaths = new ArrayList<>();

    private static int minScore;


    public static void main(String[] args) {
        System.out.println(solve());
        System.out.println(solve2());
        printMap();
    }

    private static int solve() {
        goRight(map.length - 2, 1, 0);
        minScore = map[1][map[1].length - 2];
        return minScore;
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

    private static long solve2() {
        searchMinPathsRight(map.length - 2, 1, 0, new LinkedHashSet<>());
        return minPaths.stream()
                .flatMap(Collection::stream)
                .distinct().count();
    }

    private static void searchMinPathsLeft(int x, int y, int currentScore, Set<Point> currentPath) {
        if (processAndFinish(x, y, currentScore, currentPath)) {
            return;
        }

        searchMinPathsUp(x - 1, y, currentScore  + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsDown(x + 1, y, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsLeft(x, y - 1, currentScore + STEP_COST, new LinkedHashSet<>(currentPath));
    }

    private static void searchMinPathsRight(int x, int y, int currentScore, Set<Point> currentPath) {
        if (processAndFinish(x, y, currentScore, currentPath)) {
            return;
        }

        searchMinPathsUp(x - 1, y, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsDown(x + 1, y, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsRight(x, y + 1, currentScore + STEP_COST, new LinkedHashSet<>(currentPath));
    }

    private static void searchMinPathsUp(int x, int y, int currentScore, Set<Point> currentPath) {
        if (processAndFinish(x, y, currentScore, currentPath)) {
            return;
        }

        searchMinPathsRight(x, y + 1, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsLeft(x, y - 1, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsUp(x - 1, y, currentScore + STEP_COST, new LinkedHashSet<>(currentPath));
    }

    private static void searchMinPathsDown(int x, int y, int currentScore, Set<Point> currentPath) {
        if (processAndFinish(x, y, currentScore, currentPath)) {
            return;
        }

        searchMinPathsRight(x, y + 1, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsLeft(x, y - 1, currentScore + STEP_COST + ROTATE_COST, new LinkedHashSet<>(currentPath));
        searchMinPathsDown(x + 1, y, currentScore + STEP_COST, new LinkedHashSet<>(currentPath));
    }

    private static boolean processAndFinish(int x, int y, int currentScore, Set<Point> currentPath) {
        if (currentScore > minScore ||
            map[x][y] == WALL ||
            map[x][y] > minScore ||
            (map[x][y] == minScore && (x != 1 || y != map[1].length - 2)) ||
            !currentPath.add(new Point(x, y))
        ) {
            return true;
        }

        if (x == 1 && y == map[1].length - 2) {
            minPaths.add(currentPath);
            System.out.println(currentScore);
            System.out.println(currentPath.size());
            System.out.println();
            return true;
        }

        return false;
    }

    private static void printMap() {
        Set<Point> points = minPaths.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == WALL) {
                    System.out.print("|");
                } else if (points.contains(new Point(x, y))) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
