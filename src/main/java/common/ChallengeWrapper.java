package common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChallengeWrapper {
    public static final String RESOURCES_PATH_BLUEPRINT = "src/main/resources/%d/day%d.txt";

    private final int year;
    private final int dayNumber;
    private final Challenge<?, ?> challenge;
    private final boolean skip;
    private final boolean printExecutionTime;


    public ChallengeWrapper(int year, int dayNumber, Challenge<?, ?> challenge) {
        this(year, dayNumber, challenge,false, false);
    }

    public ChallengeWrapper(int year, int dayNumber, Challenge<?, ?> challenge, boolean skip) {
        this(year, dayNumber, challenge, skip, false);
    }

    public void computeChallengeAnswers() {
        if (!skip) {
            long start = System.currentTimeMillis();
            challenge.compute(String.format(RESOURCES_PATH_BLUEPRINT, year, dayNumber));
            if (printExecutionTime) {
                System.out.println("Day " + dayNumber + " calc took " + (System.currentTimeMillis() - start) + " ms");
            }
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
