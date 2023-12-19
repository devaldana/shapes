package com.example.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class RectangleTest {

    @Test
    void shouldCreateTheRectangleInTheGivenCoordinates() {
        var rectangle = new Rectangle(2, 3, 3, 2);

        // Validates upper left corner
        assertEquals(rectangle.upLeft(), new Point(2, 3));

        // Validates upper right corner
        assertEquals(rectangle.upRight(), new Point(5, 3));

        // Validates lower right corner
        assertEquals(rectangle.downRight(), new Point(5, 1));

        // Validates lower left corner
        assertEquals(rectangle.downLeft(), new Point(2, 1));
    }

    @Test
    void shouldNotAllowZeroWidthOrHeight() {
        var exc1 = assertThrows(Exception.class, () -> new Rectangle(0, 0, 0, 0));
        var exc2 = assertThrows(Exception.class, () -> new Rectangle(0, 0, 1, 0));

        assertEquals("width must be greater than 0", exc1.getMessage());
        assertEquals("height must be greater than 0", exc2.getMessage());
    }

    @Test
    void shouldReturnTheCorrectArea() {
        var rectangle = new Rectangle(2, 3, 3, 2);
        assertEquals(6, rectangle.area());
    }

    @Test
    void shouldReturnTheVertices() {
        var rectangle = new Rectangle(0, 1, 1, 1);
        assertThat(rectangle.vertices()).containsExactlyInAnyOrder(
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 0),
                new Point(0, 0)
        );
    }
}