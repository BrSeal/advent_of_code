package year2015;

import common.ChallengeExecutor;
import common.ChallengeWrapper;

import java.util.List;

public class Main2015 {
    private final static List<ChallengeWrapper> challenges =  List.of(
//                    new ChallengeWrapper(2015, 1, new Day1()),
//                    new ChallengeWrapper(2015, 2, new Day2()),
//                    new ChallengeWrapper(2015, 3, new Day3()),
//                    new ChallengeWrapper(2015, 4, new Day4(), true),
//                    new ChallengeWrapper(2015, 5, new Day5(), false, true),
//                    new ChallengeWrapper(2015, 6, new Day6()),
//                    new ChallengeWrapper(2015, 7, new Day7()),
                    new ChallengeWrapper(2015, 8, new Day8())
            );

    public static void main(String[] args) {
        ChallengeExecutor.execute(challenges);
    }
}
