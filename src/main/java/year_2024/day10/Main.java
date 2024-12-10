package year_2024.day10;

import common.InputParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/10.txt";

    private static Map<Point, Set<Point>> map = new HashMap<>();

    private static final int[][] input = InputParser.parseToList(RESOURCE_PATH, str -> str.chars()
                    .map(el -> el - '0')
                    .toArray()
            )
            .toArray(int[][]::new);

    public static void main(String[] args) {
        int[] result = solve();
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    private static int[] solve() {
        int totalScore = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[i][j] == 0) {
                    Point root = new Point(i, j, 0);
                    totalScore += countTrails(i, j, root);
                }
            }
        }

        int totalRating = map.keySet().stream().mapToInt(point -> point.rating).sum();

        return new int[]{totalScore, totalRating};
    }

    private static int countTrails(int i, int j, Point root) {
        if (input[i][j] == 9) {
            Set<Point> trailEnds = map.getOrDefault(root, new HashSet<>());
            map.putIfAbsent(root, trailEnds);
            root.rating++;
            return trailEnds.add(new Point(i, j, 0)) ? 1 : 0;
        }
        int current = input[i][j];

        int left = i > 0 && input[i - 1][j] == current + 1 ? countTrails(i - 1, j, root) : 0;
        int right = i < input.length - 1 && input[i + 1][j] == current + 1 ? countTrails(i + 1, j, root) : 0;
        int up = j > 0 && input[i][j - 1] == current + 1 ? countTrails(i, j - 1, root) : 0;
        int down = j < input.length - 1 && input[i][j + 1] == current + 1 ? countTrails(i, j + 1, root) : 0;

        return left + right + up + down;
    }
}
