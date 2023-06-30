
package Environment;

import Game.GameLevel;
import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;
/**
 * Class removes balls from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * Constractor.
     * @param game
     * @param ballsCounter
     */
    public BallRemover(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.remainingBalls = ballsCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
    }
}
