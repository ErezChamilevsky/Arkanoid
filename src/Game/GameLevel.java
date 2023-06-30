package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Geometry.Point;
import Interfaces.Sprite;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import Sprites.ScoreIndicator;
import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.LevelInformation;
import Environment.BallRemover;
import Environment.BlockRemover;
import Environment.Counter;
import Environment.GameEnvironment;
import Environment.ScoreTrackingListener;
import Environment.SpriteCollection;
import java.awt.Color;

/**
 * A class representing the game "Arkanoid", which contains a collection of
 * sprites and a game environment.
 * The game is played on a game board GUI, where the player has to destroy all
 * blocks with a ball using a paddle.
 *
 */
public class GameLevel implements Animation {
    static final int BALLS = 1;
    static final int BROW = 6;
    static final int BCOL = 12;
    static final int SIZESIZE = 6;
    static final int FPS = 60;
    static final int SPF = 1000;
    static final int RANGE = 2;
    static final int MIN = 3;
    static final int R = 5;
    static final int X = 33;
    static final int Y = 33;
    static final int PADWIDTH = 80;
    static final int PADHEIGHT = 20;
    static final Point PADPLACE = new Point(400, 600 - SIZESIZE - 20 - 1);
    static final int BLOCKWIDTH = 50;
    static final int BLOCKHEIGHT = 30;
    static final int BOND = 800;
    static final int HBOND = 600;
    static final int SBOND = 0;
    static final int COLINT = 50;
    static final int COL = 10;
    static final int BOTTOMSIZE = 3;
    static final int STARTHEIGHTBLOCKS = 120;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private boolean running;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    // constractors
    /**
     * Constructs a new Game object with the given number of balls, rows of blocks,
     * and columns of blocks.
     * Initializes the sprites collection, game environment, and game board GUI.
     *
     * @param level
     * @param ar
     * @param ks
     * @param score
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = null;
        this.ballsCounter = null;
        this.score = score;
        this.running = true;
        this.runner = ar;
        this.keyboard = ks;
        this.levelInfo = level;

    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Initializes the borders blocks of the game board: top, bottom, left, and
     * right.
     */
    public void sideBlocksInit() {
        Counter countBalls = new Counter();
        countBalls.increase(this.levelInfo.numberOfBalls());
        this.setBallsCounter(countBalls);
        HitListener hl = new BallRemover(this, countBalls);

        Block top = new Block(new Point(SBOND, 24 + SBOND), BOND, SIZESIZE, Color.GRAY);
        Block bottom = new Block(new Point(SBOND, HBOND - BOTTOMSIZE), BOND, SIZESIZE, Color.GRAY);
        Block left = new Block(new Point(SBOND, 24 + SBOND), SIZESIZE, HBOND, Color.GRAY);
        Block right = new Block(new Point(BOND - SIZESIZE, 24 + SBOND), SIZESIZE, HBOND, Color.GRAY);
        top.addToGame(this);
        left.addToGame(this);
        bottom.addHitListener(hl);
        bottom.addToGame(this);
        right.addToGame(this);
    }

    /**
     * Initializes the middle blocks of the game board.
     * The blocks are arranged in rows and columns.
     * Each block has a unique color determined by its position.
     *
     * @param hl    hitlistener
     * @param count counter
     */
    public void middleBlocksInit(HitListener hl, Counter count) {
        for (Block block : this.levelInfo.blocks()) {
            block.addHitListener(hl);
            block.addHitListener(new ScoreTrackingListener(this.score));
            block.addToGame(this);
        }
        count.increase(this.levelInfo.numberOfBlocksToRemove());

    }

    /**
     * Initilize of score of the game.
     */
    public void scoreInit() {
        Sprite scoreIndicator = new ScoreIndicator(score, this.levelInfo.levelName());
        scoreIndicator.addToGame(this);
    }

    /**
     * Initialize all the sprites of the game.
     */
    public void initialize() {
        this.backgroundInit();
        this.scoreInit();
        Counter countBlocks = new Counter();
        this.setBlocksCounter(countBlocks);
        HitListener hl = new BlockRemover(this, countBlocks);
        this.middleBlocksInit(hl, countBlocks);

        this.sideBlocksInit();
        paddInit();
        ballInit();
    }

    /**
     * Paddle init.
     */
    public void paddInit() {
        Paddle paddle = this.levelInfo.getPad();
        paddle.setKeyboard(keyboard);
        paddle.addToGame(this);
    }

    /**
     * Balls initilaize.
     */
    public void ballInit() {
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(this.levelInfo.startingPointOfBalls().get(i), R, this.levelInfo.ballsColor());
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * Background init.
     */
    public void backgroundInit() {
        this.levelInfo.getBackground().addToGame(this);
    }

    /**
     * Running the game animation.
     */
    public void run() {
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(
                    new KeyPressStoppableAnimation(this.keyboard, null, new PauseScreen(new DefaultScreen(keyboard))));
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Removing colldiable that has been hitten from the game.
     *
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        this.getGameEnvironment().getCollidables().remove(c);
    }

    /**
     * Removing sprite that has been hitten from the game.
     *
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Setting the counter of the blocks.
     *
     * @param counter counter
     */
    public void setBlocksCounter(Counter counter) {
        this.blocksCounter = counter;
    }

    /**
     * Setting the counter of the balls.
     *
     * @param counter counter
     */
    public void setBallsCounter(Counter counter) {
        this.ballsCounter = counter;
    }

    /**
     * Getting the counter of the balls.
     *
     * @return counter
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Getting the sprite collection.
     *
     * @return spriteCollection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
}