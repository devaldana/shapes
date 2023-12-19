package com.example.shapes;

import java.util.Set;
import java.util.stream.Collectors;

public final class Shapes {

    private Shapes(){}

    /**
     * Check whether a rectangle is contained inside another.
     * @param rect1 a rectangle.
     * @param rect2 a rectangle.
     * @return <b>true</b> if the rectangles are equal.<br>
     *         <b>true</b> if all the corners of the smaller rectangle are inside the bigger rectangle.<br>
     *         <b>false</b> if none of the previous conditions are met.<br>
     */
    static boolean isContainment(final Rectangle rect1, final Rectangle rect2) {
        if(rect1 == null || rect2 == null) return false;
        if(rect1.equals(rect2)) return true;

        Rectangle bigger, smaller;

        if (rect1.area() > rect2.area()) {
            bigger = rect1;
            smaller = rect2;
        } else {
            bigger = rect2;
            smaller = rect1;
        }

        // Check whether the upper left and the lower right corners of [smaller] are inside [bigger]
        return smaller.upLeft().x() >= bigger.upLeft().x() &&
               smaller.upLeft().y() <= bigger.upLeft().y() &&
               smaller.downRight().x() <= bigger.downRight().x() &&
               smaller.downRight().y() >= bigger.downRight().y();
    }

    /**
     * Check whether a rectangle intersects another one.
     * @param rect1 a rectangle.
     * @param rect2 a rectangle.
     * @return <b>true</b> if the rectangles intersect.<br>
     *         <b>false</b> otherwise.
     */
    static boolean isIntersection(final Rectangle rect1, final Rectangle rect2) {
        // Conditions for non-intersection:
        // A: The top side of rect1 is below the bottom side of rect2.
        // B: The right side of rect1 is to the left of the left side of rect2.
        // C: The bottom side of rect1 is above the top side of rect2.
        // D: The left side of rect1 is to the right of the right side of rect2.
        // If those are the conditions for non-intersection, then an intersection can
        // be expressed as the negation of those conditions => !(A or B or C or D).
        return !(rect1.upLeft().y() <= rect2.downLeft().y() ||   // A
                 rect1.upRight().x() <= rect2.upLeft().x() ||    // B
                 rect1.downRight().y() >= rect2.upRight().y() || // C
                 rect1.downLeft().x() >= rect2.downRight().x()); // D
    }

    /**
     * Check whether two rectangles are adjacent.
     * Two rectangles are considered adjacent if any of the following conditions are <code>true</code>:
     * <li>The top side of rect1 is overlapped with the bottom side of rect2 at the same 'y' position.</li>
     * <li>The right side of rect1 is overlapped with the left side of rect2 at the same 'x' position.</li>
     * <li>The bottom side of rect1 is overlapped with the top side of rect2 at the same 'y' position.</li>
     * <li>The left side of rect1 is overlapped with the right side of rect2 at the same 'x' position.</li>
     *
     * @param rect1 The first rectangle.
     * @param rect2 The second rectangle.
     * @return <b>true</b> if the rectangles are adjacent, <b>false</b> otherwise.
     */
    static boolean isAdjacency(final Rectangle rect1, final Rectangle rect2) {
        final var overlapTop = rect1.upLeft().y() == rect2.downLeft().y() &&
                isOverlap(rect1.upLeft().x(), rect1.upRight().x(), rect2.downLeft().x(), rect2.downRight().x());

        final var overlapRight = rect1.upRight().x() == rect2.downLeft().x() &&
                isOverlap(rect1.downRight().y(), rect1.upRight().y(), rect2.downLeft().y(), rect2.upLeft().y());

        final var overlapBottom = rect1.downRight().y() == rect2.upRight().y() &&
                isOverlap(rect1.downLeft().x(), rect1.downRight().x(), rect2.downLeft().x(), rect2.downRight().x());

        final var overlapLeft = rect1.downLeft().x() == rect2.upRight().x() &&
                isOverlap(rect1.downLeft().y(), rect1.upLeft().y(), rect2.downRight().y(), rect2.upRight().y());

        return overlapTop || overlapRight || overlapBottom || overlapLeft;
    }

    /**
     * Check whether two segments overlap.
     * @param s1 Start of segment 1.
     * @param e1 End of segment 1.
     * @param s2 Start of segment 2.
     * @param e2 End of segment 2.
     * @return <b>true</b> if the segments overlap.<br>
     *         <b>false</b> otherwise.
     */
    static boolean isOverlap(final int s1, final int e1, final int s2, final int e2) {
        // Segments overlap if the start of one segment is less than or equal to
        // the end of the other segment, and vice versa.
        return s2 <= e1 && s1 <= e2;
    }

    /**
     * Searches the intersection points between two given rectangles.
     * @param rect1 a rectangle.
     * @param rect2 a rectangle.
     * @return a set of points in which the lines of the two rectangles intercept or
     *         an empty set otherwise.
     */
    static Set<Point> intersectionPoints(final Rectangle rect1, final Rectangle rect2) {
        // Check whether the two rectangles intersect
        if (isIntersection(rect1, rect2)) {
            final var intersectRectangle = getIntersectRectangle(rect1, rect2);
            final var rect1Vertices = rect1.vertices();
            final var rect2Vertices = rect2.vertices();

            // returns only the vertices of the intersect rectangle that
            // are NOT vertices of rect1 or rect2 - exclude non-valid points.
            return intersectRectangle.vertices()
                                     .stream()
                                     .filter(point -> !rect1Vertices.contains(point) && !rect2Vertices.contains(point))
                                     .collect(Collectors.toSet());
        }

        return Set.of();
    }

    /**
     * Calculate the rectangle that represents the intersection area between two rectangles.
     * @param rect1 The first rectangle.
     * @param rect2 The second rectangle.
     * @return A new rectangle representing the intersection area.
     */
    private static Rectangle getIntersectRectangle(final Rectangle rect1, final Rectangle rect2) {
        // Calculate coordinates of the intersection rectangle
        final var upLeftX = Math.max(rect1.upLeft().x(), rect2.upLeft().x());
        final var upLeftY = Math.min(rect1.upLeft().y(), rect2.upLeft().y());
        final var downRightX = Math.min(rect1.downRight().x(), rect2.downRight().x());
        final var downRightY = Math.max(rect1.downRight().y(), rect2.downRight().y());

        // Create and return the rectangle that represents the intersection area
        return new Rectangle(upLeftX, upLeftY, downRightX - upLeftX, upLeftY - downRightY);
    }
}
