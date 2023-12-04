package year2015;

import common.Challenge;
import common.InputParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day7 extends Challenge<Map<String, String>, Short> {

    @Override
    protected Map<String, String> parseInput(String filePath) {
        Map<String,String> map = new HashMap<>();
        for(String s : InputParser.parseToList(filePath)){
            String[] data = s.split(" -> ");
            String key = data[1];
            String value = data[0];
            map.put(key,value);
        }
        return map;
    }

    @Override
    protected void computeAnswers() {
        setFirst(computeValue("a"));
    }

    private short computeValue(String key){
        String[] data = getInput().get(key).split(" ");
        short result;

        if(data.length == 1) {
            result = isParsable(data[0])? parse(data[0]) : computeValue(data[0]);
        }

        else if("NOT".equals(data[0])){
            short first = isParsable(data[1])? parse(data[1]) : computeValue(data[1]);
            result = (short) ~first;
        }

        else if("LSHIFT".equals(data[1])){
            short first = isParsable(data[0])? parse(data[0]) : computeValue(data[0]);
            result = (short) (first << parse(data[2]));
        }

        else if("RSHIFT".equals(data[1])){
            short first = isParsable(data[0])? parse(data[0]) : computeValue(data[0]);
            result = (short) (first >>> parse(data[2]));
        }

        else if("AND".equals(data[1])){
            short first = isParsable(data[0])? parse(data[0]) : computeValue(data[0]);
            short second = isParsable(data[2])? parse(data[2]) : computeValue(data[2]);
            result = (short) (first & second);
        }

        else if("OR".equals(data[1])){
            short first = isParsable(data[0])? parse(data[0]) : computeValue(data[0]);
            short second = isParsable(data[2])? parse(data[2]) : computeValue(data[2]);
            result = (short) (first ^ second);
        }
        else{
            throw new IllegalArgumentException(String.format("Action not found for key:%s value:%s", key, Arrays.toString(data)));
        }

        getInput().put(key, result+"");
        return result;
    }

    private boolean isParsable(String s){
        try {
            parse(s);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    private short parse(String s){
        return Short.parseShort(s);
    }
}
