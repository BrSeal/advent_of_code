package year2015;

import common.Challenge;
import common.InputParser;
import year2015.objects.Box;

import java.util.List;

public class Day2 extends Challenge<List<Box>, Integer> {
    @Override
    protected List<Box> parseInput(String filePath) {
        return InputParser.parseToList(filePath, (line) -> new Box(line.split("x")));
    }

    public void computeAnswers() {
        int first = 0;
        int second = 0;
        for (Box box : getInput()) {
            first += box.getArea() + box.getVolume() / box.getMaxDimension();
            second += box.getVolume() + 2 * (box.getHeight() + box.getWidth() + box.getLength() - box.getMaxDimension());
        }
        setFirst(first);
        setSecond(second);
    }

}
