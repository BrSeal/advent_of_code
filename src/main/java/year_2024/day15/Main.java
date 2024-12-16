package year_2024.day15;

import common.InputParser;

import java.util.ArrayList;
import java.util.List;

import static year_2024.day15.PartOneRobot.BOX;
import static year_2024.day15.PartTwoRobot.BOX_LEFT_HALF;
import static year_2024.day15.PartTwoRobot.BOX_RIGHT_HALF;
import static year_2024.day15.Robot.*;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/15.txt";

    private char[][] map;
    private char[][] map2;
    private final List<Direction[]> instructions = new ArrayList<>();

    private Main() {
        List<String> rawInput = InputParser.parseToList(RESOURCE_PATH);
        List<char[]> rawMap = new ArrayList<>();
        int i = 0;
        while (!rawInput.get(i).isBlank()) {
            String current = rawInput.get(i);
            rawMap.add(current.toCharArray());
            i++;
        }
        i++;
        map = rawMap.toArray(char[][]::new);
        map2 = convertToPart2Map();
        for (; i < rawInput.size(); i++) {
            instructions.add(rawInput.get(i).chars().mapToObj(c -> Direction.getDirectionByCharacter((char) c)).toArray(Direction[]::new));
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve(new PartOneRobot(m.map)));
        System.out.println(m.solve2(new PartTwoRobot(m.map2)));
    }

    private int solve(Robot robot) {
        for (Direction[] instructionArr : instructions) {
            robot.move(instructionArr);
        }
        return countGPS(map);
    }

    private int solve2(Robot robot) {
        for (Direction[] instructionArr : instructions) {
            robot.move(instructionArr);
        }
        return countGPS2(map2);
    }

    private int countGPS(char[][] map) {
        int total = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == BOX) {
                    total += 100 * x + y;
                }
            }
        }
        return total;
    }

    private int countGPS2(char[][] map) {
        int total = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == BOX_LEFT_HALF) {
                    total += 100 * x + y;
                }
            }
        }
        return total;
    }

    private char[][] convertToPart2Map() {
        char[][] newMap = new char[map.length][map[0].length * 2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case WALL:
                        newMap[i][j * 2] = WALL;
                        newMap[i][j * 2 + 1] = WALL;
                        break;
                    case ROBOT:
                        newMap[i][j * 2] = ROBOT;
                        newMap[i][j * 2 + 1] = EMPTY;
                        break;
                    case BOX:
                        newMap[i][j * 2] = BOX_LEFT_HALF;
                        newMap[i][j * 2 + 1] = BOX_RIGHT_HALF;
                        break;
                    default:
                        newMap[i][j * 2] = EMPTY;
                        newMap[i][j * 2 + 1] = EMPTY;
                        break;
                }
            }
        }
        return newMap;
    }
}
