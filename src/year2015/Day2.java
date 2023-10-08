package year2015;

import common.Challenge;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 extends Challenge<List<Day2.Box>, Integer> {
    public Day2(String input) {
        super(input);
    }

    @Override
    protected List<Box> parseInput(String fileName) {
        try (
                Stream<String> lines = Files.lines(Paths.get(fileName))
        ) {
            return lines.map(el -> el.split("x"))
                    .map(Day2.Box::new)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    @Override
    protected Integer computeFirst() {
        return getInput().stream()
                .map(box -> box.getArea() + box.getVolume() / box.getMaxDimension())
                .reduce(0, Integer::sum);
    }

    @Override
    protected Integer computeSecond() {
        return getInput().stream()
                .map(box -> box.getVolume() + 2 * (box.height + box.width + box.length - box.getMaxDimension()))
                .reduce(0, Integer::sum);
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
