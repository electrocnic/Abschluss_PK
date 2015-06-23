import java.awt.*;

/**
 * Created by Andreas Wiesinger [1429087] on 22.06.2015.
 */
public class Enemy extends Thread {

    private Ball ball;
    private Board board;
    private boolean running;
    private int speed;
    private Mondrian controller;
    private Thread thread;
    private boolean repaint;

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * This class is a thread and calculates the behaviour of an enemy.
     * It will update the graphics given as argument.
     * This constructor starts the Thread!
     * @param ball The enemy ball.
     * @throws NullPointerException
     */
    public Enemy(Mondrian controller, Ball ball, Board board, int speed) throws NullPointerException{
        if( ball==null ) throw new NullPointerException("Ball is null");
        if( board==null ) throw new NullPointerException("Board is null");
        this.board=board;
        this.ball=ball;
        thread = new Thread(this);
        this.speed=speed;
        if(this.speed<1) this.speed=1;
        this.controller = controller;
        repaint=false;
        running=true;
        thread.start();
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Paints the enemy.
     */
    public void run() {
        while(running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { e.printStackTrace(); }

            //this.setRepaint(false);
            //board.repaint();

            //change coordinates
            //goes in overall direction of blue ball
            //changes direction randomly but not too often
            //straight and diagonal movement allowed

            System.out.println("Enemy.run:  Calling move()");
            if (this.move()) {
                System.out.println("Enemy.run:  Game Lost");
                this.stopEnemy();
                controller.lose();
            }

            System.out.println("Enemy.run:  Calling repaint");
            //this.setRepaint(true);
           board.repaint();

            //TODO: make movement that it trys to go to the player.
            //TODO: move: change direction doesnt work quite well :D
        }
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter of repaint. Determines for the paintComponent-Method whether the enemy should be cleared or painted.
     * @return repaint (boolean)
     */
    /**
    public synchronized boolean isRepaint() {
        return repaint;
    }*/

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Sets the repaint value to true or false.
     * @param rep
     *//*
    private synchronized void setRepaint(boolean rep) {
        this.repaint=rep;
    }*/

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter of the color of this enemy (for repaint reasons)
     * @return
     */
    public Color getColor() {
        return ball.getColor();
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Detects collisions by invoking isCollided();
     * Possible collisions: Walls(will be detected in Ball.move()), Fields, Player, other enemies.
     * @return True, if enemy hit the player, false otherwise.
     */
    public boolean move() { //TODO: check if move works...
        boolean b=false;
        for( Enemy enemy : controller.getEnemies() ) { //try all other enemies.
            System.out.println("Enemy.move:  Enemy checks collision with enemy of list.");
            if( !this.equals(enemy) ) {
                if (Mondrian.isCollided(this.ball, enemy.getBall())) {
                    System.out.println("Enemy.move:  Enemy collided with another enemy");
                    b = true;
                    break;
                }
            }
        }
        for( int i=0; i<controller.getFields().size(); i++) { //try all fields
            //TODO: compute intersect between ball and field
            System.out.println("Enemy.move:  Checks collision of enemy with an field.");
        }
        if( Mondrian.isCollided(this.ball, controller.getPlayer() ) ) { //Enemy won
            System.out.println("Enemy.move:  Enemy collided with Player: Game lost.");
            return true;
        }
        System.out.println("Enemy.move:  ball.move is called");
        ball.move(speed, b );
        return false;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for this enemy's ball.
     * @return ball
     */
    public Ball getBall() {
        return ball;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Stops the enemy.
     */
    public void stopEnemy() {
        this.running=false;
    }
}
