package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Challenge<InputType, OutputType> {
    private InputType input;
    private OutputType first;
    private OutputType second;

    abstract protected InputType parseInput(String fileName);

    abstract protected void computeAnswers();

    public void compute(String filePath){
        setInput(parseInput(filePath));
        computeAnswers();
    }
}
