package common;

public abstract class Challenge<InputType, OutputType> {
    private final InputType input;
    private final OutputType first;
    private final OutputType second;

    abstract protected InputType parseInput(String fileName);

    abstract protected OutputType computeFirst();

    abstract protected OutputType computeSecond();

    // constructor, getters and setters
    public Challenge(String inputFilePath) {
        input = parseInput(inputFilePath);
        first = computeFirst();
        second = computeSecond();
    }

    public InputType getInput() {
        return input;
    }

    public OutputType getFirst() {
        return first;
    }

    public OutputType getSecond() {
        return second;
    }
}
