package year_2024.day13;

import common.InputParser;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/13.txt";


    private static final BigInteger BUTTON_1_PRICE = BigInteger.valueOf(3L);
    private static final BigInteger BUTTON_2_PRICE = BigInteger.ONE;

    private static final BigInteger MIN_SOLUTION_VALUE = BigInteger.ZERO;
    private static final BigInteger MAX_SOLUTION_VALUE = BigInteger.valueOf(100L);
    private static final BigInteger DIFF = BigInteger.valueOf(10000000000000L);


    private final List<EquasionSystem> input;

    private Main() {
        var strings = InputParser.parseToList(RESOURCE_PATH);
        input = new ArrayList<>();
        for (int i = 0; i < strings.size() - 2; i += 4) {
            EquasionSystem equasionSystem = new EquasionSystem();
            String[] parsedA = strings.get(i).split("\\D+");
            String[] parsedB = strings.get(i + 1).split("\\D+");
            String[] parsedResult = strings.get(i + 2).split("\\D+");

            equasionSystem.dX1 = new BigInteger( parsedA[1]);
            equasionSystem.dY1 = new BigInteger( parsedA[2]);
            equasionSystem.dX2 = new BigInteger( parsedB[1]);
            equasionSystem.dY2 = new BigInteger( parsedB[2]);
            equasionSystem.cX =  new BigInteger( parsedResult[1]);
            equasionSystem.cY =  new BigInteger( parsedResult[2]);
            input.add(equasionSystem);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve(true));

        alterInput(m.input);
        System.out.println(m.solve(false));
    }

    private BigInteger solve(boolean checkSolution) {
        BigInteger total = BigInteger.ZERO;
        for (EquasionSystem equasionSystem : input) {
            BigInteger[] solution = equasionSystem.findXAndY();
            if (!checkSolution || isValidSolution(solution)) {
                total = total.add(solution[0].multiply(BUTTON_1_PRICE).add(solution[1].multiply(BUTTON_2_PRICE)));
            }
        }
        return total;
    }

    private static void alterInput(List<EquasionSystem> equationSystems) {
        for (EquasionSystem equasionSystem : equationSystems) {
            equasionSystem.cX = equasionSystem.cX.add(DIFF);
            equasionSystem.cY = equasionSystem.cY.add(DIFF);
        }
    }

    private boolean isValidSolution(BigInteger[] solution) {
        return solution[0].compareTo(MIN_SOLUTION_VALUE) >= 0 &&
                solution[0].compareTo(MAX_SOLUTION_VALUE) <= 0 &&
                solution[1].compareTo(MIN_SOLUTION_VALUE) >= 0 &&
                solution[1].compareTo(MAX_SOLUTION_VALUE) <= 0;
    }



}
