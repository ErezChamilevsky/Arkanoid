
package Interfaces;

import java.awt.Color;
import java.util.List;

import Environment.Velocity;
import Geometry.Point;
import Sprites.Block;
import Sprites.Paddle;

/**
 * LevelInformation is an interface that provides the level details to the gameflow.
 */
public interface LevelInformation {
    /**
     * Number of balls in level.
     * @return num
     */
    int numberOfBalls();

    /**
     * List of the starting points of the balls in the level.
     * @return list of points
     */
    List<Point> startingPointOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return paddle speed.
     * @return speed
     */
    int paddleSpeed();

    /**
     * Return paddle width.
     *
     * @return witdh
     */
    int paddleWidth();

    /**
     * The top left point of the paddle.
     * @return point
     */
    Point paddlePoint();

    /**
     * Returns the level name will be displayed at the top of the screen.
     *
     * @return string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level.
     * each block contains, its size, color and location.
     *
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be
     * "cleared".
     * This number should be <= blocks.size();
     *
     * @return number of blocks
     */
    int numberOfBlocksToRemove();

    /**
     * Returns the color of the balls.
     * @return color
     */
    Color ballsColor();

    /**
     * Gives the paddle of the level.
     * @return paddle
     */
    Paddle getPad();
}
