package Interfaces;
/**
 * Interface of nitifier of hit.
 */
public interface HitNotifier {


    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hitlistener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hitlistener
     */
    void removeHitListener(HitListener hl);
}
