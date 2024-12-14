package year_2024.day8;

import common.InputParser;
import common.Point;

import java.util.*;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/8.txt";

    private static final char[][]  input = InputParser.parseToList(RESOURCE_PATH, String::toCharArray)
            .toArray(char[][]::new);

    Map<Character, ArrayList<int[]>> antennas = new HashMap<>();

    Set<Point> antinodes = new HashSet<>();

    private Main(){
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if(input[i][j] == '.') {
                    continue;
                }
                ArrayList<int[]> list = antennas.getOrDefault(input[i][j], new ArrayList<>());
                list.add(new int[]{i, j});
                antennas.put(input[i][j], list);
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve(true));
        System.out.println(m.solve(false));
    }

    private int solve(boolean firstHarmonicsOnly){
        for(Map.Entry<Character, ArrayList<int[]>> entry : antennas.entrySet()) {
            List<int[]> coords = entry.getValue();
            for (int i = 0; i < coords.size(); i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    calculateAntinodes(coords.get(i), coords.get(j), firstHarmonicsOnly);
                    calculateAntinodes(coords.get(j), coords.get(i), firstHarmonicsOnly);
                }
            }
        }
        return antinodes.size();
    }

    private void calculateAntinodes(int[] firstAntenna, int[] secondAntenna, boolean firstHarmonicOnly){
        int deltaX = firstAntenna[0] - secondAntenna[0];
        int deltaY = firstAntenna[1] - secondAntenna[1];

        int harmonics = firstHarmonicOnly? 1 : 0;
        while (firstAntenna[0] + harmonics*deltaX >= 0 &&
               firstAntenna[0] + harmonics*deltaX < input.length &&
               firstAntenna[1] + harmonics*deltaY >= 0 &&
               firstAntenna[1] + harmonics*deltaY < input.length
        ){
            antinodes.add(new Point(firstAntenna[0] + harmonics*deltaX, firstAntenna[1] + harmonics*deltaY));
            harmonics++;
            if(firstHarmonicOnly) {
                return;
            }
        }
    }
}
