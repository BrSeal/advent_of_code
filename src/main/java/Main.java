import common.ChallengeWrapper;
import year2015.*;
import year2015.Day7;

import java.util.List;

public class Main {
    private final static List<ChallengeWrapper> CHALLENGES_2015 = List.of(
            new ChallengeWrapper(1, new Day1(), "src/main/resources/2015/day1.txt"),
            new ChallengeWrapper(2, new Day2(), "src/main/resources/2015/day2.txt"),
            new ChallengeWrapper(3, new Day3(), "src/main/resources/2015/day3.txt"),
            new ChallengeWrapper(4, new Day4(), "src/main/resources/2015/day4.txt", true),
            new ChallengeWrapper(5, new Day5(), "src/main/resources/2015/day5.txt", false, true),
            new ChallengeWrapper(6, new Day6(), "src/main/resources/2015/day6.txt"),
            new ChallengeWrapper(7, new Day7(), "src/main/resources/2015/day6.txt")
    );

    public static void main(String[] args) {
        for (ChallengeWrapper challengeWrapper : CHALLENGES_2015) {
            challengeWrapper.computeChallengeAnswers();
            System.out.println(challengeWrapper);
        }
    }
}