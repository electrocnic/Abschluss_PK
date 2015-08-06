import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;
import java.util.regex.Pattern;

public class Slang {

    /*fg: Stack for the operations */
    private static Stack<Value> myStack;

    //Class for StringValue
    private static class StringValue implements Value {
        String s;
        public StringValue(String s){
            this.s = s;
        }
        public String toS(){
            return s;
        }
        public int toI(){
            return 0;
        }
    }
    //Class for IntValue
    private static class IntValue implements Value {
        int i;
        public IntValue(int i){
            this.i = i;
        }
        public String toS(){
            return Integer.toString(i);
        }
        public int toI(){
            return i;
        }
    }
    /*/fg*/

    public static void main(String[] args) {
        /*fg: Stack initialization */
        myStack = new Stack<Value>();
        /*/fg*/

        //andreas
        boolean err=false;
        AtomScanner sc = null;

        try {
            sc = new AtomScanner(System.in);

            while (sc.hasNext() && !err) {
                if (sc.hasNextString()) {
                    /*fg:
                     * default:{ System.out.println("STRING: " + sc.nextString());} */
                    myStack.push(new StringValue(sc.nextString()));
                    /*/fg*/
                 }
                else if (sc.hasNextInteger()) {
                    /*fg:
                     * default: System.out.println("INTEGER: " + sc.nextInteger());*/
                    myStack.push(new IntValue(sc.nextInteger()));
                    /*/fg*/
                }
                else if (sc.hasNextAtom()) {
                    /*fg:
                     * default: System.out.println("ATOM: " + sc.nextAtom());*/
                    String s = sc.nextAtom(); //andreas
                    switch( s ) {
                        case ".s": printStack(); break;
                        case "prin": f_prin(); break;
                        case "print": f_print(); break;
                        case "sub": f_sub(); break;
                        case "add": f_add(); break;
                        case "mul": f_mul(); break;
                        case "div": f_div(); break;
                        case "mod": f_mod(); break;
                        case "read": f_read(); break;
                        case "write": f_write(); break;
                        case "ask": f_ask(); break;
                        case "askn": f_askn(); break;
                        case "append": f_append(); break;
                        case "skip-to": f_skipto(); break;
                        case "skip-n": f_skipn(); break;
                        case "trunc": f_trunc(); break;
                        case "copy-to": f_copyto(); break;
                        case "dup": f_dup(); break;
                        case "pop": f_pop(); break;
                        default: throw new ParserError("unknown atom '" + s + "'"); //andreas
                    }
                    /*/fg*/
                }
                else {
                    throw new LexerError("UNKNOWN: " + sc.next()); //andreas
                }
            }
        } catch (ParserError ex) {
            System.out.println(ex);
        } catch (LexerException ex) {
            System.out.println("[LEXER ERROR] (" + ex.getPosition() + ") " + ex.getMessage());
        } catch (EvalError ex) {
            System.out.println(ex);
        }
    }

    /*fg: Atom functions */

    /**
     * Prints the content of the stack.
     * Example: 1 2 3 .s
     * --Stack:
     * 3
     * 2
     * 1
     * --bottom
     */
    private static void printStack(){
        Stack<Value> tmpStack = new Stack<Value>();
        System.out.print("--Stack:\n");
        while(!myStack.empty()){
            tmpStack.push(myStack.peek());
            System.out.print(myStack.pop().toS() + "\n");
        }
        System.out.println("--bottom");
        while(!tmpStack.empty()){
            myStack.push(tmpStack.pop());
        }
    }

    /**
     * Removes the top element of the stack and prints it to the console without a new line.
     */
    private static void f_prin(){
        System.out.print(myStack.pop().toS());
    }

    /**
     * Removes the top element of the stack and prints it to the console with a new line.
     */
    private static void f_print(){
        System.out.println(myStack.pop().toS());
    }

    /**
     * Removes the top two elements of the stack and pushes element1 minus element2 to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_sub(){
        int t = myStack.pop().toI();
        myStack.push(new IntValue(myStack.pop().toI() - t));
    }

    /**
     * Removes the top two elements of the stack and pushes element1 plus element2 to the stack.
     */
    private static void f_add(){
        myStack.push(new IntValue(myStack.pop().toI() + myStack.pop().toI()));
    }

    /**
     * Removes the top two elements of the stack and pushes element1 multiplicated by element2 to the stack.
     */
    private static void f_mul(){
        myStack.push(new IntValue(myStack.pop().toI() * myStack.pop().toI()));
    }

    /**
     * Removes the top two elements of the stack and pushes element1 divided by element2 (Integer division) to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_div(){
        int t = myStack.pop().toI();
        myStack.push(new IntValue(myStack.pop().toI() / t));
    }

    /**
     * Removes the top two elements of the stack and pushes the rest of the division [element1 divided by element2] to
     * the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_mod(){
        int t = myStack.pop().toI();
        myStack.push(new IntValue(myStack.pop().toI() % t));
    }

    /**
     * Removes the top element from the stack. If it is a String beginning with "http://" or "https://", the content
     * of the website will be pushed to the stack as a String. Otherwise it will be assumed the element represents a
     * file, which will be opened and its content will be pushed to the stack.
     * @throws EvalError If the File could not be opened or could not be found.
     */
    private static void f_read() throws EvalError {
        String s = myStack.pop().toS();
        Pattern p = Pattern.compile("\\Ahttps?://");
        if (p.matcher(s).find()){
            try {
                if (s.charAt(4) == 's') {
                    s = sendGet(s, true);
                } else {
                    s = sendGet(s, false);
                }
                myStack.push(new StringValue(s));
            } catch (IOException e){
                throw new EvalError(e.getMessage());
            }
        } else {
            try {
                String currLine;
                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(new FileReader(s));
                while ((currLine = br.readLine()) != null){
                    sb.append(currLine);
                }
                myStack.push(new StringValue(sb.toString()));
            } catch (FileNotFoundException e){
                throw new EvalError("file not found: " + s);
            } catch (IOException e){
                throw new EvalError(e.getMessage());
            }
        }
    }

    /**
     * Overwrites the file [element1] with the string [element2] and removes those two elements from the stack.
     * Element1 is the element which was added first and popped last.
     * @throws EvalError If the file could not be opened or found.
     */
    private static void f_write() throws EvalError{
        try {
            String s = myStack.pop().toS();
            File f = new File(myStack.pop().toS());
            if (!f.exists()){
                f.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
            bw.write(s);
            bw.close();
        } catch (IOException e) {
            throw new EvalError(e.getMessage());
        }
    }

    /**
     * Prompts a JDialog which asks for Input by the User.
     * The input of the user will be pushed to the stack as a String.
     */
    private static void f_ask(){
        String inputValue = JOptionPane.showInputDialog(myStack.pop().toS());
        myStack.push(new StringValue(inputValue));
    }

    /**
     * Prompts a JDialog which asks for Input by the User.
     * The input of the user will be pushed to the stack as an Integer Value.
     */
    private static void f_askn(){
        int inputValue = Integer.parseInt(JOptionPane.showInputDialog(myStack.pop().toS()));
        myStack.push(new IntValue(inputValue));
    }

    /**
     * Removes the top two elements of the stack and appends element2 to element1. The result will be pushed to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_append(){
        String s = myStack.pop().toS();
        myStack.push(new StringValue(myStack.pop().toS() + s));
    }

    /**
     * Removes the top two elements of the stack and skips in element1 to the index, where element1 contains the String
     * element2 for the first time. The rest of element1 beginning at this index will be pushed to the stack.
     * If element1 does not contain the String element2, an empty string will be pushed to the stack.
     * If the index is negative, bigger than the length of element1, an empty string will be pushed to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_skipto(){ //andreas
        String s = myStack.pop().toS();
        String s2 = myStack.pop().toS();
        try {
            myStack.push(new StringValue(s2.substring(s2.indexOf(s))));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }

    /**
     * Removes the top two elements of the stack and skips in element1 to the index [element2]. The rest of element1
     * beginning at this index will be pushed to the stack. Element2 must be an integer value.
     * If the index is negative, bigger than the length of element1 or not an integer value, an empty string will be
     * pushed to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_skipn(){ //andreas
        Value s = myStack.pop();
        String s2 = myStack.pop().toS();
        try {
            myStack.push(new StringValue(s2.substring(s.toI())));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }

    /**
     * Removes the top two elements of the stack and cuts element1 at the index [element2].
     * The first part of element1 will be pushed to the stack (the index [element2] inclusive).
     * If the index is negative or not a number, an empty string will be pushed to the stack.
     * If the index is bigger than the length of element1, element1 will be pushed to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_trunc(){ //andreas
        Value s = myStack.pop();
        String s2 = myStack.pop().toS();
        try {
            if( s.toI()>s2.length()-1 ) myStack.push(new StringValue(s2));
            else myStack.push(new StringValue(s2.substring(0, s.toI())));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }

    /**
     * Removes the top two elements of the stack. If element1 contains element2, element1 will be cut until the
     * occurence of element2 and the first part of the result, element2 exclusive, will be pushed to the stack.
     * If element1 does not contain element2, an empty string will be pushed to the stack.
     * Element1 is the element which was added first and popped last.
     */
    private static void f_copyto(){ //andreas
        String s = myStack.pop().toS();
        String s2 = myStack.pop().toS();
        try {
            myStack.push(new StringValue(s2.substring(0, s2.indexOf(s))));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }

    /**
     * Duplicates the top element of the stack. Therefore, peeks the stack and pushes back to the stack.
     */
    private static void f_dup(){ //andreas
        myStack.push(myStack.peek());
    }

    /**
     * Removes the top element of the stack.
     */
    private static void f_pop(){ //andreas
        myStack.pop();
    }

    //Helper for GET request
    private static String sendGet(String url, boolean isSecure) throws IOException{
        URL obj = new URL(url);
        HttpURLConnection con;
        if (isSecure) {
            con = (HttpsURLConnection) obj.openConnection();
        } else {
            con = (HttpURLConnection) obj.openConnection();
        }
        con.setRequestMethod("GET");
        //idk if necessary
        con.setRequestProperty("User-agent", "Mozilla/5.0");

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response =  new StringBuffer();
        while((inputLine = br.readLine())!= null){
            response.append(inputLine);
        }
        br.close();
        return response.toString();
    }
    /*/fg*/

}
