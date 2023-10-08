package year2015;


import common.Challenge;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 extends Challenge<String, Integer> {

    public Day1(String input) {
        super(input);
    }

    @Override
    protected String parseInput(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    protected Integer computeFirst() {
        return getInput().chars()
                .map(ch -> ch == '(' ? 1 : -1)
                .reduce(0, Integer::sum);
    }

    @Override
    protected Integer computeSecond() {
        String input = getInput();
        int i = 0;
        int val = 0;
        while (val != -1 && i<input.length()) {
            val += (input.charAt(i) == '(' ? 1 : -1);
            i++;
        }
        return i;
    }
}
