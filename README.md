# Shapes Library

The Shapes library provides classes and methods to work with geometric shapes, specifically rectangles and points. The library includes functionality to check containment, intersection, adjacency, and find intersection points between rectangles.

## Classes

### `Point`

Represents a point with `x` and `y` coordinates.

### `Rectangle`

Represents a rectangle with an upper-left corner at (`x`, `y`), a width and a height. It includes methods to retrieve vertices and calculate the area.

### `Shapes`

A utility class with static methods for various geometric operations on rectangles, including checking containment, intersection, adjacency, and finding intersection points.

## Usage

### Containment Check

To check if one rectangle is contained within another:

```java
Rectangle rect1 = new Rectangle(0, 0, 5, 5);
Rectangle rect2 = new Rectangle(1, 1, 3, 3);

boolean isContained = Shapes.isContainment(rect1, rect2);
System.out.println("Is Contained: " + isContained);
```
### Intersection Check

To check if two rectangles intersect:

```java
Rectangle rect1 = new Rectangle(0, 0, 5, 5);
Rectangle rect2 = new Rectangle(3, 3, 5, 5);

boolean isIntersecting = Shapes.isIntersection(rect1, rect2);
System.out.println("Is Intersecting: " + isIntersecting);
```

### Adjacency Check

To check if two rectangles are adjacent:

```java
Rectangle rect1 = new Rectangle(0, 0, 5, 5);
Rectangle rect2 = new Rectangle(5, 0, 5, 5);

boolean isAdjacent = Shapes.isAdjacency(rect1, rect2);
System.out.println("Is Adjacent: " + isAdjacent);
```

### Intersection Points

To find intersection points between two rectangles:

```java
Rectangle rect1 = new Rectangle(0, 0, 5, 5);
Rectangle rect2 = new Rectangle(3, 3, 5, 5);

Set<Point> intersectionPoints = Shapes.intersectionPoints(rect1, rect2);
System.out.println("Intersection Points: " + intersectionPoints);
```

## Build and test

Java 17 and Maven are required to be installed.

### Run tests
`mvn test`

