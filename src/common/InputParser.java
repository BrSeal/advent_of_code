package common;

import java.nio.file.Files;
import java.nio.file.Paths;

public class InputParser {
    public static String parseToString(String filePath){
        try {
            return Files.readString(Paths.get(filePath));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
