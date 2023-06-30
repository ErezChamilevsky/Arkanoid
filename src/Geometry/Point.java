

package Geometry;

/**
 * Point class, setting it, getting values and checking distance.
 *
 * @author Erez Chamilevksy
 * @version 1.1
 * @since 2023
 */

public class Point {
    static final double EPSILON = 0.000001d;
    // the fields
    private double x;
    private double y;

    // constructor
    /**
     * Constracor of Point.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance - return the distance of this point to the other point.
     *
     * @param other point to check the distance between them
     * @return distance
     */
    public double distance(Point other) {
        return Math.sqrt((other.y - this.y) * (other.y - this.y)
                + (other.x - this.x) * (other.x - this.x));
    }

    /**
     * Checks if points equal.
     *
     * @param other
     * @return equals -- return true is the points are equal, false otherwise
     */

    public boolean equals(Point other) {
        if (Math.abs(distance(other) - (double) 0) < EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * Access to X of the point.
     *
     * @return x of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Access to Y of the point.
     *
     * @return y of the point
     */
    public double getY() {
        return this.y;
    }

}