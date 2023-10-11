package year2015;

import common.Challenge;
import common.InputParser;
import year2015.objects.Command;
import year2015.objects.Coordinates;
import year2015.objects.LedCommand;

import java.util.Arrays;
import java.util.List;

public class Day6 extends Challenge<List<LedCommand>, Integer> {

    private int[][] ledDisplay = new int[1000][1000];

    @Override
    protected List<LedCommand> parseInput(String filePath) {
        return InputParser.parseToList(filePath, this::stringToLedCommand);
    }

    @Override
    protected void computeAnswers() {
        getInput().forEach(cmd -> applyCommand(cmd, false));
        setFirst(computeLights());

        resetDisplay();
        getInput().forEach(cmd -> applyCommand(cmd, true));
        setSecond(computeLights());
    }

    private int computeLights() {
        return Arrays.stream(ledDisplay)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    private void applyCommand(LedCommand command, boolean brightness) {
        for (int i = command.getUpperLeftCorner().x(); i <= command.getLowerRightCorner().x(); i++) {
            for (int j = command.getUpperLeftCorner().y(); j <= command.getLowerRightCorner().y(); j++) {
                int value = ledDisplay[i][j];

                if (brightness) {
                    value = switch (command.getCommand()) {
                        case TOGGLE -> value + 2;
                        case TURN_ON -> value + 1;
                        case TURN_OFF -> Math.max(value - 1, 0);
                    };
                } else {
                    value = switch (command.getCommand()) {
                        case TOGGLE -> (value + 1) % 2;
                        case TURN_ON -> 1;
                        case TURN_OFF -> 0;
                    };
                }
                ledDisplay[i][j] = value;
            }
        }
    }

    private LedCommand stringToLedCommand(String str) {

        String[] coords = str.split("\\D+");
        int x1 = Integer.parseInt(coords[1]);
        int x2 = Integer.parseInt(coords[3]);
        int y1 = Integer.parseInt(coords[2]);
        int y2 = Integer.parseInt(coords[4]);

        return new LedCommand(
                new Coordinates(Math.min(x1, x2), Math.min(y1, y2)),
                new Coordinates(Math.max(x1, x2), Math.max(y1, y2)),
                Command.getFromString(str)
        );
    }

    private void resetDisplay(){
        ledDisplay = new int[1000][1000];
    }
}
