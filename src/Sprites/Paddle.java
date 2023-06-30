//Erez Chamilevsky chamile1 206399867
//Erez Chamilevsky chamile1 206399867

package Sprites;

import biuoop.KeyboardSensor;

import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Line;
import Geometry.Rectangle;
import Interfaces.Sprite;
import Interfaces.Collidable;
import Environment.Velocity;
import Game.GameLevel;
import java.awt.Color;
import java.util.ArrayList;


/**
 * A class representing a Paddle in the game, which implements the Collidable
 * and Sprite interfaces.
 */
public class Paddle implements Collidable, Sprite {
    static final double SIDESIZE = 6;
    static final double STEPSIZE = 5;
    static final double MAXXSPOT = 800;
    static final Color PADCOLOR = Color.DARK_GRAY;
    static final double SECTIONSIZE = 0.2;
    static final int SECTION = 5;
    static final int CONSANGLE = -60;
    static final int ANGLE = 30;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle representRectangle;

    /**
     * Constractor.
     * @param upperLeft
     * @param width
     * @param height
     * @param keyboard
     */
    public Paddle(Point upperLeft, double width, double height, KeyboardSensor keyboard) {
        this.representRectangle = new Rectangle(upperLeft, width, height, PADCOLOR);
        this.keyboard = keyboard;
        // this.upperLeft = upperLeft;
        // this.width = width;
        // this.height = height;
    }

    /**
     * Checks if the next step to the right is valid and not out of bound.
     *
     * @return true if the next step to the right is valid, false otherwise.
     */

    public boolean isNextStepRightOk() {
        if ((this.getCollisionRectangle().getUpperLeft().getX() + STEPSIZE) < (MAXXSPOT
                - this.getCollisionRectangle().getWidth() - SIDESIZE)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the next step to the left is valid and not out of bound.
     *
     * @return true if the next step to the left is valid, false otherwise.
     */
    public boolean isNextStepLeftOk() {
        if ((this.getCollisionRectangle().getUpperLeft().getX() - STEPSIZE) > (SIDESIZE)) {
            return true;
        }
        return false;
    }

    /**
     * Moves the paddle to the left by STEPSIZE units.
     */
    public void moveLeft() {
        this.getCollisionRectangle().setUpperLeft(this.representRectangle.getUpperLeft().getX() - STEPSIZE,
                this.representRectangle.getUpperLeft().getY());
    }

    /**
     * Moves the paddle to the right by STEPSIZE units.
     */
    public void moveRight() {
        this.getCollisionRectangle().setUpperLeft(this.representRectangle.getUpperLeft().getX() + STEPSIZE,
                this.representRectangle.getUpperLeft().getY());
    }

    /**
     * Returns the representing rectangle of the paddle.
     *
     * @return the representing rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.representRectangle;
    }

    /**
     * Returns a list of the paddle's section lines in order to make it funnier.
     *
     * @return a list of the paddle's section lines.
     */
    public ArrayList<Line> getSectionLines() {
        double sectionSize = SECTIONSIZE * this.getCollisionRectangle().getWidth();
        int i = 1;
        double startXOfObj = this.getCollisionRectangle().getUpperLeft().getX();
        ArrayList<Line> listOfSections = new ArrayList<Line>();
        for (; i < SECTION + 1; i++) {
            Point start = new Point(startXOfObj + (i - 1) * sectionSize + 1,
                    this.getCollisionRectangle().getUpperLeft().getY());
            Point end = new Point(startXOfObj + i * sectionSize,
                    this.getCollisionRectangle().getUpperLeft().getY());
            Line section = new Line(start, end);
            listOfSections.add(section);
        }
        return listOfSections;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {

        ArrayList<Line> sections = getSectionLines();

        // touch on the sections of the top border of the paddle
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).isOnLine(collisionPoint)) {
                //return new Velocity(0, 1);
                Velocity.fromAngleAndSpeed(CONSANGLE + ANGLE * i, currentVelocity.getSpeedSize()).getDx();
                return Velocity.fromAngleAndSpeed(CONSANGLE + ANGLE * i, currentVelocity.getSpeedSize());
            }
        }

        // touch right border - horz vel to positive
        if (this.representRectangle.getRightBorder().isOnLine(collisionPoint)) {
            return new Velocity(Math.abs(currentVelocity.getDx()), (currentVelocity.getDy()));
        } else if (this.representRectangle.getLeftBorder().isOnLine(collisionPoint)) {
            return new Velocity(-Math.abs(currentVelocity.getDx()), (currentVelocity.getDy()));
        }

        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        this.representRectangle.drawOn(d);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.isNextStepLeftOk()) { // checks it is fine to do nex step
                this.moveLeft();
            } else {
                this.getCollisionRectangle().setUpperLeft(SIDESIZE,
                        this.getCollisionRectangle().getUpperLeft().getY());
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.isNextStepRightOk()) { // checks it is fine to do nex step
                this.moveRight();
            } else {
                this.getCollisionRectangle().setUpperLeft(MAXXSPOT - SIDESIZE
                        - this.getCollisionRectangle().getWidth(),
                        this.getCollisionRectangle().getUpperLeft().getY());
            }
        }
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Set Keyboard.
     * @param key
     */
    public void setKeyboard(KeyboardSensor key) {
        this.keyboard = key;
    }

}
