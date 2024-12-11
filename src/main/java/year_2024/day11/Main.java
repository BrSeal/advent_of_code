package year_2024.day11;

import common.InputParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final String RESOURCE_PATH = "src/main/resources/2024/11.txt";

    private static long[] input = Arrays.stream(InputParser.parse(RESOURCE_PATH).split("\\s+"))
            .mapToLong(Long::parseLong)
            .toArray();

    private static final int MULTIPLIER = 2024;
    private static final int NUMBER_OF_BLINKS_PART_1 = 25;
    private static final int NUMBER_OF_BLINKS_PART_2 = 75;

    private static final Map<CacheValue, Long> CACHE = new HashMap<>();

    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        System.out.println(solve(NUMBER_OF_BLINKS_PART_1));
        System.out.println(solve(NUMBER_OF_BLINKS_PART_2));
        System.out.println(System.currentTimeMillis() - millis);
    }

    private static long solve(int numberOfBlinks) {
        long total = 0;
        for (long i : input) {
            total += getCountAfterBlinks(i, numberOfBlinks);
        }
        return total;
    }

    private static long getCountAfterBlinks(long i, int numberOfBlinks) {
        Long cached = CACHE.get(new CacheValue(i, numberOfBlinks));
        if (cached != null) {
            return cached;
        }

        if (numberOfBlinks == 0) {
            return 1;
        }

        long result;
        if (i == 0) {
            result = getCountAfterBlinks(1, numberOfBlinks - 1);
        } else {
            int numberOfDigits = getNumberOfDigits(i);
            if (numberOfDigits % 2 == 0) {
                long splitPoint = (long) Math.pow(10, (double) numberOfDigits / 2);
                result = getCountAfterBlinks(i / splitPoint, numberOfBlinks - 1) + getCountAfterBlinks(i % splitPoint, numberOfBlinks - 1);
            } else {
                result = getCountAfterBlinks(i * MULTIPLIER, numberOfBlinks - 1);
            }
        }

        CACHE.put(new CacheValue(i, numberOfBlinks), result);
        return result;
    }

    private static int getNumberOfDigits(long number) {
        return (int) (Math.log10(number) + 1);
    }
}
