package common;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {
    public static String parseToString(String filePath){
        try {
            return Files.readString(Paths.get(filePath));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static List<String> parseToList(String filePath){
        return parseToList(filePath, String::toString);
    }

    public static <T> List<T> parseToList(String filePath, Function<String, T> converterFn){
        try (
                Stream<String> lines = Files.lines(Paths.get(filePath))
        ) {
            return lines.map(converterFn)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
