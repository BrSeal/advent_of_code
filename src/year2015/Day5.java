package year2015;

import common.Challenge;
import common.InputParser;

import java.util.List;
import java.util.Set;

public class Day5 extends Challenge<List<String>, Integer> {

    public static final Set<Character> VOWELS = Set.of('o', 'e', 'i', 'a', 'u');

    @Override
    protected List<String> parseInput(String filePath) {
        return InputParser.parseToList(filePath);
    }

    @Override
    protected void computeAnswers() {
        int firstCount = 0;
        int secondCount = 0;

        for (String str : getInput()) {
            if (isValidFirst(str)) {
                firstCount++;
            }

            if (isValidSecond(str)) {
                secondCount++;
            }
        }


        setFirst(firstCount);
        setSecond(secondCount);
    }

    boolean isValidFirst(String str) {
        boolean hasDoubledLetter = false;
        boolean containsRestrictedStrings = str.matches(".*(ab|cd|pq|xy).*");

        int vowelCount = VOWELS.contains(str.charAt(0)) ? 1 : 0;
        char prev = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            char current = str.charAt(i);
            if (prev == current) {
                hasDoubledLetter = true;
            }
            prev = current;

            if (VOWELS.contains(current)) {
                vowelCount++;
            }
        }
        return !containsRestrictedStrings && hasDoubledLetter && vowelCount >= 3;
    }

    boolean isValidSecond(String str) {
        boolean letterRepeats = false;
        boolean hasRepeatedPair = false;

        int i = 0;

        while (i < str.length() - 2 && !letterRepeats) {
            letterRepeats = str.charAt(i) == str.charAt(i + 2);
            i++;
        }

        i = 0;
        while (i < str.length() - 1 && !hasRepeatedPair) {
            hasRepeatedPair = str.indexOf(str.substring(i, i + 2), i + 2) != -1;
            i++;
        }
        return letterRepeats && hasRepeatedPair;
    }
}
