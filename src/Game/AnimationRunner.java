
package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class of running the animation.
 */
public class AnimationRunner {
    static final int MIL = 1000;

    private GUI gui;
    private int framesPerSecond;

    /**
     * Constractor.
     *
     * @param gui gui
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.gui = gui;
    }

    /**
     * Constractor.
     *
     * @param gui
     * @param fps
     */
    public AnimationRunner(GUI gui, int fps) {
        this.framesPerSecond = fps;
        this.gui = gui;
    }

    /**
     * The runnig method.
     *
     * @param animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MIL / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            Sleeper sleeper = new Sleeper();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Setting the frames per seconds.
     *
     * @param sec
     */
    public void setFPS(int sec) {
        this.framesPerSecond = sec;
    }
}
