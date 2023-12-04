package year2015;

import common.Challenge;
import common.InputParser;
import java.util.List;

public class Day8 extends Challenge<List<String>, Integer> {
    @Override
    protected List<String> parseInput(String filePath) {
        return InputParser.parseToList(filePath);
    }

    @Override
    protected void computeAnswers() {
        setFirst(countFirst());
        setSecond(countSecond());
    }

    private int countFirst() {
        int codeLength = 0;
        int memoryLength = 0;
        for (String s : getInput()) {
            codeLength += s.length();
            memoryLength += decode(s).length() - 2;
        }
        return codeLength - memoryLength;
    }

    private String decode(String s) {
        String result = s.replaceAll("\\\\\\\\", "\\\\");
        result = result.replaceAll("\\\\\\\"", "\"");
        result = result.replaceAll("\\\\\\\"", "\"");
        result = result .replaceAll("\\\\x[0-9a-f]{2}", "1");

        return result;


    }

    private int countSecond() {
        int codeLength = 0;
        int encodedLength = 0;
        for (String s : getInput()) {
            String encoded = encode(s);

            codeLength += s.length();
            encodedLength += encoded.length() + 2;
        }
        return encodedLength - codeLength;
    }

    private String encode(String s) {
        return s.replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("\\\"", "\\\\\"");
    }
}
