package Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Geometry.Line;
import Interfaces.Collidable;

/**
 * The GameEnvironment class represents a collection of all collidable objects
 * in the game.
 * The class contains a list of collidables, methods to add collidables, get the
 * closest collision,
 * find the paddle and get the list of all collidables.
 */

public class GameEnvironment {
    static final int FIRST = 0;
    private java.util.List<Collidable> collidables;

    // constarctor
    /**
     * Constarctor of the game environment by a given list.
     * @param collidables list of collidables
     */
    public GameEnvironment(java.util.ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Making new arrayList of the collidables in the game environment.
     */

    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Adds the given collidable object to the list of collidables in this
     * GameEnvironment object.
     *
     * @param c a collidable object to be added to the list of collidables.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Given a line trajectory, returns the information about the closest collision
     * that is going to occur with any collidable.
     *
     * @param trajectory a line representing the path of an object.
     * @return CollisionInfo object representing the closest collision, or null if
     *         there is no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<CollisionInfo> allCollisionInfos = new ArrayList<CollisionInfo>();
        for (int i = 0; i < this.collidables.size(); i++) {
            allCollisionInfos.add(new CollisionInfo(this.collidables.get(i),
                    trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle())));
        }
        allCollisionInfos = this.bubbleSortOCollisionInfos(allCollisionInfos, trajectory);

        return firstNotNull(allCollisionInfos);
    }

    /**
     * Sorts the given list of CollisionInfo objects. Basing on the distance between
     * their collision points and the starting point of the given line trajectory,
     * using bubble sort algorithm.
     *
     * @param list       a list of CollisionInfo objects to be sorted.
     * @param trajectory a line representing the path of an object.
     * @return the sorted list of CollisionInfo objects.
     */
    public java.util.List<CollisionInfo> bubbleSortOCollisionInfos(java.util.List<CollisionInfo> list,
            Line trajectory) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).collisionPoint() == null || list.get(j + 1).collisionPoint() == null) {
                    Collections.swap(list, j, j + 1);
                } else if (list.get(j).collisionPoint().distance(trajectory.originalStart()) > list.get(j + 1)
                        .collisionPoint()
                        .distance(trajectory.originalStart())) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
        return list;
    }

    /**
     * Returns the first collisionInfo from sorted list of collision infos that
     * its point does not null.
     *
     * @param list
     * @return null or not collisioninfo
     */
    public CollisionInfo firstNotNull(java.util.List<CollisionInfo> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).collisionPoint() != null) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Accessor for the collidalbes list.
     *
     * @return list of collidable
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}
