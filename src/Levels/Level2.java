
package Levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Backgrounds.Lv2;
import Environment.Velocity;
import Geometry.Point;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Sprites.Block;
import Sprites.Paddle;
/**
 * The details of level 2.
 */
public class Level2 implements LevelInformation {

    static final int BLOCKWIDTH = 49;
    static final int BLOCKHEIGHT = 30;
    static final int BOND = 800;
    static final int HBOND = 600;
    static final int SBOND = 0;
    static final int BROW = 1;
    static final int BCOL = 12;
    static final int SIZESIZE = 9;
    static final int STARTHEIGHTBLOCKS = 200;

    @Override
    public int numberOfBalls() {
        return 9;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(i * 10 * Math.pow(-1, i), 5));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public Point paddlePoint() {
        return new Point(100, 570);
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Lv2();
    }
    /**
     * Returning colors.
     * @return colors
     */
    public List<Color> getColorsList() {
        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);
        colors.add(Color.white);
        return colors;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        List<Color> colors = this.getColorsList();
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < this.numberOfBlocksToRemove() - j; i++) {
                Block block = new Block(new Point(BOND - SIZESIZE - (i + 1) * BLOCKWIDTH,
                        STARTHEIGHTBLOCKS + j * BLOCKHEIGHT),
                        BLOCKWIDTH, BLOCKHEIGHT,
                        colors.get(i / 2));
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 16;
    }

    @Override
    public List<Point> startingPointOfBalls() {
        List<Point> list = new ArrayList<Point>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            list.add(new Point(400, 500));
        }
        return list;
    }

    @Override
    public Color ballsColor() {
        return Color.BLACK;
    }

    @Override
    public Paddle getPad() {
        return new Paddle(this.paddlePoint(), this.paddleWidth(), 20, null);
    }
}
