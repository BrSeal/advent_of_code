package year_2024.day2;

import common.InputParser;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Main {
    public static final String RESOURCE_PATH = "src/main/resources/2024/2.txt";

    List<int[]> list = InputParser.parseToList(RESOURCE_PATH, str -> Arrays.stream(str.split("\\s+"))
            .mapToInt(Integer::parseInt)
            .toArray());

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve(m::isValid1));
        System.out.println(m.solve(m::isValid2));
    }

    private long solve(Predicate<int[]> validationPredicate) {
        return list.stream()
                .filter(validationPredicate)
                .count();
    }

    private boolean isValid1(int[] arr) {
        BiPredicate<Integer, Integer> predicate = definePredicate(arr);
        for (int i = 0; i < arr.length -1; i++) {
            if (!predicate.test(arr[i], arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid2(int[] arr) {
        BiPredicate<Integer, Integer> predicate = definePredicate(arr);
        for (int i = 0; i < arr.length -1; i++) {
            if (!predicate.test(arr[i], arr[i + 1])) {
                return isValid1(copyWithout(arr, i)) || isValid1(copyWithout(arr, i+1));
            }
        }
        return true;
    }

    BiPredicate<Integer, Integer> definePredicate(int[] arr){
        int direction = 0;
        for (int i = 0; i < arr.length-1; i++) {
            direction += arr[i] > arr[i+1] ? -1 : 1;
        }
        if(direction < 0){
            return (first, second) -> first - second >= 1 && first - second <= 3;
        } else {
            return (first, second) -> second - first >= 1 && second - first <= 3;
        }
    }

    private int[] copyWithout(int[] arr, int indexToExclude) {
        int[] copied = new int[arr.length - 1];

        for (int i = 0; i < indexToExclude; i++) {
            copied[i] = arr[i];
        }

        for (int i = indexToExclude+1; i < arr.length; i++) {
            copied[i-1] = arr[i];
        }

        return copied;
    }

}
