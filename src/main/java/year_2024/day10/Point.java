package year_2024.day10;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
class Point {
    int x;
    int y;

    @EqualsAndHashCode.Exclude
    int rating;
}
