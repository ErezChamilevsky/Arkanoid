
package Game;

import java.awt.Color;

import Environment.SpriteCollection;
import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private double secs;
    private int countForm;
    private SpriteCollection gamescreen;

    /**
     * Constractor.
     * @param numOfSeconds
     * @param countFrom
     * @param gameScreen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.secs = numOfSeconds;
        this.countForm = countFrom;
        this.gamescreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gamescreen.drawAllOn(d);
        d.setColor(Color.white);
        d.drawText(10, d.getHeight() / 2, "Starts in ... " + this.countForm, 32);
        this.countForm--;
    }

    @Override
    public boolean shouldStop() {
        if (countForm == 0) {
            return true;
        }
        return false;
    }
}