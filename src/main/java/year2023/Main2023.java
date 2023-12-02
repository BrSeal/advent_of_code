package year2023;

import common.ChallengeExecutor;
import common.ChallengeWrapper;
import java.util.List;

public class Main2023 {
    private final static List<ChallengeWrapper> challenges =  List.of(
            new ChallengeWrapper(2023, 1, new Day1()),
            new ChallengeWrapper(2023, 2, new Day2())
    );

    public static void main(String[] args) {
        ChallengeExecutor.execute(challenges);
    }
}
