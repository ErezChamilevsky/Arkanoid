
package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
/**
 * Pause screen.
 */
public class PauseScreen implements Animation {
    private Animation decorater;
    /**
     * Contractor.
     * @param animation
     */
    public PauseScreen(Animation animation) {
        this.decorater = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        decorater.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return decorater.shouldStop();
    }
}