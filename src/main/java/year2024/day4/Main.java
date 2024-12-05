package year2024.day4;

import common.InputParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/4.txt";

    private static final String WORD_TO_FIND = "XMAS";

    private static final char[][] SHAPE_TO_FIND = {
            {'M','.','M'},
            {'.','A','.'},
            {'S','.','S'}
    };

    char[][] input = InputParser.parseToList(RESOURCE_PATH).stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve());
        System.out.println(m.solve2());
    }

    private long solve() {
        long count = 0;
        //horizontal
        for (char[] line : input) {
            count += countMatchesAllDirections(line);
        }

        //vertical
        for (int horizontal = 0; horizontal < input[0].length; horizontal++) {
            char[] line = new char[input.length];
            for (int vertical = 0; vertical < input.length; vertical++) {
                line[vertical] = input[vertical][horizontal];
            }
            count += countMatchesAllDirections(line);
        }

        //diagonals
        count+= findDiagonal(input);
        count+= findDiagonal(rotate90(input));

        return count;
    }

    private long findDiagonal(char[][] input) {
        long count = 0;
        for (int i = 0; i < input.length; i++) {
            char[] lineUpper = new char[i + 1];
            for (int j = 0; j <= i; j++) {
                lineUpper[j] = input[j][i - j];
            }
            count += countMatchesAllDirections(lineUpper);

            if(i != input.length -1) {
                char[] lineLower = new char[i + 1];
                for (int j = 0; j <= i; j++) {
                    lineLower[j] = input[input.length - i + j - 1][input.length - j - 1];
                }
                count += countMatchesAllDirections(lineLower);
            }

        }
        return count;
    }

    private char[][] rotate90(char[][] arr){
        char[][] rotated = new char[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                rotated[j][arr.length-1-i] = arr[i][j];
            }
        }
        return rotated;
    }

    private long countMatchesAllDirections(char[] line) {
        String strait = String.valueOf(line);
        String reversed = new StringBuilder(strait).reverse().toString();
        return countMatches(reversed) + countMatches(strait);
    }

    private long countMatches(String line) {
        Matcher matcher = Pattern.compile(Main.WORD_TO_FIND).matcher(line);
        return matcher.results().count();
    }

    private long solve2() {
        long count = 0;
        count += countMatches(SHAPE_TO_FIND);

        char[][] rotated = rotate90(SHAPE_TO_FIND);
        count += countMatches(rotated);

        rotated = rotate90(rotated);
        count += countMatches(rotated);

        rotated = rotate90(rotated);
        count += countMatches(rotated);
        return count;
    }

    private long countMatches(char[][] pattern) {
        int count = 0;
        for (int i = 0; i < input.length-2; i++) {
            for (int j = 0; j < input.length-2; j++) {
                if(
                        pattern[0][0] == input[i][j] &&
                        pattern[0][2] == input[i][j+2] &&
                        pattern[1][1] == input[i+1][j+1] &&
                        pattern[2][0] == input[i+2][j] &&
                        pattern[2][2] == input[i+2][j+2]
                ){
                    count++;
                }
            }
        }
        return count;
    }


}
