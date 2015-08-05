/**
 * Created by Filip on 8/5/2015.
 */
public class EvalError extends LexerError {
    public EvalError(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return "[EVAL ERROR] " + getMessage();
    }
}
