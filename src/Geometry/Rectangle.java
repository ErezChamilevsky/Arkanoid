package Geometry;

import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * A rectangle is a two-dimensional geometric object with four sides and four
 * right angles.
 * It is defined by its upper left point, width, height, and color.
 */

public class Rectangle {
    static final int BORDERS = 4;
    static final int TOPBORDER = 0;
    static final int BOTTOMBORDER = 1;
    static final int RIGHTBORDER = 2;
    static final int LEFTBORDER = 3;

    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Create a new rectangle with a specified upper-left point, width, height, and
     * color.
     *
     * @param upperLeft The upper-left point of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     * @param color     The color of the rectangle.
     */
     public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified
     * line.
     *
     * @param line The line to check for intersection.
     * @return A list of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] borders = this.getBorders();
        java.util.List<Point> intersections = new ArrayList<Point>();
        for (int i = 0; i < BORDERS; i++) {
            Point intersection = line.intersectionWith(borders[i]);
            if (intersection != null) {
                intersections.add(intersection);
            }
        }
        return intersections;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets the upper-left point of the rectangle to the specified point.
     *
     * @param point The point to set the upper-left point of the rectangle to.
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }

    /**
     * Sets the upper-left point of the rectangle to the specified x and y
     * coordinates.
     *
     * @param x The x-coordinate of the point to set the upper-left point of the
     *          rectangle to.
     * @param y The y-coordinate of the point to set the upper-left point of the
     *          rectangle to.
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }

    /**
     * Returns the left border of the rectangle.
     *
     * @return The left border of the rectangle.
     */
    public Line getLeftBorder() {
        return new Line(upperLeft,
                new Point(upperLeft.getX(), upperLeft.getY() + height));
    }

    /**
     * Returns the right border of the rectangle.
     *
     * @return The right border of the rectangle.
     */
    public Line getRightBorder() {
        return new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * Returns the top border of the rectangle.
     *
     * @return The top border of the rectangle.
     */
    public Line getTopBorder() {
        return new Line(upperLeft,
                new Point(upperLeft.getX() + width, upperLeft.getY()));
    }

    /**
     * Returns the bottom border of the rectangle.
     *
     * @return The bottom border of the rectangle.
     */
    public Line getBottomBorder() {
        return new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * Returns the list of borders of the rectangle.
     *
     * @return list of the borders of the rectangle.
     */
    public Line[] getBorders() {
        Line[] borders = new Line[BORDERS];
        borders[TOPBORDER] = this.getTopBorder();
        borders[BOTTOMBORDER] = this.getBottomBorder();
        borders[RIGHTBORDER] = this.getRightBorder();
        borders[LEFTBORDER] = this.getLeftBorder();
        return borders;
    }

    /**
     * Drawing the rectangle.
     * @param d surface draw on
     */
    public void drawOn(DrawSurface d) {

        d.setColor(this.color);
        d.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(), (int) this.getWidth(), (int) this.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(), (int) this.getWidth(), (int) this.getHeight());
    }


}
