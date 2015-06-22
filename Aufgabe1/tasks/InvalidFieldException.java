/**
 * Created by Andreas on 22.06.2015.
 */
public class InvalidFieldException extends Exception {
    public InvalidFieldException() {
        super("Invalid Field size or corner-coordinates.");
    }

    public InvalidFieldException(String s) {
        super(s);
    }
}
