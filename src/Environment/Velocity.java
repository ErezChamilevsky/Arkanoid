package Environment;

import Geometry.Point;
/*
 * 206399867
 * Erez Chamilevsky
 * chamile1
 */


/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    static final double DX = 5;
    static final double DY = 5;

    private double dx;
    private double dy;
    private double angleBetweenVel;

    // constructor

    /**
     * Constractor, gets the movement on the axes.
     * @param dx movement every step
     * @param dy movement every step
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        // angle by tan
        this.angleBetweenVel = Math.toDegrees(java.lang.Math.atan(dy / dx));
    }

    /**
     * Default velocity.
     */
    public Velocity() {
        this.dx = DX;
        this.dy = DY;
        // angle by tan
        this.angleBetweenVel = Math.toDegrees(java.lang.Math.atan(dy / dx));

    }


    /**
     * Converts from angle and vector to dx and dy movement.
     * @param angle of the movement
     * @param speed of the vector
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle)); //assuming up = 0
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
     }

     /**
      * Accessor to dx movement.
      *
      * @return dx the movement on x axe
      */
    public double getDx() {
        return this.dx;
    }

    /**
     * Accessor to dy movement.
     *
     * @return dy the movement on y axe
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Accessor to the angle between the dx movement and dy movement.
     * @return angle
     */
    public double getAngleBetweenVel() {
        return this.angleBetweenVel;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p point
     * @return the next position (with the dx and dy added)
     */

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Getting the size of the vector of the velocity.
     * @return size of the speed's vector
     */
    public double getSpeedSize() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }

}

