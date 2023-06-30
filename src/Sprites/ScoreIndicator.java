//Erez Chamilevsky chimle1 206399867

package Sprites;

import Environment.Counter;
import Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * An sprite that prints the score of the game.
 */
public class ScoreIndicator implements Sprite {
    static final int X = 380;
    static final int XLEVEL = 520;
    static final int HEIGHT = 20;
    private Counter score;
    private String levelName;
    /**
     * Constractor.
     * @param score counter of the score
     * @param levelName level
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.levelName = levelName;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(Color.black);
        d.drawText(X, HEIGHT, String.valueOf("Score: " + this.score.getValue()), HEIGHT);
        d.drawText(XLEVEL, HEIGHT, String.valueOf("Level Name: " + this.levelName), HEIGHT);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
