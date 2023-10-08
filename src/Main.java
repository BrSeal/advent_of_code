import common.Challenge;
import year2015.Day1;
import year2015.Day2;

import java.util.List;

public class Main {
    private final static List<Challenge<?, ?>> CHALLENGES_2015 = List.of(
            new Day1("resources/2015/day1.txt"),
            new Day2("resources/2015/day2.txt")
    );

    public static void main(String[] args) {
        printAnswers(CHALLENGES_2015);
    }

    public static void printAnswers(List<Challenge<?,?>> challenges){
        int i = 1;
        for (Challenge<?, ?> challenge : challenges) {
            System.out.println("Day " + i++ + ":");
            System.out.println("\tFirst answer:  " + challenge.getFirst());
            System.out.println("\tSecond answer: " + challenge.getSecond());
        }
    }
}