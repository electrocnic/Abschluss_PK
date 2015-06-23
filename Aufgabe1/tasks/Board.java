import java.awt.*;
import javax.swing.JPanel;

public class Board extends JPanel {

    //changed by Andreas Wiesinger [1429087]
    private final int WIDTH = Mondrian.MAX_FIELD_SIZE_X;
    private final int HEIGHT = Mondrian.MAX_FIELD_SIZE_Y;
    private Mondrian controller;

    public Board( Mondrian controller ) {
        super();

        this.controller=controller;

        //added by Andreas Wiesinger [1429087]
        this.addKeyListener( controller );

        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Updates the board.
     * @param g
     */
    @Override
    public void paintComponent( Graphics g ) { //TODO: better method???
        super.paintComponent(g);
        //g.setColor(Color.WHITE);  //clear the whole thing
        //g.fillOval(0,0,Mondrian.MAX_FIELD_SIZE_X, Mondrian.MAX_FIELD_SIZE_Y);
        //g.fillOval(50,50,50,50);
        for( Enemy enemy : controller.getEnemies() ) {         //draw enemies new
            /*
            if( !enemy.isRepaint() ) {
                g.setColor(Color.WHITE);
            }else if( enemy.isRepaint() ) {
                g.setColor(enemy.getColor());
            }*/
            g.setColor(enemy.getColor()); //*** remove if the above block is valid !!
            g.fillOval(enemy.getBall().getX() - enemy.getBall().getSize() / 2, //draw enemy
                    enemy.getBall().getY() - enemy.getBall().getSize() / 2,
                    enemy.getBall().getSize(),
                    enemy.getBall().getSize());
        }

        Player player=controller.getPlayer(); // redraw player
        /*
        if( !player.isRepaint() ) {
            g.setColor(Color.WHITE);
        }else if( player.isRepaint() ) {
            g.setColor( player.getColor() );
        }*/
        g.setColor( player.getColor() ); //*** remove if the above block is valid!!
        g.fillOval(player.getX() - player.getSize() / 2, //draw enemy
                player.getY() - player.getSize() / 2,
                player.getSize(),
                player.getSize());

        //redraw fields
        for( int i=controller.getFields().size()-1; i>=0; i-- ) { //will be drawn opposite direction, looks better.
            Field field = controller.getFields().get(i);
            g.setColor(field.getColor());
            g.fillRect(field.getStart_x(),
                    field.getStart_y(),
                    field.getEnd_x()-field.getStart_x(),
                    field.getEnd_y()-field.getStart_y());
            g.setColor(Color.BLACK);
            g.drawRect(field.getStart_x(),
                    field.getStart_y(),
                    field.getEnd_x()-field.getStart_x(),
                    field.getEnd_y()-field.getStart_y());
        }
    }

}
