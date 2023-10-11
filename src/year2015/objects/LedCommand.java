package year2015.objects;

public class LedCommand {
    private final Coordinates upperLeftCorner;
    private final Coordinates lowerRightCorner;
    private final Command command;

    public LedCommand(Coordinates upperLeftCorner, Coordinates lowerRightCorner, Command command) {
        this.upperLeftCorner = upperLeftCorner;
        this.lowerRightCorner = lowerRightCorner;
        this.command = command;
    }

    public Coordinates getUpperLeftCorner() {
        return upperLeftCorner;
    }

    public Coordinates getLowerRightCorner() {
        return lowerRightCorner;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return String.format("%s (%d,%d) -> (%d,%d)", command, upperLeftCorner.x(), upperLeftCorner.y(), lowerRightCorner.x(), lowerRightCorner.y());
    }
}