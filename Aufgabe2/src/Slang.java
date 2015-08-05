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

    private static void f_prin(){
        System.out.print(myStack.pop().toS());
    }
    private static void f_print(){
        System.out.println(myStack.pop().toS());
    }
    private static void f_sub(){
        int t = myStack.pop().toI();
        myStack.push(new IntValue(myStack.pop().toI() - t));
    }
    private static void f_add(){
        myStack.push(new IntValue(myStack.pop().toI() + myStack.pop().toI()));
    }
    private static void f_mul(){
        myStack.push(new IntValue(myStack.pop().toI() * myStack.pop().toI()));
    }
    private static void f_div(){
        int t = myStack.pop().toI();
        myStack.push(new IntValue(myStack.pop().toI() / t));
    }
    private static void f_mod(){
        int t = myStack.pop().toI();
        myStack.push(new IntValue(myStack.pop().toI() % t));
    }
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
    private static void f_ask(){
        String inputValue = JOptionPane.showInputDialog(myStack.pop().toS());
        myStack.push(new StringValue(inputValue));
    }
    private static void f_askn(){
        int inputValue = Integer.parseInt(JOptionPane.showInputDialog(myStack.pop().toS()));
        myStack.push(new IntValue(inputValue));
    }
    private static void f_append(){
        String s = myStack.pop().toS();
        myStack.push(new StringValue(myStack.pop().toS() + s));
    }
    private static void f_skipto(){ //andreas
        String s = myStack.pop().toS();
        String s2 = myStack.pop().toS();
        try {
            myStack.push(new StringValue(s2.substring(s2.indexOf(s))));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }
    private static void f_skipn(){ //andreas
        Value s = myStack.pop();
        String s2 = myStack.pop().toS();
        try {
            myStack.push(new StringValue(s2.substring(s.toI())));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }
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
    private static void f_copyto(){ //andreas
        String s = myStack.pop().toS();
        String s2 = myStack.pop().toS();
        try {
            myStack.push(new StringValue(s2.substring(0, s2.indexOf(s))));
        }catch( IndexOutOfBoundsException e ) {
            myStack.push(new StringValue(""));
        }
    }
    private static void f_dup(){ //andreas
        myStack.push(myStack.peek());
    }
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
