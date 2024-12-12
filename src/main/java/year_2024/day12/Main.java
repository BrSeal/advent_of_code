package year_2024.day12;

import common.InputParser;

import static java.lang.Character.toLowerCase;

public class Main {

    private static final int AREA = 0;
    private static final int PERIMETER = 1;
    private static final int CORNERS = 2;

    private static final String RESOURCE_PATH = "src/main/resources/2024/12.txt";

    private static char[][] input = InputParser.parseToList(RESOURCE_PATH, String::toCharArray)
            .toArray(char[][]::new);

    public static void main(String[] args) {
        int[] results = solve();
        System.out.println(results[0]);
        System.out.println(results[1]);
    }

    private static int[] solve() {
        int[] totals = new int[2];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[i][j] != toLowerCase(input[i][j])) {
                    int[] params = new int[3];
                    inkSpot(i, j, params);
                    totals[0] += params[AREA] * params[PERIMETER];
                    totals[1] += params[AREA] * params[CORNERS];
                }
            }
        }
        return totals;
    }

    private static void inkSpot(int i, int j, int[] params) {
        char visitedMarker = toLowerCase(input[i][j]);
        char marker = input[i][j];

        if(marker == visitedMarker) {
            return;
        }
        params[AREA]++;
        input[i][j] = visitedMarker;

        boolean hasUpperBound = i == 0 || (input[i - 1][j] != marker && input[i - 1][j] != visitedMarker);
        boolean hasLowerBound = i == input.length - 1 || (input[i + 1][j] != marker && input[i + 1][j] != visitedMarker);
        boolean hasLeftBound = j == 0 || (input[i][j - 1] != marker && input[i][j - 1] != visitedMarker);
        boolean hasRightBound = j == input.length - 1 || (input[i][j + 1] != marker && input[i][j + 1] != visitedMarker);

        if (hasUpperBound && hasLeftBound || (!hasUpperBound && !hasLeftBound && input[i - 1][j - 1] != marker && input[i - 1][j - 1] != visitedMarker)) {
            params[CORNERS]++;
        }
        if (hasUpperBound && hasRightBound || (!hasUpperBound && !hasRightBound && input[i - 1][j + 1] != marker && input[i - 1][j + 1] != visitedMarker)) {
            params[CORNERS]++;
        }
        if (hasLowerBound && hasLeftBound || (!hasLowerBound && !hasLeftBound && input[i + 1][j - 1] != marker && input[i + 1][j - 1] != visitedMarker)) {
            params[CORNERS]++;
        }
        if (hasLowerBound && hasRightBound || (!hasLowerBound && !hasRightBound && input[i + 1][j + 1] != marker && input[i + 1][j + 1] != visitedMarker)) {
            params[CORNERS]++;
        }

        // up
        if (hasUpperBound) {
            params[PERIMETER]++;
        } else {
            inkSpot(i - 1, j, params);
        }

        // down
        if (hasLowerBound) {
            params[PERIMETER]++;
        } else {
            inkSpot(i + 1, j, params);
        }

        //left
        if (hasLeftBound) {
            params[PERIMETER]++;
        } else  {
            inkSpot(i, j - 1, params);
        }

        //right
        if (hasRightBound) {
            params[PERIMETER]++;
        } else {
            inkSpot(i, j + 1, params);
        }
    }
}
