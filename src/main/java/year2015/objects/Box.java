package year2015.objects;

public record Box(int height, int width, int length) {

    public Box(String[] dimensions) {
        this(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[2]));
    }

    public int maxDimension() {
        return Math.max(Math.max(height, width), length);
    }

    public int area() {
        return 2 * (height * width + height * length + width * length);
    }

    public int volume() {
        return height * width * length;
    }

}
