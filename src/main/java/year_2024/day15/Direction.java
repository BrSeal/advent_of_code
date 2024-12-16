package year_2024.day15;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
enum Direction {
    UP(-1, 0, '^'),
    DOWN(1, 0, 'v'),
    LEFT(0, -1, '<'),
    RIGHT(0, 1, '>');

    final int dX;
    final int dY;
    final char symbol;

    static Direction getDirectionByCharacter(char symbol) {
        for (Direction direction : Direction.values()) {
            if (direction.symbol == symbol) {
                return direction;
            }
        }
        return null;
    }
}
