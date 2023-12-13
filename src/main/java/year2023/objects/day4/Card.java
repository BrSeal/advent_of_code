package year2023.objects.day4;

import lombok.Getter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Card {
    private final Set<Integer> winningNumbers;
    private final List<Integer> numbers;

    public Card(String s){
        String[] arr = s.split(":\\s+|\\s+\\|\\s+");

        System.out.println();
        winningNumbers = Arrays.stream(arr[1].split("\\s+"))
                               .map(Integer::parseInt)
                               .collect(Collectors.toSet());

        numbers = Arrays.stream(arr[2].split("\\s+"))
                     .map(Integer::parseInt)
                     .toList();
    }
}
