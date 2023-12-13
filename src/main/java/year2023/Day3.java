package year2023;

import common.Challenge;
import common.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Challenge<List<String>, Integer> {

    private final Pattern p1 = Pattern.compile("(\\d+)");
    private final Pattern p2 = Pattern.compile("(\\*)");

    @Override
    protected List<String> parseInput(String filePath) {
        return InputParser.parseToList(filePath);
    }

    @Override
    protected void computeAnswers() {
        List<String> in = getInput();

        int inputSize = in.size();
        int first = 0;
        int second = 0;

        for (int i = 0; i < getInput().size(); i++) {
            String prev = i == 0 ? "" : in.get(i - 1);
            String current = in.get(i);
            String next = i == inputSize - 1 ? "" : in.get(i + 1);
            Matcher m1 = p1.matcher(current);
            Matcher m2 = p2.matcher(current);
            first += computeFirst(m1, prev, current, next);
            second += computeSecond(m2, prev, current, next);
        }

        setFirst(first);
        setSecond(second);
    }

    private int computeFirst(Matcher m, String prev, String current, String next) {
        int result = 0;
        while (m.find()) {
            int start = m.start(0);
            int end = m.end(0);

            boolean hasNeighbourPrev = hasNeighbourSignInRow(start, end, prev);
            boolean hasNeighbourCurrent = hasNeighbourSignInRow(start, end, current);
            boolean hasNeighbourNext = hasNeighbourSignInRow(start, end, next);

            if (hasNeighbourPrev || hasNeighbourCurrent || hasNeighbourNext) {
                result += Integer.parseInt(m.group(0));
            }
        }
        return result;
    }

    private boolean hasNeighbourSignInRow(int startIndex, int endIndex, String row) {
        if (row != null && !row.isEmpty()) {
            startIndex = Math.max(startIndex - 1, 0);
            endIndex = Math.min(row.length() - 1, endIndex + 1);

            for (int i = startIndex; i < endIndex; i++) {
                char ch = row.charAt(i);
                if (!Character.isDigit(ch) && ch != '.') {
                    return true;
                }
            }
        }
        return false;
    }

    private int computeSecond(Matcher m, String prev, String current, String next) {
        int result = 0;
        while (m.find()) {
            List<Integer> ratios = new ArrayList<>();
            int start = m.start(0);

            addPossibleGears(start, prev, ratios);
            addPossibleGears(start, current, ratios);
            addPossibleGears(start, next, ratios);
            if (ratios.size() == 2) {
                result += ratios.get(0) * ratios.get(1);
            }
        }
        return result;
    }

    private void addPossibleGears(int startIndex, String row, List<Integer> ratios) {
        Matcher matcher = p1.matcher(row);
        while (matcher.find()) {
            int start = matcher.start(0) - 1;
            int end = matcher.end(0);

            if (start <= startIndex && end >= startIndex) {
                ratios.add(Integer.parseInt(matcher.group(0)));
            }
        }
    }
}
