
package Levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Backgrounds.Lv3;
import Environment.Velocity;
import Geometry.Point;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Sprites.Block;
import Sprites.Paddle;
/**
 * The infromations of level 3.
 */
public class Level3 implements LevelInformation {
    static final int BLOCKWIDTH = 50;
    static final int BLOCKHEIGHT = 30;
    static final int BOND = 800;
    static final int HBOND = 600;
    static final int SBOND = 0;
    static final int COLINT = 50;
    static final int COL = 10;
    static final int BOTTOMSIZE = 3;
    static final int STARTHEIGHTBLOCKS = 120;
    static final int BROW = 5;
    static final int BCOL = 10;
    static final int SIZESIZE = 6;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            list.add(Velocity.fromAngleAndSpeed(i * 30 * Math.pow(-1, i), 5));
        }
        return list;
    }

    @Override
    public Color ballsColor() {
        return Color.red;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
       return 80;
    }

    @Override
    public String levelName() {
        return "Galactic Mission";
    }

    @Override
    public Sprite getBackground() {
        return new Lv3();

    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        for (int j = 0; j < BROW; j++) {
            for (int i = 0; i < BCOL - j; i++) {
                Block block = new Block(new Point(BOND - SIZESIZE - (i + 1) * BLOCKWIDTH,
                        STARTHEIGHTBLOCKS + j * BLOCKHEIGHT),
                        BLOCKWIDTH, BLOCKHEIGHT,
                        Color.getHSBColor(150 + j * COL, 15 + j * COL, 45 + j * COL));
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
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
    public Point paddlePoint() {
        return new Point(360, 570);
    }

    @Override
    public Paddle getPad() {
        return new Paddle(this.paddlePoint(), this.paddleWidth(), 20, null);
    }
}
