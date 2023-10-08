import year2015.Day1;
import year2015.Day2;
import year2015.Day3;

import java.util.List;

public class Main {
    private final static List<ChallengeWrapper> CHALLENGES_2015 = List.of(
            new ChallengeWrapper(1, new Day1(), "resources/2015/day1.txt"),
            new ChallengeWrapper(2, new Day2(), "resources/2015/day2.txt"),
            new ChallengeWrapper(3, new Day3(), "resources/2015/day3test.txt")
    );

    public static void main(String[] args) {
        CHALLENGES_2015.forEach(System.out::println);
    }
}