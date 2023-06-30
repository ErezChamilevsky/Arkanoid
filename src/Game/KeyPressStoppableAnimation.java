
package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Decorator design pattern of keypress screens.
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation decorated;
    private String key;
    private KeyboardSensor keyboard;

    /**
     * Constractor.
     *
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.decorated = animation;
        this.key = key;
        this.keyboard = sensor;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        decorated.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return decorated.shouldStop();
    }

}
