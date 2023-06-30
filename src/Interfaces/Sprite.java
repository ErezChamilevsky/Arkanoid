package Interfaces;


import biuoop.DrawSurface;
import Game.GameLevel;

/**
 * Interface of object in the game.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given draw surface.
     *
     * @param d the draw surface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that interation has passed.
     */
    void timePassed();

    /**
     * Adds the sprite to the game.
     *
     * @param g the game to add the sprite to.
     */
    void addToGame(GameLevel g);
}