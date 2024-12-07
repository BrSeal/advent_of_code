package year_2024.day5;

import common.InputParser;

import java.util.*;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/5.txt";

    Map<Integer, Set<Integer>> rules = new HashMap<>();
    List<List<Integer>> changeLists = new ArrayList<>();

    private Main() {
        InputParser.parseToList(RESOURCE_PATH).forEach(str -> {
            String[] tokens = str.split("\\D");
            if (tokens.length == 2) {
                int sheetNum = Integer.parseInt(tokens[0]);
                int rule = Integer.parseInt(tokens[1]);

                Set<Integer> currentRules = rules.getOrDefault(sheetNum, new HashSet<>());
                currentRules.add(rule);
                rules.put(sheetNum, currentRules);
            } else if (tokens.length > 2) {
                List<Integer> immutableList = Arrays.stream(tokens)
                        .map(Integer::parseInt)
                        .toList();
                changeLists.add(new ArrayList<>(immutableList));
            }
        });

    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve());
        System.out.println(m.solve2());
    }

    private long solve() {
        int result = 0;
        for (List<Integer> changeList : changeLists) {
            if (isValid(changeList)) {
                result = result + changeList.get(changeList.size() / 2);
            }
        }
        return result;
    }

    private long solve2() {
        int result = 0;
        for (List<Integer> changeList : changeLists) {
            result = result + (sort(changeList) ? changeList.get(changeList.size() / 2) : 0);
        }
        return result;
    }


    private boolean isValid(List<Integer> changeList) {
        for (int i = 0; i < changeList.size(); i++) {
            Set<Integer> currentRules = rules.get(changeList.get(i));
            if (currentRules == null) {
                return true;
            }
            for (int j = 0; j < i; j++) {
                if (currentRules.contains(changeList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean sort(List<Integer> changeList) {
        boolean wasInvalid = false;
        for (int i = 0; i < changeList.size(); i++) {
            Set<Integer> currentRules = rules.get(changeList.get(i));
            if (currentRules == null) {
                return true;
            }

            for (int j = 0; j < i; j++) {
                if(currentRules.contains(changeList.get(j))){
                    Collections.swap(changeList, i, j);
                    wasInvalid = true;
                    i = -1;
                }
            }
        }
        return wasInvalid;
    }


}
