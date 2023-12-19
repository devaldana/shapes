package com.example.shapes;

import org.junit.jupiter.api.Test;
import static com.example.shapes.Shapes.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ShapesTest {

    @Test
    void isContainmentTest() {
        var rect1 = new Rectangle(2, 3, 3, 2);
        var rect2 = new Rectangle(2, 3, 1, 1);

        assertFalse(isContainment(rect1, null));
        assertFalse(isContainment(null, rect2));
        assertTrue(isContainment(rect1, rect1), "A rectangle should be contained inside itself");
        assertTrue(isContainment(rect1, rect2), "Should return true - is a containment");
        assertTrue(isContainment(rect2, rect1), "Should return true - is a containment");

        rect1 = new Rectangle(0, 1, 1, 1);
        rect2 = new Rectangle(1, 1, 1, 1);
        assertFalse(isContainment(rect1, rect2), "Should return false - is NOT a containment");
    }

    @Test
    void isIntersectionTest() {
        var rect1 = new Rectangle(2, 3, 3, 2);
        var rect2 = new Rectangle(4, 4, 3, 2);
        assertTrue(isIntersection(rect1, rect2), "Rectangles should intersects");

        rect1 = new Rectangle(0, 1, 1, 1);
        rect2 = new Rectangle(1, 1, 1, 1);
        assertFalse(isIntersection(rect1, rect2), "Should return false - is NOT an intersection");
    }

    @Test
    void isAdjacencyTest() {
        var rect1 = new Rectangle(1, 4, 3, 3);
        var rect2 = new Rectangle(5, 3, 1, 1);

        assertFalse(isAdjacency(rect1, rect2), "Should NOT be adjacent");
        assertFalse(isAdjacency(rect2, rect1), "Should NOT be adjacent");

        rect1 = new Rectangle(2, 3, 3, 2);
        rect2 = new Rectangle(4, 4, 3, 2);
        assertFalse(isAdjacency(rect1, rect2), "Rectangles intersect - they are not adjacent");
        assertFalse(isAdjacency(rect2, rect1), "Rectangles intersect - they are not adjacent");

        // Overlap top
        rect1 = new Rectangle(1, 4, 3, 3);
        rect2 = new Rectangle(3, 5, 3, 1);

        assertTrue(isAdjacency(rect1, rect2));
        assertTrue(isAdjacency(rect2, rect1));

        // Overlap right
        rect1 = new Rectangle(1, 4, 3, 3);
        rect2 = new Rectangle(4, 5, 2, 3);

        assertTrue(isAdjacency(rect1, rect2));
        assertTrue(isAdjacency(rect2, rect1));

        // Overlap bottom
        rect1 = new Rectangle(1, 4, 3, 3);
        rect2 = new Rectangle(3, 1, 3, 1);

        assertTrue(isAdjacency(rect1, rect2));
        assertTrue(isAdjacency(rect2, rect1));

        // Overlap left
        rect1 = new Rectangle(1, 4, 3, 3);
        rect2 = new Rectangle(0, 2, 1, 2);

        assertTrue(isAdjacency(rect1, rect2));
        assertTrue(isAdjacency(rect2, rect1));

        // Overlap Corner at one single point (1, 1)
        rect1 = new Rectangle(1, 4, 3, 3);
        rect2 = new Rectangle(0, 1, 1, 1);

        assertTrue(isAdjacency(rect1, rect2));
        assertTrue(isAdjacency(rect2, rect1));
    }

    @Test
    void fourIntersectingPointsTest() {
        var rect1 = new Rectangle(1, 4, 3, 3);
        var rect2 = new Rectangle(0, 3, 5, 1);
        var expectedPoints = new Point[]{
                new Point(1, 3),
                new Point(4, 3),
                new Point(1, 2),
                new Point(4, 2)
        };

        var intersectionPoints1 = intersectionPoints(rect1, rect2);
        assertThat(intersectionPoints1).containsExactlyInAnyOrder(expectedPoints);

        var intersectionPoints2 = intersectionPoints(rect2, rect1);
        assertThat(intersectionPoints2).containsExactlyInAnyOrder(expectedPoints);
    }

    @Test
    void twoIntersectingPointsTest() {
        var rect1 = new Rectangle(1, 4, 3, 3);
        var rect2 = new Rectangle(3, 3, 2, 1);

        var intersectionPoints = intersectionPoints(rect1, rect2);
        assertThat(intersectionPoints).containsExactlyInAnyOrder(
                new Point(4, 3),
                new Point(4, 2)
        );
    }

    @Test
    void noIntersectingPointsTest() {
        var rect1 = new Rectangle(1, 4, 3, 3);
        var rect2 = new Rectangle(0, 1, 1, 1);

        var intersectionPoints = intersectionPoints(rect1, rect2);
        assertThat(intersectionPoints).isEmpty();;
    }
}