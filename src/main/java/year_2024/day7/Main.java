package year_2024.day7;

import common.InputParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String RESOURCE_PATH = "src/main/resources/2024/7.txt";

    private static final List<Expression> input = InputParser.parseToList(RESOURCE_PATH, str -> {
        String[] splitted = str.trim().split("\\D+");
        long result = Long.parseLong(splitted[0]);
        List<Long> numbers = new ArrayList<>();

        for (int i = 1; i < splitted.length; i++) {
            numbers.add(Long.parseLong(splitted[i]));
        }

        return new Expression(result, numbers);
    });

    public static void main(String[] args) {
        System.out.println(solve(false));
        System.out.println(solve(true));
    }

    private static long solve(boolean canConcat) {
        long result = 0;

        for (Expression data : input) {
            if (isValid(data, data.data().getFirst(), 1, canConcat)) {
                result += data.result();
            }
        }
        return result;
    }

    private static boolean isValid(Expression input, long current, int index, boolean canConcat) {
        if (index == input.data().size()) {
            return input.result() == current;
        }

        return isValid(input, current + input.data().get(index), index + 1, canConcat) ||
                isValid(input, current * input.data().get(index), index + 1, canConcat) ||
                (canConcat && isValid(input, concatenate(current, input.data().get(index)), index + 1, true));
    }

    private static long concatenate(Long a, Long b) {
        long current = b;
        while (current > 0) {
            a *= 10;
            current /= 10;
        }
        return a + b;
    }
}
