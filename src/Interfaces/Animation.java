package Interfaces;

import biuoop.DrawSurface;
/**
 * Animation interface with the method of making one frame and stopping the frame.
 */
public interface Animation {

    /**
     * Is in charge of the logic.
     *
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * This class in charge of closing the the game if there are no more blocks or
     * balls.
     *
     * @return true for closing
     */
    boolean shouldStop();
}