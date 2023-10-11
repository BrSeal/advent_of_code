import year2015.*;

import java.util.List;

public class Main {
    private final static List<ChallengeWrapper> CHALLENGES_2015 = List.of(
            new ChallengeWrapper(1, new Day1(), "resources/2015/day1.txt"),
            new ChallengeWrapper(2, new Day2(), "resources/2015/day2.txt"),
            new ChallengeWrapper(3, new Day3(), "resources/2015/day3.txt"),
            new ChallengeWrapper(4, new Day4(), "resources/2015/day4.txt", true),
            new ChallengeWrapper(5, new Day5(), "resources/2015/day5.txt", false, true),
            new ChallengeWrapper(6, new Day6(), "resources/2015/day6.txt")
    );

    public static void main(String[] args) {
        CHALLENGES_2015.forEach(System.out::println);
    }
}