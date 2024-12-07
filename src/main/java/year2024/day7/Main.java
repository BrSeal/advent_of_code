package year2024.day7;

import common.InputParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String RESOURCE_PATH = "src/main/resources/2024/7.txt";

    private static List<InputDataDay6> input = InputParser.parseToList(RESOURCE_PATH, str -> {
        String[] splitted = str.trim().split("\\D+");

        long result = Long.parseLong(splitted[0]);

        List<Long> numbers = new ArrayList<>();

        for (int i = 1; i < splitted.length; i++) {
            numbers.add(Long.parseLong(splitted[i]));
        }

        return new InputDataDay6(result, numbers);
    });

    public static void main(String[] args) {
        System.out.println(solve());
        System.out.println(solve2());
    }

    private static long solve() {
        long result = 0;

        for (InputDataDay6 data : input) {
            if (isValid(data, data.getData().getFirst(), 1)) {
                result += data.getResult();
            }
        }
        return result;
    }

    private static boolean isValid(InputDataDay6 input, long current, int index) {
        if (index == input.getData().size()) {
            return input.result == current;
        }

        return isValid(input, current + input.getData().get(index), index + 1) ||
                isValid(input, current * input.getData().get(index), index + 1);
    }

    private static long solve2() {
        long result = 0;

        for (InputDataDay6 data : input) {
            if (isValid2(data, data.getData().getFirst(), 1)) {
                result += data.getResult();
            }
        }
        return result;
    }

    private static boolean isValid2(InputDataDay6 input, long current, int index) {
        if (index == input.getData().size()) {
            return input.result == current;
        }

        return isValid2(input, current + input.getData().get(index), index + 1) ||
                isValid2(input, current * input.getData().get(index), index + 1) ||
                isValid2(input, concatenate(current, input.getData().get(index)), index + 1);
    }

    public static long concatenate(Long a, Long b) {
        long current = b;
        while (current > 0) {
            a *= 10;
            current /= 10;
        }
        return a + b;
    }
}
