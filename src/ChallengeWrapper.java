import common.Challenge;

public class ChallengeWrapper {
    private final int dayNumber;
    private final Challenge<?, ?> challenge;
    private final boolean skip;

    public ChallengeWrapper(int dayNumber, Challenge<?, ?> challenge, String inputFilePath) {
        this(dayNumber, challenge, inputFilePath, false, false);
    }

    public ChallengeWrapper(int dayNumber, Challenge<?, ?> challenge, String inputFilePath, boolean skip) {
        this(dayNumber, challenge, inputFilePath, skip, false);
    }

    public ChallengeWrapper(int dayNumber, Challenge<?, ?> challenge, String inputFilePath, boolean skip, boolean printExecutionTime) {
        this.dayNumber = dayNumber;
        this.challenge = challenge;
        this.skip = skip;

        if (skip) {
            return;
        }

        long start = System.currentTimeMillis();
        challenge.compute(inputFilePath);
        if (printExecutionTime) {
            System.out.println("Day " + dayNumber + " calc took " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    @Override
    public String toString() {
        String first;
        String second;
        if (skip) {
            first = "skipped";
            second = "skipped";
        } else {
            first = challenge.getFirst() == null ? "not present" : challenge.getFirst().toString();
            second = challenge.getSecond() == null ? "not present" : challenge.getSecond().toString();
        }

        return String.format("""
                Day %s:
                    First answer:  %s
                    Second answer: %s""", dayNumber, first, second);
    }
}
