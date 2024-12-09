package year_2024.day9;

import common.InputParser;
import org.w3c.dom.ls.LSOutput;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/9.txt";

    private static final int[] input = InputParser.parse(RESOURCE_PATH).chars()
            .map(el -> el - '0')
            .toArray();

    public static void main(String[] args) {
        System.out.println(solve());
        System.out.println(solve2());
    }

    private static long solve() {
        long result = 0;

        int right = input.length - 1 - (input.length % 2 == 0 ? 1 : 0);

        long diskLastIndex = 0;

        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                int fileId = i / 2;
                result += calculateCheckSum(fileId, diskLastIndex, input[i]);
                diskLastIndex += input[i];
            }
            else {
                int fileId = right / 2;

                if (input[i] > input[right]) {
                    input[i] = input[i] - input[right];
                    result += calculateCheckSum(fileId, diskLastIndex, input[right]);
                    diskLastIndex += input[right];

                    input[right] = 0;
                    i--; // остаемся на месте
                    input[right-1] = 0; // костыль чтобы не сломался подсчет
                    right -= 2; //сдвигаем только right на 2 позиции, пропуская пустышку справа
                }
                else if (input[i] == input[right]) {
                    result += calculateCheckSum(fileId, diskLastIndex, input[i]);
                    diskLastIndex += input[right];

                    input[right] = 0;
                    input[i] = 0;

                    input[right-1] = 0; // костыль чтобы не сломался подсчет
                    right -= 2; //сдвигаем только right на 2 позиции, пропуская пустышку справа
                }
                else if (input[i] < input[right]) {
                    input[right] -= input[i];
                    result += calculateCheckSum(fileId, diskLastIndex, input[i]);
                    diskLastIndex += input[i];

                    input[i] = 0;
                }
            }
        }
        return result;
    }

    private static long solve2() {
        return 0;
    }

    private static long calculateCheckSum(int fileId, long fileStartOnDiskIndex, int fileLength) {
        long checksum = 0;
        for (int i = 0; i < fileLength; i++) {
            checksum += fileId * (fileStartOnDiskIndex + i);
        }
//        System.out.println("calculateCheckSum fileId="+fileId+",checksum="+checksum);
        return checksum;
    }

    private static void printInput(){
        for (int i = 0; i < input.length; i++) {
            String s = i % 2 == 0 ? i / 2 + "" : ".";
            for (int j = 0; j < input[i]; j++) {
                System.out.print(s);
            }
        }
        System.out.println();
    }
}
