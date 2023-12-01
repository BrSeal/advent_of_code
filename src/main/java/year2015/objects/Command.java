package year2015.objects;

public enum Command {
    TOGGLE("toggle"),
    TURN_ON("turn on"),
    TURN_OFF("turn off");

    public final String code;

    Command(String code) {
        this.code = code;
    }

    public static Command getFromString(String str) {
        for (Command c : values()) {
            if (str.startsWith(c.code)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Unknown command in string: " + str);
    }
}
