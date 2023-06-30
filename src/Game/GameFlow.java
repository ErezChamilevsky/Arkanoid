
package Game;

import java.util.List;

import Environment.Counter;
import Interfaces.LevelInformation;
import biuoop.KeyboardSensor;

/**
 * Class that controls the flow of the game.
 */
public class GameFlow {
   static final int END = 100;
   static final int FPS = 60;
   static final int COUNT = 2;
   private KeyboardSensor ks;
   private AnimationRunner ar;
   /**
    * Constractor.
    * @param ar
    * @param ks
    */
   public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
      this.ar = ar;
      this.ks = ks;
   }
   /**
    * Run the levels.
    * @param levels
    */
   public void runLevels(List<LevelInformation> levels) {
      Counter score = new Counter();

      for (LevelInformation levelInfo : levels) {

         GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, score);
         level.initialize();
         this.ar.setFPS(COUNT);
         CountdownAnimation cd = new CountdownAnimation(3, 3, level.getSprites());
         while (!cd.shouldStop()) {
            this.ar.run(cd);
         }

         this.ar.setFPS(FPS);
         while (!level.shouldStop()) {
            level.run();
         }

         if (level.getBallsCounter().getValue() == 0) {
            this.ar.run(new KeyPressStoppableAnimation(ks, null, new EndScreen(new DefaultScreen(ks), false, score)));
            return;
         }

         score.increase(END);

      }
      this.ar.run(new KeyPressStoppableAnimation(ks, null, new EndScreen(new DefaultScreen(ks), true, score)));

   }
}