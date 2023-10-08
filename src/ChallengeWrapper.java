import common.Challenge;

public class ChallengeWrapper {
    private final int dayNumber;
    private final Challenge<?,?> challenge;
    public ChallengeWrapper(int dayNumber, Challenge<?, ?> challenge, String inputFilePath) {
        this.dayNumber = dayNumber;
        this.challenge = challenge;

        challenge.compute(inputFilePath);
    }

    @Override
    public String toString() {
        String dayNumberPart = "Day " + dayNumber + ":";
        String firstAnswerPart = "\tFirst answer:  " + (challenge.getFirst() == null ? "not present" : challenge.getFirst());
        String secondAnswerPart = "\tSecond answer: " + (challenge.getSecond() == null ? "not present" : challenge.getSecond());

        return String.format("%s\r\n%s\r\n%s", dayNumberPart, firstAnswerPart, secondAnswerPart);
    }
}
