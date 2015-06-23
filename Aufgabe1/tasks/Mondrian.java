import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Mondrian extends JFrame implements ActionListener, KeyListener {

    //added by Andreas Wiesinger [1429087]
    public final static int MAX_FIELD_SIZE_X = 200;
    public final static int MAX_FIELD_SIZE_Y = 200;

    private Board board;
    private java.util.List<Enemy> enemies;
    private java.util.List<Field> fields;
    private Player player;
    private boolean lose;

    private boolean running=true;
    /** Changed: Andreas Wiesinger [1429087]
     * Constructor of Controller, which creates 1 Enemy with Red Color and Size 12, player size 8.
     * Player starts at 0,0;
     * Enemies start at middle of window.
     */
    public Mondrian() {
        this( false, new int[]{12}, 8, Color.BLUE );
    }

    /** Changed: Andreas Wiesinger [1429087]
     * Constructor for the controller.
     * @param randomColor Wether the colors of the enemies should be random or all red.
     * @param enemieSizes Sizes of enemies in an array. If null, size=3 for all
     * @param playerSize Size of players ball.
     */
    public Mondrian( boolean randomColor, int[] enemieSizes, int playerSize, Color playerColor ) {
        board = new Board(this);
        add(board);
        enemies = new ArrayList<Enemy>();
        fields = new ArrayList<Field>();
        lose=false;

        setResizable(false);
        pack();

        setTitle("Mondrian");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        try { //adds enemies to the list (they will start working instantly)
            if(enemieSizes==null) enemieSizes=new int[]{3};
            for( int i=0; i<enemieSizes.length; i++ ) {
                enemies.add( new Enemy(
                    this,
                    new Ball(
                        (randomColor?Mondrian.randomColor():Color.RED),
                        enemieSizes[i],
                        MAX_FIELD_SIZE_X / 2, MAX_FIELD_SIZE_Y / 2),
                    board,
                    2)
                );
            }

            player = new Player(
                    (playerColor==null?Mondrian.randomColor():playerColor),
                    (playerSize<3||playerSize>Mondrian.MAX_FIELD_SIZE_X/10)?3:playerSize,
                    0,
                    0); //Create player (no thread, is controlled by listeners)

        }catch( InvalidBallException e ) { e.printStackTrace(); }



    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for enemies.
     * @return
     */
    public java.util.List<Enemy> getEnemies() {
        return this.enemies;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for player
     * @return
     */
    public Player getPlayer() {
        return this.player;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Adds a new field to the list of fields.
     * @param field
     */
    public void addField( Field field ) {
        this.fields.add(field);
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for list of fields.
     * @return fields
     */
    public List<Field> getFields() {
        return this.fields;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Detects collision between two balls.
     * @return true if they collide at their edges, false otherwise
     */
    public static boolean isCollided(Ball ball1, Ball ball2) {
        int size1=ball1.getSize()/2;
        int size2=ball2.getSize()/2;
        double xDif = ball1.getX() - ball2.getX();
        double yDif = ball1.getY() - ball2.getY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        return distanceSquared < (size1 + size2) * (size1 + size2);
    }

    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mondrian window = new Mondrian();
                //Run the infinite painting lopp
                /*while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    window.board.repaint();
                }*/
            }
        });
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * @return random color.
     */
    public static Color randomColor() {
        return new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Updates the players ball if an arrow key has been pressed.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(!lose) {
            int keyCode = e.getKeyCode();
            switch (keyCode) { //TODO: handle player movement.
                case KeyEvent.VK_UP:
                    // handle up
                    break;
                case KeyEvent.VK_DOWN:
                    // handle down
                    break;
                case KeyEvent.VK_LEFT:
                    // handle left
                    break;
                case KeyEvent.VK_RIGHT:
                    // handle right
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Invoke, when an enemy hit the player. The controller will stop all enemies and will end the game.
     */
    public void lose() {
        for( Enemy enemy : enemies ) {
            enemy.stopEnemy();
        }
        lose=true;

    }
}
