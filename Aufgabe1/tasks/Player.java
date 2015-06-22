import java.awt.*;

/**
 * Created by Andreas Wiesinger [1429087] on 22.06.2015.
 */
public class Player extends Ball {

    private boolean repaint;

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Constructor of Player.
     * @param color
     * @param size
     * @param x
     * @param y
     * @throws InvalidBallException
     */
    public Player( Color color, int size, int x, int y ) throws InvalidBallException{
        super(color, size, x, y);
        repaint=false;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for repaint of Player.
     * @return
     */
    public synchronized boolean isRepaint() {
        return repaint;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Setter for repaint of Player
     * @param rep
     */
    public synchronized void setRepaint(boolean rep) {
        this.repaint=rep;
    }

    public void move( int dir_x, int dir_y ) {
        //TODO: move the player (collision detection!!)
    }

}
