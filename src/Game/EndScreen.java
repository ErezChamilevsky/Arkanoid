
package Game;

import Environment.Counter;
import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * End screen for the ending of the game.
 */
public class EndScreen implements Animation {
    private Animation decorator;
    private boolean win;
    private Counter score;

    /**
     * Constractor.
     *
     * @param animation
     * @param win
     * @param score
     */
    public EndScreen(Animation animation, boolean win, Counter score) {
        this.decorator = animation;
        this.win = win;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your Score is " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over! Your Score is " + this.score.getValue(), 32);
        }
        decorator.doOneFrame(d);

    }

    @Override
    public boolean shouldStop() {
        return decorator.shouldStop();
    }
}