
package Interfaces;

import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Environment.Velocity;
/**
 * The Collidable interface defines methods for objects that can collide with
 * ball.
 */

public interface Collidable {
    /**
     * Accessor to the recantalge of the collidable.
     * @return rectangle the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at the given collisionPoint with a
     * given velocity.
     * The method returns the new velocity expected after the hit.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the object when it hit the
     *                        collidable.
     * @param hitter ball
     * @return The new velocity of the ball expected after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
