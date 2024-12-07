package year_2024.day6;

import common.InputParser;

import java.util.Arrays;

import static year_2024.day6.Direction.*;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/6.txt";

    static int[][]  input = InputParser.parseToList(RESOURCE_PATH, String::toCharArray)
            .stream()
            .map(arr-> {
                int[] ints = new int[arr.length];

                for (int i = 0; i < ints.length; i++) {
                    switch (arr[i]){
                        case '.': ints[i] = 0; break;
                        case '#': ints[i] = -1; break;
                        case '^': ints[i] = UP.getMarker(); break;
                        case '>': ints[i] = RIGHT.getMarker(); break;
                        case 'v': ints[i] = DOWN.getMarker(); break;
                        case '<': ints[i] = LEFT.getMarker(); break;
                        default: break;
                    }
                }
                return ints;
            })
            .toArray(int[][]::new);

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve());
        System.out.println(m.solve2());
    }

    private long solve() {
        Guard guard = new Guard(copy(input));
        try {
            while (true) guard.move();
        } catch (Exception ignored) {
            return guard.count;
        }
    }

    private long solve2() {
        long count = 0L;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                int[][] copied = copy(input);
                if(copied[i][j] != 0) {
                    continue;
                } else {
                    copied[i][j] = -1;
                }
                Guard guard = new Guard(copied);
                try {
                    while (true) guard.move();
                } catch (IllegalStateException ignored) {
                    count++;
                } catch (Exception ignored) {}
            }
        }
        return count;
    }

    private static int[][] copy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
                copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }

}
