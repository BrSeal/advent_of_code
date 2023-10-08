package common;

public abstract class Challenge<InputType, OutputType> {
    private InputType input;
    private OutputType first;
    private OutputType second;

    abstract protected InputType parseInput(String fileName);

    abstract protected void computeAnswers();

    public void compute(String fileName){
        setInput(parseInput(fileName));
        computeAnswers();
    }

    // getters and setters

    public void setInput(InputType input) {
        this.input = input;
    }

    public InputType getInput() {
        return input;
    }

    public void setFirst(OutputType first) {
        this.first = first;
    }

    public OutputType getFirst() {
        return first;
    }

    public void setSecond(OutputType second) {
        this.second = second;
    }

    public OutputType getSecond() {
        return second;
    }
}
