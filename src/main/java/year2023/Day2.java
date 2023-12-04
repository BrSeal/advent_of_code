package year2023;

import common.Challenge;
import common.InputParser;
import year2023.objects.day2.Game;
import year2023.objects.day2.GameRound;
import java.util.List;

public class Day2 extends Challenge<List<Game>, Integer> {
    public static final int TOTAL_RED = 12;
    public static final int TOTAL_GREEN = 13;
    public static final int TOTAL_BLUE = 14;

    @Override
    protected List<Game> parseInput(String filePath) {
        return InputParser.parseToList(filePath, Game::new);
    }

    @Override
    public void computeAnswers() {
        setFirst(getInput().stream().filter(this::isPossible).mapToInt(Game::getId).sum());
        setSecond(getInput().stream().mapToInt(this::calculateMinPower).sum());
    }

    private boolean isPossible(Game game){
        for(GameRound round : game.getRounds()){
            if (round.getBlue()> TOTAL_BLUE || round.getGreen()>TOTAL_GREEN || round.getRed()> TOTAL_RED){
                return false;
            }
        }
        return true;
    }

    private int calculateMinPower(Game game){
        int maxBlue=0;
        int maxRed=0;
        int maxGreen=0;

        for (GameRound round : game.getRounds()) {
            maxBlue = Math.max(round.getBlue(), maxBlue);
            maxRed = Math.max(round.getRed(), maxRed);
            maxGreen = Math.max(round.getGreen(), maxGreen);
        }

        return maxBlue*maxGreen*maxRed;
    }
}
