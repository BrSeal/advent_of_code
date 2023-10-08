package year2015;

import common.Challenge;
import common.InputParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 extends Challenge<String, Integer> {
    Coordinates STARTING_POSITION = new Coordinates(0, 0);

    Coordinates positionSantaOnly = STARTING_POSITION;
    Coordinates positionRobot = STARTING_POSITION;
    Coordinates positionSanta = STARTING_POSITION;
    Set<Coordinates> visited = new HashSet<>(List.of(STARTING_POSITION));
    Set<Coordinates> visitedRobotAndSanta = new HashSet<>(List.of(STARTING_POSITION));

    @Override
    protected String parseInput(String filePath) {
        return InputParser.parseToString(filePath);
    }

    @Override
    public void computeAnswers() {

        visited.add(positionSantaOnly);

        boolean santaTurn = true;
        for (char directionChar : getInput().toCharArray()) {
            positionSantaOnly = positionSantaOnly.move(directionChar);
            visited.add(positionSantaOnly);

            Coordinates position = santaTurn? moveAndGetSantaCoordinates(directionChar) : moveAndGetRobotCoordinates(directionChar);

            visitedRobotAndSanta.add(position);
            santaTurn = !santaTurn;
        }

        setFirst(visited.size());
        setSecond(visitedRobotAndSanta.size());
    }

    private Coordinates moveAndGetSantaCoordinates(char directionChar){
        positionSanta = positionSanta.move(directionChar);
        return positionSanta;
    }

    private Coordinates moveAndGetRobotCoordinates(char directionChar){
        positionRobot = positionRobot.move(directionChar);
        return positionRobot;
    }


    static class Coordinates {
        private final int x;

        private final int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinates move(char command) {
            return switch (command) {
                case '<' -> new Coordinates(x - 1, y);
                case '>' -> new Coordinates(x + 1, y);
                case '^' -> new Coordinates(x, y - 1);
                case 'v' -> new Coordinates(x, y + 1);
                default -> throw new IllegalStateException("Unexpected value: " + command);
            };
        }

        @Override
        public boolean equals(Object o) {
            return this == o || ((o instanceof Coordinates that) && x == that.x && y == that.y);
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }
}
