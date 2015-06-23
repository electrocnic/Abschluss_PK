import java.awt.*;

/**
 * Created by Andreas Wiesinger [1429087] on 22.06.2015.
 * Collaborator: Filip Gerat
 */
public class Ball {

    private Color color;
    private int size;
    private int x;
    private int y;
    private int movement_x;
    private int movement_y;
    private int way_length;

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Creates a new ball.
     * @param color Color of the ball.
     * @param size Size (diameter in pixels).
     * @param x X Coordinate of Ball.
     * @param y Y Coordinate of Ball.
     * @throws InvalidBallException if the size is < 1.
     */
    public Ball( Color color, int size, int x, int y ) throws InvalidBallException {
        if(size<1) throw new InvalidBallException("Ball-Size has to be at least 1");
        if(x<0 || x>Mondrian.MAX_FIELD_SIZE_X) throw new InvalidBallException("Coordinate x is not in window: " +x);
        if(y<0 || y>Mondrian.MAX_FIELD_SIZE_Y) throw new InvalidBallException("Coordinate y is not in window: " +y);
        this.size=size;
        this.color=color;
        this.x=x;
        this.y=y;
        this.way_length=10;
        this.movement_x=1;
        this.movement_y=0;
    }

    /** Random Color */
    public Ball(int size, int x, int y) throws InvalidBallException{
        this(Mondrian.randomColor(), size, x, y);
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for color of ball.
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for the x coordinate.
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for the y coordinate.
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter of the size of the ball.
     * @return size
     */
    public int getSize() {
        return this.size;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Decreases the way_length. If way_length is 0, movement_x and y will get a new random direction.
     * @param way the length of the way.
     */
    public void move(int way, boolean collided) {
        System.out.println("Ball.move:  Old coords of this ball: x="+x+", y="+y);
        if( way_length<=0 ) {
            System.out.println("Ball.move:  Way_length <= 0");
            way_length=(int)Math.random()*Mondrian.MAX_FIELD_SIZE_X/3+1; //at minimum a way of 1 pixels
            System.out.println("Ball.move:  Way_length = " +way_length);
            int n=(int)Math.random()*10;
            for(int i=0; this.movement_y==0&&i<n; i++) {
                this.movement_y=(int)Math.random()*2-1; //can be -1, 0, +1
            }
            while(this.movement_x==0 && this.movement_y==0) this.movement_x=(int)Math.random()*2-1;
            System.out.println("Ball.move:  Movement_x = "+movement_x +", movement_y = "+movement_y);
        }
        way_length--;
        System.out.println("Ball.move:  Way_length-- ==> way_length = "+way_length);
        if(collided) { //collision detected else where
            //invert direction.
            System.out.println("Ball.move:  Collided. Invert Direction:");
            this.movement_y=-this.movement_y;
            this.movement_x=-this.movement_x;
            System.out.println("Ball.move:  movement_x = "+movement_x+", movement_y = "+movement_y);
        }
        System.out.println("Ball.move:  Movement_x before Bounds-Collision-Detection: "+movement_x);
        while(x+movement_x*way<0 || x+movement_x*way>Mondrian.MAX_FIELD_SIZE_X) { //collision detected here with bounds (higher priority)
            System.out.println("Ball.move:  Collision with bounds detected: x, trying to resolve");
            if(this.movement_x==1) {
                this.movement_x=-1;
                continue;
            }
            this.movement_x++;
        }
        System.out.println("Ball.move:  Movement_x after Bounds-Collision-Detection: "+movement_x);
        System.out.println("Ball.move:  Movement_y before Bounds-Collision-Detection: "+movement_y);
        while(y+movement_y*way<0 || y+movement_y*way>Mondrian.MAX_FIELD_SIZE_Y) {
            System.out.println("Ball.move:  Collision with bounds detected: y, trying to resolve");
            if(this.movement_y==1) {
                this.movement_y=-1;
                continue;
            }
            this.movement_y++;
        }
        System.out.println("Ball.move:  Movement_y after Bounds-Collision-Detection: "+movement_y);
        //now apply the move
        this.x += movement_x*way;
        this.y += movement_y*way;
        System.out.println("Ball.move:  New coords of this ball: x="+x+", y="+y);
    }
}
