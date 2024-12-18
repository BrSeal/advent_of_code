package year_2024.day17;

import common.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String RESOURCE_PATH = "src/main/resources/2024/17.txt";

    private long registerA;
    private long registerB;
    private long registerC;

    private int[] programm;
    private int cursor = 0;

    private Main() {
        var in = InputParser.parseToList(RESOURCE_PATH);

        registerA = Integer.parseInt(in.get(0).split("\\D+")[1]);
        registerB = Integer.parseInt(in.get(1).split("\\D+")[1]);
        registerC = Integer.parseInt(in.get(2).split("\\D+")[1]);

        programm = Arrays.stream(in.get(4).split("\\D+"))
                .filter(str -> !str.isBlank())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.solve());
        System.out.println(main.solve2());

    }

    private List<Integer> solve() {
        List<Integer> output = new ArrayList<>();

        while (cursor < programm.length) {
            int result = invokeCommand(programm[cursor], programm[cursor + 1]);
            if(result >= 0){
                output.add(result);
            }
        }
        return output;
    }

    private long solve2() {
        Main main = new Main();

        /*
            Чем левее цифра в ответе, тем она меньше меняется от изменения registerA (эмпирическое наблюдение)

            Как я искал:
            1. Находим минимальное значение registerA которое дает нам нужное количество цифр в ответе.
               Я начинал с 0 и добавлял по 100_000_000_000, потом брал значение и шел от него назад половинкой дельты
               и т.д.


            2. Смотрим как меняются цифры ответа в зависимости от registerA
               Видим, что при изменении в пару миллионов цифры правее середины не меняются вообще
            3. Далее бинарным поиском находим начало диапазона для каждой цифры

         */
        long lowerBound= 164541160571400L;
        long value = lowerBound;

        while (true) {
            main.registerA = value;
            main.cursor = 0;
            var l = main.solve();
            if(
                    l.get(0) == programm[0] &&
                    l.get(1) == programm[1] &&
                    l.get(2) == programm[2] &&
                    l.get(3) == programm[3] &&
                    l.get(4) == programm[4] &&
                    l.get(5) == programm[5] &&
                    l.get(6) == programm[6] &&
                    l.get(7) == programm[7] &&
                    l.get(8) == programm[8] &&
                    l.get(9) == programm[9] &&
                    l.get(10) == programm[10] &&
                    l.get(11) == programm[11] &&
                    l.get(12) == programm[12] &&
                    l.get(13) == programm[13] &&
                    l.get(14) == programm[14] &&
                    l.get(15) == programm[15]
            ) {
                return value;
            }
            value += 1;
        }
    }

    private int invokeCommand(int opCode, int n) {
        switch (opCode) {
            case 0:
                registerA = registerA / (int) Math.pow(2, combo(n));
                cursor += 2;
                break;
            case 1:
                registerB = registerB ^ n;
                cursor += 2;
                break;
            case 2:
                registerB = combo(n) % 8;
                cursor += 2;
                break;
            case 3:
                int oldPos = cursor;
                if (registerA != 0) {
                    cursor = n;
                }

                if (cursor == oldPos) {
                    cursor += 2;
                }
                break;
            case 4:
                registerB = registerB ^ registerC;
                cursor += 2;
                break;
            case 5:
                cursor += 2;
                return (int)(combo(n) % 8);
            case 6:
                registerB = registerA / (int) Math.pow(2, combo(n));
                cursor += 2;
                break;
            case 7:
                registerC = registerA / (int) Math.pow(2, combo(n));
                cursor += 2;
                break;
            default:
                throw new IllegalArgumentException("Invalid command");
        }
        return -1;
    }

    private long combo(int value) {
        return switch (value) {
            case 1, 2, 3 -> value;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> throw new IllegalArgumentException("Invalid value: " + value);
        };
    }
}
