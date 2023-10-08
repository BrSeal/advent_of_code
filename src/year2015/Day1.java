package year2015;


import common.Challenge;
import common.InputParser;

public class Day1 extends Challenge<String, Integer> {
    @Override
    protected String parseInput(String filePath) {
        return InputParser.parseToString(filePath);
    }

    @Override
    public void computeAnswers() {
        int first = 0;
        Integer second = null;

        for(int i = 0; i< getInput().length(); i++){
            first += getInput().charAt(i) == '(' ? 1 : -1;
            if(second == null && first == -1){
                second = i+1;
            }
        }
        setFirst(first);
        setSecond(second);
    }
}
