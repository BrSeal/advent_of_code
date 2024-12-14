package year_2024.day10;

import common.Point;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
class StartOfTrail {
    Point coordinates;

    @EqualsAndHashCode.Exclude
    int rating;

    protected StartOfTrail(int x, int y, int rating) {
        this.coordinates = new Point(x, y);
        this.rating = rating;
    }
}
