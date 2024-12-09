package year_2024.day9;

import common.InputParser;

import java.util.Arrays;

public class Main {
    private static final String RESOURCE_PATH = "src/main/resources/2024/9.txt";

    private static final int[] input = InputParser.parse(RESOURCE_PATH).chars()
            .map(el -> el - '0')
            .toArray();

    public static void main(String[] args) {
        System.out.println(solve(Arrays.copyOf(input, input.length)));
        System.out.println(solve2(Arrays.copyOf(input, input.length)));
    }

    private static long solve(int[] arr) {
        long[] prefixes = calcPrefixSum();
        long result = 0;
        int right = arr.length - 1 - (arr.length % 2 == 0 ? 1 : 0);

        while (right >= 0) {
            int fileId = right / 2;
            int indexToMove = findLeftPos(arr, right);

            if (indexToMove == -1) {
                result += calculateCheckSum(fileId, prefixes[right], arr[right]);
                arr[right] = 0;
            } else if (arr[indexToMove] < arr[right]) {
                result += calculateCheckSum(fileId, prefixes[indexToMove], arr[indexToMove]);
                arr[right] = arr[right] - arr[indexToMove];
                arr[indexToMove] = 0;
            } else if (arr[indexToMove] >= arr[right]) {
                result += calculateCheckSum(fileId, prefixes[indexToMove], arr[right]);
                arr[indexToMove] -= arr[right];
                prefixes[indexToMove] += arr[right];
                arr[right] = 0;
            }
            right -= arr[right] == 0 ? 2 : 0;
        }
        return result;
    }

    private static long solve2(int[] arr) {
        long[] prefixes = calcPrefixSum();
        long result = 0;
        int right = arr.length - 1 - (arr.length % 2 == 0 ? 1 : 0);

        while (right >= 0) {
            int fileId = right / 2;
            int indexToMove = findFirstSuitablePos(arr, right, arr[right]);

            if (indexToMove == -1) {
                result += calculateCheckSum(fileId, prefixes[right], arr[right]);
            } else {
                result += calculateCheckSum(fileId, prefixes[indexToMove], arr[right]);
                arr[indexToMove] -= arr[right];
                prefixes[indexToMove] += arr[right];
            }
            right -= 2;
        }

        return result;
    }

    private static long calculateCheckSum(int fileId, long fileStartOnDiskIndex, int fileLength) {
        long checksum = 0;
        for (int i = 0; i < fileLength; i++) {
            checksum += fileId * (fileStartOnDiskIndex + i);
        }
        return checksum;
    }

    private static long[] calcPrefixSum() {
        long[] arr = new long[input.length];
        long prefix = 0;
        for (int i = 1; i < input.length - 1; i++) {
            prefix += input[i - 1];
            arr[i] = prefix;
        }
        return arr;
    }

    private static int findLeftPos(int[] arr, int toIndex) {
        for (int i = 1; i < toIndex; i += 2) {
            if (arr[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private static int findFirstSuitablePos(int[] arr, int toIndex, int length) {
        for (int i = 1; i < toIndex; i += 2) {
            if (arr[i] >= length) {
                return i;
            }
        }
        return -1;
    }
}
