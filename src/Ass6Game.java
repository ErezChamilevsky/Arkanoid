
import java.util.ArrayList;
import java.util.List;

import Game.AnimationRunner;
import Game.GameFlow;
import Interfaces.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import biuoop.GUI;

/**
 * A board with two balls and the number of wanted blocks.
 */
public class Ass6Game {
    private List<LevelInformation> list;

    /**
     * Constractor.
     * @param levels list of levels
     */
    public Ass6Game(List<LevelInformation> levels) {
        this.list = levels;
    }

    /**
     * Runnig the assignment.
     * @param levels levels to run
     */
    public void run(List<LevelInformation> levels) {
        GUI g = new GUI("Arknoid", 800, 600);
        GameFlow game = new GameFlow(new AnimationRunner(g), g.getKeyboardSensor());
        game.runLevels(levels);
        g.close();
    }

    /**
     * Taking args from command and returns a list of levels by it.
     * @param args
     * @return list of levels
     */
    public List<LevelInformation> argsToList(String[] args) {
        if (args.length == 0) {
            return this.list;
        }
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        for (String i : args) {
            try {
                // if(Integer.parseInt(i)-1 > 3 || Integer.parseInt(i)-1 < 1 ){
                // continue;
                // }
                levels.add(this.list.get(Integer.parseInt(i) - 1));
            } catch (Exception e) {
                continue;
            }
        }
        if (levels.size() != 0) {
            return levels;
        }
        return this.list;
    }

    /**
     * The main of the program. 2 ball, 6 rows of blocks, up to 12 blocks in row.
     *
     * @param args
     */
    public static void main(String[] args) {

        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());

        Ass6Game game = new Ass6Game(levels);
        game.run(game.argsToList(args));
    }
}
