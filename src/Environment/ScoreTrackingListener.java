
package Environment;

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
/**
 * Tracking the score of the game. Adding 5 point for every clash with a block.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    static final int POINTS = 5;

    /**
     * Constractor.
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(POINTS);
    }

}
