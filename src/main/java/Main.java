import common.ChallengeWrapper;
import year2015.*;
import year2015.Day7;

import java.util.List;
import java.util.Map;

public class Main {
    private final static Map<Integer, List<ChallengeWrapper>> challenges = Map.of(
            2015, List.of(
                    new ChallengeWrapper(1, new Day1()),
                    new ChallengeWrapper(2, new Day2()),
                    new ChallengeWrapper(3, new Day3()),
                    new ChallengeWrapper(4, new Day4(), true),
                    new ChallengeWrapper(5, new Day5(), false, true),
                    new ChallengeWrapper(6, new Day6()),
                    new ChallengeWrapper(7, new Day7())
            ),
            2023, List.of(
                    new ChallengeWrapper(1, new Day1())
            )
    );

    public static void main(String[] args) {
        executeYear(2023);
    }

    private static void executeYear(int year) {
        if (challenges.containsKey(year)) {
            for (ChallengeWrapper challengeWrapper : challenges.get(year)) {
                challengeWrapper.computeChallengeAnswers();
                System.out.println(challengeWrapper);
            }
        } else {
            System.out.println("Not found challegges for year " + year);
        }
    }
}