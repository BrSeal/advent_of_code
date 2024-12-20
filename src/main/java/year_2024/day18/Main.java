package year_2024.day18;

import common.InputParser;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int WALL = -1;
    private static final int NOT_REACHED = Integer.MAX_VALUE;

    private static final int FALLEN_COUNT = 1024;
    private static final int MAP_SIZE = 71;

    private static final String RESOURCE_PATH = "src/main/resources/2024/18.txt";
    private static final List<int[]> input = InputParser.parseToList(RESOURCE_PATH, el -> {
        String[] str = el.split(",");
        return new int[]{Integer.parseInt(str[1]), Integer.parseInt(str[0])};
    });

    private final int[][] map = new int[MAP_SIZE][MAP_SIZE];

    private Main(){
       
        for(int[] arr : map) {
            Arrays.fill(arr, NOT_REACHED);
        }

        for (int i = 0; i < FALLEN_COUNT; i++) {
            int[] coords = input.get(i);
            map[coords[0]][coords[1]] = WALL;
        }
    }


    public static void main(String[] args) {
     Main main = new Main();
     System.out.println(main.solve());


     int[] part2 = main.solve2();
     System.out.println(part2[1]+","+part2[0]);
    }


    private int solve() {
        step(0,0, 0);
        return map[MAP_SIZE-1][MAP_SIZE-1];
    }

    private int[] solve2(){
        for (int i = FALLEN_COUNT; i < input.size(); i++) {
            map[input.get(i)[0]][input.get(i)[1]] = WALL;

            if(solve() == NOT_REACHED) {
                return input.get(i);
            }

            resetMap();
        }
        return new int[]{-1, -1};
    }

    private void step(int x, int y, int cost) {
        if(x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            return;
        }
        if(map[x][y] == WALL) {
            return;
        }
        if(cost >= map[x][y]){
            return;
        }

        map[x][y] = cost;

        if(x == MAP_SIZE-1 && y == MAP_SIZE-1) {
            return;
        }
        step(x-1, y, cost+1);
        step(x+1, y, cost+1);
        step(x, y-1, cost+1);
        step(x, y+1, cost+1);
    }

    private void resetMap(){
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] != WALL) {
                    map[i][j] = NOT_REACHED;
                }
            }
        }
    }

    private void printMap() {
        for (int[] row : map) {
            for (int cell : row) {
                if(cell == NOT_REACHED) {
                    System.out.print('x');
                }else {
                    System.out.print(cell == WALL ? '#' : '.');
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
