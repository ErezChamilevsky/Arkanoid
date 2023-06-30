package Environment;

import java.util.ArrayList;

import biuoop.DrawSurface;
import Interfaces.Sprite;

/**
 * The SpriteCollection class represents a collection of sprites in the game.
 * The class contains a list of sprites and methods to add sprites to the
 * collection, notify all sprites every iteration of the game and draw all
 * sprites on a
 * given surface.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spirtes;

    /**
     * Constractor of the collection.
     *
     * @param spirtes arrayList of sprites.
     */
    public SpriteCollection(ArrayList<Sprite> spirtes) {
        this.spirtes = spirtes;
    }

    /**
     * Constructs a new SpriteCollection object with an empty list of sprites.
     */
    public SpriteCollection() {
        this.spirtes = new ArrayList<Sprite>();
    }

    /**
     * Adds a given sprite to the collection.
     *
     * @param s the sprite to be added to the collection.
     */
    public void addSprite(Sprite s) {
        this.spirtes.add(s);
    }

    /**
     * Notifies all sprites in the collection every iteration of the game.
     */
    public void notifyAllTimePassed() {
        int length = this.spirtes.size();
        for (int i = 0; i < length; i++) {
            if (i >= this.spirtes.size()) {
                break;
            }
            this.spirtes.get(i).timePassed();
        }

        // for (Sprite i : this.spirtes) {
        //     i.timePassed();
        // }
    }

    /**
     * Draws all sprites in the collection on a given surface.
     *
     * @param d the surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : this.spirtes) {
            i.drawOn(d);
        }
    }

    /**
     * Getting the list of sprites.
     * @return sprites' list
     */
    public java.util.List<Sprite> getSprites() {
        return this.spirtes;
    }
}