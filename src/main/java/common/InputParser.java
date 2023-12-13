package common;

import lombok.experimental.UtilityClass;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class InputParser {
    public static String parseToString(String filePath){
        try {
            return Files.readString(Paths.get(filePath));
        } catch (Exception ex) {
            printException(ex);
            return "";
        }
    }

    public static List<String> parseToList(String filePath){
        return parseToList(filePath, String::toString);
    }

    public static <T> List<T> parseToList(String filePath, Function<String, T> lineToObjectFn){
        try (
                Stream<String> lines = Files.lines(Paths.get(filePath))
        ) {
            return lines.map(lineToObjectFn).toList();
        } catch (Exception ex) {
            printException(ex);
            return Collections.emptyList();
        }
    }

    private static void printException(Exception ex){
        System.out.println("===============!! EXCEPTION !!===============");
        System.out.println(ex.getClass());
        System.out.println(ex.getMessage());
        System.out.println("=============================================");
    }
}
