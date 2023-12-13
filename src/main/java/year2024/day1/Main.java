package year2024.day1;

import common.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static final String RESOURCE_PATH = "src/main/resources/2024/1.txt";

    List<Long> left = new ArrayList<>();
    List<Long> right = new ArrayList<>();
    Map<Long, Long> rightListCounts;

    Main() {
        InputParser.parseToList(RESOURCE_PATH)
                .forEach(el -> {
                    String[] split = el.split("\\s+");

                    left.add(Long.parseLong(split[0].trim()));
                    right.add(Long.parseLong(split[1].trim()));
                });

        rightListCounts = right.stream().collect(groupingBy(i -> i, counting()));
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve1());
        System.out.println(m.solve2());
    }

    private long solve1() {

        long diff = 0;

        for (int i = 0; i < left.size(); i++) {
            diff += Math.abs(left.get(i) - right.get(i));
        }

        return diff;
    }

    private long solve2() {
       return left.stream()
               .mapToLong(el-> el * rightListCounts.getOrDefault(el, 0L))
               .sum();
    }
}
