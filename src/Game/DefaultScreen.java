
package Game;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Class of default keypress screen, in order to apply the decorator design.
 */
public class DefaultScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * Constractor.
     * @param key
     */
    public DefaultScreen(KeyboardSensor key) {
        this.keyboard = key;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
