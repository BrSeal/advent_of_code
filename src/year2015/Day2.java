package year2015;

import common.Challenge;
import common.InputParser;

import java.util.List;

public class Day2 extends Challenge<List<Day2.Box>, Integer> {
    @Override
    protected List<Box> parseInput(String filePath) {
        return InputParser.parseToList(filePath, (line) -> new Day2.Box(line.split("x")));
    }

    public void computeAnswers() {
        int first = 0;
        int second = 0;
        for (Box box : getInput()) {
            first += box.getArea() + box.getVolume() / box.getMaxDimension();
            second += box.getVolume() + 2 * (box.height + box.width + box.length - box.getMaxDimension());
        }
        setFirst(first);
        setSecond(second);
    }

    public static class Box {
        private final int height;
        private final int width;
        private final int length;

        public Box(int height, int width, int length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }

        public Box(String[] dimensions) {
            this(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[2]));
        }

        public int getMaxDimension() {
            return Math.max(Math.max(height, width), length);
        }

        public int getArea() {
            return 2 * (height * width + height * length + width * length);
        }

        public int getVolume() {
            return height * width * length;
        }
    }
}
