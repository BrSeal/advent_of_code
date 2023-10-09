package year2015.objects;

public class Box {
    private final int height;
    private final int width;
    private final int length;

    public Box(int height, int width, int length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public Box(String[] dimensions) {
        this(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]), Integer.parseInt(dimensions[2]));
    }

    public int getMaxDimension() {
        return Math.max(Math.max(height, width), length);
    }

    public int getArea() {
        return 2 * (height * width + height * length + width * length);
    }

    public int getVolume() {
        return height * width * length;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
