package year2015;

import common.Challenge;
import common.InputParser;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Day4 extends Challenge<String, Integer> {

    private static final int MIN_STARTING_ZEROES_1 = 5;
    private static final int MIN_STARTING_ZEROES_2 = 6;

    @Override
    protected String parseInput(String filePath) {
        return InputParser.parseToString(filePath);
    }

    @Override
    protected void computeAnswers() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            setFirst(getSuffix(md, getInput(), MIN_STARTING_ZEROES_1, 0));
            setSecond(getSuffix(md, getInput(), MIN_STARTING_ZEROES_2, getFirst()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private int getSuffix(MessageDigest md, String rootPattern, int headingZeroes, int startWith) {
        int suffix = startWith;
        int hashLength = 100;
        while (hashLength > 32 - headingZeroes) {
            suffix++;
            md.update((rootPattern + suffix).getBytes());
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashLength = bigInt.toString(16).length();
        }
        return suffix;
    }
}
