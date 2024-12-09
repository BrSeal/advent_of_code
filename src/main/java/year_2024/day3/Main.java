package year_2024.day3;

import common.InputParser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/3.txt";

    private final Pattern pattern1 = Pattern.compile("mul\\((\\d{1,3},\\d{1,3})\\)");
    private final Pattern pattern2 = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3},\\d{1,3})\\)");

    List<String> list = InputParser.parseToList(RESOURCE_PATH);

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solve());
        System.out.println(m.solve2());
    }

    private long solve() {
        long sum = 0;
        for (String str : list) {
            Matcher matcher = pattern1.matcher(str);
            while (matcher.find()) {
                String[] split = matcher.group(1).split(",");
                sum += Long.parseLong(split[0]) * Long.parseLong(split[1]);
            }
        }
        return sum;
    }

    private long solve2() {
        long sum = 0;
        boolean enableMul = true;
        for (String str : list) {
            Matcher matcher = pattern2.matcher(str);
            while (matcher.find()) {
                String foundStr = matcher.group();

                if (foundStr.equals("do()")) {
                    enableMul = true;
                } else if (foundStr.equals("don't()")) {
                    enableMul = false;
                } else if (enableMul) {
                    String[] split = matcher.group(1).split(",");
                    sum += Long.parseLong(split[0]) * Long.parseLong(split[1]);
                }
            }
        }
        return sum;
    }

}
