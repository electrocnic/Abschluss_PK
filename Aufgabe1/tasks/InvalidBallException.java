/**
 * Created by Andreas Wiesinger [1429087] on 22.06.2015.
 */
public class InvalidBallException extends Exception{
    public InvalidBallException() {
        super("Invalid Ball Size");
    }

    public InvalidBallException(String s) {
        super(s);
    }
}
