package year2023.objects.day2;

import lombok.Data;

@Data
public class GameRound {
    int blue;
    int red;
    int green;

    public static GameRound fromString(String s){
        GameRound round = new GameRound();
        String[] roundData = s.split(", ");

        for (String data : roundData) {
            String[] splittedData = data.split(" ");
            int value = Integer.parseInt(splittedData[0]);

            if (splittedData[1].equals("green")) {
                round.setGreen(value);
            } else if (splittedData[1].equals("red")) {
                round.setRed(value);
            } else {
                round.setBlue(value);
            }
        }
        return round;
    }
}
