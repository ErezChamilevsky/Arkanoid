package Backgrounds;

import java.awt.Color;

import Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * Class of background for level 1.
 */
public class Lv1 implements Sprite {
    static final int START = 0;
    static final int BOND = 801;
    static final int HBOND = 601;
    static final int MID = 5;
    static final int R = 25;
    static final int ND = 400;
    static final int GD = 300;
    static final int D = 10;

    private int counter;

    /**
     * Constractor.
     */
    public Lv1() {
        this.counter = START;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.MAGENTA);
        d.fillRectangle(START, START, BOND, HBOND);
        if (this.counter == MID) {
            d.setColor(Color.red);
            this.counter = START;
        } else {
            d.setColor(Color.WHITE);
            this.counter++;
        }
        for (int i = 1; i < D; i++) {
            d.drawCircle(ND, GD, R * i);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
