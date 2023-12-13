package year2023;

import common.Challenge;
import common.InputParser;
import year2023.objects.day4.Card;
import java.util.Arrays;
import java.util.List;

public class Day4 extends Challenge<List<Card>, Integer> {

    @Override
    protected List<Card> parseInput(String filePath) {
        return InputParser.parseToList(filePath, Card::new);
    }

    @Override
    public Integer getFirst() {
        return getInput().stream()
                         .mapToInt(this::computePoints)
                         .sum();
    }

    @Override
    public Integer getSecond() {
        int cardsCount = getInput().size();
        Integer[] matches = getInput().stream()
                                          .map(this::countMatches)
                                          .toArray(Integer[]::new);

        int[] counts = new int[cardsCount];
        Arrays.fill(counts, 1);

        for (int i = 0; i < cardsCount; i++) {
            for (int j = 1; j <= matches[i]; j++) {
                counts[i+j] = counts[i+j] + counts[i];
            }
        }
        return Arrays.stream(counts).reduce(0, Integer::sum);
    }

    private int countMatches(Card card) {
        return (int) card.getNumbers()
                         .stream()
                         .filter(num -> card.getWinningNumbers().contains(num))
                         .count();
    }

    private int computePoints(Card card) {
        return (int) Math.pow(2, countMatches(card) - 1);
    }
}
