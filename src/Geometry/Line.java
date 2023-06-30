package Geometry;

import java.util.Collections;

/**
 * This class represents a line in the two-dimensional (x,y) plane.
 * The class contains methods to calculate various properties of the line,
 * such as its length, slope, and middle point.
 * It also contains methods to determine if a point is on the line or if
 * two lines intersect, as well as to calculate the intersection point of two
 * lines.
 * The class uses the Point class to represent the start and end points of the
 * line.
 *
 * @author Erez Chamilevksy
 *
 * @version 1.1
 *
 * @since 2023
 */

public class Line {
    static final int ERROR = -1;
    static final int FRIST = 0;

    private Point start;
    private Point end;
    // the slope of the line
    private double m;
    private Point originalStart;
    // constructors

    /**
     * Constructs a Line object with the given start and end points.
     * The constructor also calculates the slope of the line.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */

    public Line(Point start, Point end) {
        // for the order I want bigger x as end
        this.originalStart = start;
        if (start.getX() <= end.getX()) {

            this.start = start;
            this.end = end;

        } else {

            this.start = end;
            this.end = start;

        }
        if (start.getX() == end.getX()) {
            this.m = 0;
        } else {
            this.m = (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
    }

    /**
     * Constructs a Line object with the given x and y coordinates of the start,
     * end points.
     * The constructor also calculates the slope of the line.
     *
     * @param x1 the x coordinate of the start point of the line
     * @param y1 the y coordinate of the start point of the line
     * @param x2 the x coordinate of the end point of the line
     * @param y2 the y coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.originalStart = new Point(x1, y1);
        // for the order I want bigger x as end
        if (x1 <= x2) {

            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);

        } else {

            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);

        }
        if (x1 == x2) {
            this.m = 0;
        } else {
            this.m = (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Calculates the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        Point middle = new Point(((start.getX() + end.getX()) / 2),
                ((start.getY() + end.getY()) / 2));
        return middle;
    }

    /**
     * Gives the slope of the line.
     *
     * @return the slope of the line
     */
    public double slope() {
        return this.m;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the original start point of the line .
     *
     * @return the original start point of the line
     */
    public Point originalStart() {
        return this.originalStart;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end point of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns a point on the line that has the same y-cordinate as the
     * argument.
     *
     * @param point the point to intersect the line with
     * @return a point on the line that has the same y-coordinate as the
     *         argument
     */
    public Point yOnParLine(Point point) {
        if (this.slope() == 0) {
            if ((point.getY() <= this.start.getY()
                    && point.getY() >= this.end.getY())
                    ||
                    (point.getY() <= this.start.getY()
                            && point.getY() >= this.end.getY())) {
                if (this.start.getY() != this.end.getY()) {
                    return new Point(this.start.getX(), point.getY());
                }

            }
            // other cases mean that it is not on line
            return null;
        }
        double x = (point.getY() - this.start.getY()
                + this.slope() * this.start.getX())
                / this.slope();
        return new Point(x, point.getY());
    }

    /**
     * Returns a point on the line that has the same x-coordinate as the
     * argument.
     *
     * @param point the point to intersect the line with
     * @return a point on the line that has the same x-coordinate as the
     *         argument
     */
    public Point xOnParLine(Point point) {
        if (this.slope() == 0) {
            if ((point.getX() >= this.start.getX()
                    && point.getX() <= this.end.getX())) {
                if (this.start.getX() != this.end.getX()) {
                    return new Point(point.getX(), this.start.getY());
                }

            }
            // other cases mean that it is not on line
            return null;
        }
        double y = this.slope() * (point.getX() - this.start.getX())
                + this.start.getY();

        return new Point(point.getX(), y);
    }

    /**
     * Returns true if the argument point is on the line.
     *
     * @param point the point to test
     * @return true if the argument point lies on the line, false otherwise
     */
    public boolean isOnLine(Point point) {
        if (point == null) {
            return false;
        }
        double epsilon = 0.000001d;
        // in case start.Y < end.Y
        if (this.start.getY() <= this.end.getY()) {
            if (((this.start.getX() - point.getX()) < epsilon
                    && (point.getX() - this.end.getX()) < epsilon)
                    // cheking if the point the Y range
                    && ((this.start.getY() - point.getY()) < epsilon
                            && (point.getY() - this.end.getY()) < epsilon)) {
                return true;
            }
        } else {
            // case that start.Y > end.Y
            if (((this.start.getX() - point.getX()) < epsilon
                    && (point.getX() - this.end.getX()) < epsilon)
                    // cheking if the point the Y range
                    && ((this.end.getY() - point.getY()) < epsilon
                            && (point.getY() - this.start.getY()) < epsilon)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this line intersects the argument line.
     *
     * @param other the line to test for intersection
     * @return true if this line intersects the argument line, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // if they are the same line
        if (this.equals(other)) {
            return true;
        }
        if (this.intersectionWith(other) != null) {
            return true;
        }
        if (this.slope() == other.slope()) {

            if ((this.isOnLine(other.start) || this.isOnLine(other.end))
                    || (other.isOnLine(this.start)
                            || other.isOnLine(this.end))) {
                return true;
            }

            // end of the same slope
        }
        return false;

    }

    /**
     * Returns the intersection point of this line with the argument line, or
     * null if they do not intersect.
     *
     * @param other the line to find the intersection point with
     * @return the intersection point of this line with the argument line, or
     *         null if they do not intersect
     */
    public Point intersectionWith(Line other) {
        // if they are the same line
        if (equals(other)) {
            return null;
        }

        // if the line is a point
        if (other.length() == 0 && this.isOnLine(other.start)) {
            return other.start;
        }
        if (this.length() == 0 && other.isOnLine(this.start)) {
            return this.start;
        }

        // if the slopes are equal
        if (this.slope() == other.slope()) {

            // if the points are the intersections
            if (this.end.equals(other.end) && !this.isOnLine(other.start)
                    && !other.isOnLine(this.start)) {
                return this.end;
            }
            if (this.start.equals(other.start) && !this.isOnLine(other.end)
                    && !other.isOnLine(this.end)) {
                return this.start;
            }

            if (this.end.equals(other.start) && !this.isOnLine(other.end)
                    && !other.isOnLine(this.start)) {
                return this.end;
            }
            if (this.start.equals(other.end) && !this.isOnLine(other.start)
                    && !other.isOnLine(this.end)) {
                return this.start;
            }

            if (this.slope() == 0) {
                // parallel to x
                if (this.start.getY() == this.end.getY()
                        && other.start.getX() == other.end.getX()) {
                    Point option = new Point(other.start.getX(),
                            this.start.getY());
                    if (this.isOnLine(option) && other.isOnLine(option)) {
                        return option;
                    }
                }

                if (this.start.getX() == this.end.getX()
                        && other.start.getY() == other.end.getY()) {
                    Point option = new Point(other.start.getY(),
                            this.start.getX());
                    if (this.isOnLine(option) && other.isOnLine(option)) {
                        return option;
                    }
                }

                if (this.start.getY() == this.end.getY()) {
                    if (this.isOnLine(other.yOnParLine(this.start))
                            && other.isOnLine(other.yOnParLine(this.start))) {
                        return other.yOnParLine(this.start);
                    }
                }

                // parallel to Y
                if (this.start.getX() == this.end.getX()) {
                    if (this.isOnLine(other.xOnParLine(this.start))
                            && other.isOnLine(other.xOnParLine(this.start))) {
                        return other.xOnParLine(this.start);
                    }
                }
            }

            if ((this.isOnLine(other.start) || this.isOnLine(other.end))
                    || (other.isOnLine(this.start)
                            || other.isOnLine(this.end))) {
                return null;
            }
        }

        if (this.slope() == 0 || other.slope() == 0) {
            if (this.slope() == 0) {
                // if parallel to X
                if (this.start.getY() == this.end.getY()) {
                    double xOfIn = (this.start.getY()
                            - other.start.getY()
                            + other.slope() * other.start.getX())
                            / other.slope();
                    if (this.isOnLine(new Point(xOfIn, this.start.getY()))
                            && other.isOnLine(
                                    new Point(xOfIn, this.start.getY()))) {
                        return new Point(xOfIn, this.start.getY());
                    }
                    return null;
                } else if (this.start.getX() == this.end.getX()) {
                    double yOfIn = other.slope()
                            * (this.start.getX() - other.start.getX())
                            + other.start.getY();
                    if (this.isOnLine(new Point(this.start.getX(), yOfIn))
                            && other.isOnLine(
                                    new Point(this.start.getX(), yOfIn))) {
                        return new Point(this.start.getX(), yOfIn);
                    }
                    return null;
                }
            }
            if (other.slope() == 0) {
                // if parallel to X
                if (other.start.getY() == other.end.getY()) {
                    double xOfIn = (other.start.getY()
                            - this.start.getY()
                            + this.slope() * this.start.getX())
                            / this.slope();

                    if (this.isOnLine(new Point(xOfIn, other.start.getY()))
                            && other.isOnLine(
                                    new Point(xOfIn, other.start.getY()))) {
                        return new Point(xOfIn, other.start.getY());
                    }
                } else if (other.start.getX() == other.end.getX()) {
                    // parallel to y
                    double yOfIn = this.slope() * (other.start.getX()
                            - this.start.getX())
                            + this.start.getY();
                    if (this.isOnLine(new Point(other.start.getX(), yOfIn))
                            && other.isOnLine(new Point(other.start.getX(),
                                    yOfIn))) {
                        return new Point(other.start.getX(), yOfIn);
                    }

                }
                return null;
            }
        }

        // finding the line equation by slopes and points
        double xOfIn = (this.slope() * this.start.getX()
                - other.slope() * other.start.getX()
                + other.start.getY() - this.start.getY())
                / (this.slope() - other.slope());
        // finding the y by putting the x we find in the equation of line
        double yOfIn = this.slope()
                * (xOfIn - this.start.getX()) + this.start.getY();
        Point intersection = new Point(xOfIn, yOfIn);
        // need to check if intersection on the lines
        if (this.isOnLine(intersection) && other.isOnLine(intersection)) {
            return intersection;
        }
        // if intersection not on line
        return null;
    }

    /**
     * Determines whether this Line is equal to another Line.
     *
     * @param other the Line to compare to this one
     * @return true if the two Lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // using the method equals of Point class
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return true;
        } else if (this.start.equals(other.start)
                && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * Bubble sort of intersections point between rectangle and line. Sorted by
     * distance of the start point of line and the intersection point;
     * As lower distance it is, as early index in the list.
     *
     * @param rect the rectangle that we want to check the intersections with.
     * @return point that the closest to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        intersections = bubbleSortByDistance(intersections);
        if (intersections.size() == 0) {
            return null;
        }
        return intersections.get(FRIST);
    }

    /**
     * Getting the b of the line equation y = ax + b.
     *
     * @return b of the equation
     */
    public double getB() {
        return this.start().getY() - this.m * this.start().getX();
    }

    /**
     * Returning point on line found by x value.
     *
     * @param x of point wanted to find
     * @return point with this x value
     */
    public Point pointOnLineByX(double x) {
        if (this.m == 0) {
            return new Point(x, this.getB());
        }
        return new Point(x, this.m * x + this.getB());
    }

    /**
     * Bubble sort of intersections by the distance from this original start of
     * line.
     *
     * @param intersections list of intersections points
     * @return sorted list by distance
     */
    public java.util.List<Point> bubbleSortByDistance(java.util.List<Point> intersections) {
        for (int i = 1; i < intersections.size(); i++) {
            for (int j = 0; j < intersections.size() - 1; j++) {
                if (intersections.get(j) == null || intersections.get(j + 1) == null) {
                    continue;
                } else if (intersections.get(j).distance(this.originalStart) > intersections.get(j + 1)
                        .distance(this.originalStart)) {
                    Collections.swap(intersections, j, j + 1);
                }
            }
        }
        return intersections;
    }

    /**
     * Boolean if x value is on line.
     *
     * @param x
     * @return true or false
     */
    public boolean isXBetweenVals(double x) {
        if (this.start.getX() <= x && this.end.getX() >= x) {
            return true;
        }
        return false;
    }

    /**
     * Boolean if y value is on line.
     *
     * @param y
     * @return true or false
     */
    public boolean isYBetweenVals(double y) {
        if (this.start.getY() <= y && this.end.getY() >= y) {
            return true;
        }
        return false;
    }
}