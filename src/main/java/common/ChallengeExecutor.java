package common;

import java.util.List;

public final class ChallengeExecutor {
    private ChallengeExecutor(){}

    public static void execute(List<ChallengeWrapper> challenges) {
        for (ChallengeWrapper challengeWrapper : challenges) {
            challengeWrapper.computeChallengeAnswers();
            System.out.println(challengeWrapper);
        }
    }
}
