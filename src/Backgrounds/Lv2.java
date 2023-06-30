
package Backgrounds;

import java.awt.Color;

import Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * Class of background for level 1.
 */
public class Lv2 implements Sprite {
    static final int START = 0;
    static final int BOND = 801;
    static final int HBOND = 601;
    static final Color COLRO = new Color(110, 240, 245);
    static final int LINE = 150;
    static final int QU = 25;

    /**
     * Constractor.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(COLRO);
        d.fillRectangle(START, START, BOND, HBOND);
        d.setColor(Color.yellow);
        for (int i = 0; i < QU * 2; i++) {
            d.drawLine(LINE, LINE, QU + i * 5, LINE * 2);
        }
        d.fillCircle(LINE, LINE, QU * 3);
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
