/**
 * Created by Filip on 8/5/2015.
 */
public class ParserError extends LexerError {

    public ParserError(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return "[PARSER ERROR] " + getMessage();
    }
}
