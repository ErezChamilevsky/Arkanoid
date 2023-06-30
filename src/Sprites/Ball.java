//Erez Chamilevsky chimle1 206399867

package Sprites;

import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Line;
import Geometry.Rectangle;
import Interfaces.Sprite;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Environment.GameEnvironment;
import Environment.CollisionInfo;
import Environment.Velocity;
import Game.GameLevel;

import java.util.ArrayList;

/**
 * This is a Java program that defines a class called "Ball". The Ball class
 * represents a ball that can be drawn on a graphical user interface (GUI) and
 * moved around the screen. The class contains several fields, including the
 * ball's center point, radius, color, and velocity, as well as the minimum and
 * maximum ranges of motion for the ball. It also contains several constructors
 * and methods for accessing and modifying the ball's properties.
 *
 * @author Erez Chamilevksy
 * @version 1.0
 * @since 2023
 */

public class Ball implements Sprite, HitNotifier {
    static final int WBOND = 800;
    static final int HBOND = 600;
    static final double STEPANDHALF = 1.4;
    static final int ERROR = -1;
    static final int SIDESIZE = 6;

    // fields
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private ArrayList<HitListener> hitListeners;

    private GameEnvironment gameEnvironment; // defualt as null

    // constructors
    /**
     * A costructor of ball, gets the criteria and makes a ball object.
     *
     * @param x     x cordinate of the center of the ball
     * @param y     y cordinate of the center of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.hitListeners = new ArrayList<HitListener>();

        this.gameEnvironment = null;
    }

    /**
     * A costructor of ball, gets the criteria and makes a ball object.
     *
     * @param center of the ball
     * @param r      radius of the ball
     * @param color  color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity();
        this.gameEnvironment = null;
        this.hitListeners = new ArrayList<HitListener>();

    }

    // accessors

    /**
     * @return x cordinate of cenetr of the circle
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y cordinate of cenetr of the circle
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return radius size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return color of the circle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(),
                this.radius);
    }

    /**
     * Setting the game environment of the ball.
     *
     * @param gameEnvironment the game environment that seted
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Setting the vel of the ball.
     *
     * @param v new velocity that seted
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Setting the vel of the ball.
     *
     * @param dx the motion in x line
     * @param dy the motion in y line
     *
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * Return the velocity of the ball.
     *
     * @return the vel of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The ball movement.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Checking if the ball can make whole step, if it does it return false
     * if it moved to the colliosion point.
     *
     * @param line of the step (trajectory)
     * @return true or false if the ball moved to the collision point
     */
    public boolean movingToColision(Line line) {
        int check = 0;

        if (this.gameEnvironment.getClosestCollision(line) == null) {
            return false;
        }

        // getting the closest object that the ball has collision with
        CollisionInfo collisionObject = this.gameEnvironment.getClosestCollision(line);

        // now we want to move it to the just before the collision, cases that dx != 0
        if (this.velocity.getDx() > 0) {
            this.center = line
                    .pointOnLineByX(collisionObject.collisionPoint().getX() - this.getSize());
            check = 1;
        } else if (this.velocity.getDx() < 0) {
            this.center = line
                    .pointOnLineByX(collisionObject.collisionPoint().getX() + this.getSize());
            check = 1;
        } else {
            if (this.velocity.getDy() > 0) {
                this.center = new Point(center.getX(),
                        collisionObject.collisionPoint().getY() - this.getSize());
                check = 1;
            } else if (this.velocity.getDy() < 0) {
                this.center = new Point(center.getX(),
                        collisionObject.collisionPoint().getY() + this.getSize());
            check = 1;
            }
        }

        this.setVelocity(
                collisionObject.collisionObject().hit(collisionObject.collisionPoint(), this.velocity, this));
        if (check == 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the center of the ball is collision point with a block.
     *
     * @return true for the center is collision, false for not
     */
    public boolean isCenterCollision() {
        // setting the next step, the nearest collision object
        Line nextStep = new Line(this.center, new Point(this.center.getX() + this.getVelocity().getDx(),
                this.center.getY() + this.getVelocity().getDy()));

        CollisionInfo collisionObject = this.gameEnvironment.getClosestCollision(nextStep);

        if (this.velocity.getDx() > 0) {
            if (this.center.equals(nextStep.pointOnLineByX(collisionObject.collisionPoint().getX()
                    - this.getSize()))) {
                return true;
            }
        } else if (this.velocity.getDx() < 0) {
            if (this.center.equals(nextStep.pointOnLineByX(collisionObject.collisionPoint().getX()
                    + this.getSize()))) {
                return true;
            }
        } else {
            if (this.velocity.getDy() > 0) {
                if (this.center.equals(new Point(center.getX(),
                        collisionObject.collisionPoint().getY() - this.getSize()))) {
                    return true;
                }
            } else if (this.velocity.getDy() < 0) {
                if (this.center.equals(new Point(center.getX(),
                        collisionObject.collisionPoint().getY() + this.getSize()))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    /**
     * Making the step, checks if it will be in collision and change as wanted.
     * Checks that the ball is not stuck in borders/blocks/paddle
     */
    public void timePassed() {
        if (!this.collisionWithCollidable()) {
            this.moveOneStep();
        }

        this.stuckInBorders();
        this.checkNCorrectThatStuck();
    }

    @Override
    public void addToGame(GameLevel g) {
        this.setGameEnvironment(g.getGameEnvironment());
        g.addSprite(this);
    }

    /**
     * Setting center of te ball.
     *
     * @param point
     */
    public void setCenter(Point point) {
        this.center = point;
    }

    /**
     * Checks if ball stuck in rectangle.
     *
     * @param rectangle to check
     * @return true for stuck false for not
     */
    public boolean isBallStuckInBlock(Rectangle rectangle) {
        // check if it is in the block
        if (rectangle.getBottomBorder().isXBetweenVals(this.center.getX())
                && rectangle.getLeftBorder().isYBetweenVals(this.center.getY())) {
            return true;
        }
        return false;

    }

    /**
     * Giving the reverse trajectory.
     *
     * @return reverse trajectorry of the ball
     */
    public Line revTraj() {
        return new Line(
                new Point(this.center.getX() - this.velocity.getDx(), this.center.getY() - this.velocity.getDy()),
                this.center);
    }

    /**
     * Check and correct in case it stuck in block.
     */
    public void checkNCorrectThatStuck() {
        Line revTraj = this.revTraj();
        if (this.gameEnvironment.getClosestCollision(revTraj) == null) {
            return;
        }
        CollisionInfo col = this.gameEnvironment.getClosestCollision(revTraj);
        if (this.isBallStuckInBlock(col.collisionObject().getCollisionRectangle())) {
            movingToColision(this.revTraj());
        }

    }

    /**
     * Check if stuck in borders and correct it with velocity and center place.
     */
    public void stuckInBorders() {
        if (this.center.getX() < SIDESIZE) {
            this.center = new Point(SIDESIZE + 1, this.center.getY());
            this.setVelocity(Math.abs(this.getVelocity().getDx()), this.getVelocity().getDy());
        }
        if (this.center.getX() > WBOND - SIDESIZE) {
            this.center = new Point(WBOND - 1 - SIDESIZE, this.center.getY());
            this.setVelocity(-Math.abs(this.getVelocity().getDx()), this.getVelocity().getDy());
        }
        // if (this.center.getY() > HBOND - SIDESIZE) {
        //     this.center = new Point(this.center.getX(), HBOND - 1 - SIDESIZE);
        //     this.setVelocity((this.getVelocity().getDx()), -Math.abs(this.getVelocity().getDy()));
        // }
        if (this.center.getY() < SIDESIZE) {
            this.center = new Point(this.center.getX(), SIDESIZE + 1);
            this.setVelocity((this.getVelocity().getDx()), Math.abs(this.getVelocity().getDy()));
        }
    }

    /**
     * Checking for every collidable if the ball stuck in it.
     */
    public void colStuckCheckNCorrect() {
        for (Collidable i : this.gameEnvironment.getCollidables()) {
            this.isBallStuckInCol(i);
        }
    }

    /**
     * Checks if ball stuck in a collidable.
     *
     * @param col collidable that checked
     */
    public void isBallStuckInCol(Collidable col) {
        if (col == null) {
            return;
        }

        Rectangle rec = col.getCollisionRectangle();

        if (!rec.getLeftBorder().isYBetweenVals(this.center.getY())) {
            return;
        }

        if (!rec.getTopBorder().isXBetweenVals(this.center.getX())) {
            return;
        }

        movingToColision(this.revTraj());

    }

    /**
     * Checks if there is collision with some object in the game.
     *
     * @return true or false for can make the next step or not.
     */
    public boolean collisionWithCollidable() {
        // game environment is null
        if (this.gameEnvironment == null) {
            return false;
        }

        Line nextStep = new Line(this.center, new Point(this.center.getX() + this.getVelocity().getDx(),
                this.center.getY() + this.getVelocity().getDy()));

        // case of no collision in this step, we want to check the half next step
        if (this.gameEnvironment.getClosestCollision(nextStep) == null) {

            Line anotherStep = new Line(this.center, new Point(this.center.getX() + STEPANDHALF * this.velocity.getDx(),
                    this.center.getY() + STEPANDHALF * this.velocity.getDy()));

            return ballStepMaker(anotherStep);
        }

        // there is collision in this step
        CollisionInfo collisionObject = this.gameEnvironment.getClosestCollision(nextStep);

        /*
         * in case that the collision point is the point where the center of the ball
         * already, so we change the velocity and check again this again
         */
        if (this.isCenterCollision()) {
            this.setVelocity(
                    collisionObject.collisionObject().hit(collisionObject.collisionPoint(), this.velocity, this));
            return false;
        }
        return movingToColision(nextStep);

    }

    /**
     * Making the step.
     *
     * @param line of the trajictory
     * @return true for moved false for not
     */
    public boolean ballStepMaker(Line line) {
        if (this.gameEnvironment.getClosestCollision(line) != null) {
            this.movingToColision(line);
            return true;
        }
        return false;
    }

    /**
     * Removing this block from the game because it has been hitten.
     *
     * @param game game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


}


