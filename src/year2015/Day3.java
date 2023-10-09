package year2015;

import common.Challenge;
import common.InputParser;
import year2015.objects.Coordinates;

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
            positionSantaOnly = move(positionSantaOnly, directionChar);
            visited.add(positionSantaOnly);

            Coordinates position = santaTurn? moveAndGetSantaCoordinates(directionChar) : moveAndGetRobotCoordinates(directionChar);

            visitedRobotAndSanta.add(position);
            santaTurn = !santaTurn;
        }

        setFirst(visited.size());
        setSecond(visitedRobotAndSanta.size());
    }

    private Coordinates move(Coordinates coordinates, char command) {
        return switch (command) {
            case '<' -> new Coordinates(coordinates.getX() - 1, coordinates.getY());
            case '>' -> new Coordinates(coordinates.getX() + 1, coordinates.getY());
            case '^' -> new Coordinates(coordinates.getX(), coordinates.getY() - 1);
            case 'v' -> new Coordinates(coordinates.getX(), coordinates.getY() + 1);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }

    private Coordinates moveAndGetSantaCoordinates(char directionChar){
        positionSanta = move(positionSanta, directionChar);
        return positionSanta;
    }

    private Coordinates moveAndGetRobotCoordinates(char directionChar){
        positionRobot = move(positionRobot, directionChar);
        return positionRobot;
    }
}
