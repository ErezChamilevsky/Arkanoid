//Ere Chamilevsky chimle1 206399867
package Sprites;
import java.awt.Color;
import java.util.List;

import java.util.ArrayList;
import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Sprite;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Environment.Velocity;
import Game.GameLevel;

/**
 * The Block class represents a rectangular block that can collide with other
 * objects and be drawn on a DrawSurface.
 * Implements the Collidable and Sprite interfaces.
 */


public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle representRectangle;
    private ArrayList<HitListener> hitListeners;

    /**
     * Constructor for the Block class.
     *
     * @param upperLeft The upper left point of the rectangle of block.
     * @param width     The width of the rectangle of block.
     * @param height    The height of the rectangle of block.
     * @param color     The color of the rectangle of block.
     */
    // constarcator
    public Block(Point upperLeft, double width, double height, Color color) {
        this.representRectangle = new Rectangle(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return The collision rectangle of the block.
     */

    @Override
    public Rectangle getCollisionRectangle() {
        return this.representRectangle;
    }


    /**
     * Calculates the new velocity of the ball after a hit with the block.
     *
     * @param collisionPoint  The point of the collision.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity of the ball.
     */

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Velocity vel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // touch top border - vert vel to negative
        if (this.representRectangle.getTopBorder().isOnLine(collisionPoint)) {
            vel = new Velocity(vel.getDx(), -Math.abs(currentVelocity.getDy()));
        } else if (this.representRectangle.getBottomBorder().isOnLine(collisionPoint)) {
            vel = new Velocity(vel.getDx(), Math.abs(currentVelocity.getDy()));
        }
        // touch right border - horz vel to negative
        if (this.representRectangle.getRightBorder().isOnLine(collisionPoint)) {
            vel = new Velocity(Math.abs(currentVelocity.getDx()), (vel.getDy()));
        } else if (this.representRectangle.getLeftBorder().isOnLine(collisionPoint)) {
            vel = new Velocity(-Math.abs(currentVelocity.getDx()), vel.getDy());
        }

        this.notifyHit(hitter);
        return vel;
    }

    private void notifyHit(Ball hitter) {
      // Make a copy of the hitListeners before iterating over them.
      List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
      // Notify all listeners about a hit event:
      for (HitListener hl : listeners) {
         hl.hitEvent(this, hitter);
      }
   }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the block.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.representRectangle.drawOn(d);
    }

    /**
     * Does nothing - the block does not change now.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds the block to the given game - registers it as both a sprite and a
     * collidable.
     *
     * @param g The game to which to add the block.
     */

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removing this block from the game because it has been hitten.
     * @param game game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
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
