
package Environment;
/**
 * Class of counter, used for count score, blocks and balls.
 */
public class Counter {
    private int count;

    /**
     * Constractor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     * @return the number of the count
     */
    public int getValue() {
        return this.count;
    }
}
