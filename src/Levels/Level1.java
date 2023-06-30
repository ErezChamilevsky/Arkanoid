
package Levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Backgrounds.Lv1;
import Environment.Velocity;
import Geometry.Point;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Sprites.Block;
import Sprites.Paddle;
/**
 * Level 1 info.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Point> startingPointOfBalls() {
        List<Point> list = new ArrayList<Point>();
        list.add(new Point(400, 500));
        return list;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        list.add(new Velocity(0, -3.5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public Point paddlePoint() {
    return new Point(370, 570);
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Lv1();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        list.add(new Block(new Point(390, 290), 20, 20, Color.WHITE));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
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
