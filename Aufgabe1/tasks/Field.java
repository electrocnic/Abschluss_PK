import java.awt.*;

/**
 * Created by Andreas Wiesinger [1429087] on 22.06.2015.
 *
 * This class stores the upper left and lower right corner of a field.
 * The idea is to paint those fields which where first at last, so they overlap optically, but the whole field
 * actually is stored, not just the seen parts of it.
 */
public class Field {

    private int start_x;
    private int start_y;
    private int end_x;
    private int end_y;
    private Color color;

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Creates a new Field (probably painted by the blue ball).
     * @param start_x Upper left start-coordinate x of the field.
     * @param start_y Upper left start-coordinate y of the field.
     * @param end_x Lower right end-coordinate x of the field.
     * @param end_y Lower right end-coordinate y of the field.
     * @throws InvalidFieldException if the coordinates are not in the window.
     */
    public Field( int start_x, int start_y, int end_x, int end_y ) throws InvalidFieldException {
        if( start_x < 0 || start_x > Mondrian.MAX_FIELD_SIZE_X ) throw new InvalidFieldException("start_x < 0 or > " + Mondrian.MAX_FIELD_SIZE_X);
        if( start_y < 0 || start_y > Mondrian.MAX_FIELD_SIZE_Y ) throw new InvalidFieldException("start_y < 0 or > " + Mondrian.MAX_FIELD_SIZE_Y);
        if( end_x < 0 || end_x > Mondrian.MAX_FIELD_SIZE_X ) throw new InvalidFieldException("end_x < 0 or > " + Mondrian.MAX_FIELD_SIZE_X);
        if( end_y < 0 || end_y > Mondrian.MAX_FIELD_SIZE_Y ) throw new InvalidFieldException("end_y < 0 or > " + Mondrian.MAX_FIELD_SIZE_Y);
        this.start_x=start_x;
        this.start_y=start_y;
        this.end_x=end_x;
        this.end_y=end_y;
        this.color = Mondrian.randomColor();
    }

    /** Same like above, just with color of the field. Created by Andreas Wiesinger [1429087]*/
    public Field( int start_x, int start_y, int end_x, int end_y, Color color ) throws InvalidFieldException {
        this(start_x, start_y, end_x, end_y);
        this.color=color;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for Color.
     * @return
     */
    public Color getColor() {
        return color;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for the x coordinate of this Field.
     * @return
     */
    public int getStart_x() {
        return start_x;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for the y coordinate of this field.
     * @return
     */
    public int getStart_y() {
        return start_y;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for the end-x coordinate of this field.
     * @return
     */
    public int getEnd_x() {
        return end_x;
    }

    /** Created by Andreas Wiesinger [1429087] on 22.06.2015.
     * Getter for the end-y coordinate of this field.
     * @return
     */
    public int getEnd_y() {
        return end_y;
    }

}
