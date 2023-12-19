package com.example.shapes;

import java.util.Set;

/**
 * Represents a rectangle with the upper left corner at (x, y).
 * A rectangle must have a width and height greater than 0.
 * @param x Upper left x coordinate.
 * @param y Upper left y coordinate.
 * @param width of the rectangle.
 * @param height of the rectangle.
 */
record Rectangle (int x, int y, int width, int height){

    public Rectangle {
        if(width <= 0) throw new IllegalArgumentException("width must be greater than 0");
        if(height <= 0) throw new IllegalArgumentException("height must be greater than 0");
    }

    public Point upLeft() {
        return new Point(x, y);
    }

    public Point upRight() {
        return new Point(x + width, y);
    }

    public Point downRight() {
        return new Point(x + width, y - height);
    }

    public Point downLeft() {
        return new Point(x, y - height);
    }

    public Set<Point> vertices() {
        return Set.of(upLeft(), upRight(), downRight(), downLeft());
    }

    public int area() {
        return width * height;
    }
}
