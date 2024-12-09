package year_2024.day6;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
enum Direction {
    UP(-1, 0, 1),
    DOWN(1, 0, 2),
    LEFT(0, -1, 4),
    RIGHT(0, 1, 8);

    private final int dx;
    private final int dy;
    private final int marker;

    static Direction getDirectionByMarker(int marker) {
       for (Direction direction : Direction.values()) {
           if (direction.marker == marker) {
               return direction;
           }
       }
       return null;
    }
}
