package year2023;

import common.Challenge;
import common.InputParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 extends Challenge<List<String>, Integer> {

    Map<String, Integer> convert;
    Pattern pattern;
    Pattern last;

    public Day1() {
        convert = new HashMap<>();
        convert.put("0", 0);
        convert.put("1", 1);
        convert.put("2", 2);
        convert.put("3", 3);
        convert.put("4", 4);
        convert.put("5", 5);
        convert.put("6", 6);
        convert.put("7", 7);
        convert.put("8", 8);
        convert.put("9", 9);
        convert.put("zero", 0);
        convert.put("one", 1);
        convert.put("two", 2);
        convert.put("three", 3);
        convert.put("four", 4);
        convert.put("five", 5);
        convert.put("six", 6);
        convert.put("seven", 7);
        convert.put("eight", 8);
        convert.put("nine", 9);

        String regex = convert.keySet().stream()
                              .reduce("0",
                                      (a, b) -> String.format("%s|%s", a, b),
                                      (a, b) -> String.format("%s|%s", a, b));

        pattern = Pattern.compile(String.format("(%s)", regex));
        last = Pattern.compile(String.format(".*(%s)", regex));
    }

    @Override
    protected List<String> parseInput(String filePath) {
        return InputParser.parseToList(filePath);
    }

    @Override
    public void computeAnswers() {
        int first = getInput().stream()
                              .mapToInt(this::findIntFirst)
                              .sum();

        int second = getInput().stream()
                               .mapToInt(this::findIntSecond)
                               .sum();

        setFirst(first);
        setSecond(second);
    }

    private int findIntFirst(String str) {
        int result = 0;
        char[] chars = str.toCharArray();

        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                result += Character.getNumericValue(ch) * 10;
                break;
            }
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            if (Character.isDigit(chars[i])) {
                result += Character.getNumericValue(chars[i]);
                break;
            }
        }

        return result;
    }

    private int findIntSecond(String str) {
        int result = 0;
        Matcher firstMatcher = pattern.matcher(str);
        Matcher lastMatcher = last.matcher(str);
        if (firstMatcher.find() && lastMatcher.find()) {
            MatchResult first = firstMatcher.toMatchResult();
            MatchResult last = lastMatcher.toMatchResult();
            result += convert.get(first.group(1)) * 10;
            result += convert.get(last.group(1));
        }

        return result;
    }
}
