package Environment;

import Geometry.Point;
import Interfaces.Collidable;

/**
 * The CollisionInfo class represents information about a collision between a
 * collidable object and a ball.
 * It holds the collision point and the collidable object involved in the
 * collision.
 */

public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a new CollisionInfo object with a given collidable object and
     * collision point.
     *
     * @param object   the collidable object involved in the collision.
     * @param colPoint the point at which the collision occurs.
     */
    public CollisionInfo(Collidable object, Point colPoint) {
        this.collisionObject = object;
        this.collisionPoint = colPoint;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
