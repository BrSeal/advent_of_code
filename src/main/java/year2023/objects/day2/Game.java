package year2023.objects.day2;

import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class Game {
    private int id;
    List<GameRound> rounds;
    public Game(String s) {
        String[] splitted = s.split(": ");
        id = Integer.parseInt(splitted[0].split(" ")[1]);

        rounds = Arrays.stream(splitted[1].split("; "))
                       .map(GameRound::fromString)
                       .toList();
    }
}
