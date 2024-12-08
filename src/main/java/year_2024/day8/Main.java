package year_2024.day8;

import common.InputParser;

import java.util.*;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/8.txt";

    static char[][]  input = InputParser.parseToList(RESOURCE_PATH, String::toCharArray)
            .toArray(char[][]::new);

    Map<Character, ArrayList<int[]>> antennas = new HashMap<>();

    Set<Antinode> antinodes = new HashSet<>();

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
        System.out.println(m.solve());
        System.out.println(m.solve2());
    }

    private int solve(){
        for(Map.Entry<Character, ArrayList<int[]>> entry : antennas.entrySet()) {
            List<int[]> coords = entry.getValue();
            for (int i = 0; i < coords.size(); i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    calculateAntinode(coords.get(i), coords.get(j));
                    calculateAntinode(coords.get(j), coords.get(i));
                }
            }
        }
        return antinodes.size();
    }

    private int solve2(){
        antinodes.clear();
        for(Map.Entry<Character, ArrayList<int[]>> entry : antennas.entrySet()) {
            List<int[]> coords = entry.getValue();
            for (int i = 0; i < coords.size(); i++) {
                for (int j = i + 1; j < coords.size(); j++) {
                    calculateResonantAntinodes(coords.get(i), coords.get(j));
                    calculateResonantAntinodes(coords.get(j), coords.get(i));
                }
            }
        }
        return antinodes.size();
    }

    private void calculateAntinode(int[] firstAntenna, int[] secondAntenna){
        int antinodeX = firstAntenna[0] * 2 - secondAntenna[0];
        int antinodeY = firstAntenna[1] * 2 - secondAntenna[1];

        if(antinodeX >= 0 && antinodeX < input.length && antinodeY >= 0 && antinodeY < input.length) {
            antinodes.add(new Antinode(antinodeX, antinodeY));
        }
    }

    private void calculateResonantAntinodes(int[] firstAntenna, int[] secondAntenna){
        int deltaX = firstAntenna[0] - secondAntenna[0];
        int deltaY = firstAntenna[1] - secondAntenna[1];

        int harmonics = 0;
        while (firstAntenna[0] + harmonics*deltaX >= 0 &&
               firstAntenna[0] + harmonics*deltaX < input.length &&
               firstAntenna[1] + harmonics*deltaY >= 0 &&
               firstAntenna[1] + harmonics*deltaY < input.length
        ){
            antinodes.add(new Antinode(firstAntenna[0] + harmonics*deltaX, firstAntenna[1] + harmonics*deltaY));
            harmonics++;
        }
    }
}
