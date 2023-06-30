
package Backgrounds;

import java.awt.Color;

import Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * Class of background for level 1.
 */
public class Lv3 implements Sprite {
    private int counter;
    private boolean laser;

    /**
     * Constractor.
     */
    public Lv3() {
        this.counter = 0;
        this.laser = false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(25, 25, 50));
        d.fillRectangle(0, 0, 801, 601);
        d.setColor(Color.GRAY);
        d.fillOval(50, 150, 250, 150);
        d.fillOval(120, 60, 120, 150);
        d.setColor(Color.white);
        d.fillCircle(180, 115, 40);
        if (this.laser) {
            d.setColor(Color.cyan);
        }
        if (this.counter < 8) {
            for (int i = 0; i < 100; i++) {
                d.drawLine(175, 300, -50 + i * 5, 800);
            }
        } else if (this.counter < 14) {
            for (int i = 0; i < 100; i++) {
                d.drawLine(175, 300, 25 + i * 5, 800);
            }
        } else {
            for (int i = 0; i < 100; i++) {
                d.drawLine(175, 300, 125 + i * 5, 800);
            }
        }

    }

    @Override
    public void timePassed() {
        this.counter++;
        this.laser = false;
        if (this.counter > 10 && this.counter < 14) {
            this.laser = true;
        }
        if (this.counter == 14) {
            this.counter = 0;
        }
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
